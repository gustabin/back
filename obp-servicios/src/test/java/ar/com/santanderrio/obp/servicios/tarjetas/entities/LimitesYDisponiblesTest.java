/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import org.junit.Assert;
import org.junit.Test;

/**
 * The Class LimitesYDisponiblesTest.
 *
 * @author florencia.n.martinez
 */
public class LimitesYDisponiblesTest {

    /** The limites Y disponibles. */
    private LimitesYDisponiblesEntity limitesYDisponibles = new LimitesYDisponiblesEntity();

    /**
     * Gets the marca.
     *
     * @return the marca
     */
    @Test
    public void getMarca() {
        String marca = new String("VISA");
        limitesYDisponibles.setMarca(marca);
        Assert.assertEquals(marca, limitesYDisponibles.getMarca());
    }

    /**
     * Gets the nro tarjeta.
     *
     * @return the nro tarjeta
     */
    @Test
    public void getNroTarjeta() {
        String nroTarjeta = new String("XXXX-1234");
        limitesYDisponibles.setNroTarjeta(nroTarjeta);
        Assert.assertEquals(nroTarjeta, limitesYDisponibles.getNroTarjeta());
    }

    /**
     * Gets the saldo disponible compras.
     *
     * @return the saldo disponible compras
     */
    @Test
    public void getSaldoDisponibleCompras() {
        String saldoDispCompras = new String("8.000,00");
        limitesYDisponibles.setSaldoDisponibleCompras(saldoDispCompras);
        Assert.assertEquals(saldoDispCompras, limitesYDisponibles.getSaldoDisponibleCompras());
    }

    /**
     * Gets the limite compra pesos.
     *
     * @return the limite compra pesos
     */
    @Test
    public void getLimiteCompraPesos() {
        String limiteCompraPesos = new String("8.000,00");
        limitesYDisponibles.setLimiteCompraPesos(limiteCompraPesos);
        Assert.assertEquals(limiteCompraPesos, limitesYDisponibles.getLimiteCompraPesos());
    }

    /**
     * Gets the limite compra dolares.
     *
     * @return the limite compra dolares
     */
    @Test
    public void getLimiteCompraDolares() {
        String limiteCompraDolares = new String("8.000,00");
        limitesYDisponibles.setLimiteCompraDolares(limiteCompraDolares);
        Assert.assertEquals(limiteCompraDolares, limitesYDisponibles.getLimiteCompraDolares());
    }

    /**
     * Gets the saldo disponible cuotas.
     *
     * @return the saldo disponible cuotas
     */
    @Test
    public void getSaldoDisponibleCuotas() {
        String saldoDispCuotas = new String("8.000,00");
        limitesYDisponibles.setSaldoDisponibleCuotas(saldoDispCuotas);
        Assert.assertEquals(saldoDispCuotas, limitesYDisponibles.getSaldoDisponibleCuotas());
    }

    /**
     * Gets the limite cuotas pesos.
     *
     * @return the limite cuotas pesos
     */
    @Test
    public void getLimiteCuotasPesos() {
        String limiteCuotasPesos = new String("8.000,00");
        limitesYDisponibles.setLimiteCuotasPesos(limiteCuotasPesos);
        Assert.assertEquals(limiteCuotasPesos, limitesYDisponibles.getLimiteCuotasPesos());
    }

    /**
     * Gets the limite cuotas dolares.
     *
     * @return the limite cuotas dolares
     */
    @Test
    public void getLimiteCuotasDolares() {
        String limiteCuotasDolares = new String("8.000,00");
        limitesYDisponibles.setLimiteCuotasDolares(limiteCuotasDolares);
        Assert.assertEquals(limiteCuotasDolares, limitesYDisponibles.getLimiteCuotasDolares());
    }

    /**
     * Gets the adelanto efectivo pesos.
     *
     * @return the adelanto efectivo pesos
     */
    @Test
    public void getAdelantoEfectivoPesos() {
        String adelantoEnEfectivoPesos = new String("8.000,00");
        limitesYDisponibles.setAdelantoEfectivoPesos(adelantoEnEfectivoPesos);
        Assert.assertEquals(adelantoEnEfectivoPesos, limitesYDisponibles.getAdelantoEfectivoPesos());
    }

    /**
     * Gets the limite adelanto efectivo pesos.
     *
     * @return the limite adelanto efectivo pesos
     */
    @Test
    public void getLimiteAdelantoEfectivoPesos() {
        String limiteAdelantoEfectivoPesos = new String("8.000,00");
        limitesYDisponibles.setLimiteAdelantoEfectivoPesos(limiteAdelantoEfectivoPesos);
        Assert.assertEquals(limiteAdelantoEfectivoPesos, limitesYDisponibles.getLimiteAdelantoEfectivoPesos());
    }

    /**
     * Gets the limite adelanto efectivo dolares.
     *
     * @return the limite adelanto efectivo dolares
     */
    @Test
    public void getLimiteAdelantoEfectivoDolares() {
        String limiteAdelantoEfectivoDolares = new String("8.000,00");
        limitesYDisponibles.setLimiteAdelantoEfectivoDolares(limiteAdelantoEfectivoDolares);
        Assert.assertEquals(limiteAdelantoEfectivoDolares, limitesYDisponibles.getLimiteAdelantoEfectivoDolares());
    }

