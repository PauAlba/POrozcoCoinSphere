package com.example.coinmarketcap


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.request.ImageRequest
import com.example.coinmarketcap.ui.theme.CoinMarketCapTheme

val Background = Color(0xFF0B1020)
val Surface = Color(0xFF151B2E)
val TextMain = Color(0xFFE8ECF8)
val TextDim = Color(0xFF9AA3B2)


data class CryptoItem(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinMarketCapTheme {
                HomeScreen()
            }
        }
    }
}

// Main
@Composable
fun HomeScreen() {
    // datos para los items del lazy column
    val cryptoList = listOf(
        CryptoItem(1, "Bitcoin", "$109,797.37", "https://assets.coingecko.com/coins/images/1/large/bitcoin.png"),
        CryptoItem(2, "Ethereum", "$4,321.21", "https://assets.coingecko.com/coins/images/279/large/ethereum.png"),
        CryptoItem(3, "Tether", "$1.0000", "https://assets.coingecko.com/coins/images/325/large/Tether-logo.png"),
        CryptoItem(4, "XRP", "$2.8100", "https://assets.coingecko.com/coins/images/44/large/xrp-symbol-white-128.png"),
        CryptoItem(5, "BNB", "$845.0000", "https://assets.coingecko.com/coins/images/825/large/binance-coin-logo.png"),
        CryptoItem(6, "Solana", "$201.8500", "https://assets.coingecko.com/coins/images/4128/large/solana.png"),
        CryptoItem(7, "USDC", "$0.9998", "https://assets.coingecko.com/coins/images/6319/large/USD_Coin_icon.png"),
        CryptoItem(8, "Dogecoin", "$0.1320", "https://assets.coingecko.com/coins/images/5/large/dogecoin.png"),
        CryptoItem(9, "TRON", "$0.3630", "https://assets.coingecko.com/coins/images/1094/large/tron-logo.png")
    )


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Header
            Text(
                text = "CoinSphere",
                color = TextMain,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            //Cards
            InfoCard(title = "Global Market Cap", value = "$2.18T")
            InfoCard(title = "Fear & Greed", value = "Neutral (54)")
            InfoCard(title = "Altcoin Season", value = "No")

            Spacer(modifier = Modifier.height(24.dp))

            //List Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "# Name", color = TextDim, fontSize = 14.sp)
                Text(text = "Price", color = TextDim, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // List Section
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cryptoList) { crypto ->
                    CryptoItemRow(crypto = crypto)
                }
            }
        }
    }
}

// card
@Composable
fun InfoCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, color = TextDim, fontSize = 16.sp)
            Text(text = value, color = TextMain, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

//item
@Composable
fun CryptoItemRow(crypto: CryptoItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = crypto.id.toString(),
                color = TextDim,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            AsyncImage(
                model = crypto.imageUrl,
                contentDescription = "${crypto.name} logo",
                modifier = Modifier.size(36.dp),
                contentScale = ContentScale.Fit,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(id = R.drawable.ic_launcher_foreground)
            )

            Text(
                text = crypto.name,
                color = TextMain,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
        Text(
            text = crypto.price,
            color = TextMain,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            modifier = Modifier.wrapContentWidth(Alignment.End)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoinMarketCapTheme {
        HomeScreen()
    }
}
