package oop.io.demo.pass;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Pass")
public class Pass {

    @Id
    private String passId;

    @NotNull(message = "Pass Number is compulsory")
    private String passNo;

    @NotNull(message = "Place of Interest is compulsory")
    private String placeOfInterestName;

    private PASSSTATUS passStatus;

    public Pass() {
        
    }

    public Pass(String passNo, String placeOfInterestName){
        this.passNo = passNo;
        this.placeOfInterestName = placeOfInterestName;
        this.passId = placeOfInterestName+ passNo;
        this.passStatus = PASSSTATUS.INOFFICE;
    }

    public Pass(String passNo, String placeOfInterestName, String passId){
        this.passNo = passNo;
        this.placeOfInterestName = placeOfInterestName;
        this.passId = passId;
        this.passStatus = PASSSTATUS.INOFFICE;
    }

    public String getPassId() {
        return passId;
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

    public String getPlaceOfInterestName() {
        return placeOfInterestName;
    }

    public void setPlaceOfInterestName(String placeOfInterestName) {
        this.placeOfInterestName = placeOfInterestName;
    }

    public PASSSTATUS getPassStatus() {
        return passStatus;
    }

    public void setPassStatus(PASSSTATUS passStatus) {
        this.passStatus = passStatus;
    }

}

   







    

