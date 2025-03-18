package compiler.phase.seman;

import java.util.*;

import compiler.common.report.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.*;

/**
 * Name resolver.
 * 
 * The name resolver connects each node of a abstract syntax tree where a name
 * is used with the node where it is defined. The only exceptions are struct and
 * union component names which are connected with their definitions by the type
 * resolver. The results of the name resolver are stored in
 * {@link compiler.phase.seman.SemAn#defAt}.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class NameResolver implements AST.FullVisitor<Object, NameResolver.Mode> {

	/** Constructs a new name resolver. */
	public NameResolver() {
	}

	/** Two passes of name resolving. */
	protected enum Mode {
		/** The first pass: declaring names. */
		DECLARE,
		/** The second pass: resolving names. */
		RESOLVE,
	}

	/** The symbol table. */
	private SymbTable symbTable = new SymbTable();

	// *** TODO ***
    private AST.Defn resolveName(String name, Node node) {
        try{
            SemAn.defAt.put(node, symbTable.fnd(name));

        }catch(NameResolver.SymbTable.CannotFndNameException e){
            throw new Report.Error(node.location(), "Can't find declaration: " + name);
        }
        return null;
    }

		// ----- Trees -----

	@Override
	public Object visit(Nodes<? extends Node> nodes, Mode mode) {
		for (final Node node : nodes) {
			if ((node != null) || (!compiler.Compiler.devMode())) {
				// Report.info("======== node declare mode started ========");
				node.accept(this, Mode.DECLARE);
				// Report.info("======== node declare mode done ========");
			}
		}
		for (final Node node : nodes) {
			if ((node != null) || (!compiler.Compiler.devMode())) {
				// Report.info("======== node resolve mode started ========");
				node.accept(this, Mode.RESOLVE);
				// Report.info("======== node resolve mode done ========");
			}
		}
		return null;
	}

	// ----- Definitions -----

	@Override
	public Object visit(TypDefn typDefn, Mode mode) {
		if ((typDefn.type != null) || (!compiler.Compiler.devMode())) {
			if (mode == Mode.DECLARE) {
				try {
					symbTable.ins(typDefn.name, typDefn);
					Report.info("Type: " + typDefn.name + " declared");
				} catch (Exception e) {
					throw new Report.Error(typDefn.location(),
							"Couldn't define type: `" + typDefn.name + "`. Is type already defined?");
				}
			}
			typDefn.type.accept(this, mode);
		}
		return null;
	}

	@Override
	public Object visit(VarDefn varDefn, Mode mode) {
		if ((varDefn.type != null) || (!compiler.Compiler.devMode())){
			if (mode == Mode.DECLARE) {
				try {
					symbTable.ins(varDefn.name, varDefn);
					Report.info("Variable: " + varDefn.name + " declared");
				} catch (Exception e) {
					throw new Report.Error(varDefn.location(),
							"Couldn't define variable: `" + varDefn.name + "`. Is variable already defined?");
				}
			}
			varDefn.type.accept(this, mode);
		}
		return null;
	}

	@Override
	public Object visit(DefFunDefn defFunDefn, Mode mode) {
		if (defFunDefn.name != null || (!compiler.Compiler.devMode())) {
			if (mode == Mode.DECLARE) {
				try {
					symbTable.ins(defFunDefn.name, defFunDefn);
					Report.info("Function: " + defFunDefn.name + " declared");
				} catch (Exception e) {
					throw new Report.Error(defFunDefn.location(),
							"Couldn't define function: `" + defFunDefn.name + "`. Is function already defined?");
				}
			}
		}
		if ((defFunDefn.pars != null) || (!compiler.Compiler.devMode())) {
			try {
				symbTable.newScope();
				Report.info("New scope created for:" + defFunDefn.name);
			} catch (Exception e) {
				throw new Report.Error(defFunDefn.location(), "Couldn't create new scope");
			}
			// if (mode == Mode.RESOLVE) {
				defFunDefn.pars.accept(this, mode);
			// }
		}
		if ((defFunDefn.type != null) || (!compiler.Compiler.devMode()))
		{
			if (mode == Mode.RESOLVE) {
				defFunDefn.type.accept(this, mode);
			}
		}
		if ((defFunDefn.stmts != null) || (!compiler.Compiler.devMode()))
		{
			if (mode == Mode.RESOLVE) {
				defFunDefn.stmts.accept(this, mode);
			}
		}
		try {
			symbTable.oldScope();
		} catch (Exception e) {
			throw new Report.Error(defFunDefn.location(), "Couldn't destroy scope");
		}
		return null;
	}

	@Override
	public Object visit(ExtFunDefn extFunDefn, Mode mode) {
		if (extFunDefn.name != null || (!compiler.Compiler.devMode())) {
			if (mode == Mode.DECLARE) {
				try {
					symbTable.ins(extFunDefn.name, extFunDefn);
					Report.info("Function: " + extFunDefn.name + " declared");
				} catch (Exception e) {
					throw new Report.Error(extFunDefn.location(),
							"Couldn't define function: `" + extFunDefn.name + "`. Is function already defined?");
				}
			}
		}
		if ((extFunDefn.pars != null) || (!compiler.Compiler.devMode())) {
			try {
				symbTable.newScope();
				Report.info("New scope created for:" + extFunDefn.name);
			} catch (Exception e) {
				throw new Report.Error(extFunDefn.location(), "Couldn't create new scope");
			}
			// if (mode == Mode.RESOLVE) {
				extFunDefn.pars.accept(this, mode);
			// }
		}
		if ((extFunDefn.type != null) || (!compiler.Compiler.devMode())) {
			if (mode == Mode.RESOLVE) {
				extFunDefn.type.accept(this, mode);
			}
		}
		try {
			symbTable.oldScope();
		} catch (Exception e) {
			throw new Report.Error(extFunDefn.location(), "Couldn't destroy scope");
		}
		return null;
	}

	@Override
	public Object visit(ParDefn parDefn, Mode mode) {
		if ((parDefn.type != null) || (!compiler.Compiler.devMode())) {
			if (mode == Mode.DECLARE) {
				try {
					symbTable.ins(parDefn.name, parDefn);
					Report.info("Parameter: " + parDefn.name + " declared");
				} catch (Exception e) {
					throw new Report.Error(parDefn.location(),
							"Couldn't define parameter: `" + parDefn.name + "`. Is parameter already defined?");
				}
			}
			parDefn.type.accept(this, mode);
		}
		return null;
	}


	// ----- Statements -----

	@Override
	public Object visit(LetStmt letStmt, Mode mode) {
		if ((letStmt.defns != null) || (!compiler.Compiler.devMode())) {
			if (mode == Mode.RESOLVE) {
				try {
					symbTable.newScope();
					Report.info("New scope created for:" + letStmt.defns.location());
				} catch (Exception e) {
					throw new Report.Error(letStmt.location(), "Couldn't create new scope");
				}

				// We need to modify how we process the definitions
				// First, process all type definitions in DECLARE mode
				Report.info("Processing type definitions");
				for (Node node : letStmt.defns) {
					if (node instanceof TypDefn) {
						node.accept(this, Mode.DECLARE);
					}
				}
				Report.info("Processing type definitions done");
				// Then process the rest of the definitions in DECLARE mode
				for (Node node : letStmt.defns) {
					if (!(node instanceof TypDefn)) {
						node.accept(this, Mode.DECLARE);
					}
				}
				Report.info("Resolving variable definitions");
				for (Node node : letStmt.defns) {
						node.accept(this, Mode.RESOLVE);
				}
			}
		}

		if ((letStmt.stmts != null) || (!compiler.Compiler.devMode())) {
			if (mode == Mode.RESOLVE) {
				letStmt.stmts.accept(this, mode);
			}
		}

		if (mode == Mode.RESOLVE) {
			try {
				Report.info("Scope destroyed for:" + letStmt.defns);
				symbTable.oldScope();
			} catch (Exception e) {
				throw new Report.Error(letStmt.location(), "Couldn't destroy scope");
			}
		}

		return null;
	}
	
	@Override
	public Object visit(AssignStmt assignStmt, Mode mode) {
		if ((assignStmt.dstExpr != null) || (!compiler.Compiler.devMode()))
			assignStmt.dstExpr.accept(this, mode);
		if ((assignStmt.srcExpr != null) || (!compiler.Compiler.devMode()))
			assignStmt.srcExpr.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(ExprStmt callStmt, Mode mode) {
		if ((callStmt.expr != null) || (!compiler.Compiler.devMode()))
			callStmt.expr.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(IfThenStmt ifThenStmt, Mode mode) {
		if ((ifThenStmt.condExpr != null) || (!compiler.Compiler.devMode()))
			ifThenStmt.condExpr.accept(this, mode);
		if ((ifThenStmt.thenStmt != null) || (!compiler.Compiler.devMode()))
			ifThenStmt.thenStmt.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(IfThenElseStmt ifThenElseStmt, Mode mode) {
		if ((ifThenElseStmt.condExpr != null) || (!compiler.Compiler.devMode()))
			ifThenElseStmt.condExpr.accept(this, mode);
		if ((ifThenElseStmt.thenStmt != null) || (!compiler.Compiler.devMode()))
			ifThenElseStmt.thenStmt.accept(this, mode);
		if ((ifThenElseStmt.elseStmt != null) || (!compiler.Compiler.devMode()))
			ifThenElseStmt.elseStmt.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(ReturnStmt returnStmt, Mode mode) {
		if ((returnStmt.retExpr != null) || (!compiler.Compiler.devMode()))
			returnStmt.retExpr.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(WhileStmt whileStmt, Mode mode) {
		if ((whileStmt.condExpr != null) || (!compiler.Compiler.devMode()))
			whileStmt.condExpr.accept(this, mode);
		if ((whileStmt.stmts != null) || (!compiler.Compiler.devMode()))
			whileStmt.stmts.accept(this, mode);
		return null;
	}

	// ----- Types -----

	@Override
	public Object visit(AtomType atomType, Mode mode) {
		return null;
	}

	@Override
	public Object visit(ArrType arrType, Mode mode) {
		if ((arrType.elemType != null) || (!compiler.Compiler.devMode()))
			arrType.elemType.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(PtrType ptrType, Mode mode) {
		if ((ptrType.baseType != null) || (!compiler.Compiler.devMode()))
			ptrType.baseType.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(StrType strType, Mode mode) {
		if ((strType.comps != null) || (!compiler.Compiler.devMode()))
			strType.comps.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(UniType uniType, Mode mode) {
		if ((uniType.comps != null) || (!compiler.Compiler.devMode()))
			uniType.comps.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(FunType funType, Mode mode) {
		if ((funType.parTypes != null) || (!compiler.Compiler.devMode()))
			funType.parTypes.accept(this, mode);
		if ((funType.resType != null) || (!compiler.Compiler.devMode()))
			funType.resType.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(NameType nameType, Mode mode) {
		if (mode == Mode.RESOLVE) {
			try {
				resolveName(nameType.name, nameType);
			} catch (Exception e) {
				throw new Report.Error(nameType.location(), "Couldn't find type: `" + nameType.name + "`");
			}
			
		}
		return null;
	}

	// ----- Expressions -----

	@Override
	public Object visit(ArrExpr arrExpr, Mode mode) {
		if ((arrExpr.arrExpr != null) || (!compiler.Compiler.devMode()))
			arrExpr.arrExpr.accept(this, mode);
		if ((arrExpr.idx != null) || (!compiler.Compiler.devMode()))
			arrExpr.idx.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(AtomExpr atomExpr, Mode mode) {
		return null;
	}

	@Override
	public Object visit(BinExpr binExpr, Mode mode) {
		if ((binExpr.fstExpr != null) || (!compiler.Compiler.devMode()))
			binExpr.fstExpr.accept(this, mode);
		if ((binExpr.sndExpr != null) || (!compiler.Compiler.devMode()))
			binExpr.sndExpr.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(CallExpr callExpr, Mode mode) {
		if ((callExpr.funExpr != null) || (!compiler.Compiler.devMode()))
			callExpr.funExpr.accept(this, mode);
		if ((callExpr.argExprs != null) || (!compiler.Compiler.devMode()))
			callExpr.argExprs.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(CastExpr castExpr, Mode mode) {
		if ((castExpr.type != null) || (!compiler.Compiler.devMode()))
			castExpr.type.accept(this, mode);
		if ((castExpr.expr != null) || (!compiler.Compiler.devMode()))
			castExpr.expr.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(CompExpr compExpr, Mode mode) {
		if ((compExpr.recExpr != null) || (!compiler.Compiler.devMode()))
			compExpr.recExpr.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(NameExpr nameExpr, Mode mode) {
		if (mode == Mode.RESOLVE) {
			try {
				resolveName(nameExpr.name, nameExpr);
			} catch (Exception e) {
				throw new Report.Error(nameExpr.location(), "Couldn't find name: `" + nameExpr.name + "`");
			}
			
		}
		return null;
	}

	@Override
	public Object visit(PfxExpr pfxExpr, Mode mode) {
		if ((pfxExpr.subExpr != null) || (!compiler.Compiler.devMode()))
			pfxExpr.subExpr.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(SfxExpr sfxExpr, Mode mode) {
		if ((sfxExpr.subExpr != null) || (!compiler.Compiler.devMode()))
			sfxExpr.subExpr.accept(this, mode);
		return null;
	}

	@Override
	public Object visit(SizeExpr sizeExpr, Mode mode) {
		if ((sizeExpr.type != null) || (!compiler.Compiler.devMode()))
			sizeExpr.type.accept(this, mode);
		return null;
	}
	// ===== SYMBOL TABLE =====

	/**
	 * A symbol table.
	 */
	public class SymbTable {

		/**
		 * A symbol table record denoting a definition of a name within a certain scope.
		 */
		private class ScopedDefn {

			/** The depth of the scope the definition belongs to. */
			public final int depth;

			/** The definition. */
			public final AST.Defn defn;

			/**
			 * Constructs a new record denoting a definition of a name within a certain
			 * scope.
			 * 
			 * @param depth The depth of the scope the definition belongs to.
			 * @param defn  The definition.
			 */
			public ScopedDefn(int depth, AST.Defn defn) {
				this.depth = depth;
				this.defn = defn;
			}

		}

		/**
		 * A mapping of names into lists of records denoting definitions at different
		 * scopes. At each moment during the lifetime of a symbol table, the definition
		 * list corresponding to a particular name contains all definitions that name
		 * within currently active scopes: the definition at the inner most scope is the
		 * first in the list and is visible, the other definitions are hidden.
		 */
		private final HashMap<String, LinkedList<ScopedDefn>> allDefnsOfAllNames;

		/**
		 * The list of scopes. Each scope is represented by a list of names defined
		 * within it.
		 */
		private final LinkedList<LinkedList<String>> scopes;

		/** The depth of the currently active scope. */
		private int currDepth;

		/** Whether the symbol table can no longer be modified or not. */
		private boolean lock;

		/**
		 * Constructs a new symbol table.
		 */
		public SymbTable() {
			allDefnsOfAllNames = new HashMap<String, LinkedList<ScopedDefn>>();
			scopes = new LinkedList<LinkedList<String>>();
			currDepth = 0;
			lock = false;
			newScope();
		}

		/**
		 * Returns the depth of the currently active scope.
		 * 
		 * @return The depth of the currently active scope.
		 */
		public int currDepth() {
			return currDepth;
		}

		/**
		 * Inserts a new definition of a name within the currently active scope or
		 * throws an exception if this name has already been defined within this scope.
		 * Once the symbol table is locked, any attempt to insert further definitions
		 * results in an internal error.
		 * 
		 * @param name The name.
		 * @param defn The definition.
		 * @throws CannotInsNameException Thrown if this name has already been defined
		 *                                within the currently active scope.
		 */
		public void ins(String name, AST.Defn defn) throws CannotInsNameException {
			if (lock)
				throw new Report.InternalError();

			LinkedList<ScopedDefn> allDefnsOfName = allDefnsOfAllNames.get(name);
			if (allDefnsOfName == null) {
				allDefnsOfName = new LinkedList<ScopedDefn>();
				allDefnsOfAllNames.put(name, allDefnsOfName);
			}

			if (!allDefnsOfName.isEmpty()) {
				ScopedDefn defnOfName = allDefnsOfName.getFirst();
				if (defnOfName.depth == currDepth)
					throw new CannotInsNameException();
			}

			allDefnsOfName.addFirst(new ScopedDefn(currDepth, defn));
			scopes.getFirst().addFirst(name);
		}

		/**
		 * Returns the currently visible definition of the specified name. If no
		 * definition of the name exists within these scopes, an exception is thrown.
		 * 
		 * @param name The name.
		 * @return The definition.
		 * @throws CannotFndNameException Thrown if the name is not defined within the
		 *                                currently active scope or any scope enclosing
		 *                                it.
		 */
		public AST.Defn fnd(String name) throws CannotFndNameException {
			LinkedList<ScopedDefn> allDefnsOfName = allDefnsOfAllNames.get(name);
			if (allDefnsOfName == null)
				throw new CannotFndNameException();

			if (allDefnsOfName.isEmpty())
				throw new CannotFndNameException();

			return allDefnsOfName.getFirst().defn;
		}

		/** Used for selecting the range of scopes. */
		public enum XScopeSelector {
			/** All live scopes. */
			ALL,
			/** Currently active scope. */
			ACT,
		}

		/**
		 * Constructs a new scope within the currently active scope. The newly
		 * constructed scope becomes the currently active scope.
		 */
		public void newScope() {
			if (lock)
				throw new Report.InternalError();

			currDepth++;
			scopes.addFirst(new LinkedList<String>());
		}

		/**
		 * Destroys the currently active scope by removing all definitions belonging to
		 * it from the symbol table. Makes the enclosing scope the currently active
		 * scope.
		 */
		public void oldScope() {
			if (lock)
				throw new Report.InternalError();

			if (currDepth == 0)
				throw new Report.InternalError();

			for (String name : scopes.getFirst()) {
				allDefnsOfAllNames.get(name).removeFirst();
			}
			scopes.removeFirst();
			currDepth--;
		}

		/**
		 * Prevents further modifications of this symbol table.
		 */
		public void lock() {
			lock = true;
		}

		/**
		 * An exception thrown when the name cannot be inserted into a symbol table.
		 */
		@SuppressWarnings("serial")
		public class CannotInsNameException extends Exception {

			/**
			 * Constructs a new exception.
			 */
			private CannotInsNameException() {
			}

		}

		/**
		 * An exception thrown when the name cannot be found in the symbol table.
		 */
		@SuppressWarnings("serial")
		public class CannotFndNameException extends Exception {

			/**
			 * Constructs a new exception.
			 */
			private CannotFndNameException() {
			}

		}

	}

}

