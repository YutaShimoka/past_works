import java.util.Arrays;

public class Gauss {

    public static void main(String[] args) {

        System.out.println("1～10までの数字を全て足しなさい\n");

        long startTime;
        int sum = 0;

        System.out.println("--- 平凡エンジニア ---");

        startTime = System.currentTimeMillis();

        int i = 0;
        while (i < Integer.MAX_VALUE / 3) {
            int numbers[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
            sum = 0;
            for (int num : numbers) {
                sum += num;
            }
            i++;
        }

        System.out.print("繰り返し" + i + "回, ");
        System.out.println(Double.valueOf(System.currentTimeMillis() - startTime) / 1000 + "秒");
        System.out.println("1+2+3+・・・・=" + sum);

        System.out.println("--- 天才がうすくん ---");

        startTime = System.currentTimeMillis();

        int j = 0;
        while (j < Integer.MAX_VALUE / 3) {
            int start = 1;
            int end = 10;
            int eCnt = end - start + 1;
            sum = (start + end) * (eCnt / 2);
            if ((eCnt) % 2 == 1) {
                sum = sum + (start + end) / 2;
            }
            j++;
        }

        System.out.print("繰り返し" + j + "回, ");
        System.out.println(Double.valueOf(System.currentTimeMillis() - startTime) / 1000 + "秒");
        System.out.println("11×5=" + sum);
    }
}
