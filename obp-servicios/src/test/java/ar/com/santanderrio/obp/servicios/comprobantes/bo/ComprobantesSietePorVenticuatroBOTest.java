package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.scheduling.annotation.AsyncResult;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.ComprobanteResponse;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteScomp;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.RespuestaScomp;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ComprobantesSietePorVenticuatroBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.SietePorVenticuatroV1DAO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosSueldos;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DATOSENTRADA;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales.DatosMensAvisos;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.XMLResultado;

@RunWith(MockitoJUnitRunner.class)
public class ComprobantesSietePorVenticuatroBOTest {

    @InjectMocks
    private ComprobantesSietePorVenticuatroBO comprobantesBO = new ComprobantesSietePorVenticuatroBOImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private SietePorVenticuatroV1DAO comprobantesDAO;

    @Mock
    Future<XMLResponseEntity> thread;

    @Test
    public void obtenerComprobantesRioOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesRio(obtenerCliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesRioExceptionTest() throws DAOException {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenThrow(new DAOException());
        TransaccionDTO transaccion = new TransaccionDTO();
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesRio(obtenerCliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesRioAsyncOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Future<Respuesta<ComprobantesDTO>> rta = comprobantesBO.obtenerComprobantesRioAsync(obtenerCliente(),
                transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = comprobantesBO.obtenerComprobantesRioAsync(obtenerCliente(),
                transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOBOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesOtrosBancos(obtenerCliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOBExceptionTest() throws DAOException {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        TransaccionDTO transaccion = new TransaccionDTO();
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesOtrosBancos(obtenerCliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOBAsyncOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Future<Respuesta<ComprobantesDTO>> rta = comprobantesBO.obtenerComprobantesOtrosBancosAsync(obtenerCliente(),
                transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = comprobantesBO.obtenerComprobantesOtrosBancosAsync(obtenerCliente(),
                transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesACHOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesACH(obtenerCliente(), new TransaccionDTO());
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesACHExceptionTest() throws DAOException {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesACH(obtenerCliente(), new TransaccionDTO());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesACHAsyncOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Future<Respuesta<ComprobantesDTO>> rta = comprobantesBO.obtenerComprobantesACHAsync(obtenerCliente(),
                new TransaccionDTO());
        Future<Respuesta<ComprobantesDTO>> rta2 = comprobantesBO.obtenerComprobantesACHAsync(obtenerCliente(),
                new TransaccionDTO());
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesPagHabonOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesPagoHaberesYHonorarios(obtenerCliente(),
                new TransaccionDTO());
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesPAGHABONExceptionTest() throws DAOException {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesPagoHaberesYHonorarios(obtenerCliente(),
                new TransaccionDTO());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesPAGHABONAsyncOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Future<Respuesta<ComprobantesDTO>> rta = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosAsync(obtenerCliente(), new TransaccionDTO());
        Future<Respuesta<ComprobantesDTO>> rta2 = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosAsync(obtenerCliente(), new TransaccionDTO());
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesRioNoEfecOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesRioNoEfec(obtenerCliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesRioNoEfecExceptionTest() throws DAOException {
        TransaccionDTO transaccion = new TransaccionDTO();
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesRioNoEfec(obtenerCliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesRioNoEfecAsyncOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Future<Respuesta<ComprobantesDTO>> rta = comprobantesBO.obtenerComprobantesRioNoEfecAsync(obtenerCliente(),
                transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = comprobantesBO.obtenerComprobantesRioNoEfecAsync(obtenerCliente(),
                transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOBNoEfecOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesOtrosBancosNoEfec(obtenerCliente(),
                transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOBNoEfecExceptionTest() throws DAOException {
        TransaccionDTO transaccion = new TransaccionDTO();
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesOtrosBancosNoEfec(obtenerCliente(),
                transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOBNoEfecAsyncOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(getComprobanteResponseMock(obtenerXMLResponseBase()));
        Future<Respuesta<ComprobantesDTO>> rta = comprobantesBO
                .obtenerComprobantesOtrosBancosNoEfecAsync(obtenerCliente(), transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = comprobantesBO
                .obtenerComprobantesOtrosBancosNoEfecAsync(obtenerCliente(), transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesACHNoEfecOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(obtenerFuture(obtenerXMLResponseBase()));
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesACHNoEfec(obtenerCliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesACHNoEfecExceptionTest() throws DAOException {
        TransaccionDTO transaccion = new TransaccionDTO();
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class))).thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = comprobantesBO.obtenerComprobantesACHNoEfec(obtenerCliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesACHNoEfecAsyncOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(obtenerFuture(obtenerXMLResponseBase()));
        Future<Respuesta<ComprobantesDTO>> rta = comprobantesBO.obtenerComprobantesACHNoEfecAsync(obtenerCliente(),
                transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = comprobantesBO.obtenerComprobantesACHNoEfecAsync(obtenerCliente(),
                transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesPagHabonNoEfecOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(obtenerFuture(obtenerXMLResponseBase()));
        Respuesta<ComprobantesDTO> res = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosNoEfec(obtenerCliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesPAGHABONNoEfecExceptionTest() throws DAOException {
        TransaccionDTO transaccion = new TransaccionDTO();
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class))).thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosNoEfec(obtenerCliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesPAGHABONAsyncNoEfecOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class)))
                .thenReturn(obtenerFuture(obtenerXMLResponseBase()));
        Future<Respuesta<ComprobantesDTO>> rta = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosNoEfecAsync(obtenerCliente(), transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosNoEfecAsync(obtenerCliente(), transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    private XMLResponseEntity obtenerXMLResponseBase() {
        XMLResponseEntity xmlResponseEntity = new XMLResponseEntity();
        DATOSRESULTADO value = new DATOSRESULTADO();
        value.setCodRet("0");
        value.setRegistro(new ArrayList<Registro>());
        Registro registro = new Registro();
        registro.setNombreGuardado("TRANSF_BCO_RIO");
        registro.setFechaEjecucion("20111002");
        XMLEntrada xmlEntrada = new XMLEntrada();
        xmlEntrada.setTipoRecurrencia("I");
        xmlEntrada.setCodigoConcepto("VARVarios");
        xmlEntrada.setTipoCtaDebito("9");
        xmlEntrada.setCuit1("123123123123");
        xmlEntrada.setDatosAdicionales(new DatosAdicionales());
        xmlEntrada.getDatosAdicionales().setDatosMensAvisos(new DatosMensAvisos());
        xmlEntrada.getDatosAdicionales().setDatosMensAvisos(new DatosMensAvisos());
        registro.setXmlEntrada(xmlEntrada);
        XMLResultado xmlResultado = new XMLResultado();
        xmlResultado.setImporteOrigen("12.00");
        registro.setXmlResultado(xmlResultado);
        value.getRegistro().add(registro);
        xmlResponseEntity.setDATOSRESULTADO(value);
        return xmlResponseEntity;
    }

    private Future<XMLResponseEntity> obtenerFuture(XMLResponseEntity xmlResponseEntity) {
        return new AsyncResult<XMLResponseEntity>(getComprobanteResponseMock(xmlResponseEntity));
    }

    private XMLResponseEntity getComprobanteResponseMock(XMLResponseEntity xmlResponseEntity) {
        return xmlResponseEntity;
    }

    private Cliente obtenerCliente() {
        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento("201111112");
        cliente.setDni("38677554");
        cliente.setNup("02372381723");
        cliente.setTipoDocumento("I");
        cliente.setPasswordRacf("@Isdnas98");
        cliente.setUsuarioRacf("@aiskw23");
        return cliente;
    }

    @Test
    public void crearComprobante7x24RioEfec() throws DAOException, InterruptedException, ExecutionException {
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRANSF_BCO_RIO");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        xmlEntrada.setTipoRecurrencia("I");
        xmlEntrada.setTipoCtaDebito("09");
        xmlEntrada.setNombreCtaCredito(null);
        xmlEntrada.setCodigoConcepto("VARVarios");
        xmlEntrada.setSucCtaDebito("235");
        xmlEntrada.setNroCtaDebito("7856348");
        xmlEntrada.setCuit1("123123123123");
        xmlEntrada.setDatosAdicionales(new DatosAdicionales());
        xmlEntrada.getDatosAdicionales().setDatosMensAvisos(new DatosMensAvisos());
        registro.setXmlEntrada(xmlEntrada);
        XMLResultado xmlResultado = new XMLResultado();
        xmlResultado.setImporteOrigen("12.00");
        registro.setXmlResultado(xmlResultado);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);
        Mockito.when(thread.get()).thenReturn(xmlResponse);
        Mockito.when(thread.isDone()).thenReturn(true);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO.obtenerComprobantesRio(new Cliente(),
                transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("-", comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobante7x24RioEfecE() throws DAOException, InterruptedException, ExecutionException {
        // Con tipo recurrencia E, este comprobante deberia obtener sus datos
        // del objeto DATOSENTRADA dentro de registro
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRANSF_BCO_RIO");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        DATOSENTRADA datosEntrada = new DATOSENTRADA();
        xmlEntrada.setTipoRecurrencia("E");
        datosEntrada.setTipoCtaDebito("09");
        datosEntrada.setNombreCtaCredito("Teresa");
        datosEntrada.setSucCtaDebito("235");
        datosEntrada.setNroCtaDebito("7856348");
        datosEntrada.setCuit1("123123123123");
        datosEntrada.setCodigoConcepto("VARVarios");
        xmlEntrada.setDatosAdicionales(new DatosAdicionales());
        xmlEntrada.getDatosAdicionales().setDatosMensAvisos(new DatosMensAvisos());
        datosEntrada.setCuit1("123123123123");
        xmlEntrada.setDATOSENTRADA(datosEntrada);
        registro.setXmlEntrada(xmlEntrada);
        XMLResultado xmlResultado = new XMLResultado();
        xmlResultado.setImporteOrigen("12.00");
        registro.setXmlResultado(xmlResultado);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO.obtenerComprobantesRio(new Cliente(),
                transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Teresa",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobanteError7x24ACHEfec() throws DAOException, InterruptedException, ExecutionException {
        Registro registro = new Registro();
        registro.setNombreGuardado("TRFACH");
        registro.setFechaEjecucion("2011a212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        xmlEntrada.setTipoRecurrencia("I");
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO.obtenerComprobantesACH(new Cliente(),
                new TransaccionDTO());
        Assert.assertEquals(true, comprobanteRioSietePorVenti.getRespuesta().getTieneError());
    }

    @Test
    public void crearComprobanteError7x24OBEfec() throws DAOException, InterruptedException, ExecutionException {
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRFCCI");
        registro.setFechaEjecucion("2011a212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        xmlEntrada.setTipoRecurrencia("I");
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesOtrosBancos(new Cliente(), transaccion);
        Assert.assertEquals(true, comprobanteRioSietePorVenti.getRespuesta().getTieneError());
    }

    @Test
    public void crearComprobante7x24ACHEfec() throws InterruptedException, ExecutionException, DAOException {
        Registro registro = new Registro();
        registro.setNombreGuardado("TRFACH");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        xmlEntrada.setTipoRecurrencia("I");
        xmlEntrada.setTipoCtaDebito("03");
        xmlEntrada.setNombreCtaCredito(null);
        xmlEntrada.setSucCtaDebito("235");
        xmlEntrada.setNroCtaDebito("7856348");
        xmlEntrada.setImporteDebito("12.00");
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO.obtenerComprobantesACH(new Cliente(),
                new TransaccionDTO());
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("-", comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
        Assert.assertEquals(TipoCuenta.CUENTA_CORRIENTE_DOLARES,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoDolares());
    }

    @Test
    public void crearComprobante7x24ACHEfecE() throws DAOException, InterruptedException, ExecutionException {
        // Con tipo recurrencia E, este comprobante deberia obtener sus datos
        // del objeto DATOSENTRADA dentro de registro
        Registro registro = new Registro();
        registro.setNombreGuardado("TRFACH");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        DATOSENTRADA datosEntrada = new DATOSENTRADA();
        xmlEntrada.setTipoRecurrencia("E");
        datosEntrada.setTipoCtaDebito("04");
        datosEntrada.setNombreCtaCredito("Teresa");
        datosEntrada.setSucCtaDebito("0");
        datosEntrada.setNroCtaDebito("639170");
        datosEntrada.setImporteDebito("12.00");
        xmlEntrada.setDATOSENTRADA(datosEntrada);
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO.obtenerComprobantesACH(new Cliente(),
                new TransaccionDTO());
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Teresa",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
        Assert.assertEquals(TipoCuenta.CAJA_AHORRO_DOLARES,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoDolares());
        Assert.assertEquals("000-063917/0",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getCtaMedioDePagoDolares());
    }

    @Test
    public void crearComprobante7x24OtrosBancosEfec() throws DAOException, InterruptedException, ExecutionException {
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRFCCI");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        xmlEntrada.setTipoRecurrencia("I");
        xmlEntrada.setTipoCtaDebito("03");
        xmlEntrada.setTitular("ruiz");
        xmlEntrada.setSucCtaDebito("235");
        xmlEntrada.setNroCtaDebito("7856348");
        xmlEntrada.setMonedaTransferencia("0");
        xmlEntrada.setReferenciaUnivoca("SEGVarios");
        registro.setXmlEntrada(xmlEntrada);
        XMLResultado xmlResultado = new XMLResultado();
        xmlResultado.setImporteDebitado("12.00");
        xmlEntrada.setCuit1("123123123123");
        registro.setXmlResultado(xmlResultado);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesOtrosBancos(new Cliente(), transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Ruiz",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CUENTA_CORRIENTE_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobante7x24OtrosBancosEfecE() throws DAOException, InterruptedException, ExecutionException {
        // Con tipo recurrencia E, este comprobante deberia obtener sus datos
        // del objeto DATOSENTRADA dentro de registro
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRFCCI");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        DATOSENTRADA datosEntrada = new DATOSENTRADA();
        xmlEntrada.setTipoRecurrencia("E");
        datosEntrada.setTipoCtaDebito("04");
        datosEntrada.setTitular("Teresa");
        datosEntrada.setSucCtaDebito("235");
        datosEntrada.setNroCtaDebito("7856348");
        datosEntrada.setReferenciaUnivoca("SEGVarios");
        datosEntrada.setCuit1("123123123123");
        xmlEntrada.setDATOSENTRADA(datosEntrada);
        registro.setXmlEntrada(xmlEntrada);
        XMLResultado xmlResultado = new XMLResultado();
        xmlResultado.setImporteDebitado("12.00");
        registro.setXmlResultado(xmlResultado);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesOtrosBancos(new Cliente(), transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Teresa",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
        Assert.assertEquals(TipoCuenta.CAJA_AHORRO_DOLARES,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoDolares());
    }

    @Test
    public void crearComprobante7x24RioNoEfec() throws DAOException, InterruptedException, ExecutionException {
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRANSF_BCO_RIO");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        xmlEntrada.setTipoRecurrencia("I");
        xmlEntrada.setTipoCtaDebito("09");
        xmlEntrada.setNombreCtaCredito("Caja de Ahorro en D¿lares");
        xmlEntrada.setSucCtaDebito("235");
        xmlEntrada.setNroCtaDebito("7856348");
        xmlEntrada.setImporteDebito("12.00");
        xmlEntrada.setDatosAdicionales(new DatosAdicionales());
        xmlEntrada.getDatosAdicionales().setDatosMensAvisos(new DatosMensAvisos());
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesRioNoEfec(new Cliente(), transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
        Assert.assertEquals("Caja De Ahorro En Dólares",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
    }

    @Test
    public void crearComprobante7x24RioNoEfec2() throws DAOException, InterruptedException, ExecutionException {
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRANSF_BCO_RIO");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        xmlEntrada.setTipoRecurrencia("I");
        xmlEntrada.setTipoCtaDebito("10");
        xmlEntrada.setNombreCtaCredito(null);
        xmlEntrada.setSucCtaDebito("235");
        xmlEntrada.setNroCtaDebito("7856348");
        xmlEntrada.setImporteDebito("12.00");
        xmlEntrada.setDatosAdicionales(new DatosAdicionales());
        xmlEntrada.getDatosAdicionales().setDatosMensAvisos(new DatosMensAvisos());

        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesRioNoEfec(new Cliente(), transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("-", comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_DOLARES,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoDolares());
    }

    @Test
    public void crearComprobante7x24RioNoEfecE() throws DAOException, InterruptedException, ExecutionException {
        // Con tipo recurrencia E, este comprobante deberia obtener sus datos
        // del objeto DATOSENTRADA dentro de registro
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRANSF_BCO_RIO");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        DATOSENTRADA datosEntrada = new DATOSENTRADA();
        xmlEntrada.setTipoRecurrencia("E");
        datosEntrada.setTipoCtaDebito("09");
        datosEntrada.setNombreCtaCredito("Teresa");
        datosEntrada.setSucCtaDebito("235");
        datosEntrada.setNroCtaDebito("7856348");
        datosEntrada.setImporteDebito("12.00");
        xmlEntrada.setDatosAdicionales(new DatosAdicionales());
        xmlEntrada.getDatosAdicionales().setDatosMensAvisos(new DatosMensAvisos());
        xmlEntrada.setDATOSENTRADA(datosEntrada);
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesRioNoEfec(new Cliente(), transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Teresa",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobante7x24ACHNoEfec() throws DAOException, InterruptedException, ExecutionException {
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRFACH");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        xmlEntrada.setTipoRecurrencia("I");
        xmlEntrada.setTipoCtaDebito("03");
        xmlEntrada.setNombreCtaCredito(null);
        xmlEntrada.setSucCtaDebito("235");
        xmlEntrada.setNroCtaDebito("7856348");
        xmlEntrada.setImporteTransferencia("12.00");
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class))).thenReturn(thread);
        Mockito.when(thread.get()).thenReturn(xmlResponse);
        Mockito.when(thread.isDone()).thenReturn(true);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesACHNoEfec(new Cliente(), transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("-", comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
        Assert.assertEquals(TipoCuenta.CUENTA_CORRIENTE_DOLARES,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoDolares());
    }

    @Test
    public void crearComprobante7x24ACHNoEfecE() throws DAOException, InterruptedException, ExecutionException {
        // Con tipo recurrencia E, este comprobante deberia obtener sus datos
        // del objeto DATOSENTRADA dentro de registro
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRFACH");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        DATOSENTRADA datosEntrada = new DATOSENTRADA();
        xmlEntrada.setTipoRecurrencia("E");
        datosEntrada.setTipoCtaDebito("01");
        datosEntrada.setNombreCtaCredito("Teresa");
        datosEntrada.setSucCtaDebito("235");
        datosEntrada.setNroCtaDebito("7856348");
        datosEntrada.setImporteTransferencia("12.00");
        xmlEntrada.setDATOSENTRADA(datosEntrada);
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class))).thenReturn(thread);
        Mockito.when(thread.get()).thenReturn(xmlResponse);
        Mockito.when(thread.isDone()).thenReturn(true);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesACHNoEfec(new Cliente(), transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Teresa",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CAJA_AHORRO_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobante7x24OtrosBancosNoEfec() throws DAOException, InterruptedException, ExecutionException {
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRFCCI");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        xmlEntrada.setTipoRecurrencia("I");
        xmlEntrada.setTipoCtaDebito("03");
        xmlEntrada.setTitular("ruiz");
        xmlEntrada.setSucCtaDebito("0");
        xmlEntrada.setNroCtaDebito("639170");
        xmlEntrada.setMonedaTransferencia("0");
        xmlEntrada.setImporteTransferencia("12.00");
        xmlEntrada.setReferenciaUnivoca("VARVarios");
        xmlEntrada.setCuit1("123123123123");
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesOtrosBancosNoEfec(new Cliente(), transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Ruiz",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CUENTA_CORRIENTE_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
        Assert.assertEquals("000-063917/0",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobante7x24OtrosBancosNoEfecE() throws InterruptedException, ExecutionException, DAOException {
        // Con tipo recurrencia E, este comprobante deberia obtener sus datos
        // del objeto DATOSENTRADA dentro de registro
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("TRFCCI");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        DATOSENTRADA datosEntrada = new DATOSENTRADA();
        xmlEntrada.setTipoRecurrencia("E");
        datosEntrada.setTipoCtaDebito("04");
        datosEntrada.setTitular("Teresa");
        datosEntrada.setSucCtaDebito("235");
        datosEntrada.setNroCtaDebito("7856348");
        datosEntrada.setImporteTransferencia("12.00");
        datosEntrada.setReferenciaUnivoca("VARVarios");
        datosEntrada.setCuit1("123123123123");
        xmlEntrada.setDATOSENTRADA(datosEntrada);
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesOtrosBancosNoEfec(new Cliente(), transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Teresa",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
        Assert.assertEquals(TipoCuenta.CAJA_AHORRO_DOLARES,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoDolares());
    }

    // En el caso de PAGHABON_ efectuados y no efectuados son iguales excepto en
    // el detalle asi que solo voy a hacer uno de los 2
    @Test
    public void crearComprobante7x24PAGHABONNoEfec() throws InterruptedException, ExecutionException, DAOException {
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("PAGHABHON_");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        xmlEntrada.setTipoRecurrencia("I");
        xmlEntrada.setTipoCtaDebito("03");
        xmlEntrada.setNombreCtaCredito(null);
        xmlEntrada.setSucCtaDebito("235");
        xmlEntrada.setNroCtaDebito("7856348");
        xmlEntrada.setImporte("12.00");
        xmlEntrada.setDatosAdicionales(new DatosAdicionales());
        xmlEntrada.getDatosAdicionales().setDatosSueldos(new DatosSueldos());
        xmlEntrada.getDatosAdicionales().getDatosSueldos().setTipoPago("S");
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class))).thenReturn(thread);
        Mockito.when(thread.get()).thenReturn(xmlResponse);
        Mockito.when(thread.isDone()).thenReturn(true);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosNoEfec(new Cliente(), transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.PAGO_SUELDOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("-", comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
        Assert.assertEquals(TipoCuenta.CUENTA_CORRIENTE_DOLARES,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoDolares());
    }

    @Test
    public void crearComprobante7x24PAGOSueldosNNoEfecE()
            throws InterruptedException, ExecutionException, DAOException {
        // Con tipo recurrencia E, este comprobante deberia obtener sus datos
        // del objeto DATOSENTRADA dentro de registro
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("PAGHABHON_");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        DATOSENTRADA datosEntrada = new DATOSENTRADA();
        xmlEntrada.setTipoRecurrencia("E");
        datosEntrada.setTipoCtaDebito("01");
        datosEntrada.setNombreCtaCredito("Teresa");
        datosEntrada.setSucCtaDebito("235");
        datosEntrada.setNroCtaDebito("7856348");
        datosEntrada.setImporte("12.00");
        xmlEntrada.setDATOSENTRADA(datosEntrada);
        xmlEntrada.setDatosAdicionales(new DatosAdicionales());
        xmlEntrada.getDatosAdicionales().setDatosSueldos(new DatosSueldos());
        xmlEntrada.getDatosAdicionales().getDatosSueldos().setTipoPago("S");
        xmlEntrada.getDatosAdicionales().getDatosSueldos().setDestinatario("TERESA");
        datosEntrada.setDatosAdicionales(new DatosAdicionales());
        datosEntrada.getDatosAdicionales().setDatosSueldos(new DatosSueldos());
        datosEntrada.getDatosAdicionales().getDatosSueldos().setTipoPago("S");
        datosEntrada.getDatosAdicionales().getDatosSueldos().setDestinatario("TERESA");
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class))).thenReturn(thread);
        Mockito.when(thread.get()).thenReturn(xmlResponse);
        Mockito.when(thread.isDone()).thenReturn(true);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosNoEfec(new Cliente(), transaccion);
        Assert.assertNotNull(comprobanteRioSietePorVenti.getRespuesta());
        // Assert.assertEquals(TipoOperacionComprobanteEnum.PAGO_SUELDOS,
        // comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        // Assert.assertEquals("Teresa",
        // comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        // Assert.assertEquals(new BigDecimal("12.00"),
        // comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        // Assert.assertEquals(TipoCuenta.CAJA_AHORRO_PESOS,
        // comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobante7x24PAGOHonorariosNNoEfecE()
            throws InterruptedException, ExecutionException, DAOException {
        // Con tipo recurrencia E, este comprobante deberia obtener sus datos
        // del objeto DATOSENTRADA dentro de registro
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("PAGHABHON_");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        DATOSENTRADA datosEntrada = new DATOSENTRADA();
        xmlEntrada.setTipoRecurrencia("E");
        datosEntrada.setTipoCtaDebito("01");
        datosEntrada.setNombreCtaCredito("Teresa");
        datosEntrada.setSucCtaDebito("235");
        datosEntrada.setNroCtaDebito("7856348");
        datosEntrada.setImporte("$ 12.00");
        xmlEntrada.setDATOSENTRADA(datosEntrada);
        xmlEntrada.setDatosAdicionales(new DatosAdicionales());
        xmlEntrada.getDatosAdicionales().setDatosSueldos(new DatosSueldos());
        xmlEntrada.getDatosAdicionales().getDatosSueldos().setTipoPago("H");
        xmlEntrada.getDatosAdicionales().getDatosSueldos().setDestinatario("TERESA");
        datosEntrada.setDatosAdicionales(new DatosAdicionales());
        datosEntrada.getDatosAdicionales().setDatosSueldos(new DatosSueldos());
        datosEntrada.getDatosAdicionales().getDatosSueldos().setTipoPago("H");
        datosEntrada.getDatosAdicionales().getDatosSueldos().setDestinatario("TERESA");
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class))).thenReturn(thread);
        Mockito.when(thread.get()).thenReturn(xmlResponse);
        Mockito.when(thread.isDone()).thenReturn(true);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosNoEfec(new Cliente(), transaccion);
        Assert.assertNotNull(comprobanteRioSietePorVenti.getRespuesta());
        // Assert.assertEquals(TipoOperacionComprobanteEnum.PAGO_HONORARIOS,
        // comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        // Assert.assertEquals("Teresa",
        // comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        // Assert.assertEquals(new BigDecimal("12.00"),
        // comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        // Assert.assertEquals(TipoCuenta.CAJA_AHORRO_PESOS,
        // comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    public void detalleDTOEqualsTest() throws DAOException, InterruptedException, ExecutionException {
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("PAGHABHON_");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        DATOSENTRADA datosEntrada = new DATOSENTRADA();
        xmlEntrada.setTipoRecurrencia("E");
        datosEntrada.setTipoCtaDebito("01");
        datosEntrada.setNombreCtaCredito("Teresa");
        datosEntrada.setSucCtaDebito("235");
        datosEntrada.setNroCtaDebito("7856348");
        datosEntrada.setImporte("$ 12.00");
        xmlEntrada.setDATOSENTRADA(datosEntrada);
        xmlEntrada.setDatosAdicionales(new DatosAdicionales());
        xmlEntrada.getDatosAdicionales().setDatosSueldos(new DatosSueldos());
        xmlEntrada.getDatosAdicionales().getDatosSueldos().setTipoPago("H");
        xmlEntrada.getDatosAdicionales().getDatosSueldos().setDestinatario("TERESA");
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class))).thenReturn(thread);
        Mockito.when(thread.get()).thenReturn(xmlResponse);
        Mockito.when(thread.isDone()).thenReturn(true);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosNoEfec(new Cliente(), transaccion);

        xmlEntrada.getDatosAdicionales().getDatosSueldos().setDestinatario("JOSE");
        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti2 = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosNoEfec(new Cliente(), transaccion);
        Assert.assertTrue(!comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0)
                .equals(comprobanteRioSietePorVenti2.getRespuesta().getComprobantes().get(0)));
        Assert.assertTrue(comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0)
                .equals(comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0)));
    }

    public void detalleDTOHashTest() throws DAOException, InterruptedException, ExecutionException {
        Registro registro = new Registro();
        TransaccionDTO transaccion = new TransaccionDTO();
        registro.setNombreGuardado("PAGHABHON_");
        registro.setFechaEjecucion("20111212");
        XMLEntrada xmlEntrada = new XMLEntrada();
        DATOSENTRADA datosEntrada = new DATOSENTRADA();
        xmlEntrada.setTipoRecurrencia("E");
        datosEntrada.setTipoCtaDebito("01");
        datosEntrada.setNombreCtaCredito("Teresa");
        datosEntrada.setSucCtaDebito("235");
        datosEntrada.setNroCtaDebito("7856348");
        datosEntrada.setImporte("$ 12.00");
        xmlEntrada.setDATOSENTRADA(datosEntrada);
        xmlEntrada.setDatosAdicionales(new DatosAdicionales());
        xmlEntrada.getDatosAdicionales().setDatosSueldos(new DatosSueldos());
        xmlEntrada.getDatosAdicionales().getDatosSueldos().setTipoPago("H");
        xmlEntrada.getDatosAdicionales().getDatosSueldos().setDestinatario("TERESA");
        registro.setXmlEntrada(xmlEntrada);

        XMLResponseEntity xmlResponse = new XMLResponseEntity();
        xmlResponse.setDATOSRESULTADO(new DATOSRESULTADO());
        xmlResponse.getDATOSRESULTADO().setRegistro(new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>());
        xmlResponse.getDATOSRESULTADO().getRegistro().add(registro);
        xmlResponse.getDATOSRESULTADO().setCodRet("0");

        Mockito.when(comprobantesDAO.ejecutarAsync(Matchers.any(XMLRequestEntity.class))).thenReturn(thread);
        Mockito.when(thread.get()).thenReturn(xmlResponse);
        Mockito.when(thread.isDone()).thenReturn(true);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosNoEfec(new Cliente(), transaccion);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti2 = comprobantesBO
                .obtenerComprobantesPagoHaberesYHonorariosNoEfec(new Cliente(), transaccion);
        Assert.assertTrue(comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0)
                .hashCode() != comprobanteRioSietePorVenti2.getRespuesta().getComprobantes().get(0).hashCode());
        Assert.assertTrue(comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0)
                .hashCode() == comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).hashCode());
    }

}
