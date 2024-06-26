/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.topstarredrepos.data

import com.squareup.moshi.JsonClass

class ContributorsResponse : ArrayList<ContributorsResponseItem>()

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
