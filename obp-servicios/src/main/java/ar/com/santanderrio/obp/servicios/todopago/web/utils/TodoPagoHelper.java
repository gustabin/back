/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.web.utils;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.TipoIdentificacion;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoInDTO;
import ar.com.santanderrio.obp.servicios.todopago.web.view.TodoPagoView;

/**
 * The Class TodoPagoHelper.
 */
@Component
public class TodoPagoHelper {

	/** The Constant NL. */
	private static final String NL = "\n";

	/** The Constant TAB. */
	private static final String TAB = "\t";

	/** The Constant ESPACIO. */
	private static final String ESPACIO = " ";

	/** The Constant BLANK. */
	private static final String BLANK = "-";

	/**
	 * Crear adhesion TodoPago DTO.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the adhesion TodoPago in DTO
	 */
	public AdhesionTodoPagoInDTO crearAdhesionTodoPagoDTO(TodoPagoView viewRequest) {
		AdhesionTodoPagoInDTO dto = new AdhesionTodoPagoInDTO();
		BeanUtils.copyProperties(viewRequest, dto);
		dto.setFechaSolicitud(new Date());
		dto.setActividad(viewRequest.getActividad());
		dto.setApellido(viewRequest.getApellido());
		dto.setCelular(viewRequest.getCelular());
		dto.setNumeroCuenta(viewRequest.getNumeroCuenta());
		dto.setNumeroDocumento(viewRequest.getNumeroDocumento());
		dto.setDomicilioLegal(viewRequest.getDomicilioLegal());
		dto.setEmail(viewRequest.getEmail());
		dto.setEmpresaCelular(viewRequest.getEmpresaCelular());
		dto.setFechaNacimiento(viewRequest.getFechaNacimiento());
		dto.setCondicionIIBB(viewRequest.getCondicionIIBB());
		dto.setCondicionIVA(viewRequest.getCondicionIVA());
		dto.setNombre(viewRequest.getNombre());
		dto.setPaisOrigen(viewRequest.getPaisOrigen().getDescripcion());
		dto.setSexo(viewRequest.getSexo());
		dto.setTelefonoFijo(viewRequest.getTelefonoFijo());
		dto.setApellidoContacto(viewRequest.getApellidoContacto());
		dto.setDescripcionTipoCuenta(viewRequest.getDescripcionTipoCuenta());
		return dto;
	}

	/**
	 * Crear alta adhesion envio mail DTO.
	 *
	 * @param todoPagoView
	 *            the TodoPago view
	 * @return the string
	 */
	public String crearAltaAdhesionEnvioMailDTO(TodoPagoView todoPagoView) {

		StringBuilder mailBody = new StringBuilder();

		mailBody.append("Email:" + ESPACIO);
		mailBody.append(todoPagoView.getEmail() + NL);
		mailBody.append("Datos de la cuenta:" + NL);
		mailBody.append(TAB + "Nombre:" + ESPACIO);
		mailBody.append(todoPagoView.getNombre() + NL);
		mailBody.append(TAB + "Apellido:" + ESPACIO);
		mailBody.append(todoPagoView.getApellido() + NL);
		mailBody.append(TAB + "Sexo:" + ESPACIO);
		mailBody.append(todoPagoView.getSexo() + NL);
		mailBody.append(TAB + "País de origen:" + ESPACIO);
		mailBody.append(todoPagoView.getPaisOrigen().getDescripcion() + NL);
		mailBody.append(TAB + "Tipo de documento:" + ESPACIO);
		mailBody.append(TipoIdentificacion.fromKey(todoPagoView.getTipoDocumento()).getDescripcion() + NL);
		mailBody.append(TAB + "Nro. de documento:" + ESPACIO);
		mailBody.append(todoPagoView.getNumeroDocumento() + NL);
		mailBody.append(TAB + "Fecha de nacimiento:" + ESPACIO);
		mailBody.append(ISBANStringUtils.formatearFecha(todoPagoView.getFechaNacimiento()) + NL);
		mailBody.append(TAB + "Compañía de celular:" + ESPACIO);
		if (ISBANStringUtils.isNullOrBlank(todoPagoView.getCelular())) {
            mailBody.append(BLANK + NL);
        } else {
            mailBody.append(todoPagoView.getEmpresaCelular() + NL);
        }
		mailBody.append(TAB + "Celular:" + ESPACIO);
		if (ISBANStringUtils.isNullOrBlank(todoPagoView.getCelular())) {
			mailBody.append(BLANK + NL);
		} else {
			mailBody.append(todoPagoView.getCelular() + NL);
		}
		mailBody.append(TAB + "Teléfono fijo:" + ESPACIO);
		mailBody.append((ISBANStringUtils.isNullOrBlank(todoPagoView.getTelefonoFijo()) ? BLANK
				: todoPagoView.getTelefonoFijo()) + NL);
		mailBody.append(TAB + "Condición IVA:" + ESPACIO);
		mailBody.append(todoPagoView.getCondicionIVA() + NL);
		mailBody.append(TAB + "Condición IIBB:" + ESPACIO);
		mailBody.append((ISBANStringUtils.isNullOrBlank(todoPagoView.getCondicionIIBB()) ? BLANK
				: todoPagoView.getCondicionIIBB()) + NL);
		mailBody.append(TAB + "Número IIBB:" + ESPACIO);
		mailBody.append((ISBANStringUtils.isNullOrBlank(todoPagoView.getNumeroIIBB()) ? BLANK
                : todoPagoView.getNumeroIIBB()) + NL);
		mailBody.append(TAB + "Actividad:" + ESPACIO);
		mailBody.append((ISBANStringUtils.isNullOrBlank(todoPagoView.getActividad()) ? BLANK 
		        : todoPagoView.getActividad()) + NL);
		mailBody.append(TAB + "CUIT/CUIL" + ESPACIO);
		mailBody.append(todoPagoView.getCuitDni() + NL);

		mailBody.append("Domicilio legal:" + NL);
		mailBody.append(TAB + "Calle:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getCalle() + NL);
		mailBody.append(TAB + "Número:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getNumero() + NL);
		mailBody.append(TAB + "Piso:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getPiso() + NL);
		mailBody.append(TAB + "Departamento:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getDepartamento() + NL);
		mailBody.append(TAB + "Código postal:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getCodigoPostal() + NL);
		mailBody.append(TAB + "Provincia:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getProvincia() + NL);
		mailBody.append(TAB + "Localidad:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getLocalidad() + NL);

		mailBody.append("Domicilio de facturación:" + NL);
		mailBody.append(TAB + "Calle:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getCalle() + NL);
		mailBody.append(TAB + "Número:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getNumero() + NL);
		mailBody.append(TAB + "Piso:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getPiso() + NL);
		mailBody.append(TAB + "Departamento:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getDepartamento() + NL);
		mailBody.append(TAB + "Código postal:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getCodigoPostal() + NL);
		mailBody.append(TAB + "Provincia:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getProvincia() + NL);
		mailBody.append(TAB + "Localidad:" + ESPACIO);
		mailBody.append(todoPagoView.getDomicilioLegal().getLocalidad() + NL);

		mailBody.append("Cuenta para acreditar pagos:" + NL);
		mailBody.append(TAB + "CBU:" + ESPACIO);
		mailBody.append(todoPagoView.getCbu() + NL);

		return mailBody.toString();
	}
}
