package com.tiwgo.atividade_bimestral

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class jogo_8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jogo8)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE


        var gerenciadorNivel: GerenciadorNivel = GerenciadorNivel(2, 4, "Facil", this)
        gerenciadorNivel.placar_pares = findViewById<TextView>(R.id.placar_pares)
        gerenciadorNivel.placar_movimentos = findViewById<TextView>(R.id.placar_movimentos)
        gerenciadorNivel.nivel_dificuldade = findViewById<TextView>(R.id.nivel_dificuldade)
        gerenciadorNivel.nivel_dificuldade?.text = "Facil"
        gerenciadorNivel.imagensDisponiveis = mutableListOf(
            R.drawable.surpreso,
            R.drawable.assustado,
            R.drawable.triste,
            R.drawable.bravo,
        )

        gerenciadorNivel.sortearCartas()
        gerenciadorNivel.pegaBotoes()
    }
}