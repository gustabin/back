/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.combos.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.ComboType;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.CombosType;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.OpcionType;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.OpcionesType;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

/**
 * The Class ConsultarImportesARecargarDAOImpl.
 *
 * @author B039542
 */
@Component
@Scope("singleton")
public class DatosSelectoresDAOImpl implements DatosSelectoresDAO {
    /** The Constant logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DatosSelectoresDAOImpl.class);

    /** The file path. */
    @Autowired
    private FilePath filePath;

    /** The entidades. */
    private List<ComboType> listCombo;

    /** The list opcion. */
    @SuppressWarnings("unused")
	private List<Opcion> listOpcion;

    /** The Constant TAG_IMPORTE_A_RECARGAR. */
    private static final String TAG_IMPORTE_A_RECARGAR = "tagMaximoVez";

    /** The Constant TAG_TIPO_DOCUMENTO. */
    private static final String TAG_TIPO_DOCUMENTO = "tipoDoc";

    /** The Constant TAG_LIMITE_RECARGA_MENSUAL. */
    private static final String TAG_LIMITE_RECARGA_MENSUAL = "tagMaximoMensual";

    /** The Constant TAG_PROVINCIA. */
    private static final String TAG_PROVINCIA = "provincias";

    /** The Constant TAG_PAIS_NACIMIENTO. */
    private static final String TAG_PAIS_NACIMIENTO = "paisNacimiento";

    /** The Constant TAG_SEXO. */
    private static final String TAG_SEXO = "sexo";

    /** The Constant TAG_SEXO_COMPLETO. */
    private static final String TAG_SEXO_COMPLETO = "sexoCompleto";

    /** The Constant TAG_ESTADO_CIVIL. */
    private static final String TAG_ESTADO_CIVIL = "estadoCivil";

    /** The Constant TAG_NACIONALIDAD. */
    private static final String TAG_NACIONALIDAD = "Nacionalidades";

    /** The Constant TAG_PRG_SEGURIDAD. */
    private static final String TAG_PRG_SEGURIDAD = "prgSeguridad";

    /** The Constant TAG_CANT_CHEQUES_COMUN. */
    private static final String TAG_CANT_CHEQUES_COMUN = "cantidadChequesComun";

    /** The Constant TAG_CANT_CHEQUES_PAGO_DIFERIDO. */
    private static final String TAG_CANT_CHEQUES_PAGO_DIFERIDO = "cantidadChequesPagoDiferido";

    /** The Constant TAG_CANT_CHEQUERA_COMUN. */
    private static final String TAG_CANT_CHEQUERA_COMUN = "cantidadChequeraComun";

    /** The Constant TAG_CANT_CHEQUERA_PAGO_DIFERIDO. */
    private static final String TAG_CANT_CHEQUERA_PAGO_DIFERIDO = "cantidadChequeraPagoDiferido";

    /** The Constant TAG_VINCULOS. */
	private static final String TAG_VINCULOS_COMEX = "vinculosComex";

    /** The Constant TAG_GASTOS_A_CARGO. */
	private static final String TAG_GASTOS_A_CARGO = "gastosACargo";

    /** The Constant TAG_CODIGO_BANCARIO. */
	private static final String TAG_CODIGO_BANCARIO = "codigoBancario";
	
	/** The Constant TAG_CONDICION_IVA. */
    private static final String TAG_CONDICION_IVA = "condicionIVA";
    
    /** The Constant TAG_CONDICION_IIBB. */
    private static final String TAG_CONDICION_IIBB = "condicionIIBB";
    
    /** The Constant TAG_MODALIDADES_ECHEQ. */
    public static final String TAG_MODALIDADES_ECHEQ = "modalidadesEcheq";

    /** The Constant TAG_TIPOS_ENDOSO_ECHEQ_A_LA_ORDEN. */
    public static final String TAG_TIPOS_ENDOSO_ECHEQ_A_LA_ORDEN = "tiposEndosoEcheqAlaOrden";
    
    /** The Constant TAG_TIPOS_ENDOSO_ECHEQ_NO_A_LA_ORDEN. */
    public static final String TAG_TIPOS_ENDOSO_ECHEQ_NO_A_LA_ORDEN = "tiposEndosoEcheqNoAlaOrden";
    
    
    /** The Constant TAG_MOTIVOS_EMAIL. */
    private static final String TAG_MOTIVOS_EMAIL = "motivosEmail";

    
    /** The Constant TAG_ACTIVIDADES_GETNET. */
    private static final String TAG_ACTIVIDADES_GETNET = "actividadesGetnet";

