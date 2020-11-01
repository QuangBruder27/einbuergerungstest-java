package cf.quanganh.nhaptichduc.Models;

public class ImageResource {
    public int getQuest_nr() {
        return quest_nr;
    }

    public void setQuest_nr(int quest_nr) {
        this.quest_nr = quest_nr;
    }

    public int getResourceA() {
        return resourceA;
    }

    public void setResourceA(int resourceA) {
        this.resourceA = resourceA;
    }

    public int getResourceB() {
        return resourceB;
    }

    public void setResourceB(int resourceB) {
        this.resourceB = resourceB;
    }

    public int getResourceC() {
        return resourceC;
    }

    public void setResourceC(int resourceC) {
        this.resourceC = resourceC;
    }

    public int getResourceD() {
        return resourceD;
    }

    public void setResourceD(int resourceD) {
        this.resourceD = resourceD;
    }

    int quest_nr;
    int resourceA,resourceB,resourceC,resourceD;

    public ImageResource(int quest_nr, int resourceA, int resourceB, int resourceC, int resourceD) {
        this.quest_nr = quest_nr;
        this.resourceA = resourceA;
        this.resourceB = resourceB;
        this.resourceC = resourceC;
        this.resourceD = resourceD;
    }



}
