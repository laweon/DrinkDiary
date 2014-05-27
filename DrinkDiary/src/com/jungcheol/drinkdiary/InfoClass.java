package com.jungcheol.drinkdiary;

public class InfoClass {
	
	private int id;
	private String imgSrc;
	private String place;
	private String people;
	private int beer;
	private int soju;
	private int malgoli;
	private int whisky;
	private int etc;
	
	public InfoClass() {
		super();
	}

	public InfoClass(int id, String imgSrc, String place, String people, int beer, int soju,
			int malgoli, int whisky, int etc) {
		super();
		this.id = id;
		this.imgSrc = imgSrc;
		this.place = place;
		this.people = people;
		this.beer = beer;
		this.soju = soju;
		this.malgoli = malgoli;
		this.whisky = whisky;
		this.etc = etc;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	
	public int getBeer() {
		return beer;
	}
	public void setBeer(int beer) {
		this.beer = beer;
	}
	
	public int getSoju() {
		return soju;
	}
	public void setSoju(int soju) {
		this.soju = soju;
	}
	
	public int getMalgoli() {
		return malgoli;
	}
	public void setMalgoli(int malgoli) {
		this.malgoli = malgoli;
	}
	
	public int getWhisky() {
		return whisky;
	}
	public void setWhisky(int whisky) {
		this.whisky = whisky;
	}
	
	public int getEtc() {
		return etc;
	}
	public void setEtc(int etc) {
		this.etc = etc;
	}

}
