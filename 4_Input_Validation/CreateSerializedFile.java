import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateSerializedFile {
    public static void main(String[] args) {
        ValidatedData data = new ValidatedData("This is validated data");

        try (FileOutputStream fileOut = new FileOutputStream("validatedData.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(data);
            System.out.println("Serialized data is saved in validatedData.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}