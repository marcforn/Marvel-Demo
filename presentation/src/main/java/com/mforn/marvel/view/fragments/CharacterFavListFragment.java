package com.mforn.marvel.view.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mforn.marvel.R;
import com.mforn.marvel.adapter.CharacterListAdapter;
import com.mforn.marvel.adapter.RecyclerClickInterface;
import com.mforn.marvel.injector.component.CharacterComponent;
import com.mforn.marvel.model.CharacterModel;
import com.mforn.marvel.presenter.CharacterFavListPresenter;
import com.mforn.marvel.view.LoadDataView;
import com.mforn.marvel.view.activity.DetailActivity;
import com.mforn.marvel.view.dialog.ProgressDialog;
import com.mforn.marvel.view.fragments.common.ListBaseFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Fragment that shows list of favorite Marvel characters.
 */
public class CharacterFavListFragment extends ListBaseFragment implements LoadDataView<List<CharacterModel>>, RecyclerClickInterface<CharacterModel> {
    private ProgressDialog progressDialog;

    @Inject
    CharacterFavListPresenter mPresenter;

    private CharacterListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;


    public CharacterFavListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CharacterComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_character_fav_list, container, false);

        setViews(fragmentView);
        setAdapter();

        return fragmentView;
    }

    private void setViews(View fragmentView) {
        mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.character_list_recycler);
    }

    private void setAdapter() {
        mAdapter = new CharacterListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mPresenter.setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mPresenter.resume();
        this.loadCharacterList();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.destroy();
    }

    private void loadCharacterList() {
        if (this.mPresenter != null) {
            this.mPresenter.getUserDetails();
        }
    }

    @Override
    public void showLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
        progressDialog = null;
    }

    @Override
    public void showError(String message) {
        mListener.showSnackBarMessage(message);
    }

    @Override
    public void showData(List<CharacterModel> characterModelList) {
        mAdapter.refreshAdapterData(characterModelList);
    }

    @Override
    public void onItemClick(CharacterModel characterModel) {
        mListener.navigateToActivityDetail(DetailActivity.class, DetailActivity.FLOW_FAVORITE_CHARACTER, characterModel.getId());
    }
}
