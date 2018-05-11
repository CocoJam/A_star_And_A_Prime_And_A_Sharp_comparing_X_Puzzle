import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class A_Star {
    public static Recorder recorder = new Recorder();
    public static Comparator<Node> comparatorF = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.f, o2.f);
        }
    };

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

    public static void main(String[] args) throws IOException {

        File_Reader file_reader = new File_Reader("25.pl");
        for (Node node : file_reader.nodes) {
            A_Star a_star = new A_Star();
            Node goal = new Node(801234567);
            Node init = new Node(node.puzzle);
            List<Node> open = new ArrayList<>();
            open.add(init);
            a_star.recorder.record("Init: " + Arrays.toString(init.puzzle));
            long time1 = System.nanoTime();
            List<Node> finish = a_star.run(new Manhattan_Distance(), goal, open, new ArrayList<Node>(), new ArrayList<>());
            long time2 = System.nanoTime();
            a_star.recorder.record("NanoTime: " + (time2 - time1));
            a_star.recorder.record("801234567");
            a_star.recorder.write("25/" + (Arrays.toString(node.puzzle)));
            PuzzleBoard.evaluated = 0;
            PuzzleBoard.generated = 0;
            System.out.println("Done: " + (Arrays.toString(node.puzzle)));
        }
    }
}
