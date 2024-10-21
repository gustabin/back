/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.common;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * The Class BilleteraConstants.
 */
public final class BilleteraConstants {

    /** The Constant COD_ERR_0. */
    public static final String COD_ERR_0 = "0";

    /** The Constant COD_ERR_1. */
    public static final String COD_ERR_1 = "1";

    /** The Constant COD_ERR_2. */
    public static final String COD_ERR_2 = "2";

    /** The Constant COD_RET_0. */
    public static final int COD_RET_0 = 0;

    /** The Constant COD_RET_1. */
    public static final int COD_RET_1 = 1;

    /** The Constant COD_RET_2. */
    public static final int COD_RET_2 = 2;

    /** The Constant N. */
    public static final String N = "N";

    /** The Constant S. */
    public static final String S = "S";

    /** The Constant VIEW_ADH. */
    public static final String VIEW_ADH = "ADH";

    /** The Constant VIEW_CLV. */
    public static final String VIEW_CLV = "CLV";

    /** The Constant VIEW_CNS. */
    public static final String VIEW_CNS = "CNS";

    /** The Constant CONFIG_BILLETERA_CANAL. */
    public static final String CONFIG_BILLETERA_CANAL = "BILLETERA.CANAL";

    /** The Constant CONFIG_BILLETERA_IDBANCO. */
    public static final String CONFIG_BILLETERA_IDBANCO = "BILLETERA.IDBANCO";

    /** The Constant CONFIG_BILLETERA_MAIL_BCC. */
    public static final String CONFIG_BILLETERA_MAIL_BCC = "BILLETERA.MAIL.BCC";

    /** The Constant CONFIG_BILLETERA_MAIL_FROM. */
    public static final String CONFIG_BILLETERA_MAIL_FROM = "BILLETERA.MAIL.FROM";

    /** The Constant CONFIG_BILLETERA_MAIL_TO. */
    public static final String CONFIG_BILLETERA_MAIL_TO = "BILLETERA.MAIL.TO";

    /** The Constant CONFIG_BILLETERA_MAIL_SUBJECT. */
    public static final String CONFIG_BILLETERA_MAIL_SUBJECT = "BILLETERA.MAIL.SUBJECT";

    /** The Constant CONFIG_BILLETERA_TIPOACRED. */
    public static final String CONFIG_BILLETERA_TIPOACRED = "BILLETERA.TIPOACRED";

    /** The Constant CONFIG_BILLETERA_TIPOCUENTA. */
    public static final String CONFIG_BILLETERA_TIPOCUENTA = "BILLETERA.TIPOCUENTA";

    /** The Constant CONFIG_BILLETERA_TIPOSCTA. */
    public static final String CONFIG_BILLETERA_TIPOSCTA = "BILLETERA.TIPOSCTA";

    /** The Constant CONFIG_BILLETERA_TIPOSTRJ. */
    public static final String CONFIG_BILLETERA_TIPOSTRJ = "BILLETERA.TIPOSTRJ";

    /** The Constant DOMICILIO_FACTURACION. */
    public static final int DOMICILIO_FACTURACION = 1;

    /** The Constant DOMICILIO_LEGAL. */
    public static final int DOMICILIO_LEGAL = 0;

    /** The Constant EMP_CEL_CLARO. */
    public static final String EMP_CEL_CLARO = "CTI ";

    /** The Constant EMP_CEL_CLARO_WS. */
    public static final String EMP_CEL_CLARO_WS = "2";

    /** The Constant EMP_CEL_MOVI. */
    public static final String EMP_CEL_MOVI = "MOVI";

    /** The Constant EMP_CEL_MOVI_WS. */
    public static final String EMP_CEL_MOVI_WS = "1";

    /** The Constant EMP_CEL_NEXT. */
    public static final String EMP_CEL_NEXT = "NEXT";

    /** The Constant EMP_CEL_NEXT_WS. */
    public static final String EMP_CEL_NEXT_WS = "4";

    /** The Constant EMP_CEL_PERS. */
    public static final String EMP_CEL_PERS = "PERS";

    /** The Constant EMP_CEL_PERS_WS. */
    public static final String EMP_CEL_PERS_WS = "3";

    /** The Constant ESTADO_ACTIVA. */
    public static final String ESTADO_ACTIVA = "20";

    /** The Constant ESTADO_ACTIVA_BANELCO. */
    public static final String ESTADO_ACTIVA_BANELCO = "01";

    /** The Constant ESTADO_NORENOVAR. */
    public static final String ESTADO_NORENOVAR = "22";

    /** The Constant ESTADO_PROBLEMA. */
    public static final String ESTADO_PROBLEMA = "25";

    /** The Constant ESTADO_CERRADA. */
    public static final String ESTADO_CERRADA = "29";

    /** The Constant ESTADOMAIL_CONFIRMADO. */
    public static final String ESTADOMAIL_CONFIRMADO = "1";

    /** The Constant ESTADOMAIL_PENDIENTE. */
    public static final String ESTADOMAIL_PENDIENTE = "0";

    /** The Constant ESTCODE_ADHESION. */
    public static final String ESTCODE_ADHESION = "1414";

    /** The Constant ESTCODE_INICIO_PAGOS. */
    public static final String ESTCODE_INICIO_PAGOS = "1412";

    /** The Constant ESTCODE_MODIFICACION. */
    public static final String ESTCODE_MODIFICACION = "1422";

