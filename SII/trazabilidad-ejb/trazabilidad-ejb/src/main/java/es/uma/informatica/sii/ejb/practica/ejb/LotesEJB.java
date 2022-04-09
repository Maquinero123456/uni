package es.uma.informatica.sii.ejb.practica.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.ejb.practica.ejb.exceptions.IngredientesIncorrectosException;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.LoteExistenteException;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.LoteNoEncontradoException;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.ProductoNoEncontradoException;
import es.uma.informatica.sii.ejb.practica.entidades.Lote;
import es.uma.informatica.sii.ejb.practica.entidades.Producto;
import es.uma.informatica.sii.ejb.practica.entidades.Lote.LoteId;

/**
 * Session Bean implementation class Sample
 */
@Stateless
public class LotesEJB implements GestionLotes {
	
	private static final Logger LOG = Logger.getLogger(LotesEJB.class.getCanonicalName());
	
	@PersistenceContext(name="Trazabilidad")
	private EntityManager em;

	@Override
	public void insertarLote(String producto, Lote lote) throws ProductoNoEncontradoException, LoteExistenteException, IngredientesIncorrectosException {
		Producto productoEntity = em.find(Producto.class, producto);
		if (productoEntity == null) {
			throw new ProductoNoEncontradoException();
		}
		
		Lote loteExistente = em.find(Lote.class, new Lote.LoteId(lote.getCodigo(), producto));
		if (loteExistente != null) {
			throw new LoteExistenteException();
		}
		
		if (!productoEntity.getIngredientes().equals(lote.getLoteIngredientes().keySet())) {
			throw new IngredientesIncorrectosException();
		}
		
		lote.setProducto(productoEntity);
		em.persist(lote);
	}

	@Override
	public List<Lote> obtenerLotesDeProducto(String nombre) throws ProductoNoEncontradoException {
		Producto producto = em.find(Producto.class, nombre);
		if (producto == null) {
			throw new ProductoNoEncontradoException();
		}
		producto.getLotes().size();
		return new ArrayList<>(producto.getLotes());
	}

	@Override
	public void actualizarLote(String producto, Lote lote) throws ProductoNoEncontradoException, LoteNoEncontradoException, IngredientesIncorrectosException {
		Producto productoEntity = em.find(Producto.class, producto);
		if (productoEntity == null) {
			throw new ProductoNoEncontradoException();
		}

		Lote loteEntity = em.find(Lote.class, new Lote.LoteId(lote.getCodigo(), producto));
		if (loteEntity == null){
			throw new LoteNoEncontradoException();
		}

		if (!productoEntity.getIngredientes().equals(lote.getLoteIngredientes().keySet())) {
			throw new IngredientesIncorrectosException();
		}

		loteEntity.setFechaFabricacion(lote.getFechaFabricacion());
		loteEntity.setCantidad(lote.getCantidad());
		loteEntity.setLoteIngredientes(lote.getLoteIngredientes());
	}

	@Override
	public void eliminarLote(String producto, Lote lote)
			throws ProductoNoEncontradoException, LoteNoEncontradoException {
		Producto productoEntity = em.find(Producto.class, producto);
		if (productoEntity == null) {
			throw new ProductoNoEncontradoException();
		}

		Lote loteEntity = em.find(Lote.class, new Lote.LoteId(lote.getCodigo(), producto));
		if (loteEntity == null){
			throw new LoteNoEncontradoException();
		}
		em.remove(loteEntity);
	}

	@Override
	public void eliminarTodosLotes(String producto) throws ProductoNoEncontradoException {
		Producto productoEntity = em.find(Producto.class, producto);
		if (productoEntity == null) {
			throw new ProductoNoEncontradoException();
		}
		productoEntity.getLotes().clear();
	}

    

}
