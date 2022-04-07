package es.uma.informatica.sii.ejb.practica.ejb;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.practica.ejb.exceptions.IngredientesIncorrectosException;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.LoteExistenteException;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.LoteNoEncontradoException;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.ProductoNoEncontradoException;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.TrazabilidadException;
import es.uma.informatica.sii.ejb.practica.entidades.Lote;

@Local
public interface GestionLotes {
	
	/**
	 * Este método debe insertar un lote en la base de datos. 
	 * Debe asegurarse de que hay una entrada para cada ingrediente 
	 * de un producto en el mapa de ingredientes del lote.
	 * Si no es así debe lanzar la excepción pertinente.
	 * Si el lote que se intenta introducir ya existe en la base de datos hay que lanzar la excepción adecuada.
	 * @param producto Nombre del producto al que añadir este lote. 
	 * Si no se encuentra, se lanza una excepción ProductoNoEncontradoException 
	 * @param lote Lote que se quiere insertar. 
	 * El código y el mapa de lotes de los ingredientes no pueden ser null.
	 */
	public void insertarLote(String producto, Lote lote) throws TrazabilidadException;
	
	/**
	 * Obtiene la lista de lotes asociados a un producto que se indica como argumento.
	 * @param nombre Nombre del producto cuyos lotes se quieren buscar. Si el producto
	 * no se encuentra se lanza la excepción ProductoNoEncontradoException
	 * @return
	 */
	public List<Lote> obtenerLotesDeProducto(String nombre) throws TrazabilidadException;
	
	/**
	 * Actualiza el lote en la base de datos.
	 * Solo se pueden actualizar los campos cantidad, fechaFabricacion y 
	 * el mapa de lotes de ingredientes 
	 * Si el lote no existe en la base de datos lanza una excepción
	 * LoteNoEncontradoException. El método debe asegurarse de que el mapa
	 * contiene todos los ingredientes del producto.
	 * Si no es así debe lanzar la excepción pertinente.
	 * @param producto Nombre del producto al que añadir este lote. 
	 * Si no se encuentra, se lanza una excepción ProductoNoEncontradoException 
	 * @param lote Lote que se quiere actualizar. 
	 * El código y el mapa de lotes de los ingredientes no pueden ser null.
	 */
	public void actualizarLote (String producto, Lote lote) throws TrazabilidadException;
	
	/**
	 * Elimina un lote de la base dedatos.
	 * @param producto Nombre del producto. Si no exsite se lanza la excepción pertinente.
	 * @param lote Lote a eliminar. Si no existe se lanza la excepción pertinente.
	 */
	public void eliminarLote(String producto, Lote lote) throws TrazabilidadException;
	
	/**
	 * Elimina todos los lotes asociados a un producto.
	 * @param producto Nombre del producto cuyos lotes se quieren eliminar. 
	 * Si el producto no existe se lanza la excepción pertinente.
	 */
	public void eliminarTodosLotes(String producto) throws TrazabilidadException;

}
