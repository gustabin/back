package ar.com.santanderrio.obp.servicios.solicitudes.view;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.solicitudes.entities.PersonasVap;

public class RequestVapView {
	
		@JsonProperty("IdSolicitud")
		private int idSolicitud = 0;
	
		@JsonProperty("Personas")
		private List<PersonasVap> personas;
		
		@JsonProperty("Producto")
		private String producto;
		
		@JsonProperty("Subproducto")
		private String subproducto;
		
		@JsonProperty("Canal")
		private String canal;
		
		@JsonProperty("Operador")
		private String operador;
		
		@JsonProperty("entidad")
		private String entidad;
		
		@JsonProperty("Nacionalidad")
		private String nacionalidad;
		
		@JsonProperty("CodigoPaisResidencia")
		private String codigoPaisResidencia;
		
		@JsonProperty("CodigoOcupacion")
		private String codigoOcupacion;

		

		public List<PersonasVap> getPersonas() {
			return personas;
		}

		public void setPersonas(List<PersonasVap> personas) {
			this.personas = personas;
		}

		public String getProducto() {
			return producto;
		}

		public void setProducto(String producto) {
			this.producto = producto;
		}

		public String getSubproducto() {
			return subproducto;
		}

		public void setSubproducto(String subproducto) {
			this.subproducto = subproducto;
		}

		public String getCanal() {
			return canal;
		}

		public void setCanal(String canal) {
			this.canal = canal;
		}

		public String getOperador() {
			return operador;
		}

		public void setOperador(String operador) {
			this.operador = operador;
		}

		public String getEntidad() {
			return entidad;
		}

		public void setEntidad(String entidad) {
			this.entidad = entidad;
		}

		public String getNacionalidad() {
			return nacionalidad;
		}

		public void setNacionalidad(String nacionalidad) {
			this.nacionalidad = nacionalidad;
		}

		public String getCodigoPaisResidencia() {
			return codigoPaisResidencia;
		}

		public void setCodigoPaisResidencia(String codigoPaisResidencia) {
			this.codigoPaisResidencia = codigoPaisResidencia;
		}

		public String getCodigoOcupacion() {
			return codigoOcupacion;
		}

		public void setCodigoOcupacion(String codigoOcupacion) {
			this.codigoOcupacion = codigoOcupacion;
		}			

		
}
