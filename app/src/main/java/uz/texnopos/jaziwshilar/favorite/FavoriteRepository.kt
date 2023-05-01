package uz.texnopos.jaziwshilar.favorite

import uz.texnopos.jaziwshilar.data.Poet
import uz.texnopos.jaziwshilar.data.PoetsDao

class FavoriteRepository(private val dao: PoetsDao) {

    suspend fun getAllFavorites():List<Poet> = dao.getAllFavorites()

}