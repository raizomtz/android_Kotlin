package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import org.w3c.dom.Text

class QuimicaActivity : AppCompatActivity() {
    lateinit var txtField: EditText
    lateinit var txtFieldTxt: String
    lateinit var txtUpdate: EditText
    lateinit var lblUpdate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quimica)

        txtUpdate = findViewById<EditText>(R.id.txtFieldUpdateLive)
        lblUpdate = findViewById<TextView>(R.id.lblQuimica)

        txtUpdate.addTextChangedListener(object : TextWatcher{

            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                lblUpdate.setText(""+p0)
            }
        })


        val btnAlert = findViewById<Button>(R.id.btnSetAlert)
        btnAlert.setOnClickListener{

            txtField = findViewById<EditText>(R.id.txtFieldMsgAlert)
            txtFieldTxt = txtField.text.toString()

            AlertDialog.Builder(this).apply {
                setTitle("Ing. Quimica")
                setMessage(""+txtFieldTxt)
                setPositiveButton("ok",null)
            }.show()
        }

    }
}