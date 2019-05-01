package com.example.xavi.proyectoxavigimenez

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.pantalla_log_in.*
import java.util.regex.Pattern

class Pantalla_log_in : AppCompatActivity() {

    companion object {
        const val EMAIL_EXTRA = "email"
        const val PASSWORD_EXTRA = "contraseña"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_log_in)

        login_boton.setOnClickListener {
            var contraOK = false
            var emailOK = false

            if(login_email.text.isEmpty()){
                login_email.error = "No puede estar vacio."
            }else{
                if(!Pattern.compile(".+\\@.+\\..+").matcher(login_email.text).matches()) {
                    login_email.error = "Formato incorrecto."
                }else{
                    emailOK = true;
                }
            }

            if(login_contrasena.text.isEmpty()){
                login_contrasena.error = "No puede estar vacio."
            }else{
                if (login_contrasena.length() >= 8) {
                    if (login_contrasena.text.toString().contains("[0-9]".toRegex())) {
                        if (login_contrasena.text.toString().contains("[a-zA-Z]".toRegex())) {
                            contraOK = true
                        }else{
                            login_contrasena.error = "Tiene que tener minimo una letra."
                        }
                    }else{
                        login_contrasena.error = "Tiene que tener minimo un numero."
                    }
                }else{
                    login_contrasena.error = "Tiene que tener minimo 8 caracteres."
                }
            }

            if(contraOK && emailOK ){
                /*
                val text = "Logueado correctamente"
                val duration = Toast.LENGTH_LONG

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                */

                val intent2 = Intent(this, Pantalla_MenuPrincipal::class.java)
                startActivityForResult(intent2,1)
            }
        }

        login_registrarse.setOnClickListener {
            val intent = Intent(this, Pantalla_Registrarse::class.java)
            //intento1.putExtra("email", login_email.text.toString())
            //intento1.putExtra("contrasenya", login_contrasena.text.toString())
            intent.putExtras(getRegisterBundle())
            startActivityForResult(intent,Pantalla_Registrarse.REQUEST_CODE)
        }
    }

    private fun getRegisterBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(EMAIL_EXTRA, login_email.text.toString())
        bundle.putString(PASSWORD_EXTRA, login_contrasena.text.toString())
        return bundle
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Pantalla_Registrarse.REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) updateViews(data!!)
            }
        }
    }

    private fun updateViews(data: Intent) {
        login_email.setText(data.getStringExtra(EMAIL_EXTRA))
        login_contrasena.setText(data.getStringExtra(PASSWORD_EXTRA))
    }


}
