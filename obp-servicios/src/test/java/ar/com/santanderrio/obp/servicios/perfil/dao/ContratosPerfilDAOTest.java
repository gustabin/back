package ar.com.santanderrio.obp.servicios.perfil.dao;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.perfil.dao.impl.ContratosPerfilDAOImpl;
import ar.com.santanderrio.obp.servicios.perfil.entities.ContratosPerfil;

public class ContratosPerfilDAOTest {

	private ContratosPerfilDAO contratosPerfilDAO = new ContratosPerfilDAOImpl();

	@Before
	public void init() throws IllegalAccessException {
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "tituloMya", "Términos y condiciones de Mensajes y Avisos",
				true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "urlMya",
				"https://www.santanderrio.com.ar/RenderPDF/Render?name=Terminos-y-condiciones-de-Mensajes-y-Avisos.pdf&library=CONTENIDO-SR",
				true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "tituloSorpresa", "Contrato de Mensajes y Avisos Sorpresa",
				true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "urlSorpresa",
				"https://www.santanderrio.com.ar/RenderPDF/Render?name=Terminos-y-condiciones-de-Sorpresa-Santander-Rio.pdf&library=CONTENIDO-SR",
				true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "tituloOnlineBanking", "Contrato de Online Banking", true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "urlOnlineBanking",
				"https://www.santanderrio.com.ar/RenderPDF/Render?name=Terminos-y-condiciones-de-uso-de-Online-Banking-Personas.pdf&library=CONTENIDO-SR",
				true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "tituloSuperclub", "Términos y Condiciones SuperClub", true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "urlSuperclub",
				"https://www.santanderrio.com.ar/RenderPDF/Render?name=Terminos-y-condiciones-de-Superclub.pdf&library=CONTENIDO-SR",
				true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "tituloTycTarjetaRecargable",
				"Términos y Condiciones de Tarjeta Recargable", true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "urlTycTarjetaRecargable",
				"https://www.santanderrio.com.ar/RenderPDF/Render?name=Terminos-y-condiciones-de-uso-de-Tarjeta-Recargable.pdf&library=CONTENIDO-SR",
				true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "tituloComisionesTarjetaRecargable",
				"Comisiones de Tarjeta Recargable", true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "urlComisionesTarjetaRecargable",
				"https://www.santanderrio.com.ar/RenderPDF/Render?name=Comisiones-de-Tarjeta-Recargable.pdf&library=CONTENIDO-SR",
				true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "tituloComparacionDeComisiones", "Comparación de comisiones",
				true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "urlComparacionDeComisiones",
				"http://www.bcra.gov.ar/BCRAyVos/Comparacion_de_Comisiones.asp", true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "tituloRegimenTransparenciaBcra",
				"Régimen de transparencia del BCRA", true);
		FieldUtils.writeDeclaredField(contratosPerfilDAO, "urlRegimenTransparenciaBcra",
				"http://www.bcra.gov.ar/BCRAyVos/Regimen_de_transparencia.asp", true);
	}

	@Test
	public void obtenerContratos() {
		ContratosPerfil contratos = contratosPerfilDAO.consultarContratos();
		Assert.assertNotNull(contratos);
		Assert.assertEquals(5, contratos.getContratosServicios().size());
        Assert.assertEquals(6, contratos.getContratosProductos().size());
		Assert.assertEquals(2, contratos.getContratosComunicacionesBcra().size());
	}

}
