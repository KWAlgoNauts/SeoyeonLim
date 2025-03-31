import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class M1475 {
    private static void checkingFor6or9(int[] arr, int dig){
        if(arr[6] <= arr[9]){
            arr[6]++;
        }
        else{
            arr[9]++;
        }
    }

    private static int maximum(int[] arr){
        int setNum = 1;
        for(int i = 0; i<arr.length; i++){
            if(arr[i] > setNum){
                setNum = arr[i];
            }
        }

        return setNum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int setNum = 1;
        int arr[] = new int[10]; //0으로 초기화

        String strN = Integer.toString(N);

        for (int i = 0; i < strN.length(); i++) {
            int dig = Character.getNumericValue(strN.charAt(i));

            if(dig == 6 || dig == 9){
                checkingFor6or9(arr, dig);
            }
            else{
                arr[dig]++;
            }
        }

        setNum = maximum(arr);
        System.out.println(setNum);
    }
}