import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class lab5q2 {
    public static void main(String[] args){
        FileReader fr = null;
        FileWriter fw = null;
        try{
            fr = new FileReader("source2.txt");
            fw = new FileWriter("Destination2.txt");
            int ch;
            while((ch=fr.read())!=-1){
                fw.write(ch);
            }
            System.out.println("Text Copied successfully");
        }catch(IOException e){
            System.out.println("Error:" + e.getMessage());
        } finally{
            try{
                if(fr!=null)
                    fr.close();
                if(fw!=null)
                    fr.close();
            }catch(IOException e){
                System.out.println("Error closing Fiiles");
            }
        }
    }    
}
