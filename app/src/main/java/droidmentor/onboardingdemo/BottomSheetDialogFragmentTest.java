package droidmentor.onboardingdemo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetDialogFragmentTest extends BottomSheetDialogFragment {


    private RecyclerView mRecyclerView;
    private ListViewAdaptor mAdapter;
    private List<MonAn> mDataList = new ArrayList<>();

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.cart_view, null);
        dialog.setContentView(contentView);
        for(MonAn a : CustomDataHolder.getInstance().menu){
            if(a.getSoluong()>0){
                mDataList.add(a);
            }
        }
        mRecyclerView = contentView.findViewById(R.id.recycler_view);
        mAdapter = new ListViewAdaptor(mDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(contentView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        //prepareList();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);

    }

    public void prepareList(){
        MonAn data = new MonAn("Mon an 1",15000);
        mDataList.add(data);
        data = new MonAn("Mon an 2",20000);
        mDataList.add(data);
        data = new MonAn("Item3",35000);
        mDataList.add(data);
        data = new MonAn("Item4",32000);
        mDataList.add(data);
        data = new MonAn("Item5",450000);
        mDataList.add(data);
        for(int i=10;i<20;i++){
            data = new MonAn("Mon an"+i,i*1000);
            mDataList.add(data);
        }
    }



}
