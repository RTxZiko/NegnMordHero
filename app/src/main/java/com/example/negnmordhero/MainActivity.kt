@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.negnmordhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.negnmordhero.model.Hero
import com.example.negnmordhero.model.HeroesRepository
import com.example.negnmordhero.ui.theme.NegnMordHeroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NegnMordHeroTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Машины",
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 30.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        )
                    }
                ) { innerPadding ->
                    SuperheroesScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SuperheroesScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(HeroesRepository.heroes) { hero ->
            HeroListItem(
                hero = hero,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun HeroListItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .heightIn(min = 72.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(hero.imageRes),
                contentDescription = stringResource(hero.descriptionRes),
                modifier = Modifier
                    .size(72.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroPreview() {
    NegnMordHeroTheme {
        HeroListItem(
            hero = Hero(
                nameRes = R.string.car1,
                descriptionRes = R.string.description1,
                imageRes = R.drawable.car1
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SuperheroesScreenPreview() {
    NegnMordHeroTheme {
        SuperheroesScreen()
    }
}