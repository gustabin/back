/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.banelco.bo.BanelcoBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class BanelcoDesafio.
 */
@XmlRootElement(name = "banelcoDTO", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BanelcoDesafioEntity extends Desafio<AutentificacionDTO> {

	/** The banelco BO. */
	@Autowired
	private BanelcoBO banelcoBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/**
	 * solicita los datos de autenticacion.
	 *
	 * @return Respuesta<AutentificacionDTO>
	 */
	@Override
	public Respuesta<AutentificacionDTO> solicitar() {
		return banelcoBO.obtenerUltimosDigitosValidacion();
	}

	/**
	 * ejecuta autientificacion.
	 *
	 * @param auntentificacionDTO
	 *            the auntentificacion DTO
	 * @return Respuesta<AutentificacionDTO>
	 */
	@Override
	public Respuesta<AutentificacionDTO> ejecutar(AutentificacionDTO auntentificacionDTO) {
		Respuesta<Boolean> rstaBanelco;
		try {
			rstaBanelco = banelcoBO.validarTarjetaDebito(auntentificacionDTO.getBanelcoOperacion().getIngresoDigitos());
			if (EstadoRespuesta.OK.equals(rstaBanelco.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaOk(AutentificacionDTO.class, auntentificacionDTO);
			}
			return respuestaFactory.crearRespuestaError(AutentificacionDTO.class,
					rstaBanelco.getItemsMensajeRespuesta());
		} catch (BusinessException e) {
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, "");
		}

	}

	/**
	 * logica de comparacion.
	 *
	 * @param o
	 *            the o
	 * @return the int
	 */
	@Override
	public int compareTo(Desafio<AutentificacionDTO> o) {
		return Comparators.PRIORIDAD.compare(this, o);
	}

}
