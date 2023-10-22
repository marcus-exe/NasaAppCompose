package android.com.nasaapp.ui.screens

import android.com.nasaapp.R
import android.com.nasaapp.model.NasaInfo
import android.com.nasaapp.ui.theme.NasaAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HomeScreen(
    nasaUiState: NasaUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (nasaUiState) {
        is NasaUiState.Loading -> LoadingScreen(modifier)
        is NasaUiState.Success -> NasaListCard(nasaListInfo = nasaUiState.info)
        is NasaUiState.Error -> ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = null,
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.broken_img),
            contentDescription = null
        )
        Button(onClick = retryAction) {
            Text(text = "Retry Action")
        }
    }
}

@Composable
fun NasaCard(
    nasaInfo: NasaInfo,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(nasaInfo.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = null,
            error = painterResource(id = R.drawable.broken_img),
            placeholder = painterResource(id = R.drawable.loading_img),
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun NasaListCard(
    nasaListInfo: List<NasaInfo>,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(nasaListInfo) {
            NasaCard(nasaInfo = it)
        }
    }
}

@Preview
@Composable
fun NasaListPreview() {
    val mockdata = List(10) { NasaInfo(".", ".", ".", ".") }
    NasaAppTheme {
        NasaListCard(nasaListInfo = mockdata, modifier = Modifier.fillMaxWidth())
    }
}








