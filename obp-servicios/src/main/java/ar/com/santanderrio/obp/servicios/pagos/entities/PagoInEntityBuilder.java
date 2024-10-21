/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;

/**
 * The Class PagoInEntityBuilder.
 */
public class PagoInEntityBuilder {

    /** The pago in entity. */
    private PagoInEntity pagoInEntity = new PagoInEntity();

    /**
     * this.simboloMoneda = medioPago.getMoneda(); this.tipoEmpresa =
     * medioPago.getTipoEmpresa(); this.tipoPagoEmpresa =
     * medioPago.getTipoPago().toString(); this.tipoPago =
     * medioPago.getTipoPago().toString(); this.descripcionServicioPago =
     * medioPago.getNombreFantasia();
     */

    public PagoInEntityBuilder() {
        super();
    }

    /**
     * Asignaciones comunes.
     *
     * @param pago
     *            the pago
     */
    private void asignacionesComunes(PagoMisCuentasDTO pago) {
        pagoInEntity.setCodigoEmpresa(pago.getCodigoEmpresa());
        String tipoSeleccion = "I";
        if ("3".equals(pago.getTipoPago())) {
            tipoSeleccion = "F";
        } else { // tipo pago 1 o 2
            if (pago.getIsFromCalendario()) {
                if (new BigDecimal(pago.getMontoDeGrilla()).signum() != 0) {
                    tipoSeleccion = "F";
                }
            }
        }
        if (pago.isValidacionImporte() && StringUtils.equals(pago.getMontoSinFormatear(), pago.getMontoDeGrilla())) {
            pagoInEntity.setTipoMonto("3");
        } else {
            pagoInEntity.setTipoMonto("0");
        }

        pagoInEntity.setTipoSeleccion(tipoSeleccion);
        pagoInEntity.setNumeroFactura(asignarNumeroFactura(pago));
        pagoInEntity.setMontoMensajeFeedback("$ " + ISBANStringUtils.formatearSaldosConCerosYSignos(pago.getMonto()));
        // medio de pago
        pagoInEntity.setEmpresaNombreFantasia(pago.getDescripcionServicioPago());
        // ---------------------------------
        pagoInEntity.setSucursalCuenta(pago.getSucursal());
        pagoInEntity.setNumeroCuenta(pago.getNumeroCuenta());
        pagoInEntity.setMoneda(pago.getMoneda());
        pagoInEntity.setMonto(pago.getMonto());
        asignarIdentificacion(pago.getIdentificacionCliente());
        pagoInEntity.setFechaDeVencimiento(pago.getFechaVencimiento());
        // medio de pago
        pagoInEntity.setTipoCuenta(pago.getTipoCuenta());
        // if (TipoCuenta.CUENTA_UNICA.getCodigo().equals(pago.getTipoCuenta()))
        // {
        // if ("ARS".equals(pago.getMoneda())) {
        // pagoInEntity.setTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString());
        // } else if ("USD".equals(pago.getMoneda())) {
        // pagoInEntity.setTipoCuenta(TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString());
        // }
        // }
        // pagoInEntity.setTipoCuenta("09");
        // ------------------------------------
        if (StringUtils.isNotBlank(pago.getNroTarjeta())) {
            if (TipoCuenta.VISA.equals(pago.getTipoCuentaTC())) {
                pagoInEntity.setNroTarjeta(StringUtils.substring(pago.getNroTarjeta(), 4, 20));
            } else {
                pagoInEntity.setNroTarjeta(StringUtils.substring(pago.getNroTarjeta(), 4, 19) + " ");
            }
            pagoInEntity.setVencimientoTarjeta(pago.getVencimientoTarjeta());
            pagoInEntity.setTipoCuentaTC(pago.getTipoCuentaTC().getCodigo());
           
        }
    }

    /**
     * Asignar identificacion.
     *
     * @param identificacion
     *            the identificacion
     */
    private void asignarIdentificacion(String identificacion) {
        this.pagoInEntity.setIdentificacion(StringUtils.rightPad(String.valueOf(identificacion), 19, " "));
    }

    /**
     * transforma la informacion de nuevoPago en lo que sera el parametro
     * numeroFactura del multipago.
     *
     * @param nuevoPago
     *            the nuevo pago
     * @return String
     */
    private String asignarNumeroFactura(PagoMisCuentasDTO nuevoPago) {
        String res = "";
        if (StringUtils.isNotBlank(nuevoPago.getNumeroFactura())) {
            res = nuevoPago.getNumeroFactura();
        }

        return formatearNumeroDeFactura(res);
    }

    /**
     * Formatear numero de factura.
     *
     * @param numeroFactura
     *            the numero factura
     * @return the string
     */
    private String formatearNumeroDeFactura(String numeroFactura) {
        return StringUtils.rightPad(String.valueOf(numeroFactura), 20, " ");
    }

