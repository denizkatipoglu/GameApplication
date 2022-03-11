package com.example.baseKotlinApp.repository

import com.example.baseKotlinApp.base.BaseRepository
import com.example.baseKotlinApp.model.MakeUpItem
import com.example.baseKotlinApp.network.APIClientImpl
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class MakeUpRepository @Inject
constructor(private val apiServiceImpl: APIClientImpl) : BaseRepository() {

    suspend fun getMakeUps(
        scope: CoroutineScope,
        onSuccess: ((List<MakeUpItem>?) -> Unit),
        onErrorAction: ((String?) -> Unit)
    ) =
        sendRequest(
            scope = scope,
            client = { apiServiceImpl.apiCollect.getMakeUp() },
            onSuccess = {
                onSuccess(it)
            },
            onErrorAction = {
                onErrorAction(it)
            }
        )
}