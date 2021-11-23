package com.fic.muei.lactachain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FarmData(val code: Int,
               val province: String,
               val town: String,
               val name: String): Parcelable