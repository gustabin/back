package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.util.Iterator;
import java.util.List;

@JsonSerialize(include = Inclusion.NON_NULL)
public class SellerWithCategoryDTO {

	@JsonProperty("branch")
	String branch;
	
	@JsonProperty("categories")
	List <CategoryRequestDTO> categories;
	
	@JsonProperty("cbu")
	String cbu;	
	
	@JsonProperty("cuit")
	String cuit;	
	
	@JsonProperty("email")
	String email;
	
	@JsonProperty("fancyName")
	String fancyName;

	public SellerWithCategoryDTO() {
	}

	public SellerWithCategoryDTO(String branch, List<CategoryRequestDTO> categories, String cbu, String cuit, String email, String fancyName) {
		this.branch = branch;
		this.categories = categories;
		this.cbu = cbu;
		this.cuit = cuit;
		this.email = email;
		this.fancyName = fancyName;
	}

	public String getCbu() {
		return cbu;
	}
	
	public String getFancyName() {
		return fancyName;
	}
	
	public String getBranch() {
		return branch;
	}
	
	public String getCuit() {
		return cuit;
	}
	
	public String getEmail() {
		return email;
	}

	public List <CategoryRequestDTO> getCategories() {
		return categories;
	}

	public String getCategoryNames() {
		String joinedCategoryNames = "";
		if (this.getCategories() != null && this.getCategories().size() >= 1) {
			StringBuilder categoryNames = new StringBuilder();
			Iterator<CategoryRequestDTO> categoryIterator = this.getCategories().iterator();
			categoryNames.append(categoryIterator.next().getName());
			while (categoryIterator.hasNext()) {
				categoryNames.append(", ");
				categoryNames.append(categoryIterator.next().getName());
			}
			joinedCategoryNames = categoryNames.toString();
		}
		return joinedCategoryNames;
	}
	
}