package gen3check;

import javax.swing.JCheckBox;

import gen3check.observers.PokemonListContainerObserver;
import gen3check.pokemon.data.StatPack;

public class Controller {
	private ToolEngine e;
	public Controller(ToolEngine e) {
		this.e = e;
	}
	
	public void onPokemonGridSelectionChanged(int index){
		this.e.onPokemonGridSelectionChanged(index);
	}
	
	public void addPokemonListContainerObserver(PokemonListContainerObserver observer) {
		this.e.addPokemonListContainerObserver(observer);
	}
	public int getTrainerID(){
		return this.e.getTrainerID();
	}
	public int getPokemonID(){
		return this.e.getPokemonID();
	}
	public PokemonFoundData getPokemon(int index){
		return this.e.getPokemon(index);
	}
	public void requestQuit() {
		this.e.quit();
	}
	
	//////////////
	
	public void search(int minFrame, int maxFrame, int trainerID, int pokemonID, StatPack spminus, StatPack spneutral, StatPack spplus, JCheckBox[] natures){
		this.e.search(minFrame,maxFrame,trainerID,pokemonID,spminus,spneutral,spplus, natures);
	}
}
