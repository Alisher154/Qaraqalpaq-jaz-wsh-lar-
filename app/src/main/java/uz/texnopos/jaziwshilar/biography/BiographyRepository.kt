package uz.texnopos.jaziwshilar.biography

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.texnopos.jaziwshilar.data.PoetEntity
import uz.texnopos.jaziwshilar.data.PoetsDao

class BiographyRepository(private val dao: PoetsDao) {


    suspend fun getPoetById(id: Int): PoetEntity {
        return withContext(Dispatchers.IO) {
            dao.getPoetById(id)
        }
    }

    suspend fun setStatus(id: Int, isFavorite: Boolean) {
        withContext(Dispatchers.IO){
            if (isFavorite) {
                dao.setStatusAndDate(id, 1, System.currentTimeMillis())
            } else {
                dao.setStatusAndDate(id, 0, System.currentTimeMillis())
            }
        }
    }
}