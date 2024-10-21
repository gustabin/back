package ar.com.santanderrio.obp.servicios.prestamos.mock;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoInEntity;

/** 
 * @author florencia.n.martinez
 *
 */
public final class PrestamoInEntityMock {

    private PrestamoInEntityMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtiene la entidad prestamo in entity OK.
     *
     * @return the prestamo in entity
     */
    public static PrestamoInEntity obtenerPrestamoInEntityOKParaSimulador() {
        PrestamoInEntity prestamoInEntity = new PrestamoInEntity();
        prestamoInEntity.setFase("S");
        prestamoInEntity.setTipoCuenta("02");
        prestamoInEntity.setSucursalCuenta("023");
        prestamoInEntity.setNumeroCuenta("1234567");
        prestamoInEntity.setImportePrestamo("00000100000000");
        prestamoInEntity.setCantidadCuotas("036");
        prestamoInEntity.setFechaPrimerCuota("20170121");
        prestamoInEntity.setTipoTasa("T");
        prestamoInEntity.setCodProductoUG("04");
        prestamoInEntity.setCodSubpUG("0011");
        prestamoInEntity.setDestFondosUG("blabl");
        prestamoInEntity.setCodDivisaO("000");
        prestamoInEntity.setValorTasa("034123456");
        prestamoInEntity.setClausulaRevUG("Blah");
        prestamoInEntity.setSucPaquete("0023");
        prestamoInEntity.setNumPaquete("000123456789");
        prestamoInEntity.setTpoPolizaDs("123");
        prestamoInEntity.setTpoRiesgoDs("345");
        prestamoInEntity.setCodCondici("EO");
        prestamoInEntity.setNIO(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 24));
        prestamoInEntity.setDestFondosComerO("00345");
        prestamoInEntity.setSucPrest(StringUtils.repeat(String.valueOf(NumberUtils.INTEGER_ZERO), 4));
        prestamoInEntity.setNumPrest(StringUtils.repeat(String.valueOf(NumberUtils.INTEGER_ZERO), 12));
        prestamoInEntity.setEntCuentaProve("0265");
        prestamoInEntity.setSucCuentaProve("0023");
        prestamoInEntity.setNroCuentaProve("0000123456789123");
        prestamoInEntity.setDivisaCtaProve("000");
        prestamoInEntity.setImporteAbono(StringUtils.repeat(String.valueOf(NumberUtils.INTEGER_ZERO), 17));
        return prestamoInEntity;
    }

    /**
     * Obtiene la entidad prestamo in entity con error de formato de las
     * regular expression.
     *
     * @return the prestamo in entity
     */
    public static PrestamoInEntity obtenerPrestamoInEntityConErrorFormatoRegexp() {
        PrestamoInEntity prestamoInEntity = obtenerPrestamoInEntityOKParaSimulador();
        prestamoInEntity.setFase("SL");
        return prestamoInEntity;
    }
}
