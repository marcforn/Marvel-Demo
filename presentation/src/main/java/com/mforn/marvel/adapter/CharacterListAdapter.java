package com.mforn.marvel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mforn.marvel.R;
import com.mforn.marvel.model.CharacterModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to populate Marvel Character list
 */
public class CharacterListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int MARVEL_ITEM = 0;
    private List<CharacterModel> characterList;
    private RecyclerClickInterface<CharacterModel> mListener;


    public CharacterListAdapter(RecyclerClickInterface<CharacterModel> mListener) {
        this.characterList = new ArrayList<>();
        this.mListener = mListener;
    }

    public void refreshAdapterData(List<CharacterModel> characterList) {
        this.characterList = characterList;
        this.notifyDataSetChanged();
    }

    public void addAdapterData(List<CharacterModel> characterList) {
        this.characterList.addAll(characterList);
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CharacterListViewHolder viewHolder = null;

        switch (viewType) {
            case MARVEL_ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marvel_item, parent, false);
                viewHolder = new CharacterListViewHolder(view, mListener);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CharacterModel item = characterList.get(position);

        CharacterListViewHolder holder = (CharacterListViewHolder) viewHolder;
        holder.setData(item);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return MARVEL_ITEM;
    }
}
