package com.example.test1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class SignInActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var carrera: EditText
    lateinit var carrTxt: String
    lateinit var email: EditText
    lateinit var emailTxt: String
    lateinit var contrasena: EditText
    lateinit var contraTxt: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        firebaseAuth = FirebaseAuth.getInstance()

      val btnRegis = findViewById<Button>(R.id.btnRegistrarse)

        btnRegis.setOnClickListener{

            validateData()
        }

    }

    private fun validateData() {
        carrera = findViewById<EditText>(R.id.txtFieldCarrera)
        carrTxt = carrera.text.toString()
        email = findViewById<EditText>(R.id.txtFieldEmail)
        emailTxt = email.text.toString()
        contrasena = findViewById<EditText>(R.id.txtFieldContraseña)
        contraTxt = contrasena.text.toString()

        if (carrTxt.isEmpty()){
            Toast.makeText(this,"Introduce tu carrera..", Toast.LENGTH_SHORT).show()
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()){
            Toast.makeText(this,"Correo no valido..", Toast.LENGTH_SHORT).show()
        }
        else if(contraTxt.isEmpty()){
            Toast.makeText(this,"Introduce tu contraseña..", Toast.LENGTH_SHORT).show()
        }
        else{
            createUser()
        }
    }

    private fun createUser() {
        firebaseAuth.createUserWithEmailAndPassword(emailTxt,contraTxt)
            .addOnSuccessListener {
                //add user data
                saveUserData()
                Toast.makeText(this,"Usuario creado con sexito..", Toast.LENGTH_SHORT).show()
                val Intent = Intent(this, LogInActivity::class.java)
                startActivity(Intent)
            }
            .addOnFailureListener{
                Toast.makeText(this,"Error al crear usuario..", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveUserData() {
        val timestamp = System.currentTimeMillis()

        val uid = firebaseAuth.uid

        val hashMap: HashMap<String, Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["email"] = emailTxt
        hashMap["carrerra"] = carrTxt
        hashMap["userType"] = "user"
        hashMap["timestamp"] = timestamp

        val db = FirebaseDatabase.getInstance().getReference("Usuarios")
        db.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                Toast.makeText(this,"datos guardados...", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Error al guardar datos..", Toast.LENGTH_SHORT).show()
            }
    }
}