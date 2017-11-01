package com.example.test.sample

import com.example.test.sample.network.SearchRepositoryProvider
import com.google.gson.JsonObject
import greenRobotEvents.SearchEvents
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus


/**
 * Created by sumon.chatterjee on 01/11/17.
 */
class SearchPresenter{
    var compositeDisposable: CompositeDisposable = CompositeDisposable()
   // var mEventBus: EventBus = EventBus()

    fun getSearchResult(){
        EventBus.getDefault().post(SearchEvents.SearchShowProgress())
        val repository = SearchRepositoryProvider.provideSearchRepository()

        compositeDisposable.add(
                repository.searchUsers("Lagos", "Java")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(object : DisposableObserver<JsonObject>() {

                    override fun onNext(jsonObject: JsonObject) {
                        EventBus.getDefault().post(SearchEvents.SearchHideProgress())

                        jsonObject.getAsJsonObject("result")
                        EventBus.getDefault().post(SearchEvents.SearchResultEvent(jsonObject))
                    }

                    override fun onError(e: Throwable) {
                        println("error")
                    }

                    override fun onComplete() {
                        println("complete")
                    }
                }));

    }


}