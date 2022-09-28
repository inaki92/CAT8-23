package com.example.graphqlspaceexample.model

import com.example.graphqlspaceexample.LaunchListQuery

data class DomainLaunch(
    val missionName: String,
    val date: String,
    val rocket: DomainRocket,
    val ships: List<DomainShip>
)

data class DomainRocket(val rocketName: String)

data class DomainShip(
    val shipName: String,
    val image: String
)

fun List<LaunchListQuery.LaunchesPast?>.mapToDomainLaunch(): List<DomainLaunch> =
    this.map {
        DomainLaunch(
            missionName = it?.mission_name ?: "",
            date = it?.launch_date_local as? String ?: "",
            rocket = DomainRocket(
                rocketName = it?.rocket?.rocket_name ?: ""
            ),
            ships = it?.ships?.map { ship ->
                DomainShip(
                    shipName = ship?.name ?: "",
                    image = ship?.image ?: ""
                )
            } ?: emptyList()

        )
    }