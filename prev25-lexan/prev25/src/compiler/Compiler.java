package compiler;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

import org.antlr.v4.runtime.BaseErrorListener;

import compiler.common.report.*;
import compiler.phase.lexan.*;
import compiler.phase.lexan.LexAn.LocLogToken;

/**
 * The Prev25 compiler.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class Compiler {

	/** (Unused but included to keep javadoc happy.) */
	private Compiler() {
		throw new Report.InternalError();
	}

	/** Names of command line options. */
	private static final HashSet<String> cmdLineOptNames = new HashSet<String>(Arrays.asList("--src-file-name",
			"--dst-file-name", "--target-phase", "--logged-phase", "--xml", "--xsl", "--dev-mode"));

	/** Values of command line options indexed by their command line option name. */
	private static final HashMap<String, String> cmdLineOptValues = new HashMap<String, String>();

	/** All valid phases name of the compiler. */
	private static final Vector<String> phaseNames = new Vector<String>(Arrays.asList("none", "all", "lexan"));

	/**
	 * Returns the value of a command line option.
	 *
	 * @param cmdLineOptName Command line option name.
	 * @return Command line option value.
	 */
	public static final String cmdLineOptValue(final String cmdLineOptName) {
		return cmdLineOptValues.get(cmdLineOptName);
	}

	/** Specifies whether the compiler is run in the development mode. */
	private static boolean devMode = false;

	/**
	 * Returns information on whether the compiler is run in the development mode.
	 * 
	 * @return {@code true} if the compiler is run in the development mode,
	 *         {@code false} otherwise.
	 */
	public static final boolean devMode() {
		return devMode;
	}

	/**
	 * The compiler's main driver running all phases one after another.
	 * 
	 * @param opts Command line arguments (see {@link compiler}).
	 */
	public static void main(final String[] opts) {
		try {
			Report.info("This is Prev25 compiler:");

			// Scan the command line.
			for (int optc = 0; optc < opts.length; optc++) {
				if (opts[optc].startsWith("--")) {
					// Command line option.
					final String cmdLineOptName = opts[optc].replaceFirst("=.*", "");
					final String cmdLineOptValue = opts[optc].replaceFirst("^[^=]*=", "");
					if (!cmdLineOptNames.contains(cmdLineOptName)) {
						Report.warning("Unknown command line option '" + cmdLineOptName + "'.");
						continue;
					}
					if (cmdLineOptValues.get(cmdLineOptName) == null) {
						// Not yet successfully specified command line option.

						// Check the value of the command line option.
						if (cmdLineOptName.equals("--target-phase") && (!phaseNames.contains(cmdLineOptValue))) {
							Report.warning("Illegal phase specification in '" + opts[optc] + "' ignored.");
							continue;
						}
						if (cmdLineOptName.equals("--logged-phase") && (!phaseNames.contains(cmdLineOptValue))) {
							Report.warning("Illegal phase specification in '" + opts[optc] + "' ignored.");
							continue;
						}
						if (cmdLineOptName.equals("--dev-mode") && (!cmdLineOptValue.matches("on|off"))) {
							Report.warning("Illegal value in '" + opts[optc] + "' ignored.");
							continue;
						}

						cmdLineOptValues.put(cmdLineOptName, cmdLineOptValue);
					} else {
						// Repeated specification of a command line option.
						Report.warning("Command line option '" + opts[optc] + "' ignored.");
						continue;
					}
				} else {
					// Source file name.
					if (cmdLineOptValues.get("--src-file-name") == null) {
						cmdLineOptValues.put("--src-file-name", opts[optc]);
					} else {
						Report.warning("Source file '" + opts[optc] + "' ignored.");
						continue;
					}
				}
			}
			// Check the command line option values.
			if (cmdLineOptValues.get("--src-file-name") == null) {
				try {
					// Source file has not been specified, so consider using the last modified
					// prev25 file in the working directory.
					final String currWorkDir = new File(".").getCanonicalPath();
					FileTime latestTime = FileTime.fromMillis(0);
					Path latestPath = null;
					for (final Path path : java.nio.file.Files.walk(Paths.get(currWorkDir))
							.filter(path -> path.toString().endsWith(".prev25")).toArray(Path[]::new)) {
						final FileTime time = Files.getLastModifiedTime(path);
						if (time.compareTo(latestTime) > 0) {
							latestTime = time;
							latestPath = path;
						}
					}
					if (latestPath != null) {
						cmdLineOptValues.put("--src-file-name", latestPath.toString());
						Report.warning("Source file not specified, using '" + latestPath.toString() + "'.");
					}
				} catch (final IOException __) {
					throw new Report.Error("Source file not specified.");
				}

				if (cmdLineOptValues.get("--src-file-name") == null) {
					throw new Report.Error("Source file not specified.");
				}
			}
			if (cmdLineOptValues.get("--dst-file-name") == null) {
				cmdLineOptValues.put("--dst-file-name",
						// TODO: Insert the appropriate file suffix.
						cmdLineOptValues.get("--src-file-name").replaceFirst("\\.[^./]*$", ".TODO"));
			}
			if (cmdLineOptValues.get("--target-phase") == null)
				cmdLineOptValues.put("--target-phase", "all");
			if (cmdLineOptValues.get("--logged-phase") == null)
				cmdLineOptValues.put("--logged-phase", "none");
			devMode = ("on".equals(cmdLineOptValues.get("--dev-mode")));

			// Carry out the compilation phase by phase.
			while (true) {

				if (cmdLineOptValues.get("--target-phase").equals("none"))
					break;

				// Lexical analysis.
				if (cmdLineOptValues.get("--target-phase").equals("lexan")
						|| cmdLineOptValues.get("--target-phase").equals("all")) {
					try (final LexAn lexan = new LexAn()) {
						LocLogToken token = lexan.lexer.nextToken();
						while (token.getType() != LexAn.LocLogToken.EOF) {
							// Report.info(String.valueOf(token.getType()) + "token: " + token.toString());
							// token.log(lexan.logger);
							// if (token.getType() == 52) {
							// 	throw new Report.Error(token.location(), "Error caused by invalid token: "+token.getText());
							// }
							token = lexan.lexer.nextToken();
						}
					}
					break;
				}

				// Do not loop... ever.
				break;
			}

			// Let's hope we ever come this far.
			// But beware:
			// 1. The generated translation of the source file might be erroneous :-o
			// 2. The source file might not be what the programmer intended it to be ;-)
			Report.info("Done.");
		} catch (final Report.Error error) {
			System.err.println(error.getMessage());
			System.exit(1);
		}
	}
}
