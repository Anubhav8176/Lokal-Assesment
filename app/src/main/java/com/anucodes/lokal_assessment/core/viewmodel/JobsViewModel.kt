package com.anucodes.lokal_assessment.core.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.anucodes.lokal_assessment.bookmarkJob.BookmarkEntity
import com.anucodes.lokal_assessment.bookmarkJob.JobDatabase
import com.anucodes.lokal_assessment.networking.core.RetrofitInstance
import com.anucodes.lokal_assessment.networking.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JobsViewModel(
    application: Application
): AndroidViewModel(application) {

    private val rdb = Room.databaseBuilder(
        application,
        JobDatabase::class.java,
        "job.db"
    ).build()

    private val bookmarkDb = rdb.dao()

    private val _jobList = MutableStateFlow<List<Result>>(emptyList())
    val jobList = _jobList.asStateFlow()

    private val _bookMarkJobs = MutableStateFlow<List<BookmarkEntity>>(emptyList())
    val bookmarkEntity = _bookMarkJobs.asStateFlow()

    private val _error = MutableStateFlow<Boolean>(false)
    val error = _error.asStateFlow()

    private var pageNumber = 1

    init {
        getAllJobs()
        getBookmark()
    }

    fun getAllJobs(){
        viewModelScope.launch {
            try {
                _error.value = false
                val response = RetrofitInstance.apiInterface.getJobs(pageNumber)
                _jobList.value += response.results
                pageNumber += 1
            }catch (e: Exception){
                Log.i("The error is: ", e.message.toString())
                _error.value = true
            }
        }
    }

     fun addBookmark(bookmarkEntity: BookmarkEntity){
        viewModelScope.launch {
            bookmarkDb.addBookmark(bookmarkEntity)
            getBookmark()
            Log.i("Book mark add: ", bookmarkEntity.toString())
        }
    }

    fun deleteBookmark(bookmarkEntity: BookmarkEntity){
        viewModelScope.launch {
            bookmarkDb.deleteBookmarkJob(bookmarkEntity)
            getBookmark()
            Log.i("Book mark delete: ", bookmarkEntity.toString())
        }
    }

    fun getBookmark(){
        viewModelScope.launch {
            _bookMarkJobs.value = bookmarkDb.getBookmarkJobs()
            Log.i("All bookmark jobs", _bookMarkJobs.value.toString())
        }
    }
}