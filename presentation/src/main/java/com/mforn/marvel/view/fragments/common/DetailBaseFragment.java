package com.mforn.marvel.view.fragments.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;

import com.mforn.marvel.injector.HasComponent;
import com.mforn.marvel.view.activity.interfaces.DetailActivityInterface;

/**
 * Detail Base fragment to handle activity communication and dependency injection.
 */
public class DetailBaseFragment extends Fragment {
    protected DetailActivityInterface mListener;

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    private void onAttachToContext(Context context) {
        try {
            mListener = (DetailActivityInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("You must implement OnBoardingInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
