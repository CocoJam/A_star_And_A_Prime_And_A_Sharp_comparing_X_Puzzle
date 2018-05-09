import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleBoard {

    public static List<Node> move(Node inti){
        int size =(int)Math.sqrt(inti.puzzle.length);
        Integer[] init = inti.puzzle;
        List<Node> listOfPuzzle = new ArrayList<>();
        int pointZero = init[0];
        if(Xposition(pointZero,size)>1){
            int switchInt = pointZero-1;
           listOfPuzzle.add(switching(init, switchInt));
        }
        if(Xposition(pointZero,size)<size){
            int switchInt=pointZero+1;
            listOfPuzzle.add(switching(init, switchInt));
        }
        if (Yposition(pointZero,size)> 1){
            int switchInt = pointZero-size;
            listOfPuzzle.add(switching(init, switchInt));
        }
        if (Yposition(pointZero,size)<size){
            int switchInt = pointZero+size;
            listOfPuzzle.add(switching(init, switchInt));
        }
        return listOfPuzzle;
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

        Integer[] state2 = {6,0,1,2,3,4,5,8,7};
        Integer[] state3 = {6,0,1,2,3,4,5,8,7};
        Arrays.equals(state2,state3);
        System.out.println( Arrays.equals(state2,state3));
    }
}
