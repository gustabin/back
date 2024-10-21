package ar.com.santanderrio.obp.servicios.inversiones.descargapdf.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaResumenCuentaBP;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenMesualCuentaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.bo.DescargaPdfBO;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.ListadoPDF;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.manager.DescargaPdfManager;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobantesPorCuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListaFechasComprobantes;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

@Component
public class DescargaPdfManagerImpl extends InversionesAbstractBO implements DescargaPdfManager {

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;
	
    @Autowired
    private SessionResumenCuenta sesionResumenCuenta;
    
    @Autowired
    private DescargaPdfBO descargaPDFBO;
    
	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;
	
	@Autowired
	private MensajeBO mensajeBO;
    
	
	@Override
	public Respuesta<ListaFechasComprobantes> obtenerListaComprobantes(TipoPDFEnum tipoPDF) {

		Cliente cliente = sesionCliente.getCliente();
		ListaFechasComprobantes listaFechas = new ListaFechasComprobantes();
		List<ComprobantesPorCuentaView> listaRespuestasCuentasFechas = new ArrayList<ComprobantesPorCuentaView>();
		Boolean hayError = Boolean.FALSE;
		ListaFechasComprobantes listaFechasComprobantesSesion = recuperarListaDeComprobantesDeSesion(tipoPDF);
		String estadisticaCorrespondiente = setearEstadisticaFechasComprobantes(tipoPDF);
		
		if (listaFechasComprobantesSesion == null) {
			for (CuentaBancaPrivada cuentaBP : cliente.getCuentasBancaPrivada()) {
				Respuesta<List<ResumenMensualCuenta>> listaFechasComprobantesDesdeServicio;
				List<Interviniente> listaIntervinientes = obtenerIntervinientes((Cuenta) cuentaBP.getCuentaOperativa());		
				try {
					listaFechasComprobantesDesdeServicio = descargaPDFBO.obtenerListaComprobantes(cuentaBP.getCuentaTitulo(), tipoPDF);
				} catch (BusinessException e) {
					estadisticaManager.add(estadisticaCorrespondiente, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					listaFechasComprobantesDesdeServicio = respuestaFactory.crearRespuestaError(null, TipoError.CODIGO_ERROR_LISTA_RESUMENES_ERROR,
		                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
				} catch (WSODException e) {
					estadisticaManager.add(estadisticaCorrespondiente, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					listaFechasComprobantesDesdeServicio = respuestaFactory.crearRespuestaError(null, TipoError.CODIGO_ERROR_LISTA_RESUMENES_ERROR,
		                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
				}
				
				ComprobantesPorCuentaView respResumenMensual = armarRespuestaListaFechasCuentaView(listaFechasComprobantesDesdeServicio,
																cuentaBP, estadisticaCorrespondiente, listaIntervinientes);
				listaRespuestasCuentasFechas.add(respResumenMensual);
				if (EstadoRespuesta.ERROR.equals(respResumenMensual.getEstado())) {
					hayError = Boolean.TRUE;
				}
			}
			
			if (listaRespuestasCuentasFechas.isEmpty()) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			
			listaFechas.setCuentas(listaRespuestasCuentasFechas);	
			grabarListaEnSesion(hayError, tipoPDF, listaFechas);
			
			return respuestaFactory.crearRespuestaOk(listaFechas);
		} else {
			return respuestaFactory.crearRespuestaOk(listaFechasComprobantesSesion);
		}
	}
	
	private ListaFechasComprobantes recuperarListaDeComprobantesDeSesion (TipoPDFEnum tipoPDF) {
		
		return TipoPDFEnum.TITULOS_VALORES.equals(tipoPDF) ? 
				sessionParametros.getListaFechasComprobantesTVBpriv() : sessionParametros.getListaFechasComprobantesFCIBpriv();
	}
	
	private void grabarListaEnSesion(Boolean hayError, TipoPDFEnum tipoPDF, ListaFechasComprobantes listaFechas) {
		
		if (!hayError && TipoPDFEnum.TITULOS_VALORES.equals(tipoPDF)) {
			sessionParametros.setListaFechasComprobantesTVBpriv(listaFechas);
		} else if (!hayError && TipoPDFEnum.FONDOS_COMUNES_INVERSION.equals(tipoPDF)) {
			sessionParametros.setListaFechasComprobantesFCIBpriv(listaFechas);
		}
		
	}
	
	private String setearEstadisticaFechasComprobantes(TipoPDFEnum tipoPDF) {
		
		String estadistica;
		if (TipoPDFEnum.FONDOS_COMUNES_INVERSION.equals(tipoPDF)) {
			estadistica = EstadisticasConstants.VISUALIZAR_FECHAS_COMPROBANTES_FCI_BANCA_PRIVADA;
		} else {
			estadistica = EstadisticasConstants.VISUALIZAR_FECHAS_COMPROBANTES_TV_BANCA_PRIVADA;
		}
		return estadistica;
	}
	
    private ComprobantesPorCuentaView armarRespuestaListaFechasCuentaView( Respuesta<List<ResumenMensualCuenta>> respuestaResumenesList, 
    		CuentaBancaPrivada cuenta, String estadisticaCorrespondiente, List<Interviniente> listaIntervinientes) {
        List<ResumenMesualCuentaView> resumenMesualCuentaViewList = new ArrayList<ResumenMesualCuentaView>();
		ComprobantesPorCuentaView comprobantesPorCuentaView = new ComprobantesPorCuentaView();
		String numeroCuentaOperativa = ISBANStringUtils.formatearNumeroCuenta(cuenta.getCuentaOperativa().getNroCuentaProducto());
		String sucursalCuentaOperativa = StringUtils.stripStart(cuenta.getCuentaOperativa().getNroSucursal(), "0");
		String numeroCuentaTitulo = ISBANStringUtils.formatearNumeroCuenta(cuenta.getCuentaTitulo().getNroCuentaProducto());
		
		
        if (EstadoRespuesta.OK.equals(respuestaResumenesList.getEstadoRespuesta())) {
        	if (respuestaResumenesList.getRespuesta().isEmpty()) {
        		estadisticaManager.add(estadisticaCorrespondiente, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        		comprobantesPorCuentaView.setNumeroCuenta(numeroCuentaOperativa);
        		comprobantesPorCuentaView.setSucursal(sucursalCuentaOperativa);
        		comprobantesPorCuentaView.setNumeroCuentaTitulo(numeroCuentaTitulo);
                comprobantesPorCuentaView.setIntervinientes(listaIntervinientes);
        		comprobantesPorCuentaView.setEstado(EstadoRespuesta.WARNING);
        		comprobantesPorCuentaView.setTipoError(TipoError.WARNING_NO_HAY_COMPROBANTES_TV_DISPONIBLES.getDescripcion());
        		comprobantesPorCuentaView.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.WARNING_NO_TENES_COMPROBANTES_TV).getMensaje());
        		return comprobantesPorCuentaView;
        	}
            List<ResumenMensualCuenta> resumenesList = respuestaResumenesList.getRespuesta();
            for (ResumenMensualCuenta resumenMensualCuenta : resumenesList) {
                ResumenMesualCuentaView resumenMesualCuentaView = new ResumenMesualCuentaView(resumenMensualCuenta);
                resumenMesualCuentaViewList.add(resumenMesualCuentaView);
            }
            comprobantesPorCuentaView.setNumeroCuenta(numeroCuentaOperativa);
    		comprobantesPorCuentaView.setSucursal(sucursalCuentaOperativa);
    		comprobantesPorCuentaView.setNumeroCuentaTitulo(numeroCuentaTitulo);
            comprobantesPorCuentaView.setIntervinientes(listaIntervinientes);
        	Collections.sort(resumenMesualCuentaViewList);

            comprobantesPorCuentaView.setResumenes(resumenMesualCuentaViewList);
            String tipoCuenta = TipoCuenta.fromCodigo(cuenta.getCuentaOperativa().getTipoCuenta()).getDescripcionConMoneda();
            comprobantesPorCuentaView.setTipoCuenta(tipoCuenta);

			estadisticaManager.add(estadisticaCorrespondiente, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    		comprobantesPorCuentaView.setEstado(EstadoRespuesta.OK);

        } else {
			estadisticaManager.add(estadisticaCorrespondiente, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    		comprobantesPorCuentaView.setNumeroCuenta(numeroCuentaOperativa);
    		comprobantesPorCuentaView.setSucursal(sucursalCuentaOperativa);
    		comprobantesPorCuentaView.setNumeroCuentaTitulo(numeroCuentaTitulo);
            comprobantesPorCuentaView.setIntervinientes(listaIntervinientes);
    		comprobantesPorCuentaView.setEstado(EstadoRespuesta.ERROR);
    		comprobantesPorCuentaView.setTipoError(TipoError.ERROR_FECHAS_COMPROBANTES_TV_PRIVADA.getDescripcion());
    		comprobantesPorCuentaView.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS).getMensaje());
        }

        return comprobantesPorCuentaView;
    }
	
	@Override
	public Respuesta<ListadoPDF> obtenerPDF(ConsultaResumenCuentaBP consultaResumenCuentaBP, TipoPDFEnum tipoPDF) {
		
		Respuesta<ListadoPDF> respuesta;

		if (TipoPDFEnum.CUENTAS_BANCA_PRIVADA.equals(tipoPDF)) {
			respuesta = obtenerListadoPDFResumenesCuentasBancaPrivada(consultaResumenCuentaBP, tipoPDF);			
		} else {
			respuesta = obtenerListadoPDFComprobantesBancaPrivada(consultaResumenCuentaBP, tipoPDF);
		}
        
        return respuesta;
	}
	
	private Respuesta<ListadoPDF> obtenerListadoPDFResumenesCuentasBancaPrivada (ConsultaResumenCuentaBP consultaResumenCuentaBP, TipoPDFEnum tipoPDF) {
		
		int cantidadErrores = 0;
		List<ReporteView> listaPDF = new ArrayList <ReporteView>();
		
		String nroCuentaFormateado = consultaResumenCuentaBP.getNumeroCuenta().replaceAll("\\/", "");
		String[] nroCuentaPartes = nroCuentaFormateado.split("-");
							
		AbstractCuenta cuenta = sesionCliente.getCliente().getCuentaPrivadaSiContieneNumero(nroCuentaPartes[1]);
        
        List<ResumenMensualCuenta> resumenesSeleccionados = sesionResumenCuenta
                .getResumenesBPByIds(Arrays.asList(consultaResumenCuentaBP.getFechas()), cuenta);
		
        for (ResumenMensualCuenta resumen : resumenesSeleccionados) {
        	Respuesta<ReporteResumenMensualCuenta> reporteMensual = descargaPDFBO.obtenerPDF(resumen, cuenta, tipoPDF);
            if (reporteMensual.getRespuesta() != null) {
                ReporteView resumenesMensualesView = ReporteView.fromReporte(reporteMensual.getRespuesta());
                listaPDF.add(resumenesMensualesView);
            } else {
            	cantidadErrores++;
            }
        }
        
    	return armarRespuesta (cantidadErrores, consultaResumenCuentaBP.getFechas().length, listaPDF, EstadisticasConstants.DESCARGA_PDF_RESUMEN_CUENTA_BANCA_PRIVADA);
		
	}
	
	private Respuesta<ListadoPDF> obtenerListadoPDFComprobantesBancaPrivada (ConsultaResumenCuentaBP consultaResumenCuentaBP, TipoPDFEnum tipoPDF) {
		
		int cantidadErrores = 0;
		List<ReporteView> listaPDF = new ArrayList <ReporteView>();

		String nroCuentaFormateado = consultaResumenCuentaBP.getNumeroCuenta().replaceAll("\\/", "");
							
		AbstractCuenta cuenta = buscarCuentaTitulo(nroCuentaFormateado);
		ListaFechasComprobantes listaCuentaFechasComprobante = recuperarListaDeComprobantesDeSesion(tipoPDF);
        List<ResumenMensualCuenta> comprobantesSeleccionados = buscarComprobantesSeleccionados(listaCuentaFechasComprobante, Arrays.asList(consultaResumenCuentaBP.getFechas()), consultaResumenCuentaBP.getNumeroCuenta());
        
        for (ResumenMensualCuenta resumen : comprobantesSeleccionados) {
        	Respuesta<ReporteResumenMensualCuenta> reporteMensual = descargaPDFBO.obtenerPDF(resumen, cuenta, tipoPDF);
            if (reporteMensual.getRespuesta() != null) {
                ReporteView resumenesMensualesView = ReporteView.fromReporte(reporteMensual.getRespuesta());
                listaPDF.add(resumenesMensualesView);
            } else {
            	cantidadErrores++;
            }
        }
        
        if (TipoPDFEnum.FONDOS_COMUNES_INVERSION.equals(tipoPDF)) {
        	return armarRespuesta (cantidadErrores, consultaResumenCuentaBP.getFechas().length, listaPDF, EstadisticasConstants.DESCARGA_PDF_COMPROBANTE_FCI_BANCA_PRIVADA);
        } else {
        	return armarRespuesta (cantidadErrores, consultaResumenCuentaBP.getFechas().length, listaPDF, EstadisticasConstants.DESCARGA_PDF_COMPROBANTE_TITULOS_VALORES_BANCA_PRIVADA);
        }
		
	}

	
	private AbstractCuenta buscarCuentaTitulo (String numeroCuenta) {
		
		Cuenta cuenta = new Cuenta();
		for (CuentaBancaPrivada cuentaBancaPrivada : sesionCliente.getCliente().getCuentasBancaPrivada()) {
			if (cuentaBancaPrivada.getCuentaOperativa().getNroCuentaProducto().contains(numeroCuenta)) {
				cuenta = cuentaBancaPrivada.getCuentaTitulo();
			}
			
		}
		return cuenta;		
	}
	
	private List<ResumenMensualCuenta> buscarComprobantesSeleccionados(ListaFechasComprobantes listaCuentaFechasComprobante,
			List<String> ids, String cuentaSeleccionada) {
		
		List<ResumenMesualCuentaView> comprobantesCuentaElegida = null;
		
		List<ResumenMensualCuenta> resumenesMensuales = new ArrayList<ResumenMensualCuenta>();
		if (listaCuentaFechasComprobante != null) {
			// obtengo la lista de resumenes en sesion por cuenta
			for (ComprobantesPorCuentaView comprobantesPorCuenta : listaCuentaFechasComprobante.getCuentas()) {
				if (comprobantesPorCuenta.getNumeroCuenta().equals(cuentaSeleccionada)) {
					comprobantesCuentaElegida = comprobantesPorCuenta.getResumenes();
				}
			}
			
			List<ResumenMensualCuenta> comprobantesEnSesion = armarListaFechas(comprobantesCuentaElegida);

			// filtra la lista en sesion segun la cuenta por id
			for (String id : ids) {
				for (ResumenMensualCuenta resumenMensualCuenta : comprobantesEnSesion) {
					if (resumenMensualCuenta.getId().toString().equals(id)) {
						resumenesMensuales.add(resumenMensualCuenta);
						break;
					}
				}
			}
		}
		return resumenesMensuales;
		
		
	}
	
	private List<ResumenMensualCuenta> armarListaFechas (List<ResumenMesualCuentaView> comprobantesCuentaElegida) {
		
		List<ResumenMensualCuenta> listaResumenMensualCuenta = new ArrayList<ResumenMensualCuenta>();
		
		for (ResumenMesualCuentaView fechaComprobante : comprobantesCuentaElegida) {
			ResumenMensualCuenta fecha = new ResumenMensualCuenta();
			fecha.setId(fechaComprobante.getId());
			fecha.setFecha(armarFecha(fechaComprobante));
			fecha.setReferencia(fechaComprobante.getReferencia());
			fecha.setNroLiquidacion(fechaComprobante.getNroLiquidacion());
			listaResumenMensualCuenta.add(fecha);
		}
		
		return listaResumenMensualCuenta;
	}
	
	private Date armarFecha (ResumenMesualCuentaView fechaComprobante) {
		
		int anio = Integer.parseInt(fechaComprobante.getAnio());
		int mes = detectarMes(fechaComprobante.getMes());
		int dia = Integer.parseInt(fechaComprobante.getDia());
		
		DateTime dateTime = new DateTime(anio, mes, dia, 0, 0);
		
		return dateTime.toDate();
		
	}
	
	private int detectarMes(String mes) {
		
		int mesNumero = 0;
		
		if ("Enero".equals(mes)) {
			mesNumero = 1;
		} else if ("Febrero".equals(mes)) {
			mesNumero = 2;
		} else if ("Marzo".equals(mes)) {
			mesNumero = 3;
		} else if ("Abril".equals(mes)) {
			mesNumero = 4;
		} else if ("Mayo".equals(mes)) {
			mesNumero = 5;
		} else if ("Junio".equals(mes)) {
			mesNumero = 6;
		} else if ("Julio".equals(mes)) {
			mesNumero = 7;
		} else if ("Agosto".equals(mes)) {
			mesNumero = 8;
		} else if ("Septiembre".equals(mes)) {
			mesNumero = 9;
		} else if ("Octubre".equals(mes)) {
			mesNumero = 10;
		} else if ("Noviembre".equals(mes)) {
			mesNumero = 11;
		} else {
			mesNumero = 12;
		}
		
		return mesNumero;
	}
	
	private Respuesta<ListadoPDF> armarRespuesta(int cantidadErrores, int cantidadPedidos, List<ReporteView> listaPDF, String codigoEstadistica) {
		
		Respuesta<ListadoPDF> respuesta = new Respuesta<ListadoPDF>();
		ListadoPDF listaPDFCuentas = new ListadoPDF(); 

        if (cantidadErrores == cantidadPedidos || cantidadErrores == 1 && cantidadPedidos == 1) {
    		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        	respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_TOTAL_PDF,
        			CodigoMensajeConstantes.ERROR_TOTAL_DESCARGA_PDF_RESUMEN_CUENTA_BP);
        } else if (cantidadErrores >= 1 && cantidadErrores < cantidadPedidos) {
            listaPDFCuentas.setPdfPedidos(listaPDF);
    		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
        	respuesta = respuestaFactory.crearRespuestaWarning(listaPDFCuentas, "", TipoError.WARNING_DESCARGA_PARCIAL_PDF,
        			CodigoMensajeConstantes.ERROR_PARCIAL_DESCARGA_PDF_RESUMEN_CUENTA_BP);
        }  else if (cantidadErrores == 0) {
            listaPDFCuentas.setPdfPedidos(listaPDF);
    		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        	respuesta = respuestaFactory.crearRespuestaOk(listaPDFCuentas);
        }
        
        return respuesta;
		
	}

	@Override
	protected String getTipo() {
		return null;
	}
	
}
