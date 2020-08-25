package com.example.finalppb10791;

public class Hotel {

    String hotelId, hotelName, hotelClass, hotelFacility, hotelBed, hotelNote, hotelPrice;

    public Hotel(){

    }

    public Hotel(String hotelId, String hotelName, String hotelClass, String hotelFacility, String hotelBed, String hotelNote, String hotelPrice) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelClass = hotelClass;
        this.hotelFacility = hotelFacility;
        this.hotelBed = hotelBed;
        this.hotelNote = hotelNote;
        this.hotelPrice = hotelPrice;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelFacility() {
        return hotelFacility;
    }

    public String getHotelClass() {
        return hotelClass;
    }

    public String getHotelBed() { return hotelBed; }

    public String getHotelNote() { return hotelNote; }

    public String getHotelPrice() { return hotelPrice; }


}
