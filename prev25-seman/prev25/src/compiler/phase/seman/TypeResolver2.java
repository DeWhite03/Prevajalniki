package compiler.phase.seman;

import java.util.*;

import compiler.common.report.*;
import compiler.phase.abstr.*;
import compiler.phase.seman.NameResolver.Mode;
import compiler.phase.abstr.AST.*;
import compiler.phase.seman.TYP.*;
import compiler.phase.seman.NameResolver.*;

/**
 * Type resolver.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class TypeResolver2 implements AST.FullVisitor<TYP.Type, Mode> {

	/** Constructs a new name resolver. */
	public TypeResolver2() {
	}

	Node main = null;

	@Override
	public TYP.Type visit(Nodes<? extends AST.Node> nodes, Mode arg) {
		for (final AST.Node node : nodes){
			node.accept(this, Mode.DECLARE);
			if (main == null) {
				throw new Report.Error(node, "Main function not found");
			}
		}
		for (final AST.Node node : nodes){
			node.accept(this, Mode.RESOLVE);
		}

		return null;
	}

	@Override
	public TYP.Type visit(AST.AtomType atomType, Mode arg){
		if(arg == Mode.RESOLVE){
			TYP.Type temp;
			switch(atomType.type){
				case CHAR:
					temp = TYP.CharType.type;
					break;
				case BOOL:
					temp = TYP.BoolType.type;
					break;
				case INT:
					temp = TYP.IntType.type;
					break;
				case VOID:
					temp = TYP.VoidType.type;
					break;
				default:
					temp = null;
					break;
			}
			return SemAn.isType.put(atomType, temp);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ArrType arrType, Mode arg) {
		arrType.elemType.accept(this, arg);
		if(arg == Mode.RESOLVE){
			SemAn.isType.put(arrType, SemAn.isType.get(arrType.elemType));
		}
		return null;
	}
	// TODO
	@Override
	public TYP.Type visit(AST.PtrType ptrType, Mode arg) {
		ptrType.baseType.accept(this, arg);
		if(arg == Mode.RESOLVE){
			TYP.Type tmp = TYP.PtrType.type;
			SemAn.isType.put(ptrType, tmp);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.StrType strType, Mode arg) {
		if(arg == Mode.DECLARE){
            TYP.Type typ =  new TYP.StrType(null);
            return SemAn.isType.put(strType, typ);
        } else if (arg == Mode.RESOLVE){
			LinkedList<TYP.Type> typelist = new LinkedList<TYP.Type>();
            for (Node comp : strType.comps) {
                typelist.addFirst(comp.accept(this, arg));
            }
			var typ = new TYP.StrType(typelist);
			return SemAn.isType.put(strType, typ);
        }
        return null;
	}

	@Override
	public TYP.Type visit(AST.UniType uniType, Mode arg) {
		if(arg == Mode.DECLARE){
            TYP.Type typ =  new TYP.UniType(null);
            return SemAn.isType.put(uniType, typ);
        } else if (arg == Mode.RESOLVE){
			LinkedList<TYP.Type> typelist = new LinkedList<TYP.Type>();
            for (Node comp : uniType.comps) {
                typelist.addFirst(comp.accept(this, arg));
            }
			var typ = new TYP.UniType(typelist);
			return SemAn.isType.put(uniType, typ);
        }
		return null;
	}

	@Override
	public TYP.Type visit(AST.NameType nameType, Mode arg) {
		if(arg == Mode.DECLARE){
			TYP.NameType tmp = new TYP.NameType(nameType.name);
			//Report.info("Putted");
			return SemAn.isType.put(nameType, tmp);
		}
		if(arg == Mode.RESOLVE){
			TYP.NameType a = (TYP.NameType) SemAn.isType.get(nameType);
			//if(a==null)
				// throw new Report.Error(nameType, "Unable to define: " + nameType.name);

			a.setActType(a);
			return SemAn.isType.put(nameType, a);
			//return SemAn.ofType.put(nameType, VoidType.type);
		}

		return null;
	}

	@Override
	public TYP.Type visit(AST.FunType funType, Mode arg) {
		if (arg == Mode.DECLARE) {
			funType.resType.accept(this, arg);
			return SemAn.isType.put(funType, SemAn.isType.get(funType.resType));
		}
		if(arg == Mode.RESOLVE){
			funType.resType.accept(this, arg);
			funType.parTypes.accept(this, arg);
			TYP.Type a = SemAn.isType.get(funType.resType);
			return SemAn.isType.put(funType, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.TypDefn typDefn, Mode arg) {
		if(arg == Mode.DECLARE){
			return SemAn.isType.put(typDefn, VoidType.type);
		}
		typDefn.type.accept(this, arg);
			if(arg == Mode.RESOLVE){
				TYP.Type tmp = SemAn.isType.get(typDefn.type);
				return SemAn.isType.put(typDefn, tmp.actualType());
			}
		return null;
		
	}

	// ----- Definitions -----

	@Override
	public TYP.Type visit(AST.VarDefn varDefn, Mode arg) {
		varDefn.type.accept(this, arg);
		if(arg==Mode.RESOLVE){
			TYP.Type a = SemAn.isType.get(varDefn.type);
			return SemAn.ofType.put(varDefn, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.DefFunDefn defFunDefn, Mode arg) {
		defFunDefn.pars.accept(this, arg);
		if (arg == Mode.DECLARE) {
		}
		if (arg == Mode.RESOLVE){
			defFunDefn.stmts.accept(this, arg);
			TYP.Type a = SemAn.isType.get(defFunDefn.type);
			return SemAn.ofType.put(defFunDefn, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ExtFunDefn extFunDefn, Mode arg) {
		extFunDefn.pars.accept(this, arg);
		if(arg == Mode.DECLARE){
		}
		if (arg == Mode.RESOLVE){
			TYP.Type a = SemAn.isType.get(extFunDefn.type);
			return SemAn.ofType.put(extFunDefn, a);
			
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ParDefn parDefn, Mode arg) {
		parDefn.type.accept(this, arg);
		if(arg==Mode.RESOLVE){
			TYP.Type a = SemAn.isType.get(parDefn.type);
			return SemAn.ofType.put(parDefn, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.CompDefn compDefn, Mode arg) {
		compDefn.type.accept(this, arg);
		if(arg==Mode.RESOLVE){
			TYP.Type a = SemAn.isType.get(compDefn.type);
			return SemAn.ofType.put(compDefn, a);
		}

		return null;
	}

	// ----- Expressions -----

	@Override
	public TYP.Type visit(AST.ArrExpr arrExpr, Mode arg) {

		return null;
	}

	@Override
	public TYP.Type visit(AST.AtomExpr atomExpr, Mode arg) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.BinExpr binExpr, Mode arg) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.CallExpr callExpr, Mode arg) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.CastExpr castExpr, Mode arg) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.CompExpr compExpr, Mode arg) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.NameExpr nameExpr, Mode arg) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.PfxExpr pfxExpr, Mode arg) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.SfxExpr sfxExpr, Mode arg) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.SizeExpr sizeExpr, Mode arg) {
		return null;
	}
	
}