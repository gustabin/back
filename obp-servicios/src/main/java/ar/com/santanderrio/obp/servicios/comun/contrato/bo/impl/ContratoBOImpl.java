/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contrato.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.dao.ContratoDAO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.ContratoInEntity;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.TipoContratoEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * Clase ContratosBOImpl.
 */
@Component
public class ContratoBOImpl implements ContratoBO {

	/** The Constant HA_OCURRIDO_UN_MONTON_AL_BUSCAR_EL_CONTRATO. */
	private static final String HA_OCURRIDO_UN_MONTON_AL_BUSCAR_EL_CONTRATO = "Ha ocurrido un monton al buscar el contrato.";

	/** The Constant BUSQUEDA_CONTRATO. */
	private static final String BUSQUEDA_CONTRATO = "BUSQUEDA_CONTRATO";

	/** The Constant ACEPTACION_CONTRATO. */
	private static final String ACEPTACION_CONTRATO = "BUSQUEDA_CONTRATO";

	/** The Constant HA_OCURRIDO_UN_ERROR_AL_ACEPTAR_EL_CONTRATO. */
	private static final String HA_OCURRIDO_UN_ERROR_AL_ACEPTAR_EL_CONTRATO = "Ha ocurrido un error al aceptar el contrato.";

	/** Variable contratoDAO. */
	@Autowired
	private ContratoDAO contratoDAO;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String buscarContratoAceptadoOld(String fechaNac, String dni, CampoEnum campoEnum, SinonimoEnum sinonimoEnum)
			throws DAOException {
		ContratoInEntity contratoInEntity = new ContratoInEntity();
		contratoInEntity.setDni(dni);
		contratoInEntity.setFechaNac(fechaNac);
		return contratoDAO.verContrato(contratoInEntity, campoEnum, sinonimoEnum);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String confirmarAceptacionContratoOld(String fechaNac, String dni, CampoEnum campoEnum,
			SinonimoEnum sinonimoEnum) throws DAOException {
		ContratoInEntity contratoInEntity = new ContratoInEntity();
		contratoInEntity.setDni(dni);
		contratoInEntity.setFechaNac(fechaNac);
		return contratoDAO.aceptarContrato(contratoInEntity, campoEnum, sinonimoEnum);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO#
	 * buscarContratoAceptado(java.lang.String, java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum,
	 * ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum)
	 */
	@Override
	public Respuesta<String> buscarContratoAceptado(String fechaNac, String dni, CampoEnum campoEnum,
			SinonimoEnum sinonimoEnum) {
		try {
			ContratoInEntity contratoInEntity = new ContratoInEntity();
			contratoInEntity.setDni(dni);
			contratoInEntity.setFechaNac(fechaNac);
			String respuesta = contratoDAO.verContrato(contratoInEntity, campoEnum, sinonimoEnum);
			return respuestaFactory.crearRespuestaOk(String.class, respuesta);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError(String.class, HA_OCURRIDO_UN_MONTON_AL_BUSCAR_EL_CONTRATO,
					BUSQUEDA_CONTRATO);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO#
	 * confirmarAceptacionContrato(java.lang.String, java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum,
	 * ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum)
	 */
	@Override
	public Respuesta<String> confirmarAceptacionContrato(String fechaNac, String dni, CampoEnum campoEnum,
			SinonimoEnum sinonimoEnum) {
		try {
			ContratoInEntity contratoInEntity = new ContratoInEntity();
			contratoInEntity.setDni(dni);
			contratoInEntity.setFechaNac(fechaNac);
			String respuesta = contratoDAO.aceptarContrato(contratoInEntity, campoEnum, sinonimoEnum);
			return respuestaFactory.crearRespuestaOk(String.class, respuesta);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError(String.class, HA_OCURRIDO_UN_ERROR_AL_ACEPTAR_EL_CONTRATO,
					ACEPTACION_CONTRATO);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO#
	 * tieneContrato(ar.com.santanderrio.obp.servicios.comun.contrato.entity.
	 * TipoContratoEnum,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Boolean tieneContrato(TipoContratoEnum tipoContrato, Cliente cliente) {
		Boolean tieneContrato = Boolean.FALSE;
		if (!cliente.getContratos().isEmpty() && cliente.getContratos().containsKey(tipoContrato)) {
			tieneContrato = cliente.getContratos().get(tipoContrato);
		}
		return tieneContrato;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO#
	 * agregarContrato(ar.com.santanderrio.obp.servicios.comun.contrato.entity.
	 * TipoContratoEnum,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public void agregarContrato(TipoContratoEnum tipoContrato, Cliente cliente) {
		cliente.getContratos().put(tipoContrato, Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO#
	 * eliminarContrato(ar.com.santanderrio.obp.servicios.comun.contrato.entity.
	 * TipoContratoEnum,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public void eliminarContrato(TipoContratoEnum tipoContrato, Cliente cliente) {
		cliente.getContratos().remove(tipoContrato);
	}

}
