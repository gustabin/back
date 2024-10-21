/**
 * 
 */
package ar.com.santanderrio.obp.base.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class ContextHolder.
 *
 * @author Gabriel_Vigano
 */
@Component
public class ContextHolder implements ApplicationListener<ContextRefreshedEvent> {

	/** The context. */
	@Autowired
	private static ApplicationContext context;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org.
	 * springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		setContext(event.getApplicationContext());
	}

	/**
	 * Setter para context.
	 *
	 * @param context
	 *            el nuevo context
	 */
	public static void setContext(ApplicationContext context) {
		ContextHolder.context = context;
	}

	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	public static ApplicationContext getContext() {
		return context;
	}
}