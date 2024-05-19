data class Jogo (val titulo:String,
         val capa:String) {

    val descricao:String? = null
    override fun toString(): String {
        return "Meu Jogo:\n" +
                "Titulo=  '$titulo' \n" +
                "Capa='$capa' \n" +
                "Descricao='$descricao' "
    }
}