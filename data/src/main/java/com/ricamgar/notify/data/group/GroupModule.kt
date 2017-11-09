package com.ricamgar.notify.data.group

import com.ricamgar.notify.domain.group.repository.GroupsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class GroupModule {

  @Singleton
  @Binds
  internal abstract fun bindGroupsRepository(groupsSqlRepository: GroupsSqlRepository): GroupsRepository
}