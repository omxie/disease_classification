package com.example.diseaseclassification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardItemsViewholder> {

    private ArrayList<CardItems> mCardsList;

    public static class CardItemsViewholder extends RecyclerView.ViewHolder{
        public ImageView imgView;
        public TextView txtTitle;
        public TextView txtDesc;
        public CardItemsViewholder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgCard);
            txtTitle = itemView.findViewById(R.id.textCardTitle);
            txtDesc = itemView.findViewById(R.id.textCardDesc);
        }
    }

    public CardsAdapter(ArrayList<CardItems> cardlist){
        mCardsList = cardlist;
    }

    @NonNull
    @Override
    public CardItemsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.homescreencardview, parent, false);
        CardItemsViewholder cvh = new CardItemsViewholder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardItemsViewholder holder, int position) {
        CardItems currentItem = mCardsList.get(position);
        holder.imgView.setImageResource(currentItem.getImgRes());
        holder.txtTitle.setText(currentItem.getTextTitle());
        holder.txtDesc.setText(currentItem.getTextDesc());
    }

    @Override
    public int getItemCount() {
        return mCardsList.size();
    }



}
