package com.example.breezepoc.android.presentation

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
const val PERSON_IMAGE_LIST_HEIGHT = 60

@Composable
fun PersonImage(
  url: String?,
  contentDescription: String?
){
  val urlPrefix = "https://files.breezechms.com"
  val painter = rememberImagePainter("$urlPrefix/$url")
  // TODO(create own internal shape ref 34)
  Box {
    Image(
      modifier = Modifier
        .size(PERSON_IMAGE_LIST_HEIGHT.dp)
        .clip(CircleShape),
      painter = painter,
      contentDescription = contentDescription,
      contentScale = ContentScale.Crop,
    )
    when(painter.state){
      is ImagePainter.State.Error -> {
        Image(
          modifier = Modifier
            .size(PERSON_IMAGE_LIST_HEIGHT.dp)
            .clip(CircleShape),
          painter = painterResource(R.drawable.ic_default_account_circle_24),
          contentDescription = contentDescription,
          contentScale = ContentScale.Crop,
        )
      }
      is ImagePainter.State.Success -> {
        // do something when displaying the image is successful
      }
      is ImagePainter.State.Loading -> {
        Box(modifier = Modifier
          .size(PERSON_IMAGE_LIST_HEIGHT.dp)
          .clip(CircleShape)
        ) {
          // empty for white background
        }
      }
    }
  }
}

//Image(
//modifier = Modifier
//.fillMaxWidth()
//.height(PERSON_IMAGE_HEIGHT.dp),
//painter = painter,
//contentDescription = contentDescription,
//contentScale = ContentScale.Crop,
//)