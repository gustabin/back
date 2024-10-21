/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.view.EstadisticaView;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;


/**
 * The Class TransferenciaComexView.
 */
@XmlRootElement(name = "transferenciaExteriorView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferenciaComexView extends RsaDTO implements EstadisticaView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1997697478015177827L;

	/** The mya BO. */
	@Autowired
	protected DatosSelectoresDAO selectoresDAO;
	
	
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaComexView.class);

    /** The motivos comex. */
    private List<MotivoComexView> motivosComex;
	
    /** The monedas comex. */
    private List<ConsultaMonedasView> monedasComex;
    
    /** The datos personales. */
    private DatosPersonalesView datosPersonales;
    
    /**
	 * Gets the selectores DAO.
	 *
	 * @return the selectoresDAO
	 */
	public DatosSelectoresDAO getSelectoresDAO() {
		return selectoresDAO;
	}

	/**
	 * Sets the selectores DAO.
	 *
	 * @param selectoresDAO
	 *            the selectoresDAO to set
	 */
	public void setSelectoresDAO(DatosSelectoresDAO selectoresDAO) {
		this.selectoresDAO = selectoresDAO;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Gets the motivos comex.
	 *
	 * @return the motivosComex
	 */
	public List<MotivoComexView> getMotivosComex() {
		return motivosComex;
	}

	/**
	 * Sets the motivos comex.
	 *
	 * @param motivosComex
	 *            the motivosComex to set
	 */
	public void setMotivosComex(List<MotivoComexView> motivosComex) {
		this.motivosComex = motivosComex;
	}
	
	/**
	 * Gets the monedas comex.
	 *
	 * @return the monedasView
	 */
	public List<ConsultaMonedasView> getMonedasComex() {
		return monedasComex;
	}

	/**
	 * Sets the monedas comex.
	 *
	 * @param monedasComex
	 *            the monedasView to set
	 */
	public void setMonedasComex(List<ConsultaMonedasView> monedasComex) {
		this.monedasComex = monedasComex;
	}

	/**
	 * Gets the datos personales.
	 *
	 * @return the datosPersonales
	 */
	public DatosPersonalesView getDatosPersonales() {
		return datosPersonales;
	}

	/**
	 * Sets the datos personales.
	 *
	 * @param datosPersonales
	 *            the datosPersonales to set
	 */
	public void setDatosPersonales(DatosPersonalesView datosPersonales) {
		this.datosPersonales = datosPersonales;
	}

	/**
	 * Instantiates a new transferencia comex view.
	 */
	public TransferenciaComexView() {
		//TODO: generar OperacionesRSAEnum.TRANSFERENCIA_EXTERIOR
		super(OperacionesRSAEnum.TRANSFERENCIA);

	}
		
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comun.estadistica.web.view.EstadisticaView#cargarEstadistica()
	 */
	@Override
	public Estadistica cargarEstadistica() {
		// TODO Auto-generated method stub
		return null;
	}
}