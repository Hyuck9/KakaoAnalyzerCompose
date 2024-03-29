package me.hyuck.kakaoanalyzer.features.detail.keyword.ui

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.detail.keyword.data.DetailKeywordEnvironment
import me.hyuck.kakaoanalyzer.features.detail.keyword.data.IDetailKeywordEnvironment
import me.hyuck.kakaoanalyzer.model.Filter
import me.hyuck.kakaoanalyzer.runtime.MainDestinations
import javax.inject.Inject

@HiltViewModel
class DetailKeywordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    detailEnvironment: DetailKeywordEnvironment
) : StatefulViewModel<DetailKeywordState, Unit, DetailKeywordAction, IDetailKeywordEnvironment>(DetailKeywordState(), detailEnvironment) {

    private val chatId = savedStateHandle.get<String>(MainDestinations.CHAT_ID_KEY)

    init {
        initFilters()
    }

    private fun initFilters() {
        viewModelScope.launch {
            if (chatId != null) {
                environment.getUsers(chatId)
                    .collect { userName ->
                        setState { copy(filters = userName.map { Filter(name = it) }) }
                    }
            }
        }
    }

    override fun dispatch(action: DetailKeywordAction) {
        when (action) {
            is DetailKeywordAction.ObserveKeywords -> {
                getKeywords(action.filters)
            }
            is DetailKeywordAction.OnQueryChange -> {
                viewModelScope.launch {
                    setState { copy(query = action.query) }
                }
            }
            is DetailKeywordAction.OnClearQuery -> {
                viewModelScope.launch {
                    setState { copy(query = TextFieldValue("")) }
                }
            }
        }
    }

    private fun getKeywords(filters: List<Filter>) {
        viewModelScope.launch {
            if (chatId != null) {
//                environment.getKeywords(chatId, filters, state.value.query.text)
//                    .collect {
//                        setState { copy(items = it) }
//                    }
                val keywords = environment.getKeywords(chatId, filters, state.value.query.text)
                setState { copy(items = keywords) }
            }
        }
    }

}