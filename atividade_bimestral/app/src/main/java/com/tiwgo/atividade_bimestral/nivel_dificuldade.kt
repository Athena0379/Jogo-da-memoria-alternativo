package com.tiwgo.atividade_bimestral

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class nivel_dificuldade : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nivel_dificuldade)

        val btn_facil = findViewById<Button>(R.id.btn_facil)
        val btn_medio = findViewById<Button>(R.id.btn_medio)
        val btn_dificil = findViewById<Button>(R.id.btn_dificil)


        btn_facil.setOnClickListener {
            val intent = Intent(this, jogo_8::class.java)
            startActivity(intent)
        }
        btn_medio.setOnClickListener {
            val intent = Intent(this, jogo_12::class.java)
            startActivity(intent)
        }
        btn_dificil.setOnClickListener {
            val intent = Intent(this, jogo_16::class.java)
            startActivity(intent)
        }
    }
}