package ar.com.santanderrio.obp.base.iatx.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.iatx.helpers.FormatFep;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxInicial;
import ar.com.santanderrio.obp.base.iatx.helpers.SessionUsuarioProvider;

// TODO: Auto-generated Javadoc
/**
 * The Class IatxRequestData.
 */
/**
 * @author sergio.e.goldentair
 *
 */
public class IatxRequestData implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The no log request. */
    private boolean noLogRequest;

    /** The no log response. */
    private boolean noLogResponse;

    /** The n req censurar. */
    private int nReqCensurar = -1;

    /** The n res censurar. */
    private int nResCensurar = -1;

    /** The iatx header. */
    /*
     * TODO Ver si es adecuado reemplazar Vector con List
     */
    private Vector<String> iatxHeader;

    /** The iatx body. */
    private Vector<String> iatxBody;

    /** The accion mje multiple. */
    // 00 normal, 01 next, para rellamada
    private String accionMjeMultiple = "00";

    /** The autorizacion requerida. */
    // const
    private String autorizacionRequerida = "0";

    /** The canal id. */
    // const
    private String canalId = "HTML";

    /** The canal tipo. */
    // const
    private String canalTipo = "04";

    /** The canal version. */
    // const
    private String canalVersion = "000";

    /** The cant autorizantes. */
    // const
    private String cantAutorizantes = "0";

    /** The delimitador campos. */
    // const
    private String delimitadorCampos = "õ";

    /** The estado autorizacion. */
    // const
    private String estadoAutorizacion = "  ";

    /** The estado autorizante. */
    // const
    private String estadoAutorizante = "00";

    /** The fecha hora ejec diferida. */
    // const
    private String fechaHoraEjecDiferida = "            ";

    /** The fecha hora requerimiento. */
    // eso
    private String fechaHoraRequerimiento = "              ";

    /** The fecha nac cliente. */
    // segun cliente
    private String fechaNacCliente = "        ";

    /** The filler nup. */
    // con el anterior, ex fechaHoraCantDiasEjecDiferida
    private String fillerNup = "   ";

    /** The formato params. */
    // const
    private String formatoParams = "DH";

    /** The funcion autorizacion. */
    // const
    private String funcionAutorizacion = "00";

    /** The id autorizacion. */
    // const
    private String idAutorizacion = "00000000";

    /** The id autorizante. */
    // const
    private String idAutorizante = "000000000000";

    /** The id cliente. */
    // segun cliente
    private String idCliente = "           ";

    /** The id registro oper. */
    // const
    private String idRegistroOper = "00000000";

    /** The id sesion cliente. */
    // const
    private String idSesionCliente = "        ";

    /** The id sesion transaccional. */
    // variar� en msg m�ltiple
    private String idSesionTransaccional = "        ";

    /** The id sincronizacion. */
    // const
    private String idSincronizacion = "        ";

    /** The ind autenticacion. */
    // const
    private String indAutenticacion = "0";

    /** The ind autorizacion. */
    // const
    private String indAutorizacion = "0";

    /** The ind no utilizados v200. */
    // const
    private String indNoUtilizadosV200 = "  ";

    /** The ind oper reversa. */
    // segun servicio
    private String indOperReversa = "0";

    /** The ind sincronizacion. */
    // const
    private String indSincronizacion = "0";

    /** The ind sinonimo. */
    // segun cliente
    private String indSinonimo = " ";

    /** The nombre servicio. */
    // eso
    private String nombreServicio = "          ";

    /** The nro mje multiple. */
    // retorna 000 si no hay mas
    private String nroMjeMultiple = "000";

    /** The nro oper canal. */
    // const
    private String nroOperCanal = "00000000";

    /** The nro requerimiento. */
    // contador secuencial
    private String nroRequerimiento = "00000000";

    /** The nup. */
    // nup, ex fechaHoraCantDiasEjecDiferida
    private String nup = "        ";

    /** The pin autorizante. */
    // const
    private String pinAutorizante = "0000";

    /** The pin cliente. */
    // const
    private String pinCliente = "0000";

    /** The read lock time stamp. */
    // const
    private String readLockTimeStamp = "                          ";

    /** The request replay. */
    // const
    private String requestReplay = "Q";

    /** The sesion usuario. */
    private String sesionUsuario = "        ";

    /** The stat flag. */
    // statFlag
    private String statFlag = " ";

    /** The sub canal id. */
    // const
    private String subCanalId = "0001";

    /** The sub canal tipo. */
    // const
    private String subCanalTipo = "99";

    /** The tipo autorizante. */
    // const
    private String tipoAutorizante = "0";

    /** The tipo cliente. */
    // const
    private String tipoCliente = "I";

    /** The tipo compresion. */
    // const
    private String tipoCompresion = "00";

    /** The tipo ejecucion. */
    // const
    private String tipoEjecucion = "0";

    /** The tipo id cliente. */
    // segun cliente
    private String tipoIdCliente = " ";

    /** The tipo oper. */
    // const
    private String tipoOper = "0";

    /** The tipo respuesta. */
    // const
    private String tipoRespuesta = "0";

    /** The tipo seguridad. */
    // const
    private String tipoSeguridad = "00";

    /** The tipo validacion. */
    // segun servicio
    private String tipoValidacion = "0";

    /** The usuario racf atributos. */
    // const
    private String usuarioRacfAtributos = "  ";

    /** The usuario racf id. */
    // del seginform
    private String usuarioRacfId = "        ";

    /** The usuario racf pwd. */
    // del seginform
    private String usuarioRacfPwd = "        ";

    /** The usuario racf tipo. */
    // o 04, segun servicio
    private String usuarioRacfTipo = "03";

    /** The version. */
    // const
    private String version = "200";

    /** The version servicio. */
    // eso
    private String versionServicio = "   ";

    /**
     * No llama a iatx desde la session de un usuario sino que la api para validar
     * si esta respondiendo.
     */
    private Boolean apiMonitoreo = Boolean.FALSE;

    /** The Constant BODY_SIZE_5. */
    private static final int BODY_SIZE_5 = 5;

    /** The Constant CALENDAR_SIZE_4. */
    private static final int CALENDAR_SIZE_4 = 4;

    /** The Constant STAT_FLAG_SIZE. */
    private static final int STAT_FLAG_SIZE = 1;

    /** The Constant USUARIO_RACF_TIPO_SIZE. */
    private static final int USUARIO_RACF_TIPO_SIZE = 2;

    /** The Constant USUARIO_RACF_ID_SIZE. */
    private static final int USUARIO_RACF_ID_SIZE = 8;

    /** The Constant USUARIO_RACF_PWD_SIZE. */
    private static final int USUARIO_RACF_PWD_SIZE = 8;

    /** The Constant SESION_USUARIO_SIZE. */
    private static final int SESION_USUARIO_SIZE = 8;

    /** The Constant NRO_REQUERIMIENTO_SIZE. */
    private static final int NRO_REQUERIMIENTO_SIZE = 8;

    /** The Constant NOMBRE_SERVICIO_SIZE. */
    private static final int NOMBRE_SERVICIO_SIZE = 10;

    /** The Constant VERSION_SERVICIO_SIZE. */
    private static final int VERSION_SERVICIO_SIZE = 3;

    /** The Constant TIPO_VALIDACION_SIZE. */
    private static final int TIPO_VALIDACION_SIZE = 1;

    /** The Constant NUP_SIZE. */
    private static final int NUP_SIZE = 8;

    /** The Constant IND_OPER_REVERSA_SIZE. */
    private static final int IND_OPER_REVERSA_SIZE = 1;

    /** The Constant IND_SINONIMO_SIZE. */
    private static final int IND_SINONIMO_SIZE = 1;

    /** The Constant TIPO_ID_CLIENTE_SIZE. */
    private static final int TIPO_ID_CLIENTE_SIZE = 1;

    /** The Constant ID_CLIENTE_SIZE. */
    private static final int ID_CLIENTE_SIZE = 11;

    /** The Constant FECHA_NAC_CLIENTE_SIZE. */
    private static final int FECHA_NAC_CLIENTE_SIZE = 8;

    /** The Constant IND_AUTENTICACION. */
    private static final String IND_AUTENTICACION = "1";

    /**
     * Consultar a iatx para validar si esta respondiendo desde api de monitoreo.
     */
    private static final String CONSULTA_API_SIN_SESSION = "00000001";

    /**
     * Instantiates a new iatx request data.
     */
    public IatxRequestData() {
        this.iatxHeader = new Vector<String>();
        this.iatxBody = new Vector<String>();
    }

    /**
     * Gets the canal tipo.
     *
     * @return the canal tipo
     */
    public String getCanalTipo() {
        return canalTipo;
    }

    /**
     * Sets the canal tipo.
     *
     * @param canalTipo the new canal tipo
     */
    public void setCanalTipo(String canalTipo) {
        this.canalTipo = canalTipo;
    }

    /**
     * Gets the sub canal tipo.
     *
     * @return the sub canal tipo
     */
    public String getSubCanalTipo() {
        return subCanalTipo;
    }

    /**
     * Sets the sub canal tipo.
     *
     * @param subCanalTipo the new sub canal tipo
     */
    public void setSubCanalTipo(String subCanalTipo) {
        this.subCanalTipo = subCanalTipo;
    }

    /**
     * Instantiates a new iatx request data.
     *
     * @param resumenCliente
     *            the resumen cliente
     */
    public IatxRequestData(ResumenCliente resumenCliente) {
        iniciarFields(resumenCliente, Boolean.TRUE);
    }

    /**
     * Instantiates a new iatx request data.
     *
     * @param resumenCliente            the resumen cliente
     * @param conSession the con session
     */
    public IatxRequestData(ResumenCliente resumenCliente, Boolean conSession) {
        iniciarFields(resumenCliente, conSession);
    }

    /**
     * Iniciar los campos para invocar a iatx.
     *
     * @param resumenCliente the resumen cliente
     * @param conSession the con session
     */
    private void iniciarFields(ResumenCliente resumenCliente, Boolean conSession) {
        this.iatxHeader = new Vector<String>();
        this.iatxBody = new Vector<String>();
        if (resumenCliente.getUsuarioRacf() != null) {
            setUsuarioRacfId(resumenCliente.getUsuarioRacf());
        }
        if (resumenCliente.getPasswordRacf() != null) {
            setUsuarioRacfPwd(resumenCliente.getPasswordRacf());
        }
        if (resumenCliente.getTipoRacf() != null) {
            setUsuarioRacfTipo(resumenCliente.getTipoRacf());
        }
        if (resumenCliente.getNup() != null) {
            setNup(resumenCliente.getNup());
        }
        if (resumenCliente.getValorSinonimo() != null) {
            setIndSinonimo(resumenCliente.getValorSinonimo());
        }
        if (resumenCliente.getTipoDocumento() != null) {
            setTipoIdCliente(resumenCliente.getTipoDocumento());
        }
        if (resumenCliente.getDni() != null) {
            setIdCliente(resumenCliente.getDni());
        }
        if (resumenCliente.getFechaNacimiento() != null) {
            setFechaNacCliente(resumenCliente.getFechaNacimiento());
        }
        if (conSession) {
            setSesionUsuario(SessionUsuarioProvider.getSessionUsuario());
        } else {
            this.apiMonitoreo = Boolean.TRUE;
            setSesionUsuario(CONSULTA_API_SIN_SESSION);
        }
    }

    /**
     * Gets the header.
     *
     * @return the header
     */
    private String getHeader() {
        int i = 0;
        StringBuilder header = new StringBuilder();

        while (i < this.iatxHeader.size()) {

            header.append((String) this.iatxHeader.elementAt(i));
            i++;
        }

        return header.toString();
    }

    /**
     * Gets the body.
     *
     * @return the body
     */
    private String getBody() {
        int i = 0;
        StringBuilder body = new StringBuilder();

        while (i < this.iatxBody.size()) {

            body.append((String) this.iatxBody.elementAt(i) + delimitadorCampos);
            i++;
        }

        return body.toString();
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public String getData() {
        this.buildHeader();

        String body = this.getBody();
        String header = this.getHeader();
        String bodyLen = formatNum(body.length(), BODY_SIZE_5);

        return (header + bodyLen + body);
    }

    /**
     * Builds the header.
     */
    private void buildHeader() {
        this.iatxHeader = new Vector<String>();

        fechaHoraRequerimiento = getTimeStamp();
        if (this.apiMonitoreo) {
            setNroRequerimiento("0");
        } else {
            setNroRequerimiento(SessionUsuarioProvider.getNroReq());
        }
        // completa el vector datos header
        this.iatxHeader.addElement(version);
        this.iatxHeader.addElement(tipoSeguridad);
        this.iatxHeader.addElement(tipoCompresion);
        this.iatxHeader.addElement(nroMjeMultiple);
        this.iatxHeader.addElement(accionMjeMultiple);
        this.iatxHeader.addElement(requestReplay);
        this.iatxHeader.addElement(canalTipo);
        this.iatxHeader.addElement(canalId);
        this.iatxHeader.addElement(canalVersion);
        this.iatxHeader.addElement(subCanalTipo);
        this.iatxHeader.addElement(subCanalId);
        this.iatxHeader.addElement(usuarioRacfTipo);
        this.iatxHeader.addElement(usuarioRacfId);
        this.iatxHeader.addElement(usuarioRacfAtributos);
        this.iatxHeader.addElement(usuarioRacfPwd);
        this.iatxHeader.addElement(sesionUsuario);
        this.iatxHeader.addElement(nroRequerimiento);
        this.iatxHeader.addElement(fechaHoraRequerimiento);
        this.iatxHeader.addElement(nroOperCanal);
        this.iatxHeader.addElement(idSesionTransaccional);
        this.iatxHeader.addElement(idRegistroOper);
        this.iatxHeader.addElement(nombreServicio);
        this.iatxHeader.addElement(versionServicio);
        this.iatxHeader.addElement(tipoOper);
        this.iatxHeader.addElement(tipoEjecucion);
        this.iatxHeader.addElement(tipoValidacion);
        this.iatxHeader.addElement(tipoRespuesta);
        this.iatxHeader.addElement(fechaHoraEjecDiferida);
        this.iatxHeader.addElement(nup);
        this.iatxHeader.addElement(statFlag);
        this.iatxHeader.addElement(fillerNup);
        this.iatxHeader.addElement(autorizacionRequerida);
        this.iatxHeader.addElement(indSincronizacion);
        this.iatxHeader.addElement(idSincronizacion);
        this.iatxHeader.addElement(indOperReversa);
        this.iatxHeader.addElement(indSinonimo);
        this.iatxHeader.addElement(indNoUtilizadosV200);
        this.iatxHeader.addElement(tipoCliente);
        this.iatxHeader.addElement(tipoIdCliente);
        this.iatxHeader.addElement(idCliente);
        this.iatxHeader.addElement(fechaNacCliente);
        this.iatxHeader.addElement(pinCliente);
        this.iatxHeader.addElement(indAutenticacion);
        this.iatxHeader.addElement(idSesionCliente);
        this.iatxHeader.addElement(funcionAutorizacion);
        this.iatxHeader.addElement(idAutorizacion);
        this.iatxHeader.addElement(readLockTimeStamp);
        this.iatxHeader.addElement(estadoAutorizacion);
        this.iatxHeader.addElement(indAutorizacion);
        this.iatxHeader.addElement(cantAutorizantes);

        // autorizante #1
        this.iatxHeader.addElement(tipoAutorizante);
        this.iatxHeader.addElement(idAutorizante);
        this.iatxHeader.addElement(pinAutorizante);
        this.iatxHeader.addElement(estadoAutorizante);
        // autorizante #2
        this.iatxHeader.addElement(tipoAutorizante);
        this.iatxHeader.addElement(idAutorizante);
        this.iatxHeader.addElement(pinAutorizante);
        this.iatxHeader.addElement(estadoAutorizante);
        // autorizante #3
        this.iatxHeader.addElement(tipoAutorizante);
        this.iatxHeader.addElement(idAutorizante);
        this.iatxHeader.addElement(pinAutorizante);
        this.iatxHeader.addElement(estadoAutorizante);
        // autorizante #4
        this.iatxHeader.addElement(tipoAutorizante);
        this.iatxHeader.addElement(idAutorizante);
        this.iatxHeader.addElement(pinAutorizante);
        this.iatxHeader.addElement(estadoAutorizante);
        // autorizante #5
        this.iatxHeader.addElement(tipoAutorizante);
        this.iatxHeader.addElement(idAutorizante);
        this.iatxHeader.addElement(pinAutorizante);
        this.iatxHeader.addElement(estadoAutorizante);

        this.iatxHeader.addElement(formatoParams);
        this.iatxHeader.addElement(delimitadorCampos);
    }

    /**
     * Adds the body value.
     *
     * @param fieldValue
     *            the field value
     */
    public void addBodyValue(String fieldValue) {
        this.iatxBody.addElement(fieldValue);
    }

    /**
     * Format num.
     *
     * @param i
     *            the i
     * @param size
     *            the size
     * @return the string
     */
    private String formatNum(int i, int size) {
        String s = String.valueOf(i);
        int l = s.length();
        if (l >= size) {
            return s.substring(l - size, l);
        }
        StringBuilder ceros = new StringBuilder();
        for (int n = 0; n < size - l; ++n) {
            ceros.append("0");
        }
        return ceros + s;
    }

    /**
     * Gets the time stamp.
     *
     * @return the time stamp
     */
    private String getTimeStamp() {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        return formatNum(calendar.get(Calendar.YEAR), CALENDAR_SIZE_4) + formatNum(calendar.get(Calendar.MONTH) + 1, 2)
                + formatNum(calendar.get(Calendar.DATE), 2) + formatNum(calendar.get(Calendar.HOUR_OF_DAY), 2)
                + formatNum(calendar.get(Calendar.MINUTE), 2) + formatNum(calendar.get(Calendar.SECOND), 2);
    }

    /**
     * Setter para usuario racf tipo.
     *
     * @param value
     *            el nuevo usuario racf tipo
     */
    private void setUsuarioRacfTipo(String value) {
        usuarioRacfTipo = FormatFep.fillNum(value, USUARIO_RACF_TIPO_SIZE);
    }

    /**
     * Setter para usuario racf id.
     *
     * @param value
     *            el nuevo usuario racf id
     */
    public void setUsuarioRacfId(String value) {
        usuarioRacfId = FormatFep.fillStr(value, USUARIO_RACF_ID_SIZE);
    }

    /**
     * Setter para usuario racf pwd.
     *
     * @param value
     *            el nuevo usuario racf pwd
     */
    public void setUsuarioRacfPwd(String value) {
        usuarioRacfPwd = FormatFep.fillStr(value, USUARIO_RACF_PWD_SIZE);
    }

    /**
     * Setter para sesion usuario.
     *
     * @param value
     *            el nuevo sesion usuario
     */
    // TODO IMPLEMENTAR
    private void setSesionUsuario(String value) {
        sesionUsuario = FormatFep.fillNum(value, SESION_USUARIO_SIZE);
    }

    /**
     * Setter para nro requerimiento.
     *
     * @param value
     *            el nuevo nro requerimiento
     */
    // TODO IMPLEMENTAR
    private void setNroRequerimiento(String value) {
        nroRequerimiento = FormatFep.fillNum(value, NRO_REQUERIMIENTO_SIZE);
    }

    /**
     * Define tipo, id y pw iniciales.
     */
    public void setRacfInicial() {
        ApplicationContext ctx = ContextHolder.getContext();
        IatxInicial iatxInicial = ctx.getBean(IatxInicial.class);
        setUsuarioRacfId(iatxInicial.getRacfInicialId());
        setUsuarioRacfPwd(iatxInicial.getRacfInicialPwd());
        setUsuarioRacfTipo(ResumenCliente.RACF_TIPO_INICIAL);
    }

    /**
     * Setter para nombre servicio.
     *
     * @param value
     *            el nuevo nombre servicio
     */
    public void setNombreServicio(String value) {
        nombreServicio = FormatFep.fillStr(value, NOMBRE_SERVICIO_SIZE);
    }

    /**
     * Setter para version servicio.
     *
     * @param value
     *            el nuevo version servicio
     */
    public void setVersionServicio(String value) {
        versionServicio = FormatFep.fillNum(value, VERSION_SERVICIO_SIZE);
    }

    /**
     * Setter para tipo validacion.
     *
     * @param value
     *            el nuevo tipo validacion
     */
    void setTipoValidacion(String value) {
        tipoValidacion = FormatFep.fillNum(value, TIPO_VALIDACION_SIZE);
    }

    /**
     * Setter para nup.
     *
     * @param value
     *            el nuevo nup
     */
    void setNup(String value) {
        nup = FormatFep.fillNum(value, NUP_SIZE);
    }

    /**
     * Setter para ind oper reversa.
     *
     * @param value
     *            el nuevo ind oper reversa
     */
    public void setIndOperReversa(String value) {
        indOperReversa = FormatFep.fillNum(value, IND_OPER_REVERSA_SIZE);
    }

    /**
     * Setter para ind sinonimo.
     *
     * @param value
     *            el nuevo ind sinonimo
     */
    void setIndSinonimo(String value) {
        indSinonimo = FormatFep.fillNum(value, IND_SINONIMO_SIZE);
    }

    /**
     * Setter para tipo id cliente.
     *
     * @param value
     *            el nuevo tipo id cliente
     */
    void setTipoIdCliente(String value) {
        tipoIdCliente = FormatFep.fillStr(value, TIPO_ID_CLIENTE_SIZE);
    }

    /**
     * Setter para id cliente.
     *
     * @param value
     *            el nuevo id cliente
     */
    public void setIdCliente(String value) {
        idCliente = FormatFep.fillNum(value, ID_CLIENTE_SIZE);
    }

    /**
     * Setter para fecha nac cliente.
     *
     * @param value
     *            el nuevo fecha nac cliente
     */
    void setFechaNacCliente(String value) {
        fechaNacCliente = FormatFep.fillNum(value, FECHA_NAC_CLIENTE_SIZE);
    }

    /**
     * Sets the ind autenticacion.
     */
    public void setIndAutenticacion() {
        indAutenticacion = IND_AUTENTICACION;
    }

    /**
     * Gets the nombre servicio.
     *
     * @return the nombre servicio
     */
    public String getNombreServicio() {
        return nombreServicio;
    }

    /**
     * Gets the version servicio.
     *
     * @return the version servicio
     */
    public String getVersionServicio() {
        return versionServicio;
    }

    /**
     * Setter para nro mje multiple.
     *
     * @param nroMjeMultiple
     *            the nroMjeMultiple to set
     */
    public void setNroMjeMultiple(String nroMjeMultiple) {
        this.nroMjeMultiple = nroMjeMultiple;
    }

    /**
     * Getter para nro mje multiple.
     *
     * @return nroMjeMultiple
     */
    public String getNroMjeMultiple() {
        return nroMjeMultiple;
    }

    /**
     * Gets the n req censurar.
     *
     * @return the n req censurar
     */
    public int getNReqCensurar() {
        return nReqCensurar;
    }

    /**
     * Setter para n req censurar.
     *
     * @param reqCensurar
     *            el nuevo n req censurar
     */
    public void setNReqCensurar(int reqCensurar) {
        nReqCensurar = reqCensurar;
    }

    /**
     * Gets the n res censurar.
     *
     * @return the n res censurar
     */
    public int getNResCensurar() {
        return nResCensurar;
    }

    /**
     * Setter para n res censurar.
     *
     * @param resCensurar
     *            el nuevo n res censurar
     */
    public void setNResCensurar(int resCensurar) {
        nResCensurar = resCensurar;
    }

    /**
     * Gets the sesion usuario.
     *
     * @return the sesion usuario
     */
    public String getSesionUsuario() {
        return sesionUsuario;
    }

    /**
     * Gets the fecha hora req.
     *
     * @return the fecha hora req
     */
    public String getFechaHoraReq() {
        return fechaHoraRequerimiento;
    }

    /**
     * Gets the stat flag.
     *
     * @return the stat flag
     */
    public String getStatFlag() {
        return statFlag;
    }

    /**
     * Setter para stat flag.
     *
     * @param value
     *            el nuevo stat flag
     */
    public void setStatFlag(String value) {
        this.statFlag = FormatFep.fillStr(value, STAT_FLAG_SIZE);
    }

    /**
     * Gets the accion mje multiple.
     *
     * @return the accion mje multiple
     */
    public String getAccionMjeMultiple() {
        return accionMjeMultiple;
    }

    /**
     * Setter para accion mje multiple.
     *
     * @param accionMjeMultiple
     *            el nuevo accion mje multiple
     */
    public void setAccionMjeMultiple(String accionMjeMultiple) {
        this.accionMjeMultiple = accionMjeMultiple;
    }

    /**
     * Checks if is no log request.
     *
     * @return the noLogRequest
     */
    public boolean isNoLogRequest() {
        return noLogRequest;
    }

    /**
     * Setter para no log request.
     *
     * @param noLogRequest
     *            the noLogRequest to set
     */
    public void setNoLogRequest(boolean noLogRequest) {
        this.noLogRequest = noLogRequest;
    }

    /**
     * Checks if is no log response.
     *
     * @return the noLogResponse
     */
    public boolean isNoLogResponse() {
        return noLogResponse;
    }

    /**
     * Setter para no log response.
     *
     * @param noLogResponse
     *            the noLogResponse to set
     */
    public void setNoLogResponse(boolean noLogResponse) {
        this.noLogResponse = noLogResponse;
    }

    /**
     * Setter para id sesion transaccional.
     *
     * @param idSesionTransaccional
     *            the idSesionTransaccional to set
     */
    public void setIdSesionTransaccional(String idSesionTransaccional) {
        this.idSesionTransaccional = idSesionTransaccional;
    }

}
