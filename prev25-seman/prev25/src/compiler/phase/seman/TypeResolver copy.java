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
public class TypeResolver implements AST.FullVisitor<TYP.Type, Mode> {

	/** Constructs a new name resolver. */
	public TypeResolver() {
	}
	
	@Override
	public TYP.Type visit(Nodes<? extends AST.Node> nodes, Mode mode) {
		Report.info("1");
		for (final AST.Node node : nodes){
			node.accept(this, Mode.DECLARE);

		}
		for (final AST.Node node : nodes){
			node.accept(this, NameResolver.Mode.RESOLVE);
		}

		return null;
	}

	@Override
	public TYP.Type visit(AST.AtomType atomType, Mode mode) {
		
		Report.info("2");
		if(mode == Mode.RESOLVE){
			TYP.Type type;
			switch(atomType.type){
				case CHAR:
					type = TYP.CharType.type;
					break;
				case BOOL:
					type = TYP.BoolType.type;
					break;
				case INT:
					type = TYP.IntType.type;
					break;
				case VOID:
					type = TYP.VoidType.type;
					break;
				default:
					type = null;
					break;
			}
			return SemAn.isType.put(atomType, type);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ArrType arrType, Mode mode) {
		if(mode == Mode.RESOLVE){
			Long arrLen = Long.parseLong(arrType.numElems);
			TYP.Type tmp = arrType.elemType.accept(this, mode);
			if(arrLen < 1){
				throw new Report.Error("Array can not be empty!");
			}
			if (tmp instanceof TYP.VoidType) {
				throw new Report.Error(arrType, "Can not create array of type void.");
			}
			return SemAn.isType.put(arrType, new TYP.ArrType(tmp, arrLen));
		}
		return null;
	}


	@Override
	public TYP.Type visit(AST.PtrType ptrType, Mode mode) {
	
		if(mode == Mode.RESOLVE){
			TYP.Type type = ptrType.baseType.accept(this, mode);
			if(type instanceof TYP.VoidType){
				throw new Report.Error(ptrType, "Base pointer cannot be void!");
			}
			return SemAn.isType.put(ptrType, type);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.StrType strType, Mode mode) {
		if(mode == Mode.DECLARE){
            TYP.Type typ =  new TYP.StrType(null);
            return SemAn.isType.put(strType, typ);
        } else if (mode == Mode.RESOLVE){
			LinkedList<TYP.Type> typelist = new LinkedList<TYP.Type>();
            for (Node comp : strType.comps) {
				TYP.Type b = comp.accept(this, mode);
				if(b instanceof TYP.VoidType){
					throw new Report.Error(strType, "Components cannot be void");
				}
                typelist.addFirst(b);
            }
			var typ = new TYP.StrType(typelist);
			return SemAn.isType.put(strType, typ);
        }
        return null;
	}

	@Override
	public TYP.Type visit(AST.UniType uniType, Mode mode) {
		if(mode == Mode.DECLARE){
            TYP.Type type =  new TYP.UniType(null);
            return SemAn.isType.put(uniType, type);
        } else if (mode == Mode.RESOLVE){
			LinkedList<TYP.Type> typeList = new LinkedList<TYP.Type>();
            for (Node comp : uniType.comps) {
                TYP.Type type = comp.accept(this, mode);
				if(type instanceof TYP.VoidType){
					throw new Report.Error(uniType, "Components cannot be void");
				}
                typeList.addFirst(type);
            }
			return SemAn.isType.put(uniType, new TYP.UniType(typeList));
        }
		return null;
	}

	@Override
	public TYP.Type visit(AST.NameType nameType, Mode mode) {
		if(mode == Mode.DECLARE){
			return SemAn.isType.put(nameType, new TYP.NameType(nameType.name));
		}
		if(mode == Mode.RESOLVE){
			AST.TypDefn typeDefn = (AST.TypDefn) SemAn.defAt.get(nameType);

			if(typeDefn instanceof AST.TypDefn){
				TYP.Type type = SemAn.isType.get(typeDefn);
				return SemAn.isType.put(nameType, type);
			}
			throw new Report.Error(nameType, "Unable to resolve type for: " + nameType.name);
		}

		return null;
	}

	@Override
	public TYP.Type visit(AST.FunType funType, Mode mode) {
		if(mode == Mode.RESOLVE){
			ArrayList<TYP.Type> parTypes = new ArrayList<TYP.Type>();
			TYP.Type resType = funType.resType.accept(this, mode);
			
			for(Node parType : funType.parTypes){
				TYP.Type parType2 = parType.accept(this, mode);
				if(parType2 instanceof TYP.VoidType){
					throw new Report.Error(funType, "Paramater was of type void");
				}else if(parType2 instanceof TYP.UniType){
					throw new Report.Error(funType, "Paramater was of type union");
				}else if(parType2 instanceof TYP.StrType){
					throw new Report.Error(funType, "Paramater was of type struct");
				}else if(parType2 instanceof TYP.ArrType){
					throw new Report.Error(funType, "Paramater was of type array");
				}
				parTypes.add(parType2);
			}
			if(resType instanceof TYP.UniType){
				throw new Report.Error(funType, "Return was of type union");
			}else if(resType instanceof TYP.StrType){
				throw new Report.Error(funType, "Return was of type struct");
			}else if(resType instanceof TYP.ArrType){
				throw new Report.Error(funType, "Return was of type array");
			}
			return SemAn.isType.put(funType, new TYP.FunType(parTypes, resType));
		}
		return null;
	}

	// ----------- DEFINITIONS -----------
	
	@Override
	public TYP.Type visit(AST.TypDefn typDefn, Mode mode) {
		if(mode == Mode.DECLARE){
			SemAn.isType.put(typDefn, new TYP.NameType(typDefn.name));
		}
		if(mode == Mode.RESOLVE){
			TYP.Type t = typDefn.type.accept(this, mode);
			TYP.Type type = SemAn.isType.get(typDefn);
			if(!(type instanceof TYP.NameType))
				throw new Report.Error(typDefn, "Couldnt resolve: " + typDefn.name);
			((TYP.NameType)type).setActType(t);
		}
		return null;
		
	} 

	@Override
	public TYP.Type visit(AST.VarDefn varDefn, Mode mode) {
		varDefn.type.accept(this, mode);
		if(mode==Mode.RESOLVE){
			TYP.Type a = SemAn.isType.get(varDefn.type);
			return SemAn.ofType.put(varDefn, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.DefFunDefn defFunDefn, Mode mode) {
		defFunDefn.pars.accept(this, mode);
		if(mode == Mode.DECLARE){
		}
		if (mode == Mode.RESOLVE){
			defFunDefn.stmts.accept(this, mode);
			TYP.Type a = (defFunDefn.type).accept(this, mode);
			return SemAn.ofType.put(defFunDefn, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ExtFunDefn extFunDefn, Mode mode) {
		extFunDefn.pars.accept(this, mode);
		if(mode == Mode.DECLARE){
		}
		if (mode == Mode.RESOLVE){
			return SemAn.ofType.put(extFunDefn, (extFunDefn.type).accept(this, mode));
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ParDefn parDefn, Mode mode) {
		parDefn.type.accept(this, mode);
		if(mode==Mode.RESOLVE){
			TYP.Type a = SemAn.isType.get(parDefn.type);
			return SemAn.ofType.put(parDefn, a);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.CompDefn compDefn, Mode mode) {
		if(mode==Mode.RESOLVE){
			return SemAn.ofType.put(compDefn, compDefn.type.accept(this, mode));
		}
		return null;
	}

	// ----- Expressions -----

	@Override
	public TYP.Type visit(AST.ArrExpr arrExpr, Mode mode) {

		return null;
	}

	@Override
	public TYP.Type visit(AST.AtomExpr atomExpr, Mode mode) {
		TYP.Type b = atomExpr.accept(this, mode);
		return SemAn.isType.put(atomExpr, b);
	}

	@Override
	public TYP.Type visit(AST.BinExpr binExpr, Mode mode) {
		return null;
	}

	@Override
	public TYP.Type visit(AST.CallExpr callExpr, Mode mode) {
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
