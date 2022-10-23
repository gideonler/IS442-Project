package oop.io.demo.attraction;


public class AttractionRequest{
    
    private String attraction;

    private double replacementFee;

    private String passType;

    public AttractionRequest(String attraction, double replacementFee, String passType) {
        this.attraction=attraction;
        this.replacementFee=replacementFee;
        this.passType= passType;
    }

    public String getAttraction() {
        return attraction;
    }

    public void setAttraction(String attraction) {
        this.attraction = attraction;
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
