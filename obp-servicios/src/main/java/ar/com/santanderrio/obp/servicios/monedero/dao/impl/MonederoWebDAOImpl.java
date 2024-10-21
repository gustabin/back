/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dao.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.monedero.SingleServiceSoap;
import ar.com.santanderrio.obp.servicios.monedero.dao.MonederoWebDAO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsTransacDTO;
import ar.com.santanderrio.obp.servicios.monedero.entities.ObtenerTrxTagMedioDeRecargaEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.RespuestaTag;
import ar.com.santanderrio.obp.servicios.monedero.entities.RespuestaTag.Datos.Dato.ObtenerTagsDataResponse;
import ar.com.santanderrio.obp.servicios.monedero.entities.RespuestaTag.Datos.Dato.ObtenerTagsDataResponse.MedioDeRecarga;
import ar.com.santanderrio.obp.servicios.monedero.entities.RespuestaTransaccionesTag;
import ar.com.santanderrio.obp.servicios.monedero.entities.RespuestaTransaccionesTag.Datos;
import ar.com.santanderrio.obp.servicios.monedero.entities.RespuestaTransaccionesTag.Datos.Dato.ObtenerTransaccionesTagDataResponse;
import ar.com.santanderrio.obp.servicios.monedero.entities.TagEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TransaccionEntity;

/**
 * The Class MonederoWebDAOImpl.
 */
