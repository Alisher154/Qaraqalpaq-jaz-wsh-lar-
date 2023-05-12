package uz.texnopos.jaziwshilar.data

import androidx.room.*

@Dao
interface PoetsDao {

    @Query("SELECT biography FROM jaziwshilar WHERE id =:id")
    fun getBiographyById(id: Int): String

    @Query("SELECT lifeSpan FROM jaziwshilar WHERE id=:id")
    fun getLifeSpanById(id: Int): String

    @Query("SELECT poetName FROM jaziwshilar WHERE id=:id")
    fun getPoetNameById(id: Int): String

    @Query("UPDATE jaziwshilar SET isFavorite=:isFavorite WHERE id=:id")
    fun setStatus(id: Int, isFavorite: Int)

    @Query("SELECT isFavorite FROM jaziwshilar WHERE id=:id")
    fun getStatus(id: Int): Int

    @Query("UPDATE jaziwshilar SET date=:currentTime WHERE id=:id")
    fun setDate(id: Int, currentTime: Long)

    @Query("SELECT id,poetName FROM jaziwshilar WHERE isFavorite=1 ORDER by date DESC")
    suspend fun getFavorites(): List<PoetEntity>

    @Query("SELECT id , poetName from jaziwshilar")
    fun getPoetsAndId(): List<PoetEntity>

    @Query("SELECT * FROM  jaziwshilar")
    suspend fun getPoets(): List<PoetEntity>

    @Query("SELECT * FROM jaziwshilar WHERE poetName LIKE '%' || :query || '%'")
    suspend fun getPoetsByName(query: String): List<PoetEntity>

    @Query("SELECT * FROM jaziwshilar WHERE id =:id")
    suspend fun getPoetById(id: Int): PoetEntity

    @Query("UPDATE jaziwshilar SET isFavorite=:isFavorite, date=:currentTime WHERE id=:id")
    suspend fun setStatusAndDate(id:Int, isFavorite: Int, currentTime: Long)
}