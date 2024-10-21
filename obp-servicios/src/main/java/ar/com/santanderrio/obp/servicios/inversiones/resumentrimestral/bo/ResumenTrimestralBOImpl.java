package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.dao.ResumenTrimestralDAO;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.InfoRTFCuentaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.InfoRTFEntity;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResponsePdfView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenTrimestralView;

@Component
public class ResumenTrimestralBOImpl implements ResumenTrimestralBO{
	
	@Autowired
	private ResumenTrimestralDAO resumenTrimestralDAO;
	
	@Autowired
    private LegalBO legalBO;
	
	@Override
	public ResumenTrimestralView obtenerDisponibles(String nup, String segmento) {
		// TODO Auto-generated method stub
		
		ResumenTrimestralView resumen=resumenTrimestralDAO.obtenerDisponibles(nup);
		ResumenTrimestralView rta=new ResumenTrimestralView();
		List<InfoRTFEntity> listaRTF=filtrarRTFPorSegmento(resumen.getListaRTF(),resumen.getListaCuentas(),segmento);
		List<InfoRTFCuentaEntity> listaCuentas=filtrarCuentasPorSegmento(resumen.getListaCuentas(),segmento);
		
		String ultPer=resumen.getUltPeriodo();
		int ultAnio=resumen.getUltAnio();
		
		listaRTF=completarPeriodos(listaRTF,ultPer,ultAnio);
		listaCuentas=ordenarCuentas(listaCuentas,listaRTF);
		listaRTF=parsearCuentasRTF(listaRTF);
		listaCuentas=parsearCuentas(listaCuentas);
		rta.setListaRTF(listaRTF);
		rta.setListaCuentas(listaCuentas);
		rta.setAyuda(obtenerMensajeAyudaRTF());
		rta.setMensajeSinResumen(obtenerMensajeSinRTF());
		rta.setUltAnio(ultAnio);
		rta.setUltPeriodo(ultPer);
		
		return rta;
		
	}
	
	private List<InfoRTFEntity> parsearCuentasRTF(List<InfoRTFEntity> listaRTF) {

		List<InfoRTFEntity> listaRTFFiltrada = filtrarCuentasRTFDuplicadas(listaRTF);
		for (InfoRTFEntity rtf : listaRTFFiltrada) {
			// Evitar error de formateo por duplicidad de referencias en la lista
			if (!rtf.getCuenta().contains("/")) {
				rtf.setCuenta(ISBANStringUtils.formatearNumeroCuenta(rtf.getCuenta()));
			}
		}

		return listaRTFFiltrada;
	}

	private List<InfoRTFEntity> filtrarCuentasRTFDuplicadas(List<InfoRTFEntity> listaRTF) {
		// Filtrar las cuentas RTF duplicadas
		Iterator<InfoRTFEntity> it = listaRTF.iterator();
		List<InfoRTFEntity> listaRTFFiltrada = new ArrayList<InfoRTFEntity>();

		while (it.hasNext()) {
			int flag = 1;
			InfoRTFEntity rtf = it.next();
			for (InfoRTFEntity el : listaRTFFiltrada) {
				flag = 1;
				if (el.equals(rtf)) {
					flag = 0;
					break;
				}
			}
			if (flag == 1) {
				listaRTFFiltrada.add(rtf);
			}
		}

		return listaRTFFiltrada;
	}

	private List<InfoRTFCuentaEntity> parsearCuentas(List<InfoRTFCuentaEntity> listaCuentas) {

		List<InfoRTFCuentaEntity> listaCuentasFiltrada = filtrarListaCuentasDuplicadas(listaCuentas);
		// Evitar error de formateo por duplicidad de referencias en la lista
		for (InfoRTFCuentaEntity cuenta : listaCuentasFiltrada) {
			if (!cuenta.getNumeroCuenta().contains("/")) {
				cuenta.setNumeroCuenta(ISBANStringUtils.formatearNumeroCuenta(cuenta.getNumeroCuenta()));
			}
		}
		
		return listaCuentasFiltrada;
	}

