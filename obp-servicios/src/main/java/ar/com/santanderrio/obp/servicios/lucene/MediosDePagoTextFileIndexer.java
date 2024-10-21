/*
 * 
 */
package ar.com.santanderrio.obp.servicios.lucene;

import static org.apache.commons.lang3.StringUtils.defaultString;

import java.util.Iterator;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.exceptions.LuceneSearchException;
import ar.com.santanderrio.obp.base.lucene.AbstractFileIndexer;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * Implementacion Indexado tomando el contenido de un Archivo de Texto.
 *
 * @author pablo.martin.gore
 */

@Component
public class MediosDePagoTextFileIndexer extends AbstractFileIndexer<MedioPago> {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MediosDePagoTextFileIndexer.class);

    /**
     * Instantiates a new medios de pago text file indexer.
     */
    public MediosDePagoTextFileIndexer() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.base.lucene.AbstractFileIndexer#
     * createDocumentToIndex(java.lang.Object)
     */
    @Override
    protected Document createDocumentToIndex(MedioPago value) {

        LOGGER.trace("Medio de pago a indexar {}", value);

        Document doc = new Document();
        doc.add(new TextField("cuit", defaultString(value.getCuit()), Field.Store.YES));
        doc.add(new TextField("datosAdicionales", defaultString(value.getDatosAdicionales()), Field.Store.YES));
        doc.add(new TextField("fiid", defaultString(value.getFiid()), Field.Store.YES));
        doc.add(new TextField("pesPrepago", defaultString(value.getPesPrepago()), Field.Store.YES));
        doc.add(new TextField("pesGifFactura", defaultString(value.getPesGifFactura()), Field.Store.YES));
        doc.add(new TextField("rubroFantasia", defaultString(value.getRubroFantasia()), Field.Store.YES));
        doc.add(new TextField("nombreFantasia", defaultString(value.getNombreFantasia()), Field.Store.YES));
        doc.add(new TextField("tipoEmpresa", defaultString(value.getTipoEmpresa()), Field.Store.YES));
        doc.add(new TextField("tipoImporte", defaultString(value.getTipoImporte()), Field.Store.YES));
        doc.add(new IntField("tipoPago", value.getTipoPago() == null ? 0 : value.getTipoPago(), Field.Store.YES));
        doc.add(new TextField("pesIdentificacion", defaultString(value.getPesIdentificacion()), Field.Store.YES));
        doc.add(new TextField("pescodigorubro", defaultString(value.getPescodigorubro()), Field.Store.YES));
        doc.add(new TextField("pesHabilitado", defaultString(value.getPesHabilitado()), Field.Store.YES));
        doc.add(new TextField("pesPAHabilitado", defaultString(value.getPesPAHabilitado()), Field.Store.YES));
        doc.add(new TextField("addiHabilitado", defaultString(value.getAddiHabilitado()), Field.Store.YES));
        doc.add(new TextField("campoBusqueda", value.getNombreFantasia() + " " + value.getRubroFantasia(),
                Field.Store.YES));
        doc.add(new TextField("servicio", defaultString(value.getServicio()), Field.Store.YES));
        doc.add(new TextField("addiLeyendaIdentificacion", value.getAddiLeyendaIdentificacion(), Field.Store.YES));
        doc.add(new TextField("addiLongitud", defaultString(value.getAddiLongitud()), Field.Store.YES));
        doc.add(new TextField("addiGifFactura", defaultString(value.getAddiGifFactura()), Field.Store.YES));
        doc.add(new TextField("marcaPagoTc", defaultString(value.getMarcaPagoTc()), Field.Store.YES));
        doc.add(new TextField("montoMaximoPagoTc", defaultString(value.getMontoMaximoPagoTc()), Field.Store.YES));
        doc.add(new TextField("montoMinimoPagoTc", defaultString(value.getMontoMinimoPagoTc()), Field.Store.YES));
        doc.add(new TextField("visaHabilitado", defaultString(value.getVisaHabilitado()), Field.Store.YES));
        doc.add(new TextField("amexHabilitado", defaultString(value.getAmexHabilitado()), Field.Store.YES));
        doc.add(new TextField("amexIdentificador", defaultString(value.getAmexIdentificador()), Field.Store.YES));
        doc.add(new TextField("amexGifFactura", defaultString(value.getAmexGifFactura()), Field.Store.YES));
        doc.add(new TextField("visaIdentificador", defaultString(value.getVisaIdentificador()), Field.Store.YES));
        doc.add(new TextField("visaGifFactura", defaultString(value.getVisaGifFactura()), Field.Store.YES));
        doc.add(new TextField("visaCodEstablecimiento", defaultString(value.getVisaCodEstablecimiento()),
                Field.Store.YES));
        doc.add(new TextField("moneda", defaultString(value.getMoneda()), Field.Store.YES));
        doc.add(new TextField("ppdctaHabilitado", defaultString(value.getPpdctaHabilitado()), Field.Store.YES));
        doc.add(new TextField("ppdctaIdEmpAcuerdo", defaultString(value.getPpdctaIdEmpAcuerdo()), Field.Store.YES));
        doc.add(new TextField("ppdctaCodProdAcuerdo", defaultString(value.getPpdctaCodProdAcuerdo()), Field.Store.YES));
        doc.add(new TextField("ppdctaNroInstaCuerdo", defaultString(value.getPpdctaNroInstaCuerdo()), Field.Store.YES));
        doc.add(new TextField("ppdctaLimiteMaximo", defaultString(value.getPpdctaLimiteMaximo()), Field.Store.YES));
        doc.add(new TextField("ppdctaModalidad", defaultString(value.getPpdctaModalidad()), Field.Store.YES));
        doc.add(new TextField("ppdctaMoneda", defaultString(value.getPpdctaMoneda()), Field.Store.YES));
        doc.add(new TextField("ppdctaIdentificadorPyrip", defaultString(value.getPpdctaIdentificadorPyrip()),
                Field.Store.YES));
        doc.add(new TextField("ppdctaPagoParcial", defaultString(value.getPpdctaPagoParcial()), Field.Store.YES));

        return doc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.base.lucene.AbstractFileIndexer#marshall(org.
     * apache.lucene.document.Document)
     */
    @Override
    protected MedioPago marshall(Document document) {

        Iterator<IndexableField> it = document.getFields().iterator();
        MedioPago pago = new MedioPago();
        while (it.hasNext()) {
            IndexableField field = it.next();
            try {
                java.lang.reflect.Field javaField = pago.getClass().getDeclaredField(field.name());

                if (!java.lang.reflect.Modifier.isStatic(javaField.getModifiers())) {
                    javaField.setAccessible(true);
                }

                if (javaField.getType().equals(String.class)) {
                    javaField.set(pago, field.stringValue());
                }

                if (javaField.getType().equals(Integer.class)) {
                    javaField.set(pago, field.numericValue());
                }
            } catch (Exception e) {
                LOGGER.error("Excepcion", e);
                throw new LuceneSearchException(e);
            }
        }
        return pago;
    }
}
