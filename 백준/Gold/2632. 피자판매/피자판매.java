import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        //원형을 구현하기 위해 사이즈 2배
        int[] pizzaA = new int[m*2+1];
        int[] pizzaB = new int[n*2+1];

        for(int i = 1; i<=m; i++){
            int num = Integer.parseInt(br.readLine());
            pizzaA[i] = num;
            pizzaA[i+m] = num;
        }
        for(int i = 1; i<=n; i++){
            int num = Integer.parseInt(br.readLine());
            pizzaB[i] = num;
            pizzaB[i+n] = num;
        }

        //A피자, B피자 조각에 대한 누적합 배열을 구현 -> 구간별로 연속된 조각의 합을 구하기 위한 준비
        int[] sumA = new int[m*2+1];
        int[] sumB = new int[n*2+1];

        for(int i = 1; i<m*2+1; i++){
            //i번 째 조각의 누적 합 = i-1번 째 조낙의 누적 합 + i번째 조각의 크기
            sumA[i]+=sumA[i-1]+pizzaA[i];
        }
        for(int i = 1; i<n*2+1; i++){
            sumB[i]+=sumB[i-1]+pizzaB[i];
        }

        //손님이 원하는 크기 까지의 경우의 수만 구한다
        int[] casesA = new int[size+1];
        int[] casesB = new int[size+1];

        //몇 번째 조각을 시작으로 할지
        for(int i = 1; i<=m; i++){
            //몇 개의 조각을 연달아 누적시킬 것 인가
            for(int j = 0; j<m; j++){
                int sum = sumA[i+j]-sumA[i-1];
                if(sum<=size)
                    casesA[sum]++;
            }
        }
        //전체 합이 m개 만큼 증가했을 경우 m-1개를 빼줌
        if(sumA[m]<=size)
            casesA[sumA[m]]-=(m-1);
        for(int i = 1; i<=n; i++){
            for(int j = 0; j<n; j++){
                int sum = sumB[i+j]-sumB[i-1];
                if(sum<=size)
                    casesB[sum]++;
            }
        }
        //전체 합이 m개 만큼 증가했을 경우 m-1개를 빼줌
        if(sumB[n]<=size)
            casesB[sumB[n]]-=(n-1);

        int cnt = 0;
        //A pizza의 합의 경우의 수에서 size 크기의 수 더하기
        cnt+=casesA[size];
        //B pizza의 합의 경우의 수에서 size 크기의 수 더하기
        cnt+=casesB[size];
        //A와 B의 합으로 size를 구하는 경우의 수 더하기
        for(int i = 1; i<size; i++)
            cnt+=casesA[i]*casesB[size-i];

        bw.write(String.valueOf(cnt));
        br.close();
        bw.close();
    }
}