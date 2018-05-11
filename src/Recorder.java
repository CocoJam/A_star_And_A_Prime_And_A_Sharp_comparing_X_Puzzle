import java.io.*;

public class Recorder {
    private String string="";
    public void record(String s){
        string += s+"\n";
    }
    public void print(){
        System.out.println(string);
    }
    public void write(String s){
        File file = new File(s);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        string="";
    }
}
