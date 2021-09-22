package com.example.breezepoc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.breezepoc.android.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalStdlibApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Navigation()
        }
    }
}
