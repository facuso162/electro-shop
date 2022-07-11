package ui_test;

import java.util.LinkedList;

import data.CategoriaDAO;
import entities.Categoria;

public class ui_test_categoria {
	
	private static CategoriaDAO cDao = new CategoriaDAO();
	
	public static void main(String[] args) {
		//add(new Categoria("Televisores"));
		//add(new Categoria("Computadoras"));
		//update(new Categoria(2, "Microondas"));
		//delete(new Categoria(1));
		//list();
	}
	private static void list() {
		LinkedList<Categoria> categorias = cDao.list();
		for(Categoria c: categorias) {
			System.out.println("id: " + String.valueOf(c.getId()) + " descripcion: " + c.getDescripcion());
		}
	}
	
	private static void add(Categoria c) {
		c = cDao.add(c);
		System.out.println("Se agrego la categoria, su id es: " + String.valueOf(c.getId()));
	}
	
	private static void update(Categoria c) {
		cDao.update(c);
		System.out.println("Se actualizo la categoria con exito");
	}
	
	private static void delete(Categoria c) {
		cDao.delete(c);
		System.out.println("Se borro la categoria con exito");
	}
}

