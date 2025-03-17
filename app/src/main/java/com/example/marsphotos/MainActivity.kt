package com.example.marsphotos

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boschcompose1.R
import com.example.marsphotos.ui.theme.Boschcompose1Theme

//main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       var name =  intent.extras?.getString("name")
        setContent {
            Boschcompose1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(
                        message = "Happy Birthday Sam!",
                        from = "From "+name
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    //trailing lamda syntax
    Box(modifier) {
        Image(painter = image,
            contentDescription = "birthday greeting image",
            contentScale = ContentScale.Crop,
            alpha = 0.5F

        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }

}

@Composable
fun GreetingText(message: String,from:String, modifier: Modifier = Modifier) {

    Column(modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center) {
        Text(text = message, fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center)
        Text(text = from, fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.End) )
        Button(onClick = {
            val resIntent = Intent().putExtra("pn","12345678")

        }) {

        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Boschcompose1Theme {
        GreetingImage(message = "happy birthday", from = "emma" )
    }
}