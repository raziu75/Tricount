package com.github.raziu75.tricount.presentation.participant.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import com.github.raziu75.tricount.domain.usecases.AddParticipantUseCase
import com.github.raziu75.tricount.domain.usecases.DeleteParticipantUseCase
import com.github.raziu75.tricount.domain.usecases.FetchParticipantListUseCase
import com.github.raziu75.tricount.presentation.participant.list.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ParticipantListViewModel
@Inject constructor(
    private val addParticipantUseCase: AddParticipantUseCase,
    private val fetchParticipantListUseCase: FetchParticipantListUseCase,
    private val deleteParticipantUseCase: DeleteParticipantUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchParticipantList()
    }

    fun onNameInputChange(value: String) {
        val isInputValid = value.isNotBlank()

        _uiState.update {
            it.copy(
                addParticipantSubmitButtonEnabled = isInputValid,
                nameValue = value
            )
        }
    }

    fun onAddParticipantSubmitClick() {
        if (uiState.value.nameValue.isBlank()) return

        viewModelScope.launch {
            addParticipantUseCase(uiState.value.nameValue)
        }

        _uiState.update {
            it.copy(nameValue = "")
        }

        fetchParticipantList()
    }

    fun onDeleteParticipantClick(participant: Participant) {
        viewModelScope.launch {
            deleteParticipantUseCase(participant)

            _uiState.update {
                it.copy(
                    participantList =
                    it.participantList
                        .toMutableList()
                        .apply { remove(participant) }
                )
            }
        }
    }

    fun onAddParticipantFabClick() {
        _uiState.update {
            it.copy(addParticipantBottomSheetVisible = true)
        }
    }

    fun onAddParticipantDismiss() {
        _uiState.update {
            it.copy(addParticipantBottomSheetVisible = false)
        }
    }

    private fun fetchParticipantList() {
        viewModelScope.launch {
            val participantList = fetchParticipantListUseCase()

            _uiState.update {
                it.copy(participantList = participantList)
            }
        }
    }
}
