/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.AccesosInversiones;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.Menu;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RequestEnriFlagView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.ResponseEnriFlagView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dao.MapServiceDAOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaFeriadosRequestEntity;

/**
 * The Class InversionWSHelper.
 */
@Component("InversionWSHelper")
public class InversionWSHelper {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(InversionWSHelper.class);

	/** The sign. */
	@Autowired
	private Sign sign;

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/** The Constant JKS. */
	private static final String JKS = "MYA";

	@Value("${INVERSIONES.FIRMAR:true}")
	private String firmar;

	@Value("${INVERSIONES.FIRMA.DATO:Prueba}")
	private String dato;

	@Value("${FONDOS.CANALTIPO}")
	private String canalTipo;

	/** The canal id. */
	@Value("${FONDOS.CANALID}")
	private String canalId;
	
	private static final String NOMBRE_WS = "ORDENES.TENENCIAS";
	
	private static String pathEnri = "ConsultaHabilitadoAperturaEnri";
	
	private static final String CEDEAR_DESC = "CEDEARs de ETFs";
	
	private static final String CEDEAR_LINK = "goToMFETF(RTL)";
	
	private static final String MEP_DESC = "Venta Dólar MEP";
	
	private static final String MEP_LINK = "goToMFMEP(RTL)";
	
	private static final String MEP_BP_DESC = "Venta Dólar MEP Banca Privada";
	
	private static final String MEP_BP_LINK = "goToMFMEP(BP)";

	@Autowired
	private RestWebClient restWebClient;

	@Autowired
	private MapServiceDAOImpl mapServiceDAOImpl;

	/**
	 * Gets the datos firmados.
	 *
	 * @param datosSinFirmar
	 *                       the datos sin firmar
	 * @return the datos firmados
	 * @throws DAOException
	 *                      the DAO exception
	 */
	public String getDatosFirmados(String datosSinFirmar) throws DAOException {
		LOGGER.info("Generando Firma");
		byte[] firma;
		try {
			firma = sign.buildB64Signature(datosSinFirmar.getBytes(appEncoding), JKS, true);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error generando firma", e);
			throw new DAOException(e);
		}
		return new String(firma);
	}

	public boolean enriFlag() {
		WebClient service;
		RequestEnriFlagView request = new RequestEnriFlagView();

		boolean enriFlag = false;
		try {
			request.setDato(dato);
			request.setFirma(getDatosFirmados(dato));
			request.setCanal(canalTipo);
			request.setSubCanal(canalId);

			service = crearLlamadaAWebService(pathEnri);
			ResponseEnriFlagView respuesta = service.post(request, ResponseEnriFlagView.class);
			if (respuesta.getCodigo() == 0) {
				enriFlag = respuesta.getDatos().isHabilitado();
			}

		} catch (Exception e) {
			enriFlag = false;
			LOGGER.error("Error en servicio ENRI FLAG Middleware", e);
		}
		return enriFlag;
	}

	private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

		WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
		service.accept(MediaType.APPLICATION_JSON);
		service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		service.path(pathDeConsulta);
		return service;
	}

	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_DIAS_NO_HABILES }, key = "T(ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils).formatearFecha(#fecha)")
	public boolean esFeriado(Date fecha) {
		LOGGER.info("CACHE_DIAS_NO_HABILES: Fecha actual " + ISBANStringUtils.formatearFecha(fecha));
		
		boolean esFeriado = false;
		ConsultaFeriadosRequestEntity request = mapServiceDAOImpl.crearRequestFeri();
		List<String> listaDeFeriados = new ArrayList<String>();
		try {
			listaDeFeriados = mapServiceDAOImpl.consultaFeriados(request);
			String fechaStr = ISBANStringUtils.formatearFecha(fecha);

			for (String feriado : listaDeFeriados) {
				if (feriado.equals(fechaStr)) {
					esFeriado = true;
				}
			}
			LOGGER.info("CACHE_DIAS_NO_HABILES: Cache generado");
			this.cleanCacheDiasNoHabiles(false);

		} catch (DAOException e) {
			LOGGER.error("CACHE_DIAS_NO_HABILES: Fallo la consulta al listado de feriados, no se genera cache");
			this.cleanCacheDiasNoHabiles(true);
		}
		return esFeriado;
	}

	public List<Menu> getItemDinamico(Cliente clienteAccesos) throws DAOException {

		List<Menu> Lista = new ArrayList<Menu>();
		
		AccesosInversiones accesosInversiones = new AccesosInversiones();
		clienteAccesos.setAccesosInversiones(accesosInversiones);
		
		Menu cdr = new Menu();
		cdr.setTitulo(CEDEAR_DESC);
		cdr.setLink(CEDEAR_LINK);
		Lista.add(cdr);
		
		Menu mepRtl = new Menu();
		mepRtl.setTitulo(MEP_DESC);
		mepRtl.setLink(MEP_LINK);
		Lista.add(mepRtl);
		
		if (clienteAccesos.getClienteBancaPrivada()) {
		 	Menu mepBp = new Menu();
		 	mepBp.setTitulo(MEP_BP_DESC);
		 	mepBp.setLink(MEP_BP_LINK);
			Lista.add(mepBp);
		}
		
		clienteAccesos.getAccesosInversiones().setAccesos(Lista);
		return Lista;
	}

	@CacheEvict(value = CacheConstants.Names.CACHE_DIAS_NO_HABILES, condition = "#clean")
	private void cleanCacheDiasNoHabiles(boolean clean) {
		if (clean)
			LOGGER.info("Limpiando cache {}.", CacheConstants.CACHE_DIAS_NO_HABILES);
	}

}
