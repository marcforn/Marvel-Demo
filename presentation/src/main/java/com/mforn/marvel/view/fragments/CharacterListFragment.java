package com.mforn.marvel.view.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mforn.marvel.R;
import com.mforn.marvel.adapter.CharacterListAdapter;
import com.mforn.marvel.adapter.RecyclerClickInterface;
import com.mforn.marvel.injector.component.CharacterComponent;
import com.mforn.marvel.model.CharacterModel;
import com.mforn.marvel.presenter.CharacterListPresenter;
import com.mforn.marvel.view.LoadDataView;
import com.mforn.marvel.view.activity.DetailActivity;
import com.mforn.marvel.view.fragments.common.ListBaseFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Fragment that shows list of Marvel characters.
 */
public class CharacterListFragment extends ListBaseFragment implements LoadDataView<List<CharacterModel>>, RecyclerClickInterface<CharacterModel> {
    private ProgressBar mProgressBar;

    @Inject
    CharacterListPresenter mPresenter;

    private CharacterListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    // Handle scroll down
    public int totalItemCount;
    private int visibleItemCount;
    public int pastVisibleItems;
    public boolean isDataLoading;


    public CharacterListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CharacterComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_character_list, container, false);

        setViews(fragmentView);
        setViewsAction();
        setAdapter();

        return fragmentView;
    }

    private void setViews(View fragmentView) {
        mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.character_list_recycler);
        mProgressBar = (ProgressBar) fragmentView.findViewById(R.id.progressBar_character_list);
    }

    private void setViewsAction() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dX, int dY) {
                if (dY > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (!isDataLoading) {
                        // When scroll finish retrieve next page
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            isDataLoading = true;
                            mPresenter.loadMoreCharacters();
                        }
                    }
                }
            }
        });
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

        if (savedInstanceState == null) {
            this.loadCharacterList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mPresenter.resume();
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
            this.mPresenter.initialize();
        }
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        isDataLoading = false;

        mListener.showSnackBarMessage(message);
    }

    @Override
    public void showData(List<CharacterModel> characterModelList) {
        isDataLoading = false;
        mAdapter.addAdapterData(characterModelList);
    }

    @Override
    public void onItemClick(CharacterModel characterModel) {
        mListener.navigateToActivityDetail(DetailActivity.class, DetailActivity.FLOW_MARVEL_CHARACTER, characterModel.getId());
    }
}
