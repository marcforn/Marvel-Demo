package com.mforn.marvel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mforn.marvel.R;
import com.mforn.marvel.model.CharacterModel;


/**
 * ViewHolder Marvel Character item
 */
public class CharacterListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView itemImage;
    private TextView itemName;
    private RecyclerClickInterface<CharacterModel> mListener;

    public CharacterListViewHolder(View itemView, RecyclerClickInterface<CharacterModel> mListener) {
        super(itemView);

        this.itemImage = (ImageView) itemView.findViewById(R.id.item_image);
        this.itemName = (TextView) itemView.findViewById(R.id.item_name);
        this.mListener = mListener;

        itemView.setOnClickListener(this);
    }

    public void setData(CharacterModel item) {
        itemName.setText(item.getName());
        Glide.with(itemView.getContext()).load(item.getPortraitImageUrl()).into(itemImage);

        itemView.setTag(item);
    }

    @Override
    public void onClick(View view) {
        mListener.onItemClick((CharacterModel) view.getTag());
    }
}

