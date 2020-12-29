package com.example.android4a.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android4a.data.local.models.UserLocal

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM userlocal")
    fun getAll(): List<UserLocal>


    @Query("SELECT * FROM userlocal WHERE email LIKE :email and password LIKE :password LIMIT 1")
    fun findByName(email: String, password: String): UserLocal?

    @Insert
    fun insert(vararg user: UserLocal)

    @Delete
    fun delete(user: UserLocal)
}
