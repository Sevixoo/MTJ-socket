package com.muaythai.core.common;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by pi19124 on 12.06.2017.
 */

public class BasePresenter<VIEW extends UI>{

    protected VIEW mView;

    private Scheduler mViewThreadScheduler;

    public BasePresenter(Scheduler viewThreadScheduler) {
        mViewThreadScheduler = viewThreadScheduler;
    }

    public <RESULT,REQUEST> Observable<RESULT> executeUseCase(final UseCase<RESULT,REQUEST> useCase ,final REQUEST requestObject ){
        return Observable.create(new Observable.OnSubscribe<RESULT>(){
                @Override
                public void call(Subscriber<? super RESULT> subscriber) {
                    try {
                        subscriber.onStart();
                        RESULT result = useCase.execute(requestObject);
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }catch (Exception ex){
                        ex.getMessage();
                        subscriber.onError(ex);
                    }
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(mViewThreadScheduler);
    }

    public void setView(VIEW view) {
        this.mView = view;
    }
}
