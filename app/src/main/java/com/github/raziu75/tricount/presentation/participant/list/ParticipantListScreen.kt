package com.github.raziu75.tricount.presentation.participant.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import com.github.raziu75.tricount.presentation.participant.list.composable.AddParticipantBottomSheet
import com.github.raziu75.tricount.presentation.participant.list.composable.ParticipantItem
import com.github.raziu75.tricount.presentation.participant.list.state.UiState

@Preview(showBackground = true)
@Composable
private fun ParticipantListScreenPreview() {
    MaterialTheme {
        ParticipantListScreen(
            modifier = Modifier.fillMaxSize(),
            state = UiState(
                participantList = emptyList()
            ),
            onAddParticipantFabClick = {},
            onAddParticipantNameInputChange = {},
            onAddParticipantSubmitClick = {},
            onAddParticipantDismiss = {},
            onDeleteParticipantClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun ParticipantListScreen(
    state: UiState,
    onAddParticipantFabClick: () -> Unit,
    onAddParticipantNameInputChange: (String) -> Unit,
    onAddParticipantSubmitClick: () -> Unit,
    onAddParticipantDismiss: () -> Unit,
    onDeleteParticipantClick: (Participant) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.participant_list_title),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
            )

            if (state.participantList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(id = R.string.participant_list_participants_empty),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(top = 32.dp),
                ) {
                    items(state.participantList) { item ->
                        ParticipantItem(
                            modifier = modifier.fillMaxWidth(),
                            participant = item,
                            onDeleteClick = onDeleteParticipantClick,
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = onAddParticipantFabClick,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.BottomCenter)
        ) {
            Icon(Icons.Filled.Add, contentDescription = null)
        }

        if (state.addParticipantBottomSheetVisible) {
            AddParticipantBottomSheet(
                modifier = Modifier.fillMaxWidth(),
                nameValue = state.nameValue,
                submitButtonEnabled = state.addParticipantSubmitButtonEnabled,
                onNameChange = onAddParticipantNameInputChange,
                onAddButtonClick = onAddParticipantSubmitClick,
                onDismiss = onAddParticipantDismiss,
            )
        }
    }
}
