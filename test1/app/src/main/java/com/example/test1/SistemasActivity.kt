package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class SistemasActivity : AppCompatActivity() {

    lateinit var txtField: EditText
    lateinit var textFieldTxt: String
    lateinit var label: TextView
    lateinit var labelSet: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistemas)

        val btnAlert = findViewById<Button>(R.id.btnAlert)

        btnAlert.setOnClickListener{
            AlertDialog.Builder(this).apply {
                setTitle("ISC")
                setMessage("")
                setPositiveButton("ok",null)
            }.show()
        }

        val btnSetText = findViewById<Button>(R.id.btnSetText)
        btnSetText.setOnClickListener{
            txtField = findViewById<EditText>(R.id.txtFieldSistemas)
            textFieldTxt = txtField.text.toString()

            label = findViewById<TextView>(R.id.lblSistemas).apply {
                text=textFieldTxt
            }

        }
    }
}