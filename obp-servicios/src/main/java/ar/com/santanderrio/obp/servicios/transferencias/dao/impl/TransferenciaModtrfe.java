/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.dao.impl;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Generar objetos que se van a subir a la cache y deben ser inmutables.
 * 
 * @author sergio.e.goldentair
 *
 */
public class TransferenciaModtrfe implements Serializable {
	private static final long serialVersionUID = -829163173863654996L;
	private String modalidadTransferencia;
	private String indicadorAdhesionBee;
	private String posicionRespuesta;

	/**
	 * 
	 */
	private TransferenciaModtrfe() {
		super();
	}

	/**
	 * @return the modalidadTransferencia
	 */
	public String getModalidadTransferencia() {
		return modalidadTransferencia;
	}

	/**
	 * @return the indicadorAdhesionBee
	 */
	public String getIndicadorAdhesionBee() {
		return indicadorAdhesionBee;
	}

	/**
	 * @return the posicionRespuesta
	 */
	public String getPosicionRespuesta() {
		return posicionRespuesta;
	}

	/**
	 * Builder para las consultas de marca de pago de haberes.
	 * 
	 * @author sergio.e.goldentair
	 *
	 */
	public static class TransferenciaModtrfeBuilder {
		private String modalidadTransferencia;
		private String indicadorAdhesionBee;
		private String posicionRespuesta;

		/**
		 * 
		 */
		public TransferenciaModtrfeBuilder() {
			super();
		}

		/**
		 * @param modalidadTransferencia
		 * @return
		 */
		public TransferenciaModtrfeBuilder addModalidadTransferencia(String modalidadTransferencia) {
			this.modalidadTransferencia = modalidadTransferencia;
			return this;
		}

		/**
		 * @param indicadorAdhesionBee
		 * @return
		 */
		public TransferenciaModtrfeBuilder addIndicadorAdhesionBee(String indicadorAdhesionBee) {
			this.indicadorAdhesionBee = indicadorAdhesionBee;
			return this;
		}

		/**
		 * @param posicionRespuesta
		 * @return
		 */
		public TransferenciaModtrfeBuilder addPosicionRespuesta(String posicionRespuesta) {
			this.posicionRespuesta = posicionRespuesta;
			return this;
		}

		/**
		 * @return
		 */
		public TransferenciaModtrfe build() {
			TransferenciaModtrfe TransferenciaModtrfe = new TransferenciaModtrfe();
			TransferenciaModtrfe.modalidadTransferencia = this.modalidadTransferencia;
			TransferenciaModtrfe.indicadorAdhesionBee = this.indicadorAdhesionBee;
			TransferenciaModtrfe.posicionRespuesta = this.posicionRespuesta;
			return TransferenciaModtrfe;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("modalidadTransferencia", modalidadTransferencia)
		        .append("indicadorAdhesionBee", indicadorAdhesionBee).append("posicionRespuesta", posicionRespuesta)
		        .toString();
	}
}
