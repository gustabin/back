package ar.com.santanderrio.obp.servicios.adhesionwomen.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.adhesionwomen.dao.AdhesionWomenDAO;
import ar.com.santanderrio.obp.servicios.adhesionwomen.entities.DatosTarjetaWomenEntity;
import ar.com.santanderrio.obp.servicios.adhesionwomen.entities.TarjetaWomenEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.solicitudes.view.AdhesionWomenView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.DatosTarjetaWomen;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao.ReimpresionTarjetasDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.AltaReimpresionTarjetasIn;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.AltaReimpresionTarjetasOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaSolicitada;

@Component
public class AdhesionWomenBOImpl implements AdhesionWomenBO {

	@Autowired
	AdhesionWomenDAO adhesionWomenDAO;
	
	@Autowired
	SesionParametros sesionParametros;
	
	@Autowired
	ReimpresionTarjetasDAO reimpresionTarjetasDAO;
	
    @Value("${WOMEN.ALTA.MARCATC}")
	private String marcaTCWomen;
    
    @Value("${WOMEN.ALTA.TARJETASPARAADHERIR}")
	private String tarjetasAptasAdhesion;
    
    private static final String TARJETA_TITULAR = "1";
    
    private static final String TARJETA_ADICIONAL = "0";
    
    private static final String TARJETA_ACTIVA = "20";
    
    private static final String ES_WOMEN = "W";
    
    private static final String SOLICITUD_EN_CURSO_WOMEN = "10000002";
    
    private static final String MARCA_AMEX = "4";
    
    private static final String TITULAR = "TI";
	@Override
	public List<DatosTarjetaWomen> obtenerListadoWomen(List<Cuenta> listaTarjetasCliente) throws BusinessException {

		List<DatosTarjetaWomen> listaTarjetas = new ArrayList<DatosTarjetaWomen>();
		List<DatosTarjetaWomen> listaTarjetasNoAdheridas = new ArrayList<DatosTarjetaWomen>();

		int errores = 0;
		
		for (Cuenta cuenta : listaTarjetasCliente) {
			if (TITULAR.equals(cuenta.getCalidadDeParticipacion())){
				try {
					listaTarjetas.addAll(crearDatoTarjetaView(adhesionWomenDAO.consultaDatosTarjetas(cuenta), listaTarjetasNoAdheridas));
				} catch (DAOException e) {
					if ("Error inesperado".equals(e.getMessage())) {
						throw new BusinessException(e.getMessage());
					}
					errores++;
				}
			}
		}
		
		if (errores == listaTarjetasCliente.size()) {
			throw new BusinessException("Error inesperado");
		}
		
		Collections.sort(listaTarjetasNoAdheridas);
		Collections.sort(listaTarjetas);
		sesionParametros.setTarjetasPosiblesHabilitacion(listaTarjetasNoAdheridas);
		return listaTarjetas;
	}
	
	private List<DatosTarjetaWomen> crearDatoTarjetaView (TarjetaWomenEntity listaTarjetaWomenEntity, List<DatosTarjetaWomen> listaTarjetasNoAdheridas) {
		
		List<DatosTarjetaWomen> listaTarjetasAdheridas = new ArrayList<DatosTarjetaWomen>();

		
		if (StringUtils.isNotEmpty(marcaTCWomen)) {
			String[] tarjetas = marcaTCWomen.split("\\|");
			
			for (String tarjeta : tarjetas) {
				for(DatosTarjetaWomenEntity tarjetaWomen : listaTarjetaWomenEntity.getListaTarjetas()) {
					if (revisarSiTarjetaPodriaSerWomen(tarjeta, tarjetaWomen)) {
						if (ES_WOMEN.equals(tarjetaWomen.getMarcaWomen())) {
							listaTarjetasAdheridas.add(new DatosTarjetaWomen(tarjetaWomen));
						} else {
							if (correspondeMostrarTarjetaNoAdherida(tarjetaWomen)) {
								listaTarjetasNoAdheridas.add(new DatosTarjetaWomen(tarjetaWomen));
							}
						}
					}
				}
			}
		}
		return listaTarjetasAdheridas;
	}
	
	private Boolean revisarSiTarjetaPodriaSerWomen(String tarjeta, DatosTarjetaWomenEntity tarjetaWomen) {
		
		return tarjeta.equals(tarjetaWomen.getMarcaTC()) && (TARJETA_TITULAR.equals(tarjetaWomen.getAutorizadoTC()) || 
				TARJETA_ADICIONAL.equals(tarjetaWomen.getAutorizadoTC())) && TARJETA_ACTIVA.equals(tarjetaWomen.getEstadoTarjeta());			
		
	}

