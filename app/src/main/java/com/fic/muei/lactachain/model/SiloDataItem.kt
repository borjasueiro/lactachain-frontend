package com.fic.muei.lactachain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SiloDataItem(val code: Int?,
                        val type: String
): Parcelable