package com.github.raziu75.tricount.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EuroSymbol
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R
import com.github.raziu75.tricount.presentation.common.compose.VerticalSpacer
import com.github.raziu75.tricount.presentation.home.composable.CountCard
import com.github.raziu75.tricount.presentation.home.state.UiState

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            state = UiState(participantCount = 4),
            navigateToParticipantList = {},
            navigateToTransactionList = {}
        )
    }
}

@Composable
fun HomeScreen(
    state: UiState,
    navigateToParticipantList: () -> Unit,
    navigateToTransactionList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
        )

        VerticalSpacer(space = 32.dp)

        CountCard(
            title = stringResource(id = R.string.home_participants_card_title),
            count = state.participantCount?.toString(),
            icon = Icons.Default.Person,
            onClick = navigateToParticipantList,
        )

        VerticalSpacer(space = 16.dp)

        CountCard(
            title = stringResource(id = R.string.home_transactions_card_title),
            count = state.totalExpensesInCents?.div(100f)?.toString(),
            icon = Icons.Default.EuroSymbol,
            onClick = navigateToTransactionList,
        )
    }
}
