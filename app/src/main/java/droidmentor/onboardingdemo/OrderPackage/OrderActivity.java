package droidmentor.onboardingdemo.OrderPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;


import java.util.ArrayList;
import java.util.List;

import droidmentor.onboardingdemo.Category;
import droidmentor.onboardingdemo.CustomDataHolder;
import droidmentor.onboardingdemo.MonAn;
import droidmentor.onboardingdemo.R;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CustomDataHolder dataHolders;

    public static List<MonAn> getMenu() {
        return menu;
    }

    public static void setMenu(List<MonAn> menu) {
        OrderActivity.menu = menu;
    }

    public static List<MonAn> menu = new ArrayList<>();
    private Category category[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ArrayList<OrderItem> orderItemArrayList = new ArrayList<>();

        Intent intent = getIntent();
        int menu_length = intent.getIntExtra("menu_length",0);
        System.out.println(menu_length);

        for(int i=0; i<menu_length; i++){
            int id = intent.getIntExtra("id"+i, 0);
            String name = intent.getStringExtra("name"+i);
            int gia = intent.getIntExtra("gia"+i, 0);
            String mota = intent.getStringExtra("mota"+i);
            menu.add(new MonAn(id, name, gia, mota));

        }



        int category_length = intent.getIntExtra("category_length",0);
        category = new Category[category_length+1];
        System.out.print(category_length);
        for(int i=0; i<category_length; i++){
            int len = intent.getIntExtra("lenPic"+i, 0);
            byte pic[] = new byte[len];
            pic = intent.getByteArrayExtra("picCategory"+i);
            String name = intent.getStringExtra("nameCategory"+i);
            int mon[] = intent.getIntArrayExtra("monanCategory"+i);
            category[i] = new Category(pic, name, mon);
            System.out.println(category[i].name);
        }

        for(int i=0 ;i <category_length; i++){

            orderItemArrayList.add(new OrderItem(category[i].picture,category[i].name,"101topic"));
        }

        mRecyclerView = findViewById(R.id.order_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new OrderAdapter(orderItemArrayList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}