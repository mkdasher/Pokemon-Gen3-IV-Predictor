package rng;

import rng.PokemonRNG;
import gen3check.pokemon.data.Nature;

public class PokemonMethod1 extends PokemonRNG{
	public PokemonMethod1(int pid, RNG rng){
		super(pid,rng);
	}
	public PokemonMethod1(Seed seed, int frame){
		this.frame = frame;
        RNG rng1 = new RNG(seed);
        RNG rng2 = new RNG(seed);
        rng1.advance(frame);
        rng2.copy(rng1);
        rng1.advance();
        this.pid = ((long)rng1.getTop() << 16) + (long)rng2.getTop();
        this.nature = new Nature((int)(this.pid % 25));
        this.generate(rng1);
	}
}
