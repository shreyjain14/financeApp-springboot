package com.shreyjain.financeApp.services.impl

import com.shreyjain.financeApp.domain.models.DefaultsModel
import com.shreyjain.financeApp.repository.DefaultsRepository
import com.shreyjain.financeApp.services.DefaultsService
import org.springframework.stereotype.Service

@Service
class DefaultsServiceImpl(
    private val defaultsRepository: DefaultsRepository
) : DefaultsService {

    override fun createDefaults(email: String): DefaultsModel {
        return defaultsRepository.save(
            DefaultsModel(
                email = email,
                payedTo = mutableListOf(),
                payedFrom = mutableListOf()
            )
        )
    }

    override fun findByEmail(email: String): DefaultsModel? {
        return defaultsRepository.findByEmail(email)
    }

    override fun updateByEmail(email: String, updatedDefaults: DefaultsModel): DefaultsModel {
        val existingDefaults = defaultsRepository.findByEmail(email)
            ?: throw Error("No defaults found for email: $email")

        // Update the existing entity with new values
        existingDefaults.payedTo.clear()
        existingDefaults.payedTo.addAll(updatedDefaults.payedTo)
        existingDefaults.payedFrom.clear()
        existingDefaults.payedFrom.addAll(updatedDefaults.payedFrom)

        // Save the updated entity
        return defaultsRepository.save(existingDefaults)
    }

    override fun addPayTo(email: String, payTo: String): DefaultsModel? {
        val defaultsModel = defaultsRepository.findByEmail(email) ?: return null
        defaultsModel.payedTo.add(payTo)
        return updateByEmail(email, defaultsModel)
    }

    override fun addPayFrom(email: String, payFrom: String): DefaultsModel? {
        val defaultsModel = defaultsRepository.findByEmail(email) ?: return null
        defaultsModel.payedFrom.add(payFrom)
        return updateByEmail(email, defaultsModel)
    }

    override fun removePayTo(email: String, payTo: String): DefaultsModel? {
        val defaultsModel = defaultsRepository.findByEmail(email) ?: return null
        if (defaultsModel.payedTo.contains(payTo))
            defaultsModel.payedTo.remove(payTo)
        return updateByEmail(email, defaultsModel)
    }

    override fun removePayFrom(email: String, payFrom: String): DefaultsModel? {
        val defaultsModel = defaultsRepository.findByEmail(email) ?: return null
        if (defaultsModel.payedFrom.contains(payFrom))
            defaultsModel.payedFrom.remove(payFrom)
        return updateByEmail(email, defaultsModel)
    }

    override fun findPayTo(email: String): List<String>? {
        return defaultsRepository.findByEmail(email)?.payedTo
    }

    override fun findPayFrom(email: String): List<String>? {
        return defaultsRepository.findByEmail(email)?.payedFrom
    }

}