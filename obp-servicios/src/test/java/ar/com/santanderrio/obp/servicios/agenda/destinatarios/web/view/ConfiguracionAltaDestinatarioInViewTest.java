/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConfiguracionAltaDestinatarioInViewMock;

/**
 * The Class ConfiguracionAltaDestinatarioInViewTest.
 *
 * @author florencia.n.martinez
 */
public class ConfiguracionAltaDestinatarioInViewTest {

	/**
	 * Gets the configuracion alta destinatario in view alias valido.
	 *
	 * @return the configuracion alta destinatario in view alias valido
	 */
	@Test
	public void getConfiguracionAltaDestinatarioInViewAliasValido() {
		ConfiguracionAltaDestinatarioInView inView = ConfiguracionAltaDestinatarioInViewMock
				.completarInfoParaAliasValido();
		Boolean r = validate(inView);
		Assert.assertTrue(r);
		Assert.assertTrue(inView.getAlias().matches("^[0-9|a-z|A-Z|\\.|\\-]{6,20}$"));
	}

	/**
	 * Gets the configuracion alta destinatario in view alias invalido.
	 *
	 * @return the configuracion alta destinatario in view alias invalido
	 */
	@Test
	public void getConfiguracionAltaDestinatarioInViewAliasInvalido() {
		ConfiguracionAltaDestinatarioInView inView = ConfiguracionAltaDestinatarioInViewMock
				.completarInfoParaAliasInvalido();
		Boolean r = validate(inView);
		Assert.assertFalse(r);
	}

	/**
	 * Validate.
	 *
	 * @param bean
	 *            the bean
	 * @return the boolean
	 */
	private Boolean validate(ConfiguracionAltaDestinatarioInView bean) {
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<ConfiguracionAltaDestinatarioInView>> errores = validator.validate(bean);
			for (ConstraintViolation<ConfiguracionAltaDestinatarioInView> cv : errores) {
				System.out.println(cv.getMessage());
			}
			return CollectionUtils.isEmpty(errores);
		} catch (Exception e) {
			return false;
		}
	}
}
