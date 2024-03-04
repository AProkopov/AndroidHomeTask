package com.antonprokopov.breedsfeed.usecase

import com.antonprokopov.breedsfeed.data.api.ApiService
import com.antonprokopov.breedsfeed.data.models.BreedListResponse
import com.antonprokopov.breedsfeed.data.models.BreedsListModel
import com.antonprokopov.breedsfeed.di.BreedsFeedScope
import com.antonprokopov.core.data.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@BreedsFeedScope
class BreedsUseCase @Inject constructor(private val apiService: ApiService) {

    suspend fun execute(): Flow<Resource<BreedsListModel>> {
        return flow {
            emit(Resource.newLoading())

            val result = try {
                Resource.newSuccess(getBreedsList())
            } catch (e: Exception) {
                Resource.newError()
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun getBreedsList(): BreedsListModel {
        var breeds: Deferred<BreedListResponse>
        val breedsList: BreedsListModel

        coroutineScope {
            breeds = async { apiService.getBreeds() }
            breeds.await()

            breedsList = breeds.getCompleted().toBreedsListModel()
        }

        return breedsList
    }
}