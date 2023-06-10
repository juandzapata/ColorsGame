package com.juanza.colors

import com.juanza.colors.ui.theme.ColorsTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp


class MainActivity3 : ComponentActivity() {
    private var colorsGame: ColorsGame? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            myUI()
        }
    }

    @Composable
    fun myUI() {

        var sliderRed by remember {mutableStateOf(128f)}
        var sliderGreen by remember {mutableStateOf(128f)}
        var sliderBlue by remember {mutableStateOf(128f)}
        var randomColor by remember {
            mutableStateOf(Color.White)
        }
        var targetBackColor by remember { mutableStateOf(Color(0xFF808080)) }
        var proposedBackColor by remember { mutableStateOf(Color(0xFF6699CC)) }

        Column(
            modifier = Modifier.padding(Dp(10f))
        ) {

            Row(
                modifier = Modifier.weight(2f)
            ) {
                colorSection(targetBackColor, proposedBackColor)
            }
            sliderSection(title = stringResource(R.string.Red),
                color = Color.Red,
                value = sliderRed,
                onValueChange = {newValue ->
                    sliderRed = newValue
                    proposedBackColor = Color(sliderRed.toInt(),sliderGreen.toInt(),sliderBlue.toInt())
                }
            )
            sliderSection(title = stringResource(R.string.Green),
                color = Color.Green,
                value = sliderGreen,
                onValueChange = {newValue ->
                    sliderGreen = newValue
                    proposedBackColor = Color(sliderRed.toInt(),sliderGreen.toInt(),sliderBlue.toInt())
                }
            )
            sliderSection(title = stringResource(R.string.Blue),
                color = Color.Blue,
                value = sliderBlue,
                onValueChange = {newValue ->
                    sliderBlue = newValue
                    proposedBackColor = Color(sliderRed.toInt(),sliderGreen.toInt(),sliderBlue.toInt())
                }
            )
            buttonSection()

        }
    }

    @Composable
    fun buttonSection(

    ){
        Column(
            modifier = Modifier

        ) {
            Row(
            ){
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(166, 223, 158)
                    )
                ){
                    Text(text = stringResource(R.string.New))
                }
            }
            Row(
            ){
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(157, 180, 222)
                    )
                ){
                    Text(text = stringResource(R.string.Score))
                }
            }
        }

    }

    @Composable
    fun colorSection(
        targetColor: Color,
        proposedColor: Color
    ){
        Row(
            modifier = Modifier
                .fillMaxHeight()
        ) {

            Column(
                modifier = Modifier
                    .background(proposedColor)
                    .fillMaxHeight()
                    .padding(Dp(16f))
                    .weight(1f)

            ){
                Text(
                    text = stringResource(R.string.Proposed_color ),
                    textAlign = TextAlign.Center

                )
            }
            Column(
                modifier = Modifier
                    .background(targetColor)
                    .fillMaxHeight()
                    .padding(Dp(16f))
                    .weight(1f)

            ){
                Text(
                    text = stringResource(R.string.Target_color ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    fun sliderSection(title: String,
                      color: Color,
                      value: Float,
                      onValueChange: (Float) -> Unit
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Slider(
                value = value,
                onValueChange = onValueChange,
                valueRange = 0f..255f,
                colors = SliderDefaults.colors(
                    thumbColor = color,
                    activeTickColor = color,
                    inactiveTrackColor = Color.Gray,
                    activeTrackColor = color
                ),
                modifier = Modifier.weight(3f)
            )
            Text(text = value.toInt().toString(),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Default_Preview(){
        myUI()
    }

}
