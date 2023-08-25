package ru.vaa.testedapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.vaa.testedapp.data.cameras.CamerasViewModel
import ru.vaa.testedapp.repository.model.Camera

@Composable
fun CamerasScreen(camerasViewModel: CamerasViewModel = hiltViewModel()) {
    val listCam = camerasViewModel.listCameras.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Lounge",
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            style = TextStyle(
                fontSize = 18.sp
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            listCam.value?.let {
                items(it) { item: Camera ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 5.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        AsyncImage(
                            model = item.snapshot,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(3.dp)
                        )
                        Text(
                            text = item.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, top = 8.dp, bottom = 20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCamerasScreen() {
    CamerasScreen()
}