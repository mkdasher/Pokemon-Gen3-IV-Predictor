package gen3check.pokemon.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gen3check.util.CSVFileReader;
import gen3check.util.FileUtil;

public class Ability extends Data{
	/**
	 * Public constructor. Creates instance from ID. (Gets name / description from file).
	 * @param n
	 */
	public Ability(int n){
		super(n, findName(n));
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, FileUtil.ABILITIES);
		this.description = data[2];
	}
	
	/**
	 * Private constructor. For getCopy()
	 * @param n
	 * @param name
	 */
	private Ability(int n, String name, String description){
		super(n, name);
		this.description = description;
	}
	
	private static String findName(int n){
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, FileUtil.ABILITIES);
		return data[1];
	}

	/**
	 * Gets list of all abilities
	 * @return
	 */
	public static List<Ability> getList(){
		List<Ability> list = new ArrayList<Ability>();
		for (int i = 0; i <= ABILITY_N; i++){
			list.add(new Ability(i));
		}
		Collections.sort(list);
		return list;
	}

	public Ability getCopy() {
		return new Ability(n, this.getName(), this.description);
	}
	
	public String getDescription(){
		return this.description;
	}
	
	private String description;
	
	public static final int ABILITY_N = 164;
	
	//Ability constants
	public static final int THICK_FAT = 47;
	public static final int TECHNICIAN = 101;
}
