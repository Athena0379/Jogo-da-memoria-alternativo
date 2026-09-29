package com.tiwgo.atividade_bimestral

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val btn_start = findViewById<Button>(R.id.btn_start)

        btn_start.setOnClickListener {
            val intent = Intent(this, tela_de_jogo::class.java)
            startActivity(intent)
        }
        val btn_regras = findViewById<Button>(R.id.btn_regras)

        btn_regras.setOnClickListener {
            val intent = Intent(this, regras_e_mecanicas::class.java)
            startActivity(intent)
        }
    }
}