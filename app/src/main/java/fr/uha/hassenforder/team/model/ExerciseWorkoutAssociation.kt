package fr.uha.hassenforder.team.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "ewas",
    primaryKeys = ["eid", "wid"],
    indices = [Index("eid"), Index("wid")],
)
class ExerciseWorkoutAssociation (
    val eid: Long,
    val wid: Long
)