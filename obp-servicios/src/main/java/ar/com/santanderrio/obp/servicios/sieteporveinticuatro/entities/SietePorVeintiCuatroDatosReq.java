/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda.CNSAgendaDatosReq;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.modificacion.ModificacionTADatosReq;

/**
 * The Class SietePorVeintiCuatroDatosReq.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ CNSAgendaDatosReq.class, ModificacionTADatosReq.class })
public abstract class SietePorVeintiCuatroDatosReq {

}
