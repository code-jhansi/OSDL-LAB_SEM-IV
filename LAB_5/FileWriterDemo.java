import java.io.FileWriter;
import java.io.IOException;
public class FileWriterDemo{
    public static void main(String[] args){
        try{
            FileWriter fw = new FileWriter("output1.txt");
            fw.write("Hello Java\n");
            fw.write("FileWriter Demo");
            fw.close();
            System.out.println("Data written successfully");
        }catch(IOException e){
            System.out.println("File error:" + e.getMessage());
        }
    }
}


