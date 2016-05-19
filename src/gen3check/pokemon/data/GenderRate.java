package gen3check.pokemon.data;

import java.util.ArrayList;
import java.util.List;

public enum GenderRate {
	MaleOnly, Male87, Male75, Male50, Male25, FemaleOnly, Genderless;

	public static GenderRate fromInt(int n) {
		switch(n){
		case 0: return GenderRate.MaleOnly;
		case 1: return GenderRate.Male87;
		case 2: return GenderRate.Male75;
		case 3: return GenderRate.Male50;
		case 4: return GenderRate.Male25;
		case 5: return GenderRate.FemaleOnly;
		case 6: return GenderRate.Genderless;
		}
		return GenderRate.Genderless;
	}
	
	@Override
	public String toString(){
		switch(this){
		case MaleOnly: return "Male only";
		case FemaleOnly: return "Female only";
		case Genderless: return "Genderless";
		case Male25: return "Male 25%";
		case Male50: return "Male 50%";
		case Male75: return "Male 75%";
		case Male87: return "Male 87.5%";
		default:
		}
		return null;
	}
	
	/**
	 * Gets list of all move categories
	 * @return
	 */
	public static List<GenderRate> getList(){
		List<GenderRate> list = new ArrayList<GenderRate>();
		list.add(MaleOnly);
		list.add(Male87);
		list.add(Male75);
		list.add(Male50);
		list.add(Male25);
		list.add(FemaleOnly);
		list.add(Genderless);
		return list;
	}
	
	
}
