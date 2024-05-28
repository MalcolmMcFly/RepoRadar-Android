/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.api

/**
 * Represents the state of a UI operation.
 *
 * This sealed class is used to represent various states of a UI operation,
 * such as loading, success, error, and network error.
 *
 * @param T The type of data associated with the success state.
 */
sealed class UIState<out T> {

    /**
     * Represents the loading state of a UI operation.
     *
     * This state indicates that a background process is currently running,
     * such as a network request or a database operation.
     */
    object Loading : UIState<Nothing>()

    /**
     * Represents the idle state of a UI operation.
     *
     * This state indicates that the UI is currently idle and not performing
     * any background operations.
     */
    object Idle : UIState<Nothing>()

    /**
     * Represents the success state of a UI operation.
     *
     * This state indicates that a background operation completed successfully
     * and provides the resulting data.
     *
     * @param T The type of data associated with the success state.
     * @property data The data resulting from the successful operation.
     */
    data class Success<out T>(val data: T) : UIState<T>()

    /**
     * Represents the error state of a UI operation.
     *
     * This state indicates that a background operation encountered an error
     * and provides the exception associated with the error.
     *
     * @property exception The exception that caused the error state.
     */
    data class Error(val exception: Exception) : UIState<Nothing>()

    /**
     * Represents the network error state of a UI operation.
     *
     * This state indicates that a background operation encountered a network-related error,
     * such as a loss of internet connectivity.
     */
    object NetworkError : UIState<Nothing>()
}
