package compiler.phase.seman;

import java.util.*;

import compiler.common.report.*;
import compiler.phase.abstr.*;
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
	// ----- Expressions -----

	@Override
	public TYP.Type visit(AST.ArrExpr arrExpr, Mode mode) {
		TYP.Type indexType = arrExpr.idx.accept(this, mode);
		TYP.Type arrType = arrExpr.arrExpr.accept(this, mode);
		if (!(indexType instanceof TYP.IntType))
			throw new Report.Error(arrExpr, "Array index must be of type int");
		// if (!(arrType instanceof TYP.ArrType))
		// 	throw new Report.Error(arrExpr, "Array expression must be of type array");
		return SemAn.ofType.put(arrExpr, arrType);
	}

	@Override
	public TYP.Type visit(AST.AtomExpr atomExpr, Mode mode) {
		TYP.Type type;
		switch(atomExpr.type){
			case CHAR:
				type = TYP.CharType.type;
				break;
			case BOOL:
				type = TYP.BoolType.type;
				break;
			case INT:
				type = TYP.IntType.type;
				break;
			case PTR:
				type = TYP.PtrType.type;
				break;
			case STR:
				type = TYP.PtrType.type;
				break;
			default:
				type = null;
				break;
		}
		return SemAn.ofType.put(atomExpr, type);
	}

	@Override
	public TYP.Type visit(AST.BinExpr binExpr, Mode mode) {
		TYP.Type lefType = binExpr.fstExpr.accept(this, mode);
		TYP.Type rigType = binExpr.sndExpr.accept(this, mode);
		Report.info(binExpr, "BinExpr: " + binExpr.oper);
		if (lefType.actualType().equals(rigType.actualType()) && !(lefType instanceof TYP.RecType)) {
			switch (binExpr.oper) {
				case OR:
				case AND:
					if (lefType.actualType() == TYP.BoolType.type)
						return SemAn.ofType.put(binExpr, TYP.BoolType.type);
					else
						throw new Report.Error(binExpr,
								"Operator " + binExpr.oper + " is not defined for type " + lefType);
				case EQU:
				case NEQ:
				case LTH:
				case GTH:
				case LEQ:
				case GEQ:
					return SemAn.ofType.put(binExpr, TYP.BoolType.type);
				case MUL:
				case DIV:
				case MOD:
				case ADD:
				case SUB:
					if (lefType.actualType() == TYP.IntType.type)
						return SemAn.ofType.put(binExpr, TYP.IntType.type);
					else
						throw new Report.Error(binExpr,
								"Operator " + binExpr.oper + " is not defined for type " + lefType);
				default:
					break;
			}
		}
		else {
			new Report.Error(binExpr, "Operator " + binExpr.oper + " is not defined for type " + lefType + binExpr.oper + rigType);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.CallExpr callExpr, Mode mode) {
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
		return SemAn.ofType.put(callExpr, ((TYP.FunType) funType).resType);
	}

	@Override
	public TYP.Type visit(AST.CastExpr castExpr, Mode mode) {
		castExpr.expr.accept(this, mode);
		TYP.Type castType = SemAn.isType.get(castExpr.type);

		if (castType == null)
			throw new Report.Error(castExpr, "Cannot resolve type " + castExpr.type);
		if (castType instanceof TYP.NameType)
			castType = ((TYP.NameType) castType).actualType();
		return SemAn.ofType.put(castExpr, castType);
	}

	@Override
	public TYP.Type visit(AST.CompExpr compExpr, Mode mode) {
		compExpr.recExpr.accept(this, mode);
		TYP.Type compType = SemAn.ofType.get(compExpr);
		if(compType == null){
			return SemAn.ofType.put(compExpr, TYP.VoidType.type);	
		}
		return SemAn.ofType.put(compExpr, compType);
	}

	@Override
	public TYP.Type visit(AST.NameExpr nameExpr, Mode mode) {
		AST.Defn defn = SemAn.defAt.get(nameExpr);
		TYP.Type nameType = SemAn.ofType.get(defn);
		return SemAn.ofType.put(nameExpr, nameType);
	}

	@Override
	public TYP.Type visit(AST.PfxExpr pfxExpr, Mode mode) {
		TYP.Type exprType = pfxExpr.subExpr.accept(this, mode);
		if (exprType instanceof TYP.NameType)
			exprType = ((TYP.NameType) exprType).actualType();
		if (exprType == TYP.IntType.type || exprType == TYP.BoolType.type || exprType == TYP.PtrType.type) {
			switch (pfxExpr.oper) {
				case ADD:
				case SUB:
					if (exprType == TYP.IntType.type)
						return SemAn.ofType.put(pfxExpr, TYP.IntType.type);
					else
						throw new Report.Error(pfxExpr, "Operator " + pfxExpr.oper + " is not defined for type "
								+ exprType);
				case NOT:
					if (exprType == TYP.BoolType.type)
						return SemAn.ofType.put(pfxExpr, TYP.BoolType.type);
					else
						throw new Report.Error(pfxExpr, "Operator " + pfxExpr.oper + " is not defined for type "
								+ exprType);
				case PTR:
					if (exprType == TYP.PtrType.type)
						return SemAn.ofType.put(pfxExpr, ((TYP.PtrType) exprType).baseType);
					else if(exprType == TYP.IntType.type)
						return SemAn.ofType.put(pfxExpr, TYP.IntType.type);
					else
						throw new Report.Error(pfxExpr, "Operator " + pfxExpr.oper + " is not defined for type "
								+ exprType);
				default:
					break;
			}
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.SfxExpr sfxExpr, Mode mode) {
		// Report.info(sfxExpr, "SfxExpr: " + sfxExpr.oper);
		TYP.Type temp = sfxExpr.subExpr.accept(this, Mode.RESOLVE);
		if(temp.actualType() == TYP.VoidType.type){
			throw new Report.Error(sfxExpr,"Unable to dereference a null pointer");
		}
		return SemAn.ofType.put(sfxExpr, temp);
		// return null;
	}

	@Override
	public TYP.Type visit(AST.SizeExpr sizeExpr, Mode mode) {
		TYP.Type exprType = sizeExpr.type.accept(this, mode);
		if(exprType != TYP.VoidType.type)
			return SemAn.ofType.put(sizeExpr, TYP.IntType.type);
		else
			throw new Report.Error(sizeExpr, "Cannot get size of type void");
	}

}
