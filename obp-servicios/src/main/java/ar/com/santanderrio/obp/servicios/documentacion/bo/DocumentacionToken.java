package ar.com.santanderrio.obp.servicios.documentacion.bo;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;

@Component
public class DocumentacionToken extends AbstractTokenEntesExternos {

    private static final String CONSUMER = "OBP";
    
    private static final String PROCESS_DEFINITION_CODE = "0";
    
    private static final String JKS = "DOCUSD";
    
    private static final String INDIVIDUO = "I";

	@Value("${DOCUMENTACION.URL}")
    private String url;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentacionToken.class);

 
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
				
        token.append(CONSUMER).append(AbstractTokenEntesExternos.PIPE);
        token.append(PROCESS_DEFINITION_CODE).append(AbstractTokenEntesExternos.PIPE);
        StringBuilder data = new StringBuilder();
        data.append(StringUtils.EMPTY).append(AbstractTokenEntesExternos.PIPE);
        data.append(StringUtils.EMPTY).append(AbstractTokenEntesExternos.PIPE);
        data.append(cuentaDolares.getNroSucursal()).append(AbstractTokenEntesExternos.PIPE);
        data.append(cuentaDolares.getNroCuentaProducto()).append(AbstractTokenEntesExternos.PIPE);

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
        datosCliente.append(cuentaDolares.getCodigoTitularidad()).append(AbstractTokenEntesExternos.PIPE);
        
        token.append(datosCliente);
        
        LOGGER.info("Datos Cliente DocumentacionToken{}: ", datosCliente.toString());
        
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

}
