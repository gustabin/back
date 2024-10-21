/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.TitulosBOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ComptaVtaTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaComisionResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.CompraVentaTitulosValoresDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaDeTenenciaResponseDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaSuscripcionSaldoPDCDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaDeTenenciaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaDeTenenciaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class TituloOrdenVentaBOImpl.
 */
@Component
public class TituloOrdenVentaBOImpl extends InversionesAbstractBO implements TituloOrdenVentaBO {

	/** The ordenes titulos DAO. */
	@Autowired
	private OrdenesTitulosDAO ordenesTitulosDAO;
	
	@Autowired
	private TitulosBOImpl titulosBOImpl;
	
	/** The dato firmado tipo. */
	@Value("${INVERSIONES.FIRMA.DATO}")
	private String datoTitulos;

	/** The Constant PESOS. */
	private static final String PESOS = "Pesos";

	/** The Constant TIPO_OPERACION_VENTA. */
	private static final String TIPO_OPERACION_VENTA = "V";

	/** The Constant VEINTICUATRO_HS. */
	private static final String VEINTICUATRO_HS = "24 hs";

	/** The Constant CUARENTA_Y_OCHO_HS. */
	private static final String CUARENTA_Y_OCHO_HS = "48 hs";

	/** The Constant SETENTA_Y_DOS_HS. */
	private static final String SETENTA_Y_DOS_HS = "72 hs";

	/** The Constant SEGMENTO_BPRIV. */
	private static final String SEGMENTO_BPRIV = "BP";

	/** The Constant SEGMENTO. */
	private static final String SEGMENTO = "RTL";

	/** The Constant CERO. */
	private static final String CERO = "0";

	/** The constant CUENTA_CUSTODIA. */
	public static final String TIPO_CUENTA_OPERATIVA = "O";

	/** The Constant mjeTecnicoFueraHorario. */
	private static final String FUERA_DE_HORARIO = "SMC-00007";
	
	/** The Constant mjeTecnicoSinSaldo. */
	private static final String SIN_SALDO = "ADB-00001";
	
	private static final String CUENTA_SIN_OPERAR_MAS_180_DIAS = "ADB-00002";
	
	private static final String EXCEDE_LIMITE_DE_CANAL = "ADB-00003";
	
	private static final String CUENTA_NO_FIRMADA = "SMC-00001";
	
	private static final String CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA = "ch";
	
	private static final String SE_ENCUENTRA_FUERA_DEL_TUNEL = "SMC-00003";
	
	private static final String CUENTA_TITULOS_BLOQUEADA = "SMC-00004";
	
	private static final String TENENCIA_BLOQUEADA = "SMC-00005";
	
	private static final String CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES = "SMC-00006";
	
	private static final String SUPERA_TIEMPO_DE_ESPERA = "WEB-00001";
	
	private static final String NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS = "WEB-00002";

	private static final String ERROR_PROCESO_COM_7001 = "SMC-00061: Validacion comunicado 7001";

	private static final String OTROS = "WEB-00003";

	private static final int CODIGO_ERROR_PROCESO_COM_7001 = 61;