    /** The Constant EXISTE_USUARIO_BLOQ. */
    public static final String EXISTE_USUARIO_BLOQ = "B";

    /** The Constant EXISTE_USUARIO_NO. */
    public static final String EXISTE_USUARIO_NO = "N";

    /** The Constant EXISTE_USUARIO_SI. */
    public static final String EXISTE_USUARIO_SI = "S";

    /** The Constant MARCA_CBU_BANCO. */
    public static final String MARCA_CBU_BANCO = "B";

    /** The Constant MARCA_CBU_NO. */
    public static final String MARCA_CBU_NO = "N";

    /** The Constant MARCA_CBU_OTROBANCO. */
    public static final String MARCA_CBU_OTROBANCO = "O";

    /** The Constant MASK_XXXXX. */
    public static final String MASK_XXXXX = "xxxxxx-";

    /** The Constant MASK_XXXX. */
    public static final String MASK_XXXX = "xxxx-";

    /** The Constant MASK_FVTO. */
    public static final String MASK_FVTO = "XX/XXXX";

    /** The Constant MODE_ACTMYA. */
    public static final String MODE_ACTMYA = "0";

    /** The Constant MODE_CONT. */
    public static final String MODE_CONT = "1";

    /** The Constant MODE_CONT_TRJ. */
    public static final String MODE_CONT_TRJ = "2";

    /** The Constant MODE_ADH. */
    public static final String MODE_ADH = "ADH";

    /** The Constant MODE_ALTA. */
    public static final String MODE_ALTA = "A";

    /** The Constant MODE_MOD. */
    public static final String MODE_MOD = "M";

    /** The Constant MODE_ADMCLAVES_LOGIN. */
    public static final String MODE_ADMCLAVES_LOGIN = "0";

    /** The Constant MODE_ADMCLAVES_REC. */
    public static final String MODE_ADMCLAVES_REC = "1";

    /** The Constant LEN_AMEX. */
    public static final int LEN_AMEX = 15;

    /** The Constant LEN_VISA. */
    public static final int LEN_VISA = 16;

    /** The Constant OFF_AMEX. */
    public static final int OFF_AMEX = 5;

    /** The Constant OFF_VISA. */
    public static final int OFF_VISA = 4;

    /** The Constant PARAM_ACEPTA_TYC. */
    public static final String PARAM_ACEPTA_TYC = "aceptaTyC";

    /** The Constant PARAM_ACTIVIDAD. */
    public static final String PARAM_ACTIVIDAD = "actividad";

    /** The Constant PARAM_APELLIDO. */
    public static final String PARAM_APELLIDO = "apellido";

    /** The Constant PARAM_CALLE. */
    public static final String PARAM_CALLE = "calle";

    /** The Constant PARAM_CANAL. */
    public static final String PARAM_CANAL = "canal";

    /** The Constant PARAM_CBU. */
    public static final String PARAM_CBU = "cbu";

    /** The Constant PARAM_COD_POSTAL. */
    public static final String PARAM_COD_POSTAL = "codPostal";

    /** The Constant PARAM_COND_IVA. */
    public static final String PARAM_COND_IVA = "condicionIVA";

    /** The Constant PARAM_COND_IIBB. */
    public static final String PARAM_COND_IIBB = "condicionIIBB";

    /** The Constant PARAM_NRO_IIBB. */
    public static final String PARAM_NRO_IIBB = "nroIIBB";

    /** The Constant PARAM_CONTRASENIA. */
    public static final String PARAM_CONTRASENIA = "contrasenia";

    /** The Constant PARAM_CUIT. */
    public static final String PARAM_CUIT = "cuit";

    /** The Constant PARAM_DEPTO. */
    public static final String PARAM_DEPTO = "depto";

    /** The Constant PARAM_EMP_CEL. */
    public static final String PARAM_EMP_CEL = "companiaCelular";

    /** The Constant PARAM_FACTOR_VALIDACION. */
    public static final String PARAM_FACTOR_VALIDACION = "factorValidacion";

    /** The Constant PARAM_FAVORITO. */
    public static final String PARAM_FAVORITO = "favorito";

    /** The Constant PARAM_FECHA_NAC. */
    public static final String PARAM_FECHA_NAC = "fechaNac";

    /** The Constant PARAM_FECHA_VENC. */
    public static final String PARAM_FECHA_VENC = "fechaVenc";

    /** The Constant PARAM_GENERO. */
    public static final String PARAM_GENERO = "genero";

    /** The Constant PARAM_ID_BANCO. */
    public static final String PARAM_ID_BANCO = "idBanco";

    /** The Constant PARAM_ID_CUENTA. */
    public static final String PARAM_ID_CUENTA = "idCuenta";

    /** The Constant PARAM_ID_MEDIOPAGO. */
    public static final String PARAM_ID_MEDIOPAGO = "idMedioPago";

    /** The Constant PARAM_LOCALIDAD. */
    public static final String PARAM_LOCALIDAD = "localidad";

    /** The Constant PARAM_MAIL. */
    public static final String PARAM_MAIL = "mail";

    /** The Constant PARAM_MAIL_OK. */
    public static final String PARAM_MAIL_OK = "mailOk";

    /** The Constant PARAM_MONEDA_CUENTA. */
    public static final String PARAM_MONEDA_CUENTA = "monedaCuenta";

    /** The Constant PARAM_NACIONALIDAD. */
    public static final String PARAM_NACIONALIDAD = "nacionalidad";

