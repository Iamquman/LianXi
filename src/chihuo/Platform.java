package chihuo;
//平台类
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Platform {
    Map<String,Customer> customers;         //顾客集合
    List<Restaurant> restaurants;           //商家
    List<Order> orderList;                  //订单

    //构造方法
    public Platform(){
        //平台初始化
        //创建成员实例
        this.customers = new HashMap<>();
        this.restaurants = new ArrayList<>();
        this.orderList = new ArrayList<>();
    }

    //读取文件中每行内容
    private static List<String> readCSV(String fileName){     //读取文件中的每行内容
        List<String> lines = new ArrayList<>();
        //CSV = Comma Split Values   逗号分隔值
        File f = new File(fileName);
        if(f.exists()) {       //文件f存在的方法，返回boolean值
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                //循环读整行,每行是一条记录(一个对象)
                String line = br.readLine();
                while (null != line) {
                    lines.add(line);
                    line = br.readLine();
                }
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lines;
        }else{
            return null;
        }
    }

    //调用readCSV（读取逗号分隔值），加载商家数据，菜品数据，菜品和商家匹配，加载顾客信息，加载订单信息
    void loadData() {
        //从数据文件加载 菜品,商家,顾客
        //foods.csv
        List<Food> foodList = new ArrayList<>();
        System.out.println("开始加载数据:Food");
        List<String> foods = readCSV(".\\files\\food.csv");
        for(String f:foods){
            String[] cols = f.split(",");
            Food fd = new Food(cols[0],cols[1],Integer.valueOf(cols[2]),Integer.valueOf(cols[3]));
            foodList.add(fd);
        }

        //restaurants.csv
        System.out.println("开始加载数据:Restaurant");
        List<String> strRests = readCSV(".\\files\\restaurants.csv");
        for(String r:strRests){
            String[] cols = r.split(",");
            this.restaurants.add(new Restaurant(Integer.valueOf(cols[0]),cols[1],cols[2],cols[3]));
        }

        // 菜品和商家匹配
        for(Food f:foodList){
            Restaurant r = getRestaurantByRID(f.getRid());
            if(null != r){
                r.addFood(f);
            }
        }

        //加载顾客信息
        System.out.println("开始加载数据：会员信息");
        List<String> strCus=readCSV(".\\files\\customers.csv");
        for(String s:strCus){
            String[] c=s.split(",");
            Customer cs=new Customer(c[1],c[0],c[2]);
            this.customers.put(cs.telno,cs);
        }

        //加载订单信息
        File f=new File(".\\files\\orders.obj");
        if(f.exists()){
            //如果数据文件存在  则从文件读取订单列表对象
            try{
                FileInputStream fis=new FileInputStream(f);
                BufferedInputStream bis=new BufferedInputStream(fis);
                ObjectInputStream ois=new ObjectInputStream(bis);
                // Object o=ois.readObject();
                this.orderList = (List<Order>) ois.readObject();
                ois.close();
                bis.close();
                fis.close();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("订单数据加载未成功");
                this.orderList=new ArrayList<>();
            }
        }
        System.out.println("加载完毕");
    }

    //提交订单：修改订单状态为已提交，分配订单编号，设置订单时间为当前时间，添加至订单列表
    void submit(Order o){
        // 修改订单状态
        o.status = Order.ST_SUBMIT;
        // 分配订单编号
        o.number = "" + this.orderList.size();
        // 设置订单时间
        o.orderTime = Instant.now();
        // 添加到订单列表
        this.orderList.add(o);
    }

    //增强型的for，找出电话为num的商家并返回，否则返回为空
    Restaurant getRestaurantByTelno(String num){
        for(Restaurant r:this.restaurants){
            if(r.telno.equals(num)){
                return r;
            }
        }
        return null;
    }

    //增强型的for，找出编号为rid的商家并返回，否则返回为空
    Restaurant getRestaurantByRID(int rid){           //增强型for循环，找出编号为rid的商家并返回，否则返回为空
        for(Restaurant r:this.restaurants){
            if(r.rid == rid){
                return r;
            }
        }
        return null;
    }
}
