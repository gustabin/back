package ar.com.santanderrio.obp.servicios.ofuscardato.web.manager;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.ofuscardato.web.view.MailDesafioView;
import ar.com.santanderrio.obp.servicios.ofuscardato.web.view.MostrarMailView;

@Component
public interface MostrarDatoManager {
	/** 
	 * Obtiene el mail del cliente sin ofuscar.
	 * @param mailDesafioView
	 * @param credencialesMya
	 * 
	 * */
	Respuesta<MostrarMailView> obtenerMailSinOfuscar(MailDesafioView mailDesafioView);

}
