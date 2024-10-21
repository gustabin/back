/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.constants.SietePorVeintiCuatroConstant;

/**
 * The Class InsertarTransferenciaDTO.
 */
public class InsertarTransferenciaDTO {
	
	private static final int LONGITUD_NRO_CUENTA = 17;
	
	/** The Constant LONGITUD_NRO_CTA. */
	private static final int LONGITUD_NRO_CTA = 12;
	
	/** The Constant LONGITUD_SUCURSA_CTA_CRED. */
	private static final int LONGITUD_SUCURSA_CTA_CRED = 4;
	
	/** The Constant STRING_VACIO_LONGITUD_1. */
	private static final String STRING_VACIO_LONGITUD_1 = " ";
	
	/** The Constant EGRESO. */
	private static final String EGRESO = "E";
	
	/** The Constant CUIT. */
	private static final String CUIT = "1";
	
	/** The Constant OPER. */
	private static final String OPER = "BCAI";
	
	/** The Constant LONGITUD_CUIT. */
	private static final int LONGITUD_CUIT = 11;
	
	/** The Constant TITULAR_LONGITUD. */
	private static final int TITULAR_LONGITUD = 30;
	
	/** The Constant LONGITUD_NRO_CUENTA_CREDITO. */
	private static final int LONGITUD_NRO_CUENTA_CREDITO = 7;
	
	/** The Constant LONGITUD_NRO_CUENTA_DEBITO. */
	private static final int LONGITUD_NRO_CUENTA_DEBITO = 7;
	
	/** The Constant LONGITUD_SUCURSAL_DEBITO. */
	private static final int LONGITUD_SUCURSAL_DEBITO = 3;
	
	/** The Constant ONLINE. */
	private static final String ONLINE = "O";
	
	/** The Constant PRODUCTO_ALTAIR. */
	private static final String PRODUCTO_ALTAIR = "7";
	
	/** The Constant ZEROS_ZEROS. */
	private static final String ZEROS_ZEROS = "00";
	
	/** The beneficiario. */
	private String beneficiario;
	
	/** The cbuDestino. */
	private String cbuDestino;
	
	/** The comicion. */
	private BigDecimal comision;
	
	/** The concepto. */
	private String concepto;
	
	/** The cuentaAltairDest. */
	private String cuentaAltairDest;
	
	/** The cuentaAltairOrig. */
	private String cuentaAltairOrig;
	
	/** The cuitDestino. */
	private String cuitDestino;
	
	/** The importe. */
	private BigDecimal importe;
	
	/** The modena. */
	private String moneda;
	
	/** The observaciones. */
	private String observaciones;
	
	/** The oper. */
	private String oper;
	
	/** The sucursalDest. */
	private String sucursalDest;
	
	/** The sucursalOrig. */
	private String sucursalOrig;
	
	/** The tipoOrd. */
	private String tipoOrd;
	
	/** The documentoDest. */
	private String documentoDest;
	
	/** The documentoOrig. */
	private String documentoOrig;
	
	/** The entidadDest. */
	private String entidadDest;
	
	/** The modoTranf. */
	private String modoTranf;
	
	/** The tipoCuentaDest. */
	private String tipoCuentaDest;
	
	/** The tipoDocDest. */
	private String tipoDocDest;
	
