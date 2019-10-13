import java.util.*;
public class Program {
	static int [][] start = new int[7][7];

	static int [][] end = new int[7][7];
	static int backCount;
	
	static ArrayList<Vertex> graph = new ArrayList<>();
	
	static boolean foundpath = false;
	static ArrayList<Integer> backtracks = new ArrayList<>();
	
	public static void main(String args[]) {
//		Scanner in = new Scanner(System.in);
//
//		
//		for(int i = 0; i < 7; i++) {
//			for(int j = 0; j < 7; j++) {
//				start[i][j] = in.nextInt();
//			}
//		}
//		
//		for(int i = 0; i < 7; i++) {
//			for(int j = 0; j < 7; j++) {
//				end[i][j] = in.nextInt();
//			}
//		}
//		
//		
//		in.close();
//		genBoard(10,true);//create the start board
//		print(start);
//		genBoard(10,false);//create the end board
//		print(end);
//		
//		Vertex root = new Vertex(0,start.clone(),"",-2,-2);
//		graph.add(root);
//		int [][] thingy = new int[7][];
//		for(int i = 0; i < 7; i++)
//		    thingy[i] = start[i].clone();
//	
//		
//		print(start);
//		thingy[0][0]=9;
//		System.out.println();
////		print(start);
//		backCount = 0;
//		String m = "";
//		backtrack(root,m);
//		System.out.println("Backtrack count: "+ Integer.toString(backCount++));
		


		//print(end);
	//	printOptions(move);
		//System.out.println();
		
		findWorstCase();
	}
	
	

