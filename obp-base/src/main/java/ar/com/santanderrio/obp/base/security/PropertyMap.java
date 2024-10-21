package ar.com.santanderrio.obp.base.security;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertyMap.
 */
public class PropertyMap implements InitializingBean {

	/** The env. */
	@Autowired
	private Environment env;

	/** The properties. */
	private Map<String, Object> properties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Iterator<?> it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext();) {
			Object propertySource = it.next();
			if (propertySource instanceof MapPropertySource) {
				map.putAll(((MapPropertySource) propertySource).getSource());
			}
		}
		properties = map;
	}

	/**
	 * Gets the.
	 *
	 * @param prop
	 *            the prop
	 * @return the string
	 */
	public String get(String prop) {
		if (this.properties != null) {
			Object obj = this.properties.get(prop);
			if (obj != null) {
				return obj.toString();
			}
		}
		return null;
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * Sets the properties.
	 *
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
