package gen3check.util;

import gen3check.pokemon.Pokemon;

public class Formula {
	
	/*return floor((max(1,(exp_yield * loser_level // 7) // num_poke))
                       * trainer_battle * orig_trainer * hold_item)*/
	public static int calculateExperience(Pokemon pkmUsed, Pokemon pkmDefeated, int usedPokemonAmount, boolean isTrainer){
		if (usedPokemonAmount == 0) return 0;
		
		int yield = pkmDefeated.getBaseData().exp;
		int level = pkmDefeated.getLevel();
		float outsider = 1F;
		float trainer = 1F;
		if (isTrainer) trainer = 1.5F;
		if (pkmUsed.isOutsider()) outsider = 1.5F;
		
		int exp = ((yield * level) / 7) / usedPokemonAmount;
		if (exp < 1) exp = 1;
		exp = (int)(exp * trainer);
        exp = (int)(exp * outsider);
        
        return exp;
	}
	
	/*Damage Formula = (((((((Level × 2 ÷ 5) + 2) × BasePower × [Sp]Atk ÷ 50) ÷ [Sp]Def) × Mod1) + 2) × 
                 CH × Mod2 × R ÷ 100) × STAB × Type1 × Type2 × Mod3)*/
	public static int calculateDamage(int level, int basepower, int atk, int def, float mod1, int crit, float mod2, int rand, float stab, float supereffect1, float supereffect2, float mod3){
		int aux = level * 2 / 5;
		aux = aux + 2;
		aux *= basepower * atk;
		aux = aux / 50;
		aux = aux / def;
		aux = (int) (aux * mod1);
		aux = aux + 2;
		aux = aux * crit;
		aux = (int) (aux * mod2);
		aux = aux * rand;
		aux = aux / 100;
		aux = (int) (aux * stab);
		aux = (int) (aux * supereffect1);
		aux = (int) (aux * supereffect2);
		aux = (int) (aux * mod3);
		return aux;
	}
}
