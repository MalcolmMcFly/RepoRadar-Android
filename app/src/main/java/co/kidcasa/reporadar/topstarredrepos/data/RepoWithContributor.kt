/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.topstarredrepos.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoWithContributor(
    val repo: Item,
    val topContributor: ContributorsResponseItem?
)
