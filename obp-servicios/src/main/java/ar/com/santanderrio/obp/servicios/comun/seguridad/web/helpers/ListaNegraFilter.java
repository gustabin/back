/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.seguridad.web.helpers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ar.com.santanderrio.obp.base.exceptions.SecurityValidatorException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * Filtro que evalua los parametros enviados al servidor <br>
 * Aplica a los atributos pasados por URL y al payload (json) del request como
 * texto plano.
 */
@Component("listaNegraFilter")
public class ListaNegraFilter extends OncePerRequestFilter {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ListaNegraFilter.class);
	/** Tipo de filtro authentication. */
	public static final String WWW_AUTH_MESSAGE = "Lista Negra";
	/** Charset utf-8. */
	public static final String CHARSET_UTF8 = ";charset=UTF-8";
	
	/** Postion path. */
	public static final int POSITION_PATH = 2;

	/** the excludeUrlList. */
	private List<String> excludeUrlList;

	/** The reduced wordlist url list. */
	private List<String> reducedWordlistUrlList;

	/** the excludeUrls. */
	private String excludeUrls;

	/** The reduced wordlist urls. */
	private String reducedWordlistUrls;

    private static final String URL_BFF = "/obp-servicios/bff/";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		try {
            if (!servletRequest.getRequestURI().contains(URL_BFF)) {
				validarPayload(servletRequest);
                validarParametros(servletRequest);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (SecurityValidatorException e) {
            LOGGER.error("Error en el request recibido.", e);
            sendErrorPage(servletResponse, e.getMessage());
        }
    }

	/**
	 * Validar los parametros recibidos.
	 *
	 * @param servletRequest
	 *            the servlet request
	 * @throws SecurityValidatorException
	 *             the security validator exception
	 */
	private void validarParametros(HttpServletRequest servletRequest) throws SecurityValidatorException {
		Enumeration<String> e = servletRequest.getParameterNames();
		while (e.hasMoreElements()) {
			validateParameters(e, servletRequest);
		}

	}

	/**
	 * Obtener el json posteado y validarlo.
	 *
	 * @param servletRequest
	 *            the servlet request
	 * @throws SecurityValidatorException
	 *             the security validator exception
	 */
	private void validarPayload(HttpServletRequest servletRequest) throws SecurityValidatorException {
		try {
			String json = obtenerJson(servletRequest);
			validarJson(json, Boolean.TRUE, shouldUseReducedWordList(servletRequest.getRequestURI()));
		} catch (IOException e) {
			LOGGER.error("Error al obtener el payload no se puede validar.", e);
		}
	}

	/**
	 * Should use reduced word list.
	 *
	 * @param requestUri the request uri
	 * @return the boolean
	 */
	private Boolean shouldUseReducedWordList(String requestUri) {
		int off = requestUri.indexOf("/", 1);
		String actionpath = requestUri.substring(off);
		if (actionpath.endsWith("/")) {
			actionpath = actionpath.substring(0, actionpath.length() - 1);
		}
		return reducedWordlistUrlList.contains(actionpath);
	}

	/**
	 * Validar json posteado.
	 *
	 * @param json
	 *            the json
	 * @param useReducedWordList
	 *            the use reduced word list
	 * @throws SecurityValidatorException
	 *             the security validator exception
	 */
	private void validarJson(String json, Boolean isPayload, Boolean useReducedWordList) throws SecurityValidatorException {
		if (StringUtils.isNotBlank(json)) {
			Validator.getParam(json, isPayload, useReducedWordList);
		}
	}

	/**
	 * Obtener el json posteado.
	 *
	 * @param servletRequest
	 *            the servlet request
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private String obtenerJson(HttpServletRequest servletRequest) throws IOException {
		HttpServletRequestWrapper requestWrapper = (HttpServletRequestWrapper) servletRequest;
		return IOUtils.toString(requestWrapper.getInputStream());
	}

	/**
	 * Armar el objeto de respuesta con el mensaje a mostrar.
	 *
	 * @param res
	 *            the res
	 * @param mensaje
	 *            the mensaje
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void sendErrorPage(HttpServletResponse res, String mensaje) throws IOException {
		Respuesta<String> respuestaError = new Respuesta<String>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setMensaje(mensaje);
		item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		respuestaError.add(item);

		ObjectMapper mapper = new ObjectMapper();
		String jsonRespuesta = mapper.writeValueAsString(respuestaError);

		res.reset();
		res.setHeader(HttpHeaders.WWW_AUTHENTICATE, WWW_AUTH_MESSAGE);
		res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		res.setContentType(MediaType.APPLICATION_JSON + CHARSET_UTF8);
		res.getWriter().write(jsonRespuesta);
	}

	/**
	 * Validate parameters.
	 *
	 * @param e
	 *            the e
	 * @param request
	 *            the request
	 * @throws SecurityValidatorException
	 *             the security validator exception
	 */
	private void validateParameters(Enumeration<String> e, HttpServletRequest request)
			throws SecurityValidatorException {
		String param = request.getParameter(e.nextElement());
		Validator.getParam(param, Boolean.FALSE, shouldUseReducedWordList(request.getRequestURI()));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.filter.OncePerRequestFilter#shouldNotFilter(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String[] urlPath = request.getRequestURI().split("/");
		if(urlPath.length > POSITION_PATH && this.excludeUrlList.contains(urlPath[POSITION_PATH])) {
			return true;
		}
		return super.shouldNotFilter(request);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.GenericFilterBean#initFilterBean()
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		if (this.excludeUrls != null) {
			String[] urlsToSet = this.excludeUrls.split(",");
			setExcludeUrlList(Arrays.asList(urlsToSet));
		}
		if (this.reducedWordlistUrls != null) {
			String[] urlsToSet = this.reducedWordlistUrls.split(",");
			setReducedWordlistUrlList(Arrays.asList(urlsToSet));
		}
		super.initFilterBean();
	}

	/**
	 * Gets the exclude urls.
	 *
	 * @return excludeUrls
	 */
	public String getExcludeUrls() {
		return excludeUrls;
	}

	/**
	 * Sets the exclude urls.
	 *
	 * @param excludeUrls
	 *            the new exclude urls
	 */
	public void setExcludeUrls(String excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * Gets the exclude url list.
	 *
	 * @return excludeUrlList
	 */
	public List<String> getExcludeUrlList() {
		return excludeUrlList;
	}

	/**
	 * Sets the exclude url list.
	 *
	 * @param excludeUrlList
	 *            the new exclude url list
	 */
	public void setExcludeUrlList(List<String> excludeUrlList) {
		this.excludeUrlList = excludeUrlList;
	}	   

	/**
	 * Gets the reduced wordlist urls.
	 *
	 * @return the reduced wordlist urls
	 */
	public String getReducedWordlistUrls() {
		return reducedWordlistUrls;
	}

	/**
	 * Sets the reduced wordlist urls.
	 *
	 * @param reducedWordlistUrls the new reduced wordlist urls
	 */
	public void setReducedWordlistUrls(String reducedWordlistUrls) {
		this.reducedWordlistUrls = reducedWordlistUrls;
	}

	/**
	 * Gets the reduced wordlist url list.
	 *
	 * @return the reduced wordlist url list
	 */
	public List<String> getReducedWordlistUrlList() {
		return reducedWordlistUrlList;
	}

	/**
	 * Sets the reduced wordlist url list.
	 *
	 * @param reducedWordlistUrlList the new reduced wordlist url list
	 */
	public void setReducedWordlistUrlList(List<String> reducedWordlistUrlList) {
		this.reducedWordlistUrlList = reducedWordlistUrlList;
	}

}
