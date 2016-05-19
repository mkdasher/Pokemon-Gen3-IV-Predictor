package gen3check.pokemon.data;

import java.util.ArrayList;
import java.util.List;

public class EvolutionType extends Data{
	public EvolutionType(String type){
		super(getTypeID(type),type);
	}
	
	public EvolutionType(int n){
		super(n,toStr[n]);
	}
	
	public static int getTypeID(String type){
		for (int i = 0; i < toStr.length; i++){
			if (type.equals(toStr[i])){
				return i;
			}
		}
		return 0; //level up (default)
	}
	
	public boolean isNone(){
		return this.getName().equals("None");
	}
	
	public PokemonType getCopy(){
		return new PokemonType(this.n);
	}
		
	private final static String[] toStr = {"Level Up", "Trade", "Used Item", "Shedinja"};
	
	/**
	 * Gets list of all types
	 * @return
	 */
	public static List<PokemonType> getList(){
		List<PokemonType> list = new ArrayList<PokemonType>();
		for (int i = 0; i < EVOLUTIONTYPE_N; i++){ // < instead of <= cause there's no "none" type at the beginning.
			list.add(new PokemonType(i));
		}
		return list;
	}
	
	public static final int EVOLUTIONTYPE_N = 4;
}