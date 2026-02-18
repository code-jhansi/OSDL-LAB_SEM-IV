/*Design and implement a Java application that copies the contents of one file 
to another using byte streams. The program must use FileInputStream to 
read data from a source file and FileOutputStream to write the same data to 
a destination file.*/
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class lab5q1 {
    public static void main(String[] args){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            //source file (file to read from)
            fis = new FileInputStream("source.txt");
            //Destination file (file to write into)
            fos = new FileOutputStream("destination.txt");
            int data;
            while((data=fis.read())!=-1){
                fos.write(data);
            }
            System.out.println("File copied successfully.");
        }catch(IOException e){
            System.out.println("Error occurred: " + e.getMessage());
        }finally{
            try{
                if(fis!=null)
                    fis.close();
                if(fos!=null)
                    fos.close();
            }catch(IOException e){
                System.out.println("Error closing Files");
            }
        }
    }
    
}
