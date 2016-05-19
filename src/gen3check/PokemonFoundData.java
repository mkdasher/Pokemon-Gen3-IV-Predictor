package gen3check;
import rng.PokemonRNG;

public class PokemonFoundData {
	public PokemonFoundData(PokemonRNG pokemon, int frame){
		this.pokemon = pokemon;
		this.frame = frame;
	}
	private int frame;
	private PokemonRNG pokemon;
	public PokemonRNG getPokemon(){
		return this.pokemon;
	}
	public int getFrame(){
		return this.frame;
	}
}
