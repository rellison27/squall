package com.example.breezepoc.android.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

const val PERSON_IMAGE_HEIGHT = 260

@Composable
fun PersonImage(
  url: String?,
  contentDescription: String?
){
  val urlPrefix = "https://files.breezechms.com"
  val painter = rememberImagePainter("$urlPrefix/$url")
  Box {
    Image(
      modifier = Modifier
        .fillMaxWidth()
        .height(PERSON_IMAGE_HEIGHT.dp),
      painter = painter,
      contentDescription = contentDescription,
      contentScale = ContentScale.Crop,
    )
    when(painter.state){
      is ImagePainter.State.Error -> {
        // display some kind of an error
      }
      is ImagePainter.State.Success -> {
        // do something when displaying the image is successful
      }
      is ImagePainter.State.Loading -> {
        Box(modifier = Modifier
          .fillMaxWidth()
          .height(PERSON_IMAGE_HEIGHT.dp)
        ) {
          // empty for white background
        }
      }
    }
  }
}