	private List<InfoRTFCuentaEntity> filtrarListaCuentasDuplicadas(List<InfoRTFCuentaEntity> listaCuentas) {
		// Filtrar las cuentas titulos duplicadas
		Iterator<InfoRTFCuentaEntity> it = listaCuentas.iterator();
		List<InfoRTFCuentaEntity> listaCuentasFiltrada = new ArrayList<InfoRTFCuentaEntity>();

		while (it.hasNext()) {
			int flag = 1;
			InfoRTFCuentaEntity rtf = it.next();
			for (InfoRTFCuentaEntity el : listaCuentasFiltrada) {
				flag = 1;
				String cuenta = el.getNumeroCuenta();
				String cuentaRTF = rtf.getNumeroCuenta();

				if (cuenta.equals(cuentaRTF)) {
					flag = 0;
					break;
				}
			}
			if (flag == 1) {
				listaCuentasFiltrada.add(rtf);
			}
		}

		return listaCuentasFiltrada;
	}

	@Override
    public String obtenerMensajeAyudaRTF() {
        return buscarMensajeAyuda(CodigoMensajeConstantes.MENSAJE_AYUDA_RESUMEN_TRIMESTRAL_FONDOS);
    }
	 
	@Override
    public String obtenerMensajeSinRTF() {
        return buscarMensajeAyuda(CodigoMensajeConstantes.MENSAJE_SIN_RESUMEN_TRIMESTRAL_FONDOS);
    }
	
	
	private List<InfoRTFEntity> completarPeriodos(List<InfoRTFEntity> listaRTF, String ultPer, int ultAnio) {
		// TODO Auto-generated method stub
		List<InfoRTFEntity> listaCompleta = new ArrayList<InfoRTFEntity>();
		listaCompleta=listaRTF;
		String[] descripciones= {"ENERO MARZO","ABRIL JUNIO","JULIO SEPTIEMBRE","OCTUBRE DICIEMBRE"};
		
		int idUltPer;
		
		if(ultPer.equals(descripciones[0])) {
			idUltPer=1;
		}else if(ultPer.equals(descripciones[1])) {
			idUltPer=2;
		}else if(ultPer.equals(descripciones[2])) {
			idUltPer=3;
		}else {
			idUltPer=4;
		}
		
		
		
		Set<String> cuentas=new HashSet<String>();
		for(InfoRTFEntity item : listaRTF) {
			cuentas.add(item.getCuenta());
		}
		
		Set<Integer> anios=new HashSet<Integer>();
		for(InfoRTFEntity item : listaRTF) {
				anios.add(item.getAnio());
		}
		
		
		for(String cuenta : cuentas) {
			
			for(int anio:anios) {
				boolean per1=false;
				boolean per2=false;
				boolean per3=false;
				boolean per4=false;
				for(InfoRTFEntity item : listaRTF) {
					if(item.getCuenta().equals(cuenta) && item.getAnio()==anio) {
						if(item.getDescripcion().equals(descripciones[0])) {
							per1=true;
						}else if(item.getDescripcion().equals(descripciones[1])) {
							per2=true;
						}else if(item.getDescripcion().equals(descripciones[2])) {
							per3=true;
						}else if(item.getDescripcion().equals(descripciones[3])) {
							per4=true;
						}
					}
				}
				
				
				
				if(!per1) {
					if(anio==ultAnio) {
						InfoRTFEntity vacio=new InfoRTFEntity();
						vacio.setCuenta(cuenta);
						vacio.setAnio(anio);
						vacio.setDescripcion(descripciones[0]);
						vacio.setIdPeriodo(1);
						listaCompleta.add(vacio);
					}else {
						InfoRTFEntity vacio=new InfoRTFEntity();
						vacio.setCuenta(cuenta);
						vacio.setAnio(anio);
						vacio.setDescripcion(descripciones[0]);
						vacio.setIdPeriodo(1);
						listaCompleta.add(vacio);
					}
					
					
				}
				if(!per2) {
					if(anio==ultAnio) {
						if(idUltPer>=2) {
							InfoRTFEntity vacio=new InfoRTFEntity();
							vacio.setCuenta(cuenta);
							vacio.setAnio(anio);
							vacio.setDescripcion(descripciones[1]);
							vacio.setIdPeriodo(2);
							listaCompleta.add(vacio);
						}
						
					} else {
						InfoRTFEntity vacio=new InfoRTFEntity();
						vacio.setCuenta(cuenta);
						vacio.setAnio(anio);
						vacio.setDescripcion(descripciones[1]);
						vacio.setIdPeriodo(2);
						listaCompleta.add(vacio);
					}
					
					
				}
				if(!per3) {
					if(anio==ultAnio) {
						if(idUltPer>=3) {
							InfoRTFEntity vacio=new InfoRTFEntity();
							vacio.setCuenta(cuenta);
							vacio.setAnio(anio);
							vacio.setDescripcion(descripciones[2]);
							vacio.setIdPeriodo(3);
							listaCompleta.add(vacio);
						}
					}else {
						InfoRTFEntity vacio=new InfoRTFEntity();
						vacio.setCuenta(cuenta);
						vacio.setAnio(anio);
						vacio.setDescripcion(descripciones[2]);
						vacio.setIdPeriodo(3);
						listaCompleta.add(vacio);
					}
				}
				if(!per4) {
					if(anio==ultAnio) {
						if(idUltPer>=4) {
							InfoRTFEntity vacio=new InfoRTFEntity();
							vacio.setCuenta(cuenta);
							vacio.setAnio(anio);
							vacio.setDescripcion(descripciones[3]);
							vacio.setIdPeriodo(4);
							listaCompleta.add(vacio);
						}
					}else {
						InfoRTFEntity vacio=new InfoRTFEntity();
						vacio.setCuenta(cuenta);
						vacio.setAnio(anio);
						vacio.setDescripcion(descripciones[3]);
						vacio.setIdPeriodo(4);
						listaCompleta.add(vacio);
					}
				}
			}
		}

		
	Collections.sort(listaCompleta, new Comparator<InfoRTFEntity>(){
	     public int compare(InfoRTFEntity o1, InfoRTFEntity o2){
	         if(o1.getIdPeriodo()== o2.getIdPeriodo())
	             return 0;
	         return o1.getIdPeriodo() < o2.getIdPeriodo() ? -1 : 1;
	     }
	});
		
		
	return listaCompleta;
	}

