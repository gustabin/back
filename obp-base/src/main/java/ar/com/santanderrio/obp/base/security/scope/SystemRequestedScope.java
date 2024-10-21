package ar.com.santanderrio.obp.base.security.scope;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import ar.com.santanderrio.obp.base.security.aop.aspect.TargetSystemAspect;

// TODO: Auto-generated Javadoc
/**
 * Este scope funciona en conjunto con {@link TargetSystemAspect} Limpia los
 * beans al salir del metodo.
 *
 * @author enrico.agnoli
 */
public class SystemRequestedScope implements Scope {

	/**
	 * This map contains for each bean name or ID the created object. The
	 * objects are created with a spring object factory. The map is ThreadLocal,
	 * so the bean are defined only in the current thread!
	 */

	private final ThreadLocalScope threadLocalObjectMap = new ThreadLocalScope();

	/**
	 * The Class ThreadLocalScope.
	 */
	static class ThreadLocalScope extends ThreadLocal<Map<String, Object>> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.ThreadLocal#initialValue()
		 */
		@Override
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>();
		};

	}

	/** {@inheritDoc} */
	public Object get(final String beanName, final ObjectFactory<?> theObjectFactory) {
		Object object = threadLocalObjectMap.get().get(beanName);
		if (null == object) {
			object = theObjectFactory.getObject();
			threadLocalObjectMap.get().put(beanName, object);
		}
		return object;
	}

	/** {@inheritDoc} */
	public String getConversationId() {
		// In this case, it returns the thread name.
		return Thread.currentThread().getName();
	}

	/** {@inheritDoc} */
	public void registerDestructionCallback(final String beanName, final Runnable theCallback) {
		// nothing to do ... this is optional and not required
	}

	/** {@inheritDoc} */
	public Object remove(final String beanName) {
		return threadLocalObjectMap.get().remove(beanName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.config.Scope#resolveContextualObject(
	 * java.lang.String)
	 */
	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	/**
	 * Invoke this method to cleanUp the ThreadLocal beans. This call is
	 * required since in case of run in a thread pool, the thread will never be
	 * removed and the threadLocal variables would be shared between two
	 * different executions!
	 */
	public void cleanUpThreadScopedBeans() {
		threadLocalObjectMap.remove();
	}
}