package by.godevelopment.currencyexchangeapplication.testutils

import by.godevelopment.currencyexchangeapplication.domain.usecases.*
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import org.junit.Rule

open class ViewModelTest {

    @get:Rule
    val testViewModelScopeRule = TestViewModelScopeRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var moveItemToTopListByBaseUseCase: MoveItemToTopListByBaseUseCase

    @RelaxedMockK
    lateinit var roundRateValueUseCase: RoundRateValueUseCase
}