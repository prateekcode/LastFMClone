package `in`.androidcodes.lastfmclone.views

import `in`.androidcodes.lastfmclone.repo.Repository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TagsModelFactory(private val repo: Repository): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TagViewModel(repo) as T
    }
}