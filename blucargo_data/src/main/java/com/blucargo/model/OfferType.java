package com.blucargo.model;

public enum OfferType {

    CARGO(1, "CARGO"), VEHICLE(10, "VEHICLE");
    
    private final String typeName;
    private final int typeId;
    
    OfferType(int typeId, String typeName){
        this.typeId = typeId;
        this.typeName = typeName; 
    }

    public String getTypeName() {
        return typeName;
    }

    public int getTypeId() {
        return typeId;
    }

}