	private List<InfoRTFCuentaEntity> ordenarCuentas(List<InfoRTFCuentaEntity> listaCuentas,
			List<InfoRTFEntity> listaRTF) {
		List<InfoRTFCuentaEntity> listaOrdenada=new ArrayList<InfoRTFCuentaEntity>();
		for(InfoRTFCuentaEntity cuenta : listaCuentas) {
			boolean poseeInfo=false;
			for(InfoRTFEntity pdf : listaRTF) {
				if(pdf.getCuenta().equals(cuenta.getNumeroCuenta())) {
					poseeInfo=true;
				}
			}
			
			
			if(poseeInfo) {
				listaOrdenada.add(0,cuenta);
			}else {
				listaOrdenada.add(cuenta);
			}
		}
		return listaOrdenada;
	}

	private List<InfoRTFEntity> filtrarRTFPorSegmento(List<InfoRTFEntity> listaRTF,
			List<InfoRTFCuentaEntity> listaCuentas, String segmento) {
		List<InfoRTFEntity> listaFinal = new ArrayList<InfoRTFEntity>();
		for (InfoRTFCuentaEntity cuenta : listaCuentas) {
			if (cuenta.getSegmento().equals(segmento)) {
				for (InfoRTFEntity rtf : listaRTF) {
					if (rtf.getCuenta().equals(cuenta.getNumeroCuenta())) {
						listaFinal.add(rtf);
					}
				}
			}
		}
		return listaFinal;
	}

	private List<InfoRTFCuentaEntity> filtrarCuentasPorSegmento(List<InfoRTFCuentaEntity> listaCuentas,
			String segmento) {

		List<InfoRTFCuentaEntity> listaFinal = new ArrayList<InfoRTFCuentaEntity>();
		for (InfoRTFCuentaEntity cuenta : listaCuentas) {
			if (cuenta.getSegmento().equals(segmento)) {
				listaFinal.add(cuenta);
			}
		}
		return listaFinal;

	}
	
	
	
	private String buscarMensajeAyuda(String codigoMensaje) {
        return legalBO.obtenerLegalesPorCodigo(codigoMensaje);
        
    }
	

	

	@Override
	public ResponsePdfView obtenerPdf(String idPdf, String nup) {
		
		ResponsePdfView infoPdf=resumenTrimestralDAO.obtenerPdf(idPdf,nup);
		
		return infoPdf;
	}

}
