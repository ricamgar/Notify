package com.ricamgar.notify.domain.group.repository

import com.ricamgar.notify.domain.group.model.Group
import io.reactivex.Completable
import io.reactivex.Single

interface GroupsRepository {
  fun groups(): Single<List<Group>>
  fun addGroup(group: Group): Completable
}