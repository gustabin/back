package ar.com.santanderrio.obp.servicios.prestamos.entity;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Bean con datos de salida para el servicio ALTFOPMOPE
 * 
 * @author A309331
 *
 */
@Record
public class PrestamoPreaprobadoMonoproductoOutEntity {

	@Field
	private String headerTrama;

	/** codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;

	@JsonIgnore
	private String nroComprobante;

	@Field
	private String cft;
	@Field
	private String cftSimp;

	@Field(required = false)
	private String contieneSeccion1;
	@Field
	private String seccion1;
	@Field
	private String contieneSeccion2;
	@Field
	private String seccion2;
	@Field
	private String contieneSeccion3;
	@Field
	private String seccion3;
	@Field
	private String contieneSeccion4;
	@Field
	private String seccion4;
	@Field
	private String contieneSeccion5;
	@Field
	private String seccion5;
	@Field
	private String inicioSeccion1;
	@Field(minOccurs = 0)
	private String cuenta;
	@Field(minOccurs = 0)
	private String oficina;
	@Field(minOccurs = 0)
	private String entidad;
	@Field(minOccurs = 0)
	private String ofioper;
	@Field(minOccurs = 0)
	private String entoper;
	@Field(minOccurs = 0)
	private String entit;
	@Field(minOccurs = 0)
	private String oftit;
	@Field(minOccurs = 0)
	private String impconc;
	@Field(minOccurs = 0)
	private String divisa;
	@Field(minOccurs = 0)
	private String feforma;
	@Field(minOccurs = 0)
	private String impcarg;
	@Field(minOccurs = 0)
	private String divcarg;
	@Field(minOccurs = 0)
	private String fpagcar;
	@Field(minOccurs = 0)
	private String vtacarg;
	@Field(minOccurs = 0)
	private String oficarg;
	@Field(minOccurs = 0)
	private String entcarg;
	@Field(minOccurs = 0)
	private String entdoc;
	@Field(minOccurs = 0)
	private String sucdoc;
	@Field(minOccurs = 0)
	private String ctadoc;
	@Field(minOccurs = 0)
	private String tipodoc;
	@Field(minOccurs = 0)
	private String cpladoc;
	@Field(minOccurs = 0)
	private String numdoc;
	@Field(minOccurs = 0)
	private String impabon;
	@Field(minOccurs = 0)
	private String divabon;
	@Field(minOccurs = 0)
	private String fopagab;
	@Field(minOccurs = 0)
	private String ctabono;
	@Field(minOccurs = 0)
	private String ofiabon;
	@Field(minOccurs = 0)
	private String entabon;
	@Field(minOccurs = 0)
	private String titdoc;
	@Field(minOccurs = 0)
	private String pnumdoc;
	@Field(minOccurs = 0)
	private String priape;
	@Field(minOccurs = 0)
	private String segape;
	@Field(minOccurs = 0)
	private String nomper;
	@Field(minOccurs = 0)
	private String nomcal;
	@Field(minOccurs = 0)
	private String numbloc;
	@Field(minOccurs = 0)
	private String nomloc;
	@Field(minOccurs = 0)
	private String nomcom;
	@Field(minOccurs = 0)
	private String codpos;
	@Field(minOccurs = 0)
	private String pretel;
	@Field(minOccurs = 0)
	private String numtel;
	@Field(minOccurs = 0)
	private String desprod;
	@Field(minOccurs = 0)
	private String tna;
	@Field(minOccurs = 0)
	private String tea;
	@Field(minOccurs = 0)
	private String fevenci;
	@Field(minOccurs = 0)
	private String plazo;
	@Field(minOccurs = 0)
	private String totcomi;
	@Field(minOccurs = 0)
	private String totgast;
	@Field(minOccurs = 0)
	private String totivat;
	@Field(minOccurs = 0)
	private String totrimp;
	@Field(minOccurs = 0)
	private String cuopur;
	@Field(minOccurs = 0)
	private String feprivt;
	@Field(minOccurs = 0)
	private String nio;
	@Field(minOccurs = 0)
	private String inicioSeccion2;
	@Field(minOccurs = 0)
	private String codconl;
	@Field(minOccurs = 0)
	private String inddh;
	@Field(minOccurs = 0)
	private String tiptasa;
	@Field(minOccurs = 0)
	private String tipconc;
	@Field(minOccurs = 0)
	private String basecal;
	@Field(minOccurs = 0)
	private String conbase;
	@Field(minOccurs = 0)
	private String desconb;
	@Field(minOccurs = 0)
	private String indcolo;
	@Field(minOccurs = 0)
	private String codtpco;
	@Field(minOccurs = 0)
	private String impcon;
	@Field(minOccurs = 0)
	private String divcon;
	@Field(minOccurs = 0)
	private String tpcambi;
	@Field(minOccurs = 0)
	private String impcond;
	@Field(minOccurs = 0)
	private String divconc;
	@Field(minOccurs = 0)
	private String codconc;
	@Field(minOccurs = 0)
	private String impconcLiqGastos;
	@Field(minOccurs = 0)
	private String divcar;
	@Field(minOccurs = 0)
	private String impgast;
	@Field(minOccurs = 0)
	private String imprecu;
	@Field(minOccurs = 0)
	private String divisaLiqGastos;
	@Field(minOccurs = 0)
	private String cambiof;
	@Field(minOccurs = 0)
	private String indgast;
	@Field(minOccurs = 0)
	private String obsgast;
	@Field(minOccurs = 0)
	private String ofifcol;
	@Field(minOccurs = 0)
	private String numpers;
	@Field(minOccurs = 0)
	private String nombre;
	@Field(minOccurs = 0)
	private String tipcoll;
	@Field(minOccurs = 0)
	private String destpco;
	@Field(minOccurs = 0)
	private String fecinva;
	@Field(minOccurs = 0)
	private String tippol;
	@Field(minOccurs = 0)
	private String detppol;
	@Field(minOccurs = 0)
	private String codries;
	@Field(minOccurs = 0)
	private String descori;
	@Field(minOccurs = 0)
	private String numgar;
	@Field(minOccurs = 0)
	private String numbien;
	@Field(minOccurs = 0)
	private String titaseg;
	@Field(minOccurs = 0)
	private String impcuot;
	@Field(minOccurs = 0)
	private String divisaLiqSeguro;
	@Field(minOccurs = 0)
	private String titdocLiqAgente;
	@Field(minOccurs = 0)
	private String numdocu;
	@Field(minOccurs = 0)
	private String conivat;
	@Field(minOccurs = 0)
	private String produ;
	@Field(minOccurs = 0)
	private String plazoLiqAgente;
	@Field(minOccurs = 0)
	private String gastost;
	@Field(minOccurs = 0)
	private String ivagast;
	@Field(minOccurs = 0)
	private String impabonLiqAgente;
	@Field(minOccurs = 0)
	private String impesc;
	@Field(minOccurs = 0)
	private String impcrg;
	@Field(minOccurs = 0)
	private String tasat;
	@Field(minOccurs = 0)
	private String teaLiqAgente;
	@Field(minOccurs = 0)
	private String tcamope;
	@Field(minOccurs = 0)
	private String fecpliq;
	@Field(minOccurs = 0)
	private String impctot;
	@Field(minOccurs = 0)
	private String fecvenc;
	@Field(minOccurs = 0)
	private String codtiam;
	@Field(minOccurs = 0)
	private String gasescr;
	@Field(minOccurs = 0)
	private String agente;
	@Field(minOccurs = 0)
	private String numage;
	@Field(minOccurs = 0)
	private String nomcalLiqAgente;
	@Field(minOccurs = 0)
	private String numblo;
	@Field(minOccurs = 0)
	private String nomlocLiqAgente;
	@Field(minOccurs = 0)
	private String nomcomLiqAgente;
	@Field(minOccurs = 0)
	private String vodpos;
	@Field(minOccurs = 0)
	private String gastag;
	@Field(minOccurs = 0)
	private String dellado;
	@Field(minOccurs = 0)
	private String bcalcap;
	@Field(minOccurs = 0)
	private String tasaag;
	@Field(minOccurs = 0)
	private String tcpto;
	@Field(minOccurs = 0)
	private String bcalpr;
	@Field(minOccurs = 0)
	private String fpliqag;
	@Field(minOccurs = 0)
	private String ictpri;
	@Field(minOccurs = 0)
	private String ictpre;
	@Field(minOccurs = 0)
	private String tipbie;
	@Field(minOccurs = 0)
	private String cmarveh;
	@Field(minOccurs = 0)
	private String cmodveh;
	@Field(minOccurs = 0)
	private String nmotveh;
	@Field(minOccurs = 0)
	private String ncarveh;
	@Field(minOccurs = 0)
	private String domveh;
	@Field(minOccurs = 0)
	private String priapeLiqAgente;
	@Field(minOccurs = 0)
	private String segapeLiqAgente;
	@Field(minOccurs = 0)
	private String nomperLiqAgente;
	@Field(minOccurs = 0)
	private String tipdoc;
	@Field(minOccurs = 0)
	private String ndocag;
	@Field(minOccurs = 0)
	private String coniva;
	@Field(minOccurs = 0)
	private String bacaag;
	@Field(minOccurs = 0)
	private String ivaag;
	@Field(minOccurs = 0)
	private String iva3337;
	@Field(minOccurs = 0)
	private String cuotas;
	@Field(minOccurs = 0)
	private String tipcupo;
	@Field(minOccurs = 0)
	private String capital;
	@Field(minOccurs = 0)
	private String iva1com;
	@Field(minOccurs = 0)
	private String iva2com;
	@Field(minOccurs = 0)
	private String ingbrut;
	@Field(minOccurs = 0)
	private String endeuda;
	@Field(minOccurs = 0)
	private String otros;
	@Field(minOccurs = 0)
	private String imcuoti;
	@Field(minOccurs = 0)
	private String fvcuoti;
	@Field(minOccurs = 0)
	private String ctacagt;
	@Field(minOccurs = 0)
	private String oficagt;
	@Field(minOccurs = 0)
	private String entcagt;
	@Field(minOccurs = 0)
	private String impcagt;
	@Field(minOccurs = 0)
	private String tpremio;
	@Field(minOccurs = 0)
	private String ipreact;

	public String getCft() {
		return cft;
	}

	public void setCft(String cft) {
		this.cft = cft;
	}

	public String getCftSimp() {
		return cftSimp;
	}

	public void setCftSimp(String cftSimp) {
		this.cftSimp = cftSimp;
	}

	public String getHeaderTrama() {
		return headerTrama;
	}

	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getContieneSeccion1() {
		return contieneSeccion1;
	}

	public void setContieneSeccion1(String contieneSeccion1) {
		this.contieneSeccion1 = contieneSeccion1;
	}

	public String getSeccion1() {
		return seccion1;
	}

	public void setSeccion1(String seccion1) {
		this.seccion1 = seccion1;
	}

	public String getContieneSeccion2() {
		return contieneSeccion2;
	}

	public void setContieneSeccion2(String contieneSeccion2) {
		this.contieneSeccion2 = contieneSeccion2;
	}

	public String getSeccion2() {
		return seccion2;
	}

	public void setSeccion2(String seccion2) {
		this.seccion2 = seccion2;
	}

	public String getContieneSeccion3() {
		return contieneSeccion3;
	}

	public void setContieneSeccion3(String contieneSeccion3) {
		this.contieneSeccion3 = contieneSeccion3;
	}

	public String getSeccion3() {
		return seccion3;
	}

	public void setSeccion3(String seccion3) {
		this.seccion3 = seccion3;
	}

	public String getContieneSeccion4() {
		return contieneSeccion4;
	}

	public void setContieneSeccion4(String contieneSeccion4) {
		this.contieneSeccion4 = contieneSeccion4;
	}

	public String getSeccion4() {
		return seccion4;
	}

	public void setSeccion4(String seccion4) {
		this.seccion4 = seccion4;
	}

	public String getContieneSeccion5() {
		return contieneSeccion5;
	}

	public void setContieneSeccion5(String contieneSeccion5) {
		this.contieneSeccion5 = contieneSeccion5;
	}

	public String getSeccion5() {
		return seccion5;
	}

	public void setSeccion5(String seccion5) {
		this.seccion5 = seccion5;
	}

	public String getInicioSeccion1() {
		return inicioSeccion1;
	}

	public void setInicioSeccion1(String inicioSeccion1) {
		this.inicioSeccion1 = inicioSeccion1;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getOfioper() {
		return ofioper;
	}

	public void setOfioper(String ofioper) {
		this.ofioper = ofioper;
	}

	public String getEntoper() {
		return entoper;
	}

	public void setEntoper(String entoper) {
		this.entoper = entoper;
	}

	public String getEntit() {
		return entit;
	}

	public void setEntit(String entit) {
		this.entit = entit;
	}

	public String getOftit() {
		return oftit;
	}

	public void setOftit(String oftit) {
		this.oftit = oftit;
	}

	public String getImpconc() {
		return impconc;
	}

	public void setImpconc(String impconc) {
		this.impconc = impconc;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public String getFeforma() {
		return feforma;
	}

	public void setFeforma(String feforma) {
		this.feforma = feforma;
	}

	public String getImpcarg() {
		return impcarg;
	}

	public void setImpcarg(String impcarg) {
		this.impcarg = impcarg;
	}

	public String getDivcarg() {
		return divcarg;
	}

	public void setDivcarg(String divcarg) {
		this.divcarg = divcarg;
	}

	public String getFpagcar() {
		return fpagcar;
	}

	public void setFpagcar(String fpagcar) {
		this.fpagcar = fpagcar;
	}

	public String getVtacarg() {
		return vtacarg;
	}

	public void setVtacarg(String vtacarg) {
		this.vtacarg = vtacarg;
	}

	public String getOficarg() {
		return oficarg;
	}

	public void setOficarg(String oficarg) {
		this.oficarg = oficarg;
	}

	public String getEntcarg() {
		return entcarg;
	}

	public void setEntcarg(String entcarg) {
		this.entcarg = entcarg;
	}

	public String getEntdoc() {
		return entdoc;
	}

	public void setEntdoc(String entdoc) {
		this.entdoc = entdoc;
	}

	public String getSucdoc() {
		return sucdoc;
	}

	public void setSucdoc(String sucdoc) {
		this.sucdoc = sucdoc;
	}

	public String getCtadoc() {
		return ctadoc;
	}

	public void setCtadoc(String ctadoc) {
		this.ctadoc = ctadoc;
	}

	public String getTipodoc() {
		return tipodoc;
	}

	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}

	public String getCpladoc() {
		return cpladoc;
	}

	public void setCpladoc(String cpladoc) {
		this.cpladoc = cpladoc;
	}

	public String getNumdoc() {
		return numdoc;
	}

	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}

	public String getImpabon() {
		return impabon;
	}

	public void setImpabon(String impabon) {
		this.impabon = impabon;
	}

	public String getDivabon() {
		return divabon;
	}

	public void setDivabon(String divabon) {
		this.divabon = divabon;
	}

	public String getFopagab() {
		return fopagab;
	}

	public void setFopagab(String fopagab) {
		this.fopagab = fopagab;
	}

	public String getCtabono() {
		return ctabono;
	}

	public void setCtabono(String ctabono) {
		this.ctabono = ctabono;
	}

	public String getOfiabon() {
		return ofiabon;
	}

	public void setOfiabon(String ofiabon) {
		this.ofiabon = ofiabon;
	}

	public String getEntabon() {
		return entabon;
	}

	public void setEntabon(String entabon) {
		this.entabon = entabon;
	}

	public String getTitdoc() {
		return titdoc;
	}

	public void setTitdoc(String titdoc) {
		this.titdoc = titdoc;
	}

	public String getPnumdoc() {
		return pnumdoc;
	}

	public void setPnumdoc(String pnumdoc) {
		this.pnumdoc = pnumdoc;
	}

	public String getPriape() {
		return priape;
	}

	public void setPriape(String priape) {
		this.priape = priape;
	}

	public String getSegape() {
		return segape;
	}

	public void setSegape(String segape) {
		this.segape = segape;
	}

	public String getNomper() {
		return nomper;
	}

	public void setNomper(String nomper) {
		this.nomper = nomper;
	}

	public String getNomcal() {
		return nomcal;
	}

	public void setNomcal(String nomcal) {
		this.nomcal = nomcal;
	}

	public String getNumbloc() {
		return numbloc;
	}

	public void setNumbloc(String numbloc) {
		this.numbloc = numbloc;
	}

	public String getNomloc() {
		return nomloc;
	}

	public void setNomloc(String nomloc) {
		this.nomloc = nomloc;
	}

	public String getNomcom() {
		return nomcom;
	}

	public void setNomcom(String nomcom) {
		this.nomcom = nomcom;
	}

	public String getCodpos() {
		return codpos;
	}

	public void setCodpos(String codpos) {
		this.codpos = codpos;
	}

	public String getPretel() {
		return pretel;
	}

	public void setPretel(String pretel) {
		this.pretel = pretel;
	}

	public String getNumtel() {
		return numtel;
	}

	public void setNumtel(String numtel) {
		this.numtel = numtel;
	}

	public String getDesprod() {
		return desprod;
	}

	public void setDesprod(String desprod) {
		this.desprod = desprod;
	}

	public String getTna() {
		return tna;
	}

	public void setTna(String tna) {
		this.tna = tna;
	}

	public String getTea() {
		return tea;
	}

	public void setTea(String tea) {
		this.tea = tea;
	}

	public String getFevenci() {
		return fevenci;
	}

	public void setFevenci(String fevenci) {
		this.fevenci = fevenci;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public String getTotcomi() {
		return totcomi;
	}

	public void setTotcomi(String totcomi) {
		this.totcomi = totcomi;
	}

	public String getTotgast() {
		return totgast;
	}

	public void setTotgast(String totgast) {
		this.totgast = totgast;
	}

	public String getTotivat() {
		return totivat;
	}

	public void setTotivat(String totivat) {
		this.totivat = totivat;
	}

	public String getTotrimp() {
		return totrimp;
	}

	public void setTotrimp(String totrimp) {
		this.totrimp = totrimp;
	}

	public String getCuopur() {
		return cuopur;
	}

	public void setCuopur(String cuopur) {
		this.cuopur = cuopur;
	}

	public String getFeprivt() {
		return feprivt;
	}

	public void setFeprivt(String feprivt) {
		this.feprivt = feprivt;
	}

	public String getNio() {
		return nio;
	}

	public void setNio(String nio) {
		this.nio = nio;
	}

	public String getInicioSeccion2() {
		return inicioSeccion2;
	}

	public void setInicioSeccion2(String inicioSeccion2) {
		this.inicioSeccion2 = inicioSeccion2;
	}

	public String getCodconl() {
		return codconl;
	}

	public void setCodconl(String codconl) {
		this.codconl = codconl;
	}

	public String getInddh() {
		return inddh;
	}

	public void setInddh(String inddh) {
		this.inddh = inddh;
	}

	public String getTiptasa() {
		return tiptasa;
	}

	public void setTiptasa(String tiptasa) {
		this.tiptasa = tiptasa;
	}

	public String getTipconc() {
		return tipconc;
	}

	public void setTipconc(String tipconc) {
		this.tipconc = tipconc;
	}

	public String getBasecal() {
		return basecal;
	}

	public void setBasecal(String basecal) {
		this.basecal = basecal;
	}

	public String getConbase() {
		return conbase;
	}

	public void setConbase(String conbase) {
		this.conbase = conbase;
	}

	public String getDesconb() {
		return desconb;
	}

	public void setDesconb(String desconb) {
		this.desconb = desconb;
	}

	public String getIndcolo() {
		return indcolo;
	}

	public void setIndcolo(String indcolo) {
		this.indcolo = indcolo;
	}

	public String getCodtpco() {
		return codtpco;
	}

	public void setCodtpco(String codtpco) {
		this.codtpco = codtpco;
	}

	public String getImpcon() {
		return impcon;
	}

	public void setImpcon(String impcon) {
		this.impcon = impcon;
	}

	public String getDivcon() {
		return divcon;
	}

	public void setDivcon(String divcon) {
		this.divcon = divcon;
	}

	public String getTpcambi() {
		return tpcambi;
	}

	public void setTpcambi(String tpcambi) {
		this.tpcambi = tpcambi;
	}

	public String getImpcond() {
		return impcond;
	}

	public void setImpcond(String impcond) {
		this.impcond = impcond;
	}

	public String getDivconc() {
		return divconc;
	}

	public void setDivconc(String divconc) {
		this.divconc = divconc;
	}

	public String getCodconc() {
		return codconc;
	}

	public void setCodconc(String codconc) {
		this.codconc = codconc;
	}

	public String getImpconcLiqGastos() {
		return impconcLiqGastos;
	}

	public void setImpconcLiqGastos(String impconcLiqGastos) {
		this.impconcLiqGastos = impconcLiqGastos;
	}

	public String getDivcar() {
		return divcar;
	}

	public void setDivcar(String divcar) {
		this.divcar = divcar;
	}

	public String getImpgast() {
		return impgast;
	}

	public void setImpgast(String impgast) {
		this.impgast = impgast;
	}

	public String getImprecu() {
		return imprecu;
	}

	public void setImprecu(String imprecu) {
		this.imprecu = imprecu;
	}

	public String getDivisaLiqGastos() {
		return divisaLiqGastos;
	}

	public void setDivisaLiqGastos(String divisaLiqGastos) {
		this.divisaLiqGastos = divisaLiqGastos;
	}

	public String getCambiof() {
		return cambiof;
	}

	public void setCambiof(String cambiof) {
		this.cambiof = cambiof;
	}

	public String getIndgast() {
		return indgast;
	}

	public void setIndgast(String indgast) {
		this.indgast = indgast;
	}

	public String getObsgast() {
		return obsgast;
	}

	public void setObsgast(String obsgast) {
		this.obsgast = obsgast;
	}

	public String getOfifcol() {
		return ofifcol;
	}

	public void setOfifcol(String ofifcol) {
		this.ofifcol = ofifcol;
	}

	public String getNumpers() {
		return numpers;
	}

	public void setNumpers(String numpers) {
		this.numpers = numpers;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipcoll() {
		return tipcoll;
	}

	public void setTipcoll(String tipcoll) {
		this.tipcoll = tipcoll;
	}

	public String getDestpco() {
		return destpco;
	}

	public void setDestpco(String destpco) {
		this.destpco = destpco;
	}

	public String getFecinva() {
		return fecinva;
	}

	public void setFecinva(String fecinva) {
		this.fecinva = fecinva;
	}

	public String getTippol() {
		return tippol;
	}

	public void setTippol(String tippol) {
		this.tippol = tippol;
	}

	public String getDetppol() {
		return detppol;
	}

	public void setDetppol(String detppol) {
		this.detppol = detppol;
	}

	public String getCodries() {
		return codries;
	}

	public void setCodries(String codries) {
		this.codries = codries;
	}

	public String getDescori() {
		return descori;
	}

	public void setDescori(String descori) {
		this.descori = descori;
	}

	public String getNumgar() {
		return numgar;
	}

	public void setNumgar(String numgar) {
		this.numgar = numgar;
	}

	public String getNumbien() {
		return numbien;
	}

	public void setNumbien(String numbien) {
		this.numbien = numbien;
	}

	public String getTitaseg() {
		return titaseg;
	}

	public void setTitaseg(String titaseg) {
		this.titaseg = titaseg;
	}

	public String getImpcuot() {
		return impcuot;
	}

	public void setImpcuot(String impcuot) {
		this.impcuot = impcuot;
	}

	public String getDivisaLiqSeguro() {
		return divisaLiqSeguro;
	}

	public void setDivisaLiqSeguro(String divisaLiqSeguro) {
		this.divisaLiqSeguro = divisaLiqSeguro;
	}

	public String getTitdocLiqAgente() {
		return titdocLiqAgente;
	}

	public void setTitdocLiqAgente(String titdocLiqAgente) {
		this.titdocLiqAgente = titdocLiqAgente;
	}

	public String getNumdocu() {
		return numdocu;
	}

	public void setNumdocu(String numdocu) {
		this.numdocu = numdocu;
	}

	public String getConivat() {
		return conivat;
	}

	public void setConivat(String conivat) {
		this.conivat = conivat;
	}

	public String getProdu() {
		return produ;
	}

	public void setProdu(String produ) {
		this.produ = produ;
	}

	public String getPlazoLiqAgente() {
		return plazoLiqAgente;
	}

	public void setPlazoLiqAgente(String plazoLiqAgente) {
		this.plazoLiqAgente = plazoLiqAgente;
	}

	public String getGastost() {
		return gastost;
	}

	public void setGastost(String gastost) {
		this.gastost = gastost;
	}

	public String getIvagast() {
		return ivagast;
	}

	public void setIvagast(String ivagast) {
		this.ivagast = ivagast;
	}

	public String getImpabonLiqAgente() {
		return impabonLiqAgente;
	}

	public void setImpabonLiqAgente(String impabonLiqAgente) {
		this.impabonLiqAgente = impabonLiqAgente;
	}

	public String getImpesc() {
		return impesc;
	}

	public void setImpesc(String impesc) {
		this.impesc = impesc;
	}

	public String getImpcrg() {
		return impcrg;
	}

	public void setImpcrg(String impcrg) {
		this.impcrg = impcrg;
	}

	public String getTasat() {
		return tasat;
	}

	public void setTasat(String tasat) {
		this.tasat = tasat;
	}

	public String getTeaLiqAgente() {
		return teaLiqAgente;
	}

	public void setTeaLiqAgente(String teaLiqAgente) {
		this.teaLiqAgente = teaLiqAgente;
	}

	public String getTcamope() {
		return tcamope;
	}

	public void setTcamope(String tcamope) {
		this.tcamope = tcamope;
	}

	public String getFecpliq() {
		return fecpliq;
	}

	public void setFecpliq(String fecpliq) {
		this.fecpliq = fecpliq;
	}

	public String getImpctot() {
		return impctot;
	}

	public void setImpctot(String impctot) {
		this.impctot = impctot;
	}

	public String getFecvenc() {
		return fecvenc;
	}

	public void setFecvenc(String fecvenc) {
		this.fecvenc = fecvenc;
	}

	public String getCodtiam() {
		return codtiam;
	}

	public void setCodtiam(String codtiam) {
		this.codtiam = codtiam;
	}

	public String getGasescr() {
		return gasescr;
	}

	public void setGasescr(String gasescr) {
		this.gasescr = gasescr;
	}

	public String getAgente() {
		return agente;
	}

	public void setAgente(String agente) {
		this.agente = agente;
	}

	public String getNumage() {
		return numage;
	}

	public void setNumage(String numage) {
		this.numage = numage;
	}

	public String getNomcalLiqAgente() {
		return nomcalLiqAgente;
	}

	public void setNomcalLiqAgente(String nomcalLiqAgente) {
		this.nomcalLiqAgente = nomcalLiqAgente;
	}

	public String getNumblo() {
		return numblo;
	}

	public void setNumblo(String numblo) {
		this.numblo = numblo;
	}

	public String getNomlocLiqAgente() {
		return nomlocLiqAgente;
	}

	public void setNomlocLiqAgente(String nomlocLiqAgente) {
		this.nomlocLiqAgente = nomlocLiqAgente;
	}

	public String getNomcomLiqAgente() {
		return nomcomLiqAgente;
	}

	public void setNomcomLiqAgente(String nomcomLiqAgente) {
		this.nomcomLiqAgente = nomcomLiqAgente;
	}

	public String getVodpos() {
		return vodpos;
	}

	public void setVodpos(String vodpos) {
		this.vodpos = vodpos;
	}

	public String getGastag() {
		return gastag;
	}

	public void setGastag(String gastag) {
		this.gastag = gastag;
	}

	public String getDellado() {
		return dellado;
	}

	public void setDellado(String dellado) {
		this.dellado = dellado;
	}

	public String getBcalcap() {
		return bcalcap;
	}

	public void setBcalcap(String bcalcap) {
		this.bcalcap = bcalcap;
	}

	public String getTasaag() {
		return tasaag;
	}

	public void setTasaag(String tasaag) {
		this.tasaag = tasaag;
	}

	public String getTcpto() {
		return tcpto;
	}

	public void setTcpto(String tcpto) {
		this.tcpto = tcpto;
	}

	public String getBcalpr() {
		return bcalpr;
	}

	public void setBcalpr(String bcalpr) {
		this.bcalpr = bcalpr;
	}

	public String getFpliqag() {
		return fpliqag;
	}

	public void setFpliqag(String fpliqag) {
		this.fpliqag = fpliqag;
	}

	public String getIctpri() {
		return ictpri;
	}

	public void setIctpri(String ictpri) {
		this.ictpri = ictpri;
	}

	public String getIctpre() {
		return ictpre;
	}

	public void setIctpre(String ictpre) {
		this.ictpre = ictpre;
	}

	public String getTipbie() {
		return tipbie;
	}

	public void setTipbie(String tipbie) {
		this.tipbie = tipbie;
	}

	public String getCmarveh() {
		return cmarveh;
	}

	public void setCmarveh(String cmarveh) {
		this.cmarveh = cmarveh;
	}

	public String getCmodveh() {
		return cmodveh;
	}

	public void setCmodveh(String cmodveh) {
		this.cmodveh = cmodveh;
	}

	public String getNmotveh() {
		return nmotveh;
	}

	public void setNmotveh(String nmotveh) {
		this.nmotveh = nmotveh;
	}

	public String getNcarveh() {
		return ncarveh;
	}

	public void setNcarveh(String ncarveh) {
		this.ncarveh = ncarveh;
	}

	public String getDomveh() {
		return domveh;
	}

	public void setDomveh(String domveh) {
		this.domveh = domveh;
	}

	public String getPriapeLiqAgente() {
		return priapeLiqAgente;
	}

	public void setPriapeLiqAgente(String priapeLiqAgente) {
		this.priapeLiqAgente = priapeLiqAgente;
	}

	public String getSegapeLiqAgente() {
		return segapeLiqAgente;
	}

	public void setSegapeLiqAgente(String segapeLiqAgente) {
		this.segapeLiqAgente = segapeLiqAgente;
	}

	public String getNomperLiqAgente() {
		return nomperLiqAgente;
	}

	public void setNomperLiqAgente(String nomperLiqAgente) {
		this.nomperLiqAgente = nomperLiqAgente;
	}

	public String getTipdoc() {
		return tipdoc;
	}

	public void setTipdoc(String tipdoc) {
		this.tipdoc = tipdoc;
	}

	public String getNdocag() {
		return ndocag;
	}

	public void setNdocag(String ndocag) {
		this.ndocag = ndocag;
	}

	public String getConiva() {
		return coniva;
	}

	public void setConiva(String coniva) {
		this.coniva = coniva;
	}

	public String getBacaag() {
		return bacaag;
	}

	public void setBacaag(String bacaag) {
		this.bacaag = bacaag;
	}

	public String getIvaag() {
		return ivaag;
	}

	public void setIvaag(String ivaag) {
		this.ivaag = ivaag;
	}

	public String getIva3337() {
		return iva3337;
	}

	public void setIva3337(String iva3337) {
		this.iva3337 = iva3337;
	}

	public String getCuotas() {
		return cuotas;
	}

	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}

	public String getTipcupo() {
		return tipcupo;
	}

	public void setTipcupo(String tipcupo) {
		this.tipcupo = tipcupo;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getIva1com() {
		return iva1com;
	}

	public void setIva1com(String iva1com) {
		this.iva1com = iva1com;
	}

	public String getIva2com() {
		return iva2com;
	}

	public void setIva2com(String iva2com) {
		this.iva2com = iva2com;
	}

	public String getIngbrut() {
		return ingbrut;
	}

	public void setIngbrut(String ingbrut) {
		this.ingbrut = ingbrut;
	}

	public String getEndeuda() {
		return endeuda;
	}

	public void setEndeuda(String endeuda) {
		this.endeuda = endeuda;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public String getImcuoti() {
		return imcuoti;
	}

	public void setImcuoti(String imcuoti) {
		this.imcuoti = imcuoti;
	}

	public String getFvcuoti() {
		return fvcuoti;
	}

	public void setFvcuoti(String fvcuoti) {
		this.fvcuoti = fvcuoti;
	}

	public String getCtacagt() {
		return ctacagt;
	}

	public void setCtacagt(String ctacagt) {
		this.ctacagt = ctacagt;
	}

	public String getOficagt() {
		return oficagt;
	}

	public void setOficagt(String oficagt) {
		this.oficagt = oficagt;
	}

	public String getEntcagt() {
		return entcagt;
	}

	public void setEntcagt(String entcagt) {
		this.entcagt = entcagt;
	}

	public String getImpcagt() {
		return impcagt;
	}

	public void setImpcagt(String impcagt) {
		this.impcagt = impcagt;
	}

	public String getTpremio() {
		return tpremio;
	}

	public void setTpremio(String tpremio) {
		this.tpremio = tpremio;
	}

	public String getIpreact() {
		return ipreact;
	}

	public void setIpreact(String ipreact) {
		this.ipreact = ipreact;
	}


}
