/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.topstarredrepos.viewmodel

import android.app.Application
import android.util.Log
import co.kidcasa.reporadar.api.GitHubRepository
import co.kidcasa.reporadar.api.UIState
import co.kidcasa.reporadar.testutils.MainDispatcherRule
import co.kidcasa.reporadar.topstarredrepos.data.ContributorsResponseItem
import co.kidcasa.reporadar.topstarredrepos.data.Item
import co.kidcasa.reporadar.topstarredrepos.data.Owner
import co.kidcasa.reporadar.topstarredrepos.data.TopStarredReposResponse
import co.kidcasa.reporadar.utils.NetworkUtils
import io.mockk.mockk
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockkStatic
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ReposViewModelTest : KoinTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // Dependencies
    private val application: Application = mockk(relaxed = true)
    private val gitHubRepository: GitHubRepository = mockk()

    // Mocked dependencies for Koin
    private val networkUtils: NetworkUtils = mockk()

    // Test class
    private lateinit var reposViewModel: ReposViewModel

    @Before
    fun setup() {
        stopKoin() // Stop the previous instance of Koin
        startKoin {
            modules(module {
                single { application }
                single { gitHubRepository }
                single { networkUtils }
            })
        }

        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0
        every { networkUtils.isNetworkAvailable(any()) } returns true

        reposViewModel = ReposViewModel(application, gitHubRepository, networkUtils)
    }

    @Test
    fun `getTopStarredRepos should update state to Success with data`() = runTest {
        // Given
        val mockRepo = Item(
            id = 1,
            node_id = "node_id",
            name = "repo_name",
            full_name = "full_name",
            owner = Owner(
                login = "owner_login",
                id = 1,
                node_id = "node_id",
                avatar_url = "avatar_url",
                gravatar_id = "gravatar_id",
                url = "url",
                events_url = "events_url",
                followers_url = "followers_url",
                following_url = "following_url",
                gists_url = "gists_url",
                html_url = "html_url",
                organizations_url = "organizations_url",
                received_events_url = "received_events_url",
                repos_url = "repos_url",
                site_admin = false,
                starred_url = "starred_url",
                subscriptions_url = "subscriptions_url",
                type = "User"
            ),
            private = false,
            html_url = "html_url",
            description = "description",
            fork = false,
            url = "url",
            archive_url = "archive_url",
            assignees_url = "assignees_url",
            blobs_url = "blobs_url",
            branches_url = "branches_url",
            collaborators_url = "collaborators_url",
            comments_url = "comments_url",
            commits_url = "commits_url",
            compare_url = "compare_url",
            contents_url = "contents_url",
            contributors_url = "contributors_url",
            deployments_url = "deployments_url",
            downloads_url = "downloads_url",
            events_url = "events_url",
            forks_url = "forks_url",
            git_commits_url = "git_commits_url",
            git_refs_url = "git_refs_url",
            git_tags_url = "git_tags_url",
            git_url = "git_url",
            issue_comment_url = "issue_comment_url",
            issue_events_url = "issue_events_url",
            issues_url = "issues_url",
            keys_url = "keys_url",
            labels_url = "labels_url",
            languages_url = "languages_url",
            merges_url = "merges_url",
            milestones_url = "milestones_url",
            notifications_url = "notifications_url",
            open_issues = 5,
            open_issues_count = 5,
            pulls_url = "pulls_url",
            pushed_at = "pushed_at",
            releases_url = "releases_url",
            score = 1,
            size = 123,
            ssh_url = "ssh_url",
            stargazers_count = 50,
            stargazers_url = "stargazers_url",
            statuses_url = "statuses_url",
            subscribers_url = "subscribers_url",
            subscription_url = "subscription_url",
            svn_url = "svn_url",
            tags_url = "tags_url",
            teams_url = "teams_url",
            trees_url = "trees_url",
            updated_at = "updated_at",
            visibility = "visibility",
            watchers = 100,
            watchers_count = 100,
            archived = true,
            clone_url = "clone_url",
            created_at = "crated_at",
            default_branch = "default_branch",
            disabled = true,
            has_pages = true,
            has_issues = true,
            has_projects = true,
            has_wiki = true,
            has_downloads = true,
            forks = 1,
            hooks_url = "hooks_url",
            forks_count = 1,
        )

        val topStarredReposResponse = TopStarredReposResponse(
            incomplete_results = false,
            items = listOf(mockRepo),
            total_count = 1
        )

        coEvery { gitHubRepository.getTopStarredRepos() } returns topStarredReposResponse
        coEvery { gitHubRepository.getContributors(any(), any()) } returns listOf(
            ContributorsResponseItem(
                login = "login",
                id = 1,
                node_id = "node_id",
                avatar_url = "avatar_url",
                gravatar_id = "",
                url = "url",
                followers_url = "followers_url",
                following_url = "following_url",
                gists_url = "gists_url",
                starred_url = "starred_url",
                subscriptions_url = "subscriptions_url",
                organizations_url = "organizations_url",
                repos_url = "repos_url",
                events_url = "events_url",
                received_events_url = "received_events_url",
                type = "User",
                site_admin = false,
                contributions = 100
            )
        )

        // When
        reposViewModel.getTopStarredRepos()

        // Then
        val result = reposViewModel.topStarredReposState.first()
        assert(result is UIState.Success)
        val successResult = result as UIState.Success
        assertEquals(1, successResult.data.size)
        assertEquals(mockRepo, successResult.data[0].repo)
        assertEquals("login", successResult.data[0].topContributor?.login)
    }

    @Test
    fun `getTopStarredRepos should update state to Error on failure`() = runTest {
        // Given
        val exception = RuntimeException("Network error")
        coEvery { gitHubRepository.getTopStarredRepos() } throws exception

        // When
        reposViewModel.getTopStarredRepos()

        // Then
        val result = reposViewModel.topStarredReposState.first()
        assert(result is UIState.Error)
        val errorResult = result as UIState.Error
        assertEquals(exception, errorResult.exception)
    }
}
