package com.fic.muei.lactachain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MilkCollectionDataItem(val code: Int?,
                                  val volumn: Int,
                                  val transporterName: String,
                                  val transporterCode: Int,
                                  val date: String
) : Parcelable
