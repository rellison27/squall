package com.example.breezepoc.datasource.network.mappers.login

import com.example.breezepoc.datasource.network.model.AuthResponse
import com.example.breezepoc.domain.model.Login.Token
import com.example.breezepoc.domain.model.util.DtoMapper

class TokenMapper constructor() : DtoMapper<AuthResponse, Token> {
    override fun mapToDomainModel(model: AuthResponse?): Token {
        if (model != null) {
            return Token(
                accessToken = model.accessToken,
                tokenType = model.tokenType,
                expiresIn = model.expiresIn
            )
        }
        return Token("", "", 0)
    }

    override fun mapFromDomainModel(domainModel: Token?): AuthResponse {
        TODO("Not yet implemented")
    }
}