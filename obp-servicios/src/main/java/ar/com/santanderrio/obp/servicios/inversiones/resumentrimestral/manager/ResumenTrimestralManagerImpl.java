package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.manager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager.impl.ResumenInversionesManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.bo.ResumenTrimestralBO;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResponsePdfView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenPdfInView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenTrimestralView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenesDispInView;

@Component
public class ResumenTrimestralManagerImpl implements ResumenTrimestralManager {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResumenInversionesManagerImpl.class);

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	SesionCliente sesionCliente;
	
	@Autowired
	private ResumenTrimestralBO resumenTrimestralBO;
	

	@Override
	public Respuesta<ResumenTrimestralView> obtenerDisponibles(ResumenesDispInView request) {
		Cliente cliente = sesionCliente.getCliente();
		String segmento=request.getSegmento();
		Respuesta<ResumenTrimestralView> respuesta;
		ResumenTrimestralView resumenesView=resumenTrimestralBO.obtenerDisponibles(cliente.getNup(),segmento);
		respuesta=respuestaFactory.crearRespuestaOk(ResumenTrimestralView.class,resumenesView);
		if(respuesta.getRespuesta().getListaCuentas().isEmpty()) {
			LOGGER.info("No posee Cuenta Títulos");
			List<ItemMensajeRespuesta> itemsMensaje=new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta mensajeResp=new ItemMensajeRespuesta();
			mensajeResp.setMensaje("No posee Cuenta Títulos");
			itemsMensaje.add(mensajeResp);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			mensajeResp.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			respuesta.setItemMensajeRespuesta(itemsMensaje);
		}
		return respuesta;
	}

	@Override
	public Respuesta<ResponsePdfView> obtenerPdf(ResumenPdfInView viewRequest) {
		// TODO Auto-generated method stub
		Cliente cliente = sesionCliente.getCliente();
		String idPdf="";
		
		if(viewRequest.getId().length>1) {
			
			for(int i=0;i<viewRequest.getId().length;i++) {
				if(i==0) {
					idPdf=Integer.toString(viewRequest.getId()[i]);
				}else {
					idPdf=idPdf+","+viewRequest.getId()[i];
				}
				
			}
			
		}else {
			idPdf=Integer.toString(viewRequest.getId()[0]);
		}
		
		Respuesta<ResponsePdfView> respuesta=new Respuesta<ResponsePdfView>();
		ResponsePdfView resPView=new ResponsePdfView();
		
		if(idPdf.equals("0")) {
			List<ItemMensajeRespuesta> itemsMensaje=new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta mensajeResp=new ItemMensajeRespuesta();
			mensajeResp.setMensaje("REQUEST INVALIDO");
			itemsMensaje.add(mensajeResp);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			mensajeResp.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			respuesta.setItemMensajeRespuesta(itemsMensaje);
		}else {
			resPView=resumenTrimestralBO.obtenerPdf(idPdf,cliente.getNup());
			respuesta=respuestaFactory.crearRespuestaOk(ResponsePdfView.class,resPView);
		}
		
		
		return respuesta;
		
	}

	
}
