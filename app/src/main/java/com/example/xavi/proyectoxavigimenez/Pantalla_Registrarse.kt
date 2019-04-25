package com.example.xavi.proyectoxavigimenez

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pantalla_registrarse.*
import java.util.regex.Pattern

class Pantalla_Registrarse : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1234
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registrarse)

        //val email = intent.getStringExtra("email")
        //val contrasenya = intent.getStringExtra("contrasenya")

        //registrarse_email.setText(email)
        //registrarse_contrasena.setText(contrasenya)

        loadFields(intent.extras!!)

        registrarse_boton.setOnClickListener {
            var contraOK = false
            var contra2OK = false
            var emailOK = false
            var nombreOK = false

            if(registrarse_nombre.text.isEmpty()){
                registrarse_nombre.error = "No puede estar vacio."
            }else{
                nombreOK = true
            }

            if(registrarse_email.text.isEmpty()){
                registrarse_email.error = "No puede estar vacio."
            }else{

                if ( !Pattern.compile(".+\\@.+\\..+").matcher(registrarse_email.text).matches()) {
                    registrarse_email.error = "Formato incorrecto."
                }else{
                    emailOK = true
                }
            }

            if(registrarse_contrasena.text.isEmpty()){
                registrarse_contrasena.error = "No puede estar vacio."
            }else{
                if (registrarse_contrasena.length() >= 8) {
                    if (registrarse_contrasena.text.toString().contains("[0-9]".toRegex())) {
                        if (registrarse_contrasena.text.toString().contains("[a-zA-Z]".toRegex())) {
                            contraOK = true
                        }else{
                            registrarse_contrasena.error = "Tiene que tener minimo una letra."
                        }
                    }else{
                        registrarse_contrasena.error = "Tiene que tener minimo un numero."
                    }
                }else{
                    registrarse_contrasena.error = "Tiene que tener minimo 8 caracteres."
                }
            }


            if(registrarse_repetir_contrasena.text.isEmpty()){
                registrarse_repetir_contrasena.error = "No puede estar vacio."
            }else{
                if (registrarse_repetir_contrasena.length() >= 8) {
                    if(registrarse_repetir_contrasena.text.toString().equals(registrarse_contrasena.text.toString()) ){
                        contra2OK = true
                    }else{
                        registrarse_repetir_contrasena.error = "Las dos contraseñas tienen que ser iguales."
                    }
                }else{
                    registrarse_repetir_contrasena.error = "Tiene que tener minimo 8 caracteres."
                }

            }

            if(contraOK && emailOK && contra2OK && nombreOK){
                val intent = Intent()
                //intent.putExtra("email", registrarse_email.text.toString())
                //intent.putExtra("contrasenya", registrarse_contrasena.text.toString())
                intent.putExtras(getLoginBundle())
                setResult(Activity.RESULT_OK, intent)

                finish()

                val text3 = "Usuario creado correctamente"
                val duration3 = Toast.LENGTH_SHORT

                val toast3 = Toast.makeText(applicationContext, text3, duration3)
                toast3.show()
            }

        }
    }

    private fun loadFields(extras: Bundle) {
        registrarse_email.setText(extras.getString(Pantalla_log_in.EMAIL_EXTRA))
        registrarse_contrasena.setText(extras.getString(Pantalla_log_in.PASSWORD_EXTRA))
    }

    private fun getLoginBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(Pantalla_log_in.EMAIL_EXTRA, registrarse_email.text.toString())
        bundle.putString(Pantalla_log_in.PASSWORD_EXTRA, registrarse_contrasena.text.toString())
        return bundle
    }

}
