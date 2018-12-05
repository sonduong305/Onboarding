package droidmentor.onboardingdemo;

import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.data.DataHolder;

import java.util.ArrayList;
import java.util.List;


public class MenuMonAnActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ListViewAdaptor mAdapter;
    private List<MonAn> mDataList = new ArrayList<>();
    private Button btnCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mon_an);
        btnCart = findViewById(R.id.btn_cart);




        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogFragmentTest();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ListViewAdaptor(CustomDataHolder.getInstance().menu);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        prepareList();
    }

    public void prepareList(){
        MonAn data = new MonAn("Item1",15000);
        CustomDataHolder.getInstance().menu.add(data);
        data = new MonAn("Item2",20000);
        CustomDataHolder.getInstance().menu.add(data);
        data = new MonAn("Item3",35000);
        CustomDataHolder.getInstance().menu.add(data);
        data = new MonAn("Item4",32000);
        CustomDataHolder.getInstance().menu.add(data);
        data = new MonAn("Item5",450000);
        CustomDataHolder.getInstance().menu.add(data);
        for(int i=10;i<20;i++){
            data = new MonAn("Item"+i,i*1000);
            CustomDataHolder.getInstance().menu.add(data);
        }
    }

}