	@Override
	public AdhesionWomenView ejecutarAltaBajaWomen(List<DatosTarjetaWomen> tarjetasParaOperar,
			List<DatosTarjetaWomen> tarjetasSesion, Cliente cliente, String motivoReimpresion) {

		AltaReimpresionTarjetasIn altaReimpresionTarjetasIn = new AltaReimpresionTarjetasIn();
		List<TarjetaSolicitada> listaTarjetasParaHabilitarDTO = new ArrayList<TarjetaSolicitada>();
		AdhesionWomenView adhesionWomenView = new AdhesionWomenView();
		
		for (DatosTarjetaWomen tarjetaDTO : tarjetasSesion) {
			for (DatosTarjetaWomen tarjetaAUtilizar : tarjetasParaOperar) {
				Boolean corresponde = revisarSiCorrespondeTarjeta(tarjetaDTO, tarjetaAUtilizar.getTarjeta());
				if (corresponde) {
					TarjetaSolicitada tarjetaParaHabilitarDTO = new TarjetaSolicitada();
					tarjetaParaHabilitarDTO.setNombreApellido(tarjetaDTO.getNombreCliente().trim());
					tarjetaParaHabilitarDTO.setCodigoTarjeta(tarjetaDTO.getMarcaTarjeta());
					tarjetaParaHabilitarDTO.setNroTarjeta(tarjetaDTO.getNumeroTarjeta());
					tarjetaParaHabilitarDTO.setMotivoReimpresion(motivoReimpresion);
					tarjetaParaHabilitarDTO.setDomicilio("000");
					listaTarjetasParaHabilitarDTO.add(tarjetaParaHabilitarDTO);
				}
			}
		}
		altaReimpresionTarjetasIn.setTarjetasCredito(listaTarjetasParaHabilitarDTO);
		altaReimpresionTarjetasIn.setCliente(cliente);
		
		try {
			AltaReimpresionTarjetasOut altaReimpresionOut = reimpresionTarjetasDAO.altaReimpresionTarjetaCredito(altaReimpresionTarjetasIn);
			if (SOLICITUD_EN_CURSO_WOMEN.equals(altaReimpresionOut.getCodigoTarjeta())) {
				adhesionWomenView.setErrorServicio(SOLICITUD_EN_CURSO_WOMEN);
			}
		} catch (RobotException e) {
			if (TipoError.ERROR_GENERICO.getDescripcion().equals(e.getMessage())) {
				adhesionWomenView.setErrorServicio("ERROR_ARQUITECTURA");
			} else {
				adhesionWomenView.setErrorServicio(e.getMessage());
			}
		}
		return adhesionWomenView;
	}
	
	private Boolean correspondeMostrarTarjetaNoAdherida(DatosTarjetaWomenEntity tarjetaWomen) {
		
		Boolean corresponde = Boolean.FALSE;
		
		if (StringUtils.isNotEmpty(tarjetasAptasAdhesion)) {
			String[] tarjetas = this.tarjetasAptasAdhesion.split("\\|");
			for (String tarjetaApta : tarjetas) {
				if (tarjetaApta.equals(tarjetaWomen.getMarcaTC())) {
					corresponde = Boolean.TRUE;
				}
			}		
		}
		
		return corresponde;
	}
	
	
	private Boolean revisarSiCorrespondeTarjeta(DatosTarjetaWomen tarjetaDTO, String numeroTarjetaView) {
		
		Boolean corresponde = Boolean.FALSE;
		String ultimosDigitosEntero;
		
		Boolean esAmex = MARCA_AMEX.equals(tarjetaDTO.getMarcaTarjeta()) ? Boolean.TRUE : Boolean.FALSE; 
		
		String ultimosDigitosEnmascarada = numeroTarjetaView.substring(numeroTarjetaView.length()-4, numeroTarjetaView.length());
		
		if (esAmex) {
			String numeroTarjetaSinCeroFinal = tarjetaDTO.getNumeroTarjeta().substring(0, tarjetaDTO.getNumeroTarjeta().length()-1);
			ultimosDigitosEntero = numeroTarjetaSinCeroFinal.substring(numeroTarjetaSinCeroFinal.length()-4, numeroTarjetaSinCeroFinal.length());
		} else {
			ultimosDigitosEntero = tarjetaDTO.getNumeroTarjeta().substring(tarjetaDTO.getNumeroTarjeta().length()-4, tarjetaDTO.getNumeroTarjeta().length());
		}
		
		if (ultimosDigitosEnmascarada.equals(ultimosDigitosEntero)) {
			corresponde = Boolean.TRUE;
		}
		return corresponde;
	}

}
