package ar.com.santanderrio.obp.servicios.home.entitites;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class MenuEntity {

		private String name;
		private String channel;
		private String profile;
		private List<ItemEntity> items;
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getChannel() {
			return channel;
		}
		public void setChannel(String channel) {
			this.channel = channel;
		}
		public String getProfile() {
			return profile;
		}
		public void setProfile(String profile) {
			this.profile = profile;
		}
		public List<ItemEntity> getItems() {
			return items;
		}
		public void setItems(List<ItemEntity> items) {
			this.items = items;
		}
	
	
	
}