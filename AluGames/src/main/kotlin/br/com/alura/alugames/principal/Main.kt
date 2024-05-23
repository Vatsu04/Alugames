package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)

    val gamer = Gamer.criarGamer(leitura)
    print("Cadastro concluido com sucesso. Dados do gamer: ")
    print(gamer)

    do {
        println("\nDigite um código de jogo para buscar: ")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)

        var meuJogo: Jogo? = null

        val resultado = runCatching {
            meuJogo = Jogo(
                informacaoJogo?.info?.title ?: throw IllegalArgumentException("Título não encontrado"),
                informacaoJogo.info.thumb
            )
        }

        resultado.onFailure {
            println("br.com.alura.alugames.modelo.Jogo inexistente, tente outro id.")
        }

        resultado.onSuccess {
            println("Deseja inserir uma descrição personalizada? (S/N)")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", ignoreCase = true)) {
                println("Insira a descrição personalizada para o jogo.")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada
            } else {
                meuJogo?.descricao = meuJogo?.titulo
            }

            gamer.jogosBuscados.add(meuJogo)
        }

        println("Deseja buscar um novo jogo? (S/N)")
        val resposta = leitura.nextLine()
    } while (resposta.equals("s", ignoreCase = true))


    println("Jogos buscados: ")
    println(gamer.jogosBuscados)

    println("\nJogos ordenados por Título: ")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach{
        println("Título: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }


    println("\nJogos filtrados: ")
    println(jogosFiltrados)


    println("Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()
    if(opcao.equals("s", true)) {
        println(gamer.jogosBuscados)
        println("Informe a posição do jogo que deseja excluir: ")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }



    println("\n Lista atualizada.")
    println(gamer.jogosBuscados)

    println("Busca finalizada com sucesso.")
}
