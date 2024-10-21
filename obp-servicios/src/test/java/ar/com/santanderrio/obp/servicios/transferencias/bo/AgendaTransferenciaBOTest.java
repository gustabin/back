package ar.com.santanderrio.obp.servicios.transferencias.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.RespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.AgendaTransferenciaBOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.dao.AgendaTransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ConfiguracionTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ConsultaAgendaTransferencias;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DatosTransferenciaAgendadaRioRioEntity;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PedidoTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.BancoDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.DatosOrigenTransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.DestinatarioDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.FrecuenciaTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;

/**
 * The Class AgendaTransferenciaBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class AgendaTransferenciaBOTest {

	/** The agenda transferencia BO. */
	@InjectMocks
	private AgendaTransferenciaBOImpl agendaTransferenciaBO = new AgendaTransferenciaBOImpl();

	/** The agenda transferencia DAO. */
	@Mock
	private AgendaTransferenciaDAO agendaTransferenciaDAO;

	/** The cuenta BO. */
	@Mock
	private CuentaBO cuentaBO;

	/** The respuesta BO. */
	@InjectMocks
	@Spy
	private RespuestaBO respuestaBO = new RespuestaBOImpl();

	/** The mensaje DAO. */
	@Mock
	private MensajeDAO mensajeDAO;

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	@Mock
	private RespuestaFactory respuestaFactory;

	/**
	 * Obtener agenda transferencias ok test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ParseException
	 *             the parse exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ImporteConvertException
	 */
	@Test
	public void obtenerAgendaTransferenciasOkTest()
			throws DAOException, ParseException, BusinessException, ImporteConvertException {

		IdentificacionCuenta idOrigen = new IdentificacionCuenta();
		idOrigen.setNroCuentaProducto("81995");
		idOrigen.setNroSucursal("33");

		IdentificacionCuenta idDestino = new IdentificacionCuenta();
		idDestino.setNroCuentaProducto("3607390");
		idDestino.setNroSucursal("0");

		AbstractCuenta cuentaOrigen = mock(AbstractCuenta.class);
		AbstractCuenta cuentaDestino = mock(AbstractCuenta.class);

		Cliente cliente = mock(Cliente.class);

		PedidoTransferenciaAgendada pedidoTransferencia = mock(PedidoTransferenciaAgendada.class);
		DatosTransferenciaAgendadaRioRioEntity datosTransferenciaAgendada = mock(
				DatosTransferenciaAgendadaRioRioEntity.class);
		ConfiguracionTransferenciaAgendada configuracionTransferenciaAgendada = mock(
				ConfiguracionTransferenciaAgendada.class);

		PedidoTransferenciaAgendada pedidoTransferencia2 = mock(PedidoTransferenciaAgendada.class);
		DatosTransferenciaAgendadaRioRioEntity datosTransferenciaAgendada2 = mock(
				DatosTransferenciaAgendadaRioRioEntity.class);
		ConfiguracionTransferenciaAgendada configuracionTransferenciaAgendada2 = mock(
				ConfiguracionTransferenciaAgendada.class);

		PedidoTransferenciaAgendada pedidoTransferencia3 = mock(PedidoTransferenciaAgendada.class);
		DatosTransferenciaAgendadaRioRioEntity datosTransferenciaAgendada3 = mock(
				DatosTransferenciaAgendadaRioRioEntity.class);
		ConfiguracionTransferenciaAgendada configuracionTransferenciaAgendada3 = mock(
				ConfiguracionTransferenciaAgendada.class);

		PedidoTransferenciaAgendada pedidoTransferencia4 = mock(PedidoTransferenciaAgendada.class);
		DatosTransferenciaAgendadaRioRioEntity datosTransferenciaAgendada4 = mock(
				DatosTransferenciaAgendadaRioRioEntity.class);
		ConfiguracionTransferenciaAgendada configuracionTransferenciaAgendada4 = mock(
				ConfiguracionTransferenciaAgendada.class);

		PedidoTransferenciaAgendada pedidoTransferencia5 = mock(PedidoTransferenciaAgendada.class);
		DatosTransferenciaAgendadaRioRioEntity datosTransferenciaAgendada5 = mock(
				DatosTransferenciaAgendadaRioRioEntity.class);
		ConfiguracionTransferenciaAgendada configuracionTransferenciaAgendada5 = mock(
				ConfiguracionTransferenciaAgendada.class);

		PedidoTransferenciaAgendada pedidoTransferencia6 = mock(PedidoTransferenciaAgendada.class);
		DatosTransferenciaAgendadaRioRioEntity datosTransferenciaAgendada6 = mock(
				DatosTransferenciaAgendadaRioRioEntity.class);
		ConfiguracionTransferenciaAgendada configuracionTransferenciaAgendada6 = mock(
				ConfiguracionTransferenciaAgendada.class);

		List<TransferenciaAgendada> transferenciaAgendadaList = new ArrayList<TransferenciaAgendada>();

		TransferenciaAgendada transferenciaAgendada = new TransferenciaAgendada();
		TransferenciaAgendada transferenciaAgendada2 = new TransferenciaAgendada();
		TransferenciaAgendada transferenciaAgendada3 = new TransferenciaAgendada();
		TransferenciaAgendada transferenciaAgendada4 = new TransferenciaAgendada();
		TransferenciaAgendada transferenciaAgendada5 = new TransferenciaAgendada();
		TransferenciaAgendada transferenciaAgendada6 = new TransferenciaAgendada();

		// Cuenta Corriente en Pesos
		when(pedidoTransferencia.getDatosTransferenciaAgendada()).thenReturn(datosTransferenciaAgendada);
		when(datosTransferenciaAgendada.getImporteDebito()).thenReturn("0.1");
		when(datosTransferenciaAgendada.getNombreCtaCredito()).thenReturn("NOMBRE TITULAR");
		when(datosTransferenciaAgendada.getTitularCredito()).thenReturn("TITULAR CREDITO");
		when(datosTransferenciaAgendada.getCuentaPropia()).thenReturn("N");
		when(datosTransferenciaAgendada.getTipoCtaDebito()).thenReturn("00");
		when(datosTransferenciaAgendada.getConcepto()).thenReturn("VARIOS");
		when(datosTransferenciaAgendada.getEmail()).thenReturn("prueba@hotmail.com");
		when(datosTransferenciaAgendada.getComentario()).thenReturn("prueba mensaje email");
		when(datosTransferenciaAgendada.getCodigoConcepto()).thenReturn("VAR");
		when(datosTransferenciaAgendada.getAnotacionesPersonales()).thenReturn("");
		when(datosTransferenciaAgendada.getConceptoAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada.getConceptoAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada.getConceptoAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada.getCotizacionTransferencia()).thenReturn("");
		when(datosTransferenciaAgendada.getCuentaPropia()).thenReturn("");
		when(datosTransferenciaAgendada.getDescripcionAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada.getDescripcionAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada.getDescripcionAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada.getFechaNac()).thenReturn("");
		when(datosTransferenciaAgendada.getIdCliente()).thenReturn("");

		when(pedidoTransferencia.getConfiguracionTransferenciaAgendada())
				.thenReturn(configuracionTransferenciaAgendada);
		when(configuracionTransferenciaAgendada.getNroOperacionTerminal()).thenReturn("000000");

		// Caja de Ahorro en Pesos
		when(pedidoTransferencia2.getDatosTransferenciaAgendada()).thenReturn(datosTransferenciaAgendada2);
		when(datosTransferenciaAgendada2.getImporteDebito()).thenReturn("0.1");
		when(datosTransferenciaAgendada2.getNombreCtaCredito()).thenReturn("NOMBRE TITULAR");
		when(datosTransferenciaAgendada2.getTitularCredito()).thenReturn("TITULAR CREDITO");
		when(datosTransferenciaAgendada2.getCuentaPropia()).thenReturn("N");
		when(datosTransferenciaAgendada2.getTipoCtaDebito()).thenReturn("01");
		when(datosTransferenciaAgendada2.getConcepto()).thenReturn("Préstamo");
		when(datosTransferenciaAgendada2.getEmail()).thenReturn("prueba@hotmail.com");
		when(datosTransferenciaAgendada2.getComentario()).thenReturn("prueba mensaje email");
		when(datosTransferenciaAgendada2.getCodigoConcepto()).thenReturn("VAR");
		when(datosTransferenciaAgendada2.getConcepto()).thenReturn("VAR");
		when(datosTransferenciaAgendada2.getAnotacionesPersonales()).thenReturn("");
		when(datosTransferenciaAgendada2.getConceptoAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada2.getConceptoAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada2.getConceptoAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada2.getCotizacionTransferencia()).thenReturn("");
		when(datosTransferenciaAgendada2.getCuentaPropia()).thenReturn("");
		when(datosTransferenciaAgendada2.getDescripcionAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada2.getDescripcionAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada2.getDescripcionAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada2.getFechaNac()).thenReturn("");
		when(datosTransferenciaAgendada2.getIdCliente()).thenReturn("");

		when(pedidoTransferencia2.getConfiguracionTransferenciaAgendada())
				.thenReturn(configuracionTransferenciaAgendada2);
		when(configuracionTransferenciaAgendada2.getNroOperacionTerminal()).thenReturn("000000");

		// Cuenta Unica en Pesos
		when(pedidoTransferencia3.getDatosTransferenciaAgendada()).thenReturn(datosTransferenciaAgendada3);
		when(datosTransferenciaAgendada3.getImporteDebito()).thenReturn("0.1");
		when(datosTransferenciaAgendada3.getNombreCtaCredito()).thenReturn("NOMBRE TITULAR");
		when(datosTransferenciaAgendada3.getTitularCredito()).thenReturn("TITULAR CREDITO");
		when(datosTransferenciaAgendada3.getCuentaPropia()).thenReturn("N");
		when(datosTransferenciaAgendada3.getTipoCtaDebito()).thenReturn("09");
		when(datosTransferenciaAgendada3.getConcepto()).thenReturn("Varios");
		when(datosTransferenciaAgendada3.getEmail()).thenReturn("prueba@hotmail.com");
		when(datosTransferenciaAgendada3.getComentario()).thenReturn("prueba mensaje email");
		when(datosTransferenciaAgendada3.getCodigoConcepto()).thenReturn("VAR");
		when(datosTransferenciaAgendada3.getAnotacionesPersonales()).thenReturn("");
		when(datosTransferenciaAgendada3.getConceptoAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada3.getConceptoAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada3.getConceptoAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada3.getCotizacionTransferencia()).thenReturn("");
		when(datosTransferenciaAgendada3.getCuentaPropia()).thenReturn("");
		when(datosTransferenciaAgendada3.getDescripcionAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada3.getDescripcionAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada3.getDescripcionAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada3.getFechaNac()).thenReturn("");
		when(datosTransferenciaAgendada3.getIdCliente()).thenReturn("");

		when(pedidoTransferencia3.getConfiguracionTransferenciaAgendada())
				.thenReturn(configuracionTransferenciaAgendada3);
		when(configuracionTransferenciaAgendada3.getNroOperacionTerminal()).thenReturn("000000");

		// Caja de Ahorro en Dolares
		when(pedidoTransferencia4.getDatosTransferenciaAgendada()).thenReturn(datosTransferenciaAgendada4);
		when(datosTransferenciaAgendada4.getImporteDebito()).thenReturn("10");
		when(datosTransferenciaAgendada4.getNombreCtaCredito()).thenReturn("NOMBRE TITULAR");
		when(datosTransferenciaAgendada4.getTitularCredito()).thenReturn("TITULAR CREDITO");
		when(datosTransferenciaAgendada4.getCuentaPropia()).thenReturn("N");
		when(datosTransferenciaAgendada4.getTipoCtaDebito()).thenReturn("04");
		when(datosTransferenciaAgendada4.getConcepto()).thenReturn("Expensas");
		when(datosTransferenciaAgendada4.getEmail()).thenReturn("prueba@hotmail.com");
		when(datosTransferenciaAgendada4.getComentario()).thenReturn("prueba mensaje email");
		when(datosTransferenciaAgendada4.getCodigoConcepto()).thenReturn("VAR");
		when(datosTransferenciaAgendada4.getAnotacionesPersonales()).thenReturn("");
		when(datosTransferenciaAgendada4.getConceptoAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada4.getConceptoAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada4.getConceptoAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada4.getCotizacionTransferencia()).thenReturn("");
		when(datosTransferenciaAgendada4.getCuentaPropia()).thenReturn("");
		when(datosTransferenciaAgendada4.getDescripcionAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada4.getDescripcionAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada4.getDescripcionAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada4.getFechaNac()).thenReturn("");
		when(datosTransferenciaAgendada4.getIdCliente()).thenReturn("");

		when(pedidoTransferencia4.getConfiguracionTransferenciaAgendada())
				.thenReturn(configuracionTransferenciaAgendada4);
		when(configuracionTransferenciaAgendada4.getNroOperacionTerminal()).thenReturn("000000");

		// Cuenta Corriente en Dolares
		when(pedidoTransferencia5.getDatosTransferenciaAgendada()).thenReturn(datosTransferenciaAgendada5);
		when(datosTransferenciaAgendada5.getImporteDebito()).thenReturn("0.1");
		when(datosTransferenciaAgendada5.getNombreCtaCredito()).thenReturn("NOMBRE TITULAR");
		when(datosTransferenciaAgendada5.getTitularCredito()).thenReturn("TITULAR CREDITO");
		when(datosTransferenciaAgendada5.getCuentaPropia()).thenReturn("N");
		when(datosTransferenciaAgendada5.getTipoCtaDebito()).thenReturn("03");
		when(datosTransferenciaAgendada5.getConcepto()).thenReturn("Expensas");
		when(datosTransferenciaAgendada5.getEmail()).thenReturn("prueba@hotmail.com");
		when(datosTransferenciaAgendada5.getComentario()).thenReturn("prueba mensaje email");
		when(datosTransferenciaAgendada5.getCodigoConcepto()).thenReturn("VAR");
		when(datosTransferenciaAgendada5.getAnotacionesPersonales()).thenReturn("");
		when(datosTransferenciaAgendada5.getConceptoAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada5.getConceptoAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada5.getConceptoAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada5.getCotizacionTransferencia()).thenReturn("");
		when(datosTransferenciaAgendada5.getCuentaPropia()).thenReturn("");
		when(datosTransferenciaAgendada5.getDescripcionAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada5.getDescripcionAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada5.getDescripcionAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada5.getFechaNac()).thenReturn("");
		when(datosTransferenciaAgendada5.getIdCliente()).thenReturn("");

		when(pedidoTransferencia5.getConfiguracionTransferenciaAgendada())
				.thenReturn(configuracionTransferenciaAgendada5);
		when(configuracionTransferenciaAgendada5.getNroOperacionTerminal()).thenReturn("000000");

		// Caja de Ahorro en Dolares
		when(pedidoTransferencia6.getDatosTransferenciaAgendada()).thenReturn(datosTransferenciaAgendada6);
		when(datosTransferenciaAgendada6.getImporteDebito()).thenReturn("0.1");
		when(datosTransferenciaAgendada6.getNombreCtaCredito()).thenReturn("NOMBRE TITULAR");
		when(datosTransferenciaAgendada6.getTitularCredito()).thenReturn("TITULAR CREDITO");
		when(datosTransferenciaAgendada6.getCuentaPropia()).thenReturn("N");
		when(datosTransferenciaAgendada6.getTipoCtaDebito()).thenReturn("10");
		when(datosTransferenciaAgendada6.getConcepto()).thenReturn("Expensas");
		when(datosTransferenciaAgendada6.getEmail()).thenReturn("prueba@hotmail.com");
		when(datosTransferenciaAgendada6.getComentario()).thenReturn("prueba mensaje email");
		when(datosTransferenciaAgendada6.getCodigoConcepto()).thenReturn("VAR");
		when(datosTransferenciaAgendada6.getAnotacionesPersonales()).thenReturn("");
		when(datosTransferenciaAgendada6.getConceptoAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada6.getConceptoAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada6.getConceptoAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada6.getCotizacionTransferencia()).thenReturn("");
		when(datosTransferenciaAgendada6.getCuentaPropia()).thenReturn("");
		when(datosTransferenciaAgendada6.getDescripcionAdicional1()).thenReturn("");
		when(datosTransferenciaAgendada6.getDescripcionAdicional2()).thenReturn("");
		when(datosTransferenciaAgendada6.getDescripcionAdicional3()).thenReturn("");
		when(datosTransferenciaAgendada6.getFechaNac()).thenReturn("");
		when(datosTransferenciaAgendada6.getIdCliente()).thenReturn("");

		when(pedidoTransferencia6.getConfiguracionTransferenciaAgendada())
				.thenReturn(configuracionTransferenciaAgendada6);
		when(configuracionTransferenciaAgendada6.getNroOperacionTerminal()).thenReturn("000000");

		// Cuenta Corriente en Pesos
		transferenciaAgendada.setTipoAgendamiento("I");
		transferenciaAgendada.setPedidoTransferenciaAgendada(pedidoTransferencia);
		transferenciaAgendada.setFechaEjecucion("20160620");
		transferenciaAgendada.setTxServicio("TRANSF_BCO_RIO");
		transferenciaAgendada.setFechaBaseRec("20160620000000");
		transferenciaAgendada.setFrecuenciaRec("NR");
		transferenciaAgendada.setCtaOrigTipo("00");
		transferenciaAgendada.setCtaOrigNum("81995");
		transferenciaAgendada.setCtaOrigSuc("33");
		transferenciaAgendada.setCtaDestNum("3607390");
		transferenciaAgendada.setCtaDestSuc("0");
		transferenciaAgendada.setDiasAvisoPrevio("");
		transferenciaAgendada.setConcepto("VAR");
		transferenciaAgendada.setRioRio(true);
		transferenciaAgendada.setTipoCtaDebito("1");
		transferenciaAgendada.setImporteDebito("100");

		// Caja de Ahorro en Pesos
		transferenciaAgendada2.setTipoAgendamiento("E");
		transferenciaAgendada2.setPedidoTransferenciaAgendada(pedidoTransferencia2);
		transferenciaAgendada2.setFechaEjecucion("20160620");
		transferenciaAgendada2.setTxServicio("TRANSF_BCO_RIO");
		transferenciaAgendada2.setFechaBaseRec("20160620000000");
		transferenciaAgendada2.setCtaOrigTipo("01");
		transferenciaAgendada2.setCtaOrigNum("81995");
		transferenciaAgendada2.setCtaOrigSuc("33");
		transferenciaAgendada2.setCtaDestNum("3607390");
		transferenciaAgendada2.setCtaDestSuc("0");
		transferenciaAgendada2.setDiasAvisoPrevio("");
		transferenciaAgendada2.setRioRio(true);
		transferenciaAgendada2.setTipoCtaDebito("1");
		transferenciaAgendada2.setImporteDebito("100");
		transferenciaAgendada.setConcepto("VAR");
		// Cuenta Unica en Pesos
		transferenciaAgendada3.setTipoAgendamiento("E");
		transferenciaAgendada3.setPedidoTransferenciaAgendada(pedidoTransferencia3);
		transferenciaAgendada3.setFechaEjecucion("20160620");
		transferenciaAgendada3.setTxServicio("NOT_TRANSF_BCO_RIO");
		transferenciaAgendada3.setFechaBaseRec("20160620000000");
		transferenciaAgendada3.setCtaOrigTipo("09");
		transferenciaAgendada3.setCtaOrigNum("81995");
		transferenciaAgendada3.setCtaOrigSuc("33");
		transferenciaAgendada3.setCtaDestNum("3607390");
		transferenciaAgendada3.setCtaDestSuc("0");
		transferenciaAgendada3.setDiasAvisoPrevio("");
		transferenciaAgendada3.setRioRio(true);
		transferenciaAgendada3.setTipoCtaDebito("1");
		transferenciaAgendada3.setImporteDebito("100");
		transferenciaAgendada.setConcepto("VAR");
		// Caja de Ahorro en Dolares
		transferenciaAgendada4.setTipoAgendamiento("E");
		transferenciaAgendada4.setPedidoTransferenciaAgendada(pedidoTransferencia4);
		transferenciaAgendada4.setFechaEjecucion("20160620");
		transferenciaAgendada4.setTxServicio("NOT_TRANSF_BCO_RIO");
		transferenciaAgendada4.setFechaBaseRec("20160620000000");
		transferenciaAgendada4.setCtaOrigTipo("04");
		transferenciaAgendada4.setCtaOrigNum("81995");
		transferenciaAgendada4.setCtaOrigSuc("33");
		transferenciaAgendada4.setCtaDestNum("3607390");
		transferenciaAgendada4.setCtaDestSuc("0");
		transferenciaAgendada4.setDiasAvisoPrevio("");
		transferenciaAgendada4.setRioRio(true);
		transferenciaAgendada4.setTipoCtaDebito("1");
		transferenciaAgendada4.setImporteDebito("100");
		// Cuenta Corriente en Dolares
		transferenciaAgendada5.setTipoAgendamiento("E");
		transferenciaAgendada5.setPedidoTransferenciaAgendada(pedidoTransferencia5);
		transferenciaAgendada5.setFechaEjecucion("20160620");
		transferenciaAgendada5.setTxServicio("NOT_TRANSF_BCO_RIO");
		transferenciaAgendada5.setFechaBaseRec("20160620000000");
		transferenciaAgendada5.setCtaOrigTipo("03");
		transferenciaAgendada5.setCtaOrigNum("81995");
		transferenciaAgendada5.setCtaOrigSuc("33");
		transferenciaAgendada5.setCtaDestNum("3607390");
		transferenciaAgendada5.setCtaDestSuc("0");
		transferenciaAgendada5.setDiasAvisoPrevio("");
		transferenciaAgendada5.setRioRio(true);
		transferenciaAgendada5.setTipoCtaDebito("3");
		transferenciaAgendada5.setImporteDebito("100");
		// Cuenta Unica en Dolares
		transferenciaAgendada6.setTipoAgendamiento("E");
		transferenciaAgendada6.setPedidoTransferenciaAgendada(pedidoTransferencia6);
		transferenciaAgendada6.setFechaEjecucion("20160620");
		transferenciaAgendada6.setTxServicio("NOT_TRANSF_BCO_RIO");
		transferenciaAgendada6.setFechaBaseRec("20160620000000");
		transferenciaAgendada6.setCtaOrigTipo("10");
		transferenciaAgendada6.setCtaOrigNum("81995");
		transferenciaAgendada6.setCtaOrigSuc("33");
		transferenciaAgendada6.setCtaDestNum("3607390");
		transferenciaAgendada6.setCtaDestSuc("0");
		transferenciaAgendada6.setDiasAvisoPrevio("");
		transferenciaAgendada6.setRioRio(true);
		transferenciaAgendada6.setTipoCtaDebito("10");
		transferenciaAgendada6.setImporteDebito("100");
		
		transferenciaAgendadaList.add(transferenciaAgendada);
		transferenciaAgendadaList.add(transferenciaAgendada2);
		transferenciaAgendadaList.add(transferenciaAgendada3);
		transferenciaAgendadaList.add(transferenciaAgendada4);
		transferenciaAgendadaList.add(transferenciaAgendada5);
		transferenciaAgendadaList.add(transferenciaAgendada6);
		
		when(agendaTransferenciaDAO.obtenerTransferenciasAgendadas(Matchers.any(ConsultaAgendaTransferencias.class),
				Matchers.anyBoolean())).thenReturn(transferenciaAgendadaList);
		when(cuentaBO.getCuentaById(idOrigen, cliente)).thenReturn(cuentaOrigen);
		when(cuentaBO.getCuentaById(idDestino, cliente)).thenReturn(cuentaDestino);

		when(cuentaOrigen.getAlias()).thenReturn("Cuenta Papá");
		when(cuentaOrigen.getNroCuentaProducto()).thenReturn("81995");
		when(cuentaOrigen.getNroSucursal()).thenReturn("33");

		when(cuentaDestino.getAlias()).thenReturn("Cuenta Papá");
		when(cuentaDestino.getNroCuentaProducto()).thenReturn("81995");
		when(cuentaDestino.getNroSucursal()).thenReturn("33");


		Respuesta<List<TransferenciaAgendadaDTO>> respuestaTransferenciaAgendadaDto = agendaTransferenciaBO
				.obtenerTransferenciasAgendadasRioRio(cliente);
		assertNotNull(respuestaTransferenciaAgendadaDto);
		assertEquals(respuestaTransferenciaAgendadaDto.getEstadoRespuesta(), EstadoRespuesta.OK);
		assertNotNull(respuestaTransferenciaAgendadaDto.getRespuesta());

		List<TransferenciaAgendadaDTO> listRespuesta = respuestaTransferenciaAgendadaDto.getRespuesta();

		assertEquals(listRespuesta.get(0).getFechaEjecucion(),
				new SimpleDateFormat("yyyyMMddHHmmss").parse("20160620000000"));
		assertEquals(listRespuesta.get(0).getDestinatario().getBanco().getDetalle(), "Cuenta Propia");
		assertEquals(listRespuesta.get(0).getDestinatario().getBanco().getNombre(), BancoEnum.SANTANDER_RIO.getDescripcion());
		assertNotNull(listRespuesta.get(0).getDatosOrigen());
		assertNotNull(listRespuesta.get(0).getDatosOrigen().getTransferenciaAgendada());

		assertEquals(listRespuesta.get(1).getFechaEjecucion(),
				new SimpleDateFormat("yyyyMMddHHmmss").parse("20160620000000"));
		assertEquals(listRespuesta.get(1).getDestinatario().getBanco().getDetalle(), "Cuenta Propia");
		assertEquals(listRespuesta.get(1).getDestinatario().getBanco().getNombre(), BancoEnum.SANTANDER_RIO.getDescripcion());
		assertNotNull(listRespuesta.get(1).getDatosOrigen());

		assertEquals(listRespuesta.get(2).getFechaEjecucion(),
				new SimpleDateFormat("yyyyMMddHHmmss").parse("20160620000000"));
		assertEquals(listRespuesta.get(2).getDestinatario().getBanco().getDetalle(), "Cuenta Propia");
		assertEquals(listRespuesta.get(2).getDestinatario().getBanco().getNombre(), BancoEnum.SANTANDER_RIO.getDescripcion());
		assertNotNull(listRespuesta.get(2).getDatosOrigen());

	}

	/**
	 * Obtener concepto from codigo test.
	 */
	@Test
	public void obtenerConceptoFromCodigoTest() {
		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("VAR"), ConceptoTransferenciaEnum.VARIOS);
		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("ALQ"), ConceptoTransferenciaEnum.ALQUILER);
		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("CUO"), ConceptoTransferenciaEnum.CUOTA);
		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("EXP"), ConceptoTransferenciaEnum.EXPENSAS);
		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("FAC"), ConceptoTransferenciaEnum.FACTURA);
		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("PRE"), ConceptoTransferenciaEnum.PRESTAMO);
		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("SEG"), ConceptoTransferenciaEnum.SEGURO);
		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("HON"), ConceptoTransferenciaEnum.HONORARIOS);
