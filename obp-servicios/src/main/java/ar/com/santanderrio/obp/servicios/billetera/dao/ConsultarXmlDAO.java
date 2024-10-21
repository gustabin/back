/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dao;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Interface ConsultarXmlDAO.
 */
public interface ConsultarXmlDAO {

	/**
	 * The Class Entidad.
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Entidad {

		/** The idCPF. */
		@XmlAttribute(name = "idCPF")
		private String idCpf;

		/** The id. */
		@XmlAttribute(name = "idPrisma")
		private String idPrisma;

		/**
		 * Gets the id cpf.
		 *
		 * @return the idCpf
		 */
		public String getIdCpf() {
			return idCpf;
		}

		/**
		 * Gets the id prisma.
		 *
		 * @return the idPrisma
		 */
		public String getIdPrisma() {
			return idPrisma;
		}

		/**
		 * Sets the id cpf.
		 *
		 * @param idCpf
		 *            the idCpf to set
		 */
		public void setIdCpf(String idCpf) {
			this.idCpf = idCpf;
		}

		/**
		 * Sets the id prisma.
		 *
		 * @param idPrisma
		 *            the idPrisma to set
		 */
		public void setIdPrisma(String idPrisma) {
			this.idPrisma = idPrisma;
		}

	}

	/**
	 * The Class EntidadLocalidad.
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class EntidadLocalidad {

		/** The id. */
		@XmlAttribute(name = "id")
		private String id;

		/** The idProvCPF. */
		@XmlAttribute(name = "idProvCPF")
		private String idProvCpf;

		/**
		 * Gets the id.
		 *
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * Gets the id prov cpf.
		 *
		 * @return the idProvCpf
		 */
		public String getIdProvCpf() {
			return idProvCpf;
		}

		/**
		 * Sets the id.
		 *
		 * @param id
		 *            the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * Sets the id prov cpf.
		 *
		 * @param idProvCpf
		 *            the idProvCpf to set
		 */
		public void setIdProvCpf(String idProvCpf) {
			this.idProvCpf = idProvCpf;
		}

	}

	/**
	 * The Class Localidades.
	 */
	@XmlRootElement(name = "Localidades")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Localidades {

		/** The entidades. */
		@XmlElement(name = "Localidad")
		private List<EntidadLocalidad> entidades;

		/**
		 * Gets the entidades.
		 *
		 * @return the entidades
		 */
		public List<EntidadLocalidad> getEntidades() {
			return entidades;
		}

		/**
		 * Setter para entidades.
		 *
		 * @param entidades
		 *            el nuevo entidades
		 */
		public void setEntidades(List<EntidadLocalidad> entidades) {
			this.entidades = entidades;
		}

	}

	/**
	 * The Class Nacionalidades.
	 */
	@XmlRootElement(name = "Nacionalidades")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Nacionalidades {

		/** The entidades. */
		@XmlElement(name = "Nacionalidad")
		private List<Entidad> entidades;

		/**
		 * Gets the entidades.
		 *
		 * @return the entidades
		 */
		public List<Entidad> getEntidades() {
			return entidades;
		}

		/**
		 * Setter para entidades.
		 *
		 * @param entidades
		 *            el nuevo entidades
		 */
		public void setEntidades(List<Entidad> entidades) {
			this.entidades = entidades;
		}

	}

	/**
	 * The Class Provincias.
	 */
	@XmlRootElement(name = "Provincias")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Provincias {

		/** The entidades. */
		@XmlElement(name = "Provincia")
		private List<Entidad> entidades;

		/**
		 * Gets the entidades.
		 *
		 * @return the entidades
		 */
		public List<Entidad> getEntidades() {
			return entidades;
		}

		/**
		 * Setter para entidades.
		 *
		 * @param entidades
		 *            el nuevo entidades
		 */
		public void setEntidades(List<Entidad> entidades) {
			this.entidades = entidades;
		}

	}

	/**
	 * Busca una localidad por id.
	 *
	 * @param idCpf
	 *            the id cpf
	 * @return entidad asociada, o null si no existe
	 * @throws DAOException
	 *             si hubo error
	 */
	EntidadLocalidad consultarLocalidadPorId(String idCpf) throws DAOException;

	/**
	 * Busca una naconalidad por id.
	 *
	 * @param idCpf
	 *            the id cpf
	 * @return entidad asociada, o null si no existe
	 * @throws DAOException
	 *             si hubo error
	 */
	Entidad consultarNacionalidadPorId(String idCpf) throws DAOException;

	/**
	 * Busca una provincia por id.
	 *
	 * @param idCpf
	 *            the id cpf
	 * @return entidad asociada, o null si no existe
	 * @throws DAOException
	 *             si hubo error
	 */
	Entidad consultarProvinciaPorId(String idCpf) throws DAOException;
}
