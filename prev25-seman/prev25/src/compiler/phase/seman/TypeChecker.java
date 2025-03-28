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

	
	// ----- Expressions -----

	@Override
	public TYP.Type visit(AST.ArrExpr arrExpr, Mode mode) {
		TYP.Type indexType = arrExpr.idx.accept(this, mode);
		TYP.Type arrType = arrExpr.arrExpr.accept(this, mode);
		if(mode == Mode.RESOLVE){
			if(!(indexType instanceof TYP.IntType))
				throw new Report.Error(arrExpr, "Array index must be of type int");
			if(!(arrType instanceof TYP.ArrType))
				throw new Report.Error(arrExpr, "Array expression must be of type array");
			return SemAn.ofType.put(arrExpr, arrType);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.AtomExpr atomExpr, Mode mode) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.BinExpr binExpr, Mode mode) {
		TYP.Type lefType = binExpr.fstExpr.accept(this, mode);
		TYP.Type rigType = binExpr.sndExpr.accept(this, mode);
		if (lefType.equals(rigType)) {
			Report.info("Attempt coersion");
		}
		else
		{
			switch (binExpr.oper) {
				case OR:
					break;
				case AND:
					
					break;
				case EQU:
					
					break;
				case NEQ:
					
					break;
				case LTH:
					
					break;
				case GTH:
					
					break;
				case LEQ:
					
					break;
				case GEQ:
					
					break;
				case MUL:
					
					break;
				case DIV:
					
					break;
				case MOD:
					
					break;
				case ADD:
					
					break;
				case SUB:
						
					break;
			
				default:
					break;
			}
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.CallExpr callExpr, Mode mode) {
		ArrayList<TYP.Type> parTypes = new ArrayList<TYP.Type>();
		TYP.Type funType = callExpr.funExpr.accept(this, mode);
		for (AST.Expr arg : callExpr.argExprs)
			parTypes.add(arg.accept(this, mode));
		if (mode == Mode.RESOLVE) {
			if(!(funType instanceof TYP.FunType))
				throw new Report.Error(callExpr, "Function expression must be of type function");
			if(parTypes.size() != ((TYP.FunType) funType).parTypes.size())
				throw new Report.Error(callExpr, "Function call has wrong number of arguments");
			return SemAn.ofType.put(callExpr, ((TYP.FunType) funType).resType);
			
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.CastExpr castExpr, Mode mode) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.CompExpr compExpr, Mode mode) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.NameExpr nameExpr, Mode mode) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.PfxExpr pfxExpr, Mode mode) {
		
		return null;
	}

	@Override
	public TYP.Type visit(AST.SfxExpr sfxExpr, Mode mode) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.SizeExpr sizeExpr, Mode mode) {
		return null;
	}
	
}
