package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.SessionMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.BoxCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CabeceraCuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CopiarMensajeView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSelectorView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasIntermedioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DatosComprobanteTransferencia;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.GrupoCajitaCuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteinEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenMesualCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dto.CuentaIntermedioDTO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.ComprobanteNuevaTransferencia;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.CuentaBancaPrivadaSelectorView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.DetalleCuentaCBUBancaPrivadaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.NuevaTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.SelectorCuentasBancaPrivadaView;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

/**
 * The Class CuentasBancaPrivadaManagerImpl.
 */
@Component
public class CuentasBancaPrivadaManagerImpl implements CuentasBancaPrivadaManager {

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The cuentas banca privada BO. */
	@Autowired
	private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The alias cuenta BO. */
	@Autowired
	private AliasCuentaBO aliasCuentaBO;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The sesionParametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The cuenta manager. */
	@Autowired
	private CuentaManager cuentaManager;
	
	/** The sesion movimientos. */
	@Autowired
	private SessionMovimientos sesionMovimientos;
	
    @Autowired
    private SessionResumenCuenta sesionResumenCuenta;

	/** The Constant MENSAJE_ERROR_CONSULTA_SALDOS. */
	private static final String MENSAJE_ERROR_CONSULTA_SALDOS = "10504";

	/** The Constant LEGALES_CUENTAS_BANCA_PRIVADA. */
	private static final String LEGALES_CUENTAS_BANCA_PRIVADA = "10041";

	/** The Constant NOMBRE_PDF. */
	private static final String NOMBRE_PDF = "ComprobanteCbuyCuenta.pdf";

	/** The Constant TIPO_IDENTIFICACION_DETALLE_CUENTA. */
	private static final String TIPO_IDENTIFICACION_DETALLE_CUENTA = "CUIT/CUIL";

	/** The Constant ESPACIO. */
	private static final String ESPACIO = " ";

	/** The Constant MONEDA_DOLAR. */
	private static final String MONEDA_DOLAR = "dolar";

	/** The Constant MONEDA_PESO. */
	private static final String MONEDA_PESO = "peso";

	/** The Constant SALDOS_TOTALES. */
	private static final String SALDOS_TOTALES = "Saldos totales";

	/** The Constant POSEE_SOLO_UNA_CUENTA_BPRIV. */
	private static final int POSEE_SOLO_UNA_CUENTA_BPRIV = 1;
	
    /** The Constant FECHA_DESDE_MOVIMIENTO. */
    private static final int FECHA_DESDE_MOVIMIENTO = -60;