    /** The Constant PARAM_NOMBRE. */
    public static final String PARAM_NOMBRE = "nombre";

    /** The Constant PARAM_NRO_CUENTA. */
    public static final String PARAM_NRO_CUENTA = "nroCuenta";

    /** The Constant PARAM_NRO_DOC. */
    public static final String PARAM_NRO_DOC = "nroDoc";

    /** The Constant PARAM_NRO_TARJETA. */
    public static final String PARAM_NRO_TARJETA = "nroTarjeta";

    /** The Constant PARAM_NUM_CALLE. */
    public static final String PARAM_NUM_CALLE = "numCalle";

    /** The Constant PARAM_NUM_CEL. */
    public static final String PARAM_NUM_CEL = "numeroCelular";

    /** The Constant PARAM_PAIS_ORIGEN. */
    public static final String PARAM_PAIS_ORIGEN = "paisOrigen";

    /** The Constant PARAM_PISO. */
    public static final String PARAM_PISO = "piso";

    /** The Constant PARAM_PREG_SEG. */
    public static final String PARAM_PREG_SEG = "pregSeg";

    /** The Constant PARAM_PROVINCIA. */
    public static final String PARAM_PROVINCIA = "provincia";

    /** The Constant PARAM_RESP_SEG. */
    public static final String PARAM_RESP_SEG = "respSeg";

    /** The Constant PARAM_SEXO. */
    public static final String PARAM_SEXO = "sexo";

    /** The Constant PARAM_TEL. */
    public static final String PARAM_TEL = "telefonoFijo";

    /** The Constant PARAM_TIPO_ACREDITACION. */
    public static final String PARAM_TIPO_ACREDITACION = "tipoAcreditacion";

    /** The Constant PARAM_TIPO_CUENTA. */
    public static final String PARAM_TIPO_CUENTA = "tipoCuenta";

    /** The Constant PARAM_TIPO_DOC. */
    public static final String PARAM_TIPO_DOC = "tipoDoc";

    /** The Constant PARAM_TIPO_NOVEDAD. */
    public static final String PARAM_TIPO_NOVEDAD = "tipoNovedad";

    /** The Constant PATTERN_CLAVE_TP. */
    public static final String PATTERN_CLAVE_TP = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$";

    /** The Constant PATTERN_RESP_SEG_TP. */
    public static final String PATTERN_RESP_SEG_TP = "^[A-Za-záéíóúñÁÉÍÓÚÑ1234567890\\s]+$";

    /** The Constant PRIMERA_VEZ_NO. */
    public static final String PRIMERA_VEZ_NO = N;

    /** The Constant PRIMERA_VEZ_SI. */
    public static final String PRIMERA_VEZ_SI = S;

    /** The Constant REQPAR_ACTIVIDAD. */
    public static final String REQPAR_ACTIVIDAD = "actividad";

    /** The Constant REQPAR_ADH. */
    public static final String REQPAR_ADH = "adh";

    /** The Constant REQPAR_ADHIRIOMYA. */
    public static final String REQPAR_ADHIRIOMYA = "adhirioMyA";

    /** The Constant REQPAR_APCONTACTO. */
    public static final String REQPAR_APCONTACTO = "apellidoContacto";

    /** The Constant REQPAR_BACK. */
    public static final String REQPAR_BACK = "back";

    /** The Constant REQPAR_CALLE. */
    public static final String REQPAR_CALLE = "calle";

    /** The Constant REQPAR_CLAVETP. */
    public static final String REQPAR_CLAVETP = "claveTp";

    /** The Constant REQPAR_CLAVETP_CONFIRM. */
    public static final String REQPAR_CLAVETP_CONFIRM = "claveTpConfirm";

    /** The Constant REQPAR_CLAVETP_REC. */
    public static final String REQPAR_CLAVETP_REC = "claveTpRec";

    /** The Constant REQPAR_CLAVETP_REC_CONFIRM. */
    public static final String REQPAR_CLAVETP_REC_CONFIRM = "claveTpRecConfirm";

    /** The Constant REQPAR_CODAREA. */
    public static final String REQPAR_CODAREA = "codArea1";

    /** The Constant REQPAR_CODAREA2. */
    public static final String REQPAR_CODAREA2 = "codArea2";

    /** The Constant REQPAR_CODPOSTAL. */
    public static final String REQPAR_CODPOSTAL = "codpostal";

    /** The Constant REQPAR_COND_IIBB. */
    public static final String REQPAR_COND_IIBB = "condicionIIBB";

    /** The Constant REQPAR_NRO_IIBB. */
    public static final String REQPAR_NRO_IIBB = "nroIIBB";

    /** The Constant REQPAR_COND_IVA. */
    public static final String REQPAR_COND_IVA = "condicionIVA";

    /** The Constant REQPAR_CTA. */
    public static final String REQPAR_CTA = "cta";

    /** The Constant REQPAR_CTA_INI. */
    public static final String REQPAR_CTA_INI = "ctaIni";

    /** The Constant REQPAR_CUITCUIL. */
    public static final String REQPAR_CUITCUIL = "cuitcuil";

    /** The Constant REQPAR_DTO. */
    public static final String REQPAR_DTO = "dto";

    /** The Constant REQPAR_EMPCEL. */
    public static final String REQPAR_EMPCEL = "empCel1";

    /** The Constant REQPAR_ERR. */
    public static final String REQPAR_ERR = "err";

