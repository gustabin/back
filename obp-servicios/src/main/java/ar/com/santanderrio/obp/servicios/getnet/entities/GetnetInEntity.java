package ar.com.santanderrio.obp.servicios.getnet.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetAdhesionDTO;

/**
 * The Class GetNetInEntity.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class GetnetInEntity {
	
	/** The channel. */
	@JsonProperty("channel")
	private String canal;
	
	/** The email. */
	@JsonProperty("email_address")
	private String email;
	
	/** The celular. */
	@JsonProperty("phone_number")
	private String celular;
	
	/** The nombre empresa. */
	@JsonProperty("company_name")
	private String nombreEmpresa;
	
	/** The nombre fantasia. */
	@JsonProperty("fantasy_name")
	private String nombreFantasia;
	
	/** The codigo sujeto. */
	@JsonProperty("liable_subject")
	private Boolean codigoSujeto;
	
	/** The cbu. */
	@JsonProperty("account_cbu")
	private String cbu;
	
	/** The informacion personal. */
	@JsonProperty("personal_information")
	private GetnetInformacionPersonalEntity informacionPersonal;
	
	/** The direccion. */
	@JsonProperty("address")
	private GetnetDireccionEntity direccion;
	
	/** The representativos legales. */
	@JsonProperty("legal_representatives")
	private List<GetnetInformacionPersonalEntity> representativosLegales;
	
	/** The categorizacion iva. */
	@JsonProperty("iva_categorization")
	private String categorizacionIva;
	
	/** The categorizacion iibb. */
	@JsonProperty("iibb_categorization")
	private String categorizacionIibb;
	
	/** The numero iibb. */
	@JsonProperty("iibb_numero")
	private String numeroIibb;
	
	/** The fecha de exclusion iibb desde. */
	@JsonProperty("iibb_exclusion_date_since")
	private String fechaExclusionIibbDesde;
	
	/** The fecha de exclusion iibb hasta. */
	@JsonProperty("iibb_exclusion_date_until")
	private String fechaExclusionIibbHasta;
	
	/** The actividad. */
	@JsonProperty("activity")
	private String actividad;
	
	/** The ingresos. */
	@JsonProperty("income")
	private Integer ingreso;
	
	/** The tyc. */
	@JsonProperty("terms_and_conditions")
	private String tyc;
	
	/** The funcionario bancario. */
	@JsonProperty("bank_official")
	private String funcionarioBancario;
	
	/** The sucursal. */
	@JsonProperty("bank_branch")
	private String sucursal;
	
	/** The fecha apertura cuenta. */
	@JsonProperty("account_creation_date")
	private String fechaAperturaCuenta;

	/** The CANAL_OBP. */
	private static final String CANAL_OBP = "OBP";
	
	/** The VACIO. */
	private static final String VACIO = "";
	
	/** The MALE. */
	private static final String MALE = "MALE";
	
	/** The FEMALE. */
	private static final String FEMALE = "FEMALE";
	
	/** The H. */
	private static final String H = "H";
	
	/** The CEROS. */
	private static final Integer CEROS = 000;
	
	/** The ESPACIOS. */
	private static final String ESPACIO = " ";
	
	/** The GUION. */
	private static final String GUION = "-";
	
	/** The GUION_CON_BARRA. */
	private static final String GUION_CON_BARRA = "\\-";
	
	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * Sets the celular.
	 *
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Gets the nombre empresa.
	 *
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Sets the nombre empresa.
	 *
	 * @param nombreEmpresa the nombre empresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * Gets the nombre fantasia.
	 *
	 * @return the nombreFantasia
	 */
	public String getNombreFantasia() {
		return nombreFantasia;
	}

	/**
	 * Sets the nombre fantasia.
	 *
	 * @param nombreFantasia the nombreFantasia to set
	 */
	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	/**
	 * Gets the codigo sujeto.
	 *
	 * @return the codigoSujeto
	 */
	public Boolean getCodigoSujeto() {
		return codigoSujeto;
	}

	/**
	 * Sets the codigo sujeto.
	 *
	 * @param codigoSujeto the codigoSujeto to set
	 */
	public void setCodigoSujeto(Boolean codigoSujeto) {
		this.codigoSujeto = codigoSujeto;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the informacion personal.
	 *
	 * @return the informacionPersonal
	 */
	public GetnetInformacionPersonalEntity getInformacionPersonal() {
		return informacionPersonal;
	}

	/**
	 * Sets the informacion personal.
	 *
	 * @param informacionPersonal the informacionPersonal to set
	 */
	public void setInformacionPersonal(GetnetInformacionPersonalEntity informacionPersonal) {
		this.informacionPersonal = informacionPersonal;
	}

	/**
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public GetnetDireccionEntity getDireccion() {
		return direccion;
	}

	/**
	 * Sets the direccion.
	 *
	 * @param direccion the direccion to set
	 */
	public void setDireccion(GetnetDireccionEntity direccion) {
		this.direccion = direccion;
	}

	/**
	 * Gets the representativos legales.
	 *
	 * @return the representativosLegales
	 */
	public List<GetnetInformacionPersonalEntity> getRepresentativosLegales() {
		return representativosLegales;
	}

	/**
	 * Sets the representativos legales.
	 *
	 * @param representativosLegales the representativosLegales to set
	 */
	public void setRepresentativosLegales(List<GetnetInformacionPersonalEntity> representativosLegales) {
		this.representativosLegales = representativosLegales;
	}

	/**
	 * Gets the categorizacion iva.
	 *
	 * @return the categorizacionIva
	 */
	public String getCategorizacionIva() {
		return categorizacionIva;
	}

	/**
	 * Sets the categorizacion iva.
	 *
	 * @param categorizacionIva the categorizacionIva to set
	 */
	public void setCategorizacionIva(String categorizacionIva) {
		this.categorizacionIva = categorizacionIva;
	}

	/**
	 * Gets the categorizacion iibb.
	 *
	 * @return the categorizacionIibb
	 */
	public String getCategorizacionIibb() {
		return categorizacionIibb;
	}

	/**
	 * Sets the categorizacion iibb.
	 *
	 * @param categorizacionIibb the categorizacionIibb to set
	 */
	public void setCategorizacionIibb(String categorizacionIibb) {
		this.categorizacionIibb = categorizacionIibb;
	}

	/**
	 * Gets the numero iibb.
	 *
	 * @return the numeroIibb
	 */
	public String getNumeroIibb() {
		return numeroIibb;
	}

	/**
	 * Sets the numero iibb.
	 *
	 * @param numeroIibb the numeroIibb to set
	 */
	public void setNumeroIibb(String numeroIibb) {
		this.numeroIibb = numeroIibb;
	}

	/**
	 * Gets the fecha exclusion iibb desde.
	 *
	 * @return the fechaExclusionIibbDesde
	 */
	public String getFechaExclusionIibbDesde() {
		return fechaExclusionIibbDesde;
	}

	/**
	 * Sets the fecha exclusion iibb desde.
	 *
	 * @param fechaExclusionIibbDesde the fechaExclusionIibbDesde to set
	 */
	public void setFechaExclusionIibbDesde(String fechaExclusionIibbDesde) {
		this.fechaExclusionIibbDesde = fechaExclusionIibbDesde;
	}

	/**
	 * Gets the fecha exclusion iibb hasta.
	 *
	 * @return the fechaExclusionIibbHasta
	 */
	public String getFechaExclusionIibbHasta() {
		return fechaExclusionIibbHasta;
	}

	/**
	 * Sets the fecha exclusion iibb hasta.
	 *
	 * @param fechaExclusionIibbHasta the fechaExclusionIibbHasta to set
	 */
	public void setFechaExclusionIibbHasta(String fechaExclusionIibbHasta) {
		this.fechaExclusionIibbHasta = fechaExclusionIibbHasta;
	}

	/**
	 * Gets the actividad.
	 *
	 * @return the actividad
	 */
	public String getActividad() {
		return actividad;
	}

	/**
	 * Sets the actividad.
	 *
	 * @param actividad the actividad to set
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	/**
	 * Gets the ingreso.
	 *
	 * @return the ingreso
	 */
	public Integer getIngreso() {
		return ingreso;
	}

	/**
	 * Sets the ingreso.
	 *
	 * @param ingreso the ingreso to set
	 */
	public void setIngreso(Integer ingreso) {
		this.ingreso = ingreso;
	}

	/**
	 * Gets the tyc.
	 *
	 * @return the tyc
	 */
	public String getTyc() {
		return tyc;
	}

	/**
	 * Sets the tyc.
	 *
	 * @param tyc the tyc to set
	 */
	public void setTyc(String tyc) {
		this.tyc = tyc;
	}

	/**
	 * Gets the funcionario bancario.
	 *
	 * @return the funcionario bancario
	 */
	public String getFuncionarioBancario() {
		return funcionarioBancario;
	}

	/**
	 * Sets the funcionario bancario.
	 *
	 * @param funcionarioBancario the funcionario bancario to set
	 */
	public void setFuncionarioBancario(String funcionarioBancario) {
		this.funcionarioBancario = funcionarioBancario;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the fecha apertura cuenta.
	 *
	 * @return the fecha apertura cuenta
	 */
	public String getFechaAperturaCuenta() {
		return fechaAperturaCuenta;
	}

	/**
	 * Sets the fecha apertura cuenta.
	 *
	 * @param fechaAperturaCuenta the new fecha apertura cuenta
	 */
	public void setFechaAperturaCuenta(String fechaAperturaCuenta) {
		this.fechaAperturaCuenta = fechaAperturaCuenta;
	}

	/**
	 * Instantiates a new getnet in entity.
	 *
	 * @param dto the dto
	 */
	public GetnetInEntity(GetnetAdhesionDTO dto) {
		// Inicializo los datos vacios
		this.canal = CANAL_OBP;
		this.informacionPersonal = new GetnetInformacionPersonalEntity();
		this.direccion = new GetnetDireccionEntity();
		this.representativosLegales = new ArrayList<GetnetInformacionPersonalEntity>();
		this.fechaExclusionIibbDesde = VACIO;
		this.fechaExclusionIibbHasta = VACIO;
		this.funcionarioBancario = VACIO;
		this.sucursal = VACIO;
		
		// Inicializo los datos obtenidos desde el front
		this.email = dto.getEmail();
		this.celular = dto.getCelular();
		this.nombreFantasia = dto.getNombreFantasia();
		this.cbu = dto.getCbu();
		this.actividad = dto.getActividad();
		if (this.actividad.contains(GUION) ) {
			this.actividad.replace(GUION, GUION_CON_BARRA);
		}
		this.ingreso = dto.getIngreso();
	}
	
	/**
	 * Cargo los datos de IDECLTSDO.
	 *
	 * @param cliente the cliente
	 * @return void
	 */
	public void cargarDatosIdeClienteConSaldo(Cliente cliente) {
		this.nombreEmpresa = cliente.getNombre() + ESPACIO + cliente.getApellido1();
		this.informacionPersonal.setNup(cliente.getNup());
		this.informacionPersonal.setTipoDocumento(cliente.getTipoDocumento());
		this.informacionPersonal.setNumeroDocumento(Integer.parseInt(cliente.getDni()));
		// Formato IATX: 19290726 IATX - Formato API: 1970-01-01
		String fechaFormateada = cliente.getFechaNacimiento().substring(0, 4) + "-" 
				+ cliente.getFechaNacimiento().substring(4, 6) + "-"
				+ cliente.getFechaNacimiento().substring(6, 8);
		this.informacionPersonal.setFechaNacimiento(fechaFormateada);
		this.informacionPersonal.setNombre(cliente.getNombre());
		this.informacionPersonal.setApellido(cliente.getApellido1());
	}
	
	/**
	 * Cargo los datos de CNSPERSFIS.
	 *
	 * @param solicitante the solicitante
	 * @return void
	 */
	public void cargarDatosCnsPersFis(DatosSolicitanteEntity solicitante) {
		this.codigoSujeto = false;
		if (StringUtils.isNotBlank(solicitante.getCodigoSujeto())) {
			Integer codigoSujeto = Integer.parseInt(solicitante.getCodigoSujeto());
			if (CEROS.compareTo(codigoSujeto) > 0) {
				this.codigoSujeto = true;
			}
		}
		
		if (H.equals(solicitante.getSexo())) {
			this.informacionPersonal.setGenero(MALE);
		} else {
			this.informacionPersonal.setGenero(FEMALE);
		}
		
		this.informacionPersonal.setNacionalidad(solicitante.getNacionalidad());
		this.direccion.setCalle(solicitante.getCalle());
		this.direccion.setNumero(solicitante.getCalleNro());
		this.direccion.setPiso(solicitante.getPiso());
		this.direccion.setDepartamento(solicitante.getDepto());
		this.direccion.setCodigoPostal(solicitante.getCp());
		this.direccion.setCiudad(solicitante.getLocalidad());
		this.direccion.setProvincia(solicitante.getProvincia());
		this.direccion.setPais(solicitante.getCodigoPais());
	}
	
	/**
	 * Cargo los datos de ACTADHECNL y CNSINFIMPO.
	 *
	 * @param situacionImpositiva the situacion impositiva
	 * @param isExpuestoPoliticamente the is expuesto politicamente
	 * @return void
	 */
	public void cargarSituacionImpositivaYPolitica(ConsultaSitImpositivaOutEntity situacionImpositiva, 
			Boolean isExpuestoPoliticamente) {
		this.informacionPersonal.setExpuestoPoliticamente(isExpuestoPoliticamente);
		this.categorizacionIva = situacionImpositiva.getListaInfoImpositiva().get(0).getRespAnteIVA();
		this.categorizacionIibb = situacionImpositiva.getListaInfoImpositiva().get(0).getRespAnteIngBrutos();
		this.numeroIibb = situacionImpositiva.getListaInfoImpositiva().get(0).getNumIngBrutos();
	}
	
}
