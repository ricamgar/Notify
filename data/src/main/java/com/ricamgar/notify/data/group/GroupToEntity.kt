package com.ricamgar.notify.data.group

import com.ricamgar.notify.data.mapper.Mapper
import com.ricamgar.notify.domain.group.model.Group

internal class GroupToEntity: Mapper<Group, GroupEntity> {

  override fun map(group: Group): GroupEntity {
    return GroupEntity(
      group.id,
      group.name
    )
  }
}