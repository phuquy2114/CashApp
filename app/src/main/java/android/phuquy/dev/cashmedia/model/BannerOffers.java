package android.phuquy.dev.cashmedia.model;

import java.util.List;

public class BannerOffers {
	private int success;
	private List<Offer> offers;
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	

}
