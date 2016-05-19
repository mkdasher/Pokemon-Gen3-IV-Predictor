package gen3check.gui;

import gen3check.Controller;
import gen3check.observers.PokemonListContainerObserverHelper;
import gen3check.pokemon.Pokemon;
import gen3check.pokemon.data.Ability;
import gen3check.pokemon.data.GenderRate;
import gen3check.pokemon.data.PokemonData;
import gen3check.pokemon.data.PokemonItem;
import gen3check.pokemon.data.StatPack;
import gen3check.PokemonFoundData;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import rng.HiddenPowerRNG;
import rng.PokemonMethod1;
import rng.PokemonRNG;
import rng.Seed;

public class PokemonFoundPanel extends JPanel{
	
	static final long serialVersionUID = 547805783;
	
	final DefaultTableModel model = new DefaultTableModel(){
		private static final long serialVersionUID = 4140756218591596256L;

		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	}; 
	
	private final JTable table;
	private JScrollPane pane;
	
	public PokemonFoundPanel(final MainWindow mw, final Controller c, final RestPanel rp){
		this.rp = rp;
		this.mw = mw;
		this.c = c;
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Pokemon Found List"));
		model.addColumn("Frame");
		model.addColumn("Time");
		model.addColumn("Nature");
		model.addColumn("HP"); 
		model.addColumn("Atk");
		model.addColumn("Def");
		model.addColumn("SpA");
		model.addColumn("SpD");
		model.addColumn("Spe");
		model.addColumn("M/F");
		model.addColumn("Hidden");
		model.addColumn("Power");
		this.table = new JTable(model){
			private static final long serialVersionUID = 1L;
			{
				c.addPokemonListContainerObserver(new PokemonListContainerObserverHelper() {
					private Thread changeThread;
					@Override
					public void pokemonListChange(final List<PokemonFoundData> pokemonList) {
						int scrollVal = pane.getHorizontalScrollBar().getValue();
						//we can only change if we can interrupt, else it's not neccesary
						if (changeThread != null && changeThread.isAlive())
							changeThread.interrupt();
						//avoiding to update the table multiple times in a few miliseconds (else we can get errors)
						(changeThread = new Thread() {
							public void run() {
								//if the table is changed before 25 milisecs, we don't update it yet.
								try {
									Thread.sleep(25);
								} catch (InterruptedException e) {return;}
								
								model.getDataVector().removeAllElements();
								model.fireTableDataChanged();
								GenderRate gr = new PokemonData(c.getPokemonID()).genderRate;
								for (int i = 0; i < pokemonList.size(); i++) {
									PokemonRNG pokemon_aux = pokemonList.get(i).getPokemon();
									String gender_str = "M";
									if (pokemon_aux.isFemale(gr)) gender_str = "F";
									else if (gr == GenderRate.Genderless) gender_str = "-";
									int frame_aux = pokemonList.get(i).getFrame();
									HiddenPowerRNG hpow = new HiddenPowerRNG(pokemon_aux);
									model.addRow(new Object[]{
											frame_aux,
											Integer.toString(frame_aux / 3600) + ":" + String.format("%02d",(frame_aux / 60) % 60) + "." + String.format("%02d",(frame_aux % 60) * 100 / 60),
											pokemon_aux.nature,
											pokemon_aux.hp,
											pokemon_aux.atk,
											pokemon_aux.def,
											pokemon_aux.spa,
											pokemon_aux.spd,
											pokemon_aux.spe,
											gender_str,
											HiddenPowerRNG.hp_tostr[hpow.type],
											hpow.damage
									});
								}
								model.fireTableDataChanged();
								if (pokemonList.size() > 0){
									table.setRowSelectionInterval(0,0);
								}
								pane.getVerticalScrollBar().setValue(0);
							}
						}).start();
					}
				});
				
			}	
		};
		// Setting sizes of some columns
		for (int i = 3; i < 10; i++){
			this.table.getColumnModel().getColumn(i).setPreferredWidth(35);
		}
		
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.getTableHeader().setResizingAllowed(false);
		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (table.getSelectedRow() == -1) return;
				int frame = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
				PokemonRNG pRNG = new PokemonMethod1(new Seed(c.getTrainerID()), frame);
				Pokemon pokemon = new Pokemon(
						c.getPokemonID(), 5, new StatPack(pRNG.hp, pRNG.atk, pRNG.def, pRNG.spa, pRNG.spd, pRNG.spe),
						new StatPack(0), pRNG.nature, new Ability(0), new PokemonItem(0), false);
				rp.updateSelectedPokemon(pokemon, frame);
			}	
		});
		pane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
			private static final long serialVersionUID = 1L;
			{	
				this.setPreferredSize(new Dimension(0, table.getRowHeight() * 6));
			}
		};
		this.add(pane,BorderLayout.CENTER);
	}
	
	public void updateGridAround(int frame){
		List<PokemonFoundData> pokemonList = new ArrayList<PokemonFoundData>();
		Seed s = new Seed(c.getTrainerID());
		int frame_index = 0;
		for (int i = frame - 20; i < frame + 20; i++){
			if (i >= 0){ 
				pokemonList.add(new PokemonFoundData(new PokemonMethod1(s,i),i));
				if (i < frame) frame_index++;
			}
		}
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		GenderRate gr = new PokemonData(c.getPokemonID()).genderRate;
		for (int i = 0; i < pokemonList.size(); i++) {
			PokemonRNG pokemon_aux = pokemonList.get(i).getPokemon();
			String gender_str = "M";
			if (pokemon_aux.isFemale(gr)) gender_str = "F";
			else if (gr == GenderRate.Genderless) gender_str = "-";
			int frame_aux = pokemonList.get(i).getFrame();
			HiddenPowerRNG hpow = new HiddenPowerRNG(pokemon_aux);
			model.addRow(new Object[]{
					frame_aux,
					Integer.toString(frame_aux / 3600) + ":" + String.format("%02d",(frame_aux / 60) % 60) + "." + String.format("%02d",(frame_aux % 60) * 100 / 60),
					pokemon_aux.nature,
					pokemon_aux.hp,
					pokemon_aux.atk,
					pokemon_aux.def,
					pokemon_aux.spa,
					pokemon_aux.spd,
					pokemon_aux.spe,
					gender_str,
					HiddenPowerRNG.hp_tostr[hpow.type],
					hpow.damage
			});
		}
		model.fireTableDataChanged();
		if (pokemonList.size() > 0){
			table.setRowSelectionInterval(frame_index, frame_index);
		}
		this.removeAll();
		pane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
			private static final long serialVersionUID = 1L;
			{	
				this.setPreferredSize(new Dimension(0, table.getRowHeight() * 6));
			}
		};
		this.add(pane,BorderLayout.CENTER);
		int value = pane.getVerticalScrollBar().getMaximum() / 2 - 3 * table.getRowHeight();
		if (value < 0) value = 0;
		pane.getVerticalScrollBar().setValue(value);
		pane.repaint();
		pane.revalidate();
		this.repaint();
		this.revalidate();
		value = pane.getVerticalScrollBar().getMaximum() / 2 - 3 * table.getRowHeight();
		if (value < 0) value = 0;
		pane.getVerticalScrollBar().setValue(value);
	}

	public int getSelectedIndex() {
		return table.getSelectedRow();
	}
	
	private RestPanel rp;
	private Controller c;
	private MainWindow mw;
}
