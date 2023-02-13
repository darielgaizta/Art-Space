package com.darielgaizta.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darielgaizta.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtworkWall(
    imageId: Int
) {
    val image = painterResource(id = imageId)

    Column {
        Image (
            painter = image,
            contentDescription = null,
            modifier = Modifier.height(480.dp)
        )
    }
}

@Composable
fun ArtworkDescriptor(
    text: String,
    year: String
) {
    Column {
        Text(
            text = text,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = year,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Artwork(
    imageId: Int,
    text: String,
    year: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtworkWall(imageId = imageId)
        ArtworkDescriptor(text = text, year = year)
    }
}

@Composable
fun ArtSpaceScreen() {
    var currentArtwork by remember {
        mutableStateOf(0)
    }

    var currentImageId: Int = R.drawable.ny
    var currentArtName: String = stringResource(id = R.string.ny)
    var currentArtYear: String = stringResource(id = R.string.ny_year)

    if (currentArtwork == 0) {
        currentImageId = R.drawable.ny
        currentArtName = stringResource(id = R.string.ny)
        currentArtYear = stringResource(id = R.string.ny_year)
    } else if (currentArtwork == 1) {
        currentImageId = R.drawable.borobudur
        currentArtName = stringResource(id = R.string.borobudur)
        currentArtYear = stringResource(id = R.string.borobudur_year)
    } else if (currentArtwork == 2) {
        currentImageId = R.drawable.greatwall
        currentArtName = stringResource(id = R.string.greatwall)
        currentArtYear = stringResource(id = R.string.greatwall_year)
    } else if (currentArtwork == 3) {
        currentImageId = R.drawable.japan
        currentArtName = stringResource(id = R.string.tokyo)
        currentArtYear = stringResource(id = R.string.tokyo_year)
    } else if (currentArtwork == 4) {
        currentImageId = R.drawable.namsan
        currentArtName = stringResource(id = R.string.namsan)
        currentArtYear = stringResource(id = R.string.namsan_year)
    } else if (currentArtwork == 5) {
        currentImageId = R.drawable.maldives
        currentArtName = stringResource(id = R.string.maldives)
        currentArtYear = stringResource(id = R.string.maldives_year)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Artwork(imageId = currentImageId, text = currentArtName, year = currentArtYear)
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Button(onClick = {
                if (currentArtwork <= 0) {
                    currentArtwork = 5
                } else {
                    currentArtwork -= 1
                }
            },
            modifier = Modifier.width(100.dp)) {
                Text(text = "Previous")
            }
            Button(onClick = {
                if (currentArtwork >= 5) {
                    currentArtwork = 0
                } else {
                    currentArtwork += 1
                }
            },
            modifier = Modifier.width(100.dp)) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = false, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}