    /** The Constant TAG_INGRESOS_GETNET. */
    private static final String TAG_INGRESOS_GETNET = "ingresosGetnet";

    /**
     * Generar entidades.
     *
     * @return the list
     */
    @PostConstruct
    public void init() {
        try {
            /**
             * File file = new File(filePath.getFilePath() +
             * ExternalPropertyType.FILE_COMBOS.getName()); // Hasta que no se
             * actualice el obp-base se utilizara el string hardcode
             */
            File file = new File(filePath.getFilePath() + "combos.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(CombosType.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            CombosType combos = (CombosType) unmarshaller.unmarshal(file);
            this.listCombo = combos.getCombo();

        } catch (JAXBException e) {
            LOGGER.error("No se puede inicializar la lectura de combos.xml", e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerTiposDeDocumento()
     */
    @Override
    public List<Opcion> obtenerTiposDeDocumento() throws DAOException {

        return this.buscarComboPorTag(TAG_TIPO_DOCUMENTO);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerImportesMaximosMensuales()
     */
    @Override
    public List<Opcion> obtenerImportesMaximosMensuales() throws DAOException {

        return this.buscarComboPorTag(TAG_IMPORTE_A_RECARGAR);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerLimitesDeRecargaMensual()
     */
    @Override
    public List<Opcion> obtenerLimitesDeRecargaMensual() throws DAOException {

        return this.buscarComboPorTag(TAG_LIMITE_RECARGA_MENSUAL);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerProvincias()
     */
    @Override
    public List<Opcion> obtenerProvincias() throws DAOException {

        return this.buscarComboPorTag(TAG_PROVINCIA);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerPaisesDeNacimiento()
     */
    // obtenerPaisesDeNacimiento
    @Override
    public List<Opcion> obtenerPaisesDeNacimiento() throws DAOException {

        return this.buscarComboPorTag(TAG_PAIS_NACIMIENTO);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerSexo()
     */
    @Override
    public List<Opcion> obtenerSexo() throws DAOException {

        return this.buscarComboPorTag(TAG_SEXO);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerSexoCompleto()
     */
    @Override
    public List<Opcion> obtenerSexoCompleto() throws DAOException {

        return this.buscarComboPorTag(TAG_SEXO_COMPLETO);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerEstadoCivil()
     */
    @Override
    public List<Opcion> obtenerEstadoCivil() throws DAOException {

        return this.buscarComboPorTag(TAG_ESTADO_CIVIL);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerNacionalidad()
     */
    @Override
    public List<Opcion> obtenerNacionalidad() throws DAOException {

        return this.buscarComboPorTag(TAG_NACIONALIDAD);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerPreguntasSeguridad()
     */
    @Override
    public List<Opcion> obtenerPreguntasSeguridad() throws DAOException {

        return this.buscarComboPorTag(TAG_PRG_SEGURIDAD);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerOpcionDescripcion(java.lang.String, java.lang.String)
     */
    @Override
    public String obtenerOpcionDescripcion(String comboTag, String id) {

        List<Opcion> opc = this.buscarComboPorTag(comboTag);

        return this.buscarOpcionPorId(id, opc);
    }

    /**
     * Obtengo ID.
     *
     * @param comboID
     *            the combo ID
     * @param desc
     *            the desc
     * @return the string
     */
    public String obtenerIdOpcionPorDescripcion(String comboID, String desc) {

        List<Opcion> opciones = this.buscarComboPorTag(comboID);

        return this.buscarIdPorDescripcion(desc.trim(), opciones);
    }

    /**
     * Buscar sucursal.
     *
     * @param id
     *            the id
     * @return the sucursal
     */
    private List<Opcion> buscarComboPorTag(String id) {
        List<Opcion> opciones = new ArrayList<Opcion>();
        for (ComboType combo : this.listCombo) {
            if (combo.getId().equals(id)) {
                opciones = convertTo(combo.getOpciones());
            }
        }
        return opciones;
    }

    /**
     * Buscar opcion por id.
     *
     * @param id
     *            the id
     * @param opciones
     *            the opciones
     * @return the string
     */
    private String buscarOpcionPorId(String id, List<Opcion> opciones) {
        for (Opcion opcion : opciones) {
            if (opcion.getId().equals(id)) {
                return opcion.getOpcion();
            }
        }
        return null;
    }

    /**
     * Buscar id por descripcion.
     *
     * @param descripcion
     *            the descripcion
     * @param opciones
     *            the opciones
     * @return the string
     */
    private String buscarIdPorDescripcion(String descripcion, List<Opcion> opciones) {
        String idResultado = null;
        for (Opcion opcion : opciones) {
            if (opcion.getOpcion().equals(descripcion)) {
                idResultado = opcion.getId();
                break;
            }
        }
        return idResultado;
    }

    /**
     * Convert to.
     *
     * @param opcionesType
     *            the opciones type
     * @return the list
     */
    private List<Opcion> convertTo(OpcionesType opcionesType) {
        List<Opcion> opciones = new ArrayList<Opcion>();
        for (OpcionType opcionType : opcionesType.getOpcion()) {
            Opcion opcion = new Opcion();
            opcion.setId(opcionType.getId());
            opcion.setOpcion(opcionType.getValue());
            opciones.add(opcion);
        }
        return opciones;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerCantidadChequesComun()
     */
    @Override
    public List<Opcion> obtenerCantidadChequesComun() throws DAOException {
        return this.buscarComboPorTag(TAG_CANT_CHEQUES_COMUN);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerCantidadChequesPagoDiferido()
     */
    @Override
    public List<Opcion> obtenerCantidadChequesPagoDiferido() throws DAOException {
        return this.buscarComboPorTag(TAG_CANT_CHEQUES_PAGO_DIFERIDO);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerCantidadChequeraComun()
     */
    @Override
    public List<Opcion> obtenerCantidadChequeraComun() throws DAOException {
        return this.buscarComboPorTag(TAG_CANT_CHEQUERA_COMUN);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerCantidadChequeraPagoDiferido()
     */
    @Override
    public List<Opcion> obtenerCantidadChequeraPagoDiferido() throws DAOException {
        return this.buscarComboPorTag(TAG_CANT_CHEQUERA_PAGO_DIFERIDO);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerVinculos()
     */
	@Override
	public List<Opcion> obtenerVinculos() throws DAOException {
		return this.buscarComboPorTag(TAG_VINCULOS_COMEX);
	}

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerGastoACargo()
     */
	@Override
	public List<Opcion> obtenerGastoACargo() throws DAOException {
		return this.buscarComboPorTag(TAG_GASTOS_A_CARGO);
	}

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerCodigoBancario()
     */
	@Override
	public List<Opcion> obtenerCodigoBancario() throws DAOException {
		return this.buscarComboPorTag(TAG_CODIGO_BANCARIO);
	}

    /* 
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerCondicionIVA()
     */
    @Override
    public List<Opcion> obtenerCondicionIVA() throws DAOException {
        return this.buscarComboPorTag(TAG_CONDICION_IVA);
    }

    /* 
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerCondicionIIBB()
     */
    @Override
    public List<Opcion> obtenerCondicionIIBB() throws DAOException {
        return this.buscarComboPorTag(TAG_CONDICION_IIBB);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerModalidadesECheq()
     */
	@Override
	public List<Opcion> obtenerModalidadesECheq() throws DAOException {
		return this.buscarComboPorTag(TAG_MODALIDADES_ECHEQ);
	}

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#
     * obtenerTiposEndosoECheq()
     */
	@Override
	public List<Opcion> obtenerTiposEndosoECheq(String idModalidadEcheq) throws DAOException {
		if(ECheqUtils.MODALIDAD_CHEQUE_A_LA_ORDEN.equalsIgnoreCase(idModalidadEcheq))
			return this.buscarComboPorTag(TAG_TIPOS_ENDOSO_ECHEQ_A_LA_ORDEN);
		else
			return this.buscarComboPorTag(TAG_TIPOS_ENDOSO_ECHEQ_NO_A_LA_ORDEN);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO#obtenerMotivosEmail()
	 */
	@Override
	public List<Opcion> obtenerMotivosEmail() throws DAOException {
		return this.buscarComboPorTag(TAG_MOTIVOS_EMAIL);
	}

	
	/**
     * Obtener actividades getnet.
     *
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
	@Override
    public List<Opcion> obtenerActividadesGetnet() throws DAOException {
    	return this.buscarComboPorTag(TAG_ACTIVIDADES_GETNET);
    }

	/**
     * Obtener ingresos getnet.
     *
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public List<Opcion> obtenerIngresosGetnet() throws DAOException {
    	return this.buscarComboPorTag(TAG_INGRESOS_GETNET);
    }
}
