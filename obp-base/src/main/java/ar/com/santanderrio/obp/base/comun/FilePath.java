package ar.com.santanderrio.obp.base.comun;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class FilePath.
 */
@Component
public class FilePath {

	/** The env. */
	@Autowired
	private Environment env;

	/** The file path. */
	private String filePath;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		String properties = env.getProperty("config/OBP_UBICACION_PROPIEDADES") + "/" + env.getActiveProfiles()[0];
		filePath = properties + "/files/";
	}

	/**
	 * Gets the file path.
	 *
	 * @return the file path
	 */
	public String getFilePath() {
		return filePath;
	}
}
