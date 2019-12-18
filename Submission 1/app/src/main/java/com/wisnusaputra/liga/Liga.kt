package com.wisnusaputra.liga

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Liga  (val name: String ?, val image: Int?, val desc: String? ) : Parcelable