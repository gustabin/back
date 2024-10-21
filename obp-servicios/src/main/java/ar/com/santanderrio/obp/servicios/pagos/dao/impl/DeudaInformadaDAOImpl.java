/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagos.dao.DeudaInformadaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DeudaInformada;

/**
 * The Class DeudaInformadaDAOImpl.
 */
@Component
public class DeudaInformadaDAOImpl implements DeudaInformadaDAO {
	/** The Constant ERROR_AL_FORMATEAR_FECHA. */
	private static final String ERROR_AL_FORMATEAR_FECHA = "Error al formatear fecha";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DeudaInformadaDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The servicio cnspesdeud. */
	@Value("${SERVICIO.PREFIJO.CNSPESDEUD}")
	private String servicioCnspesdeud;

	/** The version cnspesdeud. */
	@Value("${SERVICIO.VERSION.CNSPESDEUD}")
	private String versionCnspesdeud;

	/** The Constant CODIGO_DE_ERROR_DESCONOCIDO_MSG. */
	private static final String CODIGO_DE_ERROR_DESCONOCIDO_MSG = "Codigo de error iatx desconocido";

	/** The Constant CODIGO_DE_ERROR_INICIO_STRING. */
	private static final String CODIGO_DE_ERROR_INICIO_STRING = "[codigo_error=";

	/** The Constant CODIGO_DE_ERROR_FIN_STRING. */
	private static final String CODIGO_DE_ERROR_FIN_STRING = "]";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant SIN_MOVIMIETOS_CODIGO_RETORNO. */
	private static final int SIN_MOVIMIETOS_CODIGO_RETORNO = 10000065;

	/** The Constant SIN_FECHA_1. */
	private static final String SIN_FECHA_1 = "********";

	/** The Constant VACIO_STRING. */
	private static final String VACIO_STRING = "";

	/** The Constant PAGO_RECURRENTE_TRUE. */
	private static final String PAGO_RECURRENTE_TRUE = "S";

	/** The Constant PAGO_RECURRENTE_FALSE. */
	private static final String PAGO_RECURRENTE_FALSE = "N";

	/** The date formatter. */
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy");

	/** he Constant VALORES_PRIMER_PAGO. */
	// momento
	private static final String[] VALORES_PRIMER_PAGO = { " ", "A", "B" };

