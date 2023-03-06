package com.uzun.pseudosendy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme
import com.uzun.pseudosendy.ui.theme.SendyTypography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val localSendyTypography = staticCompositionLocalOf { SendyTypography() }

            CompositionLocalProvider(
                localSendyTypography provides SendyTypography()
            ) {
                PseudoSendyTheme {
                    OrderFormScreen()
                }
            }
        }
    }
}
