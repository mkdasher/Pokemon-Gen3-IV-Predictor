package gen3check;

import gen3check.gui.MainWindow;

import javax.swing.UIManager;

import gen3check.util.DataListUtil;

public class Main {
	
	public static void main(String[] args) {
		DataListUtil.init();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		Controller controller = new Controller(new ToolEngine());
		new MainWindow(controller);
	}
}
