package droidmentor.onboardingdemo;

public class MonAn {
    public int id;
    public String name;
    public int gia;
    public String mota;

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    int soluong=0;

    public MonAn(int id, String name, int gia, String mota) {
        this.id = id;
        this.name = name;
        this.gia = gia;
        this.mota = mota;
    }
    public MonAn(String name, int gia) {

        this.name = name;
        this.gia = gia;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }



}
