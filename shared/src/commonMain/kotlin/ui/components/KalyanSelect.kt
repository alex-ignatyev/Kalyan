package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.kalyan.shared.images.AppResImages
import io.github.skeptick.libres.compose.painterResource
import ui.KalyanTheme

@Composable
fun KalyanSelect(title: String, text: String = "", modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = modifier.fillMaxWidth()
        .height(64.dp)
        .padding(horizontal = 16.dp)
        .padding(top = 16.dp).clickable {
            onClick.invoke()
        }) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = if (text.isBlank()) KalyanTheme.typography.body else KalyanTheme.typography.hint,
                    color = KalyanTheme.colors.secondaryText
                )

                if (text.isNotBlank()) {
                    Text(text = text)
                }
            }

            Image(
                painter = painterResource(AppResImages.ic_next),
                contentDescription = null,
                colorFilter = ColorFilter.tint(KalyanTheme.colors.secondaryText),
                modifier = Modifier.size(16.dp)
            )
        }

        KalyanDivider(modifier.padding(top = 8.dp))
    }
}