//		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("OIN"), ConceptoTransferenciaEnum.INMOBILIARIAS);
//		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("OIH"), ConceptoTransferenciaEnum.INMOBILIARIAS_HABITUALISTAS);
//		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("BRH"), ConceptoTransferenciaEnum.BIENES_REG_HABIT);
//		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("BRN"), ConceptoTransferenciaEnum.BIENES_REG_NO_HABIT);
//		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("SON"), ConceptoTransferenciaEnum.SUSCRIP_OBLIG_NEG);
//		assertEquals(ConceptoTransferenciaEnum.getConceptoFromCodigo("APC"), ConceptoTransferenciaEnum.APORTES_CAPITAL);
	}

	/**
	 * Obtener agenda transferencias error test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void obtenerAgendaTransferenciasErrorTest_RRio() throws DAOException {

		Cliente cliente = mock(Cliente.class);
		Respuesta<Mensaje> respuestaMensaje = mock(Respuesta.class);
		Mensaje mensaje = mock(Mensaje.class);

		when(mensaje.getMensaje()).thenReturn("Error mensaje");
		when(respuestaMensaje.getRespuesta()).thenReturn(mensaje);
		when(agendaTransferenciaDAO.obtenerTransferenciasAgendadas(Matchers.any(ConsultaAgendaTransferencias.class),
				Matchers.anyBoolean())).thenThrow(new DAOException());
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<List<TransferenciaAgendadaDTO>> respuestaTransferenciaAgendadaDto = agendaTransferenciaBO
				.obtenerTransferenciasAgendadasRioRio(cliente);
		assertNotNull(respuestaTransferenciaAgendadaDto);
		assertEquals(respuestaTransferenciaAgendadaDto.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		assertSame(respuestaTransferenciaAgendadaDto.getItemsMensajeRespuesta().iterator().next().getTipoError(),
				TipoError.ERROR_GENERICO.getDescripcion());
	}

	/**
	 * Obtener agenda transferencias error test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void obtenerAgendaTransferenciasErrorTest_OB() throws DAOException {

		Cliente cliente = mock(Cliente.class);
		Respuesta<Mensaje> respuestaMensaje = mock(Respuesta.class);
		Mensaje mensaje = mock(Mensaje.class);

		when(mensaje.getMensaje()).thenReturn("Error mensaje");
		when(respuestaMensaje.getRespuesta()).thenReturn(mensaje);
		when(agendaTransferenciaDAO.obtenerTransferenciasAgendadas(Matchers.any(ConsultaAgendaTransferencias.class),
				Matchers.anyBoolean())).thenThrow(new DAOException());
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<List<TransferenciaAgendadaDTO>> respuestaTransferenciaAgendadaDto = agendaTransferenciaBO
				.obtenerTransferenciasAgendadasOtrosBancos(cliente);
		assertNotNull(respuestaTransferenciaAgendadaDto);
		assertEquals(respuestaTransferenciaAgendadaDto.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		assertSame(respuestaTransferenciaAgendadaDto.getItemsMensajeRespuesta().iterator().next().getTipoError(),
				TipoError.ERROR_GENERICO.getDescripcion());
	}

	/**
	 * Verificar orden de transferencias.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	@Test
	public void verificarOrdenDeTransferencias() throws ParseException {

		List<TransferenciaAgendadaDTO> trasnferenciaAgendadaDTOList = new ArrayList<TransferenciaAgendadaDTO>();

		TransferenciaAgendadaDTO transferenciaAgendadaRecurrenteDTO = new TransferenciaAgendadaDTO();
		transferenciaAgendadaRecurrenteDTO.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECURRENTE);
		transferenciaAgendadaRecurrenteDTO.setFechaEjecucion(
				agendaTransferenciaBO.limpiarHoras(agendaTransferenciaBO.parsearFecha("20160620010000")));
		trasnferenciaAgendadaDTOList.add(transferenciaAgendadaRecurrenteDTO);

		TransferenciaAgendadaDTO transferenciaAgendadaRecurrenteDTO2 = new TransferenciaAgendadaDTO();
		transferenciaAgendadaRecurrenteDTO2.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECURRENTE);
		transferenciaAgendadaRecurrenteDTO2.setFechaEjecucion(
				agendaTransferenciaBO.limpiarHoras(agendaTransferenciaBO.parsearFecha("20160621020000")));
		trasnferenciaAgendadaDTOList.add(transferenciaAgendadaRecurrenteDTO2);

		TransferenciaAgendadaDTO transferenciaAgendadaRecurrenteDTO3 = new TransferenciaAgendadaDTO();
		transferenciaAgendadaRecurrenteDTO3.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECURRENTE);
		transferenciaAgendadaRecurrenteDTO3.setFechaEjecucion(
				agendaTransferenciaBO.limpiarHoras(agendaTransferenciaBO.parsearFecha("20160720020202")));
		trasnferenciaAgendadaDTOList.add(transferenciaAgendadaRecurrenteDTO3);

		TransferenciaAgendadaDTO transferenciaAgendadaRecordatorioDTO = new TransferenciaAgendadaDTO();
		transferenciaAgendadaRecordatorioDTO.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECORDATORIO);
		transferenciaAgendadaRecordatorioDTO.setFechaEjecucion(
				agendaTransferenciaBO.limpiarHoras(agendaTransferenciaBO.parsearFecha("20160620010100")));
		trasnferenciaAgendadaDTOList.add(transferenciaAgendadaRecordatorioDTO);

		TransferenciaAgendadaDTO transferenciaAgendadaRecordatorioDTO2 = new TransferenciaAgendadaDTO();
		transferenciaAgendadaRecordatorioDTO2.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECORDATORIO);
		transferenciaAgendadaRecordatorioDTO2.setFechaEjecucion(
				agendaTransferenciaBO.limpiarHoras(agendaTransferenciaBO.parsearFecha("20160622020200")));
		trasnferenciaAgendadaDTOList.add(transferenciaAgendadaRecordatorioDTO2);

		TransferenciaAgendadaDTO transferenciaAgendadaRecordatorioDTO3 = new TransferenciaAgendadaDTO();
		transferenciaAgendadaRecordatorioDTO3.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECORDATORIO);
		transferenciaAgendadaRecordatorioDTO3.setFechaEjecucion(
				agendaTransferenciaBO.limpiarHoras(agendaTransferenciaBO.parsearFecha("20160720020202")));
		trasnferenciaAgendadaDTOList.add(transferenciaAgendadaRecordatorioDTO3);

		TransferenciaAgendadaDTO transferenciaAgendadaProgramadaDTO = new TransferenciaAgendadaDTO();
		transferenciaAgendadaProgramadaDTO.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.PROGRAMADA);
		transferenciaAgendadaProgramadaDTO.setFechaEjecucion(
				agendaTransferenciaBO.limpiarHoras(agendaTransferenciaBO.parsearFecha("20160620010300")));
		trasnferenciaAgendadaDTOList.add(transferenciaAgendadaProgramadaDTO);

		TransferenciaAgendadaDTO transferenciaAgendadaProgramadaDTO2 = new TransferenciaAgendadaDTO();
		transferenciaAgendadaProgramadaDTO2.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.PROGRAMADA);
		transferenciaAgendadaProgramadaDTO2.setFechaEjecucion(
				agendaTransferenciaBO.limpiarHoras(agendaTransferenciaBO.parsearFecha("20160623020300")));
		trasnferenciaAgendadaDTOList.add(transferenciaAgendadaProgramadaDTO2);

		TransferenciaAgendadaDTO transferenciaAgendadaProgramadaDTO4 = new TransferenciaAgendadaDTO();
		transferenciaAgendadaProgramadaDTO4.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.PROGRAMADA);
		transferenciaAgendadaProgramadaDTO4.setFechaEjecucion(
				agendaTransferenciaBO.limpiarHoras(agendaTransferenciaBO.parsearFecha("20160720020202")));
		trasnferenciaAgendadaDTOList.add(transferenciaAgendadaProgramadaDTO4);

		System.out.println("TEST ----INICIO-----");
		System.out.println("TEST - LISTA DESORDENADA ");

		for (TransferenciaAgendadaDTO transferenciaAgendadaDTO : trasnferenciaAgendadaDTOList) {
			System.out.println(transferenciaAgendadaDTO.getClass().getName() + "  -  "
					+ transferenciaAgendadaDTO.getFechaEjecucion());
		}
		agendaTransferenciaBO.ordenarListaPorFecha(trasnferenciaAgendadaDTOList);

		assertNotNull(trasnferenciaAgendadaDTOList);
		System.out.println("TEST ---------");
		System.out.println("TEST - LISTA ORDENADA");
		for (TransferenciaAgendadaDTO transferenciaAgendadaDTO : trasnferenciaAgendadaDTOList) {
			System.out.println(transferenciaAgendadaDTO.getClass().getName() + "  -  "
					+ transferenciaAgendadaDTO.getFechaEjecucion());
		}
		System.out.println("TEST ----FIN-----");
	}

	private List<TransferenciaAgendadaDTO> mockparsearTransferenciaAgendadaToDto() throws ParseException {

		List<TransferenciaAgendadaDTO> transferenciaAgendadaDTOList = new ArrayList<TransferenciaAgendadaDTO>();

		TransferenciaAgendadaDTO recurrente3 = null;
		TransferenciaAgendadaDTO dto2 = null;
		TransferenciaAgendadaDTO dto3 = null;
		TransferenciaAgendadaDTO dto4 = null;
		TransferenciaAgendadaDTO dto5 = null;// =
												// mapearTransferenciaAgendada(transferenciaAgendada,
												// cliente);

		// AbstractCuenta cuentaOrigen = cuentaBO.getCuentaById(
		// getIdentificacionCuenta(transferenciaAgendada.getCtaOrigNum(),
		// transferenciaAgendada.getCtaOrigSuc()),
		// cliente);
		// AbstractCuenta cuentaDestino = cuentaBO.getCuentaById(
		// getIdentificacionCuenta(transferenciaAgendada.getCtaDestNum(),
		// transferenciaAgendada.getCtaDestSuc()),
		// cliente);

		TransferenciaAgendadaDTO recurrente1 = new TransferenciaAgendadaDTO(); // is
																				// riorio
		recurrente1.setIdDef("12333");
		recurrente1.setIdEvento("IdEvento");
		recurrente1.setIdDatosRec("IdDatosRec");
		recurrente1.setHaciaOtroBanco(false);
		recurrente1.setFechaEjecucion(new Date());
		DestinatarioDTO destinatario1 = new DestinatarioDTO();
		destinatario1.setNombre("Falabella :)");
		destinatario1.setDescripcion("Otros");
		BancoDTO banco1 = new BancoDTO();
		banco1.setNombre("detalle");
		banco1.setDetalle(BancoEnum.SANTANDER_RIO.getDescripcion());
		destinatario1.setBanco(banco1);
		recurrente1.setDestinatario(destinatario1);
		recurrente1.setImporte(new BigDecimal("100"));
		recurrente1.setMoneda(DivisaEnum.PESO);
		recurrente1.setCuentaAliasOrigen("cuentaOrigenAlias");
		recurrente1.setCuentaAliasDestino("cuentaDestinoAlias");
		recurrente1.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_2_MESES);

		TransferenciaAgendadaDTO recurrente2 = new TransferenciaAgendadaDTO();
		recurrente2.setIdDef("12373");
		recurrente2.setIdEvento("IdEvento");
		recurrente2.setIdDatosRec("IdDatosRec");
		recurrente2.setHaciaOtroBanco(false);
		recurrente2.setFechaEjecucion(new Date());
		DestinatarioDTO destinatario2 = new DestinatarioDTO();
		destinatario2.setNombre("Musi Mundillo :)");
		destinatario2.setDescripcion("Otros");
		BancoDTO banco2 = new BancoDTO();
		banco2.setNombre("detalle");
		banco2.setDetalle(BancoEnum.SANTANDER_RIO.getDescripcion());
		destinatario2.setBanco(banco2);
		recurrente2.setDestinatario(destinatario2);
		recurrente2.setImporte(new BigDecimal("100"));
		recurrente2.setMoneda(DivisaEnum.PESO);
		recurrente2.setCuentaAliasOrigen("cuentaOrigenAlias");
		recurrente2.setCuentaAliasDestino("cuentaDestinoAlias");
		recurrente2.setFrecuencia(FrecuenciaTransferenciaAgendada.FRECUENCIA_E);

		// ==================
		// TransferenciaAgendadaRecordatorioDTO automaticaProgramadaMF = new
		// TransferenciaAgendadaRecordatorioDTO();
		// recurrente3 = automaticaProgramadaMF;
		// automaticaRec5.setIdDef("12333");
		// automaticaRec5.setIdEvento("IdEvento");
		// automaticaRec5.setIdDatosRec("IdDatosRec");
		// automaticaRec5.setIsRioRio(true);
		// SimpleDateFormat sdf5 = new SimpleDateFormat("dd-M-yyyy");
		// String dateInString5 = "31-08-2016";
		// Date date5 = sdf5.parse(dateInString5);
		// automaticaRec5.setFechaEjecucion(date5);
		// DestinatarioDTO destinatario6 = new DestinatarioDTO();
		// destinatario6.setNombre("Vacaciones");
		// destinatario6.setDescripcion("Viajes y Turismo S.A.");
		// BancoDTO banco6 = new BancoDTO();
		// banco6.setNombre(BancoEnum.SANTANDER_RIO.getDescripcion());
		// banco6.setDetalle("detalle 4");
		// destinatario6.setBanco(banco6);
		// automaticaRec5.setDestinatario(destinatario6);
		// automaticaRec5.setImporte(new BigDecimal("1040"));
		// automaticaRec5.setDivisa(DivisaEnum.PESO);
		// automaticaRec5.setCuentaAliasOrigen("cuentaOrigenAlias");
		// automaticaRec5.setCuentaAliasDestino("cuentaDestinoAlias");
		// FrecuenciaTransferenciaAgendada frecuenciaTransferenciaAgendada =
		// null;
		// frecuenciaTransferenciaAgendada =
		// FrecuenciaTransferenciaAgendada.FRECUENCIA_E;
		// automaticaRec5.setRecurrencia(frecuenciaTransferenciaAgendada);

		TransferenciaAgendadaDTO automaticaProgramadaMF = new TransferenciaAgendadaDTO();
		automaticaProgramadaMF.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.PROGRAMADA);
		automaticaProgramadaMF.setIdDef("2333");
		automaticaProgramadaMF.setIdEvento("IdEvento");
		automaticaProgramadaMF.setIdDatosRec("IdDatosRec");
		automaticaProgramadaMF.setHaciaOtroBanco(false);
		automaticaProgramadaMF.setFechaEjecucion(new Date());
		DestinatarioDTO destinatario3 = new DestinatarioDTO();
		destinatario3.setNombre("Falabella :)");
		destinatario3.setDescripcion("Otros");
		BancoDTO banco3 = new BancoDTO();
		banco3.setNombre("detalle");
		banco3.setDetalle("Banco Macro");
		destinatario3.setBanco(banco3);
		automaticaProgramadaMF.setDestinatario(destinatario3);
		automaticaProgramadaMF.setImporte(new BigDecimal("560"));
		automaticaProgramadaMF.setMoneda(DivisaEnum.PESO);
		automaticaProgramadaMF.setCuentaAliasOrigen("cuentaOrigenAlias");
		automaticaProgramadaMF.setCuentaAliasDestino("cuentaDestinoAlias");
		FrecuenciaTransferenciaAgendada frecuenciaTransferenciaAgendadap = FrecuenciaTransferenciaAgendada.FRECUENCIA_E;
		automaticaProgramadaMF.setFrecuencia(frecuenciaTransferenciaAgendadap);
		recurrente3 = automaticaProgramadaMF;// is riorio

		////////////////////////////

		TransferenciaAgendadaDTO automaticaProgramada2 = new TransferenciaAgendadaDTO();
		dto2 = automaticaProgramada2;
		dto2.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.PROGRAMADA);
		dto2.setIdDef("12333");
		dto2.setIdEvento("IdEvento");
		dto2.setIdDatosRec("IdDatosRec");
		dto2.setHaciaOtroBanco(false);
		DestinatarioDTO destinatario22 = new DestinatarioDTO();
		destinatario22.setNombre("Alquiler");
		destinatario22.setDescripcion("M. A.");
		BancoDTO banco22 = new BancoDTO();
		banco22.setNombre("detalle 2");
		banco22.setDetalle("Galicia");
		destinatario22.setBanco(banco22);
		dto2.setDestinatario(destinatario22);
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy");
		String dateInString2 = "31-01-2017";
		Date date2 = sdf2.parse(dateInString2);
		dto2.setFechaEjecucion(date2);
		dto2.setImporte(new BigDecimal("100.11"));
		dto2.setMoneda(DivisaEnum.PESO);
		dto2.setCuentaAliasOrigen("cuentaOrigenAlias");
		dto2.setCuentaAliasDestino("cuentaDestinoAlias");

		TransferenciaAgendadaDTO automaticaProgramada3 = new TransferenciaAgendadaDTO();
		dto3 = automaticaProgramada3;
		dto3.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.PROGRAMADA);
		dto3.setIdDef("12333");
		dto3.setIdEvento("IdEvento");
		dto3.setIdDatosRec("IdDatosRec");
		dto3.setHaciaOtroBanco(false);
		DestinatarioDTO destinatario4 = new DestinatarioDTO();
		destinatario4.setNombre("Alquiler");
		destinatario4.setDescripcion("Inmobiliaria QQ");
		BancoDTO banco4 = new BancoDTO();
		banco4.setNombre("detalle 3");
		banco4.setDetalle(BancoEnum.SANTANDER_RIO.getDescripcion());
		destinatario4.setBanco(banco4);
		dto3.setDestinatario(destinatario4);
		SimpleDateFormat sdf3 = new SimpleDateFormat("dd-M-yyyy");
		String dateInString3 = "29-08-2016";
		Date date3 = sdf3.parse(dateInString3);
		dto3.setFechaEjecucion(date3);
		dto3.setImporte(new BigDecimal("254.50"));
		dto3.setMoneda(DivisaEnum.PESO);
		dto3.setCuentaAliasOrigen("cuentaOrigenAlias");
		dto3.setCuentaAliasDestino("cuentaDestinoAlias");

		TransferenciaAgendadaDTO automaticaProgramada4 = new TransferenciaAgendadaDTO();
		dto4 = automaticaProgramada4;
		dto4.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.PROGRAMADA);
		dto4.setIdDef("12333");
		dto4.setIdEvento("IdEvento");
		dto4.setIdDatosRec("IdDatosRec");
		dto4.setHaciaOtroBanco(false);
		DestinatarioDTO destinatario5 = new DestinatarioDTO();
		destinatario5.setNombre("Vacaciones");
		destinatario5.setDescripcion("Viajes y Turismo S.A.");
		BancoDTO banco5 = new BancoDTO();
		banco5.setNombre("detalle 4");
		banco5.setDetalle(BancoEnum.SANTANDER_RIO.getDescripcion());
		destinatario5.setBanco(banco5);
		dto4.setDestinatario(destinatario5);
		SimpleDateFormat sdf4 = new SimpleDateFormat("dd-M-yyyy");
		String dateInString4 = "31-02-2016";
		Date date4 = sdf4.parse(dateInString4);
		dto4.setFechaEjecucion(date4);
		dto4.setImporte(new BigDecimal("100"));
		dto4.setMoneda(DivisaEnum.PESO);
		dto4.setCuentaAliasOrigen("cuentaOrigenAlias");
		dto4.setCuentaAliasDestino("cuentaDestinoAlias");

		TransferenciaAgendadaDTO automaticaRec5 = new TransferenciaAgendadaDTO();
		automaticaRec5.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECURRENTE);
		automaticaRec5.setIdDef("12333");
		automaticaRec5.setIdEvento("IdEvento");
		automaticaRec5.setIdDatosRec("IdDatosRec");
		automaticaRec5.setHaciaOtroBanco(false);
		dto5 = automaticaRec5;
		SimpleDateFormat sdf5 = new SimpleDateFormat("dd-M-yyyy");
		String dateInString5 = "31-08-2016";
		Date date5 = sdf5.parse(dateInString5);
		automaticaRec5.setFechaEjecucion(date5);
		DestinatarioDTO destinatario6 = new DestinatarioDTO();
		destinatario6.setNombre("Vacaciones");
		destinatario6.setDescripcion("Viajes y Turismo S.A.");
		BancoDTO banco6 = new BancoDTO();
		banco6.setNombre(BancoEnum.SANTANDER_RIO.getDescripcion());
		banco6.setDetalle("detalle 4");
		destinatario6.setBanco(banco6);
		automaticaRec5.setDestinatario(destinatario6);
		automaticaRec5.setImporte(new BigDecimal("1040"));
		automaticaRec5.setMoneda(DivisaEnum.PESO);
		automaticaRec5.setCuentaAliasOrigen("cuentaOrigenAlias");
		automaticaRec5.setCuentaAliasDestino("cuentaDestinoAlias");
		FrecuenciaTransferenciaAgendada frecuenciaTransferenciaAgendada = null;
		frecuenciaTransferenciaAgendada = FrecuenciaTransferenciaAgendada.FRECUENCIA_E;
		automaticaRec5.setFrecuencia(frecuenciaTransferenciaAgendada);

		transferenciaAgendadaDTOList.add(recurrente1);
		transferenciaAgendadaDTOList.add(recurrente2);
		transferenciaAgendadaDTOList.add(recurrente3);
		transferenciaAgendadaDTOList.add(dto2);
		transferenciaAgendadaDTOList.add(dto3);
		transferenciaAgendadaDTOList.add(dto4);
		transferenciaAgendadaDTOList.add(automaticaRec5);

		return transferenciaAgendadaDTOList;
	}

	@Test
	public void ejecutarModificacionDeTransferenciaAgendada() throws DAOException {
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("La transferencia a {0}");
		Respuesta<TransferenciaAgendadaDTO> respuesta = new Respuesta<TransferenciaAgendadaDTO>();
		// TransferenciaAgendadaDTO transferenciaAgendadaDTO = new
		// TransferenciaAgendadaRecurrenteDTO();
		TransferenciaAgendadaDTO transferenciaAgendadaDTO = new TransferenciaAgendadaDTO();
		TransferenciaAgendada transferenciaAgendada = new TransferenciaAgendada();
		transferenciaAgendada.setTitularCredito("Jose");
		DatosOrigenTransferenciaAgendadaDTO datosOrigen = new DatosOrigenTransferenciaAgendadaDTO();
		datosOrigen.setTransferenciaAgendada(transferenciaAgendada);
		transferenciaAgendadaDTO.setDatosOrigen(datosOrigen);

		when(agendaTransferenciaDAO.ejecutarModificacionDeTransferenciaAgendada(
				Matchers.any(TransferenciaAgendadaDTO.class), Matchers.any(String.class), Matchers.any(Cliente.class)))
						.thenReturn(transferenciaAgendadaDTO);
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(TransferenciaAgendadaDTO.class))).thenReturn(respuesta);

		Respuesta<TransferenciaAgendadaDTO> respuestaTransferenciaAgendada = agendaTransferenciaBO
				.ejecutarModificacionDeTransferenciaAgendada(transferenciaAgendadaDTO, "AUTOMATICO", new Cliente());

		Assert.assertNotNull(respuestaTransferenciaAgendada.getRespuesta().getMensaje());

	}
}
