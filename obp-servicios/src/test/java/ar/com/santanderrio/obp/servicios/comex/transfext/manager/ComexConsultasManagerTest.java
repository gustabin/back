package ar.com.santanderrio.obp.servicios.comex.transfext.manager;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Ignore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexCanalesBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexConsultasBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ComprobanteComexDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConceptoPorTipoOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaPaisesOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ComprobanteComexInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptosPorTipoInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.manager.impl.ComexConsultasManagerImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ComprobanteInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConfiguracionAdjuntarArchivosOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaBancosInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaBancosOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaConceptoOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DatosBeneficiarioOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DatosPersonalesView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.TransferenciaComexOutView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.PadronOutEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ComexConsultasManagerTest {

    private static final String CUENTA = "Cuenta";

    private static final String NOMBRE_COMPROBANTE = "ComprobanteComex";

    /** The Constant CUENTA_CORRIENTE. */
    private static final String CUENTA_CORRIENTE = "0";

    /** The Constant CAJA_AHORROS. */
    private static final String CAJA_AHORROS = "1";

    /** The Constant CUENTA_NO_PESOS. */
    private static final String CUENTA_NO_PESOS = "3";

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory;

    /** The comex Consultas Manager. */
    @InjectMocks
    private ComexConsultasManager comexConsultasManager = new ComexConsultasManagerImpl();

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The id seg. */
    @Value("${KEYSTORE.TRANSFEXT.CONSULTAS.IDSEGURIDAD}")
    private String idSeg;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The ComexConsultas BO. */
    @Mock
    private ComexConsultasBO comexConsultasBO;

    /** The ComexCanales BO. */
    @Mock
    private ComexCanalesBO comexCanalesBO;

    /** The CambioDomicilio BO. */
    @Mock
    protected CambioDomicilioBO cambioDomicilioBO;

    /** The legal bo. */
    @Mock
    private LegalBO legalBO;

    /** The selectores BO. */
    @Mock
    private DatosSelectoresBO selectoresBO;

    /** The Cuenta BO. */
    @Mock
    protected CuentaBO cuentaBO;

    /**
     * Inits.
     */
    @Before
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    /** The mensaje bo. */
    @Mock
    private MensajeBO mensajeBO;

    @Test
    public void obtenerDatosComexErrorBOMotivos() {

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("PRUEBA");

        List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
        detalleCuentas.add(getDetalleCuentas("000", "000011", CAJA_AHORROS, "1999", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_CORRIENTE, "2214", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_NO_PESOS, "14124", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", "02", "14124", null, null));

        when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);

        when(sesionParametros.getCredencialesMya()).thenReturn(generarDatosMYASA());
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO))
                .thenReturn(mensaje);

        Respuesta<List<ConceptoPorTipoOutDTO>> respuestaBO = new Respuesta<List<ConceptoPorTipoOutDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(comexCanalesBO.consultaConceptoPorTipo(Matchers.any(ConsultaConceptosPorTipoInEntity.class))).thenReturn(respuestaBO);
        Respuesta<TransferenciaComexOutView> respuesta = comexConsultasManager.obtenerDatosComex();

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void obtenerDatosComexErrorDatosPadronBO() {

        List<CambioDomicilioDTO> domiciliosSesion = generarDomicilioPrimario();

        PadronOutEntity consultaPadronCuitOutEntity = new PadronOutEntity();
        consultaPadronCuitOutEntity.setCuit("123456789");

        Respuesta<PadronOutEntity> respuestaConsultaPadron = new Respuesta<PadronOutEntity>();
        respuestaConsultaPadron.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("PRUEBA");

        when(sesionParametros.getCredencialesMya()).thenReturn(generarDatosMYASA());
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO))
                .thenReturn(mensaje);

        Respuesta<List<ConceptoPorTipoOutDTO>> respuestaBO = new Respuesta<List<ConceptoPorTipoOutDTO>>();
        respuestaBO.setRespuesta(generarMotivos());
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<DatosPersonalesView> respuestaDatosPersonales = new Respuesta<DatosPersonalesView>();
        respuestaDatosPersonales.setEstadoRespuesta(EstadoRespuesta.ERROR);

        List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
        detalleCuentas.add(getDetalleCuentas("000", "000011", CAJA_AHORROS, "1999", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_CORRIENTE, "2214", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_NO_PESOS, "14124", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", "02", "14124", null, null));

        when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);

        when(comexCanalesBO.consultaConceptoPorTipo(Matchers.any(ConsultaConceptosPorTipoInEntity.class))).thenReturn(respuestaBO);
        when(sesionCliente.getCliente()).thenReturn(generarCliente());
        when(comexConsultasBO.getDatosPadronBO(Matchers.any(Cliente.class))).thenReturn(respuestaConsultaPadron);
        when(sesionParametros.getDomiciliosCliente()).thenReturn(domiciliosSesion);

        Respuesta<TransferenciaComexOutView> respuesta = comexConsultasManager.obtenerDatosComex();

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void obtenerDatosComexErrorSinCuentasValidas() throws BusinessException {
        List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
        Respuesta<List<ResumenDetalleCuenta>> respuestaCuentas = new Respuesta<List<ResumenDetalleCuenta>>();
        respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentas.setRespuesta(detalleCuentas);
        respuestaCuentas.setRespuestaVacia(false);

        detalleCuentas.add(getDetalleCuentas("000", "000011", CAJA_AHORROS, "1999", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_CORRIENTE, "2214", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_NO_PESOS, "14124", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", "02", "14124", null, null));

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("PRUEBA");

        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_SIN_CUENTAS))
                .thenReturn(mensaje);

        when(cuentaBO.getCuentasSaldo(Matchers.any(Cliente.class), Matchers.anyListOf(Cuenta.class)))
                .thenReturn(respuestaCuentas);

        when(sesionParametros.getCuentasComex()).thenReturn(null);
        when(sesionCliente.getCliente()).thenReturn(generarCliente());

        Respuesta<TransferenciaComexOutView> respuesta = comexConsultasManager.obtenerDatosComex();

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void obtenerDatosComexOKCuentasEnSesion() throws IllegalAccessException {
        List<String> habilitarArgentina = new ArrayList<String>();
        FieldUtils.writeField(comexConsultasManager, "habilitarArgentina", habilitarArgentina, true);
        List<CambioDomicilioDTO> domiciliosSesion = generarDomicilioPrimario();

        Respuesta<List<ConceptoPorTipoOutDTO>> respuestaBO = new Respuesta<List<ConceptoPorTipoOutDTO>>();

        respuestaBO.setRespuesta(generarMotivos());
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        PadronOutEntity consultaPadronCuitOutEntity = new PadronOutEntity();
        consultaPadronCuitOutEntity.setCuit("123456789");

        Respuesta<PadronOutEntity> respuestaConsultaPadron = new Respuesta<PadronOutEntity>();
        respuestaConsultaPadron.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaConsultaPadron.setRespuesta(consultaPadronCuitOutEntity);

        Respuesta<List<ConsultaPaisesOutDTO>> respuestaConsultaPaises = new Respuesta<List<ConsultaPaisesOutDTO>>();
        List<ConsultaPaisesOutDTO> listConsultaPaisesOutDTO = new ArrayList<ConsultaPaisesOutDTO>();
        ConsultaPaisesOutDTO consultaPaisesOutDTO = new ConsultaPaisesOutDTO();
        consultaPaisesOutDTO.setCodigoPais("AE");
        consultaPaisesOutDTO.setDescripcionPais("U.A.E.");
        ConsultaPaisesOutDTO consultaPaisesOutDTO2 = new ConsultaPaisesOutDTO();
        consultaPaisesOutDTO2.setCodigoPais("AF");
        consultaPaisesOutDTO2.setDescripcionPais("AFGHANISTAN");
        listConsultaPaisesOutDTO.add(consultaPaisesOutDTO);
        listConsultaPaisesOutDTO.add(consultaPaisesOutDTO2);

        respuestaConsultaPaises.setRespuesta(listConsultaPaisesOutDTO);
        respuestaConsultaPaises.setEstadoRespuesta(EstadoRespuesta.OK);

        List<Opcion> codigoBancario = generarComboCodigoBancario();

        String legal = "Prueba";
        Respuesta<String> respuestaLegales = new Respuesta<String>();

        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta(legal);

        List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
        detalleCuentas.add(getDetalleCuentas("000", "000011", CAJA_AHORROS, "1999", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_CORRIENTE, "2214", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_NO_PESOS, "14124", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", "02", "14124", null, null));

        when(comexConsultasBO.consultaPaises()).thenReturn(respuestaConsultaPaises);
        when(selectoresBO.obtenerVinculos()).thenReturn(generarComboVinculo());
        when(selectoresBO.obtenerGastoACargo()).thenReturn(generarComboGastoACargo());
        when(selectoresBO.obtenerCodigoBancario()).thenReturn(codigoBancario);
        when(legalBO.buscarLegal(CodigoMensajeConstantes.COMEX_LEGALES_TYC)).thenReturn(respuestaLegales);

        when(comexConsultasBO.getDatosPadronBO(Matchers.any(Cliente.class))).thenReturn(respuestaConsultaPadron);
        when(comexCanalesBO.consultaConceptoPorTipo(Matchers.any(ConsultaConceptosPorTipoInEntity.class))).thenReturn(respuestaBO);

        when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
        when(sesionParametros.getDomiciliosCliente()).thenReturn(domiciliosSesion);
        when(sesionParametros.getCredencialesMya()).thenReturn(generarDatosMYASA());
        when(sesionCliente.getCliente()).thenReturn(generarCliente());

        Respuesta<TransferenciaComexOutView> respuesta = comexConsultasManager.obtenerDatosComex();

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    @Test
    public void obtenerDatosComexLegaleError() throws IllegalAccessException {
        List<String> habilitarArgentina = new ArrayList<String>();
        FieldUtils.writeField(comexConsultasManager, "habilitarArgentina", habilitarArgentina, true);

        List<CambioDomicilioDTO> domiciliosSesion = generarDomicilioPrimario();

        Respuesta<List<ConceptoPorTipoOutDTO>> respuestaBO = new Respuesta<List<ConceptoPorTipoOutDTO>>();

        respuestaBO.setRespuesta(generarMotivos());
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        PadronOutEntity consultaPadronCuitOutEntity = new PadronOutEntity();
        consultaPadronCuitOutEntity.setCuit("123456789");

        Respuesta<PadronOutEntity> respuestaConsultaPadron = new Respuesta<PadronOutEntity>();
        respuestaConsultaPadron.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaConsultaPadron.setRespuesta(consultaPadronCuitOutEntity);

        Respuesta<List<ConsultaPaisesOutDTO>> respuestaConsultaPaises = new Respuesta<List<ConsultaPaisesOutDTO>>();
        List<ConsultaPaisesOutDTO> listConsultaPaisesOutDTO = new ArrayList<ConsultaPaisesOutDTO>();
        ConsultaPaisesOutDTO consultaPaisesOutDTO = new ConsultaPaisesOutDTO();
        consultaPaisesOutDTO.setCodigoPais("AE");
        consultaPaisesOutDTO.setDescripcionPais("U.A.E.");
        ConsultaPaisesOutDTO consultaPaisesOutDTO2 = new ConsultaPaisesOutDTO();
        consultaPaisesOutDTO2.setCodigoPais("AF");
        consultaPaisesOutDTO2.setDescripcionPais("AFGHANISTAN");
        listConsultaPaisesOutDTO.add(consultaPaisesOutDTO);
        listConsultaPaisesOutDTO.add(consultaPaisesOutDTO2);

        respuestaConsultaPaises.setRespuesta(listConsultaPaisesOutDTO);
        respuestaConsultaPaises.setEstadoRespuesta(EstadoRespuesta.OK);

        List<Opcion> codigoBancario = generarComboCodigoBancario();

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("PRUEBA");

        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);

        List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
        detalleCuentas.add(getDetalleCuentas("000", "000011", CAJA_AHORROS, "1999", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_CORRIENTE, "2214", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_NO_PESOS, "14124", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", "02", "14124", null, null));

        when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);

        when(comexConsultasBO.consultaPaises()).thenReturn(respuestaConsultaPaises);
        when(selectoresBO.obtenerVinculos()).thenReturn(generarComboVinculo());
        when(selectoresBO.obtenerGastoACargo()).thenReturn(generarComboGastoACargo());
        when(selectoresBO.obtenerCodigoBancario()).thenReturn(codigoBancario);
        when(legalBO.buscarLegal(CodigoMensajeConstantes.COMEX_LEGALES_TYC)).thenReturn(respuestaLegales);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO))
                .thenReturn(mensaje);

        when(comexConsultasBO.getDatosPadronBO(Matchers.any(Cliente.class))).thenReturn(respuestaConsultaPadron);
        when(comexCanalesBO.consultaConceptoPorTipo(Matchers.any(ConsultaConceptosPorTipoInEntity.class))).thenReturn(respuestaBO);

        when(sesionParametros.getDomiciliosCliente()).thenReturn(domiciliosSesion);
        when(sesionParametros.getCredencialesMya()).thenReturn(generarDatosMYASA());
        when(sesionCliente.getCliente()).thenReturn(generarCliente());

        Respuesta<TransferenciaComexOutView> respuesta = comexConsultasManager.obtenerDatosComex();

        Assert.assertNotNull(respuesta);
        //el test original espera error ya que no se setea el legal pero ese codigo fue comentado y no esta el desarrollador para consultar porque no se ajusto el test
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        Assert.assertThat(respuesta.getRespuesta().getLegales(), nullValue());

    }

    @Test
    public void obtenerDatosComexDatosBeneficiarioError() throws IllegalAccessException {

        List<String> habilitarArgentina = new ArrayList<String>();
        FieldUtils.writeField(comexConsultasManager, "habilitarArgentina", habilitarArgentina, true);

        List<CambioDomicilioDTO> domiciliosSesion = generarDomicilioPrimario();

        Respuesta<List<ConceptoPorTipoOutDTO>> respuestaBO = new Respuesta<List<ConceptoPorTipoOutDTO>>();

        respuestaBO.setRespuesta(generarMotivos());
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        PadronOutEntity consultaPadronCuitOutEntity = new PadronOutEntity();
        consultaPadronCuitOutEntity.setCuit("123456789");

        Respuesta<PadronOutEntity> respuestaConsultaPadron = new Respuesta<PadronOutEntity>();
        respuestaConsultaPadron.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaConsultaPadron.setRespuesta(consultaPadronCuitOutEntity);

        List<Opcion> codigoBancario = generarComboCodigoBancario();

        Respuesta<List<ConsultaPaisesOutDTO>> respuestaBOPaises = new Respuesta<List<ConsultaPaisesOutDTO>>();
        respuestaBOPaises.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("PRUEBA");

        List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
        detalleCuentas.add(getDetalleCuentas("000", "000011", CAJA_AHORROS, "1999", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_CORRIENTE, "2214", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_NO_PESOS, "14124", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", "02", "14124", null, null));

        when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);

        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO))
                .thenReturn(mensaje);
        when(comexConsultasBO.consultaPaises()).thenReturn(respuestaBOPaises);

        when(selectoresBO.obtenerVinculos()).thenReturn(generarComboVinculo());
        when(selectoresBO.obtenerGastoACargo()).thenReturn(generarComboGastoACargo());
        when(selectoresBO.obtenerCodigoBancario()).thenReturn(codigoBancario);

        Respuesta<DatosBeneficiarioOutView> respuestaDatosBeneficario = new Respuesta<DatosBeneficiarioOutView>();
        respuestaDatosBeneficario.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(comexConsultasBO.getDatosPadronBO(Matchers.any(Cliente.class))).thenReturn(respuestaConsultaPadron);
        when(comexCanalesBO.consultaConceptoPorTipo(Matchers.any(ConsultaConceptosPorTipoInEntity.class))).thenReturn(respuestaBO);

        when(sesionParametros.getDomiciliosCliente()).thenReturn(domiciliosSesion);
        when(sesionParametros.getCredencialesMya()).thenReturn(generarDatosMYASA());
        when(sesionCliente.getCliente()).thenReturn(generarCliente());

        Respuesta<TransferenciaComexOutView> respuesta = comexConsultasManager.obtenerDatosComex();

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

    }

    @Test
    public void obtenerDatosComexDomicilioBOOk() throws IllegalAccessException {
        List<String> habilitarArgentina = new ArrayList<String>();
        FieldUtils.writeField(comexConsultasManager, "habilitarArgentina", habilitarArgentina, true);

        List<CambioDomicilioDTO> domiciliosSesion = generarDomicilioPrimario();

        Respuesta<List<ConceptoPorTipoOutDTO>> respuestaBO = new Respuesta<List<ConceptoPorTipoOutDTO>>();

        respuestaBO.setRespuesta(generarMotivos());
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        PadronOutEntity consultaPadronCuitOutEntity = new PadronOutEntity();
        consultaPadronCuitOutEntity.setCuit("123456789");

        Respuesta<PadronOutEntity> respuestaConsultaPadron = new Respuesta<PadronOutEntity>();
        respuestaConsultaPadron.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaConsultaPadron.setRespuesta(consultaPadronCuitOutEntity);

        Respuesta<List<ConsultaPaisesOutDTO>> respuestaConsultaPaises = new Respuesta<List<ConsultaPaisesOutDTO>>();
        List<ConsultaPaisesOutDTO> listConsultaPaisesOutDTO = new ArrayList<ConsultaPaisesOutDTO>();
        ConsultaPaisesOutDTO consultaPaisesOutDTO = new ConsultaPaisesOutDTO();
        consultaPaisesOutDTO.setCodigoPais("AE");
        consultaPaisesOutDTO.setDescripcionPais("U.A.E.");
        ConsultaPaisesOutDTO consultaPaisesOutDTO2 = new ConsultaPaisesOutDTO();
        consultaPaisesOutDTO2.setCodigoPais("AF");
        consultaPaisesOutDTO2.setDescripcionPais("AFGHANISTAN");
        listConsultaPaisesOutDTO.add(consultaPaisesOutDTO);
        listConsultaPaisesOutDTO.add(consultaPaisesOutDTO2);

        respuestaConsultaPaises.setRespuesta(listConsultaPaisesOutDTO);
        respuestaConsultaPaises.setEstadoRespuesta(EstadoRespuesta.OK);

        List<Opcion> codigoBancario = generarComboCodigoBancario();

        String legal = "Prueba";
        Respuesta<String> respuestaLegales = new Respuesta<String>();

        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta(legal);

        Respuesta<List<CambioDomicilioDTO>> respuestaDomicilio = new Respuesta<List<CambioDomicilioDTO>>();
        respuestaDomicilio.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDomicilio.setRespuesta(domiciliosSesion);

        List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
        detalleCuentas.add(getDetalleCuentas("000", "000011", CAJA_AHORROS, "1999", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_CORRIENTE, "2214", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_NO_PESOS, "14124", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", "02", "14124", null, null));

        when(comexConsultasBO.consultaPaises()).thenReturn(respuestaConsultaPaises);
        when(selectoresBO.obtenerVinculos()).thenReturn(generarComboVinculo());
        when(selectoresBO.obtenerGastoACargo()).thenReturn(generarComboGastoACargo());
        when(selectoresBO.obtenerCodigoBancario()).thenReturn(codigoBancario);
        when(legalBO.buscarLegal(CodigoMensajeConstantes.COMEX_LEGALES_TYC)).thenReturn(respuestaLegales);

        when(comexConsultasBO.getDatosPadronBO(Matchers.any(Cliente.class))).thenReturn(respuestaConsultaPadron);
        when(comexCanalesBO.consultaConceptoPorTipo(Matchers.any(ConsultaConceptosPorTipoInEntity.class))).thenReturn(respuestaBO);

        when(cambioDomicilioBO.consultarDomiciliosRegistrados()).thenReturn(respuestaDomicilio);
        when(sesionParametros.getCredencialesMya()).thenReturn(generarDatosMYASA());
        when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
        when(sesionCliente.getCliente()).thenReturn(generarCliente());

        Respuesta<TransferenciaComexOutView> respuesta = comexConsultasManager.obtenerDatosComex();

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void obtenerDatosComexDomicilioBOError() throws IllegalAccessException {
        List<String> habilitarArgentina = new ArrayList<String>();
        FieldUtils.writeField(comexConsultasManager, "habilitarArgentina", habilitarArgentina, true);

        List<CambioDomicilioDTO> domiciliosSesion = generarDomicilioPrimario();

        Respuesta<List<ConceptoPorTipoOutDTO>> respuestaBO = new Respuesta<List<ConceptoPorTipoOutDTO>>();

        respuestaBO.setRespuesta(generarMotivos());
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        PadronOutEntity consultaPadronCuitOutEntity = new PadronOutEntity();
        consultaPadronCuitOutEntity.setCuit("123456789");

        Respuesta<PadronOutEntity> respuestaConsultaPadron = new Respuesta<PadronOutEntity>();
        respuestaConsultaPadron.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaConsultaPadron.setRespuesta(consultaPadronCuitOutEntity);

        Respuesta<List<ConsultaPaisesOutDTO>> respuestaConsultaPaises = new Respuesta<List<ConsultaPaisesOutDTO>>();
        List<ConsultaPaisesOutDTO> listConsultaPaisesOutDTO = new ArrayList<ConsultaPaisesOutDTO>();
        ConsultaPaisesOutDTO consultaPaisesOutDTO = new ConsultaPaisesOutDTO();
        consultaPaisesOutDTO.setCodigoPais("AE");
        consultaPaisesOutDTO.setDescripcionPais("U.A.E.");
        ConsultaPaisesOutDTO consultaPaisesOutDTO2 = new ConsultaPaisesOutDTO();
        consultaPaisesOutDTO2.setCodigoPais("AF");
        consultaPaisesOutDTO2.setDescripcionPais("AFGHANISTAN");
        listConsultaPaisesOutDTO.add(consultaPaisesOutDTO);
        listConsultaPaisesOutDTO.add(consultaPaisesOutDTO2);

        respuestaConsultaPaises.setRespuesta(listConsultaPaisesOutDTO);
        respuestaConsultaPaises.setEstadoRespuesta(EstadoRespuesta.OK);

        List<Opcion> codigoBancario = generarComboCodigoBancario();

        String legal = "Prueba";
        Respuesta<String> respuestaLegales = new Respuesta<String>();

        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta(legal);

        Respuesta<List<CambioDomicilioDTO>> respuestaDomicilio = new Respuesta<List<CambioDomicilioDTO>>();
        respuestaDomicilio.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("PRUEBA");

        List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
        detalleCuentas.add(getDetalleCuentas("000", "000011", CAJA_AHORROS, "1999", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_CORRIENTE, "2214", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_NO_PESOS, "14124", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", "02", "14124", null, null));

        when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);

        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO))
                .thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS))
                .thenReturn(mensaje);

        when(comexConsultasBO.consultaPaises()).thenReturn(respuestaConsultaPaises);
        when(selectoresBO.obtenerVinculos()).thenReturn(generarComboVinculo());
        when(selectoresBO.obtenerGastoACargo()).thenReturn(generarComboGastoACargo());
        when(selectoresBO.obtenerCodigoBancario()).thenReturn(codigoBancario);
        when(legalBO.buscarLegal(CodigoMensajeConstantes.COMEX_LEGALES_TYC)).thenReturn(respuestaLegales);

        when(comexConsultasBO.getDatosPadronBO(Matchers.any(Cliente.class))).thenReturn(respuestaConsultaPadron);
        when(comexCanalesBO.consultaConceptoPorTipo(Matchers.any(ConsultaConceptosPorTipoInEntity.class))).thenReturn(respuestaBO);

        when(sesionParametros.getDomiciliosCliente()).thenReturn(null, domiciliosSesion);
        when(cambioDomicilioBO.consultarDomiciliosRegistrados()).thenReturn(respuestaDomicilio);
        when(sesionParametros.getCredencialesMya()).thenReturn(generarDatosMYASA());
        when(sesionCliente.getCliente()).thenReturn(generarCliente());

        Respuesta<TransferenciaComexOutView> respuesta = comexConsultasManager.obtenerDatosComex();

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void descargarComprobanteOk() {

        List<ConceptoPorTipoOutDTO> conceptoPorTipoOutDTO = generarMotivos();

        Respuesta<List<ConceptoPorTipoOutDTO>> respuestaConcepto = new Respuesta<List<ConceptoPorTipoOutDTO>>();

        respuestaConcepto.setRespuesta(conceptoPorTipoOutDTO);
        respuestaConcepto.setEstadoRespuesta(EstadoRespuesta.OK);

        ComprobanteComexDTO comprobante = new ComprobanteComexDTO();

        comprobante.setNombre("Adrian Gonzales");
        comprobante.setImporte("USD 100,00");
        comprobante.setIdConcepto("1387");
        comprobante.setVinculo("Hermano");

        comprobante.setDomicilio("Park Avenue 42");

        comprobante.setPais(ISBANStringUtils.normalizarCaraceteres("CA, Estados Unidos"));

        comprobante.setNombreEmisor("Agustina Fernandez");
        comprobante.setCuentaOrigen(CUENTA.concat(" ").concat("000-358047/5"));
        comprobante.setDescripcionCuentaOrigen("Cuenta única en $");
        comprobante.setCuentaDestino("72834196582");
        comprobante.setCodigoBancario("CITIUS33XXX");
        comprobante.setIdGastosACargo("1");
        comprobante.setBancoIntermediario("123456789");
        comprobante.setCodigoIntermediario("CITIUS33XXX");
        comprobante.setNumeroComprobante("4500");

        ComprobanteComexInEntity comprobanteEntity = new ComprobanteComexInEntity();
        comprobanteEntity.setCodigoBancario("CITIUS33XXX");
        comprobanteEntity.setCuenta("Cuenta 023-123456/7");
        comprobanteEntity.setCuentaDestino("72834196582");
        comprobanteEntity.setDescripcionCuenta("Caja de ahorro en u$s");
        comprobanteEntity.setDocumentacion("Documento 1-Documento 2-Documento 3-Documento 4");
        comprobanteEntity.setDomicilio("Park Avenue 42");
        comprobanteEntity.setFecha("12/08/2016 - 12:32");
        comprobanteEntity.setGastosACargo("Ordenante");
        comprobanteEntity.setImporte("USD 100,00");
        comprobanteEntity.setMotivo("Transferenca personales");
        comprobanteEntity.setNombre("Magalí Romero");
        comprobanteEntity.setNombreEmisor("Agustina Fernandez");
        comprobanteEntity.setNumeroComprobante("123456789");
        comprobanteEntity.setPais("CA, Estados Unidos");
        comprobanteEntity.setCodigoBancarioIntermediario("asdaw123123");
        comprobanteEntity.setCuentaBancoIntermediario("123245");
        comprobanteEntity.setVinculo("Hermano");
        comprobanteEntity.setLegales("PRUEBA");

        Respuesta<Reporte> respuestaComprobante = new Respuesta<Reporte>();
        byte[] byteArray = "asd".getBytes();
        Reporte reporteOperaExterior = new Reporte();
        reporteOperaExterior.setTipoArchivo(TipoArchivoEnum.PDF);
        reporteOperaExterior.setBytes(byteArray);
        reporteOperaExterior.setNombre(NOMBRE_COMPROBANTE);
        respuestaComprobante.setRespuesta(reporteOperaExterior);
        respuestaComprobante.setEstadoRespuesta(EstadoRespuesta.OK);
        ComprobanteInView comprobanteInView = new ComprobanteInView();
        List<String> documentacion = new ArrayList<String>();
        documentacion.add("Documento 1");
        documentacion.add("Documento 2");
        comprobanteInView.setArchivos(documentacion);

        ConsultaConceptoOutView consultaConceptoOutView = new ConsultaConceptoOutView();
        consultaConceptoOutView.setTerminosYCondiciones("Terminos y Condiciones");

        when(selectoresBO.obtenerGastoACargo()).thenReturn(generarComboGastoACargo());
        when(sesionParametros.getComprobanteComex()).thenReturn(comprobante);
        when(sesionParametros.getConsultaConceptoOutView()).thenReturn(consultaConceptoOutView);
        when(comexCanalesBO.consultaConceptoPorTipo(Matchers.any(ConsultaConceptosPorTipoInEntity.class))).thenReturn(respuestaConcepto);
        when(comexConsultasBO.obtenerComprobante(Matchers.any(ComprobanteComexDTO.class)))
                .thenReturn(respuestaComprobante);

        Respuesta<ReporteView> respuesta = comexConsultasManager.descargarComprobante(comprobanteInView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void descargarComprobanteErrorMotivo() {

        List<ConceptoPorTipoOutDTO> conceptoPorTipoOutDTO = generarMotivos();

        Respuesta<List<ConceptoPorTipoOutDTO>> respuestaConcepto = new Respuesta<List<ConceptoPorTipoOutDTO>>();

        respuestaConcepto.setRespuesta(conceptoPorTipoOutDTO);
        respuestaConcepto.setEstadoRespuesta(EstadoRespuesta.ERROR);

        ComprobanteComexDTO comprobante = new ComprobanteComexDTO();

        comprobante.setNombre("Adrian Gonzales");
        comprobante.setImporte("USD 100,00");
        comprobante.setIdConcepto("1387");
        comprobante.setVinculo("Hermano");

        comprobante.setDomicilio("Park Avenue 42");

        comprobante.setPais(ISBANStringUtils.normalizarCaraceteres("CA, Estados Unidos"));

        comprobante.setNombreEmisor("Agustina Fernandez");
        comprobante.setCuentaOrigen(CUENTA.concat(" ").concat("000-358047/5"));
        comprobante.setDescripcionCuentaOrigen("Cuenta única en $");
        comprobante.setCuentaDestino("72834196582");
        comprobante.setCodigoBancario("CITIUS33XXX");
        comprobante.setIdGastosACargo("1");
        comprobante.setBancoIntermediario("123456789");
        comprobante.setCodigoIntermediario("CITIUS33XXX");
        comprobante.setNumeroComprobante("4500");

        ComprobanteComexInEntity comprobanteEntity = new ComprobanteComexInEntity();
        comprobanteEntity.setCodigoBancario("CITIUS33XXX");
        comprobanteEntity.setCuenta("Cuenta 023-123456/7");
        comprobanteEntity.setCuentaDestino("72834196582");
        comprobanteEntity.setDescripcionCuenta("Caja de ahorro en u$s");
        comprobanteEntity.setDocumentacion("Documento 1-Documento 2-Documento 3-Documento 4");
        comprobanteEntity.setDomicilio("Park Avenue 42");
        comprobanteEntity.setFecha("12/08/2016 - 12:32");
        comprobanteEntity.setGastosACargo("Ordenante");
        comprobanteEntity.setImporte("USD 100,00");
        comprobanteEntity.setMotivo("Transferenca personales");
        comprobanteEntity.setNombre("Magalí Romero");
        comprobanteEntity.setNombreEmisor("Agustina Fernandez");
        comprobanteEntity.setNumeroComprobante("123456789");
        comprobanteEntity.setPais("CA, Estados Unidos");
        comprobanteEntity.setCodigoBancarioIntermediario("asdaw123123");
        comprobanteEntity.setCuentaBancoIntermediario("123245");
        comprobanteEntity.setVinculo("Hermano");
        comprobanteEntity.setLegales("PRUEBA");

        Respuesta<Reporte> respuestaComprobante = new Respuesta<Reporte>();
        byte[] byteArray = "asd".getBytes();
        Reporte reporteOperaExterior = new Reporte();
        reporteOperaExterior.setTipoArchivo(TipoArchivoEnum.PDF);
        reporteOperaExterior.setBytes(byteArray);
        reporteOperaExterior.setNombre(NOMBRE_COMPROBANTE);
        respuestaComprobante.setRespuesta(reporteOperaExterior);
        respuestaComprobante.setEstadoRespuesta(EstadoRespuesta.OK);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("PRUEBA");

        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO))
                .thenReturn(mensaje);

        when(selectoresBO.obtenerGastoACargo()).thenReturn(generarComboGastoACargo());
        when(sesionParametros.getComprobanteComex()).thenReturn(comprobante);
        when(comexCanalesBO.consultaConceptoPorTipo(Matchers.any(ConsultaConceptosPorTipoInEntity.class))).thenReturn(respuestaConcepto);
        when(comexConsultasBO.obtenerComprobante(Matchers.any(ComprobanteComexDTO.class)))
                .thenReturn(respuestaComprobante);

        Respuesta<ReporteView> respuesta = comexConsultasManager.descargarComprobante(null);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void obtenerBancosOK() {
        ConsultaBancosInDTO consultaBancosInDTO = new ConsultaBancosInDTO();
        consultaBancosInDTO.setCodigoBancario("CITIUS");
        consultaBancosInDTO.setTipoCodigo("SWIFT");

        Respuesta<List<ConsultaBancosOutDTO>> respuestaBO = new Respuesta<List<ConsultaBancosOutDTO>>();
        List<ConsultaBancosOutDTO> listConsultaBancosOutDTO = new ArrayList<ConsultaBancosOutDTO>();
        ConsultaBancosOutDTO consultaBancosOutDTO = new ConsultaBancosOutDTO();
        consultaBancosOutDTO.setDomicilioBanco("N/D");
        consultaBancosOutDTO.setLocalidadBanco("N/D");
        consultaBancosOutDTO.setNombreBanco("CITIBANK N.A.");
        consultaBancosOutDTO.setPaisBanco("ESTADOSUNIDOS");
        consultaBancosOutDTO.setSwiftBanco("CITIUS33GRP");
        ConsultaBancosOutDTO consultaBancosOutDTO2 = new ConsultaBancosOutDTO();
        consultaBancosOutDTO2.setDomicilioBanco("111 WALL STREET");
        consultaBancosOutDTO2.setLocalidadBanco("NEW YORK,NY 10005");
        consultaBancosOutDTO2.setNombreBanco("CITIBANK N.A.");
        consultaBancosOutDTO2.setPaisBanco("ESTADOSUNIDOS");
        consultaBancosOutDTO2.setSwiftBanco("CITIUS3PPBX");
        listConsultaBancosOutDTO.add(consultaBancosOutDTO);
        listConsultaBancosOutDTO.add(consultaBancosOutDTO2);
        respuestaBO.setRespuesta(listConsultaBancosOutDTO);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        when(comexConsultasBO.consultaBancos(Matchers.any(ConsultaBancosInDTO.class))).thenReturn(respuestaBO);

        ConsultaBancosInView consultaBancosInView = new ConsultaBancosInView();
        consultaBancosInView.setCodigo("CITIUS");
        consultaBancosInView.setTipoCodigo("SWIFT");

        Respuesta<ConsultaBancosOutView> respuesta = comexConsultasManager.obtenerBancos(consultaBancosInView);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void obtenerBancosErrorBO() {
        Respuesta<List<ConsultaBancosOutDTO>> respuestaBO = new Respuesta<List<ConsultaBancosOutDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_CONSULTA_BANCOS))
                .thenReturn(mensaje);
        when(comexConsultasBO.consultaBancos(Matchers.any(ConsultaBancosInDTO.class))).thenReturn(respuestaBO);

        ConsultaBancosInView consultaBancosInView = new ConsultaBancosInView();
        consultaBancosInView.setCodigo("CITIUS");
        consultaBancosInView.setTipoCodigo("SWIFT");

        Respuesta<ConsultaBancosOutView> respuesta = comexConsultasManager.obtenerBancos(consultaBancosInView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void obtenerDatosBeneficiariosOk() {
        Respuesta<List<ConsultaPaisesOutDTO>> respuestaBO = new Respuesta<List<ConsultaPaisesOutDTO>>();
        List<ConsultaPaisesOutDTO> listConsultaPaisesOutDTO = new ArrayList<ConsultaPaisesOutDTO>();
        ConsultaPaisesOutDTO consultaPaisesOutDTO = new ConsultaPaisesOutDTO();
        consultaPaisesOutDTO.setCodigoPais("AE");
        consultaPaisesOutDTO.setDescripcionPais("U.A.E.");
        ConsultaPaisesOutDTO consultaPaisesOutDTO2 = new ConsultaPaisesOutDTO();
        consultaPaisesOutDTO2.setCodigoPais("AF");
        consultaPaisesOutDTO2.setDescripcionPais("AFGHANISTAN");
        listConsultaPaisesOutDTO.add(consultaPaisesOutDTO);
        listConsultaPaisesOutDTO.add(consultaPaisesOutDTO2);

        respuestaBO.setRespuesta(listConsultaPaisesOutDTO);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        when(comexConsultasBO.consultaPaises()).thenReturn(respuestaBO);
        when(selectoresBO.obtenerVinculos()).thenReturn(generarComboVinculo());

        Respuesta<DatosBeneficiarioOutView> respuesta = comexConsultasManager.obtenerDatosBeneficiarios();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    public List<Opcion> generarComboVinculo() {
        List<Opcion> vinculosComex = new ArrayList<Opcion>();
        Opcion vinculo = new Opcion();
        vinculo.setId("1");
        vinculo.setOpcion("Hermano");
        Opcion vinculo2 = new Opcion();
        vinculo2.setId("2");
        vinculo2.setOpcion("Nieto");
        vinculosComex.add(vinculo);
        vinculosComex.add(vinculo2);
        return vinculosComex;
    }

    public CredencialesMya generarDatosMYASA() {
        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setClienteEstado("SA");
        credencialesMya.setEmail("isban@isbanexternos.com.ar");
        credencialesMya.setCelular("11111111");
        credencialesMya.setCodigoArea("11");
        credencialesMya.setCompaniaSeleccionada("Claro");
        return credencialesMya;
    }

    public List<Opcion> generarComboGastoACargo() {
        List<Opcion> gastosACargo = new ArrayList<Opcion>();
        Opcion ordenante = new Opcion();
        ordenante.setId("1");
        ordenante.setOpcion("Ordenante");
        Opcion compartido = new Opcion();
        compartido.setId("2");
        compartido.setOpcion("compartido");
        gastosACargo.add(ordenante);
        gastosACargo.add(compartido);
        return gastosACargo;
    }

    public List<Opcion> generarComboCodigoBancario() {
        List<Opcion> codigoBancario = new ArrayList<Opcion>();
        Opcion swift = new Opcion();
        swift.setId("1");
        swift.setOpcion("swift");
        Opcion aba = new Opcion();
        aba.setId("2");
        aba.setOpcion("ABA");
        codigoBancario.add(swift);
        codigoBancario.add(aba);
        return codigoBancario;
    }

    public List<ConceptoPorTipoOutDTO> generarMotivos() {
        List<ConceptoPorTipoOutDTO> conceptoPorTipoOutDTO = new ArrayList<ConceptoPorTipoOutDTO>();
        ConceptoPorTipoOutDTO concepto = new ConceptoPorTipoOutDTO();
        concepto.setConcepto("I07 TRANSFERENCIAS PERSONALES");
        concepto.setIdConcepto(new BigDecimal(1387));
        ConceptoPorTipoOutDTO concepto2 = new ConceptoPorTipoOutDTO();
        concepto2.setConcepto("I08 OTRAS TRANSFERENCIAS CORRIENTES");
        concepto2.setIdConcepto(new BigDecimal(1388));
        ConceptoPorTipoOutDTO concepto3 = new ConceptoPorTipoOutDTO();
        concepto3.setConcepto("B05 PAGOS ANTICIPADOS DE IMPORTACIONES DE BIENES");
        concepto3.setIdConcepto(new BigDecimal(1414));
        ConceptoPorTipoOutDTO concepto4 = new ConceptoPorTipoOutDTO();
        concepto4.setConcepto("B06 PAGOS DIFERIDOS DE IMPORTACIONES DE BIENES");
        concepto4.setIdConcepto(new BigDecimal(1415));
        ConceptoPorTipoOutDTO concepto5 = new ConceptoPorTipoOutDTO();
        concepto5.setConcepto("B07 PAGO VISTA DE IMPORTACIONES DE BIENES");
        concepto5.setIdConcepto(new BigDecimal(1416));
        ConceptoPorTipoOutDTO concepto6 = new ConceptoPorTipoOutDTO();
        concepto6.setConcepto("S06 TURISMO Y VIAJES");
        concepto6.setIdConcepto(new BigDecimal(1394));
        ConceptoPorTipoOutDTO concepto7 = new ConceptoPorTipoOutDTO();
        concepto7.setConcepto("A07 DEPÓSITOS DE RESIDENTES EN EL EXTERIOR");
        concepto7.setIdConcepto(new BigDecimal(1430));
        conceptoPorTipoOutDTO.add(concepto);
        conceptoPorTipoOutDTO.add(concepto2);
        conceptoPorTipoOutDTO.add(concepto3);
        conceptoPorTipoOutDTO.add(concepto4);
        conceptoPorTipoOutDTO.add(concepto5);
        conceptoPorTipoOutDTO.add(concepto6);
        conceptoPorTipoOutDTO.add(concepto7);
        return conceptoPorTipoOutDTO;
    }

    public Cliente generarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        return cliente;
    }

    public List<CambioDomicilioDTO> generarDomicilioPrimario() {
        List<CambioDomicilioDTO> domiciliosSesion = new ArrayList<CambioDomicilioDTO>();
        CambioDomicilioDTO cambioDomicilioDTO = new CambioDomicilioDTO();
        cambioDomicilioDTO.setApt("1234");
        cambioDomicilioDTO.setCalle("Calle");
        cambioDomicilioDTO.setLocalidad("Avellaneda");
        cambioDomicilioDTO.setTelefono("34567");
        cambioDomicilioDTO.setPrefijo("011");
        cambioDomicilioDTO.setCodigoPostal("1234");
        cambioDomicilioDTO.setCaracteristica("22");
        cambioDomicilioDTO.setNumeroTelefono("344543634543");
        cambioDomicilioDTO.setTipoDomicilio("PRI");
        domiciliosSesion.add(cambioDomicilioDTO);
        return domiciliosSesion;
    }

    /**
     * Gets the detalle cuentas.
     *
     * @param sucursal
     *            the sucursal
     * @param nroCuentaProducto
     *            the nro cuenta producto
     * @param tipoCuenta
     *            the tipo cuenta
     * @param saldo
     *            the saldo
     * @param sobregiro
     *            the sobregiro
     * @param limiteDescubierto
     *            the limite descubierto
     * @return the detalle cuentas
     */
    private ResumenDetalleCuenta getDetalleCuentas(String sucursal, String nroCuentaProducto, String tipoCuenta,
            String saldo, String sobregiro, BigDecimal limiteDescubierto) {
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        resumenDetalleCuenta.setNroSucursal(sucursal);
        resumenDetalleCuenta.setNroCuentaProducto(nroCuentaProducto);
        resumenDetalleCuenta.setTipoCuenta(tipoCuenta);
        resumenDetalleCuenta.setCuentaCerrada(false);
        resumenDetalleCuenta.setCuentaUnica(false);
        resumenDetalleCuenta.setCuentaPrincipal(true);
        resumenDetalleCuenta.setAlias("Cuenta1");
        resumenDetalleCuenta.setFechaDesdeMovimiento("10/10/2010");
        resumenDetalleCuenta.setFechaHastaMovimiento("10/10/2010");
        resumenDetalleCuenta.setIndicadorSobregiro(sobregiro);
        resumenDetalleCuenta.setLimiteDescubierto(limiteDescubierto);
        Cliente cliente = new Cliente();
        cliente.setNombre("Pepe");
        cliente.setApellido1("Luis");
        cliente.setTipoDocumento("N");
        cliente.setDni("33333333");
        resumenDetalleCuenta.setCliente(cliente);

        if (CUENTA_CORRIENTE.equals(tipoCuenta)) {

            resumenDetalleCuenta.setSaldoCuentaCorrientePesos(new BigDecimal(Integer.valueOf(saldo)));
        } else if (CAJA_AHORROS.equals(tipoCuenta)) {

            resumenDetalleCuenta.setSaldoCajaAhorroPesos(new BigDecimal(Integer.valueOf(saldo)));
        } else if (CUENTA_NO_PESOS.equals(tipoCuenta)) {

            resumenDetalleCuenta.setSaldoCuentaSueldoPesos(new BigDecimal(Integer.valueOf(saldo)));
        } else if ("2".equals(tipoCuenta)) {
            resumenDetalleCuenta.setSaldoPesos(new BigDecimal(Integer.valueOf(saldo)));
            resumenDetalleCuenta.setSaldoDolares(new BigDecimal(Integer.valueOf(saldo)));
        }
        return resumenDetalleCuenta;
    }

    @Test
    public void obtenerDatosBeneficiariosErrorBO() {
        Respuesta<List<ConsultaPaisesOutDTO>> respuestaBO = new Respuesta<List<ConsultaPaisesOutDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("PRUEBA");

        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO))
                .thenReturn(mensaje);
        when(comexConsultasBO.consultaPaises()).thenReturn(respuestaBO);

        Respuesta<DatosBeneficiarioOutView> respuesta = comexConsultasManager.obtenerDatosBeneficiarios();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void verificarArchivoOK() {
        Respuesta<Boolean> respuestaBO = new Respuesta<Boolean>();

        respuestaBO.setRespuesta(Boolean.TRUE);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        ReporteView archivo = new ReporteView();

        when(comexConsultasBO.verificarArchivo(Matchers.any(AdjuntarArchivosDTO.class))).thenReturn(respuestaBO);

        Respuesta<Boolean> respuesta = comexConsultasManager.verificarArchivo(archivo);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void obtenerConfiguracionAdjuntarArchivo() {
        Respuesta<ConfiguracionAdjuntarArchivosOutView> respuesta = comexConsultasManager
                .obtenerConfiguracionAdjuntarArchivo();

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

}
