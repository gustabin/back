package ar.com.santanderrio.obp.servicios.delete.account.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import ar.com.santanderrio.obp.servicios.delete.account.model.Error;
import ar.com.santanderrio.obp.servicios.delete.account.web.enums.ErrorCodeEnum;
import org.apache.commons.collections.Predicate;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;

public class AccountUtils {
	
	public static final Integer ERROR_SALDO =20010095;
	
	private AccountUtils() {}
	
	public static final Predicate CuentaUnicaPredicate = new Predicate() {
		@Override
		public boolean evaluate(Object object) {
			TipoCuenta tipoCuenta = ((Cuenta) object).getTipoCuentaEnum();
			return TipoCuenta.CUENTA_UNICA.equals(tipoCuenta) 
				|| TipoCuenta.CUENTA_UNICA_DOLARES.equals(tipoCuenta)
				|| TipoCuenta.CUENTA_UNICA_PESOS.equals(tipoCuenta);
		}
	};
	
	public static final Predicate CajaAhorroPredicate = new Predicate() {
		@Override
		public boolean evaluate(Object object) {
			TipoCuenta tipoCuenta = ((Cuenta) object).getTipoCuentaEnum();
			return TipoCuenta.CAJA_AHORRO_DOLARES.equals(tipoCuenta) 
				|| TipoCuenta.CAJA_AHORRO_PESOS.equals(tipoCuenta);
		}
	};

	public static final Predicate CuentaCorrientePredicate = new Predicate() {
		@Override
		public boolean evaluate(Object object) {
			TipoCuenta tipoCuenta = ((Cuenta) object).getTipoCuentaEnum();
			return TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(tipoCuenta) 
				|| TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(tipoCuenta);
		}
	};

	public static final Predicate CuentaPrivadaPredicate = new Predicate() {
		@Override
		public boolean evaluate(Object object) {
			return CuentasBancaPrivadaUtil.isCuentaBP(((Cuenta) object).getNroSucursal());
		}
	};
	
	public static final Predicate CuentasDolaresPredicate = new Predicate() {
		@Override
		public boolean evaluate(Object object) {
			TipoCuenta tipoCuenta = ((Cuenta) object).getTipoCuentaEnum();
			return TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(tipoCuenta) 
				|| TipoCuenta.CAJA_AHORRO_DOLARES.equals(tipoCuenta)
				|| TipoCuenta.CUENTA_UNICA.equals(tipoCuenta) 
				|| TipoCuenta.CUENTA_UNICA_DOLARES.equals(tipoCuenta);
		}
	};
	
	public static Set<Cuenta> getCuentasByPredicate(Cliente cliente, Predicate predicate) {
		Set<Cuenta> cuentas = new LinkedHashSet<Cuenta>();
		cuentas.addAll(cliente.getCuentas());
		cuentas.addAll(cliente.getCuentasMonetarias());
		cuentas.addAll(cliente.getCuentasRetail());
		cuentas.addAll(cliente.getCuentasPrivadas());
		
		Set<Cuenta> filteredCuentas = new HashSet<Cuenta>();
		
		for(Cuenta cuenta : cuentas) {
			if(predicate.evaluate(cuenta)) {
				filteredCuentas.add(cuenta);
			}
		}
		return filteredCuentas;
	}

	public static Predicate getPredicateNumeroCuentaFormateado(final String numeroCuenta) {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				return numeroCuenta.equalsIgnoreCase(((Cuenta)object).obtenerNroCuentaFormateado());
			}
		};
	}

	public static boolean containsErrorType(final List<Error> errors, final List<ErrorCodeEnum> errorTypes) {
		for(Error error : errors) {
			for(ErrorCodeEnum errorType : errorTypes) {
				if (errorType.equals(error.getType())) {
					return true;
				}
			}
		}
		return false;
	}

	public static Cuenta getCuenta(Cliente cliente, Predicate cuentaPredicate) {
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.addAll(cliente.getCuentas());
		cuentas.addAll(cliente.getCuentasRetail());
		cuentas.addAll(cliente.getCuentasPrivadas());

		for (Cuenta cta : cuentas) {
			if (cuentaPredicate.evaluate(cta)) {
				return cta;
			}
		}
		return null;
	}
	
	
}
