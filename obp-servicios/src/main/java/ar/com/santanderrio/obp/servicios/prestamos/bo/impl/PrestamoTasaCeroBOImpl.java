/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.bo.impl;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoTasaCeroConfigEntity;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoTasaCeroBO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoTasaCeroDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmarPrestamoTasaCeroInDTO;



@Component
public class PrestamoTasaCeroBOImpl implements PrestamoTasaCeroBO {

	/** The destino prestamo open credit DAO. */
	@Autowired
	private PrestamoTasaCeroDAO prestamoTasaCeroDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The nup DAO. */
	@Autowired
	private NUPDAO nupDAO;
	
	@Autowired
	private LegalBO legalBO;
	
	private static final String PRESTAMO_TASA_CERO_NORMAL = "N";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoTasaCeroBOImpl.class);


	@Override
	public Respuesta<PrestamoTasaCeroConfigEntity> inicioPrestamosTasaCero(SesionCliente cliente) throws DAOException {

		PrestamoTasaCeroConfigEntity prestamosConfig = new PrestamoTasaCeroConfigEntity();
		try {
			boolean prestamoActivo = prestamoTasaCeroDAO.consultarPrestamosTasaCero(cliente.getCliente().getNup());
			if (prestamoActivo) {
				LOGGER.error("Prestamo Tasa Cero: cliente con prestamo activo");
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_INICIO_PRESTAMOS,
						CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
			}
			cargarCuit(cliente.getCliente());
			prestamosConfig = prestamoTasaCeroDAO.inicioPrestamosTasaCero(cliente);
			//if (PRESTAMO_TASA_CERO_NORMAL.equalsIgnoreCase(prestamosConfig.getTipo()) && validarFecha()) {
			if (validarFecha()) {
				return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_PRESTAMO_TASA_CERO_NORMAL_NO_DISPONIBLE), "PRESTAMO_NO_DISPONIBLE");
			}
			if (prestamosConfig.getHabilitadoPrestamo()) {
				return respuestaFactory.crearRespuestaOk(prestamosConfig);
			} else {
				LOGGER.error("Error en el DAO de inicioPrestamosTasaCero.");
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS,
						CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
			}
		} catch (DAOException e) {
			LOGGER.error("Error en el DAO de prestamoTasaCeroDAO.", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS,
					CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
		} catch (RuntimeException e) {
			LOGGER.error("Error generico en prestamoSaldosDAO.", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS,
					CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
		}
	}

	private boolean validarFecha() {
		/*
		LOGGER.info("Validamos que la fecha en que se quiere sacar el prestamo sea posterior al 1ero de Diciembre del 2020");
		DateTime fechaHoy = new DateTime();

		DateTime primeroDiciembre = new DateTime(2020, 12, 1, 00, 00, 0, 0);
		return fechaHoy.isAfter(primeroDiciembre);
		*/
		LOGGER.info("Validamos que la fecha en que se quiere sacar el prestamo no sea posterior al 20 de Enero del 2022");
		DateTime fechaHoy = new DateTime();
		DateTime fechaHabilitadoHasta = new DateTime(2022, 1, 21, 0, 0, 0, 0);
		return fechaHoy.isAfter(fechaHabilitadoHasta);
	}
	

	/**
	 * Cargar cuit.
	 *
	 * @param cliente the cliente
	 * @throws DAOException the DAO exception
	 */
	private void cargarCuit(Cliente cliente) throws DAOException {
		if (cliente.getNroDocCnsDocXNup() == null || cliente.getNroDocCnsDocXNup().isEmpty()) {
            NupDTO nupDTO = nupDAO.obtenerNUP(cliente);
			String nroDoc = nupDTO.getCuit(NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CDI);
			String tipoDoc = nupDTO.getTipoDocumento(NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CDI);
			if (nroDoc.isEmpty()) {
				return;
			}
			cliente.setNroDocCnsDocXNup(nroDoc);
			cliente.setTipoDocCnsDocXNup(tipoDoc);
        }
	}

	@Override
	public boolean solicitarPrestamosTasaCero(ConfirmarPrestamoTasaCeroInDTO dto, SesionCliente cliente)
			throws DAOException {
		return prestamoTasaCeroDAO.solicitarPrestamosTasaCero(dto, cliente);
	}

}
