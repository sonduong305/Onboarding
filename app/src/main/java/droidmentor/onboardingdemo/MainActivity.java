package droidmentor.onboardingdemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import droidmentor.onboardingdemo.MainPackage.RecyclerViewDataAdapter;
import droidmentor.onboardingdemo.MainPackage.SectionDataModel;
import droidmentor.onboardingdemo.MainPackage.SingleItemModel;
import droidmentor.onboardingdemo.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SectionDataModel> allSampleData;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Button mBtn_QR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        mBtn_QR = findViewById(R.id.btn_qr);
        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int scrollDist = 0;
                boolean isVisible = true;
                super.onScrolled(recyclerView, dx, dy);
                if (isVisible && scrollDist > 50) {
                    mBtn_QR.setVisibility(recyclerView.INVISIBLE);
                    scrollDist = 0;
                    isVisible = false;
                }
                else if (!isVisible && scrollDist < -50) {
                    mBtn_QR.setVisibility(recyclerView.VISIBLE);
                    scrollDist = 0;
                    isVisible = true;
                }

                if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
                    scrollDist += dy;
                }
            }
        });

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        allSampleData = new ArrayList<>();
        createDummyData();

        mToggle.syncState();





        recyclerView.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(allSampleData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);


        mBtn_QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToQR();
            }
        });
    }

    private void createDummyData() {
        for (int i = 1; i <= 20; i++) {
            SectionDataModel dm = new SectionDataModel();
            dm.setHeaderTitle("Section " + i);
            ArrayList<SingleItemModel> singleItemModels = new ArrayList<>();
            for (int j = 1; j <= 20; j++) {
                singleItemModels.add(new SingleItemModel("Item " + j, "URL " + j));
            }
            dm.setAllItemInSection(singleItemModels);
            allSampleData.add(dm);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void moveToQR(){
        Intent intent  = new Intent(getApplicationContext(),QRScanner.class);
        startActivity(intent);
    }
}
