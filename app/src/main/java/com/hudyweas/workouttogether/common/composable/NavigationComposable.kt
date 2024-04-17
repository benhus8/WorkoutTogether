package com.hudyweas.workouttogether.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hudyweas.workouttogether.R
import com.hudyweas.workouttogether.common.static.navigationItemsList
import com.hudyweas.workouttogether.model.NavigationItem

@Composable
fun NavigationDrawerHeader(value: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(32.dp)

    ) {

        NavigationDrawerText(
            title = value?: stringResource(R.string.lorem), 28.sp , Color.White
        )

    }
}

@Composable
fun NavigationDrawerBody(
    navigationDrawerItems: List<NavigationItem>,
    onNavigationItemClicked: (NavigationItem) -> Unit,
    openScreen: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        items(navigationDrawerItems){
            NavigationItemRow(item = it, openScreen)
        }

    }
}

@Composable
fun NavigationItemRow(item: NavigationItem,
                      openScreen:(String) -> Unit ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                openScreen(item.itemId)
            }
            .padding(all = 18.dp)
    ) {
        Icon(
            painterResource(item.icon),
            item.description,
        )

        Spacer(modifier = Modifier.width(22.dp))

        NavigationDrawerText(title = item.title, 22.sp, Color.White)
    }
}

@Composable
fun NavigationDrawerText(title: String, textUnit: TextUnit, color: Color) {
    Text(
        text = title, style = TextStyle(
            fontSize = textUnit,
            fontStyle = FontStyle.Normal,
        )
    )
}

@Preview(showBackground = true)
@ExperimentalMaterialApi
@Composable
fun NavigationDrawerHeaderPreview() {
    NavigationDrawerHeader("Home")
}

@Preview(showBackground = true)
@ExperimentalMaterialApi
@Composable
fun NavigationDrawerBodyPreview() {
    NavigationDrawerBody(
        navigationDrawerItems = navigationItemsList,
        {},
        {}
    )
}
