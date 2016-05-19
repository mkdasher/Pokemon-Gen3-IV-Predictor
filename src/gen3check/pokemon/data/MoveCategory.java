package gen3check.pokemon.data;

import java.util.ArrayList;
import java.util.List;

public enum MoveCategory {
	Physical, Special, Status;

	public static MoveCategory fromString(String s) {
		if (s.equals("Physical")) return MoveCategory.Physical;
		else if (s.equals("Special")) return MoveCategory.Special;
		else return MoveCategory.Status;
	}
	
	@Override
	public String toString(){
		if (this == Physical) return "Physical";
		else if (this == Special) return "Special";
		else return "Status";
	}
	
	/**
	 * Gets list of all move categories
	 * @return
	 */
	public static List<MoveCategory> getList(){
		List<MoveCategory> list = new ArrayList<MoveCategory>();
		list.add(Physical);
		list.add(Special);
		list.add(Status);
		return list;
	}
	
	
}
