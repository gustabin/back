/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.dao.ConsultaTarjetasDAO;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasIn;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasOut;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.TarjetaDatos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.InfoTarjetas;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.Tarjeta;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.TarjetaDelSocio;

/**
 * The Class DatosTarjetasBOImpl.
 */
@Component
public class DatosTarjetasBOImpl implements DatosTarjetasBO {

	/** The Constant ESTADO_TARJETA_NO_RENOVAR. */
	private static final String ESTADO_TARJETA_NO_RENOVAR = "22";

    /** The Constant ESTADO_TARJETA_ACTIVA. */
    private static final String ESTADO_TARJETA_ACTIVA = "20";

    /** The Constant CONDICION_TITULAR. */
    private static final String CONDICION_TITULAR = "TITULAR";

    /** The Constant MARCA_AMEX. */
    private static final String MARCA_AMEX = "AMEX";

    /** The Constant ESPACIO. */
    private static final String ESPACIO = " ";

    /** The Constant CODIGO_PARTICIPACION_TITULAR. */
    private static final String CODIGO_PARTICIPACION_TITULAR = "TI";

    /** The Constant MARCA_AMEX_CNSTJCDATC. */
    private static final String MARCA_AMEX_CNSTJCDATC = "4";

	/** The Constant MARCA_VISA_CNSTJCDATC. */
	private static final String MARCA_VISA_CNSTJCDATC = "1";

    /** The Constant TIPO_CUENTA_AMEX_CNSTJCDATC. */
    private static final String TIPO_CUENTA_AMEX_CNSTJCDATC = "3";

    /** The Constant TIPO_CUENTA_VISA_CNSTJCDATC. */
    private static final String TIPO_CUENTA_VISA_CNSTJCDATC = "1";

