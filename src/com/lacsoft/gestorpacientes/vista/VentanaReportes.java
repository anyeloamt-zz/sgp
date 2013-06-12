package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotones;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;
import com.lacsoft.gestorpacientes.reportes.Reportes;

public class VentanaReportes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel pnlReporte, pnlPrincipal;
	private JButton btnTopPadecimientos, btnTopMedicos;
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private static VentanaReportes instancia;
	private Reportes reporte = new Reportes();

	public static synchronized VentanaReportes getInstancia() {
		return instancia = (instancia == null) ? new VentanaReportes() : instancia;
	}

	protected void setInstanceNull() {
		instancia = null;
	}

	private VentanaReportes() {
		setTitle("Gestor de pacientes | Reportes");
		setIconImage(new ImageIcon("Imagenes/reportes.PNG").getImage());

		btnTopPadecimientos = factoriaDeBotones
				.crearBotonConBorde("10 padecimientos más frecuentes", 
						new ImageIcon("Imagenes/reportes.PNG"));
		btnTopMedicos = factoriaDeBotones
				.crearBotonConBorde("10 médico más consultados", 
						new ImageIcon("Imagenes/reportes.PNG"));
		btnTopMedicos.setBorder(BorderFactory
				.createLineBorder(new Color(00, 72, 255), 2));

		btnTopPadecimientos.setBorder(BorderFactory
				.createLineBorder(new Color(00, 72, 255), 2));
		pnlReporte = new JPanel();
		pnlReporte.add(btnTopPadecimientos);
		pnlReporte.add(btnTopMedicos);
		pnlReporte.setLayout(new GridLayout(2, 1));

		pnlPrincipal = new JPanel();
		pnlPrincipal.add(pnlReporte);

		add(new JLabel(new ImageIcon("Imagenes/mantenimientosBanner2.JPG")), 
				BorderLayout.NORTH);
		add(pnlPrincipal, BorderLayout.CENTER);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 250);
		setVisible(true);
		
		btnTopPadecimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                    reporte.GenerarReportes("reportes/TopPadecimientos.jasper");				
			}
		});
		
		btnTopMedicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                   reporte.GenerarReportes("reportes/TopMedicosMasConsultado.jasper");				
			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				setInstanceNull();
			}
		});

	}

}
