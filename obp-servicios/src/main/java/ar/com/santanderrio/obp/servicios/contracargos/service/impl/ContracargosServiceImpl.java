package ar.com.santanderrio.obp.servicios.contracargos.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.contracargos.service.ContracargosService;

@Component
public class ContracargosServiceImpl implements ContracargosService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ContracargosService.class);
	
	@Autowired
	private ArchivoDeRecursosDAO archivoResource;
	
	@Value("${CHARGEBACKS-NUP-MIN}")
    private String nupMin;
	
	@Value("${CHARGEBACKS-NUP-MAX}")
    private String nupMax;
	
	@Override
	public Boolean getOptionContracargos(String nup) {
		List<String> nups = new LinkedList<String>();
		int linea = 0;
		int largoDatosMinimo = 5;
		
		try {
			List<String> lineasTexto = archivoResource.leerArchivo(ExternalPropertyType.FILE_NUPS_CHARGEBACKS);
			if ((Integer.valueOf(nup) < Integer.valueOf(nupMax)) || (lineasTexto.get(0).equals("*"))){
				return true;
			} else {
				for (String lineaTexto : lineasTexto) {
					++linea;
	
					if (largoDatosMinimo < lineaTexto.length()) {
						nups.add(lineaTexto);
					} else {
						LOGGER.debug("Error de formato en la linea: " + linea + " del archivo: "
								+ ExternalPropertyType.FILE_NUPS_CHARGEBACKS.getName() + ".txt");
					}
				}
				return nups.contains(nup);
			} 
		}
		catch (DAOException e) {
			e.printStackTrace();
			LOGGER.info("Error al leer archivo " + ExternalPropertyType.FILE_NUPS_CHARGEBACKS);
			return false;
		}		
	}

}
