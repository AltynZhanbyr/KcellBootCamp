package kz.kcell.kcellbootcamp.presentation.moviesSearch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kz.kcell.kcellbootcamp.data.entities.Movie
import kz.kcell.kcellbootcamp.data.repository.MovieRepository
import kz.kcell.kcellbootcamp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    repository: MovieRepository
) : ViewModel() {

}