import java.io.FileOutputStream;
import java.io.IOException;
public class FileOutputStreamDemo {
    public static void main(String[] args){
        FileOutputStream fos = null;
        try{
            //Create or open file
            fos = new FileOutputStream("output.txt");
            String message = "Welcome to Java FileOutputStream.\nFile writing example.";
            //convert string to bytes
            byte[] data = message.getBytes();
            //write data to file
            fos.write(data);
            System.out.println("Data written to file successfully.");
        }catch(IOException e){
            System.out.println("File error:" +e.getMessage());
        }finally{
            try{
                if(fos!=null){
                    fos.close();//close the file
                }
            }catch(IOException e){
                System.out.println("Error closing file");
            }
        }
    }    
}
