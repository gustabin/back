/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo.ModificacionLimiteDebitoBO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.ModifLimiteDebitoArchivoDAO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.ModifLimiteDebitoDAO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dto.ConsultaDatosTarjetaDebitoDTO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dto.LimitesExtraccionDebitoDTO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.ConsultaDatosTarjetaDebitoEntity;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.LimiteClase;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.LimiteDebito;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.ModificarLimiteDebitoEntity;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteDescargaCambioLimiteView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteModificacionLimiteDebitoView;

/**
 * The Class ModificacionLimiteDebitoBOImpl.
 */
@Component
public class ModificacionLimiteDebitoBOImpl implements ModificacionLimiteDebitoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ModificacionLimiteDebitoBOImpl.class);

	/** The Constant ERROR_CNS_CLASE_TARJETA. */
	private static final String ERROR_CNS_CLASE_TARJETA = "ERROR al obtener la clase de la tarjeta";

	/** The Constant ERROR_CARGA_LIMITES_DEBITO. */
	private static final String ERROR_CARGA_LIMITES_DEBITO = "ERROR al obtener limites desde el archivo";

	/** The Constant ERROR_CARGA_LIMITES_DEBITO. */
	private static final String ERROR_MODIFICACION_LIMITE = "ERROR al modificar el límite de extracción";

	/** The Constant CLASE_BANELCO. */
	private static final String CLASE_BANELCO = "CLASE.BANELCO";

	/** The Constant LIMITE_EXTRACCION. */
	private static final String LIMITE_EXTRACCION = "LIMITE.EXTRACCION";

	/** The Constant LIMITE_COMPRA. */
	private static final String LIMITE_COMPRA = "LIMITE.COMPRA";

	/** The Constant LIMITE_CAMBIO. */
	private static final String LIMITE_CAMBIO = "LIMITE.CAMBIO";

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBo;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The modif limite debito DAO. */
	@Autowired
	private ModifLimiteDebitoDAO modifLimiteDebitoDAO;

	/** The modif limite debito archivo DAO. */
	@Autowired
	private ModifLimiteDebitoArchivoDAO modifLimiteDebitoArchivoDAO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The limites archivo. */
	private List<LimiteDebito> limitesArchivo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo.
	 * ModificacionLimiteDebitoBO#getClaseTarjetaDebito(java.lang.String,
	 * java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ConsultaDatosTarjetaDebitoDTO> getClaseTarjetaDebito(String sucursal, String numTarjeta,
			Cliente cliente) throws BusinessException {
		ConsultaDatosTarjetaDebitoDTO datosTarjetaDebitoDTO = null;
		try {
			LOGGER.debug("Llamando al DAO para la realizar la operacion getClaseTarjetaDebito");
			ConsultaDatosTarjetaDebitoEntity datosTarjetaDebitoEntity = modifLimiteDebitoDAO
					.getClaseTarjetaBanelco(sucursal, numTarjeta, cliente);
			if (!datosTarjetaDebitoEntity.getTieneError()) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_CONSULTA_CLASE_TARJETA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				datosTarjetaDebitoDTO = crearDTOConsultaClaseTarjetaResponse(datosTarjetaDebitoEntity);
			} else {
				return getRespuestaFactory().crearRespuestaError(ConsultaDatosTarjetaDebitoDTO.class, null);
			}
		} catch (Exception e) {
			LOGGER.error(ERROR_CNS_CLASE_TARJETA, e);
			return getRespuestaFactory().crearRespuestaError(ConsultaDatosTarjetaDebitoDTO.class, null);
		}

		return getRespuestaFactory().crearRespuestaOk(ConsultaDatosTarjetaDebitoDTO.class, datosTarjetaDebitoDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo.
	 * ModificacionLimiteDebitoBO#getLimitesArchivo(java.lang.String)
	 */
	@Override
	public Respuesta<LimitesExtraccionDebitoDTO> getLimitesArchivo(String clase) throws BusinessException {

		try {
			LOGGER.debug("Llamando al DAO para obtener los limites desde el archivo");

			LimitesExtraccionDebitoDTO limites = new LimitesExtraccionDebitoDTO();
			limitesArchivo = modifLimiteDebitoArchivoDAO.cargarLimitesDebito();

			int classIndex = getIndiceClaseTarjeta(clase, getLineaLimiteArchivo(CLASE_BANELCO));

			LimiteDebito limiteCambio = getLineaLimiteArchivo(LIMITE_CAMBIO);
			int valorLimiteCambio;
			LimiteDebito limiteExtraccion = getLineaLimiteArchivo(LIMITE_EXTRACCION);
			LimiteDebito limiteCompra = getLineaLimiteArchivo(LIMITE_COMPRA);

			if (limiteCambio != null && limiteExtraccion != null && limiteCompra != null) {
				valorLimiteCambio = Integer.parseInt((String) limiteCambio.getValores().get(0).getLimite());
				limites.setLimiteExtraccionActual(limiteExtraccion.getValores().get(classIndex).getLimite());
				limites.setLimiteCompraActual(limiteCompra.getValores().get(classIndex).getLimite());
				limites.setLimitesCompletosCompra(limiteCompra.getValores());
			} else {
				return creaRespuestaErroneaGetLimitesArchivo();
			}

			limites.setLimitesExtraccion(
					getLimitesExtraccion(getLineaLimiteArchivo(LIMITE_EXTRACCION), valorLimiteCambio));
			limites.setLimitesCompra(getLineaLimiteArchivo(LIMITE_COMPRA));

			Respuesta<LimitesExtraccionDebitoDTO> respuesta = new Respuesta<LimitesExtraccionDebitoDTO>();
			estadisticaManager.add(EstadisticasConstants.CODIGO_CARGA_LIMITES_DEBITO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuesta(limites);
			return respuesta;
		} catch (Exception e) {
			LOGGER.error(ERROR_CARGA_LIMITES_DEBITO, e);
			return creaRespuestaErroneaGetLimitesArchivo();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo.
	 * ModificacionLimiteDebitoBO#modificarLimitesExtraccion(ar.com.santanderrio
	 * .obp.servicios.modificacionlimitedebito.web.view.
	 * ComprobanteModificacionLimiteDebitoView, java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> modificarLimitesExtraccion(
			ComprobanteModificacionLimiteDebitoView comprobanteModificacionLimiteDebitoView, String numTarjetaBanelco,
			Cliente cliente) throws BusinessException {

		ModificarLimiteDebitoEntity modificarLimiteDebitoInEntity = new ModificarLimiteDebitoEntity();

		modificarLimiteDebitoInEntity.setCliente(cliente);
		modificarLimiteDebitoInEntity
				.setClase(StringUtils.leftPad(comprobanteModificacionLimiteDebitoView.getClaseTarjetaDebito(), 2, '0'));
		if (StringUtils.isNotEmpty(numTarjetaBanelco)) {
			modificarLimiteDebitoInEntity.setNumTarjetaBanelco(numTarjetaBanelco);
		} else {
			return respuestaFactory.crearRespuestaError(ResultadoTransaccion.class, ERROR_MODIFICACION_LIMITE,
					ERROR_MODIFICACION_LIMITE);
		}

		modificarLimiteDebitoInEntity
				.setMontoActual(StringUtils.leftPad(comprobanteModificacionLimiteDebitoView.getMontoActual(), 6, "0"));
		modificarLimiteDebitoInEntity.setMontoSeleccionado(
				StringUtils.leftPad(comprobanteModificacionLimiteDebitoView.getMontoSeleccionado(), 6, "0"));

		Respuesta<ResultadoTransaccion> modificarLimiteDebitoOutEntity;
		try {
			modificarLimiteDebitoOutEntity = modifLimiteDebitoDAO
					.modificarLimitesExtraccion(modificarLimiteDebitoInEntity);
			estadisticaManager.add(EstadisticasConstants.CODIGO_MODIFICACION_LIMITE_EXTRACCION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} catch (DAOException e) {
			LOGGER.error("DAOException - modificarLimitesExtraccion", e);
			return respuestaFactory.crearRespuestaError(ResultadoTransaccion.class, ERROR_MODIFICACION_LIMITE,
					ERROR_MODIFICACION_LIMITE);
		}

		return modificarLimiteDebitoOutEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo.
	 * ModificacionLimiteDebitoBO#modificarLimitesExtraccion(ar.com.santanderrio
	 * .obp.servicios.modificacionlimitedebito.web.view.
	 * ComprobanteModificacionLimiteDebitoView, java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, 
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> modificarLimitesExtraccion(
			ComprobanteModificacionLimiteDebitoView comprobanteModificacionLimiteDebitoView, String numTarjetaBanelco,
			Cliente cliente, String ip, String userAgent) throws BusinessException {

		ModificarLimiteDebitoEntity modificarLimiteDebitoInEntity = new ModificarLimiteDebitoEntity();

		modificarLimiteDebitoInEntity.setCliente(cliente);
		modificarLimiteDebitoInEntity.setClase(comprobanteModificacionLimiteDebitoView.getClaseTarjetaDebito());
		if (StringUtils.isNotEmpty(numTarjetaBanelco)) {
			modificarLimiteDebitoInEntity.setNumTarjetaBanelco(numTarjetaBanelco);
		} else {
			return respuestaFactory.crearRespuestaError(ResultadoTransaccion.class, ERROR_MODIFICACION_LIMITE,
					ERROR_MODIFICACION_LIMITE);
		}

		modificarLimiteDebitoInEntity
				.setMontoActual(StringUtils.leftPad(comprobanteModificacionLimiteDebitoView.getMontoActual(), 6, "0"));
		modificarLimiteDebitoInEntity.setMontoSeleccionado(
				StringUtils.leftPad(comprobanteModificacionLimiteDebitoView.getMontoSeleccionado(), 6, "0"));

		Respuesta<ResultadoTransaccion> modificarLimiteDebitoOutEntity;
		try {
			modificarLimiteDebitoOutEntity = modifLimiteDebitoDAO
					.modificarLimitesExtraccion(modificarLimiteDebitoInEntity, ip, userAgent);
			estadisticaManager.add(EstadisticasConstants.CODIGO_MODIFICACION_LIMITE_EXTRACCION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} catch (DAOException e) {
			LOGGER.error("DAOException - modificarLimitesExtraccion", e);
			return respuestaFactory.crearRespuestaError(ResultadoTransaccion.class, ERROR_MODIFICACION_LIMITE,
					ERROR_MODIFICACION_LIMITE);
		}

		return modificarLimiteDebitoOutEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo.
	 * ModificacionLimiteDebitoBO#comprobanteModifLimitesExtraccion(ar.com.
	 * santanderrio.obp.servicios.modificacionlimitedebito.web.view.
	 * ComprobanteDescargaCambioLimiteView)
	 */
	@Override
	public Respuesta<Reporte> comprobanteModifLimitesExtraccion(ComprobanteDescargaCambioLimiteView comprobanteView) {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = modifLimiteDebitoDAO.comprobanteModifLimitesExtraccion(comprobanteView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

	/**
	 * Gets the linea limite archivo.
	 *
	 * @param clave
	 *            the clave
	 * @return the linea limite archivo
	 */
	private LimiteDebito getLineaLimiteArchivo(String clave) {
		for (LimiteDebito linea : limitesArchivo) {
			if (linea.getClave().equalsIgnoreCase(clave)) {
				return linea;
			}
		}
		return null;
	}

	/**
	 * Crear DTO consulta clase tarjeta response.
	 *
	 * @param consultaDatosTarjetaDebitoEntity
	 *            the consulta datos tarjeta debito entity
	 * @return the consulta datos tarjeta debito DTO
	 */
	private ConsultaDatosTarjetaDebitoDTO crearDTOConsultaClaseTarjetaResponse(
			ConsultaDatosTarjetaDebitoEntity consultaDatosTarjetaDebitoEntity) {
		ConsultaDatosTarjetaDebitoDTO consultaDatosTarjetaDebitoDTO = new ConsultaDatosTarjetaDebitoDTO();
		consultaDatosTarjetaDebitoDTO.setClaseTarjetaDebito(consultaDatosTarjetaDebitoEntity.getClaseTarjetaDebito());
		return consultaDatosTarjetaDebitoDTO;
	}

	/**
	 * Crea respuesta erronea get limites archivo.
	 *
	 * @return the respuesta
	 */
	private Respuesta<LimitesExtraccionDebitoDTO> creaRespuestaErroneaGetLimitesArchivo() {
		return getRespuestaFactory().crearRespuestaWarning(null, "ERROR", "");
	}

	/**
	 * Gets the indice clase tarjeta.
	 *
	 * @param clase
	 *            the clase
	 * @param listaClases
	 *            the lista clases
	 * @return the indice clase tarjeta
	 */
	private int getIndiceClaseTarjeta(String clase, LimiteDebito listaClases) {
		int classIndex = 0;
		for (LimiteClase limite : listaClases.getValores()) {
			if (clase.equalsIgnoreCase(StringUtils.leftPad(limite.getLimite(), 2, '0'))) {
				return classIndex;
			}
			classIndex++;
		}
		return 0;
	}

	/**
	 * Gets the limites extraccion.
	 *
	 * @param limitesExtArchivo
	 *            the limites ext archivo
	 * @param valorLimiteCambio
	 *            the valor limite cambio
	 * @return the limites extraccion
	 */
	private LimiteDebito getLimitesExtraccion(LimiteDebito limitesExtArchivo, int valorLimiteCambio) {
		LimiteDebito lim = new LimiteDebito();
		lim.setClave(limitesExtArchivo.getClave());
		lim.setValor(new ArrayList<LimiteClase>());

		for (int i = 0; i < limitesExtArchivo.getValores().size(); i++) {
			String limite = limitesExtArchivo.getValores().get(i).getLimite();
			if (!"".equals(limite) && Integer.parseInt(limite) >= valorLimiteCambio) {
				lim.getValores().add(new LimiteClase(limite, limitesExtArchivo.getValores().get(i).getClase()));
			}
		}
		return lim;
	}

	/**
	 * Gets the cuenta bo.
	 *
	 * @return the cuenta bo
	 */
	public CuentaBO getCuentaBo() {
		return cuentaBo;
	}

	/**
	 * Gets the respuesta factory.
	 *
	 * @return the respuesta factory
	 */
	public RespuestaFactory getRespuestaFactory() {
		return respuestaFactory;
	}

	/**
	 * Gets the mensaje BO.
	 *
	 * @return the mensaje BO
	 */
	public MensajeBO getMensajeBO() {
		return mensajeBO;
	}

	/**
	 * Gets the limites archivo.
	 *
	 * @return the limites archivo
	 */
	public List<LimiteDebito> getLimitesArchivo() {
		return limitesArchivo;
	}

	/**
	 * Sets the limites archivo.
	 *
	 * @param limitesArchivo
	 *            the new limites archivo
	 */
	public void setLimitesArchivo(List<LimiteDebito> limitesArchivo) {
		this.limitesArchivo = limitesArchivo;
	}

}
