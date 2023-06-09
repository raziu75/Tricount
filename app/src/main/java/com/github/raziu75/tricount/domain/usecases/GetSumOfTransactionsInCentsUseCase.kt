package com.github.raziu75.tricount.domain.usecases

import com.github.raziu75.tricount.data.TricountRepository
import javax.inject.Inject

class GetSumOfTransactionsInCentsUseCase
@Inject constructor(
    private val repository: TricountRepository
) {
    suspend operator fun invoke(): Int {
        return repository.getSumOfTransactionsInCents()
    }
}