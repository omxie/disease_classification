package com.example.diseaseclassification;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

    public static class CardItemsViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgView;
        public TextView txtTitle;
        public TextView txtDesc;

        public CardItemsViewholder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgCard);
            txtTitle = itemView.findViewById(R.id.textCardTitle);
            txtDesc = itemView.findViewById(R.id.textCardDesc);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if(getLayoutPosition()==0){
                Intent intent = new Intent(v.getContext(), MelanomaClassification.class);
                v.getContext().startActivity(intent);

            }else if(getLayoutPosition()==1){
                Intent intent = new Intent(v.getContext(), EyeDefectsClassification.class);
                v.getContext().startActivity(intent);

            }
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
