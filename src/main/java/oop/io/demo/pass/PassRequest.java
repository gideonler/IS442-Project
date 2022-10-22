package oop.io.demo.pass;

import javax.validation.constraints.NotBlank;

public class PassRequest {
    
    private String passNo;

    private String placeOfInterestName;

    public PassRequest(String passNo, String placeOfInterestName) {
        this.passNo = passNo;
        this.placeOfInterestName = placeOfInterestName;
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
    
}
