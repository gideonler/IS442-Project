package oop.io.demo.placeOfInterest;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotBlank;

public class PlaceOfInterestRequest{
    
    @NotBlank
    private String placeOfInterest;

    @NotBlank
    private double replacementFee;

    @NotBlank
    private String passType;

    public PlaceOfInterestRequest(String placeOfInterest, double replacementFee, String passType) {
        this.placeOfInterest=placeOfInterest;
        this.replacementFee=replacementFee;
        this.passType= passType;
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

    public String getPassType() {
        return passType;
    }

    public void setPasstype(String passType) {
        this.passType = passType;
    }

}
