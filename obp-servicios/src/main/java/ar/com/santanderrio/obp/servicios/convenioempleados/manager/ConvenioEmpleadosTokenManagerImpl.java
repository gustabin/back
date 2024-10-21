package ar.com.santanderrio.obp.servicios.convenioempleados.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.convenioempleados.bo.ConvenioEmpleadosToken;
import ar.com.santanderrio.obp.servicios.token.externos.TokenManager;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@Component
public class ConvenioEmpleadosTokenManagerImpl implements TokenManager{

    @Autowired
    private SesionCliente sesionCliente;

    @Autowired
    private ConvenioEmpleadosToken convenioEmpleadosTokenBO;
	
	@Override
	public Respuesta<TokenView> obtenerTokenEncriptado() {
        Respuesta<TokenDTO> respuesta = convenioEmpleadosTokenBO.obtenerHash(sesionCliente);
        Respuesta<TokenView> respuestaView = Respuesta.copy(TokenView.class, respuesta);
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            TokenView view = new TokenView();
            view.setTokenFirmado(respuesta.getRespuesta().getTokenFirmado());
            view.setUrl(respuesta.getRespuesta().getUrl());

            respuestaView.setRespuesta(view);

        }
        return respuestaView;
	}

}
