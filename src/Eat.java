import java.util.Scanner;
public class Eat {
    public static void main(String[] args) {
        //订单信息数组（订餐人，所定餐品（餐品名称，份数），送餐时间，送餐地址，订单状态，总金额）
        String[] names=new String[5];
        String[] disMegs=new String[5];
        int[] times=new int[5];
        String[] addresses=new String[5];
        int[] States=new int[5];
        double[] sumPrices=new double[5];

        Scanner input =new Scanner(System.in);
        //商品信息数组
        String[] commodity={"水煮鱼","红烧肉","锡纸烧"};
        double[] unitPrice={38.5,25.0,26.8};
        int[] praiseNums = new int[3];
        int num=0;  //临时输入变量
        String sum;  //总价
        int n= 0;  //当前订单数
        int code=0;  //餐品编号
        do {
            //功能菜单显示 function menu
            System.out.println("欢迎使用吃货联盟订餐系统！");
            System.out.println("***************************");
            System.out.println("1.我要订餐");
            System.out.println("2.查看餐袋");
            System.out.println("3.签收订单");
            System.out.println("4.删除订单");
            System.out.println("5.我要点赞");
            System.out.println("6.退出系统");
            System.out.println("**************************");
            System.out.print("请选择：");
            num = input.nextInt();
            switch(num){
                case 1:
                    System.out.println("***我要订餐***");
                    //判断餐袋中是否有空位，若有可订餐，若没有，则餐袋已满
                    boolean flage=false;
                    for(int i=0;i<names.length;i++) {
                        if(names[i]==null) {
                            flage=true;
                            System.out.print("请输入订餐人姓名：");
                            String name = input.next();
                            //显示餐品信息:遍历数组
                            System.out.println("商品编号\t\t商品名\t\t商品单价");
                            int j=0;
                            for ( j = 0; j < commodity.length; j++) {
                                System.out.printf("%4d\t\t%7S\t\t%6.1f", (j + 1), commodity[j], unitPrice[j]);
                                //点赞数：为0时不显示
                                if(praiseNums[j]!=0){
                                    System.out.print("\t\t"+praiseNums[j]+"赞");
                                }
                               System.out.println();
                            }

                            System.out.print("选择菜品编号：");
                            code = input.nextInt();
                            System.out.print("请选择份数：");
                            int copies = input.nextInt();
                            //计算金额（餐品金额，送餐费，总价）
                            double sumPrice=unitPrice[code-1]*copies;   //餐品金额
                            double takeoutPrice=sumPrice>=50?0:6.0;  //送餐费
                            sum=sumPrice+takeoutPrice+"元";
                            //商品信息
                             disMegs[i]=commodity[code-1]+"\t"+copies+"份";

                            System.out.print("请输入送餐时间（10点至20点整点送餐）：");
                            int time = input.nextInt();
                            //判断是否为10~20点，若不是则重新输时间
                            while(time<10||time>20){
                                System.out.print("输入错误，请重新输入：");
                                time=input.nextInt();
                            }
                            System.out.print("请输入送餐地址：");
                            String address = input.next();
                            System.out.printf("订餐成功！\n您订的是：%S\t%d份\n",commodity[code-1],copies);
                            System.out.printf("送餐时间：%d点\n餐费：%.1f元\t送餐费：%.0f元\t总价：%S\n",time,sumPrice,takeoutPrice,sum);

                            //将数据存入订单信息中
                            names[i]=name;
                            disMegs[i]=commodity[code-1]+"\t"+copies+"份";
                            times[i]=time;
                            addresses[i]=address;
                            //状态默认为0已预订状态 （int类型的默认值为0）
                            sumPrices[i]=sumPrice;
                            n++;
                            break;
                        }
                    }
                    if(!flage){
                        System.out.println("对不起，您的餐袋已满！");
                    }
                    break;
                case 2: //查看餐袋
                    System.out.println("***查看餐袋***");
                    System.out.println("序号\t\t订餐人\t\t餐品信息\t\t送餐时间\t\t送餐地址\t\t总金额\t\t订单状态");
                    //判断订单状态
                    String state=null;   //订单状态
                    flage=false;
                    for(int i=0;i<=n;i++){
                        if(names[i]!=null) {
                            flage = true;
                            state = States[i] == 0 ? "已预订" : "已完成";
                            System.out.printf("%2d\t\t\t%S\t\t\t%S\t\t\t%2d\t\t\t%S\t\t%2.1f\t\t\t%S\n", (i + 1), names[i], disMegs[i], times[i], addresses[i], sumPrices[i], state);
                        }
                        break;
                    }
                    if(!flage){
                        break;
                    }
                    break;
                case 3: //签收订单
                    flage=false;
                    boolean found=false;
                    System.out.println("***签收订单***");
                    System.out.print("请选择想签收的订单号：");
                    num=input.nextInt();
                    for(int i=0;i<num;i++){
                        if(names[num-1]!=null){
                            flage=true;
                            state= States[i]==0?"已预订":"已完成";
                            if(States[i]==0){
                                States[i]=1;
                                System.out.println("签收成功！");
                                found=true;
                                break;
                            }
                            break;
                        }
                    }
                    if(!found && flage==true){
                        System.out.println("此订单已签收，不能再次签收！");
                    }
                    if(!flage){
                        System.out.println("未找到此条订单！");
                    }
                    break;
                case 4: //删除订单
                    System.out.println("***删除订单***");
                    System.out.print("请选择需要删除的订单号：");
                    num=input.nextInt();
                    found=false;
                    flage=false;
                    for(int i=0;i<num;i++){
                        if(names[num-1]!=null){
                            flage=true;
                           if(States[i]!=0){
                               found=true;
                               //可以删除（从num开始，向前移位）
                               for(int j=num-1;j<n;j++){
                                   names[j]=names[j+1];
                                   disMegs[j]=disMegs[j+1];
                                   times[j]=times[j+1];
                                   addresses[j]=addresses[j+1];
                                   States[j]=States[j+1];
                                   sumPrices[j]=sumPrices[j+1];
                               }
                               //清空最后一个数据
                               names[n]=null;
                               disMegs[n]=null;
                               times[n]=0;
                               addresses[n]=null;
                               States[n]=0;
                               sumPrices[n]=0;
                               n--;

                               System.out.println("删除成功！");
                           }

                            break;
                        }
                    }

                    if(!found && flage==true){
                        System.out.println("此订单处于预定状态，不能删除！");
                    }
                    if(!flage){
                        System.out.println("未找到此条订单！");
                    }
                    break;
                case 5: //我要点赞
                    System.out.println("***我要点赞***");
                    //显示餐品信息:遍历数组
                    System.out.println("商品编号\t\t商品名\t\t商品单价");
                    int j=0;
                    for ( j = 0; j < commodity.length; j++) {
                        System.out.printf("%4d\t\t%7S\t\t%6.1f", (j + 1), commodity[j], unitPrice[j]);
                        //点赞数：为0时不显示
                        if(praiseNums[j]!=0){
                            System.out.print("\t\t"+praiseNums[j]+"赞");
                        }
                        System.out.println();
                    }
                    System.out.print("选择想要点赞的餐品编号：");
                    code=input.nextInt();
                    if(code<=commodity.length) {
                        praiseNums[code - 1] += 1;
                        System.out.println("点赞成功！");
                        break;
                    }else{
                        System.out.println("您输入的餐品不存在！");
                    }
                    break;
                case 6: //退出系统
                     System.out.print("您选择了退出系统,");

                    break;
                default: //提醒用户输入不正确
                    while(num>5) {
                        System.out.print("输入错误，请重新输入：");
                        num = input.nextInt();
                    }
                    continue;   //输入正确时显示主菜单
            }
            if(num!=6) {
                System.out.print("输入0返回(其他整数均为退出系统)：");
                int numEnd = input.nextInt();
                if (numEnd == 0) {
                    continue;
                } else if (numEnd != 0) {
                    break;
                }
            }
        }while (num>=1&&num<=5);
        System.out.println("欢迎使用！");

    }
}
