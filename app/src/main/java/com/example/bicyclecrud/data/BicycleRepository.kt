package com.example.bicyclecrud.data

import com.example.bicyclecrud.model.Bicycle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BicycleRepository @Inject constructor(
    private val dao: BicycleDao
) {

    val bicycles: Flow<List<Bicycle>> = dao.getAll()

    suspend fun insert(bicycle: Bicycle) {
        dao.insert(bicycle)
    }

    suspend fun update(bicycle: Bicycle) {
        dao.update(bicycle)
    }

    suspend fun deleteById(id: Int) {
        dao.softDelete(id)
    }

    suspend fun softDelete(bicycle: Bicycle) {
        dao.softDelete(bicycle.id)
    }

    suspend fun populateDefaultsIfEmpty() {
        if (dao.countAll() == 0) {
            dao.insert(
                Bicycle(
                    manufacturer = "Specialized",
                    model = "Roubaix SL8 Sport",
                    price = 1200000,
                    isActive = true
                )
            )
            dao.insert(
                Bicycle(
                    manufacturer = "Giant",
                    model = "TCR Advanced 1 Disc",
                    price = 1150000,
                    isActive = false
                )
            )
            dao.insert(
                Bicycle(
                    manufacturer = "Merida",
                    model = "Scultura 6000",
                    price = 1100000,
                    isActive = true,
                    isDeleted = true
                )
            )
        }
    }
}