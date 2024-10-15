package com.shreyjain.financeApp.services.impl

import com.shreyjain.financeApp.domain.models.ShareModel
import com.shreyjain.financeApp.repository.ShareRepository
import com.shreyjain.financeApp.services.ShareService
import org.springframework.stereotype.Service

@Service
class ShareServiceImpl(
    private val shareRepository: ShareRepository
) : ShareService {

    override fun createShare(email: String): ShareModel =
        shareRepository.save(ShareModel(email, mutableListOf()))

    override fun findByEmail(email: String): ShareModel? =
        shareRepository.findByEmail(email)

    override fun addShares(email: String, share: String): ShareModel? {
        val shareModel = shareRepository.findByEmail(email) ?: return null
        shareModel.allowedUsers.add(share)
        return shareRepository.save(shareModel)
    }

    override fun removeShares(email: String, share: String): ShareModel? {
        val shareModel = shareRepository.findByEmail(email) ?: return null
        shareModel.allowedUsers.remove(share)
        return shareRepository.save(shareModel)
    }

    override fun findShares(email: String): List<String>? =
        shareRepository.findByEmail(email)?.allowedUsers

}