package com.ricamgar.notify.domain.group.usecase

import com.ricamgar.notify.domain.group.model.Group
import com.ricamgar.notify.domain.group.repository.GroupsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetGroups
@Inject constructor(private val groupsRepository: GroupsRepository){
  fun execute(): Single<List<Group>> = groupsRepository.groups()
}