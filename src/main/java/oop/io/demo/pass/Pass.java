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
    private String passId;

    private String passNo;

    private String placeOfInterest;

    private PASSSTATUS passStatus;

    public Pass(){ 
        this.passStatus = PASSSTATUS.INOFFICE;
    }

    public String getPassId() {
        return passId;
    }

    public void setPassID(String passId) {
        this.passId = passId;
    }

    public void setPassId(String passId) {
        this.passId = passId;
    }

    public String getPassNo() {
        return passNo;
    }

    public void setPassNo(String passNo) {
        this.passNo = passNo;
    }

    public String getPlaceOfInterest() {
        return placeOfInterest;
    }

    public void setPlaceOfInterest(String placeOfInterest) {
        this.placeOfInterest = placeOfInterest;
    }

    public PASSSTATUS getPassStatus() {
        return passStatus;
    }

    public void setPassStatus(PASSSTATUS passStatus) {
        this.passStatus = passStatus;
    }

}

   







    

