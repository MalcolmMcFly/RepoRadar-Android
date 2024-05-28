/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.topstarredrepos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import co.kidcasa.reporadar.api.UIState
import co.kidcasa.reporadar.topstarredrepos.data.PreviewMocks
import co.kidcasa.reporadar.ui.theme.RepoRadarTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import co.kidcasa.reporadar.R
import co.kidcasa.reporadar.topstarredrepos.data.Item
import co.kidcasa.reporadar.topstarredrepos.data.RepoWithContributor
import co.kidcasa.reporadar.ui.theme.Purple40

/**
 * A composable function that displays the top starred repositories along with their top contributors.
 *
 * @param topStarredReposState The state of the top starred repositories, wrapped in a [UIState].
 * @param onGetTopStarredRepos A callback function to fetch the top starred repositories.
 * @param launchSettings A callback function to launch the network settings.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopStarredReposScreen(
    topStarredReposState: State<UIState<List<RepoWithContributor>>>,
    onGetTopStarredRepos: () -> Unit,
    launchSettings: () -> Unit
) {
    val refreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(refreshing, onGetTopStarredRepos)

    LaunchedEffect(Unit) {
        onGetTopStarredRepos()
    }

    RepoRadarTheme {
        Box(
            modifier = Modifier
                .pullRefresh(pullRefreshState)
                .fillMaxSize()
                .background(color = Color.White),
        ) {
            Column {
                when (val state = topStarredReposState.value) {
                    is UIState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Purple40)
                        }
                    }

                    is UIState.Success -> {
                        LazyColumn(
                            modifier = Modifier.pullRefresh(pullRefreshState)
                        ) {
                            items(state.data.size) { index ->
                                RepoComponent(
                                    rank = index,
                                    repoWithContributor = state.data[index]
                                )
                            }
                        }
                        PullRefreshIndicator(
                            refreshing = refreshing,
                            state = pullRefreshState,
                            modifier = Modifier
                        )
                    }

                    is UIState.Error -> {
                        ErrorComponent(onRetry = onGetTopStarredRepos)
                    }

                    is UIState.NetworkError -> {
                        ErrorComponent(
                            title = stringResource(R.string.no_network),
                            buttonTitle = stringResource(R.string.settings),
                            onRetry = launchSettings,
                        )
                    }

                    else -> {
                        // Handle idle or other states if needed
                    }
                }
            }
        }
    }
}

/**
 * A preview function for [TopStarredReposScreen] to display it in Android Studio's preview.
 */
@Preview(showBackground = true)
@Composable
private fun TopStarredReposScreenPreview() {
    TopStarredReposScreen(
        topStarredReposState = remember {
            mutableStateOf(UIState.Success(PreviewMocks.topStarredReposResponse.items.map {
                RepoWithContributor(it, PreviewMocks.contributorsResponse)
            }))
        },
        onGetTopStarredRepos = {},
        launchSettings = {}
    )
}
