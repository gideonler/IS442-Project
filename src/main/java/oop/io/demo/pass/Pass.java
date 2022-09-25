package oop.io.demo.pass;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Pass")
public class Pass {
    private String PassID;
    private String PassName;
    private int PassAvailability;

    public Pass(){
        
    }

    public Pass(String PassID, String PassName, int PassAvailability) {
        this.PassID = PassID;
        this.PassName = PassName;
        this.PassAvailability = PassAvailability;
    }

    public String GetPasses() {
        return PassName;
    }

    public int GetAvailability() {
        return PassAvailability;
    }

    public String GetId() {
        return PassID;
    }

    public void SetPasses(String PassName) {
        this.PassName = PassName;
    }

    public void SetID(String PassID) {
        this.PassID=PassID;
    }
    public void SetAvailability(int PassAvailability) {
        this.PassAvailability=PassAvailability;
    }
    
}