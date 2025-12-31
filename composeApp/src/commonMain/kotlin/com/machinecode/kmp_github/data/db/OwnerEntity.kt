package com.machinecode.kmp_github.data.db

data class OwnerEntity(/* @PrimaryKey*/ val ownerTableId: Int,
                       val login: String? = null,
                       val nodeId: String? = null,
                       val avatarUrl: String? = null,
                       val type: String? = null,
                       val siteAdmin: Boolean? = null
)