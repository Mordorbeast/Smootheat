package com.example.xavi.proyectoxavigimenez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pantalla__menu_principal.*

class Pantalla_MenuPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla__menu_principal)

        boton_recetas.setOnClickListener{
            val intent1 = Intent(this, Pantalla_recetas::class.java)
            startActivityForResult(intent1,1)
        }

        boton_nevera.setOnClickListener{
            val intent2 = Intent(this, Pantalla_nevera::class.java)
            startActivityForResult(intent2,1)
        }

        boton_aprende_a_cocinar.setOnClickListener{
            val intent3 = Intent(this, Pantalla_aprende_a_cocinar::class.java)
            startActivityForResult(intent3,1)
        }

        boton_lista_compra.setOnClickListener{
            val intent4 = Intent(this, Pantalla_lista_compra::class.java)
            startActivityForResult(intent4,1)
        }


    }
}
