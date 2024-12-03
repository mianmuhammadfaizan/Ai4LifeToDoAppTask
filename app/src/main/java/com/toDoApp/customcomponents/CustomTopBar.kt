package com.toDoApp.customcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.toDoApp.R
import com.toDoApp.theme.settingThemeColor


@Composable
fun CustomTopBar(
    title: String,
    onBackPress: () -> Unit,
    onManuClick: () -> Unit

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp),
        colors = CardDefaults.cardColors(
            settingThemeColor.value.copy(alpha = .3f)
        )
    ) {


        Row(
            modifier = Modifier
                .padding(top = 50.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f)
                    .background(Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                IconButton(onClick = { onBackPress() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier =
                        Modifier
                            .padding(end = 10.dp)
                            .height(30.dp)
                            .width(40.dp)
                    )
                }

                CustomText(
                    isSingleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    fontSize = 20f,
                    textAlign = TextAlign.Center,
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.W600,

                        )
                )


            }
            Spacer(modifier = Modifier.size(15.dp))
            IconButton(onClick = { onManuClick() }) {
                Icon(

                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }


        }
    }
}

@Composable
fun DashboardTopBar(
    title: String,

    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp),
        colors = CardDefaults.cardColors(
            settingThemeColor.value.copy(alpha = .3f)
        )
    ) {


        Row(
            modifier = Modifier
                .padding(top = 65.dp, start = 8.dp, end = 8.dp, bottom = 15.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {


                CustomText(
                    isSingleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    fontSize = 20f,
                    textAlign = TextAlign.Center,
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.W600,

                        )
                )


            }


        }
    }
}