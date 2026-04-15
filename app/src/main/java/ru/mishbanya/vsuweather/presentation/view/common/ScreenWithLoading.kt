package ru.mishbanya.vsuweather.presentation.view.common

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.mishbanya.vsuweather.R

@Composable
fun ColumnScope.ScreenWithLoading(
    isLoading: Boolean,
    content: (@Composable () -> Unit)?
) {
    content?.let {
        it()
    } ?: run {
        if(isLoading){
            Spacer(modifier = Modifier.weight(1f))
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.weight(1f))
        }else{
            Spacer(modifier = Modifier.weight(1f))
            Text(
                stringResource(R.string.is_error),
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}