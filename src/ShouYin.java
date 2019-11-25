import java.util.Scanner;

public class ShouYin {
    public static void main(String[] args) {
        //1.商品信息保存
        String[] names = {" 苹果", " 牛奶", "西红柿", "方便面", " 果汁", " 薯片"};
        double[] price = {4.0, 2.5, 2.8, 5.0, 6.0, 8.0,};

        //2.显示所有商品
        System.out.println("**********商品列表**********");
        System.out.println("编号    商品名称     单价");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%2d%9S\t%8.1f", (i + 1), names[i], price[i]);
            System.out.println();

        }

        //3.输入编号、份数，计算一类总商品价格  ，计算所购商品价格累加
        int bh;   //编号
        int a=0;  //商品类型数量
        double sums=0;  //购买的所有商品总额
        do {
            double sum = 0;   //某件商品总额
            Scanner input = new Scanner(System.in);
            System.out.print("请输入想购买的商品编号(输入0取消购买并离开)：");
            bh = input.nextInt();  //输入编号
            if (bh < names.length && bh > 0) {                 //若在范围内则继续进行，若不在范围则跳出循环
                a++;
                System.out.print("请输入份数：");
                int num = input.nextInt();   //输入份数
                sum = sum + num * price[bh - 1];       //计算总金额
                System.out.printf("%d份%S的金额为：%2.1f", num, names[bh - 1], sum);
                System.out.println();
                sums+=sum;    //计算所购商品价格累加
            } else if (bh == 0) {
                System.out.println("您一共购买了"+a+"种商品");
                 System.out.println("您的总消费金额为："+sums);
            } else {
                System.out.println("未找到此商品，请重新输入！");
                continue;
            }
        }while(bh!=0);
            System.out.println("谢谢惠顾！");
    }
}
