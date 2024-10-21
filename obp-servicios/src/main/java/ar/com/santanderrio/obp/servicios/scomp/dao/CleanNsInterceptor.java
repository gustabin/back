/**
 * 
 */
package ar.com.santanderrio.obp.servicios.scomp.dao;

import java.util.Collections;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.transform.TransformInInterceptor;
import org.apache.cxf.interceptor.transform.TransformOutInterceptor;
import org.apache.cxf.message.Message;

/**
 * Interceptor para los mensajes cxf para ayudar a reemplazar namespaces
 * complejos.
 * 
 * @author sergio.e.goldentair
 *
 */
public final class CleanNsInterceptor {

	/**
	 * Limpia el buffer del namespace mensajes salientes.
	 *
	 * @param keyMap
	 *            the key map
	 * @param valueMap
	 *            the value map
	 * @return interceptor
	 */
	public static Interceptor<? extends Message> cleanNsOut(String keyMap, String valueMap) {
		TransformOutInterceptor transformOutInterceptor = new TransformOutInterceptor();
		transformOutInterceptor.setOutTransformElements(Collections.singletonMap(keyMap, valueMap));
		return transformOutInterceptor;
	}

	/**
	 * Limpia el buffer del namespace mensajes entrantes.
	 *
	 * @param keyMap
	 *            the key map
	 * @param valueMap
	 *            the value map
	 * @return interceptor
	 */
	public static Interceptor<? extends Message> cleanNsIn(String keyMap, String valueMap) {
		TransformInInterceptor transformInInterceptor = new TransformInInterceptor();
		transformInInterceptor.setInTransformElements(Collections.singletonMap(keyMap, valueMap));
		return transformInInterceptor;
	}
}
