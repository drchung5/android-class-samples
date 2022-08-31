package com.example.simplemvvmapp

import android.util.Log
import android.util.Log.DEBUG
import android.view.View
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.simplemvvmapp.data.Team

class TeamViewModel : ViewModel() {

    var teamListState: MutableState<List<Team>>
        = mutableStateOf(emptyList())

    fun addTeam( name :String, city : String ) {
        teamListState.value =  teamListState.value + listOf<Team>( Team(name,city) )
    }

}