package chihuo;
// 顾客类
import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {
    String telno;
    String name;
    String address;

    //构造方法
    public Customer(String name,String telno, String address) {
        this.telno = telno;
        this.name = name;
        this.address = address;
    }

    //重写toString()方法：Alt+Insert键
    public String toString() {
        return "Customer{" +
                "telno='" + telno + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    //顾客订餐流程
    void order(){
        //TODO: 订餐流程
        //显示商家列表
        List<Restaurant> restaurantList = Business.getAllRestaurants();
        for(int i=0;i<restaurantList.size();i++){
            Restaurant r = restaurantList.get(i);
            System.out.printf("%2d - %s\n",i,r);
        }
        System.out.print("请选择商家:");
        int ch = Business.sc.nextInt();
        Restaurant r = restaurantList.get(ch);
        //点餐过程:
        //创建订单
        Order o = new Order(this,r);
        //循环点餐
        do {
            // 显示商家菜品
            for (String k : r.foods.keySet()) {
                System.out.println(r.foods.get(k));
            }
            System.out.println("请输入菜品编号[0-结束]");
            String se = Business.sc.next();
            if(se.equals("0")) break;
            if(r.foods.containsKey(se)) {
                o.add(r.foods.get(se));
            }else{
                System.out.println("编号输入错误");
            }
        }while(true);
        System.out.print("当前订单总金额:");
        System.out.println(o.totalPrice());
        //提交订单
        Business.submit(o);
    }

    //顾客确认清单已完成
    void finishOrder(){
        //TODO: 确认订单流程
        //根据用户身份去平台查询
        Business.confirm(this);
    }
}
