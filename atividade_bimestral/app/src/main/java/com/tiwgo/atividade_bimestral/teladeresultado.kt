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

        val btn_tela_inicial = findViewById<Button>(R.id.btn_tela_inicial)
        val btn_nova_partida = findViewById<Button>(R.id.btn_nova_partida)

        var movimento = intent.getIntExtra(getString(R.string.movimentos), 0)
        var placar_movimentos: TextView = findViewById(R.id.placar_movimentos)
        placar_movimentos.text = "$movimento"


        var pares = intent.getIntExtra(getString(R.string.pares), 0)
        var placar_pares: TextView = findViewById(R.id.placar_pares)
        placar_pares.text = "$pares"

        btn_tela_inicial.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btn_nova_partida.setOnClickListener {
            val intent = Intent(this, nivel_dificuldade::class.java)
            startActivity(intent)
        }
    }
}