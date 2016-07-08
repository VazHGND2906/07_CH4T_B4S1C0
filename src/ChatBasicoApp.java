import vista.VentanaBienvenida;

/**
 * @author Hugo Vázquez Andrés
 * @date 08/07/2016 - 12:55:31	
 * @project 14-MensajesPull-master
 * @file ChatBasicoApp.java
 */
public class ChatBasicoApp {

	/**
	 * 
	 * @param args que recibe cualquier parametro de tipo string
	 */
	public static void main(String[] args) {
		VentanaBienvenida vp = VentanaBienvenida.getVp();
		vp.showView(true);
	}
}
