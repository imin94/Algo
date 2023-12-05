import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

   public static void main(String[] args) throws IOException{

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      StringTokenizer st;
      Set<String> set = new HashSet<String>();
      
      int N = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      for(int i=0;i<N;i++) {
         set.add(st.nextToken());
      }
      
      int M = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      for(int i=0;i<M;i++) {
         if(set.contains(st.nextToken()))
        	 bw.write("1 ");
         else
        	 bw.write("0 ");
      }
      
      br.close();
      bw.close();
      
   }

}