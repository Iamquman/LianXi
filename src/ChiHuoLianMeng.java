import java.util.Scanner;

public class ChiHuoLianMeng {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //餐品信息数组
        String[] cai={"红烧肉","黄焖鸡","水煮鱼","干锅牛腩","锡纸烧","糖醋小排"};
        double[] price={38.0,26.0,42.0,36.5,22.0,28.6};
        int[] dianzan=new int[6];
        //订单信息数组(订餐人，所定餐品信息，送餐时间，送餐地址，订单状态，总金额)
        String[] names =new String[4];
        String[] canfen=new String[4];
        int[] times=new int[4];
        String[] adress=new String[4];
        int[] zhuangtai=new int[4];
        double[] sums=new double[4];
        //插入一组订单信息
        names[0]="quman";
        canfen[0]="红烧肉 1份";
        times[0]=18;
        adress[0]="圣和巷";
        sums[0]=38.0;

        int num=-1;
        System.out.println("****************欢迎使用“吃货联盟订餐系统”*******************");
        do {
        System.out.println("**********功能**********");
        System.out.println("1.我要订餐");
        System.out.println("2.查看餐袋");
        System.out.println("3.签收订单");
        System.out.println("4.删除订单");
        System.out.println("5.我要点赞");
        System.out.println("6.退出系统");
        System.out.println("************************");
        System.out.print("请选择：");
        int func = input.nextInt();     //function 功能
            boolean flage=false;        //标注位：false没找到，true找到一个为空的位置
        switch (func){
            case 1:  //因要把订单信息插入到餐袋的四个位置之一处，因此先要遍历数组中有没有空位
                for(int j=0;j<names.length;j++) {
                    if (names[j] == null) {
                        flage = true;
                        System.out.println("*******我要订餐******");
                        System.out.println("序号         菜名            单价          点赞数");
                        for (int i = 0; i < cai.length; i++) {
                            System.out.printf("%d%14S\t%13.1f\t%10d", i + 1, cai[i], price[i],dianzan[i]);
                            System.out.println();
                        }
                        System.out.print("请输入您要订的餐品序号：");
                        int xh = input.nextInt();
                        System.out.print("请输入您需要的份数：");
                        int fen = input.nextInt();
                        String disMeg=cai[xh-1]+" "+fen+"份";//餐品信息：餐品名+份数
                        System.out.print("请输入订餐人姓名：");
                        String name = input.next();
                        System.out.print("请输入送餐时间（10~20点整点送餐）");
                        int time = input.nextInt();
                        while (time > 20 || time < 10) {
                            System.out.print("您的输入有误，请重新输入10~20间的整数：");
                            time = input.nextInt();
                        }
                        System.out.print("请输入送餐地址：");
                        String adressen = input.next();
                        //计算总餐费
                        double sumPrice=price[xh-1]*fen;  //餐品费用
                        //送餐费，当达到50时免餐费
                        double deliCharge=sumPrice>=50?0.0:6.0;
                        System.out.printf("订餐成功！\n您定的是:%S\t%d份\n送餐时间：%d\n餐费：%1.1f\n", cai[xh - 1], fen, time, deliCharge+sumPrice);
                       //保存数据到一组订单数组中
                         names[j]=name;
                        canfen[j]=disMeg;
                        times[j]=time;
                        adress[j]=adressen;
                        sums[j]=deliCharge+sumPrice;
                        break;
                    }
                }
                if(!flage){
                    System.out.println("对不起，您的餐袋已满！");
                }
                break;
            case 2:
                System.out.println("********查看餐袋*********");
                System.out.println("序号\t订餐人\t   餐品信息\t    送餐时间\t送餐地址\t总金额\t    订单状态");
               for(int z=0;z<names.length;z++) {
                   if(names[z]!=null) {
                       String zt = zhuangtai[z]==0?"已预订":"已完成";
                       System.out.printf("%2d\t%7S\t%7s\t%7d\t%12S\t%7.1f\t ",(z + 1)  , names[z] , canfen[z] , times[z] , adress[z] ,sums[z]);
                       System.out.print("\t "+zt+"\n");
                   }
               }
                break;
            case 3:
                boolean flage2=false;     //记录是否找到这条订单，true 找到了  false 没有找到
                System.out.println("******签收订单********");
                System.out.print("请输入要签收的订单需序号：");
                int qiannum=input.nextInt();
                for(int x=0;x<names.length;x++) {      //遍历查找这个序号是否有值
                    if (names[qiannum-1]!=null&&zhuangtai[qiannum - 1] == 1) {
                        flage2=true;
                        System.out.println("您输入的订单号已完成签收，不能再次签收！");
                        break;
                    } else if(names[qiannum-1]!=null&&zhuangtai[qiannum - 1] == 0){
                        zhuangtai[qiannum - 1] = 1;
                        System.out.println("已完成签收！");
                        flage2=true;
                        break;
                    }
                }
                //如果遍历完后没找到，则就没有这条数据
                if(!flage2) {
                    System.out.println("对不起，此条订单不存在");
                }
                break;
            case 4:
                System.out.println("**********删除订单**********");
                System.out.print("请输入想要删除的订单序号：");
                int delet=input.nextInt();
                int delIndex=-1;//定义一个变量用来储存下标的位置
                boolean flage3=false;    //记录是否找到此订单
                //循环查找这条订单
                for(int y=0;y<names.length;y++){
                    if(names[y]!=null&&zhuangtai[y]==1&&delet==y+1){ //有值，已签收，订单序号对应数组下标加1
                        delIndex=y;   //订单对应的数组下标
                        flage3=true;
                        break;
                    }else if(names[y]!=null&&zhuangtai[y]==0&&delet==y+1){
                        System.out.println("此订单未签收，不能删除！");
                        flage3=true;
                        break;
                    }
                }
                if(!flage3){
                    System.out.println("对不起，未找到此条订单");
                }
                //删除操作：循环移位
                if(delIndex!=-1){    //其初始值为-1，若未发生变化则可以执行删除
                    for(int i=delIndex+1;i<=names.length-1;i++){
                        names[i-1]=names[i];
                        canfen[i-1]=canfen[i];
                        times[i-1]=times[i];
                        adress[i-1]=adress[i];
                        sums[i-1]=sums[i];
                    }
                    //清空最后一组数据
                    names[names.length-1]=null;
                    canfen[canfen.length-1]=null;
                    times[times.length-1]=0;
                    adress[adress.length-1]=null;
                    sums[sums.length-1]=0;
                    System.out.println("订单删除成功！");


                }


                break;
            case 5:
                System.out.println("**********我要点赞**********");
                //显示供点赞的餐品信息
                System.out.println("序号         菜名            单价          点赞数");
                for (int i = 0; i < cai.length; i++) {
                    System.out.printf("%d%14S\t%13.1f\t%10d", i + 1, cai[i], price[i],dianzan[i]);
                    System.out.println();
                }
                System.out.print("输入要点赞的餐品序号：");
                int dznum=input.nextInt();
                //该餐品的点赞数加1
                dianzan[dznum-1]++;
                System.out.println("点赞成功！");
                System.out.println(cai[dznum-1]+dianzan[dznum-1]+"赞");
                break;
            case 6:
                break;

        }
        if(func<1||func>5){          //选择功能时，输入1~5之外的数字则退出系统
            break;
        }else {
            System.out.print("输入0返回：");
            num = input.nextInt();
        }

    }while(num==0);
        System.out.println("谢谢使用，欢迎下次光临！");
}
}