	private static final int TENENCIA_NO_DISPONIBLE_PARA_OPERAR = 53;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TituloOrdenVentaBOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.
	 * InversionesAbstractBO#getTipo()
	 */
	@Override
	protected String getTipo() {
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo.TituloOrdenVentaBO#consultarCuentasTitulosWebService(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.util.List, java.lang.Boolean)
	 */
	@Override
	public ConsultaDeTenenciaResponseDTO consultarCuentasTitulosWebService(Cliente cliente,
			List<Cuenta> listaCuentasTitulos, Boolean esBancaPrivada) throws BusinessException {

		String listaCuentas = StringUtils.EMPTY;
		ConsultaDeTenenciaResponseDTO respuestaDTO = new ConsultaDeTenenciaResponseDTO();

		for (Cuenta cuentaTitulo : listaCuentasTitulos) {
			listaCuentas = new StringBuilder().append(listaCuentas).append(cuentaTitulo.getNroCuentaProducto())
					.append(",").toString();
		}
		if (StringUtils.isNotEmpty(listaCuentas)) {
			listaCuentas = listaCuentas.substring(0, listaCuentas.length() - 1);
		} else {
			throw new BusinessException();
		}

		try {
			ConsultaDeTenenciaRequestEntity consultaDeTenenciaEntity = crearRequestConsultaDeTenenciaEntity(cliente,
					esBancaPrivada);
			consultaDeTenenciaEntity.getDatos().setListaCuentasTitulos(listaCuentas);

			ConsultaDeTenenciaResponse respuestaConsultaDeTenencia = ordenesTitulosDAO
					.consultaDeTenencia(consultaDeTenenciaEntity);

			respuestaDTO.setDatos(respuestaConsultaDeTenencia.getDatos());

		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new BusinessException(e);
		}

		return respuestaDTO;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo.TituloOrdenVentaBO#consultarTitulares(java.util.List)
	 */
	@Override
	public List<String> consultarTitulares(List<Interviniente> listaIntervinientes) throws BusinessException {
		String log = "Consulto los titulares de una cuenta titulo";
		LOGGER.info(log);
		List<String> titularesCuentaTitulo = new ArrayList<String>();
		
		for (Interviniente interviniente : listaIntervinientes) {
			String apellido = interviniente.getApellido();
			
			String nombre = ISBANStringUtils.formateoStringPrimeraLetraMayuscula(interviniente.getNombre());
			nombre = nombre.trim();
			if(!apellido.equals("")){
				apellido = ISBANStringUtils.formateoStringPrimeraLetraMayuscula(interviniente.getApellido());
				apellido = apellido.trim();
			}
			titularesCuentaTitulo.add(apellido + ", " + nombre);
		}
		return titularesCuentaTitulo;
	}

	/**
	 * Crear request consulta de tenencia entity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return the consulta de tenencia request entity
	 */
	private ConsultaDeTenenciaRequestEntity crearRequestConsultaDeTenenciaEntity(Cliente cliente,
			Boolean esBancaPrivada) {
		ConsultaDeTenenciaRequestEntity request = new ConsultaDeTenenciaRequestEntity();
		setearCanalesRequestMWCanales(request, esBancaPrivada);
		setearDatosRequestMWCanales(request.getDatos(), cliente, esBancaPrivada);
		request.setDatosFirmados(0);
		request.getDatos().setSegmento(esBancaPrivada ? SEGMENTO_BPRIV : SEGMENTO);
		request.getDatos().setEstadoCuentaTitulo("0");
		request.getDatos().setEstadoTenencia("0");
		request.getDatos().setEstadoEspecie("0");
		return request;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo.TituloOrdenVentaBO#ejecutarMetodoCompraVentaTitulosValores(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView, java.lang.String)
	 */
	@Override
	public CompraVentaTitulosValoresDTO ejecutarMetodoCompraVentaTitulosValores(Cliente cliente,
			ConfirmacionVentaTitulosInView confirmacionInView, String accion) throws BusinessException {

		CompraVentaTitulosValoresDTO compraVentaTitulosValoresDTO = new CompraVentaTitulosValoresDTO();
		ComptaVtaTitulosRequestEntity entity = obtenerComptaVtaTitulosRequestEntity(cliente, confirmacionInView,
				accion);
		
		try {
			CompraVtaTitulosResponse response = ordenesTitulosDAO.compraVtaTitulos(entity);
			if (response.getCodigo() != 0) {
				compraVentaTitulosValoresDTO.setTipoError(obtenerTipoError(response));
			} else {
				
				String ctaTitulo = entity.getDatos().getCuentaTitulo();
				String codigoEspecie = entity.getDatos().getEspecieCodigo();
				String monedaOperacion = entity.getDatos().getMonedaOperacion();
				
				ConsultaComisionResponse comision = titulosBOImpl.setearDatosComision(cliente, ctaTitulo, codigoEspecie, monedaOperacion, TIPO_OPERACION_VENTA);
				
				response.getDatos().setDatosConsultaComisionResponsee(comision.getDatos());
				
				response.getDatos().getDatosConsultaComisionResponse().setBonificacion
				(ISBANStringUtils.formatearConComaYDosDecimales(comision.getDatos().getBonificacion()));
				
				response.getDatos().getDatosConsultaComisionResponse().setComision
				(ISBANStringUtils.formatearConComaYDosDecimales(comision.getDatos().getComision()));
				
				response.getDatos().getDatosConsultaComisionResponse().setComisionOriginal
				(ISBANStringUtils.formatearConComaYDosDecimales(comision.getDatos().getComisionOriginal()));

				
				
				compraVentaTitulosValoresDTO.setDatos(response.getDatos());
			}
		} catch (DAOException e) {
			throw new BusinessException(e);
		}

		return compraVentaTitulosValoresDTO;
	}

	/**
	 * Obtener tipo error.
	 *
	 * @param response
	 *            the response
	 * @return the tipo error
	 */
	private TipoError obtenerTipoError(CompraVtaTitulosResponse response) {
		if(TENENCIA_NO_DISPONIBLE_PARA_OPERAR == response.getCodigo()) {
			return TipoError.TENENCIA_NO_DISPONIBLE_PARA_OPERAR;
		}else if (StringUtils.startsWith(response.getMensajeTecnico(), SIN_SALDO)) {
			return TipoError.ERROR_SALDO_INSUFICIENTE;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), FUERA_DE_HORARIO)) {
			return TipoError.FUERADEHORARIO;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), CUENTA_SIN_OPERAR_MAS_180_DIAS)) {
			return TipoError.CUENTA_SIN_OPERAR_MAS_180_DIAS;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), EXCEDE_LIMITE_DE_CANAL)) {
			return TipoError.EXCEDE_LIMITE_DE_CANAL;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), CUENTA_NO_FIRMADA)) {
			return TipoError.CUENTA_NO_FIRMADA;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA)) {
			return TipoError.CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), SE_ENCUENTRA_FUERA_DEL_TUNEL)) {
			return TipoError.SE_ENCUENTRA_FUERA_DEL_TUNEL;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), CUENTA_TITULOS_BLOQUEADA)) {
			return TipoError.CUENTA_TITULOS_BLOQUEADA;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), TENENCIA_BLOQUEADA)) {
			return TipoError.TENENCIA_BLOQUEADA;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES)) {
			return TipoError.CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), SUPERA_TIEMPO_DE_ESPERA)) {
			return TipoError.SUPERA_TIEMPO_DE_ESPERA;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS)) {
			return TipoError.NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), ERROR_PROCESO_COM_7001) && response.getCodigo() == CODIGO_ERROR_PROCESO_COM_7001) {
			return TipoError.ERROR_PROCESO_ORDEN_VENTA;
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), OTROS)) {
			return TipoError.OTROS_COMPRA_VENTA_CUENTA_TITULOS;
		}		
		return TipoError.ERROR_GENERICO;
	}

	/**
	 * Obtener compta vta titulos request entity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param confirmacionInView
	 *            the confirmacion in view
	 * @param accion
	 *            the accion
	 * @return the compta vta titulos request entity
	 */
	private ComptaVtaTitulosRequestEntity obtenerComptaVtaTitulosRequestEntity(Cliente cliente,
			ConfirmacionVentaTitulosInView confirmacionInView, String accion) {

		ComptaVtaTitulosRequestEntity request = new ComptaVtaTitulosRequestEntity();
		setearCanalesRequestMWCanales(request, confirmacionInView.getEsBancaPrivada());
		setearDatosRequestMWCanales(request.getDatos(), cliente, confirmacionInView.getEsBancaPrivada());
		request.setDatosFirmados(0);
		request.getDatos().setTipoAccion(accion);
		request.getDatos().setIdCumplimiento(confirmacionInView.getIdCumplimiento());
		request.getDatos().setTipoEspecie(confirmacionInView.getTipoEspecie());
		request.getDatos().setSucursalCuenta(confirmacionInView.getSucursalCuenta());
		request.getDatos().setCuentaTitulo(confirmacionInView.getCuentaTitulo().replaceAll("\\/", ""));
		request.getDatos().setFechaLiquidacion(confirmacionInView.getFechaLiquidacion());
		if (confirmacionInView.getEsBancaPrivada()) {
			request.getDatos().setTipoCtaOper(obtenerTipoCuentaOperativaBPriv(confirmacionInView.getMonedaOperacion()));
		} else {
			request.getDatos().setTipoCtaOper(obtenerTipoCuentaOperativa(confirmacionInView.getTipoCuenta(),
					confirmacionInView.getMonedaOperacion()));
		}
		request.getDatos()
				.setMonedaOperacion(PESOS.equals(confirmacionInView.getMonedaOperacion()) ? DivisaEnum.PESO.getCodigo()
						: DivisaEnum.DOLAR.getCodigo());
		request.getDatos().setNumeroCuenta(formatearNroCuenta(confirmacionInView.getNumeroCuenta()));
		request.getDatos().setTipoOperacion(TIPO_OPERACION_VENTA);
		request.getDatos().setCantidadTitulo(confirmacionInView.getCantidadTitulo());
		request.getDatos().setEspecieCodigo(confirmacionInView.getEspecieCodigo().trim());
		if (confirmacionInView.getCotizacionLimite() == null || confirmacionInView.getCotizacionLimite().isEmpty()) {
			request.getDatos().setCotizacionLimite("0");
		} else {
			request.getDatos().setCotizacionLimite(confirmacionInView.getCotizacionLimite());
		}
		request.getDatos().setCotizacion(obtenerCotizacion(confirmacionInView.getCotizacion()));
		request.getDatos().setPlazo(setearPlazo(confirmacionInView.getPlazo()));
		request.getDatos().setOperadorAlta("HB");
		request.getDatos().setSegmento(confirmacionInView.getEsBancaPrivada() ? SEGMENTO_BPRIV : SEGMENTO);
		request.getDatos().setFechaPrecioRef(confirmacionInView.getFechaReferencia());
		request.getDatos().setHoraPrecioRef(confirmacionInView.getHoraReferencia());
		return request;

	}

	/**
	 * Obtener tipo cuenta desde abreviatura.
	 *
	 * @param tipoCuentaDebito
	 *            the tipo cuenta debito
	 * @param moneda
	 *            the moneda
	 * @return the string
	 */
	private String obtenerTipoCuentaOperativa(String tipoCuentaDebito, String moneda) {
		if (TipoCuenta.CUENTA_UNICA.getAbreviatura().equals(tipoCuentaDebito)) {
			if (PESOS.equals(moneda)) {
				return CERO + TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
			} else {
				return TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
			}
		}
		if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo().toString();
		}
		if (TipoCuenta.CAJA_AHORRO_DOLARES.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CAJA_AHORRO_DOLARES.getCodigo().toString();
		}
		if (TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString();
		}
		if (TIPO_CUENTA_OPERATIVA.equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_UNICA_PESOS.getAbreviatura().equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_UNICA_DOLARES.getAbreviatura().equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
		}
		return null;
	}

	/**
	 * Obtener tipo cuenta operativa B priv.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the string
	 */
	private String obtenerTipoCuentaOperativaBPriv(String moneda) {
		if (PESOS.equals(moneda)) {
			return "0" + TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
		} else {
			return TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
		}
	}

	/**
	 * Obtener cotizacion.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the string
	 */
	private String obtenerCotizacion(String cotizacion) {
		if (cotizacion.equals("Sin cotización")) {
			return null;
		} else {
			return cotizacion.replaceAll("\\$ ", "").replaceAll("\\.", "").replaceAll(",", ".");
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo.TituloOrdenVentaBO#consultarDatosSuscripcionSaldoPDC(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView)
	 */
	@Override
	public ConsultaSuscripcionSaldoPDCDTO consultarDatosSuscripcionSaldoPDC(Cliente cliente,
			ConfirmacionVentaTitulosInView confirmacionInView) {

		ConsultaSuscripcionSaldoPDCDTO consultaSuscripcion = new ConsultaSuscripcionSaldoPDCDTO();

		ConsultaSuscripcionSaldoPDCRequest entity = obtenerDatosParaConsultaSuscripcion(cliente, confirmacionInView);

		try {
			ConsultaSuscripcionSaldoPDCResponse response = ordenesTitulosDAO.consultaSuscripcionSaldoPDC(entity);
			consultaSuscripcion.setDatos(response.getDatos());
		} catch (DAOException e) {
			consultaSuscripcion.setFalloServicio(true);
		}
		return consultaSuscripcion;
	}

	/**
	 * Obtener datos para consulta suscripcion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param confirmacionInView
	 *            the confirmacion in view
	 * @return the consulta suscripcion saldo PDC request
	 */
	private ConsultaSuscripcionSaldoPDCRequest obtenerDatosParaConsultaSuscripcion(Cliente cliente,
			ConfirmacionVentaTitulosInView confirmacionInView) {

		ConsultaSuscripcionSaldoPDCRequest request = new ConsultaSuscripcionSaldoPDCRequest();
		setearCanalesRequestMWCanales(request, confirmacionInView.getEsBancaPrivada());
		setearDatosRequestMWCanales(request.getDatos(), cliente, confirmacionInView.getEsBancaPrivada());
		request.setDatosFirmados(0);
		request.getDatos().setFechaLiquidacion(confirmacionInView.getFechaLiquidacion());
		request.getDatos().setCuentaTitulos(confirmacionInView.getCuentaTitulo().replaceAll("\\/", ""));
		request.getDatos().setCodigoMoneda(PESOS.equals(confirmacionInView.getMonedaOperacion()) ? "ARS" : "USD");
		request.getDatos().setTipoCtaOper(confirmacionInView.getTipoCuenta());
		request.getDatos().setSucCtaOper(confirmacionInView.getSucursalCuenta());
		request.getDatos().setNumeroCuentaOper(formatearNroCuenta(confirmacionInView.getNumeroCuenta()));
		request.getDatos().setSegmento(confirmacionInView.getEsBancaPrivada() ? SEGMENTO_BPRIV : SEGMENTO);
		request.getDatos().setProductoInversion(confirmacionInView.getTipoEspecie());
		return request;
	}

	/**
	 * Setear plazo.
	 *
	 * @param plazo
	 *            the plazo
	 * @return the string
	 */
	private String setearPlazo(String plazo) {

		String plazoAEnviar;

		if (VEINTICUATRO_HS.equals(plazo)) {
			plazoAEnviar = "24";
		} else if (CUARENTA_Y_OCHO_HS.equals(plazo)) {
			plazoAEnviar = "48";
		} else if (SETENTA_Y_DOS_HS.equals(plazo)) {
			plazoAEnviar = "72";
		} else {
			plazoAEnviar = "0";
		}

		return plazoAEnviar;
	}

	/**
	 * Formatear nro cuenta.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the string
	 */
	private String formatearNroCuenta(String numeroCuenta) {

		String cuenta = numeroCuenta.substring(4, numeroCuenta.length());
		return cuenta.replaceAll("\\/", "");

	}

}