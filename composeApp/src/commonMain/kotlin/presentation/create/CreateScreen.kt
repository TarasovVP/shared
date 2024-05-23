package presentation.create

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import com.vnteam.architecturetemplates.presentation.viewmodels.DetailsViewModel
import com.vnteam.architecturetemplates.presentation.states.DetailsViewState
import presentation.components.painterRes
import com.vnteam.architecturetemplates.presentation.resources.DrawableResources
import com.vnteam.architecturetemplates.presentation.resources.LocalLargeAvatarSize
import com.vnteam.architecturetemplates.presentation.resources.LocalLargePadding
import com.vnteam.architecturetemplates.presentation.resources.LocalMediumPadding
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import com.vnteam.architecturetemplates.presentation.viewmodels.viewModel

@Composable
fun CreateScreen(onClick: () -> Unit) {
    val viewModel = viewModel(DetailsViewModel::class)
    val viewState = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        //viewModel.processIntent(DetailsIntent.LoadFork(forkId ?: 0))
    }

    CreateContent(viewState.value, onClick)
}

@Composable
fun CreateContent(viewState: DetailsViewState, onClick: () -> Unit) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalLargePadding.current.size),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = viewState.fork?.name.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalLargePadding.current.size)
            )
            Text(
                text = viewState.fork?.owner?.login.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalLargePadding.current.size)
            )
            Text(
                text = "Description:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalLargePadding.current.size)
            )
            Text(
                text = viewState.fork?.description.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalLargePadding.current.size)
            )
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalLargePadding.current.size)
            ) {
                Text(text = getStringResources().BACK)
            }
            OwnerCard(
                avatarUrl = viewState.fork?.owner?.avatarUrl.orEmpty(),
                description = viewState.fork?.description.orEmpty()
            )
        }
        if (viewState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun OwnerCard(avatarUrl: String, description: String) {
    Card {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(
            LocalMediumPadding.current.size)) {
            SubcomposeAsyncImage(
                model = avatarUrl,
                contentDescription = getStringResources().OWNER_AVATAR,
                modifier = Modifier
                    .wrapContentSize()
                    .width(LocalLargeAvatarSize.current.size)
                    .height(LocalLargeAvatarSize.current.size),
                contentScale = ContentScale.Crop
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                    is AsyncImagePainter.State.Error -> Image(painter = painterRes(DrawableResources.IC_PERSON), contentDescription = null)
                    else -> SubcomposeAsyncImageContent()
                }
            }
            Text(
                text = description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalLargePadding.current.size)
            )
        }
    }
}
