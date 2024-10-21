package ar.com.santanderrio.obp.servicios.solicitudes.view;

import ar.com.santanderrio.obp.servicios.gestiondecasos.connector.models.InformationObpCardsDto;

public class InformationObpCardsView {

    private String tipo;
    private String antetitulo;
    private String descripcion;
    private String icono;
    private String label;
    private String labelAccion;
    private String titulo;
    private String accion;

    /**
	 * Instantiates a new solicitudes view.
	 */
	public InformationObpCardsView() {
		super();
	}

    public InformationObpCardsView(InformationObpCardsDto informationObpCardsDto)
    {
        this.setTipo(informationObpCardsDto.getType());
        this.setAntetitulo(informationObpCardsDto.getBeforeTitle());
        this.setLabel(informationObpCardsDto.getLabel());
        this.setTitulo(informationObpCardsDto.getTitle());
        this.setDescripcion(informationObpCardsDto.getDescription());
        this.setLabelAccion(informationObpCardsDto.getActionLabel());
        this.setIcono(informationObpCardsDto.getIcon());
        this.setAccion(informationObpCardsDto.getAction());
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAntetitulo() {
        return antetitulo;
    }
    public void setAntetitulo(String antetitulo) {
        this.antetitulo = antetitulo;
    }
    
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLabelAccion() {
        return labelAccion;
    }
    public void setLabelAccion(String labelAccion) {
        this.labelAccion = labelAccion;
    }

    public String getIcono() {
        return icono;
    }
    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getAccion() {
        return accion;
    }
    public void setAccion(String accion) {
        this.accion = accion;
    }
}