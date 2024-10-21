package ar.com.santanderrio.obp.servicios.comun.campaniapublica.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.campaniapublica.dao.CampaniaPublicaDAO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLink;

@Component
public class CampaniaPublicaBOImpl implements CampaniaPublicaBO{

	
	@Autowired
	private CampaniaPublicaDAO campaniaPublicaDAO;
	
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<OfertaComercialDTO> armarCampaniasPublicas() {

		List<OfertaComercialDTO> listaCampanias = new ArrayList<OfertaComercialDTO>();
		
		try {
			Map<String, String> campaniasBD = ContextHolder.getContext().getBean(CampaniaPublicaDAO.class).obtenerGoToCampaniasPublicas();
			
			Iterator it = campaniasBD.keySet().iterator();
			while(it.hasNext()){
				OfertaComercialDTO ofertaDTO = new OfertaComercialDTO();
				Object key = it.next();
				ofertaDTO.setIdRtd((String) key);
				ofertaDTO.setGotoLink(new GotoLink(campaniasBD.get(key)));
				listaCampanias.add(ofertaDTO);
			}
			
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaCampanias;
	}

	@Override
	public Respuesta<Boolean> limpiarCampaniasPublicas() {
		campaniaPublicaDAO.limpiarCampaniasPublicas();
		return respuestaFactory.crearRespuestaOk(true);
	}

}
