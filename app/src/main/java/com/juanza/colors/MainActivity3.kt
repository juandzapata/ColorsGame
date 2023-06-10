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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp


class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            myUI()
        }
    }

    @Composable
    fun myUI() {


        Column(
            modifier = Modifier.padding(Dp(10f))
        ) {

            Row(
                modifier = Modifier.weight(2f)
            ) {
                colorSection()
            }
            sliderSection(title = stringResource(R.string.Red), color = Color.Red, value = 30f )
            sliderSection(title = stringResource(R.string.Green), color = Color.Green, value = 90f )
            sliderSection(title = stringResource(R.string.Blue), color = Color.Blue, value = 180f )
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
                        .fillMaxWidth()
                ){
                    Text(text = stringResource(R.string.New))
                }
            }
            Row(
            ){
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(text = stringResource(R.string.Score))
                }
            }
        }

    }

    @Composable
    fun colorSection(

    ){
        Row(
            modifier = Modifier
                .fillMaxHeight()
        ) {

            Column(
                modifier = Modifier
                    .background(Color(0xFF6699CC))
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
                    .background(Color(0xFF808080))
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
                      value: Float
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
                onValueChange = {},
                valueRange = 0f..255f,
                colors = SliderDefaults.colors(
                    thumbColor = color,
                    activeTickColor = color,
                    inactiveTickColor = Color.Gray
                ),
                modifier = Modifier.weight(3f)
            )
            Text(text = value.toString(),
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
