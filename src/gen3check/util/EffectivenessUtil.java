package gen3check.util;

import gen3check.pokemon.data.PokemonType;

/*"Fighting","Flying","Poison","Ground",
"Rock","Bug","Ghost","Steel", "Fire","Water","Grass","Electric",
"Psychic","Ice","Dragon","Dark", "Normal"*/

public class EffectivenessUtil {
	
	public static float getEffectiveness(PokemonType attacker, PokemonType defender){
		if (attacker.isNone() || defender.isNone()) return 1;
		if (attacker.getID() == PokemonType.UNKNOWN || defender.getID() == PokemonType.UNKNOWN) return 1;
		return effectiveTable[getIndex(attacker.getID())][getIndex(defender.getID())];
	}
	
	private static int getIndex(int id){
		switch(id){
		case PokemonType.FIGHTING: return FIGHTING_INDEX;
		case PokemonType.FLYING: return FLYING_INDEX;
		case PokemonType.POISON: return POISON_INDEX;
		case PokemonType.GROUND: return GROUND_INDEX;
		case PokemonType.ROCK: return ROCK_INDEX;
		case PokemonType.BUG: return BUG_INDEX;
		case PokemonType.GHOST: return GHOST_INDEX;
		case PokemonType.STEEL: return STEEL_INDEX;
		case PokemonType.FIRE: return FIRE_INDEX;
		case PokemonType.WATER: return WATER_INDEX;
		case PokemonType.GRASS: return GRASS_INDEX;
		case PokemonType.ELECTRIC: return ELECTRIC_INDEX;
		case PokemonType.PSYCHIC: return PSYCHIC_INDEX;
		case PokemonType.ICE: return ICE_INDEX;
		case PokemonType.DRAGON: return DRAGON_INDEX;
		case PokemonType.DARK: return DARK_INDEX;
		case PokemonType.NORMAL: return NORMAL_INDEX;
		default: return 0;
		}
	}
	
	private static float effectiveTable[][] = new float[][]{
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.5f, 0, 1, 1, 0.5f},
		{1, 0.5f, 0.5f, 1, 2, 2, 1, 1, 1, 1, 1, 2, 0.5f, 1, 0.5f, 1, 2},
		{1, 2, 0.5f, 1, 0.5f, 1, 1, 1, 2, 1, 1, 1, 2, 1, 0.5f, 1, 1},
		{1, 1, 2, 0.5f, 0.5f, 1, 1, 1, 0, 2, 1, 1, 1, 1, 0.5f, 1, 1},
		{1, 0.5f, 2, 1, 0.5f, 1, 1, 0.5f, 2, 0.5f, 1, 0.5f, 2, 1, 0.5f, 1, 0.5f},
		{1, 0.5f, 0.5f, 1, 2, 0.5f, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 0.5f},
		{2, 1, 1, 1, 1, 2, 1, 0.5f, 1, 0.5f, 0.5f, 0.5f, 2, 0, 1, 2, 2},
		{1, 1, 1, 1, 2, 1, 1, 0.5f, 0.5f, 1, 1, 1, 0.5f, 0.5f, 1, 1, 0},
		{1, 2, 1, 2, 0.5f, 1, 1, 2, 1, 0, 1, 0.5f, 2, 1, 1, 1, 2},
		{1, 1, 1, 0.5f, 2, 1, 2, 1, 1, 1, 1, 2, 0.5f, 1, 1, 1, 0.5f},
		{1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 0.5f, 1, 1, 1, 1, 0, 0.5f},
		{1, 0.5f, 1, 1, 2, 1, 0.5f, 0.5f, 1, 0.5f, 2, 1, 1, 0.5f, 1, 2, 0.5f},
		{1, 2, 1, 1, 1, 2, 0.5f, 1, 0.5f, 2, 1, 2, 1, 1, 1, 1, 0.5f},
		{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 0.5f, 0.5f},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 0.5f},
		{1, 1, 1, 1, 1, 1, 0.5f, 1, 1, 1, 2, 1, 1, 2, 1, 0.5f, 0.5f},
		{1, 0.5f, 0.5f, 0.5f, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0.5f}
		};

	private static final int NORMAL_INDEX = 0;
	private static final int FIRE_INDEX = 1;
	private static final int WATER_INDEX = 2;
	private static final int ELECTRIC_INDEX = 3;
	private static final int GRASS_INDEX = 4;
	private static final int ICE_INDEX = 5;
	private static final int FIGHTING_INDEX = 6;
	private static final int POISON_INDEX = 7;
	private static final int GROUND_INDEX = 8;
	private static final int FLYING_INDEX = 9;
	private static final int PSYCHIC_INDEX = 10;
	private static final int BUG_INDEX = 11;
	private static final int ROCK_INDEX = 12;
	private static final int GHOST_INDEX = 13;
	private static final int DRAGON_INDEX = 14;
	private static final int DARK_INDEX = 15;
	private static final int STEEL_INDEX = 16;
}


