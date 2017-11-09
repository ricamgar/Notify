package com.ricamgar.notify.data.group

import com.ricamgar.notify.data.database.AppDatabase
import com.ricamgar.notify.domain.group.model.Group
import com.ricamgar.notify.domain.group.repository.GroupsRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

internal class GroupsSqlRepository
@Inject constructor(database: AppDatabase): GroupsRepository {

  private val groupsDao = database.groupModel()
  private val mapper = GroupToEntity()

  override fun groups(): Single<List<Group>> {
    return groupsDao.groups()
  }

  override fun addGroup(group: Group): Completable {
    return Completable.fromCallable { groupsDao.insert(mapper.map(group)) }
  }
}