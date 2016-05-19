package gen3check.util;

import javax.swing.JComboBox;

import gen3check.pokemon.data.Data;

public class ComboBoxUtil {
	/**
	 * Finds an item in a JComboBox<Data> based on its ID.
	 * @param comboBox
	 * @param ID
	 * @return
	 */
	public static Data getComboBoxItem(JComboBox comboBox, int ID){
		for (int i = 0; i < comboBox.getModel().getSize(); i++){
			if (((Data)comboBox.getModel().getElementAt(i)).getID() == ID)
				return (Data)comboBox.getModel().getElementAt(i);
		}
		return null;
	}
}
