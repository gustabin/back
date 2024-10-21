/*
 *
 */
package ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.impl;

import java.util.LinkedList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.prestamos.view.DestinoPrestamoSeleccionView;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.DestinoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;

/**
 * The Class DestinoPrestamoDAOImpl.
 */
@Component
public class DestinoPrestamoDAOImpl implements DestinoPrestamoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DestinoPrestamoDAOImpl.class);

	/** The file path. */
	@Autowired
	private ArchivoDeRecursosDAO archivoResource;

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.
	 * DestinoPrestamoDAO#obtener()
	 */
	// @Cacheable(cacheNames = { CacheConstants.CACHE_DESTINOS_BCRA })
	@Override
	public List<DestinoPrestamo> obtener() throws DAOException {
		List<DestinoPrestamo> destinosPrestamo = new LinkedList<DestinoPrestamo>();

		int linea = 0;
		int largoDatosMinimo = 14;
		for (String lineaTexto : archivoResource.leerArchivo(ExternalPropertyType.FILE_DESTINO_PRESTAMO)) {
			++linea;

			if (largoDatosMinimo < lineaTexto.length()) {
				destinosPrestamo.add(new DestinoPrestamo(lineaTexto));
			} else {
				Log.debug("Error de formato en la linea: " + linea + " del archivo: "
						+ ExternalPropertyType.FILE_DESTINO_PRESTAMO.getName() + ".txt");
			}
		}
		return destinosPrestamo;
	}

	/**
	 * Devuelve un Destino Prestamo segun producto, subproducto y destino de fondo
	 */
	@Override
	public DestinoPrestamo obtenerPorProductoSubproductoDestino(String producto, String subproducto, String destinoFondo) {
		try {
			List<DestinoPrestamo> destinosPrestamo = this.obtener();
			for (DestinoPrestamo destinoPrestamo : destinosPrestamo) {
				if (destinoPrestamo.getProductoUG().equals(producto) && destinoPrestamo.getSubproductoUG().equals(subproducto) && destinoPrestamo.getDestinoDeFondosUG().equals(destinoFondo)) {
					return destinoPrestamo;
				}

			}
		} catch (DAOException e) {
			return null;
		}
		return null;
	}

    /**
     * Devuelve un Destino Prestamo View segun producto, subproducto y destino de fondo
     */
    @Override
    public DestinoPrestamoSeleccionView obtenerViewPorProductoSubproductoDestino(String producto, String subproducto, String destinoFondo) {
        DestinoPrestamo destinoPrestamo = this.obtenerPorProductoSubproductoDestino(producto, subproducto, destinoFondo);
        if(destinoPrestamo == null){
            return null;
        }
        DestinoPrestamoSeleccionView motivoSeleccionado = new DestinoPrestamoSeleccionView();
        motivoSeleccionado.setDescripcion(destinoPrestamo.getDescripcionUG());
        motivoSeleccionado.setId(destinoPrestamo.obtenerId());
        return motivoSeleccionado;
    }

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.
	 * DestinoPrestamoDAO#vaciarDestinosPrestamo()
	 */
	// @CacheEvict(value = CacheConstants.CACHE_DESTINOS_BCRA, allEntries =
	// true)
	@Override
	public void vaciarDestinosPrestamo() {
		LOGGER.info("Se limpia la cache.");
	}

}