package compiler.phase;

import compiler.common.logger.*;

/**
 * The abstract compiler phase.
 * 
 * All concrete compiler phases should be implemented as a subclass of this
 * class in order to ensure they can be properly logged.
 */
public abstract class Phase implements AutoCloseable {

	/** The logger used to produce the log of this phase. */
	public final Logger logger;

	/**
	 * Constructs a new phase of a compiler. If logging of this phase has been
	 * requested, it prepares a logger using the phase name for naming the XML and
	 * XSL files as well as for the topmost XML element within the XML file.
	 * 
	 * @param phaseName The phase name.
	 */
	protected Phase(final String phaseName) {
		final String loggedPhase = compiler.Compiler.cmdLineOptValue("--logged-phase");
		if (loggedPhase.matches(phaseName + "|all")) {
			// Prepare the name of the xml file.
			String xmlFileName = compiler.Compiler.cmdLineOptValue("--xml");
			if (xmlFileName == null) {
				xmlFileName = compiler.Compiler.cmdLineOptValue("--src-file-name"). //
						replaceFirst("\\.[^./]*$", "") + "-" + phaseName + ".xml";
			}

			// Prepare the name of the supporting xsl file.
			String xslDirName = compiler.Compiler.cmdLineOptValue("--xsl");
			if (xslDirName == null) {
				xslDirName = "";
			}

			logger = new Logger(phaseName, xmlFileName, xslDirName + phaseName + ".xsl");
		} else {
			logger = null;
		}
	}

	@Override
	public void close() {
		if (logger != null)
			logger.close();
	}

}
