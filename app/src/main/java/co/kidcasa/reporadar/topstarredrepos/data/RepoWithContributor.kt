/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.topstarredrepos.data

import com.squareup.moshi.JsonClass

/**
 * Data class representing a repository along with its top contributor.
 *
 * @property repo The repository item.
 * @property topContributor The top contributor to the repository, if available.
 */
@JsonClass(generateAdapter = true)
data class RepoWithContributor(
    val repo: Item,
    val topContributor: ContributorsResponseItem?
)
