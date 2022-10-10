package oop.io.demo.PlaceOfInterest;

import oop.io.demo.pass.PassRequest;

public class PlaceOfInterestRequest{
    
    private String placeOfInterest;

    private double replacementFee;

    private String passtype;

    public PlaceOfInterestRequest(String placeOfInterest, double replacementFee, String passType) {
        this.placeOfInterest=placeOfInterest;
        this.replacementFee=replacementFee;
        this.passtype= passType;
    }

    public String getPlaceOfInterest() {
        return placeOfInterest;
    }

    public void setPlaceOfInterest(String placeOfInterest) {
        this.placeOfInterest = placeOfInterest;
    }

    public double getReplacementFee() {
        return replacementFee;
    }

    public void setReplacementFee(double replacementFee) {
        this.replacementFee = replacementFee;
    }

    public String getPasstype() {
        return passtype;
    }

    public void setPasstype(String passtype) {
        this.passtype = passtype;
    }

}
