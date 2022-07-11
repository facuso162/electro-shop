package ui_test;

import java.sql.SQLException;
import java.util.LinkedList;

import data.FleteroDAO;
import entities.Fletero;

/* PARA CADA ABMC CREAR UNA CLASE ui_test_entidad-utilizada
 * para probar que el DAO funcione bien. Una vez visto que 
 * andan correctamente los metodos del DAO crear el JSP y Servlet
 * correspondientes.
*/
public class ui_test_fletero {
	
	private static FleteroDAO fDao = new FleteroDAO();
	
	public static void main(String[] args) {
		/* Aqui se llama a los metodos de la UI
		 * para ser testeados, comentar los que
		 * ya se probaron, y descomentar los que
		 * se quieran testear
		 */
		//add(new Fletero("Daniel", "Argento", "3416578957", "daniargento.20@gmail.com"));
		//update(new Fletero(6, "Daniel", "Argentini", "3416578957", "daniA@yahoo.com.ar"));
		//delete(new Fletero(6));
		//list();
	} 
	
	/* Aca se agregan los metodos de la UI
	 * estos utilizaran el DAO 
	 */
	private static void list() {
		LinkedList<Fletero> fleteros = fDao.list();
		for(Fletero f: fleteros) {
			System.out.println("id: " + String.valueOf(f.getId()) + " nombre: " + f.getNombre() + " apellido: " + f.getApellido());
		}
	}
	
	private static void add(Fletero f) {
		f = fDao.add(f);
		System.out.println("Se agrego el fletero, su id es: " + String.valueOf(f.getId()));
	}
	
	private static void update(Fletero f) {
		fDao.update(f);
		System.out.println("Se actualizo el fletero con exito");
	}
	
	private static void delete(Fletero f) {
		fDao.delete(f);
		System.out.println("Se borro el fletero con exito");
	}
}
