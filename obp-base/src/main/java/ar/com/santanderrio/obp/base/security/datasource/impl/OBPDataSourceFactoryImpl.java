package ar.com.santanderrio.obp.base.security.datasource.impl;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

import org.apache.tomcat.jdbc.pool.DataSourceFactory;

/**
 * The Class OBPDataSourceFactoryImpl.
 */
public class OBPDataSourceFactoryImpl extends DataSourceFactory {

	/**
	 * Instantiates a new OBP data source factory impl.
	 */
	public OBPDataSourceFactoryImpl() {
	}

	/**
	 * Gets the object instance.
	 *
	 * @param obj the obj
	 * @param name the name
	 * @param nameCtx the name ctx
	 * @param environment the environment
	 * @return the object instance
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object getObjectInstance(final Object obj, final Name name, final Context nameCtx,
			final Hashtable environment) throws Exception {
		if (obj instanceof Reference && name != null) {
			configurar((Reference) obj, name.toString());
		}
		return super.getObjectInstance(obj, name, nameCtx, environment);
	}

	/**
	 * Configurar.
	 *
	 * @param ref the ref
	 * @param name the name
	 * @throws Exception the exception
	 */
	private void configurar(final Reference ref, String name) throws Exception {
		int usernameIdx = obtenerRefTypeIdx("username", ref);
		int passwordIdx = obtenerRefTypeIdx("password", ref);
		int urlIdx = obtenerRefTypeIdx("url", ref);
		Map<String, String> env = System.getenv();
		ref.remove(usernameIdx);
		String pass = null;
		String user = null;
		if ("seguridadbd".equals(name)) {
			user = env.get("db.seguridadbd.user");
			pass = env.get("db.seguridadbd.pass");
		} else {
			String secId = env.get("db." + name + ".id");
			if (secId != null) {
				secId = secId.replace("\"", "");
				user = env.get("sec.id." + secId + ".user");
				pass = env.get("sec.id." + secId + ".pass");
			}
		}
		ref.add(usernameIdx, new StringRefAddr("username", user));
		ref.remove(passwordIdx);
		ref.add(passwordIdx, new StringRefAddr("password", pass));
		ref.remove(urlIdx);
		ref.add(urlIdx, new StringRefAddr("url", env.get("db." + name + ".url")));
	}

	/**
	 * Obtener ref type idx.
	 *
	 * @param addrType the addr type
	 * @param ref the ref
	 * @return the int
	 * @throws Exception the exception
	 */
	private int obtenerRefTypeIdx(final String addrType, final Reference ref) throws Exception {
		final Enumeration<RefAddr> enu = ref.getAll();
		for (int i = 0; enu.hasMoreElements(); i++) {
			final RefAddr addr = (RefAddr) enu.nextElement();
			if (addr.getType().compareTo(addrType) == 0) {
				return i;
			}
		}
		throw new Exception("Error: no se encuentra " + addrType);
	}

}
