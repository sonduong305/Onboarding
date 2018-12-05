package droidmentor.onboardingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import droidmentor.onboardingdemo.MainPackage.RecyclerViewDataAdapter;
import droidmentor.onboardingdemo.MainPackage.SectionDataModel;
import droidmentor.onboardingdemo.MainPackage.SingleItemModel;

public class ListItemActivity extends AppCompatActivity {
    RecyclerView rList;
    ArrayList<SectionDataModel> allSampleData;
    RecyclerViewDataAdapter adapter;
    ArrayList<SingleItemModel> singleItemModels;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        Intent intent = getIntent();
        String menu[] = intent.getStringArrayExtra("menu");

        allSampleData = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_list);

        adapter = new RecyclerViewDataAdapter(allSampleData, this);
        SectionDataModel dm = new SectionDataModel();
        ArrayList<SingleItemModel> singleItemModels = new ArrayList<>();
        //rList = (RecyclerView) findViewById(R.id.recycler_view_list);
        for(int i=0; i<menu.length; i++) System.out.print(menu[i]);


        for(int i=0; i<menu.length; i++) singleItemModels.add(new SingleItemModel(menu[i],"URL 2"));
        dm.setAllItemInSection(singleItemModels);
        allSampleData.add(dm);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(allSampleData, this);
        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        //rList.setAdapter(adapter);
    }
}
