package droidmentor.onboardingdemo.OrderPackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import droidmentor.onboardingdemo.Category;
import droidmentor.onboardingdemo.R;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private ArrayList<OrderItem> orderItemArrayListAdapter;
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(v);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItem currentItem = orderItemArrayListAdapter.get(position);

        Bitmap bmp = BitmapFactory.decodeByteArray(currentItem.getImageOrderItem(), 0, currentItem.getImageOrderItem().length);
        if(bmp == null)
        {

        }else {


            holder.OrderImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, bmp.getWidth(),
                    bmp.getHeight(), false));
            holder.OrderTitle.setText(currentItem.getTitleOrder());
            holder.OrderDescription.setText(currentItem.getDescriptionOrder());
        }
    }

    @Override
    public int getItemCount() {
        return orderItemArrayListAdapter.size();
    }
    public OrderAdapter(ArrayList<OrderItem> orderItemsList){
        orderItemArrayListAdapter = orderItemsList;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        public ImageView OrderImage;
        public TextView OrderTitle;
        public TextView OrderDescription;
        public OrderViewHolder(View itemView) {
            super(itemView);
            OrderImage = itemView.findViewById(R.id.img_photo_food);
            OrderTitle = itemView.findViewById(R.id.tv_title);
            OrderDescription = itemView.findViewById(R.id.tv_Desc);

        }
    }
}