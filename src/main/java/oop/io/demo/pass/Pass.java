package oop.io.demo.pass;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("Pass")
public class Pass {

    @Id
    private String PassID;
    private String PlaceOfInterest;
    private PASSTYPE PassType;
    private double ReplacementFee;
    //MaxGuest includes borrower- should we change the name of this field though?
    private int MaxNoGuest;
    private PASSSTATUS PassStatus;

    public Pass(){ 
        this.PassStatus = PASSSTATUS.INOFFICE;
    }

    public String getPassID() {
        return PassID;
    }

    public void setPassID(String passID) {
        PassID = passID;
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

}

   







    

