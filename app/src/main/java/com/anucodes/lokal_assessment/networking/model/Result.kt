package com.anucodes.lokal_assessment.networking.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Result(
    val amount: String,
    val company_name: String,
    val content: String,
    val created_on: String,
    val experience: Int,
    val expire_on: String,
    val id: Int,
    val num_applications: Int,
    val openings_count: Int,
    val primary_details: PrimaryDetails,
    val qualification: Int,
    val shares: Int,
    val title: String,
    val updated_on: String,
    val views: Int,
    val whatsapp_no: String
): Parcelable