    /** TarjetasDAO. */
	@Autowired
	private ConsultaTarjetasDAO consultaTarjetasDAO;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.marcaviajero.bo.DatosTarjetasBO#
	 * obtenerDatosTarjeta(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, java.util.List,
	 * ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta)
	 */
	@Override
	public Tarjeta obtenerDatosTarjeta(Cliente cliente, List<InfoTarjetas> tarjetasReenganche,
			ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta tarjetaWS) {

		Tarjeta tarjetaEncontrada = null;
		for (InfoTarjetas infoTarjetas : tarjetasReenganche) {

			if (infoTarjetas.getHayMasDatos()) {
				try {
					tarjetaEncontrada = buscarMasTarjetas(cliente, infoTarjetas, tarjetaWS);
				} catch (BusinessException e) {
					return null;
				}
				if (tarjetaEncontrada != null) {
					return tarjetaEncontrada;
				}
			}
		}
		return tarjetaEncontrada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.marcaviajero.bo.DatosTarjetasBO#
	 * preparatInfoTarjetas(java.util.List)
	 */
	@Override
	public List<InfoTarjetas> preparatInfoTarjetas(List<Cuenta> tarjetas) {

		List<InfoTarjetas> infoTarjetas = new ArrayList<InfoTarjetas>();
		for (Cuenta tarjeta : tarjetas) {

			if (TipoCuenta.VISA.equals(tarjeta.getTipoCuentaEnum()) && tarjeta.esEstadoTarjetaCredito()) {
				InfoTarjetas infoTarjeta = getInfoTarjeta(tarjeta, MARCA_VISA_CNSTJCDATC, TIPO_CUENTA_VISA_CNSTJCDATC, Boolean.TRUE);
				infoTarjetas.add(infoTarjeta);
			}
			if (TipoCuenta.BANELCO.equals(tarjeta.getTipoCuentaEnum())) {
				InfoTarjetas infoTarjeta = getInfoTarjeta(tarjeta, MARCA_VISA_CNSTJCDATC, TIPO_CUENTA_VISA_CNSTJCDATC, Boolean.FALSE);
				infoTarjetas.add(infoTarjeta);

			}
			if (TipoCuenta.AMEX.equals(tarjeta.getTipoCuentaEnum()) && tarjeta.esEstadoTarjetaCredito()) {
				InfoTarjetas infoTarjeta = getInfoTarjeta(tarjeta, MARCA_AMEX_CNSTJCDATC, TIPO_CUENTA_AMEX_CNSTJCDATC, Boolean.TRUE);
				infoTarjetas.add(infoTarjeta);
			}
		}
		return infoTarjetas;
	}

	/**
	 * Gets the info tarjeta.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param marca
	 *            the marca
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param hayMasDatos
	 *            the hay mas datos
	 * @return the info tarjeta
	 */
	private InfoTarjetas getInfoTarjeta(Cuenta tarjeta, String marca, String tipoCuenta, Boolean hayMasDatos) {
		InfoTarjetas infoTarjeta = new InfoTarjetas();
		infoTarjeta.setIsTitular(CODIGO_PARTICIPACION_TITULAR.equals(tarjeta.getCalidadDeParticipacion()));
		infoTarjeta.setMarca(tarjeta.getTipoCuentaEnum().getAbreviatura());
		infoTarjeta.setMarcaCodigo(marca);
		infoTarjeta.setTipoCuenta(tipoCuenta);
		infoTarjeta.setAliasCuenta(tarjeta.getAlias());
		infoTarjeta.setNumeroCuentaProducto(tarjeta.getNroCuentaProducto());
		infoTarjeta.setUltimosCuatroDigitos(
				ultimosCuatro(tarjeta.getNroTarjetaCredito(), tarjeta.getTipoCuentaEnum().getAbreviatura()));
		infoTarjeta.setNumeroTarjetaCredito(tarjeta.getNroTarjetaCredito());
		infoTarjeta.setNombrePlastico(tarjeta.getCliente().getNombre() + ESPACIO + tarjeta.getCliente().getApellido1());
		infoTarjeta.setHayMasDatos(hayMasDatos);
		return infoTarjeta;

	}

	/**
	 * Ultimos cuatro.
	 *
	 * @param nroTarjetaCredito
	 *            the nro tarjeta credito
	 * @param marca
	 *            the marca
	 * @return the string
	 */
	private String ultimosCuatro(String nroTarjetaCredito, String marca) {

		if (MARCA_AMEX.equalsIgnoreCase(marca)) {
			return nroTarjetaCredito.substring(nroTarjetaCredito.length() - 5, nroTarjetaCredito.length() - 1);
		}

		return nroTarjetaCredito.substring(nroTarjetaCredito.length() - 4);
	}

	/**
	 * Buscar mas tarjetas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param infoTarjeta
	 *            the info tarjeta
	 * @param tarjetaWS
	 *            the tarjeta WS
	 * @return the tarjeta
	 * @throws BusinessException
	 *             the business exception
	 */
	private Tarjeta buscarMasTarjetas(Cliente cliente, InfoTarjetas infoTarjeta,
			ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta tarjetaWS) throws BusinessException {

		Tarjeta tarjetaEncontrada = null;
		
		while (tarjetaEncontrada == null && infoTarjeta.getHayMasDatos()) {
		    tarjetaEncontrada = buscarEnDatosTarjeta(cliente, infoTarjeta, tarjetaWS.getUltimosCuatro());
		}
		if (tarjetaEncontrada != null) {
		    tarjetaEncontrada.setIsTitular(CONDICION_TITULAR.equals(tarjetaWS.getCondicion()));
			return tarjetaEncontrada;
		}
		return null;
	}

	/**
	 * Buscar en datos tarjeta.
	 *
	 * @param cliente the cliente
	 * @param infoTarjeta the info tarjeta
	 * @param ultimosCuatro the ultimos cuatro
	 * @return the tarjeta
	 * @throws BusinessException the business exception
	 */
	private Tarjeta buscarEnDatosTarjeta(Cliente cliente, InfoTarjetas infoTarjeta, String ultimosCuatro) throws BusinessException {
	    
	    Tarjeta tarjetaEncontrada = null;
	    ConsultaDatosTarjetasIn consultaTarjetaIn = new ConsultaDatosTarjetasIn();
        consultaTarjetaIn.setCliente(cliente);
        consultaTarjetaIn.setMarca(infoTarjeta.getMarcaCodigo());
        consultaTarjetaIn.setTipoCuenta(infoTarjeta.getTipoCuenta());
        consultaTarjetaIn.setClaveReEnganche(infoTarjeta.getClaveReenganche());
        consultaTarjetaIn.setNroCuenta(infoTarjeta.getNumeroCuentaProducto());

        ConsultaDatosTarjetasOut tarjetasOut = null;
        try {
            tarjetasOut = consultaTarjetasDAO.consultaDatosTarjetas(consultaTarjetaIn);
        } catch (DAOException e) {
            throw new BusinessException();
        }
        if (CollectionUtils.isEmpty(tarjetasOut.getTarjetas())) {
            infoTarjeta.setHayMasDatos(Boolean.FALSE);
        }
        infoTarjeta.setHayMasDatos("S".equalsIgnoreCase(tarjetasOut.getHayReEnganche()));
        infoTarjeta.setClaveReenganche(tarjetasOut.getMarcaReLlamada());

        for (TarjetaDatos tarjetaDato : tarjetasOut.getTarjetas()) {
            Tarjeta tarjeta = agregarATarjetasRio(infoTarjeta, tarjetaDato);
            if (tarjeta!= null && !ultimosCuatro(tarjetaDato).equals(infoTarjeta.getUltimosCuatroDigitos())
                    && tarjeta.getUltimosCuatro().equals(ultimosCuatro)
             && estadoTarjetaOK(tarjetaDato)) {
                tarjetaEncontrada = tarjeta;
            }
        }
        if(tarjetaEncontrada!= null) {
            return tarjetaEncontrada;
        }
        return null;
    }

    /**
     * Estado tarjeta OK.
     *
     * @param tarjeta the tarjeta
     * @return true, if successful
     */
    private boolean estadoTarjetaOK(TarjetaDatos tarjeta) {
	    
	    return ESTADO_TARJETA_ACTIVA.equals(tarjeta.getEstadoTarjeta())||
	            ESTADO_TARJETA_NO_RENOVAR.equals(tarjeta.getEstadoTarjeta());
    }

    /**
     * Ultimos cuatro.
     *
     * @param tarjetaDato the tarjeta dato
     * @return the string
     */
    private String ultimosCuatro(TarjetaDatos tarjetaDato) {
	    if(TIPO_CUENTA_AMEX_CNSTJCDATC.equals(tarjetaDato.getTipoCuenta())) {
	        return tarjetaDato.getNroTarjeta().substring(tarjetaDato.getNroTarjeta().length()-5, tarjetaDato.getNroTarjeta().length()-1);
	    }
	    return tarjetaDato.getNroTarjeta().substring(tarjetaDato.getNroTarjeta().length()-4);
    }

    /**
     * Agregar A tarjetas rio.
     *
     * @param infoTarjeta            the info tarjeta
     * @param tarjetaDato            the tarjeta dato
     * @return the tarjeta
     */
	private Tarjeta agregarATarjetasRio(InfoTarjetas infoTarjeta, TarjetaDatos tarjetaDato) {

	    Tarjeta tarjeta = null;
	    if(!infoTarjeta.getUltimosCuatroDigitos().equals(ultimosCuatro(tarjetaDato))) {
	        
	        tarjeta = new Tarjeta();
	        tarjeta.setAlias(null);
	        tarjeta.setNumero(tarjetaDato.getNroTarjeta());
	        tarjeta.setMarca(infoTarjeta.getMarca());
	        tarjeta.setUltimosCuatro(ultimosCuatro(tarjetaDato));
	        if (!infoTarjeta.getTarjetasAsociadas().contains(tarjeta)) {
	            infoTarjeta.getTarjetasAsociadas().add(tarjeta);
	        }
	    }
		return tarjeta;
	}

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.bo.DatosTarjetasBO#filtrarDatosTarjeta(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.util.List)
     */
    @Override
    public void filtrarDatosTarjeta(Cliente cliente, List<TarjetaDelSocio> tarjetasDelSocio) throws BusinessException {
        List<InfoTarjetas> tarjetasReenganche = preparatInfoTarjetas(cliente.getCuentas());
        List<TarjetaDelSocio> borrarTarjetas = new ArrayList<TarjetaDelSocio>();
        for(TarjetaDelSocio tarjeta : tarjetasDelSocio) {

            if(!existeTarjetaEnTarjetasEnganche(tarjetasReenganche, tarjeta)) {
                if(!existeEnDatosTarjetas(cliente, tarjetasReenganche, tarjeta)){
                    borrarTarjetas.add(tarjeta);
                }
            }
        }
        for(TarjetaDelSocio borrarTarjeta: borrarTarjetas) {
            tarjetasDelSocio.remove(borrarTarjeta);
        }
    }

    /**
     * Existe tarjeta en tarjetas enganche.
     *
     * @param tarjetas the tarjetas
     * @param tarjeta the tarjeta
     * @return the boolean
     */
    private Boolean existeTarjetaEnTarjetasEnganche(List<InfoTarjetas> tarjetas, TarjetaDelSocio tarjeta) {
        
        for (InfoTarjetas infoTarjetas : tarjetas) {
            if (infoTarjetas.getUltimosCuatroDigitos().equals(tarjeta.getUltimosCuatro())) {
                tarjeta.setAlias(infoTarjetas.getAliasCuenta());
                tarjeta.setNumero(infoTarjetas.getNumeroTarjetaCredito());
                tarjeta.setApellidoNombre(infoTarjetas.getNombrePlastico());
                tarjeta.setPrioridad(0);
                return true;
            }
            if(buscarEnTajetasAsociadas(infoTarjetas, tarjeta)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Buscar en tajetas asociadas.
     *
     * @param infoTarjetas the info tarjetas
     * @param tarjeta the ultimos cuatro
     * @return the boolean
     */
    private Boolean buscarEnTajetasAsociadas(InfoTarjetas infoTarjetas, TarjetaDelSocio tarjeta) {
        
        for(Tarjeta tarj: infoTarjetas.getTarjetasAsociadas()){
            if(tarj.getUltimosCuatro().equals(tarjeta.getUltimosCuatro())){
                tarjeta.setNumero(tarj.getNumero());
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Existe en datos tarjetas.
     *
     * @param cliente the cliente
     * @param tarjetasReenganche the tarjetas reenganche
     * @param tarjeta the tarjeta
     * @return the boolean
     * @throws BusinessException the business exception
     */
    private Boolean existeEnDatosTarjetas(Cliente cliente, List<InfoTarjetas> tarjetasReenganche, TarjetaDelSocio tarjeta) throws BusinessException {
        Tarjeta tarjetaEncontrada = null;
        for (InfoTarjetas tarjReenganche: tarjetasReenganche) {

            while(tarjetaEncontrada == null && tarjReenganche.getHayMasDatos() ) {

                tarjetaEncontrada = buscarEnDatosTarjeta(cliente, tarjReenganche, tarjeta.getUltimosCuatro());

            }
            if(tarjetaEncontrada != null) {
                tarjeta.setNumero(tarjetaEncontrada.getNumero());
                break;

            }
        }
        return tarjetaEncontrada != null;
    }
}
