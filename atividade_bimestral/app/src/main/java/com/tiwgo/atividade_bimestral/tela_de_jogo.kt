package com.tiwgo.atividade_bimestral

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.google.android.material.slider.Slider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class tela_de_jogo : AppCompatActivity() {

    val linha: Int = 4
    val coluna: Int = 6
    var contador: Int = 0
    var ultimaCarta: Int = -1

    var selecionados: MutableList<ImageButton?> = mutableListOf(null, null)

    var ponto: Int = 0

    var matrizTabuleiro: Array<Array<Int>> = Array(linha) {
        Array(coluna) { 0 }
    }

    var bloqueioInput: Boolean = true

    val imagensDisponiveis = listOf(
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
    var progress_bar: ProgressBar? = null
    var slider: Slider? = null
    var placarpontos: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_de_jogo)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE


        placarpontos = findViewById<TextView>(R.id.placar_pontos)

        progress_bar = findViewById<ProgressBar>(R.id.progress_bar)
        slider = Slider(this)

        slider?.valueFrom = 0f
        slider?.valueTo = 100f
        slider?.stepSize = 10f
        slider?.value = 0f

        sortearCartas()
        // Percorre as linhas (de 0 a 2, totalizando 3 linhas)
        for (linha in 0 .. linha - 1) {
            // Percorre as colunas (de 0 a 4, totalizando 5 colunas)
            for (coluna in 0..coluna - 1) {

                // Monta o nome do ID dinamicamente (ex: "btn_00", "btn_01", etc.)
                val nomeId = "btn_${linha}${coluna}"

                // Converte a string para o ID real do Android
                val idBotao = resources.getIdentifier(nomeId, "id", packageName)

                if (idBotao != 0) {
                    val botao = findViewById<ImageButton>(idBotao) // ou Button, dependendo do seu componente

                    // Configura o evento de clique para cada carta/botão da matriz
                    botao.setOnClickListener {
                        // Aqui entra a lógica de quando o jogador clica na carta da posição [linha][coluna]
                        tratarCliqueCarta(linha, coluna, botao)
                    }
                }
            }
            slider?.addOnChangeListener { _, value: Float, fromUser: Boolean ->
                progress_bar?.progress = value.toInt()
                Log.d("text", slider?.valueFrom.toString())
                if(value >= (slider?.valueTo ?: 0f)){
                    val intent = Intent(this, teladeresultado::class.java)
                    intent.putExtra(getString(R.string.pontos_keys), ponto)
                    startActivity(intent)
                }
            }
        }
    }
    fun tratarCliqueCarta(linha: Int, coluna: Int, btn: ImageButton) : Unit {
        if(!bloqueioInput) return
        selecionados[contador] = btn
        selecionados[contador]?.setImageResource(matrizTabuleiro[linha][coluna])

        val coordenada : IVector = IVector(linha,coluna )

        if(contador == 0){

            ultimaCarta = pegaImagem(coordenada)
            selecionados[contador]?.isEnabled = false
        }
       else{
            if(ultimaCarta == pegaImagem(coordenada)){
                selecionados[contador]?.isEnabled = false
                ponto++
                placarpontos?.text = ponto.toString()
                selecionados.replaceAll{null}
                if(ponto >= imagensDisponiveis.count()*2){
                    val intent = Intent(this, teladeresultado::class.java)
                    intent.putExtra(getString(R.string.pontos_keys), ponto)
                    startActivity(intent)
                }
            }
            else{
                selecionados[0]?.isEnabled = true
                lifecycleScope.launch{
                    val novoValor: Float = (slider?.value ?: 0f) + (slider?.stepSize ?: 1f)
                    slider?.value = novoValor
                    bloqueioInput = false
                    resetpar()
                    bloqueioInput = true
                }
            }
        }
        contador = (contador + 1) % 2
    }
    suspend fun resetpar() : Unit {
        delay(1000)
        for (e in selecionados){
            e?.setImageResource(R.drawable.cartadecosta)
        }
    }
    fun pegaImagem(coordenada : IVector) :Int{
        return matrizTabuleiro[coordenada.x][coordenada.y]
    }
    fun sortearCartas(): Unit{
        // 2. Criar a lista final de 24 cartas, duplicando cada uma das 12 imagens
        val listaCartas = mutableListOf<Int>()
        for (imagem in imagensDisponiveis) {
            listaCartas.add(imagem) // Adiciona a carta 1 do par
            listaCartas.add(imagem) // Adiciona a carta 2 do par
        }

        // 3. Baralhar as 24 cartas de forma totalmente aleatória e eficiente
        val cartasEmbaralhadas = listaCartas.shuffled()

        // 5. Distribuir as cartas baralhadas para dentro da matriz
        var indiceBaralho = 0
        for (linha in 0..linha - 1) {
            for (coluna in 0..coluna - 1) {
                matrizTabuleiro[linha][coluna] = cartasEmbaralhadas[indiceBaralho]
                indiceBaralho++
            }
        }
    }
}