package com.example.xavi.proyectoxavigimenez

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

class SplashScreen : AppCompatActivity() {

    private val DURACION_SPLASH = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            val intent = Intent(this, Pantalla_log_in::class.java)
            startActivity(intent)
            finish()
        }, DURACION_SPLASH.toLong())
    }
}
