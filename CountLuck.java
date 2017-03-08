import java.io.*;
import java.util.*;

class Cell{
/* This class is for representing each cell of a matrix*/
    protected char value;
    HashMap<Integer,ArrayList> adjacents;
    ArrayList<Integer> adjacentCol;
    
    Cell(char val){
        this.value=val;
        this.adjacents=null;
        this.adjacentCol=null;    
       
    }
    
   void setAdjacents(int row,int col,int N,int M){
        this.adjacents=new HashMap<Integer,ArrayList>();
        this.adjacentCol=new ArrayList<Integer>();
       //System.out.println("For "+this.value+", Row,Col is in "+row+" "+col);
        adjacentCol.add(col);
        if(row > 0)
            this.adjacents.put(row-1,adjacentCol);
    
              
        if(row+1 < N) 
            this.adjacents.put(row+1,adjacentCol);
        
          
        this.adjacentCol=new ArrayList<Integer>();
        if(col-1 >= 0 )
             this.adjacentCol.add(col-1);
        if(col+1 < M)    
             this.adjacentCol.add(col+1);
        this.adjacents.put(row,adjacentCol);
        
        
    }
    
}

public class CountLuck {
    
    static int find_path(Cell[][] cells,Cell start,Cell end){
        
        Stack<Cell> DFS=new Stack<Cell>(); // as there is a single path to destination
        Set<Cell> visited=new HashSet<Cell>();   // keep track of visited nodes  without going back and forth
        HashMap<Cell,Integer> pathTrace=new HashMap<Cell,Integer>(); //Backtracking the path of a node
        int choice=0; // choice of adjacent cells from current cell
        int[] path=new int[4]; //number of branching paths from start cell
        int count=0; //count of paths
        
        DFS.add(start);
        pathTrace.put(start,-1); //root doesn't have parent
        
        while(!DFS.isEmpty()){ 
        /*Perform DFS on the cells of the matrix*/
        
            Cell cell=DFS.pop();
            int parent=pathTrace.get(cell);
             if(cell.value=='*')
                return path[parent];
            visited.add(cell);
       //     System.out.println("Current cell "+cell.value);
            
            Iterator it=cell.adjacents.entrySet().iterator(); //iterate thru hashmap to find adjacents
            while(it.hasNext()){
                Map.Entry<Integer,ArrayList<Integer>> pair=(Map.Entry<Integer,ArrayList<Integer>> )it.next();
                int row=(int)pair.getKey();
                ArrayList<Integer> columns=new ArrayList<Integer>(pair.getValue());
                
                for(int col: columns){         
                    if(!visited.contains(cells[row][col])){
         //           System.out.println("row = "+row+"col = "+col+"value = "+cells[row][col].value);
                        //if start cell, create 'choice' number of different paths
                        //have an array increment of count for different paths eg. path[0]=2
                        //hashmap path number -> neighbor nodes. when a node's neighbor is *, see from which path it has come.
                        //return that path's count
                        if(cells[row][col].value!='X' && cells[row][col].value!='M'){
                            choice++;
                            DFS.add(cells[row][col]);
                            if(pathTrace.get(cell)==-1)
                                pathTrace.put(cells[row][col],choice-1);
                            else
                                pathTrace.put(cells[row][col],parent);
                         
                        }
                     
                    }     
                }
                
            }
            if(choice > 1){    
            /* This part gets executed for the current cell when there is more than one path to choose from */
                if(cell.value=='M'){            
                    path=new int[choice];
                    Arrays.fill(path,1);
                }
                else{
                    path[parent]++;
                }
               
           }
            choice=0; //reset choice for every cell
        }
        return 0;
    }

    public static void main(String[] args) {
        
        Scanner scan=new Scanner(System.in);
        int T=scan.nextInt(); //number of test cases
        for(int i=0;i<T;i++){
            int N=scan.nextInt(); //row size
            int M=scan.nextInt(); //column size
            scan.nextLine();
            Cell[][] cells=new Cell[N][M];
            Cell startcell=null,endcell=null;
            for(int row=0;row<N;row++){
                String row_data=scan.nextLine(); 
                for(int col=0;col<M;col++){
                    cells[row][col]=new Cell(row_data.charAt(col)); //each cell of the matrix
                    cells[row][col].setAdjacents(row,col,N,M);
                    if(cells[row][col].value=='M')
                        startcell=cells[row][col];
                    if(cells[row][col].value=='*')
                        endcell=cells[row][col];
                }
            }
            int guess_K=scan.nextInt();
            if(startcell!=null && endcell!=null){
                int actual_K=find_path(cells,startcell,endcell); 
            if(guess_K==actual_K)
                System.out.println("Impressed");
            else
                System.out.println("Oops!");
            }
        }
    }
}