	public InsertarTransferenciaDTO(TransferenciaDTO transferenciaDTO, Cliente cliente) {
		if(transferenciaDTO.esRioRio()) {
			this.beneficiario = StringUtils.rightPad(transferenciaDTO.getNombreReceptor(), TITULAR_LONGITUD);
			this.cbuDestino = ISBANStringUtils.repeat("0",22);
			this.comision = new BigDecimal(0.0);
			this.concepto = transferenciaDTO.getConcepto().getCodigo();
			this.cuentaAltairDest = PRODUCTO_ALTAIR + ZEROS_ZEROS + StringUtils.leftPad(transferenciaDTO.getNumeroCuentaDestino().getNroCuentaProducto(),
			LONGITUD_NRO_CUENTA_CREDITO, "0");
			this.cuentaAltairOrig = PRODUCTO_ALTAIR + ZEROS_ZEROS + StringUtils.right(transferenciaDTO.getCuentaOrigen().getNroCuentaProducto(), LONGITUD_NRO_CUENTA_DEBITO);
			this.cuitDestino = formatearCuit(transferenciaDTO.getCuit());
			this.importe = transferenciaDTO.getImporte();
			this.moneda = transferenciaDTO.getMoneda().getCodigo();
			this.observaciones = ISBANStringUtils.repeat(" ",50);
			this.oper = OPER;
			String sucDestino = StringUtils.leftPad(transferenciaDTO.getNumeroCuentaDestino().getNroSucursal(),
					LONGITUD_SUCURSAL_DEBITO, "0");
			String sucOrigen = StringUtils.leftPad(transferenciaDTO.getCuentaOrigen().getNroSucursal(),
					LONGITUD_SUCURSAL_DEBITO, "0");
			this.sucursalDest = formatearSucursal(sucDestino, 3);
			this.sucursalOrig = formatearSucursal(sucOrigen, 3);
			this.tipoOrd = EGRESO;
		} else {
			this.beneficiario = transferenciaDTO.getTitular();
			this.cbuDestino = transferenciaDTO.getCbuCuenta();
			this.concepto = transferenciaDTO.getConcepto().getCodigo();
			String numCuentaDest = StringUtils.leftPad(transferenciaDTO.getNumeroCuentaCredito(), LONGITUD_NRO_CUENTA, '0');
			String numCuentaOrig = StringUtils.right(transferenciaDTO.getCuentaOrigen().getNroCuentaProducto(), LONGITUD_NRO_CTA);
			this.cuentaAltairDest = PRODUCTO_ALTAIR + ZEROS_ZEROS + numCuentaDest.substring(numCuentaDest.length()-7);
			this.cuentaAltairOrig = PRODUCTO_ALTAIR + ZEROS_ZEROS + numCuentaOrig.substring(numCuentaOrig.length()-7);
			this.documentoDest = formatearCuit(transferenciaDTO.getCuit());
			String dni = new String(cliente.getDni());
			this.documentoOrig = dni;
			this.entidadDest = transferenciaDTO.getBancoDestino();
			this.importe = transferenciaDTO.getImporte();
			this.modoTranf = ONLINE;
			this.moneda = transferenciaDTO.getMoneda().getCodigo();
			this.observaciones = ISBANStringUtils.repeat(" ",50);
			this.oper = OPER;
			this.sucursalDest = StringUtils.leftPad(transferenciaDTO.getSucursalCuentaCredito(), LONGITUD_SUCURSA_CTA_CRED, '0');
			this.tipoCuentaDest = esTransferenciaOtrosBancosCuentaPropia(transferenciaDTO, cliente);
			this.tipoDocDest = CUIT;
			this.tipoOrd = EGRESO;
		}
		
	}

	/**
	 * @return the beneficiario
	 */
	public String getBeneficiario() {
		return beneficiario;
	}

	/**
	 * @param beneficiario the beneficiario to set
	 */
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	/**
	 * @return the cbuDestino
	 */
	public String getCbuDestino() {
		return cbuDestino;
	}

	/**
	 * @param cbuDestino the cbuDestino to set
	 */
	public void setCbuDestino(String cbuDestino) {
		this.cbuDestino = cbuDestino;
	}

	/**
	 * @return the comicion
	 */
	public BigDecimal getComicion() {
		return comision;
	}

	/**
	 * @param comicion the comicion to set
	 */
	public void setComicion(BigDecimal comicion) {
		this.comision = comicion;
	}

	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return the cuentaAltairDest
	 */
	public String getCuentaAltairDest() {
		return cuentaAltairDest;
	}

	/**
	 * @param cuentaAltairDest the cuentaAltairDest to set
	 */
	public void setCuentaAltairDest(String cuentaAltairDest) {
		this.cuentaAltairDest = cuentaAltairDest;
	}

	/**
	 * @return the cuentaAltairOrig
	 */
	public String getCuentaAltairOrig() {
		return cuentaAltairOrig;
	}

	/**
	 * @param cuentaAltairOrig the cuentaAltairOrig to set
	 */
	public void setCuentaAltairOrig(String cuentaAltairOrig) {
		this.cuentaAltairOrig = cuentaAltairOrig;
	}

	/**
	 * @return the cuitDestino
	 */
	public String getCuitDestino() {
		return cuitDestino;
	}

