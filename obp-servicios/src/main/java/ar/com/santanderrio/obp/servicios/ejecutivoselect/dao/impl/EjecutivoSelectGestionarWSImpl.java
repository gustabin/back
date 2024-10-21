package ar.com.santanderrio.obp.servicios.ejecutivoselect.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.omnicanalidad.IOmnicanalidad;

/**
 * The Class EjecutivoSelectGestionarWSImpl.
 */
@Component("ejucutivoSelectGestor")
public class EjecutivoSelectGestionarWSImpl extends GestionarWSAbstract<IOmnicanalidad> {

	/** The Constant EJECUTIVO_SELECT_CODE. */
	private static final String EJECUTIVO_SELECT_CODE = "EJECUTIVO.SELECT";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return EJECUTIVO_SELECT_CODE;
	}

}
