package me.hyuck.kakaoanalyzer.features.detail.keyword.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.detail.keyword.data.DetailKeywordEnvironment
import me.hyuck.kakaoanalyzer.features.detail.keyword.data.IDetailKeywordEnvironment
import me.hyuck.kakaoanalyzer.runtime.MainDestinations
import javax.inject.Inject

@HiltViewModel
class DetailKeywordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    detailEnvironment: DetailKeywordEnvironment
) : StatefulViewModel<DetailKeywordState, Unit, Unit, IDetailKeywordEnvironment>(DetailKeywordState(), detailEnvironment) {

    private val chatId = savedStateHandle.get<String>(MainDestinations.CHAT_ID_KEY)

    init {
        initKeywords()
    }

    private fun initKeywords() {
        viewModelScope.launch {
            if (chatId != null) {
                environment.getChatById(chatId)
                    .collect {
                        setState { copy(chat = it) }
                    }
            }
        }
    }

    override fun dispatch(action: Unit) {
        TODO("Not yet implemented")
    }

}