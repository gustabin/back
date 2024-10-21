/*
 * 
 */
package ar.com.santanderrio.obp.servicios.obe.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.obe.manager.ObeManager;

/**
 * La clase ObeSEIImpl.
 */
@Component("obeSEI")
public class ObeSEIImpl implements ObeSEI {

	/** The seguro manager. */
	@Autowired
	@Qualifier("obeManager")
	private ObeManager obeManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.seguros.sei.SeguroSEI#
	 * obtenerTokenEncriptado()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Respuesta obtenerTokenEncriptado(org.apache.cxf.jaxrs.ext.MessageContext mc) {
		return obeManager.obtenerTokenEncriptado(mc);
	}
}
