package com.mforn.marvel.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mforn.marvel.R;
import com.mforn.marvel.injector.component.CharacterComponent;
import com.mforn.marvel.model.CharacterDetailModel;
import com.mforn.marvel.presenter.CharacterFavDetailPresenter;
import com.mforn.marvel.view.CharacterDetailDataView;
import com.mforn.marvel.view.dialog.ProgressDialog;
import com.mforn.marvel.view.fragments.common.DetailBaseFragment;

import javax.inject.Inject;

/**
 * Fragment that shows details of a certain favorite Marvel character.
 */
public class CharacterFavDetailFragment extends DetailBaseFragment implements View.OnClickListener, CharacterDetailDataView<CharacterDetailModel> {
    private ImageView characterImage;
    private TextView characterDescription;
    private ImageView favoriteButton;

    private ProgressDialog progressDialog;


    @Inject
    CharacterFavDetailPresenter mPresenter;

    public CharacterFavDetailFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CharacterComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_character_fav_detail, container, false);

        getViews(fragmentView);
        setViewsAction();

        return fragmentView;
    }

    private void getViews(View fragmentView) {
        characterImage = (ImageView) fragmentView.findViewById(R.id.character_image);
        characterDescription = (TextView) fragmentView.findViewById(R.id.character_desc_content);
        favoriteButton = (ImageView) fragmentView.findViewById(R.id.favorite_button);
    }

    private void setViewsAction() {
        favoriteButton.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mPresenter.setView(this);

        if (savedInstanceState == null) {
            this.loadCharacterDetail();
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

    private void loadCharacterDetail() {
        if (this.mPresenter != null) {
            this.mPresenter.getUserDetails();
        }
    }

    @Override
    public void showLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        mListener.setToolbarTitle(getActivity().getString(R.string.loading_string));
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
        progressDialog = null;
    }

    @Override
    public void showError(String message) {
        mListener.showSnackBarMessage(true, message);
    }

    @Override
    public void showData(CharacterDetailModel characterDetailModel) {
        mListener.setToolbarTitle(characterDetailModel.getName());

        Glide.with(getActivity()).load(characterDetailModel.getLandscapeImageUrl()).into(characterImage);

        String description = characterDetailModel.getDescription().isEmpty() ? getActivity().getString(R.string.description_not_available) : characterDetailModel.getDescription();
        characterDescription.setText(description);

        if (characterDetailModel.isFavorite()){
            favoriteButton.setImageResource(android.R.drawable.star_big_on);
        } else {
            favoriteButton.setImageResource(android.R.drawable.star_big_off);
        }
    }

    @Override
    public void setFavorite(boolean isFavorite) {
        if (isFavorite) {
            favoriteButton.setImageResource(android.R.drawable.star_big_on);
            mListener.showSnackBarMessage(false, getActivity().getString(R.string.added_to_favorite));
        } else {
            favoriteButton.setImageResource(android.R.drawable.star_big_off);
            mListener.showSnackBarMessage(false, getActivity().getString(R.string.removed_from_favorite));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.favorite_button:
                mPresenter.toggleFavorite();
                break;
        }
    }
}
