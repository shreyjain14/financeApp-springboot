package com.shreyjain.financeApp.services

import com.shreyjain.financeApp.domain.models.ShareModel

interface ShareService {

    fun createShare(email: String): ShareModel
    fun findByEmail(email: String): ShareModel?
    fun addShares(email: String, share: String): ShareModel?
    fun removeShares(email: String, share: String): ShareModel?
    fun findShares(email: String): List<String>?


}