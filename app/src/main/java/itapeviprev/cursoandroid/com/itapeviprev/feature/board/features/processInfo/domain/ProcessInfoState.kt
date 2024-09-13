package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.processInfo.domain

sealed class ProcessInfoState {
    data object Initial : ProcessInfoState()
    data object Loading : ProcessInfoState()
    data object NoDataFound : ProcessInfoState()
    data object Complete : ProcessInfoState()
    data object Error : ProcessInfoState()
}