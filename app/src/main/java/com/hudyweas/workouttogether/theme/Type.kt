

package com.hudyweas.workouttogether.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hudyweas.workouttogether.R

private val Inika = FontFamily(
  Font(R.font.inika_regular, FontWeight.Normal),
  Font(R.font.inika_bold, FontWeight.Bold)
)

val Typography = Typography(
    displayLarge = TextStyle(
      fontFamily = Inika,
      fontWeight = FontWeight.Bold,
      fontSize = 34.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Inika,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Inika,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Inika,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Inika,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Inika,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Inika,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Inika,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Inika,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    headlineLarge = TextStyle(
      fontFamily = Inika,
      fontWeight = FontWeight.Bold,
      fontSize = 20.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Inika,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Inika,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
      fontFamily = Inika,
      fontWeight = FontWeight.Bold,
      fontSize = 20.sp
    ),
      labelMedium = TextStyle(
          fontFamily = Inika,
          fontWeight = FontWeight.Bold,
          fontSize = 16.sp
      ),
      labelSmall = TextStyle(
          fontFamily = Inika,
          fontWeight = FontWeight.Bold,
          fontSize = 14.sp
      ),

)