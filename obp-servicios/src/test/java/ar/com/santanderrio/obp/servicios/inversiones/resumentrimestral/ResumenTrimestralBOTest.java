package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral;

import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.InfoRTFCuentaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.InfoRTFEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ResumenTrimestralBOTest {

    /** The dto request. */
    private List<InfoRTFCuentaEntity> listaCuentas = new ArrayList<InfoRTFCuentaEntity>();
    private List<InfoRTFCuentaEntity> listaCuentasExpected = new ArrayList<InfoRTFCuentaEntity>();

    private List<InfoRTFEntity> listaRTF = new ArrayList<InfoRTFEntity>();
    private List<InfoRTFEntity> listaRTFExpected = new ArrayList<InfoRTFEntity>();

    /**
     * Inits the.
     *
     * @throws ServiceException the service exception
     */
    @Before
    public void init() throws ServiceException {
        InfoRTFCuentaEntity cuenta = new InfoRTFCuentaEntity();
        InfoRTFEntity rtf = new InfoRTFEntity();

        cuenta.setNumeroCuenta("000040777382");
        cuenta.setSegmento("RTL");
        listaCuentas.add(cuenta);
        listaCuentasExpected.add(cuenta);

        cuenta = new InfoRTFCuentaEntity();
        cuenta.setNumeroCuenta("000040777382");
        cuenta.setSegmento("RTL");
        listaCuentas.add(cuenta);

        cuenta = new InfoRTFCuentaEntity();
        cuenta.setNumeroCuenta("000032986495");
        cuenta.setSegmento("RTL");
        listaCuentas.add(cuenta);
        listaCuentasExpected.add(cuenta);

        cuenta = new InfoRTFCuentaEntity();
        cuenta.setNumeroCuenta("000000453385");
        cuenta.setSegmento("RTL");
        listaCuentas.add(cuenta);
        listaCuentasExpected.add(cuenta);

        rtf.setId("1132496");
        rtf.setCuenta("000027487761");
        rtf.setDescripcion("ENERO MARZO");
        rtf.setNombre("ResumenFondos.ENE-MAR-2022.pdf");
        rtf.setAnio(2022);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("1071480");
        rtf.setCuenta("000000453385");
        rtf.setDescripcion("ENERO MARZO");
        rtf.setNombre("ResumenFondos.ENE-MAR-2022.pdf");
        rtf.setAnio(2022);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("1191850");
        rtf.setCuenta("000040777382");
        rtf.setDescripcion("ENERO MARZO");
        rtf.setNombre("ResumenFondos.ENE-MAR-2022.pdf");
        rtf.setAnio(2022);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("1191849");
        rtf.setCuenta("000040777382");
        rtf.setDescripcion("ENERO MARZO");
        rtf.setNombre("ResumenFondos.ENE-MAR-2022.pdf");
        rtf.setAnio(2022);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("578574");
        rtf.setCuenta("000000453385");
        rtf.setDescripcion("ABRIL JUNIO");
        rtf.setNombre("ResumenFondos.ABR-JUN-2021.pdf");
        rtf.setAnio(2022);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("608633");
        rtf.setCuenta("000000453385");
        rtf.setDescripcion("JULIO SEPTIEMBRE");
        rtf.setNombre("ResumenFondos.JUL-SEPT-2021.pdf");
        rtf.setAnio(2022);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("674834");
        rtf.setCuenta("000027487761");
        rtf.setDescripcion("JULIO SEPTIEMBRE");
        rtf.setNombre("ResumenFondos.JUL-SEPT-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("736659");
        rtf.setCuenta("000000453385");
        rtf.setDescripcion("OCTUBRE DICIEMBRE");
        rtf.setNombre("ResumenFondos.OCT-DIC-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("749543");
        rtf.setCuenta("000000453385");
        rtf.setDescripcion("OCTUBRE DICIEMBRE");
        rtf.setNombre("ResumenFondos.OCT-DIC-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("753098");
        rtf.setCuenta("000000453385");
        rtf.setDescripcion("OCTUBRE DICIEMBRE");
        rtf.setNombre("ResumenFondos.OCT-DIC-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("768490");
        rtf.setCuenta("000000453385");
        rtf.setDescripcion("OCTUBRE DICIEMBRE");
        rtf.setNombre("ResumenFondos.OCT-DIC-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("829399");
        rtf.setCuenta("000027487761");
        rtf.setDescripcion("OCTUBRE DICIEMBRE");
        rtf.setNombre("ResumenFondos.OCT-DIC-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("719687");
        rtf.setCuenta("000040777382");
        rtf.setDescripcion("JULIO SEPTIEMBRE");
        rtf.setNombre("ResumenFondos.JUL-SEPT-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("891970");
        rtf.setCuenta("000040777382");
        rtf.setDescripcion("OCTUBRE DICIEMBRE");
        rtf.setNombre("ResumenFondos.OCT-DIC-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("891971");
        rtf.setCuenta("000040777382");
        rtf.setDescripcion("OCTUBRE DICIEMBRE");
        rtf.setNombre("ResumenFondos.OCT-DIC-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf.setId("891971");
        rtf.setCuenta("000040777382");
        rtf.setDescripcion("OCTUBRE DICIEMBRE");
        rtf.setNombre("ResumenFondos.OCT-DIC-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("484395");
        rtf.setCuenta("000027487761");
        rtf.setDescripcion("ABRIL JUNIO");
        rtf.setNombre("ResumenFondos.ABR-JUN-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("467699");
        rtf.setCuenta("000027487761");
        rtf.setDescripcion("ABRIL JUNIO");
        rtf.setNombre("ResumenFondos.ABR-JUN-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("464800");
        rtf.setCuenta("000027487761");
        rtf.setDescripcion("ABRIL JUNIO");
        rtf.setNombre("ResumenFondos.ABR-JUN-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("449759");
        rtf.setCuenta("000000453385");
        rtf.setDescripcion("ENERO MARZO");
        rtf.setNombre("ResumenFondos.ENE-MAR-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);

        rtf = new InfoRTFEntity();
        rtf.setId("377498");
        rtf.setCuenta("000027487761");
        rtf.setDescripcion("ENERO MARZO");
        rtf.setNombre("ResumenFondos.ENE-MAR-2021.pdf");
        rtf.setAnio(2021);
        rtf.setVisto(false);
        listaRTF.add(rtf);
        listaRTFExpected.add(rtf);
    }

    @Test
    public void parsearCuentas() {

        List<InfoRTFCuentaEntity> listaCuentasFiltrada = filtrarListaCuentasDuplicadas(listaCuentas);
        // Evitar error de formateo por duplicidad de referencias en la lista
        for (InfoRTFCuentaEntity cuenta : listaCuentasFiltrada) {
            if (!cuenta.getNumeroCuenta().contains("/")) {
                cuenta.setNumeroCuenta(ISBANStringUtils.formatearNumeroCuenta(cuenta.getNumeroCuenta()));
            }
        }
        Assert.assertNotNull(listaCuentasFiltrada);
        Assert.assertEquals(listaCuentasFiltrada, listaCuentasExpected);
    }

    private List<InfoRTFCuentaEntity> filtrarListaCuentasDuplicadas(List<InfoRTFCuentaEntity> listaCuentas) {
        // Filtrar las cuentas titulos duplicadas
        Iterator<InfoRTFCuentaEntity> it = listaCuentas.iterator();
        List<InfoRTFCuentaEntity> listaCuentasFiltrada = new ArrayList<InfoRTFCuentaEntity>();

        while (it.hasNext()) {
            int flag = 1;
            InfoRTFCuentaEntity rtf = it.next();
            for (InfoRTFCuentaEntity el : listaCuentasFiltrada) {
                flag = 1;
                String cuenta = el.getNumeroCuenta();
                String cuentaRTF = rtf.getNumeroCuenta();

                if (cuenta.equals(cuentaRTF)) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) {
                listaCuentasFiltrada.add(rtf);
            }
        }

        return listaCuentasFiltrada;
    }

    @Test
    public void parsearCuentasRTF() {

        List<InfoRTFEntity> listaRTFFiltrada = filtrarCuentasRTFDuplicadas(listaRTF);
        for (InfoRTFEntity rtf : listaRTFFiltrada) {
            // Evitar error de formateo por duplicidad de referencias en la lista
            if (!rtf.getCuenta().contains("/")) {
                rtf.setCuenta(ISBANStringUtils.formatearNumeroCuenta(rtf.getCuenta()));
            }
        }
        Assert.assertNotNull(listaRTFFiltrada);
        Assert.assertEquals(listaRTFFiltrada, listaRTFExpected);
    }

    private List<InfoRTFEntity> filtrarCuentasRTFDuplicadas(List<InfoRTFEntity> listaRTF) {
        // Filtrar las cuentas RTF duplicadas
        Iterator<InfoRTFEntity> it = listaRTF.iterator();
        List<InfoRTFEntity> listaRTFFiltrada = new ArrayList<InfoRTFEntity>();

        while (it.hasNext()) {
            int flag = 1;
            InfoRTFEntity rtf = it.next();
            for (InfoRTFEntity el : listaRTFFiltrada) {
                flag = 1;
                if (el.equals(rtf)) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) {
                listaRTFFiltrada.add(rtf);
            }
        }

        return listaRTFFiltrada;
    }

}
