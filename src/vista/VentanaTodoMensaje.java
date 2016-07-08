package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import modelo.MensajesPull;

/**
 * @author Hugo Vázquez Andrés
 * @date 08/07/2016 - 12:40:41
 * @project 14-MensajesPull-master
 * @file VentanaTodoMensaje.java
 */
public final class VentanaTodoMensaje extends JFrame implements ActionListener {

	private static final VentanaTodoMensaje vap = new VentanaTodoMensaje();

	private Container content = getContentPane();
	private JScrollPane scroll = new JScrollPane();
	private JTextArea txtMensaje = new JTextArea();
	private JButton btnEnviar = new JButton();
	private JScrollPane scrollPulls = new JScrollPane();
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JList<String> ltPulls = new JList<>();

	private int userId;
	private String user;

	private MensajesPull mp = new MensajesPull();

	/**
	 * Consutructor privado que inicializa la ventana con los controles
	 * necesarios
	 */
	private VentanaTodoMensaje() {
		super("Todos los mensajes");
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
	 * Metodo que agrega los controles necesarios
	 */
	public void cargarControles() {
		content.setLayout(null);

		scroll.setBounds(5, 260, 280, 50);
		scroll.setViewportView(txtMensaje);
		content.add(scroll);

		btnEnviar.setBounds(290, 260, 50, 49);
		btnEnviar.setIcon(new ImageIcon(getClass().getResource("/image/sent.png")));
		btnEnviar.setBackground(Color.white);
		btnEnviar.addActionListener(this);
		content.add(btnEnviar);

		scrollPulls.setViewportView(ltPulls);
		scrollPulls.setBounds(5, 20, 335, 230);
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
	 * Metodo que devuelve la instancia de la clase
	 */
	public static VentanaTodoMensaje getVap() {
		return vap;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEnviar) {
			mp.sentMessage(txtMensaje.getText(), getUserId());
			agregarModelo();
			VentanaMensajePersonal vmp = VentanaMensajePersonal.getVmp();
			vmp.agregarModelo();
		}
	}

	/**
	 * Metodo que agrega el modelo a la lista
	 */
	public void agregarModelo() {
		model = mp.llenarTablaAllPulls();
		ltPulls.setModel(model);
	}  

	/**
	 * 
	 * @return
	 */
	public JTextArea getTxtMensaje() {
		return txtMensaje;
	}

	/**
	 * 
	 * @param txtMensaje
	 */
	public void setTxtMensaje(JTextArea txtMensaje) {
		this.txtMensaje = txtMensaje;
	}

	/**
	 * Metodo que devuelve el valor del nombre de usuario
	 */
	public String getUser() {
		return user;
	}
	
	

	/**
	 * Metodo que asigna un valor a el nombre de usuario
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Metodo que devuelve el id de usuario
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Metodo que asigna el valor del id del usuario
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
