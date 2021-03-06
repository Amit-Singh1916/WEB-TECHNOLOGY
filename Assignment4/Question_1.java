import java.util.Arrays;
import java.util.Scanner;
public class Question_1 {
    public static void main(String[] args)
    {   System.out.println("Enter row and Column in single line then all the values line by line");
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        in.nextLine();
        String[] crops = new String[n];
        for (int i=0;i<n;i++){
            crops[i]=in.nextLine().trim();
        }
        System.out.print(replant(crops));
    }


    public static int helper_function(String[] crops) {
        int N = crops.length;
        int M = crops[0].length();
        int[][] arr = new int[N][M];
        for(int i = 0;i<N;++i)
            Arrays.fill(arr[i], 0);
        arr[0][0] = 0;
        for(int i= 1;i<N;++i) {
            arr[i][0] = arr[i-1][0];
            if(crops[i].charAt(0)!=' '&&crops[i].charAt(0)==crops[i-1].charAt(0)) {
                arr[i][0] = 1+arr[i][0];
                String s = crops[i];
                s = ' '+s.substring(1);
                crops[i] = s;
            }
        }
        for(int i = 1;i<M;++i) {
            arr[0][i] = arr[0][i-1];
            if(crops[0].charAt(i)!=' '&&crops[0].charAt(i)==crops[0].charAt(i-1)) {
                arr[0][i] = 1+arr[0][i];
                String s = crops[0];
                s = s.substring(0,i)+' '+s.substring(i+1);
                crops[0] = s;
            }
        }
        for(int i = 1;i<N;++i) {
            for(int j = 1;j<M;++j) {
                arr[i][j] = arr[i-1][j]+arr[i][j-1]-arr[i-1][j-1];
                if((crops[i].charAt(j)==crops[i].charAt(j-1))||(crops[i].charAt(j)==crops[i-1].charAt(j))){
                    arr[i][j] = 1+arr[i][j];
                    String s = crops[i];
                    s = s.substring(0,j)+' '+s.substring(j+1);
                    crops[i] = s;
                }
            }
        }
        return arr[N-1][M-1];
    }

    public static int replant(String[] crops){
        int n = crops.length;
        String[] crops_temp = new String[n];
        for(int i = 0;i<n;++i) {
            StringBuilder temp = new StringBuilder(crops[i]);
            crops_temp[i] =  temp.reverse().toString();
        }
        int val1 = helper_function(crops);
        int val2 = helper_function(crops_temp);
        return Math.min(val1, val2);
    }
}
