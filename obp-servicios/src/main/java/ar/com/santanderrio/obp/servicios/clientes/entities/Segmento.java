package ar.com.santanderrio.obp.servicios.clientes.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;

public class Segmento extends Entity {
	private static final long serialVersionUID = 1L;

	private static final String DUO_CLIENT = "duo";
	private static final String PYME_CLIENT = "pyme";
	private static final String SELECT_CLIENT = "select";
	private static final String UNIVERSIDAD_CLIENT = "universidad";
	private static final String IU_CLIENT = "iu";
	private static final String COMMON_CLIENT = "santander";

	private String ejecutivo;
	private OperadorEjecutivo operadorEjecutivo;

	/** The pesucadm. */
	private String pesucadm;

	/** The pesubseg. */
	private String pesubseg;
	private String cuadrante;

	private boolean select;
	private boolean selectOnline;
	private boolean duo;
	private boolean pyme;
	private boolean universidad;
	private boolean iU;

	/** The Constant BOOLEAN_TRUE_HASH. */
	private static final int BOOLEAN_TRUE_HASH = 1231;

	/** The Constant BOOLEAN_FALSE_HASH. */
	private static final int BOOLEAN_FALSE_HASH = 1237;

	public String getEjecutivo() {
		return ejecutivo;
	}

	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}

	public String getPesucadm() {
		return pesucadm;
	}

	public void setPesucadm(String pesucadm) {
		this.pesucadm = pesucadm;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public boolean isSelectOnline() {
		return selectOnline;
	}

	public void setSelectOnline(boolean selectOnline) {
		this.selectOnline = selectOnline;
	}

	public void setDuo(boolean duo) {
		this.duo = duo;
	}

	public boolean isDuo() {
		return duo;
	}

	public boolean isPyme() {
		return pyme;
	}

	public void setPyme(boolean pyme) {
		this.pyme = pyme;
	}

	/**
	 * Checks if is universidad.
	 *
	 * @return true, if is universidad
	 */
	public boolean isUniversidad() {
		return universidad;
	}

	/**
	 * Setter para universidad.
	 *
	 * @param universidad
	 *            el nuevo universidad
	 */
	public void setUniversidad(boolean universidad) {
		this.universidad = universidad;
	}

	/**
	 * Gets the cuadrante.
	 *
	 * @return the cuadrante
	 */
	public String getCuadrante() {
		return cuadrante;
	}

	/**
	 * Sets the cuadrante.
	 *
	 * @param cuadrante the new cuadrante
	 */
	public void setCuadrante(String cuadrante) {
		this.cuadrante = cuadrante;
	}

	/**
	 * Gets the pesubseg.
	 *
	 * @return the pesubseg
	 */
	public String getPesubseg() {
		return pesubseg;
	}

	/**
	 * Sets the pesubseg.
	 *
	 * @param pesubseg the new pesubseg
	 */
	public void setPesubseg(String pesubseg) {
		this.pesubseg = pesubseg;
	}

	public boolean isiU() {
		return iU;
	}

	public void setiU(boolean iU) {
		this.iU = iU;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		String segmentoStr = COMMON_CLIENT;
		if (this.isDuo()) {
			segmentoStr = DUO_CLIENT;
		} else if (this.isPyme()) {
			segmentoStr = PYME_CLIENT;
		} else if (this.isSelect()) {
			segmentoStr = SELECT_CLIENT;
		} else if (this.isiU()) {
			segmentoStr = IU_CLIENT;
		} else if (this.isUniversidad()) {
			segmentoStr = UNIVERSIDAD_CLIENT;
		}
		return segmentoStr;
	}

	public List<String> getSegmentos() {
		List<String> segmentos = new ArrayList<String>();
		if (this.isDuo()) {
			segmentos.add(DUO_CLIENT);
		} 
		if (this.isPyme()) {
			segmentos.add(PYME_CLIENT);
		} 
		if (this.isSelect()) {
			segmentos.add(SELECT_CLIENT);
		} 
		if (this.isiU()) {
			segmentos.add(IU_CLIENT);
		}
		if (this.isUniversidad()) {
			segmentos.add(UNIVERSIDAD_CLIENT);
		}
		if (segmentos.isEmpty()) {
			segmentos.add(COMMON_CLIENT);
		}
		return segmentos;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (duo ? BOOLEAN_TRUE_HASH : BOOLEAN_FALSE_HASH);
		result = prime * result + (pyme ? BOOLEAN_TRUE_HASH : BOOLEAN_FALSE_HASH);
		result = prime * result + ((ejecutivo == null) ? 0 : ejecutivo.hashCode());
		result = prime * result + ((pesucadm == null) ? 0 : pesucadm.hashCode());
		result = prime * result + (select ? BOOLEAN_TRUE_HASH : BOOLEAN_FALSE_HASH);
		result = prime * result + (universidad ? BOOLEAN_TRUE_HASH : BOOLEAN_FALSE_HASH);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Segmento other = (Segmento) obj;
		if (duo != other.duo) {
			return false;
		}
		if (pyme != other.pyme) {
			return false;
		}
		if (ejecutivo == null) {
			if (other.ejecutivo != null) {
				return false;
			}
		} else if (!ejecutivo.equals(other.ejecutivo)) {
			return false;
		}
		if (pesucadm == null) {
			if (other.pesucadm != null) {
				return false;
			}
		} else if (!pesucadm.equals(other.pesucadm)) {
			return false;
		}
		if (select != other.select) {
			return false;
		}
		if (universidad != other.universidad) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("ejecutivo", ejecutivo).append("pesucadm", pesucadm)
				.append("select", select).append("duo", duo).append("pyme", pyme).append("universidad", universidad).toString();
	}

	/**
	 * Gets the operador ejecutivo.
	 *
	 * @return the operador ejecutivo
	 */
	public OperadorEjecutivo getOperadorEjecutivo() {
		return operadorEjecutivo;
	}

	/**
	 * Sets the operador ejecutivo.
	 *
	 * @param operadorEjecutivo the new operador ejecutivo
	 */
	public void setOperadorEjecutivo(OperadorEjecutivo operadorEjecutivo) {
		this.operadorEjecutivo = operadorEjecutivo;
	}

	public static Segmento defaultSegmento() {
		Segmento segmento = new Segmento();
		segmento.setDuo(false);
		segmento.setPyme(false);
		segmento.setSelect(false);
		segmento.setUniversidad(false);
		segmento.setSelectOnline(false);
		segmento.setEjecutivo("");
		segmento.setPesucadm("");
		segmento.setCuadrante("");
		segmento.setPesubseg("");
		return segmento;
	}
}
