import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner;


//    try{
//        val meuJogo = Jogo(
//            meuInfoJogo.info.title,
//            meuInfoJogo.info.thumb
//        )
//    } catch(ex: NullPointerException){
//        println("Jogo Inexistenete. Tente outro id")
//    }

fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar: ")
    val busca = leitura.nextLine()

    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"


    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()
    val response = client
        .send(request, BodyHandlers.ofString())

    val json = response.body()
    println(json)


    val gson = Gson()
    var meuInfoJogo:InfoJogo? = null

    val resultadoIJ = runCatching {
        meuInfoJogo =  gson.fromJson(
            json,
            InfoJogo::class.java
        )
    }
    resultadoIJ.onFailure{
        println("Id informado inexistente. Tente outro id.")
        System.exit(1)
    }

    var meuJogo:Jogo? = null

    val resultado = runCatching {
        meuJogo = Jogo(
            meuInfoJogo!!.info.title,
            meuInfoJogo!!.info.thumb
        )
    }
    resultado.onFailure{
        println("Jogo inexsitstente, tente outro id.")

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

    resultado.onSuccess {
        println("Busca finalizada com sucesso.")
    }


}