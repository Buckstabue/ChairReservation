package com.vtb.myapplication.chair.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vtb.myapplication.chair.domain.ChairReservationInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ChairReservationsViewModel(
    private val chairReservationInteractor: ChairReservationInteractor
) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    var chairReservations = MutableLiveData<String>()

    init {
        chairReservationInteractor.observeReservations()
            .map { "${it.user.name} seated ${it.chair.number}" }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    chairReservations.value = it
                },
                {
                    it.printStackTrace()
                }
            )
            .let(subscriptions::add)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}