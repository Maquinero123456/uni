package es.uma.informatica.sii.ejb.practica.ejb;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.practica.ejb.exceptions.ProductoNoEncontradoException;
import es.uma.informatica.sii.ejb.practica.entidades.Producto;

@Local
public interface GestionProductos {
	
	/**
	 * Devuelve un producto con sus ingredientes (pero no sus lotes).
	 * @param producto Nombre del producto a obtener
	 * @return Producto con ingredientes extraídos de la base de datos.
	 */
	public Producto obtenerProductoEIngredientes(String producto) throws ProductoNoEncontradoException;

}
