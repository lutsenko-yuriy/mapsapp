package com.yurich.mapsapp.data.network.utils

import com.yurich.mapsapp.data.network.models.NetworkVehicle
import com.yurich.mapsapp.data.network.models.NetworkVehicleResponse
import com.yurich.mapsapp.domain.GeoCoordinates
import com.yurich.mapsapp.domain.Vehicle
import org.junit.Assert
import org.junit.Test

class NetworkResponseMappingKtTest {

    @Test
    fun `empty response to empty list`() {
        val response = NetworkVehicleResponse(emptyList())

        Assert.assertTrue(response.toVehicles().isEmpty())
    }

    @Test
    fun `some response to some list`() {
        val response = NetworkVehicleResponse(
            listOf(
                NetworkVehicle(
                    address = "Lesserstraße 170, 22049 Hamburg",
                    coordinates = listOf(
                        10.07526,
                        53.59301,
                        0.0
                    ),
                    engineType = "CE",
                    exterior = "UNACCEPTABLE",
                    fuel = 42,
                    interior = "UNACCEPTABLE",
                    name = "HH-GO8522",
                    vin = "WME4513341K565439"
                ),
                NetworkVehicle(
                    address = "Grosse Reichenstraße 7, 20457 Hamburg",
                    coordinates = listOf(
                        9.99622,
                        53.54847,
                        0.0
                    ),
                    engineType = "CE",
                    exterior = "UNACCEPTABLE",
                    fuel = 45,
                    interior = "GOOD",
                    name = "HH-GO8480",
                    vin = "WME4513341K412697"
                )
            )
        )

        val expectedValues = arrayOf(
            Vehicle(
                address = "Lesserstraße 170, 22049 Hamburg",
                coordinates = GeoCoordinates(
                    latitude = 53.59301,
                    longitude = 10.07526
                ),
                engineType = "CE",
                exterior = "UNACCEPTABLE",
                fuel = 42,
                interior = "UNACCEPTABLE",
                name = "HH-GO8522",
                vin = "WME4513341K565439"
            ),
            Vehicle(
                address = "Grosse Reichenstraße 7, 20457 Hamburg",
                coordinates = GeoCoordinates(
                    latitude = 53.54847,
                    longitude = 9.99622
                ),
                engineType = "CE",
                exterior = "UNACCEPTABLE",
                fuel = 45,
                interior = "GOOD",
                name = "HH-GO8480",
                vin = "WME4513341K412697"
            )
        )

        Assert.assertArrayEquals(expectedValues, response.toVehicles().toTypedArray())
    }

}