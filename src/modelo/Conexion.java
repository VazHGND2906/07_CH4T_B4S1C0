package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * @author Hugo Vázquez Andrés
 * @date 08/07/2016 - 12:40:15
 * @project 14-MensajesPull-master
 * @file Conexion.java
 */
public final class Conexion {

	private static final Conexion con = new Conexion();
	private Connection conect = null;

	/**
	 * Constructor privado de la clase
	 */
	private Conexion() {
		getConect();
	}

	/**
	 * Metodo privado que realiza la conexion
	 */
	private boolean conection() {
		// DATOS PARA LA CONEXION A LA BASE DE DATOS
		// DATOS PARA LA CONEXION A LA BASE DE DATOS
		// DATOS PARA LA CONEXION A LA BASE DE DATOS

		String basededatos = "chat_basico";
		String usuario = "hugo";
		String password = "secret";

		// FIN DATOS PARA LA CONEXION A LA BASE DE DATOS
		// FIN DATOS PARA LA CONEXION A LA BASE DE DATOS
		// FIN DATOS PARA LA CONEXION A LA BASE DE DATOS

		String url = "jdbc:mysql://localhost/" + basededatos + "?noAccessToProcedureBodies=true";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conect = DriverManager.getConnection(url, usuario, password);
			return true;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error con la base de datos. " + ex.getLocalizedMessage(), " Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (InstantiationException ex) {
			JOptionPane.showMessageDialog(null, "No se puedo establecer la conexion", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "No se econtro el Driver", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (IllegalAccessException ex) {
			JOptionPane.showMessageDialog(null, "Error de compatibilidad", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	/**
	 * Metodo que optiene la conexion
	 * 
	 * @return la variable que mantiene la conexion
	 */
	public Connection getConect() {
		if (conect == null) {
			conection();
		}
		return conect;
	}

	/**
	 * 
	 * @return Instancia de la clase
	 */
	public static Conexion getCon() {
		return con;
	}

}