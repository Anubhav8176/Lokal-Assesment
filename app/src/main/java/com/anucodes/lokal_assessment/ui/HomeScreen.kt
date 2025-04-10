package com.anucodes.lokal_assessment.ui

import android.content.Intent
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anucodes.lokal_assessment.JobDetailsActivity
import com.anucodes.lokal_assessment.bookmarkJob.BookmarkEntity
import com.anucodes.lokal_assessment.core.viewmodel.JobsViewModel
import com.anucodes.lokal_assessment.networking.model.Result
import com.anucodes.lokal_assessment.ui.theme.poppinsFamily
import kotlin.math.exp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    jobsViewModel: JobsViewModel
) {

    val jobsList by jobsViewModel.jobList.collectAsState()
    val error by jobsViewModel.error.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        if (error){
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Failed to load the data!",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        jobsViewModel.getAllJobs()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Gray
                    ),
                    border = BorderStroke(2.dp, Color.Black)
                ) {
                    Text(
                        text = "Refresh Now!!!!",
                        fontSize = 18.sp,
                        fontFamily = poppinsFamily,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }else{
            LazyColumn {
                items(jobsList.size) { index->
                    Log.i("The data is: ", jobsList[index].toString())
                    if (jobsList[index].title != null) {
                        JobInformation(jobDetails = jobsList[index], jobsViewModel = jobsViewModel)
                        if (index >= jobsList.size-7){
                            jobsViewModel.getAllJobs()
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun JobInformation(
    modifier: Modifier = Modifier,
    jobDetails: Result,
    jobsViewModel: JobsViewModel
) {

    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        onClick = {
            val intent = Intent(context, JobDetailsActivity::class.java).apply {
                putExtra("job_detail", jobDetails)
            }
            context.startActivity(intent)
        }
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
            if(jobDetails.primary_details.Salary.isNotEmpty()) {
                Text(
                    text = "Salary: ${jobDetails.primary_details.Salary}",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily
                )
            }
            Text(
                text = jobDetails.primary_details.Place,
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )

            Text(
                text = "Contact: ${jobDetails.whatsapp_no}",
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val bookmarkEntity = BookmarkEntity(
                        id = jobDetails.id,
                        title = jobDetails.title,
                        location = jobDetails.primary_details.Place,
                        phoneData = jobDetails.whatsapp_no,
                        salary = jobDetails.primary_details.Salary,
                        expiredOn = jobDetails.expire_on
                    )

                    jobsViewModel.addBookmark(bookmarkEntity)
                    Toast.makeText(context, "Bookmark added!", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Gray
                ),
                border = BorderStroke(1.dp, Color.Gray)
            ) {
                Text(
                    text = "Add to Bookmark",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily
                )
            }
        }
    }
}