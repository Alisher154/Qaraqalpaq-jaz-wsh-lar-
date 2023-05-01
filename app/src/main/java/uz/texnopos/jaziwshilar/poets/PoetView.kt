package uz.texnopos.jaziwshilar.poets

import uz.texnopos.jaziwshilar.data.Poet

interface PoetView {
    fun setData(models: List<Poet>)
    fun filteredNames(list: List<Poet>)
}