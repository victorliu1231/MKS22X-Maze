import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Maze{
    private char[][] maze;
    private boolean animate;//false by default
    private int sum;
    private boolean reachedEnd;

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
        File file = new File(filename);
        Scanner s = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();
        while (s.hasNextLine()){
          lines.add(s.nextLine());
        }
        if (lines.size() == 0){
          throw new IllegalStateException("Enter a file that actually isn't empty!");
        }
        int numStarts = 0; //should be 1 by end of the next loop
        int numEnds = 0; //should be 1 by end of the next loop
        maze = new char[lines.size()][lines.get(0).length()];
        for (int r = 0; r < maze.length; r++){
          for (int c = 0; c < maze[r].length; c++){
            maze[r][c] = lines.get(r).charAt(c);
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
        sum = 1;
        reachedEnd = false;
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
        System.out.println();
    }

   /*Return the string that represents the maze.
     It should look like the text file with some characters replaced.
    */
    public String toString(){
      String ans = "";
      for (int r = 0; r < maze.length; r++){
        for (int c = 0; c < maze[r].length; c++){
          ans+= maze[r][c];
        }
        if (r != maze.length - 1){ //so that ans doesn't have an extra '\n'
          ans+= '\n';
        }
      }
      return ans;
    }

    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
      for (int r = 0; r < maze.length; r++){
        for (int c = 0; c < maze[r].length; c++){
          if (maze[r][c] == 'S'){
            maze[r][c] = '@';
            return solve(r,c);
          }
        }
      }
      return -1; //just to compile
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
    private int solve(int r, int c){ //you can add more parameters since this is private
        //automatic animation! You are welcome.
      if (!reachedEnd){
        if (maze[r][c] == 'E'){
          reachedEnd = true;
          return sum-1; //minus 1 accounts for the sum++ we did when we hit an E earlier in the recursive sequence
        }
        if (maze[r][c] == '#' || maze[r][c] == '.'){
          return 0; //my current position is a wall or a place already visited
        }
        if(animate){
          clearTerminal();
          System.out.println(this);
          wait(20);
        }
        //if the current position is surrounded by deadends...
        if ((maze[r+1][c] == '@' || maze[r+1][c] == '#' || maze[r+1][c] == '.') &&
            (maze[r-1][c] == '@' || maze[r-1][c] == '#' || maze[r-1][c] == '.') &&
            (maze[r][c+1] == '@' || maze[r][c+1] == '#' || maze[r][c+1] == '.') &&
            (maze[r][c-1] == '@' || maze[r][c-1] == '#' || maze[r][c-1] == '.')){
              maze[r][c] = '.'; //...then mark this spot as visited but not correct path
              sum--;
              return solve(r+1,c) + solve(r-1,c) + solve(r,c+1) + solve(r,c-1); //backtracks
        }
        maze[r][c] = '@'; //because '#' and '.' and 'E' were all already accounted for in earlier if statements, we know that this square must be ' '
        ArrayList<Integer> sumOfPaths = new ArrayList<>();
        if (maze[r+1][c] == ' ' || maze[r+1][c] == 'E'){
          sum++;
          sumOfPaths.add(solve(r+1,c));
        }
        if (maze[r-1][c] == ' ' || maze[r-1][c] == 'E'){
          sum++;
          sumOfPaths.add(solve(r-1,c));
        }
        if (maze[r][c+1] == ' ' || maze[r][c+1] == 'E'){
          sum++;
          sumOfPaths.add(solve(r,c+1));
        }
        if (maze[r][c-1] == ' ' || maze[r][c-1] == 'E'){
          sum++;
          sumOfPaths.add(solve(r,c-1));
        }
        int sum = 0;
        for (int n = 0; n < sumOfPaths.size(); n++){
          sum+= sumOfPaths.get(n);
        }
        return sum;
      }
      return 0; //if maze has reached end, then nothing should happen
    }


}