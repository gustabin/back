package ar.com.santanderrio.obp.servicios.pagos.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.AfipCategoriaCincoMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.AfipCategoriaDosMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.AfipCategoriaUnoMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.CelularRecargaMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.DomesticoMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.ElectronicoMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.ImporteFijoPorDefectoMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.ImporteVariablePorDefectoMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.MediosPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.MonotributoMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.SubeRecargaMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.VepMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * The Class MediosPagoBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class MediosPagoBOTest {

    /** The medios pago BO. */
    @InjectMocks
    private MediosPagoBOImpl mediosPagoBO;

    /** The mock medios pago DAO. */
    @Mock
    private BuscadorEmpresaDAO mockMediosPagoDAO;

    /** The mock mensaje DAO. */
    @Mock
    private MensajeDAO mockMensajeDAO;

    /**
     * Gets the by codigo.
     *
     * @return the by codigo
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void getByCodigo() throws BusinessException, DAOException {
        String codigo = "code";
        MedioPago medioPago = new MedioPago();
        medioPago.setNombreFantasia("Superman");
        Mockito.when(mockMediosPagoDAO.getByCodigo(codigo)).thenReturn(medioPago);

        Respuesta<MedioPago> respuesta = mediosPagoBO.getByCodigo(codigo);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Gets the by codigo error.
     *
     * @return the by codigo error
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void getByCodigoError() throws BusinessException, DAOException {
        String codigo = "code";

        Respuesta<Mensaje> rtaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje");
        rtaMensaje.setRespuesta(mensaje);

        Mockito.when(mockMediosPagoDAO.getByCodigo(codigo)).thenReturn(null);
        Mockito.when(mockMensajeDAO.obtenerMensajePorCodigo("1060")).thenReturn(mensaje);
        Respuesta<MedioPago> respuesta = mediosPagoBO.getByCodigo(codigo);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        Assert.assertEquals("Mensaje", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }


    /**
     * Gets the by cuit servicio.
     *
     * @return the by cuit servicio
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void getByCuitServicio() throws DAOException, BusinessException {
        String cuit = "30737654658";
        String servicio = "servicio";
        MedioPago medioPago = new MedioPago();
        medioPago.setCuit(cuit);
        medioPago.setServicio(servicio);
        Mockito.when(mockMediosPagoDAO.getByCuitServicio(cuit, servicio)).thenReturn(medioPago);

        Respuesta<MedioPago> respuesta = mediosPagoBO.getByCuitServicio(cuit, servicio);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("servicio", respuesta.getRespuesta().getServicio());
        Assert.assertEquals("30737654658", respuesta.getRespuesta().getCuit());
    }

    /**
     * Gets the by cuit servicio DAO exception.
     *
     * @return the by cuit servicio DAO exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void getByCuitServicioDAOException() throws DAOException {
        String cuit = "30737654658";
        String servicio = "servicio";
        Mockito.when(mockMediosPagoDAO.getByCuitServicio(cuit, servicio)).thenThrow(new DAOException());

        Respuesta<MedioPago> respuesta = mediosPagoBO.getByCuitServicio(cuit, servicio);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void cuandoObtengoTipoMedioPagoDevuelveTipoElectronico() {
        // Cuando
        cargarTipoMedioPagoBO(new ElectronicoMedioPagoBOImpl());
        MedioPago medioPago = new MedioPago("DONACIONES|UNICEF|30685833928|N|||||||$|S|N|UNCF|;SU NUMERO DE DOCUMENTO CONSTA DE ENTRE 6 Y 8 DIGITOS|DNCS|DONACIONES|0|010|UNICEF|NUMERO DE DOCUMENTO|1|2|1|ARS||||||||;||N||||||||N|||||||N||||0||||N|;|0.01|S||0|000000|000000|");
       
        // Entonces
        TipoMedioPagoBO tipoMedioPagoBO = mediosPagoBO.obtenerTipoMedioPago(medioPago);

        // Se espera
        Assert.assertTrue(TipoNuevoPagoEnum.ELECTRONICO.equals(tipoMedioPagoBO.getTipoNuevoPagoEnum()));
    }

    @Test
    public void cuandoObtengoTipoMedioPagoDevuelveTipoPagoDomestico() {
        // cuando
        String unMedioDePago = "SERVICIO DOMESTICO JUBILADO|DOMESTICO JUBILADO 06 H|33693450239|N|||||||$|S|N|D142|;SU CUIT/CUIL EMPLEADO: CONSTA DE 11 DIGITOS|DOJU|SERVICIO DOMESTICO JUBILADO|1|072|DOMESTICO JUBILADO 06 H|CUIT/CUIL EMPLEADO:|1|1|2|ARS|3|||||||;||N||||||||N|||||||N||||0||||N|;|0.01|||0|000000|000000|";
        MedioPago medioPago = new MedioPago(unMedioDePago);
       
        DomesticoMedioPagoBOImpl domestico = new DomesticoMedioPagoBOImpl();
        domestico.setCodigoRubroDomesticos( Arrays.asList("DOAC","DOJU","DOME") );
        cargarTipoMedioPagoBO(domestico);
        
        // espera
        Assert.assertTrue(TipoNuevoPagoEnum.DOMESTICO.equals(mediosPagoBO.obtenerTipoMedioPago(medioPago).getTipoNuevoPagoEnum()));
    }

    @Test
    public void cuandoObtengoTipoMedioPagoDevuelveTipoPagoAfipCategoriaUno() {
        // cuando
        String unMedioDePago = "IMPUESTOS - MONOT. IMPOSITIV. HASTA 06/0|MONOTR.IMPOSIT.(CAT 1)|33693450239|N|||||||$|S|N|MTC1|;SU NRO C.U.I.T./C.U.I.L. CONSTA DE 11 DIGITOS|MOPF|IMPUESTOS - MONOT. IMPOSITIV. HASTA 06/0|1|068|MONOTR.IMPOSIT.(CAT 1)|NRO C.U.I.T./C.U.I.L.|1|1|2|ARS|1|||||||;||N||||||||N|||||||N||||0||||N|;|0.01|||0|000000|000000|";
        MedioPago medioPago = new MedioPago(unMedioDePago);
       
        DomesticoMedioPagoBOImpl domestico = new DomesticoMedioPagoBOImpl();
        domestico.setCodigoRubroDomesticos( Arrays.asList("DOAC","DOJU","DOME") );
        
        AfipCategoriaUnoMedioPagoBOImpl afip = new AfipCategoriaUnoMedioPagoBOImpl();
        afip.setDomesticoMedioPagoBO(domestico);
        cargarTipoMedioPagoBO(afip);
        
        // espera
        Assert.assertTrue(TipoNuevoPagoEnum.AFIP_CAT1.equals(mediosPagoBO.obtenerTipoMedioPago(medioPago).getTipoNuevoPagoEnum()));
    }
    
    @Test
    public void cuandoObtengoTipoMedioPagoDevuelveTipoPagoAfipCategoriaDos() {
        // cuando
        String unMedioDePago = "IMPUESTOS - MONOT. IMPOSITIV. HASTA 06/0|MONOTR.IMPOSIT.(CAT 1)|33693450239|N|||||||$|S|N|MTC1|;SU NRO C.U.I.T./C.U.I.L. CONSTA DE 11 DIGITOS|MOPF|IMPUESTOS - MONOT. IMPOSITIV. HASTA 06/0|1|068|MONOTR.IMPOSIT.(CAT 1)|NRO C.U.I.T./C.U.I.L.|1|1|2|ARS|2|||||||;||N||||||||N|||||||N||||0||||N|;|0.01|||0|000000|000000|";
        MedioPago medioPago = new MedioPago(unMedioDePago);
       
        DomesticoMedioPagoBOImpl domestico = new DomesticoMedioPagoBOImpl();
        domestico.setCodigoRubroDomesticos( Arrays.asList("DOAC","DOJU","DOME") );
        
        AfipCategoriaDosMedioPagoBOImpl afip = new AfipCategoriaDosMedioPagoBOImpl();
        afip.setDomesticoMedioPagoBO(domestico);
        cargarTipoMedioPagoBO(afip);
        
        // espera
        Assert.assertTrue(TipoNuevoPagoEnum.AFIP_CAT2.equals(mediosPagoBO.obtenerTipoMedioPago(medioPago).getTipoNuevoPagoEnum()));
    }

    @Test
    public void cuandoObtengoTipoMedioPagoDevuelveTipoPagoMonotributo() {
        // cuando
        String unMedioDePago = "IMPUESTOS - MONOT. IMPOSITIV. HASTA 06/0|MONOTR.IMPOSIT.(CAT 1)|33693450239|N|||||||$|S|N|MTC1|;SU NRO C.U.I.T./C.U.I.L. CONSTA DE 11 DIGITOS|MOPF|IMPUESTOS - MONOT. IMPOSITIV. HASTA 06/0|1|068|MONOTR.IMPOSIT.(CAT 1)|NRO C.U.I.T./C.U.I.L.|1|1|2|ARS|3|||||||;||N||||||||N|||||||N||||0||||N|;|0.01|||0|000000|000000|";
        MedioPago medioPago = new MedioPago(unMedioDePago);
       
        DomesticoMedioPagoBOImpl domestico = new DomesticoMedioPagoBOImpl();
        domestico.setCodigoRubroDomesticos( Arrays.asList("DOAC","DOJU","DOME") );
        
        MonotributoMedioPagoBOImpl monotributo = new MonotributoMedioPagoBOImpl();
        monotributo.setDomesticoMedioPagoBO(domestico);
        cargarTipoMedioPagoBO(monotributo);
        
        // espera
        Assert.assertTrue(TipoNuevoPagoEnum.MONOTRIBUTO.equals(mediosPagoBO.obtenerTipoMedioPago(medioPago).getTipoNuevoPagoEnum()));
    }

    @Test
    public void cuandoObtengoTipoMedioPagoDevuelveTipoPagoAfipCategoriaCinco() {
        // cuando
        String unMedioDePago = "IMPUESTOS - MONOT. IMPOSITIV. HASTA 06/0|MONOTR.IMPOSIT.(CAT 1)|33693450239|N|||||||$|S|N|MTC1|;SU NRO C.U.I.T./C.U.I.L. CONSTA DE 11 DIGITOS|MOPF|IMPUESTOS - MONOT. IMPOSITIV. HASTA 06/0|1|068|MONOTR.IMPOSIT.(CAT 1)|NRO C.U.I.T./C.U.I.L.|1|1|2|ARS|5|||||||;||N||||||||N|||||||N||||0||||N|;|0.01|||0|000000|000000|";
        MedioPago medioPago = new MedioPago(unMedioDePago);
       
        DomesticoMedioPagoBOImpl domestico = new DomesticoMedioPagoBOImpl();
        domestico.setCodigoRubroDomesticos( Arrays.asList("DOAC","DOJU","DOME") );
        
        AfipCategoriaCincoMedioPagoBOImpl afipCategoriaCinco = new AfipCategoriaCincoMedioPagoBOImpl();
        afipCategoriaCinco.setDomesticoMedioPagoBO(domestico);
        cargarTipoMedioPagoBO(afipCategoriaCinco);
        
        // espera
        Assert.assertTrue(TipoNuevoPagoEnum.AFIP_CAT5.equals(mediosPagoBO.obtenerTipoMedioPago(medioPago).getTipoNuevoPagoEnum()));
    }

    @Test
    public void cuandoObtengoTipoMedioPagoDevuelveTipoPagoVep() {
        // cuando
        String unMedioDePago = "IMPUESTOS - AFIP|AFIP - PAGO DE IMPUESTOS AFIP (VEP)|33693450239|N|||||||$|S|N|SEA |;SU CUIT DEL CONTRIBUYENTE DE CONSTA DE 11 DIGITOS|PSEA|IMPUESTOS - AFIP|1|001|VEP|CUIT del contribuyente de|1|0|3|ARS|||||F|||;||N||||||||N|||||||N||||0||||N|;|0.01|||0|000000|000000|";
        MedioPago medioPago = new MedioPago(unMedioDePago);
       
        VepMedioPagoBOImpl vep = new VepMedioPagoBOImpl();
        cargarTipoMedioPagoBO(vep);
        
        // espera
        Assert.assertTrue(TipoNuevoPagoEnum.VEP.equals(mediosPagoBO.obtenerTipoMedioPago(medioPago).getTipoNuevoPagoEnum()));
    }

    @Test
    public void cuandoObtengoTipoMedioPagoDevuelveImporteFijoPorDefecto() {
        // cuando
        String unMedioDePago = "IMPUESTOS MUNICIPALES|MUNICIPALIDAD DEL PILAR|30999005825|N|||||||$|S|N|MNPL|;SU CLAVE DE PAGO RED BANELCO CONSTA DE 12 DIGITOS|MCPS|IMPUESTOS MUNICIPALES|0|015|MUNICIPALIDAD DEL PILAR|CLAVE DE PAGO RED BANELCO|1|0|3|ARS||||||||;||S|IMPUESTOS, RENTAS, TASAS|MUNICIPALIDAD DEL P|0013296348|PARTIDA CONSTA DE 14 POSICIONES NUMERICAS|PARTIDA|PARTIDA CONSTA DE 14 POSICIONES NUMERICAS|14|S|HIPERMERCADOS|MUNICIPALIDAD DE PILAR|PARTIDA CONSTA DE 6 POSICIONES NUMERICAS|PARTIDA|PARTIDA CONSTA DE 6 POSICIONES NUMERICAS|6|N||||0||||N|;|0.01|||1|000000|000000|";
        MedioPago medioPago = new MedioPago(unMedioDePago);
       
        ImporteFijoPorDefectoMedioPagoBOImpl importeFijo = new ImporteFijoPorDefectoMedioPagoBOImpl();
        cargarTipoMedioPagoBO(importeFijo);
        
        // espera
        Assert.assertTrue(TipoNuevoPagoEnum.CUSTOM_IMPORTE_FIJO.equals(mediosPagoBO.obtenerTipoMedioPago(medioPago).getTipoNuevoPagoEnum()));
    }

    @Test
    public void cuandoObtengoTipoMedioPagoDevuelveImporteVariablePorDefecto() {
        // cuando
        String unMedioDePago = "OTROS SERVICIOS|SPARKLING|30678617306|N|||||||$|S|N|SPAR|;SU CODIGO PAGO ELECTRONICO CONSTA DE 8 DIGITOS|SVAR|OTROS SERVICIOS|0|019|SPARKLING|CODIGO PAGO ELECTRONICO|1|2|3|ARS||||||||;||N||||||||N|||||||N||||0||||N|;|0.01|S||1|000000|000000|";
        MedioPago medioPago = new MedioPago(unMedioDePago);
       
        ImporteVariablePorDefectoMedioPagoBOImpl importeVariable = new ImporteVariablePorDefectoMedioPagoBOImpl();
        cargarTipoMedioPagoBO(importeVariable);
        
        // espera
        Assert.assertTrue(TipoNuevoPagoEnum.CUSTOM_IMPORTE_VARIABLE.equals(mediosPagoBO.obtenerTipoMedioPago(medioPago).getTipoNuevoPagoEnum()));
    }

    @Test
    public void cuandoObtengoTipoMedioPagoDevuelveCelularRecargaMedioPago() {
        // cuando
        String unMedioDePago = "RECARGA DE CELULARES|RECARGA PERSONAL|30678186445|N|PERSONAL|;SU NUMERO DE REFERENCIA CONSTA DE 10 DIGITOS|NUMERO DE REFERENCIA|TELECOM PERSONAL|10||$|S|N|REPE|;SU CODAREA(SIN 0)+NRO(SIN15) CONSTA DE 10 DIGITOS|RCEL|RECARGAS|0|024|RECARGA PERSONAL|CODAREA(SIN 0)+NRO(SIN15)|1|2|1|ARS|||||P|||;||N|PAGO DE SERVICIO - TEL CELULAR|TELECOM PERSONAL DA|0007400781|REF PAGO CONSTA DE 15 POSICIONES NUMERICAS|REF PAGO|REF PAGO CONSTA DE 15 POSICIONES NUMERICAS|15|N|||||||N||||0||||N|;|0.01|S||0|000000|000000|";
        MedioPago medioPago = new MedioPago(unMedioDePago);
       
        CelularRecargaMedioPagoBOImpl celularRecarga = new CelularRecargaMedioPagoBOImpl();
        cargarTipoMedioPagoBO(celularRecarga);
        
        // espera
        Assert.assertTrue(TipoNuevoPagoEnum.CELULAR_RECARGA.equals(mediosPagoBO.obtenerTipoMedioPago(medioPago).getTipoNuevoPagoEnum()));
    }

    @Test
    public void cuandoObtengoTipoMedioPagoDevuelveSubeRecargaMedioPago() {
        // cuando
        String unMedioDePago = "RECARGAS|RECARGA SUBE|33621364559|N|||||||$|S|N|SUBE|;SU NUMERO DE TARJETA CONSTA DE 16 DIGITOS|RCEL|RECARGAS|0|024|RECARGA SUBE|NUMERO DE TARJETA|1|2|1|ARS|||||P|||;||N||||||||N|||||||N||||0||||N|;|0.01|||0|000000|000000|";
        MedioPago medioPago = new MedioPago(unMedioDePago);
       
        SubeRecargaMedioPagoBOImpl subeRecarga = new SubeRecargaMedioPagoBOImpl();
        cargarTipoMedioPagoBO(subeRecarga);
        
        // espera
        Assert.assertTrue(TipoNuevoPagoEnum.SUBE_RECARGA.equals(mediosPagoBO.obtenerTipoMedioPago(medioPago).getTipoNuevoPagoEnum()));
    }

    private void cargarTipoMedioPagoBO(TipoMedioPagoBO tipoMedioPagoBO) {
        List <TipoMedioPagoBO> tiposMedioPagoBO = new ArrayList<TipoMedioPagoBO>();
        tiposMedioPagoBO.add(tipoMedioPagoBO);
        mediosPagoBO.setTiposMedioDePagoBO(tiposMedioPagoBO);
    }

}
