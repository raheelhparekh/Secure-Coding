import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateSerializedFiles {
    public static void main(String[] args) {
        // Create an instance of TrustedData
        TrustedData trustedData = new TrustedData("This is trusted data");

        // Serialize the trustedData object
        try (FileOutputStream fileOut = new FileOutputStream("trustedData.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(trustedData);
            System.out.println("Serialized TrustedData is saved in trustedData.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create an instance of another class (e.g., MaliciousData)
        MaliciousData maliciousData = new MaliciousData("This is potentially harmful data");

        // Serialize the maliciousData object
        try (FileOutputStream fileOut = new FileOutputStream("maliciousData.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(maliciousData);
            System.out.println("Serialized MaliciousData is saved in maliciousData.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Example class for malicious data (can be any other serializable class)
class MaliciousData implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    String message;

    MaliciousData(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MaliciousData: " + message;
    }
}