    /** The Constant REQPAR_ERRIMPACTOMYA. */
    public static final String REQPAR_ERRIMPACTOMYA = "errImpactoMyA";

    /** The Constant REQPAR_IDX_CTATP. */
    public static final String REQPAR_IDX_CTATP = "idxCtaTp";

    /** The Constant REQPAR_INIT. */
    public static final String REQPAR_INIT = "init";

    /** The Constant REQPAR_LOCALIDAD. */
    public static final String REQPAR_LOCALIDAD = "localidad";

    /** The Constant REQPAR_MAIL. */
    public static final String REQPAR_MAIL = "mail1";

    /** The Constant REQPAR_MAIL_CONFIRM. */
    public static final String REQPAR_MAIL_CONFIRM = "mail1Confirm";

    /** The Constant REQPAR_MODE. */
    public static final String REQPAR_MODE = "mode";

    /** The Constant REQPAR_NACIONALIDADES. */
    public static final String REQPAR_NACIONALIDADES = "Nacionalidades";

    /** The Constant REQPAR_NOMBRECONTACTO. */
    public static final String REQPAR_NOMBRECONTACTO = "nombreContacto";

    /** The Constant REQPAR_NRO. */
    public static final String REQPAR_NRO = "nro";

    /** The Constant REQPAR_NROCEL. */
    public static final String REQPAR_NROCEL = "nroCel1";

    /** The Constant REQPAR_NROTRJ_PPAL. */
    public static final String REQPAR_NROTRJ_PPAL = "principalTrj";

    /** The Constant REQPAR_PISO. */
    public static final String REQPAR_PISO = "piso";

    /** The Constant REQPAR_PREG_SEG. */
    public static final String REQPAR_PREG_SEG = "prgSeguridad";

    /** The Constant REQPAR_PRINCIPAL. */
    public static final String REQPAR_PRINCIPAL = "principal";

    /** The Constant REQPAR_PROVINCIAS. */
    public static final String REQPAR_PROVINCIAS = "provincias";

    /** The Constant REQPAR_BARRIOSCAP. */
    public static final String REQPAR_BARRIOSCAP = "barriosCapital";

    /** The Constant REQPAR_RESP_SEG. */
    public static final String REQPAR_RESP_SEG = "rtaSeguridad";

    /** The Constant REQPAR_SEXOCOMPLETO. */
    public static final String REQPAR_SEXOCOMPLETO = "sexoCompleto";

    /** The Constant REQPAR_SHOWRECUPERO. */
    public static final String REQPAR_SHOWRECUPERO = "showRecupero";

    /** The Constant REQPAR_TELEFONO. */
    public static final String REQPAR_TELEFONO = "telefono";

    /** The Constant REQPAR_TIPO_NOV. */
    public static final String REQPAR_TIPO_NOV = "tipoNov";

    /** The Constant REQPAR_TYC. */
    public static final String REQPAR_TYC = "tyc";

    /** The Constant REQPAR_VINCULAR. */
    public static final String REQPAR_VINCULAR = "vincular";

    /** The Constant STATUS_00000. */
    public static final String STATUS_00000 = "00000";

    /** The Constant STATUS_1000. */
    public static final String STATUS_1000 = "1000";

    /** The Constant STATUS_1001. */
    public static final String STATUS_1001 = "1001";

    /** The Constant STATUS_1003. */
    public static final String STATUS_1003 = "1003";

    /** The Constant STATUS_1009. */
    public static final String STATUS_1009 = "1009";

    /** The Constant STATUS_1010. */
    public static final String STATUS_1010 = "1010";

    /** The Constant STATUS_1011. */
    public static final String STATUS_1011 = "1011";

    /** The Constant STATUS_1016. */
    public static final String STATUS_1016 = "1016";

    /** The Constant STATUS_1019. */
    public static final String STATUS_1019 = "1019";

    /** The Constant STATUS_1021. */
    public static final String STATUS_1021 = "1021";

    /** The Constant STATUS_1023. */
    public static final String STATUS_1023 = "1023";

    /** The Constant STATUS_1028. */
    public static final String STATUS_1028 = "1028";

    /** The Constant STATUS_1040. */
    public static final String STATUS_1040 = "1040";

    /** The Constant STATUS_1042. */
    public static final String STATUS_1042 = "1042";

    /** The Constant STATUS_1043. */
    public static final String STATUS_1043 = "1043";

    /** The Constant STATUS_1044. */
    public static final String STATUS_1044 = "1044";

    /** The Constant STATUS_1045. */
    public static final String STATUS_1045 = "1045";

    /** The Constant STATUS_1046. */
    public static final String STATUS_1046 = "1046";

    /** The Constant STATUS_1055. */
    public static final String STATUS_1055 = "1055";

    /** The Constant STATUS_1060. */
    public static final String STATUS_1060 = "1060";

    /** The Constant STATUS_1061. */
    public static final String STATUS_1061 = "1061";

    /** The Constant STATUS_1062. */
    public static final String STATUS_1062 = "1062";

    /** The Constant STATUS_1063. */
    public static final String STATUS_1063 = "1063";

    /** The Constant STATUS_1064. */
    public static final String STATUS_1064 = "1064";

    /** The Constant STATUS_1091. */
    public static final String STATUS_1091 = "1091";

    /** The Constant STATUS_1092. */
    public static final String STATUS_1092 = "1092";

    /** The Constant STATUS_1102. */
    public static final String STATUS_1102 = "1102";

    /** The Constant STATUS_107. */
    public static final String STATUS_107 = "107";

