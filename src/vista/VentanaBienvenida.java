package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.MensajesPull;

/**
 * @author Hugo Vázquez Andrés
 * @date 08/07/2016 - 12:40:29	
 * @project 14-MensajesPull-master
 * @file VentanaBienvenida.java
 */
public final class VentanaBienvenida extends JFrame implements ActionListener {

	
	private static final VentanaBienvenida vp = new VentanaBienvenida();

	private MensajesPull mp = new MensajesPull();

	private Container content = getContentPane();
	private JLabel lblImage = new JLabel(new ImageIcon(getClass().getResource("/image/mensajesPull.png")));
	private JLabel lblNombre = new JLabel();
	private JTextField txtNombre = new JTextField();
	private JButton btnEntrar = new JButton("Entrar");

	/**
	 * Constructor privado de la clase
	 */
	private VentanaBienvenida() {
		super("Bienvenido");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(353, 285);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		cargarControles();
	}

	/**
	 * Metodo que hace visible a la ventana
	 */
	public void showView(boolean visibility) {
		setVisible(visibility);
	}

	/**
	 * Metodo que agrega los controles necesarios a la ventana
	 */
	public void cargarControles() {
		content.setLayout(null);
		content.setBackground(Color.white);

		lblImage.setBounds(100, 0, 150, 150);
		content.add(lblImage);

		lblNombre.setBounds(30, 150, 200, 30);
		lblNombre.setText("Ingresar como");
		content.add(lblNombre);

		txtNombre.setBounds(130, 150, 150, 30);
		content.add(txtNombre);

		btnEntrar.setBounds(130, 200, 100, 30);
		btnEntrar.setBackground(Color.getHSBColor((float) 85, (float) 172, (float) 238));
		btnEntrar.addActionListener(this);
		content.add(btnEntrar);

	}

	/**
	 * Metodo que devuelve la instancia de la clase
	 */
	public static VentanaBienvenida getVp() {
		return vp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mp.login(txtNombre.getText());
	}
}
