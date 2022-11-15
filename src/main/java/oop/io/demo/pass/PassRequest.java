package oop.io.demo.pass;

public class PassRequest {
    
    private String passNo;

    private String attractionName;

    public PassRequest(String passNo, String attractionName) {
        this.passNo = passNo;
        this.attractionName = attractionName;
    }

    public String getPassNo() {
        return passNo;
    }

    public void setPassNo(String passNo) {
        this.passNo = passNo;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }
    
}