	/**
	 * @param cuitDestino the cuitDestino to set
	 */
	public void setCuitDestino(String cuitDestino) {
		this.cuitDestino = cuitDestino;
	}

	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the modena
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param modena the modena to set
	 */
	public void setMoneda(String modena) {
		this.moneda = modena;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the oper
	 */
	public String getOper() {
		return oper;
	}

	/**
	 * @param oper the oper to set
	 */
	public void setOper(String oper) {
		this.oper = oper;
	}

	/**
	 * @return the sucursalDest
	 */
	public String getSucursalDest() {
		return sucursalDest;
	}

	/**
	 * @param sucursalDest the sucursalDest to set
	 */
	public void setSucursalDest(String sucursalDest) {
		this.sucursalDest = sucursalDest;
	}

	/**
	 * @return the tipoOrd
	 */
	public String getTipoOrd() {
		return tipoOrd;
	}

	/**
	 * @param tipoOrd the tipoOrd to set
	 */
	public void setTipoOrd(String tipoOrd) {
		this.tipoOrd = tipoOrd;
	}
	
	/**
	 * @return the comision
	 */
	public BigDecimal getComision() {
		return comision;
	}

	/**
	 * @param comision the comision to set
	 */
	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	/**
	 * @return the documentoDest
	 */
	public String getDocumentoDest() {
		return documentoDest;
	}

	/**
	 * @param documentoDest the documentoDest to set
	 */
	public void setDocumentoDest(String documentoDest) {
		this.documentoDest = documentoDest;
	}

	/**
	 * @return the documentoOrig
	 */
	public String getDocumentoOrig() {
		return documentoOrig;
	}

	/**
	 * @param documentoOrig the documentoOrig to set
	 */
	public void setDocumentoOrig(String documentoOrig) {
		this.documentoOrig = documentoOrig;
	}

	/**
	 * @return the entidadDest
	 */
	public String getEntidadDest() {
		return entidadDest;
	}

	/**
	 * @param entidadDest the entidadDest to set
	 */
	public void setEntidadDest(String entidadDest) {
		this.entidadDest = entidadDest;
	}

	/**
	 * @return the modoTranf
	 */
	public String getModoTranf() {
		return modoTranf;
	}

	/**
	 * @param modoTranf the modoTranf to set
	 */
	public void setModoTranf(String modoTranf) {
		this.modoTranf = modoTranf;
	}

	/**
	 * @return the tipoCuentaDest
	 */
	public String getTipoCuentaDest() {
		return tipoCuentaDest;
	}

	/**
	 * @param tipoCuentaDest the tipoCuentaDest to set
	 */
	public void setTipoCuentaDest(String tipoCuentaDest) {
		this.tipoCuentaDest = tipoCuentaDest;
	}

	/**
	 * @return the tipoDocDest
	 */
	public String getTipoDocDest() {
		return tipoDocDest;
	}

	/**
	 * @param tipoDocDest the tipoDocDest to set
	 */
	public void setTipoDocDest(String tipoDocDest) {
		this.tipoDocDest = tipoDocDest;
	}
	
	

	/**
	 * @return the sucursalOrig
	 */
	public String getSucursalOrig() {
		return sucursalOrig;
	}

	/**
	 * @param sucursalOrig the sucursalOrig to set
	 */
	public void setSucursalOrig(String sucursalOrig) {
		this.sucursalOrig = sucursalOrig;
	}

	/**
	 * Formatear cuit.
	 *
	 * @param cuit
	 *            the cuit
	 * @return the string
	 */
	private String formatearCuit(String cuit) {
		if (cuit == null || "".equalsIgnoreCase(cuit.trim())) {
			return StringUtils.repeat(STRING_VACIO_LONGITUD_1, LONGITUD_CUIT);
		}
		String cuitSinGuiones = cuit.replaceAll("-", "");
		if (cuitSinGuiones.matches(String.format(Locale.US, "\\d{%d}", LONGITUD_CUIT))) {
			return cuitSinGuiones;
		}
		throw new IllegalArgumentException("CUIT/CUIL no formateable: [" + cuit + "].");
	}
	
	/**
	 * @param transferenciaDTO
	 * @param Cliente
	 * @return String
	 */
    private String esTransferenciaOtrosBancosCuentaPropia(TransferenciaDTO transferenciaDTO, Cliente clienteSesion) {
        List<String> cuitDestinoList = Arrays.asList(transferenciaDTO.getCuit(), transferenciaDTO.getCuit2(), transferenciaDTO.getCuit3());
        if (StringUtils.isNotBlank(clienteSesion.getNumeroCUILCUIT()) && cuitDestinoList.contains(StringUtils.replace(clienteSesion.getNumeroCUILCUIT(), "-", StringUtils.EMPTY))) {
            return "01"; 
        } else {
            return SietePorVeintiCuatroConstant.CARACTERISTICA_CUENTA_CREDITO;
        }
    }
	
    /**
     * pasar de un formato a 4 cuatro caracteres y a 3 caracteres. 
	 * @param String
	 * @param int
	 * @return String
	 */
	private String formatearSucursal(String sucursal, int tamMaximo) {
		if(ISBANStringUtils.isEmptyOrNull(sucursal)) {
    		return "";
    	}
		int tamSucursal = sucursal.length(); 
		int cantCaracSobrantes = tamSucursal - tamMaximo;
		if(cantCaracSobrantes == 0) {
			return sucursal;
		}
		String numSucursal = sucursal.substring(cantCaracSobrantes, tamSucursal);
		return numSucursal;
		
	}
}
