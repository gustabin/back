package ar.com.santanderrio.obp.base.webservice.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class RestLoggingInterceptor.
 */
public class RestLoggingInterceptor implements ClientHttpRequestInterceptor {

    /** The logger. */
    private static final Logger logger = LoggerFactory.getLogger(RestLoggingInterceptor.class);

	/** The palabras sensibles. */
	private String[] palabrasSensibles = {"pin", "newPin", "actualPin", "password", "newPassword", "actualPassword", "access_token", "refresh_token", "username", "newUserName"};

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.http.client.ClientHttpRequestInterceptor#intercept(
     * org.springframework.http.HttpRequest, byte[],
     * org.springframework.http.client.ClientHttpRequestExecution)
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = null;
        try {
            response = execution.execute(request, body);
        } catch (IOException e) {
            throw e;
        } finally {
            if (response != null) {
            	if (response.getRawStatusCode() == 451 ) {
            		logger.info("REST - Response Raw Status Code: " + response.getRawStatusCode());
            	} else {
            		logger.info("REST - Response Status Code: " + response.getStatusCode());
            		logger.info("REST - Response Status Text: " + response.getStatusText());
            		logger.info("REST - Response Body : " + getBodyString(response));
            	}
           }
        }
        return response;
    }

    /**
     * Gets the body string.
     *
     * @param response
     *            the response
     * @return the body string
     */
    private String getBodyString(ClientHttpResponse response) {
        try {
            if (response != null && response.getBody() != null && isReadableResponse(response)) {
                StringBuilder inputStringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
                String line = bufferedReader.readLine();
                while (line != null) {
                    inputStringBuilder.append(line);
                    inputStringBuilder.append('\n');
                    line = bufferedReader.readLine();
                }
                return RestMaskLoggingUtils.procesarLogRest(inputStringBuilder.toString(), palabrasSensibles);
            } else {
                return null;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Checks if is readable response.
     *
     * @param response
     *            the response
     * @return true, if is readable response
     */
    private boolean isReadableResponse(ClientHttpResponse response) {
        if (response.getHeaders().containsKey("Content-Type")) {
            for (String contentType : response.getHeaders().get("Content-Type")) {
                if (isReadableContentType(contentType)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if is readable content type.
     *
     * @param contentType
     *            the content type
     * @return true, if is readable content type
     */
    private boolean isReadableContentType(String contentType) {
        return contentType.startsWith("application/json") || contentType.startsWith("text");
    }

    /**
     * Trace request.
     *
     * @param request
     *            the request
     * @param body
     *            the body
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        logger.info("Request URI : " + request.getURI());
        logger.info("Request Method : " + request.getMethod());
        logger.info("Request Body : " + getRequestBody(body));
    }

    /**
     * Gets the request body.
     *
     * @param body
     *            the body
     * @return the request body
     * @throws UnsupportedEncodingException
     *             the unsupported encoding exception
     */
    private String getRequestBody(byte[] body) throws UnsupportedEncodingException {
        if (body != null && body.length > 0) {
            return getBodyAsJson(new String(body, "UTF-8"));
        } else {
            return null;
        }
    }

    /**
     * Gets the body as json.
     *
     * @param bodyString
     *            the body string
     * @return the body as json
     */
    private String getBodyAsJson(String bodyString) {
        if (bodyString == null || bodyString.length() == 0) {
            return null;
        } else {
        	bodyString = RestMaskLoggingUtils.procesarLogRest(bodyString, palabrasSensibles);
            if (isValidJSON(bodyString)) {
                return bodyString;
            } else {
                bodyString.replaceAll("\"", "\\\"");
                return "\"" + bodyString + "\"";
            }
        }
    }

    /**
     * Checks if is valid JSON.
     *
     * @param json
     *            the json
     * @return true, if is valid JSON
     */
    public boolean isValidJSON(final String json) {
        boolean valid = false;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readTree(json);
        } catch (IOException ioe) {
            logger.debug("El json sobre el que se esta trabajando NO ES VALIDO. {}", json, ioe);
            valid = false;
        }

        return valid;
    }
}
