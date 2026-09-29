package com.tiwgo.atividade_bimestral

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val btn_start = findViewById<Button>(R.id.btn_facil)

        btn_start.setOnClickListener {
            val intent = Intent(this, jogo_16::class.java)
            startActivity(intent)
        }
        val btn_regras = findViewById<Button>(R.id.btn_comecar)

        btn_regras.setOnClickListener {
            val intent = Intent(this, regras_e_mecanicas::class.java)
            startActivity(intent)
        }
    }
}