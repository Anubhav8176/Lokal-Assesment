package com.anucodes.lokal_assessment.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anucodes.lokal_assessment.bookmarkJob.BookmarkEntity
import com.anucodes.lokal_assessment.core.viewmodel.JobsViewModel
import com.anucodes.lokal_assessment.networking.model.Result
import com.anucodes.lokal_assessment.ui.theme.poppinsFamily

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    jobsViewModel: JobsViewModel
) {

    val bookmarkJobs by jobsViewModel.bookmarkEntity.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        if (bookmarkJobs.isEmpty()){
            Column (
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Nothing to show here!! Add some bookmark.",
                    modifier = modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        }else{
            LazyColumn {
                items(bookmarkJobs.size) { index->
                    Log.i("The data is: ", bookmarkJobs[index].toString())
                    if (bookmarkJobs[index].title != null) {
                        BookmarkJobInformation(jobDetails = bookmarkJobs[index], jobsViewModel = jobsViewModel)
                    }
                }
            }
        }
    }

}

@Composable
fun BookmarkJobInformation(
    modifier: Modifier = Modifier,
    jobDetails: BookmarkEntity,
    jobsViewModel: JobsViewModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
        ) {
            Text(
                text = jobDetails.title,
                fontSize = 18.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier.height(10.dp))

            HorizontalDivider()
            if(jobDetails.salary.isNotEmpty()) {
                Text(
                    text = "Salary: ${jobDetails.salary}",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily
                )
            }
            Text(
                text = jobDetails.location,
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )

            Text(
                text = "Contact: ${jobDetails.phoneData}",
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    jobsViewModel.deleteBookmark(jobDetails)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Gray
                ),
                border = BorderStroke(1.dp, Color.Gray)
            ) {
                Text(
                    text = "Remove Bookmark",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily
                )
            }
        }
    }
}