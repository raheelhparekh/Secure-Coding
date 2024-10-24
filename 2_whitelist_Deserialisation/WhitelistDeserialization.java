import java.io.*;
import java.util.HashSet;
import java.util.Set;

class SafeData implements Serializable {
    private static final long serialVersionUID = 1L;
    String message;

    SafeData(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SafeData: " + message;
    }
}

public class WhitelistDeserialization {

    // Whitelisted classes
    private static final Set<String> whitelist = new HashSet<>();

    static {
        whitelist.add(SafeData.class.getName());
    }

    public static Object deserializeWithWhitelist(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (whitelist.contains(obj.getClass().getName())) {
                return obj;
            } else {
                System.err.println("Class not whitelisted: " + obj.getClass().getName());
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization failed: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        SafeData safeData = (SafeData) deserializeWithWhitelist("safeData.ser");
        if (safeData != null) {
            System.out.println("Safe Data Deserialized: " + safeData);
        }

        Object nonWhitelistedData = deserializeWithWhitelist("nonWhitelistedData.ser");
        if (nonWhitelistedData != null) {
            System.out.println("Non-Whitelisted Data Deserialized: " + nonWhitelistedData);
        }
    }
}