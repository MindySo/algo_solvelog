import java.io.*;
import java.util.*;

class Main{
  public static void main(String args[]) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    boolean[][] board = new boolean[100][100];
    int size = 0;

    for(int n = 0; n < N; n++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      for(int i = 0; i < 10; i++){
        for(int j = 0; j < 10; j++){
          if(!board[r + i][c + j]){
            board[r + i][c + j] = true;
            size++;
          }
        }
      }
    }
      System.out.println(size);
  }
}