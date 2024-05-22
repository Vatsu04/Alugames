import br.com.alura.alugames.modelo.Gamer

fun main(){
    val gamer1 = Gamer("Guga", "jacque@email.com")



    println(gamer1)

    gamer1.let{
        it.dataNascimento = "01/04/2004"
        it.usuario = "Vatsu"

    }.also{
        println(gamer1.idInterno)
    }

    println(gamer1)
    gamer1.usuario = "vatsu"
    println(gamer1)
}