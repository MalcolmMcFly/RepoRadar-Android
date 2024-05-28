/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.topstarredrepos.data

import com.squareup.moshi.JsonClass

/**
 * A response class representing a list of contributors for a GitHub repository.
 *
 * This class extends `ArrayList` to hold a list of `ContributorsResponseItem` objects.
 */
class ContributorsResponse : ArrayList<ContributorsResponseItem>()

/**
 * Data class representing an individual contributor to a GitHub repository.
 *
 * @property avatar_url The URL of the contributor's avatar image.
 * @property contributions The number of contributions made by the contributor.
 * @property events_url The URL for fetching the contributor's events.
 * @property followers_url The URL for fetching the contributor's followers.
 * @property following_url The URL for fetching the users the contributor is following.
 * @property gists_url The URL for fetching the contributor's gists.
 * @property gravatar_id The Gravatar ID associated with the contributor.
 * @property id The unique identifier of the contributor.
 * @property login The username of the contributor.
 * @property node_id The node ID of the contributor.
 * @property organizations_url The URL for fetching the contributor's organizations.
 * @property received_events_url The URL for fetching the events received by the contributor.
 * @property repos_url The URL for fetching the contributor's repositories.
 * @property site_admin Indicates if the contributor is a site administrator.
 * @property starred_url The URL for fetching the repositories starred by the contributor.
 * @property subscriptions_url The URL for fetching the repositories the contributor is subscribed to.
 * @property type The type of the contributor (e.g., User).
 * @property url The URL for fetching the contributor's profile.
 */
@JsonClass(generateAdapter = true)
data class ContributorsResponseItem(
    val avatar_url: String,
    val contributions: Int,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
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
