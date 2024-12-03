package com.toDoApp.screens.tutorialsscreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.toDoApp.storage.database.SellViewModel
import com.toDoApp.R
import com.toDoApp.customcomponents.CustomText
import com.toDoApp.customcomponents.CustomTopBar
import com.toDoApp.models.Sell


@Composable
fun SellListScreen(viewModel: SellViewModel, onNavigate: (String) -> Unit) {

    val sellsList by viewModel.sells.collectAsState()

    if (viewModel.addState.value) {
        SellInputScreen(viewModel)
    } else {

        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {

            CustomTopBar(
                title = stringResource(id = R.string.sell_list),
                onBackPress =
                {
                    onNavigate("")
                }

            ) {}


        }, floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    viewModel.addState.value = true
                },
                modifier = Modifier
                    .padding(16.dp),
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Save Sell Item")
            }
        },bottomBar = {
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp)) {
                CustomText(
                    isSingleLine = true,
                    modifier = Modifier,
                    text = "Total Price of Items : " ,
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
                    text = (viewModel.getTotalPriceForSellItem(sellsList)).toString(),
                    fontSize = 20f,
                    textAlign = TextAlign.Center,
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.W600,

                        )
                )
            }

        }) { paddingValues ->


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {


                items(sellsList) { sell ->
                    SellListSingleItemView(sell, {
                        viewModel.delete(sell)
                    }) {
                        viewModel.setDataForEdit(it)
                        viewModel.addState.value = true
                    }

                }
            }


        }
    }

}


@Composable
fun SellInputScreen(viewModel: SellViewModel) {

    BackHandler(enabled = true, onBack = { viewModel.addState.value = false })


    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CustomTopBar("All Sell Items", {
            viewModel.addState.value = false
        }) {

        }
    }) { paddingValues ->


        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Add New Sell Item", style = MaterialTheme.typography.h6)


            OutlinedTextField(
                value = viewModel.price.value,
                onValueChange = { viewModel.price.value = it },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = viewModel.category.value,
                onValueChange = { viewModel.category.value = it },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = viewModel.description.value,
                onValueChange = { viewModel.description.value = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (viewModel.isValidInput()) {

                        if (viewModel.itemId.value == -1) {

                            viewModel.insert()
                        } else {
                            viewModel.update()
                        }


                    }
                },
                enabled = viewModel.isValidInput(),
                modifier = Modifier.align(
                    Alignment.End
                )
            ) {
                Text(
                    if (viewModel.itemId.value == -1) {

                        "Save"
                    } else {
                        "Update"
                    }
                )
            }
        }
    }


}


@Composable

fun SellListSingleItemView(
    productItem: Sell,
    onDeleteClocked: (Sell) -> Unit,
    onEditClick: (Sell) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.light_blue))
    ) {


        Row(modifier = Modifier.fillMaxWidth()) {

            Column(
                modifier = Modifier
                    .weight(4f)
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {


                Row() {
                    CustomText(
                        isSingleLine = true,
                        modifier = Modifier,
                        text = "Price : ",
                        fontSize = 18f,
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
                        fontSize = 18f,
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
                        text = "Category : ",
                        fontSize = 18f,
                        textAlign = TextAlign.Center,
                        textStyle = TextStyle(
                            color = colorResource(id = R.color.black),
                            fontWeight = FontWeight.W600,

                            )
                    )
                    CustomText(
                        isSingleLine = true,
                        modifier = Modifier,
                        text = productItem.category,
                        fontSize = 18f,
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
                        text = "Description : ",
                        fontSize = 18f,
                        textAlign = TextAlign.Center,
                        textStyle = TextStyle(
                            color = colorResource(id = R.color.black),
                            fontWeight = FontWeight.W600,

                            )
                    )
                    CustomText(
                        isSingleLine = true,
                        modifier = Modifier,
                        text = productItem.description,
                        fontSize = 18f,
                        textAlign = TextAlign.Center,
                        textStyle = TextStyle(
                            color = colorResource(id = R.color.black),
                            fontWeight = FontWeight.W400,

                            )
                    )
                }

            }
            Column(modifier = Modifier.weight(1f)) {
                IconButton(onClick = { onDeleteClocked(productItem) }) {
                    androidx.compose.material.Icon(
                        Icons.Default.Delete,
                        contentDescription = "Localized description"
                    )
                }
                IconButton(onClick = {
                    onEditClick(productItem)
                }) {
                    androidx.compose.material.Icon(
                        Icons.Default.Edit,
                        contentDescription = "Localized description"
                    )
                }
            }
        }

    }

}