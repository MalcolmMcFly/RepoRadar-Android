/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import co.kidcasa.reporadar.topstarredrepos.ui.TopStarredReposScreen
import co.kidcasa.reporadar.topstarredrepos.viewmodel.ReposViewModel
import co.kidcasa.reporadar.ui.theme.RepoRadarTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val reposViewModel by viewModel<ReposViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepoRadarTheme {
                TopStarredReposScreen(
                    topStarredReposState = reposViewModel.topStarredReposState.collectAsState(),
                    onGetTopStarredRepos = { reposViewModel.getTopStarredRepos() },
                    launchSettings = { openNetworkSettings() }
                )
            }
        }
    }

    /**
     * Open internet settings
     */
    private fun openNetworkSettings() {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        startActivity(intent)
    }
}
