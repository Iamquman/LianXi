package huiyuan;

import java.util.Date;

//会员类：属性：姓名，会员卡号，密码，积分，开卡日期
public class Member {
    private String name;
    private int cardId;
    private String password;
    private int score;
    private String registDate;

    public Member(){}
    public Member(String name,int cardId,String passwoid,int score,String registDate){
        this.name=name;
        this.cardId=cardId;
        this.password=passwoid;
        this.score=score;
        this.registDate=registDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    public String getName() {
        return name;
    }

    public int getCardId() {
        return cardId;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public String getRegistDate() {
        return registDate;
    }
}
