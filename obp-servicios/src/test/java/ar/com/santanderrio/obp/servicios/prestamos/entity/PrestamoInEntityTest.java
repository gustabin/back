/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.servicios.prestamos.mock.PrestamoInEntityMock;

/**
 * The Class PrestamoInEntityTest.
 *
 * @author florencia.n.martinez
 */
public class PrestamoInEntityTest {

	/**
	 * Gets the prestamo in entity.
	 *
	 * @return the prestamo in entity
	 */
	@Test
	public void getPrestamoInEntityTest() {
		PrestamoInEntity prestamoInEntity = PrestamoInEntityMock.obtenerPrestamoInEntityOKParaSimulador();
		Boolean r = validate(prestamoInEntity);
		Assert.assertTrue(r);
		Assert.assertTrue(prestamoInEntity.getFase().matches("S|L"));
		Assert.assertTrue(prestamoInEntity.getTipoCuenta().matches("[0-9]{2}"));
		Assert.assertTrue(prestamoInEntity.getSucursalCuenta().matches("[0-9]{3}"));
		Assert.assertTrue(prestamoInEntity.getNumeroCuenta().matches("[0-9]{7}"));
		Assert.assertTrue(prestamoInEntity.getImportePrestamo().matches("[0-9]{14}"));
		Assert.assertTrue(prestamoInEntity.getCantidadCuotas().matches("[0-9]{3}"));
		Assert.assertTrue(prestamoInEntity.getFechaPrimerCuota().matches("[0-9]{8}"));
		Assert.assertTrue(prestamoInEntity.getTipoTasa().matches("[0-9A-Za-z]{1}"));
		Assert.assertTrue(prestamoInEntity.getCodProductoUG().matches("[0-9A-Za-z]{2}"));
		Assert.assertTrue(prestamoInEntity.getCodSubpUG().matches("[0-9A-Za-z]{4}"));
		Assert.assertTrue(prestamoInEntity.getDestFondosUG().matches("[0-9A-Za-z]{5}"));
		Assert.assertTrue(prestamoInEntity.getCodDivisaO().matches("[0-9A-Za-z]{3}"));
		Assert.assertTrue(prestamoInEntity.getValorTasa().matches("[0-9]{9}"));
		Assert.assertTrue(prestamoInEntity.getClausulaRevUG().matches("[0-9A-Za-z]{4}"));
		Assert.assertTrue(prestamoInEntity.getSucPaquete().matches("[0-9A-Za-z]{4}"));
		Assert.assertTrue(prestamoInEntity.getNumPaquete().matches("[0-9]{12}"));
		Assert.assertTrue(prestamoInEntity.getTpoPolizaDs().matches("[0-9A-Za-z]{3}"));
		Assert.assertTrue(prestamoInEntity.getTpoRiesgoDs().matches("[0-9A-Za-z]{3}"));
		Assert.assertTrue(prestamoInEntity.getCodCondici().matches("EO|  "));
		Assert.assertTrue(prestamoInEntity.getNIO().matches("[ ]{24}"));
		Assert.assertTrue(prestamoInEntity.getDestFondosComerO().matches("[0-9A-Za-z]{5}"));
		Assert.assertTrue(prestamoInEntity.getSucPrest().matches("0000"));
		Assert.assertTrue(prestamoInEntity.getNumPrest().matches("0{12}"));
		Assert.assertTrue(prestamoInEntity.getEntCuentaProve().matches("[0-9A-Za-z]{4}"));
		Assert.assertTrue(prestamoInEntity.getSucCuentaProve().matches("[0-9A-Za-z]{4}"));
		Assert.assertTrue(prestamoInEntity.getNroCuentaProve().matches("[0-9]{16}"));
		Assert.assertTrue(prestamoInEntity.getDivisaCtaProve().matches("[0-9A-Za-z]{3}"));
		Assert.assertTrue(prestamoInEntity.getImporteAbono().matches("0{17}"));
	}

	/**
	 * Validate.
	 *
	 * @param bean
	 *            the bean
	 * @return the boolean
	 */
	private Boolean validate(PrestamoInEntity bean) {
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<PrestamoInEntity>> errores = validator.validate(bean);
			for (ConstraintViolation<PrestamoInEntity> cv : errores) {
				System.out.println(
						"Error en el llamado a iatx, en el atributo: " + cv.getPropertyPath() + ", " + cv.getMessage());
			}
			return CollectionUtils.isEmpty(errores);
		} catch (Exception e) {
			return false;
		}
	}

}
