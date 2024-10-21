/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The Class EnumData.
 */
@Component
public class EnumData {

	/** The valor trj coord. */
	@Value("${TRJCOORD.OPERAINDISTINTO.REIMPRESIONTRJ}")
	public String valorTrjCoord;

	/** The instance. */
	public static EnumData instance = null;

	/**
	 * Instantiates a new enum data.
	 */
	public EnumData() {
		super();
	}

	/**
	 * Gets the single instance of EnumData.
	 *
	 * @return single instance of EnumData
	 */
	public static final EnumData getInstance() {
		if (instance == null) {
			instance = new EnumData();
		}
		return instance;
	}

	/**
	 * Gets the valor trj coord.
	 *
	 * @return the valor trj coord
	 */
	public String getValorTrjCoord() {
		return valorTrjCoord;
	}

	/**
	 * Sets the valor trj coord.
	 *
	 * @param valorTrjCoord
	 *            the new valor trj coord
	 */
	public void setValorTrjCoord(String valorTrjCoord) {
		valorTrjCoord = valorTrjCoord;
	};

}
