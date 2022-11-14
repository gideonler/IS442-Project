package oop.io.demo.pass;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Pass")
public class Pass {

    @Id
    private String passId;

    @NotNull(message = "Pass Number is compulsory")
    private String passNo;

    @NotNull(message = "Place of Interest is compulsory")
    private String attractionName;

    private PASSSTATUS passStatus;

    public Pass() {
        
    }

    public Pass(String passNo, String attractionName){
        this.passNo = passNo;
        this.attractionName = attractionName;
        this.passId = attractionName+ passNo;
        this.passStatus = PASSSTATUS.INOFFICE;
    }

    public Pass(String passNo, String attractionName, String passId){
        this.passNo = passNo;
        this.attractionName = attractionName;
        this.passId = passId;
        this.passStatus = PASSSTATUS.INOFFICE;
    }

    public String getPassId() {
        return passId;
    }

    public  String getPassNo() {
        return passNo;
    }

    public void setPassNo(String passNo) {
        this.passNo = passNo;
        this.passId = attractionName+ passNo;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
        this.passId = attractionName + passNo;
    }

    public PASSSTATUS getPassStatus() {
        return passStatus;
    }

    public void setPassStatus(PASSSTATUS passStatus) {
        this.passStatus = passStatus;
    }

}

   







    

