package uz.texnopos.jaziwshilar.poets

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.texnopos.jaziwshilar.data.PoetEntity
import uz.texnopos.jaziwshilar.data.PoetsDao

class PoetRepository(private val poetsDao: PoetsDao) {
    suspend fun getAllPoets(): List<PoetEntity> {
        return withContext(Dispatchers.IO) {
            poetsDao.getPoets()
        }
    }

    suspend fun getALlPoetsByName(query: String): List<PoetEntity> {

        return withContext(Dispatchers.IO) {
            poetsDao.getPoetsByName(query)
        }
    }

}