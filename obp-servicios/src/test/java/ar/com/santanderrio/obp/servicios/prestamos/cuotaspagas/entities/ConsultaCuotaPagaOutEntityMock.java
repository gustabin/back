package ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ConsultaCuotaPagaOutEntityMock.
 *
 * @author florencia.n.martinez
 */
public final class ConsultaCuotaPagaOutEntityMock {
    
    private ConsultaCuotaPagaOutEntityMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtiene la consulta cuota paga out entity.
     *
     * @return the consulta cuota paga out entity
     */
    public static ConsultaCuotaPagaOutEntity obtenerConsultaCuotaPagaOutEntity() {
        ConsultaCuotaPagaOutEntity outEntity = new ConsultaCuotaPagaOutEntity();
        outEntity.setCuotasPagas(obtenerPrestamoCuotasPagas());
        return outEntity;
    }

    /**
     * Obtiene el prestamo con cuotas pagas.
     *
     * @return the list
     */
    private static List<PrestamoCuotaPagaOutEntity> obtenerPrestamoCuotasPagas() {
        List<PrestamoCuotaPagaOutEntity> cuotasPagasOutEntity = new ArrayList<PrestamoCuotaPagaOutEntity>();
        cuotasPagasOutEntity.add(obtenerPrestamoCuotaPagaOutEntity("00034", "2017-03-13", "00000000034500000",
                "00000000034000000", "00000000034000000", "00000000003400000", "00000000000340000", "034500000",
                "045420000", "045420000", "045420000", "00000000003400000", "00000000003400000", "00000000003400000",
                "00000000003400000"));
        return cuotasPagasOutEntity;
    }

    /**
     * Obtiene el prestamo cuota paga out entity.
     *
     * @param numrec
     *            the numrec
     * @param feliq
     *            the feliq
     * @param saldoant
     *            the saldoant
     * @param capinire
     *            the capinire
     * @param intinire
     *            the intinire
     * @param seginire
     *            the seginire
     * @param importeCargo
     *            the importe cargo
     * @param tna
     *            the tna
     * @param tae
     *            the tae
     * @param cftna
     *            the cftna
     * @param cftnasi
     *            the cftnasi
     * @param impinire
     *            the impinire
     * @param gasinire
     *            the gasinire
     * @param cominire
     *            the cominire
     * @param saldopost
     *            the saldopost
     * @return the prestamo cuota paga out entity
     */
    public static PrestamoCuotaPagaOutEntity obtenerPrestamoCuotaPagaOutEntity(String numrec, String feliq,
            String saldoant, String capinire, String intinire, String seginire, String importeCargo, String tna,
            String tae, String cftna, String cftnasi, String impinire, String gasinire, String cominire,
            String saldopost) {
        PrestamoCuotaPagaOutEntity outEntity = new PrestamoCuotaPagaOutEntity();
        outEntity.setNumrec(numrec);
        outEntity.setFeliq(feliq);
        outEntity.setSaldoant(saldoant);
        outEntity.setCapinire(capinire);
        outEntity.setIntinire(intinire);
        outEntity.setSeginire(seginire);
        outEntity.setImporteCargo(importeCargo);
        outEntity.setTna(tna);
        outEntity.setTae(tae);
        outEntity.setCftna(cftna);
        outEntity.setCftnasi(cftnasi);
        outEntity.setImpinire(impinire);
        outEntity.setGasinire(gasinire);
        outEntity.setCominire(cominire);
        outEntity.setSaldopost(saldopost);
        return outEntity;
    }
}
