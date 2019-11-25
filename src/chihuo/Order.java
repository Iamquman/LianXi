package chihuo;
//订单类
import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable {    //实现一下Serializacble接口
    final static String[] stNames={
            "","顾客已提交","商家已接单","商家已配送","顾客已确认","因超时已取消"};
    final static int ST_CREATE = 0;
    final static int ST_SUBMIT = 1;
    final static int ST_ACCEPT = 2;
    final static int ST_SENT = 3;
    final static int ST_FINISHED = 4;
    final static int ST_CANCELED = 5;

    String number;   //订单编号
    Customer customer;
    Restaurant restaurant;
    Map<Food,Integer> bill;   //订单菜品列表：菜品，个数
    Instant orderTime;
    int status;   //订单状态

    //带餐构造：顾客点餐时创建订单时使用
    public Order(Customer c, Restaurant r){
        this.customer = c;
        this.restaurant = r;
        this.bill = new HashMap<>();
        this.status = ST_CREATE;
    }

    //往订单内添加所点菜品
    void add(Food f){
        if(bill.containsKey(f)){
            bill.put(f,bill.get(f)+1);
        }else{
            bill.put(f,1);
        }
    }

    //计算此条订单的总金额
    int totalPrice(){
        int s = 0;
        for(Food f:this.bill.keySet()){
            int n = this.bill.get(f);
            s += f.getPrice() * n;
        }
        return s;
    }

    //toString方法重写:显示订单信息
    public String toString() {
        //把订单信息拼装成一个字符串
        StringBuffer sb = new StringBuffer();
        sb.append(this.number);//订单号
        sb.append("\n");
        sb.append(this.restaurant.name);//商家名称
        sb.append("\n");
        sb.append(this.restaurant.address);//商家地址
        sb.append("\n");
        sb.append(this.restaurant.telno);//商家电话
        sb.append("订单状态:");
        sb.append(stNames[this.status]);
        sb.append("\n");
        sb.append("配送地址:");
        sb.append(this.customer.address);
        sb.append("\n");
        sb.append("=======================\n");
        for(Food k:bill.keySet()){
            int n = bill.get(k);
            sb.append("\t");
            sb.append(n);
            sb.append("×");
            sb.append(k.getName());
            sb.append(" ￥");
            sb.append(k.getPrice());
            sb.append(".00元\n");
        }
        sb.append("=======================\n");
        sb.append("\t\t\t总价格:￥");
        sb.append(this.totalPrice());
        sb.append(".00元");
        return sb.toString();
    }
}