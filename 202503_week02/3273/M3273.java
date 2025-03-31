import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class M3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        //한 줄에 여러 숫자를 받기 위함
        StringTokenizer st = new StringTokenizer(br.readLine());
        //두 개의 수를 비교하기 위함
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            set.add(Integer.parseInt(st.nextToken()));
        }

        int x = Integer.parseInt(br.readLine());
        int pairNum = 0;

        HashSet<Integer> visited = new HashSet<>();
        for(int num: set){
            int necessary = x - num;

            if(set.contains(necessary) && !visited.contains(necessary) && !visited.contains(num)){
                pairNum++;
                visited.add(num);
                visited.add(necessary);
            }
        }

        System.out.println(pairNum);
    }
}