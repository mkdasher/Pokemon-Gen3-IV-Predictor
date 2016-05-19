package gen3check.predictor;

import gen3check.pokemon.Pokemon;
import gen3check.pokemon.data.Ability;
import gen3check.pokemon.data.GenderRate;
import gen3check.pokemon.data.Nature;
import gen3check.pokemon.data.PokemonItem;
import gen3check.pokemon.data.StatPack;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import rng.PokemonMethod1;
import rng.PokemonRNG;
import rng.Seed;

public class IVPredictorPanel extends JPanel{
	
	static final long serialVersionUID = 34563;
	
	final DefaultTableModel model = new DefaultTableModel(){
		private static final long serialVersionUID = 414075547851596256L;

		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	}; 
	
	private final JTable table;
	private JScrollPane pane;

	public IVPredictorPanel(final IVPredictorWindow ivpw) {
		this.ivpw = ivpw;
		this.trainerID = this.pokemonID = 0;
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Pokemon Found List"));
		model.addColumn("Fr.");
		model.addColumn("HP"); 
		model.addColumn("Atk");
		model.addColumn("Def");
		model.addColumn("SpA");
		model.addColumn("SpD");
		model.addColumn("Spe");
		model.addColumn("M/F");
		model.addColumn("Hidden");
		model.addColumn("Pow");
		this.table = new JTable(model);
		this.table.getColumnModel().getColumn(8).setPreferredWidth(120); //Hidden Power
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.getTableHeader().setResizingAllowed(false);
		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (table.getSelectedRow() == -1) return;
				int frame = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
				PokemonRNG pkmRNG = new PokemonMethod1(new Seed(IVPredictorPanel.this.trainerID), frame);
				Pokemon p = new Pokemon(
						IVPredictorPanel.this.pokemonID,
						5,
						new StatPack(
							pkmRNG.hp,
							pkmRNG.atk,
							pkmRNG.def,
							pkmRNG.spa,
							pkmRNG.spd,
							pkmRNG.spe
						),
						new StatPack(0),
						pkmRNG.nature,
						new Ability(0),
						new PokemonItem(0),
						false	
				);
				ivpw.updateCurrentPokemon(p);
			}	
		});		
		pane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
			private static final long serialVersionUID = 1L;
			{	
				this.setPreferredSize(new Dimension(0, table.getRowHeight() * 6));
			}
		};
		this.pane.setPreferredSize(new Dimension(380,0));
		this.add(pane,BorderLayout.CENTER);
	}
	
	public void obtainPokemon(int pokemonID, Nature nature, StatPack stat, int minFrame, int maxFrame, int trainerID){
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {return;}
		
		this.trainerID = trainerID;
		this.pokemonID = pokemonID;
		
		boolean rowsAdded = false;
		
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		
		Seed s = new Seed(this.trainerID);
		Pokemon p = new Pokemon(
				pokemonID,
				5,
				new StatPack(0),
				new StatPack(0),
				nature,
				new Ability(0),
				new PokemonItem(0),
				false
		);
		for (int i = minFrame; i <= maxFrame; i++){
			PokemonRNG pkmRNG = new PokemonMethod1(s,i);
			if (pkmRNG.nature.getID() != nature.getID()) continue;
			p.IV.hp = pkmRNG.hp;
			p.IV.atk = pkmRNG.atk;
			p.IV.def = pkmRNG.def;
			p.IV.spa = pkmRNG.spa;
			p.IV.spd = pkmRNG.spd;
			p.IV.spe = pkmRNG.spe;
			p.calculateStats();
			if (
				(p.stat.hp == stat.hp || stat.hp == 0) &&
				(p.stat.atk == stat.atk || stat.atk == 0) &&
				(p.stat.def == stat.def || stat.def == 0) &&
				(p.stat.spa == stat.spa || stat.spa == 0) &&
				(p.stat.spd == stat.spd || stat.spd == 0) &&
				(p.stat.spe == stat.spe|| stat.spe == 0)
			){
				GenderRate gr = p.getBaseData().genderRate;
				String gender_str = "M";
				if (pkmRNG.isFemale(gr)) gender_str = "F";
				else if (gr == GenderRate.Genderless) gender_str = "-";
				model.addRow(new Object[]{
					i,
					p.IV.hp,
					p.IV.atk,
					p.IV.def,
					p.IV.spa,
					p.IV.spd,
					p.IV.spe,
					gender_str,
					p.getHiddenPowerType(),
					p.getHiddenPowerDamage()
				});
				rowsAdded = true;
			}
		}
			
		model.fireTableDataChanged();
		if (rowsAdded){
			table.setRowSelectionInterval(0,0);
		}
	}

	private IVPredictorWindow ivpw;
	private int trainerID;
	private int pokemonID;
}
