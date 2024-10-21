package ar.com.santanderrio.obp.servicios.gestiondecasos.connector.models;

public class InformationObpCardsDto {
    
    private String type;
    private String typification;
    private String section;
    private String label;
    private String beforeTitle;
    private String title;
    private String description;
    private String actionLabel;
    private String icon;
    private String action;
    private Boolean active;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getTypification() {
        return typification;
    }
    public void setTypification(String typification) {
        this.typification = typification;
    }

    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public String getBeforeTitle() {
        return beforeTitle;
    }
    public void setBeforeTitle(String beforeTitle) {
        this.beforeTitle = beforeTitle;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionLabel() {
        return actionLabel;
    }
    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    } 
}