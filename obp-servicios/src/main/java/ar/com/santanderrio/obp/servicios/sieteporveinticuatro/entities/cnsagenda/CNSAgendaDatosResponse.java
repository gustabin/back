/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class CNSAgendaDatosResponse.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CNSAgendaDatosResponse {

	/** The reg. */
	@XmlElement(name = "Reg")
	protected List<CNSAgendaRegResponse> reg;

	/**
	 * Gets the reg.
	 *
	 * @return the reg
	 */
	public List<CNSAgendaRegResponse> getReg() {
		if (reg == null) {
			reg = new ArrayList<CNSAgendaRegResponse>();
		}
		return this.reg;
	}

}
