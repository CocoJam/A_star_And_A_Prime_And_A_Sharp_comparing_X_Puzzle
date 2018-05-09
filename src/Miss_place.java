public class Miss_place implements Heuristics {
    @Override
    public int compute(Integer[] state, Integer[] goal) {
        int sum=0;
        for (int i = 0; i < state.length; i++) {
            sum+= state[i] != goal[i]? 1:0;
        }
        return sum;
    }
}