    /** The Constant STATUS_9500. */
    public static final String STATUS_9500 = "9500";

    /** The Constant STATUS_9501. */
    public static final String STATUS_9501 = "9501";

    /** The Constant STATUS_9504. */
    public static final String STATUS_9504 = "9504";

    /** The Constant STATUS_9505. */
    public static final String STATUS_9505 = "9505";

    /** The Constant STATUS_9507. */
    public static final String STATUS_9507 = "9507";

    /** The Constant STATUS_9508. */
    public static final String STATUS_9508 = "9508";

    /** The Constant STATUS_9512. */
    public static final String STATUS_9512 = "9512";

    /** The Constant STATUS_9513. */
    public static final String STATUS_9513 = "9513";

    /** The Constant STATUS_9514. */
    public static final String STATUS_9514 = "9514";

    /** The Constant STATUS_9515. */
    public static final String STATUS_9515 = "9515";

    /** The Constant STATUS_9517. */
    public static final String STATUS_9517 = "9517";

    /** The Constant STATUS_9519. */
    public static final String STATUS_9519 = "9519";

    /** The Constant STATUS_9520. */
    public static final String STATUS_9520 = "9520";

    /** The Constant STATUS_9521. */
    public static final String STATUS_9521 = "9521";

    /** The Constant TIPOCTA_CAD. */
    public static final String TIPOCTA_CAD = "04";

    /** The Constant TIPOCTA_CAP. */
    public static final String TIPOCTA_CAP = "01";

    /** The Constant TIPOCTA_CCD. */
    public static final String TIPOCTA_CCD = "03";

    /** The Constant TIPOCTA_CCP. */
    public static final String TIPOCTA_CCP = "00";

    /** The Constant TIPOCTA_CU. */
    public static final String TIPOCTA_CU = "02";

    /** The Constant TIPOCTA_CUD. */
    public static final String TIPOCTA_CUD = "10";

    /** The Constant TIPOCTA_CUP. */
    public static final String TIPOCTA_CUP = "09";

    /** The Constant TIPOCTA_AMEX. */
    public static final String TIPOCTA_AMEX = "42";

    /** The Constant TIPOCTA_DEB. */
    public static final String TIPOCTA_DEB = "05";

    /** The Constant TIPOCTA_VISA. */
    public static final String TIPOCTA_VISA = "07";

    /** The Constant TIPO_NOVEDAD_ALTA. */
    public static final String TIPO_NOVEDAD_ALTA = "A";

    /** The Constant TIPO_NOVEDAD_BAJA. */
    public static final String TIPO_NOVEDAD_BAJA = "B";

    /** The Constant TIPO_NOVEDAD_MOD. */
    public static final String TIPO_NOVEDAD_MOD = "M";

    /** The Constant TIPO_NOVEDAD_REC. */
    public static final String TIPO_NOVEDAD_REC = "R";

    /** The Constant TIPO_NOVEDAD_VER. */
    public static final String TIPO_NOVEDAD_VER = "V";

    /** The Constant TIPO_MP_RECARGABLE. */
    public static final String TIPO_MP_RECARGABLE = "RECARGABLE";

    /** The Constant TIPO_MP_CREDITO. */
    public static final String TIPO_MP_CREDITO = "CREDITO";

    /** The Constant TIPO_MP_DEBITO. */
    public static final String TIPO_MP_DEBITO = "DEBITO";

    /** The Constant TRJ_CRED. */
    public static final String TRJ_CRED = "CREDITO";

    /** The Constant TRJ_DEB. */
    public static final String TRJ_DEB = "DEBITO";

    /** The Constant TRJ_AMEX. */
    public static final String TRJ_AMEX = "AMEX";

    /** The Constant TRJ_VISA. */
    public static final String TRJ_VISA = "VISA";

    /** The Constant TRJ_VISA_REC. */
    public static final String TRJ_VISA_REC = "TARJETA RECARGABLE";

    /** The Constant TRJ_MARCA_AMEX. */
    public static final String TRJ_MARCA_AMEX = "AMEX";

    /** The Constant TRJ_MARCA_CABAL. */
    public static final String TRJ_MARCA_CABAL = "CABAL";

    /** The Constant TRJ_MARCA_CABAL24. */
    public static final String TRJ_MARCA_CABAL24 = "CABAL24";

    /** The Constant TRJ_MARCA_DEBITO. */
    public static final String TRJ_MARCA_DEBITO = TRJ_DEB;

    /** The Constant TRJ_MARCA_DINERS. */
    public static final String TRJ_MARCA_DINERS = "DINERS";

    /** The Constant TRJ_MARCA_MAESTRO. */
    public static final String TRJ_MARCA_MAESTRO = "MAESTRO";

    /** The Constant TRJ_MARCA_MASTER. */
    public static final String TRJ_MARCA_MASTER = "MASTERCARD";

    /** The Constant TRJ_MARCA_RECARGABLE. */
    public static final String TRJ_MARCA_RECARGABLE = "TARJETA RECARGABLE";

    /** The Constant TRJ_MARCA_VISA. */
    public static final String TRJ_MARCA_VISA = "VISA";

    /** The Constant TRJ_MARCA_VISA_DEBITO. */
    public static final String TRJ_MARCA_VISA_DEBITO = TRJ_DEB;

    /** The Constant EDAD_MINIMA. */
    public static final int EDAD_MINIMA = 18;