	/** The Constant VALORES_NO_PRIMER_PAGO. */
	private static final String[] VALORES_NO_PRIMER_PAGO = { "P", "Q" };

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.DeudaInformadaDAO#
	 * consultarDeudaInformada(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente)
	 */
	@Override
	public List<DeudaInformada> consultarDeudaInformada(Cliente cliente) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(servicioCnspesdeud, versionCnspesdeud);
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequest.setData(iatxRequestData);
		List<DeudaInformada> resultado = null;
		try {
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			switch (errorCode) {
			case OK_CODIGO_RETORNO:
				resultado = parsearResponse(iatxResponse);
				break;
			case SIN_MOVIMIETOS_CODIGO_RETORNO:
				return new ArrayList<DeudaInformada>();
			default:
				throw new DAOException(generarCodigoErrorDesconocidoMensaje(errorCode));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return resultado;
	}

	/**
	 * Generar codigo error desconocido mensaje.
	 *
	 * @param errorCode the error code
	 * @return the string
	 */
	private String generarCodigoErrorDesconocidoMensaje(int errorCode) {
		return new StringBuilder().append(CODIGO_DE_ERROR_DESCONOCIDO_MSG).append(CODIGO_DE_ERROR_INICIO_STRING)
		        .append(errorCode).append(CODIGO_DE_ERROR_FIN_STRING).toString();
	}

	/**
	 * Parsear response.
	 *
	 * @param iatxResponse the iatx response
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	private List<DeudaInformada> parsearResponse(IatxResponse iatxResponse) throws DAOException {
		ArrayList<DeudaInformada> respuesta = new ArrayList<DeudaInformada>();
		int cantidadDeElementos = Integer.parseInt(iatxResponse.getNextData());
		DeudaInformada elemento = null;
		/*
		 * Empresa A 4 Codigo de Empresa Identificacion del cliente en la empresa A 19
		 * Codigo de Identificacion del Cliente en la Empresa Vencimiento N 8 F.
		 * Vencimiento - ddmmaaaa (*) Importe N 12,2 Importe Moneda A 3 Moneda ARS / USD
		 * Factura A 20 Numero de Factura Texto en pantalla A 15 Texto en la pantalla
		 * Modo de alta A 1 Modo de alta (Primer o no primer pago) Descipcion A 20
		 * Informacion adicional al pago Pago_Recurrente A 1 S o N
		 */
		for (int i = 1; i <= cantidadDeElementos; i++) {
			elemento = new DeudaInformada();
			elemento.setEmpresa(iatxResponse.getNextData());
			elemento.setIdClienteEmpresa(iatxResponse.getNextData());
			elemento.setVencimiento(formatearFecha(iatxResponse.getNextData()));
			elemento.setImporte(new BigDecimal(ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getNextData())));

			String divisa = iatxResponse.getNextData().trim();
			if (VACIO_STRING.equals(divisa)) {
				elemento.setDivisa(DivisaEnum.PESO);
			} else {
				elemento.setDivisa(DivisaEnum.fromCodigoString(divisa));
			}
			elemento.setFactura(iatxResponse.getNextData());
			elemento.setTextoEnPantalla(iatxResponse.getNextData());
			String modoDeAlta = iatxResponse.getNextData();
			elemento.setModoDeAlta(modoDeAlta);
			elemento.setPrimerPago(isPrimerPago(modoDeAlta));
			elemento.setDescripcion(iatxResponse.getNextData());
			elemento.setPagoRecurrente(isPagoRecurrente(iatxResponse.getNextData()));
			respuesta.add(elemento);
		}
		return respuesta;
	}

	/**
	 * Checks if is primer pago.
	 *
	 * @param valor the valor
	 * @return the boolean
	 */
	private Boolean isPrimerPago(String valor) {
		if (existsInArrayIgnoreCase(VALORES_PRIMER_PAGO, valor)) {
			return Boolean.TRUE;
		}
		if (existsInArrayIgnoreCase(VALORES_NO_PRIMER_PAGO, valor)) {
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	/**
	 * Exists in array ignore case.
	 *
	 * @param array the array
	 * @param valor the valor
	 * @return true, if successful
	 */
	private boolean existsInArrayIgnoreCase(String[] array, String valor) {
		for (String elemento : array) {
			if (elemento.equalsIgnoreCase(valor)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if is pago recurrente.
	 *
	 * @param valor the valor
	 * @return the boolean
	 */
	private Boolean isPagoRecurrente(String valor) {
		if (PAGO_RECURRENTE_TRUE.equalsIgnoreCase(valor)) {
			return Boolean.TRUE;
		}
		if (PAGO_RECURRENTE_FALSE.equalsIgnoreCase(valor)) {
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	/**
	 * Formatear fecha.
	 *
	 * @param vencimientoString the vencimiento string
	 * @return the date
	 * @throws DAOException the DAO exception
	 */
	private Date formatearFecha(String vencimientoString) throws DAOException {
		Date fecha = null;
		if (vencimientoString == null || vencimientoString.trim().isEmpty() || SIN_FECHA_1.equals(vencimientoString)) {
			fecha = null;
		} else {
			try {
				// workaround por si viene la trama en 0
				if (Integer.parseInt(vencimientoString) > 0) {
					fecha = dateFormatter.parse(vencimientoString);
				}
			} catch (ParseException e) {
				arrojarExcepcionFormatoFecha(e);
			} catch (NumberFormatException e) {
				arrojarExcepcionFormatoFecha(e);
			}
		}
		return fecha;
	}

	/**
	 * Arrojar excepcion formato fecha.
	 *
	 * @param e the e
	 * @throws DAOException the DAO exception
	 */
	private void arrojarExcepcionFormatoFecha(Exception e) throws DAOException {
		LOGGER.error(ERROR_AL_FORMATEAR_FECHA, e);
		throw new DAOException(e, ERROR_AL_FORMATEAR_FECHA);
	}
}
