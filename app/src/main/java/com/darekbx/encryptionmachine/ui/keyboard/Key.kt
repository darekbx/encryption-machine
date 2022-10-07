package com.darekbx.encryptionmachine.ui.keyboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darekbx.encryptionmachine.R

@Preview
@Composable
fun Key(
    modifier: Modifier = Modifier,
    key: Char = 'A',
    onClick: (Char) -> Unit = { }
) {
    KeyBase(
        modifier = modifier,
        onClick = { onClick(key) }
    ) {
        Text(modifier = Modifier, text = "$key")
    }
}

@Preview
@Composable
fun PrintKey(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    KeyBase(
        modifier = modifier,
        onClick = { onClick() }
    ) {
        Icon(
            painterResource(id = R.drawable.ic_baseline_print_24),
            contentDescription = "print"
        )
    }
}

@Preview
@Composable
fun SpaceKey(
    modifier: Modifier = Modifier,
    onClick: (Char) -> Unit = { }
) {
    KeyBase(
        modifier = modifier,
        onClick = { onClick(' ') }
    ) {
        Text(modifier = Modifier, text = " ")
    }
}

@Preview
@Composable
fun BackSpace(
    modifier: Modifier = Modifier,
    onClick: (Char) -> Unit = { }
) {
    KeyBase(
        modifier = modifier,
        onClick = { onClick(' ') }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_keyboard_backspace),
            modifier = Modifier,
            contentDescription = ""
        )
    }
}

@Composable
private fun KeyBase(
    modifier: Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier
            .padding(4.dp)
            .defaultMinSize(minWidth = 10.dp, minHeight = 10.dp),
        contentPadding = PaddingValues(top = 14.dp, bottom = 14.dp, start = 24.dp, end = 24.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        onClick = { onClick() }
    ) {
        content()
    }
}