    /** The Constant IMPACT_MYA_CONFIRMAR_ACTUALIZACION. */
    public static final int IMPACT_MYA_CONFIRMAR_ACTUALIZACION = 1;

    /** The Constant IMPACT_MYA_CONTINUAR. */
    public static final int IMPACT_MYA_CONTINUAR = 0;

    /** The Constant NRODOC_LEN. */
    public static final int NRODOC_LEN = 12;

    /** The Constant MAXLEN_ACTIVIDAD. */
    public static final int MAXLEN_ACTIVIDAD = 40;

    /** The Constant MAXLEN_CALLE. */
    public static final int MAXLEN_CALLE = 50;

    /** The Constant MAXLEN_CEL_TP. */
    public static final int MAXLEN_CEL_TP = 11;

    /** The Constant MAXLEN_CODCEL. */
    public static final int MAXLEN_CODCEL = 4;

    /** The Constant MAXLEN_CODPOSTAL. */
    public static final int MAXLEN_CODPOSTAL = 8;

    /** The Constant MAXLEN_DTO. */
    public static final int MAXLEN_DTO = 5;

    /** The Constant MAXLEN_LOCALIDAD. */
    public static final int MAXLEN_LOCALIDAD = 30;

    /** The Constant MAXLEN_MAIL. */
    public static final int MAXLEN_MAIL = 256;

    /** The Constant MAXLEN_MAIL_TP. */
    public static final int MAXLEN_MAIL_TP = 50;

    /** The Constant MAXLEN_NOMAP. */
    public static final int MAXLEN_NOMAP = 40;

    /** The Constant MAXLEN_NRO. */
    public static final int MAXLEN_NRO = 5;

    /** The Constant MAXLEN_NROCEL. */
    public static final int MAXLEN_NROCEL = 8;

    /** The Constant MAXLEN_PISO. */
    public static final int MAXLEN_PISO = 4;

    /** The Constant MAXLEN_PROV. */
    public static final int MAXLEN_PROV = 20;

    /** The Constant MAXLEN_BARRIO. */
    public static final int MAXLEN_BARRIO = 20;

    /** The Constant MAXLEN_TEL. */
    public static final int MAXLEN_TEL = 21;

    /** The Constant MINLEN_TEL. */
    public static final int MINLEN_TEL = 6;

    /** The Constant ACEPTA_TYC. */
    public static final String ACEPTA_TYC = S;

    /** The Constant COND_IVA_CONSUMIDOR_FINAL. */
    public static final String COND_IVA_CONSUMIDOR_FINAL = "6";

    /** The Constant FACTOR_VALIDACION. */
    public static final String FACTOR_VALIDACION = S;

    /** The Constant FAVORITO_N. */
    public static final String FAVORITO_N = N;

    /** The Constant FAVORITO_S. */
    public static final String FAVORITO_S = S;

    /** The Constant MP_HABILITADO. */
    public static final String MP_HABILITADO = "MP_HABILITADO";

    /** The Constant MP_PEND_HABILITAR. */
    public static final String MP_PEND_HABILITAR = "MP_PEND_HABILITAR";

    /** The Constant MP_VENCIDO. */
    public static final String MP_VENCIDO = "MP_VENCIDO";

    /** The Constant NEWLINE. */
    public static final String NEWLINE = "\n";

    /** The Constant MAILBODY_ACTIVIDAD. */
    public static final String MAILBODY_ACTIVIDAD = "    Actividad:";

    /** The Constant MAILBODY_APELLIDO. */
    public static final String MAILBODY_APELLIDO = "    Apellido:";

    /** The Constant MAILBODY_CBU. */
    public static final String MAILBODY_CBU = "    CBU:";

    /** The Constant MAILBODY_CEL. */
    public static final String MAILBODY_CEL = "    Celular:";

    /** The Constant MAILBODY_CONDICION_IIBB. */
    public static final String MAILBODY_CONDICION_IIBB = "    Condición IIBB:";

    /** The Constant MAILBODY_NUMERO_IIBB. */
    public static final String MAILBODY_NUMERO_IIBB = "    Número IIBB:";

    /** The Constant MAILBODY_CONDICION_IVA. */
    public static final String MAILBODY_CONDICION_IVA = "    Condición IVA:";

    /** The Constant MAILBODY_CONTACTO. */
    public static final String MAILBODY_CONTACTO = "Contacto:";

    /** The Constant MAILBODY_CONTACTO_NOM. */
    public static final String MAILBODY_CONTACTO_NOM = "    Nombre de contacto:";

    /** The Constant MAILBODY_CONTACTO_AP. */
    public static final String MAILBODY_CONTACTO_AP = "    Apellido de contacto:";

    /** The Constant MAILBODY_CTA. */
    public static final String MAILBODY_CTA = "Cuenta para acreditar pagos:";

    /** The Constant MAILBODY_CUIT. */
    public static final String MAILBODY_CUIT = "    CUIT/CUIL/DNI:";

    /** The Constant MAILBODY_CUIT_CUIL. */
    public static final String MAILBODY_CUIT_CUIL = "    CUIT/CUIL:";

    /** The Constant MAILBODY_DATOSCUENTA. */
    public static final String MAILBODY_DATOSCUENTA = "Datos de la cuenta:";

    /** The Constant MAILBODY_DNI. */
    public static final String MAILBODY_DNI = "    DNI:";

    /** The Constant MAILBODY_DOM_CALLE. */
    public static final String MAILBODY_DOM_CALLE = "    Calle:";

