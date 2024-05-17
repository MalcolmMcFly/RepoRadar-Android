/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.topstarredrepos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.kidcasa.reporadar.R
import co.kidcasa.reporadar.ui.theme.Pink40
import co.kidcasa.reporadar.ui.theme.RepoRadarTheme
import co.kidcasa.reporadar.ui.theme.defaultButtonHeight

@Composable
fun ErrorComponent(
    title: String = stringResource(R.string.error_screen_title),
    buttonTitle: String = stringResource(R.string.retry),
    onRetry: () -> Unit,
) {
    RepoRadarTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = title, fontSize = 16.sp, color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier
                        .height(defaultButtonHeight)
                        .align(
                            Alignment.CenterHorizontally
                        ),
                    onClick = { onRetry() },
                    colors = ButtonColors(
                        containerColor = Pink40,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    ),
                ) {
                    Text(text = buttonTitle, fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorComponentPreview() {
    ErrorComponent(onRetry = {})
}
