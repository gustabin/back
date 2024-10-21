package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.impl.CambioGrupoAfinidadDAOImpl;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dto.TarjetaAsociadaComprobanteDTO;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ComprobanteSolicitudCambioAfinidadView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class CambioGrupoAfinidadDAOTest {

	@Mock
	private RespuestaFactory respuestaFactory;

	@InjectMocks
	@Spy
	private CambioGrupoAfinidadDAO cambioGrupoAfinidadDAO = new CambioGrupoAfinidadDAOImpl();

	/** The app context. */
	ApplicationContext appContext = new ClassPathXmlApplicationContext();

	@Test
	public void generarComprobante() throws Exception {
		Reporte reporte = null;
		ComprobanteSolicitudCambioAfinidadView datosComprobante = new ComprobanteSolicitudCambioAfinidadView();

		FieldUtils.writeField(cambioGrupoAfinidadDAO, "logoCabecera",
				appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(cambioGrupoAfinidadDAO, "fileJasperAadvantage", appContext.getResource(
				"classpath:/report/cambioGrupoAfinidad/comprobanteCambioGrupoAfinidadAadvantage.jasper"), true);
		FieldUtils.writeField(cambioGrupoAfinidadDAO, "fileJasperSuperclub", appContext.getResource(
				"classpath:/report/cambioGrupoAfinidad/comprobanteCambioGrupoAfinidadSuperclub.jasper"), true);
		FieldUtils.writeField(cambioGrupoAfinidadDAO, "logoPie",
				appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);

		List<TarjetaAsociadaComprobanteDTO> tarjetasAsociadas = new ArrayList<TarjetaAsociadaComprobanteDTO>();
		TarjetaAsociadaComprobanteDTO tarjeta = new TarjetaAsociadaComprobanteDTO();
		tarjeta.setTipoNumero("VISA 1234-XXXX");
		tarjeta.setTipoNumero("Constancio Percy");
		tarjetasAsociadas.add(tarjeta);

		datosComprobante.setFechaHora("kjhsad");
		datosComprobante.setInfoPie("asdasd");
		datosComprobante.setNroSocioAdvantage("12345");
		datosComprobante.setNumeroGestion("128763");
		datosComprobante.setTarjetasAsociadas(tarjetasAsociadas);

		reporte = cambioGrupoAfinidadDAO.generarComprobanteCambioGrupoAfinidad(datosComprobante);

		Assert.assertNotNull(reporte);
	}

}
