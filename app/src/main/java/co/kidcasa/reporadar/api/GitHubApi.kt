/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.api

import co.kidcasa.reporadar.topstarredrepos.data.ContributorsResponse
import co.kidcasa.reporadar.topstarredrepos.data.ContributorsResponseItem
import co.kidcasa.reporadar.topstarredrepos.data.TopStarredReposResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface for accessing GitHub API endpoints related to repositories and contributors.
 */
interface GitHubApi {

    /**
     * Get a list of 100 most starred GitHub repositories.
     *
     * @param query The search query, defaults to "stars:>0" to find repositories with more than 0 stars.
     * @param sort The sorting criteria, defaults to "stars".
     * @param order The order of sorting, defaults to "desc" for descending.
     * @param perPage The number of repositories to return per page, defaults to 100.
     * @return A [TopStarredReposResponse] containing the list of the top starred repositories.
     *
     * GET https://api.github.com/search/repositories?q=stars:>0
     * Docs: https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-repositories
     */
    @GET("search/repositories")
    suspend fun getTopStarredRepos(
        @Query("q") query: String = "stars:>0",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc",
        @Query("per_page") perPage: Int = 100
    ): TopStarredReposResponse

    /**
     * Get the list of contributors for a given repository.
     *
     * @param owner The owner of the repository.
     * @param repo The name of the repository.
     * @return A [ContributorsResponse] with a list of contributors for the specified repository.
     *
     * GET https://api.github.com/repos/{owner}/{repo}/contributors
     * Docs: https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repository-contributors
     */
    @GET("repos/{owner}/{repo}/contributors")
    suspend fun getContributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<ContributorsResponseItem>
}
