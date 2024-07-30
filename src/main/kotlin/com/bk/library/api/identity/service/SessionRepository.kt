package com.bk.library.api.identity.service

import com.bk.library.api.identity.db.SessionDAO
import com.bk.library.api.identity.db.SessionTable
import com.bk.library.api.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greater
import org.jetbrains.exposed.sql.and
import java.util.UUID

internal interface SessionRepository {
    suspend fun getSession(username: String): String?
    suspend fun generateSession(username: String): String
    suspend fun isSessionValid(token: String): String?
}


internal class SessionRepositoryImpl(private val sessionValidity: Long) : SessionRepository {

    override suspend fun getSession(username: String): String? = suspendTransaction {
        SessionDAO.find((SessionTable.username eq username) and (SessionTable.validTill greater System.currentTimeMillis()))
            .map { it.token }.firstOrNull()
    }

    override suspend fun generateSession(username: String): String = suspendTransaction {
        val time = System.currentTimeMillis()
        val token = "${UUID.randomUUID()}~" + time
        SessionDAO.new {
            this.username = username
            this.token = token
            validTill = (time + sessionValidity)
            created = time
            updated = time
        }
        token
    }

    override suspend fun isSessionValid(token: String): String? = suspendTransaction {
        SessionDAO.find((SessionTable.token eq token and (SessionTable.validTill greater System.currentTimeMillis())))
            .firstOrNull()?.username
    }

}