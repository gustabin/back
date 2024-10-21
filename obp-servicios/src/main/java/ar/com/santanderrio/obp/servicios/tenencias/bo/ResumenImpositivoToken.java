/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.bo;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ConsultaResumenTokenEntity;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;

/**
 * The Class ResumenImpositivoToken.
 */
@Component
public class ResumenImpositivoToken extends AbstractTokenEntesExternos {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResumenImpositivoToken.class);

	/**
	 * KeyStore PMC.
	 */
	private static final String JKS_PMC = "PMC";

	/** The url. */
	@Value("${PMC.URL}")
	private String pmcUrl;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos#obtenerDatosAFirmar(org.apache.cxf.jaxrs.ext.MessageContext, ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO)
	 */
	@Override
	protected String obtenerDatosAFirmar(MessageContext mc, TokenExternoDTO tokenExternoDTO) {
		ConsultaResumenTokenEntity entity = new ConsultaResumenTokenEntity(tokenExternoDTO.getCliente(),
				obtenerTipoDoc(tokenExternoDTO.getCliente().getTipoDocumento()));
		try {
			return JaxbUtils.transformarObjetoAXml(entity, "UTF-8", true, false, "");
		} catch (JAXBException e) {
			LOGGER.error("No fue posible armar el objeto firmado apartir de {}.", tokenExternoDTO, e);
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Obtener tipo doc.
	 *
	 * @param tipoDocumento
	 *            the tipo documento
	 * @return the string
	 */
	private String obtenerTipoDoc(String tipoDocumento) {
		String res = null;
		if ("N".equals(tipoDocumento) || "X".equals(tipoDocumento)) {
			res = "DNI";
		} else if ("I".equals(tipoDocumento)) {
			res = "CI";
		} else if ("E".equals(tipoDocumento)) {
			res = "LE";
		} else if ("C".equals(tipoDocumento)) {
			res = "LC";
		} else if ("P".equals(tipoDocumento)) {
			res = "PAS";
		} else {
			throw new RuntimeException("Segun el DTF si llega a este punto debe retornar error generico, REVISAR.");
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos#getKeystore()
	 */
	@Override
	protected String getKeystore() {
		return JKS_PMC;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos#getUrl()
	 */
	@Override
	protected String getUrl() {
		return pmcUrl;
	}

}
