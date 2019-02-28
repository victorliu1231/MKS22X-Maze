import java.io.FileNotFoundException;

public class VDriver{
  public static void main(String[]args){    
    solve("data1.dat",false); 
    
  }

  public static void solve(String filename, boolean setAnimate){
    try{
      Maze f;
      f = new Maze(filename);//true animates the maze.
      
      f.setAnimate(setAnimate);
      f.solve();
      System.out.println(f);
    }catch(FileNotFoundException e){
      System.out.println("Invalid filename: "+filename);
    }
  }
}