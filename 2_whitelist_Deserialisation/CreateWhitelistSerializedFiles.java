import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateWhitelistSerializedFiles {
    public static void main(String[] args) {
        // Create an instance of SafeData
        SafeData safeData = new SafeData("This is safe data");

        // Serialize the safeData object
        try (FileOutputStream fileOut = new FileOutputStream("safeData.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(safeData);
            System.out.println("Serialized SafeData is saved in safeData.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create an instance of another class (e.g., NonWhitelistedData)
        NonWhitelistedData nonWhitelistedData = new NonWhitelistedData("This is non-whitelisted data");

        // Serialize the nonWhitelistedData object
        try (FileOutputStream fileOut = new FileOutputStream("nonWhitelistedData.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(nonWhitelistedData);
            System.out.println("Serialized NonWhitelistedData is saved in nonWhitelistedData.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Example class for non-whitelisted data (can be any other serializable class)
class NonWhitelistedData implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    String message;

    NonWhitelistedData(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NonWhitelistedData: " + message;
    }
}