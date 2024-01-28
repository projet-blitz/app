package com.blitz.model

class stm {

    data class Route(val routeId: String, val routeName: String, val direction1: String, val direction2: String)
    data class Stop(val stopId: String?, val stopName: String)
}