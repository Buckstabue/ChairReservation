package com.vtb.myapplication.chair.domain

import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class ChairReservationInteractor(
    private val chairReservationRepository: ChairReservationRepository
) {

    fun observeReservations(): Observable<ChairReservation> {
        return Observable.zip(
            chairReservationRepository.observeReservators(),
            chairReservationRepository.observeReservedChairs(),
            BiFunction { user, chair ->
                ChairReservation(
                    user = user,
                    chair = chair
                )
            })
    }
}