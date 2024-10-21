/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaSolicitadaDTO;

/**
 * The Class AltaDatosReimpresionTarjetas.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AltaDatosReimpresionTarjetas {

	/** The tarjetas solicitadas. */
	private List<TarjetaSolicitadaDTO> tarjetasSolicitadas;

	/** The motivos. */
	private List<MotivoView> motivos;

	/** The domicilios. */
	private List<DomicilioView> domicilios;

	/**
	 * Gets the tarjetas solicitadas.
	 *
	 * @return the tarjetas solicitadas
	 */
	public List<TarjetaSolicitadaDTO> getTarjetasSolicitadas() {
		return tarjetasSolicitadas;
	}

	/**
	 * Sets the tarjetas solicitadas.
	 *
	 * @param tarjetasSolicitadas
	 *            the new tarjetas solicitadas
	 */
	public void setTarjetasSolicitadas(List<TarjetaSolicitadaDTO> tarjetasSolicitadas) {
		this.tarjetasSolicitadas = tarjetasSolicitadas;
	}

	/**
	 * Gets the motivos.
	 *
	 * @return the motivos
	 */
	public List<MotivoView> getMotivos() {
		return motivos;
	}

	/**
	 * Sets the motivos.
	 *
	 * @param motivos
	 *            the new motivos
	 */
	public void setMotivos(List<MotivoView> motivos) {
		this.motivos = motivos;
	}

	/**
	 * Gets the domicilios.
	 *
	 * @return the domicilios
	 */
	public List<DomicilioView> getDomicilios() {
		return domicilios;
	}

	/**
	 * Sets the domicilios.
	 *
	 * @param domicilios
	 *            the new domicilios
	 */
	public void setDomicilios(List<DomicilioView> domicilios) {
		this.domicilios = domicilios;
	}

}
