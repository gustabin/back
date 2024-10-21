package ar.com.santanderrio.obp.servicios.contratospd.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.contratosvigentes.dao.ContratosVigentesDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.perfil.entities.ProductoContratoEntity;
import ar.com.santanderrio.obp.servicios.segmento.dao.SegmentoSuscripcionesDAO;
import ar.com.santanderrio.obp.servicios.segmento.dto.CajaSeguridadEntity;
import ar.com.santanderrio.obp.servicios.segmento.dto.SubscriptionRequest;
import ar.com.santanderrio.obp.servicios.segmento.dto.SubscriptionResponse;
import ar.com.santanderrio.obp.servicios.segmento.dto.SubscriptionResponseDTO;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;

@Component
public class ContratosPdToken extends AbstractTokenEntesExternos {

    private static final String CONSUMER = "OBP";
    
    private static final String PROCESS_DEFINITION_CODE = "2020";
    
    private static final String JKS = "DOCUSD"; //bcorio
    
    private static final String INDIVIDUO = "I";
    
    private static final String CODIGO_PRODUCTO_PAQUETE = "90";
    
    private static final String CODIGO_TITULARIDAD = "TI";

    private static final String CODIGO_COTITULARIDAD = "CT";
    
    private static final String ESTADO_ACTIVO = "ACTIVE";
    
    @Value("${LISTCONTRATOS.URL}") //Url microfrontend LIST CONTRATOS
    private String url;
    
    @Value("${CONTRATO.WOMEN}")
    private String contratoWomen;
    
    @Value("${CONTRATO.SORPRESA}")
    private String contratoSorpresa;
    
    @Value("${CONTRATO.CAJASEGURIDAD}")
    private String contratoCajaSeguridad;
    
	@Autowired
	private SesionParametros sesionParametros;
	
    @Autowired
    private EstadisticaManager estadisticaManager;
	
	@Autowired
	private ContratosVigentesDAO contratosVigentesDAO;
	
