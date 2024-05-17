/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.api

/**
 * Represents the state of a UI operation.
 */
sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    object Idle : UIState<Nothing>()
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val exception: Exception) : UIState<Nothing>()
    object NetworkError : UIState<Nothing>()
}
