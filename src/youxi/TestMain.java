package youxi;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.*;
import java.io.*;

public class TestMain {
    private static Random rand = new Random();
    private static Scanner sc = new Scanner(System.in);
    static List<String> cityA = new ArrayList<>();
    static List<String> cityB = new ArrayList<>();
    static List<String> cityAll = new ArrayList<>();

    /**
     * 判断一个城市是否为A类城市,A类城市的拼音大写首字母笔画是直的
     * @param cityName 城市名
     * @return true 表示是
     */
    static boolean inCityA(String cityName){
        String firstLetter = getFirstLetter(cityName);
        int pos = "BCDGJOPQRSU".indexOf(firstLetter);
        return -1 == pos;
    }

    static void loadCity(){
        //加载数据文件
        File f = new File("./files/city.txt");
        try{
            //逐行读取
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            while(null != line){
                //拆分每行所有的城市
                String[] cities = line.split(",");
                //逐个城市判断类别,添加到对应的城市群
                for(String cityName:cities){
                    if(inCityA(cityName)){
                        //A类城市
                        cityA.add(cityName);
                    }else{
                        //B类城市
                        cityB.add(cityName);
                    }
                    cityAll.add(cityName);
                }
                line = br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 从给定列表中,任选一个
   /* static String randomCityIn(List<String> cities){
        //在整个列表中随机获得一个城市返回
        int n = cities.size();
        int t = rand.nextInt(n);
        return cities.get(t);
    }*/

    /**
     * 从给定城市列表中,任选若干个不重复的城市
     * 如果制定的个数超过列表中城市数,则全部返回
     * @param cities 给定的城市列表
     * @param n 选取个数
     * @return 城市列表
     */
    static List<String> getCitiesFrom(List<String> cities,int n){
        //随机生成一系列整数,范围在给定的城市数量之内
        int len = cities.size();
        if(len < n){
            return cities;
        }
        // 哈希集合,元素不重复
        Set<Integer> indexes = new HashSet<>();
        do{
            int t = rand.nextInt(len);
            indexes.add(t);
        }while (indexes.size() < n);

        List<String> result = new ArrayList<>();
        for(Integer i:indexes){
            result.add(cities.get(i));
        }
        return result;
    }

    /**
     * 按序号显示列表内容
     * @param c 列表
     */
       static void show(List<String> c){
        int len = c.size();
        for(int i=0;i<len;i++){
            if(i%4==0) System.out.println();
            System.out.printf("%2d:%s\t",i,c.get(i));
        }
        System.out.println();
    }

    /**
     * 获取给定城市名的大写首字母
     * @param cityName 城市名
     * @return 大写首字母
     */
    static String getFirstLetter(String cityName){
        char a = cityName.charAt(0);
        String[] p = PinyinHelper.toHanyuPinyinStringArray(a);
        return p[0].toUpperCase().substring(0,1);
    }

    public static void main(String[] args) {
        //加载城市数据并完成分类
        loadCity();
        // 随机选取所有城市中的20个
        List<String> c = getCitiesFrom(cityAll,20);
        show(c);
        //让用户选择一个序号
        System.out.print("请选择:");
        int ch = sc.nextInt();
        String ci = c.get(ch);
        System.out.printf("你选择了:%s\n",ci);

        //选择另一个类的11个陪伴城市
        List<String> otherCities;
        if(inCityA(ci)){
            //B
            otherCities = getCitiesFrom(cityB,11);
        }else{
            //A
            otherCities = getCitiesFrom(cityA,11);
        }
        //为了避免出现在最末尾
        //otherCities.add(ci);
        //随机位置位置添加
        int r = rand.nextInt(11);
        otherCities.add(r,ci);
        show(otherCities);
    }
}
