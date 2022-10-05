package oop.io.demo.pass;

import javax.persistence.Id;

public class PassRequest {
    
    private String PlaceOfInterest;
    private PASSTYPE PassType;
    private double ReplacementFee;
    //MaxGuest includes borrower- should we change the name of this field though?
    private int MaxNoGuest;
    private PASSSTATUS PassStatus;
    private int noOfPasses;

    public PassRequest(String PlaceOfInterest, PASSTYPE PassType, double ReplacementFee, int MaxNoGuest, int noOfPasses) {
        this.PlaceOfInterest = PlaceOfInterest;
        this.PassType = PassType;
        this.ReplacementFee = ReplacementFee;
        this.MaxNoGuest = MaxNoGuest;
        this.PassStatus = PASSSTATUS.INOFFICE;
    }

    public String getPlaceOfInterest() {
        return PlaceOfInterest;
    }

    public void setPlaceOfInterest(String placeOfInterest) {
        PlaceOfInterest = placeOfInterest;
    }

    public PASSTYPE getPassType() {
        return PassType;
    }

    public void setPassType(PASSTYPE passType) {
        PassType = passType;
    }

    public double getReplacementFee() {
        return ReplacementFee;
    }

    public void setReplacementFee(double replacementFee) {
        ReplacementFee = replacementFee;
    }

    public int getMaxNoGuest() {
        return MaxNoGuest;
    }

    public void setMaxNoGuest(int maxNoGuest) {
        MaxNoGuest = maxNoGuest;
    }

    public PASSSTATUS getPassStatus() {
        return PassStatus;
    }

    public void setPassStatus(PASSSTATUS passStatus) {
        PassStatus = passStatus;
    }

    public int getNoOfPasses() {
        return noOfPasses;
    }

    public void setNoOfPasses(int noOfPasses) {
        this.noOfPasses = noOfPasses;
    }
    
}
