/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

/**
 * The Class DetalleOperacionCompraVentaView.
 */
public class DetalleOperacionCompraVentaView extends OperacionTitulosView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The accion. */
	private String accion;
	
	/** The estado. */
	private String estado;
	
	/** The plazo de liquidacion. */
	private String plazoDeLiquidacion;
	
	/** The mercado. */
	private String mercado;
	
	/** The precio limite. */
	private String precioLimite;
	
	/** The precio referencia. */
	private String precioReferencia;
	
	/** The importe. */
	private String importe;
	
	/** The cuenta titulos. */
	private String cuentaTitulos;
	
	/** The cuenta destino. */
	private String cuentaDestino;
	
	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;
	
	/** The comisiones. */
	private String comisiones;
	
	/** The impuestos. */
	private String impuestos;
	
	/** The canal ingreso. */
	private String canalIngreso;
	
	/** The descripcion codigo. */
	private String descripcionCodigo;
	
	/** The abreviatura caja valores. */
	private String abreviaturaCajaValores;
	
	/** The numero operacion. */
	private String numeroOperacion;
	
	/** The derechos. */
	private String derechos;
	
	/** The numero orden key. */
	protected final String numeroOrdenKey = "NUMERO_ORDEN";
	
	/** The accion key. */
	protected final String accionKey = "ACCION";
	
	/** The plazo liquidacion key. */
	protected final String plazoLiquidacionKey = "PLAZO_LIQUIDACION";
	
	/** The mercado key. */
	protected final String mercadoKey = "MERCADO";
	
	/** The precio limite key. */
	protected final String precioLimiteKey = "PRECIO_LIMITE";
	
	/** The precio referencia key. */
	protected final String precioReferenciaKey = "PRECIO_REFERENCIA";
	
	/** The cuenta destino numero key. */
	protected final String cuentaDestinoNumeroKey = "CUENTA_DESTINO_NUMERO";
	
	/** The cuenta destino tipo key. */
	protected final String cuentaDestinoTipoKey = "CUENTA_DESTINO_TIPO";
	
	/** The descripcion codigo key. */
	protected final String descripcionCodigoKey = "DESCRIPCION_CODIGO";
	
	/** The canal key. */
	protected final String canalKey = "CANAL";
	
	/** The derechos key. */
	protected final String derechosKey = "DERECHOS";
	
	/** The consulta operacion compra jasper. */
	protected final String consultaOperacionCompraJasper = "consultaOperacionCompraTitulo.jasper";
	
	/** The consulta operacion venta jasper. */
	protected final String consultaOperacionVentaJasper = "consultaOperacionVentaTitulo.jasper";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView#getTipoCuentaDestino()
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView#setTipoCuentaDestino(java.lang.String)
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion
	 *            the new accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#getEstado()
	 */
	public String getEstado() {
		return estado;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#setEstado(java.lang.String)
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the plazo de liquidacion.
	 *
	 * @return the plazo de liquidacion
	 */
	public String getPlazoDeLiquidacion() {
		return plazoDeLiquidacion;
	}

	/**
	 * Sets the plazo de liquidacion.
	 *
	 * @param plazoDeLiquidacion
	 *            the new plazo de liquidacion
	 */
	public void setPlazoDeLiquidacion(String plazoDeLiquidacion) {
		this.plazoDeLiquidacion = plazoDeLiquidacion;
	}

	/**
	 * Gets the mercado.
	 *
	 * @return the mercado
	 */
	public String getMercado() {
		return mercado;
	}

	/**
	 * Sets the mercado.
	 *
	 * @param mercado
	 *            the new mercado
	 */
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	/**
	 * Gets the precio limite.
	 *
	 * @return the precio limite
	 */
	public String getPrecioLimite() {
		return precioLimite;
	}

	/**
	 * Sets the precio limite.
	 *
	 * @param precioLimite
	 *            the new precio limite
	 */
	public void setPrecioLimite(String precioLimite) {
		this.precioLimite = precioLimite;
	}

	/**
	 * Gets the precio referencia.
	 *
	 * @return the precio referencia
	 */
	public String getPrecioReferencia() {
		return precioReferencia;
	}

	/**
	 * Sets the precio referencia.
	 *
	 * @param precioReferencia
	 *            the new precio referencia
	 */
	public void setPrecioReferencia(String precioReferencia) {
		this.precioReferencia = precioReferencia;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the cuenta titulos.
	 *
	 * @return the cuenta titulos
	 */
	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulos.
	 *
	 * @param cuentaTitulos
	 *            the new cuenta titulos
	 */
	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	/**
	 * Gets the cuenta destino.
	 *
	 * @return the cuenta destino
	 */
	public String getCuentaDestino() {
		return cuentaDestino;
	}

	/**
	 * Sets the cuenta destino.
	 *
	 * @param cuentaDestino
	 *            the new cuenta destino
	 */
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	/**
	 * Gets the comisiones.
	 *
	 * @return the comisiones
	 */
	public String getComisiones() {
		return comisiones;
	}

	/**
	 * Sets the comisiones.
	 *
	 * @param comisiones
	 *            the new comisiones
	 */
	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the new impuestos
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the canal ingreso.
	 *
	 * @return the canal ingreso
	 */
	public String getCanalIngreso() {
		return canalIngreso;
	}

	/**
	 * Sets the canal ingreso.
	 *
	 * @param canalIngreso
	 *            the new canal ingreso
	 */
	public void setCanalIngreso(String canalIngreso) {
		this.canalIngreso = canalIngreso;
	}

	/**
	 * Gets the descripcion codigo.
	 *
	 * @return the descripcion codigo
	 */
	public String getDescripcionCodigo() {
		return descripcionCodigo;
	}

	/**
	 * Sets the descripcion codigo.
	 *
	 * @param descripcionCodigo
	 *            the new descripcion codigo
	 */
	public void setDescripcionCodigo(String descripcionCodigo) {
		this.descripcionCodigo = descripcionCodigo;
	}

	/**
	 * Gets the abreviatura caja valores.
	 *
	 * @return the abreviatura caja valores
	 */
	public String getAbreviaturaCajaValores() {
		return abreviaturaCajaValores;
	}

	/**
	 * Sets the abreviatura caja valores.
	 *
	 * @param abreviaturaCajaValores
	 *            the new abreviatura caja valores
	 */
	public void setAbreviaturaCajaValores(String abreviaturaCajaValores) {
		this.abreviaturaCajaValores = abreviaturaCajaValores;
	}

	/**
	 * Gets the numero operacion.
	 *
	 * @return the numeroOperacion
	 */
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	/**
	 * Sets the numero operacion.
	 *
	 * @param numeroOperacion
	 *            the numeroOperacion to set
	 */
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#obtenerParametrosPDF()
	 */
	@Override
	public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(descripcionKey, StringUtils.trim(getDescripcion()));
		parametros.put(descripcionCodigoKey, getAbreviaturaCajaValores());
		parametros.put(fechaKey, getFecha());
		parametros.put(numeroOrdenKey, getNumero());
		parametros.put(numeroOperacionKey, getNumeroOperacion());
		parametros.put(accionKey, getAccion());
		parametros.put(estadoKey, getEstado());
		parametros.put(tipoEspecieKey, getTipo());
		parametros.put(monedaKey, getMoneda());
		parametros.put(cantidadNominalesKey, getCantidadNominales());
		parametros.put(plazoLiquidacionKey, getPlazoDeLiquidacion());
		parametros.put(mercadoKey, getMercado());
		parametros.put(precioKey, getPrecio());
		parametros.put(precioLimiteKey, getPrecioLimite());
		parametros.put(precioReferenciaKey, getPrecioReferencia());
		parametros.put(importeKey, getImporte());
		parametros.put(cuentaTitulosKey, getCuentaTitulos());
		parametros.put(cuentaDestinoNumeroKey, getCuentaDestino());
		parametros.put(cuentaDestinoTipoKey, getTipoCuentaDestino());
		parametros.put(comisionesKey, getComisiones());
		parametros.put(impuestosKey, getImpuestos());
		parametros.put(derechosKey, getDerechos());
		parametros.put(canalKey, getCanalIngreso() != null ? getCanalIngreso() : "");
		parametros.put(legalKey, getLegal());
		return parametros;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		Boolean esCompra = StringUtils.equalsIgnoreCase(getAccion(), "compra");
		if (esCompra) {
			return ResourceUtils.getFile(path + pathInversiones + consultaOperacionCompraJasper).getPath();
		}
		return ResourceUtils.getFile(path + pathInversiones + consultaOperacionVentaJasper).getPath();
	}

    /**
	 * Gets the derechos.
	 *
	 * @return the derechos
	 */
    public String getDerechos() {
        return derechos;
    }

    /**
	 * Sets the derechos.
	 *
	 * @param derechos
	 *            the new derechos
	 */
    public void setDerechos(String derechos) {
        this.derechos = derechos;
    }

}
