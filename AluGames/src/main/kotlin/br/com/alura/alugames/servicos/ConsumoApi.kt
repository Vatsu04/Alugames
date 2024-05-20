package br.com.alura.alugames.servicos

import br.com.alura.alugames.modelo.InfoJogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoApi {

    fun buscaJogo(id: String): InfoJogo? {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"


        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()
        val response = client
            .send(request, BodyHandlers.ofString())

        val json = response.body()

        val gson = Gson()



        var meuInfoJogo: InfoJogo? = null

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

        return meuInfoJogo
    }

}