/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.GestorEventoComercialBO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.PeriodoActualDTO;
import ar.com.santanderrio.obp.servicios.home.web.manager.ChanceHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeChanceView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;

/**
 * The Class ChanceHomeManagerImpl.
 */
@Component
public class ChanceHomeManagerImpl extends AbstractHomeManager implements ChanceHomeManager{

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The gestor evento comercial BO. */
    @Autowired
    private GestorEventoComercialBO gestorEventoComercialBO;
	
	/** Obtener cajas chance 
	 * 
	 * @return GrupoCajaHomeView
	 * 
	 */
	@Override
	protected GrupoCajaHomeView obtenerCajas() {
		//creo una lista de Caja
		ArrayList<Caja> cajas = new ArrayList<Caja>();
		GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
		Boolean hayCuentaSueldo = Boolean.FALSE;
		Cliente cliente = sesionCliente.getCliente();
		if(cliente != null ) {
			for(Cuenta cuenta: cliente.getCuentas()) {
				if(cuenta.isConvenioPAS()) {
					hayCuentaSueldo = Boolean.TRUE;
					cajas.add(new CajaHomeChanceView(hayCuentaSueldo));
					grupoCajaHomeView.setSinError(hayCuentaSueldo);
					break;
				}
			}
		}
		grupoCajaHomeView.setCajas(cajas);
		return grupoCajaHomeView;
	}

	/** Aplicar Grupo 
	 * 
	 * @return Respuesta<Boolean>
	 */
	@Override
	public Respuesta<Boolean> aplicaGrupo() {
		return respuestaFactory.crearRespuestaOk(Boolean.TRUE);
	}

	/** Obtener Accion chance 
	 * 
	 * @return AccionController
	 */
	@Override
	public AccionController obtenerAccion() {

		return AccionController.IR_CHANCES;
	}
	
	/**
	 * Obtener premiaciones periodo actual.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CajaHomeChanceView> premiacionesPeriodoActual() {
		Cliente cliente = sesionCliente.getCliente();
		Respuesta<PeriodoActualDTO> respuesta = gestorEventoComercialBO.premiacionesPeriodoActual(cliente);
		if(EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			return respuestaFactory.transformar(null, respuesta);
		}
		CajaHomeChanceView cajaHomeChanceView = new CajaHomeChanceView(respuesta.getRespuesta());
		return respuestaFactory.crearRespuestaOk(cajaHomeChanceView);
	}
	
}
