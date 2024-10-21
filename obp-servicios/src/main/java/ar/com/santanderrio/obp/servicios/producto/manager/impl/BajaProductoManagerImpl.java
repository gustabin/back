/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.producto.bo.BajaProductoBO;
import ar.com.santanderrio.obp.servicios.producto.dto.BajaProductoDTO;
import ar.com.santanderrio.obp.servicios.producto.dto.ObtenerBajaProductoDTO;
import ar.com.santanderrio.obp.servicios.producto.dto.ProductoDTO;
import ar.com.santanderrio.obp.servicios.producto.manager.BajaProductoManager;
import ar.com.santanderrio.obp.servicios.producto.view.ArrepentimientoProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.BajaProductoInView;
import ar.com.santanderrio.obp.servicios.producto.view.BajaProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.ComprobanteBajaProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.ProductoArrepentimientoEnum;
import ar.com.santanderrio.obp.servicios.producto.view.ProductoDescripcionArrepentimiento;
import ar.com.santanderrio.obp.servicios.producto.view.ProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.TipoOperacionBajaProductoEnum;

/**
 * The Class BajaProductoManagerImpl.
 */
@Component
public class BajaProductoManagerImpl implements BajaProductoManager {

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The baja producto BO. */
	@Autowired
	private BajaProductoBO bajaProductoBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;
	
    @Autowired
    MensajeBO mensajeBO;
	
	/** The legal BO. */
	@Autowired
	protected LegalBO legalBO;

    @Autowired
    private SesionCliente sesionCliente;
	
    
    private static final String CODIGO_OPERACION_PRESTAMOS_BAJA_GENERICA = "115";
	
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.producto.manager.BajaProductoManager#
	 * obtenerProductos(ar.com.santanderrio.obp.servicios.producto.view.
	 * BajaProductoInView)
	 */
	@Override
	public Respuesta<BajaProductoView> obtenerProductos(BajaProductoInView bajaProductoInView) {
		// tipoOperacionEnum=TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA
		
		Respuesta<ObtenerBajaProductoDTO> respuestaDTO = new Respuesta<ObtenerBajaProductoDTO>();
		TipoOperacionBajaProductoEnum tipoOperacionEnum = null;
		if (TipoOperacionBajaProductoEnum.BAJA_TARJETA.getCodigo().equals(bajaProductoInView.getCodigoOperacion())) {
			respuestaDTO = bajaProductoBO.obtenerProductosBajaTarjeta();
			tipoOperacionEnum = TipoOperacionBajaProductoEnum.BAJA_TARJETA;
		}
		if (TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA.getCodigo()
				.equals(bajaProductoInView.getCodigoOperacion())) {
			respuestaDTO = bajaProductoBO.obtenerProductosPaquetesCuenta();
			tipoOperacionEnum = TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA;
		}

		if (EstadoRespuesta.OK.equals(respuestaDTO.getEstadoRespuesta())) {
			BajaProductoView bajaProductoView = new BajaProductoView();
			List<ProductoView> productosView = new ArrayList<ProductoView>();
			if (respuestaDTO.getRespuesta().getProductos().isEmpty()) {		
				return respuestaFactory.crearRespuestaWarning("Atencion", TipoError.WARNING_USUARIO_SIN_PRODUCTOS_DISPONIBLES, CodigoMensajeConstantes.USUARIO_SIN_PRODUCTOS_DISPONIBLES);
			}
			for (ProductoDTO productoDTO : respuestaDTO.getRespuesta().getProductos()) {

				ProductoView productoView = new ProductoView();
				productoView.setCodigo(productoDTO.getCodigo());
				productoView.setTipoProducto(productoDTO.getTipoProducto());
				productoView.setDescripcion(productoDTO.getDescripcion());
				productoView.setCuenta(productoDTO.getCuenta());
				productoView.setNumero(productoDTO.getNumero());
				productoView.setRecargable(productoDTO.getRecargable());
				productosView.add(productoView);
			}
			bajaProductoView.setCodigoOperacion("2");
			bajaProductoView.setProductos(productosView);
			bajaProductoView.setTipoOperacion(tipoOperacionEnum);
			
			Respuesta<String> respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGAL_BAJA_PRODUCTO);
			if (EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
				bajaProductoView.setLegales(respuestaLegales.getRespuesta());
			}
			
			estadisticaManager.add(EstadisticasConstants.BAJA_PRODUCTO_INGRESAR,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			bajaProductoView.setMensajeOferta(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BAJA_PRODUCTO_ALERTA).getMensaje());
			
			return respuestaFactory.crearRespuestaOk(BajaProductoView.class, bajaProductoView);
		}

