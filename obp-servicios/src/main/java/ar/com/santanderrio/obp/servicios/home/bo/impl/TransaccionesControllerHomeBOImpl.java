/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.ConsultaPaquetesDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.InformacionCuentaPaqueteInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.PaqueteEntity;
import ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;

/**
 * The Class TransaccionesControllerHomeBOImpl.
 */
@Component
public class TransaccionesControllerHomeBOImpl implements TransaccionesControllerHomeBO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransaccionesControllerHomeBOImpl.class);

    /** The cuenta BO. */
    @Autowired
    private CuentaBO cuentaBO;

    /** The consulta. */
    @Autowired
    private ConsultaPaquetesDAO consulta;
    
    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;
    
	/** The cuentas banca privada BO. */
	@Autowired
	private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;
    
    /** The codigos BP. */
    @Value("#{'${CUENTAS.DESCCHEQUES.BYP.CODIGOS}'.split(',')}")
    private List<String> codigosBP;

    /** The Constant TITULARIDAD_TITULAR. */
    public static final String CODIGO_TITULARIDAD_TITULAR = "TI";

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#
     * aplicaTransferencias(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente)
     */
    @Override
    public Boolean aplicaTransferencias(Cliente cliente) {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#
     * aplicaCalendarioPagos(ar.com.santanderrio.obp.servicios.clientes.entities
     * .Cliente)
     */
    @Override
    public Boolean aplicaCalendarioPagos(Cliente cliente) {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#
     * aplicaPagoTarjeta(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente)
     */
    @Override
    public Boolean aplicaPagoTarjeta(Cliente cliente) {

        try {
            List<Cuenta> productos = cliente.getCuentas();
            List<Cuenta> tarjetas = TarjetaBOUtils.filtrarCuentasDeTipoCuentaTarjeta(productos);
            Boolean tieneCuentas = cuentaBO.hasCuentasMonetariasActivas(cliente);

            if (tieneCuentas && CollectionUtils.isNotEmpty(tarjetas)) {
                return true;
            }

        } catch (TarjetaBOUtilsException e) {
            LOGGER.error("Error al cargar el topbar de tarjetas", e);
            return false;
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#
     * aplicaEnvioEfectivo(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente)
     */
    @Override
    public Boolean aplicaEnvioEfectivo(Cliente cliente) {
        return cuentaBO.hasCuentasMonetariasActivas(cliente);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#
     * aplicaBilleteraVirtual(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente)
     */
    @Override
    public Boolean aplicaBilleteraVirtual(Cliente cliente) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#
     * aplicaCesionCheques(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente)
     */
    @Override
    public Boolean aplicaCesionCheques(Cliente cliente) {
        LOGGER.info("ML aplicaCesionCheques");
        List<Cuenta> productos = cliente.getCuentas();

        for (Cuenta cuenta : productos) {
            if (TipoCuenta.CUENTA_UNICA.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()))
                    && CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad()) && esBP(cuenta)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Es BP.
     *
     * @param cuenta
     *            the cuenta
     * @return true, if successful
     */
    private boolean esBP(Cuenta cuenta) {

        for (String codBP : codigosBP) {
            if (cuenta.getCodigoPaquete().equals(codBP)) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#
     * aplicaPagoHaberes(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente)
     */
    @Override
    public Boolean aplicaPagoHaberes(Cliente cliente) {
        return cuentaBO.hasCuentasMonetariasActivas(cliente);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#
     * aplicaDolares(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente)
     */
    @Override
    public Boolean aplicaDolares(Cliente cliente) {   
        return  cuentaBO.hasCuentasMonetariasActivas(cliente);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#
     * aplicaDebin(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Boolean aplicaDebin(Cliente cliente) {
        return cuentaBO.hasCuentasMonetariasActivas(cliente);
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#aplicaDescuentoCheques(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public boolean aplicaDescuentoCheques(Cliente cliente) {
		return corroborarCuentasSiByP(cliente);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#aplicaTransferenciasExterior(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Boolean aplicaTransferenciasExterior(Cliente cliente) {
		return true;
	}
	
    /**
	 * Corroborar cuentas si by P.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
    private Boolean corroborarCuentasSiByP(Cliente cliente) {
    	List<Cuenta> cuentasFiltradas = obtenerCuentasUnicas(cliente.getCuentas());
    	if(cuentasFiltradas.isEmpty()) {
    		sesionParametros.setIsCalificadoModuloDescCheques(false);
    		return false;
    	}
        ConsultaPaquetesInEntity consultaPaquetesInEntity = new ConsultaPaquetesInEntity();
        consultaPaquetesInEntity.setCliente(cliente);
        consultaPaquetesInEntity.setCantidadDeCuentas(
                ISBANStringUtils.formateadorConCerosIzq(String.valueOf(cuentasFiltradas.size()), 3));
        consultaPaquetesInEntity.setCuentasFiltradas(agregarCuentas(cuentasFiltradas));
        ConsultaPaquetesOutEntity res = null;
        try {
            res = consulta.consultar(consultaPaquetesInEntity);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            sesionParametros.setIsCalificadoModuloDescCheques(false);
            return true;
        }
        if (res == null || !StringUtils.containsOnly(res.getCodigoRetornoExtendido(), "0")) {
            sesionParametros.setIsCalificadoModuloDescCheques(false);
        	return true;
        }

        for (PaqueteEntity paquete : res.getPaquetes()) {
            if ("09".equals(paquete.getTipoCuenta()) && subProductoPaqueteValido(paquete.getSubProductoPaquete())) {
            	sesionParametros.setSubproductoPaquete(paquete.getSubProductoPaquete());
            	sesionParametros.setIsCalificadoModuloDescCheques(true);
            	sesionParametros.setCuentaSeleccionadaParaTransferencia(StringUtils.right(paquete.getSucursal(), 3)+"-"+StringUtils.left(paquete.getNumeroCuenta(), 6)+"/"+StringUtils.right(paquete.getNumeroCuenta(), 1), "");
                return true;
            }
        }
        sesionParametros.setIsCalificadoModuloDescCheques(false);
        return false;
    }
    
    /**
	 * Obtener cuentas unicas.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
    private List<Cuenta> obtenerCuentasUnicas(List<Cuenta> cuentas) {
        List<Cuenta> cuentasFiltradas = new ArrayList<Cuenta>();
        LOGGER.info("Obteniendo cuentas unicas infinity");
        for (Cuenta cuenta : cuentas) {
            if (StringUtils.isNotBlank(cuenta.getTipoCuentaSinUnificar()) && TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().equals(Integer.valueOf(cuenta.getTipoCuentaSinUnificar()))
                    && ("TI".equals(cuenta.getCodigoTitularidad()))) {
                cuentasFiltradas.add(cuenta);
            }
        }
        return cuentasFiltradas;
    }

    /**
	 * Sub producto paquete valido.
	 *
	 * @param subProductoPaquete
	 *            the sub producto paquete
	 * @return true, if successful
	 */
    private boolean subProductoPaqueteValido(String subProductoPaquete) {
    	return codigosBP.contains(subProductoPaquete);
    }
    
    /**
	 * Agregar cuentas.
	 *
	 * @param cuentasFiltradas
	 *            the cuentas filtradas
	 * @return the list
	 */
    private List<InformacionCuentaPaqueteInEntity> agregarCuentas(List<Cuenta> cuentasFiltradas) {
        List<InformacionCuentaPaqueteInEntity> lista = new ArrayList<InformacionCuentaPaqueteInEntity>();
        for (Cuenta cuenta : cuentasFiltradas) {
            InformacionCuentaPaqueteInEntity informacionCuenta = new InformacionCuentaPaqueteInEntity();
            informacionCuenta.setNumeroCuenta(StringUtils.right(cuenta.getNroCuentaProducto(), 7));
            informacionCuenta.setSucursalCuenta(StringUtils.right(cuenta.getSucursalPaquete(), 3));
            informacionCuenta.setTipoCuenta(cuenta.getTipoCuentaSinUnificar());
            lista.add(informacionCuenta);
        }
        return lista;
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#aplicaTransferenciasBancaPrivada(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Boolean aplicaTransferenciasBancaPrivada(Cliente cliente) {
		return CollectionUtils.isNotEmpty(cuentasBancaPrivadaBO.obtenerCuentasBancaPrivada(cliente));
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#aplicaECheq(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Boolean aplicaECheq(Cliente cliente) {
		if(!cliente.getCuentas().isEmpty()) {
			return true;
		}
	    return false;
	}

	@Override
	public Boolean aplicaOrdenDePagoDelExterior(Cliente cliente) {
		return true;
	}
	
	@Override
	public Boolean aplicaExtraccionEfectivo(Cliente cliente) {
		if(!cliente.getCuentasMonetarias().isEmpty()) {
			return true;
		}
	    return false;
	}
	

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO#aplicaDolaresBP(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Boolean aplicaDolaresBP(Cliente cliente) {
		Boolean aplicaDolaresBP = Boolean.FALSE;
    	if (cliente.getCuentas().isEmpty() || !cuentaBO.hasCuentasMonetariasActivas(cliente)) {
    		if (CuentasBancaPrivadaUtil.cuentasBPMonetariasActivas(cliente)) {
        		aplicaDolaresBP = Boolean.TRUE;
        	}    		
    	}
        return  aplicaDolaresBP;
	}
	
	@Override
	public Boolean aplicaExtraccionEnSantanderExpress(Cliente cliente) {
	    return true;
	}

}
