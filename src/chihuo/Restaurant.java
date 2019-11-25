package chihuo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//商家类
public class Restaurant implements Serializable {
        //名称，电话，地址
        String name;
        String telno;
        String address;
    /*//菜品列表 (Food类型的列表foodsList：可以按顺序访问)
    List<Food> foodsList;*/
        //菜品列表（）
        Map<String,Food> foods=new HashMap<>();
        int rid;    //商家的编号：0或1；在foods文件中也有，表示是哪个商家的菜

        //构造器
        public Restaurant(int rid,String name,String telno,String address){
        this.name=name;
        this.telno=telno;
        this.address=address;
        this.rid=rid;
        }

        //获取菜品列表
        void addFood(Food f){
        this.foods.put(f.getId(),f);
    }

        //接受订单方法
        void accept() {
            Order o = Business.requestOrder(this);
            if (null == o) {
                System.out.println("暂时无单，休息一下吧");
            } else {
                //商家接单
                //o.status = Order.ST_ACCEPT;
                //制作
                //配送
                o.status = Order.ST_SENT;
                System.out.println("商家已接单并完成配送");
            }
        }

        //显示餐馆列表
        public String toString(){
        return this.name+","+this.telno+","+this.address;
        }

    }
