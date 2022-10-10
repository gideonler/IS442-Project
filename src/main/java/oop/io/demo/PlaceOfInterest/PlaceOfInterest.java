package oop.io.demo.PlaceOfInterest;

import javax.persistence.Id;

public class PlaceOfInterest {
    
    @Id
    private String placeOfInterestName;

    private double replacementFee;

    private PASSTYPE passtype;

    private boolean active;

    public PlaceOfInterest(String placeOfInterestName, double replacementFee, PASSTYPE passType) {
        this.placeOfInterestName=placeOfInterestName;
        this.replacementFee=replacementFee;
        this.passtype= passType;
    }

    public String getPlaceOfInterestName() {
        return placeOfInterestName;
    }

    public void setPlaceOfInterestName(String placeOfInterestName) {
        this.placeOfInterestName = placeOfInterestName;
    }

    public double getReplacementFee() {
        return replacementFee;
    }

    public void setReplacementFee(double replacementFee) {
        this.replacementFee = replacementFee;
    }

    public PASSTYPE getPasstype() {
        return passtype;
    }

    public void setPasstype(PASSTYPE passtype) {
        this.passtype = passtype;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
}
