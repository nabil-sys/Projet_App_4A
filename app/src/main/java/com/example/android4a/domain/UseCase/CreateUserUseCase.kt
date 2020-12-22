package com.example.android4a.domain.UseCase

import com.example.android4a.data.repository.UserRepository
import com.example.android4a.domain.entity.User

class CreateUserUseCase(
    private val userRepository: UserRepository
) {

    suspend fun invoke(user: User){
    userRepository.createUser(user)
    }
}