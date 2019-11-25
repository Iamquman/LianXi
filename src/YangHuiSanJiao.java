import java.util.Scanner;

public class YangHuiSanJiao {
    public static void main(String[] args) {
        //键盘输入一个数
        Scanner input = new Scanner(System.in);
        System.out.print("键盘输入一个想要输出的行数:");
        int sum = input.nextInt();

        for (int i = 1; i <= sum; i++) {
            int a = 1;
            //每次循环输出一行
            //打印空格
            for (int s = 0; s < sum - i; s++) {
                System.out.print("  ");     //输出一个空格
            }
            //输出每行的第一个数：1
            System.out.printf(" %4d", 1);
            //输出中间的数
            if (i > 1) {
                //i-2个数
                for (int j = 1; j <= i - 2; j++) {
                    a = a * (i - j) / j;           //先乘a再除j
                    System.out.printf("%4d", a);
                }
                //输出每行的最后一个数：1
                System.out.printf("%4d", 1);
            }
            //回车换行
            System.out.println();

        }
    }
}
