package com.example.xavi.proyectoxavigimenez

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.xavi.proyectoxavigimenez.menu.PantallaMenuPrincipal
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.pantalla_log_in.*
import java.util.regex.Pattern




class PantallaLogin : AppCompatActivity() {

    companion object {
        const val EMAIL_EXTRA = "email"
        const val PASSWORD_EXTRA = "contraseña"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_log_in)


        login_boton.setOnClickListener {
            var contraOK = false
            var emailOK = false

            if(login_email.text.isEmpty()){
                login_email.error = getString(R.string.error_noVacio)
            }else{
                if(!Pattern.compile(".+@.+\\..+").matcher(login_email.text).matches()) {
                    login_email.error = getString(R.string.error_formatoIncorrecto)
                }else{
                    emailOK = true
                }
            }

            if(login_contrasena.text.isEmpty()){
                login_contrasena.error = getString(R.string.error_noVacio)
            }else{
                if (login_contrasena.length() >= 8) {
                    if (login_contrasena.text.toString().contains("[0-9]".toRegex())) {
                        if (login_contrasena.text.toString().contains("[a-zA-Z]".toRegex())) {
                            contraOK = true
                        }else{
                            login_contrasena.error = getString(R.string.error_min1letra)
                        }
                    }else{
                        login_contrasena.error = getString(R.string.error_min1num)
                    }
                }else{
                    login_contrasena.error = getString(R.string.error_min8caracteres)
                }
            }

            if(contraOK && emailOK ){
                logUsuario()
            }
        }

        login_registrarse.setOnClickListener {
            val intent = Intent(this, PantallaRegistrarse::class.java)
            intent.putExtras(getRegisterBundle())
            startActivityForResult(intent,PantallaRegistrarse.REQUEST_CODE)
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
            PantallaRegistrarse.REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) updateViews(data!!)
            }
        }
    }

    private fun updateViews(data: Intent) {
        login_email.setText(data.getStringExtra(EMAIL_EXTRA))
        login_contrasena.setText(data.getStringExtra(PASSWORD_EXTRA))
    }


    fun logUsuario(){
        val email =  login_email.text.toString()
        val password = login_contrasena.text.toString()


        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (!it.isSuccessful){
                    return@addOnCompleteListener
                }else{

                    startActivity(Intent(this, PantallaMenuPrincipal::class.java))

                    Log.d("Main", "Usuario logueado con uid: ${it.result!!.user.uid}")
                }
            }
            .addOnFailureListener{
                login_email.error = getString(R.string.login_emailContraseñaIncorrectos)

                Log.d("Main", "Fallo al loguear el usuario: ${it.message}")
            }







    }

}
