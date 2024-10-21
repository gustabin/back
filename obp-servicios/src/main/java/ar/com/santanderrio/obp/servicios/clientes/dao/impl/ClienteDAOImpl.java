/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.clientes.dao.ClienteDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteConSaldoResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.CtasTitEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosConsultaCtasEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.TitularesEntity;
import ar.com.santanderrio.obp.servicios.clientes.view.RequestCtasTitServiceView;
import ar.com.santanderrio.obp.servicios.clientes.view.ResponseCtasTitServiceView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.delete.account.utils.AccountUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.dao.CuentaTituloDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloOutEntity;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.ValidacionSaldo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class ClienteDAOImpl.
 */
/**
 * @author sergio.e.goldentair
 *
 */
@TargetSystem(DataBase.ESTADISTICAS)
@Component
public class ClienteDAOImpl implements ClienteDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The cuenta titulo DAO. */
	@Autowired
	private CuentaTituloDAO cuentaTituloDAO;
	
	@Autowired
	private RestWebClient restWebClient;

	/** The prefijo idecltsdo. */
	@Value("${SERVICIO.PREFIJO.IDECLTSDO}")
	private String prefijoIdecltsdo;

	/** The version180. */
	@Value("${SERVICIO.VERSION.180}")
	private String version180;
	
	@Value("${FONDOS.CANALTIPO}")
	private String canalTipo;

	/** The canal id. */
	@Value("${FONDOS.CANALID}")
	private String canalId;

	/** The canal version. */
	@Value("${FONDOS.CANALVERSION}")
	private String canalVersion;

	/** The subcanal tipo. */
	@Value("${FONDOS.SUBCANALTIPO}")
	private String subcanalTipo;

	/** The subcanal id. */
	@Value("${FONDOS.SUBCANALID}")
	private String subcanalId;

	/** The dato. */
	@Value("${INVERSIONES.FIRMA.DATO:Prueba}")
	private String dato;

	/** The firmar. */
	@Value("${INVERSIONES.FIRMAR:true}")
	private String firmar;
	
	@Autowired
	private InversionWSHelper inversionWSHelper;

	/** The Constant IGNORE_CASE_3001. */
	private static final String IGNORE_CASE_3001 = "3001";

	/** The Constant IGNORE_CASE_3101. */
	private static final String IGNORE_CASE_3101 = "3101";
	
	/** The Constant IGNORE_CASE_2018. */
	private static final String IGNORE_CASE_2018 = "2018";
	
	/** The Constant IGNORE_CASE_2019. */
	private static final String IGNORE_CASE_2019 = "2019";

	/** The Constant SUCURSAL_250. */
	private static final int SUCURSAL_250 = 250;

	/** The Constant SUCURSAL_259. */
	private static final int SUCURSAL_259 = 259;

	/** ALTAIR PLATA. */
	private static final String SUBPRODUCTO_ALTAIR_PLATA = "PLAT";

	/** The Constant LARGO_CTA_TIT. */
	private static final int LARGO_CTA_TIT = 16;
	
	private static final String NOMBRE_WS="ORDENES.TENENCIAS";
	
	private static final String SEGMENTO_RTL="RTL";
	
	private static final String SEGMENTO_BP="BP";
	
	private static final String REPATRIACION="REPATRIACION";

	private static final Integer CODIGO_SUBPRODUCTO_REPATRIACION = 7;
	
	private static final String CODIGO_PRODUCTO_REPATRIACION_2021 = "0003";

	private static final String CODIGO_SUBPRODUCTO_REPATRIACION_2021 = "0007";

	private final String pathConsultarCtasTit="ConsultaCuentasCustodia";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.clientes.dao.ClienteDAO#obtenerCliente(ar.
	 * com.santanderrio.obp.base.clientes.entities.ResumenCliente)
	 */
	@Override
	public ClienteConSaldoResponse obtenerCliente(ResumenCliente resumenCliente) {
		return obtenerCliente(resumenCliente, Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.clientes.dao.ClienteDAO#
	 * obtenerClienteSinSessionUsuario(ar.com.santanderrio.obp.base.clientes.
	 * entities.ResumenCliente)
	 */
	@Override
	public ClienteConSaldoResponse obtenerClienteSinSessionUsuario(ResumenCliente resumenCliente) {
		return obtenerCliente(resumenCliente, Boolean.FALSE);
	}

	/**
	 * Poder generar request a iatx sin usuario de session.
	 *
	 * @param resumenCliente the resumen cliente
	 * @param conSession     the con session
	 * @return the cliente con saldo response
	 */
	private ClienteConSaldoResponse obtenerCliente(ResumenCliente resumenCliente, Boolean conSession) {
		ClienteConSaldoResponse clienteConSaldoResponse = new ClienteConSaldoResponse();

		try {
			IatxRequest request = new IatxRequest(prefijoIdecltsdo, version180);
			IatxRequestData requestData = new IatxRequestData(resumenCliente, conSession);
			
			// nro tarjeta
			requestData.addBodyValue("");
			// certificado digital
			requestData.addBodyValue("");
			// pin certificado digital
			requestData.addBodyValue("");
			request.setData(requestData);
			IatxResponse iatxResponse = iatxComm.exec(request);
			
			if (EstadoRespuesta.ERROR.equals(iatxResponse.getEstadoRespuesta())
					&& iatxResponse.getErrorCode() != AccountUtils.ERROR_SALDO.intValue()) {
				throw new IatxException();
			}
			Cliente cliente = parsearCliente(resumenCliente, iatxResponse);
			clienteConSaldoResponse.setCliente(cliente);
			clienteConSaldoResponse.setCodigoRespuesta(iatxResponse.getErrorCode());
			clienteConSaldoResponse.setEstadoRespuesta(iatxResponse.getEstadoRespuesta());

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			clienteConSaldoResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);

		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			clienteConSaldoResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
		}
		return clienteConSaldoResponse;
	}

	/**
	 * Parsear cliente.
	 *
	 * @param resumenCliente the resumen cliente
	 * @param iatxResponse   the iatx response
	 * @return the cliente
	 * @throws DAOException the DAO exception
	 */
	private Cliente parsearCliente(ResumenCliente resumenCliente, IatxResponse iatxResponse) throws DAOException {

		Cliente cliente = buildCliente(resumenCliente, iatxResponse);

		// fechaNac
		iatxResponse.getNextData();

		cliente.setTipoPersona(iatxResponse.getNextData());
		// // nup
		iatxResponse.getNextData();
		// // idRacf
		iatxResponse.getNextData();
		// // pwRacf
		iatxResponse.getNextData();

		// calculo la cantidad total de cuentas
		int cantCtas = Integer.parseInt(iatxResponse.getNextData());
		int nextCta = 0;
		int nextCtaBPriv = 0;
		int nextCtaRetail = 0;
		List<Cuenta> cuentas = cliente.getCuentas();
		List<Cuenta> cuentasbPriv = cliente.getCuentasPrivadas();
		List<Cuenta> cuentasRetail = cliente.getCuentasRetail();
		
		for (int n = 0; n < cantCtas; ++n) {
			
			Cuenta cta = buildCuenta(iatxResponse, cliente);
			cta.setRepatriacion(TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo().equals(Integer.parseInt(cta.getProductoAltair()))
				&& CODIGO_SUBPRODUCTO_REPATRIACION.equals(Integer.parseInt(cta.getSubproductoAltair())));
			cta.setRepatriacion(isCuentaRepatriacion(cta));

			// si es CU, la agrego solo si todavia no lo hice
			if (cta.getTipoCuenta().equals(Cuenta.TIPOCTA_CUP) || cta.getTipoCuenta().equals(Cuenta.TIPOCTA_CUD)) {
				if (isSucBancaPrivada(cta)) {
					Cuenta c = getCuentaBPrivadaFromTipoSucNro(cliente, Cuenta.TIPOCTA_CU, cta.getNroSucursal(),
							cta.getNroCuentaProducto());
					cliente.setClienteBancaPrivada(true);
					// ya estaba, solo actualizo el saldo
					if (c != null) {
						if (cta.getTipoCuenta().equals(Cuenta.TIPOCTA_CUP)) {
							c.setSaldoCUPesos(cta.getSaldoCUPesos());
						} else {
							c.setSaldoCUDls(cta.getSaldoCUDls());
						}
					} else {
						cta.setTipoCuenta(Cuenta.TIPOCTA_CU);
						cta.setTipoCuentaSinUnificarDls(Cuenta.TIPOCTA_CUD);
						cta.setIndex(nextCtaBPriv++);
						cuentasbPriv.add(cta);
					}
				} else {
					Cuenta c = getCuentaFromTipoSucNro(cliente, Cuenta.TIPOCTA_CU, cta.getNroSucursal(),
							cta.getNroCuentaProducto());
					// ya estaba, solo actualizo el saldo
					if (c != null) {
						if (cta.getTipoCuenta().equals(Cuenta.TIPOCTA_CUP)) {
							c.setSaldoCUPesos(cta.getSaldoCUPesos());
						} else {
							c.setSaldoCUDls(cta.getSaldoCUDls());
						}
					} else {
						cta.setTipoCuenta(Cuenta.TIPOCTA_CU);
						cta.setTipoCuentaSinUnificarDls(Cuenta.TIPOCTA_CUD);
						cta.setIndex(nextCta++);
						cuentas.add(cta);
					}
				}
			} else {
				if (isSucBancaPrivada(cta) || isCuentaTituloBancaPrivada(cta)) {
					cta.setIndex(nextCtaBPriv++);
					cuentasbPriv.add(cta);
					cliente.setClienteBancaPrivada(true);
				} else if (cta.getTipoCuenta().equals(Cuenta.TIPOCTA_TITULO)) {
					cta.setIndex(nextCtaRetail++);
					cuentasRetail.add(cta);
					
				} else {
					cta.setIndex(nextCta++);
					cuentas.add(cta);
				}
				
			}
			
			cliente = setTipoCliente(cliente, cta);
		}
		
		try {
			cliente=incluirRepatriacion(cliente);
		}catch(Exception exc) {
			LOGGER.error("Error al incluir Repatriacion", exc);
		}
		
		if (!cliente.getCuentasPrivadas().isEmpty()) {
			//Cuando esté el servicio nuevo todo esto ya se hace con el response
			vincularOperativasTitulo(cliente);
		}
		
		return cliente;
	}

	private boolean isCuentaRepatriacion(Cuenta cuenta) {
		return TipoCuenta.CAJA_AHORRO_DOLARES.equals(cuenta.getTipoCuentaEnum()) &&
			CODIGO_PRODUCTO_REPATRIACION_2021.equals(cuenta.getProductoAltair()) &&
				CODIGO_SUBPRODUCTO_REPATRIACION_2021.equals(cuenta.getSubproductoAltair());

	}

	private Cliente incluirRepatriacion(Cliente cliente) {
		ResponseCtasTitServiceView cuentasTit=consultarCtasTitService(cliente.getNup());
		/*
		 * SE VALIDA QUE EL SERVICIO HAYA RESPONDIDO CON CODIGO 0 OK
		 */
		if(cuentasTit!=null && cuentasTit.getCodigo()==0) {
			if(cuentasTit.getDatos().getCuentas().size()>0) {
				
				cliente=repatriacionTitRtl(cliente,cuentasTit);
				cliente=repatriacionTitBP(cliente,cuentasTit);
				
			}
		}
		return cliente;
	}

	private Cliente repatriacionTitBP(Cliente cliente, ResponseCtasTitServiceView cuentasTit) {
		List<CtasTitEntity> ctasBP=new ArrayList<CtasTitEntity>();
		ctasBP=obtenerCtasRep(cuentasTit,SEGMENTO_BP);
		List<Cuenta> cuentasTitRepBP=new ArrayList<Cuenta>();
		List<Cuenta> ctas=new ArrayList<Cuenta>(cliente.getCuentasRetail());
		try {
			for(Cuenta cta : ctas) {
				for(CtasTitEntity ctaSvc : ctasBP) {
					if(ctaSvc.getNumeroCtaTitulo()==Long.parseLong(cta.getNroCuentaProducto())) {
						List<Interviniente> intervinientes=new ArrayList<Interviniente>();
						for(TitularesEntity titul : ctaSvc.getTitulares()) {
							Interviniente interv = new Interviniente();
							interv.setNombre(titul.getNombre());
							interv.setApellido(titul.getApellido());
							interv.setOrdenParticipacion(titul.getParticipacion());
							intervinientes.add(interv);
						}
						int cuentaOpAsociada=ctaSvc.getCuentasOp().get(0).getNumeroCuentaOp();
						cta.setIntervinientes(intervinientes);
						cta.setRepatriacion(true);
						cta.setCuentaOPRepatriacion(cuentaOpAsociada);
						cta.setSucursalCtaOpRep(ctaSvc.getCuentasOp().get(0).getSucursal());
						// Se agrega la cuenta titulo de repatriacion de BP, a cuentas Privadas
						cliente.getCuentasPrivadas().add(cta);
						// Se agrega la cuenta titulo de repatriacion de BP, a cuentasTitBPRepatriacion
						cuentasTitRepBP.add(cta);
						// Se elimina de Cuentas Retail, la cuenta titulo de repatriacion de BP
						cliente.getCuentasRetail().remove(cta);
						for(Cuenta ctaOpBP : cliente.getCuentasPrivadas()) {
							if(ctaOpBP.getCbu().substring(4,7).equals(ctaSvc.getCuentasOp().get(0).getSucursal())
									&& Integer.parseInt(ctaOpBP.getCbu().substring(14,21))==ctaSvc.getCuentasOp().get(0).getNumeroCuentaOp()){
								ctaOpBP.setRepatriacion(true);
							}
						}
					}
				}
			}
			cliente.setCuentasTitBPRepatriacion(cuentasTitRepBP);
			return cliente;
		} catch (Exception ex) {
			LOGGER.error("Error al identificar cuentas de repatriacion de Banca Privada. ", ex);
			return null;
		}
	}

	private Cliente repatriacionTitRtl(Cliente cliente, ResponseCtasTitServiceView cuentasTit) {

		List<CtasTitEntity> ctasRTL=new ArrayList<CtasTitEntity>();
		ctasRTL=obtenerCtasRep(cuentasTit,SEGMENTO_RTL);
		List<Cuenta> cuentasTitRepRtl=new ArrayList<Cuenta>();
		List<Cuenta> ctas=new ArrayList<Cuenta>(cliente.getCuentasRetail());
		List<Cuenta> ctasOPRtl=cliente.getCuentas();
		for(Cuenta cta : ctas) {
			
			for(CtasTitEntity ctaSvc : ctasRTL) {
				if(ctaSvc.getNumeroCtaTitulo()==Long.parseLong(cta.getNroCuentaProducto())) {
					cliente.getCuentasRetail().remove(cta);
					List<Interviniente> intervinientes=new ArrayList<Interviniente>();
					for(TitularesEntity titul : ctaSvc.getTitulares()) {
						Interviniente interv = new Interviniente();
						interv.setNombre(titul.getNombre());
						interv.setApellido(titul.getApellido());
						interv.setOrdenParticipacion(titul.getParticipacion());
						intervinientes.add(interv);
					}
					int cuentaOpAsociada=ctaSvc.getCuentasOp().get(0).getNumeroCuentaOp();
					cta.setIntervinientes(intervinientes);
					cta.setRepatriacion(true);
					cta.setCuentaOPRepatriacion(cuentaOpAsociada);
					cta.setSucursalCtaOpRep(ctaSvc.getCuentasOp().get(0).getSucursal());
					
					for(Cuenta ctaOpRTL : cliente.getCuentas()) {
						if(ctaOpRTL.getCbu().substring(4,7).equals(ctaSvc.getCuentasOp().get(0).getSucursal())
								&& Integer.parseInt(ctaOpRTL.getCbu().substring(14,21))==ctaSvc.getCuentasOp().get(0).getNumeroCuentaOp()){
							ctaOpRTL.setRepatriacion(true);
						}
					}
					
					cuentasTitRepRtl.add(cta);
				}
				
				if(ctaSvc.getNumeroCtaTitulo()==0) {
					for(Cuenta ctaOpRtl : ctasOPRtl) {
						if(ctaSvc.getCuentasOp().get(0).getNumeroCuentaOp()==Long.parseLong(ctaOpRtl.getNroCuentaProducto())) {
							ctaOpRtl.setRepatriacion(true);
						}
					}
				}
				
			}
		}
		cliente.setCuentasTitRtlRepatriacion(cuentasTitRepRtl);
		return cliente;
	}

	private List<CtasTitEntity> obtenerCtasRep(ResponseCtasTitServiceView cuentasTit, String segmento) {
		List<CtasTitEntity> listaCtas = new ArrayList<CtasTitEntity>();
			for(CtasTitEntity cta : cuentasTit.getDatos().getCuentas()) {
				if(cta.getSegmento().equals(segmento) && cta.getEstado().equals(REPATRIACION)) {
					listaCtas.add(cta);
				}
			}
		return listaCtas;
	}

	/**
	 * Asigna los pares de cuentas titulo y operatias al cliente Las guarda en
	 * cuentasBancaPrivada Las obtiene de la lista de cuentas privadas ya cargadas.
	 *
	 * @param cliente the cliente
	 */
	private void vincularOperativasTitulo(Cliente cliente) {
		CuentaTituloInEntity inEntity = new CuentaTituloInEntity();
		inEntity.setCliente(cliente);
		CuentaTituloOutEntity outEntityLoadAtits;
		List<CuentaBancaPrivada> listaCuentasBancaPrivada = cliente.getCuentasBancaPrivada();
		
		try {
			outEntityLoadAtits = cuentaTituloDAO.obtenerCuentasTitulo(inEntity);
			Iterator<Cuenta> iteratorCuentasPrivadas = cliente.getCuentasPrivadas().iterator();
			while (iteratorCuentasPrivadas.hasNext()) {
				Cuenta cuenta = iteratorCuentasPrivadas.next();
				if (!cuenta.getTipoCuenta().equals(Cuenta.TIPOCTA_TITULO)) {
//					String nroCuentaOperatFormateado = "7"
					String nroCuentaOperatFormateado = Integer.parseInt(cuenta.getProductoAltair())
							+ llenarConCerosIzqOTruncar(cuenta.getNroCuentaProducto(), 9);
					Iterator<CuentaBP> iteratorRelacionesCuentas = outEntityLoadAtits.relacionesOperativaTitulo()
							.iterator();
					while (iteratorRelacionesCuentas.hasNext()) {
						CuentaBP relacionCuentas = iteratorRelacionesCuentas.next();
						if (relacionCuentas.getCuentaOp().equals(nroCuentaOperatFormateado)) {
							Cuenta cuentaTitulo = buscarCuenta(relacionCuentas.getCuentaTit(),
									cliente.getCuentasPrivadas());
							if (cuentaTitulo != null) {
								CuentaBancaPrivada cuentaBancaPrivada = new CuentaBancaPrivada();
								cuentaBancaPrivada.setCuentaOperativa(cuenta);
								cuentaBancaPrivada.setCuentaTitulo(cuentaTitulo);
								listaCuentasBancaPrivada.add(cuentaBancaPrivada);
							}
						}
					}
				}
			}
		} catch (DAOException ex) {
			LOGGER.error("Error al cargar las cuentas titulo. ", ex);
		}
		
	}

	/**
	 * Busca la cuenta titulo recibida dentro de la lista recibida.
	 *
	 * @param cuentaTitBuscada the cuenta tit buscada
	 * @param cuentasPrivadas  the cuentas privadas
	 * @return Cuenta
	 */
	private Cuenta buscarCuenta(String cuentaTitBuscada, List<Cuenta> cuentasPrivadas) {
		Iterator<Cuenta> iterator = cuentasPrivadas.iterator();
		cuentaTitBuscada = StringUtils.leftPad(cuentaTitBuscada, LARGO_CTA_TIT, "0");
		while (iterator.hasNext()) {
			Cuenta cuenta = iterator.next();
			if (cuenta.getNroCuentaProducto().equals(cuentaTitBuscada)) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Llenar con ceros izq O truncar.
	 *
	 * @param s    the s
	 * @param size the size
	 * @return the string
	 */
	protected String llenarConCerosIzqOTruncar(String s, int size) {
		int l = s.length();
		if (l >= size) {
			return s.substring(l - size, l);
		}
		StringBuffer ceros = new StringBuffer();
		for (int n = 0; n < size - l; ++n) {
			ceros.append("0");
		}
		return ceros + s;
	}

	/**
	 * Sets the tipo cliente.
	 *
	 * @param cliente the cliente
	 * @param cta     the cta
	 * @return the cliente
	 */
	private Cliente setTipoCliente(Cliente cliente, Cuenta cta) {
		// ID-BIMA1614273
		if (cta.getSubproductoAltair().equals(SUBPRODUCTO_ALTAIR_PLATA) && "H".equals(cta.getClaseCuenta())) {
			cliente.setClienteSelect(true);
		} else if (cta.getSubproductoAltair().equals(SUBPRODUCTO_ALTAIR_PLATA)) {
			cliente.setClientePlatinum(true);
		} else if ("ORO ".equals(cta.getSubproductoAltair())) {
			cliente.setClienteGold(true);
		}
		// FIN ID-BIMA1614273
		return cliente;
	}

	/**
	 * Builds the cuenta.
	 *
	 * @param iatxResponse the iatx response
	 * @param cliente      the cliente
	 * @return the cuenta
	 * @throws DAOException the DAO exception
	 */
	private Cuenta buildCuenta(IatxResponse iatxResponse, Cliente cliente) throws DAOException {
		
		Cuenta cta = new Cuenta();
		cta.setCliente(cliente);
		String tipoCuentaString = iatxResponse.getNextData();
		cta.setTipoCuenta(tipoCuentaString);
		cta.setTipoCuentaEnum(obtenerTipoCuentaEnum(tipoCuentaString));
		cta.setTipoCuentaSinUnificar(tipoCuentaString);
		cta.setNroSucursal(iatxResponse.getNextData());
		cta.setNroCuentaProducto(iatxResponse.getNextData());
		cta.setNroOrdenFirmante(iatxResponse.getNextData());
		cta.setCodigoAplicacion(iatxResponse.getNextData());
		cta.setJerarquiaCuenta(iatxResponse.getNextData());
		cta.setClaseCuenta(iatxResponse.getNextData());
		cta.setFormaDeOperar(iatxResponse.getNextData());

		// datos de la tarjeta (20)
		cta.setNroTarjetaCredito(iatxResponse.getNextData());

		cta.setGrupoAfinidad(iatxResponse.getNextData());
		cta.setOficinaAltair(iatxResponse.getNextData());
		cta.setContratoAltair(iatxResponse.getNextData());
		cta.setEstadoTarjetaCredito(iatxResponse.getNextData());
		cta.setCalidadDeParticipacion(iatxResponse.getNextData());
		cta.setCodigoTitularidad(cta.getCalidadDeParticipacion());

		// datos del paquete
		cta.setCodigoPaquete(iatxResponse.getNextData());
		cta.setClasePaquete(iatxResponse.getNextData());
		cta.setSucursalPaquete(iatxResponse.getNextData());
		cta.setNumeroPaquete(iatxResponse.getNextData());

		String sdoCta = iatxResponse.getNextData();
		if (cta.getTipoCuenta().equals(Cuenta.TIPOCTA_CUP)) {
			cta.setSaldoCUPesos(ISBANStringUtils.importePtoFijo2Canonico(sdoCta));

			if (Integer.parseInt(cta.getNroTarjetaCredito()) == 1) {
				cta.setConvenioPAS(true);
			}
		} else if (cta.getTipoCuenta().equals(Cuenta.TIPOCTA_CUD)) {
			cta.setSaldoCUDls(ISBANStringUtils.importePtoFijo2Canonico(sdoCta));
		} else {
			cta.setSaldoCuenta(ISBANStringUtils.importePtoFijo2Canonico(sdoCta));
		}

		cta.setSaldoParaConformar(iatxResponse.getNextData());
		cta.setDepositoEfectivo(iatxResponse.getNextData());

		// otros datos altair
		cta.setCbu(iatxResponse.getNextData());
		cta.setProductoAltair(iatxResponse.getNextData());
		cta.setSubproductoAltair(iatxResponse.getNextData());
		cta.setMonedaAltair(iatxResponse.getNextData());

		// TODO: DESCOMENTAR LOGICA
		// si es recargable, le cambio el codigo
		if (TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo().equals(cta.getTipoCuenta())
				&& "T".equals(cta.getClaseCuenta())) {
			cta.setTipoCuenta(Cuenta.TIPOCTA_VISARECARGABLE);
		}
		
		return cta;
	}

	/**
	 * Obtener tipo cuenta enum.
	 *
	 * @param tipoCuentaString the tipo cuenta string
	 * @return the tipo cuenta
	 */
	private TipoCuenta obtenerTipoCuentaEnum(String tipoCuentaString) {
		if (tipoCuentaString == null || tipoCuentaString.trim().isEmpty()) {
			return null;
		}
		return TipoCuenta.fromCodigo(tipoCuentaString);
	}

	/**
	 * Builds the cliente.
	 *
	 * @param resumenCliente the resumen cliente
	 * @param iatxResponse   the iatx response
	 * @return the cliente
	 */
	private Cliente buildCliente(ResumenCliente resumenCliente, IatxResponse iatxResponse) {
		Cliente cliente = new Cliente(resumenCliente);

		cliente.setValorSinonimo(iatxResponse.getIndicSinonimo());
		// guardo todos los datos del cliente
		cliente.setTipoDocumento(iatxResponse.getNextData());
		cliente.setDni(iatxResponse.getNextData());

		cliente.setNombre(iatxResponse.getNextData().trim());
		cliente.setApellido1(iatxResponse.getNextData().trim());
		// ID 4276 - Migracion de cuentas - Se reutiliza el
		// campo apellido2 para recuperar si el
		// cliente posee cuentas migradas o no
		String apellido2 = iatxResponse.getNextData();
		cliente.setApellido2(apellido2.trim());
		cliente.setNova(esClienteNova(resumenCliente.getFechaNacimiento()));

		// Cierre de sucursales
		if (apellido2.length() >= 18 && 'M' == apellido2.charAt(17)) {
			cliente.setIsCuentaMigrada(true);
		} else {
			cliente.setIsCuentaMigrada(false);
		}
		return cliente;
	}
	
	
	/**
	 * Devuelve true si el cliente es un cliente nova (tiene entre 13 y 17 años)
	 * 
	 * @param fechaNacimiento
	 * @return
	 */
	private boolean esClienteNova(String fechaNacimiento) {
		if(fechaNacimiento == null || fechaNacimiento.isEmpty()) {
			LOGGER.error("El servicio {}{} no informa fecha de nacimiento",
					prefijoIdecltsdo, version180);
			return false;
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date fecNac = new Date();
		try {
			fecNac = format.parse(fechaNacimiento);
		} catch (ParseException e) {
			LOGGER.error("Error en el parseo de la fecha de nacimiento.", e);
		}
		Calendar fechaNac = new GregorianCalendar();
		Calendar hoy = Calendar.getInstance();
		fechaNac.setTime(fecNac);
		int edad = hoy.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
		int diffMes = hoy.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
		int diffDia = hoy.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
		if (diffMes < 0 || (diffMes == 0 && diffDia < 0)) {
			edad = edad - 1;
		}
		return edad >= 13 && edad <= 17;
	}

	/**
	 * Gets the cuenta from tipo suc nro.
	 *
	 * @param cliente           the cliente
	 * @param tipo              the tipo
	 * @param nroSucursal       the nro sucursal
	 * @param nroCuentaProducto the nro cuenta producto
	 * @return the cuenta from tipo suc nro
	 */
	private Cuenta getCuentaFromTipoSucNro(Cliente cliente, String tipo, String nroSucursal, String nroCuentaProducto) {
		try {
			int tip = Integer.parseInt(tipo);
			int suc = Integer.parseInt(nroSucursal);
			int nro = Integer.parseInt(nroCuentaProducto);
			for (int n = 0; n < cliente.getCuentas().size(); ++n) {
				Cuenta c = cliente.getCuentas().get(n);
				try {
					int tip1 = Integer.parseInt(c.getTipoCuenta());
					int suc1 = Integer.parseInt(c.getNroSucursal());
					int nro1 = Integer.parseInt(c.getNroCuentaProducto());
					if (tip == tip1 && suc == suc1 && nro == nro1) {
						return c;
					}
				} catch (NumberFormatException e) {
					LOGGER.info(e.getMessage());
				}
			}
		} catch (NumberFormatException e) {
			// abajo retorna null
		} catch (Exception e) {
			// abajo retorna null
		}
		return null;
	}

	/**
	 * Gets the cuenta b privada from tipo suc nro.
	 *
	 * @param cliente           the cliente
	 * @param tipo              the tipo
	 * @param nroSucursal       the nro sucursal
	 * @param nroCuentaProducto the nro cuenta producto
	 * @return the cuenta b privada from tipo suc nro
	 */
	private Cuenta getCuentaBPrivadaFromTipoSucNro(Cliente cliente, String tipo, String nroSucursal,
			String nroCuentaProducto) {
		try {
			int tip = Integer.parseInt(tipo);
			int suc = Integer.parseInt(nroSucursal);
			int nro = Integer.parseInt(nroCuentaProducto);
			for (int n = 0; n < cliente.getCuentasPrivadas().size(); ++n) {
				Cuenta c = cliente.getCuentasPrivadas().get(n);
				try {
					int tip1 = Integer.parseInt(c.getTipoCuenta());
					int suc1 = Integer.parseInt(c.getNroSucursal());
					int nro1 = Integer.parseInt(c.getNroCuentaProducto());
					if (tip == tip1 && suc == suc1 && nro == nro1) {
						return c;
					}
				} catch (NumberFormatException e) {
					LOGGER.info(e.getMessage());
				}
			}
		} catch (NumberFormatException e) {
			// abajo retorna null
		} catch (Exception e) {
			// abajo retorna null
		}
		return null;
	}

	/**
	 * Checks if is cuenta titulo banca privada.
	 *
	 * @param cta the cta
	 * @return true, if is cuenta titulo banca privada
	 */
	private boolean isCuentaTituloBancaPrivada(Cuenta cta) {
		return "ATIT".equalsIgnoreCase(cta.getCodigoAplicacion())
				&& (cta.getCodigoPaquete().equalsIgnoreCase(IGNORE_CASE_3001)
						|| cta.getCodigoPaquete().equalsIgnoreCase(IGNORE_CASE_3101)
						// SE CONTEMPLAN LOS CÓDIGOS DE PAQUETE PARA CUENTAS DE SINCERAMIENTO DE BANCA PRIVADA						
						|| cta.getCodigoPaquete().equalsIgnoreCase(IGNORE_CASE_2018)
						|| cta.getCodigoPaquete().equalsIgnoreCase(IGNORE_CASE_2019));
	}

	/**
	 * Checks if is suc banca privada.
	 *
	 * @param cta the cta
	 * @return true, if is suc banca privada
	 */
	private boolean isSucBancaPrivada(Cuenta cta) {
		int sucursal = Integer.parseInt(cta.getNroSucursal());
		return sucursal >= SUCURSAL_250 && sucursal <= SUCURSAL_259;
	}

	/**
	 * Gets the iatx comm.
	 *
	 * @return the iatx comm
	 */
	public IatxComm getIatxComm() {
		return iatxComm;
	}

	/**
	 * Setter para iatx comm.
	 *
	 * @param iatxComm el nuevo iatx comm
	 */
	public void setIatxComm(IatxComm iatxComm) {
		this.iatxComm = iatxComm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.clientes.dao.ClienteDAO#obtenerSaldo(ar
	 * .com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.nuevopago.entities.ValidacionSaldo)
	 */
	@Override
	public String obtenerSaldo(Cliente cliente, ValidacionSaldo validacionSaldo) throws DAOException {
		return null;
	}
	
	public ResponseCtasTitServiceView consultarCtasTitService(String nup) {
		
		ResponseCtasTitServiceView rta=new ResponseCtasTitServiceView();
		RequestCtasTitServiceView request=new RequestCtasTitServiceView();
		
		try {
			WebClient service=crearLlamadaAWebService(pathConsultarCtasTit);
			request=generarRequest(request,nup);
			rta=service.post(request,ResponseCtasTitServiceView.class);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return rta;
	}
	
	
	private RequestCtasTitServiceView generarRequest(RequestCtasTitServiceView request, String nup) {
		DatosConsultaCtasEntity datos = new DatosConsultaCtasEntity();
		datos.setNup(nup);
		datos.setUsuario("system");
		request.setCanal(canalTipo);
		request.setSubCanal(subcanalId);
		request.setDatos(datos);
		request.setDato(dato);
		request.setFirma(generarFirma());
		
		return request;
		
	}
	
	private String generarFirma() {
		String firma = "";
		if (Boolean.TRUE.equals(Boolean.parseBoolean(firmar))) {
			try {
				firma = inversionWSHelper.getDatosFirmados(dato);
			} catch (Exception e) {
				LOGGER.error("Error creando firma", e);
			}
		}
		return firma;
	}

	private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

		WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
        service.accept(MediaType.APPLICATION_JSON);
        service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        service.path(pathDeConsulta);
		return service;
	}
}
