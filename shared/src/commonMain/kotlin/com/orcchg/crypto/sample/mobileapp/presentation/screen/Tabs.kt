package com.orcchg.crypto.sample.mobileapp.presentation.screen

import androidx.compose.runtime.Immutable
import cryptosamplekmpmobileapp.shared.generated.resources.Res
import cryptosamplekmpmobileapp.shared.generated.resources.label_tab_all
import cryptosamplekmpmobileapp.shared.generated.resources.label_tab_favourite
import org.jetbrains.compose.resources.StringResource

@Immutable
enum class Tabs(val resId: StringResource) {
    ALL(resId = Res.string.label_tab_all),
    FAVOURITES(resId = Res.string.label_tab_favourite)
}
