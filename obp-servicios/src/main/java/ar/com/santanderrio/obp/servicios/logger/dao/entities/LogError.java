/*
 * 
 */
package ar.com.santanderrio.obp.servicios.logger.dao.entities;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * The Class LogError.
 */
public class LogError {

    /** The nup. */
    private Integer nup;
    
    /** The fecha error. */
    private Timestamp fechaError;
    
    /** The documento. */
    private String documento;
    
    /** The fecha nacimiento. */
    private Date fechaNacimiento;
    
    /** The servicio. */
    private String servicio;
    
    /** The severidad. */
    private String severidad;
    
    /** The descripcion error servicio. */
    private String descripcionErrorServicio;
    
    /** The clase java. */
    private String claseJava;
    
    /** The archivo log. */
    private String archivoLog;
    
    /** The servidor. */
    private String servidor;
    
    /** The descripcion java. */
    private String descripcionJava;
    
    /**
     * Instantiates a new log error.
     *
     * @param builder the builder
     */
    private LogError(LogErrorBuilder builder) {
        this.nup = builder.nup;
        this.fechaError = builder.fechaError;
        this.documento = builder.documento;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.servicio = builder.servicio;
        this.severidad = builder.severidad;
        this.descripcionErrorServicio = builder.descripcionErrorServicio;
        this.claseJava = builder.claseJava;
        this.archivoLog = builder.archivoLog;
        this.servidor = builder.servidor;
        this.descripcionJava = builder.descripcionJava;
    }

    /**
     * Gets the nup.
     *
     * @return the nup
     */
    public Object getNup() {
        return this.nup;
    }

    /**
     * Gets the fecha error.
     *
     * @return the fecha error
     */
    public Object getFechaError() {
        return this.fechaError;
    }

    /**
     * Gets the documento.
     *
     * @return the documento
     */
    public String getDocumento() {
        return this.documento;
    }

    /**
     * Gets the fecha nacimiento.
     *
     * @return the fecha nacimiento
     */
    public Object getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    /**
     * Gets the servicio.
     *
     * @return the servicio
     */
    public Object getServicio() {
        return this.servicio;
    }

    /**
     * Gets the severidad.
     *
     * @return the severidad
     */
    public Object getSeveridad() {
        return this.severidad;
    }

    /**
     * Gets the descripcion error servicio.
     *
     * @return the descripcion error servicio
     */
    public Object getDescripcionErrorServicio() {
        return this.descripcionErrorServicio;
    }

    /**
     * Gets the clase java.
     *
     * @return the clase java
     */
    public Object getClaseJava() {
        return this.claseJava;
    }

    /**
     * Gets the archivo log.
     *
     * @return the archivo log
     */
    public Object getArchivoLog() {
        return this.archivoLog;
    }

    /**
     * Gets the servidor.
     *
     * @return the servidor
     */
    public Object getServidor() {
        return this.servidor;
    }

    /**
     * Gets the descripcion java.
     *
     * @return the descripcion java
     */
    public Object getDescripcionJava() {
        return this.descripcionJava;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("nup", nup)
                .append("fechaError", fechaError)
                .append("documento", documento)
                .append("fechaNacimiento", fechaNacimiento)
                .append("servicio", servicio)
                .append("severidad", severidad)
                .append("descripcionErrorServicio", descripcionErrorServicio)
                .append("claseJava", claseJava)
                .append("archivoLog", archivoLog)
                .append("servidor", servidor).toString();
    }



    /**
     * The Class LogErrorBuilder.
     */
    //Builder Class
    public static class LogErrorBuilder {
        
        /** The nup. */
        private Integer nup = NumberUtils.INTEGER_ZERO;
        
        /** The fecha error. */
        private Timestamp fechaError;
        
        /** The documento. */
        private String documento;
        
        /** The fecha nacimiento. */
        private Date fechaNacimiento = null;
        
        /** The servicio. */
        private String servicio;
        
        /** The severidad. */
        private String severidad;
        
        /** The descripcion error servicio. */
        private String descripcionErrorServicio;
        
        /** The clase java. */
        private String claseJava;
        
        /** The archivo log. */
        private String archivoLog;
        
        /** The servidor. */
        private String servidor;
        
        /** The descripcion java. */
        private String descripcionJava;
        
        /**
         * Sets the nup.
         *
         * @param nup the nup
         * @return the log error builder
         */
        public LogErrorBuilder setNup(Integer nup) {
            this.nup = nup;
            return this;
        }
        
        /**
         * Sets the fecha error.
         *
         * @param fechaError the fecha error
         * @return the log error builder
         */
        public LogErrorBuilder setFechaError(Timestamp fechaError) {
            this.fechaError = fechaError;
            return this;
        }
        
        /**
         * Sets the documento.
         *
         * @param documento the documento
         * @return the log error builder
         */
        public LogErrorBuilder setDocumento(String documento) {
            this.documento = documento;
            return this;
        }
        
        /**
         * Sets the fecha nacimiento.
         *
         * @param fechaNacimiento the fecha nacimiento
         * @return the log error builder
         */
        public LogErrorBuilder setFechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }
        
        /**
         * Sets the servicio.
         *
         * @param servicio the servicio
         * @return the log error builder
         */
        public LogErrorBuilder setServicio(String servicio) {
            this.servicio = servicio;
            return this;
        }
        
        /**
         * Sets the severidad.
         *
         * @param severidad the severidad
         * @return the log error builder
         */
        public LogErrorBuilder setSeveridad(String severidad) {
            this.severidad = severidad;
            return this;
        }
        
        /**
         * Sets the descripcion error servicio.
         *
         * @param descripcionErrorServicio the descripcion error servicio
         * @return the log error builder
         */
        public LogErrorBuilder setDescripcionErrorServicio(String descripcionErrorServicio) {
            this.descripcionErrorServicio = descripcionErrorServicio;
            return this;
        }
        
        /**
         * Sets the clase java.
         *
         * @param claseJava the clase java
         * @return the log error builder
         */
        public LogErrorBuilder setClaseJava(String claseJava) {
            this.claseJava = claseJava;
            return this;
        }
        
        /**
         * Sets the archivo log.
         *
         * @param archivoLog the archivo log
         * @return the log error builder
         */
        public LogErrorBuilder setArchivoLog(String archivoLog) {
            this.archivoLog = archivoLog;
            return this;
        }
        
        /**
         * Sets the servidor.
         *
         * @param servidor the servidor
         * @return the log error builder
         */
        public LogErrorBuilder setServidor(String servidor) {
            this.servidor = servidor;
            return this;
        }
        
        /**
         * Sets the descripcion java.
         *
         * @param descripcionJava the descripcion java
         * @return the log error builder
         */
        public LogErrorBuilder setDescripcionJava(String descripcionJava) {
            this.descripcionJava = descripcionJava;
            return this;
        }
        
        /**
         * Builds the.
         *
         * @return the log error
         */
        public LogError build(){
            return new LogError(this);
        }
            
    }

}
