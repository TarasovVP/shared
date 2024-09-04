import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.Res
import com.vnteam.architecturetemplates.data.APP_LANG_EN
import com.vnteam.architecturetemplates.data.APP_LANG_UK
import com.vnteam.architecturetemplates.ic_dark_mode
import com.vnteam.architecturetemplates.ic_light_mode
import com.vnteam.architecturetemplates.presentation.resources.LocalDefaultPadding
import com.vnteam.architecturetemplates.presentation.resources.LocalLargePadding
import com.vnteam.architecturetemplates.presentation.resources.LocalMediumTextSize
import com.vnteam.architecturetemplates.presentation.resources.LocalStringResources
import com.vnteam.architecturetemplates.presentation.resources.getStringResourcesByLocale
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import com.vnteam.architecturetemplates.presentation.states.screen.AppBarState
import com.vnteam.architecturetemplates.presentation.viewmodels.AppViewModel
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import presentation.components.SplashScreen
import theme.AppTheme

@Composable
fun App(appViewModel: AppViewModel) {
    val isDarkTheme = appViewModel.isDarkTheme.collectAsState()
    val language = appViewModel.language.collectAsState()
    CompositionLocalProvider(LocalStringResources provides getStringResourcesByLocale(language.value.orEmpty())) {
        isDarkTheme.value?.let {
            AppTheme(it) {
                AppContent(appViewModel, koinInject())
            }
        } ?: SplashScreen()
    }
}

@Composable
fun AppContent(appViewModel: AppViewModel, screenState: MutableState<ScreenState>) {
    LaunchedEffect(screenState.value.appMessageState.messageVisible) {
        if (screenState.value.appMessageState.messageVisible) {
            delay(2000)
            screenState.value = screenState.value.copy(
                appMessageState = screenState.value.appMessageState.copy(messageVisible = false, messageText = "")
            )
        }
    }
    Surface {
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppBar(appViewModel, screenState.value.appBarState)
                Box(modifier = Modifier.fillMaxWidth(0.8f)) {
                    AppNavigation(koinInject())
                }
            }
            if (screenState.value.floatingActionState.floatingActionButtonVisible) {
                ExtendedFloatingActionButton(
                    onClick = { screenState.value.floatingActionState.floatingActionButtonAction() },
                    content = { Text(screenState.value.floatingActionState.floatingActionButtonTitle) },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = Color.White,
                    modifier = Modifier
                        .padding(LocalLargePadding.current.size)
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth().padding(
                    horizontal = LocalDefaultPadding.current.size,
                    vertical = LocalLargePadding.current.size
                ),
                contentAlignment = Alignment.Center
            ) {
                if (screenState.value.appMessageState.messageVisible) {
                    Snackbar(
                        containerColor = if (screenState.value.appMessageState.isMessageError) Color.Red else Color.Green
                    ) {
                        Text(text = screenState.value.appMessageState.messageText)
                    }
                }
            }
            if (screenState.value.isProgressVisible) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun AppBar(appViewModel: AppViewModel, appBarState: AppBarState) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = appBarState.appBarTitle,
            fontSize = LocalMediumTextSize.current.textSize,
            color = Color.White,
            modifier = Modifier.padding(LocalLargePadding.current.size).weight(1f)
        )
        Row(modifier = Modifier.padding(horizontal = LocalLargePadding.current.size)) {
            IconButton(onClick = {
                appViewModel.setLanguage(if (appViewModel.language.value == APP_LANG_EN) APP_LANG_UK else APP_LANG_EN)
            }) {
                Text(
                    if (appViewModel.language.value == APP_LANG_EN) APP_LANG_UK else APP_LANG_EN,
                    color = Color.White
                )
            }
            IconButton(onClick = {
                appViewModel.setIsDarkTheme(appViewModel.isDarkTheme.value != true)
            }) {
                Icon(
                    painter = painterResource(if (appViewModel.isDarkTheme.value == true) Res.drawable.ic_light_mode else Res.drawable.ic_dark_mode),
                    contentDescription = if (appViewModel.isDarkTheme.value == true) "Switch to Light Theme" else "Switch to Dark Theme",
                    tint = Color.White
                )
            }
        }
    }
}