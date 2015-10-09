package com.airamerica.products;

public class SpecialAssistance extends Service {

	
	private String TypeOfService;

	public SpecialAssistance(String code, String productType, String typeOfService) {
		super(code, productType);
		TypeOfService = typeOfService;
	}

	public String getTypeOfService() {
		return TypeOfService;
	}

	public void setTypeOfService(String typeOfService) {
		TypeOfService = typeOfService;
	}
	
	@Override
	public String getName(){
		return TypeOfService;
	}
	
	
}
