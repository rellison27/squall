package com.example.breezepoc.android.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.breezepoc.android.R

const val PERSON_IMAGE_HEIGHT = 260

@Composable
fun PersonDetailImage(
    url: String?,
    contentDescription: String?
){
    val urlPrefix = "https://files.breezechms.com"
    val painter = rememberImagePainter("$urlPrefix/$url")
    Box {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(PERSON_IMAGE_HEIGHT.dp),
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
        )
        when(painter.state){
            is ImagePainter.State.Error -> {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(PERSON_IMAGE_HEIGHT.dp),
                    painter = painterResource(R.drawable.ic_baseline_account_box_24),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                )
            }
            is ImagePainter.State.Success -> {
                // do something when displaying the image is successful
            }
            is ImagePainter.State.Loading -> {
                Box(modifier = Modifier
                    .size(PERSON_IMAGE_HEIGHT.dp)
                ) {
                    // empty for white background
                }
            }
        }
    }
}