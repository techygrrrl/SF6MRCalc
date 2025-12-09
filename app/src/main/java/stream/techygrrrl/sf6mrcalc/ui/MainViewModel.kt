package stream.techygrrrl.sf6mrcalc.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import stream.techygrrrl.sf6mrcalc.utils.SF6Utils

/**
 * One big ass view model to share across screens, for now, because the app is tiny.
 */
class MainViewModel : ViewModel() {

    // region MR VS Win/Lose

    private var _player1MrInput = MutableStateFlow<String>("")
    val player1Input: StateFlow<String> = _player1MrInput

    val player1Mr: StateFlow<Int> = player1Input
        .map { input ->
            input.filter { char -> char.isDigit() }.toIntOrNull() ?: 0
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0,
        )

    private var _player2MrInput = MutableStateFlow<String>("")
    val player2Input: StateFlow<String> = _player2MrInput

    val player2Mr: StateFlow<Int> = player2Input
        .map { input ->
            input.filter { char -> char.isDigit() }.toIntOrNull() ?: 0
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0,
        )

    // Winnable
    val player1MrResult: StateFlow<PlayerMasterRateResult> =
        combine(player1Mr, player2Mr) { p1mr, p2mr ->
            PlayerMasterRateResult(
                winnable = SF6Utils.calculateWinnableMr(p1mr, p2mr),
                losable = SF6Utils.calculateLosableMr(p1mr, p2mr),
            )
        }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                PlayerMasterRateResult(0, 0),
            )
    val player2MrResult: StateFlow<PlayerMasterRateResult> =
        combine(player1Mr, player2Mr) { p1mr, p2mr ->
            PlayerMasterRateResult(
                winnable = SF6Utils.calculateWinnableMr(p2mr, p1mr),
                losable = SF6Utils.calculateLosableMr(p2mr, p1mr),
            )
        }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                PlayerMasterRateResult(0, 0),
            )

    /**
     * MR VS Win/Lose screen
     * Player 1 MR changed
     */
    fun onPlayer1MrChange(input: String) {
        _player1MrInput.value = input
    }

    /**
     * MR VS Win/Lose screen
     * Player 2 MR changed
     */
    fun onPlayer2MrChange(input: String) {
        _player2MrInput.value = input
    }

    data class PlayerMasterRateResult(
        val winnable: Int,
        val losable: Int,
    )

    // endregion MR VS Win/Lose


    // region MR Reset

    private var _currentMrInput = MutableStateFlow<String>("")
    val currentMrInput: StateFlow<String> = _currentMrInput

    val currentMr: StateFlow<Int> = currentMrInput
        .map { input ->
            input.filter { char -> char.isDigit() }.toIntOrNull() ?: 0
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0,
        )

    val mrResetValue: StateFlow<Int> = currentMr
        .map { SF6Utils.calculateResetMr(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0,
        )

    fun onCurrentMrChange(input: String) {
        _currentMrInput.value = input
    }

    // endregion MR Reset
}