@Component
public class MonederoWebDAOImpl implements MonederoWebDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MonederoWebDAOImpl.class);

	/** The gestor plan V. */
	@Autowired
	@Qualifier("monederoWeb")
	private GestionarWSAbstract<SingleServiceSoap> gestorMonederoWeb;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.monedero.dao.MonederoWebDAO#obtenerTags
	 * (ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.
	 * TagsDTO)
	 */
	@Override
	public List<TagEntity> obtenerTags(TagsDTO dto) throws DAOException {

		RespuestaTag respuestas = new RespuestaTag();
		SingleServiceSoap singleServiceSoap = null;
		List<TagEntity> responseObj = null;

		try {

			singleServiceSoap = gestorMonederoWeb.obtenerPort();

			String obtenerTagsResponse = singleServiceSoap.obtenerTags(dto.getIdBanco(), dto.getPassword(),
					dto.getTipoNumDoc(), dto.getIdCuentaVirtual(), dto.getCategoria() /* 2 */, dto.getIdUsuario(),
					dto.getIdTag(), dto.getPagCantReg(), dto.getPagNum());

			respuestas = unmarsall(obtenerTagsResponse, respuestas);
			responseObj = obtenerDatosServicioTag(respuestas);

		} catch (javax.xml.ws.soap.SOAPFaultException sfe) {
			LOGGER.error("Error al consumir el ws, {}, con codigo {}", sfe.getMessage(), sfe.getFault().getFaultCode(),
					sfe);
			throw new DAOException(sfe, sfe.getMessage(), sfe.getFault().getFaultCode());
		} finally {
			gestorMonederoWeb.liberarPort(singleServiceSoap);
		}

		return responseObj;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.dao.MonederoWebDAO#
	 * obtenerTransaccionesTag(ar.com.santanderrio.obp.servicios.transferencias.
	 * entities.agenda.TagsTransacDTO)
	 */
	@Override
	public List<TransaccionEntity> obtenerTransaccionesTag(TagsTransacDTO dto) throws DAOException {

		RespuestaTransaccionesTag respuestas = new RespuestaTransaccionesTag();
		SingleServiceSoap singleServiceSoap = null;
		List<TransaccionEntity> responseObj = new ArrayList<TransaccionEntity>();

		try {

			singleServiceSoap = gestorMonederoWeb.obtenerPort();

			String obtenerTransaccionesTagResponse = singleServiceSoap.obtenerTransaccionesTag(dto.getIdBanco(),
					dto.getPassword(), dto.getTipoNumDoc(), dto.getIdTag(), dto.getFecDesde(), dto.getFecHasta(),
					dto.getPagCantReg(), dto.getPagNum());

			respuestas = unmarsall(obtenerTransaccionesTagResponse, respuestas);
			responseObj = obtenerTransaccionesTagsResponse(respuestas);

		} catch (javax.xml.ws.soap.SOAPFaultException sfe) {
			LOGGER.error("Error al consumir el ws, {}, con codigo {}", sfe.getMessage(), sfe.getFault().getFaultCode(),
					sfe);
			throw new DAOException(sfe, sfe.getMessage(), sfe.getFault().getFaultCode());
		} finally {
			gestorMonederoWeb.liberarPort(singleServiceSoap);
		}

		return responseObj;
	}

	/**
	 * Unmarsall.
	 *
	 * @param <T>
	 *            the generic type
	 * @param xml
	 *            the xml
	 * @param t
	 *            the t
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	private <T> T unmarsall(String xml, T t) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xml);
			return (T) unmarshaller.unmarshal(reader);

		} catch (JAXBException e) {
			LOGGER.error("Error de lectura en la respuesta", e);
		}

		return null;
	}

	/**
	 * Obtener datos servicio tag.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the list
	 */
	private List<TagEntity> obtenerDatosServicioTag(RespuestaTag respuesta) {

		List<TagEntity> objList = new ArrayList<TagEntity>();

		if (respuesta == null || respuesta.getDatos() == null || respuesta.getDatos().getDato() == null) {
			return objList;

		} else {

			for (RespuestaTag.Datos.Dato dato : respuesta.getDatos().getDato()) {

				ObtenerTagsDataResponse response = dato.getObtenerTagsDataResponse();

				TagEntity obj = new TagEntity();
				obj.setIdTag(convertirString(response.getIDTag()));
				obj.setTag(convertirString(response.getTag()));
				obj.setIdBanco(convertirString(response.getIDBanco()));
				obj.setApellido(response.getApellido());
				obj.setNombre(response.getNombre());
				obj.setClteLimiteMensualRecarga(convertirString(response.getClteLimiteMensualRecarga()));
				obj.setClteModuloRecarga(convertirString(response.getClteModulorecarga()));
				obj.setClteCodTipoFrecuencia(response.getClteCodTipoFrecuencia());
				obj.setBcoLimiteRecargaMensualTAG(convertirString(response.getBcoLimiteRecargaMensualTAG()));
				obj.setClteCantFrecuencia(convertirString(response.getClteCantFrecuencia()));
				obj.setSaldo(convertirString(response.getSaldo()));
				obj.setFechaSaldo(response.getFechaSaldo());
				obj.setLimiteDisponible(convertirString(response.getLimiteDisponible()));

				MedioDeRecarga recarga = response.getMedioDeRecarga();
				if (recarga != null) {
					ObtenerTrxTagMedioDeRecargaEntity medioDeRecargaObj = new ObtenerTrxTagMedioDeRecargaEntity();
					medioDeRecargaObj.setTipo(convertirString(recarga.getTipo()));
					medioDeRecargaObj.setIdCuentaVitual(convertirString(recarga.getIDCuentaVitual()));
					medioDeRecargaObj.setIdTarjeta(convertirString(recarga.getIDTarjeta()));
					medioDeRecargaObj.setIdMarcaTarjeta(recarga.getIDMarcaTarjeta());
					medioDeRecargaObj.setUlt4DigitosTarjetas(convertirString(recarga.getUlt4DigitosTarjetas()));

					obj.setMedioDeRecarga(medioDeRecargaObj);
				}

				obj.setCategoria(convertirString(response.getCategoria()));
				obj.setEstado(convertirString(response.getEstado()));

				objList.add(obj);
			}
		}
		return objList;

	}

	/**
	 * Convertir string.
	 *
	 * @param obj
	 *            the obj
	 * @return the string
	 */
	private String convertirString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * Obtener transacciones tags response.
	 *
	 * @param respuestas
	 *            the respuestas
	 * @return the list
	 */
	private List<TransaccionEntity> obtenerTransaccionesTagsResponse(RespuestaTransaccionesTag respuestas) {
		List<TransaccionEntity> objList = new ArrayList<TransaccionEntity>();

		Datos datos = respuestas.getDatos();

		for (RespuestaTransaccionesTag.Datos.Dato dato : datos.getDato()) {
			ObtenerTransaccionesTagDataResponse dataResponse = dato.getObtenerTransaccionesTagDataResponse();
			TransaccionEntity obj = new TransaccionEntity();
			obj.setIdTrx(convertirString(dataResponse.getIDTrx()));
			obj.setOrden(convertirString(dataResponse.getOrden()));
			obj.setFecha(dataResponse.getFecha());
			obj.setTipo(dataResponse.getTipo());
			obj.setEstado(dataResponse.getEstado());
			obj.setImporte(convertirString(dataResponse.getImporte()));
			obj.setLugar(dataResponse.getLugar());
			objList.add(obj);
		}

		return objList;
	}

}
