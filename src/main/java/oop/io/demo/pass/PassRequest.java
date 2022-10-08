package oop.io.demo.pass;

public class PassRequest {
    
    private String placeOfInterest;
    private String passType;
    private double replacementFee;
    //MaxGuest includes borrower- should we change the name of this field though?
    private int maxNoGuest;
    private String passStatus;
    private int noOfPasses;

    public PassRequest(String placeOfInterest, String passType, double replacementFee, int maxNoGuest, int noOfPasses) {
        this.placeOfInterest = placeOfInterest;
        this.replacementFee = replacementFee;
        this.maxNoGuest = maxNoGuest;
        this.passType= passType;
        this.passStatus = "inoffice";
        this.noOfPasses=noOfPasses;
    }

    public String getPlaceOfInterest() {
        return placeOfInterest;
    }

    public void setPlaceOfInterest(String placeOfInterest) {
        this.placeOfInterest = placeOfInterest;
    }

    public String getPassType() {
        return passType;
    }

    public void setPassType(String passType) {
        this.passType = passType;
    }

    public double getReplacementFee() {
        return replacementFee;
    }

    public void setReplacementFee(double replacementFee) {
        this.replacementFee = replacementFee;
    }

    public int getMaxNoGuest() {
        return maxNoGuest;
    }

    public void setMaxNoGuest(int maxNoGuest) {
        this.maxNoGuest = maxNoGuest;
    }

    public String getPassStatus() {
        return passStatus;
    }

    public void setPassStatus(String passStatus) {
        this.passStatus = passStatus;
    }

    public int getNoOfPasses() {
        return noOfPasses;
    }

    public void setNoOfPasses(int noOfPasses) {
        this.noOfPasses = noOfPasses;
    }
    
}
