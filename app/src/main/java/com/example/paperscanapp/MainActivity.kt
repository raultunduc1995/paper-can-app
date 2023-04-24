package com.example.paperscanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.paperscanapp.ui.theme.PaperScanAppTheme
import com.example.paperscanapp.ui.utils.PaperScanAppPreview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaperScanAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val titleTextResId by remember(uiState.isEditModeEnabled) {
        derivedStateOf {
            if (uiState.isEditModeEnabled) R.string.organize_title else R.string.app_name
        }
    }

    Main(
        modifier = modifier,
        title = stringResource(id = titleTextResId),
        isEditModelEnabled = uiState.isEditModeEnabled,
        onActionButtonClicked = viewModel::toggleEditMode
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(
    modifier: Modifier = Modifier,
    title: String,
    isEditModelEnabled: Boolean,
    onActionButtonClicked: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopAppBar(
                title = title,
                isEditModelEnabled = isEditModelEnabled,
                onActionButtonClicked = onActionButtonClicked
            )
        }
    ) { _ ->
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    isEditModelEnabled: Boolean,
    onActionButtonClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            TextButton(
                modifier = Modifier.animateContentSize(),
                onClick = onActionButtonClicked,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                AnimatedVisibility(visible = isEditModelEnabled) {
                    Text(
                        text = stringResource(id = R.string.done_action),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                AnimatedVisibility(visible = !isEditModelEnabled) {
                    Text(
                        text = stringResource(id = R.string.organize_action),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@PaperScanAppPreview
@Composable
fun MainScreenPreview() {
    PaperScanAppTheme {
        Main(
            title = "PaperScan",
            isEditModelEnabled = true,
        )
    }
}