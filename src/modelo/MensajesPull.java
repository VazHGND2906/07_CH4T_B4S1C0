package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import vista.VentanaTodoMensaje;
import vista.VentanaMensajePersonal;
import vista.VentanaBienvenida;

/**
 * @author Hugo Vázquez Andrés
 * @date 08/07/2016 - 12:40:23
 * @project 14-MensajesPull-master
 * @file MensajesPull.java
 */
public class MensajesPull {
	private ResultSet sent = null;
	private Statement eject = null;
	private int ejectSent = 0;

	private Conexion con = Conexion.getCon();

	private Connection conect = con.getConect();

	/**
	 * 
	 * Metodo que inicia sesion
	 */
	public void login(String usuario) {
		try {
			sent = ejecutarConsulta(String.format("SELECT userName FROM users WHERE userName LIKE '%s'", usuario));

			if (!sent.next()) {
				ejecutarSentence(String.format("INSERT INTO users VALUES(null, '%s')", usuario));
			}
			showViews(usuario);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al ejecutar la operacion\n" + e);
		}
	}

	/**
	 * 
	 * Metodo que muestra las ventanas necesarias y cierra la principal
	 */
	public void showViews(String usuario) {
		VentanaTodoMensaje vap = VentanaTodoMensaje.getVap();
		vap.showView(true);
		VentanaBienvenida vp = VentanaBienvenida.getVp();
		vp.showView(false);
		VentanaMensajePersonal vmp = VentanaMensajePersonal.getVmp();
		vmp.showView(true);

		sent = ejecutarConsulta(String.format("SELECT * FROM users WHERE userName LIKE '%s'", usuario));

		try {
			while (sent.next()) {
				vap.setUserId(sent.getInt(1));
				vap.setUser(sent.getString(2));
				vmp.setUserId(sent.getInt(1));
				vmp.setUser(sent.getString(2));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al ejecutar la operacion\n" + e);
		}
	}

	/**
	 * Metodo que registra los mensajes enviados
	 */
	public void sentMessage(String message, int idUser) {
		ejecutarSentence(
				String.format("INSERT INTO messages VALUES(null, %d, curdate(), curtime(),'%s')", idUser, message));
	}

	/**
	 * Metodo que llena el modelo de la lista y devuelve el modelo para despues
	 * agregarlo a una lista
	 */
	public DefaultListModel<String> llenarTablaAllPulls() {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		VentanaTodoMensaje vtm = VentanaTodoMensaje.getVap();
		// vap.showView(true);
		String mensaje = "";
		try {

			// vtm.setTxtMensaje();
			sent = ejecutarConsulta(
					"SELECT userName, date_format(dateMessage, '%y/%m/%d'), date_format(timeMessage, '%H:%i'), message FROM users "
							+ "INNER JOIN messages ON messages.idUser=users.idUser");
			while (sent.next()) {

				Object[] fila = new Object[1];
				for (int i = 0; i < fila.length; i++) {
					mensaje = "<html><body><p>-" + sent.getString(1) + " <a href=\"#\">el</a> " + sent.getObject(2)
							+ " <a href=\"#\">a las</a> " + sent.getObject(3) + "</p><p>" + sent.getObject(4)
							+ "<p><br></body></html>";
				}
				modelo.addElement(mensaje);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return modelo;
	}

	/**
	 * Metodo que llena el modelo de la lista recibiendo al usuario que esta
	 * logeado y devolviendo el modelo para despues agregarlo a una lista
	 */
	public DefaultListModel<String> llenarTablaMyPulls(int idUsuario) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		String mensaje = "";
		try {
			sent = ejecutarConsulta(
					"SELECT userName, date_format(dateMessage, '%y/%m/%d'), date_format(timeMessage, '%H:%i'), message FROM users "
							+ "INNER JOIN messages ON messages.idUser=users.idUser WHERE users.idUser = " + idUsuario);
			while (sent.next()) {
				Object[] fila = new Object[1];
				for (int i = 0; i < fila.length; i++) {
					mensaje = "<html><body><p>-" + sent.getString(1) + " <a href=\"#\">el</a> " + sent.getObject(2)
							+ " <a href=\"#\">a las</a> " + sent.getObject(3) + "</p><p>" + sent.getObject(4)
							+ "</p><br></body></html>";
				}
				modelo.addElement(mensaje);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return modelo;
	}

	/**
	 * Metodo que ejecuta las sentencias (INSERT) recibiendo la sentencia como
	 * parametro
	 */
	public int ejecutarSentence(String query) {
		try {
			ejectSent = eject.executeUpdate(query);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return ejectSent;
	}

	/**
	 * Metodo que ejecuta consultas recibiendo como parametro la consulta
	 */
	public ResultSet ejecutarConsulta(String query) {
		try {
			eject = conect.createStatement();
			sent = eject.executeQuery(query);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al ejecutar la operacion\n" + ex);
		}
		return sent;
	}

}
