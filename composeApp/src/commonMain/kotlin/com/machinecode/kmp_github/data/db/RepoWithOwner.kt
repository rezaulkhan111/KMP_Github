package com.machinecode.kmp_github.data.db

data class RepoWithOwner(
  /*  @Embedded */val repository: RepositoryEntity,

//    @Relation(
//        parentColumn = "ownerId", entityColumn = "ownerTableId"
//    )

    val owner: OwnerEntity
)