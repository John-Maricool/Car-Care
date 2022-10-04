package com.maricool.carcare.data.interfaces

interface Mapper<Model, Entity> {
    fun mapToEntity(model: Model): Entity
    fun mapToModel(entity: Entity): Model
}