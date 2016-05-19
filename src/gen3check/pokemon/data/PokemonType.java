package gen3check.pokemon.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokemonType extends Data{
	public PokemonType(String type){
		super(getTypeID(type),type);
	}
	
	public PokemonType(int n){
		super(n,toStr[n]);
	}
	
	public static int getTypeID(String type){
		for (int i = 0; i < toStr.length; i++){
			if (type.equals(toStr[i])){
				return i;
			}
		}
		return UNKNOWN;
	}
	
	public boolean isNone(){
		return (this.getID() == NONE);
	}
	
	public PokemonType getCopy(){
		return new PokemonType(this.n);
	}
	
		
	private final static String[] toStr = {"Fighting","Flying","Poison","Ground",
		 "Rock","Bug","Ghost","Steel", "Fire","Water","Grass","Electric",
		 "Psychic","Ice","Dragon","Dark", "Normal", "None", "Unknown"};
	
	/**
	 * Gets list of all types
	 * @return
	 */
	public static List<PokemonType> getList(){
		List<PokemonType> list = new ArrayList<PokemonType>();
		for (int i = 0; i < POKEMONTYPE_N; i++){ // < instead of <= cause there's no "none" type at the beginning. List doesn't include Unknown.
			list.add(new PokemonType(i));
		}
		Collections.sort(list);
		return list;
	}
	
	//Pokemon types
	public static final int FIGHTING = 0;
	public static final int FLYING = 1;
	public static final int POISON = 2;
	public static final int GROUND = 3;
	public static final int ROCK = 4;
	public static final int BUG = 5;
	public static final int GHOST = 6;
	public static final int STEEL = 7;
	public static final int FIRE = 8;
	public static final int WATER = 9;
	public static final int GRASS = 10;
	public static final int ELECTRIC = 11;
	public static final int PSYCHIC = 12;
	public static final int ICE = 13;
	public static final int DRAGON = 14;
	public static final int DARK = 15;
	public static final int NORMAL = 16;
	public static final int NONE = 17;
	public static final int UNKNOWN = 18;
	
	//Total amount
	public static final int POKEMONTYPE_N = 18;
}
