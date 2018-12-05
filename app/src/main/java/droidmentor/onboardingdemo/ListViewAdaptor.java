package droidmentor.onboardingdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

public class ListViewAdaptor extends RecyclerView.Adapter<ListViewAdaptor.MyViewHolder> {
    private List<MonAn> mDataList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name,price;
        public ImageView imageView;
        public ElegantNumberButton elegantNumberButton;

        public MyViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.foodName);
            price= (TextView) view.findViewById(R.id.price);
            imageView = (ImageView) view.findViewById(R.id.image);
            elegantNumberButton = view.findViewById(R.id.ebtn_soluong);
        }
    }

    public ListViewAdaptor(List<MonAn> dataList){
        this.mDataList = dataList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_food, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        MonAn data = mDataList.get(position);
        //String gia = data.getGia()+"";
        holder.name.setText(data.getName());
        holder.price.setText(data.getGia()+"");
        holder.elegantNumberButton.setNumber(data.getSoluong()+"");
        holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                mDataList.get(position).setSoluong(newValue);

            }
        });

        //holder.imageView.setImageResource(data.getImageId());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}