public class Node {
    public int g=0;
    public Integer[] puzzle;
    public int f=0;
    public int p=0;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node parent=null;

    public Node(Integer[] puzzle) {
        this.puzzle = puzzle;
    }
    public Node(int puzzle) {
        String puzzleString = ""+puzzle;
        String[] p= puzzleString.split("");
        this.puzzle = new Integer[p.length];
        for (int i = 0; i < this.puzzle.length; i++) {
            this.puzzle[i] = Integer.parseInt(p[i]);
        }
    }
    public Node(String puzzle) {
        String[] p= puzzle.split("");
        this.puzzle = new Integer[p.length];
        for (int i = 0; i < this.puzzle.length; i++) {
            this.puzzle[i] = Integer.parseInt(p[i]);
        }
    }
}