	@Autowired
	private SegmentoSuscripcionesDAO segmentoSuscripcionesDAO;	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContratosPdToken.class);

 
	@Override
	protected String obtenerDatosAFirmar(MessageContext mc, TokenExternoDTO tokenExternoDTO) {
        Cliente cliente = tokenExternoDTO.getCliente();

		StringBuilder token = new StringBuilder();

		Cuenta cuentaDolares = new Cuenta();
		
		if (cliente.getCuentas() == null) {
			return "";
		}
	 
		
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (StringUtils.isNotEmpty(cuenta.getTipoCuentaSinUnificar()) && StringUtils.isNotEmpty(cuenta.getTipoCuentaSinUnificarDls())) {
				if (TipoCuenta.CAJA_AHORRO_DOLARES.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) || 
					TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) ||
					TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificarDls()))) {
					cuentaDolares = cuenta;
					break;
				}
			}
		}
		
		List<ProductoContratoEntity> listaProductosNormativo = new ArrayList<ProductoContratoEntity>();

		try {
			listaProductosNormativo = contratosVigentesDAO.obtenerProductosContratos();
		} catch (DAOException e) {
			return "";
		}
		
		
        token.append(CONSUMER).append(AbstractTokenEntesExternos.PIPE);
        token.append(PROCESS_DEFINITION_CODE).append(AbstractTokenEntesExternos.PIPE);
        StringBuilder data = new StringBuilder();
        if (sesionParametros.getOperadorEjecutivo() != null) { 
	        data.append(sesionParametros.getOperadorEjecutivo().getIdEjecutivo()).append(AbstractTokenEntesExternos.PIPE);
	        data.append(sesionParametros.getOperadorEjecutivo().getIdEjecutivo()).append(AbstractTokenEntesExternos.PIPE);
        }
        
        if (cuentaDolares != null) {
	        data.append(cuentaDolares.getNroSucursal()).append(AbstractTokenEntesExternos.PIPE);
	        data.append(cuentaDolares.getNroCuentaProducto()).append(AbstractTokenEntesExternos.PIPE);
        }
        
        token.append(data);
        StringBuilder datosCliente = new StringBuilder();
        datosCliente.append(cliente.getApellido1()).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(cliente.getApellido2()).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(cliente.getTipoDocumento()).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(cliente.getTipoDocCnsDocXNup()).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(INDIVIDUO.equals(cliente.getTipoPersona()) ? "F" : "J").append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(cliente.getSexo()).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(cliente.getFechaNacimiento()).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(cliente.getNombre()).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(cliente.getNroDocCnsDocXNup()).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(cliente.getDni()).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(cliente.getNup()).append(AbstractTokenEntesExternos.PIPE);
        if (cuentaDolares != null) {
        	datosCliente.append(cuentaDolares.getCodigoTitularidad()).append(AbstractTokenEntesExternos.PIPE);
        }
        comprararProductosClienteConDatabase(listaProductosNormativo, cliente, datosCliente);
        revisarSiClienteTieneCajaSeguridad(cliente, datosCliente);
        revisarSuscripcionesWomenSorpresa(cliente, datosCliente);      
        token.append(datosCliente);
        return token.toString();
	}


	@Override
	protected String getUrl() {
		return this.url;
	}
	
	public Respuesta<TokenDTO> obtenerHash(SesionCliente sesionCliente) {
        TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(sesionCliente.getCliente());
        Respuesta<TokenDTO> respuesta = this.armarToken(null, tokenExternoDTO);
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
        	respuesta.getRespuesta().setUrl(this.url);
        }
        
        return respuesta;
      
    }


	@Override
	protected String getKeystore() {
		return JKS;
	}
	
	private void comprararProductosClienteConDatabase(List<ProductoContratoEntity> listaProductosNormativo, Cliente cliente, StringBuilder datosCliente) {
		
		List<ProductoContratoEntity> listaProductosAMostrar = new ArrayList<ProductoContratoEntity>();
		
		if (!listaProductosNormativo.isEmpty()) {
			revisarListas(cliente.getCuentas(), listaProductosNormativo, listaProductosAMostrar);
			revisarListas(cliente.getCuentasTitBPRepatriacion(), listaProductosNormativo, listaProductosAMostrar);
			revisarListas(cliente.getCuentasTitRtlRepatriacion(), listaProductosNormativo, listaProductosAMostrar);
			revisarListas(cliente.getCuentasRetail(), listaProductosNormativo, listaProductosAMostrar);
        	
			revisarListasBancaPrivada(cliente.getCuentasBancaPrivada(), listaProductosNormativo, listaProductosAMostrar);
        }
        
		//Ordenamos lista de productos
        Collections.sort(listaProductosAMostrar);
        
        //Agregamos el primer producto de la lista
        agregarADatosCliente(datosCliente, listaProductosAMostrar.get(0));
        
        //A partir del segundo, si codigoProducto o codigoSubproducto son distintos al anterior, es un producto nuevo y lo agregamos tambien
		for (int i = 1; i < listaProductosAMostrar.size(); i++) {
			if (!listaProductosAMostrar.get(i).getCodigoProducto().equals(listaProductosAMostrar.get(i-1).getCodigoProducto()) || 
				!listaProductosAMostrar.get(i).getCodigoSubproducto().equals(listaProductosAMostrar.get(i-1).getCodigoSubproducto())) {
		        agregarADatosCliente(datosCliente, listaProductosAMostrar.get(i));
			}
		}		
	}
	
	private void revisarListas(List<Cuenta> listaCuentas, List<ProductoContratoEntity> listaProductosNormativo, List<ProductoContratoEntity> listaProductosAMostrar) {
		
    	for (Cuenta cuenta : listaCuentas) {
    		for (ProductoContratoEntity producto : listaProductosNormativo) {
    			if (((CODIGO_PRODUCTO_PAQUETE.equals(producto.getCodigoProducto()) && cuenta.getCodigoPaquete().trim().equals(producto.getCodigoSubproducto())) ||
    				cuenta.getProductoAltair().equals(producto.getCodigoProducto()) && cuenta.getSubproductoAltair().trim().equals(producto.getCodigoSubproducto())) &&
    				(CODIGO_TITULARIDAD.equals(cuenta.getCodigoTitularidad()) || CODIGO_COTITULARIDAD.equals(cuenta.getCodigoTitularidad()))){
    				agregarProductoALista(listaProductosAMostrar, producto);
    			}
    		}
    	}
		
	}
	
	private void revisarListasBancaPrivada(List<CuentaBancaPrivada> listaCuentasPrivadas, List<ProductoContratoEntity> listaProductosNormativo, List<ProductoContratoEntity> listaProductosAMostrar) {
		
    	for (CuentaBancaPrivada cuentaPrivada : listaCuentasPrivadas) {
    		for (ProductoContratoEntity producto : listaProductosNormativo) {
    			if (cuentaPrivada.getCuentaOperativa().getProductoAltair().equals(producto.getCodigoProducto()) && 
    				cuentaPrivada.getCuentaOperativa().getSubproductoAltair().trim().equals(producto.getCodigoSubproducto())) {
    				agregarProductoALista(listaProductosAMostrar, producto);

        			}
    			
    			if (cuentaPrivada.getCuentaTitulo().getProductoAltair().equals(producto.getCodigoProducto()) && 
    				cuentaPrivada.getCuentaTitulo().getSubproductoAltair().trim().equals(producto.getCodigoSubproducto())) {
    				agregarProductoALista(listaProductosAMostrar, producto);
    			}
    		}
    	}
		
	}
	
	private void agregarProductoALista (List<ProductoContratoEntity> listaProductosAMostrar, ProductoContratoEntity producto) {
		ProductoContratoEntity productoAMostrar = new ProductoContratoEntity();
		productoAMostrar.setCodigoProducto(producto.getCodigoProducto());
		productoAMostrar.setCodigoSubproducto(producto.getCodigoSubproducto());
		productoAMostrar.setLinkContrato(producto.getLinkContrato());
		productoAMostrar.setNombreProducto(producto.getNombreProducto());
		listaProductosAMostrar.add(productoAMostrar);
	}
	
	private void agregarADatosCliente (StringBuilder datosCliente, ProductoContratoEntity producto) {
        datosCliente.append(producto.getCodigoProducto()).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(producto.getCodigoSubproducto()).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(ISBANStringUtils.formateoStringPrimeraLetraMayuscula(producto.getNombreProducto())).append(AbstractTokenEntesExternos.PIPE);
        datosCliente.append(producto.getLinkContrato()).append(AbstractTokenEntesExternos.PIPE);		
	}
	
	private void revisarSuscripcionesWomenSorpresa(Cliente cliente, StringBuilder datosCliente) {
		
		SubscriptionRequest request = new SubscriptionRequest();
		Integer nup = Integer.parseInt(cliente.getNup());
		request.setNup(nup);
		
		boolean tieneWomen = false;
		boolean tieneSorpresa = false;
		
		try {
			SubscriptionResponse suscripcionRespuesta = segmentoSuscripcionesDAO.consultarEstadoSuscripciones(request);
			
			for (SubscriptionResponseDTO suscripcion : suscripcionRespuesta.getSubscriptions()) {
				
				if ("WOMEN".equals(suscripcion.getSegment()) && ESTADO_ACTIVO.equals(suscripcion.getStatus())) {
					tieneWomen = true;
				}
				
				if ("SORPRESA".equals(suscripcion.getSegment()) && ESTADO_ACTIVO.equals(suscripcion.getStatus())) {
					tieneSorpresa = true;
				}				
			}
			
			if (tieneWomen) {
		        datosCliente.append("NA").append(AbstractTokenEntesExternos.PIPE);
		        datosCliente.append("NA").append(AbstractTokenEntesExternos.PIPE);
				datosCliente.append("Tarjeta Women").append(AbstractTokenEntesExternos.PIPE);
				datosCliente.append(contratoWomen).append(AbstractTokenEntesExternos.PIPE);
			}
			
			if (tieneSorpresa) {
		        datosCliente.append("NA").append(AbstractTokenEntesExternos.PIPE);
		        datosCliente.append("NA").append(AbstractTokenEntesExternos.PIPE);
				datosCliente.append("Suscripcion Sorpresa").append(AbstractTokenEntesExternos.PIPE);
				datosCliente.append(contratoSorpresa).append(AbstractTokenEntesExternos.PIPE);
			}
			estadisticaManager.add(EstadisticasConstants.CONSULTA_API_ESTADO_SORPRESA_WOMEN, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			
		} catch (DAOException e1) {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_API_ESTADO_SORPRESA_WOMEN, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			LOGGER.error("Error al obtener estado de suscripciones Women y Sorpresa");
		}
		
		
	}
	
	private void revisarSiClienteTieneCajaSeguridad(Cliente cliente, StringBuilder datosCliente) {
		
		CajaSeguridadEntity cajaSeguridadEntity = new CajaSeguridadEntity();
		
		try {
			cajaSeguridadEntity = segmentoSuscripcionesDAO.consultarCajasSeguridad(cliente);
			if (!cajaSeguridadEntity.isHayError() && cajaSeguridadEntity.getCantidadRegistros() >= 1) {
				estadisticaManager.add(EstadisticasConstants.CONSULTA_SERVICIO_CAJAS_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		        datosCliente.append("NA").append(AbstractTokenEntesExternos.PIPE);
		        datosCliente.append("NA").append(AbstractTokenEntesExternos.PIPE);
				datosCliente.append("Caja de Seguridad").append(AbstractTokenEntesExternos.PIPE);
				datosCliente.append(contratoCajaSeguridad).append(AbstractTokenEntesExternos.PIPE);
			}
		} catch (DAOException e) {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_SERVICIO_CAJAS_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			LOGGER.error("Error al obtener cajas de seguridad");
		}
		
	}
	
}
