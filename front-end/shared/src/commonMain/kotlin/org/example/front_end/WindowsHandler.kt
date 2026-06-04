package org.example.front_end

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun WindowsHandler() {
    var currentScreen by remember { mutableStateOf(Windows.LOGIN)}

    when (currentScreen) {
        Windows.LOGIN -> LoginWindow(
            onLoginSuccess = {
                currentScreen = Windows.IMPORT
            }
        )

        Windows.IMPORT -> LocalDataImportWindow(
            onNavBarSwitch = {
                currentScreen = Windows.EXPORT
            }
        )

        Windows.EXPORT -> ExportToServerWindow(
            onNavBarSwitch = {
                currentScreen = Windows.IMPORT
            }
        )
    }
}

/**
 * Contains all the differents screens of Shanoir Uploader (do not include pop-up)
 */
enum class Windows {
    LOGIN,
    IMPORT,
    EXPORT,
}