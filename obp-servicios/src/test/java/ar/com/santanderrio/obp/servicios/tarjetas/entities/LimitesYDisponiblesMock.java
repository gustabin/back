package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * The Class LimitesYDisponiblesMock.
 *
 * @author florencia.n.martinez
 */
public final class LimitesYDisponiblesMock {

    /**
     * Instantiates a new limites Y disponibles mock.
     */
    private LimitesYDisponiblesMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    /**
     * Completar info limites Y disponibles.
     *
     * @return the limites Y disponibles DTO
     */
    public static LimitesYDisponiblesDTO completarInfoLimitesYDisponibles() {
        LimitesYDisponiblesDTO limitesYDisponibles = new LimitesYDisponiblesDTO();
        limitesYDisponibles.setSaldoDisponibleCompras("8.000,00");
        limitesYDisponibles.setLimiteCompraPesos("8.000,00");
        limitesYDisponibles.setSaldoDisponibleCuotas("2.050,00");
        limitesYDisponibles.setLimiteCuotasPesos("10.500,00");
        limitesYDisponibles.setAdelantoEfectivoPesos("3.000,00");
        limitesYDisponibles.setLimiteAdelantoEfectivoPesos("3.000,00");
        limitesYDisponibles.setHasError(Boolean.FALSE);
        limitesYDisponibles.setHasLimiteUnificado(Boolean.FALSE);
        limitesYDisponibles.setIsLimiteAdelantoDolares(Boolean.FALSE);
        limitesYDisponibles.setIsLimiteAdelantoPesos(Boolean.TRUE);
        limitesYDisponibles.setIsLimiteCompraDolares(Boolean.FALSE);
        limitesYDisponibles.setIsLimiteCompraPesos(Boolean.TRUE);
        limitesYDisponibles.setIsLimiteCuotasDolares(Boolean.FALSE);
        limitesYDisponibles.setIsLimiteCuotasPesos(Boolean.TRUE);
        limitesYDisponibles.setMarca("VISA");
        limitesYDisponibles.setMostrarSaldoEnCompras(Boolean.TRUE);
        limitesYDisponibles.setMostrarSaldoEnCuotas(Boolean.TRUE);
        limitesYDisponibles.setNroTarjeta("XXXX-3456");
        limitesYDisponibles.setLegales("Legales...");
        return limitesYDisponibles;
    }
}
