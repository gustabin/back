/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Esta clase representa el objeto DTO de Cliente u otros datos que son
 * independientes del metodo de autentificacion.
 *
 * @author emilio.watemberg
 * @since Nov 3, 2016.
 */
@XmlRootElement(name = "clienteDTO", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteDTO {

}
