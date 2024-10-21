package ar.com.santanderrio.obp.servicios.home.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.bo.impl.TarjetasHomeBOImpl;
import ar.com.santanderrio.obp.servicios.home.entitites.ListaTarjetasDTO;
import ar.com.santanderrio.obp.servicios.home.entitites.TarjetaHomeDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class TarjetasHomeBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class TarjetasHomeBOTest {

	/** The tarjetas home BO. */
	@InjectMocks
	private TarjetasHomeBO tarjetasHomeBO = new TarjetasHomeBOImpl();

	/** The respuesta factory. */
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/**
	 * Obtener tarjetas test.
	 */
	@Test
	public void obtenerTarjetasTest() {

		Cliente cliente = new Cliente();
		List<Cuenta> listCuentas = new ArrayList<Cuenta>();
		Cuenta cuenta1 = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		Cuenta cuenta3 = new Cuenta();

		cuenta1.setAlias("Alias 1");
		cuenta1.setIndex(1);
		cuenta1.setNroTarjetaCredito("0000000338501234");
		cuenta1.setSaldoCuenta("50");
		cuenta1.setTipoCuenta(TipoCuenta.VISA.getCodigo().toString());
		cuenta1.setTipoCuentaEnum(TipoCuenta.VISA);
		cuenta1.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta1.setSaldoParaConformar("50");
		cuenta1.setClaseCuenta("H");
		

		cuenta2.setAlias("Alias 2");
		cuenta2.setIndex(2);
		cuenta2.setNroTarjetaCredito("0000000338505678");
		cuenta2.setSaldoCuenta("200");
		cuenta2.setTipoCuenta(TipoCuenta.AMEX.getCodigo().toString());
		cuenta2.setTipoCuentaEnum(TipoCuenta.AMEX);
		cuenta2.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta2.setSaldoParaConformar("200");
		cuenta2.setClaseCuenta("H");

		cuenta3.setAlias("Alias 3");
		cuenta3.setIndex(3);
		cuenta3.setNroTarjetaCredito("0000000338505678");
		cuenta3.setSaldoCuenta("200");
		cuenta3.setTipoCuenta(TipoCuenta.MASTERCARD.getCodigo().toString());
		cuenta3.setTipoCuentaEnum(TipoCuenta.MASTERCARD);
		cuenta3.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta3.setSaldoParaConformar("200");
		cuenta3.setClaseCuenta("H");

		listCuentas.add(cuenta1);
		listCuentas.add(cuenta2);
		listCuentas.add(cuenta3);
		cliente.setCuentas(listCuentas);

		Respuesta<ListaTarjetasDTO> respuesta = tarjetasHomeBO.obtenerTarjetas(cliente);
		assertNotNull(respuesta);

		ListaTarjetasDTO tarjetasDTO = respuesta.getRespuesta();
		assertNotNull(tarjetasDTO);

		List<TarjetaHomeDTO> listaTarjetas = tarjetasDTO.getTarjetas();
		assertTrue(listaTarjetas.size() > 0);
		assertEquals(listaTarjetas.get(0).getAlias(), "Alias 1");
		assertEquals(listaTarjetas.get(0).getHasAlias(), true);
		assertEquals(listaTarjetas.get(0).getId(), new Integer(1));
		assertEquals(listaTarjetas.get(0).getMarca(), "Visa");
		assertEquals(listaTarjetas.get(0).getNumero(), "XXXX-1234");
		assertEquals(listaTarjetas.get(0).getSaldoPesos(), new BigDecimal("0.50"));
		assertEquals(listaTarjetas.get(0).getProducto(), "VI");
		assertEquals(listaTarjetas.get(0).getSubProducto(), "BLACK");

		assertEquals(listaTarjetas.get(1).getAlias(), "Alias 2");
		assertEquals(listaTarjetas.get(1).getHasAlias(), true);
		assertEquals(listaTarjetas.get(1).getId(), new Integer(2));
		assertEquals(listaTarjetas.get(1).getMarca(), "Amex");
		assertEquals(listaTarjetas.get(1).getNumero(), "XXXX-50567");
		assertEquals(listaTarjetas.get(1).getSaldoPesos(), new BigDecimal("2.00"));
		assertEquals(listaTarjetas.get(1).getProducto(), "AMX");
		assertEquals(listaTarjetas.get(1).getSubProducto(), "BLACK");

		assertEquals(listaTarjetas.get(2).getAlias(), "Alias 3");
		assertEquals(listaTarjetas.get(2).getHasAlias(), true);
		assertEquals(listaTarjetas.get(2).getId(), new Integer(3));
		assertEquals(listaTarjetas.get(2).getMarca(), "Mastercard");
		assertEquals(listaTarjetas.get(2).getNumero(), "XXXX-5678");
		assertEquals(listaTarjetas.get(2).getSaldoPesos(), new BigDecimal("2.00"));
		assertEquals(listaTarjetas.get(2).getProducto(), "MC");
		assertEquals(listaTarjetas.get(2).getSubProducto(), "BLACK");

	}

	/**
	 * Obtener tarjetas test con favorita.
	 */
	@Test
	public void obtenerTarjetasTestConFavorita() {

		Cliente cliente = new Cliente();
		List<Cuenta> listCuentas = new ArrayList<Cuenta>();
		Cuenta cuenta1 = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		Cuenta cuenta3 = new Cuenta();

		cuenta1.setAlias("Alias 1");
		cuenta1.setIndex(1);
		cuenta1.setNroTarjetaCredito("0000000338501234");
		cuenta1.setSaldoCuenta("50");
		cuenta1.setTipoCuenta(TipoCuenta.VISA.getCodigo().toString());
		cuenta1.setTipoCuentaEnum(TipoCuenta.VISA);
		cuenta1.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta1.setSaldoParaConformar("50");
		cuenta1.setClaseCuenta("H");

		cuenta1.setAlias("Alias 2");
		cuenta2.setIndex(2);
		cuenta2.setNroTarjetaCredito("0000000338505678");
		cuenta2.setSaldoCuenta("200");
		cuenta2.setTipoCuenta(TipoCuenta.AMEX.getCodigo().toString());
		cuenta2.setTipoCuentaEnum(TipoCuenta.AMEX);
		cuenta2.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta2.setSaldoParaConformar("200");
		cuenta2.setIsFavorita(true);
		cuenta2.setClaseCuenta("H");

		cuenta3.setAlias("Alias 3");
		cuenta3.setIndex(3);
		cuenta3.setNroTarjetaCredito("0000000338501234");
		cuenta3.setSaldoCuenta("50");
		cuenta3.setTipoCuenta(TipoCuenta.MASTERCARD.getCodigo().toString());
		cuenta3.setTipoCuentaEnum(TipoCuenta.MASTERCARD);
		cuenta3.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta3.setSaldoParaConformar("50");
		cuenta3.setClaseCuenta("H");

		listCuentas.add(cuenta1);
		listCuentas.add(cuenta2);
		listCuentas.add(cuenta3);
		cliente.setCuentas(listCuentas);

		Respuesta<ListaTarjetasDTO> respuesta = tarjetasHomeBO.obtenerTarjetas(cliente);
		assertNotNull(respuesta);

		ListaTarjetasDTO tarjetasDTO = respuesta.getRespuesta();
		assertNotNull(tarjetasDTO);

		List<TarjetaHomeDTO> listaTarjetas = tarjetasDTO.getTarjetas();
		assertTrue(listaTarjetas.size() > 0);

		assertEquals(listaTarjetas.get(0).getAlias(), "");
		assertEquals(listaTarjetas.get(0).getHasAlias(), false);
		assertEquals(listaTarjetas.get(0).getId(), new Integer(2));
		assertEquals(listaTarjetas.get(0).getMarca(), "Amex");
		assertEquals(listaTarjetas.get(0).getNumero(), "XXXX-50567");
		assertEquals(listaTarjetas.get(0).getSaldoPesos(), new BigDecimal("2.00"));
		assertEquals(listaTarjetas.get(0).getProducto(), "AMX");
		assertEquals(listaTarjetas.get(0).getSubProducto(), "BLACK");

		assertEquals(listaTarjetas.get(1).getAlias(), "Alias 2");
		assertEquals(listaTarjetas.get(1).getHasAlias(), true);
		assertEquals(listaTarjetas.get(1).getId(), new Integer(1));
		assertEquals(listaTarjetas.get(1).getMarca(), "Visa");
		assertEquals(listaTarjetas.get(1).getNumero(), "XXXX-1234");
		assertEquals(listaTarjetas.get(1).getSaldoPesos(), new BigDecimal("0.50"));
		assertEquals(listaTarjetas.get(1).getProducto(), "VI");
		assertEquals(listaTarjetas.get(1).getSubProducto(), "BLACK");

		assertEquals(listaTarjetas.get(2).getAlias(), "Alias 3");
		assertEquals(listaTarjetas.get(2).getHasAlias(), true);
		assertEquals(listaTarjetas.get(2).getId(), new Integer(3));
		assertEquals(listaTarjetas.get(2).getMarca(), "Mastercard");
		assertEquals(listaTarjetas.get(2).getNumero(), "XXXX-1234");
		assertEquals(listaTarjetas.get(2).getSaldoPesos(), new BigDecimal("0.50"));
		assertEquals(listaTarjetas.get(2).getProducto(), "MC");
		assertEquals(listaTarjetas.get(2).getSubProducto(), "BLACK");
	}

	/**
	 * Obtener tarjetas test con adicionales con consumos unica titular.
	 */
	@Test
	public void obtenerTarjetasTestConAdicionalesConConsumosUnicaTitular() {

		Cliente cliente = new Cliente();
		List<Cuenta> listCuentas = new ArrayList<Cuenta>();
		Cuenta cuenta1 = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		Cuenta cuenta3 = new Cuenta();

		cuenta1.setAlias("Alias 1");
		cuenta1.setIndex(1);
		cuenta1.setNroTarjetaCredito("0000000338501234");
		cuenta1.setSaldoCuenta("50");
		cuenta1.setTipoCuenta(TipoCuenta.VISA.getCodigo().toString());
		cuenta1.setTipoCuentaEnum(TipoCuenta.VISA);
		cuenta1.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta1.setSaldoParaConformar("50");
		cuenta1.setIsFavorita(true);
		cuenta1.setClaseCuenta("H");

		cuenta2.setAlias("Alias 2");
		cuenta2.setIndex(2);
		cuenta2.setNroTarjetaCredito("0000000338501234");
		cuenta2.setSaldoCuenta("100");
		cuenta2.setTipoCuenta(TipoCuenta.AMEX.getCodigo().toString());
		cuenta2.setTipoCuentaEnum(TipoCuenta.AMEX);
		cuenta2.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta2.setSaldoParaConformar("100");
		cuenta2.setIsFavorita(true);
		cuenta2.setClaseCuenta("H");

		cuenta3.setAlias("Alias 3");
		cuenta3.setIndex(3);
		cuenta3.setNroTarjetaCredito("0000000338501234");
		cuenta3.setSaldoCuenta("100");
		cuenta3.setTipoCuenta(TipoCuenta.MASTERCARD.getCodigo().toString());
		cuenta3.setTipoCuentaEnum(TipoCuenta.MASTERCARD);
		cuenta3.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta3.setSaldoParaConformar("100");
		cuenta3.setIsFavorita(true);
		cuenta3.setClaseCuenta("H");

		listCuentas.add(cuenta1);
		listCuentas.add(cuenta2);
		listCuentas.add(cuenta3);
		cliente.setCuentas(listCuentas);

		Respuesta<ListaTarjetasDTO> respuesta = tarjetasHomeBO.obtenerTarjetas(cliente);
		assertNotNull(respuesta);

		ListaTarjetasDTO tarjetasDTO = respuesta.getRespuesta();
		assertNotNull(tarjetasDTO);

		List<TarjetaHomeDTO> listaTarjetas = tarjetasDTO.getTarjetas();
		assertTrue(listaTarjetas.size() > 0);

		assertEquals(listaTarjetas.get(0).getAlias(), "Alias 1");
		assertEquals(listaTarjetas.get(0).getHasAlias(), true);
		assertEquals(listaTarjetas.get(0).getId(), new Integer(1));
		assertEquals(listaTarjetas.get(0).getMarca(), "Visa");
		assertEquals(listaTarjetas.get(0).getNumero(), "XXXX-1234");
		assertEquals(listaTarjetas.get(0).getSaldoPesos(), new BigDecimal("0.50"));
		assertEquals(listaTarjetas.get(0).getProducto(), "VI");
		assertEquals(listaTarjetas.get(0).getSubProducto(), "BLACK");

		assertEquals(listaTarjetas.get(1).getAlias(), "Alias 2");
		assertEquals(listaTarjetas.get(1).getHasAlias(), true);
		assertEquals(listaTarjetas.get(1).getId(), new Integer(2));
		assertEquals(listaTarjetas.get(1).getMarca(), "Amex");
		assertEquals(listaTarjetas.get(1).getNumero(), "XXXX-50123");
		assertEquals(listaTarjetas.get(1).getSaldoPesos(), new BigDecimal("1.00"));
		assertEquals(listaTarjetas.get(1).getProducto(), "AMX");
		assertEquals(listaTarjetas.get(1).getSubProducto(), "BLACK");

		assertEquals(listaTarjetas.get(2).getAlias(), "Alias 3");
		assertEquals(listaTarjetas.get(2).getHasAlias(), true);
		assertEquals(listaTarjetas.get(2).getId(), new Integer(3));
		assertEquals(listaTarjetas.get(2).getMarca(), "Mastercard");
		assertEquals(listaTarjetas.get(2).getNumero(), "XXXX-1234");
		assertEquals(listaTarjetas.get(2).getSaldoPesos(), new BigDecimal("1.00"));
		assertEquals(listaTarjetas.get(2).getProducto(), "MC");
		assertEquals(listaTarjetas.get(2).getSubProducto(), "BLACK");

	}

	/**
	 * Obtener tarjetas test con adicionales sin consumos unica titular.
	 */
	@Test
	public void obtenerTarjetasTestConAdicionalesSinConsumosUnicaTitular() {

		Cliente cliente = new Cliente();
		List<Cuenta> listCuentas = new ArrayList<Cuenta>();
		Cuenta cuenta1 = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		Cuenta cuenta3 = new Cuenta();
		Cuenta cuenta4 = new Cuenta();
		Cuenta cuenta5 = new Cuenta();
		Cuenta cuenta6 = new Cuenta();

		cuenta1.setAlias("Alias 1");
		cuenta1.setIndex(1);
		cuenta1.setNroTarjetaCredito("0000000338501234");
		cuenta1.setSaldoCuenta("50");
		cuenta1.setTipoCuenta(TipoCuenta.VISA.getCodigo().toString());
		cuenta1.setTipoCuentaEnum(TipoCuenta.VISA);
		cuenta1.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta1.setSaldoParaConformar("50");
		cuenta1.setIsFavorita(true);
		cuenta1.setClaseCuenta("H");

		cuenta2.setAlias("Alias 2");
		cuenta2.setIndex(2);
		cuenta2.setNroTarjetaCredito("0000000338501234");
		cuenta2.setSaldoCuenta("50");
		cuenta2.setTipoCuenta(TipoCuenta.VISA.getCodigo().toString());
		cuenta2.setTipoCuentaEnum(TipoCuenta.VISA);
		cuenta2.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL);
		cuenta2.setSaldoParaConformar("0");
		cuenta2.setClaseCuenta("H");

		cuenta3.setAlias("Alias 3");
		cuenta3.setIndex(3);
		cuenta3.setNroTarjetaCredito("0000000338505678");
		cuenta3.setSaldoCuenta("200");
		cuenta3.setTipoCuenta(TipoCuenta.AMEX.getCodigo().toString());
		cuenta3.setTipoCuentaEnum(TipoCuenta.AMEX);
		cuenta3.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta3.setSaldoParaConformar("0");
		cuenta3.setClaseCuenta("H");
		
		cuenta4.setAlias("Alias 4");
		cuenta4.setIndex(4);
		cuenta4.setNroTarjetaCredito("0000000338505678");
		cuenta4.setSaldoCuenta("200");
		cuenta4.setTipoCuenta(TipoCuenta.AMEX.getCodigo().toString());
		cuenta4.setTipoCuentaEnum(TipoCuenta.AMEX);
		cuenta4.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL);
		cuenta4.setSaldoParaConformar("0");
		cuenta4.setClaseCuenta("H");

		cuenta5.setAlias("Alias 5");
		cuenta5.setIndex(5);
		cuenta5.setNroTarjetaCredito("0000000338505678");
		cuenta5.setSaldoCuenta("200");
		cuenta5.setTipoCuenta(TipoCuenta.MASTERCARD.getCodigo().toString());
		cuenta5.setTipoCuentaEnum(TipoCuenta.MASTERCARD);
		cuenta5.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR);
		cuenta5.setSaldoParaConformar("0");
		cuenta5.setClaseCuenta("H");

		cuenta6.setAlias("Alias 6");
		cuenta6.setIndex(6);
		cuenta6.setNroTarjetaCredito("0000000338505678");
		cuenta6.setSaldoCuenta("200");
		cuenta6.setTipoCuenta(TipoCuenta.MASTERCARD.getCodigo().toString());
		cuenta6.setTipoCuentaEnum(TipoCuenta.MASTERCARD);
		cuenta6.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL);
		cuenta6.setSaldoParaConformar("0");
		cuenta6.setClaseCuenta("H");

		listCuentas.add(cuenta1);
		listCuentas.add(cuenta2);
		listCuentas.add(cuenta3);
		listCuentas.add(cuenta4);
		listCuentas.add(cuenta5);
		listCuentas.add(cuenta6);
		cliente.setCuentas(listCuentas);

		Respuesta<ListaTarjetasDTO> respuesta = tarjetasHomeBO.obtenerTarjetas(cliente);
		assertNotNull(respuesta);

		ListaTarjetasDTO tarjetasDTO = respuesta.getRespuesta();
		assertNotNull(tarjetasDTO);

		List<TarjetaHomeDTO> listaTarjetas = tarjetasDTO.getTarjetas();
		assertTrue(listaTarjetas.size() > 0);

		assertEquals(listaTarjetas.get(0).getAlias(), "Alias 1");
		assertEquals(listaTarjetas.get(0).getHasAlias(), true);
		assertEquals(listaTarjetas.get(0).getId(), new Integer(1));
		assertEquals(listaTarjetas.get(0).getMarca(), "Visa");
		assertEquals(listaTarjetas.get(0).getNumero(), "XXXX-1234");
		assertEquals(listaTarjetas.get(0).getSaldoPesos(), new BigDecimal("0.50"));
		assertEquals(listaTarjetas.get(0).getProducto(), "VI");
		assertEquals(listaTarjetas.get(0).getSubProducto(), "BLACK");

		assertEquals(listaTarjetas.get(1).getAlias(), "Alias 3");
		assertEquals(listaTarjetas.get(1).getHasAlias(), true);
		assertEquals(listaTarjetas.get(1).getId(), new Integer(3));
		assertEquals(listaTarjetas.get(1).getMarca(), "Amex");
		assertEquals(listaTarjetas.get(1).getNumero(), "XXXX-50567");
		assertEquals(listaTarjetas.get(1).getSaldoPesos(), new BigDecimal("0.00"));
		assertEquals(listaTarjetas.get(1).getProducto(), "AMX");
		assertEquals(listaTarjetas.get(1).getSubProducto(), "BLACK");

		assertEquals(listaTarjetas.get(2).getAlias(), "Alias 5");
		assertEquals(listaTarjetas.get(2).getHasAlias(), true);
		assertEquals(listaTarjetas.get(2).getId(), new Integer(5));
		assertEquals(listaTarjetas.get(2).getMarca(), "Mastercard");
		assertEquals(listaTarjetas.get(2).getNumero(), "XXXX-5678");
		assertEquals(listaTarjetas.get(2).getSaldoPesos(), new BigDecimal("0.00"));
		assertEquals(listaTarjetas.get(2).getProducto(), "MC");
		assertEquals(listaTarjetas.get(2).getSubProducto(), "BLACK");
	}

	/**
	 * Obtener tarjetas test con adicionales sin titular. Se cambió el criterio
	 * para armar las cajas, no se deben mostrar tarjetas adicionales. Modifiqué
	 * el assert para verificar que no se incluyeron adicionales.
	 */
	@Test
	public void obtenerTarjetasTestConAdicionalesSinTitular() {

		Cliente cliente = new Cliente();
		List<Cuenta> listCuentas = new ArrayList<Cuenta>();

		Cuenta cuenta1 = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		Cuenta cuenta3 = new Cuenta();

		cuenta1.setAlias("Alias 1");
		cuenta1.setIndex(1);
		cuenta1.setNroTarjetaCredito("0000000338501234");
		cuenta1.setSaldoCuenta("50");
		cuenta1.setTipoCuenta(TipoCuenta.VISA.getCodigo().toString());
		cuenta1.setTipoCuentaEnum(TipoCuenta.VISA);
		cuenta1.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL);
		cuenta1.setSaldoParaConformar("0");
		cuenta1.setClaseCuenta("H");

		cuenta2.setAlias("Alias 2");
		cuenta2.setIndex(2);
		cuenta2.setNroTarjetaCredito("0000000338505678");
		cuenta2.setSaldoCuenta("200");
		cuenta2.setTipoCuenta(TipoCuenta.AMEX.getCodigo().toString());
		cuenta2.setTipoCuentaEnum(TipoCuenta.AMEX);
		cuenta2.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL);
		cuenta2.setSaldoParaConformar("0");
		cuenta2.setClaseCuenta("H");
		
		cuenta3.setAlias("Alias 3");
		cuenta3.setIndex(3);
		cuenta3.setNroTarjetaCredito("0000000338505678");
		cuenta3.setSaldoCuenta("200");
		cuenta3.setTipoCuenta(TipoCuenta.MASTERCARD.getCodigo().toString());
		cuenta3.setTipoCuentaEnum(TipoCuenta.MASTERCARD);
		cuenta3.setCodigoTitularidad(TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL);
		cuenta3.setSaldoParaConformar("0");
		cuenta3.setClaseCuenta("H");

		listCuentas.add(cuenta1);
		listCuentas.add(cuenta2);
		listCuentas.add(cuenta3);
		cliente.setCuentas(listCuentas);

		Respuesta<ListaTarjetasDTO> respuesta = tarjetasHomeBO.obtenerTarjetas(cliente);
		assertNotNull(respuesta);

		ListaTarjetasDTO tarjetasDTO = respuesta.getRespuesta();
		assertNotNull(tarjetasDTO);

		List<TarjetaHomeDTO> listaTarjetas = tarjetasDTO.getTarjetas();
		assertEquals(listaTarjetas.size(), 3);

	}

}
