/*
 * 
 */
package ar.com.santanderrio.obp.servicios.seguridad;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.prepost.PreInvocationAttribute;
import org.springframework.security.access.prepost.PrePostAnnotationSecurityMetadataSource;
import org.springframework.security.access.prepost.PrePostInvocationAttributeFactory;
import org.springframework.util.ClassUtils;

import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;

/**
 * The Class CustomPrePostAnnotationSecurityMetadataSource.
 */
public class CustomPrePostAnnotationSecurityMetadataSource extends PrePostAnnotationSecurityMetadataSource {

	/** The attribute factory. */
	private final PrePostInvocationAttributeFactory attributeFactory;

	/**
	 * Instantiates a new custom pre post annotation security metadata source.
	 *
	 * @param attributeFactory
	 *            the attribute factory
	 */
	public CustomPrePostAnnotationSecurityMetadataSource(PrePostInvocationAttributeFactory attributeFactory) {
		super(attributeFactory);

		this.attributeFactory = attributeFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.access.prepost.
	 * PrePostAnnotationSecurityMetadataSource#getAttributes(java.lang.reflect.
	 * Method, java.lang.Class)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Method method, Class<?> targetClass) {
		if (method.getDeclaringClass() == Object.class) {
			return Collections.emptyList();
		}

		CustomPreAuthorize preAuthorize = findAnnotation(method, targetClass, CustomPreAuthorize.class);

		if (preAuthorize == null) {
			// There is no meta-data so return
			logger.trace("No expression annotations found");
			return Collections.emptyList();
		}

		AccionController[] preAuthorizeValue = preAuthorize.value();
		String descripciones = obtenerDescripciones(preAuthorizeValue);
		String preAuthorizeAttribute = "hasPermission(#user, '" + descripciones + "')";

		PreInvocationAttribute pre = attributeFactory.createPreInvocationAttribute(null, null, preAuthorizeAttribute);

		ArrayList<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>(2);

		if (pre != null) {
			attrs.add(pre);
		}

		attrs.trimToSize();

		return attrs;
	}

	/**
	 * Obtener descripciones.
	 *
	 * @param preAuthorizeValue
	 *            the pre authorize value
	 * @return the string
	 */
	private String obtenerDescripciones(AccionController[] preAuthorizeValue) {
		StringBuilder descripcion = new StringBuilder();
		for (AccionController accionController : preAuthorizeValue) {
			descripcion.append(",").append(accionController.getDescripcion());
		}
		return descripcion.toString().replaceFirst(",", "");
	}

	/**
	 * See
	 * {@link org.springframework.security.access.method.AbstractFallbackMethodSecurityMetadataSource#getAttributes(Method, Class)}
	 * for the logic of this method. The ordering here is slightly different in
	 * that we consider method-specific annotations on an interface before
	 * class-level ones.
	 *
	 * @param <A>
	 *            the generic type
	 * @param method
	 *            the method
	 * @param targetClass
	 *            the target class
	 * @param annotationClass
	 *            the annotation class
	 * @return the a
	 */
	private <A extends Annotation> A findAnnotation(Method method, Class<?> targetClass, Class<A> annotationClass) {
		// The method may be on an interface, but we need attributes from the
		// target
		// class.
		// If the target class is null, the method will be unchanged.
		Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
		A annotation = AnnotationUtils.findAnnotation(specificMethod, annotationClass);

		if (annotation != null) {
			logger.debug(annotation + " found on specific method: " + specificMethod);
			return annotation;
		}

		// Check the original (e.g. interface) method
		if (specificMethod != method) {
			annotation = AnnotationUtils.findAnnotation(method, annotationClass);

			if (annotation != null) {
				logger.debug(annotation + " found on: " + method);
				return annotation;
			}
		}

		// Check the class-level (note declaringClass, not targetClass, which
		// may not
		// actually implement the method)
		annotation = AnnotationUtils.findAnnotation(specificMethod.getDeclaringClass(), annotationClass);

		if (annotation != null) {
			logger.debug(annotation + " found on: " + specificMethod.getDeclaringClass().getName());
			return annotation;
		}

		return null;
	}

}
