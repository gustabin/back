package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class UltimoResumenDTOMock.
 *
 * @author florencia.n.martinez
 */
public final class UltimoResumenDTOMock {
    
    private UltimoResumenDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    /**
     * Completa la informacion de ultimo resumen DTO.
     *
     * @return the ultimo resumen DTO
     */
    public static UltimoResumenDTO completarInfoUltimoResumenDTO() {
        UltimoResumenDTO dto = new UltimoResumenDTO();
        dto.setFechaCierreActual(new Date());
        dto.setFechaVencimientoActual(new Date());
        dto.setFechaCierreAnterior(new Date());
        dto.setFechaProximoCierre(new Date());
        dto.setFechaProximoVencimiento(new Date());
        dto.setFechaVencimientoAnterior(new Date());
        dto.setLimiteCompra(BigDecimal.valueOf(21000));
        dto.setLimiteCompraEnCuotas(BigDecimal.valueOf(31500));
        dto.setLimiteFinanciacion(BigDecimal.valueOf(18900));
        dto.setMarca("AMEX");
        dto.setMensajeSEUO("Información obtenida de VISA@home S.E.U.O.");
        dto.setMuestraTarjetasConCabecera(Boolean.FALSE);
        dto.setNumeroTarjeta("XXXX - 7280");
        dto.setPagoMinimo(BigDecimal.valueOf(190));
        dto.setSaldoDolares(BigDecimal.valueOf(13.98));
        dto.setSaldoPesos(BigDecimal.valueOf(3560.54));
        dto.setSonLimitesDolar(Boolean.FALSE);
        dto.setTarjetas(completarInfoTarjetas());
        dto.setTasaEfectivaMensualDolares(BigDecimal.valueOf(0));
        dto.setTasaEfectivaMensualPesos(BigDecimal.valueOf(3.12));
        dto.setTasaNominalAnualDolares(BigDecimal.valueOf(0));
        dto.setTasaNominalAnualPesos(BigDecimal.valueOf(38.0));
        dto.setTerminosYCondiciones("Términos y condiciones.");
        dto.setTieneAlias(Boolean.FALSE);
        dto.setOtrosConceptos(new ArrayList<LineaUltimoResumenTarjetaDTO>());
        return dto;
    }

    /**
     * Completa la informacion de las tarjetas.
     *
     * @return the list
     */
    private static List<UltimoResumenTarjetaDTO> completarInfoTarjetas() {
        List<UltimoResumenTarjetaDTO> tarjetas = new ArrayList<UltimoResumenTarjetaDTO>();
        tarjetas.add(completarInfoTarjeta());
        return tarjetas;
    }

    /**
     * Completa la informacion de la tarjeta.
     *
     * @return the ultimo resumen tarjeta DTO
     */
    private static UltimoResumenTarjetaDTO completarInfoTarjeta() {
        UltimoResumenTarjetaDTO dto = new UltimoResumenTarjetaDTO();
        dto.setLineas(completarLineas());
        dto.setNumeroTarjeta("XXXX - 7280");
        dto.setTotalDolares(BigDecimal.valueOf(0));
        dto.setTotalPesos(BigDecimal.valueOf(241.5));
        return dto;
    }

    /**
     * Completa las lineas.
     *
     * @return the list
     */
    private static List<LineaUltimoResumenTarjetaDTO> completarLineas() {
        List<LineaUltimoResumenTarjetaDTO> lineas = new ArrayList<LineaUltimoResumenTarjetaDTO>();
        lineas.add(completarInfoLinea());
        return lineas;
    }

    /**
     * Completa la informacion de la linea.
     *
     * @return the linea ultimo resumen tarjeta DTO
     */
    private static LineaUltimoResumenTarjetaDTO completarInfoLinea() {
        LineaUltimoResumenTarjetaDTO dto = new LineaUltimoResumenTarjetaDTO();
        dto.setComprobante("792507");
        dto.setCuota("08/12");
        dto.setCuotasCanceladas("08");
        dto.setCuotasTotales("12");
        dto.setDescripcion("Arredo");
        dto.setFecha(new Date());
        dto.setImportePesos(BigDecimal.valueOf(241.5));
        dto.setTieneCuota(Boolean.TRUE);
        return dto;
    }
}