package ar.com.santanderrio.obp.servicios.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.appender.ManagerFactory;
import org.apache.logging.log4j.core.appender.db.AbstractDatabaseManager;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.logger.dao.entities.LogError.LogErrorBuilder;
import ar.com.santanderrio.obp.servicios.logger.dao.impl.LogErrorDAO;

/**
 * An {@link AbstractDatabaseManager} implementation for relational databases
 * accessed via JDBC.
 */
public final class OBPJdbcDatabaseManager extends AbstractDatabaseManager {

    /** The Constant INSTANCE. */
    private static final JdbcDatabaseManagerFactory INSTANCE = new JdbcDatabaseManagerFactory();

    /** The Constant SEPARADOR_ERROR_SERVICIO. */
    private static final String SEPARADOR_ERROR_SERVICIO = ";";

    /** The Constant LONGITUD_MAXIMA_CLASE_JAVA. */
    private static final int LONGITUD_MAXIMA_CLASE_JAVA = 100;

    /** The Constant LONGITUD_MAXIMA_DESC_JAVA. */
    private static final int LONGITUD_MAXIMA_DESC_JAVA = 3950;

    /**
     * Instantiates a new OBP jdbc database manager.
     *
     * @param name
     *            the name
     * @param bufferSize
     *            the buffer size
     */
    private OBPJdbcDatabaseManager(final String name, final int bufferSize) {
        super(name, bufferSize);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.logging.log4j.core.appender.db.AbstractDatabaseManager#
     * startupInternal()
     */
    @Override
    protected void startupInternal() throws Exception {
        // do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.logging.log4j.core.appender.db.AbstractDatabaseManager#
     * shutdownInternal()
     */
    @Override
    protected void shutdownInternal() {
        // do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.logging.log4j.core.appender.db.AbstractDatabaseManager#
     * connectAndStart()
     */
    @Override
    protected void connectAndStart() {
        // do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.logging.log4j.core.appender.db.AbstractDatabaseManager#
     * writeInternal(org.apache.logging.log4j.core.LogEvent)
     */
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.logging.log4j.core.appender.db.AbstractDatabaseManager#
     * writeInternal(org.apache.logging.log4j.core.LogEvent)
     */
    @Override
    protected void writeInternal(final LogEvent event) {
        try {
            if (!this.isRunning()) {
                throw new AppenderLoggingException("Cannot write logging event.");
            }
            ClienteManager clienteManager = getClientManagerInstance();
            if (clienteManager == null) {
                return;
            }
            RegistroSesion registroSesion = null;
            try {
                registroSesion = clienteManager.obtenerSesionParametros().getRegistroSession();
            } catch (BeanCreationException e) {
                // Evitar romper la app cuando no hay session de usuario y hay un error no
                // manejado.
                return;
            }
            LogErrorBuilder builder = new LogErrorBuilder();
            if (registroSesion != null && registroSesion.getNup() != null) {
                builder = builder.setNup(Integer.valueOf(registroSesion.getNup()));
            }
            builder = builder.setFechaError(new java.sql.Timestamp(event.getTimeMillis()));
            builder = builder.setDocumento(registroSesion != null ? registroSesion.getDni() : "");
            if (registroSesion != null && registroSesion.getFechaNacimiento() != null) {
                SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
                Date date = dt.parse(registroSesion.getFechaNacimiento());
                builder = builder.setFechaNacimiento(new java.sql.Date(date.getTime()));
            }
            String servicio = (String) (ThreadContext.get(IatxAspect.NOMBRE_SERVICIO) != null
                    ? ThreadContext.get(IatxAspect.NOMBRE_SERVICIO)
                    : "");
            ThreadContext.remove(IatxAspect.NOMBRE_SERVICIO);

            builder = builder.setSeveridad(event.getLevel() != null ? event.getLevel().toString() : null);
            String claseJava = event.getLoggerName().substring(NumberUtils.INTEGER_ZERO,
                    Math.min(LONGITUD_MAXIMA_CLASE_JAVA, event.getLoggerName().length()));
            builder = builder.setClaseJava(claseJava);
            Integer errorCode = (Integer) (ThreadContext.get(IatxAspect.ERROR_CODE) != null
                    && NumberUtils.isNumber(ThreadContext.get(IatxAspect.ERROR_CODE))
                            ? new Integer(ThreadContext.get(IatxAspect.ERROR_CODE))
                            : null);
            String errorMessage = (String) (ThreadContext.get(IatxAspect.ERROR_MESSAGE) != null
                    ? ThreadContext.get(IatxAspect.ERROR_MESSAGE)
                    : "");
            String errorSystem = (String) (ThreadContext.get(IatxAspect.ERROR_SYSTEM) != null
                    ? ThreadContext.get(IatxAspect.ERROR_SYSTEM)
                    : "");
            String trama = (String) (ThreadContext.get(IatxAspect.ERROR_TRAMA) != null
                    ? ThreadContext.get(IatxAspect.ERROR_TRAMA)
                    : "");
            ThreadContext.remove(IatxAspect.ERROR_CODE);
            ThreadContext.remove(IatxAspect.ERROR_MESSAGE);
            ThreadContext.remove(IatxAspect.ERROR_SYSTEM);
            ThreadContext.remove(IatxAspect.ERROR_TRAMA);
            // HB_ERROR_LOG.Servicio
            // HB_ERROR_LOG.DESCRIPCION_ERROR_SERVICIO (ERROR_CODE:MESSAGE:SYSTEM)
            // HB_ERROR_LOG.DESCRIPCION_JAVA (TRAMA\n TRACE)
            Boolean existeErrorEnIatx = existeErrorEnIatx(servicio, errorCode, errorMessage, errorSystem, trama);
            if (existeErrorEnIatx) {
                builder = builder.setServicio(servicio);
            }

            builder = builder.setArchivoLog(registroSesion != null ? registroSesion.getLogFile() : "");
            builder = builder.setServidor(registroSesion != null ? registroSesion.getHostName() : "");
            if (event.getThrown() != null) {
                StringWriter errors = new StringWriter();
                event.getThrown().printStackTrace(new PrintWriter(errors));
                String stackTrace = errors.toString().substring(NumberUtils.INTEGER_ZERO,
                        Math.min(LONGITUD_MAXIMA_DESC_JAVA, errors.toString().length()));
                // java.sql.SQLException: ORA-22835: Buffer too small for CLOB
                // to CHAR or BLOB to RAW conversion (actual: 5783, maximum:
                // 4000)
                if (existeErrorEnIatx) {
                    stackTrace = trama + '\n' + stackTrace;
                    stackTrace = stackTrace.substring(NumberUtils.INTEGER_ZERO,
                            Math.min(LONGITUD_MAXIMA_DESC_JAVA, stackTrace.length()));
                }

                builder = builder.setDescripcionJava(stackTrace);

                String descripcionErrorServicio = armarDescripcionErrorServicio(existeErrorEnIatx,
                        event.getThrown().getClass().getCanonicalName(), errorCode, errorMessage, errorSystem);
                builder = builder.setDescripcionErrorServicio(
                        descripcionErrorServicio != null ? descripcionErrorServicio : null);
            }
            LogErrorDAO logErrorDAO = this.getLogErrorDAOInstance();
            if (logErrorDAO == null) {
                return;
            }
            logErrorDAO.agregar(builder.build());

        } catch (ParseException e) {
            throw new AppenderLoggingException(
                    "Failed to insert record for log event in JDBC manager: " + e.getMessage(), e);
        }
    }

    /**
     * Retorna true si todos los elementos son distintos de nulos y distintos de
     * cadena vacia.
     *
     * @param servicio
     *            the servicio
     * @param errorCode
     *            the error code
     * @param errorMessage
     *            the error message
     * @param errorSystem
     *            the error system
     * @param trama
     *            the trama
     * @return the boolean
     */
    private Boolean existeErrorEnIatx(String servicio, Integer errorCode, String errorMessage, String errorSystem,
            String trama) {
        if (StringUtils.isEmpty(servicio) || errorCode == null || StringUtils.isEmpty(errorMessage)
                || StringUtils.isEmpty(errorSystem) || StringUtils.isEmpty(trama))
            return Boolean.FALSE;

        return Boolean.TRUE;
    }

    /**
     * Armar descripcion error servicio.
     *
     * @param existeErrorEnIatx
     *            the existe error en iatx
     * @param throwableStrRep
     *            the throwable str rep
     * @param errorCode
     *            the error code
     * @param errorMessage
     *            the error message
     * @param errorSystem
     *            the error system
     * @return the string
     */
    private String armarDescripcionErrorServicio(Boolean existeErrorEnIatx, String throwableStrRep, Integer errorCode,
            String errorMessage, String errorSystem) {
        if (existeErrorEnIatx) {
            StringBuilder descripcionErrorServicio = new StringBuilder();
            descripcionErrorServicio.append(errorCode).append(SEPARADOR_ERROR_SERVICIO).append(errorSystem)
                    .append(SEPARADOR_ERROR_SERVICIO).append(errorMessage);

            return descripcionErrorServicio.toString().substring(NumberUtils.INTEGER_ZERO,
                    Math.min(150, descripcionErrorServicio.length()));
        } else {
            if (throwableStrRep != null) {
                return throwableStrRep.substring(NumberUtils.INTEGER_ZERO, Math.min(150, throwableStrRep.length()));
            }
        }

        return null;
    }

    /**
     * Gets the bean.
     *
     * @param <T>
     *            the generic type
     * @param requiredType
     *            the required type
     * @return the bean
     */
    private <T> T getBean(Class<T> requiredType) {
        ApplicationContext contexto = ContextHolder.getContext();

        if (contexto != null && contexto.getAutowireCapableBeanFactory() != null
                && contexto.getBean(requiredType) != null) {
            return contexto.getAutowireCapableBeanFactory().getBean(requiredType);
        }
        LOGGER.info("No se encontro instancia para el bean {}", requiredType);
        return null;
    }

    /**
     * Gets the client manager instance.
     *
     * @return the client manager instance
     */
    private ClienteManager getClientManagerInstance() {
        return this.getBean(ClienteManager.class);
    }

    /**
     * Gets the log error DAO instance.
     *
     * @return the log error DAO instance
     */
    private LogErrorDAO getLogErrorDAOInstance() {
        return this.getBean(LogErrorDAO.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.logging.log4j.core.appender.db.AbstractDatabaseManager#
     * commitAndClose()
     */
    @Override
    protected void commitAndClose() {
        // do nothing
    }

    /**
     * Creates a JDBC manager for use within the {@link JdbcAppender}, or returns a
     * suitable one if it already exists.
     *
     * @param name
     *            The name of the manager, which should include connection details
     *            and hashed passwords where possible.
     * @return a new or existing JDBC manager as applicable.
     */
    public static OBPJdbcDatabaseManager getJDBCDatabaseManager(final String name) {

        return AbstractDatabaseManager.getManager(name, new FactoryData(), getFactory());
    }

    /**
     * Gets the factory.
     *
     * @return the factory
     */
    private static JdbcDatabaseManagerFactory getFactory() {
        return INSTANCE;
    }

    /**
     * Encapsulates data that {@link JdbcDatabaseManagerFactory} uses to create
     * managers.
     */
    private static final class FactoryData extends AbstractDatabaseManager.AbstractFactoryData {

        /**
         * Instantiates a new factory data.
         */
        protected FactoryData() {
            // NO debe realizar buffering, ya que levanta datos del contexto.
            super(NumberUtils.INTEGER_ZERO);

        }
    }

    /**
     * Creates managers.
     */
    private static final class JdbcDatabaseManagerFactory
            implements ManagerFactory<OBPJdbcDatabaseManager, FactoryData> {

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.apache.logging.log4j.core.appender.ManagerFactory#createManager(java.lang
         * .String, java.lang.Object)
         */
        @Override
        public OBPJdbcDatabaseManager createManager(final String name, final FactoryData data) {

            return new OBPJdbcDatabaseManager(name, data.getBufferSize());
        }
    }
}
