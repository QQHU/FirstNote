package com.gan.lib.frame.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import com.gan.lib.frame.viewmodel.IViewModelProvider;
import com.gan.lib.frame.viewmodel.ViewModelProvider;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * All your activities must extend this activity - even in case your activity has no viewmodel. The fragment viewmodels are using the {@link IViewModelProvider}
 * interface to get the {@link ViewModelProvider} from the current activity.
 * You can copy this implementation in case you don't want to extend this class.
 */
public abstract class ViewModelBaseEmptyActivity extends SupportActivity implements IViewModelProvider {

    @Nullable
    private ViewModelProvider mViewModelProvider;


    @CallSuper
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        //This code must be execute prior to super.onCreate()
        mViewModelProvider = ViewModelProvider.newInstance(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    @Nullable
    public Object onRetainCustomNonConfigurationInstance() {
        return mViewModelProvider;
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        if (isFinishing()) {
            if (null == mViewModelProvider) {
                throw new IllegalStateException("ViewModelProvider for activity " + this + " was null."); //NON-NLS
            }
            mViewModelProvider.removeAllViewModels();
        }
    }

    @Nullable
    @Override
    public ViewModelProvider getViewModelProvider() {
        return mViewModelProvider;
    }



}
