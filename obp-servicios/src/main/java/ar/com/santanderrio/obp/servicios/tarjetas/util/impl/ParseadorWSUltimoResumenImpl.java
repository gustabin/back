/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.FechaUltimaLiquidacion;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.LimiteUltimaLiquidacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.PagoUltimaLiquidacion;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.SaldoTasaUltimaLiquidacion;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.UltimaLiquidacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenFilaBean;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class ParseadorWSUltimoResumenImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class ParseadorWSUltimoResumenImpl extends ParseadorWSVisaImpl implements ParseadorWSUltimoResumen {

    /** The Constant CODIGO_ERROR_SIN_ULTIMO_RESUMEN. */
    private static final String CODIGO_ERROR_SIN_ULTIMO_RESUMEN = "111908";

    /** The Constant CERO_ENTERO. */
    private static final Integer CERO_ENTERO = 0;

    /** The Constant OCHO_ENTERO. */
    private static final Integer OCHO_ENTERO = 8;

    /** The Constant NUEVE_ENTERO. */
    private static final Integer NUEVE_ENTERO = 9;

    /** The Constant CINCUENTAYTRES_ENTERO. */
    private static final Integer CINCUENTAYTRES_ENTERO = 53;

    /** The Constant CINCUENTAYNUEVE_ENTERO. */
    private static final Integer CINCUENTAYNUEVE_ENTERO = 59;

    /** The Constant SESENTAYOCHO_ENTERO. */
    private static final Integer SESENTAYOCHO_ENTERO = 68;

    /** The Constant SESENTAYSEIS_ENTERO. */
    private static final Integer SESENTAYSEIS_ENTERO = 66;

    /** The Constant CUARENTAYCUATRO_ENTERO. */
    private static final Integer CUARENTAYCUATRO_ENTERO = 44;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerTarjetaPorNroTarjetaActivaUltimaLiquidacion(ar.com.santanderrio.
     * obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity, java.lang.String)
     */
    @Override
    public TarjetaEntity obtenerTarjetaPorNroTarjetaActivaUltimaLiquidacion(RetornoTarjetasEntity entity,
            String nroTarjeta) throws ParseadorUltimaLiquidacionException, ParseadorVisaException {
        List<TarjetaEntity> tarjetasEntity = obtenerTarjetas(entity);
        for (TarjetaEntity tarjetaEntity : tarjetasEntity) {
            if (!tarjetaTieneError(tarjetaEntity)) {
                String nroTarjetaActiva = obtenerNumeroTarjetaActivaUltimaLiquidacion(tarjetaEntity);
                if (nroTarjetaActiva != null && nroTarjeta != null
                        && StringUtils.equals(nroTarjeta, nroTarjetaActiva)) {
                    return tarjetaEntity;
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerFechaVencimientoActual(ar.com.santanderrio.obp.servicios.tarjetas.
     * dao.entities.TarjetaEntity)
     */
    @Override
    public String obtenerFechaVencimientoActual(TarjetaEntity tarjetaEntity)
            throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getFechas() == null || ultimaLiquidacionEntity.getFechas().getFechas().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            for (FechaUltimaLiquidacion fecha : ultimaLiquidacionEntity.getFechas().getFechas()) {
                if (esDescripcionFechaActual(fecha)) {
                    return fecha.getVencimiento();
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerFechaCierreActual(ar.com.santanderrio.obp.servicios.tarjetas.dao.
     * entities.TarjetaEntity)
     */
    @Override
    public String obtenerFechaCierreActual(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getFechas() == null || ultimaLiquidacionEntity.getFechas().getFechas().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            for (FechaUltimaLiquidacion fecha : ultimaLiquidacionEntity.getFechas().getFechas()) {
                if (esDescripcionFechaActual(fecha)) {
                    return fecha.getCierre();
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerSaldoEnPesos(ar.com.santanderrio.obp.servicios.tarjetas.dao.
     * entities.TarjetaEntity)
     */
    @Override
    public String obtenerSaldoEnPesos(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getSaldos() == null || ultimaLiquidacionEntity.getSaldos().getSaldos().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            String sSaldo = "";
            for (SaldoTasaUltimaLiquidacion saldo : ultimaLiquidacionEntity.getSaldos().getSaldos()) {
                if (esDescripcionSaldoActual(saldo)) {
                    sSaldo = saldo.getTotal();
                    break;
                }
            }
            return sSaldo;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerSaldoEnDolares(ar.com.santanderrio.obp.servicios.tarjetas.dao.
     * entities.TarjetaEntity)
     */
    @Override
    public String obtenerSaldoEnDolares(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getSaldos() == null || ultimaLiquidacionEntity.getSaldos().getSaldos().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            String sSaldo = "";
            for (SaldoTasaUltimaLiquidacion saldo : ultimaLiquidacionEntity.getSaldos().getSaldos()) {
                if (esDescripcionSaldoActual(saldo)) {
                    sSaldo = saldo.getUsdTotal();
                    break;
                }
            }
            return sSaldo;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerPagoMinimo(ar.com.santanderrio.obp.servicios.tarjetas.dao.entities
     * .TarjetaEntity)
     */
    @Override
    public String obtenerPagoMinimo(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getPagos() == null || ultimaLiquidacionEntity.getPagos().getPagos().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            String sPago = "";
            for (PagoUltimaLiquidacion pago : ultimaLiquidacionEntity.getPagos().getPagos()) {
                if (esPagoMinimo(pago)) {
                    sPago = pago.getTotal();
                    break;
                }
            }
            return sPago;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerLimiteFinanciacion(ar.com.santanderrio.obp.servicios.tarjetas.dao.
     * entities.TarjetaEntity)
     */
    @Override
    public String obtenerLimiteFinanciacion(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getLimites() == null
                || ultimaLiquidacionEntity.getLimites().getLimites().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            String sLimite = "";
            for (LimiteUltimaLiquidacionEntity limite : ultimaLiquidacionEntity.getLimites().getLimites()) {
                if (esLimiteFinanciero(limite)) {
                    sLimite = limite.getTotal();
                    break;
                }
            }
            return sLimite;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerLimiteCompra(ar.com.santanderrio.obp.servicios.tarjetas.dao.
     * entities.TarjetaEntity)
     */
    @Override
    public String obtenerLimiteCompra(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getLimites() == null
                || ultimaLiquidacionEntity.getLimites().getLimites().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            String sLimite = "";
            for (LimiteUltimaLiquidacionEntity limite : ultimaLiquidacionEntity.getLimites().getLimites()) {
                if (esLimiteCompra(limite)) {
                    sLimite = limite.getTotal();
                    break;
                }
            }
            return sLimite;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerLimiteCompraEnCuotas(ar.com.santanderrio.obp.servicios.tarjetas.
     * dao.entities.TarjetaEntity)
     */
    @Override
    public String obtenerLimiteCompraEnCuotas(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getLimites() == null
                || ultimaLiquidacionEntity.getLimites().getLimites().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            String sLimite = "";
            for (LimiteUltimaLiquidacionEntity limite : ultimaLiquidacionEntity.getLimites().getLimites()) {
                if (esLimiteCompraEnCuotas(limite)) {
                    sLimite = limite.getTotal();
                    break;
                }
            }
            return sLimite;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerFechaProximoCierre(ar.com.santanderrio.obp.servicios.tarjetas.dao.
     * entities.TarjetaEntity)
     */
    @Override
    public String obtenerFechaProximoCierre(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getFechas() == null || ultimaLiquidacionEntity.getFechas().getFechas().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            for (FechaUltimaLiquidacion fecha : ultimaLiquidacionEntity.getFechas().getFechas()) {
                if (esFechaProximoCierre(fecha)) {
                    return fecha.getCierre();
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerFechaProximoVencimiento(ar.com.santanderrio.obp.servicios.tarjetas
     * .dao.entities.TarjetaEntity)
     */
    @Override
    public String obtenerFechaProximoVencimiento(TarjetaEntity tarjetaEntity)
            throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getFechas() == null || ultimaLiquidacionEntity.getFechas().getFechas().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            for (FechaUltimaLiquidacion fecha : ultimaLiquidacionEntity.getFechas().getFechas()) {
                if (esFechaProximoVencimiento(fecha)) {
                    return fecha.getVencimiento();
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerFechaCierreAnterior(ar.com.santanderrio.obp.servicios.tarjetas.dao
     * .entities.TarjetaEntity)
     */
    @Override
    public String obtenerFechaCierreAnterior(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getFechas() == null || ultimaLiquidacionEntity.getFechas().getFechas().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            for (FechaUltimaLiquidacion fecha : ultimaLiquidacionEntity.getFechas().getFechas()) {
                if (esFechaAnterior(fecha)) {
                    return fecha.getCierre();
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerFechaVencimientoAnterior(ar.com.santanderrio.obp.servicios.
     * tarjetas.dao.entities.TarjetaEntity)
     */
    @Override
    public String obtenerFechaVencimientoAnterior(TarjetaEntity tarjetaEntity)
            throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getFechas() == null || ultimaLiquidacionEntity.getFechas().getFechas().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            for (FechaUltimaLiquidacion fecha : ultimaLiquidacionEntity.getFechas().getFechas()) {
                if (esFechaAnterior(fecha)) {
                    return fecha.getVencimiento();
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerTasaNominalAnualPesos(ar.com.santanderrio.obp.servicios.tarjetas.
     * dao.entities.TarjetaEntity)
     */
    @Override
    public String obtenerTasaNominalAnualPesos(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getTasas() == null || ultimaLiquidacionEntity.getTasas().getTasas().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            String sTasa = "";
            for (SaldoTasaUltimaLiquidacion tasa : ultimaLiquidacionEntity.getTasas().getTasas()) {
                if (esTasaNominalAnual(tasa)) {
                    sTasa = tasa.getTotal();
                    break;
                }
            }
            return sTasa;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerTasaNominalAnualDolares(ar.com.santanderrio.obp.servicios.tarjetas
     * .dao.entities.TarjetaEntity)
     */
    @Override
    public String obtenerTasaNominalAnualDolares(TarjetaEntity tarjetaEntity)
            throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getTasas() == null || ultimaLiquidacionEntity.getTasas().getTasas().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            String sTasa = "";
            for (SaldoTasaUltimaLiquidacion tasa : ultimaLiquidacionEntity.getTasas().getTasas()) {
                if (esTasaNominalAnual(tasa)) {
                    sTasa = tasa.getUsdTotal();
                    break;
                }
            }
            return sTasa;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerTasaEfectivaMensualPesos(ar.com.santanderrio.obp.servicios.
     * tarjetas.dao.entities.TarjetaEntity)
     */
    @Override
    public String obtenerTasaEfectivaMensualPesos(TarjetaEntity tarjetaEntity)
            throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getTasas() == null || ultimaLiquidacionEntity.getTasas().getTasas().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            String sTasa = "";
            for (SaldoTasaUltimaLiquidacion tasa : ultimaLiquidacionEntity.getTasas().getTasas()) {
                if (esTasaEfectivaMensual(tasa)) {
                    sTasa = tasa.getTotal();
                    break;
                }
            }
            return sTasa;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerTasaEfectivaMensualDolares(ar.com.santanderrio.obp.servicios.
     * tarjetas.dao.entities.TarjetaEntity)
     */
    @Override
    public String obtenerTasaEfectivaMensualDolares(TarjetaEntity tarjetaEntity)
            throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacionEntity.getTasas() == null || ultimaLiquidacionEntity.getTasas().getTasas().isEmpty()) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            String sTasa = "";
            for (SaldoTasaUltimaLiquidacion tasa : ultimaLiquidacionEntity.getTasas().getTasas()) {
                if (esTasaEfectivaMensual(tasa)) {
                    sTasa = tasa.getUsdTotal();
                    break;
                }
            }
            return sTasa;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * noTieneUltimoResumen(ar.com.santanderrio.obp.servicios.tarjetas.dao.
     * entities.RetornoTarjetasEntity)
     */
    @Override
    public Boolean noTieneUltimoResumen(RetornoTarjetasEntity respuesta) {
        for (TarjetaEntity tarjeta : respuesta.getTarjetas().getTarjetaList()) {
            if (tarjeta.getError() != null
                    && !StringUtils.equals(tarjeta.getError().getCodigo(), CODIGO_ERROR_SIN_ULTIMO_RESUMEN)) {
                return Boolean.FALSE;
            } else {
                continue;
            }
        }
        return Boolean.TRUE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerCamposDetalleLiquidacion(java.lang.String, java.lang.String)
     */
    @Override
    public UltimoResumenFilaBean obtenerCamposDetalleLiquidacion(String linea, String trjTipo) {
        UltimoResumenFilaBean fila = new UltimoResumenFilaBean();
        String posibleFecha = linea.substring(CERO_ENTERO, OCHO_ENTERO);
        // regex fecha
        String regexFecha = "\\d{2}/\\d{2}/\\d{2}";

        String fecha = "";
        StringBuilder descripcion = new StringBuilder("");
        String comprobante = "";
        String montoPesos = "";
        String montoDolares = "";
        String total = "";
        String totalPesos = "";
        String totalDolares = "";
        String legal = "";

        if (posibleFecha.matches(regexFecha)) {
            // si no matchea una fecha, no es un consumo
            fecha = posibleFecha;
            String descripcionAux = linea.substring(NUEVE_ENTERO, CINCUENTAYTRES_ENTERO).trim();

            // quitamos los espacios en blanco de más que esten en la
            // descripción
            for (int x = 0; x < descripcionAux.length(); x++) {
                if (descripcionAux.charAt(x) != ' ') {
                    descripcion.append(descripcionAux.charAt(x));
                } else if (descripcionAux.charAt(x) == ' '
                        && ((x + 1) <= descripcionAux.length() && descripcionAux.charAt(x + 1) != ' ')) {
                    descripcion.append(descripcionAux.charAt(x));
                }
            }

            Matcher m = Pattern.compile("\\d{6}\\*?").matcher(descripcion.toString());
            if ("VR".equals(trjTipo)) {
                if (m.find()) {
                    comprobante = m.group();
                    comprobante = descripcion.toString().replace(comprobante, "");
                } else {
                    comprobante = linea.substring(CINCUENTAYTRES_ENTERO, CINCUENTAYNUEVE_ENTERO).trim();
                }
                montoPesos = "(" + linea.substring(CINCUENTAYNUEVE_ENTERO, SESENTAYOCHO_ENTERO).trim() + ")";
                if ("()".equals(montoPesos)) {
                    montoPesos = linea.substring(SESENTAYOCHO_ENTERO).trim();
                }
            } else {
                int diferenciaAsterico = 1;
                if (m.find()) {
                    comprobante = m.group();
                    if (comprobante.contains("*")) {
                        diferenciaAsterico = 0;
                        comprobante = comprobante.replace("*", "");
                    }
                }

                montoPesos = linea.substring(CINCUENTAYTRES_ENTERO + diferenciaAsterico, SESENTAYSEIS_ENTERO).trim();
                montoDolares = linea.substring(SESENTAYSEIS_ENTERO).trim();
            }

        } else if (linea.indexOf("TOTAL TARJETA") != -1) {
            // Es un total de tarjeta
            total = linea.substring(CERO_ENTERO, CUARENTAYCUATRO_ENTERO).trim();

            Pattern p = Pattern.compile("([0-9\\.,]+\\s)\\*");
            Matcher m = p.matcher(linea.substring(CUARENTAYCUATRO_ENTERO).trim());
            if (m.find()) {
                totalPesos = m.group(1);
            }
            if (m.find()) {
                totalDolares = m.group(1);
            }
        } else {
            // Comienzo de los legales.
            legal = linea;
        }

        // agrego los campos al mapa
        fila.setFecha(fecha);
        fila.setDescripcion(descripcion.toString());
        fila.setComprobante(comprobante);
        fila.setPesos(montoPesos);
        fila.setDolares(montoDolares);
        fila.setTotal(total);
        fila.setTotalPesos(totalPesos);
        fila.setTotalDolares(totalDolares);
        fila.setLegal(legal);

        return fila;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * esMovimiento(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * UltimoResumenFilaBean)
     */
    @Override
    public Boolean esMovimiento(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getFecha()) && StringUtils.isNotEmpty(fila.getDescripcion())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerFechaLinea(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * UltimoResumenFilaBean)
     */
    @Override
    public String obtenerFechaLinea(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getFecha())) {
            return fila.getFecha();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * formatearFechaDate(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * UltimoResumenFilaBean)
     */
    @Override
    public Date formatearFechaDate(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getFecha())) {
            return ISBANStringUtils.formatearFechaAnioCorto(fila.getFecha());
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerDescripcion(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * UltimoResumenFilaBean)
     */
    @Override
    public String obtenerDescripcion(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getDescripcion())) {
            String descripcion = fila.getDescripcion();
            if (esDescripcionURL(descripcion)) {
                return TarjetaBOUtils.convertirStringToLowercase(descripcion);
            }
            return TarjetaBOUtils.convertirStringToCamelcase(descripcion);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerImportePesos(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * UltimoResumenFilaBean)
     */
    @Override
    public String obtenerImportePesos(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getPesos())) {
            return fila.getPesos();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerImporteDolares(ar.com.santanderrio.obp.servicios.tarjetas.entities
     * .UltimoResumenFilaBean)
     */
    @Override
    public String obtenerImporteDolares(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getDolares())) {
            return fila.getDolares();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * tieneTotales(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * UltimoResumenFilaBean)
     */
    @Override
    public Boolean tieneTotales(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getTotal())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Tiene legales.
     *
     * @param fila
     *            the fila
     * @return the boolean
     */
    @Override
    public Boolean tieneLegales(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getLegal())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerNumeroTarjeta(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * UltimoResumenFilaBean)
     */
    @Override
    public String obtenerNumeroTarjeta(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getTotal())) {
            String regex = "((\\b\\w{4}|\\d{4})\\s{0,2}(XXXX|\\d{4})\\s{0,2}(XXXX|\\d{4})\\s{0,2})(\\d{4})";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(fila.getTotal());
            if (matcher.find()){
                return "XXXX - " + matcher.group(5);
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerTotalPesos(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * UltimoResumenFilaBean)
     */
    @Override
    public String obtenerTotalPesos(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getTotalPesos())) {
            return fila.getTotalPesos();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen#
     * obtenerTotalDolares(ar.com.santanderrio.obp.servicios.tarjetas.entities.
     * UltimoResumenFilaBean)
     */
    @Override
    public String obtenerTotalDolares(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getTotalDolares())) {
            return fila.getTotalDolares();
        }
        return null;
    }

    /**
     * Es descripcion URL.
     *
     * @param descripcion
     *            the descripcion
     * @return the boolean
     */
    private Boolean esDescripcionURL(String descripcion) {
        return descripcion.contains(".COM") || StringUtils.startsWith(descripcion, "WWW.");
    }

    /**
     * Es tasa efectiva mensual.
     *
     * @param tasa
     *            the tasa
     * @return the boolean
     */
    private Boolean esTasaEfectivaMensual(SaldoTasaUltimaLiquidacion tasa) {
        if (StringUtils.equals(tasa.getDescripcion(), TarjetaUtils.DESCRIPCION_TEM)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Es tasa nominal anual.
     *
     * @param tasa
     *            the tasa
     * @return the boolean
     */
    private Boolean esTasaNominalAnual(SaldoTasaUltimaLiquidacion tasa) {
        if (StringUtils.equals(tasa.getDescripcion(), TarjetaUtils.DESCRIPCION_TNA)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Es fecha anterior.
     *
     * @param fecha
     *            the fecha
     * @return the boolean
     */
    private Boolean esFechaAnterior(FechaUltimaLiquidacion fecha) {
        if (StringUtils.equals(fecha.getDescripcion(), TarjetaUtils.DESCRIPCION_ANTERIOR)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Es fecha proximo vencimiento.
     *
     * @param fecha
     *            the fecha
     * @return the boolean
     */
    private Boolean esFechaProximoVencimiento(FechaUltimaLiquidacion fecha) {
        if (StringUtils.equals(fecha.getDescripcion(), TarjetaUtils.FECHA_VENCIMIENTO)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Es fecha proximo cierre.
     *
     * @param fecha
     *            the fecha
     * @return the boolean
     */
    private Boolean esFechaProximoCierre(FechaUltimaLiquidacion fecha) {
        if (StringUtils.equals(fecha.getDescripcion(), TarjetaUtils.FECHA_CIERRE)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Es limite compra en cuotas.
     *
     * @param limite
     *            the limite
     * @return the boolean
     */
    private Boolean esLimiteCompraEnCuotas(LimiteUltimaLiquidacionEntity limite) {
        if (StringUtils.equals(limite.getDescripcion(), TarjetaUtils.DESCRIPCION_LIMITE_COMPRA_CUOTAS)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Es limite compra.
     *
     * @param limite
     *            the limite
     * @return the boolean
     */
    private Boolean esLimiteCompra(LimiteUltimaLiquidacionEntity limite) {
        if (StringUtils.equals(limite.getDescripcion(), TarjetaUtils.DESCRIPCION_LIMITE_COMPRA)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Es limite financiero.
     *
     * @param limite
     *            the limite
     * @return the boolean
     */
    private Boolean esLimiteFinanciero(LimiteUltimaLiquidacionEntity limite) {
        if (StringUtils.equals(limite.getDescripcion(), TarjetaUtils.DESCRIPCION_LIMITE_FINANCIAMIENTO)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Es pago minimo.
     *
     * @param pago
     *            the pago
     * @return the boolean
     */
    private Boolean esPagoMinimo(PagoUltimaLiquidacion pago) {
        if (StringUtils.equals(pago.getDescripcion(), TarjetaUtils.DESCRIPCION_PAGO_MINIMO)
                && StringUtils.equals(pago.getTipoMoneda(), TarjetaUtils.TIPO_MONEDA_PAGO_MINIMO)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Es descripcion saldo actual.
     *
     * @param saldo
     *            the saldo
     * @return the boolean
     */
    private Boolean esDescripcionSaldoActual(SaldoTasaUltimaLiquidacion saldo) {
        if (StringUtils.equals(saldo.getDescripcion(), TarjetaUtils.DESCRIPCION_ACTUAL)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Es descripcion fecha actual.
     *
     * @param fecha
     *            the fecha
     * @return the boolean
     */
    private Boolean esDescripcionFechaActual(FechaUltimaLiquidacion fecha) {
        if (StringUtils.equals(fecha.getDescripcion(), TarjetaUtils.DESCRIPCION_ACTUAL)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Obtener numero tarjeta activa ultima liquidacion.
     *
     * @param tarjetaEntity
     *            the tarjeta entity
     * @return the string
     * @throws ParseadorUltimaLiquidacionException
     *             the parseador ultima liquidacion exception
     */
    public String obtenerNumeroTarjetaActivaUltimaLiquidacion(TarjetaEntity tarjetaEntity)
            throws ParseadorUltimaLiquidacionException {
        UltimaLiquidacionEntity ultimaLiquidacion = obtenerUltimaLiquidacion(tarjetaEntity);
        if (ultimaLiquidacion.getDatos().getTarjetaActiva() == null) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            return ultimaLiquidacion.getDatos().getTarjetaActiva();
        }
    }

    /**
     * Obtiene los datos de ultima liquidacion: /tarjetas/tarjeta/liquidacion.
     *
     * @param tarjetaEntity
     *            the tarjeta entity
     * @return the ultima liquidacion entity
     * @throws ParseadorUltimaLiquidacionException
     *             the parseador ultima liquidacion exception
     */
    public UltimaLiquidacionEntity obtenerUltimaLiquidacion(TarjetaEntity tarjetaEntity)
            throws ParseadorUltimaLiquidacionException {
        if (tieneUltimaLiquidacionDatosYFechas(tarjetaEntity) || tieneDetalleLimitesYPagos(tarjetaEntity)
                || tieneSaldosTasasYTotal(tarjetaEntity)) {
            throw new ParseadorUltimaLiquidacionException();
        } else {
            return tarjetaEntity.getUltimaLiquidacion();
        }
    }

    /**
     * Verifica si TarjetaEntity de UltimaLiquidacionEntity tiene saldos, tasas Y
     * total.
     *
     * @param tarjetaEntity
     *            the tarjeta entity
     * @return the boolean
     */
    private Boolean tieneSaldosTasasYTotal(TarjetaEntity tarjetaEntity) {
        if (tarjetaEntity.getUltimaLiquidacion().getTasas() == null
                || tarjetaEntity.getUltimaLiquidacion().getTotal() == null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Verifica si TarjetaEntity de UltimaLiquidacionEntity tiene detalle, limites y
     * pagos.
     *
     * @param tarjetaEntity
     *            the tarjeta entity
     * @return the boolean
     */
    private Boolean tieneDetalleLimitesYPagos(TarjetaEntity tarjetaEntity) {
        if (tarjetaEntity.getUltimaLiquidacion().getDetalleLiquidacion() == null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Verifica si TarjetaEntity de UltimaLiquidacionEntity tiene ultima
     * liquidacion, datos y fechas.
     *
     * @param tarjetaEntity
     *            the tarjeta entity
     * @return the boolean
     */
    private Boolean tieneUltimaLiquidacionDatosYFechas(TarjetaEntity tarjetaEntity) {
        if (tarjetaEntity.getUltimaLiquidacion() == null || tieneDatos(tarjetaEntity)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Verifica si TarjetaEntity de UltimaLiquidacionEntity tiene datos.
     *
     * @param tarjetaEntity
     *            the tarjeta entity
     * @return the boolean
     */
    private Boolean tieneDatos(TarjetaEntity tarjetaEntity) {
        if (tarjetaEntity.getUltimaLiquidacion().getDatos() == null
                || tarjetaEntity.getUltimaLiquidacion().getDatosExtra() == null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Obtiene el comprobante.
     *
     * @param fila
     *            the fila
     * @return the string
     */
    @Override
    public String obtenerComprobante(UltimoResumenFilaBean fila) {
        if (StringUtils.isNotEmpty(fila.getComprobante())) {
            return fila.getComprobante();
        } else {
            return ISBANStringUtils.GUION_STRING;
        }
    }

}