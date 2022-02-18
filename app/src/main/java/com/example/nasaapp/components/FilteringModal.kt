package com.example.nasaapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilteringModal(dialogState: MutableState<Boolean>, solValue: String, roverValue: String, onValueChange: (String) -> Unit, getPhotos: (String, String) -> Unit, updateSol: (String) -> Unit, viewModelSol: String,
                   viewModelRover: String, options: List<String>, onOptionClick: (String) -> Unit, updateRover: (String) -> Unit) {
    AlertDialog(
        onDismissRequest = {
            dialogState.value = false
            onValueChange(viewModelSol)
            onOptionClick(viewModelRover)
        },
        title = {
            Text(text = "Search preferences", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        },
        text = {
            Column {
                Text(text = "Rover name", modifier = Modifier.padding(bottom = 4.dp))
                Column(
                    Modifier
                        .selectableGroup()
                        .padding(bottom = 12.dp)) {
                    options.forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (text == roverValue),
                                    onClick = { onOptionClick(text) },
                                    role = Role.RadioButton
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == roverValue),
                                onClick = null,
                                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.body1.plus(TextStyle (color = MaterialTheme.colors.secondary)),
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = solValue,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = MaterialTheme.typography.body1.plus(TextStyle (color = MaterialTheme.colors.secondary)),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = onValueChange,
                    label = { Text("Sol") }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    dialogState.value = false
                    updateSol(solValue)
                    updateRover(roverValue)
                    getPhotos(roverValue, solValue)
                }
            ) {
                Text("CONFIRM")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    dialogState.value = false
                    onValueChange(viewModelSol)
                    onOptionClick(viewModelRover)
                }
            ) {
                Text("CANCEL")
            }
        }
    )
}