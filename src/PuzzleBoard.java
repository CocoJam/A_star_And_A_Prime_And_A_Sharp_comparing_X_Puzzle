import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleBoard {
    public static int generated = 0;
    public static int evaluated=0;
    public static int expaned = 0;

    public static List<Node> move(Node inti){

        int size =(int)Math.sqrt(inti.puzzle.length);
        Integer[] init = inti.puzzle;
        List<Node> listOfPuzzle = new ArrayList<>();

        int pointZero = init[0];

        if(Xposition(pointZero,size)<size){
            int switchInt=pointZero+1;
            listOfPuzzle.add(switching(init, switchInt));
        }
        if (Yposition(pointZero,size)> 1){
            int switchInt = pointZero-size;
            listOfPuzzle.add(switching(init, switchInt));
        }
        if(Xposition(pointZero,size)>1){
            int switchInt = pointZero-1;
            listOfPuzzle.add(switching(init, switchInt));
        }
        if (Yposition(pointZero,size)<size){
            int switchInt = pointZero+size;
            listOfPuzzle.add(switching(init, switchInt));
        }
        return listOfPuzzle;
    }

    public static List<Node> checkPresentNode(List<Node> listOfPuzzle, List<Node> generate, Heuristics heuristics, Node inti, Node goal){
        for (Node node1 : listOfPuzzle) {
            if (!PuzzleBoard.contains(generate,node1)){
                generated++;
                evaluated++;
                node1.g = inti.g+1;
                node1.f = node1.g + heuristics.compute(node1.puzzle,goal.puzzle);
                A_Star.recorder.record(inti.f+","+expaned+","+PuzzleBoard.generated+","+PuzzleBoard.evaluated);
                node1.setParent(inti);
                generate.add(node1);
            }
        }
        return generate;
    }

    public static List<Node> move(Node inti,List<Node> generate, Node goal, Heuristics heuristics){

        List<Node> listOfPuzzle = move(inti);

        expaned++;
//        for (Node node1 : listOfPuzzle) {
//            if (!PuzzleBoard.contains(generate,node1)){
//                generated++;
//                evaluated++;
//                node1.g = inti.g+1;
//                node1.f = node1.g + heuristics.compute(node1.puzzle,goal.puzzle);
//                A_Star.recorder.record(inti.f+","+expaned+","+PuzzleBoard.generated+","+PuzzleBoard.evaluated);
//                node1.setParent(inti);
//                generate.add(node1);
//            }
//        }
        return checkPresentNode(listOfPuzzle,generate,heuristics,inti,goal);
    }

    public static int Xposition(int x,int size){
        return (x+1)% size == 0? size: (x+1)% size;
    }
    public static int Yposition(int y, int size){
        if((y+1)%size ==0){
           return  (y+1)/size == size?size:(y+1)/size;
        }
        return (y+1)/size+1;
    }
    public static Node switching(Integer[] init, int switchpoint){
        Integer[] clone = init.clone();
        int temp = clone[0];
        for (int i = 0; i < clone.length; i++) {
            if(clone[i]==switchpoint){
                clone[0] = clone[i];
                clone[i] = temp;
                break;
            }
        }
        Node node = new Node(clone);
        return node;
    }

    public static boolean contains(List<Node> list, Node state){
        for (Node integers : list) {
          if (Arrays.equals(integers.puzzle,state.puzzle)){
              return true;
          };
        }
        return false;
    }

    public static void main(String[] args) {
        List<Node> list = new ArrayList<>();
        Node node = new Node("123456780");
        node.g=1000;
        list.add(node);
        List<Node> list2 = new ArrayList<>();
        Node node2 = new Node("123456780");
        Node node3 = new Node("123456783");
        list2.add(node2);
        list2.add(node3);

        for (Node node1 : list2) {
            if (!PuzzleBoard.contains(list,node1)){
                list.add(node1);
            }
        }
        for (Node node1 : list) {
            System.out.println(Arrays.toString(node1.puzzle));
            System.out.println(node1.g);
        }
    }
}
