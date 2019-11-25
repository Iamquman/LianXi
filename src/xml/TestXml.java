package xml;
//20191007
import org.dom4j.io.SAXReader;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/*
* 1.创建SAXReader对象 reader
* 2.创建文件对象并给出文件
* 3.调用reader的read()方法，返回Document(文件)类型 doc（Document是一个接口）
* 4.调用doc的getRootElement()方法，返回代表根节点的元素   root(根)  Element(元素) ，返回值问Element类型  （Element是一个接口）
* 5.显示一下元素
* */
public class TestXml {
    public static void main(String[] args) {
        //创建SAXReader的对象reader
        SAXReader reader=new SAXReader();
        File xmlFile=new File(".\\Files\\demo.xml");
        try{
            Document doc = reader.read(xmlFile);   //read方法返回一个document（文件）对象    ，document是一个接口
            Element ele=doc.getRootElement();   //getRootElement方法返回代表根节点的元素，   root(根)
            show(ele);  //显示元素
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 显示一个元素信息
     * @param e 元素
     */
    static void show(Element e){
        // 获取元素的标签名并显示
        String name=e.getName();
        System.out.println(name+":");
        //获取标签的属性列表并显示
        List<Attribute> al = e.attributes();       //Attribute（属性）
        for(Attribute a:al){
            String n1 = a.getName();   //getName()是Node接口的一个方法（Node节点）   Node接口是Element接口的间接父类
            String n2 = a.getValue();
            System.out.println(n1+":"+n2);
        }
        //获取文本
        String txt=e.getTextTrim();    //getText():获得文本    getTextTrim():获得文本并除去空格
        //如果文本为空则获取子节点
        if(txt.equals("")){
            List<Element> el = e.elements();
            for(Element et:el){
                show(et);
            }
        }else{
            System.out.println(txt);
        }
        }

    }


