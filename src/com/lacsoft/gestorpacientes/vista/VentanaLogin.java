package com.lacsoft.gestorpacientes.vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.divxdede.swing.busy.DefaultBusyModel;
import org.divxdede.swing.busy.JBusyComponent;

import com.lacsoft.gestorpacientes.correo.EnviadorDeCorreo;
import com.lacsoft.gestorpacientes.entidades.Usuario;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotones;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabels;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabelsGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextosGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloLogin;
import com.lacsoft.gestorpacientes.persistencia.Serializadora;

public class VentanaLogin extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField txtUsuario;
	private JTextField txtClave;
	private JLabel lblUsuario, lblClave, lblBanner, lblRecordarClave;
	private JButton btnEntrar, btnCancelar;
	private JCheckBox checkRecordar;
	private JPanel pnlCampos, pnlBotones, pnlComplementos, pnlTodo;
	
	private DefaultBusyModel modeloBusy = new DefaultBusyModel();
	private JBusyComponent<JPanel> componenteBusy;
	
	private Serializadora serializadora = new Serializadora();

	private FactoriaDeTextosGenericos factoriadeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	
	public VentanaLogin() {
		super("Gestor de Pacientes | LAC-soft");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());
		
		txtUsuario = factoriadeTextos.crearJtextFieldEnmascarado("UUU-#####");
		txtUsuario.setColumns(11);
		txtClave = factoriadeTextos.crearJTextFieldEstiloClave();
		txtClave.setColumns(11);
		
		try {
			txtUsuario.setText(( (Usuario) serializadora.leer("usuario.dat")).getCodigoDeEmpleado());
			txtClave.setText(( (Usuario) serializadora.leer("usuario.dat")).getClave());
		} catch (Exception e) {
			System.out.println("Mueren");
		}

		lblUsuario = factoriaDeLabels.crearLabel("Usuario");
		lblClave = factoriaDeLabels.crearLabel("Contraseña");
		lblBanner = new JLabel(new ImageIcon(
				"Imagenes/mantenimientosBanner2.JPG"));
		lblRecordarClave = new JLabel("¿Olvidó su contraseña?");
		lblRecordarClave.setForeground(Color.blue);

		btnEntrar = factoriaDeBotones.crearBoton("Entrar");
		btnEntrar.setForeground(Color.BLUE);
		btnCancelar = factoriaDeBotones.crearBoton("Cancelar");
		btnCancelar.setForeground(Color.RED);

		checkRecordar = new JCheckBox("Recordar cuenta", true);
		checkRecordar.setForeground(Color.blue);

		pnlCampos = new JPanel(new GridLayout(2, 2, -5, 5));

		pnlCampos.add(lblUsuario);
		pnlCampos.add(txtUsuario);
		pnlCampos.add(lblClave);
		pnlCampos.add(txtClave);

		pnlComplementos = new JPanel(new GridLayout(1, 1, 5, 5));
		pnlComplementos.add(checkRecordar);
		pnlComplementos.add(lblRecordarClave);

		pnlBotones = new JPanel(new FlowLayout());
		pnlBotones.add(btnEntrar);
		pnlBotones.add(btnCancelar);

		pnlTodo = new JPanel();
		pnlTodo.add(lblBanner);
		pnlTodo.add(pnlCampos);
		pnlTodo.add(pnlComplementos);
		pnlTodo.add(pnlBotones);
		
		componenteBusy = new JBusyComponent<JPanel>(pnlTodo);
		componenteBusy.setBusyModel(modeloBusy);
		
		add(componenteBusy);

		setSize(380, 380);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		lblRecordarClave.addMouseListener(new MouseAdapter() {
			
			private String codigoUsuario, nombreCompleto, correo, clave;
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRecordarClave.setForeground(Color.RED);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				int confirmacion = JOptionPane.showConfirmDialog(VentanaLogin.this,
						"¿Confirma que ha olvidado su contraseña? \nSe enviará información " +
						"a su correo electrónico para obtener su contraseña",
						"Confirmación", JOptionPane.YES_NO_OPTION);
				if (confirmacion == JOptionPane.YES_OPTION) {

					codigoUsuario = JOptionPane.showInputDialog(
							VentanaLogin.this,
							"Introduzca su código de empleado",
							"Obtención de contraseña",
							JOptionPane.QUESTION_MESSAGE);
					
					/*
					 * Para convertir el código del usuario en mayúsculas.
					 */
					codigoUsuario = (!(codigoUsuario == null)) ? codigoUsuario.toUpperCase() : codigoUsuario;

					if (!(codigoUsuario == null)) {

						if (ModeloLogin.getInstancia().verificarCodigo(
								codigoUsuario)) {

							Usuario usuarioLoguin = ModeloLogin.getInstancia()
									.obtenerDatosCorreo(codigoUsuario);

							nombreCompleto = usuarioLoguin.getNombre()
									+ " " + usuarioLoguin.getApellido();
							correo = usuarioLoguin.getCorreo();
							clave = usuarioLoguin.getClave();
							
							/**
							 * Hilo para poner una imagen cargando en lo que se envía en correo
							 */
							
							new SwingWorker<Void, Void>() {
								boolean enviado = true;

								@Override
								protected Void doInBackground()
										throws Exception {
									componenteBusy.setBusyModel(modeloBusy);
									modeloBusy.setCancellable(true);
									modeloBusy.setBusy(true);
									enviado = new EnviadorDeCorreo()
									.enviarCorreo(correo, nombreCompleto, codigoUsuario, clave);
									return null;
								}
								
								@Override
								protected void done() {
									modeloBusy.setBusy(false);
									if (enviado) {
										JOptionPane
										.showMessageDialog(
												VentanaLogin.this,
												"Se ha enviado un correo con la contraseña a su dirección de correo electrónico",
												"Correo enviado",
												JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(null, 
												"No se pudo enviar el correo por problemas de conexión " +
												"a Internet", "Problemas al enviar correo", 
												JOptionPane.INFORMATION_MESSAGE);
									}
								}
							}.execute();
						} else {
							JOptionPane
							.showMessageDialog(
									VentanaLogin.this,
									"Debe ingresar su código de empleado correctamente",
									"Código de empleado incorrecto",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane
						.showMessageDialog(
								VentanaLogin.this,
								"Debe ingresar su código de empleado correctamente",
								"Código de empleado incorrecto",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblRecordarClave.setForeground(Color.BLUE);
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreUsuario = txtUsuario.getText().trim();
				String clave = txtClave.getText().trim();

				if (nombreUsuario.equals("")
						|| clave.equals("")) {
					JOptionPane.showMessageDialog(null,
							"Debe poner su nombre y contraseña");
				} else {
					if (checkRecordar.isSelected()) {
						serializadora.escribir(new Usuario(nombreUsuario, clave));
					}
					String rol = ModeloLogin.getInstancia().comprobarUsuario(
							txtUsuario.getText(), txtClave.getText());
					
					if ("Asistente".equals(rol)) {
						VentanaLogin.this.dispose();
						new VentanaPrincipalAsistente();
					} else if ("Médico".equals(rol)) {
						VentanaLogin.this.dispose();
						new VentanaPrincipal();
					} else if ("Administrador".equals(rol)) {
						VentanaLogin.this.dispose();
						new VentanaPrincipal();
					} else {
						JOptionPane.showMessageDialog(VentanaLogin.this, 
								"Usuario o contraseña inválida");
					}

				}
			}
		});

		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					txtClave.requestFocus();
					
					if (!txtClave.getText().trim().equals("")) {
						btnEntrar.doClick();
					}
				}
			}
		});

		txtClave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnEntrar.doClick();
				}
			}
		});

	}

}
