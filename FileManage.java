import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class FileManage {
    File file;
    String path;

    public FileManage(String path) {
        this.file = new File(path);
        this.path = path;
    }

    public ArrayList<String> getData() {
        ArrayList<String> fdata = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader source = new BufferedReader(reader);
            for (String line = source.readLine(); line != null; line = source.readLine()) {
                if (line.length() != 0) {
                    fdata.add(line);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error: Can not read data from " + path);
        }
        return fdata;
    }

    public void setData(ArrayList<String> fdata) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("");
            for (String line : fdata) {
                writer.append(line + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: Can not write data to " + path);
        }
    }
}