package gen3check.pokemon;

import gen3check.pokemon.data.Ability;
import gen3check.pokemon.data.Move;
import gen3check.pokemon.data.Nature;
import gen3check.pokemon.data.PokemonData;
import gen3check.pokemon.data.PokemonItem;
import gen3check.pokemon.data.PokemonType;
import gen3check.pokemon.data.StatPack;
import gen3check.util.CSVFileReader;
import gen3check.util.FileUtil;

public class Pokemon {
	
	/**
	 * Default Constructor
	 */
	public Pokemon(){
		this.n = 0;
		this.baseData = new PokemonData(0);
		this.level = 1;
		this.experience = this.baseData.expType.experience(this.level - 1);
		this.IV = new StatPack(0);
		this.EV = new StatPack(0);
		this.nature = new Nature(0);
		this.ability = new Ability(0);
		this.calculateStats();
		this.heldItem = new PokemonItem(0);
		this.pokerus = false;
		this.outsider = false;
		this.setMoves(new Move(0), new Move(0), new Move(0), new Move(0));
	}
	
	/**
	 * Constructor. Level and Stats are calculated from IV, EV, level.
	 * Moves aren't added in the constructor, they are added later.
	 * @param n
	 * @param level
	 * @param IV
	 * @param EV
	 * @param nature
	 * @param ability
	 */
	public Pokemon(int n, int level, StatPack IV, StatPack EV, Nature nature, Ability ability, PokemonItem heldItem, boolean outsider){
		this.n = n;
		this.baseData = new PokemonData(n);
		this.level = level;
		this.experience = this.baseData.expType.experience(this.level - 1); //calculates experience from level
		this.IV = IV;
		this.EV = EV;
		this.nature = nature;
		this.ability = ability;
		this.calculateStats();
		this.heldItem = heldItem;
		this.pokerus = false;
		this.outsider = outsider;
		this.setMoves(new Move(0), new Move(0), new Move(0), new Move(0));
	}
	
	/**
	 * Private constructor for getCopy(). (Level and Stats are copied)
	 * @param n
	 * @param level
	 * @param exp
	 * @param stat
	 * @param IV
	 * @param EV
	 * @param nature
	 * @param ability
	 * @param heldItem
	 * @param pokerus
	 * @param outsider
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	private Pokemon(int n, int level, int exp, StatPack stat, StatPack IV, StatPack EV, Nature nature, Ability ability, PokemonItem heldItem,
			boolean pokerus, boolean outsider, Move a, Move b, Move c, Move d){
		this.n = n;
		this.baseData = new PokemonData(n);
		this.level = level;
		this.experience = exp;
		this.IV = IV;
		this.EV = EV;
		this.nature = nature;
		this.ability = ability;
		this.stat = stat;
		this.heldItem = heldItem;
		this.pokerus = pokerus;
		this.outsider = outsider;
		this.setMoves(a,b,c,d);
	}
	
	/**
	 * Sets current stats from current level / EV / IV.
	 */
	public void calculateStats(){
		this.stat = new StatPack();
        for (short i = 0; i < StatPack.STAT_N; i++)
        {
            this.stat.setStat(i, calculateStat(i));
        }
	}
	private short calculateStat(int i)
    {
        int aux;
        if (i == 0)
        {
            aux = ((this.IV.hp + (2 * this.getBaseData().baseStat.hp) + (this.EV.hp / 4) + 100) * this.level) / 100 + 10;
            return (short)aux;
        }
        else
        {
            double aux_d = (double)(((this.IV.getStat(i) + (2 * this.getBaseData().baseStat.getStat(i)) + (this.EV.getStat(i) / 4)) * this.level) / 100 + 5) * this.nature.getNatureBoost(i);
            aux = (int)aux_d;
            if (Math.abs((double)aux - aux_d) > 0.9999)
                aux++;
            return (short)aux;
        }
    }
	 
	 /**
	  * Levels up a pokemon (Rare Candy)
	  */
	 public void levelUp(){
		 this.experience = this.getBaseData().expType.experience(this.level);
		 this.level++;
		 this.calculateStats();
	 }
	 
	 /**
	  * Check is this pokemon is going to evolve
	  */
	 public void checkEvolution(){
		 if (this.heldItem.getID() == PokemonItem.EVERSTONE) return;
		 
		 final int _NUMROWS = 320;
		 String[] data;
		 CSVFileReader fileReader = new CSVFileReader();
		 for (int i = 0; i < _NUMROWS; i++){
			 data = fileReader.getLine(i, FileUtil.POKEMON_EVOLUTION);
			 if (this.baseData.getID() == Integer.parseInt(data[0])
					 && data[2].equals("Level Up")
					 && this.level >= Integer.parseInt(data[4])){
				 this.evolve(Integer.parseInt(data[1]));
				 return;
			 }
		 }
	 }
	 
	 /**
	  * Evolve this pokemon to Pokemon ID n.
	  * @param n
	  */
	 private void evolve(int n){
		 this.n = n;
		 this.baseData = new PokemonData(n);
		 //this.ability might be changed in the future while evolving
		 this.calculateStats();
	 }
	 
	 /**
	  * The pokemon gains experience.
	  * @param gainedExp: exp gained.
	  */
	 public void gainExperience(int gainedExp){
		 this.experience += gainedExp;
		 if (level >= MAX_LEVEL){
			 this.level = MAX_LEVEL;
			 this.experience = this.baseData.expType.experience(this.level - 1);
			 return;
		 }
		 while (this.getBaseData().expType.experience(this.level) <= this.experience){
			 level++;
			 this.calculateStats();
			 if (level == MAX_LEVEL) return;
		 }
	 }
	 
