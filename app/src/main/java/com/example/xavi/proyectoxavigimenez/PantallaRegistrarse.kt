package com.example.xavi.proyectoxavigimenez

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.pantalla_registrarse.*
import java.util.regex.Pattern


class PantallaRegistrarse : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1
    }

    var registroOK = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_registrarse)

        loadFields(intent.extras!!)

        registrarse_boton.setOnClickListener {
            var contraOK = false
            var contra2OK = false
            var emailOK = false
            //var nombreOK = false
/*
            if(registrarse_nombre.text.isEmpty()){
                registrarse_nombre.error = "No puede estar vacio."
            }else{
                nombreOK = true
            }
*/
            if(registrarse_email.text.isEmpty()){
                registrarse_email.error = getString(R.string.error_noVacio)
            }else{

                if ( !Pattern.compile(".+\\@.+\\..+").matcher(registrarse_email.text).matches()) {
                    registrarse_email.error = getString(R.string.error_formatoIncorrecto)
                }else{
                    emailOK = true
                }
            }

            if(registrarse_contrasena.text.isEmpty()){
                registrarse_contrasena.error = getString(R.string.error_noVacio)
            }else{
                if (registrarse_contrasena.length() >= 8) {
                    if (registrarse_contrasena.text.toString().contains("[0-9]".toRegex())) {
                        if (registrarse_contrasena.text.toString().contains("[a-zA-Z]".toRegex())) {
                            contraOK = true
                        }else{
                            registrarse_contrasena.error = getString(R.string.error_min1letra)
                        }
                    }else{
                        registrarse_contrasena.error = getString(R.string.error_min1num)
                    }
                }else{
                    registrarse_contrasena.error = getString(R.string.error_min8caracteres)
                }
            }

            if(registrarse_repetir_contrasena.text.isEmpty()){
                registrarse_repetir_contrasena.error = getString(R.string.error_noVacio)
            }else{
                if (registrarse_repetir_contrasena.length() >= 8) {
                    if(registrarse_repetir_contrasena.text.toString().equals(registrarse_contrasena.text.toString()) ){
                        contra2OK = true
                    }else{
                        registrarse_repetir_contrasena.error = getString(R.string.error_contrasDif)
                    }
                }else{
                    registrarse_repetir_contrasena.error = getString(R.string.error_min8caracteres)
                }

            }

            if(contraOK && emailOK && contra2OK){
                registrarUsuario()
            }

        }
    }

    private fun loadFields(extras: Bundle) {
        registrarse_email.setText(extras.getString(PantallaLogin.EMAIL_EXTRA))
        registrarse_contrasena.setText(extras.getString(PantallaLogin.PASSWORD_EXTRA))
    }

    private fun getLoginBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(PantallaLogin.EMAIL_EXTRA, registrarse_email.text.toString())
        bundle.putString(PantallaLogin.PASSWORD_EXTRA, registrarse_contrasena.text.toString())
        return bundle
    }


    fun registrarUsuario(){
        val email = registrarse_email.text.toString()
        val password = registrarse_contrasena.text.toString()


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful){
                    return@addOnCompleteListener
                }else{
                    intent.putExtras(getLoginBundle())
                    setResult(Activity.RESULT_OK, intent)

                    finish()

                    Log.d("Main", "Usuario creado con uid: ${it.result!!.user.uid}")
                }
            }
            .addOnFailureListener{
                registrarse_email.error = getString(R.string.register_errorCrearUsuario)

                Log.d("Main", "Fallo al crear el usuario: ${it.message}")
            }
    }
}
