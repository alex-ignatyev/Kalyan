package screens.main.rating.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Tobacco
import ui.themes.KalyanTheme

@Composable
fun TobaccoView(tobacco: Tobacco) {
    Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.wrapContentWidth()) {
            Text(
                text = tobacco.company,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = KalyanTheme.colors.primaryText
            )
            Text(text = tobacco.taste, fontSize = 12.sp, color = KalyanTheme.colors.primaryText)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(
                text = "${(tobacco.aroma + tobacco.smokiness + tobacco.strength) / 3}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = KalyanTheme.colors.primaryText,
                textAlign = TextAlign.End
            )
            Image(imageVector = Icons.Filled.Star, "", colorFilter = ColorFilter.tint(Color.White))
        }

    }
}
