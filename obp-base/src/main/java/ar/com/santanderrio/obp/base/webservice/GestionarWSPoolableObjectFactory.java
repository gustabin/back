/**
 * 
 */
package ar.com.santanderrio.obp.base.webservice;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

// TODO: Auto-generated Javadoc
/**
 * Clase encargada de la creacion de los servicios del tipo T.
 *
 * @author sergio.e.goldentair
 * @param <T>
 *            the generic type
 */
public class GestionarWSPoolableObjectFactory<T> extends BasePoolableObjectFactory<T> {

	/** URL. */
	private String address;

	/** Clase. */
	private Class<T> clazz;

	/** bindingId. */
	private String bindingId = null;

	/**
	 * Instantiates a new gestionar WS poolable object factory.
	 *
	 * @param address
	 *            the address
	 * @param clazz
	 *            the clazz
	 * @param bindingId
	 *            the binding id
	 */
	public GestionarWSPoolableObjectFactory(String address, Class<T> clazz, String bindingId) {
		this.address = address;
		this.clazz = clazz;
		this.bindingId = bindingId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.pool.BasePoolableObjectFactory#makeObject()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T makeObject() throws Exception {
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.setServiceClass(clazz);
		proxyFactory.setAddress(this.address);
		if (this.bindingId != null) {
			proxyFactory.setBindingId(this.bindingId);
		}
		return (T) proxyFactory.create();
	}

}
