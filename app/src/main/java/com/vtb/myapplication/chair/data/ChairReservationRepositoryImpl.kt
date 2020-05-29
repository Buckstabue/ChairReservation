package com.vtb.myapplication.chair.data

import com.vtb.myapplication.chair.domain.Chair
import com.vtb.myapplication.chair.domain.ChairReservationRepository
import com.vtb.myapplication.chair.domain.User
import io.reactivex.Observable
import java.util.Random
import java.util.concurrent.TimeUnit.SECONDS

class ChairReservationRepositoryImpl : ChairReservationRepository {
    private val random = Random()
    private val userNames = listOf(
        "Alexey",
        "Dmitriy",
        "Vaycheslav",
        "Anton",
        "Jessica"
    )

    override fun observeReservedChairs(): Observable<Chair> {
        return Observable.interval(1, SECONDS)
            .map {
                Chair(
                    number = random.nextInt(100)
                )
            }
    }

    override fun observeReservators(): Observable<User> {
        return Observable.interval(2, SECONDS)
            .map {
                User(
                    name = userNames.random()
                )
            }
    }
}