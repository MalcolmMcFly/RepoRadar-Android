/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Utility object for checking network connectivity status.
 */
object NetworkUtils {

    /**
     * Checks if internet is available.
     *
     * This function checks the network capabilities to determine if there is an active
     * network connection with internet access.
     *
     * @param context The application context.
     * @return True if there is an active network connection with internet access, false otherwise.
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
