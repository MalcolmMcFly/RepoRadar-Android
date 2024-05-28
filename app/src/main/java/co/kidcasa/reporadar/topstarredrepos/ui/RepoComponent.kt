/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.topstarredrepos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.kidcasa.reporadar.R
import co.kidcasa.reporadar.topstarredrepos.data.ContributorsResponseItem
import co.kidcasa.reporadar.topstarredrepos.data.Item
import co.kidcasa.reporadar.topstarredrepos.data.PreviewMocks
import co.kidcasa.reporadar.topstarredrepos.data.RepoWithContributor
import co.kidcasa.reporadar.ui.theme.RepoRadarTheme

/**
 * A composable function that displays a repository along with its top contributor.
 *
 * @param rank The rank of the repository.
 * @param repoWithContributor The repository and its top contributor.
 */
@Composable
fun RepoComponent(
    rank: Int,
    repoWithContributor: RepoWithContributor
) {
    RepoRadarTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            content = {
                Column(modifier = Modifier.padding(4.dp)) {
                    Text(text = stringResource(R.string.rank, rank + 1), fontSize = 16.sp)
                    Text(
                        text = stringResource(R.string.repo_name, repoWithContributor.repo.name),
                        fontSize = 16.sp
                    )
                    Text(
                        text = stringResource(
                            R.string.top_contributor,
                            repoWithContributor.topContributor?.login ?: ""
                        ), fontSize = 16.sp
                    )
                }
            }
        )
    }
}

/**
 * A preview function for [RepoComponent] to display it in Android Studio's preview.
 */
@Preview(showBackground = true)
@Composable
fun RepoComponentPreview() {
    RepoComponent(
        rank = 0,
        repoWithContributor = RepoWithContributor(
            repo = PreviewMocks.topStarredReposResponse.items.first(),
            topContributor = PreviewMocks.contributorsResponse
        )
    )
}
