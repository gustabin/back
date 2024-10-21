package ar.com.santanderrio.obp.base.log.aop.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.context.ContextHolder;

// TODO: Auto-generated Javadoc
/**
 * The Class LogAuditAspect.
 */
public abstract class BaseAuditAspect {

	/** The Constant LOGGER. */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** The inicio ms. */
	private long inicioMs;

	/**
	 * Limpia el response eliminando palabras definidas.
	 *
	 * @param response
	 *            the response
	 * @return the string
	 */
	private static String limpiar(String response) {

		String respuesta = response;

		if (ContextHolder.getContext() == null || ContextHolder.getContext().getEnvironment() == null) {
			return response;
		}

		String tag = ContextHolder.getContext().getEnvironment().getProperty("TAGS.A.ELIMINAR.ASPECTO");

		if (StringUtils.isEmpty(tag)) {
			return response;
		}
		String[] tagsAEliminar = tag.split(",");
		for (String palabraABuscar : tagsAEliminar) {

			if (!StringUtils.contains(respuesta, palabraABuscar)) {
				continue;
			}

			String start = StringUtils.substringBefore(respuesta, palabraABuscar);
			String firstEnd = StringUtils.substringAfter(respuesta, palabraABuscar);
			String coma = StringUtils.substringAfter(firstEnd, ",");
			String puntoComa = StringUtils.substringAfter(firstEnd, ";");

			String end = "]";
			if (StringUtils.isNotEmpty(coma)) {
				end = coma;
			} else {
				if (StringUtils.isNotEmpty(puntoComa)) {
					end = puntoComa;
				}
			}
			respuesta = start + end;
		}

		return respuesta;
	}

	/**
	 * Around.
	 *
	 * @param joinPoint
	 *            the join point
	 * @return the object
	 * @throws Throwable
	 *             the throwable
	 */
	@Around(value = "logAudit()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object returnValue = null;
		try {
			Object[] args = joinPoint.getArgs();
			inicioMs = System.currentTimeMillis();
			logger.info("Inicio ejecucion: " + joinPoint.getSignature().toShortString());
			if (args.length > 0) {
				int i = 0;
				for (Object object : args) {
					try {
						if (object != null) {
							String toString = limpiar(object.toString());
							logger.info("Arg[" + i + "]: " + toString);
						}
						i++;
					} catch (RuntimeException e) {
						logger.error("Error al loguear", e);
					}
				}
			}
		} catch (Throwable t) {
			System.out.println(t);
		}

		returnValue = joinPoint.proceed();

		try {
			Long endTimeOndemand = System.currentTimeMillis() - inicioMs;
			logger.info("Final ejecucion :" + joinPoint.getSignature().toShortString());
			if (returnValue != null) {
				if (!revisarSiNoAplicaLog(joinPoint.getSignature().toShortString())) {
					String toString = limpiar(returnValue.toString());
					logger.info("Objeto retornado :" + toString);
				}
			}				

			if (endTimeOndemand != null) {
				if (endTimeOndemand >= 10000) {
					logger.info("**TIME RESPUESTA > 10 SEG = " + endTimeOndemand + "ms "
							+ joinPoint.getSignature().toShortString());
				} else if (endTimeOndemand > 5000 && endTimeOndemand < 10000) {
					logger.info("**TIME RESPUESTA 5 a 10 SEG = " + endTimeOndemand + "ms "
							+ joinPoint.getSignature().toShortString());
				} else if (endTimeOndemand > 3000 && endTimeOndemand < 5000) {
					logger.info("**TIME RESPUESTA 3 a 5 SEG = " + endTimeOndemand + "ms "
							+ joinPoint.getSignature().toShortString());
				} else if (endTimeOndemand >= 0 && endTimeOndemand <= 3000) {
					logger.info("**TIME RESPUESTA 0 a 3 SEG = " + endTimeOndemand + "ms "
							+ joinPoint.getSignature().toShortString());
				}
			}
		} catch (Throwable t) {
			System.out.println(t);
		}

		return returnValue;
	}
	
	private boolean revisarSiNoAplicaLog(String ruta) {
		return ruta != null && (ruta.contains("ArchivoDeRecursosDAOImpl.leerArchivo") || ruta.contains("TarjetaCoordenadaDAOImpl.obtener"));
	}

}