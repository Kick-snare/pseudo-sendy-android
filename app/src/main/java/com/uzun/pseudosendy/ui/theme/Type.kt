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

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

@Immutable
data class SendyTypography(
    val XXXLBold : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    val XXXL : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    val XXLBold : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    val XXL : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    val XLBold : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    val XL : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    val LargeBold : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    val Large : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    val MediumBold : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    val Medium : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    val NormalBold : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    val Normal : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    val SmallBold : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    val Small : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    val XSBold : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp
    ),
    val XS : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    val XXSBold : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
    val XXS : TextStyle = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
)

val NotoSansKR = FontFamily(
    Font(R.font.notosanskr_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.notosanskr_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.notosanskr_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.notosanskr_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.notosanskr_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.notosanskr_thin, FontWeight.Thin, FontStyle.Normal),
)