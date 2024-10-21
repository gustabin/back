package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static org.mockito.Mockito.when;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dao.BonificacionesVigentesDAO;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ListPromocionDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ProductoClienteEntity;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.PromocionDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.view.BonificacionVigenteView;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;

@RunWith(MockitoJUnitRunner.class)
public class BonificacionesVigentesBOTest {

	@InjectMocks
	BonificacionesVigentesBOImpl bonificacionesVigentesBO = new BonificacionesVigentesBOImpl();
	
	@Mock
	private SesionCliente sesionCliente;
	
	@Mock
	BonificacionesVigentesDAO bonificacionesVigentesDAO;
	
	@Test
	public void obtenerBonificaciones() throws BusinessException, DAOException {
		
		//When
		List<LinkedHashMap<Object, Object>> listaBonificaciones = new ArrayList<LinkedHashMap<Object, Object>>();
		
		LinkedHashMap<Object, Object> bonificacionCuenta = new LinkedHashMap<Object, Object>();
		bonificacionCuenta.put("branch", "0067");
		bonificacionCuenta.put("number", "007001586574");
		
		LinkedHashMap<Object, Object> bonificacion = new LinkedHashMap<Object, Object>();
		bonificacion.put("name", "BONIFICACION DE CUENTA");
		bonificacion.put("description", "BONIFICACION DE CUENTA: Cosmos 6 Meses 01-12-20, Evento por Base de Participantes");
		bonificacion.put("dateFrom", "09/02/2021");
		bonificacion.put("dateTo", "28/02/2022");
		bonificacion.put("percentaje", 20.0);
		bonificacion.put("months", 12);
		bonificacion.put("product", "90");
		bonificacion.put("subProduct", "0563");
		bonificacion.put("bonificationCode", "D107"); 
		bonificacion.put("account", bonificacionCuenta);
		bonificacion.put("premiationId", "10219933");
		bonificacion.put("id", "ACCOUNT_BONIFICATION:64530:10219933:5:0:20210209");
		bonificacion.put("dateApply", "09/02/2021");
		bonificacion.put("type", "ACCOUNT_BONIFICATION");
		
		listaBonificaciones.add(bonificacion);
		
		ProductoClienteEntity producto = new ProductoClienteEntity();
		producto.setNombreProducto("Super Cuenta");
		PromocionDto prodDto = new PromocionDto();
		prodDto.setCdPlan("Cd_plan");
		prodDto.setCdProducto("Cd_producto");
		prodDto.setCdRamo(1);
		prodDto.setCdTipoBeneficio("tipo_beneficio");
		prodDto.setDeBeneficio("De_beneficio");
		prodDto.setDeCategoria("categoria");
		prodDto.setDeLegales("legales");
		prodDto.setDeLinkDetalle("link_detalle");
		prodDto.setDeProductoComercial("producto_comercial");
		prodDto.setFeDesdePromocion("01/01/2022");
		prodDto.setFeHastaPromocion("31/12/2022");
		prodDto.setFeSuscripcion("06/07/2022");
		prodDto.setNuCertificado(1);
		prodDto.setNuPeriodoVigencia(1);
		prodDto.setNuPoliza(1);
		prodDto.setNuPrioridad(1);
		prodDto.setPoBeneficio("por_beneficio");
		
		ListPromocionDto listPromocionDto = new ListPromocionDto();
		listPromocionDto.setResult(new ArrayList<PromocionDto>());
		listPromocionDto.getResult().add(prodDto);
		
		when(bonificacionesVigentesDAO.obtenerBonificaciones(Matchers.anyString())).thenReturn(listaBonificaciones);
		when(bonificacionesVigentesDAO.obtenerProductoBonificado(Matchers.anyString(), Matchers.anyString())).thenReturn(producto);		
		when(bonificacionesVigentesDAO.obtenerPromociones(Matchers.anyString())).thenReturn(listPromocionDto);
		//Then
		List<BonificacionVigenteView> bonificacionesEntity = bonificacionesVigentesBO.obtenerBonificaciones("10000");
		
		//Expected
		Assert.assertNotNull(bonificacionesEntity);
		
	}
	
}
