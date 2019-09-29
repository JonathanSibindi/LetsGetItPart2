import java.util.*;
public class Program {
	static int [][] start = new int[7][7];

	static int [][] end = new int[7][7];
	
	static ArrayList<Vertex> graph = new ArrayList<>();
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				start[i][j] = in.nextInt();
			}
		}
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				end[i][j] = in.nextInt();
			}
		}
		
		
		in.close();
		Vertex root = new Vertex(0,start.clone(),"",-2,-2);
		graph.add(root);
//		int [][] thingy = new int[7][];
//		for(int i = 0; i < 7; i++)
//		    thingy[i] = start[i].clone();
//	
//		
//		print(start);
//		thingy[0][0]=9;
		System.out.println();
//		print(start);
		backtrack(root);
		print(end);
		printOptions(root);
	}

	private static void backtrack(Vertex node) {
	//first loop through to find the first valid peg to move
		for (int i = 0; i<7;i++) {//the row
			for (int j = 0; j < 7; j++) {//the column
				if (node.config[i][j]==1) {// we have found a peg
					
					if (checkLeft(i,j, node.config)) {//valid left move
						//back track from new node
						//we want to compute this once only for efficiency
						int size = graph.size();
						int [][] newConfig = new int[7][];
						for(int d = 0; d < 7; d++)
						    newConfig[d] = node.config[d].clone();
						
						
						graph.add(new Vertex(size, newConfig, "left", i, j));//at the point of adding the node in, the new node will go into position n, not n - 1
						//then move the peg to the left
						graph.get(size).config[i][j]=2;
						graph.get(size).config[i][j-1]=2;
						graph.get(size).config[i][j-2]=1;
						//add this node to the parent's adjaceny list
						node.addAdjacency(size);
						backtrack(graph.get(size));
						
					}
					if (checkRight(i,j, node.config)) {
						//back track from new node
						int size = graph.size();
						int [][] newConfig = new int[7][];
						for(int d = 0; d < 7; d++)
						    newConfig[d] = node.config[d].clone();
						graph.add(new Vertex(size, newConfig, "right", i, j));//at the point of adding the node in, the new node will go into position n, not n - 1
						//then move the peg to the left
						graph.get(size).config[i][j]=2;
						graph.get(size).config[i][j+1]=2;
						graph.get(size).config[i][j+2]=1;
						//add this node to the parent's adjaceny list
						node.addAdjacency(size);
						backtrack(graph.get(size));
					}
					if (checkDown(i,j, node.config)) {
						//back track from new node
						int size = graph.size();
						int [][] newConfig = new int[7][];
						for(int d = 0; d < 7; d++)
						    newConfig[d] = node.config[d].clone();
						graph.add(new Vertex(size, newConfig, "Down", i, j));//at the point of adding the node in, the new node will go into position n, not n - 1
						//then move the peg to the left
						graph.get(size).config[i][j]=2;
						graph.get(size).config[i+1][j]=2;
						graph.get(size).config[i+2][j]=1;
						//add this node to the parent's adjaceny list
						node.addAdjacency(size);
						backtrack(graph.get(size));
					}
					if (checkUp(i,j, node.config)) {
						//back track from that node
						int size = graph.size();
						int [][] newConfig = new int[7][];
						for(int d = 0; d < 7; d++)
						    newConfig[d] = node.config[d].clone();
						graph.add(new Vertex(size, newConfig, "Up", i, j));//at the point of adding the node in, the new node will go into position n, not n - 1
						//then move the peg to the left
						graph.get(size).config[i][j]=2;
						graph.get(size).config[i-1][j]=2;
						graph.get(size).config[i-2][j]=1;
						//add this node to the parent's adjaceny list
						node.addAdjacency(size);
						backtrack(graph.get(size));
					}
				}
			}
		}
	}
	private static boolean checkLeft(int row, int col, int[][] config) {
		if(col-1>0) {//you need to be able to move the peg two spaces to the left
			if (config[row][col-1]==1 && config[row][col-2]==2){//a valid left move
				return true;
			}			
		}
		return false;
	}
	private static boolean checkDown(int row, int col, int[][] config) {
		if(row+1<6) {//you need to be able to move the peg two spaces down
			if (config[row+1][col]==1 && config[row+2][col]==2){//a valid move down
				return true;
			}			
		}
		return false;
	}
	private static boolean checkRight(int row, int col, int[][] config) {
		if(col+1<6) {//you need to be able to move the peg two spaces to the right i.e. bounds checking
			if (config[row][col+1]==1 && config[row][col+2]==2){//a valid right move
				return true;
			}			
		}
		return false;
	}
	private static boolean checkUp(int row, int col, int[][] config) {
		if(row-1>0) {//you need to be able to move the peg two spaces up i.e. bounds checking
			if (config[row-1][col]==1 && config[row-2][col]==2){//a valid move up
				return true;
			}			
		}
		return false;
	}
	
	private static void print(int[][] end) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				System.out.print(end[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	private static void printOptions(Vertex node) {//print all possible move configurations
		
		System.out.println(node.move());
		for (int j = 0; j<node.Adjacencies.size();j++) {
			System.out.print(node.move);
			printOptions(graph.get(node.Adjacencies.get(j)));
		}
		
	}
}
