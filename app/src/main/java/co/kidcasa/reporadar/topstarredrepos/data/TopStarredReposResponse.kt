/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.topstarredrepos.data

import com.squareup.moshi.JsonClass

/**
 * Data class representing the response for top starred repositories.
 *
 * @property incomplete_results Indicates whether the results are incomplete.
 * @property items The list of repository items.
 * @property total_count The total count of repositories found.
 */
@JsonClass(generateAdapter = true)
data class TopStarredReposResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)

/**
 * Data class representing a GitHub repository item.
 *
 * @property archive_url The URL to archive the repository.
 * @property archived Indicates if the repository is archived.
 * @property assignees_url The URL for fetching assignees.
 * @property blobs_url The URL for fetching blobs.
 * @property branches_url The URL for fetching branches.
 * @property clone_url The URL for cloning the repository.
 * @property collaborators_url The URL for fetching collaborators.
 * @property comments_url The URL for fetching comments.
 * @property commits_url The URL for fetching commits.
 * @property compare_url The URL for comparing commits.
 * @property contents_url The URL for fetching contents.
 * @property contributors_url The URL for fetching contributors.
 * @property created_at The creation date of the repository.
 * @property default_branch The default branch of the repository.
 * @property deployments_url The URL for fetching deployments.
 * @property description The description of the repository.
 * @property disabled Indicates if the repository is disabled.
 * @property downloads_url The URL for fetching downloads.
 * @property events_url The URL for fetching events.
 * @property fork Indicates if the repository is a fork.
 * @property forks The number of forks.
 * @property forks_count The count of forks.
 * @property forks_url The URL for fetching forks.
 * @property full_name The full name of the repository.
 * @property git_commits_url The URL for fetching git commits.
 * @property git_refs_url The URL for fetching git references.
 * @property git_tags_url The URL for fetching git tags.
 * @property git_url The git URL of the repository.
 * @property has_downloads Indicates if the repository has downloads.
 * @property has_issues Indicates if the repository has issues.
 * @property has_pages Indicates if the repository has GitHub pages.
 * @property has_projects Indicates if the repository has projects.
 * @property has_wiki Indicates if the repository has a wiki.
 * @property hooks_url The URL for fetching hooks.
 * @property html_url The HTML URL of the repository.
 * @property id The unique identifier of the repository.
 * @property issue_comment_url The URL for fetching issue comments.
 * @property issue_events_url The URL for fetching issue events.
 * @property issues_url The URL for fetching issues.
 * @property keys_url The URL for fetching keys.
 * @property labels_url The URL for fetching labels.
 * @property languages_url The URL for fetching languages.
 * @property merges_url The URL for fetching merges.
 * @property milestones_url The URL for fetching milestones.
 * @property name The name of the repository.
 * @property node_id The node ID of the repository.
 * @property notifications_url The URL for fetching notifications.
 * @property open_issues The number of open issues.
 * @property open_issues_count The count of open issues.
 * @property owner The owner of the repository.
 * @property private Indicates if the repository is private.
 * @property pulls_url The URL for fetching pull requests.
 * @property pushed_at The last push date of the repository.
 * @property releases_url The URL for fetching releases.
 * @property score The score of the repository.
 * @property size The size of the repository.
 * @property ssh_url The SSH URL of the repository.
 * @property stargazers_count The count of stargazers.
 * @property stargazers_url The URL for fetching stargazers.
 * @property statuses_url The URL for fetching statuses.
 * @property subscribers_url The URL for fetching subscribers.
 * @property subscription_url The URL for fetching subscriptions.
 * @property svn_url The SVN URL of the repository.
 * @property tags_url The URL for fetching tags.
 * @property teams_url The URL for fetching teams.
 * @property trees_url The URL for fetching trees.
 * @property updated_at The last update date of the repository.
 * @property url The URL of the repository.
 * @property visibility The visibility of the repository.
 * @property watchers The number of watchers.
 * @property watchers_count The count of watchers.
 */
@JsonClass(generateAdapter = true)
data class Item(
    val archive_url: String,
    val archived: Boolean,
    val assignees_url: String,
    val blobs_url: String,
    val branches_url: String,
    val clone_url: String,
    val collaborators_url: String,
    val comments_url: String,
    val commits_url: String,
    val compare_url: String,
    val contents_url: String,
    val contributors_url: String,
    val created_at: String,
    val default_branch: String,
    val deployments_url: String,
    val description: String,
    val disabled: Boolean,
    val downloads_url: String,
    val events_url: String,
    val fork: Boolean,
    val forks: Int,
    val forks_count: Int,
    val forks_url: String,
    val full_name: String,
    val git_commits_url: String,
    val git_refs_url: String,
    val git_tags_url: String,
    val git_url: String,
    val has_downloads: Boolean,
    val has_issues: Boolean,
    val has_pages: Boolean,
    val has_projects: Boolean,
    val has_wiki: Boolean,
    val hooks_url: String,
    val html_url: String,
    val id: Int,
    val issue_comment_url: String,
    val issue_events_url: String,
    val issues_url: String,
    val keys_url: String,
    val labels_url: String,
    val languages_url: String,
    val merges_url: String,
    val milestones_url: String,
    val name: String,
    val node_id: String,
    val notifications_url: String,
    val open_issues: Int,
    val open_issues_count: Int,
    val owner: Owner,
    val `private`: Boolean,
    val pulls_url: String,
    val pushed_at: String,
    val releases_url: String,
    val score: Int,
    val size: Int,
    val ssh_url: String,
    val stargazers_count: Int,
    val stargazers_url: String,
    val statuses_url: String,
    val subscribers_url: String,
    val subscription_url: String,
    val svn_url: String,
    val tags_url: String,
    val teams_url: String,
    val trees_url: String,
    val updated_at: String,
    val url: String,
    val visibility: String,
    val watchers: Int,
    val watchers_count: Int
)

/**
 * Data class representing the owner of a GitHub repository.
 *
 * @property avatar_url The URL of the owner's avatar image.
 * @property events_url The URL for fetching the owner's events.
 * @property followers_url The URL for fetching the owner's followers.
 * @property following_url The URL for fetching the users the owner is following.
 * @property gists_url The URL for fetching the owner's gists.
 * @property gravatar_id The Gravatar ID associated with the owner.
 * @property html_url The HTML URL of the owner's profile.
 * @property id The unique identifier of the owner.
 * @property login The username of the owner.
 * @property node_id The node ID of the owner.
 * @property organizations_url The URL for fetching the owner's organizations.
 * @property received_events_url The URL for fetching the events received by the owner.
 * @property repos_url The URL for fetching the owner's repositories.
 * @property site_admin Indicates if the owner is a site administrator.
 * @property starred_url The URL for fetching the repositories starred by the owner.
 * @property subscriptions_url The URL for fetching the repositories the owner is subscribed to.
 * @property type The type of the owner (e.g., User).
 * @property url The URL for fetching the owner's profile.
 */
@JsonClass(generateAdapter = true)
data class Owner(
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
)
