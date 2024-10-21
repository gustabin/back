package ar.com.santanderrio.obp.servicios.delete.account.config;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeatureBajaCuentaConfig {

	@Autowired
	private ArchivoDeRecursosDAO archivoDeRecursosDAO;
	
	@Value("${FEATURE.ACCOUNTS.BAJA-CUENTA.ENABLED:false}")
	private boolean featureEnabled;
	
	@Value("${FEATURE.ACCOUNTS.BAJA-CUENTA.SEGMENTO-HABILITADO}")
	private String segmentosHabilitados;

	@Value("${FEATURE.ACCOUNTS.BAJA-CUENTA.VALIDATE-SEGMENTO-HABILITADO:true}")
	private boolean validateSegmentosHabilitados;

	@Value("${FEATURE.ACCOUNTS.BAJA-CUENTA.HORARIO-DESDE}")
	private String horarioDesde;
	
	@Value("${FEATURE.ACCOUNTS.BAJA-CUENTA.HORARIO-HASTA}")
	private String horarioHasta;

	@Value("${FEATURE.ACCOUNTS.BAJA-CUENTA.VALIDATE-NUP:false}")
	private boolean validateNup;

	private List<String> nupsHabilitados;

	public boolean isFeatureEnabled() {
		return featureEnabled;
	}

	public void setFeatureEnabled(boolean featureEnabled) {
		this.featureEnabled = featureEnabled;
	}

	public String getSegmentosHabilitados() {
		return segmentosHabilitados;
	}

	public void setSegmentosHabilitados(String segmentosHabilitados) {
		this.segmentosHabilitados = segmentosHabilitados;
	}

	public boolean isValidateSegmentosHabilitados() {
		return validateSegmentosHabilitados;
	}

	public void setValidateSegmentosHabilitados(boolean validateSegmentosHabilitados) {
		this.validateSegmentosHabilitados = validateSegmentosHabilitados;
	}

	public String getHorarioDesde() {
		return horarioDesde;
	}

	public void setHorarioDesde(String horarioDesde) {
		this.horarioDesde = horarioDesde;
	}

	public String getHorarioHasta() {
		return horarioHasta;
	}

	public void setHorarioHasta(String horarioHasta) {
		this.horarioHasta = horarioHasta;
	}

	public List<String> getSegmentosList() {
		return Arrays.asList(segmentosHabilitados.split(Pattern.quote("|")));
	}

	public boolean isValidateNup() {
		return validateNup;
	}

	public void setValidateNup(boolean validateNup) {
		this.validateNup = validateNup;
	}

	public List<String> getNupsHabilitados() throws DAOException {
		if (this.nupsHabilitados == null) {
			this.nupsHabilitados = archivoDeRecursosDAO.leerArchivo(ExternalPropertyType.FILE_NUPS_FLUJO_BAJA_CUENTA);
		}
		return this.nupsHabilitados;
	}

	public void setNupsHabilitados(List<String> nupsHabilitados) {
		this.nupsHabilitados = nupsHabilitados;
	}
}
