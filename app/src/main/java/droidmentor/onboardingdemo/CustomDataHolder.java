package droidmentor.onboardingdemo;


import java.util.ArrayList;
import java.util.List;

public class CustomDataHolder {
    final List<MonAn> menu = new ArrayList<MonAn>();


    public void setOrder(int index,int amount){

        menu.get(index).setSoluong(amount);
    }

    private CustomDataHolder() {}

    public static CustomDataHolder getInstance() {
        if( instance == null ) {
            instance = new CustomDataHolder();
        }
        return instance;
    }



    private static CustomDataHolder instance;
}