/*
 *
 */
package ar.com.santanderrio.obp.servicios.comun.seguridad.web.helpers;

import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Wrapper del objeto request para poder aplicar un filtrado <br>
 * sobre los parametros enviados al servidor.<br>
 * Solo aplica a los atributos pasados por URL.<br>
 * Levanta el payload para poder evaluar el json.
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XssRequestWrapper.class);

    /**
     * The req.
     */
    private HttpServletRequest req = null;
    /**
     * The request body.
     */
    private final String payload;

    /**
     * The patterns.
     */
    private static Pattern[] patterns = new Pattern[]{
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
            Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
            Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("\""), Pattern.compile("\'"), Pattern.compile("`"), Pattern.compile("<"),
            Pattern.compile(">"), Pattern.compile("<(\\s*)(/\\s*)?script(\\s*)>"), Pattern.compile("%3Cscript%3E"),
            Pattern.compile("alert(\\s*)\\("), Pattern.compile("alert%28"),
            Pattern.compile("document(.*)\\.(.*)cookie"), Pattern.compile("eval(\\s*)\\("),
            Pattern.compile("setTimeout(\\s*)\\("), Pattern.compile("setInterval(\\s*)\\("),
            Pattern.compile("execScript(\\s*)\\("), Pattern.compile("(?i)javascript(?-i):"),
            Pattern.compile("(?i)onclick(?-i)"), Pattern.compile("(?i)ondblclick(?-i)"),
            Pattern.compile("(?i)onmouseover(?-i)"), Pattern.compile("(?i)onmousedown(?-i)"),
            Pattern.compile("(?i)onmouseup(?-i)"), Pattern.compile("(?i)onmousemove(?-i)"),
            Pattern.compile("(?i)onmouseout(?-i)"), Pattern.compile("(?i)onchange(?-i)"),
            Pattern.compile("(?i)onfocus(?-i)"), Pattern.compile("(?i)onblur(?-i)"),
            Pattern.compile("(?i)onkeypress(?-i)"), Pattern.compile("(?i)onkeyup(?-i)"),
            Pattern.compile("(?i)onkeydown(?-i)"), Pattern.compile("(?i)onload(?-i)"),
            Pattern.compile("(?i)onreset(?-i)"), Pattern.compile("(?i)onselect(?-i)"),
            Pattern.compile("(?i)onsubmit(?-i)"), Pattern.compile("(?i)onunload(?-i)"),
            Pattern.compile("&#x61;&#x6C;&#x65;&#x72;&#x74;"), Pattern.compile("catcat"), Pattern.compile("alert"),
            Pattern.compile("object data"), Pattern.compile("data:text"), Pattern.compile("base64")};

    /**
     * Instantiates a new xss request wrapper.
     *
     * @param servletRequest the servlet request
     */
    public XssRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
        req = (HttpServletRequest) servletRequest;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            InputStream inputStream = servletRequest.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            LOGGER.error("Error reading the request payload.", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException iox) {
                    LOGGER.error("Error al cerrar el buffer para levantar el payload.", iox);
                }
            }
        }
        payload = stringBuilder.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.servlet.ServletRequestWrapper#getParameterValues(java.lang.String)
     */
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        String[] vuelta;
        if (values == null) {
            vuelta = null;
        } else {
            int count = values.length;
            String[] encodedValues = new String[count];
            for (int i = 0; i < count; i++) {
                encodedValues[i] = stripXSS(values[i]);
            }
            vuelta = encodedValues;
        }

        return vuelta;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
     */
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        return stripXSS(value);
    }

    /**
     * Strip XSS.
     *
     * @param value the value
     * @return String
     */
    private String stripXSS(String value) {
        String newValue = value;
        LOGGER.debug("{}: Remove all sections that match a pattern in stripXSS()", this.req);
        String cleareadValue = "";
        String originalValue = "";
        if (newValue != null) {
            newValue = ESAPI.encoder().canonicalize(newValue);
            newValue = newValue.replaceAll("\0", "");
            originalValue = newValue;
            for (Pattern scriptPattern : patterns) {
                Matcher match = scriptPattern.matcher(newValue);
                if (match.find()) {
                    LOGGER.info("Se remueve el dato {} recibido.", newValue);
                    newValue = match.replaceAll("");
                }
            }
            cleareadValue = newValue;
            if (!cleareadValue.equals(originalValue)) {
                LOGGER.warn("{}: XssFilter originalValue = {}", this.req, originalValue);
                LOGGER.warn("{}: XssFilter cleareadValue = {}", this.req, cleareadValue);
            }
        }
        return newValue;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.ServletRequestWrapper#getInputStream()
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payload.getBytes());
        return new ServletInputStream() {
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.ServletRequestWrapper#getReader()
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}