package uz.texnopos.jaziwshilar.poets

import androidx.lifecycle.*
import androidx.room.Query
import kotlinx.coroutines.launch
import uz.texnopos.jaziwshilar.data.PoetEntity
import uz.texnopos.jaziwshilar.favorite.FavoriteRepository
import uz.texnopos.jaziwshilar.favorite.FavoriteViewModel
import uz.texnopos.jaziwshilar.toKiril

class PoetsViewModel(private val repository: PoetRepository) : ViewModel() {

    private val _poets = MutableLiveData<List<PoetEntity>>()

    val poets: LiveData<List<PoetEntity>> get() = _poets

    fun getPoets() {
        viewModelScope.launch {
            _poets.value = repository.getAllPoets()
        }
    }

    fun getPoetsByName(query: String) = viewModelScope.launch {
        _poets.value = repository.getALlPoetsByName(query.toKiril())
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: PoetRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PoetsViewModel(repository) as T
        }
    }

}