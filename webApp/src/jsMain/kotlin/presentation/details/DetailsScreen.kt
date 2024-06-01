package presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.vnteam.architecturetemplates.presentation.intents.DetailsIntent
import com.vnteam.architecturetemplates.presentation.viewmodels.DetailsViewModel
import components.AppStyles
import components.BaseButton
import components.CircularProgress
import components.OwnerCard
import components.VerticalLayout
import kotlinx.browser.window
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.events.Event
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import com.vnteam.architecturetemplates.presentation.viewmodels.viewModel


@Composable
fun DetailsScreen(itemId: String) {
    Style(AppStyles)
    val viewModel = viewModel(DetailsViewModel::class)

    val detailsViewStateState = viewModel.state.collectAsState()
    val error = mutableStateOf(viewModel.state.value.infoMessage.value)

    LaunchedEffect(itemId) {
        viewModel.processIntent(DetailsIntent.LoadFork(itemId.toLong()))
    }

    LaunchedEffect(detailsViewStateState.value) {
        error.value = viewModel.state.value.infoMessage.value
    }
    console.log("DetailsScreen ${detailsViewStateState.value.fork?.name}")
    VerticalLayout {
        Div(attrs = { classes(AppStyles.textStyle) }) { Text(detailsViewStateState.value.fork?.name.orEmpty()) }
        Div(attrs = { classes(AppStyles.textStyle) }) { Text(detailsViewStateState.value.fork?.owner?.login.orEmpty()) }
        OwnerCard(detailsViewStateState.value.fork?.owner)
        Div(attrs = { classes(AppStyles.textStyle) }) { Text("Description:") }
        Div(attrs = { classes(AppStyles.textStyle) }) { Text(detailsViewStateState.value.fork?.description.orEmpty()) }
        BaseButton(getStringResources().BACK) {
            window.history.pushState(null, "", "/list")
            window.dispatchEvent(Event("popstate"))
        }

        if (viewModel.state.value.isLoading) {
            CircularProgress()
        }
    }
}