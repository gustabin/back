/**
 * 
 */
package ar.com.santanderrio.obp.servicios.canalesautomaticos.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.dao.AltaCanalAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.monedero.dao.impl.MonederoActivacionDAOImpl;
import ar.com.santanderrio.obp.servicios.monedero.utils.MonederoUtils;

/**
 * The Class AltaCanalAutomaticoDAOImpl.
 *
 * @author alejandro_leal
 */
@Component("canalAutomaticoDAO")
public class AltaCanalAutomaticoDAOImpl extends IatxBaseDAO implements AltaCanalAutomaticoDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MonederoActivacionDAOImpl.class);

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The servicio pag tjc. */
    private static final String SERV_ALTPEFICNL = "ALTPEFICNL";

    /** The version pag tjc. */
    private static final String SERV_ALTPEFICNL_VER = "100";

    /** CHAR A. */
    private static final String CHAR_A = "A";

    /** CHAR N. */
    private static final String CHAR_N = "N";

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monedero.dao.MonederoActivacionDAO#
     * activar(ar.com.santanderrio.obp.servicios.monedero.entities.
     * MonederoActivacionInEntities)
     */

    @Override
    public AltaCanalAutomaticoOutEntity altaCanalAutomatico(AltaCanalAutomaticoInEntity entity) throws DAOException {
        IatxRequest iatxRequest = new IatxRequest(SERV_ALTPEFICNL, SERV_ALTPEFICNL_VER);

        AltaCanalAutomaticoOutEntity altaCanalAutomaticoOutEntity = new AltaCanalAutomaticoOutEntity();

        try {
            IatxRequestData data = generateRequestDataALTPEFICNL(entity);

            iatxRequest.setData(data);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            int errorCode = iatxResponse.getErrorCode();

            /**
             * ESTA ES LA RESPUESTA DE HABER CREADO EL USUARIO DE JUAN CON EL ALTPECFIS
             * iatxResponse.setTrama("200000000000P04HTML0009900010300KLLT03
             * ********00195691000000082017041016364200000000IBF29829000000000000ALTPEFICNL1000000
             * 00011903 00 00 000000000201704101636340000000000
             * 0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
             * altaCanalAutomaticoOutEntity = processTrama(iatxResponse.getTrama(),
             * AltaCanalAutomaticoOutEntity.class);
             */

            // OJO DESCOMENTAR ESTO Y COMENTAR LO DE ARRIBA!!
            if (OK_CODIGO_RETORNO == errorCode) {
                altaCanalAutomaticoOutEntity = processTrama(iatxResponse.getTrama(),
                        AltaCanalAutomaticoOutEntity.class);
            } else {
                // manejar respuesta ERROR
                return null;
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }

        return altaCanalAutomaticoOutEntity;
    }

    /**
     * Rellenar.
     *
     * @param campo
     *            the campo
     * @param tipo
     *            the tipo
     * @param cantidad
     *            the cantidad
     * @return the string
     */
    private String rellenar(String campo, String tipo, int cantidad) {
        if (campo == null) {
            return campo;
        } else {
            if (tipo.equals(CHAR_A)) {
                return ISBANStringUtils.normalizarCampoAlfanumericoIatx(campo, cantidad);
            } else if (tipo.equals(CHAR_N)) {
                return StringUtils.leftPad(campo, cantidad, "0");
            } else {
                return null;
            }
        }
    }

    /**
     * Genera el objeto IatxRequestData para llamar al servicio iatx.
     * 
     * @param entity
     *            the entity
     * @return the iatx request data
     */
    private IatxRequestData generateRequestDataALTPEFICNL(AltaCanalAutomaticoInEntity entity) {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");

        IatxRequestData data = new IatxRequestData(entity.getCliente());

        /* inicio obligatorios */
        data.addBodyValue(ISBANStringUtils.traerIdComboDni(entity.getTipoDocumentoTIT().trim())); // #1
                                                                                                  // //tipoDocumentoTit
                                                                                                  // *CO
                                                                                                  // (Del
                                                                                                  // Adicional)
        data.addBodyValue(ISBANStringUtils.fillStr(entity.getNumDocumentoTIT(), 11)); // #2
                                                                                      // //nroDocumentoTit
                                                                                      // *CO
                                                                                      // (Del
                                                                                      // Adicional)
        data.addBodyValue(entity.getApellido().trim()); // #3 // apellido *CO
        data.addBodyValue(""); // #4 // segundoApellido
        data.addBodyValue(entity.getNombre().trim()); // #5 // nombre *CO
        data.addBodyValue(MonederoUtils.estadoCivilIatxCodigo(entity.getEstadoCivil())); // #6
                                                                                         // //
                                                                                         // estadoCivil
                                                                                         // *CO
        data.addBodyValue(rellenar(entity.getSexo(), "A", 1)); // #7 // sexo *CO
        try {
            data.addBodyValue(sdf2.format(sdf1.parse(entity.getFecNacimiento()))); // #8
                                                                                   // //
                                                                                   // fecNacimiento
                                                                                   // *CO
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        data.addBodyValue(rellenar(entity.getPaisNacimiento(), "N", 3)); // #9
                                                                         // //
                                                                         // paisNacimiento
                                                                         // *CO
        data.addBodyValue(rellenar(entity.getNacionalidad(), "N", 3)); // #10
                                                                       // //
                                                                       // titularPaisResidencia
                                                                       // *CO
        data.addBodyValue(rellenar(entity.getNacionalidad(), "N", 3)); // #11
                                                                       // //
                                                                       // nacionalidad
                                                                       // *CO

        data.addBodyValue("002"); // datImpositivosCodActBcra *CO
        data.addBodyValue(" ");
        data.addBodyValue("000");
        data.addBodyValue(""); // DomTitularidad
        data.addBodyValue(""); // DomPartVia
        data.addBodyValue(rellenar(entity.getDomPartCalle(), "A", 50));
        data.addBodyValue(rellenar(entity.getDomPartNro(), "A", 10));
        /* fin obligatorios */

        /* inicio NO-obligatorios */
        data.addBodyValue("");// #19 // DomPartManzana
        data.addBodyValue("");// #20 // DomPartTorre
        data.addBodyValue("");// #21 // DomPartSucCorreo
        data.addBodyValue("");// #22 // DomPartNumeroPortal

        // Campos del 21 al 30
        data.addBodyValue("");// #23 // DomPartNumeroEscalera
        data.addBodyValue("");// #24 // DomPartEntreCalle
        data.addBodyValue("");// #25 // DomPartYCalle
        data.addBodyValue("");// #26 // DomPartPiso
        data.addBodyValue("");// #27 // DomPartDpto
        /* fin NO-obligatorios */

        /* inicio obligatorios */
        data.addBodyValue(
                !"".equals(entity.getDomPartCPPatente()) ? rellenar(entity.getDomPartCPPatente(), "A", 1) : "C"); // #28
                                                                                                                  // //
                                                                                                                  // DomPartCpPatente
        data.addBodyValue(ISBANStringUtils.fillNum(entity.getDomPartCPCodPostal(), 4)); // #29
                                                                                        // //
                                                                                        // DomPartCpCodPostal
        data.addBodyValue(
                !"".equals(entity.getDomPartCPManzana()) ? rellenar(entity.getDomPartCPManzana(), "A", 3) : "AAG"); // #30
                                                                                                                    // //
                                                                                                                    // DomPartCpManzana
        data.addBodyValue(rellenar(entity.getDomPartLocalidad(), "A", 30)); // #31
                                                                            // //
                                                                            // DomPartLocalidad
        data.addBodyValue(""); // #32 // DomPartComuna

        // Campos del 31 al 40
        data.addBodyValue(rellenar(entity.getDomPartProvincia(), "N", 2)); // #33
                                                                           // //
                                                                           // domPartProvincia
                                                                           // *CO
        data.addBodyValue(rellenar(entity.getPaisNacimiento(), "N", 3)); // #34
                                                                         // //
                                                                         // domCodPais
                                                                         // *CO
        data.addBodyValue("");// #35 // domPartViveDesde
        data.addBodyValue("PRI");// # 36 //domTipo *CO
        data.addBodyValue("");// #37 //domPartFecVerificacion
        data.addBodyValue("S");// #38 //domMarcaNormalizaci�n *CO
        data.addBodyValue("N");// #39 //domMarcaDomErroneo *CO
        data.addBodyValue("N");// #40 //domMarcaCorrespDevuel *CO
        data.addBodyValue("   ");// #41 //domMotivoDevolucion *CO
        data.addBodyValue("");// #42 //tipoTelefono *CO

        // Campos del 41 al 50
        data.addBodyValue("");// #43 // claseTelefono *CO
        data.addBodyValue("");// #44 //telPartDDN
        data.addBodyValue("");// #45 //telPartCaracteristica
        data.addBodyValue("");// #46 //telPartNumero
        data.addBodyValue("");// #47 //internosTelefonos
        data.addBodyValue("");// #48 //cantidadDatComplementarios *CO
        data.addBodyValue(StringUtils.leftPad("", 4)); // #49
                                                       // //datLabOcup1TipoOcupacion
        data.addBodyValue(StringUtils.leftPad("", 40)); // #50
                                                        // //datLabOcup1Empresa_nombre
        data.addBodyValue(StringUtils.leftPad("", 4)); // #51
                                                       // //datLabOcup1TipoActividad
        data.addBodyValue("09");// #52 //datLabOcup1CargoFuncion

        data.addBodyValue(StringUtils.leftPad("", 8));// #53
                                                      // //datLabOcup1FecIngreso
        data.addBodyValue(StringUtils.leftPad("", 1));// #54
                                                      // //datLabOcup1FijoContratado
        data.addBodyValue(StringUtils.leftPad("", 5));// #55
                                                      // //datLabOcup1TipoEmpresa
        data.addBodyValue("");// #56 //cantPersonaACargo
        data.addBodyValue("");// #57 //cantHijos
        data.addBodyValue("");// #58 //apellidoNombreMadre
        data.addBodyValue("");// #59 //apellidoNombrePadre
        data.addBodyValue("");// #60 //nivelEstudio
        data.addBodyValue("");// #61 //profesion
        data.addBodyValue("");// #62 //ultTitulo

        data.addBodyValue("");// #63 //antig�edadDomAnterior
        data.addBodyValue("06");// #64 //canalVenta *CO
        data.addBodyValue(ISBANStringUtils.llenaConCeros(3));// #65
                                                             // //canalCaptacion
                                                             // *CO
        data.addBodyValue(ISBANStringUtils.llenaConCeros(2));// #66 //campa�a
                                                             // *CO
        data.addBodyValue("");// #67 //oficial1 *CO
        data.addBodyValue(StringUtils.leftPad("", 4));// #68 //oficial2
        data.addBodyValue(StringUtils.leftPad("", 4));// #69 //oficial3
        data.addBodyValue(StringUtils.leftPad("", 4));// #70 //oficial4
        data.addBodyValue(StringUtils.leftPad("", 4));// #71 //oficial5
        data.addBodyValue("COM");// #72 //unidadNegocios *CO

        data.addBodyValue("N");// #73 //marcaClienteBancaPrivada *CO
        data.addBodyValue("NO");// #74 //datLabOcupPDomTitularidad *CO
        data.addBodyValue("");// #75 //datLabOcupPVia
        data.addBodyValue("");// #76 //datLabOcupPDomCalle
        data.addBodyValue("");// #77 //datLabOcupPDomNro
        data.addBodyValue("");// #78 //datLabOcupPDomPiso
        data.addBodyValue("");// #79 //datLabOcupPDomDpto
        data.addBodyValue("");// #80 //datLabOcupPManzana
        data.addBodyValue("");// #81 //datLabOcupPTorre
        data.addBodyValue("");// #82 //datLabOcupPSucCorreo

        data.addBodyValue("");// #83 //datLabOcupPNumeroPortal
        data.addBodyValue("");// #84 //datLabOcupPNumeroEscalera
        data.addBodyValue("");// #85 //datLabOcupPEntreCalle
        data.addBodyValue("");// #86 //datLabOcupPYCalle
        data.addBodyValue("");// #87 //datLabOcupPPatente
        data.addBodyValue("");// #88 //datLabOcupPDomCodPostal
        data.addBodyValue("");// #89 //datLabOcupPManzana
        data.addBodyValue("");// #90 //datLabOcupPDomLocalidad
        data.addBodyValue("");// #91 //datLabOcupPDomComuna
        data.addBodyValue("");// #92 //datLabOcupPDomProvincia

        data.addBodyValue("");// #93 //datLabOcupPDomTipo
        data.addBodyValue("");// #94 //datLabOcupPDomCodPais
        data.addBodyValue("");// #95 //adhesionRioLine
        data.addBodyValue("");// #96 //datLabMarcaNormalizacion
        data.addBodyValue("");// #97 //datLabMarcaDomErroneo
        data.addBodyValue("");// #98 //datLabMarcaCorrespDevuel
        data.addBodyValue("");// #99 //datLabMotivoDevolucion
        data.addBodyValue(ISBANStringUtils.fillStr(entity.getTipoInscripcionCuitCuil().trim(), 1) + " "); // tipoInscripcion
                                                                                                          // *CO
                                                                                                          // ->
                                                                                                          // "L
                                                                                                          // "
                                                                                                          // o
                                                                                                          // "T
                                                                                                          // "
        data.addBodyValue(rellenar(entity.getNroInscripcion(), "N", 11)); // nroInscripci�n
                                                                          // *CO
        data.addBodyValue("");// #102 //expedidoPor *CO

        data.addBodyValue("");// #103 //tipoDocumento2 *CO
        data.addBodyValue(rellenar(entity.getNroInscripcion(), "N", 11));// #104
                                                                         // //nroDocumento2
                                                                         // *CO
        data.addBodyValue("080");// # 105 /expedidoPor *CO
        data.addBodyValue(StringUtils.leftPad("", 2));// #106
                                                      // //tipoDocumentoPasaporte
                                                      // *CO
        data.addBodyValue(StringUtils.leftPad("", 11));// # 107 //nroPasaporte
                                                       // *CO
        data.addBodyValue("");// #108 //paisPasaporte

        // se sacan de
        data.addBodyValue(rellenar(entity.getBanca(), "N", 2));// #109 //banca
                                                               // *CO
        data.addBodyValue(rellenar(entity.getDivision(), "N", 2));// #110
                                                                  // //division
                                                                  // *CO
        data.addBodyValue(rellenar(entity.getTeamLeader(), "N", 4));// #111
                                                                    // //teamLeader
        data.addBodyValue(rellenar(entity.getEjecutivoCta(), "N", 4));// #112
                                                                      // //ejecutivoCuenta
        data.addBodyValue(rellenar(entity.getJefeArea(), "A", 4));// #113
                                                                  // //jefeArea
                                                                  // *CO
        data.addBodyValue(rellenar(entity.getGerente(), "A", 4));// #114
                                                                 // //gerente
                                                                 // *CO

        /* Cantidad de Informacion Impositiva */
        data.addBodyValue("01");// #115 //cantidadInformaci�nImpositiva *CO
        data.addBodyValue("N");// #116 //datImpositivosAgentePercepcion
        data.addBodyValue("01");// #117 //datImpositivosTipoIva *CO
        data.addBodyValue("N");// #118 //constTipoRespAnteAFIP
        data.addBodyValue(" ");// #119 //certExclusionPercepcion3337
        data.addBodyValue("01010001");// #120 //fechaInicioValidezExclusion
        data.addBodyValue("31129999");// #121 //fechaVencimientoValidezExclusion
        data.addBodyValue("4");// #122 //datImpositivosCondImpGcias *CO

        data.addBodyValue(StringUtils.leftPad("", 1));// #123
                                                      // //certificadoNoRetencion
        data.addBodyValue("0");// #124 //porcentajeNoRetencionGanancias
        data.addBodyValue("01010001");// #125 //fechaInicioCertificado
        data.addBodyValue("31129999");// #126 //fechaVencimientoCertificado
        data.addBodyValue("N");// #127 //certificadoExtencion
        data.addBodyValue(StringUtils.leftPad("", 1));// #128
                                                      // //ajustePorInflacion
        data.addBodyValue("000");// # 129 //datImpCodIng_brutos *CO
        data.addBodyValue(StringUtils.leftPad("", 20));// #130
                                                       // //datImpNroInscIngBrutos
        data.addBodyValue(StringUtils.leftPad("", 1));// #131 //marcaEmpleador
        data.addBodyValue(StringUtils.leftPad("", 1));// #132 //marcaVigilancia

        data.addBodyValue(StringUtils.leftPad("", 1));// #133 //marcaAutonomo
        data.addBodyValue(StringUtils.leftPad("", 6));// #134
                                                      // //ultimoPagoPrevisional
        data.addBodyValue(StringUtils.leftPad("", 1));// #135 //marcaRetenedor
        data.addBodyValue(StringUtils.leftPad("", 1));// #136
                                                      // //marcaAutoretenedor
        data.addBodyValue(StringUtils.leftPad("", 1));// #137 //marcaPactoFiscal
        data.addBodyValue("");// #138 //informacionPactoFiscal
        data.addBodyValue(StringUtils.leftPad("", 1));// #139
                                                      // //marcaAgenteRetencion
        data.addBodyValue(StringUtils.leftPad("", 1));// #140
                                                      // //marcaPresentoCertAgRet
        data.addBodyValue("0");// #141 //porcExclusionIVARet3337
        data.addBodyValue(" ");// #142 //numeroDNRP

        data.addBodyValue(StringUtils.leftPad("", 1));// #143
                                                      // //marcaSociedadRegular
        data.addBodyValue("7");// #144 //tipo_compPAutonomo
        data.addBodyValue(StringUtils.leftPad("", 1));// #145
                                                      // //marcaParaFuturosImp
        data.addBodyValue(StringUtils.leftPad("", 1));// #146
                                                      // //marcaParaFuturosImp
        data.addBodyValue(StringUtils.leftPad("", 1));// #147
                                                      // //marcaParaFuturosImp
        data.addBodyValue("01");// #148 //cantidadTelefonos
        data.addBodyValue("003");// #149 //telefono1Tipo
        data.addBodyValue("001");// #150 //telefono1Clase

        data.addBodyValue(!"".equals(entity.getTelefono1DDN()) ? rellenar(entity.getTelefono1DDN(), "A", 3) : "011"); // #151
                                                                                                                      // //telefono1DDN
        data.addBodyValue(!"".equals(entity.getTelefono1Caracteristica())
                ? StringUtils.leftPad(entity.getTelefono1Caracteristica(), 4).substring(0, 4)
                : "3122"); // #152
                           // //telefono1Caracteristica
        data.addBodyValue(
                !"".equals(entity.getTelefono1Numero()) ? rellenar(entity.getTelefono1Numero(), "N", 4) : "9999"); // #153
                                                                                                                   // //telefono1Numero

        data.addBodyValue("");// #154 //telefono1Internos
        data.addBodyValue("");// #155 //telefono2Tipo
        data.addBodyValue("");// #156 //telefono2Clase
        data.addBodyValue("");// #157 //telefono2DDN
        data.addBodyValue("");// #158 //telefono2Caracteristica
        data.addBodyValue("");// #159 //telefono3Numero
        data.addBodyValue("");// #160 //telefono3Internos
        data.addBodyValue("");// #161 //telefono3Tipo
        data.addBodyValue("");// #162 //telefono3Clase

        data.addBodyValue("");// #163 //telefono3DDN
        data.addBodyValue("");// #164 //telefono3Caracteristica
        data.addBodyValue("");// #165 //telefono3Numero
        data.addBodyValue("");// #166 //telefono3Internos
        data.addBodyValue("");// #167 //CantidadDeRefPersonales
        data.addBodyValue("");// #168 //RefCom1tipoDeRelacion
        data.addBodyValue("");// #169 //RefComLApellido
        data.addBodyValue("");// #170 //RefCom1Nombre
        data.addBodyValue("");// #171 //refCom1IndicadorDeCliente
        data.addBodyValue("");// #172 //refCom1telefonoDDN

        data.addBodyValue("");// #173 //refCom1telefonoNumero
        data.addBodyValue("");// #174 //refCom1telefonoInternos
        data.addBodyValue("");// #175 //refCom1Domtitularidad
        data.addBodyValue("");// #176 //refCom1DomVia
        data.addBodyValue("");// #177 //refCom1DomCalle
        data.addBodyValue("");// #178 //refCom1DomNro
        data.addBodyValue("");// #179 //refCom1DomPiso
        data.addBodyValue("");// #180 //refCom1DomDpto
        data.addBodyValue("");// #181 //refCom1DomManzana
        data.addBodyValue("");// #182 //refCom1DomTorre

        data.addBodyValue("");// #183 //refCom1DomSucCorreo
        data.addBodyValue("");// #184 //refCom1DomNumeroPortal
        data.addBodyValue("");// #185 //refCom1DomNumeroEscalera
        data.addBodyValue("");// #186 //refCom1DomEntreCalle
        data.addBodyValue("");// #187 //refCom1DomYCalle
        data.addBodyValue("");// #188 //refCom1DomPatente
        data.addBodyValue("");// #189 //refCom1DomCodPostal
        data.addBodyValue("");// #190 //refCom1DomManzana
        data.addBodyValue("");// #191 //refCom1DomLocalidad
        data.addBodyValue("");// #192 //refCom1DomComuna

        data.addBodyValue("");// #193 //refCom1Domprovincia
        data.addBodyValue("");// #194 //refCom1Domtipo
        data.addBodyValue("");// #195 //refCom1DomCodPais
        data.addBodyValue("");// #196 //refCom1MarcaNormalizacion
        data.addBodyValue("");// #197 //refCom1MarcaDomErroneo
        data.addBodyValue("");// #198 //refCom1MarcaCorrespDevuel
        data.addBodyValue("");// #199 //refCom1MotivoDevolucion
        data.addBodyValue("");// #200 //refCom2TipoDRelacion
        data.addBodyValue("");// #201 //refCom2Apellido
        data.addBodyValue("");// #202 //refCom2Nombre

        data.addBodyValue("");// #203 //refCom2IndicadorDeCliente
        data.addBodyValue("");// #204 //refCom2TelefonoDDN
        data.addBodyValue("");// #205 //refCom2TelefonoNumero
        data.addBodyValue("");// #206 //efCom2TelefonoInternos
        data.addBodyValue("");// #207 //refCom2DomTitularidad
        data.addBodyValue("");// #208 //refCom2DomVia
        data.addBodyValue("");// #209 //refCom2DomCalle
        data.addBodyValue("");// #210 //refCom2DomNro
        data.addBodyValue("");// #211 //refCom2DomPiso
        data.addBodyValue("");// #212 //refCom2DomDpto

        data.addBodyValue("");// #213 //refCom2DomManzana
        data.addBodyValue("");// #214 //refCom2DomTorre
        data.addBodyValue("");// #215 //refCom2DomSucCorreo
        data.addBodyValue("");// #216 //refCom2DomNumeroPortal
        data.addBodyValue("");// #217 //refCom2DomNumeroEscalera
        data.addBodyValue("");// #218 //refCom2DomEntreCalle
        data.addBodyValue("");// #219 //refCom2DomYCalle
        data.addBodyValue("");// #220 //refCom2DomPatente
        data.addBodyValue("");// #221 //refCom2DomCodPostal
        data.addBodyValue("");// #222 //refCom2DomManzana

        data.addBodyValue("");// #223 //refCom2DomLocalidad
        data.addBodyValue("");// #224 //refCom2DomComuna
        data.addBodyValue("");// #225 //refCom2DomProvincia
        data.addBodyValue("");// #226 //refCom2DomTipo
        data.addBodyValue("");// #227 //refCom2DomCodPais
        data.addBodyValue("");// #228 //refCom2MarcaNormalizacion
        data.addBodyValue("");// #229 //refCom2MarcaDomErroneo
        data.addBodyValue("");// #230 //refCom2MarcaCorrespDevuel
        data.addBodyValue("");// #231 //refCom2MotivoDevolucion
        data.addBodyValue("");// #232 //cantidadIngresos

        data.addBodyValue("");// #233 //tipoIngreso
        data.addBodyValue("");// #234 //importeIngreso N11(9,2)
        data.addBodyValue("");// #235 //codMoneda
        data.addBodyValue("");// #236 //importeHasta N11(9,2)
        data.addBodyValue("");// #237 //tipoIngreso
        data.addBodyValue("");// #238 //importeIngreso N11(9,2)
        data.addBodyValue("");// #239 //codMoneda
        data.addBodyValue("");// #240 //ImporteHasta
        data.addBodyValue("");// #241 //TipoIngreso
        data.addBodyValue("");// #242 //ImporteIngreso

        data.addBodyValue("");// #243 //CodMoneda
        data.addBodyValue("");// #244 //ImporteHasta
        data.addBodyValue("");// #245 //CantidadEmpresasAnteriores
        data.addBodyValue("");// #246 //ocupAnterior1NombreEmpresa
        data.addBodyValue("");// #247 //ocupAnterior1TelefonoNumero
        data.addBodyValue("");// #248 //ocupAnterior1Actividad
        data.addBodyValue("");// #249 //ocupAnterior1cargoFuncion
        data.addBodyValue("");// #250 //ocupAnterior1fechaIngreso
        data.addBodyValue("");// #251 //ocupAnterior1fechaEgreso
        data.addBodyValue("");// #252 //ocupAnterior1ingMens

        data.addBodyValue("");// #253 //ocupAnterior1CodDeMoneda
        data.addBodyValue("");// #254 //CantidadInformacionPatrimonial
        data.addBodyValue("");// #255 //infPat1CodInfPat
        data.addBodyValue("");// #256 //infPat1Valor
        data.addBodyValue("");// #257 //infPat1SupInmueble
        data.addBodyValue("");// #258 //infPat1IngAlquiler
        data.addBodyValue("");// #259 //infPat1MarcaAuto
        data.addBodyValue("");// #260 //infPat1ModeloAuto
        data.addBodyValue("");// #261 //infPat1Antiguedad
        data.addBodyValue("");// #262 //infPat1InmuebleOpcionAVenta

        data.addBodyValue("");// #263 //infPat1Condominio
        data.addBodyValue("");// #264 //infPat1BienDeFamilia
        data.addBodyValue("");// #265 //infPat1InmuebleGastos N11(9,2)
        data.addBodyValue("");// #266 //infPat1A�oRodado
        data.addBodyValue("");// #267 //infPat1PatrimonioNeto N15(13,2)
        data.addBodyValue("");// #268 //infPat1CodDeMoneda
        data.addBodyValue("");// #269 //infPat1Obs
        data.addBodyValue("");// #270 //infPat1Inmueble1Domcalle
        data.addBodyValue("");// #271 //infPat1Inmueble1Domnro
        data.addBodyValue("");// #272 //infPat1Inmueble1Dompiso

        data.addBodyValue("");// #273 //infPat1Inmueble1Domdpto
        data.addBodyValue("");// #274 //infPat1Inmueble1Domlocalidad
        data.addBodyValue("");// #275 //infPat1Inmueble1Dompatente
        data.addBodyValue("");// #276 //infPat1Inmueble1DomcodPostal
        data.addBodyValue("");// #277 //infPat1Inmueble1Dommanzana
        data.addBodyValue("");// #278 //infPat1Inmueble1Domprovincia
        data.addBodyValue("");// #279 //infpat2CodInfPatrimonial
        data.addBodyValue("");// #280 //infpat2Valor N15(13,2)
        data.addBodyValue("");// #281 //infpat2SuperficieInmueble
        data.addBodyValue("");// #282 //infpat2IngresosAlquiler N11(9,2)

        data.addBodyValue("");// #283 //infpat2MarcaAuto
        data.addBodyValue("");// #284 //infpat2ModeloAuto
        data.addBodyValue("");// #285 //infpat2Antiguedad
        data.addBodyValue("");// #286 //infpat2InmuebleOpcionAVenta
        data.addBodyValue("");// #287 //infpat2Condominio
        data.addBodyValue("");// #288 //infpat2BienFamilia
        data.addBodyValue("");// #289 //infpat2InmuebleGastos N11(9,2)
        data.addBodyValue("");// #290 //infpat2A�oRodado
        data.addBodyValue("");// #291 //infpat2Patrimonio_Neto N15(13,2)
        data.addBodyValue("");// #292 //infpat2CodDeMoneda

        data.addBodyValue("");// #293 //infpat2Obs(OTRO BIEN Descr)
        data.addBodyValue("");// #294 //infpat2Inmueble1Domcalle
        data.addBodyValue("");// #295 //infpat2Inmueble1Domnro
        data.addBodyValue("");// #296 //infpat2Inmueble1Dompiso
        data.addBodyValue("");// #297 //infpat2Inmueble1Domdpto
        data.addBodyValue("");// #298 //infpat2Inmueble1Domlocalidad
        data.addBodyValue("");// #299 //infpat2Inmueble1Dompatente
        data.addBodyValue("");// #300 //infpat2Inmueble1DomCodPostal
        data.addBodyValue("");// #301 //infpat2Inmueble1DomManzana
        data.addBodyValue("");// #302 //infpat2Inmueble1DomProvincia

        data.addBodyValue("");// #303 //infpat3CodInformacionPatrimonial
        data.addBodyValue("");// #304 //infpat3Valor N15(13,2)
        data.addBodyValue("");// #305 //infpat3SuperficieInmueble
        data.addBodyValue("");// #306 //infpat3IngresosAlquiler N11(9,2)
        data.addBodyValue("");// #307 //infpat3MarcaAuto
        data.addBodyValue("");// #308 //infpat3ModeloAuto
        data.addBodyValue("");// #309 //infpat3Antiguedad
        data.addBodyValue("");// #310 //infpat3InmuebleOpcionaVenta
        data.addBodyValue("");// #311 //infpat3Condominio
        data.addBodyValue("");// #312 //infpat3BienDeFamilia

        data.addBodyValue("");// #313 //infpat3InmuebleGastos N11(9,2)
        data.addBodyValue("");// #314 //infpat3A�oRodado
        data.addBodyValue("");// #315 //infpat3PatrimonioNeto N15(13,2)
        data.addBodyValue("");// #316 //infpat3CodDeMoneda
        data.addBodyValue("");// #317 //infpat3Obs(OTRO BIEN Descr)
        data.addBodyValue("");// #318 //infpat3Inmueble1DomCalle
        data.addBodyValue("");// #319 //infpat3Inmueble1DomNro
        data.addBodyValue("");// #320 //infpat3Inmueble1DomPiso
        data.addBodyValue("");// #321 //infpat3Inmueble1DomDpto
        data.addBodyValue("");// #322 //infpat3Inmueble1DomLocalidad

        data.addBodyValue("");// #323 //infpat3Inmueble1DomPatente
        data.addBodyValue("");// #324 //infpat3Inmueble1DomCodPostal
        data.addBodyValue("");// #325 //infpat3Inmueble1DomManzana
        data.addBodyValue("");// #326 //infpat3Inmueble1DomProvincia
        data.addBodyValue("");// #327 //infpat4CodInfPat
        data.addBodyValue("");// #328 //infpat4Valor N15(13,2)
        data.addBodyValue("");// #329 //infpat4SupInmueble
        data.addBodyValue("");// #330 //infpat4IngAlquiler N11(9,2)
        data.addBodyValue("");// #331 //infpat4MarcaAuto
        data.addBodyValue("");// #332 //infpat4ModeloAuto

        data.addBodyValue("");// #333 //infpat4Antiguedad
        data.addBodyValue("");// #334 //infpat4InmuebleOpcionAVenta
        data.addBodyValue("");// #335 //infpat4Condominio
        data.addBodyValue("");// #336 //infpat4BienDeFamilia
        data.addBodyValue("");// #337 //infpat4InmuebleGastos N11(9,2)
        data.addBodyValue("");// #338 //infpat4A�oRodado
        data.addBodyValue("");// #339 //infpat4PatrimonioNeto N15(13,2)
        data.addBodyValue("");// #340 //infpat4CodDeMoneda
        data.addBodyValue("");// #341 //infpat4Obs(OTRO BIEN Descr)
        data.addBodyValue("");// #342 //infpat4Inmueble1DomCalle

        data.addBodyValue("");// #343 //infpat4Inmueble1DomNro
        data.addBodyValue("");// #344 //infpat4Inmueble1DomPiso
        data.addBodyValue("");// #345 //infpat4Inmueble1DomDpto
        data.addBodyValue("");// #346 //infpat4Inmueble1DomLocalidad
        data.addBodyValue("");// #347 //infpat4Inmueble1DomPatente
        data.addBodyValue("");// #348 //infpat4Inmueble1DomCodPostal
        data.addBodyValue("");// #349 //infpat4Inmueble1DomManzana
        data.addBodyValue("");// #350 //infpat4Inmueble1Dom_provincia
        data.addBodyValue("");// #351 //infpat5CodInfPat
        data.addBodyValue("");// #352 //infpat5Valor N15(13,2)

        data.addBodyValue("");// #353 //infpat5SuperficieInmueble
        data.addBodyValue("");// #354 //infpat5IngresosAlquiler N11(9,2)
        data.addBodyValue("");// #355 //infpat5MarcaAuto
        data.addBodyValue("");// #356 //infpat5ModeloAuto
        data.addBodyValue("");// #357 //infpat5Antiguedad
        data.addBodyValue("");// #358 //infpat5InmuebleOpcionAVenta
        data.addBodyValue("");// #359 //infpat5Condominio
        data.addBodyValue("");// #360 //infpat5BienDefamilia
        data.addBodyValue("");// #361 //infpat5InmuebleGastos N11(9,2)
        data.addBodyValue("");// #362 //infpat5A�oRodado

        data.addBodyValue("");// #363 //infpat5PatrimonioNeto N15(13,2)
        data.addBodyValue("");// #364 //infpat5CodDeMoneda
        data.addBodyValue("");// #365 //infpat5Obs(OTRO BIEN Descr)
        data.addBodyValue("");// #366 //infpat5Inmueble1DomCalle
        data.addBodyValue("");// #367 //infpat5Inmueble1DomNro
        data.addBodyValue("");// #368 //infpat5Inmueble1DomPiso
        data.addBodyValue("");// #369 //infpat5Inmueble1DomDpto
        data.addBodyValue("");// #370 //infpat5Inmueble1DomLocalidad
        data.addBodyValue("");// #371 //infpat5Inmueble1DomPatente
        data.addBodyValue("");// #372 //infpat5Inmueble1DomCodPostal

        data.addBodyValue("");// #373 //infpat5Inmueble1DomManzana
        data.addBodyValue("");// #374 //infpat5Inmueble1DomProvincia
        data.addBodyValue("");// #375 //infpat6C�dInfPat
        data.addBodyValue("");// #376 //infpat6Valor N15(13,2)
        data.addBodyValue("");// #377 //infpat6SuperficieInmueble
        data.addBodyValue("");// #378 //infpat6IngresosAlquiler N11(9,2)
        data.addBodyValue("");// #379 //infpat6MarcaAuto
        data.addBodyValue("");// #380 //infpat6ModeloAuto
        data.addBodyValue("");// #381 //infpat6Antiguedad
        data.addBodyValue("");// #382 //infpat6InmuebleOpcionAVenta

        data.addBodyValue("");// #383 //infpat6Condominio
        data.addBodyValue("");// #384 //infpat6BienDeFamilia
        data.addBodyValue("");// #385 //infpat6InmuebleGastos N11(9,2)
        data.addBodyValue("");// #386 //infpat6A�oRodado
        data.addBodyValue("");// #387 //infpat6PatrimonioNeto N15(13,2)
        data.addBodyValue("");// #388 //infpat6CodDeMoneda
        data.addBodyValue("");// #389 //infpat6Obs(OTRO BIEN Descr)
        data.addBodyValue("");// #390 //infpat6Inmueble1DomCalle
        data.addBodyValue("");// #391 //infpat6Inmueble1DomNro
        data.addBodyValue("");// #392 //infpat6Inmueble1DomPiso

        data.addBodyValue("");// #393 //infpat6Inmueble1DomDpto
        data.addBodyValue("");// #394 //infpat6Inmueble1DomLocalidad
        data.addBodyValue("");// #395 //infpat6Inmueble1DomPatente
        data.addBodyValue("");// #396 //infpat6Inmueble1DomCodPostal
        data.addBodyValue("");// #397 //infpat6Inmueble1DomManzana
        data.addBodyValue("");// #398 //infpat6Inmueble1DomProvincia
        data.addBodyValue("00"); // #399 //cantidadDatAdicionales (010)
        data.addBodyValue("");// #400 //datadic1CodRef
        data.addBodyValue("");// #401 //datadic1TipoRef
        data.addBodyValue("");// #402 //datadic1NroCuenta

        data.addBodyValue("");// #403 //datadic1TipoEntidadEmisora
        data.addBodyValue("");// #404 //datadic1CodEntidadEmisora
        data.addBodyValue("");// #405 //datadic1Cuota N13(11,2)
        data.addBodyValue("");// #406 //datadic1Plazo
        data.addBodyValue("");// #407 //datadic1MontoAsignado N13(11,2)
        data.addBodyValue("");// #408 //datadic1SaldoDeuda N13(11,2)
        data.addBodyValue("");// #409 //datadic1CodDeMoneda
        data.addBodyValue("");// #410 //datadic1NombreAcreedor
        data.addBodyValue("");// #411 //datadic2CodRef
        data.addBodyValue("");// #412 //datadic2TipoRef

        data.addBodyValue("");// #413 //datadic2NroCuenta
        data.addBodyValue("");// #414 //datadic2TipoEntidadEmisora
        data.addBodyValue("");// #415 //datadic2CodEntidadEmisora
        data.addBodyValue("");// #416 //datadic2Cuota N13(11,2)
        data.addBodyValue("");// #417 //datadic2Plazo
        data.addBodyValue("");// #418 //datadic2MontoAsignado N13(11,2)
        data.addBodyValue("");// #419 //datadic2SaldoDeuda N13(11,2)
        data.addBodyValue("");// #420 //datadic2CodDeMoneda
        data.addBodyValue("");// #421 //datadic2NombreAcreedor
        data.addBodyValue("");// #422 //datadic3CodRef

        data.addBodyValue("");// #423 //datadic3TipoRef
        data.addBodyValue("");// #424 //datadic3NroCuenta
        data.addBodyValue("");// #425 //datadic3TipoEntidadEmisora
        data.addBodyValue("");// #426 //datadic3CodEntidadEmisora
        data.addBodyValue("");// #427 //datadic3Cuota N13(11,2)
        data.addBodyValue("");// #428 //datadic3Plazo
        data.addBodyValue("");// #429 //datadic3MontoAsignado N13(11,2)
        data.addBodyValue("");// #430 //datadic3SaldoDeuda N13(11,2)
        data.addBodyValue("");// #431 //datadic3CodDeMoneda
        data.addBodyValue("");// #432 //datadic3NombreAcreedor

        data.addBodyValue("");// #433 //datadic4CodRef
        data.addBodyValue("");// #434 //datadic4TipoRef
        data.addBodyValue("");// #435 //datadic4NroCuenta
        data.addBodyValue("");// #436 //datadic4TipoEntidadEmisora
        data.addBodyValue("");// #437 //datadic4CodEntidadEmisora
        data.addBodyValue("");// #438 //datadic4Cuota N13(11,2)
        data.addBodyValue("");// #439 //datadic4Plazo
        data.addBodyValue("");// #440 //datadic4MontoAsignado N13(11,2)
        data.addBodyValue("");// #441 //datadic4SaldoDeuda N13(11,2)
        data.addBodyValue("");// #442 //datadic4CodDeMoneda

        data.addBodyValue("");// #443 //datadic4NombreAcreedor
        data.addBodyValue("");// #444 //datadic5CodRef
        data.addBodyValue("");// #445 //datadic5TipoRef
        data.addBodyValue("");// #446 //datadic5NroCuenta
        data.addBodyValue("");// #447 //datadic5TipoEntidadEmisora
        data.addBodyValue("");// #448 //datadic5CodEntidadEmisora
        data.addBodyValue("");// #449 //datadic5Cuota N13(11,2)
        data.addBodyValue("");// #450 //datadic5Plazo
        data.addBodyValue("");// #451 //datadic5MontoAsignado N13(11,2)
        data.addBodyValue("");// #452 //datadic5SaldoDeuda N13(11,2)

        data.addBodyValue("");// #453 //datadic5CodDeMoneda
        data.addBodyValue("");// #454 //datadic5NombreAcreedor
        data.addBodyValue("");// #455 //datadic6CodRef
        data.addBodyValue("");// #456 //datadic6TipoRef
        data.addBodyValue("");// #457 //datadic6NroCuenta
        data.addBodyValue("");// #458 //datadic6TipoEntidadEmisora
        data.addBodyValue("");// #459 //datadic6CodEntidadEmisora
        data.addBodyValue("");// #460 //datadic6Cuota N13(11,2)
        data.addBodyValue("");// #461 //datadic6Plazo
        data.addBodyValue("");// #462 //datadic6MontoAsignado N13(11,2)

        data.addBodyValue("");// #463 //datadic6SaldoDeuda N13(11,2)
        data.addBodyValue("");// #464 //datadic6CodDeMoneda
        data.addBodyValue("");// #465 //datadic6NombreAcreedor
        data.addBodyValue("");// #466 //datadic7CodRef
        data.addBodyValue("");// #467 //datadic7TipoRef
        data.addBodyValue("");// #468 //datadic7NroCuenta
        data.addBodyValue("");// #469 //datadic7TipoEntidadEmisora
        data.addBodyValue("");// #470 //datadic7CodEntidadEmisora
        data.addBodyValue("");// #471 //datadic7Cuota N13(11,2)
        data.addBodyValue("");// #472 //datadic7Plazo

        data.addBodyValue("");// #473 //datadic7MontoAsignado N13(11,2)
        data.addBodyValue("");// #474 //datadic7SaldoDeuda N13(11,2)
        data.addBodyValue("");// #475 //datadic7CodDeMoneda
        data.addBodyValue("");// #476 //datadic7NombreAcreedor
        data.addBodyValue("");// #477 //datadic8CodRef
        data.addBodyValue("");// #478 //datadic8TipoRef
        data.addBodyValue("");// #479 //datadic8NroCuenta
        data.addBodyValue("");// #480 //datadic8TipoEntidadEmisora
        data.addBodyValue("");// #481 //datadic8CodEntidadEmisora
        data.addBodyValue("");// #482 //datadic8Cuota N13(11,2)

        data.addBodyValue("");// #483 //datadic8Plazo
        data.addBodyValue("");// #484 //datadic8MontoAsignado N13(11,2)
        data.addBodyValue("");// #485 //datadic8SaldoDeuda N13(11,2)
        data.addBodyValue("");// #486 //datadic8CodDeMoneda
        data.addBodyValue("");// #487 //datadic8NombreAcreedor
        data.addBodyValue("");// #488 //datadic9CodRef
        data.addBodyValue("");// #489 //datadic9TipoRef
        data.addBodyValue("");// #490 //datadic9NroCuenta
        data.addBodyValue("");// #491 //datadic9TipoEntidadEmisora
        data.addBodyValue("");// #492 //datadic9CodEntidadEmisora

        data.addBodyValue("");// #493 //datadic9Cuota N13(11,2)
        data.addBodyValue("");// #494 //datadic9Plazo
        data.addBodyValue("");// #495 //datadic9MontoAsignado N13(11,2)
        data.addBodyValue("");// #496 //datadic9SaldoDeuda N13(11,2)
        data.addBodyValue("");// #497 //datadic9CodDeMoneda
        data.addBodyValue("");// #498 //datadic9NombreAcreedor
        data.addBodyValue("");// #499 //datadic10CodRef
        data.addBodyValue("");// #500 //datadic10TipoRef
        data.addBodyValue("");// #501 //datadic10NroCuenta
        data.addBodyValue("");// #502 //datadic10TipoEntidadEmisora

        data.addBodyValue("");// #503 //datadic10CodEntidadEmisora
        data.addBodyValue("");// #504 //datadic10Cuota N13(11,2)
        data.addBodyValue("");// #505 //datadic10Plazo
        data.addBodyValue("");// #506 //datadic10MontoAsignado N13(11,2)
        data.addBodyValue("");// #507 //datadic10SaldoDeuda N13(11,2)
        data.addBodyValue(""); // #508 //datadic10CodDeMoneda
        data.addBodyValue("");// #509 //datadic10NombreAcreedor

        data.addBodyValue(ISBANStringUtils.fillNum(entity.getSucursal(), 4)); // #510
                                                                              // //Sucursal
                                                                              // *CO
        data.addBodyValue(ISBANStringUtils.traerIdComboDni(entity.getTipoDocumentoTIT().trim()).trim()); // #511
                                                                                                         // //tipoDocumento
                                                                                                         // *CO
        data.addBodyValue(StringUtils.leftPad(entity.getNumDocumentoTIT(), 11, "0")); // #512
                                                                                      // //nroDocumento
                                                                                      // *CO
        data.addBodyValue("001"); // # 513 pesecper *CO

        /*
         * data.addBodyValue(rellenar(entity.getSucursal(), "A", 4)); //#510 //Sucursal
         * *CO data.addBodyValue(rellenar(entity.getTipoDocumentoAdic(), "A", 2));
         * //#511 //tipoDocumento *CO
         * data.addBodyValue(ISBANStringUtils.fillNum(entity.getNroDocumentoAdic (),
         * 11)); //#512 //nroDocumento *CO data.addBodyValue("001"); //# 513 //pesecper
         * *CO
         */
        return data;
    }

}
