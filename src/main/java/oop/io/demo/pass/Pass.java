package oop.io.demo.pass;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("Pass")
public class Pass {
    @Transient
    public static final String SEQUENCE_NAME = "pass_sequence";

    @Id
    private long passId;

    private String placeOfInterest;
    private PASSTYPE passType;
    private double replacementFee;
    //MaxGuest includes borrower- should we change the name of this field though?
    private int maxNoGuest;

    private PASSSTATUS passStatus;

    public Pass(){ 
        this.passStatus = PASSSTATUS.INOFFICE;
    }

    public long getPassId() {
        return passId;
    }

    public void setPassID(long passId) {
        this.passId = passId;
    }

    public String getPlaceOfInterest() {
        return placeOfInterest;
    }

    public void setPlaceOfInterest(String placeOfInterest) {
        this.placeOfInterest = placeOfInterest;
    }

    public PASSTYPE getPassType() {
        return passType;
    }

    public void setPassType(PASSTYPE passType) {
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

    public PASSSTATUS getPassStatus() {
        return passStatus;
    }

    public void setPassStatus(PASSSTATUS passStatus) {
        this.passStatus = passStatus;
    }

}

   







    

