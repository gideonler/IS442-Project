package oop.io.demo.PlaceOfInterest;

public class PlaceOfInterestRequest{
    
    private String placeOfInterest;

    private double replacementFee;

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
