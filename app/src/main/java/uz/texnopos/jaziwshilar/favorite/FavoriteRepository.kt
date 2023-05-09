package uz.texnopos.jaziwshilar.favorite

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.texnopos.jaziwshilar.data.PoetEntity
import uz.texnopos.jaziwshilar.data.PoetsDao

class FavoriteRepository(private val dao: PoetsDao) {

    suspend fun getAllFavorites(): List<PoetEntity> {
        return withContext(Dispatchers.IO) {
            dao.getFavorites()
        }
    }

}