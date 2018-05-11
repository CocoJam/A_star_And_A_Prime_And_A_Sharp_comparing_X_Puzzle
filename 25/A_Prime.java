import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class A_Prime {
    private Recorder recorder = new Recorder();
    private int generated = 0;
    private int expaned = 0;
    private int evaluated = 0;
    public static List<Node> run(Heuristics heuristics, Node goal, List<Node> open, List<Node> close, List<Node> generate) {
        while (true) {
            Integer[] node = open.get(0).puzzle;
            recorder.record(open.get(0).f + "," + PuzzleBoard.expaned + "," + PuzzleBoard.generated + "," + PuzzleBoard.evaluated);
            if (Arrays.equals(node, goal.puzzle)) {
                recorder.record("Goal: " + Arrays.toString(open.get(0).puzzle) + " cost: " + open.get(0).g);
                recorder.record("Heuristics: " + heuristics.getClass().getSimpleName());
                return close;
            } else if (PuzzleBoard.contains(close, open.get(0))) {
                open.remove(0);
                continue;
//                return run(heuristics, goal, open, close, generate);
            } else {
                List<Node> puzzleBoard = PuzzleBoard.move(open.get(0), generate, goal, heuristics);
                puzzleBoard.sort(comparatorF);
                close.add(0, open.get(0));
                PuzzleBoard.expaned++;
                open = puzzleBoard;
//                return run(heuristics, goal, puzzleBoard, close, generate);
            }
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
