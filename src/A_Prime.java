import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class A_Prime {
    private static Recorder recorder = new Recorder();
    private static int generated = 0;
    private static int expaned = 0;
    private static int evaluated = 0;
    public List<Node> run (Heuristics heuristics,int E, Node goal, List<Node> open, List<Node>close){
        Integer[] node = open.get(0).puzzle;
        if(Arrays.equals(node,goal.puzzle)){
            System.out.println("Done");
            System.out.println(open.get(0).g);
            return close;
        }
        else if (PuzzleBoard.contains(close,open.get(0))){
            open.remove(0);
            return run(heuristics,goal,open,close);
        }
        else {
            List<Node> nodeList = new ArrayList<>();
            List <Node> puzzleBoard=PuzzleBoard.move(open.get(0));
            generated+= puzzleBoard.size();
            for (Node node1 : puzzleBoard) {
                node1.g  = open.get(0).g + 1;
                node1.f = node1.g + heuristics.compute(node1.puzzle,goal.puzzle);
                evaluated++;
                node1.p = Math.max(node1.f,node1.g+E);
                nodeList.add(node1);
            }
            Comparator<Node> comparator =new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return Integer.compare(o1.p,o2.p);
                }
            };
            System.out.println(1);
            nodeList.sort(comparator);
            for (Node node1 : nodeList) {
                recorder.record("Node: "+ Arrays.toString(node1.puzzle) + " P-value: "+ node1.p);
            }
            close.add(0,open.get(0));
            expaned++;
            return run(heuristics,goal,nodeList,close);
        }
    }

    public List<Node> run (Heuristics heuristics, Node goal, List<Node> open, List<Node>close){
        Integer[] node = open.get(0).puzzle;
        if(Arrays.equals(node,goal.puzzle)){
            System.out.println(open.get(0).g);
            return close;
        }
        else if (PuzzleBoard.contains(close,open.get(0))){
            open.remove(0);
            return run(heuristics,goal,open,close);
        }
        else {
            List<Node> nodeList = new ArrayList<>();
            List <Node> puzzleBoard=PuzzleBoard.move(open.get(0));
            generated+= puzzleBoard.size();
            for (Node node1 : puzzleBoard) {
                node1.g  = open.get(0).g + 1;
                node1.f = node1.g + heuristics.compute(node1.puzzle,goal.puzzle);
                evaluated++;
                nodeList.add(node1);
            }
            Comparator<Node> comparator =new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return Integer.compare(o1.f,o2.f);
                }
            };
            nodeList.sort(comparator);
            for (Node node1 : nodeList) {
                recorder.record("Node: "+ Arrays.toString(node1.puzzle) + " F-value: "+ node1.f);
            }
            close.add(0,open.get(0));
            expaned++;
            return run(heuristics,goal,nodeList,close);
        }
    }
    public static void main(String[] args) {
        A_Prime a_prime = new A_Prime();
        Node goal = new Node(801234567);
        Node init = new Node(642510837);
        List<Node> open = new ArrayList<>();
        open.add(init);
        List<Node> finish = a_prime.run(new Manhattan_Distance(),1, goal,open,new ArrayList<Node>());
    }
}
