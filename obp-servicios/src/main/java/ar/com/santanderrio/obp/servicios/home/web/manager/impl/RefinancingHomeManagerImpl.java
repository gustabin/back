package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.web.manager.RefinancingHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeMicrofrontView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;

@Component
public class RefinancingHomeManagerImpl extends AbstractHomeManager implements RefinancingHomeManager {

	private static final String PLANES = "PLANES DE PAGO EN CUOTAS";

	@Value("${BFF_URL.MICROFRONTEND}")
    protected String microfrontendUrl;

    private static final String MICROFRONT_NAME = "financialHealth";
	
	@Autowired
    private RespuestaFactory respuestaFactory;
	
	@Autowired
	private ConsultasControllerHomeBO consultasControllerHomeBO;
	

	@Override
	public Respuesta<Boolean> aplicaGrupo() {
		return respuestaFactory.crearRespuestaOk(Boolean.TRUE);
	}

	@Override
	public AccionController obtenerAccion() {
		return AccionController.IR_FINANCIAL_HEALTH;
	}

	@Override
	protected GrupoCajaHomeView obtenerCajas() {
		ArrayList<Caja> cajas = new ArrayList<Caja>();
        GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
        grupoCajaHomeView.setSinError(Boolean.TRUE);
        
        if(consultasControllerHomeBO.aplicaRecuperaciones()) {
	        CajaHomeMicrofrontView caja = new CajaHomeMicrofrontView(PLANES, "Tu Salud Financiera", "", MICROFRONT_NAME, microfrontendUrl);
	        cajas.add(caja);
	        grupoCajaHomeView.setCajas(cajas);
        } else return grupoCajaHomeView;
        return grupoCajaHomeView;
	}

}
