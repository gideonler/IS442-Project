package oop.io.demo.PlaceOfInterest;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PlaceOfInterest")
public class PlaceOfInterest {
    
    @Id
    private String id;

    @Indexed(unique=true)
    private String placeOfInterestName;

    private double replacementFee;

    private PASSTYPE passType;

    private boolean active;

    public PlaceOfInterest(String placeOfInterestName, double replacementFee, PASSTYPE passType) {
        this.placeOfInterestName=placeOfInterestName;
        this.replacementFee=replacementFee;
        this.passType= passType;
        this.active = true;
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

    public PASSTYPE getPassType() {
        return passType;
    }

    public void setPasstype(PASSTYPE passType) {
        this.passType = passType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
}
