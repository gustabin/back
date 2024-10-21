package ar.com.santanderrio.obp.servicios.prestamos.bo.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesBOEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoPreaprobadoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoPreaprobadoMonoproductoBDDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoPreaprobadoMonoproductoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.ReportePrestamosPreaprobadoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoPreaprobadoMonoproductoInOutView;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("prestamoPreaprobadoBO")
public class PrestamoPreaprobadoBOImpl implements PrestamoPreaprobadoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoPreaprobadoBOImpl.class);
	
  /** The Constant PRESTAMO_YA_ENCOLADO. */
  private static final String PRESTAMO_YA_ENCOLADO = "10000022";
  
  /** Constant CUENTA BLOQUEO 33 */
   
  private static final String PRESTAMO_CUENTA_BLOQUEO33 = "10002065";
  
  
  private static final String LIQUIDAR_ENCOLADO = "3";

	@Autowired
	private RespuestaFactory respuestaFactory;

	@Autowired
	private PrestamoPreaprobadoMonoproductoBDDAO prestamoPreaprobadoMonoproductoBDDAO;

	@Autowired
	private PrestamoPreaprobadoMonoproductoDAO prestamoPreaprobadoMonoproductoDAO;

	@Autowired
	private EstadisticaManager estadisticaManager;

	@Autowired
	private ReportePrestamosPreaprobadoDAO reportePrestamoPreaprobadoDAO;

	/**
	 * Obtiene el valor maximo de oferta para prestamos preaprobados En caso de
	 * error devuelve 0
	 */
	@Override
	public Respuesta<PrestamoPermitidoEntity> getMaxImporteOfertaPrestamoPreaprobado(Cliente cliente) {

		try {
			Respuesta<PrestamoPermitidoEntity> respuesta = respuestaFactory
					.crearRespuestaOk(prestamoPreaprobadoMonoproductoBDDAO.consultarValorMaximoOferta(cliente));
			if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_OFERTA_MAX,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}

			return respuesta;

		} catch (DAOException e) {
			LOGGER.error("Error generico en getMaxImporteOfertaPrestamoPreaprobado.", e);
			estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_OFERTA_MAX,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_BUSQUEDA_PRESTAMO_PREAPROBADO, "");
		}
	}

	/**
	 * Busca todas las ofertas de prestamos preaprobados por nup
	 */
	@Override
	public Respuesta<List<PrestamoPermitidoEntity>> consultarPrestamoPreaprobadoMonoproducto(Cliente cliente) {

		try {
			return respuestaFactory.crearRespuestaOk(
					prestamoPreaprobadoMonoproductoBDDAO.consultarPrestamoPreaprobadoMonoproducto(cliente));

		} catch (DAOException e) {
			LOGGER.error("Error generico en consultarPrestamoPreaprobadoMonoproducto.", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_BUSQUEDA_PRESTAMO_PREAPROBADO, "");
		}
	}

	/**
	 * Realizar alta de prestamo preaprobado
	 */
	@Override
	public Respuesta<PrestamoPreaprobadoMonoproductoOutEntity> altaSimulacionPrestamoPreabrobadoMonoproducto(
			PrestamoPreaprobadoMonoproductoInEntity prestamoPreaprobadoInOutEntity, Cliente cliente) {

		try {
			return respuestaFactory.crearRespuestaOk(prestamoPreaprobadoMonoproductoDAO
					.altaPrestamoPreabrobadoMonoproducto(prestamoPreaprobadoInOutEntity, cliente));
		} catch (DAOException e) {
			if (e.getErrorCode().equals(PRESTAMO_YA_ENCOLADO)) {
				return respuestaFactory.crearRespuestaWarning(null, TipoError.ERROR_PRESTAMO_ENCOLADO_PREAPROBADO,
						StringUtils.EMPTY);				
			} 
			else if (e.getErrorCode().equals(PRESTAMO_CUENTA_BLOQUEO33)) {
				return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(null, "Error en Bloqueo 33", "CODIGO_ERROR_CUENTA_BLOQUEO33");
			}
			
			else if (LIQUIDAR_ENCOLADO.equals(prestamoPreaprobadoInOutEntity.getFase())) {
 // 	        	LOGGER.info("Error en la Liquidación de prestamo encolado en ALTFOPMOPE");
//				return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(null, mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_ENCOLADO).getMensaje(), "CODIGO_ERROR_LIQUIDAR_ENCOLADO");
 				return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(null, "Lo sentimos. No podemos procesar tu solicitud en este momento", "CODIGO_ERROR_LIQUIDAR_ENCOLADO");
			}
			else {
				LOGGER.error("Error generico en altaPrestamoPreabrobadoMonoproducto.", e);
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_ALTA_PRESTAMO_PREAPROBADO,
						StringUtils.EMPTY);
			}
		
		}
	}

	@Override
	public Respuesta<Reporte> descargarPrestamoPreaprobadoMonoproducto(PrestamoPreaprobadoMonoproductoInOutView datos) {
		try {
			Reporte reporte = reportePrestamoPreaprobadoDAO.generarComprobantePrestamoPreaprobado(datos);
			return respuestaFactory.crearRespuestaOk(reporte);
		} catch (DAOException e) {
			LOGGER.error(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle(), e);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle());
			item.setTipoError(TipoError.ERROR_DESCARGA_COMPROBANTE.getDescripcion());
			return respuestaFactory.crearRespuestaError(Reporte.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		}
	}

}
