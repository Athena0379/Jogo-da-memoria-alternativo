package com.tiwgo.atividade_bimestral

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class jogo_12 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jogo12)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        var gerenciadorNivel: GerenciadorNivel = GerenciadorNivel(3, 4, "Medio", this)
        gerenciadorNivel.placar_pares = findViewById<TextView>(R.id.placar_pares)
        gerenciadorNivel.placar_movimentos = findViewById<TextView>(R.id.placar_movimentos)
        gerenciadorNivel.nivel_dificuldade = findViewById<TextView>(R.id.nivel_dificuldade)
        gerenciadorNivel.nivel_dificuldade?.text = "Medio"

        gerenciadorNivel.imagensDisponiveis = mutableListOf(
            R.drawable.alegre,
            R.drawable.confuso,
            R.drawable.contente,
            R.drawable.emburrado,
            R.drawable.triste,
            R.drawable.assustado,
        )

        gerenciadorNivel.sortearCartas()
        gerenciadorNivel.pegaBotoes()
    }
}