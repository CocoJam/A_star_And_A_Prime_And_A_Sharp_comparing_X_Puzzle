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

    public File_Reader(String file) {
        try {
            this.reader = new BufferedReader(new FileReader(file));
            String line = "";
            nodes = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                Pattern p = Pattern.compile("( \\d+)");
                Matcher m = p.matcher(line);
                if (m.find()) {
                    Node node = new Node(m.group().trim());
                    nodes.add(node);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
