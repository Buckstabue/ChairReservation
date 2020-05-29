package com.vtb.myapplication.chair.domain

import io.reactivex.Observable

interface ChairReservationRepository {
    fun observeReservedChairs(): Observable<Chair>
    fun observeReservators(): Observable<User>
}