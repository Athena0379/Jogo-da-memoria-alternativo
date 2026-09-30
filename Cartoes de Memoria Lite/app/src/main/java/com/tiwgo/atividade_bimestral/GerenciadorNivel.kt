package com.tiwgo.atividade_bimestral

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

public class GerenciadorNivel(var linha: Int, var coluna: Int, var level: String, private val context: Context) {

    var contador: Int = 0
    var ultimaCarta: Int = -1

    var selecionados: MutableList<ImageButton?> = mutableListOf(null, null)

    var contador_pares: Int = 0

    var contador_movimentos: Int = 0

    var matrizTabuleiro: Array<Array<Int>> = Array(linha) {
        Array(coluna) { 0 }
    }
    var bloqueioInput: Boolean = true

    var placar_pares: TextView? = null
    var placar_movimentos: TextView? = null

    var nivel_dificuldade: TextView? = null

    var activity :AppCompatActivity? = null

    var imagensDisponiveis : MutableList<Int>? = null
    init {
        activity = context as? AppCompatActivity
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
            contador_movimentos++
            placar_movimentos?.text = contador_movimentos.toString()

            if(ultimaCarta == pegaImagem(coordenada)){
                selecionados[contador]?.isEnabled = false
                contador_pares++
                placar_pares?.text = contador_pares.toString()
                selecionados.replaceAll{null}
                if(contador_pares >= imagensDisponiveis?.count() ?: 0 *2){
                    val intent = Intent(context, teladeresultado::class.java)
                    intent.putExtra(context.getString(R.string.pares), contador_pares)
                    intent.putExtra(context.getString(R.string.movimentos), contador_movimentos)
                    intent.putExtra(context.getString(R.string.level), level)

                    context.startActivity(intent)
                }
            }
            else{
                selecionados[0]?.isEnabled = true
                activity?.lifecycleScope?.launch{
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
        for (imagem in imagensDisponiveis ?: 0 .. 0) {
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
    fun pegaBotoes() :Unit{
        for (linha in 0 until linha) { // Cuidado para não usar "linha in 0..linha - 1" se "linha" for o tamanho
            for (coluna in 0 until coluna) {

                // Monta o nome do ID dinamicamente (ex: "btn_00", "btn_01", etc.)
                val nomeId = "btn_${linha}${coluna}"

                // Converte a string para o ID real do Android
                val idBotao = activity?.resources?.getIdentifier(nomeId, "id", activity?.packageName)

                if (idBotao != 0 && idBotao != null) {
                    val botao = activity?.findViewById<ImageButton>(idBotao)

                    if (botao != null) {
                        // SUCESSO: O botão foi encontrado no layout!
                        Log.d("MatrizTeste", "Sucesso: Encontrou o botão [$linha][$coluna] com o nome '$nomeId'")
                    } else {
                        // ERRO: O ID existe no resources, mas o findViewById não o achou (pode estar noutro layout)
                        Log.d("MatrizTeste", "Aviso: O ID '$nomeId' existe, mas o findViewById retornou null.")
                    }

                    // Configura o evento de clique para cada carta/botão da matriz
                    botao?.setOnClickListener {
                        Log.d("MatrizTeste", "Clicou no botão da posição: [$linha][$coluna]")
                        tratarCliqueCarta(linha, coluna, botao)
                    }
                } else {
                    // ERRO: O nome gerado não corresponde a nenhum ID no XML
                    Log.d("MatrizTeste", "Erro: O ID '$nomeId' NÃO foi encontrado no projeto.")
                }
            }
        }
    }
}