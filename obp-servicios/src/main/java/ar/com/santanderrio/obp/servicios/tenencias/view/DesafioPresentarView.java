/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;

/**
 * The Class DesafioPresentarView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DesafioPresentarView extends RsaDTOParaDesafio {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new desafio presentar view.
     */
    public DesafioPresentarView() {
        super(OperacionesRSAEnum.RECARGA_TARJETA);
    }

    /**
     * Instantiates a new desafio presentar view.
     *
     * @param tipoOperacion
     *            the tipo operacion
     */
    public DesafioPresentarView(OperacionesRSAEnum tipoOperacion) {
        super(OperacionesRSAEnum.RECARGA_TARJETA);
    }

}
