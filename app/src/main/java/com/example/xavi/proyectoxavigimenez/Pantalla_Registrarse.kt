package com.example.xavi.proyectoxavigimenez

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.pantalla_registrarse.*
import java.util.regex.Pattern
import com.google.firebase.auth.FirebaseUser






class Pantalla_Registrarse : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1234
    }

    private lateinit var mAuth: FirebaseAuth
    private val TAG: String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_registrarse)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()



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

                registrarUsuario()

                val intent = Intent()

                intent.putExtras(getLoginBundle())
                setResult(Activity.RESULT_OK, intent)

                finish()

                /*
                val text3 = "Usuario creado correctamente"
                val duration3 = Toast.LENGTH_SHORT

                val toast3 = Toast.makeText(applicationContext, text3, duration3)
                toast3.show()
                */
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

    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

    }


    fun registrarUsuario(){
        var email = registrarse_email.text.toString()
        var password = registrarse_contrasena.text.toString()


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = mAuth.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception);
                Toast.makeText(this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
                updateUI(null)
            }


        }
    }



}
