package gen3check;

import javax.swing.JCheckBox;

import gen3check.observers.PokemonListContainerObserver;
import gen3check.pokemon.data.StatPack;
import rng.*;

public class ToolEngine {
	
	private PokemonListContainer elc;
	
	public ToolEngine(){
		this.elc = new PokemonListContainer();
	}
	
	/**
	 * Calls eventSelectionChange
	 */
	public void onPokemonGridSelectionChanged(int index) {
		this.elc.onPokemonGridSelectionChanged(index);
	}
	
		
	/****************************************/
	
	public void quit() {
		System.exit(0);		
	}

	public void addPokemonListContainerObserver(PokemonListContainerObserver observer) {
		this.elc.addObserver(observer);
	}
	
	public int getTrainerID(){
		return this.elc.getTrainerID();
	}
	public int getPokemonID(){
		return this.elc.getPokemonID();
	}
	public PokemonFoundData getPokemon(int index){
		return this.elc.getPokemon(index);
	}
	
	/////////////////////////////////////////

	public void search(int minFrame, int maxFrame, int trainerID,
			int pokemonID, StatPack spminus, StatPack spneutral,
			StatPack spplus, JCheckBox[] natures) {
		this.elc.clear();
		this.elc.setPokemonID(pokemonID);
		this.elc.setTrainerID(trainerID);
		Seed s = new Seed(trainerID);
		for (int i = minFrame; i <= maxFrame; i++){
			PokemonRNG pkmRNG = new PokemonMethod1(s,i);
			if (natures[pkmRNG.nature.getID()].isSelected()){
				StatPack sp = new StatPack(
						spneutral.hp,
						spneutral.atk,
						spneutral.def,
						spneutral.spa,
						spneutral.spd,
						spneutral.spe
				);
				for (int k = 1; k < 6; k++){
					if (pkmRNG.nature.getNatureBoost(k) > 1.01) sp.setStat(k, spplus.getStat(k));
					if (pkmRNG.nature.getNatureBoost(k) < 0.99) sp.setStat(k, spminus.getStat(k));
				}
				if (sp.hp <= pkmRNG.hp &&
					sp.atk <= pkmRNG.atk &&
					sp.def <= pkmRNG.def &&
					sp.spa <= pkmRNG.spa &&
					sp.spd <= pkmRNG.spd &&
					sp.spe <= pkmRNG.spe){
						this.elc.addPokemon(new PokemonFoundData(pkmRNG,i));
				}
			}
		this.elc.update();
		}
	}
	
}
