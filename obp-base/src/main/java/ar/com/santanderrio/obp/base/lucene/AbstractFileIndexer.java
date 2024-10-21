package ar.com.santanderrio.obp.base.lucene;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.exceptions.LuceneSearchException;

// TODO: Auto-generated Javadoc
/**
 * Comportamiento comun de indexado y busquefda en nodos Lucene.
 *
 * @author pablo.martin.gore
 * @param <T>
 *            the generic type
 */
public abstract class AbstractFileIndexer<T> {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AbstractFileIndexer.class);

	/** The writer. */
	private IndexWriter writer;

	/** The index. */
	private Directory index;

	/** The analyzer. */
	private SpanishAnalyzer analyzer = new SpanishAnalyzer(Version.LUCENE_47);

	/**
	 * Este metodo esta encargado de transformar un DTO en un Docuemnto Lucene
	 * para luego indexar.
	 *
	 * @param value
	 *            the value
	 * @return the document
	 * @throws LuceneSearchException
	 *             the lucene search exception
	 */
	protected abstract Document createDocumentToIndex(T value) throws LuceneSearchException;

	/**
	 * Transformacion de Docuemnt al DTO T.
	 *
	 * @param document
	 *            the document
	 * @return the t
	 * @throws LuceneSearchException
	 *             the lucene search exception
	 */
	protected abstract T marshall(Document document) throws LuceneSearchException;

	/**
	 * metodo encargado de coordinar el indexado del DTO T.
	 *
	 * @param value
	 *            the value
	 * @param indexLocation
	 *            the index location
	 * @throws LuceneSearchException
	 *             the lucene search exception
	 */
	public void toIndex(T value, String indexLocation) throws LuceneSearchException {
		try {
			if (index == null) {
				index = FSDirectory.open(new File(indexLocation));
				IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
				config.setUseCompoundFile(false);
				config.setOpenMode(OpenMode.CREATE_OR_APPEND);

				writer = new IndexWriter(index, config);

			}

			indexDocuemnt(createDocumentToIndex(value));
		} catch (IOException e) {
			logger.error("Error al realizar el indexado", e);
			throw new LuceneSearchException(e);
		}
	}

	/**
	 * Busca en el indice pasado como parametro.
	 *
	 * @param query
	 *            the query
	 * @param indexLocation
	 *            the index location
	 * @param hitsPerPage
	 *            the hits per page
	 * @return the sets the
	 * @throws LuceneSearchException
	 *             the lucene search exception
	 */
	public Set<T> toSerch(Query query, String indexLocation, int hitsPerPage) throws LuceneSearchException {
		return toSerch(query, indexLocation, hitsPerPage, null);
	}

	/**
	 * Busca en el indice pasado como parametro y si se desea darle orden.<br>
	 * Si el comparador esta presente se utiliza un TreeSet de otra manera
	 * resuelve a HashSet.<br>
	 *
	 * @param query
	 *            the query
	 * @param indexLocation
	 *            the index location
	 * @param hitsPerPage
	 *            the hits per page
	 * @param comparador
	 *            the comparador
	 * @return the sets the
	 * @throws LuceneSearchException
	 *             the lucene search exception
	 */
	public Set<T> toSerch(Query query, String indexLocation, int hitsPerPage, Comparator<T> comparador)
			throws LuceneSearchException {
		logger.info("search query {}, ubicacion del indice {}, hit por pagina {}", query, indexLocation, hitsPerPage);
		try {

			Directory directory = FSDirectory.open(new File(indexLocation));
			IndexReader reader = DirectoryReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(reader);

			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);

			indexSearcher.search(query, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;

			String contents = query.toString("contents");
			logger.debug("Query string: {}", contents);
			logger.debug("Found: {} hits.", hits.length);

			Set<T> values = obtenerSet(comparador);

			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d = indexSearcher.doc(docId);
				T object = marshall(d);

				if (!values.contains(object)) {
					values.add(object);
					logger.debug("found: {}", object);
				}
			}
			logger.info("Cantidad de resultados posibles retornados {}", values.size());
			return values;

		} catch (IOException e) {
			logger.error("INDEXADOR-error al ejecutar el indexador", e);
			throw new LuceneSearchException(e);
		}

	}

	/**
	 * Devuelve diferente implementaciones de set segun la presencia o no del
	 * comparador para ordenar los conjuntos.
	 *
	 * @param comparador
	 *            the comparador
	 * @return the sets the
	 */
	private Set<T> obtenerSet(Comparator<T> comparador) {
		if (comparador != null) {
			return new TreeSet<T>(comparador);
		}
		return new HashSet<T>();
	}

	/**
	 * Index docuemnt.
	 *
	 * @param document
	 *            the document
	 * @throws LuceneSearchException
	 *             the lucene search exception
	 */
	private void indexDocuemnt(Document document) throws LuceneSearchException {
		try {
			writer.addDocument(document);
		} catch (IOException e) {
			logger.error("Error al realizar el indexado del documento", e);
			throw new LuceneSearchException(e);
		}
	}

	/**
	 * Close.
	 *
	 * @throws LuceneSearchException
	 *             the lucene search exception
	 */
	public void close() throws LuceneSearchException {
		try {
			writer.close();
		} catch (IOException e) {
			logger.error("Error al realizar el cierre del indice", e);
			throw new LuceneSearchException(e);
		}
	}

	/**
	 * Borra el contenido del indice,usado para reconstruirlo.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void deleteAll() throws IOException {
		if (writer != null) {
			writer.deleteAll();
			writer.commit();
		}
	}

	/**
	 * Hace un commit de los cambios del indicen, pero no lo cierra
	 * como se va a modificar el indice se hace solo un commit
	 * que es 5 veces mas rapido que un close.
	 *
	 * @throws LuceneSearchException the lucene search exception
	 */
	public void commit() throws LuceneSearchException {
		try {
			writer.commit();
		} catch (IOException e) {
			logger.error("Error al realizar el commit del indice", e);
			throw new LuceneSearchException(e);
		}
	}

	/**
	 * Close all.
	 */
	public void closeAll() throws LuceneSearchException {
		if (writer != null) {
			try {
				writer.rollback();
				index.close();
				index = null;
			} catch (IOException e) {
				logger.error("closeAll() - error", e);
				throw new LuceneSearchException(e);
			}
		}
	}

	/**
	 * Init
	 *
	 * @param indexLocation the index location
	 * @throws LuceneSearchException the lucene search exception
	 */
	public void init(String indexLocation) throws LuceneSearchException {
		try {
			if (index == null) {
				index = FSDirectory.open(new File(indexLocation));
				IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
				config.setUseCompoundFile(false);
				config.setOpenMode(OpenMode.CREATE_OR_APPEND);

				writer = new IndexWriter(index, config);
			}
		} catch (IOException e) {
			logger.error("Error al inicializar el index", e);
			throw new LuceneSearchException(e);
		}
	}
}