	private static void backtrack(Vertex node, String moves) {
	//first loop through to find the first valid peg to move
		for (int i = 0; i<7;i++) {//the row
			for (int j = 0; j < 7; j++) {//the column
				if (node.config[i][j]==1) {// we have found a peg
					
					if (checkLeft(i,j, node.config) && foundpath == false) {//valid left move
						//back track from new node
						//we want to compute this once only for efficiency
						int size = graph.size();
						int [][] newConfig = new int[7][];
						for(int d = 0; d < 7; d++)
						    newConfig[d] = node.config[d].clone();
						

						graph.add(new Vertex(size, newConfig, "LEFT", i, j));//at the point of adding the node in, the new node will go into position n, not n - 1
						//then move the peg to the left
						graph.get(size).config[i][j]=2;
						graph.get(size).config[i][j-1]=2;
						graph.get(size).config[i][j-2]=1;
						//add this node to the parent's adjaceny list
						node.addAdjacency(size);
						if (Arrays.deepEquals(graph.get(size).config,end)) {
							foundpath = true;
							System.out.println(moves+ graph.get(size).move());
							return;
						}
						
						backtrack(graph.get(size),moves + graph.get(size).move());
						
					}
					if (checkRight(i,j, node.config) && foundpath == false) {
						
						//back track from new node
						int size = graph.size();
						int [][] newConfig = new int[7][];
						for(int d = 0; d < 7; d++)
						    newConfig[d] = node.config[d].clone();
						graph.add(new Vertex(size, newConfig, "RIGHT", i, j));//at the point of adding the node in, the new node will go into position n, not n - 1
						//then move the peg to theli left
						graph.get(size).config[i][j]=2;
						graph.get(size).config[i][j+1]=2;
						graph.get(size).config[i][j+2]=1;
						//add this node to the parent's adjaceny list
						node.addAdjacency(size);
						if (Arrays.deepEquals(graph.get(size).config,end)) {
							foundpath = true;
							System.out.println(moves+ graph.get(size).move());
							return;
						}
						backtrack(graph.get(size),moves + graph.get(size).move());
					}
					if (checkDown(i,j, node.config) && foundpath == false) {
						
						//back track from new node
						int size = graph.size();
						int [][] newConfig = new int[7][];
						for(int d = 0; d < 7; d++)
						    newConfig[d] = node.config[d].clone();
						graph.add(new Vertex(size, newConfig, "DOWN", i, j));//at the point of adding the node in, the new node will go into position n, not n - 1
						//then move the peg to the left
						graph.get(size).config[i][j]=2;
						graph.get(size).config[i+1][j]=2;
						graph.get(size).config[i+2][j]=1;
						//add this node to the parent's adjaceny list
						node.addAdjacency(size);
						if (Arrays.deepEquals(graph.get(size).config,end)) {
							foundpath = true;
							System.out.println(moves+ graph.get(size).move());
							
							return;
						}
						backtrack(graph.get(size),moves + graph.get(size).move());
					}
					if (checkUp(i,j, node.config) && foundpath == false) {
						//back track from that node
						int size = graph.size();
						int [][] newConfig = new int[7][];
						for(int d = 0; d < 7; d++)
						    newConfig[d] = node.config[d].clone();
						graph.add(new Vertex(size, newConfig, "UP", i, j));//at the point of adding the node in, the new node will go into position n, not n - 1
						//then move the peg to the left
						graph.get(size).config[i][j]=2;
						graph.get(size).config[i-1][j]=2;
						graph.get(size).config[i-2][j]=1;
						//add this node to the parent's adjaceny list
						node.addAdjacency(size);
						if (Arrays.deepEquals(graph.get(size).config,end)) {
							foundpath = true;
							System.out.println(moves+"\n"+ graph.get(size).move());
							return;
						}
						backtrack(graph.get(size),moves + graph.get(size).move());
					}
				}
			}
		}
		backCount++;
		
		if (backCount>10000) {
			print(start);
			print(end);
			System.out.println("Backcount:"+backCount);
			
		}
		if(foundpath == false && node.index == 0) {

			System.out.println("No solution bruv");
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
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}
	
	private static void printOptions(Vertex node) {//print all possible move configurations
		
		System.out.println(node.move());
		for (int j = 0; j<node.Adjacencies.size();j++) {
			//System.out.print(node.move);
			printOptions(graph.get(node.Adjacencies.get(j)));
		}
		
		
		
	}
	
	
	private static int [][] genBoard(int numPegs,boolean StartEndFlag){
		int [][] b = new int[7][7];
		
		int k = 0;
		
		//first we generate a board without pegs
		for(int j = 0; j < 7; j++){
			for(int i = 0; i < 7; i++){
				if(j < 2){
					if( i < 2){
						b[j][i] = 0;				
					}else if(i > 4){
						b[j][i] = 0;
					}else{
						b[j][i] = 2;
					}
				}
				else if(j > 4){
					if( i < 2){
						b[j][i] = 0;				
					}else if(i > 4){
						b[j][i] = 0;
					}else{
						b[j][i] = 2;
					}
				}else{

					b[j][i] = 2;
				}
			}
		}
//		print(b);
		
		//Then we randomly insert pegs to create a start configuration
		while(k < numPegs){
			Random random = new Random();
			int x = random.nextInt(7);
			int y = random.nextInt(7);
			boolean bin = false;
			if(x < 2){
				if( y < 2 || y > 4){
					bin = false;
				}else{
					bin = true;
				}
			}
			else if(x > 4){
				if( y < 2 || y >4){	
					bin = false;
				}else{
					bin = true;
				}
			}else{
				bin = true;
			}
			if(bin){
				b[x][y] = 1;
				k++;
			}
			
		}
		
		if (StartEndFlag) {//Then change the start configuration
			for(int d = 0; d < 7; d++)
			    start[d] = b[d].clone();
			
		}else {//Change the final configuration
			for(int j = 0; j < 7; j++){
				for(int i = 0; i < 7; i++){
					if(j < 2){
						if( i < 2){
							b[j][i] = 0;				
						}else if(i > 4){
							b[j][i] = 0;
						}else{
							b[j][i] = 2;
						}
					}
					else if(j > 4){
						if( i < 2){
							b[j][i] = 0;				
						}else if(i > 4){
							b[j][i] = 0;
						}else{
							b[j][i] = 2;
						}
					}else{

						b[j][i] = 2;
					}
				}
			}
			for(int d = 0; d < 7; d++) {
			    end[d] = b[d].clone();
			}
//			end[3][3] =1;
		}

		
		return b;
	}
	private static void findWorstCase() {
		 int [][] startkeeper = new int[7][7];

		 int [][] endkeeper = new int[7][7];
		int highest = -5;//The most amount of back tracks in this iteration
		
		for (int j = 0; j< 50000; j++) {
			graph.clear();
			System.out.println("counter: "+j);
			genBoard(10,true);//create the start board
			
			genBoard(10,false);//create the end board
			
			
			Vertex root = new Vertex(0,start.clone(),"",-2,-2);
			graph.add(root);
			System.out.println();
//			print(start);
			backCount = 0;
			String m = "";
			backtrack(root,m);
			
			
			if (backCount>highest) {
				backtracks.add(backCount);
				print(start);
				print(end);
				for(int d = 0; d < 7; d++)
				    startkeeper[d] = start[d].clone();
				for(int d = 0; d < 7; d++)
				    endkeeper[d] = end[d].clone();
				highest = backCount;
				System.out.println("Backtrack count: "+ Integer.toString(highest++));
				System.out.println("|||||||||||||||||||||||||||||");
			}
		}
		
		print(startkeeper);
		print(endkeeper);
		System.out.println("Backtrack count: "+ Integer.toString(highest++));
		Collections.sort(backtracks);
		int counter = 0;
		for(int back:backtracks) {
			counter ++;
			System.out.println(counter +":"+back);
		}
		
	}
}