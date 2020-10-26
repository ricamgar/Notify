package com.ricamgar.notify.data.group

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ricamgar.notify.domain.group.model.Group
import io.reactivex.Single

@Dao
internal abstract class GroupDao {

  @Query("SELECT * FROM groups ORDER BY name")
  abstract fun groups(): Single<List<Group>>

  @Insert
  abstract fun insert(groupEntity: GroupEntity)
}