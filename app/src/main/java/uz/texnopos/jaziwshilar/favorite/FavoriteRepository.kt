package uz.texnopos.jaziwshilar.favorite

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.texnopos.jaziwshilar.data.Poet
import uz.texnopos.jaziwshilar.data.PoetsDao

class FavoriteRepository(private val dao: PoetsDao) {

    suspend fun getAllFavorites(): List<Poet> {
        return withContext(Dispatchers.IO) {
            dao.getAllFavorites()
        }
    }

}