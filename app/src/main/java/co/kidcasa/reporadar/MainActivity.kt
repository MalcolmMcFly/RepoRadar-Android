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

/**
 * The main activity of the RepoRadar application.
 *
 * This activity sets up the content view using Jetpack Compose and displays the top starred
 * repositories screen. It also provides functionality to launch network settings.
 */
class MainActivity : ComponentActivity() {

    private val reposViewModel by viewModel<ReposViewModel>()

    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this contains the data it most recently supplied.
     */
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
     * Opens the internet settings screen.
     *
     * This function launches the system's Wi-Fi settings screen to allow the user to configure network settings.
     */
    private fun openNetworkSettings() {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        startActivity(intent)
    }
}
