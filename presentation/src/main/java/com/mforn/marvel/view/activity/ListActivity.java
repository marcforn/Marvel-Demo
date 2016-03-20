package com.mforn.marvel.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.mforn.marvel.R;
import com.mforn.marvel.injector.HasComponent;
import com.mforn.marvel.injector.component.CharacterComponent;
import com.mforn.marvel.injector.component.DaggerCharacterComponent;
import com.mforn.marvel.injector.module.CharacterModule;
import com.mforn.marvel.view.activity.common.BaseActivity;
import com.mforn.marvel.view.activity.interfaces.ListActivityInterface;
import com.mforn.marvel.view.fragments.CharacterFavListFragment;
import com.mforn.marvel.view.fragments.CharacterListFragment;

/**
 * Activity to show Marvel Character collection items.
 */
public class ListActivity extends BaseActivity implements ListActivityInterface, HasComponent<CharacterComponent> {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private FrameLayout fragmentContainer;

    private CharacterComponent characterComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeInjector();
        getViews();
        setViewsAction();
    }

    private void getViews() {
        setContentView(R.layout.activity_home);

        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mNavigationView = (NavigationView) findViewById(R.id.nvView);
        this.fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
    }

    private void setViewsAction() {
        super.setSupportActionBar(mToolbar);
        this.setupDrawerContent(mNavigationView);
        this.mActionBarDrawerToggle = setupDrawerToggle();
        this.mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        this.mDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                replaceFragment(fragmentContainer, new CharacterListFragment());
                break;
            case R.id.nav_second_fragment:
                replaceFragment(fragmentContainer, new CharacterFavListFragment());
                break;
            default:
                replaceFragment(fragmentContainer, new CharacterListFragment());
        }

        setTitle(menuItem.getTitle());
        this.mDrawerLayout.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                this.mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        this.mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Activity interface methods
     */
    @Override
    public void showSnackBarMessage(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void navigateToActivityDetail(Class activity, int flowCode, long characterId) {
        Intent intent = DetailActivity.makeIntent(this, flowCode, characterId);
        super.startActivity(intent);
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
                .characterModule(new CharacterModule())
                .build();
    }
}
