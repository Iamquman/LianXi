package MyMarket;
//会员类
public class Member {
    //姓名，手机号，密码，卡号，积分，开卡日期
    private String name;
    private String phone;
    private int mima;
    private int kahao;
    private int jifen;
    private String riqi;

    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

    public int getMima() {
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
        
        if(phone.length()==11){
            this.phone=phone;
            return true;

        }else{

        }
    }

    public boolean setMima(int mima) {
        if(mima>=6) {
            this.mima = mima;
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
