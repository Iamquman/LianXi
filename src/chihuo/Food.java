package chihuo;

import java.io.Serializable;

// Java Bean   | POJO
public class Food  implements Serializable {     //实现可序列化的接口

    private String id;
    private String name;
    private int price;
    private int rid;

    //构造方法
    public Food(String id, String name, int price,int rid) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rid = rid;
    }


    public int getRid(){
        return this.rid;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return id+' '+name+" ￥"+price+".00元";
    }
}
