/**
 *
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.lucene.LuceneIndexerType;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.lucene.MediosDePagoTextFileIndexer;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * The Class MediosPagoDAOImpl.
 *
 * @author B039636
 */
@Component
public class BuscadorEmpresaDAOImpl implements BuscadorEmpresaDAO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BuscadorEmpresaDAOImpl.class);

    /** The Constant HIT_PER_PAGE. */
    private static final int HIT_PER_PAGE = 10000;

    /** The Constant IOEXCEPTION. */
    private static final String IOEXCEPTION = "IOException";

    /** The Constant PARSEEXCEPTION. */
    private static final String PARSEEXCEPTION = "ParseException";

    /** The Constant QUERYFINAL. */
    private static final String LOG_QUERY_FINAL = "Query Final: {}";

    /** The file path. */
    @Autowired
    private FilePath filePath;

    /** The indexer. */
    @Autowired
    private MediosDePagoTextFileIndexer indexer;

    /** The base path. */
    @Value("${LUCENE.BASEPATH}")
    private String basePath;

    /** The medios pago. */
    private static Map<String, MedioPago> mediosPago = new HashMap<String, MedioPago>();
    
    /** The medios pago compras. */
    private static Map<String, MedioPago> mediosPagoCompras = new HashMap<String, MedioPago>();

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.MediosPagoDAO#init()
     */
    @PostConstruct
    @Override
    public void init() {
        try {
            loadMediosPago();
        } catch (DAOException e) {
            LOGGER.error("No se puede inicializar el archivo de Medios de pago", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.MediosPagoDAO#getByCodigo(
     * java.lang.String)
     */
    @Override
    public MedioPago getByCodigo(String empresa) {
        if (empresa.length() > 4) {
            return mediosPagoCompras.get(empresa);
        }
        return mediosPago.get(empresa);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.MediosPagoDAO#
     * getByCuitServicio(java.lang.String, java.lang.String)
     */
    @Override
    public MedioPago getByCuitServicio(String cuit, String servicio) throws DAOException {
        for (Entry<String, MedioPago> entry : mediosPago.entrySet()) {
            MedioPago medioPago = entry.getValue();
            if (medioPago.getCuit().trim().equals(cuit.trim())) {
                LOGGER.info(medioPago.getCuit() + "|" + medioPago.getServicio());
            }
            if (medioPago.getCuit().trim().equalsIgnoreCase(cuit.trim())) {
                String servicioMP = medioPago.getServicio().trim();
                if (servicioMP.equalsIgnoreCase(servicio.trim())
                        || (servicioMP + " " + servicioMP).equalsIgnoreCase(servicio.trim())) {
                    return medioPago;
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.MediosPagoDAO#
     * findAllMediosDePago()
     */
    @Override
    public List<MedioPago> findAllMediosDePago() throws DAOException {

        FileInputStream file = null;
        DataInputStream in = null;
        BufferedReader br = null;

        List<MedioPago> medioPagos = new ArrayList<MedioPago>();
        try {
            String location = filePath.getFilePath() + ExternalPropertyType.FILE_MEDIOSDEPAGO.getName();
            LOGGER.info("Ubicacion del archivo de medios de pagos {}", location);
            file = new FileInputStream(new File(location));
            in = new DataInputStream(file);
            br = new BufferedReader(new InputStreamReader(in, Charset.defaultCharset()));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                medioPagos.add(new MedioPago(strLine));
            }

        } catch (IOException e) {
            LOGGER.error(IOEXCEPTION, e);
        } finally {
            cerrarSreamsBuffers(file, in, br);
        }

        return medioPagos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.MediosPagoDAO#search(java.
     * lang.String)
     */
    @Override
    public Set<MedioPago> search(String term) {
        String termino = term.toLowerCase(Locale.getDefault());
        Query queryFinal = createQuery(termino);
        CampoBusquedaComparator campoBusquedaComparator = new CampoBusquedaComparator(term);
        return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE,
                campoBusquedaComparator);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.MediosPagoDAO#search(java.
     * lang.String)
     */
    @Override
    public Set<MedioPago> buscarMedioPagoPorCuitServicio(String cuit, String servicio) throws ParseException {

        QueryParser queryCUITParser = new QueryParser(Version.LUCENE_47, "cuit",
                new SpanishAnalyzer(Version.LUCENE_47));
        QueryParser queryServicioParser = new QueryParser(Version.LUCENE_47, "servicio",
                new SpanishAnalyzer(Version.LUCENE_47));

        queryCUITParser.setDefaultOperator(QueryParser.Operator.AND);
        Query cuitQuery = queryCUITParser.parse(cuit);
        queryServicioParser.setDefaultOperator(QueryParser.Operator.AND);
        Query servicioQuery = queryServicioParser.parse(servicio);

        BooleanQuery queryFinal = new BooleanQuery();
        queryFinal.add(cuitQuery, Occur.MUST);
        queryFinal.add(servicioQuery, Occur.MUST);
        LOGGER.info(LOG_QUERY_FINAL, queryFinal);
        return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.MediosPagoDAO#searchByCode(
     * java.lang.String)
     */
    @Override
    public Set<MedioPago> searchByCode(String fiid) {
        Query queryFinal = createQueryByCode(fiid);
        return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE);
    }

    /**
     * Search by visa cod establecimiento.
     *
     * @param codEst
     *            the cod est
     * @return the sets the
     */
    @Override
    public Set<MedioPago> searchByVisaCodEstablecimiento(String codEst) {
        Query queryFinal = createQueryByVisaCodEstablecimiento(codEst);
        return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE);
    }

    /**
     * Cerrar sreams buffers.
     *
     * @param file
     *            the file
     * @param in
     *            the in
     * @param br
     *            the br
     * @throws DAOException
     *             the DAO exception
     */
    private void cerrarSreamsBuffers(FileInputStream file, DataInputStream in, BufferedReader br) throws DAOException {
        try {
            in.close();
        } catch (IOException e) {
            LOGGER.error("Error al cerrar DataInputStream", e);
            throw new DAOException(e);
        }
        try {
            br.close();
        } catch (IOException e) {
            LOGGER.error("Error al cerrrar BufferedReader", e);
            throw new DAOException(e);
        }
        try {
            file.close();
        } catch (IOException e) {
            LOGGER.error("Error al cerrar fileInputStram", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void loadMediosPago() throws DAOException {
        BuscadorEmpresaDAOImpl.mediosPago.clear();
        BuscadorEmpresaDAOImpl.mediosPagoCompras.clear();
        FileInputStream file = null;
        DataInputStream in = null;
        BufferedReader br = null;
        InputStream in2 = null;

        try {
            in2 = new FileInputStream(
                    new File(filePath.getFilePath() + ExternalPropertyType.FILE_MEDIOSDEPAGO.getName()));
            File.createTempFile(String.valueOf(in2.hashCode()), ".tmp");

        } catch (IOException e) {
            LOGGER.error("CARGA MEDIO PAGO - Error al crear el archivo temporal", e);

        } finally {
            try {
                if (in2 != null) {
                    in2.close();
                }

            } catch (IOException e) {
                LOGGER.error(IOEXCEPTION, e);
            }
        }

        try {
            file = new FileInputStream(
                    new File(filePath.getFilePath() + ExternalPropertyType.FILE_MEDIOSDEPAGO.getName()));

            in = new DataInputStream(file);
            Reader reader = new InputStreamReader(in, Charset.defaultCharset());
            br = new BufferedReader(reader);
            String strLine;
            while ((strLine = br.readLine()) != null) {
                MedioPago medioPago = new MedioPago(strLine);
                mediosPago.put(medioPago.getFiid(), medioPago);
                mediosPagoCompras.put(medioPago.getPpdctaIdEmpAcuerdo() + medioPago.getPpdctaNroInstaCuerdo(),
                        medioPago);
            }

        } catch (FileNotFoundException e) {
            LOGGER.error("FileNotFoundException", e);
            throw new DAOException(e);
        } catch (IOException e) {
            LOGGER.error(IOEXCEPTION, e);
            throw new DAOException(e);
        } finally {
            cerrarSreamsBuffers(file, in, br);
        }
    }

    /**
     * Armo el Query para pasar al buscador, se busca el termino en los campos
     * nombreFantasia y rubroFantasia, pesHabilitado debe ser igual a S.
     *
     * @param termino
     *            the termino
     * @return the query
     */
    private Query createQueryByCode(String termino) {
        QueryParser fiidQueryParser = new QueryParser(Version.LUCENE_47, "fiid",
                new SpanishAnalyzer(Version.LUCENE_47));

        return createBooleanQuery(termino, fiidQueryParser);
    }

    /**
     * Creates the query by visa cod establecimiento.
     *
     * @param codEst
     *            the cod est
     * @return the query
     */
    private Query createQueryByVisaCodEstablecimiento(String codEst) {
        QueryParser fiidQueryParser = new QueryParser(Version.LUCENE_47, "visaCodEstablecimiento",
                new SpanishAnalyzer(Version.LUCENE_47));

        return createBooleanQueryComprobantes(codEst, fiidQueryParser);
    }

    /**
     * Creates the boolean query.
     *
     * @param termino
     *            the termino
     * @param fiidQueryParser
     *            the fiid query parser
     * @return the boolean query
     */
    private BooleanQuery createBooleanQuery(String termino, QueryParser fiidQueryParser) {

        BooleanQuery queryFinal = new BooleanQuery();
        Query fiidQuery;
        try {
            fiidQuery = fiidQueryParser.parse(StringUtils.trim(termino));
            queryFinal.add(habilitadoPagoMisCuentas(), Occur.MUST);
            queryFinal.add(fiidQuery, Occur.MUST);

            LOGGER.info(LOG_QUERY_FINAL, queryFinal);
        } catch (ParseException e) {
            LOGGER.error(PARSEEXCEPTION, e);
        }
        return queryFinal;
    }

    /**
     * Armo el Query para pasar al buscador, se busca el termino en los campos
     * nombreFantasia y rubroFantasia, pesHabilitado debe ser igual a S.
     *
     * @param termino
     *            the termino
     * @return the query
     */
    private Query createQuery(String termino) {
        BooleanQuery queryFinal = new BooleanQuery();

        try {
        	queryFinal = crearQueryTermino(termino);

            queryFinal.add(habilitadoPagoMisCuentas(), Occur.MUST);

            QueryParser pesDatosAdicionalesQP = new QueryParser(Version.LUCENE_47, MedioPago.DATOS_ADICIONALES,
                    new SpanishAnalyzer(Version.LUCENE_47));
            Query pesDatosAdicionalesQuery = pesDatosAdicionalesQP.parse("C");
            queryFinal.add(pesDatosAdicionalesQuery, Occur.MUST_NOT);

            LOGGER.info(LOG_QUERY_FINAL, queryFinal);

        } catch (ParseException e) {
            LOGGER.error(PARSEEXCEPTION, e);
        }

        return queryFinal;
    }

    /**
     * Busca las empresas que matcheen con ese termino y tengan pago automatico
     * habilitado (PesPA_habilitado = S O Addi_habilitado = S).
     *
     * @param term
     *            the term
     * @return the sets the
     */
    @Override
    public Set<MedioPago> searchPagoAutomatico(String term) {
        LOGGER.info("MEDIO DE PAGO - searchPagoAutomatico - termino de busqueda: {}", term);
        String termino = term.toLowerCase(Locale.getDefault());
        Query queryFinal = createQueryPagoAutomatico(termino);
        return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE);
    }

    /**
     * Armo el Query para pasar al buscador, se busca el termino en los campos
     * nombreFantasia y rubroFantasia, pesHabilitado debe ser igual a S y
     * (PesPA_habilitado = S O Addi_habilitado = S).
     *
     * @param termino
     *            the termino
     * @return the query
     */
    private Query createQueryPagoAutomatico(String termino) {
        BooleanQuery queryFinal = new BooleanQuery();

        try {
        	queryFinal = crearQueryTermino(termino);

            LOGGER.info(LOG_QUERY_FINAL, queryFinal);

        } catch (ParseException e) {
            LOGGER.error(PARSEEXCEPTION, e);
        }

        return queryFinal;
    }

    /**
     * Armo el Query y ejecuto la busqueda. Se busca el termino en los campos
     * nombreFantasia y rubroFantasia, pesHabilitado debe ser igual a S y
     * rubroFantasia = RECARGA DE CELULARES).
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the query
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public Set<MedioPago> searchRecargaCelulares() throws DAOException {
        BooleanQuery queryFinal = new BooleanQuery();
        try {
            QueryParser rubroFantasiaQP = new QueryParser(Version.LUCENE_47, "rubroFantasia",
                    new SpanishAnalyzer(Version.LUCENE_47));
            WildcardQuery query = new WildcardQuery(
                    new Term(MedioPago.CAMPO_BUSQUEDA, "*" + QueryParser.escape(StringUtils.EMPTY) + "*"));
            rubroFantasiaQP.setDefaultOperator(QueryParser.Operator.AND);
            Query rubroFantasiaQuery = rubroFantasiaQP.parse(MedioPago.RUBRO_FANTASIA_RECARGA_CELULARES);
            BooleanQuery recargaCelularesQuery = new BooleanQuery();
            recargaCelularesQuery.add(new BooleanClause(rubroFantasiaQuery, Occur.SHOULD));

            queryFinal.add(query, Occur.MUST);
            queryFinal.add(recargaCelularesQuery, Occur.MUST);
            queryFinal.add(habilitadoPagoMisCuentas(), Occur.MUST);

            QueryParser pesDatosAdicionalesQP = new QueryParser(Version.LUCENE_47, MedioPago.DATOS_ADICIONALES,
                    new SpanishAnalyzer(Version.LUCENE_47));
            Query pesDatosAdicionalesQuery = pesDatosAdicionalesQP.parse("C");
            queryFinal.add(pesDatosAdicionalesQuery, Occur.MUST_NOT);

            LOGGER.info(LOG_QUERY_FINAL, queryFinal);
            return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE);

        } catch (ParseException e) {
            LOGGER.error(PARSEEXCEPTION, e);
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * Armo el Query y ejecuto la busqueda. Se busca el termino en los campos
     * nombreFantasia y rubroFantasia, pesHabilitado debe ser igual a S y
     * rubroFantasia = RECARGA DE CELULARES).
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the query
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public Set<MedioPago> searchEmpresaDebitoAutomaticoEnTarjeta(String terminoBusqueda) throws DAOException {
        BooleanQuery queryFinal = new BooleanQuery();
        String termino = terminoBusqueda.toLowerCase(Locale.getDefault());
        try {
            QueryParser visaHabilitadoQP = new QueryParser(Version.LUCENE_47, "visaHabilitado",
                    new SpanishAnalyzer(Version.LUCENE_47));
            QueryParser amexHabilitadoQP = new QueryParser(Version.LUCENE_47, "amexHabilitado",
                    new SpanishAnalyzer(Version.LUCENE_47));

            queryFinal = crearQueryTermino(termino);
            
            Query visaHabilitadoQuery = visaHabilitadoQP.parse("S");
            Query amexHabilitadoQuery = amexHabilitadoQP.parse("S");

            BooleanQuery debitoAutomaticoEnTarjetaQuery = new BooleanQuery();
            visaHabilitadoQP.setDefaultOperator(QueryParser.Operator.OR);
            amexHabilitadoQP.setDefaultOperator(QueryParser.Operator.OR);
            debitoAutomaticoEnTarjetaQuery.add(new BooleanClause(visaHabilitadoQuery, Occur.SHOULD));
            debitoAutomaticoEnTarjetaQuery.add(new BooleanClause(amexHabilitadoQuery, Occur.SHOULD));

            queryFinal.add(debitoAutomaticoEnTarjetaQuery, Occur.MUST);
            QueryParser pesDatosAdicionalesQP = new QueryParser(Version.LUCENE_47, MedioPago.DATOS_ADICIONALES,
                    new SpanishAnalyzer(Version.LUCENE_47));
            Query pesDatosAdicionalesQuery = pesDatosAdicionalesQP.parse("C");
            queryFinal.add(pesDatosAdicionalesQuery, Occur.MUST_NOT);

            LOGGER.info(LOG_QUERY_FINAL, queryFinal);
            return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE);

        } catch (ParseException e) {
            LOGGER.error(PARSEEXCEPTION, e);
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * Armo el Query y ejecuto la busqueda. Se busca el termino en los campos
     * nombreFantasia y rubroFantasia, pesHabilitado debe ser igual a S y
     * rubroFantasia = RECARGA DE CELULARES).
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the query
     */
    @Override
    public Set<MedioPago> searchEmpresaByNombreFantasia(String terminoBusqueda) {

        BooleanQuery queryFinal = new BooleanQuery();
        QueryParser nombreFantasiaQueryParser = new QueryParser(Version.LUCENE_47, "nombreFantasia",
                new SpanishAnalyzer(Version.LUCENE_47));

        Query nombreFantasiaQuery;
        try {
            nombreFantasiaQueryParser.setDefaultOperator(QueryParser.Operator.AND);
            nombreFantasiaQuery = nombreFantasiaQueryParser
                    .parse(QueryParser.escape(StringUtils.trim(terminoBusqueda)));
            queryFinal.add(nombreFantasiaQuery, Occur.MUST);

            LOGGER.info(LOG_QUERY_FINAL, queryFinal);
        } catch (ParseException e) {
            LOGGER.error(PARSEEXCEPTION, e);
        }

        return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE);

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO#
     * searchEmpresaByNombreFantasiaExacto(java.lang.String)
     */
    @Override
    public Set<MedioPago> porNombreFantasiaTarjeta(String terminoBusqueda) {

        BooleanQuery queryFinal = new BooleanQuery();
        QueryParser nombreFantasiaQueryParser = new QueryParser(Version.LUCENE_47, "nombreFantasia",
                new SpanishAnalyzer(Version.LUCENE_47));
        QueryParser visaHabilitadoQP = new QueryParser(Version.LUCENE_47, "visaHabilitado",
                new SpanishAnalyzer(Version.LUCENE_47));
        QueryParser amexHabilitadoQP = new QueryParser(Version.LUCENE_47, "amexHabilitado",
                new SpanishAnalyzer(Version.LUCENE_47));

        try {
            nombreFantasiaQueryParser.setDefaultOperator(QueryParser.Operator.AND);
            Query nombreFantasiaQuery = nombreFantasiaQueryParser
                    .parse("\"" + QueryParser.escape(terminoBusqueda) + "\"");

            Query visaHabilitadoQuery = visaHabilitadoQP.parse("S");
            Query amexHabilitadoQuery = amexHabilitadoQP.parse("S");

            BooleanQuery debitoAutomaticoEnTarjetaQuery = new BooleanQuery();
            visaHabilitadoQP.setDefaultOperator(QueryParser.Operator.OR);
            amexHabilitadoQP.setDefaultOperator(QueryParser.Operator.OR);
            debitoAutomaticoEnTarjetaQuery.add(new BooleanClause(visaHabilitadoQuery, Occur.SHOULD));
            debitoAutomaticoEnTarjetaQuery.add(new BooleanClause(amexHabilitadoQuery, Occur.SHOULD));

            queryFinal.add(debitoAutomaticoEnTarjetaQuery, Occur.MUST);
            queryFinal.add(nombreFantasiaQuery, Occur.MUST);

            LOGGER.info(LOG_QUERY_FINAL, queryFinal);
        } catch (ParseException e) {
            LOGGER.error(PARSEEXCEPTION, e);
        }

        return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE);

    }
    
    @Override
    public Set<MedioPago> porNombreFantasiaPagoAutomatico(String terminoBusqueda) {

        BooleanQuery queryFinal = new BooleanQuery();
        QueryParser nombreFantasiaQueryParser = new QueryParser(Version.LUCENE_47, "nombreFantasia",
                new SpanishAnalyzer(Version.LUCENE_47));
        try {
            Query nombreFantasiaQuery = nombreFantasiaQueryParser
                    .parse("\"" + QueryParser.escape(terminoBusqueda) + "\"");
            queryFinal.add(habilitadoPagoAutomatico(), Occur.MUST);
            queryFinal.add(nombreFantasiaQuery, Occur.MUST);
            LOGGER.info(LOG_QUERY_FINAL, queryFinal);
        } catch (ParseException e) {
            LOGGER.error(PARSEEXCEPTION, e);
        }

        return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE);

    }
    
    private Query habilitadoPagoAutomatico() throws ParseException {
        QueryParser pesPAHabilitadoQP = new QueryParser(Version.LUCENE_47, "pesPAHabilitado",
                new SpanishAnalyzer(Version.LUCENE_47));
        QueryParser pesHabilitadoQP = new QueryParser(Version.LUCENE_47, "pesHabilitado",
                new SpanishAnalyzer(Version.LUCENE_47));
        QueryParser addiHabilitadoQP = new QueryParser(Version.LUCENE_47, "addiHabilitado",
                new SpanishAnalyzer(Version.LUCENE_47));      
        
        // (pesPAHabilitado = S Y pesHabilitado = S)
        BooleanQuery pagoAutomaticoQuery = new BooleanQuery();

        pagoAutomaticoQuery.add(new BooleanClause(pesPAHabilitadoQP.parse("S"), Occur.MUST));
        pagoAutomaticoQuery.add(new BooleanClause(pesHabilitadoQP.parse("S"), Occur.MUST));

        BooleanQuery pagoAutomaticoQueryTotal = new BooleanQuery();
        pagoAutomaticoQueryTotal.add(pagoAutomaticoQuery, Occur.SHOULD);
        pagoAutomaticoQueryTotal.add(addiHabilitadoQP.parse("S"), Occur.SHOULD);
        
        return pagoAutomaticoQueryTotal;
    }
    
    /**
     * Creates the boolean query.
     *
     * @param termino
     *            the termino
     * @param fiidQueryParser
     *            the fiid query parser
     * @return the boolean query
     */
    private BooleanQuery createBooleanQueryComprobantes(String termino, QueryParser fiidQueryParser) {

        BooleanQuery queryFinal = new BooleanQuery();
        Query fiidQuery;
        try {
            fiidQuery = fiidQueryParser.parse(StringUtils.trim(termino));

            queryFinal.add(fiidQuery, Occur.MUST);

            LOGGER.info(LOG_QUERY_FINAL, queryFinal);
        } catch (ParseException e) {
            LOGGER.error(PARSEEXCEPTION, e);
        }
        return queryFinal;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO#searchEmpresaNuevoPago(java.lang.String)
     */
    @Override
    public Set<MedioPago> searchEmpresaNuevoPago(String term) {
        String termino = term.toLowerCase(Locale.getDefault());
        Query queryFinal = createQueryNuevoPago(termino);
        CampoBusquedaComparator campoBusquedaComparator = new CampoBusquedaComparator(term);
        return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE,
                campoBusquedaComparator);
    }

    /**
     * Creates the query nuevo pago.
     *
     * @param termino
     *            the termino
     * @return the query
     */
    private Query createQueryNuevoPago(String termino) {
        BooleanQuery queryFinal = new BooleanQuery();

        try {
        	queryFinal = crearQueryTermino(termino);

            BooleanQuery habilitadoNuevoPago = new BooleanQuery();
            habilitadoNuevoPago.add(new BooleanClause(habilitadoPagoMisCuentas(), Occur.SHOULD));
            habilitadoNuevoPago.add(new BooleanClause(habilitadoPagoDeCompras(), Occur.SHOULD));
            queryFinal.add(habilitadoNuevoPago, Occur.MUST);


            
            QueryParser pesDatosAdicionalesQP = new QueryParser(Version.LUCENE_47, MedioPago.DATOS_ADICIONALES,
                    new SpanishAnalyzer(Version.LUCENE_47));
            queryFinal.add(pesDatosAdicionalesQP.parse("C"), Occur.MUST_NOT);

            LOGGER.info(LOG_QUERY_FINAL, queryFinal);

        } catch (ParseException e) {
            LOGGER.error(PARSEEXCEPTION, e);
        }

        return queryFinal;
    }

    /**
     * Habilitado pago mis cuentas.
     *
     * @return the query
     * @throws ParseException
     *             the parse exception
     */
    private Query habilitadoPagoMisCuentas() throws ParseException {
        QueryParser pesHabilitadoQP = new QueryParser(Version.LUCENE_47, "pesHabilitado",
                new SpanishAnalyzer(Version.LUCENE_47));
        // Filtro por prepago
        QueryParser pesPrepagoQP = new QueryParser(Version.LUCENE_47, "pesPrepago",
                new SpanishAnalyzer(Version.LUCENE_47));

        BooleanQuery habilitadoPagoMisCuentas = new BooleanQuery();
        habilitadoPagoMisCuentas.add(new BooleanClause(pesHabilitadoQP.parse("S"), Occur.MUST));
        habilitadoPagoMisCuentas.add(new BooleanClause(pesPrepagoQP.parse("N"), Occur.MUST));
        return habilitadoPagoMisCuentas;
    }

    /**
     * Habilitado pago de compras.
     *
     * @return the query
     * @throws ParseException
     *             the parse exception
     */
    private Query habilitadoPagoDeCompras() throws ParseException {
        QueryParser ppdctaHabilitadoQP = new QueryParser(Version.LUCENE_47, "ppdctaHabilitado",
                new SpanishAnalyzer(Version.LUCENE_47));

        return ppdctaHabilitadoQP.parse("S");

    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO#searchEmpresaByIdAcuerdoCompras(java.lang.String)
     */
    @Override
    public Set<MedioPago> searchEmpresaByIdAcuerdoCompras(String idAcuerdo) {

        BooleanQuery queryFinal = new BooleanQuery();
        QueryParser idAcuerdoQP = new QueryParser(Version.LUCENE_47, "ppdctaIdEmpAcuerdo",
                new SpanishAnalyzer(Version.LUCENE_47));
        try {

            Query idAcuerdoQuery = idAcuerdoQP
                    .parse(QueryParser.escape(StringUtils.trim(idAcuerdo)));
            queryFinal.add(idAcuerdoQuery, Occur.MUST);

            LOGGER.info(LOG_QUERY_FINAL, queryFinal);
        } catch (ParseException e) {
            LOGGER.error(PARSEEXCEPTION, e);
        }

        return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE);

    }

	@Override
	public Set<MedioPago> searchRecargaTransporte() throws DAOException {
		 BooleanQuery queryFinal = new BooleanQuery();
	        try {
	            QueryParser rubroFantasiaQP = new QueryParser(Version.LUCENE_47, "rubroFantasia",
	                    new SpanishAnalyzer(Version.LUCENE_47));
	            WildcardQuery query = new WildcardQuery(new Term(MedioPago.CAMPO_BUSQUEDA, "*"));
	            rubroFantasiaQP.setDefaultOperator(QueryParser.Operator.AND);
	            Query rubroFantasiaQuery = rubroFantasiaQP.parse(MedioPago.RUBRO_RECARGA_TRANSPORTE);
	            BooleanQuery recargaCelularesQuery = new BooleanQuery();
	            recargaCelularesQuery.add(new BooleanClause(rubroFantasiaQuery, Occur.SHOULD));

	            queryFinal.add(query, Occur.MUST);
	            queryFinal.add(recargaCelularesQuery, Occur.MUST);
	            queryFinal.add(habilitadoPagoMisCuentas(), Occur.MUST);

	            QueryParser pesDatosAdicionalesQP = new QueryParser(Version.LUCENE_47, MedioPago.DATOS_ADICIONALES,
	                    new SpanishAnalyzer(Version.LUCENE_47));
	            Query pesDatosAdicionalesQuery = pesDatosAdicionalesQP.parse("C");
	            queryFinal.add(pesDatosAdicionalesQuery, Occur.MUST_NOT);

	            LOGGER.info(LOG_QUERY_FINAL, queryFinal);
	            return indexer.toSerch(queryFinal, basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName(), HIT_PER_PAGE);

	        } catch (ParseException e) {
	            LOGGER.error(PARSEEXCEPTION, e);
	            throw new DAOException(e.getMessage());
	        }
	}
	
	private BooleanQuery crearQueryTermino(String termino) throws ParseException {
		 BooleanQuery queryFinal = new BooleanQuery();
    	if (termino.charAt(termino.length() - 1) != '-') {
    		WildcardQuery query = new WildcardQuery(
                    new Term(MedioPago.CAMPO_BUSQUEDA, "*" + QueryParser.escape(termino) + "*"));
    		queryFinal.add(query, Occur.MUST);
    	} else {
        	QueryParser termQP = new QueryParser(Version.LUCENE_47, MedioPago.CAMPO_BUSQUEDA,
                  new SpanishAnalyzer(Version.LUCENE_47));
        	try {
				queryFinal.add(termQP.parse(QueryParser.escape(termino)), Occur.MUST);
			} catch (ParseException e) {
				throw new ParseException();
			}
    	}
    	
    	return queryFinal;	
	}
	
}
