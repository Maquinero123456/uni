package es.uma.informatica.sii.ejb.practica.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;

@Entity
@IdClass(Lote.LoteId.class)
public class Lote {	
	public static class LoteId implements Serializable {
		private static final long serialVersionUID = 4198591467669702858L;
		
		private String codigo;
		private String producto;
		
		public LoteId() {}
		
		public LoteId(String codigo, String producto) {
			super();
			this.codigo = codigo;
			this.producto = producto;
		}
		
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		public String getProducto() {
			return producto;
		}
		public void setProducto(String producto) {
			this.producto = producto;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
			result = prime * result + ((producto == null) ? 0 : producto.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			LoteId other = (LoteId) obj;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			if (producto == null) {
				if (other.producto != null)
					return false;
			} else if (!producto.equals(other.producto))
				return false;
			return true;
		}
		
	}
	
	@Id
	private String codigo;
	
	@Id
	@ManyToOne
	private Producto producto;
	
	@Column(precision=10, scale=2)
	private BigDecimal cantidad;
	
	
	private Date fechaFabricacion;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyJoinColumn(name="ingrediente")
	@CollectionTable(name = "LoteIngredientes")
	@Column(name="lote")
	private Map<Ingrediente, String> loteIngredientes;
	
	

	public Lote() {
	}
	
	public Lote(String codigo, Producto producto, BigDecimal cantidad, Date fechaFabricacion) {
		super();
		this.codigo = codigo;
		this.producto = producto;
		this.cantidad = cantidad;
		this.fechaFabricacion = fechaFabricacion;
	}



	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Map<Ingrediente, String> getLoteIngredientes() {
		return loteIngredientes;
	}

	public void setLoteIngredientes(Map<Ingrediente, String> loteIngredientes) {
		this.loteIngredientes = loteIngredientes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		return result;
	}
	
	public Date getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}


	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lote other = (Lote) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lote [codigo=" + codigo + ", producto=" + producto.getNombre() + "]";
	}
	

}
