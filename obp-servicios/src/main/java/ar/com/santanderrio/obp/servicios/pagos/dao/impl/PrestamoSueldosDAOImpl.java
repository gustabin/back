/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.prestamos.BCRAParameters;
import ar.com.santanderrio.obp.generated.webservices.prestamos.BCRAResponse;
import ar.com.santanderrio.obp.generated.webservices.prestamos.ObjectFactory;
import ar.com.santanderrio.obp.generated.webservices.prestamos.WSFOREXBCRASoap;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.dao.PrestamoSueldosDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.ConsultaPrestamoSueldosEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.InfoEmpleadoPrestamoTasaSubsidiada;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldoTasaSubsidiadaEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldosAgregarCBUEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldosConfigEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmarPrestamoSueldosInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaSubsidiadaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoSueldoTasaSubsidiadaView;
import oracle.jdbc.OracleTypes;

// TODO: Auto-generated Javadoc
/**
 * The Class DeudaPrestamoDAOImpl.
 */
@Component
@Repository
@TargetSystem(DataBase.HBOBP)
public class PrestamoSueldosDAOImpl extends BaseDatoDAOImpl implements PrestamoSueldosDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoSueldosDAOImpl.class);

    /** The Constant SOLICITUD_SCHEMA. */
    private static final String PRESTAMOS_SUELDOS_SCHEMA = "BBANK";
    
    /** The Constant PRESTAMOS_SUELDOS_PACKAGE. */
    private static final String PRESTAMOS_SUELDOS_PACKAGE ="PKG_PRESTAMO_SUELDOS"; 
    
    /** The Constant SOLICITUD_PACKAGE. */
    private static final String PRESTAMOS_SUELDOS_INSERTAR = "INSERTAR_PRESTAMO";

    /** The Constant CONSULTAR_CLIENTE_CHAT. */
    private static final String PRESTAMOS_SUELDOS_CONSULTAR = "CONSULTAR_CLIENTE";		

    /** Constant CONSULTAR_PRESTAMO_ATP_VIGENTE */
    private static final String CONSULTAR_PRESTAMO_ATP_VIGENTE = "CONSULTAR_PRESTAMO_ATP_VIGENTE";	
    
    /** Constant ALTA_PRESTAMO_ATP_VIGENTE */
    private static final String ALTA_PRESTAMO_ATP_VIGENTE = "insertar_prestamo_atp";	

    private static final String ACTUALIZAR_CBU = "ACTUALIZAR_CBU";
    
    /** The Constant IN_NUP. */
    private static final String IN_NUP = "P_NUP";
    
    /** The Constant IN_P_MONTO_SOLICITADO. */
    private static final String IN_P_MONTO_SOLICITADO = "P_MONTO_SOLICITADO";
    
    /** The Constant IN_CUIT. */
    private static final String IN_CUIT = "P_CUIT";
    
    /** The Constant P_CANAL. */
    private static final String P_CANAL = "P_CANAL";
    
    /** The Constant P_PRODUCTOCUENTA. */
    private static final String P_PRODUCTOCUENTA = "P_PRODUCTOCUENTA";
    
    /** The Constant P_SUBPRODUCTOCUENTA. */
    private static final String P_SUBPRODUCTOCUENTA = "P_SUBPRODUCTOCUENTA";
    
    /** The Constant P_SUCURSALCUENTA. */
    private static final String P_SUCURSALCUENTA = "P_SUCURSALCUENTA";
    
    /** The Constant P_NUMEROCUENTA. */
    private static final String P_NUMEROCUENTA = "P_NUMEROCUENTA";
    
    /** The Constant P_ENTIDAD. */
    private static final String P_ENTIDAD = "P_ENTIDAD";
    
    /** The Constant P_TIPO_DOCUMENTO. */
    private static final String P_TIPO_DOCUMENTO = "P_TIPO_DOCUMENTO";
    
    /** The Constant P_DOCUMENTO. */
    private static final String P_DOCUMENTO = "P_DOCUMENTO";
    
    /** The Constant P_MAIL. */
    private static final String P_MAIL = "P_MAIL";
    
    /** The Constant P_CANTIDAD_CUOTAS. */
    private static final String P_CANTIDAD_CUOTAS = "P_CANTIDAD_CUOTAS";
    
    /** The Constant P_SISTEMA_AMORTIZACION. */
    private static final String P_SISTEMA_AMORTIZACION = "P_SISTEMA_AMORTIZACION";
    
    /** The Constant P_TIPO_TASA. */
    private static final String P_TIPO_TASA = "P_TIPO_TASA";
    
    /** The Constant P_PERIODICIDAD. */
    private static final String P_PERIODICIDAD = "P_PERIODICIDAD";
    
    /** The Constant P_TASA_NOMINAL_ANUAL. */
    private static final String P_TASA_NOMINAL_ANUAL = "P_TASA_NOMINAL_ANUAL";
    
    /** The Constant P_TASA_EFECTIVA_ANUAL. */
    private static final String P_TASA_EFECTIVA_ANUAL = "P_TASA_EFECTIVA_ANUAL";
    
    /** The Constant P_COSTO_FINA_TEA_CIMPUESTOS. */
    private static final String P_COSTO_FINA_TEA_CIMPUESTOS = "P_COSTO_FINA_TEA_CIMPUESTOS";
    
    /** The Constant P_COSTO_FINA_TEA_SIMPUESTOS. */
    private static final String P_COSTO_FINA_TEA_SIMPUESTOS = "P_COSTO_FINA_TEA_SIMPUESTOS";
    
    /** The Constant P_PAGO_PRIM_CUOTA. */
    private static final String P_PAGO_PRIM_CUOTA = "P_PAGO_PRIM_CUOTA";
    
    /** The Constant P_MOTIVO_PRESTAMO. */
    private static final String P_MOTIVO_PRESTAMO = "P_MOTIVO_PRESTAMO";
    
    /** The Constant P_MONTO_ACUMU. */
    private static final String P_MONTO_ACUMU = "P_MONTO_ACUMU";
    
    /** The Constant P_MONTO_RESTANTE. */
    private static final String P_MONTO_RESTANTE = "P_MONTO_RESTANTE";
    
    /** The Constant P_NRO_OPERACION. */
    private static final String P_NRO_OPERACION = "P_NRO_OPERACION";
    
    /** The Constant P_ESTADO. */
    private static final String P_ESTADO = "P_ESTADO";
    
    /** The Constant P_DATOS. */
    private static final String P_DATOS = "P_DATOS";
    
    private static final String P_ID = "P_ID";
    
    private static final String P_CBU = "P_CBU";
    
    private static final String R_ID = "R_ID";

    private static final String R_SOLICITUD = "R_SOLICITUD";

    private static final String R_TASA = "R_TASA";

    private static final String R_FECHA_ALTA = "R_FECHA_ALTA";

    private static final String R_ESTADO = "R_ESTADO";

    private static final String R_TIPO_CLIENTE = "R_TIPO_CLIENTE";

    private static final String R_DATOS = "R_DATOS";

    private static final String R_EMPLEADOS = "R_EMPLEADOS";
    
    private static final String R_PERIODO = "R_PERIODO"; 
   
    /** The Constant OUT_RESULTADO. */
    private static final String OUT_RESULTADO = "R_CURSOR";

    /** The Constant OUT_ERROR_TECNICO. */
    private static final String OUT_COD_RET = "R_COD_RET";

    /** The Constant OUT_ERROR_AMIGABLE. */
    private static final String OUT_DESC_RET = "R_DESC_RET";
    
    /** The Constant SISTEMA_AMORTIZACION. */
    private static final String SISTEMA_AMORTIZACION = "frances";
    
    /** The Constant TIPO_TASA. */
    private static final String TIPO_TASA = "Fija";
    
    /** The Constant PERIODICIDAD. */
    private static final String PERIODICIDAD =  "Mensual";
    
    /** The Constant TASA_NOMINAL_ANUAL. */
    private static final double TASA_NOMINAL_ANUAL = 24.00;
    
    /** The Constant TASA_EFECTIVA_ANUAL. */
    private static final double TASA_EFECTIVA_ANUAL = 26.82;
    
    /** The Constant COSTO_FINA_TEA_CIMPUESTOS. */
    private static final double COSTO_FINA_TEA_CIMPUESTOS = 26.82;
    
    /** The Constant COSTO_FINA_TEA_SIMPUESTOS. */
    private static final double COSTO_FINA_TEA_SIMPUESTOS = 26.82;       
    
    /** The Constant MOTIVO_PRESTAMO. */
    private static final String MOTIVO_PRESTAMO = "Pago de haberes";    
    
    /** The Constant MONTO_ACUMU. */
    private static final double MONTO_ACUMU = 0.0;    
    
    /** The Constant MONTO_RESTANTE. */
    private static final double MONTO_RESTANTE = 0.0;
    
    /** The Constant ESTADO. */
    private static final double ESTADO = 0;
    
    /** The Constant DATOS. */
    private static final String DATOS = "";
    
    /** The Constant CANAL. */
    private static final String CANAL = "OBP";
    
    /** The Constant ENTIDAD. */
    private static final String ENTIDAD = "072";
    
    /** The Constant CANTIDAD_CUOTAS. */
    private static final String CANTIDAD_CUOTAS = "10";
    
    private static final String CANTIDAD_CUOTAS_TASA_SUBS = "12";
    
    private static final String FORMATO_TIMESTAMP = "yyyy-MM-dd";
    
    private static final String TIPO_CLIENTE_CC = "CC";
    
    private static final String ADJUNTA_DOCUMENTO_T = "T";
    
    private static final String ADJUNTA_DOCUMENTO_F = "F";
    
    private static final String EXTENSION_VALIDA_DOCUMENTO = ".pdf";
   
    
	@Value("${PRESTAMOSUELDOS.VALIDADOR.VIRUS.DOCUMENTOS.PATH}")
	private String prestamosSueldosDocumentosPath;
		
	@Value("${PRESTAMOSUELDOS.MAX.FILES.ATACHED}")
	private Integer cantidadMaxDeArchivosPosiblesAjuntos;
	
	@Value("${PRESTAMOSUELDOS.TIEMPO.ESPERA.VERIFICACION.ARCHIVO.ANTIVIRUS}")
	private Integer tiempoEsperaAntivirus;
	
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The client. */
	@Autowired
	@Qualifier("WSFOREXBCRAGestor")
	private GestionarWS<WSFOREXBCRASoap> client;

	/**
	 * Consultar prestamo sueldos.
	 *
	 * @param cliente the cliente
	 * @return the prestamo sueldos config entity
	 * @throws DAOException the DAO exception
	 */
	@Override
	public PrestamoSueldosConfigEntity consultarPrestamoSueldos(Cliente cliente) throws DAOException {
		String nup = cliente.getNup();
		PrestamoSueldosConfigEntity prestamoConfig = new PrestamoSueldosConfigEntity();
		List<ConsultaPrestamoSueldosEntity> listConsultaPrestamoSueldosEntity = new ArrayList<ConsultaPrestamoSueldosEntity>();
    	try {
            List<SqlParameter> parametros = new ArrayList<SqlParameter>();
            parametros.add(new SqlParameter(IN_NUP, OracleTypes.VARCHAR));
            //parametros.add(new SqlOutParameter(OUT_RESULTADO, OracleTypes.CURSOR));
            parametros.add(new SqlOutParameter(OUT_RESULTADO, OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<ConsultaPrestamoSueldosEntity>(ConsultaPrestamoSueldosEntity.class)));
            parametros.add(new SqlOutParameter(OUT_COD_RET, OracleTypes.NUMBER));
            parametros.add(new SqlOutParameter(OUT_DESC_RET, OracleTypes.VARCHAR));

            SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nup);
            Map<String, Object> respuesta = consultar(PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE, PRESTAMOS_SUELDOS_CONSULTAR, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));

            LOGGER.info("consultarPrestamoSaldos: {} .", respuesta.get(OUT_RESULTADO));
            BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_COD_RET);
            //String mensajeRetorno = (String) respuesta.get(OUT_DESC_RET);
            //List<String> cursor =  (List<String>) respuesta.get(OUT_RESULTADO);

			if (BigDecimal.ZERO.equals(codRetorno)) {
				listConsultaPrestamoSueldosEntity = (List<ConsultaPrestamoSueldosEntity>) respuesta.get(OUT_RESULTADO);
				if (CollectionUtils.isNotEmpty(listConsultaPrestamoSueldosEntity)) {
					for (ConsultaPrestamoSueldosEntity item : listConsultaPrestamoSueldosEntity) {
						prestamoConfig.setMontoPrestamoSueldos(item.getMonto());
						prestamoConfig.setSolicitado(item.getSolicitado());
						
						if (TIPO_CLIENTE_CC.equals(item.getTipoCliente())){
							prestamoConfig.setAdjuntaDocumentacion(ADJUNTA_DOCUMENTO_T);
						}else {
							prestamoConfig.setAdjuntaDocumentacion(ADJUNTA_DOCUMENTO_F);
						}
					}
				}
			}
            
            //prestamoConfig.setMontoPrestamoSueldos("10.000.00");
        } catch (SQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}.", PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE, PRESTAMOS_SUELDOS_CONSULTAR, e);
            throw new DAOException(e);
        } catch (UncategorizedSQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE,
            		PRESTAMOS_SUELDOS_CONSULTAR, e);
            throw new DAOException(e);
        } catch (InvalidDataAccessApiUsageException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE,
            		PRESTAMOS_SUELDOS_CONSULTAR, e);
            throw new DAOException(e);
        }
		return prestamoConfig;
	}
	
	
	/**
	 * Insertar prestamo sueldos.
	 *
	 * @param cliente the cliente
	 * @param confirmarPrestamoSueldosInDTO the confirmar prestamo sueldos in DTO
	 * @return the prestamo sueldos config entity
	 * @throws DAOException the DAO exception
	 */
	@Override
	public PrestamoSueldosConfigEntity insertarPrestamoSueldos(Cliente cliente, ConfirmarPrestamoSueldosInDTO confirmarPrestamoSueldosInDTO) throws DAOException {		
		//List<Cuenta> cuentasPesos = cliente.getCuentasEnPesos();
		Cuenta cta = confirmarPrestamoSueldosInDTO.getCuentasPesos();		
		String nup = cliente.getNup();
		String MONTO_SOLICITADO = confirmarPrestamoSueldosInDTO.getMontoPrestamoSeleccion();
	
		if (!MONTO_SOLICITADO.contains(".")) {
			MONTO_SOLICITADO = MONTO_SOLICITADO.concat(".00");
		}
		
		BigDecimal monto = new BigDecimal(MONTO_SOLICITADO);
	    String MAIL = confirmarPrestamoSueldosInDTO.getEmail();
	    String TIPO_DOCUMENTO = cliente.getTipoDocumento();
	    String DOCUMENTO = cliente.getDni();	    	    	    	    	   
        Date fechaHoy = new Date();
        String NRO_COMPROBANTE= String.valueOf(fechaHoy.getTime()).substring(0, 9);
        String NRO_OPERACION = "0";

        Calendar c = Calendar.getInstance(); 
        c.setTime(fechaHoy); 
        c.add(Calendar.DAY_OF_YEAR, 90);
        fechaHoy = c.getTime();        
        DateFormat dateFormat = new SimpleDateFormat(FORMATO_TIMESTAMP);  
        String PAGO_PRIM_CUOTA = dateFormat.format(fechaHoy);
        
		PrestamoSueldosConfigEntity prestamoConfig = new PrestamoSueldosConfigEntity();
		//List<ConsultaPrestamoSueldosEntity> listConsultaPrestamoSueldosEntity = new ArrayList<ConsultaPrestamoSueldosEntity>();
    	try {
            List<SqlParameter> parametros = new ArrayList<SqlParameter>();
            parametros.add(new SqlParameter(IN_NUP, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(IN_P_MONTO_SOLICITADO, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_CANAL, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_PRODUCTOCUENTA, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_SUBPRODUCTOCUENTA, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_SUCURSALCUENTA, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_NUMEROCUENTA, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_ENTIDAD, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_TIPO_DOCUMENTO, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_DOCUMENTO, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_MAIL, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_CANTIDAD_CUOTAS, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_SISTEMA_AMORTIZACION, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_TIPO_TASA, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_PERIODICIDAD, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_TASA_NOMINAL_ANUAL, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_TASA_EFECTIVA_ANUAL, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_COSTO_FINA_TEA_CIMPUESTOS, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_COSTO_FINA_TEA_SIMPUESTOS, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_PAGO_PRIM_CUOTA, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_MOTIVO_PRESTAMO, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_MONTO_ACUMU, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_MONTO_RESTANTE, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_NRO_OPERACION, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_ESTADO, OracleTypes.NUMBER));
            parametros.add(new SqlParameter(P_DATOS, OracleTypes.VARCHAR));                                         
            parametros.add(new SqlOutParameter(OUT_COD_RET, OracleTypes.NUMBER));
            parametros.add(new SqlOutParameter(OUT_DESC_RET, OracleTypes.VARCHAR));

            SqlParameterSource in= new MapSqlParameterSource()
            					   .addValue(IN_NUP, nup)
            					   .addValue(IN_P_MONTO_SOLICITADO, monto)
            					   .addValue(P_CANAL, CANAL)
            					   .addValue(P_PRODUCTOCUENTA, Integer.parseInt(cta.getProductoAltair()))
            					   .addValue(P_SUBPRODUCTOCUENTA, Integer.parseInt(cta.getSubproductoAltair()))
            					   .addValue(P_SUCURSALCUENTA, Integer.parseInt(cta.getSucursalPaquete()))
            					   .addValue(P_NUMEROCUENTA, Integer.parseInt(cta.getNroCuentaProducto()))
            					   .addValue(P_ENTIDAD, Integer.parseInt(ENTIDAD))
            					   .addValue(P_TIPO_DOCUMENTO, TIPO_DOCUMENTO)
            					   .addValue(P_DOCUMENTO, DOCUMENTO)
            					   .addValue(P_MAIL, MAIL)
            					   .addValue(P_CANTIDAD_CUOTAS, Integer.parseInt(CANTIDAD_CUOTAS))
            					   .addValue(P_SISTEMA_AMORTIZACION, SISTEMA_AMORTIZACION)
            					   .addValue(P_TIPO_TASA, TIPO_TASA)
            					   .addValue(P_PERIODICIDAD, PERIODICIDAD)
            					   .addValue(P_TASA_NOMINAL_ANUAL, TASA_NOMINAL_ANUAL)
            					   .addValue(P_TASA_EFECTIVA_ANUAL, TASA_EFECTIVA_ANUAL)
            					   .addValue(P_COSTO_FINA_TEA_CIMPUESTOS, COSTO_FINA_TEA_CIMPUESTOS)
            					   .addValue(P_COSTO_FINA_TEA_SIMPUESTOS, COSTO_FINA_TEA_SIMPUESTOS)
            					   .addValue(P_PAGO_PRIM_CUOTA, PAGO_PRIM_CUOTA)
            					   .addValue(P_MOTIVO_PRESTAMO, MOTIVO_PRESTAMO)
            					   .addValue(P_MONTO_ACUMU, MONTO_ACUMU)
            					   .addValue(P_MONTO_RESTANTE, MONTO_RESTANTE)
            					   .addValue(P_NRO_OPERACION, NRO_OPERACION)
            					   .addValue(P_ESTADO, ESTADO)
            					   .addValue(P_DATOS, DATOS);
            
            
            Map<String, Object> respuesta = consultar(PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE, PRESTAMOS_SUELDOS_INSERTAR, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));

            LOGGER.info("insertarPrestamoSaldos: {} .", respuesta.get(OUT_COD_RET));
            BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_COD_RET);
            //String mensajeRetorno = (String) respuesta.get(OUT_DESC_RET);
            //List<String> cursor =  (List<String>) respuesta.get(OUT_RESULTADO);

			if (BigDecimal.ZERO.equals(codRetorno)) {					
				prestamoConfig.setNroComprobante(NRO_COMPROBANTE);									
			}
            
            //prestamoConfig.setMontoPrestamoSueldos("10.000.00");
        } catch (SQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}.", PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE, PRESTAMOS_SUELDOS_INSERTAR, e);
            throw new DAOException(e);
        } catch (UncategorizedSQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE,
            		PRESTAMOS_SUELDOS_INSERTAR, e);
            throw new DAOException(e);
        } catch (InvalidDataAccessApiUsageException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE,
            		PRESTAMOS_SUELDOS_INSERTAR, e);
            throw new DAOException(e);
        }
		return prestamoConfig;
	}
		
	@Override
	public Respuesta<Boolean> verificarVirusArchivoFs(AdjuntarArchivosInEntity adjuntarArchivosInEntity) {
		Respuesta<Boolean> response = new Respuesta<Boolean>();
		LOGGER.info("Verificar si archivo tiene virus");
		OutputStream out = null;
		File filePath = null;
		File archivo = null;
		try {

			filePath = new File(this.prestamosSueldosDocumentosPath + "/");

			if (!filePath.exists()) {
				filePath.mkdir();
			}						

			archivo = new File(this.prestamosSueldosDocumentosPath + "/" + adjuntarArchivosInEntity.getArchivo().getNombre() + EXTENSION_VALIDA_DOCUMENTO);

			LOGGER.info("Verificar si archivo tiene virus" + archivo.toString());
			
			out = new FileOutputStream(archivo);
			out.write(adjuntarArchivosInEntity.getArchivo().getByteArray());
			out.flush();
			out.close();
			
			esperarQueAntivirusVerifiqueArchivo();

			if (archivo.exists()) {
				response.setRespuesta(true);
				
			} else {
				LOGGER.error("Error el archivo tiene virus!");
				response.setRespuesta(false);
			}

		} catch (IOException e) {
			LOGGER.error("Error verificando archivo", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				LOGGER.error("Error verificando archivo", e);
			}
		}
		return response;
	}
	

	
	/**
	 * 
	 */
	private void esperarQueAntivirusVerifiqueArchivo() {
		try {
			TimeUnit.MILLISECONDS.sleep(tiempoEsperaAntivirus);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}

	}


	@Override
	public Respuesta<Boolean> borrarDoc(AdjuntarArchivosInEntity adjuntarArchivosInEntity) {
		Respuesta<Boolean> response = new Respuesta<Boolean>();
		LOGGER.info("Borrar archivo");
		File archivo = null;					
		archivo = new File(this.prestamosSueldosDocumentosPath + "/" + this.sesionCliente.getCliente().getNup() + "/" + adjuntarArchivosInEntity.getArchivo().getNombre() + EXTENSION_VALIDA_DOCUMENTO);
			if (archivo.delete()) {
				LOGGER.info("El archivo  ha sido borrado satisfactoriamente");
				response.setRespuesta(true);
			}else {
				LOGGER.info("El archivo  no puede ser borrado satisfactoriamente");
				response.setRespuesta(false);
			}			
		return response;
	}	

	/**
	 * Consultar WSFOREXBCRA.
	 *
	 * @param cliente the cliente
	 * @return the BCRA response
	 * @throws DAOException the DAO exception
	 */
	@Override
	public BCRAResponse consultarWSFOREXBCRA(Cliente cliente) throws DAOException {
		WSFOREXBCRASoap services = null;
		try {
			services = client.obtenerPort();
			BCRAParameters parameters = crearValidaPagoDeudaVencidoRequest(cliente);
			return services.validaPagoDeudaVencido(parameters);
		} catch (Exception e) {
			LOGGER.error("Error al invocar a WSFOREXBCRASoap: ", e);
			throw new DAOException(e);
		} finally {
			client.liberarPort(services);
		}
	}

	/**
	 * Crear valida pago deuda vencido request.
	 *
	 * @param cliente the cliente
	 * @return the BCRA parameters
	 */
	private BCRAParameters crearValidaPagoDeudaVencidoRequest(Cliente cliente) {
		ObjectFactory of = new ObjectFactory();
		BCRAParameters parameters = of.createBCRAParameters();
		parameters.setNup(cliente.getNup());
		parameters.setAplicativo(CANAL);
		parameters.setTipoDocInsc(cliente.getTipoDocCnsDocXNup());
		parameters.setNroDocInsc(cliente.getNroDocCnsDocXNup());
		return parameters;
	}
	
	@Override
	public PrestamoSueldoTasaSubsidiadaEntity consultarPrestamoATPVigente(Cliente cliente) throws DAOException {
		String cuit = cliente.getNumeroCUILCUIT().replaceAll("-", "");
		PrestamoSueldoTasaSubsidiadaEntity prestamoConfig = new PrestamoSueldoTasaSubsidiadaEntity();
		List<InfoEmpleadoPrestamoTasaSubsidiada> listaEmpleados = new ArrayList<InfoEmpleadoPrestamoTasaSubsidiada>();
		List<InfoEmpleadoPrestamoTasaSubsidiada> listaEmpleadosEntity = new ArrayList<InfoEmpleadoPrestamoTasaSubsidiada>();
    	try {
            List<SqlParameter> parametros = new ArrayList<SqlParameter>();
            parametros.add(new SqlParameter(IN_CUIT, OracleTypes.VARCHAR));
            
            parametros.add(new SqlOutParameter(R_ID, OracleTypes.NUMBER));
            parametros.add(new SqlOutParameter(R_SOLICITUD, OracleTypes.NUMBER));
            parametros.add(new SqlOutParameter(R_TASA, OracleTypes.NUMBER));
            parametros.add(new SqlOutParameter(R_FECHA_ALTA, OracleTypes.VARCHAR));
            parametros.add(new SqlOutParameter(R_ESTADO, OracleTypes.VARCHAR));
            parametros.add(new SqlOutParameter(R_TIPO_CLIENTE, OracleTypes.VARCHAR));
            parametros.add(new SqlOutParameter(R_PERIODO, OracleTypes.VARCHAR));
            parametros.add(new SqlOutParameter(R_DATOS, OracleTypes.VARCHAR));
            parametros.add(new SqlOutParameter(R_EMPLEADOS, OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<InfoEmpleadoPrestamoTasaSubsidiada>(InfoEmpleadoPrestamoTasaSubsidiada.class)));

            parametros.add(new SqlOutParameter(OUT_COD_RET, OracleTypes.NUMBER));
            parametros.add(new SqlOutParameter(OUT_DESC_RET, OracleTypes.VARCHAR));

            SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CUIT, cuit);
            Map<String, Object> respuesta = consultar(PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE, CONSULTAR_PRESTAMO_ATP_VIGENTE, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));

            LOGGER.info("Estado prestamo: {} .", respuesta.get(R_ESTADO));
            
            BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_COD_RET);

            if (BigDecimal.ZERO.equals(codRetorno)) {     	
            	prestamoConfig.setId((BigDecimal) respuesta.get(R_ID));
            	prestamoConfig.setSolicitud((BigDecimal) respuesta.get(R_SOLICITUD));
            	prestamoConfig.setTasa((BigDecimal) respuesta.get(R_TASA));
            	prestamoConfig.setFechaAlta((String) respuesta.get(R_FECHA_ALTA));
            	prestamoConfig.setEstado((String) respuesta.get(R_ESTADO));
            	prestamoConfig.setTipoCliente((String) respuesta.get(R_TIPO_CLIENTE));
            	prestamoConfig.setDatos((String) respuesta.get(R_DATOS));
            	prestamoConfig.setPeriodo((String) respuesta.get(R_PERIODO));

            	listaEmpleados = (List<InfoEmpleadoPrestamoTasaSubsidiada>) respuesta.get(R_EMPLEADOS);
				if (CollectionUtils.isNotEmpty(listaEmpleados)) {
					for (InfoEmpleadoPrestamoTasaSubsidiada item : listaEmpleados) {
						InfoEmpleadoPrestamoTasaSubsidiada infoEmpleado = new InfoEmpleadoPrestamoTasaSubsidiada();
						infoEmpleado.setId(item.getId());
						infoEmpleado.setCbu(item.getCbu());
						infoEmpleado.setCuit(item.getCuit());
						infoEmpleado.setMonto(item.getMonto());
						listaEmpleadosEntity.add(infoEmpleado);
					}
				}
				prestamoConfig.setEmpleados(listaEmpleadosEntity);
            	
            } else {
            	prestamoConfig.setEstado("INHABILITADO");
            }
            
        } catch (SQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}.", PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE, CONSULTAR_PRESTAMO_ATP_VIGENTE, e);
            throw new DAOException(e);
        } catch (UncategorizedSQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", PRESTAMOS_SUELDOS_SCHEMA, CONSULTAR_PRESTAMO_ATP_VIGENTE,
            		PRESTAMOS_SUELDOS_CONSULTAR, e);
            throw new DAOException(e);
        } catch (InvalidDataAccessApiUsageException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", PRESTAMOS_SUELDOS_SCHEMA, CONSULTAR_PRESTAMO_ATP_VIGENTE,
            		PRESTAMOS_SUELDOS_CONSULTAR, e);
            throw new DAOException(e);
        }
		return prestamoConfig;
	}
	
	@Override
	public String altaPrestamoSueldoTasaSubsidiada(PrestamoSueldoTasaSubsidiadaView prestamoSueldoView,
			ComprobantePrestamoTasaSubsidiadaView comprobante, Cliente cliente, Cuenta cuentaSeleccionada) throws DAOException {
		
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
        parametros.add(new SqlParameter(R_ID, OracleTypes.NUMBER));
        parametros.add(new SqlParameter(IN_NUP, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(IN_CUIT, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(R_SOLICITUD, OracleTypes.NUMBER));
        parametros.add(new SqlParameter(IN_P_MONTO_SOLICITADO, OracleTypes.NUMBER));
        parametros.add(new SqlParameter(P_CANAL, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(P_PRODUCTOCUENTA, OracleTypes.NUMBER));
        parametros.add(new SqlParameter(P_SUBPRODUCTOCUENTA, OracleTypes.NUMBER));
        parametros.add(new SqlParameter(P_SUCURSALCUENTA, OracleTypes.NUMBER));
        parametros.add(new SqlParameter(P_NUMEROCUENTA, OracleTypes.NUMBER));
        parametros.add(new SqlParameter(P_ENTIDAD, OracleTypes.NUMBER));
        parametros.add(new SqlParameter(P_TIPO_DOCUMENTO, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(P_DOCUMENTO, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(P_MAIL, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(P_CANTIDAD_CUOTAS, OracleTypes.NUMBER));
        parametros.add(new SqlParameter(P_SISTEMA_AMORTIZACION, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(P_TIPO_TASA, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(P_PERIODICIDAD, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(P_TASA_NOMINAL_ANUAL, OracleTypes.NUMBER));
        parametros.add(new SqlParameter(P_PAGO_PRIM_CUOTA, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(P_MOTIVO_PRESTAMO, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(P_NRO_OPERACION, OracleTypes.VARCHAR));
        parametros.add(new SqlParameter(P_ESTADO, OracleTypes.NUMBER));
        parametros.add(new SqlParameter(P_DATOS, OracleTypes.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_COD_RET, OracleTypes.NUMBER));
        parametros.add(new SqlOutParameter(OUT_DESC_RET, OracleTypes.VARCHAR));
        
        String cuit = cliente.getNumeroCUILCUIT().replaceAll("-", "");
        BigDecimal tasaInteres = new BigDecimal(prestamoSueldoView.getTasaInteres());
        
        Map<String, Object> respuesta;
		try {
			Date fechaHoy = new Date();
	        String nroComprobante= String.valueOf(fechaHoy.getTime()).substring(0, 9);
			SqlParameterSource in = new MapSqlParameterSource()
					.addValue(R_ID, comprobante.getIdPrestamos())
					.addValue(IN_NUP, cliente.getNup())
					.addValue(IN_CUIT, cuit)
					.addValue(R_SOLICITUD, comprobante.getIdSolicitud())
					.addValue(IN_P_MONTO_SOLICITADO, comprobante.getMonto())
					.addValue(P_CANAL, CANAL)
					.addValue(P_PRODUCTOCUENTA, Integer.parseInt(cuentaSeleccionada.getProductoAltair()))
					.addValue(P_SUBPRODUCTOCUENTA, Integer.parseInt(cuentaSeleccionada.getSubproductoAltair()))
					.addValue(P_SUCURSALCUENTA, Integer.parseInt(cuentaSeleccionada.getSucursalPaquete()))
					.addValue(P_NUMEROCUENTA, Integer.parseInt(cuentaSeleccionada.getNroCuentaProducto()))
					.addValue(P_ENTIDAD, Integer.parseInt(ENTIDAD))
					.addValue(P_TIPO_DOCUMENTO, cliente.getTipoDocumento())
					.addValue(P_DOCUMENTO, cliente.getDni())
					.addValue(P_MAIL, prestamoSueldoView.getMail())
					.addValue(P_CANTIDAD_CUOTAS, Integer.parseInt(CANTIDAD_CUOTAS_TASA_SUBS))
					.addValue(P_SISTEMA_AMORTIZACION, SISTEMA_AMORTIZACION)
					.addValue(P_TIPO_TASA, TIPO_TASA)
					.addValue(P_PERIODICIDAD, PERIODICIDAD)
					.addValue(P_TASA_NOMINAL_ANUAL, tasaInteres)
					.addValue(P_PAGO_PRIM_CUOTA, obtenerFechaPrimerPagoCuota(fechaHoy))
					.addValue(P_MOTIVO_PRESTAMO, MOTIVO_PRESTAMO)
					.addValue(P_NRO_OPERACION, "0")
					.addValue(P_ESTADO, ESTADO)
					.addValue(P_DATOS, comprobante.getDatos());
			
			respuesta = consultar(PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE, ALTA_PRESTAMO_ATP_VIGENTE, in,
			        parametros.toArray(new SqlParameter[parametros.size()]));
			
			BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_COD_RET);
			if (BigDecimal.ZERO.equals(codRetorno)) {
				return nroComprobante;
			} else {
				LOGGER.info("Descripción de error: {} .", respuesta.get(OUT_DESC_RET));
				throw new DAOException();	
			}
			
		} catch (SQLException e) {
            throw new DAOException(e);
        } catch (UncategorizedSQLException e) {
            throw new DAOException(e);
        } catch (InvalidDataAccessApiUsageException e) {
            throw new DAOException(e);
        } catch (NumberFormatException e) {
            throw new DAOException(e);
        }
	}
	
	
	/**
	 * Calcula 90 dias
	 * @param fechaHoy
	 * @return
	 */
	private String obtenerFechaPrimerPagoCuota(Date fechaHoy) {
	        Calendar c = Calendar.getInstance(); 
	        c.setTime(fechaHoy); 
	        c.add(Calendar.DAY_OF_YEAR, 90);
	        fechaHoy = c.getTime();        
	        DateFormat dateFormat = new SimpleDateFormat(FORMATO_TIMESTAMP);  
	        return dateFormat.format(fechaHoy);
	}
	

	@Override
	public String agregarCBU(PrestamoSueldosAgregarCBUEntity agregarCBUEntity) throws DAOException {

    	try {
            List<SqlParameter> parametros = new ArrayList<SqlParameter>();
            parametros.add(new SqlParameter(P_ID, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_CBU, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(P_DATOS, OracleTypes.VARCHAR));

            parametros.add(new SqlOutParameter(OUT_COD_RET, OracleTypes.NUMBER));
            parametros.add(new SqlOutParameter(OUT_DESC_RET, OracleTypes.VARCHAR));

            SqlParameterSource in = new MapSqlParameterSource()
            			.addValue(P_ID, agregarCBUEntity.getId())
            			.addValue(P_CBU, agregarCBUEntity.getCbu())
            			.addValue(P_DATOS, agregarCBUEntity.getDatos());

            Map<String, Object> respuesta = consultar(PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE, ACTUALIZAR_CBU, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));
            
            BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_COD_RET);
            
            return codRetorno.toString();
            
        } catch (SQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}.", PRESTAMOS_SUELDOS_SCHEMA, PRESTAMOS_SUELDOS_PACKAGE, ACTUALIZAR_CBU, e);
            throw new DAOException(e);
        } catch (UncategorizedSQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", PRESTAMOS_SUELDOS_SCHEMA, ACTUALIZAR_CBU,
            		PRESTAMOS_SUELDOS_CONSULTAR, e);
            throw new DAOException(e);
        } catch (InvalidDataAccessApiUsageException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", PRESTAMOS_SUELDOS_SCHEMA, ACTUALIZAR_CBU,
            		PRESTAMOS_SUELDOS_CONSULTAR, e);
            throw new DAOException(e);
        }
		
	}
	
}
