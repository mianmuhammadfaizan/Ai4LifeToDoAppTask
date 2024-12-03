package com.toDoApp.screens.tutorialsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.toDoApp.R
import com.toDoApp.customcomponents.CustomText
import com.toDoApp.customcomponents.CustomTopBar
import com.toDoApp.models.ProductDataModel
import com.toDoApp.viewmodels.ProductListViewModel
import kotlinx.coroutines.launch


@Composable
fun BuyListScreen(productListViewModel: ProductListViewModel, onNavigate: (String) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            CustomTopBar(
                title = stringResource(id = R.string.buy_list),
                onBackPress =
                {
                    onNavigate("")
                }

            ) {


            }


        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                if (productListViewModel.isLoading.value) {
                    Box(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(paddingValues)
                    ) {
                        CircularProgressIndicator(
                            strokeWidth = 1.dp,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(50.dp),
                            color = colorResource(id = R.color.blue)
                        )
                    }
                } else {

                    productListViewModel.productList.value.forEachIndexed { index, product ->
                        if (product.type == 1) {

                            BuyListSingleItemView(product) {
                                productListViewModel.updateQuantity(product.id, true)
                            }
                        }

                    }
                }
            }
        }

    }
}


@Composable

fun BuyListSingleItemView(productItem: ProductDataModel, onAddClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.light_blue))
    ) {


        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Row() {
                CustomText(
                    isSingleLine = true,
                    modifier = Modifier,
                    text = "Name : ",
                    fontSize = 20f,
                    textAlign = TextAlign.Center,
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.W600,

                        )
                )
                CustomText(
                    isSingleLine = true,
                    modifier = Modifier,
                    text = productItem.name,
                    fontSize = 20f,
                    textAlign = TextAlign.Center,
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.W400,

                        )
                )
            }
            Row() {
                CustomText(
                    isSingleLine = true,
                    modifier = Modifier,
                    text = "Price : ",
                    fontSize = 20f,
                    textAlign = TextAlign.Center,
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.W600,

                        )
                )
                CustomText(
                    isSingleLine = true,
                    modifier = Modifier,
                    text = productItem.price.toString(),
                    fontSize = 20f,
                    textAlign = TextAlign.Center,
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.W400,

                        )
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                CustomText(
                    isSingleLine = true,
                    modifier = Modifier,
                    text = "Quentity : ",
                    fontSize = 20f,
                    textAlign = TextAlign.Center,
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.W600,

                        )
                )
                CustomText(
                    isSingleLine = true,
                    modifier = Modifier,
                    text = productItem.quantity.toString(),
                    fontSize = 20f,
                    textAlign = TextAlign.Center,
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.W400,

                        )
                )
                IconButton(onClick = { onAddClicked() }, modifier = Modifier.padding(start = 50.dp, bottom = 5.dp).size(30.dp).background(Color.White, shape = RoundedCornerShape(50.dp))) {
                    Icon(Icons.Default.Add, contentDescription = "Localized description")
                }
            }


        }

    }

}