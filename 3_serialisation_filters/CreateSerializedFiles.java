import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateSerializedFiles {
    public static void main(String[] args) {
        // Create an instance of FilteredData
        FilteredData filteredData = new FilteredData("This is filtered data");

        // Serialize the filteredData object
        try (FileOutputStream fileOut = new FileOutputStream("filteredData.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(filteredData);
            System.out.println("Serialized filteredData is saved in filteredData.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create an instance of another class (e.g., NonFilteredData)
        NonFilteredData nonFilteredData = new NonFilteredData("This is non-filtered data");

        // Serialize the nonFilteredData object
        try (FileOutputStream fileOut = new FileOutputStream("nonFilteredData.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(nonFilteredData);
            System.out.println("Serialized nonFilteredData is saved in nonFilteredData.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Example class for non-filtered data (can be any other serializable class)
class NonFilteredData implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    String message;

    NonFilteredData(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NonFilteredData: " + message;
    }
}