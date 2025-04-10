package com.anucodes.lokal_assessment.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anucodes.lokal_assessment.networking.model.Result

@Composable
fun JobDetailsScreen(result: Result) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = result.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        InfoCard("Company", result.company_name)
        InfoCard("Posted On", result.created_on)
        InfoCard("Expires On", result.expire_on)
        InfoCard("Experience (Years)", result.experience.toString())
        InfoCard("Applications", result.num_applications.toString())
        InfoCard("Openings", result.openings_count.toString())
        InfoCard("Qualification", result.qualification.toString())
        InfoCard("Shares", result.shares.toString())
        InfoCard("Views", result.views.toString())
        InfoCard("WhatsApp", result.whatsapp_no)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Primary Details",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        InfoCard("Experience", result.primary_details.Experience)
        InfoCard("Fees Charged", result.primary_details.Fees_Charged)
        InfoCard("Job Type", result.primary_details.Job_Type)
        InfoCard("Place", result.primary_details.Place)
        InfoCard("Qualification", result.primary_details.Qualification)
        InfoCard("Salary", result.primary_details.Salary)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = result.content,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun InfoCard(label: String, value: String) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
