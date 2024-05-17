/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.api

import co.kidcasa.reporadar.topstarredrepos.data.ContributorsResponse
import co.kidcasa.reporadar.topstarredrepos.data.ContributorsResponseItem
import co.kidcasa.reporadar.topstarredrepos.data.TopStarredReposResponse

/**
 * Interface for GitHub repository operations.
 */
interface GitHubRepository {

    /**
     * Get the list of contributors for a given repository.
     *
     * @param owner The owner of the repository.
     * @param repo The name of the repository.
     * @return A [ContributorsResponse] containing the list of contributors.
     */
    suspend fun getContributors(owner: String, repo: String): List<ContributorsResponseItem>

    /**
     * Get a list of 100 most starred GitHub repositories.
     *
     * @return A [TopStarredReposResponse] containing the list of repositories.
     */
    suspend fun getTopStarredRepos(): TopStarredReposResponse

}
