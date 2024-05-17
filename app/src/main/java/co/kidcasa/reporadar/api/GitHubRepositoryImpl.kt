/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.api

import co.kidcasa.reporadar.topstarredrepos.data.ContributorsResponse
import co.kidcasa.reporadar.topstarredrepos.data.ContributorsResponseItem
import co.kidcasa.reporadar.topstarredrepos.data.TopStarredReposResponse

/**
 * Implementation of [GitHubRepository] that uses [GitHubApi] to fetch data.
 *
 * @param gitHubApi The GitHub API service.
 */
class GitHubRepositoryImpl(
    private val gitHubApi: GitHubApi,
) : GitHubRepository {

    /**
     * Get the list of contributors for a given repository.
     *
     * @param owner The owner of the repository.
     * @param repo The name of the repository.
     * @return A [ContributorsResponse] containing the list of contributors.
     */
    override suspend fun getContributors(
        owner: String,
        repo: String
    ): List<ContributorsResponseItem> {
        return gitHubApi.getContributors(
            owner = owner,
            repo = repo
        )
    }

    /**
     * Get a list of 100 most starred GitHub repositories.
     *
     * @return A [TopStarredReposResponse] containing the list of repositories.
     */
    override suspend fun getTopStarredRepos(): TopStarredReposResponse {
        return gitHubApi.getTopStarredRepos()
    }
}
