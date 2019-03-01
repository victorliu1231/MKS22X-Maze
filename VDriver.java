import java.io.FileNotFoundException;

public class VDriver{
  public static void main(String[]args){
    solve("Maze1.txt",true);

  }

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
