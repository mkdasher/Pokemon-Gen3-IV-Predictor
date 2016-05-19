package gen3check.predictor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import gen3check.Controller;
import gen3check.gui.IntegerJTextField;
import gen3check.gui.PokemonIcon;
import gen3check.gui.RestPanel;
import gen3check.pokemon.Pokemon;
import gen3check.pokemon.data.Ability;
import gen3check.pokemon.data.Nature;
import gen3check.pokemon.data.PokemonData;
import gen3check.pokemon.data.PokemonItem;
import gen3check.pokemon.data.StatPack;
import gen3check.util.ComboBoxUtil;
import gen3check.util.DataListUtil;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IVPredictorWindow extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8915061394612197634L;

	public IVPredictorWindow(JFrame parent, Controller c){
		super(parent, "Starter IV Predictor");
		this.c = c;
		this.parent = parent;
		this.setParams();
	}
	
	public void showDialog(){
		this.setVisible(true);
	}
	
	public void setParams(){
		this.setModal(true);
		this.setLayout(new BorderLayout());
		this.pkmIconLabel = new JLabel(new PokemonIcon(1));
		this.jtb = new StatButton[StatPack.STAT_N][MAX_AMOUNT];
		for (int i = 0; i < StatPack.STAT_N; i++){
			for (int j = 0; j < MAX_AMOUNT; j++){
				this.jtb[i][j] = new StatButton(i,j,this);
				this.jtb[i][j].setSelected(false);
				this.jtb[i][j].setEnabled(false);
				this.jtb[i][j].setPreferredSize(new Dimension(30,30));
			}
		}
		this.pkmIconLabel.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		this.pkmIconLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.pkmIconLabel.setOpaque(true);
		this.cmbPokemon = new JComboBox<PokemonData>();
		this.cmbPokemon.setModel(new DefaultComboBoxModel(DataListUtil.pokemonList));
		this.cmbPokemon.setEnabled(true);
		this.cmbPokemon.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				updateWindow();
			}
		});
		this.cmbPokemon.setSelectedItem(ComboBoxUtil.getComboBoxItem(this.cmbPokemon, 1));
		this.cmbNature = new JComboBox<Nature>();
		this.cmbNature.setModel(new DefaultComboBoxModel(DataListUtil.natureList));
		this.cmbNature.setEnabled(true);
		this.cmbNature.setPreferredSize(new Dimension(0,25));
		this.txtID = new IntegerJTextField(5);
		this.txtID.setPreferredSize(new Dimension(0,25));
		this.txtMinFrame = new IntegerJTextField(6);
		this.txtMaxFrame = new IntegerJTextField(6);
		
		this.ivpp = new IVPredictorPanel(this);
		this.BOXPANEL = new ImageIcon(RestPanel.class.getResource("/image/thebox.png")).getImage();
		
		this.selectedPokemon = new Pokemon();
		
		//Initialize layout
		this.getContentPane().add(new JPanel(){
			{
				this.setLayout(new BorderLayout());
				this.add(new JPanel(){
					{
						this.setLayout(new BorderLayout());
						this.add(pkmIconLabel, BorderLayout.NORTH);
						this.add(cmbPokemon, BorderLayout.CENTER);
					}
				}, BorderLayout.NORTH);
				this.add(new JPanel(){
					{
						this.setLayout(new GridLayout(StatPack.STAT_N, MAX_AMOUNT+1));
						for (int i = 0; i < StatPack.STAT_N; i++){
							switch(i){
							case 0: this.add(new JLabel(" HP")); break;
							case 1: this.add(new JLabel(" Atk")); break;
							case 2: this.add(new JLabel(" Def")); break;
							case 3: this.add(new JLabel(" SpA")); break;
							case 4: this.add(new JLabel(" SpD")); break;
							case 5: this.add(new JLabel(" Spe")); break;
							}
							for (int j = 0; j < MAX_AMOUNT; j++){
								this.add(jtb[i][j]);
							}
						}
					}
				}, BorderLayout.CENTER);
				this.add(new JPanel(){
					{
						this.setLayout(new BorderLayout());
						this.add(cmbNature, BorderLayout.NORTH);
						this.add(new JPanel(){
							{
								this.setLayout(new GridLayout(1,2));
								this.add(new JLabel("Trainer ID"));
								this.add(txtID);
							}
						}, BorderLayout.CENTER);
						this.add(new JPanel(){
							{
								this.setLayout(new GridLayout(1,2));
								this.add(new JButton("Search"){
									{
										this.setPreferredSize(new Dimension(0,40));
										this.addActionListener(new ActionListener(){
											@Override
											public void actionPerformed(ActionEvent arg0){
												StatPack sp = new StatPack(0);
												for (int i = 0; i < StatPack.STAT_N; i++){
													for (int j = 0; j < MAX_AMOUNT; j++){
														if (jtb[i][j].isEnabled() && jtb[i][j].isSelected()){
															sp.setStat(i, jtb[i][j].getValue());
														}
													}
												}
												if (txtMinFrame.isEmpty()) txtMinFrame.setText("0");
												if (txtMaxFrame.isEmpty()) txtMaxFrame.setText("0");
												if (Integer.parseInt(txtMaxFrame.getText()) < Integer.parseInt(txtMinFrame.getText())){
													txtMaxFrame.setText(txtMinFrame.getText());
												}
												if (txtID.isEmpty()) txtID.setText("0");
												if (Integer.parseInt(txtID.getText()) > 65535) txtID.setText("0");
												ivpp.obtainPokemon(
														((PokemonData) cmbPokemon.getSelectedItem()).getID(),
														(Nature) cmbNature.getSelectedItem(),
														sp,
														Integer.parseInt(txtMinFrame.getText()),
														Integer.parseInt(txtMaxFrame.getText()),
														Integer.parseInt(txtID.getText())	
												);
											}
										});
									}
								});
								this.add(new JButton("Clear"){
									{
										this.setPreferredSize(new Dimension(0,40));
										this.addActionListener(new ActionListener(){
											@Override
											public void actionPerformed(ActionEvent arg0){
												for (int i = 0; i < StatPack.STAT_N; i++){
													for (int j = 0; j < MAX_AMOUNT; j++){
														jtb[i][j].setSelected(false);
														txtID.setText("");
													}
												}
											}
										});
									}
								});
							}
						}, BorderLayout.SOUTH);
					}
				}, BorderLayout.SOUTH);
				
			}
		}, BorderLayout.WEST);
		this.getContentPane().add(new JPanel(){
			{
				this.setLayout(new BorderLayout());
				this.add(ivpp, BorderLayout.CENTER);
				this.add(new JPanel(){
					{
						this.setLayout(new BorderLayout());
						this.add(new JPanel(){
							{
								this.setPreferredSize(new Dimension(240,92));
							}
							@Override
							public void paint(Graphics g) {
								int xmarg = 20, ymarg = 10;
								g.drawImage(BOXPANEL,xmarg,ymarg,200,72,this);
								g.setColor(Color.WHITE);
								g.setFont(new Font("default", Font.BOLD,12));
								g.drawString(Integer.toString(selectedPokemon.IV.hp), xmarg + 19 - Integer.toString(selectedPokemon.IV.hp).length() * 4, ymarg + 38);
								g.drawString(Integer.toString(selectedPokemon.IV.atk), xmarg + 51 - Integer.toString(selectedPokemon.IV.atk).length() * 4, ymarg + 38);
								g.drawString(Integer.toString(selectedPokemon.IV.def), xmarg + 83 - Integer.toString(selectedPokemon.IV.def).length() * 4, ymarg + 38);
								g.drawString(Integer.toString(selectedPokemon.IV.spa), xmarg + 115 - Integer.toString(selectedPokemon.IV.spa).length() * 4, ymarg + 38);
								g.drawString(Integer.toString(selectedPokemon.IV.spd), xmarg + 147 - Integer.toString(selectedPokemon.IV.spd).length() * 4, ymarg + 38);
								g.drawString(Integer.toString(selectedPokemon.IV.spe), xmarg + 179 - Integer.toString(selectedPokemon.IV.spe).length() * 4, ymarg + 38);
								g.drawString(selectedPokemon.getHiddenPowerType().toString() + " " + Integer.toString(selectedPokemon.getHiddenPowerDamage()), xmarg + 85, ymarg + 64);
							}
						}, BorderLayout.CENTER);
						this.add(new JPanel(){
							{
								this.setLayout(new GridLayout(2,2));
								this.add(new JLabel("Min Frame Search"));
								this.add(txtMinFrame);
								this.add(new JLabel("Max Frame Search"));
								this.add(txtMaxFrame);
							}
						}, BorderLayout.SOUTH);
					}	
				}, BorderLayout.SOUTH);
			}
		}, BorderLayout.CENTER);
		
		
		
		this.pack();
		this.setLocationByPlatform(true);
		this.setLocation(parent.getX() + 10, parent.getY() + 30);
	}
	
	public void updateWindow(){
		PokemonData pkm = (PokemonData) cmbPokemon.getSelectedItem();
		pkmIconLabel.setIcon(new PokemonIcon(pkm.getID(), ICON_SIZE));
		for (int i = 0; i < StatPack.STAT_N; i++){
			for (int j = 0; j < MAX_AMOUNT; j++){
				jtb[i][j].setSelected(false);
				jtb[i][j].setEnabled(false);
				jtb[i][j].setText("");
			}
		}
		Pokemon pweak = new Pokemon(
				pkm.getID(), 5,
				new StatPack(0), new StatPack(0),
				new Nature(0), new Ability(0), new PokemonItem(0), false
		);
		Pokemon pstrong = new Pokemon(
				pkm.getID(), 5,
				new StatPack(31), new StatPack(0),
				new Nature(0), new Ability(0), new PokemonItem(0), false
		);
		StatPack spweak = new StatPack(
				pweak.stat.hp,
				(int) (pweak.stat.atk * 0.9),
				(int) (pweak.stat.def * 0.9),
				(int) (pweak.stat.spa * 0.9),
				(int) (pweak.stat.spd * 0.9),
				(int) (pweak.stat.spe * 0.9)
		);
		StatPack spstrong = new StatPack(
				pstrong.stat.hp,
				(int) (pstrong.stat.atk * 1.1),
				(int) (pstrong.stat.def * 1.1),
				(int) (pstrong.stat.spa * 1.1),
				(int) (pstrong.stat.spd * 1.1),
				(int) (pstrong.stat.spe * 1.1)
		);
		for (int i = 0; i < StatPack.STAT_N; i++){
			int diff = spstrong.getStat(i) - spweak.getStat(i) + 1;
			int j_aux = 0;
			for (int j = 0; (j < diff); j++){
				jtb[i][j_aux].setEnabled(true);
				jtb[i][j_aux].setValue(spweak.getStat(i) + j);
				j_aux++;
				if (diff > MAX_AMOUNT){ //Some pokes have a range of 7 stats, but only 6 are hitable anyway.
					if (pkm.baseStat.getStat(i) == 135){
						if (j == 5) j_aux--;
					}
					else if (j == 2) j_aux--; //160, 180, 200, 230
				}
			}
		}
	}
	
	public void buttonChanged(int row, int col) {
		for (int j = 0; j < MAX_AMOUNT; j++){
			if (j != col) jtb[row][j].setSelected(false);
		}
	}
	
	public void updateCurrentPokemon(Pokemon p) {
		this.selectedPokemon = p;
		this.revalidate();
		this.repaint();
	}
	
	private Controller c;
	private JFrame parent;
	private JLabel pkmIconLabel;
	private JComboBox<PokemonData> cmbPokemon;
	private JComboBox<Nature> cmbNature;
	private IntegerJTextField txtID, txtMinFrame, txtMaxFrame;
	private StatButton jtb[][];
	
	private static final int MAX_AMOUNT = 6;
	private static final int ICON_SIZE = 64;
	
	private IVPredictorPanel ivpp;
	private Image BOXPANEL;
	
	private Pokemon selectedPokemon;
}
