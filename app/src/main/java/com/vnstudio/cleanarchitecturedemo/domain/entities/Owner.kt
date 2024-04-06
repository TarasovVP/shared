package com.vnstudio.cleanarchitecturedemo.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    var login: String? = null,
    var ownerId: Long? = null,
    var avatarUrl: String? = null,
) : Parcelable