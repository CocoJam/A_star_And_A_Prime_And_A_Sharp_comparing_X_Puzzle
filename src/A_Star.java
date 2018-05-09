import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class A_Star {
    private static Recorder recorder = new Recorder();
    private static int generated = 0;
    private static int expaned = 0;
    private static int evaluated = 0;
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
        A_Star a_star = new A_Star();
        Node goal = new Node(801234567);
        Node init = new Node(642510837);
        List<Node> open = new ArrayList<>();
        open.add(init);
        long time1 = System.nanoTime();
        System.out.println(System.nanoTime());
        List<Node> finish = a_star.run(new Manhattan_Distance_Minus_One(), goal,open,new ArrayList<Node>());
        System.out.println(System.nanoTime()- time1);
    }
}
