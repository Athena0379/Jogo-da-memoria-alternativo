package com.tiwgo.atividade_bimestral

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class teladeresultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_teladeresultado)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val btn_restart = findViewById<Button>(R.id.btn_restart)

        var pontuacao_final = intent.getIntExtra(getString(R.string.pontos_keys), 0)
        var pontos: TextView = findViewById(R.id.pontuacao_final)
        pontos.text = "$pontuacao_final"

        btn_restart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}