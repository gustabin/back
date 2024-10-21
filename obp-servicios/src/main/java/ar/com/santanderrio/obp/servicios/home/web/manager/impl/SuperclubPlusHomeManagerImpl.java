package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import ar.com.santanderrio.obp.servicios.premify.bo.MemberBO;
import org.springframework.stereotype.Component;
import ar.com.santanderrio.obp.servicios.home.web.view.*;
import org.springframework.beans.factory.annotation.Value;
import ar.com.santanderrio.obp.servicios.premify.bo.PremifyBO;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.premify.view.NotificacionView;
import ar.com.santanderrio.obp.servicios.premify.entity.NotificacionEntity;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.home.web.manager.SuperclubPlusHomeManager;
import ar.com.santanderrio.obp.servicios.premify.view.CajaHomeMicrofrontSuperclubPlusView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

@Component
public class SuperclubPlusHomeManagerImpl extends AbstractHomeManager implements SuperclubPlusHomeManager {

    @Autowired
    private RespuestaFactory respuestaFactory;

    @Autowired
    private SesionCliente sesionCliente;

    @Autowired
    private PremifyBO premifyBO;

    @Autowired
    private MemberBO memberBO;

    @Value("${BFF_URL.MICROFRONTEND}")
    protected String microfrontendUrl;

    private static final String MICROFRONT_NAME = "premify";

    private static final String SUPERCLUB_PLUS = "SuperClub+";

    @Override
    public Respuesta<Boolean> aplicaGrupo() {
        return respuestaFactory.crearRespuestaOk(Boolean.TRUE);
    }

    @Override
    public AccionController obtenerAccion() {
        return AccionController.IR_SUPERCLUB_PREMIFY;
    }

    @Override
    protected GrupoCajaHomeView obtenerCajas() {
        ArrayList<Caja> cajas = new ArrayList<Caja>();
        GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
        grupoCajaHomeView.setSinError(Boolean.TRUE);
        DecimalFormatSymbols simbols = new DecimalFormatSymbols(Locale.getDefault());
        simbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###", simbols);

        String nup = this.sesionCliente.getCliente().getNup();
        Integer cantidadPuntos = this.memberBO.getMember(nup).getPoints();
        String descripcion = cantidadPuntos == 0 ? "Sumá puntos" : "Tenés " + formatter.format(cantidadPuntos) + " puntos";
        CajaHomeMicrofrontSuperclubPlusView caja = new CajaHomeMicrofrontSuperclubPlusView("", SUPERCLUB_PLUS, descripcion, MICROFRONT_NAME, microfrontendUrl);
        NotificacionEntity notificaciones = this.premifyBO.obtenerNotificaciones(nup);
        NotificacionView notificacionView = new NotificacionView(notificaciones.getCantidadNotificaciones(), notificaciones.getHayNotificaciones());
        caja.setNotificacionView(notificacionView);
        cajas.add(caja);
        cajas.trimToSize();
        grupoCajaHomeView.setCajas(cajas);
        return grupoCajaHomeView;
    }
}