    /**
     * Gets the codigo error.
     *
     * @return the codigo error
     */
    @Test
    public void getCodigoError() {
        String codError = new String("0001");
        limitesYDisponibles.setCodigoError(codError);
        Assert.assertEquals(codError, limitesYDisponibles.getCodigoError());
    }

    /**
     * Gets the mensaje error.
     *
     * @return the mensaje error
     */
    @Test
    public void getMensajeError() {
        String mensajeError = new String("Se produjo un error.");
        limitesYDisponibles.setMensajeError(mensajeError);
        Assert.assertEquals(mensajeError, limitesYDisponibles.getMensajeError());
    }

    /**
     * Gets the checks for limite unificado.
     *
     * @return the checks for limite unificado
     */
    @Test
    public void getHasLimiteUnificado() {
        Boolean hasLimiteUnificado = Boolean.TRUE;
        limitesYDisponibles.setHasLimiteUnificado(hasLimiteUnificado);
        Assert.assertEquals(hasLimiteUnificado, limitesYDisponibles.getHasLimiteUnificado());
    }

    /**
     * Gets the mostrar saldo en cuotas.
     *
     * @return the mostrar saldo en cuotas
     */
    @Test
    public void getMostrarSaldoEnCuotas() {
        Boolean mostrarSaldoEnCuotas = Boolean.TRUE;
        limitesYDisponibles.setMostrarSaldoEnCuotas(mostrarSaldoEnCuotas);
        Assert.assertEquals(mostrarSaldoEnCuotas, limitesYDisponibles.getMostrarSaldoEnCuotas());
    }

    /**
     * Gets the mostrar saldo en compras.
     *
     * @return the mostrar saldo en compras
     */
    @Test
    public void getMostrarSaldoEnCompras() {
        Boolean mostrarSaldoEnCompras = Boolean.TRUE;
        limitesYDisponibles.setMostrarSaldoEnCompras(mostrarSaldoEnCompras);
        Assert.assertEquals(mostrarSaldoEnCompras, limitesYDisponibles.getMostrarSaldoEnCompras());
    }

    /**
     * Gets the checks if is limite compra pesos.
     *
     * @return the checks if is limite compra pesos
     */
    @Test
    public void getIsLimiteCompraPesos() {
        Boolean isLimiteCompraPesos = Boolean.TRUE;
        limitesYDisponibles.setIsLimiteCompraPesos(isLimiteCompraPesos);
        Assert.assertEquals(isLimiteCompraPesos, limitesYDisponibles.getIsLimiteCompraPesos());
    }

    /**
     * Gets the checks if is limite compra dolares.
     *
     * @return the checks if is limite compra dolares
     */
    @Test
    public void getIsLimiteCompraDolares() {
        Boolean isLimiteCompraDolares = Boolean.TRUE;
        limitesYDisponibles.setIsLimiteCompraDolares(isLimiteCompraDolares);
        Assert.assertEquals(isLimiteCompraDolares, limitesYDisponibles.getIsLimiteCompraDolares());
    }

    /**
     * Gets the checks if is limite cuotas pesos.
     *
     * @return the checks if is limite cuotas pesos
     */
    @Test
    public void getIsLimiteCuotasPesos() {
        Boolean isLimiteCuotasPesos = Boolean.TRUE;
        limitesYDisponibles.setIsLimiteCuotasPesos(isLimiteCuotasPesos);
        Assert.assertEquals(isLimiteCuotasPesos, limitesYDisponibles.getIsLimiteCuotasPesos());
    }

    /**
     * Gets the checks if is limite cuotas dolares.
     *
     * @return the checks if is limite cuotas dolares
     */
    @Test
    public void getIsLimiteCuotasDolares() {
        Boolean isLimiteCuotasDolares = Boolean.TRUE;
        limitesYDisponibles.setIsLimiteCuotasDolares(isLimiteCuotasDolares);
        Assert.assertEquals(isLimiteCuotasDolares, limitesYDisponibles.getIsLimiteCuotasDolares());
    }

    /**
     * Gets the checks if is limite adelanto pesos.
     *
     * @return the checks if is limite adelanto pesos
     */
    @Test
    public void getIsLimiteAdelantoPesos() {
        Boolean isLimiteAdelantoPesos = Boolean.TRUE;
        limitesYDisponibles.setIsLimiteAdelantoPesos(isLimiteAdelantoPesos);
        Assert.assertEquals(isLimiteAdelantoPesos, limitesYDisponibles.getIsLimiteAdelantoPesos());
    }

    /**
     * Gets the checks if is limite adelanto dolare.
     *
     * @return the checks if is limite adelanto dolare
     */
    @Test
    public void getIsLimiteAdelantoDolares() {
        Boolean isLimiteAdelantoDolares = Boolean.TRUE;
        limitesYDisponibles.setIsLimiteAdelantoDolares(isLimiteAdelantoDolares);
        Assert.assertEquals(isLimiteAdelantoDolares, limitesYDisponibles.getIsLimiteAdelantoDolares());
    }

    /**
     * Gets the checks for error.
     *
     * @return the checks for error
     */
    @Test
    public void getHasError() {
        Boolean hasError = Boolean.TRUE;
        limitesYDisponibles.setHasError(hasError);
        Assert.assertEquals(hasError, limitesYDisponibles.getHasError());
    }
}
