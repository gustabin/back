/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.bo.TarjetasHomeBO;
import ar.com.santanderrio.obp.servicios.home.entitites.ListaTarjetasDTO;
import ar.com.santanderrio.obp.servicios.home.entitites.TarjetaHomeDTO;
import ar.com.santanderrio.obp.servicios.home.web.manager.TarjetaHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeTarjetaView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BanelcoDAO;

/**
 * The Class TarjetaHomeManagerImpl.
 */
@Component
public class TarjetaHomeManagerImpl extends AbstractHomeManager implements TarjetaHomeManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaHomeManagerImpl.class);

	/** The Constant TEXTO_LINK_CAJA_TARJETA. */
	private static final String TEXTO_LINK_CAJA_TARJETA = "Ver mis consumos";

	/** The tarjetas home BO. */
	@Autowired
	private TarjetasHomeBO tarjetasHomeBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
    /** The banelco DAO. */
    @Autowired
    private BanelcoDAO banelcoDAO;
    
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.impl.
	 * AbstractHomeManager#obtenerCajas()
	 */
	@Override
	protected GrupoCajaHomeView obtenerCajas() {
		Respuesta<ListaTarjetasDTO> respuesta = new Respuesta<ListaTarjetasDTO>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		Cliente cliente = sesionCliente.getCliente();
		GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();

		List<Caja> cajas = new ArrayList<Caja>();
		respuesta = tarjetasHomeBO.obtenerTarjetas(cliente);
		if (respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			grupoCajaHomeView.setSinError(Boolean.TRUE);
			if (!respuesta.isRespuestaVacia()) {
				cajas = obtenerCajas(respuesta.getRespuesta());
			}
		} else {
			grupoCajaHomeView.setSinError(Boolean.FALSE);
		}
		grupoCajaHomeView.setCajas(cajas);
		return grupoCajaHomeView;
	}

	/**
	 * Obtener cajas.
	 *
	 * @param respuesta the respuesta
	 * @return the list
	 */
	private List<Caja> obtenerCajas(ListaTarjetasDTO respuesta) {
		List<Caja> cajas = new ArrayList<Caja>();
		for (TarjetaHomeDTO tarjetaDTO : respuesta.getTarjetas()) {
			CajaHomeTarjetaView caja = mapearCajaInicial(tarjetaDTO);
			cajas.add(caja);
		}
		return cajas;
	}

	/**
	 * Gets the caja.
	 *
	 * @param tarjetaHomeDTO the tarjeta home DTO
	 * @return the caja
	 */
	private CajaHomeTarjetaView mapearCajaInicial(TarjetaHomeDTO tarjetaHomeDTO) {
		CajaHomeTarjetaView cajaHomeTarjetaView = new CajaHomeTarjetaView();
		cajaHomeTarjetaView.setIsTarjeta(true);
		cajaHomeTarjetaView.setId(String.valueOf(tarjetaHomeDTO.getId()));
		cajaHomeTarjetaView.setEncabezado(WordUtils.capitalizeFully(tarjetaHomeDTO.getMarca()));
		cajaHomeTarjetaView
		        .setTitulo(tarjetaHomeDTO.getHasAlias() ? tarjetaHomeDTO.getAlias() : tarjetaHomeDTO.getNumero());
		cajaHomeTarjetaView.setTextoLink(TEXTO_LINK_CAJA_TARJETA);
		cajaHomeTarjetaView.setRecargable(tarjetaHomeDTO.isRecargable());
		cajaHomeTarjetaView.setProducto(tarjetaHomeDTO.getProducto());
		cajaHomeTarjetaView.setSubProducto(tarjetaHomeDTO.getSubProducto());
		return cajaHomeTarjetaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#
	 * aplicaGrupo()
	 */
	@Override
	public Respuesta<Boolean> aplicaGrupo() {
		if (sesionCliente.getItemsRespuesta() != null) {
			for (ItemMensajeRespuesta item : sesionCliente.getItemsRespuesta()) {
				if (item.getTipoError().equals(TipoError.ERROR_TARJETA_MASTERCARD_VISA_BANELCO.getDescripcion())
				        || item.getTipoError().equals(TipoError.ERROR_TARJETA_VISA_MASTERCARD.getDescripcion())
				        || item.getTipoError().equals(TipoError.ERROR_TARJETA_BANELCO.getDescripcion())) {
					return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_TABLERO_HOME,
					        CodigoMensajeConstantes.ERROR_TABLERO_HOME);
				}
			}
		}
		return respuestaFactory.crearRespuestaOk(Boolean.class, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#
	 * obtenerAccion()
	 */
	@Override
	public AccionController obtenerAccion() {
		return AccionController.IR_HOME_TARJETA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.TarjetaHomeManager#
	 * obtenerSaldoTarjeta(ar.com.santanderrio.obp.servicios.home.web.view.
	 * CajaHomeTarjetaView)
	 */
	@Override
	public Respuesta<CajaHomeTarjetaView> obtenerSaldoTarjeta(CajaHomeTarjetaView cajaTarjetasHomeView) {
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuenta = cliente.getCuentaPorIndex(cajaTarjetasHomeView.getId());
		CajaHomeTarjetaView cajaHomeTarjetaView;

        if(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VER_SALDOS_TARJETAS)
                .tienePermisoDeVisibilidad()) {
    		Respuesta<TarjetaHomeDTO> respuestaTarjetaHomeDTO = tarjetasHomeBO.obtenerSaldoTarjeta(cuenta);

    		if (EstadoRespuesta.OK.equals(respuestaTarjetaHomeDTO.getEstadoRespuesta())) {
    			cajaHomeTarjetaView = mapearCajaSaldos(respuestaTarjetaHomeDTO.getRespuesta(), cuenta.getIndex());
    			return respuestaFactory.crearRespuestaOk(cajaHomeTarjetaView);
    		} else {
    			// OJO
    			cajaHomeTarjetaView = mapearCajaSaldos(respuestaTarjetaHomeDTO.getRespuesta(), cuenta.getIndex());
    			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_TABLERO_HOME,
    			        CodigoMensajeConstantes.ERROR_TABLERO_HOME);
    		}
        } else {
			return respuestaFactory.crearRespuestaWarning(null, "", TipoError.WARNING_CAJA_VACIA, "");
        }
		
	}

	/**
	 * Mapear caja saldos.
	 *
	 * @param tarjetaHomeDTO the tarjeta home DTO
	 * @param index          the index
	 * @return the caja home tarjeta view
	 */
	private CajaHomeTarjetaView mapearCajaSaldos(TarjetaHomeDTO tarjetaHomeDTO, Integer index) {

		CajaHomeTarjetaView cajaHomeTarjetaView = new CajaHomeTarjetaView();
		BigDecimal saldoPesos = tarjetaHomeDTO.getSaldoPesos();
		BigDecimal saldoDolares = tarjetaHomeDTO.getSaldoDolares();

		cajaHomeTarjetaView.setId(index.toString());
		if (saldoPesos != null) {
			cajaHomeTarjetaView.setSaldoPesos(ISBANStringUtils.formatearSaldoConSigno(tarjetaHomeDTO.getSaldoPesos()));
			cajaHomeTarjetaView.setSaldoPesosValue(tarjetaHomeDTO.getSaldoPesos().doubleValue());
		} else {
			cajaHomeTarjetaView.setSaldoPesos("-");
			cajaHomeTarjetaView.setSaldoPesosValue(Double.valueOf(0));
		}
		cajaHomeTarjetaView.setFechaDeCierre(tarjetaHomeDTO.getCierre());
		cajaHomeTarjetaView.setFechaDeVencimiento(tarjetaHomeDTO.getVencimiento());
		if (saldoDolares != null) {
			cajaHomeTarjetaView
			        .setSaldoDolares(ISBANStringUtils.formatearSaldoConSigno(tarjetaHomeDTO.getSaldoDolares()));
			cajaHomeTarjetaView.setSaldoDolaresValue(tarjetaHomeDTO.getSaldoDolares().doubleValue());
		} else {
			cajaHomeTarjetaView.setSaldoDolares("-");
			cajaHomeTarjetaView.setSaldoDolaresValue(Double.valueOf(0));
		}

		return cajaHomeTarjetaView;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.TarjetaHomeManager#obtenerValidacionAfinidad()
	 */
	@Override
	public void obtenerValidacionAfinidad() {
    	try {
			banelcoDAO.obtenerCuentasBanelcoHabilitadas(sesionCliente.getCliente());
		} catch (DAOException e) {
			LOGGER.info("Error al consultar IDEPESBANE pero se continua el flujo de login.", e);
		}
	}

}