    /** The Constant MAILBODY_DOM_NUMERO. */
    public static final String MAILBODY_DOM_NUMERO = "    Número:";

    /** The Constant MAILBODY_DOM_PISO. */
    public static final String MAILBODY_DOM_PISO = "    Piso:";

    /** The Constant MAILBODY_DOM_DTO. */
    public static final String MAILBODY_DOM_DTO = "    Departamento:";

    /** The Constant MAILBODY_DOM_PROVINCIA. */
    public static final String MAILBODY_DOM_PROVINCIA = "    Provincia:";

    /** The Constant MAILBODY_DOM_LOCALIDAD. */
    public static final String MAILBODY_DOM_LOCALIDAD = "    Localidad:";

    /** The Constant MAILBODY_DOM_CODPOSTAL. */
    public static final String MAILBODY_DOM_CODPOSTAL = "    Código Postal:";

    /** The Constant MAILBODY_DOM_TIPO_LEGAL. */
    public static final String MAILBODY_DOM_TIPO_LEGAL = "Domicilio Legal:";

    /** The Constant MAILBODY_DOM_TIPO_FACT. */
    public static final String MAILBODY_DOM_TIPO_FACT = "Domicilio de Facturación:";

    /** The Constant MAILBODY_EMAIL. */
    public static final String MAILBODY_EMAIL = "E-mail:";

    /** The Constant MAILBODY_EMPCEL. */
    public static final String MAILBODY_EMPCEL = "    Compañía de celular:";

    /** The Constant MAILBODY_FECHANAC. */
    public static final String MAILBODY_FECHANAC = "    Fecha de Nacimiento:";

    /** The Constant MAILBODY_NOMBRE. */
    public static final String MAILBODY_NOMBRE = "    Nombre:";

    /** The Constant MAILBODY_NRODOC. */
    public static final String MAILBODY_NRODOC = "    Nro. de documento:";

    /** The Constant MAILBODY_PAIS_ORIGEN. */
    public static final String MAILBODY_PAIS_ORIGEN = "    País de Origen:";

    /** The Constant MAILBODY_RAZON_SOCIAL. */
    public static final String MAILBODY_RAZON_SOCIAL = "    Razón social:";

    /** The Constant MAILBODY_SEXO. */
    public static final String MAILBODY_SEXO = "    Sexo:";

    /** The Constant MAILBODY_TELEFONO. */
    public static final String MAILBODY_TELEFONO = "    Teléfono fijo:";

    /** The Constant MAILBODY_TIPODOC. */
    public static final String MAILBODY_TIPODOC = "    Tipo de documento:";

    /** The Constant MASK. */
    public static final int MASK = 0x00ff;

    /** The Constant OFF_VTO_INI. */
    public static final int OFF_VTO_INI = 4;

    /** The Constant OFF_VTO_FIN. */
    public static final int OFF_VTO_FIN = 8;

    /** The Constant CLASE_CUENTA_RECARGABLE. */
    public static final String CLASE_CUENTA_RECARGABLE = "T";

    /** The Constant CUENTA_UNICA. */
    public static final String CUENTA_UNICA = "Cuenta Unica";

    /** The Constant FMT_MMYYYY. */
    public static final String FMT_MMYYYY = "MMyyyy";

    /** The Constant FMT_MMYYYY_BAR. */
    public static final String FMT_MMYYYY_BAR = "MM/yyyy";

    /** The Constant FMT_MMMMYYYY. */
    public static final String FMT_MMMMYYYY = "MMMM yyyy";

    /** The Constant FMT_YYMM. */
    public static final String FMT_YYMM = "yyMM";

    /** The Constant FMT_YYYYMMDD. */
    public static final String FMT_YYYYMMDD = "yyyyMMdd";

    /** The Constant PROD_ALTAIR_03. */
    public static final String PROD_ALTAIR_03 = "03";

    /** The Constant SUBPROD_ALTAIR_0001. */
    public static final String SUBPROD_ALTAIR_0001 = "0001";

    /** The Constant SUC_BP_1. */
    public static final String SUC_BP_1 = "0250";

    /** The Constant SUC_BP_2. */
    public static final String SUC_BP_2 = "0251";

    /** The Constant UTF8_CHARSET. */
    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    /** The Constant CAJA_AHORRO. */
    public static final String CAJA_AHORRO = "CAJA_AHORRO";

    /** The Constant CTA_CTE. */
    public static final String CTA_CTE = "CTA_CTE";

    /** The Constant GENERO_IATX_H. */
    public static final String GENERO_IATX_H = "H";

    /** The Constant GENERO_IATX_M. */
    public static final String GENERO_IATX_M = "M";

    /** The Constant GENERO_WS_F. */
    public static final String GENERO_WS_F = "F";

    /** The Constant GENERO_WS_M. */
    public static final String GENERO_WS_M = "M";

    /** The Constant IDMEDIOPAGO_AMEX. */
    public static final String IDMEDIOPAGO_AMEX = "1";

    /** The Constant IDMEDIOPAGO_DEB. */
    public static final String IDMEDIOPAGO_DEB = "43";

    /** The Constant IDMEDIOPAGO_CABAL. */
    public static final String IDMEDIOPAGO_CABAL = "6";

    /** The Constant IDMEDIOPAGO_CABAL24. */
    public static final String IDMEDIOPAGO_CABAL24 = "129";

