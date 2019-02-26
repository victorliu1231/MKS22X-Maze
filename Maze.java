import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze{
  private int[][] board;

  public Maze(String str){
    board = new int[][];
    System.out.println(printFile(str));
  }

  public String printFile(String str){
    String ans = "";
    try{
      File f = new File(str);
      Scanner s = new Scanner(f);

      while (s.hasNextLine()){
        ans+= s.nextLine();
        ans+= '\n';
      }
      return ans;
    } catch (FileNotFoundException e){
      return "Enter a file that actually exists!";
    }
  }
}
