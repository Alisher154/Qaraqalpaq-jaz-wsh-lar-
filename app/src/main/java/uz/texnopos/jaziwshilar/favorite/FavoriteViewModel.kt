package uz.texnopos.jaziwshilar.favorite

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import uz.texnopos.jaziwshilar.data.Poet

class FavoriteViewModel(private val repository: FavoriteRepository) : ViewModel() {
    private val _favorites = MutableLiveData<List<Poet>>()

    val favorites: LiveData<List<Poet>> get() = _favorites


    fun getAllFavorites() {
        viewModelScope.launch {
            _favorites.value = repository.getAllFavorites()
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: FavoriteRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FavoriteViewModel(repository) as T
        }
    }
}