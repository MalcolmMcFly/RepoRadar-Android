/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.topstarredrepos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import co.kidcasa.reporadar.api.GitHubRepository
import co.kidcasa.reporadar.api.UIState
import co.kidcasa.reporadar.topstarredrepos.data.RepoWithContributor
import co.kidcasa.reporadar.utils.NetworkUtils
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

/**
 * ViewModel for managing and fetching repository and contributor data from GitHub.
 *
 * @property gitHubRepository The repository to fetch data from.
 */
class ReposViewModel(
    application: Application,
    private val gitHubRepository: GitHubRepository
) : AndroidViewModel(application), KoinComponent {

    private val _topStarredReposState =
        MutableStateFlow<UIState<List<RepoWithContributor>>>(UIState.Idle)
    val topStarredReposState: StateFlow<UIState<List<RepoWithContributor>>> get() = _topStarredReposState

    /**
     * Fetches the list of top starred repositories.
     */
    fun getTopStarredRepos() {
        viewModelScope.launch {

            if (!NetworkUtils.isNetworkAvailable(getApplication())) {
                _topStarredReposState.value = UIState.NetworkError
                return@launch
            }

            _topStarredReposState.value = UIState.Loading
            try {
                val response = gitHubRepository.getTopStarredRepos()
                Log.d(TAG, "Top starred repos fetched successfully")
                val reposWithContributors = response.items.map {
                    RepoWithContributor(it, null)
                }
                // Give top repos initially
                _topStarredReposState.value = UIState.Success(reposWithContributors)

                // Grab top contributor
                val updatedRepos = reposWithContributors.map { repoWithContributor ->
                    async {
                        try {
                            Log.d(TAG, "Now attempting to get contributors")
                            val contributors = gitHubRepository.getContributors(
                                owner = repoWithContributor.repo.owner.login,
                                repo = repoWithContributor.repo.name
                            )
                            val topContributor = contributors.maxByOrNull {
                                it.contributions
                            }
                            repoWithContributor.copy(topContributor = topContributor)
                        } catch (exception: Exception) {
                            // If it fails, atleast give back just the repos as is
                            Log.e(TAG, "Failed to get contributors")
                            exception.printStackTrace()
                            repoWithContributor
                        }
                    }
                }.awaitAll()

                // Update UI with top contributors
                _topStarredReposState.update {
                    if (it is UIState.Success) {
                        UIState.Success(updatedRepos)
                    } else {
                        it
                    }
                }
            } catch (exception: Exception) {
                Log.e(TAG, "Error fetching top starred repos", exception)
                _topStarredReposState.value = UIState.Error(exception)
            }
        }
    }

    companion object {
        private const val TAG = "ReposViewModel"
    }
}
