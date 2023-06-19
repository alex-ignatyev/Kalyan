package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kalyan.shared.images.AppResImages
import com.kalyan.shared.strings.AppResStrings
import io.github.skeptick.libres.compose.painterResource
import ui.KalyanTheme
import utils.EMPTY

@Composable
fun KalyanSearch(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = KalyanTheme.typography.hint,
            modifier = Modifier
                .background(color = KalyanTheme.colors.surfaceVariant.copy(alpha = 0.4f), shape = RoundedCornerShape(8.dp))
                .weight(1f)
                .height(46.dp),
            placeholder = { Text(text = AppResStrings.text_search, style = KalyanTheme.typography.hint) },
            trailingIcon = {
                if (value.isNotBlank()) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null, modifier = Modifier.size(16.dp).clickable {
                        onValueChange(EMPTY)
                    })
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                textColor = KalyanTheme.colors.primaryText,
                cursorColor = KalyanTheme.colors.surfaceVariantOn
            )
        )

        //TODO сделать фильтр адаптивным
        /*
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(46.dp)
                .border(
                    border = BorderStroke(1.dp, KalyanTheme.colors.surfaceVariant.copy(alpha = 0.4f)),
                    shape = RoundedCornerShape(8.dp)
                ).clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(painterResource(AppResImages.ic_filter), contentDescription = null, modifier = Modifier.size(16.dp))
        }
        */
    }
}
