package gen3check.pokemon.data;

import java.util.ArrayList;
import java.util.List;

public class Nature extends Data{
	
	public Nature(int n)
    {
		super(n, toStr[n]);
        if (n >= 0 && n < NATURE_N)
        {
            this.setNature(n);
        }
    }
	
	public Nature(String nat){
		super(getTypeID(nat), nat);
		if (n >= 0 && n < NATURE_N)
        {
            this.setNature(n);
        }
	}
	
	public static int getTypeID(String nat){
		for (int i = 0; i <= NATURE_N; i++){
			if (nat.equals(toStr[i])){
				return i;
			}
		}
		//Nature not found, sets to Hardy by default
		nat = "Hardy";
		return 0;
	}
	
	public void setNature(int n)
    {
		this.n = n;
		this.natureBoost = new double[StatPack.STAT_N];
        for (int i = 0; i < StatPack.STAT_N; i++)
            natureBoost[i] = 1;
        if (n % StatPack.STAT_N == 0) return; //neutral natures
        natureBoost[natStatOrder(n / 5 + 1)] = 1.1;
        natureBoost[natStatOrder(n % 5 + 1)] = 0.9;
    }
	
	public double getNatureBoost(int n)
    {
        return natureBoost[n];
    }
	
	/**
	 * get Nature Order for nature boost
	 * atk,def,spa,spd,spe to atk,def,spe,spa,spd
	 * @param n
	 * @return
	 */
	private int natStatOrder(int n)
    {
        if (n == 3) return 5;
        else if (n == 4) return 3;
        else if (n == 5) return 4;
        else return n;
    }
	
	/**
	 * Get a copy of the nature
	 * @return
	 */
	public Nature getCopy() {
		return new Nature(this.n);
	}
	
	/**
	 * Gets list of all natures
	 * @return
	 */
	public static List<Nature> getList(){
		List<Nature> list = new ArrayList<Nature>();
		for (int i = 0; i < NATURE_N; i++){ // < instead of <= cause there's no NONE nature
			list.add(new Nature(i));
		}
		return list;
	}
	
	private final static String[] toStr = {"Hardy", "Lonely", "Brave", "Adamant", "Naughty", "Bold", "Docile", "Relaxed",
		"Impish", "Lax", "Timid", "Hasty", "Serious", "Jolly", "Naive", "Modest", "Mild", "Quiet",
		"Bashful", "Rash", "Calm", "Gentle", "Sassy", "Careful", "Quirky"};
	
	public final static int NATURE_N = 25;
	private double[] natureBoost;
}
