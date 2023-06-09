package com.github.raziu75.tricount.presentation.participant.list.state

import com.github.raziu75.tricount.domain.model.Transaction.Participant

data class UiState(
    val participantList: List<Participant> = emptyList(),
    val nameValue: String = "",
    val addParticipantBottomSheetVisible: Boolean = false,
    val addParticipantSubmitButtonEnabled: Boolean = false,
)
