package screens.main.rating.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
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
import com.kalyan.shared.images.AppResImages
import io.github.skeptick.libres.compose.painterResource
import ktor.getBaseUrl
import model.tobacco.TobaccoResponse
import ui.KalyanTheme
import ui.components.KalyanImage

@Composable
fun TobaccoView(tobaccoResponse: TobaccoResponse, position: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = position.toString(),
            style = KalyanTheme.typography.header,
            textAlign = TextAlign.Center
        )

        Card(modifier = Modifier.fillMaxWidth().padding(start = 16.dp), elevation = 8.dp) {
            Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {

                KalyanImage(getBaseUrl() + tobaccoResponse.image, modifier = Modifier.padding(8.dp)) //TODO Перенести в маппинг

                Column(modifier = Modifier.wrapContentWidth()) {
                    Text(
                        text = tobaccoResponse.taste,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = KalyanTheme.colors.primaryText
                    )
                    Text(text = tobaccoResponse.company, fontSize = 12.sp, color = KalyanTheme.colors.primaryText)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    RatingCard(tobaccoResponse.rating.toString(), Modifier.padding(end = 8.dp))

                    ViewsCard(tobaccoResponse.votes.toString(), Modifier.padding(end = 8.dp))
                }
            }
        }
    }
}

@Composable
fun RatingCard(rating: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = rating,
            style = KalyanTheme.typography.caption,
            color = KalyanTheme.colors.primaryText,
            textAlign = TextAlign.Center
        )

        Image(
            imageVector = Icons.Filled.Star,
            null,
            colorFilter = ColorFilter.tint(Color.Yellow),
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun ViewsCard(views: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = views,
            style = KalyanTheme.typography.caption,
            color = KalyanTheme.colors.primaryText,
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(AppResImages.ic_views),
            null,
            modifier = Modifier.size(16.dp)
        )
    }
}


