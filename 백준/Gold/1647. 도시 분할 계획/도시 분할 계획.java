import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class node implements Comparable<node> {
   int st, end, w;

   public node(int st, int end, int w) {
      this.st = st;
      this.end = end;
      this.w = w;
   }

   @Override
   public int compareTo(node o) {
      return this.w - o.w;
   }
}// city

public class Main {
   //길 유지비용 최소로 하기
   //두 집 사이에 경로 항상 존재해야한다
   //제일 큰 가중치 빼면 유지비 최소
   //priorityqueue 사용해주기
   static int n, m, max;
   static int[] parent;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      n = Integer.parseInt(st.nextToken());//집의 개수
      m = Integer.parseInt(st.nextToken());//길의 개수

      parent = new int[n + 1];
      // 베열 초기화
      for (int i = 1; i <= n; i++) {
         parent[i] = i;
      }

      PriorityQueue<node> que = new PriorityQueue<node>();

      //연결된 집에 관한 정보들 받아와서 que에 넣는다
      for (int i = 0; i < m; i++) {
         st = new StringTokenizer(br.readLine());
         int a = Integer.parseInt(st.nextToken());//시작집
         int b = Integer.parseInt(st.nextToken());//연결된 집
         int cost = Integer.parseInt(st.nextToken());//유지비

         que.add(new node(a, b, cost));
      }
      
      int res = 0;
      int cnt = 0;
      
      //que 빌때까지 진행
      int max = 0;
      while(!que.isEmpty()) {
         node curr = que.poll();//일단 뽑고
         if(union(curr.st,curr.end)) {
            cnt++;//간선수 카운팅 해준다
            res += curr.w;//가중치(비용) 더해준다
            max = Math.max(max,curr.w);
         }
         if(cnt == n-1) break; //가중치 가장 큰 간선을 빼면 되는거니까 간선 n-2되면 그만두기
      }
      System.out.println(res-max);
   }// main

   // 찾아요
   static int find(int a) {
      if (parent[a] == a)
         return a;
      else {
         return parent[a] = find(parent[a]);
      }
   }// find

   static boolean union(int a, int b) {
      a = find(a);
      b = find(b);

      if (a == b)
         return false;
      else {
         
         parent[a] = b;
         return true;
      }
   }// union

}// class