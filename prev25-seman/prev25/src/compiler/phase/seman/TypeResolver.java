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
		for (final AST.Node node : nodes){
			node.accept(this, Mode.DECLARE);

		}
		for (final AST.Node node : nodes){
			node.accept(this, Mode.RESOLVE);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.AtomType atomType, Mode mode) {
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
		if (mode == Mode.RESOLVE) {
			Long numElems = Long.parseLong(arrType.numElems);
			TYP.Type baseType = arrType.elemType.accept(this, mode);
			if (numElems <= 0)
				throw new Report.Error(arrType, "Array size must be greater than 0");
			if (baseType instanceof TYP.VoidType)
				throw new Report.Error(arrType, "Array cannot be of type void");
			return SemAn.isType.put(arrType, new TYP.ArrType(baseType, numElems));
		}
		return null;
	}


	@Override
	public TYP.Type visit(AST.PtrType ptrType, Mode mode) {
		if (mode == Mode.RESOLVE) {
			TYP.Type baseType = ptrType.baseType.accept(this, mode);
			if(baseType instanceof TYP.VoidType)
				throw new Report.Error(ptrType, "Pointer cannot be of type void");
			return SemAn.isType.put(ptrType, new TYP.PtrType(baseType));
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.StrType strType, Mode mode) {
		if(mode == Mode.DECLARE)
			SemAn.isType.put(strType, new TYP.StrType(null));
		if (mode == Mode.RESOLVE) {
			ArrayList<TYP.Type> compTypes = new ArrayList<TYP.Type>();
			for (AST.CompDefn comp : strType.comps) {
				TYP.Type type = comp.accept(this, mode);
				if(type instanceof TYP.VoidType)
					throw new Report.Error(strType, "Struct component cannot be of type void");
				compTypes.add(type);
			}
			if(compTypes.size() == 0)
				throw new Report.Error(strType, "Struct must have at least one component");
			return SemAn.isType.put(strType, new TYP.StrType(compTypes));
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.UniType uniType, Mode mode) {
		if (mode == Mode.DECLARE)
			SemAn.isType.put(uniType, new TYP.UniType(null));
		if (mode == Mode.RESOLVE) {
			ArrayList<TYP.Type> compTypes = new ArrayList<TYP.Type>();
			for (AST.CompDefn comp : uniType.comps) {
				TYP.Type type = comp.accept(this, mode);
				if(type instanceof TYP.VoidType)
					throw new Report.Error(uniType, "Union component cannot be of type void");
				compTypes.add(type);
			}
			if(compTypes.size() == 0)
				throw new Report.Error(uniType, "Union must have at least one component");
			return SemAn.isType.put(uniType, new TYP.StrType(compTypes));
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
		if(mode == Mode.DECLARE)
			SemAn.isType.put(typDefn, new TYP.NameType(typDefn.name));
		else if (mode == Mode.RESOLVE) {
			TYP.Type resolvedType = typDefn.type.accept(this, mode);
			TYP.Type defnType = SemAn.isType.get(typDefn);
			if(!(defnType instanceof TYP.NameType))
				throw new Report.Error(typDefn, "Type definition is not a name type");
			((TYP.NameType) defnType).setActType(resolvedType);
		}
		return null;
		
	} 

	@Override
	public TYP.Type visit(AST.VarDefn varDefn, Mode mode) {
		varDefn.type.accept(this, mode);
		if (mode == Mode.RESOLVE)
			SemAn.ofType.put(varDefn, SemAn.isType.get(varDefn.type));
		return null;
	}

	@Override
	public TYP.Type visit(AST.DefFunDefn defFunDefn, Mode mode) {
		for (AST.ParDefn parDefn : defFunDefn.pars)
			parDefn.accept(this, mode);
		if (mode == Mode.RESOLVE) {
			ArrayList<TYP.Type> parTypes = new ArrayList<TYP.Type>();
			for (AST.ParDefn parDefn : defFunDefn.pars)
				parTypes.add(SemAn.isType.get(parDefn));
			SemAn.ofType.put(defFunDefn, new TYP.FunType(parTypes, defFunDefn.type.accept(this, mode)));
			defFunDefn.stmts.accept(this, mode);
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ExtFunDefn extFunDefn, Mode mode) {
		for (AST.ParDefn parDefn : extFunDefn.pars)
			parDefn.accept(this, mode);
		if (mode == Mode.RESOLVE) {
			ArrayList<TYP.Type> parTypes = new ArrayList<TYP.Type>();
			for (AST.ParDefn parDefn : extFunDefn.pars)
				parTypes.add(SemAn.isType.get(parDefn));
			SemAn.ofType.put(extFunDefn, new TYP.FunType(parTypes, extFunDefn.type.accept(this, mode)));
		}
		return null;
	}

	@Override
	public TYP.Type visit(AST.ParDefn parDefn, Mode mode) {
		// NE SME BIT VOID
		parDefn.type.accept(this, mode);
		if (mode == Mode.RESOLVE)
			return SemAn.ofType.put(parDefn, SemAn.isType.get(parDefn.type));
		return null;
	}

	@Override
	public TYP.Type visit(AST.CompDefn compDefn, Mode mode) {
		if (mode == Mode.RESOLVE) {
			return SemAn.ofType.put(compDefn, compDefn.type.accept(this, mode));
		}
		return null;
	}
}
