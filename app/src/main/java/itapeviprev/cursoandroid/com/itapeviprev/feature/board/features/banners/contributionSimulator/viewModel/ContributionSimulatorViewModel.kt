package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.contributionSimulator.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.contributionSimulator.widgets.SimulationCardModel
import itapeviprev.cursoandroid.com.itapeviprev.widgets.decimalFormat
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class ContributionSimulatorViewModel @Inject constructor() : ViewModel() {
    val valueToSimulate = mutableStateOf("0")
    val contributionValue = mutableStateOf(0.0)
    val showSimulation = mutableStateOf(false)
    val effective_tax_rate = mutableStateOf("0")
    val firstLineLeftOver = mutableStateOf<Double>(0.0)
    val secondLineLeftOver = mutableStateOf<Double>(0.0)
    val thirdLineLeftOver = mutableStateOf(0.0)
    val fourthLineLeftOver = mutableStateOf(0.0)
    val fifthLineLeftOver = mutableStateOf(0.0)
    val sixthLineLeftOver = mutableStateOf(0.0)
    val seventhLineLeftOver = mutableStateOf(0.0)

    val isFinished = mutableStateOf(false)

    fun getListOfTableTitle(): List<Int> {
        return arrayListOf(
            R.string.salary,
            R.string.tax_rate,
            R.string.value
        )
    }

    fun getListOfSimulationValues(): List<SimulationCardModel> {
        return arrayListOf(
            SimulationCardModel(
                titleId = R.string.simulated_value,
                iconId = R.drawable.ic_dolar_sign,
                value = "R$${valueToSimulate.value}"
            ),
            SimulationCardModel(
                titleId = R.string.contribution_value,
                iconId = R.drawable.ic_dolar_sign,
                value = "R$${decimalFormat(contributionValue.value)}"
            ),
            SimulationCardModel(
                titleId = R.string.effective_tax_rate_of,
                iconId = R.drawable.ic_chart,
                value = "${effective_tax_rate.value}%"
            ),
        )
    }

    fun getListOfTableValues(): List<SimulationModel> {
        return arrayListOf(
            SimulationModel(
                salaryId = R.string.until_1045,
                taxRateValue = R.string.tax_rate_75,
                value = getCalculatedValue(SalaryRate.until1045)
            ),
            SimulationModel(
                salaryId = R.string.from_1045_to_2089,
                taxRateValue = R.string.tax_rate_9,
                value = getCalculatedValue(SalaryRate.from1045to2089)
            ),
            SimulationModel(
                salaryId = R.string.from_2089_to_3134,
                taxRateValue = R.string.tax_rate_12,
                value = getCalculatedValue(SalaryRate.from2089to3134)
            ),
            SimulationModel(
                salaryId = R.string.from_3134_to_6101,
                taxRateValue = R.string.tax_Rate_14,
                value = getCalculatedValue(SalaryRate.from3134to6101)
            ),
            SimulationModel(
                salaryId = R.string.from_6101_to_10448,
                taxRateValue = R.string.tax_rate_14_5,
                value = getCalculatedValue(SalaryRate.from6101to10448)
            ),
            SimulationModel(
                salaryId = R.string.from_10448_to_20896,
                taxRateValue = R.string.tax_rate_16_5,
                value = getCalculatedValue(SalaryRate.from10448to20896)
            ),
            SimulationModel(
                salaryId = R.string.from_20896_to_40747,
                taxRateValue = R.string.tax_rate_19,
                value = getCalculatedValue(SalaryRate.from20896to40747)
            ),
            SimulationModel(
                salaryId = R.string.beyond_40747,
                taxRateValue = R.string.tax_rate_22,
                value = 0.0
            )
        )
    }

    fun getCalculatedValue(valuePercentage: Double): Double {
        when (valuePercentage) {
            SalaryRate.until1045 -> {
                return if (valueToSimulate.value.toDouble() > 1100) {
                    firstLineLeftOver.value = valueToSimulate.value.toDouble() - 1045
                    SalaryRate.until1045
                } else {
                    0.0
                }
            }

            SalaryRate.from1045to2089 -> {
                if (firstLineLeftOver.value < 1103 && firstLineLeftOver.value != 0.0) {
                    val finalValue = firstLineLeftOver.value * 0.09
                    contributionValue.value = SalaryRate.until1045 + finalValue
                    isFinished.value = true
                    return finalValue
                } else if (!isFinished.value) {
                    secondLineLeftOver.value = firstLineLeftOver.value - 1103.48
                    return SalaryRate.from1045to2089
                }
            }

            SalaryRate.from2089to3134 -> {
                if (secondLineLeftOver.value < 1101 && secondLineLeftOver.value != 0.0) {
                    val finalValue = secondLineLeftOver.value * 0.12
                    contributionValue.value =
                        SalaryRate.until1045 + SalaryRate.from1045to2089 + finalValue
                    isFinished.value = true
                    return finalValue
                } else if (!isFinished.value) {
                    thirdLineLeftOver.value = secondLineLeftOver.value - 1101.73
                    return SalaryRate.from2089to3134
                }
            }

            SalaryRate.from3134to6101 -> {
                if (thirdLineLeftOver.value < 3128 && thirdLineLeftOver.value != 0.0) {
                    val finalValue = thirdLineLeftOver.value * 0.14
                    contributionValue.value =
                        SalaryRate.until1045 + SalaryRate.from1045to2089 + SalaryRate.from2089to3134 + finalValue
                    isFinished.value = true
                    return finalValue
                } else if (!isFinished.value) {
                    fourthLineLeftOver.value = thirdLineLeftOver.value - 3128.33
                    return SalaryRate.from3134to6101
                }
            }

            SalaryRate.from6101to10448 -> {
                if (fourthLineLeftOver.value < 4583 && fourthLineLeftOver.value != 0.0) {
                    val finalValue = fourthLineLeftOver.value * 0.145
                    contributionValue.value =
                        SalaryRate.until1045 + SalaryRate.from1045to2089 + SalaryRate.from2089to3134 + SalaryRate.from3134to6101 + finalValue
                    isFinished.value = true
                    return finalValue
                } else if (!isFinished.value) {
                    fifthLineLeftOver.value = fourthLineLeftOver.value - 4583.84
                    return SalaryRate.from6101to10448
                }
            }

            SalaryRate.from10448to20896 -> {
                if (fifthLineLeftOver.value < 11017 && fifthLineLeftOver.value != 0.0) {
                    val finalValue = fifthLineLeftOver.value * 0.165
                    contributionValue.value =
                        SalaryRate.until1045 + SalaryRate.from1045to2089 + SalaryRate.from2089to3134 + SalaryRate.from3134to6101 + SalaryRate.from6101to10448 + finalValue
                    isFinished.value = true
                    return finalValue
                } else if (!isFinished.value) {
                    sixthLineLeftOver.value = fifthLineLeftOver.value - 11017.42
                    return SalaryRate.from10448to20896
                }
            }

            SalaryRate.from20896to40747 -> {
                if (sixthLineLeftOver.value < 20933 && sixthLineLeftOver.value != 0.0) {
                    val finalValue = sixthLineLeftOver.value * 0.19
                    contributionValue.value =
                        SalaryRate.until1045 + SalaryRate.from1045to2089 + SalaryRate.from2089to3134 + SalaryRate.from3134to6101 + SalaryRate.from6101to10448 + SalaryRate.from10448to20896 + finalValue
                    isFinished.value = true
                    return finalValue
                } else if (!isFinished.value) {
                    seventhLineLeftOver.value = sixthLineLeftOver.value - 11017.42
                    return SalaryRate.from20896to40747
                }
            }

            else -> return 0.0
        }
        return 0.0
    }
}

data class SimulationModel(
    val salaryId: Int,
    val taxRateValue: Int,
    val value: Double,
)

object SalaryRate {
    const val until1045 = 82.50
    const val from1045to2089 = 99.31
    const val from2089to3134 = 132.21
    const val from3134to6101 = 437.97
    const val from6101to10448 = 664.66
    const val from10448to20896 = 1817.87
    const val from20896to40747 = 3977.29
}