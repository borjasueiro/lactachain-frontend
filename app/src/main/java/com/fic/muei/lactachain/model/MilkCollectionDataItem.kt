package com.fic.muei.lactachain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MilkCollectionDataItem(val code: Int?,
                                  val transporterName: String,
                                  val date: String) : Parcelable
