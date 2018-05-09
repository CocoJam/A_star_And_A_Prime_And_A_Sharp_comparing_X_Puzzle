import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class File_Reader {
    public BufferedReader reader;
    public List<Node> nodes;
    public File_Reader(String file) throws IOException {
       try{ this.reader = new BufferedReader(new FileReader(file));
           this.nodes = stateReader();}
        catch (IOException e){
           reader.close();
        }
        finally {
           reader.close();
       }
    }

    public List<Node> stateReader() throws IOException {
        String line = "";
        List<Node> list = new ArrayList<>();
        while((line = reader.readLine()) != null){
            System.out.println(line);
            Pattern p = Pattern.compile("( \\d+)");
            Matcher m = p.matcher(line);
            if (m.find()){
                Node node = new Node(m.group().trim());
                list.add(node);
            }
        }
        return list;
    }
}
