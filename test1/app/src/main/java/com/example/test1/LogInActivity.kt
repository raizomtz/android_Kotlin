package com.example.test1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LogInActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var email: EditText
    lateinit var emailTxt: String
    lateinit var contra: EditText
    lateinit var contraTxt: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        firebaseAuth = FirebaseAuth.getInstance()

        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        btnEntrar.setOnClickListener{

            validateData()
        }

    }

    private fun validateData() {
        contra = findViewById<EditText>(R.id.txtFieldLogContra)
        contraTxt = contra.text.toString()
        email = findViewById<EditText>(R.id.txtFieldLogEmail)
        emailTxt = email.text.toString()

        if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()){
            Toast.makeText(this,"Correo no valido...", Toast.LENGTH_SHORT).show()
        }
        else if(contraTxt.isEmpty()){
            Toast.makeText(this,"Introduce tu contraseña..", Toast.LENGTH_SHORT).show()
        }
        else {
            loginUser()
        }

    }

    private fun loginUser() {
        firebaseAuth.signInWithEmailAndPassword(emailTxt, contraTxt)
            .addOnSuccessListener {
                checkCarrera()
            }
            .addOnFailureListener{
                Toast.makeText(this,"Credenciales no validas", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkCarrera() {
        val firebaseUser = firebaseAuth.currentUser!!

        val db = FirebaseDatabase.getInstance().getReference("Usuarios")
        db.child(firebaseUser.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot){
                    val carrera = snapshot.child("carrerra").value

                    if(carrera == "sistemas"){
                        //implementar cambio pantalla
                        startActivity(Intent(this@LogInActivity, SistemasActivity::class.java))
                        finish()

                    }else if(carrera == "quimica"){
                        startActivity(Intent(this@LogInActivity, QuimicaActivity::class.java))
                        finish()

                    }
                }
                override fun onCancelled(error : DatabaseError){

                }
            })
    }
}