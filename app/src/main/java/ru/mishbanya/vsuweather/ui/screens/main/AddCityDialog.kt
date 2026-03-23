package ru.mishbanya.vsuweather.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ru.mishbanya.vsuweather.R
import ru.mishbanya.vsuweather.domain.vm.MainScreenViewModel

@Composable
fun AddCityDialog(
    onExit: () -> Unit,
    mainScreenViewModel: MainScreenViewModel
) {
    var cityName by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onExit
    ) {
        Surface() {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text(
                    text = stringResource(R.string.add_city),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = cityName,
                    onValueChange = {cityName = it},
                    isError = cityName.isBlank()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onExit,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.cancel_btn))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            mainScreenViewModel.addCity(cityName)
                            onExit()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.confirm_btn))
                    }
                }
            }
        }
    }
}