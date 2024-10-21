package ar.com.santanderrio.obp.servicios.solicitudes.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasOut;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.AdministradorPermisos;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.solicitudes.bo.impl.SolicitudesBOImpl;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.SolicitudesDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.ConsultaTarjetaTitularDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class SolicitudesBOTest {

    @InjectMocks
    private SolicitudesBOImpl solicitudesBO;
    
    @Mock
    private Sign sign;
    
    
    @Mock
    private ModuloPermisoBO moduloPermisoBO;
    
    @Mock
    private AdministradorPermisos administradorPermisos;
    
    
    @Mock
    private ConsultaTarjetaTitularDAO consultaTarjetaTitularDAO;
    
    @Before
    public void init() {
        solicitudesBO.setAppEncoding("UTF-8");
    }
    
    
    @Test
    public void obtenerCuentasYPaquetes() throws IllegalAccessException {
        
        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class))).thenReturn(moduloPermiso);
        
        FieldUtils.writeField(solicitudesBO, "valorTrjCoord", "3",true);
        
        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerCuentasYPaquetes(cliente.getCuentas());
        
        //Expected
        Assert.assertNotNull(respuesta);
    }
    
    
    @Test
    public void obtenerTarjetas() throws IllegalAccessException {
        
        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class))).thenReturn(moduloPermiso);
        
        FieldUtils.writeField(solicitudesBO, "valorTrjCoord", "3",true);
        
        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());
        
        //Expected
        Assert.assertNotNull(respuesta);
    }
    
    
    @Test
    public void obtenerTarjetasNoTienePermisoDeMuestra() throws IllegalAccessException {
        
        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.OCULTAR);
        
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class))).thenReturn(moduloPermiso);
        
        FieldUtils.writeField(solicitudesBO, "valorTrjCoord", "3",true);
        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());
        
        //Expected
        Assert.assertNotNull(respuesta);
    }
    
    
    @Test
    public void obtenerTarjetasNoTarjetas() throws IllegalAccessException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        cliente.setCuentas(new ArrayList<Cuenta>());
        
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class))).thenReturn(moduloPermiso);
        
        FieldUtils.writeField(solicitudesBO, "valorTrjCoord", "3",true);
        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());
        
        //Expected
        Assert.assertNotNull(respuesta);
    }
    
    
    @Test
    public void obtenerTarjetasVisa() throws IllegalAccessException {
        
        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisa();
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class))).thenReturn(moduloPermiso);
        
        FieldUtils.writeField(solicitudesBO, "valorTrjCoord", "3",true);
        
        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());
        
        //Expected
        Assert.assertNotNull(respuesta);
    }
     
    
    public static TrackingTarjetasOut mockRespuestaTrackingTarjetasDAO() {
        String respuesta = "{\"codRetorno\":\"0\",\"piezas\":{\"pieza\":[{\"statusPieza\":\"0\",\"resStatusPieza\":{\"piezaError\":[{\"cod\":\"1\",\"desc\":\"Error al consultar transportista\",\"sistema\":\"Andreani\"}]},\"nroPieza\":\"9110325\",\"codProd\":\"ABAE\",\"tipoCuenta\":\"05\",\"medEnvi\":\"\",\"nyAp\":\"APELLIDO*NOMBRE NOMBRE2 9110325\",\"fecNac\":\"19850104\",\"tipoDocum\":\"N\",\"nroDocum\":\"33333333\",\"tipoPersona\":\"F\",\"bancaPieza\":\"\",\"sucUbic\":\"000\",\"conDelivery\":\"S\",\"codEstadoActual\":\"TR\",\"motivoEstadoActual\":\"90\",\"fecCodEstadoActual\":\"20170616\",\"codEstadoAnterior\":\"TT\",\"userId\":\"\",\"piezaAbierta\":\"\",\"nroPiezaOrigen\":\"0\",\"canal\":\"\",\"codCampania\":\"00\",\"codMotivoEmi\":\"4\",\"codPaq\":\"0\",\"tieneChequera\":\"N\",\"nroIdentProd\":\"00000000000001686631\",\"nroIdComponente\":\"ABAE4815500900168663\",\"canComp\":\"1\",\"areaVariable\":\"008800081215160PROB2ABAE QD1CDPL LLAMAR TECNO      004\",\"codSucAnt\":\"\",\"fechaCodEstAnt\":\"20170614\",\"codTransp\":\"4\",\"statusEvento\":\"0\"},{\"statusPieza\":\"0\",\"nroPieza\":\"3390699\",\"codProd\":\"APAQ\",\"tipoCuenta\":\"07\",\"medEnvi\":\"\",\"nyAp\":\"APELLIDO*NOMBRE NOMBRE2 3390699\",\"fecNac\":\"19850104\",\"tipoDocum\":\"N\",\"nroDocum\":\"33333333\",\"tipoPersona\":\"F\",\"bancaPieza\":\"\",\"sucUbic\":\"000\",\"conDelivery\":\"S\",\"codEstadoActual\":\"CL\",\"motivoEstadoActual\":\"0\",\"fecCodEstadoActual\":\"20160409\",\"codEstadoAnterior\":\"TR\",\"userId\":\"\",\"piezaAbierta\":\"\",\"nroPiezaOrigen\":\"0\",\"canal\":\"\",\"codCampania\":\"00\",\"codMotivoEmi\":\"2\",\"codPaq\":\"0\",\"tieneChequera\":\"N\",\"nroIdentProd\":\"00003500090000316369\",\"nroIdComponente\":\"AVIS4050710094548646\",\"canComp\":\"3\",\"areaVariable\":\"350900003163691APELLIDO  NOMBRE NOMBRE2             002\",\"codSucAnt\":\"\",\"fechaCodEstAnt\":\"20160329\",\"codTransp\":\"4\",\"statusEvento\":\"0\"},{\"statusPieza\":\"0\",\"nroPieza\":\"5745676\",\"codProd\":\"AVIS\",\"tipoCuenta\":\"07\",\"medEnvi\":\"\",\"nyAp\":\"APELLIDO*NOMBRE NOMBRE2 5745676\",\"fecNac\":\"19850104\",\"tipoDocum\":\"N\",\"nroDocum\":\"33333333\",\"tipoPersona\":\"F\",\"bancaPieza\":\"\",\"sucUbic\":\"000\",\"conDelivery\":\"S\",\"codEstadoActual\":\"CL\",\"motivoEstadoActual\":\"0\",\"fecCodEstadoActual\":\"20161007\",\"codEstadoAnterior\":\"TR\",\"userId\":\"\",\"piezaAbierta\":\"\",\"nroPiezaOrigen\":\"0\",\"canal\":\"\",\"codCampania\":\"OPNMKT00\",\"codMotivoEmi\":\"1\",\"codPaq\":\"0\",\"tieneChequera\":\"N\",\"nroIdentProd\":\"00000000000167081935\",\"nroIdComponente\":\"AVIS4050710102243388\",\"canComp\":\"1\",\"areaVariable\":\"008800047156460APELLIDO  NOMBRE NOMBRE2       OPNMKT001\",\"codSucAnt\":\"\",\"fechaCodEstAnt\":\"20161001\",\"codTransp\":\"4\",\"statusEvento\":\"0\"},{\"statusPieza\":\"1\",\"resStatusPieza\":{\"piezaError\":[{\"cod\":\"0000132\",\"desc\":\"No existen datos para la consulta realizada\",\"sistema\":\"SGM\"},{\"cod\":\"0000132\",\"desc\":\"SIN DATOS\",\"sistema\":\"SGM\"},{\"cod\":\"0000132\",\"desc\":\"NO EXISTEN DATOS PARA ESTA CONSULTADSGM630   \",\"sistema\":\"SGM\"}]},\"nroPieza\":\"0\"},{\"statusPieza\":\"1\",\"resStatusPieza\":{\"piezaError\":[{\"cod\":\"0000132\",\"desc\":\"No existen datos para la consulta realizada\",\"sistema\":\"SGM\"},{\"cod\":\"0000132\",\"desc\":\"SIN DATOS\",\"sistema\":\"SGM\"},{\"cod\":\"0000132\",\"desc\":\"NO EXISTEN DATOS PARA ESTA CONSULTADSGM630   \",\"sistema\":\"SGM\"}]},\"nroPieza\":\"0\"},{\"statusPieza\":\"0\",\"nroPieza\":\"3390699\",\"codProd\":\"APAQ\",\"tipoCuenta\":\"42\",\"medEnvi\":\"\",\"nyAp\":\"APELLIDO*NOMBRE NOMBRE2 3390699\",\"fecNac\":\"19850104\",\"tipoDocum\":\"N\",\"nroDocum\":\"33333333\",\"tipoPersona\":\"F\",\"bancaPieza\":\"\",\"sucUbic\":\"000\",\"conDelivery\":\"S\",\"codEstadoActual\":\"CL\",\"motivoEstadoActual\":\"0\",\"fecCodEstadoActual\":\"20160409\",\"codEstadoAnterior\":\"TR\",\"userId\":\"\",\"piezaAbierta\":\"\",\"nroPiezaOrigen\":\"0\",\"canal\":\"\",\"codCampania\":\"00\",\"codMotivoEmi\":\"2\",\"codPaq\":\"0\",\"tieneChequera\":\"N\",\"nroIdentProd\":\"00003500090000316369\",\"nroIdComponente\":\"AEXP3777920051111060\",\"canComp\":\"3\",\"areaVariable\":\"350900003163691                                    002\",\"codSucAnt\":\"\",\"fechaCodEstAnt\":\"20160329\",\"codTransp\":\"4\",\"statusEvento\":\"0\"},{\"statusPieza\":\"0\",\"nroPieza\":\"8316617\",\"codProd\":\"AEXP\",\"tipoCuenta\":\"42\",\"medEnvi\":\"\",\"nyAp\":\"APELLIDO*NOMBRE NOMBRE2 8316617\",\"fecNac\":\"19850104\",\"tipoDocum\":\"N\",\"nroDocum\":\"33333333\",\"tipoPersona\":\"F\",\"bancaPieza\":\"\",\"sucUbic\":\"000\",\"conDelivery\":\"S\",\"codEstadoActual\":\"CL\",\"motivoEstadoActual\":\"0\",\"fecCodEstadoActual\":\"20170511\",\"codEstadoAnterior\":\"TR\",\"userId\":\"\",\"piezaAbierta\":\"\",\"nroPiezaOrigen\":\"0\",\"canal\":\"\",\"codCampania\":\"OPNMKT00\",\"codMotivoEmi\":\"1\",\"codPaq\":\"0\",\"tieneChequera\":\"N\",\"nroIdentProd\":\"00000000000033196298\",\"nroIdComponente\":\"AEXP3777920063184110\",\"canComp\":\"1\",\"areaVariable\":\"008800073590220                              OPNMKT001\",\"codSucAnt\":\"\",\"fechaCodEstAnt\":\"20170503\",\"codTransp\":\"4\",\"statusEvento\":\"0\"},{\"statusPieza\":\"1\",\"resStatusPieza\":{\"piezaError\":[{\"cod\":\"0000132\",\"desc\":\"No existen datos para la consulta realizada\",\"sistema\":\"SGM\"},{\"cod\":\"0000132\",\"desc\":\"SIN DATOS\",\"sistema\":\"SGM\"},{\"cod\":\"0000132\",\"desc\":\"NO EXISTEN DATOS PARA ESTA CONSULTADSGM630   \",\"sistema\":\"SGM\"}]},\"nroPieza\":\"0\"}]}}";
        Gson gson = new Gson();
        return gson.fromJson(respuesta, TrackingTarjetasOut.class);
    }
    
}