    /** The Constant IDMEDIOPAGO_DINERS. */
    public static final String IDMEDIOPAGO_DINERS = "2";

    /** The Constant IDMEDIOPAGO_MAESTRO. */
    public static final String IDMEDIOPAGO_MAESTRO = "13";

    /** The Constant IDMEDIOPAGO_MASTER. */
    public static final String IDMEDIOPAGO_MASTER = "14";

    /** The Constant IDMEDIOPAGO_VISA. */
    public static final String IDMEDIOPAGO_VISA = "42";

    /** The status error return adh. */
    public static final String[] statusErrorReturnAdh = { BilleteraConstants.STATUS_1000,
            BilleteraConstants.STATUS_1001, BilleteraConstants.STATUS_1003, BilleteraConstants.STATUS_1028,
            BilleteraConstants.STATUS_1040 };

    /** The status error return adh lst. */
    public static final List<String> statusErrorReturnAdhLst = Arrays.asList(statusErrorReturnAdh);

    /** The status error conf. */
    public static final String[] statusErrorConf = { BilleteraConstants.STATUS_1023, BilleteraConstants.STATUS_1042 };

    /** The status error conf lst. */
    public static final List<String> statusErrorConfLst = Arrays.asList(statusErrorConf);

    /** The status error cuenta tp. */
    public static final String[] statusErrorCuentaTp = { BilleteraConstants.STATUS_1060, BilleteraConstants.STATUS_1061,
            BilleteraConstants.STATUS_1062, BilleteraConstants.STATUS_1063 };

    /** The status error cuenta tp lst. */
    public static final List<String> statusErrorCuentaTpLst = Arrays.asList(statusErrorCuentaTp);

    /** The status error ret adm cbu. */
    public static final String[] statusErrorRetAdmCbu = { BilleteraConstants.STATUS_1011,
            BilleteraConstants.STATUS_1092 };

    /** The status error ret adm cbu lst. */
    public static final List<String> statusErrorRetAdmCbuLst = Arrays.asList(statusErrorRetAdmCbu);

    /** The status error gral adm medio pago. */
    public static final String[] statusErrorGralAdmMedioPago = { BilleteraConstants.STATUS_1023,
            BilleteraConstants.STATUS_1019, BilleteraConstants.STATUS_1102, BilleteraConstants.STATUS_1043,
            BilleteraConstants.STATUS_1021, BilleteraConstants.STATUS_1016, BilleteraConstants.STATUS_1044,
            BilleteraConstants.STATUS_1042 };

    /** The status error gral adm medio pago lst. */
    public static final List<String> statusErrorGralAdmMedioPagoLst = Arrays.asList(statusErrorGralAdmMedioPago);

    /** The status error grico adm medio pago. */
    public static final String[] statusErrorGricoAdmMedioPago = { BilleteraConstants.STATUS_9507,
            BilleteraConstants.STATUS_9515, BilleteraConstants.STATUS_9519, BilleteraConstants.STATUS_9521 };

    /** The status error grico adm medio pago lst. */
    public static final List<String> statusErrorGricoAdmMedioPagoLst = Arrays.asList(statusErrorGricoAdmMedioPago);

    /** The status error oper adm medio pago. */
    public static String[] statusErrorOperAdmMedioPago = { BilleteraConstants.STATUS_9500,
            BilleteraConstants.STATUS_9504, BilleteraConstants.STATUS_9508, BilleteraConstants.STATUS_9512,
            BilleteraConstants.STATUS_9513, BilleteraConstants.STATUS_9514, BilleteraConstants.STATUS_9517,
            BilleteraConstants.STATUS_9520 };

    /** The status error oper adm medio pago lst. */
    public static List<String> statusErrorOperAdmMedioPagoLst = Arrays.asList(statusErrorOperAdmMedioPago);

    /** The status error adm medio pago. */
    public static final String[] statusErrorAdmMedioPago = { BilleteraConstants.STATUS_9501,
            BilleteraConstants.STATUS_9505 };

    /** The status error adm medio pago lst. */
    public static final List<String> statusErrorAdmMedioPagoLst = Arrays.asList(statusErrorAdmMedioPago);

    /** The tipos cta cte. */
    public static final String[] tiposCtaCte = { BilleteraConstants.TIPOCTA_CCP, BilleteraConstants.TIPOCTA_CCD,
            BilleteraConstants.TIPOCTA_CU, BilleteraConstants.TIPOCTA_CUD, BilleteraConstants.TIPOCTA_CUP };

    /** The tipos cta cte lst. */
    public static final List<String> tiposCtaCteLst = Arrays.asList(tiposCtaCte);

    /** The tipos cta ahorro. */
    public static final String[] tiposCtaAhorro = { BilleteraConstants.TIPOCTA_CAP, BilleteraConstants.TIPOCTA_CAD };

    /** The tipos cta ahorro lst. */
    public static final List<String> tiposCtaAhorroLst = Arrays.asList(tiposCtaAhorro);

    /** The Constant LST_PR_NOCS. */
    public static final String LST_PR_NOCS = "lstPrNoCs";

    /** The Constant LST_NOPR_CS. */
    public static final String LST_NOPR_CS = "lstNoPrCs";

    /** The Constant LST_PR_CS. */
    public static final String LST_PR_CS = "lstPrCs";

    /** The Constant LST_PR_OB. */
    public static final String LST_PR_OB = "lstPrOb";

    /**
     * Instantiates a new billetera constants.
     */
    private BilleteraConstants() {

    }
}