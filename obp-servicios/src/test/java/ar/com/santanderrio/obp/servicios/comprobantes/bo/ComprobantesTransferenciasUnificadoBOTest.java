package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ComprobantesTransferenciasUnificadoBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaInmediataOtrosBancosDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class ComprobantesTransferenciasUnificadoBOTest {

	@InjectMocks
	private ComprobantesTransferenciasUnificadoBO comprobanteTransferencias = new ComprobantesTransferenciasUnificadoBOImpl();

	@Mock
	private ScompBO scompBO;

	@Mock
	private ComprobantesSietePorVenticuatroBO comp7x24BO;

	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantesInmediataRio;

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantesInmediataOB;

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantesInmediataRioTBanco;

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantesInmediataOBTBanco;

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantesProgramadaRio;

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantesProgramadaOB;

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantesProgramadaRioNE;

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantesProgramadaOBNE;
	
	Calendar calendar = Calendar.getInstance();

	@Test
	public void obtenerComprobantesTransferenciaAsyncOkTest() throws InterruptedException, ExecutionException {
		Cliente cliente = new Cliente();
		cliente.setNup("14129875691");

		ComprobantesDTO comprobantes2 = new ComprobantesDTO(false);
        comprobantes2.setComprobantes(new ArrayList<ComprobanteDTO>());
        comprobantes2.getComprobantes().add(obtenerComprobante1());
        comprobantes2.getComprobantes().add(obtenerComprobante2());
		Respuesta<ComprobantesDTO> resInmRio = new Respuesta<ComprobantesDTO>();
		resInmRio.setRespuesta(comprobantes2);
		resInmRio.setEstadoRespuesta(EstadoRespuesta.OK);

		
		Respuesta<ComprobantesDTO> resInmOB = new Respuesta<ComprobantesDTO>();
		resInmOB.setRespuesta(comprobantes2);
		resInmOB.setEstadoRespuesta(EstadoRespuesta.OK);

		Respuesta<ComprobantesDTO> resInmRioTBanco = new Respuesta<ComprobantesDTO>();
		resInmRioTBanco.setRespuesta(comprobantes2);
		resInmRioTBanco.setEstadoRespuesta(EstadoRespuesta.OK);

		Respuesta<ComprobantesDTO> resInmOBTBanco = new Respuesta<ComprobantesDTO>();
		resInmOBTBanco.setRespuesta(comprobantes2);
		resInmOBTBanco.setEstadoRespuesta(EstadoRespuesta.OK);

		Respuesta<ComprobantesDTO> resProgramadaRio = new Respuesta<ComprobantesDTO>();
		resProgramadaRio.setRespuesta(comprobantes2);
		resProgramadaRio.setEstadoRespuesta(EstadoRespuesta.OK);

		Respuesta<ComprobantesDTO> resProgramadaRioNE = new Respuesta<ComprobantesDTO>();
		resProgramadaRioNE.setRespuesta(comprobantes2);
		resProgramadaRioNE.setEstadoRespuesta(EstadoRespuesta.OK);

		Respuesta<ComprobantesDTO> resProgramadaOB = new Respuesta<ComprobantesDTO>();
		resProgramadaOB.setRespuesta(comprobantes2);
		resProgramadaOB.setEstadoRespuesta(EstadoRespuesta.OK);

		Respuesta<ComprobantesDTO> resProgramadaOBNE = new Respuesta<ComprobantesDTO>();
		resProgramadaOBNE.setRespuesta(comprobantes2);
		resProgramadaOBNE.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(scompBO.obtenerComprobantesRioAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
				.thenReturn(futureComprobantesInmediataRio);
		Mockito.when(
				scompBO.obtenerComprobantesOtrosBancosAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
				.thenReturn(futureComprobantesInmediataOB);
		Mockito.when(
				scompBO.obtenerComprobantesRioTBancoAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
				.thenReturn(futureComprobantesInmediataRioTBanco);
		Mockito.when(scompBO.obtenerComprobantesOBTBancoAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
				.thenReturn(futureComprobantesInmediataOBTBanco);
		Mockito.when(scompBO.obtenerComprobantesOtrosComprobantesAsync(Matchers.anyString(),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantesInmediataRio);

		Mockito.when(
				comp7x24BO.obtenerComprobantesRioAsync(Matchers.any(Cliente.class), Matchers.any(TransaccionDTO.class)))
				.thenReturn(futureComprobantesProgramadaRio);
		Mockito.when(comp7x24BO.obtenerComprobantesRioNoEfecAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantesProgramadaRioNE);
		Mockito.when(comp7x24BO.obtenerComprobantesOtrosBancosAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantesProgramadaOB);
		Mockito.when(comp7x24BO.obtenerComprobantesOtrosBancosNoEfecAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantesProgramadaOBNE);

		Mockito.when(futureComprobantesInmediataRio.isDone()).thenReturn(true);
		Mockito.when(futureComprobantesInmediataOB.isDone()).thenReturn(true);
		Mockito.when(futureComprobantesInmediataRioTBanco.isDone()).thenReturn(true);
		Mockito.when(futureComprobantesInmediataOBTBanco.isDone()).thenReturn(true);

		Mockito.when(futureComprobantesProgramadaRio.isDone()).thenReturn(true);
		Mockito.when(futureComprobantesProgramadaOB.isDone()).thenReturn(true);
		Mockito.when(futureComprobantesProgramadaRioNE.isDone()).thenReturn(true);
		Mockito.when(futureComprobantesProgramadaOBNE.isDone()).thenReturn(true);

		Mockito.when(futureComprobantesInmediataRio.get()).thenReturn(resInmRio);
		Mockito.when(futureComprobantesInmediataOB.get()).thenReturn(resInmOB);
		Mockito.when(futureComprobantesInmediataRioTBanco.get()).thenReturn(resInmRioTBanco);
		Mockito.when(futureComprobantesInmediataOBTBanco.get()).thenReturn(resInmOBTBanco);

		Mockito.when(futureComprobantesProgramadaRio.get()).thenReturn(resProgramadaRio);
		Mockito.when(futureComprobantesProgramadaOB.get()).thenReturn(resProgramadaOB);
		Mockito.when(futureComprobantesProgramadaRioNE.get()).thenReturn(resProgramadaRioNE);
		Mockito.when(futureComprobantesProgramadaOBNE.get()).thenReturn(resProgramadaOBNE);

		Future<Respuesta<ComprobantesDTO>> resAsync = comprobanteTransferencias
				.obtenerComprobantesTransferenciaAsync(cliente, new TransaccionDTO());

		Respuesta<ComprobantesDTO> res = resAsync.get();

		assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
		assertEquals(1, res.getRespuesta().getComprobantes().size());
	}

    private ComprobanteDTO obtenerComprobante1() {
        ComprobanteDTO comprobanteInmOB = new ComprobanteDTO();
        DetalleComprobanteTransferenciaInmediataOtrosBancosDTO detalle = new DetalleComprobanteTransferenciaInmediataOtrosBancosDTO();
        detalle.setNroComprobante("2345");
		comprobanteInmOB.setDetalle(detalle);
		comprobanteInmOB.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
		comprobanteInmOB.setImportePesos(BigDecimal.valueOf(50));
		comprobanteInmOB.setCtaMedioDePagoPesos("pepe");
		comprobanteInmOB.setTipoCtaMedioDePagoPesos(TipoCuenta.CAJA_AHORRO_PESOS);
		comprobanteInmOB.setTieneError(false);
		comprobanteInmOB.setNecesitaMoneda(false);
		comprobanteInmOB.setIndice(1);
		comprobanteInmOB.setFecha(calendar.getTime());
        return comprobanteInmOB;
    }
    
    private ComprobanteDTO obtenerComprobante2() {
        ComprobanteDTO comprobanteInmOB = new ComprobanteDTO();
        DetalleComprobanteTransferenciaInmediataOtrosBancosDTO detalle = new DetalleComprobanteTransferenciaInmediataOtrosBancosDTO();
        detalle.setNroComprobante("2345");
        comprobanteInmOB.setDetalle(detalle);
        comprobanteInmOB.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
        comprobanteInmOB.setImportePesos(BigDecimal.valueOf(50));
        comprobanteInmOB.setCtaMedioDePagoPesos("pepe");
        comprobanteInmOB.setTipoCtaMedioDePagoPesos(TipoCuenta.CAJA_AHORRO_PESOS);
        comprobanteInmOB.setTieneError(false);
        comprobanteInmOB.setNecesitaMoneda(false);
        comprobanteInmOB.setIndice(1);
        comprobanteInmOB.setFecha(calendar.getTime());
        return comprobanteInmOB;
    }

}
