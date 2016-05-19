package gen3check.pokemon.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gen3check.util.CSVFileReader;
import gen3check.util.FileUtil;

public class PokemonItem extends Data{
	public PokemonItem(int n){
		super(n, findName(n));
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, FileUtil.ITEMS);
		this.itemPocket = data[2];
	}
	
	/**
	 * Private constructor. For getCopy()
	 * @param n
	 * @param name
	 * @param pocket
	 */
	private PokemonItem(int n, String name, String pocket){
		super(n, name);
		this.itemPocket = pocket;
	}
	
	private static String findName(int n){
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, FileUtil.ITEMS);
		return data[1];
	}

	/**
	 * Gets list of all items
	 * @return
	 */
	public static List<PokemonItem> getList(){
		List<PokemonItem> list = new ArrayList<PokemonItem>();
		for (int i = 0; i <= ITEM_N; i++){
			list.add(new PokemonItem(i));
		}
		Collections.sort(list);
		return list;
	}

	public PokemonItem getCopy() {
		return new PokemonItem(n, this.getName(), this.itemPocket);
	}
	
	public String getItemPocket(){
		return this.itemPocket;
	}
	
	public static final int ITEM_N = 536;
	private String itemPocket;
	
	//Constants of Items ID that might be used in the program
	public static final int POTION = 17;
	public static final int FULL_RESTORE = 23;
	public static final int MAX_POTION = 24;
	public static final int HYPER_POTION = 25;
	public static final int SUPER_POTION = 26;
	
	public static final int HP_UP = 45;
	public static final int PROTEIN = 46;
	public static final int IRON = 47;
	public static final int CARBOS = 48;
	public static final int CALCIUM = 49;
	public static final int RARE_CANDY = 50;
	public static final int ZINC = 52;
	
	public static final int DIRE_HIT = 57;
	public static final int X_ATTACK = 57;
	public static final int X_DEFEND = 58;
	public static final int X_SPEED = 59;
	public static final int X_ACCURACY = 60;
	public static final int X_SPECIAL = 61;
	public static final int X_SPDEF = 62;
	
	public static final int LUCKY_EGG = 231;
	public static final int CHOICE_SPECS = 297;
	public static final int EVERSTONE = 229;
	public static final int LIGHT_BALL = 236;
	public static final int MAGNET = 242;
	public static final int CHARCOAL = 249;
}
