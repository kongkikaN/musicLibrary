package musicLibrary;

import javax.swing.JButton;

public class Btn {
	int id;
	String name;
	
	public Btn(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public JButton getBtn() {
		JButton btn = new JButton("Click Me");
		return btn;
	}
	public void setBtn(int id, String name) {
		JButton btn = new JButton(this.name);
		this.id = id;
	}
}
