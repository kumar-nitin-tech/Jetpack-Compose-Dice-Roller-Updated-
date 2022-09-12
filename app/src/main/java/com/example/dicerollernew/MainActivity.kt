package com.example.dicerollernew

import android.os.Bundle
import android.view.animation.Animation
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dicerollernew.ui.theme.DiceRollerNewTheme
import kotlin.time.ExperimentalTime
import kotlin.time.seconds
import kotlinx.coroutines.delay
import org.intellij.lang.annotations.JdkConstants
import kotlin.coroutines.suspendCoroutine

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            diceApp()
        }
    }
}

@Composable
fun diceApp(){
    DiceRollerNewTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray),
            color = MaterialTheme.colors.background

        ) {
            ScreenContent()
        }
    }
}


@OptIn(ExperimentalTime::class)
@Composable
fun ScreenContent(){
    var DiceNumber by remember {mutableStateOf(1) }
    var isLoading by remember {
        mutableStateOf(true)
    }
    val imageResource = when(DiceNumber){
        1->R.drawable.dice_1
        2->R.drawable.dice_2
        3->R.drawable.dice_3
        4->R.drawable.dice_4
        5->R.drawable.dice_5
        else->R.drawable.dice_6
    }
    var interactionSource = MutableInteractionSource()
    Box(contentAlignment = Alignment.Center) {

        if(isLoading) {
            CircularProgressIndicator()
            LaunchedEffect(isLoading) {
                delay(0.5.seconds)
                isLoading = false
            }

        }
        else{
            Image(painter = painterResource(id = imageResource), contentDescription ="Dice", modifier = Modifier.clickable(interactionSource,indication = null){
                    DiceNumber = (1..6).random()
                isLoading = true
            } )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiceRollerNewTheme {
    }
}