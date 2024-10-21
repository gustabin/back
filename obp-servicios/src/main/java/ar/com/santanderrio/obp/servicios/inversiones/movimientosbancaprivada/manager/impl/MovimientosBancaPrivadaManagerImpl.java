/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.manager.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.SessionMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ItemMovimiento;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.general.entities.RangoFechaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaMovimientosCuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo.MovimientosBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.manager.MovimientosBancaPrivadaManager;

/**
 * The Class MovimientosBancaPrivadaManagerImpl.
 */
@Component
public class MovimientosBancaPrivadaManagerImpl implements MovimientosBancaPrivadaManager {

	private static final String PRODUCTO_ALTAIR_CUENTA_TRANSACCIONAL = "07";

    /** The movimientos banca privada BO. */
	@Autowired
	MovimientosBancaPrivadaBO movimientosBancaPrivadaBO;

	/** The sesion cliente. */
	@Autowired
	SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	SesionParametros sesionParametros;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/** The sesion movimientos. */
	@Autowired
	SessionMovimientos sesionMovimientos;

	/** The mensaje BO. */
	@Autowired
	MensajeBO mensajeBO;

	/** The estadistica manager. */
	@Autowired
	EstadisticaManager estadisticaManager;
	
	/** The reporte BO. */
    @Autowired
    private ReporteComprobantePDFBO reporteBO;

	/** The Constant AYER. */
	private static final int AYER = 1;

	/** The Constant NUM_TRES_DIAS. */
	private static final int NUM_TRES_DIAS = 3;

	/** The Constant NUM_SIETE_DIAS. */
	private static final int NUM_SIETE_DIAS = 7;

	/** The Constant NUM_OCHO_DIAS. */
	private static final int NUM_OCHO_DIAS = 8;
	
	/** The Constant NUM_QUINCE_DIAS. */
	private static final int NUM_QUINCE_DIAS = 15;

	/** The Constant NUM_TREINTA_DIAS. */
	private static final int NUM_TREINTA_DIAS = 30;

	/** The Constant NUM_SESENTA_DIAS. */
	private static final int NUM_SESENTA_DIAS = 60;
	
	/** The Constant NUM_CINCUENTA_Y_TRES_DIAS. */
	private static final int NUM_CINCUENTA_Y_TRES_DIAS = 53;

	/** The Constant INDICADOR_CAJA_AHORRO. */
	private static final String INDICADOR_CAJA_AHORRO = "01";

	/** The Constant CODIGO_MENSAJE_ERROR_NO_HAY_MOVIMIENTOS. */
	private static final String CODIGO_MENSAJE_ERROR_NO_HAY_MOVIMIENTOS = "10506";

	/** The Constant CODIGO_MENSAJE_ERROR_CONSULTA. */
	private static final String CODIGO_MENSAJE_ERROR_CONSULTA = "10507";

	/**
	 * The Constant
	 * CODIGO_MENSAJE_ERROR_NO_HAY_MOVIMIENTOS_BUSQUEDA_PERSONALIZADA.
	 */
	private static final String CODIGO_MENSAJE_ERROR_NO_HAY_MOVIMIENTOS_BUSQUEDA_PERSONALIZADA = "10508";

