/**
 *
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para consumir api de prestamos encolados
 */
public class PrestamosEncoladosEntity {

    public PrestamosEncoladosEntity() {
    }

    public PrestamosEncoladosEntity(List<PrestamoEncoladoEntity> prestamosEncolados) {
        this.prestamosEncolados = prestamosEncolados;
    }

    private List<PrestamoEncoladoEntity> prestamosEncolados = new ArrayList<PrestamoEncoladoEntity>();

    public List<PrestamoEncoladoEntity> getPrestamosEncolados() {
        return prestamosEncolados;
    }

    public void setPrestamosEncolados(List<PrestamoEncoladoEntity> prestamosEncolados) {
        this.prestamosEncolados = prestamosEncolados;
    }
}