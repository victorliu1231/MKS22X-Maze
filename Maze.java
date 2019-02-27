import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Maze{
    private char[][] maze;
    private boolean animate;//false by default

    /*Constructor loads a maze text file, and sets animate to false by default.
      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)
      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!
      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
    */
    public Maze(String filename) throws FileNotFoundException{
        animate = false;
        try{
          File file = new File(filename);
          Scanner s = new Scanner(file);
          ArrayList<String>() lines = new ArrayList<>();
          while (s.hasNextLine()){
            lines.add(s.nextLine());
          }
          if (lines.size() == 0){
            throw new IllegalStateException("Enter a file that actually isn't empty!");
          }
          int numStarts = 0; //should be 1 by end of the next loop
          int numEnds = 0; //should be 1 by end of the next loop
          maze = new char[lines.length][lines[0].length];
          for (int r = 0; r < maze.length; r++){
            for (int c = 0; c < maze[r].length; c++){
              maze[r][c] =
              if (maze[r][c] == 'S'){
                numStarts++;
              }
              if (maze[r][c] == 'E'){
                numEnds++;
              }
            }
          }
          if (numStarts != 1 || numEnds != 1){
            throw new IllegalStateException("You should only have 1 S and 1 E.");
          }
        } catch (FileNotFoundException e){
          return "Enter a file that actually exists!";
        }
    }


    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){

        animate = b;

    }


    public void clearTerminal(){

        //erase terminal, go to top left of screen.

        System.out.println("\033[2J\033[1;1H");

    }






   /*Return the string that represents the maze.

     It should look like the text file with some characters replaced.

    */
    public String toString(){

            return "WRITE THIS METHOD";

    }



    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */
    public int solve(){

            //find the location of the S.


            //erase the S


            //and start solving at the location of the s.

            //return solve(???,???);

    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col){ //you can add more parameters since this is private


        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }

        //COMPLETE SOLVE

        return -1; //so it compiles
    }


}




public class Maze{

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
