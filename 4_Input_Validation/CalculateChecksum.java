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

public class CalculateChecksum {

    // Method to calculate checksum
    public static long calculateChecksum(String fileName) throws IOException {
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

    public static void main(String[] args) {
        try {
            // Calculate and print the checksum of the validatedData.ser file
            long checksum = calculateChecksum("validatedData.ser");
            System.out.println("Checksum for validatedData.ser: " + checksum);
        } catch (IOException e) {
            System.err.println("Error calculating checksum: " + e.getMessage());
        }
    }
}
