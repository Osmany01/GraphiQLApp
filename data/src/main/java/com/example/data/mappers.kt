package com.example.data

import com.example.data.api.model.ApiStation
import com.example.domain.model.Station

fun ApiStation.toDomain(): Station =
    Station(name,
    latitude,
    longitude,
    picture)