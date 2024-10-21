package ar.com.santanderrio.obp.servicios.comprobantes.web.manager;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesView;
 

public final class ComprobantesViewMock {

    private ComprobantesViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtener token operacion DTO.
     *
     * @return the token operacion DTO
     */
    public static ComprobantesView obtenerComprobantesView() {
        ComprobantesView comprobantesView = new ComprobantesView();
        comprobantesView.setComprobantes(obtenerListaComprobantes());
        comprobantesView.setHayMasComprobantes(false);
        return comprobantesView;
    }

    private static List<ComprobanteView> obtenerListaComprobantes() {
        return new ArrayList<ComprobanteView>();
    }

  
    /**
     * Obtiene el registro de sesion.
     *
     * @return the registro sesion
     */
    public static  RegistroSesion obtenerRegistroSesion() {
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("123456789012345");
        return registroSesion;
    }

}