	/** The Constant NOMBRE_PDF_NUEVA_TRANSFERENCIA. */
	private static final String NOMBRE_PDF_NUEVA_TRANSFERENCIA = "Comprobante de transferencia Banca Privada.pdf";


	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#obtenerInicioCuentas()
	 */
	@Override
	public Respuesta<CuentasIntermedioView> obtenerInicioCuentas() {
		
		List<CuentaIntermedioDTO> respuestaSaldos = new ArrayList<CuentaIntermedioDTO>();
		Respuesta<CuentasIntermedioView> respuesta = new Respuesta<CuentasIntermedioView>();
		CuentasIntermedioView cuentasIntermedioView = new CuentasIntermedioView();
		respuesta.setRespuesta(cuentasIntermedioView);
		respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

		try {
			respuestaSaldos = cuentasBancaPrivadaBO.consultarSaldosCuenta(sesionCliente.getCliente());
			generarCabecera(respuestaSaldos, respuesta);
			generarGruposDeCajitas(respuestaSaldos, respuesta);
			generarSelector(respuestaSaldos, respuesta);

		} catch (SQLException e) {
			return armarRespuestaError(CuentasIntermedioView.class);
		} catch (BusinessException e1) {
			return armarRespuestaError(CuentasIntermedioView.class);
		}

		if (respuesta.getItemsMensajeRespuesta().isEmpty()) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			estadisticaManager.add(EstadisticasConstants.CODIGO_BANCA_PRIVADA_CONSULTA_DE_SALDOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
			estadisticaManager.add(EstadisticasConstants.CODIGO_BANCA_PRIVADA_CONSULTA_DE_SALDOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

		if (respuestaSaldos.size() == POSEE_SOLO_UNA_CUENTA_BPRIV
				&& (!sesionCliente.getCliente().puedeTransferirBpriv())) {
			respuesta.getRespuesta().setPuedeTransferir(false);
		}

		return respuesta;
	}

	/**
	 * Generar cabecera.
	 *
	 * @param respuestaSaldos
	 *            the respuesta saldos
	 * @param respuesta
	 *            the respuesta
	 */
	private void generarCabecera(List<CuentaIntermedioDTO> respuestaSaldos,
			Respuesta<CuentasIntermedioView> respuesta) {
		CabeceraCuentasView cabeceraCuentasView = new CabeceraCuentasView();
		BigDecimal saldoPesos = BigDecimal.ZERO;
		BigDecimal saldoDolares = BigDecimal.ZERO;

		for (CuentaIntermedioDTO cuentaSaldos : respuestaSaldos) {
			if (cuentaSaldos.getIsCuentaTransaccional() && cuentaSaldos.getSaldosStoredProcedure().getErrorEnConsulta()) {
				cabeceraCuentasView.setSaldoPesos("-");
				cabeceraCuentasView.setSaldoDolares("-");
				respuesta.getRespuesta().setCabecera(cabeceraCuentasView);
				respuesta.getItemsMensajeRespuesta().add(respuestaFactory.generarItemMensajeRespuesta(null,
						TipoError.ERROR_ITEM_CUENTA, CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDOS_BCAPRIV));

			} else {
			    BigDecimal bigPesos = BigDecimal.ZERO;
				BigDecimal bigDolares = BigDecimal.ZERO;
				if (!cuentaSaldos.getIsCuentaTransaccional()) {
				    bigDolares = ISBANStringUtils.stringToBigDecimal(cuentaSaldos.getSaldosServicioIatx()
                            .getSaldoDispoACAD(), 13,2, true);
				    bigPesos = ISBANStringUtils.stringToBigDecimal(cuentaSaldos.getSaldosServicioIatx()
				            .getSaldoDispoACAH(), 13,2, true);
                } else {
                    bigPesos = new BigDecimal(
                            cuentaSaldos.getSaldosStoredProcedure().getRespuesta().get(0).getcAhorroPesos());
				    bigDolares = new BigDecimal(
						cuentaSaldos.getSaldosStoredProcedure().getRespuesta().get(0).getcAhorroDolares());
                }
				saldoPesos = saldoPesos.add(bigPesos);
				saldoDolares = saldoDolares.add(bigDolares);
			}
		}

		if (respuesta.getItemsMensajeRespuesta().isEmpty()) {
			cabeceraCuentasView.setSaldoPesos(ISBANStringUtils.formatearSaldoConSigno(saldoPesos));
			cabeceraCuentasView.setSaldoPesosValue(saldoPesos);
			cabeceraCuentasView.setSaldoDolares(ISBANStringUtils.formatearSaldoConSigno(saldoDolares));
			cabeceraCuentasView.setSaldoDolaresValue(saldoDolares);
		}
		respuesta.getRespuesta().setCabecera(cabeceraCuentasView);
	}

	/**
	 * Generar grupos de cajitas.
	 *
	 * @param respuestaSaldos
	 *            the respuesta saldos
	 * @param respuesta
	 *            the respuesta
	 */
	private void generarGruposDeCajitas(List<CuentaIntermedioDTO> respuestaSaldos,
			Respuesta<CuentasIntermedioView> respuesta) {

		List<GrupoCajitaCuentasView> gruposCajitas = new ArrayList<GrupoCajitaCuentasView>();

		for (CuentaIntermedioDTO cuentaSaldos : respuestaSaldos) {
			GrupoCajitaCuentasView grupoCajita = new GrupoCajitaCuentasView();
			List<BoxCuentaView> listaCajitas = new ArrayList<BoxCuentaView>();
			if (cuentaSaldos.getIsCuentaUnica()) {
				listaCajitas.add(crearCajitaCuenta(cuentaSaldos, TipoCuenta.CUENTA_UNICA_PESOS, respuesta));
				listaCajitas.add(crearCajitaCuenta(cuentaSaldos, TipoCuenta.CUENTA_UNICA_DOLARES, respuesta));
			} else {
				listaCajitas.add(crearCajitaCuenta(cuentaSaldos,
						TipoCuenta.fromCodigo(cuentaSaldos.getTipoCuenta().getCodigo()), respuesta)); 
			}
			grupoCajita.setCajas(listaCajitas);
			gruposCajitas.add(grupoCajita);
		}

		respuesta.getRespuesta().setGrupos(gruposCajitas);
	}

	/**
	 * Crear cajita cuenta.
	 *
	 * @param cuentaSaldos
	 *            the cuenta saldos
	 * @param tipoCajitaCuentaEnum
	 *            the tipo cajita cuenta enum
	 * @param respuesta
	 *            the respuesta
	 * @return the box cuenta view
	 */
	private BoxCuentaView crearCajitaCuenta(CuentaIntermedioDTO cuentaSaldos, TipoCuenta tipoCajitaCuentaEnum,
			Respuesta<CuentasIntermedioView> respuesta) {

		BoxCuentaView boxCuentaView = new BoxCuentaView();

		boxCuentaView.setNumeroCuenta(cuentaSaldos.getNumeroCuenta());
		boxCuentaView.setAlias(StringUtils.isEmpty(cuentaSaldos.getApodo()) ? null : cuentaSaldos.getApodo());

		if ((cuentaSaldos.getIsCuentaTransaccional() && cuentaSaldos.getSaldosStoredProcedure().getErrorEnConsulta())
				|| cuentaSaldos.getSaldosServicioIatx().getErrorEnConsulta()) {
			boxCuentaView.setSaldo("-");
			boxCuentaView.setSaldoCajaAhorro("-");
			boxCuentaView.setSaldoCuentaCorriente("-");
			boxCuentaView.setDescripcionTipoCuenta(tipoCajitaCuentaEnum.getDescripcionConMoneda());
			boxCuentaView.setMoneda(esCuentaPesos(tipoCajitaCuentaEnum) ? MONEDA_PESO : MONEDA_DOLAR);
			respuesta.getItemsMensajeRespuesta().add(respuestaFactory.generarItemMensajeRespuesta(null,
					TipoError.ERROR_ITEM_CUENTA, CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDOS_BCAPRIV));
			return boxCuentaView;
		}

		if (TipoCuenta.CUENTA_UNICA_PESOS.equals(tipoCajitaCuentaEnum)) {
			boxCuentaView.setIsCuentaUnica(true);
			boxCuentaView.setSaldo(ISBANStringUtils.formatearSaldoConSigno(
					new BigDecimal(cuentaSaldos.getSaldosStoredProcedure().getRespuesta().get(0).getcAhorroPesos())));
			boxCuentaView.setSaldoValue(
					new BigDecimal(cuentaSaldos.getSaldosStoredProcedure().getRespuesta().get(0).getcAhorroPesos()));
			boxCuentaView.setSaldoCajaAhorro(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAH()))));
			boxCuentaView.setSaldoCajaAhorroValue(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAH())));
			if ("S".equals(cuentaSaldos.getSaldosServicioIatx().getIndSobregreido())) {
				boxCuentaView
						.setSaldoCuentaCorriente(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
								.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACTE()))));
				boxCuentaView.setSaldoCuentaCorrienteValue(new BigDecimal(ISBANStringUtils
						.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACTE())));
			}
			boxCuentaView.setDescripcionTipoCuenta(tipoCajitaCuentaEnum.getDescripcion());
			boxCuentaView.setMoneda(MONEDA_PESO);
		}

		if (TipoCuenta.CUENTA_UNICA_DOLARES.equals(tipoCajitaCuentaEnum)) {
			boxCuentaView.setIsCuentaUnica(true);
			boxCuentaView.setSaldo(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAD()))));
			boxCuentaView.setSaldoValue(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAD())));
			boxCuentaView.setDescripcionTipoCuenta(tipoCajitaCuentaEnum.getDescripcion());
			boxCuentaView.setMoneda(MONEDA_DOLAR);
		}

		if (TipoCuenta.CAJA_AHORRO_PESOS.equals(tipoCajitaCuentaEnum)) {
			boxCuentaView.setSaldo(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAH()))));
			boxCuentaView.setSaldoValue(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAH())));
			boxCuentaView.setDescripcionTipoCuenta(tipoCajitaCuentaEnum.getDescripcionConMoneda());
			boxCuentaView.setMoneda(MONEDA_PESO);
		}

		if (TipoCuenta.CAJA_AHORRO_DOLARES.equals(tipoCajitaCuentaEnum)) {
			boxCuentaView.setSaldo(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAD()))));
			boxCuentaView.setSaldoValue(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAD())));
			boxCuentaView.setDescripcionTipoCuenta(tipoCajitaCuentaEnum.getDescripcionConMoneda());
			boxCuentaView.setMoneda(MONEDA_DOLAR);
		}

		if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(tipoCajitaCuentaEnum)) {
			boxCuentaView.setSaldo(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACCD()))));
			boxCuentaView.setSaldoValue(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACCD())));
			boxCuentaView.setDescripcionTipoCuenta(tipoCajitaCuentaEnum.getDescripcionConMoneda());
			boxCuentaView.setMoneda(MONEDA_DOLAR);
		}

		if (TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(tipoCajitaCuentaEnum)) {
			boxCuentaView.setSaldo(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACTE()))));
			boxCuentaView.setSaldoValue(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACTE())));
			boxCuentaView.setDescripcionTipoCuenta(tipoCajitaCuentaEnum.getDescripcionConMoneda());
			boxCuentaView.setMoneda(MONEDA_PESO);
		}

		return boxCuentaView;
	}
	
	/**
	 * Es cuenta pesos.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	private Boolean esCuentaPesos(TipoCuenta tipoCuenta) {
		return tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_PESOS) || tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS) || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_PESOS);
	}

	/**
	 * Generar selector.
	 *
	 * @param respuestaSaldos
	 *            the respuesta saldos
	 * @param respuesta
	 *            the respuesta
	 */
	private void generarSelector(List<CuentaIntermedioDTO> respuestaSaldos,
			Respuesta<CuentasIntermedioView> respuesta) {
		List<CuentaSelectorView> listaCuentaSelectorView = new ArrayList<CuentaSelectorView>();
		CuentaSelectorView cuentaSelector = agregarCabecera(respuesta.getRespuesta().getCabecera());
		listaCuentaSelectorView.add(cuentaSelector);
		for (CuentaIntermedioDTO cuentaSaldos : respuestaSaldos) {
			CuentaSelectorView cuentaSelectorView = agregarCuentasASelector(cuentaSaldos);
			listaCuentaSelectorView.add(cuentaSelectorView);
		}
		respuesta.getRespuesta().setSelector(listaCuentaSelectorView);
	}

	/**
	 * Agregar cabecera.
	 *
	 * @param cabecera
	 *            the cabecera
	 * @return the cuenta selector view
	 */
	private CuentaSelectorView agregarCabecera(CabeceraCuentasView cabecera) {

		CuentaSelectorView cuentaSelectorView = new CuentaSelectorView();
		cuentaSelectorView.setDescripcionTipoCuenta(SALDOS_TOTALES);
		cuentaSelectorView.setSaldoPesos(cabecera.getSaldoPesos());
		cuentaSelectorView.setSaldoPesosValue(cabecera.getSaldoPesosValue());
		cuentaSelectorView.setSaldoDolares(cabecera.getSaldoDolares());
		cuentaSelectorView.setSaldoDolaresValue(cabecera.getSaldoDolaresValue());

		return cuentaSelectorView;
	}

	/**
	 * Agregar cuentas A selector.
	 *
	 * @param cuentaSaldos
	 *            the cuenta saldos
	 * @return the cuenta selector view
	 */
	private CuentaSelectorView agregarCuentasASelector(CuentaIntermedioDTO cuentaSaldos) {

		CuentaSelectorView cuentaSelectorView = new CuentaSelectorView();
		cuentaSelectorView.setAlias(cuentaSaldos.getApodo());
		cuentaSelectorView.setHasAlias(StringUtils.isEmpty(cuentaSelectorView.getAlias()) ? false : true);
		cuentaSelectorView.setDescripcionTipoCuenta(cuentaSaldos.getTipoCuenta().getDescripcion());
		cuentaSelectorView.setNumero(cuentaSaldos.getNumeroCuenta());
		cuentaSelectorView.setCbu(cuentaSaldos.getCbu());

		if ((cuentaSaldos.getIsCuentaTransaccional() && cuentaSaldos.getSaldosStoredProcedure().getErrorEnConsulta())) {
			cuentaSelectorView.setSaldoPesos("-");
			cuentaSelectorView.setSaldoCajaAhorro("-");
			cuentaSelectorView.setSaldoDolares("-");
			return cuentaSelectorView;
		}

		if (cuentaSaldos.getIsCuentaUnica()) {
			cuentaSelectorView.setIsCuentaUnica(true);
			cuentaSelectorView.setSaldoPesos(ISBANStringUtils.formatearSaldoConSigno(
					new BigDecimal(cuentaSaldos.getSaldosStoredProcedure().getRespuesta().get(0).getcAhorroPesos())));
			cuentaSelectorView.setSaldoPesosValue(
					new BigDecimal(cuentaSaldos.getSaldosStoredProcedure().getRespuesta().get(0).getcAhorroPesos()));
			cuentaSelectorView
					.setSaldoCajaAhorro(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
							.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAH()))));
			cuentaSelectorView.setSaldoCajaAhorroValue(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAH())));
			if ("S".equals(cuentaSaldos.getSaldosServicioIatx().getIndSobregreido())) {
				cuentaSelectorView
						.setSaldoCuentaCorriente(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
								.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACTE()))));
				cuentaSelectorView.setSaldoCuentaCorrienteValue(new BigDecimal(ISBANStringUtils
						.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACTE())));

				revisarSiCorrespondeMostrarDescubierto(cuentaSelectorView, cuentaSaldos);

			}
			cuentaSelectorView.setSaldoDolares(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAD()))));
			cuentaSelectorView.setSaldoDolaresValue(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAD())));
		}

		if (TipoCuenta.CAJA_AHORRO_PESOS.equals(cuentaSaldos.getTipoCuenta())) {
			cuentaSelectorView.setIsCuentaUnica(false);
			cuentaSelectorView.setSaldoPesos(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAH()))));
			cuentaSelectorView.setSaldoPesosValue(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAH())));
		}

		if (TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(cuentaSaldos.getTipoCuenta())) {
			cuentaSelectorView.setIsCuentaUnica(false);
			cuentaSelectorView.setSaldoPesos(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACTE()))));
			cuentaSelectorView.setSaldoPesosValue(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACTE())));

			cuentaSelectorView.setShowDescubierto(
					cuentaSelectorView.getSaldoPesosValue().compareTo(BigDecimal.ZERO) == 0 ? false : true);
			cuentaSelectorView.setIsDescubiertoUtilizado(true);
			cuentaSelectorView
					.setSaldoDescubierto(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
							.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getLimiteCredito()))));
			cuentaSelectorView.setSaldoDescubiertoValue(new BigDecimal(
					ISBANStringUtils.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getLimiteCredito())));

			revisarSiCorrespondeMostrarDescubierto(cuentaSelectorView, cuentaSaldos);

		}

		if (TipoCuenta.CAJA_AHORRO_DOLARES.equals(cuentaSaldos.getTipoCuenta())) {
			cuentaSelectorView.setIsCuentaUnica(false);
			cuentaSelectorView.setSaldoDolares(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAD()))));
			cuentaSelectorView.setSaldoDolaresValue(new BigDecimal(ISBANStringUtils
					.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getSaldoDispoACAD())));
		}

		return cuentaSelectorView;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#modificarApodo(java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<Void> modificarApodo(String numeroCuenta, String alias) {
		Respuesta<Void> reporteAlias = cuentasBancaPrivadaBO.actualizarApodo(new IdentificacionCuenta(numeroCuenta),
				alias, sesionCliente.getCliente());
		if (EstadoRespuesta.OK.equals(reporteAlias.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_BANCA_PRIVADA_MODIFICAR_APODO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.CODIGO_BANCA_PRIVADA_MODIFICAR_APODO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return reporteAlias;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#verDetalleCBU(java.lang.String)
	 */
	@Override
	public Respuesta<DetalleCuentaCBUBancaPrivadaView> verDetalleCBU(String numeroCuenta) {
		DetalleCuentaCBUBancaPrivadaView detalle = new DetalleCuentaCBUBancaPrivadaView();
		Cliente cliente = sesionCliente.getCliente();
		detalle.setTipoIdentificacion(TIPO_IDENTIFICACION_DETALLE_CUENTA);
		detalle.setIdentificacionCliente(sesionCliente.getCliente().getNumeroCUILCUIT());
		detalle.setNombreCliente(WordUtils.capitalizeFully(cliente.getNombre() + ESPACIO + cliente.getApellido1()));
		AbstractCuenta cuenta = cuentasBancaPrivadaBO.buscarCuentaPorId(cliente,
				new IdentificacionCuenta(numeroCuenta));
		detalle.setCbu(cuenta.getCbu());
		detalle.setNumeroCuenta(numeroCuenta);
		detalle.setNumeroSucursal(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()));
		detalle.setTipoCuenta(cuenta.getTipoCuentaEnum().equals(TipoCuenta.CUENTA_UNICA_PESOS)
				|| cuenta.getTipoCuentaEnum().equals(TipoCuenta.CUENTA_UNICA_DOLARES)
						? cuenta.getTipoCuentaEnum().getDescripcion()
						: cuenta.getTipoCuentaEnum().getDescripcionConMoneda());
		detalle.setNombreBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		sesionParametros.setDetalleComprobanteView(detalle);
		return respuestaFactory.crearRespuestaOk(detalle);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#obtenerCuentas(ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView)
	 */
	@Override
	public Respuesta<SelectorCuentasBancaPrivadaView> obtenerCuentas(ConsultaCuentaView cuenta) {	
		sesionParametros.setContador(new ContadorIntentos(2));	
		List<CuentaIntermedioDTO> respuestaSaldos = new ArrayList<CuentaIntermedioDTO>();
		Respuesta<CuentasIntermedioView> respuestaIntermedioView = new Respuesta<CuentasIntermedioView>();
		CuentasIntermedioView cuentasIntermedioView = new CuentasIntermedioView();
		respuestaIntermedioView.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
		respuestaIntermedioView.setRespuesta(cuentasIntermedioView);
		Respuesta<SelectorCuentasBancaPrivadaView> respuesta = new Respuesta<SelectorCuentasBancaPrivadaView>();
		respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
		SelectorCuentasBancaPrivadaView selectorCuentasView = new SelectorCuentasBancaPrivadaView();
		Cliente cliente = sesionCliente.getCliente();
		
		if (cuenta.getNumeroCuenta() == null) {
            cuenta.setNumeroCuenta(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cliente.getCuentasPrivadas().get(0)));
			selectorCuentasView.setUnicaCuenta(Boolean.TRUE);
		}
		try {
			respuestaSaldos = cuentasBancaPrivadaBO.consultarSaldosCuenta(cliente);

			generarCabecera(respuestaSaldos, respuestaIntermedioView);
			generarSelector(respuestaSaldos, respuestaIntermedioView);

			List<CuentaBancaPrivadaSelectorView> listaCuentas = copiarSelector(
					respuestaIntermedioView.getRespuesta().getSelector(), cliente, cuenta.getNumeroCuenta());
			Integer indiceCuenta = recuperarIndiceDeCuentaSeleccionada(respuestaSaldos, cuenta.getNumeroCuenta());
			selectorCuentasView.setLegales(legalBO.obtenerLegal(LEGALES_CUENTAS_BANCA_PRIVADA));
			selectorCuentasView.setSelected(indiceCuenta);
			selectorCuentasView.setCuentas(listaCuentas);
			respuesta.setRespuesta(selectorCuentasView);

		} catch (SQLException e) {
			return armarRespuestaError(SelectorCuentasBancaPrivadaView.class);
		} catch (BusinessException e) {
			return armarRespuestaError(SelectorCuentasBancaPrivadaView.class);
		} catch (DAOException e) {
			selectorCuentasView.setLegales("-");
		}

		for (CuentaIntermedioDTO cuentaSaldos : respuestaSaldos) {
		    if(!cuentaSaldos.getIsCuentaTransaccional()) {
		        estadisticaManager.add(EstadisticasConstants.CODIGO_BANCA_PRIVADA_CONSULTA_DE_SALDOS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		    }
			if ((cuentaSaldos.getIsCuentaTransaccional() && cuentaSaldos.getSaldosStoredProcedure().getErrorEnConsulta())
					|| cuentaSaldos.getSaldosServicioIatx().getErrorEnConsulta()) {
				String tag = "cuentas[" + cuentaSaldos.getNumeroCuenta() + "]";
				respuesta.getItemsMensajeRespuesta().add(respuestaFactory.generarItemMensajeRespuesta(tag,
						TipoError.ERROR_ITEM_CUENTA, CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDOS_BCAPRIV));
				estadisticaManager.add(EstadisticasConstants.CODIGO_BANCA_PRIVADA_CONSULTA_DE_SALDOS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
				return respuesta;
			}

		}

		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		estadisticaManager.add(EstadisticasConstants.CODIGO_BANCA_PRIVADA_CONSULTA_DE_SALDOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuesta;

	}

	/**
	 * Copiar selector.
	 *
	 * @param selector
	 *            the selector
	 * @param cliente
	 *            the cliente
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the list
	 */
	private List<CuentaBancaPrivadaSelectorView> copiarSelector(List<CuentaSelectorView> selector, Cliente cliente,
			String numeroCuenta) {

		List<CuentaBancaPrivadaSelectorView> listaCuentas = new ArrayList<CuentaBancaPrivadaSelectorView>();
		try {
			DetalleDocumentoDTO detalleDocumento = aliasCuentaBO.obtenerDocumentoValidoDTO(cliente);
			sesionCliente.getCliente().setNumeroCUILCUIT(aliasCuentaBO.obtenerCuitPorDetalle(detalleDocumento).equals("-") ? null : aliasCuentaBO.obtenerCuitPorDetalle(detalleDocumento));
		} catch (DAOException e) {
			sesionCliente.getCliente().setNumeroCUILCUIT(null);
		}
        Calendar calendar = Calendar.getInstance();
        String fechaHastaMovimiento = ISBANStringUtils.formatearFecha(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, FECHA_DESDE_MOVIMIENTO);
        String fechaDesdeMovimiento = ISBANStringUtils.formatearFecha(calendar.getTime());
		for (CuentaSelectorView cuenta : selector) {
			CuentaBancaPrivadaSelectorView cuentaBancaPrivadaSelectorView = new CuentaBancaPrivadaSelectorView();
			BeanUtils.copyProperties(cuenta, cuentaBancaPrivadaSelectorView);
			cuentaBancaPrivadaSelectorView.setNombreCliente(WordUtils.capitalizeFully(cliente.getNombre()));
			cuentaBancaPrivadaSelectorView.setApellidoCliente(WordUtils.capitalizeFully(cliente.getApellido1()));
			cuentaBancaPrivadaSelectorView.setTipoIdentificacion(TIPO_IDENTIFICACION_DETALLE_CUENTA);
			cuentaBancaPrivadaSelectorView.setNumeroIdentificacion(sesionCliente.getCliente().getNumeroCUILCUIT());
			cuentaBancaPrivadaSelectorView.setSucursal(numeroCuenta.substring(0, 3));
			cuentaBancaPrivadaSelectorView.setFechaDesdeMovimiento(fechaDesdeMovimiento);
			cuentaBancaPrivadaSelectorView.setFechaHastaMovimiento(fechaHastaMovimiento);
			listaCuentas.add(cuentaBancaPrivadaSelectorView);

		}
		return listaCuentas;

	}

	/**
	 * Recuperar indice de cuenta seleccionada.
	 *
	 * @param respuestaSaldos
	 *            the respuesta saldos
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the integer
	 */
	private Integer recuperarIndiceDeCuentaSeleccionada(List<CuentaIntermedioDTO> respuestaSaldos,
			String numeroCuenta) {
		Integer indice = 0;
		for (CuentaIntermedioDTO cuenta : respuestaSaldos) {
			if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
				return indice + 1;
			}
			indice++;
		}
		return 0;
	}

	/**
	 * Revisar si corresponde mostrar descubierto.
	 *
	 * @param cuentaSelectorView
	 *            the cuenta selector view
	 * @param cuentaSaldos
	 *            the cuenta saldos
	 */
	private void revisarSiCorrespondeMostrarDescubierto(CuentaSelectorView cuentaSelectorView,
			CuentaIntermedioDTO cuentaSaldos) {

		BigDecimal saldoDescubierto = new BigDecimal(
				ISBANStringUtils.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getLimiteCredito()));

		if (saldoDescubierto.compareTo(BigDecimal.ZERO) != 0) {
			cuentaSelectorView
					.setSaldoDescubierto(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils
							.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getLimiteCredito()))));
			cuentaSelectorView.setSaldoDescubiertoValue(new BigDecimal(
					ISBANStringUtils.importePtoFijo2Canonico(cuentaSaldos.getSaldosServicioIatx().getLimiteCredito())));
			cuentaSelectorView.setShowDescubierto(true);
			cuentaSelectorView.setIsDescubiertoUtilizado(true);
		} else {
			cuentaSelectorView.setShowDescubierto(false);
			cuentaSelectorView.setIsDescubiertoUtilizado(false);
		}

	}

	/**
	 * Armar respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @return the respuesta
	 */
	private <T> Respuesta<T> armarRespuestaError(Class<T> respuestaClass) {

		estadisticaManager.add(EstadisticasConstants.CODIGO_BANCA_PRIVADA_CONSULTA_DE_SALDOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaErrorPersonalizado(respuestaClass,
				mensajeBO.obtenerMensajePorCodigo(MENSAJE_ERROR_CONSULTA_SALDOS).getMensaje(), null);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#descargarComprobanteCBUCuenta()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteCBUCuenta() {
		Respuesta<ReporteView> respuestaReporteView;
		Respuesta<Reporte> respuestaReporte = reporteBO
				.obtenerComprobantePDF(sesionParametros.getDetalleComprobanteView());
		if (EstadoRespuesta.OK.equals(respuestaReporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
			reporteView.setNombre(NOMBRE_PDF);
			respuestaReporteView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.CODIGO_BANCA_PRIVADA_DESCARGA_COPIA_CBU,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaReporteView = respuestaFactory.crearRespuestaError(ReporteView.class,
					respuestaReporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.CODIGO_BANCA_PRIVADA_DESCARGA_COPIA_CBU,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaReporteView;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#copiarCBU()
	 */
	@Override
	public Respuesta<CopiarMensajeView> copiarCBU() {
		String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COPIAR_CBU_OK).getMensaje();
		CopiarMensajeView copiarMensajeView = new CopiarMensajeView();
		copiarMensajeView.setMensaje(mensaje);
		estadisticaManager.add(EstadisticasConstants.CODIGO_BANCA_PRIVADA_DESCARGA_COPIA_CBU,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(copiarMensajeView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#compartirCBU()
	 */
	@Override
	public Respuesta<Void> compartirCBU() {
		estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_COMPARTIR_CBU,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#nuevaTransferencia()
	 */
	@Override
	public Respuesta<NuevaTransferenciaView> nuevaTransferencia() {	
		estadisticaManager.add(EstadisticasConstants.ACCESO_TRANSFERENCIA_BANCA_PRIVADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		NuevaTransferenciaView saldosAmbasBancas = new NuevaTransferenciaView();
		try {
			Respuesta<CuentasView> respestaCuentasRetail = obtenerSaldoCuentasRetail();
			saldosAmbasBancas.setCuentasRetail(respestaCuentasRetail.getRespuesta().getCuentas());
			saldosAmbasBancas
					.setCuentasBancaPrivada(cuentasBancaPrivadaBO.obtenerSaldosCuentas(sesionCliente.getCliente()));
		} catch (BusinessException e) {
			estadisticaManager.add(EstadisticasConstants.TRANSFERENCIA_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_TRANSFERNCIA_BANCA_PRIVADA);
		}
		estadisticaManager.add(EstadisticasConstants.TRANSFERENCIA_BANCA_PRIVADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		return respuestaFactory.crearRespuestaOk(NuevaTransferenciaView.class, saldosAmbasBancas);
	}

	/**
	 * Obtener saldo cuentas retail.
	 *
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	private Respuesta<CuentasView> obtenerSaldoCuentasRetail() throws BusinessException {
		Respuesta<CuentasView> respestaCuentasRetail = new Respuesta<CuentasView>();
		if(!sesionCliente.getCliente().getCuentas().isEmpty()){
			respestaCuentasRetail = cuentaManager.getCuentasSaldo();
			if (!EstadoRespuesta.OK.equals(respestaCuentasRetail.getEstadoRespuesta())
					&& !errorObteniendoCuentaFavorita(respestaCuentasRetail)) {
				throw new BusinessException();
			}
		}else{
			//SI EL CLIENTE NO TIENE CUENTAS RETAIL NO CONSULTO AL CNSCTASDOM Y DEVUELVO OK
			respestaCuentasRetail.setRespuesta(new CuentasView());
			respestaCuentasRetail.getRespuesta().setCuentas(new ArrayList<CuentasAdhesionDebitoView>());
			respestaCuentasRetail.setEstadoRespuesta(EstadoRespuesta.OK);
		}
		return respestaCuentasRetail;
	}

	/**
	 * Error obteniendo cuenta favorita.
	 *
	 * @param rta
	 *            the rta
	 * @return true, if successful
	 */
	private boolean errorObteniendoCuentaFavorita(Respuesta<CuentasView> rta) {
		if(rta.getItemsMensajeRespuesta() != null
				&& !rta.getItemsMensajeRespuesta().isEmpty()
				&& TipoError.ERROR_OBTENER_FAVORITO.toString().equals(rta.getItemsMensajeRespuesta().get(0).getTipoError())){
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#obtenerInterviniente(ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteinEntity)
	 */
	public Respuesta<IntervinienteView> obtenerInterviniente(IntervinienteinEntity entity) {
		Respuesta<IntervinienteView> respuesta = new Respuesta<IntervinienteView>();
		try {
			respuesta = cuentasBancaPrivadaBO.obtenerInterviniente(entity);
		} catch (DAOException e) {
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);

		} catch (SQLException e) {
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#verDetalleMovimiento()
	 */
	@Override
	public Respuesta<DetalleMovimientosView> verDetalleMovimiento() {
		
		DetalleMovimientosView detalleMovimientos = new DetalleMovimientosView();
		detalleMovimientos.setNumeroCuenta(sesionMovimientos.getFiltro().getNumeroCuenta());
		detalleMovimientos.setTipoCuenta(sesionMovimientos.getFiltro().getTipoCuenta().getDescripcion());
		estadisticaManager.add(EstadisticasConstants.VER_DETALLE_MOVIMIENTO_BANCA_PRIVADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		return respuestaFactory.crearRespuestaOk(detalleMovimientos);
	}

	
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#confirmarTransferencia(ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaInEntity)
	 */
	@Override
	public Respuesta<ConfirmarTransferenciaView> confirmarTransferencia(ConfirmarTransferenciaInEntity entity) {
	        Cliente cliente = sesionCliente.getCliente();
			Respuesta<ConfirmarTransferenciaView> respuestaBO = cuentasBancaPrivadaBO.confirmarTransferencia(entity, cliente);
			if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				return efectuarLogicaTransferenciaOk(respuestaBO, entity);
			}
			if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
				ConfirmarTransferenciaView view = createRetornoView(respuestaBO.getRespuesta());
				return respuestaFactory.crearRespuestaWarning(ConfirmarTransferenciaView.class, view,
						respuestaBO.getItemsMensajeRespuesta());	
			}
			return efectuarLogicaError(respuestaBO, entity);		
	}
		
	
	/**
	 * Efectuar logica error.
	 *
	 * @param respuestaBO
	 *            the respuesta BO
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 */
	private Respuesta<ConfirmarTransferenciaView> efectuarLogicaError(
			Respuesta<ConfirmarTransferenciaView> respuestaBO, ConfirmarTransferenciaInEntity entity) {
		String signoMoneda="u$s";
		if("ARS".equals(entity.getDivisa())){
			signoMoneda="$";
		}
		
		String[] partesCuentaSecundaria = entity.getNroCuentaDestino().split("-");
		String numeroCuentaDestino = partesCuentaSecundaria[1];
		
		Estadistica estadistica = new Estadistica(EstadisticasConstants.CODIGO_FINALIZAR_TRANSFERENCIA_BANCA_PRIVADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		estadistica.setImporte(entity.getImporte());
		estadistica.setMoneda(signoMoneda);		
		estadistica.setNroCtaDestino(numeroCuentaDestino.replace("/", ""));
		estadisticaManager.add(estadistica, sesionParametros.getRegistroSession(), sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaError(ConfirmarTransferenciaView.class,
				respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(),
				respuestaBO.getItemsMensajeRespuesta().get(0).getTag());
	}
	
	
	/**
	 * Efectuar logica transferencia ok.
	 *
	 * @param respuestaBO
	 *            the respuesta BO
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 */
	private Respuesta<ConfirmarTransferenciaView> efectuarLogicaTransferenciaOk(Respuesta<ConfirmarTransferenciaView> respuestaBO, ConfirmarTransferenciaInEntity entity) {	
		String signoMoneda="u$s";
		if("ARS".equals(entity.getDivisa())){
			signoMoneda="$";
		}	
		String[] partesCuentaSecundaria = entity.getNroCuentaDestino().split("-");
		String numeroCuentaDestino = partesCuentaSecundaria[1];
		ConfirmarTransferenciaView view = createRetornoView(respuestaBO.getRespuesta());
		Estadistica estadistica = new Estadistica(EstadisticasConstants.CODIGO_FINALIZAR_TRANSFERENCIA_BANCA_PRIVADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		estadistica.setImporte(entity.getImporte());
		estadistica.setMoneda(signoMoneda);		
		estadistica.setNroCtaDestino(numeroCuentaDestino.replace("/", ""));
		estadisticaManager.add(estadistica, sesionParametros.getRegistroSession(), sesionCliente.getCliente());		
		return respuestaFactory.crearRespuestaOk(ConfirmarTransferenciaView.class, view);
	}

	/**
	 * Creates the retorno view.
	 *
	 * @param dto
	 *            the dto
	 * @return the confirmar transferencia view
	 */
	private ConfirmarTransferenciaView createRetornoView(ConfirmarTransferenciaView dto) {
		ConfirmarTransferenciaView viewReturn = new ConfirmarTransferenciaView();
		if (dto != null) {
			viewReturn.setMensajeConfirmacion(dto.getMensajeConfirmacion());
			viewReturn.setComprobanteTransaccion(dto.getComprobanteTransaccion());
		}
		return viewReturn;
	}

	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#verComprobanteTransferencia(ar.com.santanderrio.obp.servicios.cuentas.web.view.DatosComprobanteTransferencia)
	 */
	@Override
	public Respuesta<ComprobanteTransferenciaView> verComprobanteTransferencia(DatosComprobanteTransferencia viewRequest) {
		ComprobanteTransferenciaView comprobanteTransferenciaView = new ComprobanteTransferenciaView();
		comprobanteTransferenciaView.setFechaYhora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));		
		Estadistica estadistica = new Estadistica(EstadisticasConstants.VER_COMPROBANTE_TRANSFERENCIA_BANCA_PRIVADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		estadistica.setImporte(viewRequest.getImporte());
		estadistica.setMoneda(viewRequest.getMoneda());
		estadistica.setNroCtaDestino(viewRequest.getCuentaDestino().replace("/", ""));
		estadisticaManager.add(estadistica, sesionParametros.getRegistroSession(), sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaOk(ComprobanteTransferenciaView.class, comprobanteTransferenciaView);		
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager#descargarComprobantePDFNuevaTransferencia(ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.ComprobanteNuevaTransferencia)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDFNuevaTransferencia(ComprobanteNuevaTransferencia viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			reporte.getRespuesta().setNombre(NOMBRE_PDF_NUEVA_TRANSFERENCIA);
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_TRANSFERENCIA_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_DESCARGA_COMPROBANTE_TRANSFERENCIA_BANCA_PRIVADA);
			estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_TRANSFERENCIA_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	@Override
	public Respuesta<ResumenesMensualesCuentaView> obtenerListaResumen(String nroCuenta) {

		String nroCuentaFormateado = nroCuenta.replaceAll("\\/", "");
		String[] nroCuentaPartes = nroCuentaFormateado.split("-");
		
        AbstractCuenta cuenta = sesionCliente.getCliente().getCuentaPrivadaSiContieneNumero(nroCuentaPartes[1]);
        Respuesta<List<ResumenMensualCuenta>> resumenesSesionList = sesionResumenCuenta.getResumenesByCuentaBP(cuenta);
        if (resumenesSesionList == null || EstadoRespuesta.ERROR.equals(resumenesSesionList.getEstadoRespuesta())) {
	        Respuesta<List<ResumenMensualCuenta>> resumenesMensualesDesdeServicio;
			try {
				resumenesMensualesDesdeServicio = cuentasBancaPrivadaBO.obtenerListaResumen(cuenta);
			} catch (BusinessException e) {
				estadisticaManager.add(EstadisticasConstants.VISUALIZAR_FECHAS_RESUMENES_BANCA_PRIVADA,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	            return respuestaFactory.crearRespuestaError(null, TipoError.CODIGO_ERROR_LISTA_RESUMENES_ERROR,
	                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			} catch (WSODException e) {
				estadisticaManager.add(EstadisticasConstants.VISUALIZAR_FECHAS_RESUMENES_BANCA_PRIVADA,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	            return respuestaFactory.crearRespuestaError(null, TipoError.CODIGO_ERROR_LISTA_RESUMENES_ERROR,
	                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			
	        Respuesta<ResumenesMensualesCuentaView> respResumenMensual = armarRespuestaResumenMesualCuentaView(resumenesMensualesDesdeServicio, cuenta);
     
	        if(EstadoRespuesta.OK.equals(respResumenMensual.getEstadoRespuesta())) {
	            sesionResumenCuenta.setResumenesMensualesDisponiblesByCuentaBP(resumenesMensualesDesdeServicio, cuenta);
	        }

	        return respResumenMensual;

        } else {
        	return armarRespuestaResumenMesualCuentaView(resumenesSesionList, cuenta);
        }
	}
	
    private Respuesta<ResumenesMensualesCuentaView> armarRespuestaResumenMesualCuentaView(
            Respuesta<List<ResumenMensualCuenta>> respuestaResumenesList, AbstractCuenta cuenta) {
        List<ResumenMesualCuentaView> resumenMesualCuentaViewList = new ArrayList<ResumenMesualCuentaView>();
        Respuesta<ResumenesMensualesCuentaView> respuestaView;

        if (EstadoRespuesta.OK.equals(respuestaResumenesList.getEstadoRespuesta())) {
        	if (respuestaResumenesList.getRespuesta().isEmpty()) {
        		estadisticaManager.add(EstadisticasConstants.VISUALIZAR_FECHAS_RESUMENES_BANCA_PRIVADA,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        		return respuestaFactory.crearRespuestaWarning("", TipoError.WARNING_NO_HAY_RESUMENES_DISPONIBLES,
        				CodigoMensajeConstantes.WARNING_NO_TENES_RESUMENES);
        	}
            List<ResumenMensualCuenta> resumenesList = respuestaResumenesList.getRespuesta();
            for (ResumenMensualCuenta resumenMensualCuenta : resumenesList) {
                ResumenMesualCuentaView resumenMesualCuentaView = new ResumenMesualCuentaView();
                resumenMesualCuentaView.setMes(formatearMes(resumenMensualCuenta.getFecha()));
                resumenMesualCuentaView.setAnio(formatearAnio(resumenMensualCuenta.getFecha()));
                resumenMesualCuentaView.setDia(formatearDia(resumenMensualCuenta.getFecha()));
                resumenMesualCuentaView.setVisto(resumenMensualCuenta.getVisto());
                resumenMesualCuentaView.setId(resumenMensualCuenta.getId());
                resumenMesualCuentaViewList.add(resumenMesualCuentaView);
            }
            ResumenesMensualesCuentaView resumenesMensualesCuentaView = new ResumenesMensualesCuentaView();
            String nroSucursalProducto = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
            String nroCuentaProducto = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
            String nroCuentaCompleto = nroSucursalProducto + "-" + nroCuentaProducto;
            resumenesMensualesCuentaView.setNumeroCuenta(nroCuentaCompleto);
        	
            Collections.sort(resumenMesualCuentaViewList);

            resumenesMensualesCuentaView.setResumenes(resumenMesualCuentaViewList);
            String tipoCuenta = TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcionConMoneda();
            resumenesMensualesCuentaView.setTipoCuenta(tipoCuenta);

			estadisticaManager.add(EstadisticasConstants.VISUALIZAR_FECHAS_RESUMENES_BANCA_PRIVADA,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            respuestaView = respuestaFactory.crearRespuestaOk(resumenesMensualesCuentaView);

        } else {
			estadisticaManager.add(EstadisticasConstants.VISUALIZAR_FECHAS_RESUMENES_BANCA_PRIVADA,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        	respuestaView = respuestaFactory.crearRespuestaError(null, TipoError.CODIGO_ERROR_LISTA_RESUMENES_ERROR,
        			CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }

        return respuestaView;
    }
    
    
    private String formatearDia(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd", new Locale("es", "ES"));
        return ISBANStringUtils.convertirPrimerLetraEnMayuscula(formateador.format(fecha));
    }

    private String formatearMes(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("MMMM", new Locale("es", "ES"));
        return ISBANStringUtils.convertirPrimerLetraEnMayuscula(formateador.format(fecha));
    }

    private String formatearAnio(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy");
        return formateador.format(fecha);
    }
	
}
