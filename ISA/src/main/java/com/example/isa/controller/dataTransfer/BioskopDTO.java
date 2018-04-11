package com.example.isa.controller.dataTransfer;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.BioskopPozoristeType;

public class BioskopDTO {
	
	private long id;
	
	private String name;
	
	private String description;
	
	private String address;
	
	private String gmaps;
	
	private BioskopPozoristeType type;
	
	private int bronzeTreshold;
	
	private int silverTreshold;
	
	private int goldTreshold;
	
	private int bronzeSale;
	
	private int silverSale;
	
	private int goldSale;	
	
	public BioskopPozoriste getBioskop() {
		
		BioskopPozoriste retVal = new BioskopPozoriste();
		retVal.setName(name);
		retVal.setDescription(description);
		retVal.setAddress(address);
		retVal.setType(type);
		retVal.setBronzeTreshold(bronzeTreshold);
		retVal.setSilverTreshold(silverTreshold);
		retVal.setGoldTreshold(goldTreshold);
		retVal.setBronzeSale(bronzeSale);
		retVal.setSilverSale(silverSale);
		retVal.setGoldSale(goldSale);
		retVal.setGmapsUrl(gmaps);
		
		return retVal;
		
	}
	
	public BioskopDTO() {
		super();
	}
	
	

	public String getGmaps() {
		return gmaps;
	}

	public void setGmaps(String gmaps) {
		this.gmaps = gmaps;
	}

	public int getBronzeSale() {
		return bronzeSale;
	}

	public void setBronzeSale(int bronzeSale) {
		this.bronzeSale = bronzeSale;
	}

	public int getSilverSale() {
		return silverSale;
	}

	public void setSilverSale(int silverSale) {
		this.silverSale = silverSale;
	}

	public int getGoldSale() {
		return goldSale;
	}

	public void setGoldSale(int goldSale) {
		this.goldSale = goldSale;
	}

	public BioskopDTO(long id, String name, String description, String address, BioskopPozoristeType type, int bronzeTreshold,
			int silverTreshold, int goldTreshold) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.type = type;
		this.bronzeTreshold = bronzeTreshold;
		this.silverTreshold = silverTreshold;
		this.goldTreshold = goldTreshold;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BioskopPozoristeType getType() {
		return type;
	}

	public void setType(BioskopPozoristeType type) {
		this.type = type;
	}

	public int getBronzeTreshold() {
		return bronzeTreshold;
	}

	public void setBronzeTreshold(int bronzeTreshold) {
		this.bronzeTreshold = bronzeTreshold;
	}

	public int getSilverTreshold() {
		return silverTreshold;
	}

	public void setSilverTreshold(int silverTreshold) {
		this.silverTreshold = silverTreshold;
	}

	public int getGoldTreshold() {
		return goldTreshold;
	}

	public void setGoldTreshold(int goldTreshold) {
		this.goldTreshold = goldTreshold;
	}
	
	
	
}
