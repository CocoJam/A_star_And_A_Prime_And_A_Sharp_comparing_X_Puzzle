public class Manhattan_Distance implements Heuristics{
    @Override
    public int compute(Integer[] state, Integer[] goal) {
        int sum=0;
        int size =(int) Math.sqrt(state.length);
        for (int i = 1; i < state.length; i++) {
           sum+= Math.abs(PuzzleBoard.Xposition(state[i],size)-PuzzleBoard.Xposition(goal[i],size))+ Math.abs(PuzzleBoard.Yposition(state[i],size)-PuzzleBoard.Yposition(goal[i],size));
        }
        return sum;
    }
}
