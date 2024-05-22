package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.*

fun main() {

    val leitura = Scanner(System.`in`)

    do{
        println("Digite um código de jogo para buscar: ")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()

        val informacaoJogo = buscaApi.buscaJogo(busca)

        var meuJogo: Jogo? = null

        val resultado = runCatching {
            meuJogo = Jogo(
                informacaoJogo!!.info.title,
                informacaoJogo.info.thumb
            )
        }
        resultado.onFailure{
            println("br.com.alura.alugames.modelo.Jogo inexsitstente, tente outro id.")

        }

        resultado.onSuccess {
            println("Deseja inserir uma descrição personalizada? (S/N)")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)){
                println("Insira a descrição personalizada para o jogo.")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada

            } else {
                meuJogo?.descricao = meuJogo?.titulo
            }

            println(meuJogo)
        }
        println("Deseja buscar um novo jogo? (S/N)")




    }

        println("Busca finalizada com sucesso.")



}