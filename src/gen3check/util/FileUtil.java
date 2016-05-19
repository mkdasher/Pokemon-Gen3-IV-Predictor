package gen3check.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import gen3check.Controller;
import gen3check.pokemon.Pokemon;
import gen3check.pokemon.data.Ability;
import gen3check.pokemon.data.Move;
import gen3check.pokemon.data.Nature;
import gen3check.pokemon.data.PokemonItem;
import gen3check.pokemon.data.StatPack;

public class FileUtil {
	
	public static final String ABILITIES = "abilities.csv";
	public static final String ABILITIES_BY_PKM = "abilitiesByPokemon.csv";
	public static final String ITEMS = "items.csv";
	public static final String MOVES = "moves.csv";
	public static final String POKEMON = "pokemon.csv";
	public static final String POKEMON_BASESTATS = "pokemonBaseStats.csv";
	public static final String POKEMON_EVOLUTION = "pokemonEvolution.csv";
	public static final String POKEMON_EXP = "pokemonExp.csv";
	public static final String POKEMON_EXP_TYPE = "pokemonExpType.csv";
}
