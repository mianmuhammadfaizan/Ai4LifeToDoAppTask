package com.toDoApp.screens.tutorialsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.toDoApp.R
import com.toDoApp.customcomponents.CustomText
import com.toDoApp.customcomponents.DashboardTopBar
import com.toDoApp.navigation.ToDoAppScreens
import com.toDoApp.theme.sellCArdColor
import com.toDoApp.theme.settingThemeColor
import com.toDoApp.theme.white

@Composable

fun DashBoardScreen(onNavigate: (String) -> Unit) {
    Scaffold(topBar = {
        DashboardTopBar("Dashboard")
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent)
                ) {

                    CustomCardView(
                        stringResource(R.string.call_list),
                        painterResource(R.drawable.ic_call_list),
                        {

                            onNavigate(ToDoAppScreens.CallListView.name)
                        },

                        settingThemeColor.value.copy(alpha = .5f)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent)
                ) {

                    CustomCardView(
                        stringResource(R.string.buy_list),
                        painterResource(R.drawable.ic_buy),
                        {

                            onNavigate(ToDoAppScreens.BuyListScreen.name)
                        },



                        settingThemeColor.value.copy(alpha = .5f)
                    )
                }
            }


            CustomCardView(
                stringResource(R.string.sell_list),
                painterResource(R.drawable.ic_sales),
                {
                    onNavigate(ToDoAppScreens.SellListScreen.name)
                },

                sellCArdColor.value
            )
        }
    }


}


@Composable
fun CustomCardView(
    text1: String,
    topImage: Painter,
    cardClicked: () -> Unit,
    cardColor: Color = colorResource(id = R.color.white),
) {

    Card(
        modifier = Modifier
            .background(shape = RoundedCornerShape(50.dp), color = Color.Transparent)
            .height(140.dp)
            .padding(10.dp)
            .fillMaxWidth(),
        onClick = {
            cardClicked()
        },
        colors = CardDefaults.cardColors(cardColor)
    ) {


        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(
                modifier = Modifier.weight(7f),
                text = text1,
                fontSize = 18f,
                textAlign = TextAlign.Center,
                textStyle = TextStyle(
                    color = white,
                    fontWeight = FontWeight.W600,

                    )
            )

            Image(
                painter = topImage, contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .size(50.dp)
            )

        }


    }


}




