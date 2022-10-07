package com.darekbx.encryptionmachine

import android.content.Context
import android.os.Bundle
import android.print.PrintManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darekbx.encryptionmachine.printing.PlainTextPrintDocumentAdapter
import com.darekbx.encryptionmachine.ui.keyboard.Encryption
import com.darekbx.encryptionmachine.ui.keyboard.Keyboard
import com.darekbx.encryptionmachine.ui.keyboard.Keys
import com.darekbx.encryptionmachine.ui.theme.EncryptionMachineTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EncryptionMachineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen { text -> print(text) }
                }
            }
        }
    }

    private fun print(text: String) {
        val printManager = getSystemService(Context.PRINT_SERVICE) as PrintManager
        val jobName = getString(R.string.app_name)
        printManager.print(jobName, PlainTextPrintDocumentAdapter(this, text), null)
    }
}

@Composable
fun MainScreen(onPrint: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var text by rememberSaveable { mutableStateOf("") }
        Row(
            modifier = Modifier
                .padding(start = 128.dp, end = 128.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(top = 32.dp, bottom = 32.dp),
                value = text,
                enabled = false,
                maxLines = 4,
                onValueChange = { text = it },
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                label = { Text("") })
        }

        Keyboard(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            onKeyPress = { key ->
                text +=
                    if (key == Keys.SPACE) key
                    else Encryption.definition[key]
            },
            onBackSpace = { text = text.dropLast(1) },
            onPrint = { onPrint(text) }
        )
    }
}