	/** The Constant CODIGO_MENSAJE_ERROR_CONSULTA_BUSQUEDA_PERSONALIZADA. */
	private static final String CODIGO_MENSAJE_ERROR_CONSULTA_BUSQUEDA_PERSONALIZADA = "10509";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MovimientosBancaPrivadaManagerImpl.class);

	/** The Constant MENSAJE_ERROR_EXCEL. */
	private static final String MENSAJE_ERROR_EXCEL = "Ocurrió un error y no se pudo realizar la descarga. Por favor volvé a intentar";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.manager.MovimientosBancaPrivadaManager#obtenerMovimientosPrimeraVez(ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView)
	 */
	@Override
	public Respuesta<MovimientoView> obtenerMovimientosPrimeraVez(
			ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {

		ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
		consultaUltimosMovimientos.setNumeroConsulta(0);
		sesionMovimientos.setFiltro(consultaUltimosMovimientos);
		Respuesta<MovimientoView> respuesta = obtenerMovimientos(consultaUltimosMovimientosView);
		Boolean isPesos = DivisaEnum.PESO.equals(consultaUltimosMovimientosView.getMoneda());
		Boolean isBusquedaAvanzada = !isBusquedaDefault(consultaUltimosMovimientosView);

		if (!sesionParametros.getBusquedaDefaultCincuentaYTresDiasBP()) {
			estadisticaManager.add(obtenerCodigoEstadistica(isPesos, isBusquedaAvanzada),
					EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())
							|| EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())
									? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
									: EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		
		return respuesta;
	}

	/**
	 * Obtener codigo estadistica.
	 *
	 * @param isPesos
	 *            the is pesos
	 * @param isBusquedaAvanzada
	 *            the is busqueda avanzada
	 * @return the string
	 */
	private String obtenerCodigoEstadistica(Boolean isPesos, Boolean isBusquedaAvanzada) {
		if (isPesos) {
			return isBusquedaAvanzada
					? EstadisticasConstants.CODIGO_BANCA_PRIVADA_VER_MOVIMIENTOS_PESOS_BUSQUEDA_AVANZADA
					: EstadisticasConstants.CODIGO_BANCA_PRIVADA_VER_MOVIMIENTOS_PESOS;
		} else {
			return isBusquedaAvanzada
					? EstadisticasConstants.CODIGO_BANCA_PRIVADA_VER_MOVIMIENTOS_DOLARES_BUSQUEDA_AVANZADA
					: EstadisticasConstants.CODIGO_BANCA_PRIVADA_VER_MOVIMIENTOS_DOLARES;
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.manager.MovimientosBancaPrivadaManager#obtenerMasMovimientos()
	 */
	@Override
	public Respuesta<MovimientoView> obtenerMasMovimientos() {

		ConsultaUltimosMovimientosView consultaUltimosMovimientosView = obtenerValoresDeSesion();

		return obtenerMovimientos(consultaUltimosMovimientosView);
	}

	/**
	 * Obtener movimientos.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the respuesta
	 */
	private Respuesta<MovimientoView> obtenerMovimientos(
			ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {

		Respuesta<MovimientoView> respuesta = new Respuesta<MovimientoView>();

		try {

			Boolean fechaDistinta = revisarSiFechaEsDistinta(consultaUltimosMovimientosView);

			if (sesionMovimientos.getFiltro() != null && sesionMovimientos.getFiltro().getNumeroConsulta() == 0
					|| fechaDistinta) {
				movimientosBancaPrivadaBO.limpiarCache(sesionCliente.getCliente());
			}

	        setearRangoFecha(consultaUltimosMovimientosView);
		
			sesionMovimientos.setFiltro(grabarFiltro(consultaUltimosMovimientosView));
			
			Cuenta cuenta = movimientosBancaPrivadaBO.obtenerCuentaCliente(sesionCliente.getCliente(), consultaUltimosMovimientosView);
	        if (!PRODUCTO_ALTAIR_CUENTA_TRANSACCIONAL.equals(cuenta.getProductoAltair())) {
	            return manejoRespuestaAtesoramiento(consultaUltimosMovimientosView);
	        }
			MovimientosCuentaBPOutEntity movimientosCuentaBPOutEntity = movimientosBancaPrivadaBO
					.obtenerMovimientos(consultaUltimosMovimientosView, sesionCliente.getCliente(), cuenta);
			if (movimientosCuentaBPOutEntity.getErrorEnConsulta()) {
				return manejoDeRespuestaError(consultaUltimosMovimientosView);
			}
			if ((StringUtils.isNotEmpty(consultaUltimosMovimientosView.getImporteDesde())
					|| StringUtils.isNotEmpty(consultaUltimosMovimientosView.getImporteHasta()))
					|| StringUtils.isNotEmpty(consultaUltimosMovimientosView.getPalabraClave())) {
				movimientosCuentaBPOutEntity = movimientosBancaPrivadaBO.aplicarFiltros(movimientosCuentaBPOutEntity,
						consultaUltimosMovimientosView);
			}

			MovimientoView movimientoView = crearListaMovimientos(movimientosCuentaBPOutEntity,
					consultaUltimosMovimientosView);

			movimientoView = armarPaginado(movimientoView);

			respuesta = manejoDeRespuestaOk(movimientoView, consultaUltimosMovimientosView);

		} catch (BusinessException e) {
			respuesta = manejoDeRespuestaError(consultaUltimosMovimientosView);
		}
		return respuesta;

	}

    private Respuesta<MovimientoView> manejoRespuestaAtesoramiento(ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
        Respuesta<MovimientoView> respuesta;
        if (isBusquedaDefault(consultaUltimosMovimientosView)) {
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS,
                    CodigoMensajeConstantes.ERROR_SIN_MOVIMIENTOS_ATESORAMIENTO);
        } else {
            respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS_BUSQUEDA,
                    CodigoMensajeConstantes.ERROR_SIN_MOVIMIENTOS_ATESORAMIENTO_FILTRO);
        }
        return respuesta;
    }

	/**
	 * Sets the ear rango fecha.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the new ear rango fecha
	 */
	private void setearRangoFecha(ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
		
		if (RangoFechaEnum.DEFAULT.equals(consultaUltimosMovimientosView.getRango())) {
        	sesionParametros.setBusquedaDefaultCincuentaYTresDiasBP(Boolean.FALSE);
			consultaUltimosMovimientosView.setRango(RangoFechaEnum.DEFAULT);
		} else if (sesionParametros.getBusquedaDefaultCincuentaYTresDiasBP() && 
          	RangoFechaEnum.CINCUENTA_Y_TRES_DIAS.equals(consultaUltimosMovimientosView.getRango())) {
           	consultaUltimosMovimientosView.setRango(RangoFechaEnum.SESENTA_DIAS);
		} else if (!sesionParametros.getBusquedaDefaultCincuentaYTresDiasBP() && 	
            RangoFechaEnum.CINCUENTA_Y_TRES_DIAS.equals(consultaUltimosMovimientosView.getRango())) {
           	sesionParametros.setBusquedaDefaultCincuentaYTresDiasBP(Boolean.TRUE);
           	consultaUltimosMovimientosView.setRango(RangoFechaEnum.CINCUENTA_Y_TRES_DIAS);
        } else {
        	sesionParametros.setBusquedaDefaultCincuentaYTresDiasBP(Boolean.FALSE);
        	consultaUltimosMovimientosView.setRango(consultaUltimosMovimientosView.getRango());
	    }
		
	}
	
	/**
	 * Crear lista movimientos.
	 *
	 * @param movimientosCuentaBPOutEntity
	 *            the movimientos cuenta BP out entity
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the movimiento view
	 */
	private MovimientoView crearListaMovimientos(MovimientosCuentaBPOutEntity movimientosCuentaBPOutEntity,
			ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {

		List<ItemMovimiento> listaCuentaUnicaPesos = new ArrayList<ItemMovimiento>();
		List<ItemMovimiento> listaCajaAhorroPesos = new ArrayList<ItemMovimiento>();
		List<ItemMovimiento> listaCuentaCorrientePesos = new ArrayList<ItemMovimiento>();
		List<ItemMovimiento> listaDolares = new ArrayList<ItemMovimiento>();

		for (RtaMovimientosCuentaBP movimiento : movimientosCuentaBPOutEntity.getRespuesta()) {
			ItemMovimiento itemMovimiento = new ItemMovimiento();
			itemMovimiento.setFecha(setearFecha(movimiento.getFechaOperacion(), sesionParametros.getRegistroSession().isMobile()
					? ISBANStringUtils.FORMATO_FECHA_ANIO_CORTO : ISBANStringUtils.FORMATO_FECHA));
			itemMovimiento.setFechaDetalle(setearFecha(movimiento.getFechaOperacion(),
					sesionParametros.getRegistroSession().isMobile() ? ISBANStringUtils.FORMATO_FECHA_ANIO_HORA_CORTO
							: ISBANStringUtils.FORMATO_FECHA_HORA_COMPROBANTE));
			itemMovimiento.setDetalle(movimiento.getConcepto());
			itemMovimiento.setObservacion(movimiento.getTextoDelApunte());
			itemMovimiento.setDivisa("$");
			if (BigDecimal.ZERO.toString().equals(movimiento.getEgresos())) {
				itemMovimiento.setIsDebito(false);
				itemMovimiento.setImporte(ISBANStringUtils.formatearConComaYDosDecimales2(movimiento.getIngresos()));
			} else {
				itemMovimiento.setIsDebito(true);
				itemMovimiento.setImporte(
						ISBANStringUtils.formatearConComaYDosDecimales2(movimiento.getEgresos()));
			}
			itemMovimiento.setSaldo(ISBANStringUtils.formatearConComaYDosDecimales(movimiento.getSaldoFinal()));
			itemMovimiento.setNumeroSucursal(movimiento.getSucursal());
			itemMovimiento.setNumeroReferencia(movimiento.getNumeroMov());

			if (INDICADOR_CAJA_AHORRO.equals(movimiento.getIndMov())) {
				itemMovimiento.setIsCajaDeAhoroEnPesos(true);
				itemMovimiento.setIsCuentaCorrienteEnPesos(false);
				listaCajaAhorroPesos.add(itemMovimiento);
			} else {
				itemMovimiento.setIsCajaDeAhoroEnPesos(false);
				itemMovimiento.setIsCuentaCorrienteEnPesos(true);
				listaCuentaCorrientePesos.add(itemMovimiento);
			}

			if (DivisaEnum.PESO.getCodigo().equals(movimiento.getDivisa())) {
				listaCuentaUnicaPesos.add(itemMovimiento);
			} else {
				itemMovimiento.setDivisa("u$s");
				listaDolares.add(itemMovimiento);
			}

		}

		return cargarListaMovimientosSeleccionada(consultaUltimosMovimientosView, listaCuentaUnicaPesos,
				listaCajaAhorroPesos, listaCuentaCorrientePesos, listaDolares);

	}

	/**
	 * Cargar lista movimientos seleccionada.
	 *
	 * @param consulta
	 *            the consulta
	 * @param listaCuentaUnica
	 *            the lista cuenta unica
	 * @param listaCajaAhorroPesos
	 *            the lista caja ahorro pesos
	 * @param listaCuentaCorrientePesos
	 *            the lista cuenta corriente pesos
	 * @param listaDolares
	 *            the lista dolares
	 * @return the movimiento view
	 */
	private MovimientoView cargarListaMovimientosSeleccionada(ConsultaUltimosMovimientosView consulta,
			List<ItemMovimiento> listaCuentaUnica, List<ItemMovimiento> listaCajaAhorroPesos,
			List<ItemMovimiento> listaCuentaCorrientePesos, List<ItemMovimiento> listaDolares) {

		MovimientoView movimientoView = new MovimientoView();
		if (DivisaEnum.DOLAR.equals(consulta.getMoneda())) {
			movimientoView.setItemsMovimiento(listaDolares);
		} else if (TipoCuenta.CUENTA_UNICA.equals(consulta.getTipoCuenta())) {
			movimientoView.setItemsMovimiento(listaCuentaUnica);
		} else if (TipoCuenta.CAJA_AHORRO_PESOS.equals(consulta.getTipoCuenta())) {
			movimientoView.setItemsMovimiento(listaCajaAhorroPesos);
		} else {
			movimientoView.setItemsMovimiento(listaCuentaCorrientePesos);
		}

		return movimientoView;
	}

	/**
	 * Armar paginado.
	 *
	 * @param movimientoView
	 *            the movimiento view
	 * @return the movimiento view
	 */
	private MovimientoView armarPaginado(MovimientoView movimientoView) {

		MovimientoView movimientosPaginados = new MovimientoView();
		int numeroConsulta = sesionMovimientos.getFiltro().getNumeroConsulta();

		if (movimientoView.getItemsMovimiento().size() > 10) {
			List<ItemMovimiento> listaMovimientos = movimientoView.getItemsMovimiento();
			listaMovimientos = listaMovimientos.subList(numeroConsulta * 10,
					numeroConsulta * 10 + 10 > movimientoView.getItemsMovimiento().size()
							? movimientoView.getItemsMovimiento().size()
							: numeroConsulta * 10 + 10);
			movimientosPaginados.setItemsMovimiento(listaMovimientos);
			movimientosPaginados.setLastPage(numeroConsulta * 10 + 10 >= movimientoView.getItemsMovimiento().size());
			sesionMovimientos.getFiltro().setNumeroConsulta(++numeroConsulta);
		} else {
			movimientosPaginados.setItemsMovimiento(movimientoView.getItemsMovimiento());
			movimientosPaginados.setLastPage(true);
		}
		return movimientosPaginados;

	}

	/**
	 * Setear fecha.
	 *
	 * @param fechaSinFormato
	 *            the fecha sin formato
	 * @param dateFormat to be applied
	 * @return the string
	 */
	private String setearFecha(String fechaSinFormato, String dateFormat) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.s");
		DateTime dateTime = dateTimeFormatter.parseDateTime(fechaSinFormato);
		return dateTime.toString(dateFormat);
	}

	/**
	 * Revisar si fecha es distinta.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the boolean
	 */
	private Boolean revisarSiFechaEsDistinta(ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {

		Boolean fechaDistinta = false;

		// Si no tiene rango en sesion, es primera consulta
		if (sesionMovimientos.getFiltro().getRangoFecha() == null) {
			fechaDistinta = false;
		} else if (!sesionMovimientos.getFiltro().getRangoFecha().equals(consultaUltimosMovimientosView.getRango())) {
			fechaDistinta = true;
		} else if (sesionMovimientos.getFiltro().getRangoFecha().equals(consultaUltimosMovimientosView.getRango())
				&& !consultaUltimosMovimientosView.getRango().equals(RangoFechaEnum.PERSONALIZADO)) {
			fechaDistinta = false;
		} else if (consultaUltimosMovimientosView.getRango().equals(RangoFechaEnum.PERSONALIZADO)) {
			Date fechaDesde = convertirFecha(consultaUltimosMovimientosView.getFechaDesde());
			Date fechaHasta = convertirFecha(consultaUltimosMovimientosView.getFechaHasta());
			if (fechaDesde.equals(sesionMovimientos.getFiltro().getFechaDesde())
					&& fechaHasta.equals(sesionMovimientos.getFiltro().getFechaHasta())) {
				fechaDistinta = false;
			} else {
				fechaDistinta = true;
			}

		}

		return fechaDistinta;
	}

	/**
	 * Convertir fecha.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the date
	 */
	private Date convertirFecha(String fecha) {

		DateTime fechaDateTime = new DateTime(Integer.parseInt(fecha.substring(6, fecha.length())),
				Integer.parseInt(fecha.substring(3, 5)), Integer.parseInt(fecha.substring(0, 2)), 0, 0, 0, 0);

		return fechaDateTime.toDate();
	}

	/**
	 * Manejo de respuesta ok.
	 *
	 * @param movimientoView
	 *            the movimiento view
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the respuesta
	 */
	private Respuesta<MovimientoView> manejoDeRespuestaOk(MovimientoView movimientoView,
			ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {

		Respuesta<MovimientoView> respuesta;

		if (movimientoView.getItemsMovimiento().isEmpty()) {
			Boolean esBusquedaAvanzada = !(isBusquedaDefault(consultaUltimosMovimientosView));
			respuesta = respuestaFactory.crearRespuestaWarning(movimientoView, null,
					esBusquedaAvanzada ? TipoError.SIN_MOVIMIENTOS_BUSQUEDA : TipoError.SIN_MOVIMIENTOS,
					esBusquedaAvanzada ? CODIGO_MENSAJE_ERROR_NO_HAY_MOVIMIENTOS_BUSQUEDA_PERSONALIZADA
							: CODIGO_MENSAJE_ERROR_NO_HAY_MOVIMIENTOS);
		} else {
			respuesta = respuestaFactory.crearRespuestaOk(movimientoView);
		}
		return respuesta;
	}

	/**
	 * Manejo de respuesta error.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the respuesta
	 */
	private Respuesta<MovimientoView> manejoDeRespuestaError(
			ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
		Respuesta<MovimientoView> respuesta;
		if (isBusquedaDefault(consultaUltimosMovimientosView)) {
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS,
					CODIGO_MENSAJE_ERROR_CONSULTA);
		} else {
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS_BUSQUEDA,
					CODIGO_MENSAJE_ERROR_CONSULTA_BUSQUEDA_PERSONALIZADA);
		}
		return respuesta;
	}
	
	
//	private Boolean esBusquedaAvanzada(ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
//		return !consultaUltimosMovimientosView.getRango().equals(RangoFechaEnum.DEFAULT)
//				|| StringUtils.isNotBlank(consultaUltimosMovimientosView.getPalabraClave())
//				|| existeFiltroImporte(consultaUltimosMovimientosView);
//	}
//
//	private Boolean existeFiltroImporte(ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
//		return StringUtils.isNotBlank(consultaUltimosMovimientosView.getImporteDesde())
//				|| StringUtils.isNotBlank(consultaUltimosMovimientosView.getImporteHasta());
//	}

	/**
 * Grabar filtro.
 *
 * @param consultaUltimosMovimientosView
 *            the consulta ultimos movimientos view
 * @return the consulta ultimos movimientos
 */
private ConsultaUltimosMovimientos grabarFiltro(ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {

		ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
		consultaUltimosMovimientos.setMoneda(consultaUltimosMovimientosView.getMoneda());
		consultaUltimosMovimientos.setNumeroCuenta(consultaUltimosMovimientosView.getNumero());
		consultaUltimosMovimientos.setTipoCuenta(consultaUltimosMovimientosView.getTipoCuenta());
		consultaUltimosMovimientos.setRangoFecha(consultaUltimosMovimientosView.getRango());
		consultaUltimosMovimientos.setNumeroConsulta(sesionMovimientos.getFiltro().getNumeroConsulta());

		setearFechas(consultaUltimosMovimientosView, consultaUltimosMovimientos);

		if (StringUtils.isNotEmpty(consultaUltimosMovimientosView.getImporteDesde())) {
			consultaUltimosMovimientos
					.setImporteDesde(new BigDecimal(consultaUltimosMovimientosView.getImporteDesde()));
		}

		if (StringUtils.isNotEmpty(consultaUltimosMovimientosView.getImporteHasta())) {
			consultaUltimosMovimientos
					.setImporteHasta(new BigDecimal(consultaUltimosMovimientosView.getImporteHasta()));
		}

		if (StringUtils.isNotEmpty(consultaUltimosMovimientosView.getPalabraClave())) {
			consultaUltimosMovimientos.setPalabraClave(consultaUltimosMovimientosView.getPalabraClave());
		}

		return consultaUltimosMovimientos;
	}

	/**
	 * Setear fechas.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 */
	private void setearFechas(ConsultaUltimosMovimientosView consultaUltimosMovimientosView,
			ConsultaUltimosMovimientos consultaUltimosMovimientos) {
		Date fechaHoyCeroHoras = movimientosBancaPrivadaBO.obtenerFechaHoy(false);
		Date fechaHoyUltimaHora = movimientosBancaPrivadaBO.obtenerFechaHoy(true);
		switch (consultaUltimosMovimientosView.getRango()) {
		case AYER:
			consultaUltimosMovimientos.setFechaDesde(movimientosBancaPrivadaBO.restarDias(fechaHoyCeroHoras, AYER));
			consultaUltimosMovimientos.setFechaHasta(fechaHoyUltimaHora);
			break;
		case TRES_DIAS:
			consultaUltimosMovimientos
					.setFechaDesde(movimientosBancaPrivadaBO.restarDias(fechaHoyCeroHoras, NUM_TRES_DIAS));
			consultaUltimosMovimientos.setFechaHasta(fechaHoyUltimaHora);
			break;
		case SIETE_DIAS:
		case DEFAULT:
			consultaUltimosMovimientos
					.setFechaDesde(movimientosBancaPrivadaBO.restarDias(fechaHoyCeroHoras, NUM_SIETE_DIAS));
			consultaUltimosMovimientos.setFechaHasta(fechaHoyUltimaHora);
			break;
		case QUINCE_DIAS:
			consultaUltimosMovimientos
					.setFechaDesde(movimientosBancaPrivadaBO.restarDias(fechaHoyCeroHoras, NUM_QUINCE_DIAS));
			consultaUltimosMovimientos.setFechaHasta(fechaHoyUltimaHora);
			break;
		case TREINTA_DIAS:
			consultaUltimosMovimientos
					.setFechaDesde(movimientosBancaPrivadaBO.restarDias(fechaHoyCeroHoras, NUM_TREINTA_DIAS));
			consultaUltimosMovimientos.setFechaHasta(fechaHoyUltimaHora);
			break;
		case SESENTA_DIAS:
			consultaUltimosMovimientos
					.setFechaDesde(movimientosBancaPrivadaBO.restarDias(fechaHoyCeroHoras, NUM_SESENTA_DIAS));
			consultaUltimosMovimientos.setFechaHasta(fechaHoyUltimaHora);
			break;
		case CINCUENTA_Y_TRES_DIAS:
        	Date fechaEspecial = movimientosBancaPrivadaBO.restarDias(fechaHoyCeroHoras, NUM_SIETE_DIAS);
        	Date fechaEspecialMenosUnDia = movimientosBancaPrivadaBO.restarDias(fechaHoyCeroHoras, NUM_OCHO_DIAS);
        	consultaUltimosMovimientos.setFechaDesde(movimientosBancaPrivadaBO.restarDias(fechaEspecial, NUM_CINCUENTA_Y_TRES_DIAS));
        	consultaUltimosMovimientos.setFechaHasta(fechaEspecialMenosUnDia);
        	break;
		// utiliza los filtros de fecha desde y hasta de la consulta
		case PERSONALIZADO:
			consultaUltimosMovimientos.setFechaDesde(convertirFecha(consultaUltimosMovimientosView.getFechaDesde()));
			consultaUltimosMovimientos.setFechaHasta(convertirFecha(consultaUltimosMovimientosView.getFechaHasta()));
			break;
		default:
			break;
		}
	}

	/**
	 * Obtener valores de sesion.
	 *
	 * @return the consulta ultimos movimientos view
	 */
	private ConsultaUltimosMovimientosView obtenerValoresDeSesion() {

		ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();

		consultaUltimosMovimientosView.setNumero(sesionMovimientos.getFiltro().getNumeroCuenta());
		consultaUltimosMovimientosView.setMoneda(sesionMovimientos.getFiltro().getMoneda());
		consultaUltimosMovimientosView.setRango(sesionMovimientos.getFiltro().getRangoFecha());
		consultaUltimosMovimientosView.setTipoCuenta(sesionMovimientos.getFiltro().getTipoCuenta());
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		consultaUltimosMovimientosView.setFechaDesde(formateador.format(sesionMovimientos.getFiltro().getFechaDesde()));
		consultaUltimosMovimientosView.setFechaHasta(formateador.format(sesionMovimientos.getFiltro().getFechaHasta()));

		if (sesionMovimientos.getFiltro().getImporteDesde() != null) {
			consultaUltimosMovimientosView.setImporteDesde(sesionMovimientos.getFiltro().getImporteDesde().toString());
		}

		if (sesionMovimientos.getFiltro().getImporteHasta() != null) {
			consultaUltimosMovimientosView.setImporteHasta(sesionMovimientos.getFiltro().getImporteHasta().toString());
		}

		if (StringUtils.isNotEmpty(sesionMovimientos.getFiltro().getPalabraClave())) {
			consultaUltimosMovimientosView.setPalabraClave(sesionMovimientos.getFiltro().getPalabraClave());
		}

		return consultaUltimosMovimientosView;

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.manager.MovimientosBancaPrivadaManager#exportarMovimientos()
	 */
	@Override
	public Response exportarMovimientos() {
		LOGGER.info("Iniciando reporte movimientos excel");
		Respuesta<Reporte> respuestaReporte = getUltimosMovimientosReporte();
		ResponseBuilder responseBuilder = null;
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		if (EstadoRespuesta.OK.equals(respuestaReporte.getEstadoRespuesta())) {
			LOGGER.info("Armando excel OK");
			ReporteView resumenesView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
			respuestaView.setRespuesta(resumenesView);
			respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
			responseBuilder = Response.ok((Object) respuestaView.getRespuesta().getByteArray());
			responseBuilder.header("Content-Disposition", "attachment; filename=\"UltimosMovimientos.xls\"");
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_EXCEL_GRILLA_MOVIMIENTOS_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			LOGGER.info("Falla en armado de excel");
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_EXCEL_GRILLA_MOVIMIENTOS_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaView = respuestaFactory.crearRespuestaErrorPersonalizado(ReporteView.class, MENSAJE_ERROR_EXCEL,
					TipoError.ERROR_DESCARGA_EXCEL.getDescripcion());
			responseBuilder = Response.ok((Object) respuestaView);
		}
		return responseBuilder.build();
	}

	/**
	 * Gets the ultimos movimientos reporte.
	 *
	 * @return the ultimos movimientos reporte
	 */
	private Respuesta<Reporte> getUltimosMovimientosReporte() {
	    List<ItemMovimiento> movimientos;
        try {
            movimientos = obtenerMovimientosFiltro();
        } catch (BusinessException e) {
            return respuestaFactory.crearRespuestaError(Reporte.class, null);
        }

		String dispositivo = sesionParametros.getRegistroSession().getDispositivo();
		Cliente cliente = sesionCliente.getCliente();
		return movimientosBancaPrivadaBO.exportarUltimosMovimientos(movimientos, dispositivo,
				cliente, sesionMovimientos.getFiltro());
	}

	@Override
    public Respuesta<ReporteView> exportarMovimientosPDF() {
	    LOGGER.info("Iniciando reporte movimientos PDF");
        List<ItemMovimiento> movimientos;
        try {
            movimientos = obtenerMovimientosFiltro();
        } catch (BusinessException e) {
            LOGGER.info("Falla en armado del PDF");
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_COMPROBANTE,
                    CodigoMensajeConstantes.PAGO_COMPRAS_DESCARGA_COMPROBANTE_ERROR);
        }
        String dispositivo = sesionParametros.getRegistroSession().getDispositivo();
        DetalleComprobanteView detalleUltimosMovimientos = movimientosBancaPrivadaBO.exportarUltimosMovimientosPDF(
                movimientos, dispositivo, sesionMovimientos.getFiltro());
        Respuesta<Reporte> reporte = reporteBO.obtenerComprobantePDF(detalleUltimosMovimientos);
        Respuesta<ReporteView> respuestaView;
        if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
            LOGGER.info("Armando PDF OK");
            ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
            respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
        } else {
            LOGGER.info("Falla en armado del PDF");
            respuestaView = respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_COMPROBANTE,
                    CodigoMensajeConstantes.PAGO_COMPRAS_DESCARGA_COMPROBANTE_ERROR);
        }
        return respuestaView;
        
    }
	
    private List<ItemMovimiento> obtenerMovimientosFiltro() throws BusinessException {
        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = obtenerValoresDeSesion();
        
        Cuenta cuenta = movimientosBancaPrivadaBO.obtenerCuentaCliente(sesionCliente.getCliente(), consultaUltimosMovimientosView);
        if (!PRODUCTO_ALTAIR_CUENTA_TRANSACCIONAL.equals(cuenta.getProductoAltair())) {
            throw new BusinessException();
        }
        MovimientosCuentaBPOutEntity movimientosCuentaBPOutEntity = movimientosBancaPrivadaBO
                .obtenerMovimientos(consultaUltimosMovimientosView, sesionCliente.getCliente(), cuenta);

        if (tieneConfiguradoFiltro(consultaUltimosMovimientosView)) {
            movimientosCuentaBPOutEntity = movimientosBancaPrivadaBO.aplicarFiltros(movimientosCuentaBPOutEntity,
                    consultaUltimosMovimientosView);
        }

        MovimientoView movimientoView = crearListaMovimientos(movimientosCuentaBPOutEntity,
                consultaUltimosMovimientosView);
        return movimientoView.getItemsMovimiento();
    }

	/**
	 * Tiene configurado filtro.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the boolean
	 */
	private Boolean tieneConfiguradoFiltro(ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
		return (StringUtils.isNotEmpty(consultaUltimosMovimientosView.getImporteDesde())
				|| StringUtils.isNotEmpty(consultaUltimosMovimientosView.getImporteHasta()))
				|| StringUtils.isNotEmpty(consultaUltimosMovimientosView.getPalabraClave());
	}
	
	/**
	 * Checks if is busqueda default.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the boolean
	 */
	private Boolean isBusquedaDefault (ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
		return RangoFechaEnum.DEFAULT.equals(consultaUltimosMovimientosView.getRango()) || 
			   RangoFechaEnum.CINCUENTA_Y_TRES_DIAS.equals(consultaUltimosMovimientosView.getRango());
	}
	
}
