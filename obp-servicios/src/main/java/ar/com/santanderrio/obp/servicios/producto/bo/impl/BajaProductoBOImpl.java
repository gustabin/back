/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.bo.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf324931821440052060NillableString;
import ar.com.santanderrio.obp.generated.webservices.productos.InfoRequeridaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.producto.bo.BajaProductoBO;
import ar.com.santanderrio.obp.servicios.producto.dao.SolicitudProductoDAO;
import ar.com.santanderrio.obp.servicios.producto.dto.BajaProductoDTO;
import ar.com.santanderrio.obp.servicios.producto.dto.ObtenerBajaProductoDTO;
import ar.com.santanderrio.obp.servicios.producto.dto.ProductoDTO;
import ar.com.santanderrio.obp.servicios.producto.view.ComprobanteBajaProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.TipoOperacionBajaProductoEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class BajaProductoBOImpl.
 */
@Component
public class BajaProductoBOImpl implements BajaProductoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BajaProductoBOImpl.class);

	/** The Constant COD_PAQ_BLACK. */
	private static final String COD_PAQ_BLACK = "Servicio de Cuenta Black";

	/** The Constant COD_PAQ_PLATINUM. */
	private static final String COD_PAQ_PLATINUM = "Servicio de Cuenta Platinum";

	/** The Constant COD_PAQ_PLATINUM. */
	private static final String COD_PAQ_TC_INTERNACIONAL = "Servicio de Cuenta TC Internacional";

	/** The Constant COD_PAQ_GOLD. */
	private static final String COD_PAQ_GOLD = "Servicio de Cuenta Infinity Gold";

	/** The Constant COD_PAQ_INFINITY. */
	private static final String COD_PAQ_INFINITY = "Servicio de Cuenta Infinity";

	/** The Constant COD_PAQ_SC3. */
	private static final String COD_PAQ_SC3 = "Servicio de Cuenta SuperCuenta 3";

	/** The Constant COD_PAQ_SC2. */
	private static final String COD_PAQ_SC2 = "Servicio de Cuenta SuperCuenta 2";

	/** The Constant COD_PAQ_SC. */
	private static final String COD_PAQ_SC = "Servicio de Cuenta SuperCuenta";

	/** The Constant COD_PAQ_BP. */
	private static final String COD_PAQ_BP = "Cuenta de inversión";

	/** The Constant ID_CUENTA_PAQUETE. */
	private static final String ID_CUENTA_PAQUETE = "PAQUETE";

	/** The Constant ID_CUENTA_FP. */
	private static final String ID_CUENTA_FP = "CFP";

	/** The Constant DESC_BAJA_CA. */
	private static final String DESC_BAJA_CA = "Generar alta de caja de ahorros continuadora de cuenta:";

	/** The Constant COMENTARIO_PROD_AMEX. */
	private static final String COMENTARIO_PROD_AMEX = "Tarjeta AMEX";

	/** The Constant COMENTARIO_PROD_MASTERCARD. */
	private static final String COMENTARIO_PROD_MASTERCARD = "Tarjeta MASTER";

	/** The Constant COMENTARIO_PROD_MONEDERO. */
	private static final String COMENTARIO_PROD_MONEDERO = "MONEDERO";

	/** The Constant COMENTARIO_PROD_RECARGABLE. */
	private static final String COMENTARIO_PROD_RECARGABLE = "VISA RECARGABLE";

	/** The Constant COMENTARIO_PROD_VISA. */
	private static final String COMENTARIO_PROD_VISA = "Tarjeta VISA";

	/** The Constant SEPARADOR_COMENTARIO. */
	private static final String SEPARADOR_COMENTARIO = ";";

	/** The Constant COD_PROD_FUERA_PAQ. */
	private static final String COD_PROD_FUERA_PAQ = "000000000000000";

	private static final String COD_PAQ_CC = "Servicio de Cuenta con Cuenta Corriente";

	/** The Constant COD_PAQ_TC_BLACK. */
	private static final String COD_PAQ_TC_BLACK = "Servicio de Cuenta TC Black";

	/** The Constant COD_PAQ_TC_PLATINUM. */
	private static final String COD_PAQ_TC_PLATINUM = "Servicio de Cuenta TC Platinum";

	/** The Constant COD_PAQ_TC_PLATINUM_SELECT. */
	private static final String COD_PAQ_TC_PLATINUM_SELECT = "Servicio de Cuenta TC Platinum Sel";

	/** The Constant COD_PAQ_TC_GOLD. */
	private static final String COD_PAQ_TC_GOLD = "Servicio de Cuenta TC Gold";

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The solicitud producto DAO. */
	@Autowired
	private SolicitudProductoDAO solicitudProductoDAO;

	/** The mensaje dao. */
	@Autowired
	private MensajeDAO mensajeDao;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The baja CA codigo. */
	@Value("${BAJA.CA.CODIGO}")
	private String bajaCACodigo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.producto.bo.BajaProductoBO#
	 * solicitarBajaProducto(ar.com.santanderrio.obp.servicios.producto.dto.
	 * BajaProductoDTO)
	 */
	public Respuesta<BajaProductoDTO> solicitarBajaProducto(BajaProductoDTO bajaProductoDTO) {

		Cuenta cuenta = obtenerCuenta(bajaProductoDTO);

		if (cuenta == null) {
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_ERROR);
		}

		Integer codAsociacion = obtenerCodAsociacion(bajaProductoDTO);

		ArrayOf158770343432493182NillableInfoRequeridaWS infoReq = new ArrayOf158770343432493182NillableInfoRequeridaWS();
		if (Boolean.TRUE.equals(bajaProductoDTO.getBajaCA()) && isCodigoAsociacionValido(codAsociacion)) {
			InfoRequeridaWS infoBajaCA = new InfoRequeridaWS();
			infoBajaCA.setCodigo(new Integer(bajaCACodigo)); // 3461
			infoBajaCA.setDescripcion(DESC_BAJA_CA);
			ArrayOf324931821440052060NillableString valor = new ArrayOf324931821440052060NillableString();
			valor.getString().add(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
			infoBajaCA.setValor(valor);
			infoReq.getInfoRequeridaWS().add(infoBajaCA);
		}

		try {
			ResultadoAltaWS resultadoWS = solicitudProductoDAO.altaGestion(codAsociacion, "F",
					Integer.valueOf(sesionCliente.getCliente().getNup()), "NEIT", "HBAN0002", 35,
					obtenerComentarioAltaGestion(bajaProductoDTO, cuenta), infoReq);

			if (resultadoWS.getCodRetorno() != 0) {
				LOGGER.error("Error en AltaGestion - CodRetorno:" + resultadoWS.getCodRetorno() + " -"
						+ resultadoWS.getDescRetorno());
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_ERROR);
			}

			String mensaje = "";

			if (TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA.equals(bajaProductoDTO.getTipoOperacion())
					&& ID_CUENTA_PAQUETE.equals(bajaProductoDTO.getTipoProducto())) {
				if (Boolean.TRUE.equals(bajaProductoDTO.getBajaCA())) {
					mensaje = MessageFormat.format(mensajeDao
							.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_CAJA_AHORRO_OK)
							.getMensaje(), bajaProductoDTO.getDescripcion());
				} else {
					mensaje = MessageFormat.format(
							mensajeDao
									.obtenerMensajePorCodigo(
											CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_SIN_CAJA_AHORRO_OK)
									.getMensaje(),
							bajaProductoDTO.getDescripcion(), cuenta.obtenerNroCuentaFormateado());
				}
			} else {
				mensaje = BooleanUtils.isTrue(bajaProductoDTO.getRecargable())
						? MessageFormat.format(
								mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.BAJA_TARJETA_RECARGABLE)
										.getMensaje(),
								bajaProductoDTO.getDescripcion())
						: MessageFormat.format(
								mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_OK)
										.getMensaje(),
								bajaProductoDTO.getDescripcion());
			}

			if (bajaProductoDTO.getArrepentimiento()) {
				mensaje = mensaje.replaceAll("baja", "baja por arrepentimiento");
			}
			bajaProductoDTO.setMensaje(mensaje);
			bajaProductoDTO.setComprobante(String.valueOf(resultadoWS.getIdeGestionNro()));
		} catch (NumberFormatException e) {
			LOGGER.debug("no se pudo dar de alta la baja de producto", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_ERROR);
		} catch (DAOException e) {
			LOGGER.debug("no se pudo dar de alta la baja de producto", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_ERROR);
		}

		return respuestaFactory.crearRespuestaOk(BajaProductoDTO.class, bajaProductoDTO);
	}

	/**
	 * Obtener comentario alta gestion.
	 *
	 * @param bajaProductoDTO
	 *            the baja producto DTO
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	private String obtenerComentarioAltaGestion(BajaProductoDTO bajaProductoDTO, Cuenta cuenta) {
		String comentario = StringUtils.EMPTY;
		if (TipoOperacionBajaProductoEnum.BAJA_TARJETA.equals(bajaProductoDTO.getTipoOperacion())) {
			comentario = getProductoComentario(cuenta) + SEPARADOR_COMENTARIO + Long.valueOf(cuenta.getNroCuentaProducto())
					+ SEPARADOR_COMENTARIO + Long.valueOf(cuenta.getNroTarjetaCredito());			
		} else if (TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA.equals(bajaProductoDTO.getTipoOperacion())) {

			if (ID_CUENTA_FP.equals(bajaProductoDTO.getTipoProducto())) {
				comentario = bajaProductoDTO.getDescripcion();
			} else {
			// Paquete de cuentas
			comentario = obtenerComentarioPaqueteCuenta(bajaProductoDTO, cuenta);
			}
		} else if (TipoOperacionBajaProductoEnum.OTROS_MEDIO_PAGO.equals(bajaProductoDTO.getTipoOperacion())) {
			comentario = getProductoComentario(cuenta) + SEPARADOR_COMENTARIO + Long.valueOf(cuenta.getNroCuentaProducto())
					+ SEPARADOR_COMENTARIO + Long.valueOf(cuenta.getNroTarjetaCredito());
		}

		if (bajaProductoDTO.getArrepentimiento()) {
			comentario = comentario + SEPARADOR_COMENTARIO + "ARREPENTIMIENTO";
		}
		
		return comentario;
	}

	/**
	 * Obtener comentario paquete cuenta.
	 *
	 * @param bajaProductoDTO
	 *            the baja producto DTO
	 * @param cuentaBaja
	 *            the cuenta baja
	 * @return the string
	 */
	private String obtenerComentarioPaqueteCuenta(BajaProductoDTO bajaProductoDTO, Cuenta cuentaBaja) {
		StringBuilder comentario = new StringBuilder();
		if (TipoCuenta.TITULOS.equals(cuentaBaja.getTipoCuentaEnum())) {
			comentario.append(bajaProductoDTO.getDescripcion());
		} else {
			List<Cuenta> listTrjVisaPaq = new ArrayList<Cuenta>();
			List<Cuenta> listTrjAmexPaq = new ArrayList<Cuenta>();
			List<Cuenta> listTrjMasterPaq = new ArrayList<Cuenta>();
			List<Cuenta> listAtitPaq = new ArrayList<Cuenta>();
			List<Cuenta> cuentasPaqueteBaja = filtroProdPaquetes(cuentaBaja);
			for (Cuenta ctaBaja : cuentasPaqueteBaja) {
				if (ctaBaja.esTarjetaVisa()) {
					listTrjVisaPaq.add(ctaBaja);
				} else if (ctaBaja.esTarjetaAmex()) {
					listTrjAmexPaq.add(ctaBaja);
				} else if (ctaBaja.esTajetaMaster()) {
					listTrjMasterPaq.add(ctaBaja);
				} else if (TipoCuenta.TITULOS.equals(ctaBaja.getTipoCuentaEnum())) {
					listAtitPaq.add(ctaBaja);
				}
			}
			comentario.append(bajaProductoDTO.getDescripcion());
			// Ordeno cada uno de los productos como VISA - AMEX - MASTER -
			// CTATIT dejando "0" de no existir
			// VISA
			addComentario(listTrjVisaPaq, comentario, "VISA");
			// AMEX
			addComentario(listTrjAmexPaq, comentario, "AMEX");
			// MASTER
			addComentario(listTrjMasterPaq, comentario, "MASTER");
			// CUENTA TITULO
			addComentario(listAtitPaq, comentario, "TITULO");
		}
		return comentario.toString();
	}

	/**
	 * addComentario.
	 *
	 * @param lstTrj
	 *            Lista de tarjetas a procesar
	 * @param comentario
	 *            Comentario a ensamblar
	 * @param nombreProd
	 *            Producto que se esta dando de baja
	 */
	private void addComentario(List<Cuenta> lstTrj, StringBuilder comentario, String nombreProd) {
		if (lstTrj.isEmpty()) {
			comentario.append(SEPARADOR_COMENTARIO).append(nombreProd).append(SEPARADOR_COMENTARIO).append("0")
					.append(SEPARADOR_COMENTARIO).append("0");
		} else {
			for (Cuenta trj : lstTrj) {
				comentario.append(SEPARADOR_COMENTARIO).append(nombreProd).append(SEPARADOR_COMENTARIO)
						.append(ISBANStringUtils.eliminarCeros(trj.getNroCuentaProducto())).append(SEPARADOR_COMENTARIO)
						.append(ISBANStringUtils.eliminarCeros(trj.getNroTarjetaCredito()));
			}
		}
	}

	/**
	 * getProductoComentario.
	 *
	 * @param cuenta
	 *            Cuenta asociada al producto a procesar
	 * @return Descripcion del producto para el comentario a enviar al servicio
	 *         altaGestion
	 */
	private String getProductoComentario(Cuenta cuenta) {
		TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(cuenta.getTipoCuenta());
		if (TipoCuenta.VISA.equals(tipoCuenta)) {
			return COMENTARIO_PROD_VISA;
		} else if (TipoCuenta.AMEX.equals(tipoCuenta)) {
			return COMENTARIO_PROD_AMEX;
		} else if (TipoCuenta.MASTERCARD.equals(tipoCuenta)) {
			return COMENTARIO_PROD_MASTERCARD;
		} else if (TipoCuenta.VISA_RECARGABLE.equals(tipoCuenta)) {
			return COMENTARIO_PROD_RECARGABLE;
		} else if (TipoCuenta.TARJETA_MONEDERO.equals(tipoCuenta)) {
			return COMENTARIO_PROD_MONEDERO;
		}
		return null;
	}

	/**
	 * Obtener cod asociacion.
	 *
	 * @param bajaProductoDTO
	 *            the baja producto DTO
	 * @return the integer
	 */
	private Integer obtenerCodAsociacion(BajaProductoDTO bajaProductoDTO) {
		if (TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA.equals(bajaProductoDTO.getTipoOperacion())) {
			return codAsociacionPaqueteCuenta(bajaProductoDTO);
		}
		if (TipoOperacionBajaProductoEnum.BAJA_TARJETA.equals(bajaProductoDTO.getTipoOperacion())) {
			return codAsociacionTarjeta(bajaProductoDTO);
		}
		if (TipoOperacionBajaProductoEnum.OTROS_MEDIO_PAGO.equals(bajaProductoDTO.getTipoOperacion())) {
			return 77;
		}

		return null;
	}

	/**
	 * Cod asociacion paquete cuenta.
	 *
	 * @param bajaProductoDTO
	 *            the baja producto DTO
	 * @return the integer
	 */
	private Integer codAsociacionPaqueteCuenta(BajaProductoDTO bajaProductoDTO) {

		if (sesionCliente.getCliente().getSegmento().isSelect()) {
			return obtenerCodAsociacionPaqueteCuentaSelect(bajaProductoDTO);
		} else {
			return obtenerCodAsociacionPaqueteCuentaNoSelect(bajaProductoDTO);

		}
	}

	/**
	 * Obtener cod asociacion paquete cuenta no select.
	 *
	 * @param bajaProductoDTO
	 *            the baja producto DTO
	 * @return the integer
	 */
	private Integer obtenerCodAsociacionPaqueteCuentaNoSelect(BajaProductoDTO bajaProductoDTO) {
		Cuenta cuenta = obtenerCuenta(bajaProductoDTO);
		if (bajaProductoDTO.getDescripcion().indexOf(COD_PAQ_BP) > -1) {
			return 75;
		} else if (TipoCuenta.TITULOS.equals(cuenta.getTipoCuentaEnum())) {
			return 71;
		} else {
			if (isSucBancaPrivada(cuenta.getNroSucursal())) {
				return 75;
			} else {
				return 70;
			}
		}

	}

	/**
	 * Obtener cod asociacion paquete cuenta select.
	 *
	 * @param bajaProductoDTO
	 *            the baja producto DTO
	 * @return the integer
	 */
	private Integer obtenerCodAsociacionPaqueteCuentaSelect(BajaProductoDTO bajaProductoDTO) {
		Cuenta cuenta = obtenerCuenta(bajaProductoDTO);
		if (bajaProductoDTO.getDescripcion().indexOf(COD_PAQ_BP) > -1) {
			return 75;
		}

		if (isSucBancaPrivada(cuenta.getSucursalPaquete())) {
			return 75;
		} else {
			return 74;
		}
	}

	/**
	 * Checks if is suc banca privada.
	 *
	 * @param nroSucursal
	 *            the nro sucursal
	 * @return true, if is suc banca privada
	 */
	static public boolean isSucBancaPrivada(String nroSucursal) {
		int sucursal = Integer.parseInt(nroSucursal);
		return sucursal >= 250 && sucursal <= 259;
	}

	/**
	 * Checks if is asociacion valido.
	 *
	 * @param codAsociacion
	 *            the cod asociacion
	 * @return true, if is asociacion valido
	 */
	public static boolean isCodigoAsociacionValido(Integer codAsociacion) {
		return (codAsociacion == 70 || codAsociacion == 74 || codAsociacion == 75);
	}

	/**
	 * Cod asociacion tarjeta.
	 *
	 * @param bajaProductoDTO
	 *            the baja producto DTO
	 * @return the integer
	 */
	private Integer codAsociacionTarjeta(BajaProductoDTO bajaProductoDTO) {
		if (bajaProductoDTO.getDescripcion().indexOf("Visa Recargable") > -1) {
			return 99;
		} else if (bajaProductoDTO.getDescripcion().indexOf("Monedero") > -1) {
			return 77;
		}
		return 73;
	}

	/**
	 * Obtener cuenta.
	 *
	 * @param bajaProductoDTO
	 *            the baja producto DTO
	 * @return the cuenta
	 */
	private Cuenta obtenerCuenta(BajaProductoDTO bajaProductoDTO) {

		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.addAll(sesionCliente.getCliente().getCuentas());
		cuentas.addAll(sesionCliente.getCliente().getCuentasRetail());
		cuentas.addAll(sesionCliente.getCliente().getCuentasPrivadas());

		if (TipoOperacionBajaProductoEnum.BAJA_TARJETA.equals(bajaProductoDTO.getTipoOperacion())) {
			for (Cuenta cuenta : cuentas) {
				String nroTar = TarjetaBOUtils.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(),
						getMarcaTarjeta(cuenta));
				if (cuenta.getCodigoPaquete().equals(bajaProductoDTO.getCodigo())
						&& bajaProductoDTO.getDescripcion().indexOf(nroTar) > -1) {
					return cuenta;
				}
			}
		} else if (TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA.equals(bajaProductoDTO.getTipoOperacion())) {
			for (Cuenta cuenta : cuentas) {
				if (cuenta.getCodigoPaquete().equals(bajaProductoDTO.getCodigo())
						&& bajaProductoDTO.getDescripcion().indexOf(cuenta.obtenerNroCuentaFormateado()) > -1) {
					return cuenta;
				}
			}
		}
		return null;
	}

	/**
	 * Obtener descripcion producto.
	 *
	 * @param bajaProductoDTO
	 *            the baja producto DTO
	 * @return the string
	 */
	public String obtenerDescripcionProducto(BajaProductoDTO bajaProductoDTO) {
		List<Cuenta> lista = sesionCliente.getCliente().getCuentas();
		for (Cuenta cuenta : lista) {
			if (cuenta.getCodigoPaquete().equals(bajaProductoDTO.getCodigo())) {
				return descCuentaSolicitudes(cuenta.getTipoCuentaEnum()) + " " + TarjetaBOUtils
						.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(), TarjetaUtils.getMarca(cuenta));
			}
		}
		return null;
	}

	/**
	 * Desc cuenta solicitudes.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the string
	 */
	public static String descCuentaSolicitudes(TipoCuenta tipoCuenta) {

		switch (tipoCuenta.getCodigo()) {
		case 7:
			return "Visa";
		case 42:
			return "Amex";
		case 77:
			return "Visa Recargable";
		case 6:
			return "MasterCard";
		case 43:
			return "Monedero TAG";
		default:
			return null;
		}
	}

	/**
	 * Desc cuentas.
	 *
	 * @param tipoEnCanonico
	 *            the tipo en canonico
	 * @return the string
	 */
	private static String descCuentas(String tipoEnCanonico) {
		if ("CCP".equals(tipoEnCanonico)) {
			return "Servicio de Cuenta Corriente en pesos";
		}
		if ("CAP".equals(tipoEnCanonico)) {
			return "Servicio de Cuenta Caja de Ahorro en pesos";
		}
		if ("CCD".equals(tipoEnCanonico)) {
			return "Servicio de Cuenta Corriente en dólares";
		}
		if ("CAD".equals(tipoEnCanonico)) {
			return "Servicio de Cuenta Caja de ahorro en dólares";
		}
		if ("Títulos".equals(tipoEnCanonico)) {
			return "Cuenta Título";
		}
		return "";

	}

	/**
	 * Desc cuenta tarjeta.
	 *
	 * @param tipoEnCanonico
	 *            the tipo en canonico
	 * @return the string
	 */
	private static String descCuentaTarjeta(String tipoEnCanonico) {
		if ("RV".equals(tipoEnCanonico)) {
			return "Visa";
		}
		if ("RA".equals(tipoEnCanonico)) {
			return "Amex";
		}
		if ("CT".equals(tipoEnCanonico)) {
			return "Cuenta Título";
		}
		if ("AVIS".equals(tipoEnCanonico)) {
			return "Visa Recargable";
		}
		return "";

	}

	/**
	 * Desc cuenta solicitudes.
	 *
	 * @param tipoEnCanonico
	 *            the tipo en canonico
	 * @return the string
	 */
	public static String descCuentaSolicitudes(String tipoEnCanonico) {
		String descripcionCuenta = descCuentas(tipoEnCanonico);
		if (!"".equals(descripcionCuenta)) {
			return descripcionCuenta;
		}

		descripcionCuenta = descCuentaTarjeta(tipoEnCanonico);
		if (!"".equals(descripcionCuenta)) {
			return descripcionCuenta;
		}

		if ("MONE".equals(tipoEnCanonico)) {
			return "Monedero TAG";
		}
		return "";
	}

	/**
	 * Gets the desc paq solicitudes.
	 *
	 * @param codPaq
	 *            the cod paq
	 * @return the desc paq solicitudes
	 */
	public String getDescPaqSolicitudes(String codPaq) {
		String descPaq = "";
		if ("0355".equals(codPaq)) {
			descPaq = COD_PAQ_BLACK;
		} else if ("0350".equals(codPaq)) {
			descPaq = COD_PAQ_PLATINUM;
		} else if ("0502".equals(codPaq)) {
			descPaq = COD_PAQ_TC_INTERNACIONAL;
		} else if (esGold(codPaq)) {
			descPaq = COD_PAQ_GOLD;
		} else if (esInfiniti(codPaq)) {
			descPaq = COD_PAQ_INFINITY;
		} else if (esSuperCuenta3(codPaq)) {
			descPaq = COD_PAQ_SC3;
		} else if (esSuperCuenta2(codPaq)) {
			descPaq = COD_PAQ_SC2;
		} else if (esSuperCuenta(codPaq)) {
			descPaq = COD_PAQ_SC;
		} else if (esCuentaInversion(codPaq)) {
			descPaq = COD_PAQ_BP;
		} else if ("2115".equals(codPaq)) {
			descPaq = COD_PAQ_CC;
		} else if ("0358".equals(codPaq)) {
			descPaq = COD_PAQ_TC_BLACK;
		} else if ("0353".equals(codPaq)) {
			descPaq = COD_PAQ_TC_PLATINUM;
		} else if ("0352".equals(codPaq)) {
			descPaq = COD_PAQ_TC_PLATINUM_SELECT;
		} else if ("0005".equals(codPaq)) {
			descPaq = COD_PAQ_TC_GOLD;
		}
		return descPaq;

	}

	/**
	 * Es cuenta inversion.
	 *
	 * @param codPaq
	 *            the cod paq
	 * @return true, if successful
	 */
	private boolean esCuentaInversion(String codPaq) {
		return "3001".equals(codPaq) || "3101".equals(codPaq);
	}

	/**
	 * Es super cuenta.
	 *
	 * @param codPaq
	 *            the cod paq
	 * @return true, if successful
	 */
	private boolean esSuperCuenta(String codPaq) {
		return "2001".equals(codPaq) || "2002".equals(codPaq) || "2003".equals(codPaq) || "2009".equals(codPaq)
				|| "2101".equals(codPaq) || "2102".equals(codPaq) || "2109".equals(codPaq) || "2111".equals(codPaq)
				|| "2112".equals(codPaq) || "2113".equals(codPaq) || "2114".equals(codPaq) || "2140".equals(codPaq)
				|| "2150".equals(codPaq) || "2156".equals(codPaq) || "2170".equals(codPaq) || "2175".equals(codPaq)
				|| "2176".equals(codPaq) || "2178".equals(codPaq) || "2179".equals(codPaq) || "2181".equals(codPaq)
				|| "2182".equals(codPaq) || "2183".equals(codPaq) || "2184".equals(codPaq) || "2185".equals(codPaq)
				|| "2186".equals(codPaq) || "2187".equals(codPaq) || "2188".equals(codPaq) || "2190".equals(codPaq)
				|| "2191".equals(codPaq);
	}

	/**
	 * Es super cuenta 2.
	 *
	 * @param codPaq
	 *            the cod paq
	 * @return true, if successful
	 */
	private boolean esSuperCuenta2(String codPaq) {
		return "1000".equals(codPaq) || "1001".equals(codPaq) || "1250".equals(codPaq) || "1300".equals(codPaq)
				|| "1375".equals(codPaq) || "1376".equals(codPaq) || "1377".equals(codPaq) || "1378".equals(codPaq)
				|| "1391".equals(codPaq);
	}

	/**
	 * Es super cuenta 3.
	 *
	 * @param codPaq
	 *            the cod paq
	 * @return true, if successful
	 */
	private boolean esSuperCuenta3(String codPaq) {
		return "1750".equals(codPaq) || "1751".equals(codPaq) || "1752".equals(codPaq) || "1753".equals(codPaq)
				|| "1780".equals(codPaq) || "1802".equals(codPaq);
	}

	/**
	 * Es infiniti.
	 *
	 * @param codPaq
	 *            the cod paq
	 * @return true, if successful
	 */
	private boolean esInfiniti(String codPaq) {
		return "0500".equals(codPaq) || "5001".equals(codPaq) || "0540".equals(codPaq) || "0562".equals(codPaq)
				|| "0563".equals(codPaq) || "1500".equals(codPaq) || "1501".equals(codPaq);

	}

	/**
	 * Es gold.
	 *
	 * @param codPaq
	 *            the cod paq
	 * @return true, if successful
	 */
	private static boolean esGold(String codPaq) {
		return ("0001".equals(codPaq) || "0002".equals(codPaq) || "0040".equals(codPaq) || "0062".equals(codPaq)
				|| "0063".equals(codPaq));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.producto.bo.BajaProductoBO#
	 * obtenerProductosPaquetesCuenta()
	 */
	public Respuesta<ObtenerBajaProductoDTO> obtenerProductosPaquetesCuenta() {
		Set<Cuenta> cuentas = new LinkedHashSet<Cuenta>();
		cuentas.addAll(sesionCliente.getCliente().getCuentasMonetarias());
		cuentas.addAll(sesionCliente.getCliente().getCuentasRetail());
		cuentas.addAll(sesionCliente.getCliente().getCuentasPrivadas());

		ObtenerBajaProductoDTO obtenerBajaProductoDTO = new ObtenerBajaProductoDTO();
		ArrayList<ProductoDTO> cuentasProductos = new ArrayList<ProductoDTO>();
		for (Cuenta cuenta : cuentas) {
			String descripcionPaquete = getDescPaqSolicitudes(cuenta.getCodigoPaquete());
			if (COD_PROD_FUERA_PAQ.equals(cuenta.getNumeroPaquete())
					&& ("CAP".equals(cuenta.getTipoCuentaEnum().getAbreviatura())
							|| "CAD".equals(cuenta.getTipoCuentaEnum().getAbreviatura())
							|| "CCP".equals(cuenta.getTipoCuentaEnum().getAbreviatura())
							|| "CCD".equals(cuenta.getTipoCuentaEnum().getAbreviatura())
							|| cuenta.getTipoCuentaEnum().equals(TipoCuenta.TITULOS)
							|| "CT".equals(cuenta.getTipoCuentaEnum().getAbreviatura()))) {

				agregarCuentaProductoNoPAquetizado(cuentasProductos, cuenta);

			} else if (!cuenta.esAdicional() && StringUtils.isNotEmpty(descripcionPaquete)
				          && (cuenta.isCuentaDolares()|| cuenta.isCuentaPesos()|| cuenta.isCuentaUnica())) {
				ProductoDTO bajaProducto = new ProductoDTO();
				bajaProducto.setCodigo(cuenta.getCodigoPaquete());
				bajaProducto.setCuenta(cuenta.getIndex());
				bajaProducto.setPaq(cuenta.getClasePaquete());
				bajaProducto.setTipoProducto(ID_CUENTA_PAQUETE);
				// bajaProducto.setDescripcion(descripcionPaquete + " " +
				// cuenta.obtenerNroCuentaFormateado());
				bajaProducto.setDescripcion(descripcionPaquete);
				bajaProducto.setNumero(cuenta.obtenerNroCuentaFormateado());
				agregarCuentaProductoPaquetizado(cuentasProductos, bajaProducto);
			}
		}
		obtenerBajaProductoDTO.setProductos(cuentasProductos);
		return respuestaFactory.crearRespuestaOk(ObtenerBajaProductoDTO.class, obtenerBajaProductoDTO);
	}

	/**
	 * Agregar cuenta producto no P aquetizado.
	 *
	 * @param cuentasProductos
	 *            the cuentas productos
	 * @param cuenta
	 *            the cuenta
	 */
	private void agregarCuentaProductoNoPAquetizado(ArrayList<ProductoDTO> cuentasProductos, Cuenta cuenta) {
		String tipoCuenta = descCuentaSolicitudes(cuenta.getTipoCuentaEnum().getAbreviatura());
		if (!tipoCuenta.isEmpty()) {
			ProductoDTO bajaProducto = new ProductoDTO();
			bajaProducto.setCodigo(cuenta.getCodigoPaquete());
			bajaProducto.setCuenta(cuenta.getIndex());
			bajaProducto.setPaq(cuenta.getClasePaquete());
			bajaProducto.setTipoProducto(ID_CUENTA_FP);
			// bajaProducto.setDescripcion(tipoCuenta + " " +
			// cuenta.obtenerNroCuentaFormateado());
			bajaProducto.setDescripcion(tipoCuenta);
			bajaProducto.setNumero(cuenta.obtenerNroCuentaFormateado());
			cuentasProductos.add(bajaProducto);
		}

	}

	/**
	 * Agregar cuenta producto paquetizado.
	 *
	 * @param cuentasProductos
	 *            the cuentas productos
	 * @param bajaProducto
	 *            the baja producto
	 */
	private void agregarCuentaProductoPaquetizado(ArrayList<ProductoDTO> cuentasProductos, ProductoDTO bajaProducto) {
		if (!existeEnLista(cuentasProductos, bajaProducto)) {
			cuentasProductos.add(bajaProducto);
		}
	}

	/**
	 * Existe en lista.
	 *
	 * @param productos
	 *            the productos
	 * @param productoDTO
	 *            the producto DTO
	 * @return true, if successful
	 */
	private boolean existeEnLista(ArrayList<ProductoDTO> productos, ProductoDTO productoDTO) {
		for (ProductoDTO productoEnListaDTO : productos) {
			if ((productoEnListaDTO.getCodigo().equals(productoDTO.getCodigo())
			        && productoEnListaDTO.getNumero().equals(productoDTO.getNumero()))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtener productos otros medios de pago.
	 *
	 * @return the respuesta
	 */
	public Respuesta<ObtenerBajaProductoDTO> obtenerProductosOtrosMediosDePago() {
		List<Cuenta> cuentas = sesionCliente.getCliente().getCuentas();

		ObtenerBajaProductoDTO obtenerBajaProductoDTO = new ObtenerBajaProductoDTO();
		ArrayList<ProductoDTO> cuentasProductos = new ArrayList<ProductoDTO>();

		for (Cuenta cuenta : cuentas) {

			int posIniTagOk = 19;
			int posFinTagOk = 20;
			String okTag = "1";
			String flagMostrarTag = cuenta.getCbu().substring(posIniTagOk, posFinTagOk);
			if (flagMostrarTag.equals(okTag)) {
				ProductoDTO bajaProducto = new ProductoDTO();
				bajaProducto.setCodigo(cuenta.getCodigoPaquete());
				bajaProducto.setDescripcion("MONEDERO TAG " + cuenta.obtenerNroCuentaFormateado());
				cuentasProductos.add(bajaProducto);
			}

		}

		obtenerBajaProductoDTO.setProductos(cuentasProductos);
		return respuestaFactory.crearRespuestaOk(ObtenerBajaProductoDTO.class, obtenerBajaProductoDTO);
	}

	/**
	 * Se listan las tarjetas titulares, que no esten paquetizadas.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ObtenerBajaProductoDTO> obtenerProductosBajaTarjeta() {
		List<Cuenta> cuentas = sesionCliente.getCliente().getCuentas();

		ObtenerBajaProductoDTO obtenerBajaProductoDTO = new ObtenerBajaProductoDTO();
		ArrayList<ProductoDTO> cuentasProductos = new ArrayList<ProductoDTO>();

		for (Cuenta cuenta : cuentas) {
			if ("001".equals(cuenta.getNroOrdenFirmante())) {
				String descripcionPaquete = getDescPaqSolicitudes(cuenta.getCodigoPaquete());

				String descripcionTarjeta = descCuentaSolicitudes(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()));
				if (StringUtils.isNotEmpty(descripcionTarjeta) && StringUtils.isEmpty(descripcionPaquete)) {
					if (TipoCuenta.TARJETA_MONEDERO.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()))
							&& !esTagReal(cuenta)) {
						// Para TAG monedero solo se muestran los tags reales y
						// no virtuales
						continue;
					}
					ProductoDTO bajaProducto = new ProductoDTO();
					bajaProducto.setCodigo(cuenta.getCodigoPaquete());
					bajaProducto.setCuenta(cuenta.getIndex());
					String nroTar = TarjetaBOUtils.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(),
							getMarcaTarjeta(cuenta));
					bajaProducto.setDescripcion(descripcionTarjeta + " " + nroTar);
					bajaProducto.setRecargable(
							TipoCuenta.VISA_RECARGABLE.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta())));
					cuentasProductos.add(bajaProducto);
				}
			}
		}

		obtenerBajaProductoDTO.setProductos(cuentasProductos);
		return respuestaFactory.crearRespuestaOk(ObtenerBajaProductoDTO.class, obtenerBajaProductoDTO);
	}

	/**
	 * Es tag real.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if successful
	 */
	private boolean esTagReal(Cuenta cuenta) {
		// pos 20 cbu = 1 -> MOSTRAR TAG
		int posIniTagOk = 19;
		int posFinTagOk = 20;
		String flagMostrarTag = cuenta.getCbu().substring(posIniTagOk, posFinTagOk);
		return "1".equals(flagMostrarTag);
	}

	/**
	 * Gets the marca tarjeta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the marca tarjeta
	 */
	/*
	 * getMarcaTarjeta
	 * 
	 * @param cuenta Cuenta a procesar
	 * 
	 * @return Marca de Tarjeta asociada a la cuenta
	 */
	private String getMarcaTarjeta(Cuenta cuenta) {
		if (TipoCuenta.TARJETA_MONEDERO.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()))) {
			return TarjetaUtils.MARCA_VISA;
		} else {
			return TarjetaUtils.getMarca(cuenta);
		}
	}

	/**
	 * Filtro prod paquetes.
	 *
	 * @param cuentaBaja
	 *            the cuenta baja
	 * @return the list
	 */
	public List<Cuenta> filtroProdPaquetes(Cuenta cuentaBaja) {
		Map<String, List<Cuenta>> mapaPaquetes = getMapaPaquetes();
		Map<String, List<Cuenta>> mapaProdEnPaquete = new HashMap<String, List<Cuenta>>();
		for (Entry<String, List<Cuenta>> entry : mapaPaquetes.entrySet()) {
			String key = entry.getKey();
			List<Cuenta> ctaFiltrada = entry.getValue();
			if (!COD_PROD_FUERA_PAQ.equals(key)) {
				mapaProdEnPaquete.put(key, ctaFiltrada);
			}
		}
		// PRODUCTOS DENTRO DE PAQUETES
		if (!mapaProdEnPaquete.isEmpty()) {
			// cargo los productos en paquetes dentro del vector
			// productosSolBaja
			mapaProdEnPaquete = filtroAdicTrjMapaPaquetes(mapaProdEnPaquete);
			Map<String, List<Cuenta>> mapaProdSinAdicEnPaquete = filtroNombresDePaquetes(mapaProdEnPaquete);
			return obtenerCuentasPaqueteAsociado(mapaProdSinAdicEnPaquete, cuentaBaja);
		}
		return new ArrayList<Cuenta>();
	}

	/**
	 * Gets the mapa paquetes.
	 *
	 * @return the mapa paquetes
	 */
	private Map<String, List<Cuenta>> getMapaPaquetes() {
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.addAll(sesionCliente.getCliente().getCuentas());
		cuentas.addAll(sesionCliente.getCliente().getCuentasRetail());
		cuentas.addAll(sesionCliente.getCliente().getCuentasPrivadas());
		Map<String, List<Cuenta>> mapaPaquetes = new HashMap<String, List<Cuenta>>();
		for (Cuenta cuenta : cuentas) {
			String keyPaquete = cuenta.getNumeroPaquete();
			if (!mapaPaquetes.containsKey(keyPaquete)) {
				List<Cuenta> ctas = new ArrayList<Cuenta>();
				ctas.add(cuenta);
				mapaPaquetes.put(keyPaquete, ctas);
			} else {
				List<Cuenta> ctas = mapaPaquetes.get(keyPaquete);
				ctas.add(cuenta);
				mapaPaquetes.put(keyPaquete, ctas);
			}
		}
		return mapaPaquetes;
	}

	/**
	 * Obtener cuentas paquete asociado.
	 *
	 * @param mapaPaquetes
	 *            the mapa paquetes
	 * @param cuentaBaja
	 *            the cuenta baja
	 * @return the list
	 */
	private List<Cuenta> obtenerCuentasPaqueteAsociado(Map<String, List<Cuenta>> mapaPaquetes, Cuenta cuentaBaja) {
		for (Entry<String, List<Cuenta>> entry : mapaPaquetes.entrySet()) {
			List<Cuenta> ctasPaquete = entry.getValue();
			if (contieneProdBaja(ctasPaquete, cuentaBaja)) {
				return ctasPaquete;
			}
		}
		return new ArrayList<Cuenta>();
	}

	/**
	 * Contiene prod baja.
	 *
	 * @param ctas
	 *            the ctas
	 * @param cuenta
	 *            the cuenta
	 * @return true, if successful
	 */
	private boolean contieneProdBaja(List<Cuenta> ctas, Cuenta cuenta) {
		for (Cuenta cta : ctas) {
			if (cta.getNroCuentaProducto().equals(cuenta.getNroCuentaProducto())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Filtro adic trj mapa paquetes.
	 *
	 * @param mapaPaquetes
	 *            the mapa paquetes
	 * @return the map
	 */
	private Map<String, List<Cuenta>> filtroAdicTrjMapaPaquetes(Map<String, List<Cuenta>> mapaPaquetes) {
		Map<String, List<Cuenta>> mapaPaqueteSinAdicTrj = new HashMap<String, List<Cuenta>>();
		for (Entry<String, List<Cuenta>> entry : mapaPaquetes.entrySet()) {
			String key = entry.getKey();
			List<Cuenta> ctas = filtroAdicTrj(entry.getValue());
			if (!ctas.isEmpty()) {
				mapaPaqueteSinAdicTrj.put(key, ctas);
			}
		}
		return mapaPaqueteSinAdicTrj;
	}

	/**
	 * Filtro adic trj.
	 *
	 * @param ctas
	 *            the ctas
	 * @return the list
	 */
	private List<Cuenta> filtroAdicTrj(List<Cuenta> ctas) {
		List<Cuenta> ctasEliminar = new ArrayList<Cuenta>();
		for (Cuenta rcta : ctas) {
			if (esTarjeta(rcta) && "AD".equals(rcta.getCodigoTitularidad())) {
				// guardo una lista de las cuentas a eliminar
				ctasEliminar.add(rcta);
			}
		}
		// elimino todas las cuentas guardadas
		for (Cuenta rcta : ctasEliminar) {
			ctas.remove(rcta);
		}
		return ctas;
	}

	/**
	 * Es tarjeta.
	 *
	 * @param rcta
	 *            the rcta
	 * @return true, if successful
	 */
	private boolean esTarjeta(Cuenta rcta) {
		return rcta.esTarjetaVisa() || rcta.esTarjetaAmex() || rcta.esTajetaMaster()
				|| TipoCuenta.TARJETA_MONEDERO.equals(rcta.getTipoCuentaEnum());
	}

	/**
	 * Filtro nombres de paquetes.
	 *
	 * @param mapaPaquetes
	 *            the mapa paquetes
	 * @return the map
	 */
	private Map<String, List<Cuenta>> filtroNombresDePaquetes(Map<String, List<Cuenta>> mapaPaquetes) {
		Map<String, List<Cuenta>> mapaProdPaqueteConDesc = new HashMap<String, List<Cuenta>>();
		try {
			for (Entry<String, List<Cuenta>> entry : mapaPaquetes.entrySet()) {
				List<Cuenta> ctas = entry.getValue();
				// si retorna key "" es porque el paquete no se tiene que
				// mostrar para la baja
				String keyPaquetefiltrado = filtroDescPaquete(ctas);
				LOGGER.info("KeyPaquetefiltrado = ", keyPaquetefiltrado);
				if ("".equals(keyPaquetefiltrado)) {
					// si esta en blanco es porque no hay que mostrarlo
					mapaProdPaqueteConDesc.remove(keyPaquetefiltrado);
				} else {
					mapaProdPaqueteConDesc.put(keyPaquetefiltrado, ctas);
				}
			}
		} catch (Exception e) {
			LOGGER.error("ERROR filtroNombresDePaquetes", e.getMessage());
		}
		return mapaProdPaqueteConDesc;
	}

	/**
	 * Filtro desc paquete.
	 *
	 * @param ctas
	 *            the ctas
	 * @return the string
	 */
	private String filtroDescPaquete(List<Cuenta> ctas) {
		Cuenta rcta = ctas.get(0);
		StringBuilder nombrePaquete = new StringBuilder();
		nombrePaquete.append(getDescPaqSolicitudes(rcta.getCodigoPaquete()));
		if (!"".equals(nombrePaquete.toString())) {
			String sucursalPaquete = ISBANStringUtils.formatearSucursal(rcta.getSucursalPaquete());
			String numeroCuenta = ISBANStringUtils.formatearNumeroCuenta(rcta.getNroCuentaProducto());
			String nroCtaCanonico = new StringBuilder().append(sucursalPaquete).append("-").append(numeroCuenta)
					.toString();
			nombrePaquete.append(" ").append(nroCtaCanonico);
		}
		return nombrePaquete.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.producto.bo.BajaProductoBO#
	 * generarComprobante(ar.com.santanderrio.obp.servicios.producto.view.
	 * ComprobanteBajaProductoView)
	 */
	@Override
	public Respuesta<Reporte> generarComprobante(ComprobanteBajaProductoView comprobanteBajaProducto) {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = null;

		reporte = solicitudProductoDAO.generarComprobanteBaja(comprobanteBajaProducto);

		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

	/**
	 * Gets the solicitud producto DAO.
	 *
	 * @return the solicitud producto DAO
	 */
	public SolicitudProductoDAO getSolicitudProductoDAO() {
		return solicitudProductoDAO;
	}

	/**
	 * Sets the solicitud producto DAO.
	 *
	 * @param solicitudProductoDAO
	 *            the new solicitud producto DAO
	 */
	public void setSolicitudProductoDAO(SolicitudProductoDAO solicitudProductoDAO) {
		this.solicitudProductoDAO = solicitudProductoDAO;
	}

	@Override
	public Respuesta<BajaProductoDTO> solicitarArrepentimientoProducto(BajaProductoDTO bajaProductoDTO) {

		ArrayOf158770343432493182NillableInfoRequeridaWS infoReq = new ArrayOf158770343432493182NillableInfoRequeridaWS();

		ResultadoAltaWS resultadoWS;
		try {
			resultadoWS = solicitudProductoDAO.altaGestion(Integer.valueOf(bajaProductoDTO.getCodigo()), "F",
					Integer.valueOf(sesionCliente.getCliente().getNup()), "NEIT", "HBAN0002", 35,
					StringUtils.isBlank(bajaProductoDTO.getDescripcionArrepentimiento()) ? " " : bajaProductoDTO.getDescripcionArrepentimiento(), infoReq);
			
			if (resultadoWS.getCodRetorno() != 0) {
				LOGGER.error("Error en AltaGestion - CodRetorno:" + resultadoWS.getCodRetorno() + " -"
						+ resultadoWS.getDescRetorno());
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_ERROR);
			}
			
			String mensaje = mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.ARREPENTIMIENTO_FEEDBACK_OK).getMensaje();
			bajaProductoDTO.setMensaje(StringUtils.isBlank(mensaje) ? StringUtils.EMPTY : mensaje);
			bajaProductoDTO.setComprobante(String.valueOf(resultadoWS.getIdeGestionNro()));
			
		} catch (NumberFormatException e) {
			LOGGER.debug("No se pudo solicitar el arrepentimiento del producto", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_ERROR);
		} catch (DAOException e) {
			LOGGER.debug("No se pudo solicitar el arrepentimiento del producto", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_ERROR);
		}
		
		return respuestaFactory.crearRespuestaOk(BajaProductoDTO.class, bajaProductoDTO);
	}

}
