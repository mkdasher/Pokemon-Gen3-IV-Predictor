package gen3check.pokemon.data;

public class StatPack {
	public StatPack()
    {
        this.hp = 0;
        this.atk = 0;
        this.def = 0;
        this.spa = 0;
        this.spd = 0;
        this.spe = 0;
    }
    public StatPack(int hp, int atk, int def,
        int spa, int spd, int spe)
    {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spa = spa;
        this.spd = spd;
        this.spe = spe;
    }
    public StatPack(int generic)
    {
        this.hp = generic;
        this.atk = generic;
        this.def = generic;
        this.spa = generic;
        this.spd = generic;
        this.spe = generic;
    }

    public int getStat(int i)
    {
        switch (i)
        {
            case 0: return hp;
            case 1: return atk;
            case 2: return def;
            case 3: return spa;
            case 4: return spd;
            case 5: return spe;
            default: return -1;
        }
    }

    public void setStat(int i, int value)
    {
        switch (i)
        {
            case 0: this.hp = value;
                break;
            case 1: this.atk = value;
                break;
            case 2: this.def = value;
                break;
            case 3: this.spa = value;
                break;
            case 4: this.spd = value;
                break;
            case 5: this.spe = value;
                break;
        }
    }

    public StatPack getCopy()
    {
        return new StatPack(this.hp, this.atk, this.def, this.spa, this.spd, this.spe);
    }

    public int hp;
    public int atk;
    public int def;
    public int spa;
    public int spd;
    public int spe;
    public final static int STAT_N = 6;
    public final static int HP = 0;
    public final static int ATK = 1;
    public final static int DEF = 2;
    public final static int SPA = 3;
    public final static int SPD = 4;
    public final static int SPE = 5;
}
