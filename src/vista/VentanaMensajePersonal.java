package vista;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import modelo.MensajesPull;

/**
 * @author Hugo Vázquez Andrés
 * @date 08/07/2016 - 12:40:34	
 * @project 14-MensajesPull-master
 * @file VentanaMensajePersonal.java
 */
public final class VentanaMensajePersonal extends JFrame {

	private static final VentanaMensajePersonal vmp = new VentanaMensajePersonal();

	private Container content = getContentPane();
	private JScrollPane scroll = new JScrollPane();
	private JScrollPane scrollPulls = new JScrollPane();
	private int userId;
	private String user;

	private MensajesPull mp = new MensajesPull();
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JList<String> ltPulls = new JList<>();

	/**
	 * Constructor privado de la clase
	 */
	private VentanaMensajePersonal() {
		super("Mis mensajes");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(350, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				agregarModelo();
			}
		});
		cargarControles();
	}

	/**
	 * Metodo que carga los controles
	 */
	public void cargarControles() {
		content.setLayout(null);

		scrollPulls.setViewportView(ltPulls);
		scrollPulls.setBounds(5, 5, 335, 310);
		ltPulls.setFont(new Font("Tahoma", 0, 20));
		content.add(scrollPulls);
	}

	/**
	 * Metodo que hace visible la ventana
	 */
	public void showView(boolean visibility) {
		setVisible(visibility);
	}

	/**
	 * Metodo que devuelve la instalancia de la clase
	 */
	public static VentanaMensajePersonal getVmp() {
		return vmp;
	}

	/**
	 * Metodo que agrega el modelo a la lista
	 */
	public void agregarModelo() {
		model = mp.llenarTablaMyPulls(getUserId());
		ltPulls.setModel(model);
	}

	/**
	 * Metodo que devuelve el valor del usuario
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Metodo que asigna el valor del usuario
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Metodo que devuelve el id del usuario
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Metodo que asigna el id del usuario
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
