package com.uzun.pseudosendy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormScreen
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PseudoSendyTheme {
                OrderFormScreen()
            }
        }
    }
}