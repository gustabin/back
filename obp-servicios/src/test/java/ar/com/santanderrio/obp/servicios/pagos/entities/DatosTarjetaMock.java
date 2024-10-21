package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;

/**
 * @author florencia.n.martinez
 *
 */
public final class DatosTarjetaMock {

    private DatosTarjetaMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    public static List<DatosTarjeta> obtenerListaDatosTarjeta() {
        ArrayList<DatosTarjeta> datosTarjeta = new ArrayList<DatosTarjeta>();
		datosTarjeta.add(obtenerDatoTarjeta(BigDecimal.valueOf(320.00), BigDecimal.valueOf(3000.00), "", "01", "TI",
				BigDecimal.valueOf(1233.00), BigDecimal.valueOf(1111.00), "20", "03", BigDecimal.valueOf(7032.00),
				BigDecimal.valueOf(6328.00), "00004050710080531234", BigDecimal.valueOf(200.00),
				BigDecimal.valueOf(-1680.80), BigDecimal.valueOf(-9248.22), BigDecimal.valueOf(-127.80),
				BigDecimal.valueOf(-5137.22), BigDecimal.valueOf(-127.80), BigDecimal.valueOf(-5137.22),
				TipoCuentaTarjeta.TIPOCTA_VISA, BigDecimal.valueOf(60), BigDecimal.valueOf(58),
				BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000),
				BigDecimal.valueOf(10000)));
		datosTarjeta.add(obtenerDatoTarjeta(null, null, "", "03", "TI", null, null, "20", "02",
				BigDecimal.valueOf(5250.00), BigDecimal.valueOf(4625.00), "00003777920020216960",
				BigDecimal.valueOf(30.00), BigDecimal.valueOf(-11.99), BigDecimal.valueOf(-220.00),
				BigDecimal.valueOf(-11.99), BigDecimal.valueOf(-220.00), BigDecimal.valueOf(-11.99),
				BigDecimal.valueOf(-220.00), TipoCuentaTarjeta.TIPOCTA_AMEX, BigDecimal.valueOf(60),
				BigDecimal.valueOf(58), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000),
				BigDecimal.valueOf(10000)));
		datosTarjeta.add(obtenerDatoTarjeta(null, null, "", "03", "TI", null, null, "20", "02",
				BigDecimal.valueOf(5250.00), BigDecimal.valueOf(4625.00), "00003777920020216960",
				BigDecimal.valueOf(30.00), BigDecimal.valueOf(-11.99), BigDecimal.valueOf(-220.00),
				BigDecimal.valueOf(-11.99), BigDecimal.valueOf(-220.00), BigDecimal.valueOf(-11.99),
				BigDecimal.valueOf(-220.00), TipoCuentaTarjeta.TIPOCTA_AMEX, BigDecimal.valueOf(60),
				BigDecimal.valueOf(58), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000),
				BigDecimal.valueOf(10000)));
		datosTarjeta.trimToSize();
        return datosTarjeta;
    }

    public static DatosTarjeta obtenerDatoTarjeta(BigDecimal adelantosDolaresTC, BigDecimal adelantosPesosTC, String alias, 
            String cicloTarjetaCredito, String codigoTitularidad,
            BigDecimal consumosDolaresTC, BigDecimal consumosPesosTC, String estadoTarjetaCredito,
            String formaPagoTarjetaCredito, BigDecimal limiteCompraPesosTC, BigDecimal limiteFinanciacionTC, String numeroTarjeta,
            BigDecimal pagoMinimoPesosTC, 
            BigDecimal saldoActualDolaresTC, BigDecimal saldoActualPesosTC, BigDecimal saldoDolaresTC, BigDecimal saldoPesosTC, BigDecimal 
            saldoUltimoCierreDolaresTC, BigDecimal saldoUltimoCierrePesosTC, TipoCuentaTarjeta tipoCuentaTarjeta, 
            BigDecimal cotizacionVendedor, BigDecimal cotizacionComprador, BigDecimal saldoPendienteDolares, BigDecimal saldoPendientePesos,
            BigDecimal saldoTotalConvDolares, BigDecimal saldoTotalConvPesos) {
        DatosTarjeta datosTarjeta = new DatosTarjeta();
        datosTarjeta.setAdelantosDolaresTC(adelantosDolaresTC);
        datosTarjeta.setAdelantosPesosTC(adelantosPesosTC);
        datosTarjeta.setAlias(alias);
        datosTarjeta.setCicloTarjetaCredito(cicloTarjetaCredito);
        datosTarjeta.setCodigoTitularidad(codigoTitularidad);
        datosTarjeta.setConsumosDolaresTC(consumosDolaresTC);
        datosTarjeta.setConsumosPesosTC(consumosPesosTC);
        datosTarjeta.setEstadoTarjetaCredito(estadoTarjetaCredito);
        datosTarjeta.setFechaPagoProgramado(new Date());
        datosTarjeta.setFechaProximoCierreTC(new Date());
        datosTarjeta.setFechaProximoVencimientoTC(new Date());
        datosTarjeta.setFechaUltimoCierreTC(new Date());
        datosTarjeta.setFechaUltimoPagoTC(new Date());
        datosTarjeta.setFechaVencimientoLiquidacionTC(new Date());
        datosTarjeta.setFormaPagoTarjetaCredito(formaPagoTarjetaCredito);
        datosTarjeta.setLimiteCompraPesosTC(limiteCompraPesosTC);
        datosTarjeta.setLimiteFinanciacionTC(limiteFinanciacionTC);
        datosTarjeta.setNumeroTarjeta(numeroTarjeta);
        datosTarjeta.setPagoMinimoPesosTC(pagoMinimoPesosTC);
        datosTarjeta.setSaldoActualDolaresTC(saldoActualDolaresTC);
        datosTarjeta.setSaldoActualPesosTC(saldoActualPesosTC);
        datosTarjeta.setSaldoDolaresTC(saldoDolaresTC);
        datosTarjeta.setSaldoPesosTC(saldoPesosTC);
        datosTarjeta.setSaldoUltimoCierreDolaresTC(saldoUltimoCierreDolaresTC);
        datosTarjeta.setSaldoUltimoCierrePesosTC(saldoUltimoCierrePesosTC);
        datosTarjeta.setTipoCuentaTarjeta(tipoCuentaTarjeta);
        datosTarjeta.setCotizacionVendedor(cotizacionVendedor);
        datosTarjeta.setCotizacionComprador(cotizacionComprador);
        datosTarjeta.setSaldoPendienteDolares(saldoPendienteDolares);
        datosTarjeta.setSaldoPendientePesos(saldoPendientePesos);
        datosTarjeta.setSaldoTotalConvDolares(saldoTotalConvDolares);
        datosTarjeta.setSaldoTotalConvPesos(saldoTotalConvPesos);
        return datosTarjeta;
    }
}
