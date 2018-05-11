import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class A_Sharp {
    private Recorder recorder = new Recorder();
    private int generated = 0;
    private int expaned = 0;
    private int evaluated = 0;
    Comparator<Node> comparatorP = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.p, o2.p);
        }
    };


    public List<Node> run(Heuristics heuristics, Node goal, List<Node> open, List<Node> close, List<Node> generate) {
        while (true) {
            Integer[] node = open.get(0).puzzle;
            recorder.record(open.get(0).f + "," + PuzzleBoard.expaned + "," + PuzzleBoard.generated + "," + PuzzleBoard.evaluated);
            if (PuzzleBoard.contains(close, open.get(0))) {
                open.remove(0);
                continue;
//                return run(heuristics, goal, open, close, generate);
            } else {
                List<Node> puzzleBoard = PuzzleBoard.move(open.get(0));
                if (PuzzleBoard.contains(puzzleBoard, goal)) {
                    recorder.record("Goal: " + Arrays.toString(open.get(0).puzzle) + " cost: " + open.get(0).g);
                    recorder.record("Heuristics: " + heuristics.getClass().getSimpleName());
                    return close;
                }
                else{

                    puzzleBoard = PuzzleBoard.checkPresentNode(puzzleBoard,generate,heuristics,open.get(0),goal);
                    puzzleBoard.sort(comparatorP);
                    close.add(0, open.get(0));
                    PuzzleBoard.expaned++;
                    open = puzzleBoard;
                }
            }
        }
    }

    public static void main(String[] args) {
        A_Sharp a_prime = new A_Sharp();
        Node goal = new Node(801234567);
        Node init = new Node(642510837);
        List<Node> open = new ArrayList<>();
        open.add(init);
//        List<Node> finish = a_prime.run(new Manhattan_Distance(), 1, goal, open, new ArrayList<Node>());
    }
}
