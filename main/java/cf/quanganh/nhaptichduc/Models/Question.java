package cf.quanganh.nhaptichduc.Models;

public class Question {
    private String id;
    private String quest;
    private String a;
    private String b;
    private String c;
    private String d;
    private String result;

    public Question(){
    }

    public Question(String id, String quest, String a, String b, String c, String d, String result) {
        this.id = id;
        this.quest = quest;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
