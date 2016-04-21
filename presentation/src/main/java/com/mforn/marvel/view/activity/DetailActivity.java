package com.mforn.marvel.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.mforn.marvel.R;
import com.mforn.marvel.injector.HasComponent;
import com.mforn.marvel.injector.component.CharacterComponent;
import com.mforn.marvel.injector.component.DaggerCharacterComponent;
import com.mforn.marvel.injector.module.CharacterModule;
import com.mforn.marvel.view.activity.common.BaseActivity;
import com.mforn.marvel.view.activity.interfaces.DetailActivityInterface;
import com.mforn.marvel.view.fragments.CharacterDetailFragment;
import com.mforn.marvel.view.fragments.CharacterFavDetailFragment;

/**
 * Activity to show Marvel Character related information.
 */
public class DetailActivity extends BaseActivity implements DetailActivityInterface, HasComponent<CharacterComponent> {
    private static final String EXTRA_FLOW = "EXTRA_FLOW";
    private static final String EXTRA_CHARACTER_ID = "EXTRA_CHARACTER_ID";

    public static final int FLOW_MARVEL_CHARACTER = 0;
    public static final int FLOW_FAVORITE_CHARACTER = 1;

    private CharacterComponent characterComponent;

    private Toolbar mToolbar;
    private FrameLayout fragmentContainer;

    private int flowCode;
    private long characterId;


    public static Intent makeIntent(Context context, int flowCode, long characterId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_FLOW, flowCode);
        intent.putExtra(EXTRA_CHARACTER_ID, characterId);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtras();
        initializeInjector();
        getViews();
        setViewsAction();
        setFragment();
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            flowCode = extras.getInt(EXTRA_FLOW);
            characterId = extras.getLong(EXTRA_CHARACTER_ID);
        }
    }

    private void getViews() {
        setContentView(R.layout.activity_detail);

        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        this.fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
    }

    private void setViewsAction() {
        super.setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            super.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            super.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setFragment() {
        switch (flowCode) {
            case FLOW_MARVEL_CHARACTER:
                replaceFragment(fragmentContainer, new CharacterDetailFragment());
                break;
            case FLOW_FAVORITE_CHARACTER:
                replaceFragment(fragmentContainer, new CharacterFavDetailFragment());
                break;
        }
    }

    /**
     * Action Bar Menu Options
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    /**
     * Activity interface methods
     */
    @Override
    public void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void showSnackBarMessage(boolean isError, String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

        if (isError) {
            snackbar.setCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    super.onDismissed(snackbar, event);
                    DetailActivity.this.finish();
                }
            });
        }

        snackbar.show();
    }

    @Override
    public CharacterComponent getComponent() {
        return this.characterComponent;
    }

    @Override
    public void initializeInjector() {
        this.characterComponent = DaggerCharacterComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .characterModule(new CharacterModule(this.characterId))
                .build();
    }
}
