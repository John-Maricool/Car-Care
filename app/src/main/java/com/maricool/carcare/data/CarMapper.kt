package com.maricool.carcare.data

import com.maricool.carcare.data.entities.CarDetails
import com.maricool.carcare.data.interfaces.Mapper
import com.maricool.carcare.data.source.local.CarModel

object CarMapper: Mapper<CarModel, CarDetails> {
    override fun mapToEntity(model: CarModel): CarDetails {
        return CarDetails(
            model = model.carModel,
            carColor = model.carColor,
            plateNumber = model.carPlateNumber,
            vinNumber = model.vinNumber,
            area= model.area,
            image = model.carImage,
            carId = model.carId,
            date = model.dateAdded
        )
    }

    override fun mapToModel(entity: CarDetails): CarModel {
        return CarModel(
            carImage = entity.image,
            carModel = entity.model,
            carColor = entity.carColor,
            carPlateNumber = entity.plateNumber,
            area = entity.area,
            vinNumber = entity.vinNumber
        )
    }

    fun mapAllToEntity(model: List<CarModel>): List<CarDetails>{
        return model.map{ mapToEntity(it)}
    }
}