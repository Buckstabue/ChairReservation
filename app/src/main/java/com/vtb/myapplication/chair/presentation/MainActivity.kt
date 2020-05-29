package com.vtb.myapplication.chair.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.vtb.myapplication.R
import com.vtb.myapplication.R.layout
import com.vtb.myapplication.chair.data.ChairReservationRepositoryImpl
import com.vtb.myapplication.chair.domain.ChairReservationInteractor

class MainActivity : AppCompatActivity() {

    private lateinit var reservationsTextView: TextView

    // TODO use view model factory
    private val viewModel by lazy {
        ChairReservationsViewModel(
            ChairReservationInteractor(
                ChairReservationRepositoryImpl()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        reservationsTextView = findViewById(R.id.reservations)


        viewModel.chairReservations.observe(
            this,
            Observer {
                reservationsTextView.text = it
            }
        )
    }
}