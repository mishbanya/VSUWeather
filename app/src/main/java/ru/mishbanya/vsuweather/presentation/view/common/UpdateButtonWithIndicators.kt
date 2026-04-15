package ru.mishbanya.vsuweather.presentation.view.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.mishbanya.vsuweather.R

@Composable
fun UpdateButtonWithIndicators(
    isError: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.fillMaxWidth(0.25f))
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .weight(2f),
            enabled = !isLoading
        ) {
            Text(stringResource(R.string.update_btn))
        }
        Spacer(modifier = Modifier.width(5.dp))
        Row(
            modifier = Modifier.weight(1f)
        ) {
            if(isLoading){
                CircularProgressIndicator(modifier = Modifier.size(32.dp))
            }else if(isError){
                Icon(
                    painterResource(R.drawable.ic_error),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color.Red
                )
            } else{ Spacer(modifier = Modifier.fillMaxWidth()) }
        }
    }
}