		estadisticaManager.add(EstadisticasConstants.BAJA_PRODUCTO_INGRESAR,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
		        CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_ERROR);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.producto.manager.BajaProductoManager#
	 * darBajaProducto(ar.com.santanderrio.obp.servicios.producto.view.
	 * BajaProductoView)
	 */
	public Respuesta<ComprobanteBajaProductoView> darBajaProducto(BajaProductoView bajaProductoView) {

		BajaProductoDTO bajaProductoDTO = new BajaProductoDTO();
		
		if (ProductoArrepentimientoEnum.CAJA_DE_SEGURIDAD.getProducto().equalsIgnoreCase(bajaProductoView.getProducto())) {
			ComprobanteBajaProductoView comprobanteBajaView = new ComprobanteBajaProductoView();
			DateTime fechaHoy = new DateTime();
			String nroComprobante = fechaHoy.toString("ddMMyyHHmmssSSS");
			comprobanteBajaView.setNroComprobante(nroComprobante);
			comprobanteBajaView.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.BAJA_ARREPENTIMIENTO_CAJA_SEGURIDAD).getMensaje());
			comprobanteBajaView.setFechaDeBaja(ISBANStringUtils.formatearFecha(new Date(), ISBANStringUtils.FORMATO_FECHA));
			comprobanteBajaView.setFechaOperacion(ISBANStringUtils.formatearFecha(new Date(), ISBANStringUtils.FORMATO_FECHA_HORA_COMPROBANTE));
			comprobanteBajaView.setTipoProducto(ProductoArrepentimientoEnum.CAJA_DE_SEGURIDAD.getProducto());
			comprobanteBajaView.setTipoOperacion(TipoOperacionBajaProductoEnum.ARREPENTIMIENTO);
			comprobanteBajaView.setDescripcionProducto(bajaProductoView.getProducto());
			comprobanteBajaView.setArrepentimiento(bajaProductoView.getArrepentimiento());
			sessionParametros.setComprobanteBajaProducto(comprobanteBajaView);
			estadisticaManager.add(EstadisticasConstants.CONFIRMAR_SOLICITUD_BAJA_ARREPENTIMIENTO_CAJA_SEGURIDAD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK, nroComprobante, new BigDecimal("0"), null );
			return respuestaFactory.crearRespuestaOk(ComprobanteBajaProductoView.class, comprobanteBajaView);
		}
		
		if (bajaProductoView.getCodigoProducto() == null) {
			ProductoArrepentimientoEnum productoEnum = ProductoArrepentimientoEnum.fromProductoString(bajaProductoView.getProducto());
			bajaProductoDTO.setDescripcionArrepentimiento(bajaProductoView.getDescripcion());
			bajaProductoDTO.setCodigo(productoEnum.getCodOperacionArrepentimiento());
			bajaProductoDTO.setTipoProducto(productoEnum.getProducto());
			Respuesta<BajaProductoDTO> respuestaBO = bajaProductoBO.solicitarArrepentimientoProducto(bajaProductoDTO);
			
			if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
				ComprobanteBajaProductoView comprobanteBajaView = new ComprobanteBajaProductoView();
				comprobanteBajaView.setNroComprobante(respuestaBO.getRespuesta().getComprobante());
				comprobanteBajaView.setMensaje(respuestaBO.getRespuesta().getMensaje());
				comprobanteBajaView.setFechaDeBaja(ISBANStringUtils.formatearFecha(new Date(), ISBANStringUtils.FORMATO_FECHA));
				comprobanteBajaView.setFechaOperacion(ISBANStringUtils.formatearFecha(new Date(), ISBANStringUtils.FORMATO_FECHA_HORA_COMPROBANTE));
				comprobanteBajaView.setTipoProducto(bajaProductoDTO.getTipoProducto());
				comprobanteBajaView.setTipoOperacion(TipoOperacionBajaProductoEnum.ARREPENTIMIENTO);
				comprobanteBajaView.setDescripcionProducto(bajaProductoView.getProducto());
				comprobanteBajaView.setArrepentimiento(bajaProductoView.getArrepentimiento());
				sessionParametros.setComprobanteBajaProducto(comprobanteBajaView);
				estadisticaManager.add(CODIGO_OPERACION_PRESTAMOS_BAJA_GENERICA.equals(productoEnum.getCodOperacion()) ? EstadisticasConstants.CONFIRMAR_SOLICITUD_BAJA_GENERICA : 
					EstadisticasConstants.CONFIRMAR_SOLICITUD_ARREPENTIMIENTO_PRODUCTOS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaOk(ComprobanteBajaProductoView.class, comprobanteBajaView);
			}
			estadisticaManager.add(CODIGO_OPERACION_PRESTAMOS_BAJA_GENERICA.equals(productoEnum.getCodOperacion()) ? EstadisticasConstants.CONFIRMAR_SOLICITUD_BAJA_GENERICA : 
				EstadisticasConstants.CONFIRMAR_SOLICITUD_ARREPENTIMIENTO_PRODUCTOS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
			        CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_ERROR);
		}
		
		bajaProductoDTO.setCodigo(bajaProductoView.getCodigoProducto().getCodigo());


		if (TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA.getCodigo().equals(bajaProductoView.getCodigoOperacion())) {
			StringBuffer descripcion = new StringBuffer(bajaProductoView.getCodigoProducto().getDescripcion());
			descripcion.append(" ");
			descripcion.append(bajaProductoView.getCodigoProducto().getNumero());
			bajaProductoDTO.setDescripcion(descripcion.toString());
		} else {
			bajaProductoDTO.setDescripcion(bajaProductoView.getCodigoProducto().getDescripcion());
		}
		bajaProductoDTO.setCuenta(String.valueOf(bajaProductoView.getCodigoProducto().getCuenta()));
		bajaProductoDTO.setTipoOperacion(obtenerTipo(bajaProductoView));
		bajaProductoDTO.setTipoProducto(bajaProductoView.getCodigoProducto().getTipoProducto());
		bajaProductoDTO.setBajaCA(bajaProductoView.getBajaCA());
		bajaProductoDTO.setRecargable(bajaProductoView.getCodigoProducto().getRecargable());
		bajaProductoDTO.setArrepentimiento(bajaProductoView.getArrepentimiento());
		Respuesta<BajaProductoDTO> respuestaBO = bajaProductoBO.solicitarBajaProducto(bajaProductoDTO);

		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			ComprobanteBajaProductoView comprobanteBajaView = new ComprobanteBajaProductoView();
			comprobanteBajaView.setNroComprobante(respuestaBO.getRespuesta().getComprobante());
			comprobanteBajaView.setMensaje(respuestaBO.getRespuesta().getMensaje());
			comprobanteBajaView.setDescripcionProducto(bajaProductoView.getCodigoProducto().getDescripcion());
			comprobanteBajaView.setFechaDeBaja(ISBANStringUtils.formatearFecha(new Date(), ISBANStringUtils.FORMATO_FECHA));
			comprobanteBajaView.setFechaOperacion(ISBANStringUtils.formatearFecha(new Date(), ISBANStringUtils.FORMATO_FECHA_HORA_COMPROBANTE));
			comprobanteBajaView.setTipoOperacion(bajaProductoDTO.getTipoOperacion());
			comprobanteBajaView.setTipoProducto(bajaProductoDTO.getTipoProducto());
			comprobanteBajaView.setNumero(bajaProductoView.getCodigoProducto().getNumero());
			comprobanteBajaView.setMantieneCajaAhorro(bajaProductoView.getBajaCA());
			sessionParametros.setComprobanteBajaProducto(comprobanteBajaView);
			estadisticaManager.add(bajaProductoView.getArrepentimiento() ? EstadisticasConstants.CONFIRMAR_SOLICITUD_ARREPENTIMIENTO_PRODUCTOS : EstadisticasConstants.BAJA_PRODUCTO_CONFIRMAR,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(ComprobanteBajaProductoView.class, comprobanteBajaView);
		}

		estadisticaManager.add(bajaProductoView.getArrepentimiento() ? EstadisticasConstants.CONFIRMAR_SOLICITUD_ARREPENTIMIENTO_PRODUCTOS : EstadisticasConstants.BAJA_PRODUCTO_CONFIRMAR,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
		        CodigoMensajeConstantes.CODIGO_MENSAJE_BAJAPROD_ERROR);
	}

	/**
	 * Obtener tipo.
	 *
	 * @param bajaProductoView
	 *            the baja producto view
	 * @return the tipo operacion baja producto enum
	 */
	private TipoOperacionBajaProductoEnum obtenerTipo(BajaProductoView bajaProductoView) {

		if (TipoOperacionBajaProductoEnum.BAJA_TARJETA.getCodigo().equals(bajaProductoView.getCodigoOperacion())) {
			return TipoOperacionBajaProductoEnum.BAJA_TARJETA;
		}
		if (TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA.getCodigo()
				.equals(bajaProductoView.getCodigoOperacion())) {
			return TipoOperacionBajaProductoEnum.BAJA_PAQUETES_CUENTA;
		}
		if (TipoOperacionBajaProductoEnum.OTROS_MEDIO_PAGO.getCodigo().equals(bajaProductoView.getCodigoOperacion())) {
			return TipoOperacionBajaProductoEnum.OTROS_MEDIO_PAGO;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.producto.manager.BajaProductoManager#
	 * generarComprobante()
	 */
	@Override
	public Respuesta<ReporteView> generarComprobante() {
		Respuesta<Reporte> reporteRespuesta = bajaProductoBO
				.generarComprobante(sessionParametros.getComprobanteBajaProducto());
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
			estadisticaManager.add(EstadisticasConstants.BAJA_PRODUCTO_COMPROBANTE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.BAJA_PRODUCTO_COMPROBANTE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	
	@Override
	public Respuesta<ArrepentimientoProductoView> obtenerProductosArrepentimiento() {

		ArrepentimientoProductoView productosParaArrepentir = new ArrepentimientoProductoView();
		List<ProductoDescripcionArrepentimiento> listaProductos = new ArrayList<ProductoDescripcionArrepentimiento>();
		List<ProductoDescripcionArrepentimiento> listaProductosArrepentimiento = new ArrayList<ProductoDescripcionArrepentimiento>();
		
		for (ProductoArrepentimientoEnum producto : ProductoArrepentimientoEnum.values()) {
			ProductoDescripcionArrepentimiento productoView = new ProductoDescripcionArrepentimiento();
			productoView.setProducto(producto.getProducto());
			productoView.setTieneFlujoBaja(producto.getTieneFlujoBaja());
			productoView.setValue(producto.getValue());
			productoView.setPlaceholder(producto.getPlaceholder());
			productoView.setCodOperacion(producto.getCodOperacion());
			listaProductos.add(productoView);
		}		
		
		productosParaArrepentir.setListaProductosBaja(listaProductos);
		
		for (ProductoArrepentimientoEnum producto : ProductoArrepentimientoEnum.values()) {
			if(!CODIGO_OPERACION_PRESTAMOS_BAJA_GENERICA.equals(producto.getCodOperacion())) {
				ProductoDescripcionArrepentimiento productoView = new ProductoDescripcionArrepentimiento();
				productoView.setProducto(producto.getProducto());
				productoView.setTieneFlujoBaja(producto.getTieneFlujoBaja());
				productoView.setValue(producto.getValue());
				productoView.setPlaceholder(producto.getPlaceholder());
				productoView.setCodOperacion(producto.getCodOperacion());
				listaProductosArrepentimiento.add(productoView);
			}
		}	
		
		productosParaArrepentir.setListaProductosArrepentimiento(listaProductosArrepentimiento);

		try {
			productosParaArrepentir.setLegal(legalBO.obtenerLegal(CodigoMensajeConstantes.ARREPENTIMIENTO_PRODUCTO_LEGAL_CONFIGURACION));
			productosParaArrepentir.setLegalBaja(legalBO.obtenerLegal(CodigoMensajeConstantes.ARREPENTIMIENTO_PRODUCTO_LEGAL_BAJA_CONFIGURACION));
		} catch (DAOException e) {
			estadisticaManager.add(EstadisticasConstants.INICIO_FLUJO_BAJA_ARREPENTIMIENTO_PRODUCTOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		
		estadisticaManager.add(EstadisticasConstants.INICIO_FLUJO_BAJA_ARREPENTIMIENTO_PRODUCTOS,
			EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(productosParaArrepentir);
		
	}
		
}
