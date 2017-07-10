package com.muaythai.app.ui.splash;

import com.muaythai.app.app.CheckAuthorizationUseCase;
import com.muaythai.app.provider.VersionProvider;
import com.muaythai.core.common.BasePresenter;
import com.muaythai.core.common.UI;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by pi19124 on 12.06.2017.
 */

public class SplashPresenter extends BasePresenter<SplashPresenter.View> {

    private VersionProvider mVersionProvider;

    private CheckAuthorizationUseCase mCheckAuthorizationUseCase;

    @Inject
    public SplashPresenter(VersionProvider versionProvider, CheckAuthorizationUseCase checkAuthorizationUseCase) {
        super(AndroidSchedulers.mainThread());
        mVersionProvider = versionProvider;
        mCheckAuthorizationUseCase = checkAuthorizationUseCase;
    }

    public void initialize(){
        mView.displayVersionName(mVersionProvider.getVersionName());
        executeUseCase(mCheckAuthorizationUseCase,true).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                mView.navigateToMainScreen();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mView.displayError(throwable);
            }
        });
    }

    public interface View extends UI{

        void displayVersionName( String versionName );

        void navigateToMainScreen();

    }

}
