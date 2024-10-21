package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dao.BonificacionesVigentesDAO;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.BonificacionDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ListPromocionDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ProductoClienteEntity;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.PromocionDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.view.BonificacionVigenteView;
import ar.com.santanderrio.obp.servicios.comun.utils.FormatCrUtil;

@Component
public class BonificacionesVigentesBOImpl implements BonificacionesVigentesBO {

	@Autowired
	BonificacionesVigentesDAO bonificacionesVigentesDAO;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BonificacionesVigentesBOImpl.class);
	   
	@Override
	public List<BonificacionVigenteView> obtenerBonificaciones(String nup) throws BusinessException {
		
		List<BonificacionVigenteView> bonificacionesView = new ArrayList<BonificacionVigenteView>();

		try {
			List<LinkedHashMap<Object, Object>> listaBonificaciones = bonificacionesVigentesDAO.obtenerBonificaciones(nup);
			mapearBonificacionesEnView(bonificacionesView, listaBonificaciones, true);
			
			List<LinkedHashMap<Object, Object>> listaBonificacionesCajasSeguridad = bonificacionesVigentesDAO.obtenerBonificacionesCajaSeguridad(nup);
			mapearBonificacionesEnView(bonificacionesView, listaBonificacionesCajasSeguridad, false);
			
			ListPromocionDto listaPromociones = bonificacionesVigentesDAO.obtenerPromociones(nup);
			mapearPromocionesEnView(bonificacionesView, listaPromociones);
			
			List<BonificacionDto> bonificaciones = bonificacionesVigentesDAO.obtenerBonificacionesSeguros(nup);
			mapearBonificacionesEnView(bonificacionesView, bonificaciones);
			
			
			
		} catch (DAOException e) {
			LOGGER.error("Error en obtenerBonificaciones" );
			throw new BusinessException();
		}

		return bonificacionesView;
	}
	
	private void mapearBonificacionesEnView (List<BonificacionVigenteView> bonificacionesView, List<LinkedHashMap<Object, Object>> listaBonificaciones, boolean buscarEnBD) throws DAOException {
		
		for (int i = 0; i<listaBonificaciones.size(); i++) {
			LinkedHashMap<Object, Object> bonificacionDTO = listaBonificaciones.get(i);
			BonificacionVigenteView bonificacionView = new BonificacionVigenteView();

			Object fechaDesde = bonificacionDTO.get("dateFrom");
			Object fechaHasta = bonificacionDTO.get("dateTo");
			Object cantidadMeses = bonificacionDTO.get("months");
			bonificacionView.setVigencia("del " + fechaDesde + " al " + fechaHasta + " (" + cantidadMeses + " meses)");
			Object porcentajeObjeto = bonificacionDTO.get("percentaje");
			String porcentaje = porcentajeObjeto.toString();
			bonificacionView.setBonificacion(porcentaje.substring(0, 2) + " %");
			Object cuenta = bonificacionDTO.get("account");		
			
			if (buscarEnBD) {
				Object codigoProducto = bonificacionDTO.get("product");
				Object codigoSubproducto = bonificacionDTO.get("subProduct");
				
				String codigoProductoString = codigoProducto.toString();
				String codigoSubproductoString = codigoSubproducto.toString();
				ProductoClienteEntity producto = bonificacionesVigentesDAO.obtenerProductoBonificado(codigoProductoString, codigoSubproductoString);
			
				bonificacionView.setProducto(producto.getNombreProducto());
				bonificacionView.setNumero(armarNumero(cuenta, true));
			} else {
				bonificacionView.setProducto("Caja de Seguridad");
				bonificacionView.setNumero(armarNumero(cuenta, false));				
			}
			bonificacionesView.add(bonificacionView);			
		}				
	}
		
	@SuppressWarnings("unchecked")
	private String armarNumero (Object cuenta, Boolean esNumeroCuenta) {
		
		LinkedHashMap<String, String> cuentaHash = (LinkedHashMap<String, String>) cuenta;
		String number = cuentaHash.get("number");
		String branch = cuentaHash.get("branch");
		
		String sucursal = branch.substring(1, branch.length());
		
		if (esNumeroCuenta) {
			String numeroCuenta = number.substring(number.length()-7, number.length()-1);
			String ultimoNumeroCuenta = number.substring(number.length()-1, number.length());
		
			return sucursal + "-" + numeroCuenta + "/" + ultimoNumeroCuenta;
		} else {
			String sector = number.substring(0, 3);
			String caja = number.substring(number.length()-5, number.length());
			return "N° " + caja + " SUC " + sucursal + " SECTOR " + sector;
		}
		
	}
	
	private void mapearPromocionesEnView (List<BonificacionVigenteView> bonificacionesView, ListPromocionDto promociones) {
		if (promociones != null) {
			for(PromocionDto promo :promociones.getResult()) {
				BonificacionVigenteView bonificacion= new BonificacionVigenteView();
				bonificacion.setBonificacion(promo.getDeBeneficio());
				bonificacion.setNumero("Número de póliza: " + String.valueOf(promo.getNuPoliza()) + " - Certificado: " + String.valueOf(promo.getNuCertificado()));
				bonificacion.setProducto(promo.getDeProductoComercial());
				bonificacion.setVigencia(promo.getFeHastaPromocion());
				bonificacion.setLegales(promo.getDeLegales());
				bonificacionesView.add(bonificacion);
			}
		}
	}
	
    private void mapearBonificacionesEnView (List<BonificacionVigenteView> bonificacionesView, List<BonificacionDto> bonificaciones) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for(BonificacionDto b :bonificaciones) {
			BonificacionVigenteView bonificacion= new BonificacionVigenteView();
			if (b.getBenefitPercentage()> 0 ) {
				bonificacion.setBonificacion(FormatCrUtil.formatearPorcentajeSinDecimales(b.getBenefitPercentage()));
			}else {
				bonificacion.setBonificacion(FormatCrUtil.formatearPesosDosDecimales(b.getBenefitAmount()));
			}
			bonificacion.setNumero("Número de póliza: " + String.valueOf(b.getPolicyNumber()) + " - Certificado: " + String.valueOf(b.getCertificateNumber()));
			bonificacion.setProducto(b.getBranchDescription());
			bonificacion.setVigencia(sdf.format(b.getValidityDateTo()));
			bonificacion.setLegales(b.getLegal());
			
			bonificacionesView.add(bonificacion);
		}
	}
	
}
