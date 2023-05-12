package uz.texnopos.jaziwshilar.biography

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import uz.texnopos.jaziwshilar.data.PoetEntity
import uz.texnopos.jaziwshilar.poets.PoetRepository
import uz.texnopos.jaziwshilar.poets.PoetsViewModel

class BiographyViewModel(private val repository: BiographyRepository) : ViewModel() {
     var isFavorite: Boolean = false
    private val _biography = MutableLiveData<PoetEntity>()
    val biography: LiveData<PoetEntity> get() = _biography


    fun getPoetById(id: Int) {
        viewModelScope.launch {
            val poet = repository.getPoetById(id)
            _biography.value = poet
            isFavorite = poet.isFavorite == 1
        }
    }

    fun setPoetStatus(id: Int) {
        isFavorite = !isFavorite
        viewModelScope.launch {
            repository.setStatus(id, isFavorite)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: BiographyRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BiographyViewModel(repository) as T
        }
    }
}

