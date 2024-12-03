package com.toDoApp.screens.tutorialsscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.toDoApp.R
import com.toDoApp.customcomponents.CustomText
import com.toDoApp.customcomponents.CustomTopBar
import com.toDoApp.models.CallDataModel
import com.toDoApp.viewmodels.CallListViewModel

@Composable
fun CallListView(callListViewModel: CallListViewModel, onNavigate: (String) -> Unit) {



    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {

        CustomTopBar(
            title = stringResource(id = R.string.call_list),
            onBackPress =
            {
                onNavigate("")
            }

        ) {}


    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .background(Color.Transparent)
                .padding(paddingValues),

            ) {

            item {
                if (callListViewModel.isLoading.value) {
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

                    callListViewModel.callDataModelList.value.forEachIndexed { index, call ->
                        CallListSingleItemView(call) {
                            Log.d("callvalue", "CallListView: ${call.isCalled}")
                            call.isCalled.value = !call.isCalled.value
                            Log.d("callvalue", "CallListView: ${call.isCalled}")
                        }

                    }
                }
            }

        }

    }
}


@Composable

fun CallListSingleItemView(
    callDataModel:
    CallDataModel, onCLicked: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            colorResource(
                if (callDataModel.isCalled.value) {
                    R.color.purchase_button
                } else R.color.light_blue
            )
        ),
        modifier = Modifier
            .padding(top = 10.dp)
            .clickable(onClick = {
                onCLicked()
            })
            .fillMaxWidth()
            .wrapContentHeight(),

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
                    text = callDataModel.name,
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
                    text = "Number : ",
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
                    text = callDataModel.number,
                    fontSize = 20f,
                    textAlign = TextAlign.Center,
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.W400,

                        )
                )
            }

        }

    }

}