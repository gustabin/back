/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;
import ar.com.santanderrio.obp.servicios.billetera.dto.ConsultaCuentaDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.CuentaBilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.MedioDePagoBilleteraDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class BilleteraUtils.
 */
public final class BilleteraUtils {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BilleteraUtils.class);

    /** The Constant TIPOSDOCBANELCO. */
    private static final Map<String, String> TIPOSDOCBANELCO;

    static {
        Map<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("N", "DNI");
        tempMap.put("X", "DNI");
        tempMap.put("C", "LC");
        tempMap.put("E", "LE");
        tempMap.put("I", "CI");
        tempMap.put("P", "PAS");
        TIPOSDOCBANELCO = Collections.unmodifiableMap(tempMap);
    }

    /**
     * Verifica la edad del cliente.
     *
     * @param cliente
     *            the cliente
     * @return true, if successful
     * @throws ParseException
     *             the parse exception
     */
    public static boolean checkEdad(Cliente cliente) throws ParseException {
        if (getEdad(cliente) < BilleteraConstants.EDAD_MINIMA) {
            LOGGER.error("Error de acceso - Menor de 18 años");
            return false;
        }
        return true;
    }

    /**
     * Obtiene cuenta a partir de numero de tarjeta.
     *
     * @param cliente
     *            Cliente
     * @param nroTrj
     *            Numero de tarjeta a buscar
     * @return Datos de cliente (RawClientData)
     */
    public static Cuenta getCtaFromNroTrj(Cliente cliente, String nroTrj) {
        for (Cuenta cuenta : cliente.getCuentas()) {
            String nroT = ISBANStringUtils.eliminarCeros(cuenta.getNroTarjetaCredito());
            if (nroT.equals(nroTrj)) {
                return cuenta;
            }
        }
        return null;
    }

    /**
     * Obtiene cuentas a partir de un listado de tipos de cuenta.
     *
     * @param cliente
     *            Cliente asociado
     * @param tiposCtaLst
     *            Lista de tipos de cuenta admitidos
     * @return Cuentas de los tipos indicados
     */
    public static List<Cuenta> getCtas(Cliente cliente, List<String> tiposCtaLst) {
        List<Cuenta> ctas = new ArrayList<Cuenta>();
        for (int i = 0; i < cliente.getCuentas().size(); i++) {
            Cuenta cuenta = cliente.getCuentas().get(i);
            if (tiposCtaLst.contains(cuenta.getTipoCuenta())) {
                boolean cajaAtesoramiento = BilleteraConstants.TIPOCTA_CAD.equals(cuenta.getTipoCuenta())
                        && BilleteraConstants.PROD_ALTAIR_03.equals(cuenta.getProductoAltair())
                        && BilleteraConstants.SUBPROD_ALTAIR_0001.equals(cuenta.getSubproductoAltair());
                boolean cajaSucBp = BilleteraConstants.SUC_BP_1.equals(cuenta.getNroSucursal())
                        || BilleteraConstants.SUC_BP_2.equals(cuenta.getNroSucursal());
                if (cajaAtesoramiento || cajaSucBp) {
                    // Excluye cajas de ahorro especiales para atesoramiento
                    // No se deberán visualizar las cuentas que pertenezcan a
                    // las IDECLTSDO.nro_sucursal 250 y 251
                    continue;
                } else {
                    ctas.add(cuenta);
                }
            }
        }
        return ctas;
    }

    /**
     * Obtiene Map conteniendo la cuentas que estan en Prisma y en el cliente,
     * aquellas que solo estan en prisma y las que posee exclusivamente el
     * cliente.
     *
     * @param cliente
     *            Datos de Cliente
     * @param tiposTrj
     *            the tipos trj
     * @param idCuenta
     *            Identificador de cuenta TodoPag
     * @param consultaCuentaDTO
     *            the consulta cuenta DTO
     * @return Mapa conteniendo las cuentas resultantes
     */
    public static Map<String, List<MedioDePagoBilleteraDTO>> getCtasPrCs(Cliente cliente, String tiposTrj,
            String idCuenta, ConsultaCuentaDTO consultaCuentaDTO) {
        List<MedioDePagoBilleteraDTO> mediosPagoPr = getMediosPago(consultaCuentaDTO, idCuenta, "S");
        List<MedioDePagoBilleteraDTO> lstPrOb = getMediosPago(consultaCuentaDTO, idCuenta, "N");
        Map<String, List<MedioDePagoBilleteraDTO>> cuentasMap = new HashMap<String, List<MedioDePagoBilleteraDTO>>();
        List<MedioDePagoBilleteraDTO> lstPrCs = new ArrayList<MedioDePagoBilleteraDTO>();
        List<MedioDePagoBilleteraDTO> lstNoPrCs = new ArrayList<MedioDePagoBilleteraDTO>();
        List<MedioDePagoBilleteraDTO> lstPrNoCs = new ArrayList<MedioDePagoBilleteraDTO>();

        List<Cuenta> ctasTrj = getCtasTrj(cliente, Arrays.asList(tiposTrj.split("\\|")));

        fillListsCs(mediosPagoPr, lstPrCs, lstNoPrCs, ctasTrj);

        fillListNoCs(mediosPagoPr, lstPrNoCs, ctasTrj);

        cuentasMap.put(BilleteraConstants.LST_PR_CS, lstPrCs);
        cuentasMap.put(BilleteraConstants.LST_NOPR_CS, lstNoPrCs);
        cuentasMap.put(BilleteraConstants.LST_PR_NOCS, lstPrNoCs);
        cuentasMap.put(BilleteraConstants.LST_PR_OB, lstPrOb);

        return cuentasMap;
    }

    /**
     * Obtiene cuentas a partir de un listado de tipos de tarjeta.
     *
     * @param cliente
     *            Cliente asociado
     * @param tiposTrjLst
     *            Lista de tipos de tarjeta admitidos
     * @return Cuentas de los tipos indicados
     */
    public static List<Cuenta> getCtasTrj(Cliente cliente, List<String> tiposTrjLst) {
        List<Cuenta> ctasTrj = new ArrayList<Cuenta>();
        for (int j = 0; j < tiposTrjLst.size(); j++) {
            /* Incluye clase cuenta si existe */
            String tipoTrjCompleto = tiposTrjLst.get(j);
            /* Solo tipo de cuenta (N2) */
            String tipoTrj = tipoTrjCompleto.substring(0, 2);

            for (int i = 0; i < cliente.getCuentas().size(); i++) {
                Cuenta cuenta = cliente.getCuentas().get(i);
                if (tipoTrj.equals(cuenta.getTipoCuenta()) && checkClaseCta(tipoTrjCompleto, cuenta)
                        && "TI".equals(cuenta.getCodigoTitularidad())) {
                    addTrj(tipoTrj, cuenta, ctasTrj);
                }
            }
            for (int i = 0; i < cliente.getCuentas().size(); i++) {
                Cuenta cuenta = cliente.getCuentas().get(i);
                if (tipoTrj.equals(cuenta.getTipoCuenta()) && checkClaseCta(tipoTrjCompleto, cuenta)
                        && "AD".equals(cuenta.getCodigoTitularidad())) {
                    addTrj(tipoTrj, cuenta, ctasTrj);
                }
            }
        }
        return ctasTrj;
    }

    /**
     * Obtiene descripcion de la cuenta.
     *
     * @param tipoCta
     *            Tipo de cuenta
     * @return Descripcion de la cuenta
     */
    public static String getDescCta(String tipoCta) {
        String desc = TipoCuenta.fromCodigo(tipoCta).getDescripcion();
        if (BilleteraConstants.TIPOCTA_CUP.equals(tipoCta) || BilleteraConstants.TIPOCTA_CUD.equals(tipoCta)) {
            desc = BilleteraConstants.CUENTA_UNICA;
        }
        return desc;
    }

    /**
     * Obtiene la edad de la persona.
     *
     * @param cliente
     *            the cliente
     * @return Edad obtenida
     * @throws ParseException
     *             En caso de error
     */
    public static int getEdad(Cliente cliente) throws ParseException {
        String fn = cliente.getFechaNacimiento();
        SimpleDateFormat sdf = new SimpleDateFormat(BilleteraConstants.FMT_YYYYMMDD);
        Date dtNac = sdf.parse(fn);
        Calendar calFn = Calendar.getInstance();
        calFn.setTime(dtNac);
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR) - calFn.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH) - calFn.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DATE) - calFn.get(Calendar.DATE);
        if (mes < 0 || (mes == 0 && dia < 0)) {
            anio--;
        }
        return anio;
    }

    /**
     * Obtiene fecha de vencimiento de tarjeta.
     *
     * @param cbu
     *            CBU de la cuenta asociada
     * @param showBar
     *            Indica si se debe presentar barra en la fecha a obtener
     * @return Fecha de vencimiento obtenida en formato MMYYYY o MM/YYYY
     * @throws ParseException
     *             En caso de error
     */
    public static String getFechaVtoTrj(String cbu, boolean showBar) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(BilleteraConstants.FMT_YYMM);
        SimpleDateFormat sdf2 = new SimpleDateFormat(
                showBar ? BilleteraConstants.FMT_MMYYYY_BAR : BilleteraConstants.FMT_MMYYYY);
        String vtoTrj = cbu.substring(BilleteraConstants.OFF_VTO_INI, BilleteraConstants.OFF_VTO_FIN);
        return "0000".equals(vtoTrj) ? (showBar ? "12/2020" : "122020") : sdf2.format(sdf.parse(vtoTrj));
    }

    /**
     * Obtiene fecha de vencimiento de tarjeta para presentacion.
     *
     * @param cbu
     *            CBU de la cuenta asociada
     * @return Fecha de vencimiento obtenida en formato MMMM YYYY
     * @throws ParseException
     *             En caso de error
     */
    public static String getFechaVtoTrjDisplay(String cbu) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(BilleteraConstants.FMT_YYMM);
        SimpleDateFormat sdf2 = new SimpleDateFormat(BilleteraConstants.FMT_MMMMYYYY, new Locale("es", "AR"));
        String vtoTrj = cbu.substring(BilleteraConstants.OFF_VTO_INI, BilleteraConstants.OFF_VTO_FIN);
        return "0000".equals(vtoTrj) ? "diciembre 2020" : sdf2.format(sdf.parse(vtoTrj));
    }

    /**
     * Obtiene la marca de la tarjeta.
     *
     * @param cuenta
     *            Cuenta asciada a la tarjeta
     * @return Marca de la tarjeta
     */
    public static String getMarcaTrj(Cuenta cuenta) {
        String ret = BilleteraConstants.TRJ_MARCA_VISA;
        if (BilleteraConstants.TIPOCTA_AMEX.equals(cuenta.getTipoCuenta())) {
            ret = BilleteraConstants.TRJ_MARCA_AMEX;
        } else if (BilleteraConstants.TIPOCTA_DEB.equals(cuenta.getTipoCuenta())) {
            ret = BilleteraConstants.TRJ_MARCA_DEBITO;
        }
        return ret;
    }

    /**
     * Obtiene medio de pago de un listado a partir del numero de tarjeta.
     *
     * @param lst
     *            Lista de Medios de Pago
     * @param nroTrj
     *            Numero de tarjeta a buscar
     * @return Medio de pago obtenido
     */
    public static MedioDePagoBilleteraDTO getMedioPago(List<MedioDePagoBilleteraDTO> lst, String nroTrj) {
        Iterator<MedioDePagoBilleteraDTO> itMediosPago = lst.iterator();
        while (itMediosPago.hasNext()) {
            MedioDePagoBilleteraDTO mp = itMediosPago.next();
            if (nroTrj.equals(mp.getNumeroTarjeta())) {
                return mp;
            }
        }
        return null;
    }

    /**
     * Obtiene nombre de tarjeta.
     *
     * @param cuenta
     *            Cuenta asociada
     * @return Nombre de tarjeta (a partir del tipo de cuenta/clase de cuenta)
     */
    public static String getNombreTrj(Cuenta cuenta) {
        String tipoCta = cuenta.getTipoCuenta();
        String claseCta = cuenta.getClaseCuenta();
        if (BilleteraConstants.TIPOCTA_DEB.equals(tipoCta)) {
            return BilleteraConstants.TRJ_DEB;
        } else if (isVisaRec(tipoCta, claseCta)) {
            return BilleteraConstants.TRJ_VISA_REC;
        } else if (isVisa(tipoCta, claseCta)) {
            return BilleteraConstants.TRJ_VISA;
        } else if (BilleteraConstants.TIPOCTA_AMEX.equals(tipoCta)) {
            return BilleteraConstants.TRJ_AMEX;
        }
        return StringUtils.EMPTY;
    }

    /**
     * Obtiene Numero de Tarjeta formateado a partir del tipo de cuenta.
     *
     * @param nroTrj
     *            Numero de tarjeta
     * @param tipoCta
     *            Tipo de cuenta
     * @return Numero de tarjeta formateado
     */
    public static String getNroTrjFmt(String nroTrj, String tipoCta) {
        if (BilleteraConstants.TIPOCTA_DEB.equals(tipoCta) || BilleteraConstants.TIPOCTA_VISA.equals(tipoCta)) {
            return BilleteraConstants.MASK_XXXX + nroTrj.substring(nroTrj.length() - BilleteraConstants.OFF_VISA);
        } else if (BilleteraConstants.TIPOCTA_AMEX.equals(tipoCta)) {
            String numeroTarjeta = ISBANStringUtils.eliminarCeros(nroTrj);
            numeroTarjeta = numeroTarjeta.substring(0, BilleteraConstants.LEN_AMEX);
            return BilleteraConstants.MASK_XXXX
                    + numeroTarjeta.substring(numeroTarjeta.length() - BilleteraConstants.OFF_AMEX);
        }
        return StringUtils.EMPTY;
    }

    /**
     * Formatea fecha de vencimiento.
     *
     * @param fecha
     *            Fecha a procesar
     * @return Fecha en formato MMMM YYYY
     * @throws ParseException
     *             En caso de error
     */
    public static String getVtoTrjDisplay(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(BilleteraConstants.FMT_MMYYYY);
        SimpleDateFormat sdf2 = new SimpleDateFormat(BilleteraConstants.FMT_MMMMYYYY, new Locale("es", "AR"));
        return sdf2.format(sdf.parse(fecha));
    }

    /**
     * Obtiene hash.
     *
     * @param str
     *            String a procesar
     * @return Hash MD5
     */
    public static synchronized String hash(String str) {
        byte[] param;
        try {
            param = str.getBytes(BilleteraConstants.UTF8_CHARSET.name());
        } catch (UnsupportedEncodingException e) {
            throw new ISBANRuntimeException(e);
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new ISBANRuntimeException(e);
        }
        md5.update(param);

        return toHex(md5.digest());
    }

    /**
     * Verifica si la fecha de vencimiento de un medio de pago es anterior a la
     * fecha actual.
     *
     * @param mp
     *            Medio de Pago a procesar
     * @return true si la fecha de vencimiento es anterior a la fecha actual,
     *         false en caso contrario
     */
    public static boolean isFechaVencMpMenorHoy(MedioDePagoBilleteraDTO mp) {
        String fechaVenc = null;
        Date dtFv = null;
        try {
            if (BilleteraConstants.MP_VENCIDO.equals(mp.getEstadoMedioPago())) {
                fechaVenc = getFechaVtoTrj(mp.getCtaAsociada().getCbu(), false);
            } else {
                fechaVenc = mp.getFechaVencimiento();
            }
            dtFv = new SimpleDateFormat(BilleteraConstants.FMT_MMYYYY).parse(fechaVenc);
            return dtFv.before(getUltDiaMes());
        } catch (ParseException e) {
            LOGGER.error("Error al procesar fecha de vencimiento: " + fechaVenc, e);
            return false;
        }
    }

    /**
     * Llenar con ceros derecha.
     *
     * @param s
     *            the s
     * @param size
     *            the size
     * @return the string
     */
    public static String llenarConCerosDerecha(String s, int size) {
        int l = s.length();
        if (l >= size) {
            return s.substring(l - size, l);
        }
        StringBuilder ceros = new StringBuilder();
        for (int n = 0; n < size - l; ++n) {
            ceros.append("0");
        }
        return s + ceros;
    }

    /**
     * Tipo doc banelco.
     *
     * @param tipoDocIatx
     *            the tipo doc iatx
     * @return the string
     */
    public static String tipoDocBanelco(String tipoDocIatx) {
        return TIPOSDOCBANELCO.get(tipoDocIatx);
    }

    /**
     * Determina las tarjetas a preentar en pantalla de adhesión/modificación.
     *
     * @param cuenta
     *            the cuenta
     * @return true, if successful
     */
    public static boolean trjMostrar(Cuenta cuenta) {
        boolean mostrarVisa = BilleteraConstants.TIPOCTA_VISA.equals(cuenta.getTipoCuenta())
                && (estadoTrjPermitido(cuenta.getEstadoTarjetaCredito(), cuenta.getCbu())
                        || estadoEsp(cuenta.getEstadoTarjetaCredito()));
        boolean mostrarAmex = BilleteraConstants.TIPOCTA_AMEX.equals(cuenta.getTipoCuenta())
                && (estadoTrjPermitido(cuenta.getEstadoTarjetaCredito(), cuenta.getCbu())
                        || estadoEsp(cuenta.getEstadoTarjetaCredito()));
        boolean mostrarDebito = BilleteraConstants.TIPOCTA_DEB.equals(cuenta.getTipoCuenta())
                && BilleteraConstants.ESTADO_ACTIVA_BANELCO.equals(cuenta.getEstadoTarjetaCredito());
        if (mostrarVisa || mostrarAmex || mostrarDebito) {
            return true;
        }
        return false;
    }

    /**
     * Adds the medios pago.
     *
     * @param flagMpBanco
     *            the flag mp banco
     * @param mediosPago
     *            the medios pago
     * @param cuentaBilleteraDTO
     *            the cuenta billetera DTO
     */
    private static void addMediosPago(String flagMpBanco, List<MedioDePagoBilleteraDTO> mediosPago,
            CuentaBilleteraDTO cuentaBilleteraDTO) {
        for (MedioDePagoBilleteraDTO mpDTO : cuentaBilleteraDTO.getMediosDePago()) {
            if (flagMpBanco.equals(mpDTO.getFlagMedioPagoBanco())) {
                MedioDePagoBilleteraDTO mp = new MedioDePagoBilleteraDTO();
                BeanUtils.copyProperties(mpDTO, mp);
                if ("S".equals(mpDTO.getFlagMedioPagoBanco())) {
                    mp.setNumeroTarjeta(llenarConCerosDerecha(mpDTO.getNumeroTarjeta(), BilleteraConstants.LEN_VISA));
                }
                mediosPago.add(mp);
            }
        }
    }

    /**
     * Agrega una tarjeta a una lista segun el tipo de cuenta y el estado de
     * tarjeta.
     *
     * @param tipoCta
     *            Tipo de cuenta procesar
     * @param cuenta
     *            Cuenta a agregar
     * @param ctasTrj
     *            Lista de tarjetas en proceso
     */
    private static void addTrj(String tipoCta, Cuenta cuenta, List<Cuenta> ctasTrj) {
        if (BilleteraConstants.TIPOCTA_VISA.equals(tipoCta) || BilleteraConstants.TIPOCTA_AMEX.equals(tipoCta)
                || BilleteraConstants.TIPOCTA_DEB.equals(tipoCta)) {
            ctasTrj.add(cuenta);
        }
    }

    /**
     * Verifica Clase Cuenta.
     *
     * @param tipoTrjCompleto
     *            the tipo trj completo
     * @param cuenta
     *            the cuenta
     * @return true si se verifica la clase de cuenta
     */
    private static boolean checkClaseCta(String tipoTrjCompleto, Cuenta cuenta) {
        if (tipoTrjCompleto.length() == 3) {
            String claseCtaToChk = tipoTrjCompleto.substring(2, 3);
            if (BilleteraConstants.CLASE_CUENTA_RECARGABLE.equals(claseCtaToChk)) {
                // Verifica que clase cuenta sea igual a "T"
                return BilleteraConstants.CLASE_CUENTA_RECARGABLE.equals(cuenta.getClaseCuenta().trim());
            } else {
                // Verifica que clase cuenta sea distinto de "T"
                return !BilleteraConstants.CLASE_CUENTA_RECARGABLE.equals(cuenta.getClaseCuenta().trim());
            }
        } else {
            // No se verifica clase cuenta si no se especifica
            // en la lista de tipos de cuenta configurada
            return true;
        }
    }

    /**
     * Determina si el estado de tarjeta de credito es 25 o 29.
     *
     * @param estadoTarjetaCredito
     *            the estado tarjeta credito
     * @return true si el estado de tarjeta de credito es 25 o 29
     */
    private static boolean estadoEsp(String estadoTarjetaCredito) {
        if (BilleteraConstants.ESTADO_PROBLEMA.equals(estadoTarjetaCredito)
                || BilleteraConstants.ESTADO_CERRADA.equals(estadoTarjetaCredito)) {
            return true;
        }
        return false;
    }

    /**
     * Determina si un estado de tarjeta Visa/Amex es valido para presentar la
     * tarjeta en la operacion.
     *
     * @param estadoTrj
     *            Estado de tarjeta
     * @param cbu
     *            CBU asociado a la cuenta
     * @return true si el estado de tarjeta es admitido para presentar la
     *         tarjeta
     */
    private static boolean estadoTrjPermitido(String estadoTrj, String cbu) {
        boolean estadoActiva = BilleteraConstants.ESTADO_ACTIVA.equals(estadoTrj);
        boolean estadoNoRenovarActiva = BilleteraConstants.ESTADO_NORENOVAR.equals(estadoTrj)
                && isFechaVencMayorIgualHoy(cbu);
        if (estadoActiva || estadoNoRenovarActiva) {
            return true;
        }
        return false;
    }

    /**
     * Fill list no cs.
     *
     * @param mediosPagoPr
     *            the medios pago pr
     * @param lstPrNoCs
     *            the lst pr no cs
     * @param ctasTrj
     *            the ctas trj
     */
    private static void fillListNoCs(List<MedioDePagoBilleteraDTO> mediosPagoPr,
            List<MedioDePagoBilleteraDTO> lstPrNoCs, List<Cuenta> ctasTrj) {
        for (MedioDePagoBilleteraDTO mpb : mediosPagoPr) {
            boolean found = false;
            for (Cuenta cuenta : ctasTrj) {
                String nroT = ISBANStringUtils.eliminarCeros(cuenta.getNroTarjetaCredito());
                if (mpb.getNumeroTarjeta().equals(nroT)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                lstPrNoCs.add(mpb);
            }
        }
    }

    /**
     * Fill lists cs.
     *
     * @param mediosPagoPr
     *            the medios pago pr
     * @param lstPrCs
     *            the lst pr cs
     * @param lstNoPrCs
     *            the lst no pr cs
     * @param ctasTrj
     *            the ctas trj
     */
    private static void fillListsCs(List<MedioDePagoBilleteraDTO> mediosPagoPr, List<MedioDePagoBilleteraDTO> lstPrCs,
            List<MedioDePagoBilleteraDTO> lstNoPrCs, List<Cuenta> ctasTrj) {
        for (Cuenta cuenta : ctasTrj) {
            boolean found = false;
            MedioDePagoBilleteraDTO mp = null;
            String nroT = ISBANStringUtils.eliminarCeros(cuenta.getNroTarjetaCredito());
            for (MedioDePagoBilleteraDTO mpb : mediosPagoPr) {
                if (mpb.getNumeroTarjeta().equals(nroT)) {
                    mp = mpb;
                    found = true;
                    break;
                }
            }
            if (found) {
                mp.setCtaAsociada(cuenta);
                lstPrCs.add(mp);
            } else {
                mp = new MedioDePagoBilleteraDTO();
                mp.setCtaAsociada(cuenta);
                lstNoPrCs.add(mp);
            }
        }
    }

    /**
     * Obtiene medios de pago a partir de section resultante de consulta.
     *
     * @param consultaCuentaDTO
     *            Contiene los registros a procesar
     * @param idCuenta
     *            Identificador de cuenta TodoPago a buscar
     * @param flagMpBanco
     *            FlagMedioPagoBanco de los medios de pago a recuperar
     * @return Lista de medios de pago de la cuenta indicada
     */
    private static List<MedioDePagoBilleteraDTO> getMediosPago(ConsultaCuentaDTO consultaCuentaDTO, String idCuenta,
            String flagMpBanco) {
        List<MedioDePagoBilleteraDTO> mediosPago = new ArrayList<MedioDePagoBilleteraDTO>();
        for (CuentaBilleteraDTO cuentaBilleteraDTO : consultaCuentaDTO.getCuentas()) {
            String idCta = cuentaBilleteraDTO.getIdCuenta();
            if (idCta.equals(idCuenta)) {
                addMediosPago(flagMpBanco, mediosPago, cuentaBilleteraDTO);
            }
        }
        return mediosPago;
    }

    /**
     * Obtiene el ultimo dia del mes.
     *
     * @return Date correspondiente al ultimo dia del mes
     */
    private static Date getUltDiaMes() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return cal.getTime();
    }

    /**
     * Verifica si la fecha de vencimiento de una cuenta (extraida del cbu) es
     * posterior o igual a la fecha actual.
     *
     * @param cbu
     *            CBU de la cuenta a procesar
     * @return true si la fecha de vencimiento de la cuenta es posterior a la
     *         fecha actual, false en caso contrario
     */
    private static boolean isFechaVencMayorIgualHoy(String cbu) {
        String fechaVenc = null;
        Date dtFv = null;
        try {
            fechaVenc = getFechaVtoTrj(cbu, false);
            dtFv = new SimpleDateFormat(BilleteraConstants.FMT_MMYYYY).parse(fechaVenc);
        } catch (ParseException e) {
            LOGGER.error("Error al procesar fecha de vencimiento: {}", fechaVenc, e);
            return false;
        }
        return !dtFv.before(getUltDiaMes());
    }

    /**
     * Checks if is visa.
     *
     * @param tipoCta
     *            the tipo cta
     * @param claseCta
     *            the clase cta
     * @return true, if is visa
     */
    private static boolean isVisa(String tipoCta, String claseCta) {
        return BilleteraConstants.TIPOCTA_VISA.equals(tipoCta)
                && !BilleteraConstants.CLASE_CUENTA_RECARGABLE.equals(claseCta.trim());
    }

    /**
     * Checks if is visa rec.
     *
     * @param tipoCta
     *            the tipo cta
     * @param claseCta
     *            the clase cta
     * @return true, if is visa rec
     */
    private static boolean isVisaRec(String tipoCta, String claseCta) {
        return BilleteraConstants.TIPOCTA_VISA.equals(tipoCta)
                && BilleteraConstants.CLASE_CUENTA_RECARGABLE.equals(claseCta.trim());
    }

    /**
     * Convierte a hex string.
     *
     * @param digest
     *            Datos a procesar
     * @return String resultante
     */
    private static String toHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; ++i) {
            String hex = Integer.toHexString((int) digest[i] & BilleteraConstants.MASK);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * Instantiates a new billetera utils.
     */
    private BilleteraUtils() {
    }

    /**
     * Obtiene medios de pago con tipo de novedad M.
     *
     * @param ctasPrCs
     *            the ctas pr cs
     * @return Lista de medios de pago con marca de modificacion
     */
    public static List<MedioDePagoBilleteraDTO> getTarjetasModificadas(List<MedioDePagoBilleteraDTO> ctasPrCs) {
        return getMps(ctasPrCs, BilleteraConstants.TIPO_NOVEDAD_MOD);
    }

    /**
     * Obtiene medios de pago con cierto tipo de novedad.
     *
     * @param ctasPrCs
     *            Lista de medios de pago (Prisma y cliente)
     * @param tipoNov
     *            Tipo de novedad a buscar
     * @return Lista de medios de pago obtenidos
     */
    private static List<MedioDePagoBilleteraDTO> getMps(List<MedioDePagoBilleteraDTO> ctasPrCs, String tipoNov) {
        List<MedioDePagoBilleteraDTO> lst = new ArrayList<MedioDePagoBilleteraDTO>();
        Iterator<MedioDePagoBilleteraDTO> it = ctasPrCs.iterator();
        while (it.hasNext()) {
            MedioDePagoBilleteraDTO mp = it.next();
            if (tipoNov.equals(mp.getTipoNovedad())) {
                lst.add(mp);
            }
        }
        return lst;
    }

}