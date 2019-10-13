import java.util.ArrayList;
public class Vertex {
	
	int index;
	ArrayList<Integer> Adjacencies  = new ArrayList<>();
	int [][] config = new int[7][7];
	int row,col;
	String move;
	
	public Vertex(int index,int [][] board,String move,int row, int col) {
		this.index = index;
		config = board;
		this.move = move;
		this.row = row;
		this.col = col;
	}
	
	public void addAdjacency(int nodeNumber) {
		Adjacencies.add(nodeNumber);
	}
	
	public String move() {
		return Integer.toString(row)+","+Integer.toString(col)+","+move;
	}
}