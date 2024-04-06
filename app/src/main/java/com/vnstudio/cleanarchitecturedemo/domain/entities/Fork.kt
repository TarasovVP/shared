package com.vnstudio.cleanarchitecturedemo.domain.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("forks")
data class Fork(
    @PrimaryKey
    var id: Long? = null,
    var name: String? = null,
    var fullName: String? = null,
    @Embedded("owner")
    var owner: Owner? = null,
    var htmlUrl: String? = null,
    var description: String? = null,
) : Parcelable