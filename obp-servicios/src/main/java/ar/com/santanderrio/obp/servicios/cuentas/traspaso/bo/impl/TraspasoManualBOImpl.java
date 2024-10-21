/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaTraspasoManualInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaTraspasoManualOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo.TraspasoManualBO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.TraspasoManualDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ComprobanteTraspasoManualDTO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.TraspasoManualDTO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.exceptions.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class TraspasoManualBOImpl.
 */
@Component
public class TraspasoManualBOImpl implements TraspasoManualBO {

	/** The traspaso manual DAO. */
	@Autowired
	TraspasoManualDAO traspasoManualDAO;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;
	/** The Constant QUINCE. */
	private static final int COMPROBANTE_INICIO = 14;
	/** The Constant VEINTIDOS. */
	private static final int COMPROBANTE_FIN = 21;

	/** The Constant ARS. */
	private static final String ARS = "ARS";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo.TraspasoManualBO#
	 * realizarTraspasoManual(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.
	 * TraspasoManualDTO)
	 */
	@Override
	public Respuesta<ComprobanteTraspasoManualDTO> realizarTraspasoManual(Cliente cliente,
			TraspasoManualDTO traspasoManualDTO) {

		Respuesta<ComprobanteTraspasoManualDTO> respuesta = respuestaFactory
				.crearRespuestaOk(ComprobanteTraspasoManualDTO.class);
		ConsultaTraspasoManualInEntity consultaTraspasoManualInEntity = getConsultaTraspasoManualInEntity(cliente,
				traspasoManualDTO);

		ComprobanteTraspasoManualDTO comprobanteDTO = new ComprobanteTraspasoManualDTO();
		try {
			ConsultaTraspasoManualOutEntity consultaTraspasoManualOutEntity = traspasoManualDAO
					.ejecutarTraspasoManual(consultaTraspasoManualInEntity);
			Date fechaComprobante = new Date();
			DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fecha = formatoFecha.format(fechaComprobante);
			DateFormat formatoHora = new SimpleDateFormat("HH:mm");
			String hora = formatoHora.format(fechaComprobante);

			if (consultaTraspasoManualOutEntity.getNio() != null) {
				comprobanteDTO.setComprobante(
						consultaTraspasoManualOutEntity.getNio().substring(COMPROBANTE_INICIO, COMPROBANTE_FIN));
				comprobanteDTO.setFecha(fecha);
				comprobanteDTO.setHora(hora);
				respuesta.setRespuesta(comprobanteDTO);
			} else {
				respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_TRASPASO_MANUAL,
						CodigoMensajeConstantes.TRASPASO_MANUAL_FEEDBACK_ERROR);
			}
		} catch (SaldoInsuficienteException e) {
			e.printStackTrace();
			respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.TRASPASO_SALDO_INSUFICIENTE,
					StringUtils.EMPTY);
		} catch (DAOException e) {
			e.printStackTrace();
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_TRASPASO_MANUAL,
					CodigoMensajeConstantes.TRASPASO_MANUAL_FEEDBACK_ERROR);
		}
		return respuesta;

	}

	/**
	 * Gets the consulta traspaso manual in entity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param traspasoManualDTO
	 *            the traspaso manual DTO
	 * @return the consulta traspaso manual in entity
	 */
	private ConsultaTraspasoManualInEntity getConsultaTraspasoManualInEntity(Cliente cliente,
			TraspasoManualDTO traspasoManualDTO) {
		ConsultaTraspasoManualInEntity inEntity = new ConsultaTraspasoManualInEntity();

		inEntity.setSucursalCuenta(traspasoManualDTO.getSucursalCuenta());
		inEntity.setNroCuenta(traspasoManualDTO.getNumeroCuenta());
		inEntity.setDivisa(ARS);
		inEntity.setImporteDebitoCredito(ISBANStringUtils.ajustadorBigDecimalIatx(traspasoManualDTO.getImporte(), 14));
		inEntity.setIndDebitoCreditoCA(traspasoManualDTO.getIndicadorCA().getIndicador() == null ? StringUtils.EMPTY
				: traspasoManualDTO.getIndicadorCA().getIndicador());
		inEntity.setIndDebitoCreditoCC(traspasoManualDTO.getIndicadorCC().getIndicador() == null ? StringUtils.EMPTY
				: traspasoManualDTO.getIndicadorCC().getIndicador());
		inEntity.setCliente(cliente);
		inEntity.setCentroOrigen(
				traspasoManualDTO.getCentroOrigen() == null ? StringUtils.EMPTY : traspasoManualDTO.getCentroOrigen());
		inEntity.setNio(traspasoManualDTO.getNio() == null ? StringUtils.EMPTY : traspasoManualDTO.getNio());
		return inEntity;
	}

}
