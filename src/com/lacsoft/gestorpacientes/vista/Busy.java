package com.lacsoft.gestorpacientes.vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.divxdede.swing.busy.DefaultBusyModel;
import org.divxdede.swing.busy.JBusyComponent;
import org.divxdede.swing.busy.icon.DefaultBusyIcon;
import org.divxdede.swing.busy.ui.BasicBusyLayerUI;
import org.divxdede.swing.busy.ui.BusyLayerUI;

public class Busy extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultBusyModel modelo = new DefaultBusyModel();;

	public Busy() {
		super("Mueren");

		JPanel tree = new JPanel();
		tree.add(new JLabel("Todos mueren"));
		tree.add(new JButton("Morirán"));
		
		JBusyComponent<JPanel> treeComponent = new JBusyComponent<>(tree);

		treeComponent.setBusyModel(modelo);
		modelo.setCancellable(true);
//		modelo.setDeterminate(true);
//		modelo.setMinimum(0);
//		modelo.setMaximum(154151);
//		// modelo.setValueIsAdjusting(true);
//		modelo.setAutoCompletionEnabled(true);
		modelo.setBusy(true);

		BusyLayerUI ui = new BasicBusyLayerUI();
		
		//ui.setBusyIcon(new RadialBusyIcon(new ImageIcon("Imagenes/icono.PNG"), new Insets(50, 50, 50, 50)));
		//ui.setBusyIcon(new InfiniteBusyIcon(new BusyPainter(150), new Dimension(500, 500)));
		ui.setBusyIcon(new DefaultBusyIcon(new ImageIcon("Imagenes/icono.PNG")));
		//ui.setBusyIcon(new DefaultBusyIcon(new ImageIcon("Imagenes/icono.PNG"), getInsets()));
		treeComponent.setBusyLayerUI(ui);
		
		add(treeComponent);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 600);
		setVisible(true);

		int i = 0;
		while (modelo.isBusy()) {
			System.out.println(i);
			i++;
			modelo.setValue(modelo.getValue() + 1);
		}

		if (!modelo.isBusy()) {
			JOptionPane.showMessageDialog(Busy.this, "Mueren");
		}

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		new Busy();
	}

}
