package com.zolax.appforvk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var outText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.sendTextBtn)
        val inpText: EditText  = findViewById(R.id.inputText)
        outText = findViewById(R.id.outputText)
        button.setOnClickListener {
            outText.text = inpText.text
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("textForOutText", outText.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        outText.text = savedInstanceState.getString("textForOutText")
    }
}