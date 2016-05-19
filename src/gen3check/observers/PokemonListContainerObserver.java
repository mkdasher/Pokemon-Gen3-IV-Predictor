package gen3check.observers;

import java.util.List;

import gen3check.PokemonFoundData;

public interface PokemonListContainerObserver {
	
	/**
	 * Notifies that the list has been changed
	 * @param eventList
	 */
	public void pokemonListChange(List<PokemonFoundData> pList);
	
	/**
	 * Notifies that a new event has been selected in the Event Grid, and gets the current event.
	 */
	public void pokemonSelectionChange(PokemonFoundData current);
}
