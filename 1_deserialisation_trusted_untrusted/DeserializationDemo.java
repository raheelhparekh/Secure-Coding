import java.io.*;

class MaliciousData implements Serializable {
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

public class DeserializationDemo {

    // Method to deserialize object from file with class validation
    public static Object deserializeObject(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            
            // Validation: Only allow instances of TrustedData class
            if (obj instanceof TrustedData) {
                return obj;
            } else {
                System.err.println("Deserialization rejected: Class not trusted (" + obj.getClass().getName() + ")");
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization failed: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // Example of deserializing trusted data
        TrustedData trustedData = (TrustedData) deserializeObject("trustedData.ser");
        if (trustedData != null) {
            System.out.println("Trusted Data Deserialized: " + trustedData);
        }

        // Example of deserializing untrusted data
        Object maliciousData = deserializeObject("maliciousData.ser");
        if (maliciousData != null) {
            System.out.println("Untrusted Data Deserialized: " + maliciousData);
        }
    }
}