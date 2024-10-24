import java.io.*;

class FilteredData implements Serializable {
    private static final long serialVersionUID = 1L;
    String message;

    FilteredData(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FilteredData: " + message;
    }
}

public class SerializationFilterDemo {

    public static Object deserializeWithFilter(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            // Set up serialization filter
            ois.setObjectInputFilter(info -> {
                if (FilteredData.class.equals(info.serialClass())) {
                    return ObjectInputFilter.Status.ALLOWED;
                }
                return ObjectInputFilter.Status.REJECTED;
            });

            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization failed: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        FilteredData filteredData = (FilteredData) deserializeWithFilter("filteredData.ser");
        if (filteredData != null) {
            System.out.println("Filtered Data Deserialized: " + filteredData);
        }

        Object nonFilteredData = deserializeWithFilter("nonFilteredData.ser");
        if (nonFilteredData != null) {
            System.out.println("Non-Filtered Data Deserialized: " + nonFilteredData);
        }
    }
}