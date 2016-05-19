package gen3check.util;

import java.util.List;

import gen3check.pokemon.data.*;

public class DataListUtil {
	
	public static void init(){
		pokemonList = PokemonData.getList().toArray();
		abilityList = Ability.getList().toArray();
		expTypeList = ExperienceType.getList().toArray();
		moveList = Move.getList().toArray();
		moveCategoryList = MoveCategory.getList().toArray();
		natureList = Nature.getList().toArray();
		itemList = PokemonItem.getList().toArray();
		pokemonTypeList = PokemonType.getList().toArray();
	}
	
	public static Object[] pokemonList;
	public static Object[] abilityList;
	public static Object[] expTypeList;
	public static Object[] moveList;
	public static Object[] moveCategoryList;
	public static Object[] natureList;
	public static Object[] itemList;
	public static Object[] pokemonTypeList;
}
