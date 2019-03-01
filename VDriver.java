import java.io.FileNotFoundException;

public class VDriver{
  public static void main(String[]args){    
    solve("data3.dat",true); 
    
  }
  //ok i need to make sure original @ symbol still stays after a false solution
  //why are the number of moves 0 ... WHY ... lol it has to do with how i delete the @ signs

  public static void solve(String filename, boolean setAnimate){
    try{
      Maze f;
      f = new Maze(filename);//true animates the maze.
      
      f.setAnimate(setAnimate);
      System.out.println(f.solve()); System.out.println();
      System.out.println(f);
    }catch(FileNotFoundException e){
      System.out.println("Invalid filename: "+filename);
    }
  }
}