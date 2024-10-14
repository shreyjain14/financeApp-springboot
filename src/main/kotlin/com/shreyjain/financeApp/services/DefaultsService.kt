package com.shreyjain.financeApp.services

import com.shreyjain.financeApp.domain.models.DefaultsModel

interface DefaultsService {

    fun createDefaults(email: String): DefaultsModel
    fun findByEmail(email: String): DefaultsModel?
    fun updateByEmail(email: String, updatedDefaults: DefaultsModel): DefaultsModel
    fun addPayTo(email: String, payTo: String): DefaultsModel?
    fun addPayFrom(email: String, payFrom: String): DefaultsModel?
    fun removePayTo(email: String, payTo: String): DefaultsModel?
    fun removePayFrom(email: String, payFrom: String): DefaultsModel?
    fun findPayTo(email: String): List<String>?
    fun findPayFrom(email: String): List<String>?

}