    /**
     * pesTipoPago = 1 pago electronico.
     *
     * @param pagoMisCuentasDTO
     *            the pago mis cuentas DTO
     * @return the pago in entity
     */
    public PagoInEntity pagoElectronico(PagoMisCuentasDTO pagoMisCuentasDTO) {
        asignacionesComunes(pagoMisCuentasDTO);
        return this.pagoInEntity;
    }

    /**
     * pesTipoPago = 2 y nombreFantasia contiene DOMESTICO.
     *
     * @param pagoMisCuentasDTO
     *            the pago mis cuentas DTO
     * @return the pago in entity
     */
    public PagoInEntity pagoDomestico(PagoMisCuentasDTO pagoMisCuentasDTO) {
        asignacionesComunes(pagoMisCuentasDTO);

        String numeroFactura = StringUtils.isNotBlank(pagoMisCuentasDTO.getDescripcionServicioPago())
                ? pagoMisCuentasDTO.getCuitEmpleador()
                : "";
        this.pagoInEntity.setNumeroFactura(numeroFactura);

        identificacionParaDomesticoNoDomestico(pagoMisCuentasDTO);

        return this.pagoInEntity;
    }

    /**
     * pesTipoPago = 2 y nombreFantasia contiene NO_DOMESTICO.
     *
     * @param pagoMisCuentasDTO
     *            the pago mis cuentas DTO
     * @return the pago in entity
     */
    public PagoInEntity pagoNoDomestico(PagoMisCuentasDTO pagoMisCuentasDTO) {
        asignacionesComunes(pagoMisCuentasDTO);
        identificacionParaDomesticoNoDomestico(pagoMisCuentasDTO);

        return this.pagoInEntity;
    }

    /**
     * aplica tanto para domesticos como no domesticos, pesTipoPago = 2.
     *
     * @param pagoMisCuentasDTO
     *            the pago mis cuentas DTO
     */
    private void identificacionParaDomesticoNoDomestico(PagoMisCuentasDTO pagoMisCuentasDTO) {
        String identificacion = pagoMisCuentasDTO.getCuitCliente();
        if (StringUtils.isNotBlank(pagoMisCuentasDTO.getPeriodoPago())) {
            identificacion = identificacion.concat(pagoMisCuentasDTO.getPeriodoPago());

        } else if (StringUtils.isNotBlank(pagoMisCuentasDTO.getNumeroDeCuota())) {
            identificacion = identificacion.concat(pagoMisCuentasDTO.getNumeroDeCuota());

        } else if (StringUtils.isNotBlank(pagoMisCuentasDTO.getNumeroDeAnticipo())) {
            identificacion = identificacion.concat(pagoMisCuentasDTO.getNumeroDeAnticipo());
        }
        asignarIdentificacion(identificacion);
    }

    /**
     * Vep.
     *
     * @param pagoMisCuentasDTO
     *            the pago mis cuentas DTO
     * @return the pago in entity
     */
    public PagoInEntity vep(PagoMisCuentasDTO pagoMisCuentasDTO) {
        asignacionesComunes(pagoMisCuentasDTO);

        // si cuitEmpleador es distinto de 11 viene del calendario
        String cuitEmpleador = pagoMisCuentasDTO.getCuitEmpleador().length() == 11 ? StringUtils
                .substring(pagoMisCuentasDTO.getCuitEmpleador(), 2, pagoMisCuentasDTO.getCuitEmpleador().length() - 1)
                : pagoMisCuentasDTO.getCuitEmpleador();

        asignarIdentificacion(pagoMisCuentasDTO.getCuitCliente() + cuitEmpleador);
        return this.pagoInEntity;
    }

    /**
     * Custom.
     *
     * @param pagoMisCuentasDTO
     *            the pago mis cuentas DTO
     * @return the pago in entity
     */
    public PagoInEntity custom(PagoMisCuentasDTO pagoMisCuentasDTO) {
        asignacionesComunes(pagoMisCuentasDTO);
        asignarIdentificacion(pagoMisCuentasDTO.getNumeroReferenciaPago());
        return this.pagoInEntity;
    }

    /**
     * Recarga celular sube.
     *
     * @param pagoMisCuentasDTO
     *            the pago mis cuentas DTO
     * @return the pago in entity
     */
    public PagoInEntity recargaCelularSube(PagoMisCuentasDTO pagoMisCuentasDTO) {
        throw new NotImplementedException("Se sigue manejando con NuevaRecargaInDTO, No esta migrado aun! ! !");
    }
    
    /**
     * Recarga Movi.
     *
     * @param pagoMisCuentasDTO
     *            the pago mis cuentas DTO
     * @return the pago in entity
     */
    public PagoInEntity recargaMovi(PagoMisCuentasDTO pagoMisCuentasDTO) {
        throw new NotImplementedException("Se sigue manejando con NuevaRecargaInDTO, No esta migrado aun! ! !");
    }

}
