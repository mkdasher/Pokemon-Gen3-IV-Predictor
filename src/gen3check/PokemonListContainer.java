package gen3check;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gen3check.util.Observable;
import gen3check.observers.PokemonListContainerObserver;

public class PokemonListContainer extends Observable<PokemonListContainerObserver>{
	public PokemonListContainer(){
		this.eventList = new ArrayList<PokemonFoundData>();
		this.pokemonID = 1;
	}
	
	public void setPokemonID(int id){
		this.pokemonID = id;
	}
	
	public int getPokemonID(){
		return this.pokemonID;
	}
	
	public void setTrainerID(int id){
		this.trainerID = id;
	}
	
	public int getTrainerID(){
		return this.trainerID;
	}
	
	public void clear(){
		while (this.eventList.size() > 0){
			this.eventList.remove(0);
		}
		this.onPokemonListChange();
	}
	
	public void update(){
		this.onPokemonListChange();
	}
	
	public void addPokemon(PokemonFoundData e){
		this.eventList.add(e);
	}
	
	public void deletePokemon(int n) {
		this.eventList.remove(n);
	}
	
	public void moveUp(int index) {
		if (index <= 0) return;
		Collections.swap(eventList, index, index - 1);
		this.onPokemonListChange();
	}
	public void moveDown(int index) {
		if (index >= eventList.size()) return;
		Collections.swap(eventList, index, index + 1);
		this.onPokemonListChange();
	}
	
	public void editPokemon(PokemonFoundData pd, int index) {
		this.eventList.set(index, pd);
		this.onPokemonListChange();
	}
	
	public void setPokemonList(List<PokemonFoundData> pList) {
		this.eventList = pList;
		this.onPokemonListChange();
	}
	
	public List<PokemonFoundData> getEventList(){
		return this.eventList;
	}
	
	public int size(){
		return this.eventList.size();
	}
	
	public PokemonFoundData getPokemon(int n) {
		if (n < 0 || n >= this.eventList.size()){
			return null;
		}
		return this.eventList.get(n);
	}
	
	private void onPokemonListChange(){
		for (int i = 0; i < this.observers.size(); i++){
			this.observers.get(i).pokemonListChange(eventList);
		}
	}
	
	public void onPokemonGridSelectionChanged(int index) {
		//If no selection
		if (index == -1) return;

		for (int i = 0; i < this.observers.size(); i++){
			this.observers.get(i).pokemonSelectionChange(eventList.get(index));
		}
	}
	
	private List<PokemonFoundData> eventList;
	private int pokemonID;
	private int trainerID;
}
