package ar.com.santanderrio.obp.servicios.home.entitites;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class ItemEntity {

		private String name;
		private String label;
		private AccessEntity access;
		private List<ItemEntity> children = new ArrayList<ItemEntity>();
		
		
		public boolean tieneHijos() {
			return getChildren() != null && !getChildren().isEmpty();
		}
		
		public boolean tieneLinkMicrofront() {
			return getAccess() !=null && getAccess().getMicrofrontName() != null && 
					!getAccess().getMicrofrontName().isEmpty();
		}
		
		
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public AccessEntity getAccess() {
			return access;
		}
		public void setAccess(AccessEntity access) {
			this.access = access;
		}
		public List<ItemEntity> getChildren() {
			return children;
		}
		public void setChildren(List<ItemEntity> children) {
			this.children = children;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	
}