	 /**
	  * The pokemon gains EVs.
	  * @param pkmDefeated
	  */
	 public void gainEVs(Pokemon pkmDefeated){
		 int pkrsModifier = 1; if (this.pokerus) pkrsModifier = 2;
		 for (int i = 0; i < StatPack.STAT_N; i++){
			 int gain = pkmDefeated.getBaseData().EV.getStat(i) * pkrsModifier;
			 this.EV.setStat(i, this.EV.getStat(i) + gain);
		 }
	 }
	 
	 /**
	  * Function used for item EVs
	  */
	 public void useVitamin(int stat){
		this.EV.setStat(stat, this.EV.getStat(stat) + 10);
		this.stat.setStat(stat, calculateStat(stat));
	 }
	 
	 /**
	  * Gets the level of the pokemon
	  * @return
	  */
	 public int getLevel(){
		 return this.level;
	 }
	 
	 /**
	  * Gets the experience of the pokemon
	  * @return
	  */
	 public int getExperience(){
		 return this.experience;
	 }
	 
	 /**
	  * Gets the ability of the pokemon
	  * @return
	  */
	 public Ability getAbility(){
		 return this.ability;
	 }
	 
	 /**
	  * Gets the nature of the pokemon
	  * @return
	  */
	 public Nature getNature(){
		 return this.nature;
	 }
	 
	 /**
	  * Gets the held item of the pokemon
	  * @return
	  */
	 public PokemonItem getHeldItem(){
		 return this.heldItem;
	 }
	 
	 /**
	  * Sets held item to a Pokemon.
	  * @param item
	  */
	 public void setHeldItem(PokemonItem item){
		 this.heldItem = item;
	 }
	 
	 /**
	  * Return true if it's a traded pokemon, else return false
	  * @return
	  */
	 public boolean isOutsider(){
		 return this.outsider;
	 }
	 
	 /**
	  * Trade pokemon, sets outsider to true
	  */
	 public void trade(){
		 this.outsider = true;
	 }
	 
	 /**
	  * A pokemon gets infected by pokerus
	  */
	 public void pokerusInfected(){
		 this.pokerus = true;
	 }
	 
	 /**
	  * Return if the pokemon has pokerus
	  * @return
	  */
	 public boolean hasPokerus(){
		 return this.pokerus;
	 }
	 
	 /**
	  * Returns true if this Pokemon's ID is 0 (aka empty)
	  * @return
	  */
	 public boolean isEmpty(){
		 return this.getBaseData().getID() == 0;
	 }
	 
	 
	 /**
	  * Sets Moves for the Pokemon
	  * @param a
	  * @param b
	  * @param c
	  * @param d
	  */
	 public void setMoves(Move a, Move b, Move c, Move d)
     {
         this.move = new Move[4];
         move[0] = a;
         move[1] = b;
         move[2] = c;
         move[3] = d;
     }
	 
	 /**
	  * Gets move i of the pokemon
	  * @param i
	  * @return
	  */
     public Move getMove(int i)
     {
         return this.move[i];
     }
     
     public void setOutsider(boolean b){
    	 this.outsider = b;
     }
     
     
	 
	 /**
	  * Get Pokemon Data from a Pokemon
	  * @return
	  */
	 public PokemonData getBaseData(){
		 return this.baseData;
	 }

	 /**
	  * Get a copy of a Pokemon
	  * @return
	  */
	public Pokemon getCopy() {
		Pokemon pkm = new Pokemon(
				this.n,
				this.level, 
				this.experience,
				this.stat.getCopy(),
				this.IV.getCopy(),
				this.EV.getCopy(),
				this.nature.getCopy(),
				this.ability.getCopy(),
				this.heldItem.getCopy(),
				this.pokerus,
				this.outsider,
				this.move[0].getCopy(),
				this.move[1].getCopy(),
				this.move[2].getCopy(),
				this.move[3].getCopy()
			);
		return pkm;
	}
	
	public PokemonType getHiddenPowerType(){
		int type = (((IV.hp % 2) + 2 * (IV.atk % 2) + 4 * (IV.def % 2) + 8 * (IV.spe % 2) + 16 *
				(IV.spa % 2) + 32 * (IV.spd % 2)) * 15 / 63);
		return new PokemonType(type);
	}
    
    
	public int getHiddenPowerDamage(){
		return 30 + (((IV.hp >> 1) % 2) + 2 * ((IV.atk >> 1) % 2) + 4 * ((IV.def >> 1) % 2)
                + 8 * ((IV.spe >> 1) % 2) + 16 * ((IV.spa >> 1) % 2) + 32 * ((IV.spd >> 1) % 2)) * 40 / 63;
	}
	
	@Override
	public String toString(){
		return this.baseData.getName();
	}
	
	public StatPack stat;
	public StatPack IV;
	public StatPack EV;
	private PokemonItem heldItem;
	private int experience;
	private int level;
	private Nature nature;
	private Ability ability;
	private boolean pokerus;
	private boolean outsider;
	private Move[] move;
	private PokemonData baseData;
	private int n;
	
	public static final int MOVE_AMOUNT = 4;
	public static final int MAX_LEVEL = 100;
}
