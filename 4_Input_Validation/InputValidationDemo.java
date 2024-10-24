import java.io.*;
import java.util.zip.CRC32;

class ValidatedData implements Serializable {
    private static final long serialVersionUID = 1L;
    String message;

    ValidatedData(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ValidatedData: " + message;
    }
}

public class InputValidationDemo {

    // Method to calculate checksum
    private static long calculateChecksum(String fileName) throws IOException {
        CRC32 crc = new CRC32();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                crc.update(buffer, 0, bytesRead);
            }
        }
        return crc.getValue();
    }

    public static Object deserializeWithValidation(String fileName, long expectedChecksum) {
        try {
            long checksum = calculateChecksum(fileName);
            if (checksum != expectedChecksum) {
                System.err.println("Validation failed: Checksum mismatch.");
                return null;
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                return ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization failed: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // Set an example checksum for trusted data file
        long trustedChecksum = 4052609991L;
        ValidatedData validatedData = (ValidatedData) deserializeWithValidation("validatedData.ser", trustedChecksum);
        if (validatedData != null) {
            System.out.println("Validated Data Deserialized: " + validatedData);
        }
    }
}
