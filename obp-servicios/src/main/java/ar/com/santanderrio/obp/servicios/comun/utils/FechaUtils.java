/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
/***************************************************************************
 * @author Luciano Quiroga y Mariela Da Graca Guerra
 * @version  1.0 / 12-Sep-2000
 *
 * Proyecto:  - Portal
 *
 * Clase Fecha
 * Provee metodos simplificados para manejo de Fechas y Horas
 ***************************************************************************/
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.exceptions.FechaException;

/**
 * The Class FechaUtils.
 *
 * @author florencia.n.martinez
 */
public final class FechaUtils {

	/** The Constant ERROR_PARSEO_FECHA_CREACION_DESTINATARIO. */
	private static final String ERROR_PARSEO_FECHA_CREACION_DESTINATARIO = "Error al parsear fecha de creacion destinatario.";

	/** The Constant YYYYMMDDhhminsecmsec. */
	private static final String YYYYMMDDhhminsecmsec = "yyyy-MM-dd-hh.mm.ss.ms";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FechaUtils.class);

	/**
	 * Variables publicas para identificacion de la clase.
	 *
	 */
	private static String sClassType = "UTILITARIO";

	/** The s class name. */
	private static String sClassName = "Fecha";

	/**
	 * Variables de instancia.
	 *
	 */
	private GregorianCalendar cFecha;

	/** The s separador fecha. */
	private String sSeparadorFecha = "-";

	/** The s separador hora. */
	private String sSeparadorHora = ":";

	/** The Constant DIA. */
	public static final int DIA = Calendar.DATE;

	/** The Constant MES. */
	public static final int MES = Calendar.MONTH;

	/** The Constant ANIO. */
	public static final int ANIO = Calendar.YEAR;

	/** The Constant FORMATO_ORACLE_YYYYMMDD. */
	private static final int FORMATO_ORACLE_YYYYMMDD = 1;

	/** The Constant FORMATO_ORACLE_DDMMYYYY. */
	private static final int FORMATO_ORACLE_DDMMYYYY = 2;

	/** The Constant FORMATO_YYYYMMDD. */
	private static final int FORMATO_YYYYMMDD = 3;

	/** The Constant FORMATO_DDMMYYYY. */
	private static final int FORMATO_DDMMYYYY = 4;

	/** Mes 1 = enero. */
	private static final int MES_1 = 1;

	/** Mes 2 = febrero. */
	private static final int MES_2 = 2;

	/** Mes 3 = marzo. */
	private static final int MES_3 = 3;

	/** Mes 4 = abril. */
	private static final int MES_4 = 4;

	/** Mes 5 = mayo. */
	private static final int MES_5 = 5;

	/** Mes 6 = junio. */
	private static final int MES_6 = 6;

	/** Mes 7 = julio. */
	private static final int MES_7 = 7;

	/** Mes 8 = agosto. */
	private static final int MES_8 = 8;

	/** Mes 9 = septiembre. */
	private static final int MES_9 = 9;

	/** Mes 10 = octubre. */
	private static final int MES_10 = 10;

	/** Mes 11 = noviembre. */
	private static final int MES_11 = 11;

	/** Mes 12 = diciembre. */
	private static final int MES_12 = 12;

	/** The Constant FORMATO_ORACLE_DDMMYYYY_DIA_ORIGEN. */
	private static final int FORMATO_ORACLE_DDMMYYYY_DIA_ORIGEN = 0;

	/** The Constant FORMATO_ORACLE_DDMMYYYY_DIA_DESTINO. */
	private static final int FORMATO_ORACLE_DDMMYYYY_DIA_DESTINO = 2;

	/** The Constant FORMATO_ORACLE_DDMMYYYY_MES_ORIGEN. */
	private static final int FORMATO_ORACLE_DDMMYYYY_MES_ORIGEN = 3;

	/** The Constant FORMATO_ORACLE_DDMMYYYY_MES_DESTINO. */
	private static final int FORMATO_ORACLE_DDMMYYYY_MES_DESTINO = 5;

	/** The Constant FORMATO_ORACLE_DDMMYYYY_ANIO. */
	private static final int FORMATO_ORACLE_DDMMYYYY_ANIO = 6;

	/** The Constant FORMATO_ORACLE_YYYYMMDD_DIA. */
	private static final int FORMATO_ORACLE_YYYYMMDD_DIA = 8;

	/** The Constant FORMATO_ORACLE_YYYYMMDD_MES_ORIGEN. */
	private static final int FORMATO_ORACLE_YYYYMMDD_MES_ORIGEN = 5;

	/** The Constant FORMATO_ORACLE_YYYYMMDD_MES_DESTINO. */
	private static final int FORMATO_ORACLE_YYYYMMDD_MES_DESTINO = 7;

	/** The Constant FORMATO_ORACLE_YYYYMMDD_ANIO_ORIGEN. */
	private static final int FORMATO_ORACLE_YYYYMMDD_ANIO_ORIGEN = 0;

	/** The Constant FORMATO_ORACLE_YYYYMMDD_ANIO_DESTINO. */
	private static final int FORMATO_ORACLE_YYYYMMDD_ANIO_DESTINO = 48;

	/** The Constant FORMATO_YYYYMMDD_DIA. */
	private static final int FORMATO_YYYYMMDD_DIA = 6;

	/** The Constant FORMATO_YYYYMMDD_MES_ORIGEN. */
	private static final int FORMATO_YYYYMMDD_MES_ORIGEN = 4;

	/** The Constant FORMATO_YYYYMMDD_MES_DESTINO. */
	private static final int FORMATO_YYYYMMDD_MES_DESTINO = 6;

	/** The Constant FORMATO_YYYYMMDD_ANIO_ORIGEN. */
	private static final int FORMATO_YYYYMMDD_ANIO_ORIGEN = 0;

	/** The Constant FORMATO_YYYYMMDD_ANIO_DESTINO. */
	private static final int FORMATO_YYYYMMDD_ANIO_DESTINO = 4;

	/** The Constant FORMATO_DDMMYYYY_DIA_ORIGEN. */
	private static final int FORMATO_DDMMYYYY_DIA_ORIGEN = 0;

	/** The Constant FORMATO_DDMMYYYY_DIA_DESTINO. */
	private static final int FORMATO_DDMMYYYY_DIA_DESTINO = 2;

	/** The Constant FORMATO_DDMMYYYY_MES_ORIGEN. */
	private static final int FORMATO_DDMMYYYY_MES_ORIGEN = 2;

	/** The Constant FORMATO_DDMMYYYY_MES_DESTINO. */
	private static final int FORMATO_DDMMYYYY_MES_DESTINO = 4;

	/** The Constant FORMATO_DDMMYYYY_ANIO. */
	private static final int FORMATO_DDMMYYYY_ANIO = 4;
	
	/** Dia mes*/
	public static final String DD_MM = "dd/MM";
	
	/** Dia, mes y anio*/
	public static final String DD_MM_YYYY = "dd/MM/yyyy";

	/**
	 * Constructor sin parametros.
	 */
	public FechaUtils() {
	}

	/**
	 * Constructor fecha con parametros String.
	 *
	 * @param sDay
	 *            the s day
	 * @param sMonth
	 *            the s month
	 * @param sYear
	 *            the s year
	 * @throws FechaException
	 *             the fecha exception
	 */
	public FechaUtils(String sDay, String sMonth, String sYear) throws FechaException {
		LOGGER.debug("Fecha creada con dia {}, mes {}, anio {}.", sDay, sMonth, sYear);
		setFecha(sDay, sMonth, sYear);
	}

	/**
	 * Instantiates a new fecha utils.
	 *
	 * @param hoy
	 *            the hoy
	 */
	public FechaUtils(boolean hoy) {
		LOGGER.debug("Fecha creada con valor de hoy {}", hoy);
		cFecha = new GregorianCalendar();
	}

	/**
	 * Constructor fecha con parametros int.
	 *
	 * @param iDia
	 *            Dia
	 * @param iMes
	 *            Mes
	 * @param iAnio
	 *            Anio
	 * @throws FechaException
	 *             the fecha exception
	 */
	public FechaUtils(int iDia, int iMes, int iAnio) throws FechaException {
		LOGGER.debug("Fecha creada con dia {}, mes {}, anio {}.", iDia, iMes, iAnio);
		cFecha = new GregorianCalendar(iAnio, arreglarMes(iMes, true), iDia);
	}

	/**
	 * Instantiates a new fecha utils.
	 *
	 * @param lFecha
	 *            the l fecha
	 * @throws FechaException
	 *             the fecha exception
	 */
	public FechaUtils(long lFecha) throws FechaException {
		LOGGER.debug("Fecha creada con el long {}.", lFecha);
		// Creo un date con el long
		Date dFechaArch = new Date(lFecha);
		// Obtengo un string con la fecha en formato yyyy MMM ddd (MMM es
		// letras)
		DateFormat df = DateFormat.getDateInstance();
		String sFechaArch = df.format(dFechaArch);
		// Creo un stringtokenizer para obtener el mes en un entero
		StringTokenizer stFechaArch = new StringTokenizer(sFechaArch);
		// Obtengo cada uno de los campos de la fecha
		String sDia = stFechaArch.nextToken();
		String sMes = stFechaArch.nextToken();
		String sAnio = stFechaArch.nextToken();
		// Obtengo el mes en numeros en un string
		sMes = String.valueOf(convertMes(sMes));
		// Creo una fecha con la fecha de ultima modificacion del archivo
		setFecha(sDia, sMes, sAnio);

	}

	/**
	 * Constructor fecha y hora con parametros int.
	 *
	 * @param iDia
	 *            Dia
	 * @param iMes
	 *            Mes
	 * @param iAnio
	 *            Anio
	 * @param iHora
	 *            Hora
	 * @param iMin
	 *            Minutos
	 * @param iSeg
	 *            Segundos
	 * @throws FechaException
	 *             the fecha exception
	 */
	public FechaUtils(int iDia, int iMes, int iAnio, int iHora, int iMin, int iSeg) throws FechaException {
		LOGGER.debug("Fecha creada con dia {}, mes {}, anio {}, hora {}, min {} y seg {}.", iDia, iMes, iAnio, iHora,
				iMin, iSeg);
		cFecha = new GregorianCalendar(iAnio, arreglarMes(iMes, true), iDia, iHora, iMin, iSeg);
	}

	/**
	 * Constructor fecha y hora con parametros String.
	 *
	 * @param sDia
	 *            Dia
	 * @param sMes
	 *            Mes
	 * @param sAnio
	 *            Anio
	 * @param sHora
	 *            Hora
	 * @param sMin
	 *            Minutos
	 * @param sSeg
	 *            Segundos
	 * @throws FechaException
	 *             the fecha exception
	 */
	public FechaUtils(String sDia, String sMes, String sAnio, String sHora, String sMin, String sSeg)
			throws FechaException {
		LOGGER.debug("Fecha creada con dia {}, mes {}, anio {}, hora {}, min {} y seg {}.", sDia, sMes, sAnio, sHora,
				sMin, sSeg);
		Integer iDia = Integer.valueOf(sDia);
		Integer iMes = Integer.valueOf(arreglarMes(sMes, true));
		Integer iAnio = Integer.valueOf(sAnio);
		Integer iHora = Integer.valueOf(sHora);
		Integer iMin = Integer.valueOf(sMin);
		Integer iSeg = Integer.valueOf(sSeg);
		cFecha = new GregorianCalendar(iAnio.intValue(), iMes.intValue(), iDia.intValue(), iHora.intValue(),
				iMin.intValue(), iSeg.intValue());
	}

	/**
	 * Metodo privado setFecha Setea la variable de instancia cFecha.
	 *
	 * @param sDia
	 *            the s dia
	 * @param sMes
	 *            the s mes
	 * @param sAnio
	 *            the s anio
	 * @throws FechaException
	 *             the fecha exception
	 */
	private void setFecha(String sDia, String sMes, String sAnio) throws FechaException {
		Integer iDia = Integer.valueOf(sDia);
		Integer iMes = Integer.valueOf(arreglarMes(sMes, true));
		Integer iAnio = Integer.valueOf(sAnio);
		cFecha = new GregorianCalendar(iAnio.intValue(), iMes.intValue(), iDia.intValue());
	}

	/**
	 * Metodo publico setSeparadorFecha Setea el separador de fechas.
	 *
	 * @param sSep
	 *            caracter especial utilizado como separador de fecha
	 * @throws FechaException
	 *             the fecha exception
	 */
	public void setSeparadorFecha(String sSep) throws FechaException {
		sSeparadorFecha = sSep;
	}

	/**
	 * Metodo publico setSeparadorHora Setea el separador de la hora.
	 *
	 * @param sSep
	 *            caracter especial utilizado cmo separador de hora
	 * @throws FechaException
	 *             the fecha exception
	 */
	public void setSeparadorHora(String sSep) throws FechaException {
		sSeparadorHora = sSep;
	}

	/**
	 * Metodo publico getDia Si la instancia de la clase Fecha fue creada con
	 * parametros, devuelve el dia correspondiente a esa fecha, de lo contrario,
	 * devuelve el dia actual.
	 *
	 * @return String sDia
	 * @throws FechaException
	 *             the fecha exception
	 */

	public String getDia() throws FechaException {
		int iDia;
		Calendar cValue = getFecha();
		Calendar cal = new GregorianCalendar();
		StringBuffer sBuffer = new StringBuffer();
		if (isFechaEmpty()) {
			iDia = cal.get(Calendar.DATE);
		} else {
			iDia = cValue.get(Calendar.DAY_OF_MONTH);
		}
		String sDia = String.valueOf(iDia);
		sBuffer.append(sDia);
		if (sDia.length() < 2) {
			sBuffer = new StringBuffer();
			sBuffer.append("0").append(sDia);
		}
		return sBuffer.toString();
	}

	/**
	 * Metodo puiblico getDiaInt Si la instancia de la clase Fecha fue creada
	 * con parametros, devuelve el dia correspondiente a esa fecha, de lo
	 * contrario, devuelve el dia actual.
	 *
	 * @return int iDia
	 * @throws FechaException
	 *             the fecha exception
	 */
	public int getDiaInt() throws FechaException {
		int iDia;
		Calendar cValue = getFecha();
		Calendar cal = new GregorianCalendar();
		if (isFechaEmpty()) {
			iDia = cal.get(Calendar.DATE);
		} else {
			iDia = cValue.get(Calendar.DAY_OF_MONTH);
		}
		return iDia;
	}

	/**
	 * Metodo publico getMes Si la instancia de la clase Fecha fue creada con
	 * parametros, devuelve el mes correspondiente a esa fecha, de lo contrario,
	 * devuelve el mes actual.
	 *
	 * @return String sMes
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getMes() throws FechaException {
		Calendar cValue = getFecha();
		Calendar cal = new GregorianCalendar();
		StringBuffer sBuffer = new StringBuffer();
		int iMes;
		if (isFechaEmpty()) {
			iMes = cal.get(Calendar.MONTH);
		} else {
			iMes = cValue.get(Calendar.MONTH);
		}
		String sMes = String.valueOf(iMes);

		sMes = arreglarMes(sMes, false);
		sBuffer.append(sMes);

		if (sMes.length() < 2) {
			sBuffer = new StringBuffer();
			sBuffer.append("0").append(sMes);
		}
		return sBuffer.toString();
	}

	/**
	 * Gets the mes int.
	 *
	 * @return the mes int
	 * @throws FechaException
	 *             the fecha exception
	 */
	public int getMesInt() throws FechaException {
		return Integer.valueOf(getMes()).intValue();
	}

	/**
	 * Metodo publico getMesInt Si la instancia de la clase Fecha fue creada con
	 * parametros, devuelve el mes correspondiente a esa fecha, de lo contrario,
	 * devuelve el mes actual.
	 *
	 * @return int iMes
	 * @throws FechaException
	 *             the fecha exception
	 */

	/**
	 * Metodo publico getAnio Si la instancia de la clase Fecha fue creada con
	 * parametros, devuelve el ano correspondiente a esa fecha, de lo contrario,
	 * devuelve el ano actual
	 * 
	 * @return String sAnio
	 * @throws FechaException
	 */
	public String getAnio() throws FechaException {
		int iAnio;
		Calendar cValue = getFecha();
		Calendar cal = new GregorianCalendar();
		StringBuffer sBuffer = new StringBuffer();
		if (isFechaEmpty()) {
			iAnio = cal.get(Calendar.YEAR);
		} else {
			iAnio = cValue.get(Calendar.YEAR);
		}
		String sAnio = String.valueOf(iAnio);
		sBuffer.append(sAnio);
		if (sAnio.length() < FORMATO_DDMMYYYY) {
			sBuffer = new StringBuffer();
			sBuffer.append("20").append(sAnio);
		}
		return sBuffer.toString();
	}

	/**
	 * Metodo publico getAnioInt Si la instancia de la clase Fecha fue creada
	 * con parametros, devuelve el ano correspondiente a esa fecha, de lo
	 * contrario, devuelve el ano actual.
	 *
	 * @return int iAnio
	 * @throws FechaException
	 *             the fecha exception
	 */
	public int getAnioInt() throws FechaException {
		int iAnio;
		Calendar cValue = getFecha();
		Calendar cal = new GregorianCalendar();
		if (isFechaEmpty()) {
			iAnio = cal.get(Calendar.YEAR);
		} else {
			iAnio = cValue.get(Calendar.YEAR);
		}
		return iAnio;
	}

	/**
	 * Metodo publico getSeparadorFecha Devuelve el separador de hora.
	 *
	 * @return String sSeparadorFecha
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getSeparadorFecha() throws FechaException {
		return sSeparadorFecha;
	}

	/**
	 * Metodo privado getFecha Devuelve la fecha.
	 *
	 * @return Calendar
	 */
	private GregorianCalendar getFecha() {
		return cFecha;
	}

	/**
	 * Metodo publico getSeparadorHora Devuelve el separador de Hora.
	 *
	 * @return String sSeparadorHora
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getSeparadorHora() throws FechaException {
		return sSeparadorHora;
	}

	/**
	 * Metodo publico getHora Si la instancia de la clase Fecha fue creada con
	 * el parametro hora, devuelve la hora correspondiente a esa fecha, de lo
	 * contrario, devuelve el hora actual.
	 *
	 * @return String sHora
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getHora() throws FechaException {
		int iHora;
		Calendar cal = new GregorianCalendar();
		StringBuffer sBuffer = new StringBuffer();
		if (isFechaEmpty()) {
			iHora = cal.get(Calendar.HOUR_OF_DAY);
		} else {
			iHora = getFecha().get(Calendar.HOUR_OF_DAY);
		}
		String sHora = String.valueOf(iHora);
		sBuffer.append(sHora);
		if (sHora.length() < 2) {
			sBuffer = new StringBuffer();
			sBuffer.append("0").append(sHora);
		}
		return sBuffer.toString();
	}

	/**
	 * Metodo publico getMinutuos Si la instancia de la clase Fecha fue creada
	 * con el parametro minutos, devuelve los minutos correspondientes a la hora
	 * de la fecha, de lo contrario, devuelve los minutos de la hora actual.
	 *
	 * @return String sMin
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getMinutos() throws FechaException {
		int iMin;
		Calendar cal = new GregorianCalendar();
		StringBuffer sBuffer = new StringBuffer();
		if (isFechaEmpty()) {
			iMin = cal.get(Calendar.MINUTE);
		} else {
			iMin = getFecha().get(Calendar.MINUTE);
		}
		String sMin = String.valueOf(iMin);
		sBuffer.append(sMin);
		if (sMin.length() < 2) {
			sBuffer = new StringBuffer();
			sBuffer.append("0").append(sMin);
		}
		return sBuffer.toString();
	}

	/**
	 * Metodo publico getHora Si la instancia de la clase Fecha fue creada con
	 * el parametro segundos, devuelve los segundos de hora correspondiente a la
	 * fecha, de lo contrario, devuelve los segundos de la hora actual.
	 *
	 * @return String sSeg
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getSegundos() throws FechaException {
		int iSeg;
		Calendar cal = new GregorianCalendar();
		StringBuffer sBuffer = new StringBuffer();
		if (isFechaEmpty()) {
			iSeg = cal.get(Calendar.SECOND);
		} else {
			iSeg = getFecha().get(Calendar.SECOND);
		}
		String sSeg = String.valueOf(iSeg);
		sBuffer.append(sSeg);
		if (sSeg.length() < 2) {
			sBuffer = new StringBuffer();
			sBuffer.append("0").append(sSeg);
		}
		return sBuffer.toString();
	}

	/**
	 * Gets the mili segundos.
	 *
	 * @return the mili segundos
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getMiliSegundos() throws FechaException {
		int iSeg;
		Calendar cal = new GregorianCalendar();
		StringBuffer sBuffer = new StringBuffer();
		if (isFechaEmpty()) {
			iSeg = cal.get(Calendar.MILLISECOND);
		} else {
			iSeg = getFecha().get(Calendar.MILLISECOND);
		}
		String sSeg = String.valueOf(iSeg);
		sBuffer.append(sSeg);
		if (sSeg.length() < 2) {
			sBuffer = new StringBuffer();
			sBuffer.append("0").append(sSeg);
		}
		return sBuffer.toString();
	}

	/**
	 * Metodo publico getDDMMYYYY Devuelve la fecha del dia con el siguiente
	 * formato: DDsMMsYYYY donde s representa el caracter usado como separador
	 * Por defecto el separdor de fecha es -.
	 *
	 * @return String sFecha
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getDDMMYYYY() throws FechaException {
		String sFecha;
		sFecha = getDia() + getSeparadorFecha() + getMes() + getSeparadorFecha() + getAnio();
		return sFecha;
	}

	/**
	 * Checks if is fecha empty.
	 *
	 * @return true, if is fecha empty
	 * @throws FechaException
	 *             the fecha exception
	 */
	/*
	 * Metodo privado isFechaEmpty Devuelve un boolean indicando si la fecha
	 * esta vacia, es decir no fue creada con parametros. @return boolean true:
	 * fecha vacaa | false: fecha no vacia
	 * 
	 * @throws FechaException
	 */
	private boolean isFechaEmpty() throws FechaException {
		return (getFecha() == null);
	}

	/**
	 * Metodo publico getYYYYMMDD Devuelve la fecha del dia con el siguiente
	 * formato: YYYYsMMsDD donde s representa el caracter usado como separador
	 * Por defecto el separdor de fecha es -.
	 *
	 * @return String sFecha
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getYYYYMMDD() throws FechaException {
		String sFecha;
		sFecha = getAnio() + getSeparadorFecha() + getMes() + getSeparadorFecha() + getDia();
		return sFecha;
	}

	/**
	 * Metodo publico getDateTimeYYYYMMDDHHMMSS Devuelve la fecha y hora del dia
	 * con el siguiente formato: YYYYsMMsDD HHxMMxSS donde s representa el
	 * caracter usado como separador de fecha y x el separador de hora. Por
	 * defecto el separdor de fecha es - y el de hora es :.
	 *
	 * @return String sFecha
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getDateTimeYYYYMMDDHHMMSS() throws FechaException {
		String sFechaHora;
		StringBuffer sBuffer = new StringBuffer();
		// Obtengo la fecha y hora del dia
		sFechaHora = getYYYYMMDD();
		sBuffer.append(sFechaHora).append(" ").append(getTimeHHMMSS());
		return sBuffer.toString();
	}

	/**
	 * Metodo publico getDateTimeDDMMDDYYYYHHMMSS Devuelve la fecha del dia y
	 * hora con el siguiente formato: DDsMMsYYYY HHxMMxSS donde s representa el
	 * caracter usado como separador de fecha y x el separador de hora. Por
	 * defecto el separdor de fecha es - y el de hora es :.
	 *
	 * @return String sFecha
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getDateTimeDDMMYYYYHHMMSS() throws FechaException {
		String sFechaHora;
		StringBuffer sBuffer = new StringBuffer();
		// Obtengo la fecha y hora del dia
		sFechaHora = getDDMMYYYY();
		sBuffer.append(sFechaHora).append(" ").append(getTimeHHMMSS());
		return sBuffer.toString();
	}

	/**
	 * Metodo publico getDateTimeDDMMDDYYYYHHMMSSMIL Devuelve la fecha del dia y
	 * hora con el siguiente formato: DDsMMsYYYY HHxMMxSSxMIL donde s representa
	 * el caracter usado como separador de fecha y x el separador de hora. Por
	 * defecto el separdor de fecha es - y el de hora es :.
	 *
	 * @return String sFecha
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getDateTimeDDMMYYYYHHMMSSMIL() throws FechaException {
		String sFechaHora;
		StringBuffer sBuffer = new StringBuffer();
		// Obtengo la fecha y hora del dia
		sFechaHora = getDDMMYYYY();
		sBuffer.append(sFechaHora).append(" ").append(getTimeHHMMSS()).append(getSeparadorHora())
				.append(getMiliSegundos());
		return sBuffer.toString();
	}

	/**
	 * Metodo publico getDateTimeHHMMSS Devuelve la hora actual con el siguiente
	 * formato: HHxMMxSS donde x representa el caracter usado como separador de
	 * hora Por defecto el separdor de hora es :.
	 *
	 * @return String sFecha
	 * @throws FechaException
	 *             the fecha exception
	 */
	public String getTimeHHMMSS() throws FechaException {
		StringBuffer sBuffer = new StringBuffer();
		// Obtengo la fecha y hora del dia
		String sHora = getHora();
		String sMin = getMinutos();
		String sSeg = getSegundos();
		sBuffer.append(sHora).append(getSeparadorHora()).append(sMin).append(getSeparadorHora()).append(sSeg);
		return sBuffer.toString();
	}

	/**
	 * Metodo publico menorQue Compara dos las dos fechas y devuelve un boolean
	 * indicando si la fecha receptora es menor que la fecha pasada como
	 * parametro.
	 *
	 * @param fFecha
	 *            Fecha
	 * @return boolean
	 * @throws FechaException
	 *             the fecha exception
	 */
	public boolean menorQue(FechaUtils fFecha) throws FechaException {
		GregorianCalendar gFecha = new GregorianCalendar(fFecha.getAnioInt(), fFecha.getMesInt() - 1, fFecha.getDiaInt());
		return this.cFecha.before(gFecha);
	}

	/**
	 * Metodo publico mayorQue Compara dos las dos fechas y devuelve un boolean
	 * indicando si la fecha receptora es mayor que la fecha pasada como
	 * parametro.
	 *
	 * @param fFecha
	 *            Fecha
	 * @return boolean
	 * @throws FechaException
	 *             the fecha exception
	 */
	public boolean mayorQue(FechaUtils fFecha) throws FechaException {
		GregorianCalendar gFecha = new GregorianCalendar(fFecha.getAnioInt(), fFecha.getMesInt() - 1, fFecha.getDiaInt());
		return this.cFecha.after(gFecha);
	}

	/**
	 * Metodo publico add Para sumarle anos, meses o dias a la fecha receptora.
	 *
	 * @param iField
	 *            campo que indica que se desea sumar (por ejemplo Calendar.DATE)
	 * @param iCount
	 *            cantidad de dias, meses o anos
	 * @throws FechaException
	 *             the fecha exception
	 */
	public void add(int iField, int iCount) throws FechaException {
		if (this.isFechaEmpty()) {
			String sDay = this.getDia();
			String sMonth = this.getMes();
			String sYear = this.getAnio();
			setFecha(sDay, sMonth, sYear);
		}
		getFecha().add(iField, iCount);
	}

	/**
	 * Convert mes.
	 *
	 * @param sMonth
	 *            the s month
	 * @return the int
	 */
	public int convertMes(String sMonth) {
		int iMes = 0;
		String sMes = StringUtils.upperCase(sMonth);
		// En Ingles
		convertSiEneroAJunio(sMes, iMes);

		if ("JUL".equals(sMes)) {
			iMes = MES_7;
		}
		if (("AUG".equals(sMes)) || ("AGO".equals(sMes))) {
			iMes = MES_8;
		}
		if ("SEP".equals(sMes)) {
			iMes = MES_9;
		}
		if ("OCT".equals(sMes)) {
			iMes = MES_10;
		}
		if ("NOV".equals(sMes)) {
			iMes = MES_11;
		}
		if (("DEC".equals(sMes)) || ("DIC".equals(sMes))) {
			iMes = MES_12;
		}
		return iMes;
	}

	/**
	 * Convert si enero A junio.
	 *
	 * @param sMes
	 *            the s mes
	 * @param iMes
	 *            the i mes
	 */
	private void convertSiEneroAJunio(String sMes, int iMes) {
		if (("JAN".equals(sMes)) || ("ENE".equals(sMes))) {
			iMes = MES_1;
		}
		if ("FEB".equals(sMes)) {
			iMes = MES_2;
		}
		if ("MAR".equals(sMes)) {
			iMes = MES_3;
		}
		if (("APR".equals(sMes)) || ("ABR".equals(sMes))) {
			iMes = MES_4;
		}
		if ("MAY".equals(sMes)) {
			iMes = MES_5;
		}
		if ("JUN".equals(sMes)) {
			iMes = MES_6;
		}
	}

	/**
	 * Arreglar mes.
	 *
	 * @param sMonth
	 *            the s month
	 * @param bInput
	 *            the b input
	 * @return the string
	 */
	private String arreglarMes(String sMonth, boolean bInput) {
		int iMonth = Integer.parseInt(sMonth);

		if (bInput) {
			iMonth--;
		} else {
			iMonth++;
		}
		return Integer.toString(iMonth);
	}

	/**
	 * Arreglar mes.
	 *
	 * @param iMonth
	 *            the i month
	 * @param bInput
	 *            the b input
	 * @return the int
	 */
	private int arreglarMes(int iMonth, boolean bInput) {
		if (bInput) {
			return iMonth - 1;
		} else {
			return iMonth + 1;
		}
	}

	/**
	 * Convertir.
	 *
	 * @param fecha
	 *            the fecha
	 * @param fmtOrigen
	 *            the fmt origen
	 * @param fmtFinal
	 *            the fmt final
	 * @return the string
	 */
	public static String convertir(String fecha, int fmtOrigen, int fmtFinal) {

		String dia = "";
		String mes = "";
		String anio = "";
		String fechaFinal = fecha;

		if (!"".equals(fecha)) {
			// obtiene dia/mes/ano
			if (fmtOrigen == FORMATO_ORACLE_DDMMYYYY) {
				dia = fecha.substring(FORMATO_ORACLE_DDMMYYYY_DIA_ORIGEN, FORMATO_ORACLE_DDMMYYYY_DIA_DESTINO);
				mes = fecha.substring(FORMATO_ORACLE_DDMMYYYY_MES_ORIGEN, FORMATO_ORACLE_DDMMYYYY_MES_DESTINO);
				anio = fecha.substring(FORMATO_ORACLE_DDMMYYYY_ANIO);
			} else if (fmtOrigen == FORMATO_ORACLE_YYYYMMDD) {
				dia = fecha.substring(FORMATO_ORACLE_YYYYMMDD_DIA);
				mes = fecha.substring(FORMATO_ORACLE_YYYYMMDD_MES_ORIGEN, FORMATO_ORACLE_YYYYMMDD_MES_DESTINO);
				anio = fecha.substring(FORMATO_ORACLE_YYYYMMDD_ANIO_ORIGEN, FORMATO_ORACLE_YYYYMMDD_ANIO_DESTINO);
			} else if (fmtOrigen == FORMATO_YYYYMMDD) {
				dia = fecha.substring(FORMATO_YYYYMMDD_DIA);
				mes = fecha.substring(FORMATO_YYYYMMDD_MES_ORIGEN, FORMATO_YYYYMMDD_MES_DESTINO);
				anio = fecha.substring(FORMATO_YYYYMMDD_ANIO_ORIGEN, FORMATO_YYYYMMDD_ANIO_DESTINO);
			} else if (fmtOrigen == FORMATO_DDMMYYYY) {
				dia = fecha.substring(FORMATO_DDMMYYYY_DIA_ORIGEN, FORMATO_DDMMYYYY_DIA_DESTINO);
				mes = fecha.substring(FORMATO_DDMMYYYY_MES_ORIGEN, FORMATO_DDMMYYYY_MES_DESTINO);
				anio = fecha.substring(FORMATO_DDMMYYYY_ANIO);
			}

			// convierte
			if (fmtFinal == FORMATO_ORACLE_DDMMYYYY) {
				fechaFinal = dia + "-" + mes + "-" + anio;
			} else if (fmtFinal == FORMATO_ORACLE_YYYYMMDD) {
				fechaFinal = anio + "-" + mes + "-" + dia;
			} else if (fmtFinal == FORMATO_DDMMYYYY) {
				fechaFinal = dia + mes + anio;
			} else if (fmtFinal == FORMATO_YYYYMMDD) {
				fechaFinal = anio + mes + dia;
			}

		}

		return fechaFinal;
	}

	/** The mes org. */
	private String mesOrg = "";

	/** The anio org. */
	private String anioOrg = "";

	/**
	 * Gets the anio org.
	 *
	 * @return the anio org
	 */
	public String getAnioOrg() {
		return anioOrg;
	}

	/**
	 * Gets the mes org.
	 *
	 * @return the mes org
	 */
	public String getMesOrg() {
		return mesOrg;
	}

	/**
	 * Sets the anio org.
	 *
	 * @param string
	 *            the new anio org
	 */
	public void setAnioOrg(String string) {
		anioOrg = string;
	}

	/**
	 * Sets the mes org.
	 *
	 * @param string
	 *            the new mes org
	 */
	public void setMesOrg(String string) {
		mesOrg = string;
	}

	/**
	 * Gets the s class type.
	 *
	 * @return the sClassType
	 */
	public static String getsClassType() {
		return sClassType;
	}

	/**
	 * Sets the s class type.
	 *
	 * @param sClassType
	 *            the sClassType to set
	 */
	public static void setsClassType(String sClassType) {
		FechaUtils.sClassType = sClassType;
	}

	/**
	 * Gets the s class name.
	 *
	 * @return the sClassName
	 */
	public static String getsClassName() {
		return sClassName;
	}

	/**
	 * Sets the s class name.
	 *
	 * @param sClassName
	 *            the sClassName to set
	 */
	public static void setsClassName(String sClassName) {
		FechaUtils.sClassName = sClassName;
	}

	/**
	 * Metodo publico equals Compara dos las dos fechas y devuelve un boolean
	 * indicando si la fecha receptora es igual la fecha pasada como parametro.
	 *
	 * @param fFecha
	 *            Fecha
	 * @return boolean
	 * @throws FechaException
	 *             the fecha exception
	 */
	public boolean equalsFecha(FechaUtils fFecha) throws FechaException {
		boolean bResult = this.getAnio().equals(fFecha.getAnio());
		bResult = bResult & this.getMes().equals(fFecha.getMes());
		bResult = bResult & this.getDia().equals(fFecha.getDia());
		return bResult;
	}

	/**
	 * Fecha 1 mayor fecha 2.
	 *
	 * @param fecha1
	 *            the fecha 1
	 * @param fecha2
	 *            the fecha 2
	 * @return true, if successful
	 */
	public static boolean fecha1MayorFecha2(Date fecha1, Date fecha2) {
		return fecha1.getTime() >= fecha2.getTime();
	}

	/**
	 * Transformar formato fecha.
	 *
	 * @param fechaPago
	 *            en formato dd/mm/yyyy
	 * @return tranforma el formato de la fecha a yyyy-mm-dd
	 */
	public static String transformarFormatoFecha(String fechaPago) {
		String yyyy = fechaPago.substring(6, 10);
		String mm = fechaPago.substring(3, 5);
		String dd = fechaPago.substring(0, 2);

		return yyyy + "-" + mm + "-" + dd;
	}

	/**
	 * obtiene la fecha actual en el formato dd/MM/yyyy HH:mm.
	 *
	 * @return the string
	 */
	public static String obtenerFechaYHoraActual() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return dateFormat.format(new Date());
	}

	/**
	 * obtiene la fecha actual en el formato dd/MM/yyyy.
	 *
	 * @return the string
	 */
	public static String obtenerFechaActual() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(new Date());
	}

	/**
	 * Gets the fecha mas un dia.
	 *
	 * @param fecha
	 *            en formato dd/mm/yyyy
	 * @return fecha con un dia mas
	 * @throws ParseException
	 *             the parse exception
	 */
	public static String getFechaMasUnDia(String fecha) throws ParseException {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");

		Calendar cal = Calendar.getInstance();
		cal.setTime(simpleFormat.parse(fecha));
		cal.add(Calendar.DATE, 1);

		String fechaMasUno = simpleFormat.format(cal.getTime());
		return fechaMasUno;
	}

	
	
	/**
	 * Gets the fecha mas un año.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the fecha mas un año
	 * @throws ParseException
	 *             the parse exception
	 */
	public static String getFechaMasUnAño(String fecha) throws ParseException {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");

		Calendar cal = Calendar.getInstance();
		cal.setTime(simpleFormat.parse(fecha));
		cal.add(Calendar.YEAR, 1);

		String fechaMasUno = simpleFormat.format(cal.getTime());
		return fechaMasUno;
	}

	/**
	 * Parsear fecha nacimiento.
	 *
	 * @param fechaDeNacimiento
	 *            the fecha de nacimiento
	 * @param format
	 *            the format
	 * @return the date
	 */
	public static Date parsearFechaNacimiento(String fechaDeNacimiento, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date fecha;

		if (StringUtils.isNotBlank(fechaDeNacimiento)) {
			try {
				fecha = df.parse(fechaDeNacimiento);
			} catch (ParseException e) {
				fecha = null;
			}
		} else {
			fecha = null;
		}

		return fecha;
	}

	/**
	 * modifica el formato de fecha, si hay error de parseo returna EmptyString.
	 *
	 * @param dateStr
	 *            the date str
	 * @param formatoOrigen
	 *            the formato origen
	 * @param formatoDestino
	 *            the formato destino
	 * @return the string
	 */
	public static String modificarFormatoFechas(String dateStr, String formatoOrigen, String formatoDestino) {

		DateFormat fromFormat = new SimpleDateFormat(formatoOrigen);
		fromFormat.setLenient(false);
		DateFormat toFormat = new SimpleDateFormat(formatoDestino);
		toFormat.setLenient(false);
		Date date;
		try {

			date = fromFormat.parse(dateStr);
			return toFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	}

	/**
	 * parsea la fecha si no es null.
	 *
	 * @param fechaNacimiento
	 *            the fecha nacimiento
	 * @return the string
	 */
	public static String parsearFechaDeNacimientoParaIATX(Date fechaNacimiento) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		if (fechaNacimiento == null) {
			return null;
		}
		return df.format(fechaNacimiento);
	}

	/**
	 * Parsear mes separador anio.
	 *
	 * @param fecha
	 *            the fecha
	 * @param separador
	 *            the separador
	 * @return the string
	 */
	public static String parsearMesSeparadorAnio(Date fecha, String separador) {

		String mesAnio = StringUtils.EMPTY;

		try {

			DateFormatSymbols sym = DateFormatSymbols.getInstance(new Locale("es", "ar"));
			sym.setShortMonths(new String[] { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto",
					"septiembre", "octubre", "noviembre", "diciembre" });
			SimpleDateFormat date = new SimpleDateFormat("MMM", sym);
			String mes = date.format(fecha);
			date = new SimpleDateFormat("yyyy", sym);
			String anio = date.format(fecha);
			mesAnio = mes + separador + anio;

		} catch (RuntimeException e) {
			LOGGER.info("Error al formatear fecha anio");
		}
		return mesAnio;
	}
	
	/**
	 * Devuelve la fecha de hoy menos la cantidad de dias indicada por
	 * parametro.
	 *
	 * @param diasAtras
	 *            the dias atras
	 * @return the date
	 */
	public static Date obtenerFechaDesde(int diasAtras) {
		Calendar cal = Calendar.getInstance();
		diasAtras = diasAtras * -1;
		cal.add(Calendar.DATE, diasAtras);
		Date desdeFecha = cal.getTime();
		return desdeFecha;
	}

	/**
	 * Calcular antiguedad. Diferencia en dias entre la fecha actual menos la
	 * fecha de agendamiento.
	 *
	 * @param fechaCreacionDestinatario
	 *            the fecha creacion destinatario
	 * @return the string diferencia
	 */
	public static String calcularAntiguedad(String fechaCreacionDestinatario) {
		Calendar fechaActual = Calendar.getInstance(); 
		Calendar fechaCreacionDelDestinatario = Calendar.getInstance();
		
		fechaActual.setTime(new Date());
		
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDhhminsecmsec);
		try {
			Date d = sdf.parse(fechaCreacionDestinatario);
			fechaCreacionDelDestinatario.setTime(d);
		} catch (ParseException e) {
			LOGGER.info(ERROR_PARSEO_FECHA_CREACION_DESTINATARIO);
			e.printStackTrace();
		}

	    if (fechaActual.get(Calendar.YEAR) == fechaCreacionDelDestinatario.get(Calendar.YEAR)) {	    	
	        return new Integer(Math.abs(fechaActual.get(Calendar.DAY_OF_YEAR) - fechaCreacionDelDestinatario.get(Calendar.DAY_OF_YEAR))).toString();
	    } else {
	        if (fechaCreacionDelDestinatario.get(Calendar.YEAR) > fechaActual.get(Calendar.YEAR)) {
	            // swap para hacer la resta
	            Calendar temp = fechaActual;
	            fechaActual = fechaCreacionDelDestinatario;
	            fechaCreacionDelDestinatario = temp;
	        }
	        int extraDiasXBisiestos = 0;

	        int dayOneOriginalYearDays = fechaActual.get(Calendar.DAY_OF_YEAR);

	        while (fechaActual.get(Calendar.YEAR) > fechaCreacionDelDestinatario.get(Calendar.YEAR)) {
	            fechaActual.add(Calendar.YEAR, -1);
	            // getActualMaximum() años biciestos
	            extraDiasXBisiestos += fechaActual.getActualMaximum(Calendar.DAY_OF_YEAR);
	        }

	        return new Integer(extraDiasXBisiestos - fechaCreacionDelDestinatario.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays).toString() ;
	    }
	}
	
    
    /**
	 * Obtiene la fecha en el formato YYYYMMDDhhmmssff2 siendo ff2 dos
	 * posiciones para milisegundos.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the date time YYYYMMD dhhmmssff 2
	 */
    public static String getDateTimeYYYYMMDDhhmmssff2(Date fecha) {
        StringBuilder builder = new StringBuilder();
        Calendar calendar = GregorianCalendar.getInstance(); 
        calendar.setTime(fecha);
        builder.append(String.valueOf(calendar.get(Calendar.YEAR)));
        builder.append(StringUtils.leftPad(String.valueOf(calendar.get(Calendar.MONTH) + 1), 2, "0"));
        builder.append(StringUtils.leftPad(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2, "0"));
        builder.append(StringUtils.leftPad(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2, "0"));
        builder.append(StringUtils.leftPad(String.valueOf(calendar.get(Calendar.MINUTE)), 2, "0"));
        builder.append(StringUtils.leftPad(String.valueOf(calendar.get(Calendar.SECOND)), 2, "0"));
        builder.append(String.valueOf(calendar.get(Calendar.MILLISECOND)).substring(0, 2));
        return builder.toString();
    }

    /**
     * Suma la cantidad indicada a la fecha actual
     * retornando la fecha calculada
     * @param cantidadMaximaDias
     * @return
     */
    public static DateTime obtenerFechaPlusCantDias(int cantidadMaximaDias) {
    	DateTime fecha = new DateTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha.toDate());

		return fecha.plusDays(cantidadMaximaDias);
    }
    
    public static String obtenerFechaFormateada(Date fecha, String formato) {
    	SimpleDateFormat df = new SimpleDateFormat(formato);
		if (fecha == null) {
			return null;
		}
		return df.format(fecha);
    }
    
    /**
     * Determina si la fecha actual se encuentra en un rango determinado 
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    public static boolean between(Date fechaInicio, Date fechaFin) {
    	
		Calendar fechaActual = Calendar.getInstance();
		
		if(fechaFin instanceof java.sql.Date) {
			long fecha = new Date().getTime();
			Date now = new java.sql.Date(fecha);
			fechaActual.setTime(now);
		}
		
		fechaActual.set(Calendar.HOUR_OF_DAY, 0);
		fechaActual.set(Calendar.MINUTE, 0);
		fechaActual.set(Calendar.SECOND, 0);
		fechaActual.set(Calendar.MILLISECOND, 0);
		
    	if(fechaInicio.getTime() <= fechaActual.getTimeInMillis() && fechaActual.getTimeInMillis() <= fechaFin.getTime()) {
			return true;
		}
		return false;
    }
}
