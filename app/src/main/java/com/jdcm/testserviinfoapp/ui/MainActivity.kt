package com.jdcm.testserviinfoapp.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jdcm.testserviinfoapp.R
import com.jdcm.testserviinfoapp.ui.movies.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        /*
        This will sett the floating button to switch to the night/light mode of the app if the user taps in it,
        Evidently this only will work for the app theme, because if the OP have a native Dark Theme It will change
        the colors for the user automatically
        */


        val floating = this.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        when (this.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                floating.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.ic_baseline_wb_sunny_24))
                floating.setOnClickListener {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                floating.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.ic_baseline_nights_stay_24))
                floating.setOnClickListener {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }




    }
}