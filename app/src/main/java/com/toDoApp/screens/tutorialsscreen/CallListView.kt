package com.toDoApp.screens.tutorialsscreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import com.toDoApp.R
import com.toDoApp.customcomponents.CustomText
import com.toDoApp.customcomponents.CustomTopBar
import com.toDoApp.models.CallDataModel
import com.toDoApp.viewmodels.CallListViewModel

@OptIn(ExperimentalFoundationApi::class)
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
            stickyHeader {
                Column {

                    CustomSearchField(callListViewModel)
                    Box(
                        modifier = Modifier
                            .size(height = 50.dp, width = 100.dp)
                            .clickable(onClick = {

                                callListViewModel.getSortedList()
                            })
                            .background(
                                shape = RoundedCornerShape(50.dp), color =
                                if (callListViewModel.isSorted.value) {
                                    Color.LightGray.copy(alpha = .3f)
                                } else Color.Blue.copy(alpha = .3f)
                            )
                    ) {
                        Text(
                            if (callListViewModel.isSorted.value) {
                                "Sort A-Z"
                            } else {
                                "Sort Z-A"
                            }, modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.Center)
                        )

                    }
                }
            }
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


                    callListViewModel.searchList.value.forEachIndexed { index, call ->
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
            colorResource(R.color.light_blue
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



        Row(modifier = Modifier.fillMaxWidth()) {

            Column(
                modifier = Modifier.weight(5f)
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
            Column(modifier = Modifier.weight(1f)) {
                RadioButton(
                    enabled = true, onClick = {
                        onCLicked()
                    },
                    selected = callDataModel.isCalled.value,
                    modifier = Modifier.padding(top = 20.dp),
                    interactionSource = null,
                )
            }
        }

    }

}


@Composable
fun CustomSearchField(callListViewModel: CallListViewModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(30.dp),
    ) {
        Box(modifier = Modifier.padding(start = 10.dp)) {
            BasicTextField(
                value = callListViewModel.query.value,
                onValueChange = {
                    callListViewModel.query.value = it
                    callListViewModel.onSearchValue(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        innerTextField()
                    }
                }
            )
        }
    }
}
