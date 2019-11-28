package MyMarket;

import java.time.Instant;
import java.util.InputMismatchException;

//会员类
public class Member {
    //姓名，手机号，密码，卡号，积分，开卡日期
    private String name;
    private String phone;
    private String mima;
    private int kahao;
    private int jifen;
    private String riqi;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMima() {
        return mima;
    }

    public int getKahao() {
        return kahao;
    }

    public int getJifen() {
        return jifen;
    }

    public String getRiqi() {
        return riqi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean setPhone(String phone) {
        if(phone.length()==11 && testPhone(phone)){
            return true;
        }else{
            return false;
        }
    }

    public boolean testPhone(String phone) {
        boolean flag=true;
        for (int i = 0; i < phone.length(); i++) {
            char[] p = new char[phone.length()];
            p[i] = phone.charAt(i);
            if (!Character.isDigit(p[i])) {  //判断字符是否为纯数字的方法
                flag=false;
                break;
            } else {
                flag= true;
                this.phone=phone;
            }
        }
        return flag;
    }

    public boolean setMima(String mima) {
        if(mima.length()>=6){
            return true;
        }else{
            return false;
        }
    }

    public void setKahao(int kahao) {
        this.kahao = kahao;
    }

    public void setJifen(int jifen) {
        this.jifen = jifen;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }
}
