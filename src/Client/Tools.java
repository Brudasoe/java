package Client;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Vector;

public class Tools {
    /**
     * serializing the object passed as the 1st argument
     * to a byte array which is returned as the result
     * @param obj - object to serialize
     * @return - byte array
     */
    /**
     * serializing the object passed as the 1st argument
     * to a byte array which is returned as the result
     * @param obj - object to serialize
     * @return - byte array
     */
    static byte[] serialize(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            out.flush();
            return bos.toByteArray();
        }
        catch(IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * deserializing the object from the byte array passed as the 1st argument
     *
     * @param path
     *         - byte array that contains serialized object
     * @return - the object on success, null on failure
     */
    static Object deserialize(byte[] bytes) throws ClassNotFoundException {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream in = new ObjectInputStream(bis);
            obj = in.readObject();
            in.close();
            System.out.println("Serialized data is retrieved from bytes array");
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
            return obj;
        }
    }
}
