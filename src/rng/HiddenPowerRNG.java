package rng;

public class HiddenPowerRNG {
	public HiddenPowerRNG(int type, int damage){
		 if (type >= 0 && type < 16)
         {
             this.setHiddenPower(type, damage);
         }
	}
	public HiddenPowerRNG(PokemonRNG pkm){
	this.setHiddenPower(pkm.hp, pkm.atk, pkm.def, pkm.spa, pkm.spd, pkm.spe);
	}
	public void setHiddenPower(int hp, int atk, int def, int spa, int spd, int spe)
    {
        this.type = (((hp % 2) + 2 * (atk % 2) + 4 * (def % 2) + 8 * (spe % 2) + 16 * (spa % 2) + 32 * (spd % 2)) * 15 / 63);
        this.damage = 30 + (((hp >> 1) % 2) + 2 * ((atk >> 1) % 2) + 4 * ((def >> 1) % 2)
                        + 8 * ((spe >> 1) % 2) + 16 * ((spa >> 1) % 2) + 32 * ((spd >> 1) % 2)) * 40 / 63;
    }
	public void setHiddenPower(int type, int damage){
		this.type = type;
		this.damage = damage;
	}
	public String toString(){
		return hp_tostr[this.type] + " " + String.valueOf(damage);
	}
	
	public int type;
	public int damage;
    
	public static String hp_tostr[] = 
        {"Fighting","Flying","Poison","Ground",
		 "Rock","Bug","Ghost","Steel",
		 "Fire","Water","Grass","Electric",
		 "Psychic","Ice","Dragon","Dark"};
}
