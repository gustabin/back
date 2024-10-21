package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class DatosRecomendacionResponse {

	
	@JsonProperty("ProductList")
	private List<Product> productList;
	
	@JsonProperty("CanSee")
	private boolean canSee;
	
	@JsonProperty("Title")
	private String title;
	
	@JsonProperty("FontSize")
	private String fontSize;
	
	@JsonProperty("HaveCustodyAccounts")
	private boolean haveCustodyAccounts;
	
	@JsonProperty("Image")
	private String image;
	
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public boolean getCanSee() {
		return canSee;
	}
	
	public void setCanSee(boolean canSee) {
		this.canSee = canSee;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getFontSize() {
		return fontSize;
	}
	
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	
	public boolean getHaveCustodyAccounts() {
		return haveCustodyAccounts;
	}
	
	public void setHaveCustodyAccounts(boolean haveCustodyAccounts) {
		this.haveCustodyAccounts = haveCustodyAccounts;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
}
