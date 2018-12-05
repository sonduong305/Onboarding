package droidmentor.onboardingdemo;

public class Category {

    public byte[] picture;
    public String name;
    public int monAn[];

    public Category(byte[] pic, String name, int[] mon) {
        this.picture = pic;
        this.name = name;
        this.monAn = mon;
    }

}
