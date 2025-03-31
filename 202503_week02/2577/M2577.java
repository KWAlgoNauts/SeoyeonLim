import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class M2577 {
    public static void main(String[] args) throws IOException {
        //배열 0으로 초기화(자동으로 0으로 초기화 됨)
        int[] numbers = new int[10];

        //입력 받기 위함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //100<= ABC <1000
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        //곱하기
        int result = A * B * C;

        //문자열로 변경
        String strResult = Integer.toString(result);

        //result의 모든 숫자를 하나씩 순회하면서 그 숫자 =1 한 배열에 +1 하기
        for(int i = 0; i < strResult.length(); i++){
            int dig = Character.getNumericValue(strResult.charAt(i));
            numbers[dig]++;
        }

        for(int i =0 ; i < numbers.length; i++){
            System.out.println(numbers[i]);
        }
    }
}