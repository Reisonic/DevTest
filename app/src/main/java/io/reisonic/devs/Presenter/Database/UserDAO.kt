package io.reisonic.devs.Presenter.Database

import androidx.room.*
import io.reisonic.devs.Model.Database.User


@Dao
interface UserDAO {
    @Query("SELECT * FROM user ORDER BY _id")
    fun loadAllUsers(): List<User?>?

    @Insert
    fun insertUser(person: User?)

    @Update
    fun updateUser(person: User?)

    @Delete
    fun deleteUser(person: User?)

    @Query("DELETE FROM user")
    fun nukeTable()

    @Query("SELECT * FROM user WHERE _id = :id")
    fun loadUserById(id: Int): User?
}