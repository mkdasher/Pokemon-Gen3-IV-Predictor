package rng;

import gen3check.pokemon.data.GenderRate;
import gen3check.pokemon.data.Nature;

public class PokemonRNG
{
	public PokemonRNG(){
		//default constructor
	}
	public PokemonRNG(int pid, RNG rng){
		this.pid = pid;
		this.generate(rng);
	}
	protected static int getSlot(int a)
    {
        a = a % 100;
        if (a < 20) return 0;
        else if (a < 40) return 1;
        else if (a < 50) return 2;
        else if (a < 60) return 3;
        else if (a < 70) return 4;
        else if (a < 80) return 5;
        else if (a < 85) return 6;
        else if (a < 90) return 7;
        else if (a < 94) return 8;
        else if (a < 98) return 9;
        else if (a == 98) return 10;
        else return 11;
    }
    protected void generate(RNG rng)
    {
        int top;
        rng.advance(); top = rng.getTop();
        this.hp = top % 32;
        this.atk = (top / 32) % 32;
        this.def = (top / 1024) % 32;
        rng.advance(); top = rng.getTop();
        this.spe = top % 32;
        this.spa = (top / 32) % 32;
        this.spd = (top / 1024) % 32;
        int genderAux = (int)(this.pid & 0xFF);
        if (genderAux < 31) this.minGender = 12;
    	else if (genderAux < 64) this.minGender = 25;
    	else if (genderAux < 127) this.minGender = 50;
    	else if (genderAux < 191) this.minGender = 75;
    	else this.minGender = 100;
    }
    public int getTopPid(){
        return (int)((this.pid >> 16) & 0xFFFF);
    }
    public int getLowPid(){
        return (int)(this.pid & 0xFFFF);
    }
    public boolean isShiny(int tid, int sid){
    	return (tid ^ sid ^ (getLowPid() ^ getTopPid())) < 8;
    }
    public boolean isFemale(GenderRate gr){
    	switch(gr){
		case FemaleOnly: return true;
		case Genderless: return false;
		case Male25: return this.minGender <= 75;
		case Male50: return this.minGender <= 50;
		case Male75: return this.minGender <= 25;
		case Male87: return this.minGender <= 12;
		case MaleOnly: return false;
		default: return false;
    	}
    }
    public int frame;
    public long pid;
    public Nature nature;
    public int hp, atk, def, spa, spd, spe;
    private int minGender;
}
