package compiler.phase.seman;

import java.util.*;

import compiler.common.report.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.Node;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.abstr.AST.PtrType;
import compiler.phase.seman.NameResolver.Mode;

/**
 * Type checker.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class TypeChecker implements AST.FullVisitor<TYP.Type, NameResolver.Mode> {

	/** Constructs a new name checker. */
	public TypeChecker() {
	}

	private final HashSet<TYP.NameType> visited = new HashSet<TYP.NameType>();

	static boolean isMainDefined = false;
	@Override
	public TYP.Type visit(Nodes<? extends Node> nodes, Mode mode) {
		// // // Report.info("TypeChecker");
		for (final Node node : nodes) {
			if ((node != null) || (!compiler.Compiler.devMode())) {
				node.accept(this, Mode.RESOLVE);
			}
		}
		for (final Node node : nodes) {
			if ((node != null) || (!compiler.Compiler.devMode())) {
				if (node instanceof AST.DefFunDefn defFunDefn) {
					TYP.Type resType = SemAn.isType.get(defFunDefn.type);
					for (AST.Stmt stmt : defFunDefn.stmts) {
						if (stmt instanceof AST.ReturnStmt retStmt) {
							TYP.Type retType = SemAn.ofType.get(retStmt.retExpr);
							if (retType == null)
								Report.warning(retStmt, "Return type is null");
							if (resType == null)
								Report.warning(defFunDefn, "Function type is null");
							if (!compTypes(resType, retType)) {
								throw new Report.Error(retStmt, "Return type does not match function type");
							}
						}
					}
				}
			}
		}
		if (!isMainDefined) {
			throw new Report.Error(nodes, "Main function is not defined");
		}
		return null;
	}

	// ----- Expressions -----

	@Override
	public TYP.Type visit(AST.ArrExpr arrExpr, Mode mode) {
		// // Report.info("arrExpr");

		// // Report.info(arrExpr, "arr expr");
		TYP.Type b = arrExpr.idx.accept(this, mode);
		TYP.Type a = arrExpr.arrExpr.accept(this, mode).actualType();
		// // // Report.info("we made it here. a is of type " + a.elemType);
		var exprIsAddr = SemAn.isAddr.get(arrExpr);
		if (exprIsAddr == null)
			exprIsAddr = true;
			// throw new Report.Error(arrExpr, "Array isAddr is null");
		if(!exprIsAddr)
			throw new Report.Error(arrExpr, "Array must have an address");
		// // Report.info("here as well " + b);
		if (a instanceof TYP.ArrType) {
			if (b == TYP.IntType.type) {
				// // Report.info("made it to the end");
				if (a instanceof TYP.NameType)
					a = a.actualType();
				SemAn.isConst.put(arrExpr, false);
				SemAn.isAddr.put(arrExpr, true);
				return SemAn.ofType.put(arrExpr, ((TYP.ArrType) a).elemType);
			} else {
				throw new Report.Error(arrExpr, "Not an int");
			}
		} else {
			throw new Report.Error(arrExpr, "Not an array");
		}
	}

	@Override
	public TYP.Type visit(AST.AtomExpr atomExpr, Mode mode) {
		// // Report.info("atomExpr");
		TYP.Type type;
		var isConst = false;
		var isAddr = false;
		switch (atomExpr.type) {
			case CHAR:
				type = TYP.CharType.type;
				isConst = true;
				isAddr = false;
				break;
			case BOOL:
				type = TYP.BoolType.type;
				isConst = true;
				isAddr = false;
				break;
			case INT:
				type = TYP.IntType.type;
				isConst = true;
				isAddr = false;
				break;
			case PTR:
				type = TYP.PtrType.type;
				isConst = true;
				isAddr = false;
				break;
			case STR:
				type = TYP.PtrType.type;
				isConst = true;
				isAddr = false;
				break;
			default:
				Report.warning(atomExpr, "Atom type " + atomExpr.toString() + " was set to null ");
				type = null;
				break;
		}
		SemAn.isConst.put(atomExpr, isConst);
		SemAn.isAddr.put(atomExpr, isAddr);
		return SemAn.ofType.put(atomExpr, type);
	}

	@Override
	public TYP.Type visit(AST.BinExpr binExpr, Mode mode) {
		// // Report.info("binExpr");
		TYP.Type lefType = binExpr.fstExpr.accept(this, mode);
		TYP.Type rigType = binExpr.sndExpr.accept(this, mode);
		// // Report.info(binExpr, "BinExpr: " + binExpr.oper);
		var leftIsConst = SemAn.isConst.get(binExpr.fstExpr);
		var rightIsConst = SemAn.isConst.get(binExpr.sndExpr);
		if (leftIsConst == null)
		{
			Report.warning(binExpr, "leftisconst null");
			leftIsConst = false;
		}
		if (rightIsConst == null){
		rightIsConst = true;
		Report.warning(binExpr, "right isconst null");}
		// if (compTypes(lefType, rigType)) {
		if(lefType.actualType().equals(rigType.actualType())) {
			switch (binExpr.oper) {
				case OR:
				case AND:
					if (lefType.actualType() == TYP.BoolType.type){
						SemAn.isConst.put(binExpr, leftIsConst && rightIsConst);
						SemAn.isAddr.put(binExpr, false);
						return SemAn.ofType.put(binExpr, TYP.BoolType.type);
					} else
						throw new Report.Error(binExpr,
								"Operator " + binExpr.oper + " is not defined for type " + lefType);
				case EQU:
				case NEQ:
				case LTH:
				case GTH:
				case LEQ:
				case GEQ:
					SemAn.isConst.put(binExpr, leftIsConst && rightIsConst);
					SemAn.isAddr.put(binExpr, false);
					return SemAn.ofType.put(binExpr, TYP.BoolType.type);
				case MUL:
				case DIV:
				case MOD:
				case ADD:
				case SUB:
					if (lefType.actualType() == TYP.IntType.type){
						SemAn.isConst.put(binExpr, leftIsConst && rightIsConst);
						SemAn.isAddr.put(binExpr, false);
						return SemAn.ofType.put(binExpr, TYP.IntType.type);
					} else
						throw new Report.Error(binExpr,
								"Operator " + binExpr.oper + " is not defined for type " + lefType);
				default:
					break;
			}
		}
		// else {
		// new Report.Error(binExpr, "Operator " + binExpr.oper + " is not defined for
		// type " + lefType + binExpr.oper + rigType);
		// }
		return null;
	}

	@Override
	public TYP.Type visit(AST.CallExpr callExpr, Mode mode) {
		// // Report.info("callExpr");
		ArrayList<TYP.Type> parTypes = new ArrayList<TYP.Type>();
		TYP.Type funType = callExpr.funExpr.accept(this, mode);
		for (AST.Expr arg : callExpr.argExprs)
			parTypes.add(arg.accept(this, mode));
		if (funType instanceof TYP.NameType)
			funType = ((TYP.NameType) funType).actualType();
		if (!(funType instanceof TYP.FunType))
			throw new Report.Error(callExpr, "Function expression must be of type function");
		if (parTypes.size() != ((TYP.FunType) funType).parTypes.size())
			throw new Report.Error(callExpr, "Function call has wrong number of arguments");
		SemAn.isConst.put(callExpr, false);
		SemAn.isAddr.put(callExpr, false);
		return SemAn.ofType.put(callExpr, ((TYP.FunType) funType).resType);
	}

	@Override
	public TYP.Type visit(AST.CastExpr castExpr, Mode mode) {
		// // Report.info("castExpr");
		castExpr.expr.accept(this, mode);
		TYP.Type castType = SemAn.isType.get(castExpr.type);

		var exprIsAddr = SemAn.isAddr.get(castExpr.expr);
		var exprIsConst = SemAn.isConst.get(castExpr.expr);
		if(exprIsAddr == null)
			Report.warning(castExpr, "Cast expression isAddr is null");
		if(exprIsConst == null)
			Report.warning(castExpr, "Cast expression isConst is null");
		if (castType == null)
			throw new Report.Error(castExpr, "Cannot resolve type " + castExpr.type);
		if (castType instanceof TYP.NameType)
			castType = ((TYP.NameType) castType).actualType();
		SemAn.isConst.put(castExpr, exprIsConst);
		SemAn.isAddr.put(castExpr, exprIsAddr);
		return SemAn.ofType.put(castExpr, castType);
	}

	@Override
	public TYP.Type visit(AST.CompExpr compExpr, Mode mode) {
		// // Report.info("compExpr");
		// // Report.info(compExpr, "failed here " + compExpr.name);
		TYP.Type type = compExpr.recExpr.accept(this, mode);
		TYP.RecType recType = null;

		try {

			recType = (TYP.RecType) type.actualType();
		} catch (ClassCastException e) {
			throw new Report.Error(compExpr, "Can only acces types of struct and union");
		}

		AST.RecType def = TypeResolver.recDef.get(recType);
		// var exprIsAddr = SemAn.isAddr.get(def);
		var exprIsAddr = true;
		// if(exprIsAddr == null || !exprIsAddr)
		if(!exprIsAddr)
			throw new Report.Error(compExpr, "Record must have an address");
		TYP.Type compType = null;
		for (AST.CompDefn compDefn : def.comps) {
			if (compDefn.name.equals(compExpr.name)) {
				compType = SemAn.isType.get(compDefn.type);
				break;
			}

		}



		if (compType == null)
			throw new Report.Error(compExpr, "Variable does not contain component called " + compExpr.name);
		SemAn.isConst.put(compExpr, false);
		SemAn.isAddr.put(compExpr, true);
		return SemAn.ofType.put(compExpr, compType);
	}

	@Override
	public TYP.Type visit(AST.NameExpr nameExpr, Mode mode) {
		// // Report.info("nameExpr");
		AST.Defn defn = SemAn.defAt.get(nameExpr);
		TYP.Type nameType = SemAn.ofType.get(defn);
		if (defn == null)
		{
			Report.warning("defn in nameexpr null");
		}
		SemAn.isAddr.put(nameExpr, true);
		SemAn.isConst.put(nameExpr, false);
		return SemAn.ofType.put(nameExpr, nameType);
	}

	@Override
	public TYP.Type visit(AST.PfxExpr pfxExpr, Mode mode) {
		// // Report.info("pfxExpr");
		TYP.Type exprType = pfxExpr.subExpr.accept(this, mode).actualType();
		// // Report.info(pfxExpr, "before instanceof " + exprType.toString());
		// if (exprType instanceof TYP.NameType)
		// exprType = ((TYP.NameType) exprType).actualType();
		// // Report.info(pfxExpr, "after instanceof " + exprType.toString());
		// if (exprType == TYP.IntType.type || exprType == TYP.BoolType.type || exprType
		// == TYP.PtrType.type || exprType == TYP.) {
		var isConst = false;
		var isAddr = false;
		var exprIsAddr = SemAn.isAddr.get(pfxExpr.subExpr);
		switch (pfxExpr.oper) {
			case ADD:
			case SUB:
				isConst = true;
				isAddr = false;
				if (!(exprType instanceof TYP.IntType))
					throw new Report.Error(pfxExpr,
							"Unary operator " + pfxExpr.oper + " can only be used with variable of type int");
				break;
			case NOT:
				isConst = true;
				isAddr = false;
				if (!(exprType instanceof TYP.BoolType))
					throw new Report.Error(pfxExpr,
							"Unary operator " + pfxExpr.oper + " can only be used with variable of type bool");
				break;
			case PTR:
				isConst = false;
				isAddr = false;
				if (exprType instanceof TYP.VoidType)
					throw new Report.Error(pfxExpr, "Cannot have pointer of type null");
				if(exprIsAddr == null || !exprIsAddr)
					throw new Report.Error(pfxExpr, "Pointer must have an address");
				SemAn.isConst.put(pfxExpr, isConst);
				SemAn.isAddr.put(pfxExpr, isAddr);
				return SemAn.ofType.put(pfxExpr, new TYP.PtrType(exprType));
			default:
				break;
		}
		SemAn.isConst.put(pfxExpr, isConst);
		SemAn.isAddr.put(pfxExpr, isAddr);
		return SemAn.ofType.put(pfxExpr, exprType);
	}

	@Override
	public TYP.Type visit(AST.SfxExpr sfxExpr, Mode mode) {
		// // // Report.info("sfxExpr");
		// // Report.info(sfxExpr, "SfxExpr: " + sfxExpr.oper);
		TYP.Type temp = sfxExpr.subExpr.accept(this, Mode.RESOLVE);
		var exprIsConst = SemAn.isConst.get(sfxExpr.subExpr);
		if(exprIsConst == null || exprIsConst)
			throw new Report.Error(sfxExpr, "Dereferencing a constant value");
		if (temp instanceof TYP.NameType)
			temp = ((TYP.NameType) temp).actualType();
		if (temp == null)
			Report.warning(sfxExpr, "temp is null");
		else
			// Report.info(sfxExpr, ((TYP.PtrType) temp).baseType.toString());
		if (temp.actualType() == TYP.VoidType.type) {
			throw new Report.Error(sfxExpr, "Unable to dereference a null pointer");
		}
		SemAn.isAddr.put(sfxExpr, true);
		SemAn.isConst.put(sfxExpr, false);
		return SemAn.ofType.put(sfxExpr, ((TYP.PtrType) temp).baseType);
		// return null;
	}

	@Override
	public TYP.Type visit(AST.SizeExpr sizeExpr, Mode mode) {
		// // Report.info("sizeExpr");
		TYP.Type exprType = sizeExpr.type.accept(this, mode);
		if (exprType != TYP.VoidType.type) {
			SemAn.isConst.put(sizeExpr, true);
			SemAn.isAddr.put(sizeExpr, false);
			return SemAn.ofType.put(sizeExpr, TYP.IntType.type);
		} else
			throw new Report.Error(sizeExpr, "Cannot get size of type void");
	}

	@Override
	public TYP.Type visit(AST.AssignStmt assignStmt, Mode mode) {
		// // Report.info(assignStmt, "assignStmt");
		// TYP.Type dst = SemAn.ofType.get(assignStmt.dstExpr); // left side
		TYP.Type dst = assignStmt.dstExpr.accept(this, mode); // left side
		TYP.Type src = assignStmt.srcExpr.accept(this, mode); // right side
		if (dst == null)
			Report.warning(assignStmt, "dst is null");
		if (src == null)
			Report.warning(assignStmt, "src is null");
		if (!(compTypes(src, dst))) {
		// if (!(src instanceof TYP.PtrType && src instanceof TYP.RecType && dst instanceof TYP.PtrType && src instanceof TYP.RecType) && !(compTypes(src, dst))) {
		// if(src.actualType().equals(dst.actualType())) {
			throw new Report.Error(assignStmt,
					"Cannot assign " + src.actualType() + " to variable of type " + dst.actualType());
		}
		// // // Report.info("ELJNKHNJJN");
		// return SemAn.ofType.put(assignStmt, src.actualType());
		return null;
	}

	@Override
	public TYP.Type visit(AST.IfThenStmt ifThenStmt, Mode arg) {
		TYP.Type condType = ifThenStmt.condExpr.accept(this, arg);
		// Report.warning(ifThenStmt.condExpr.toString());
		// if (condType != TYP.BoolType.type) {
		if(!(compTypes(condType, TYP.BoolType.type))) {
			if (condType != null)
				// Report.info(condType.toString());
			throw new Report.Error(ifThenStmt, "Conditional statement should be of bool");
		}

		ifThenStmt.thenStmt.accept(this, arg);
		return null;
	}

	@Override
	public TYP.Type visit(AST.IfThenElseStmt ifThenElseStmt, Mode arg) {
		TYP.Type condType = ifThenElseStmt.condExpr.accept(this, arg);
		// if (condType != TYP.BoolType.type) {
		if(!(compTypes(condType, TYP.BoolType.type))) {
		throw new Report.Error(ifThenElseStmt, "Conditional statement should be of bool");
		}

		ifThenElseStmt.thenStmt.accept(this, arg);
		ifThenElseStmt.elseStmt.accept(this, arg);
		return null;
	}

	@Override
	public TYP.Type visit(AST.ReturnStmt returnStmt, Mode arg) {
		returnStmt.retExpr.accept(this, arg);
		return null;
	}

	@Override
	public TYP.Type visit(AST.WhileStmt whileStmt, Mode arg) {
		TYP.Type condType = whileStmt.condExpr.accept(this, arg);
		// if (condType != TYP.BoolType.type) {
		if(!(compTypes(condType, TYP.BoolType.type))) {
			throw new Report.Error(whileStmt, "Conditional statement should be of bool");
		}
		whileStmt.stmts.accept(this, arg);
		return null;
	}

	// @Override
	// public TYP.Type visit(AST.WhileStmt whileStmt, Mode mode) {
	// // Report.info(whileStmt, "whileStmt");
	// TYP.Type condType = whileStmt.condExpr.accept(this, mode);
	// if (condType == null)
	// Report.warning(whileStmt, "condType is null");
	// if (!(condType instanceof TYP.BoolType))
	// throw new Report.Error(whileStmt, "Condition of while statement must be of
	// type bool");
	// whileStmt.stmts.accept(this, mode);
	// return SemAn.ofType.put(whileStmt, TYP.BoolType.type);
	// }

	public boolean compTypes(TYP.Type a, TYP.Type b) {
		if (a instanceof TYP.NameType) {
			a = a.actualType();
			// // Report.info("what5");

		}
		if (b instanceof TYP.NameType) {
			b = b.actualType();
			// // Report.info("what6");

		}
		// // // Report.info("first arg val:" + a.toString() + " second arg val:" +
		// b.toString());
		if (a instanceof TYP.RecType a2 && b instanceof TYP.RecType b2) {
			// // Report.info("what9");

			boolean neki = true;
			if (a2.compTypes.size() != b2.compTypes.size()) {
				// // Report.info("what4");
				return false;
			}

			for (int i = 0; i < a2.compTypes.size(); i++) {
				var prviComp = a2.compTypes.get(i);
				var drugComp = b2.compTypes.get(i);
				if (prviComp instanceof TYP.NameType) {
					prviComp = prviComp.actualType();
				}
				if (drugComp instanceof TYP.NameType) {
					drugComp = drugComp.actualType();
				}

				if (!compTypes(prviComp, drugComp)) {
					neki = false;
					break;
				}
				;

			}
			return neki;
		} else if (a instanceof TYP.PtrType a2 && b instanceof TYP.PtrType b2) {
			// Report.info("Hallo, the pointer values are:" + a2.actualType().toString() + "," + b2.actualType().toString());
			if ((b2.baseType.actualType() instanceof TYP.VoidType)
					&& (a2.baseType.actualType() instanceof TYP.VoidType))
				return true;

			if ((a2.baseType.actualType() instanceof TYP.VoidType)) {

				return false;
			}
			if (b2.baseType.actualType() instanceof TYP.VoidType) {
				return true;
			}
			return compTypes(a2.baseType, b2.baseType);
		} else if (a instanceof TYP.IntType && b instanceof TYP.IntType) {
			return true;
		} else if (a instanceof TYP.BoolType && b instanceof TYP.BoolType) {
			return true;
		} else if (a instanceof TYP.CharType && b instanceof TYP.CharType) {
			return true;
		} else if (b instanceof TYP.FunType a2) {
			return compTypes(a, a2.resType);
		} else if (a instanceof TYP.ArrType a2 && b instanceof TYP.ArrType b2) {
			return compTypes(a2.elemType, b2.elemType);
		} else if (a.actualType().equals(b.actualType())) {
			return true;
		}

		return false;
	}
}
