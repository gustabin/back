package ar.com.santanderrio.obp.servicios.transferencias.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DestinatariosFrecuentesDTO;

public interface DestinatariosFrecuentesBO {

	public Respuesta<DestinatariosFrecuentesDTO> obtenerDestinatariosFrecuentes(Cliente cliente);

	public Respuesta<String> altaFavorito(DestinatarioView destinatario, Cliente cliente);

	public Respuesta<String> bajaFavorito(DestinatarioView destinatario);

}
