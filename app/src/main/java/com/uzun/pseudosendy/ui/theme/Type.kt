package com.uzun.pseudosendy.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzun.pseudosendy.R
import javax.annotation.concurrent.Immutable

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

@Immutable
data class SendyTypography(
    val XXXLBold: TextStyle,
    val XXXL: TextStyle,
    val XXLBold: TextStyle,
    val XXL: TextStyle,
    val XLBold: TextStyle,
    val XL: TextStyle,
    val LargeBold: TextStyle,
    val Large: TextStyle,
    val MediumBold: TextStyle,
    val Medium: TextStyle,
    val NormalBold: TextStyle,
    val Normal: TextStyle,
    val SmallBold: TextStyle,
    val Small: TextStyle,
    val XSBold: TextStyle,
    val XS: TextStyle,
    val XXSBold: TextStyle,
    val XXS: TextStyle,
)

val LocalSendyTypography = staticCompositionLocalOf {
    SendyTypography(
        XXXLBold = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        ),
        XXXL = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp
        ),
        XXLBold = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        ),
        XXL = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp
        ),
        XLBold = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        XL = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp
        ),
        LargeBold = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        ),
        Large = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        ),
        MediumBold = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        ),
        Medium = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        ),
        NormalBold = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        ),
        Normal = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        SmallBold = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        ),
        Small = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        XSBold = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        ),
        XS = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        ),
        XXSBold = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        ),
        XXS = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
    )
}

val NotoSansKR = FontFamily(
    Font(R.font.notosanskr_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.notosanskr_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.notosanskr_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.notosanskr_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.notosanskr_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.notosanskr_thin, FontWeight.Thin, FontStyle.Normal),
)