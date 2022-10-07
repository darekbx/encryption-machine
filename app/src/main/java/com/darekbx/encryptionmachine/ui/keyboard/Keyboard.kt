package com.darekbx.encryptionmachine.ui.keyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darekbx.encryptionmachine.ui.keyboard.Keys.BACKSPACE
import com.darekbx.encryptionmachine.ui.keyboard.Keys.PRINT
import com.darekbx.encryptionmachine.ui.keyboard.Keys.SPACE

object Encryption {

    val definition = mapOf(
        'A' to 'Ż',
        'Ą' to 'Ź',
        'B' to 'Z',
        'C' to 'Y',
        'Ć' to 'W',
        'D' to 'U',
        'E' to 'T',
        'Ę' to 'Ś',
        'F' to 'S',
        'G' to 'R',
        'H' to 'P',
        'I' to 'Ó',
        'J' to 'O',
        'K' to 'Ń',
        'L' to 'N',
        'Ł' to 'M',
        'M' to 'Ł',
        'N' to 'L',
        'Ń' to 'K',
        'O' to 'J',
        'Ó' to 'I',
        'P' to 'H',
        'R' to 'G',
        'S' to 'F',
        'Ś' to 'Ę',
        'T' to 'E',
        'U' to 'D',
        'W' to 'Ć',
        'Y' to 'C',
        'Z' to 'B',
        'Ź' to 'Ą',
        'Ż' to 'A',

        '0' to '7',
        '1' to '6',
        '2' to '9',
        '3' to '8',
        '4' to '0',
        '5' to '2',
        '6' to '3',
        '7' to '4',
        '8' to '1',
        '9' to '5',
        '+' to '-',
        '-' to '*',
        '*' to '=',
        '=' to '+'
    )
}

object Keys {

    val BACKSPACE = '<';
    val SPACE = ' ';
    val PRINT = '#';

    val definition = arrayOf(
        arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '='),
        arrayOf('W', 'E', 'Ę', 'R', 'T', 'Y', 'U', 'I', 'O', 'Ó', 'P', PRINT),
        arrayOf('A', 'Ą', 'S', 'Ś', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Ł'),
        arrayOf('Z', 'Ż', 'Ź', 'C', 'Ć', 'B', 'N', 'Ń', 'M', BACKSPACE),
        arrayOf(SPACE),
    )
}

@Preview(device = Devices.NEXUS_10)
@Composable
fun Keyboard(
    modifier: Modifier = Modifier,
    onKeyPress: (Char) -> Unit = { },
    onBackSpace: () -> Unit = { },
    onPrint: () -> Unit = { },
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(Keys.definition) { row ->
                LazyRow(verticalAlignment = Alignment.CenterVertically) {
                    items(row) { char ->
                        when (char) {
                            BACKSPACE -> BackSpace(onClick = { onBackSpace() })
                            SPACE -> SpaceKey(Modifier.width(300.dp)) { onKeyPress(SPACE) }
                            PRINT -> PrintKey { onPrint() }
                            else -> Key(key = char, onClick = { onKeyPress(it) })
                        }
                    }
                }
            }
        }
    }
}
