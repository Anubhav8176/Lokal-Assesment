package com.anucodes.lokal_assessment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.anucodes.lokal_assessment.networking.model.Result
import com.anucodes.lokal_assessment.ui.JobDetailsScreen
import com.anucodes.lokal_assessment.ui.theme.LokalAssessmentTheme
import com.anucodes.lokal_assessment.ui.theme.poppinsFamily

class JobDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LokalAssessmentTheme {
                val result = intent.getParcelableExtra<Result>("job_detail")
                Log.i("The data passed is: ", result.toString())
                if (result != null){
                    JobDetailsScreen(result = result)
                }else{
                    Text(
                        text = "No data Found!!",
                        fontSize = 18.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
