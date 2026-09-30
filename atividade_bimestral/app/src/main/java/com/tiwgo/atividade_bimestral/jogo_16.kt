package com.tiwgo.atividade_bimestral

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import kotlin.collections.listOf

class jogo_16 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jogo16)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        var gerenciadorNivel: GerenciadorNivel = GerenciadorNivel(4, 4, "Dificil", this)
        gerenciadorNivel.placar_pares = findViewById<TextView>(R.id.placar_pares)
        gerenciadorNivel.placar_movimentos = findViewById<TextView>(R.id.placar_movimentos)
        gerenciadorNivel.nivel_dificuldade = findViewById<TextView>(R.id.nivel_dificuldade)
        gerenciadorNivel.imagensDisponiveis = mutableListOf(
            R.drawable.abelha,
            R.drawable.beija_flor,
            R.drawable.cachorro,
            R.drawable.cobra,
            R.drawable.galinha,
            R.drawable.girafa,
            R.drawable.leao,
            R.drawable.papagaio,
            R.drawable.peixe,
            R.drawable.pinguim,
            R.drawable.urso_panda,
            R.drawable.zebra
            )
        gerenciadorNivel.nivel_dificuldade?.text = "Dificil"

        gerenciadorNivel.sortearCartas()
        gerenciadorNivel.pegaBotoes()
    }
}