package com.example.architecturesample.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.androidx.viewmodel.scope.BundleDefinition
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

inline fun <reified T : ViewModel> Fragment.savedStateViewModel(
    qualifier: Qualifier? = null,
    noinline state: BundleDefinition = { requireArguments() },
    noinline parameters: ParametersDefinition? = null,
): Lazy<T> = stateViewModel(qualifier, state, parameters)
