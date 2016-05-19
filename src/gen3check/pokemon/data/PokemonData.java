package gen3check.pokemon.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gen3check.util.CSVFileReader;
import gen3check.util.FileUtil;

public class PokemonData extends Data {
	public PokemonData(int n){
		 super(n, findName(n));
		 CSVFileReader fileReader = new CSVFileReader();
		 String[] data = fileReader.getLine(n, FileUtil.POKEMON);
		 this.type1 = new PokemonType(data[2]);
		 this.type2 = new PokemonType(data[3]);
		 this.genderRate = GenderRate.fromInt(Integer.parseInt(data[4]));
		 data = fileReader.getLine(n, FileUtil.POKEMON_BASESTATS);
		 this.baseStat = new StatPack(
				 Integer.parseInt(data[1]),
				 Integer.parseInt(data[2]),
				 Integer.parseInt(data[3]),
				 Integer.parseInt(data[4]),
				 Integer.parseInt(data[5]),
				 Integer.parseInt(data[6])
				 );
		 data = fileReader.getLine(n, FileUtil.POKEMON_EXP);
		 this.exp = Integer.parseInt(data[1]);
		 this.EV = new StatPack(
				 Integer.parseInt(data[2]),
				 Integer.parseInt(data[3]),
				 Integer.parseInt(data[4]),
				 Integer.parseInt(data[5]),
				 Integer.parseInt(data[6]),
				 Integer.parseInt(data[7])
				 );
		 data = fileReader.getLine(n, FileUtil.POKEMON_EXP_TYPE);
		 this.expType = new ExperienceType(data[1]);
	}
	
	/**
	 * private Constructor. For getCopy()
	 * @param n
	 */
	private PokemonData(int n, String name, StatPack baseStat, int exp, StatPack EV, PokemonType type1, PokemonType type2, ExperienceType expType){
		super(n,name);
		this.baseStat = baseStat;
		this.exp = exp;
		this.EV = EV;
		this.type1 = type1;
		this.type2 = type2;
		this.expType = expType;
	}
	
	public static String findName(int n){
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, FileUtil.POKEMON);
		return data[1];
	}
	
	public PokemonData getCopy(){
		return new PokemonData(n, this.getName(), this.baseStat.getCopy(), this.exp, this.EV.getCopy(), this.type1.getCopy(), this.type2.getCopy(), this.expType.getCopy());
	}
	
	/**
	 * Gets list of all Pokemon
	 * @return
	 */
	public static List<PokemonData> getList(){
		List<PokemonData> list = new ArrayList<PokemonData>();
		for (int i = 1; i <= POKEMON_3GEN; i++){
			list.add(new PokemonData(i));
		}
		Collections.sort(list);
		return list;
	}
	
	public static int POKEMON_N = 649;
	public static int POKEMON_3GEN = 386;
	
    public StatPack baseStat;
    public int exp;
    public StatPack EV;
    public PokemonType type1;
    public PokemonType type2;
    public ExperienceType expType;
    public GenderRate genderRate;
}
