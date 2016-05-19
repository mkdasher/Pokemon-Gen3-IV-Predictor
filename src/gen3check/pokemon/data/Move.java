package gen3check.pokemon.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gen3check.util.CSVFileReader;
import gen3check.util.FileUtil;

public class Move extends Data {
	public Move(int n){
		 super(n, findName(n));
		 CSVFileReader fileReader = new CSVFileReader();
		 String[] data = fileReader.getLine(n, FileUtil.MOVES);
		 this.moveType = new PokemonType(data[2]);
		 this.moveCategory = MoveCategory.fromString(data[3]);
		 this.pp = Integer.parseInt(data[4]);
		 this.power = Integer.parseInt(data[5]);
		 this.accuracy = Integer.parseInt(data[6]);
		 this.generation = Integer.parseInt(data[7]);
	}
	
	/**
	 * Private constructor. For getCopy().
	 * @param n
	 * @param name
	 * @param moveType
	 * @param moveCategory
	 * @param pp
	 * @param power
	 * @param accuracy
	 */
	private Move(int n, String name, PokemonType moveType, MoveCategory moveCategory, int pp, int power, int accuracy, int generation){
		super(n, name);
		this.moveType = moveType;
		this.moveCategory = moveCategory;
		this.pp = pp;
		this.power = power;
		this.accuracy = accuracy;
		this.generation = generation;
	}

	
	public static String findName(int n){
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, "moves.csv");
		return data[1];
	}
	
	/**
	 * Gets list of all moves
	 * @return
	 */
	public static List<Move> getList(){
		List<Move> list = new ArrayList<Move>();
		for (int i = 0; i <= MOVE_N; i++){
			list.add(new Move(i));
		}
		Collections.sort(list);
		return list;
	}
	
	public boolean isNone(){
		return (this.n == 0);
	}
	
	public int getPower(){
		return this.power;
	}
	
	public int getPP(){
		return this.pp;
	}
	
	public int getAccuracy(){
		return this.accuracy;
	}
	
	public int getGeneration(){
		return this.generation;
	}
	
	public MoveCategory getMoveCategory(){
		return this.moveCategory;
	}
	
	public PokemonType getMoveType(){
		return this.moveType;
	}
	
	public Move getCopy(){
		return new Move(this.n, this.getName(), this.moveType.getCopy(), this.moveCategory, this.pp, this.power, this.accuracy, this.generation);
	}
	
	public static final int MOVE_N = 467;
	
	private PokemonType moveType;
	private MoveCategory moveCategory;
	private int pp;
	private int power;
	private int accuracy;
	private int generation;
	
	//Move Constants
	public static final int HIDDEN_POWER = 237;
}
