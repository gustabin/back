package ar.com.santanderrio.obp.servicios.premify.dao;

import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.premify.contracts.NotificacionResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.junit.Before;
import java.io.IOException;
import org.mockito.Mockito;
import org.mockito.Matchers;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.apache.cxf.jaxrs.client.WebClient;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.premify.dao.impl.PremifyDAOImpl;
import ar.com.santanderrio.obp.servicios.premify.contracts.MemberResponse;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.BadRequestException;

@RunWith(MockitoJUnitRunner.class)
public class PremifyDAOImplTest {

    @InjectMocks
    private PremifyDAO premifyDAO = new PremifyDAOImpl();

    @Mock
    private RestWebClient restWebClient;

    @Mock
    private WebClient webClient;

    @Mock
    private SesionParametros sesionParametros;

    @Before
    public void init() throws SQLException, DAOException, IOException {
        Mockito.when(sesionParametros.getJwt()).thenReturn("dasdasdfasfasfa");
        Mockito.when(webClient.type(Matchers.any(String.class))).thenReturn(webClient);
        Mockito.when(webClient.acceptEncoding(Matchers.any(String.class))).thenReturn(webClient);
        Mockito.when(webClient.accept(Matchers.any(String.class))).thenReturn(webClient);
        Mockito.when(webClient.header(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(webClient);
        Mockito.when(restWebClient.obtenerClienteRest(Matchers.any(String.class))).thenReturn(webClient);
    }

    @Test
    public void getNotificationsOk() throws DAOException {

        List<NotificacionResponse> notificationsResponse = new ArrayList<NotificacionResponse>();
        NotificacionResponse notification = new NotificacionResponse();
        notification.id = 1;
        notification.mensaje = "un mensaje";
        notification.origen = "ui";
        notification.tipo = "un tipo";
        notificationsResponse.add(notification);

        Mockito.when(webClient.get(Matchers.any(Class.class))).thenReturn(notificationsResponse);
        List<NotificacionResponse> respuesta = premifyDAO.getNotifications("12341234");
        assertEquals(notificationsResponse.size(), respuesta.size());
        assertEquals(notificationsResponse.get(0).id, respuesta.get(0).id);
        assertEquals(notificationsResponse.get(0).mensaje, respuesta.get(0).mensaje);
        assertEquals(notificationsResponse.get(0).origen, respuesta.get(0).origen);
        assertEquals(notificationsResponse.get(0).tipo, respuesta.get(0).tipo);
    }

    @Test
    public void getNotificationsBadRequest() throws DAOException {

        Mockito.when(webClient.get(Matchers.any(Class.class))).thenThrow(new BadRequestException());
        Object respuesta = premifyDAO.getNotifications("12341234");
        assertNull(respuesta);
    }

    @Test
    public void getMemberOk() throws DAOException {

        MemberResponse memberResponse = new MemberResponse();
        memberResponse.points = 1234;

        Mockito.when(webClient.get(Matchers.any(Class.class))).thenReturn(memberResponse);
        MemberResponse respuesta = premifyDAO.getMember("12341234");
        assertEquals(memberResponse.points, respuesta.points);
    }

    @Test
    public void getMemberBadRequest() throws DAOException {

        Mockito.when(webClient.get(Matchers.any(Class.class))).thenThrow(new BadRequestException());
        Object respuesta = premifyDAO.getMember("12341234");
        assertNull(respuesta);
    }
}
