package br.com.alura.alugames.modelo

data class InfoJogo(val info: InfoAppShark) {
    override fun toString(): String {
        return info.toString()
    }

}