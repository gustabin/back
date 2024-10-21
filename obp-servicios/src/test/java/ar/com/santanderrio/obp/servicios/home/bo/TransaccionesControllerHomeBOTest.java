package ar.com.santanderrio.obp.servicios.home.bo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.home.bo.impl.TransaccionesControllerHomeBOImpl;
import ar.com.santanderrio.obp.servicios.pagohaberes.dao.PagoHaberesDAO;

/**
 * The Class TransaccionesControllerHomeBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransaccionesControllerHomeBOTest {

	/** The transacciones controller home BO. */
	@InjectMocks
	private TransaccionesControllerHomeBO transaccionesControllerHomeBO = new TransaccionesControllerHomeBOImpl();

	/** The cuenta BO. */
	@Mock
	private CuentaBO cuentaBO;

	@Mock
	private PagoHaberesDAO pagoHaberesDAO;

	/**
	 * Menu consultas test.
	 *
	 * @throws IllegalAccessException the illegal access exception
	 * @throws DAOException
	 */
	@Test
	public void menuConsultasTest() throws IllegalAccessException, DAOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		List<String> codigosBP = new ArrayList<String>();

		codigosBP.add("0003");
		codigosBP.add("0351");
		codigosBP.add("0355");
		codigosBP.add("0503");
		cuenta2.setCodigoPaquete("0003");
		cuenta2.setTipoCuenta("9");
		cuenta2.setCodigoTitularidad("TI");
		cuenta2.setTipoCuenta("02");
		cuenta.setTipoCuenta("02");
		cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
		cuentas.add(cuenta);
		cuentas.add(cuenta2);
		cliente.setCuentas(cuentas);

		FieldUtils.writeField(transaccionesControllerHomeBO, "codigosBP", codigosBP, true);
		when(cuentaBO.hasCuentasMonetariasActivas(Matchers.any(Cliente.class))).thenReturn(true);

		assertFalse(transaccionesControllerHomeBO.aplicaBilleteraVirtual(cliente));
		assertTrue(transaccionesControllerHomeBO.aplicaCalendarioPagos(cliente));
		assertTrue(transaccionesControllerHomeBO.aplicaCesionCheques(cliente));
		assertTrue(transaccionesControllerHomeBO.aplicaEnvioEfectivo(cliente));
		assertTrue(transaccionesControllerHomeBO.aplicaPagoTarjeta(cliente));
		assertTrue(transaccionesControllerHomeBO.aplicaTransferencias(cliente));

	}

}
