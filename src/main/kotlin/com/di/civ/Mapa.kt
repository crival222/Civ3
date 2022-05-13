package com.di.civ

import kotlin.random.Random

class Mapa {

    class Posicion(var fila : Int, var columna : Int)

    private var posicionActual = Posicion(0,0)

    var matriz = MutableList(Configuracion.filasMapa) {
        MutableList(Configuracion.columnasMapa) {

            when (Random.nextInt(0, 100)) {
                in 0..24 -> Terreno.crearLlanura()
                in 25..44 -> Terreno.crearColina()
                in 45..64 -> Terreno.crearBosque()
                in 65..69 -> Terreno.crearCiudad()
                in 70..89 -> Terreno.crearMar()
                in 90..99 -> Terreno.crearMontana()
                else -> Terreno.crearTerrenoDesconocido()
            }
        }
    }

    fun moverArriba() {
        posicionActual.fila--
    }
    fun moverAbajo() {
        posicionActual.fila++
    }

    fun moverIzquierda() {
        posicionActual.columna--
    }

    fun moverDerecha() {
        posicionActual.columna++
    }

    fun obtenerSubMapa() : MutableList<MutableList<Terreno>>{
        return obtenerSubMapaP()
    }

    fun obtenerPosicionActual(): Posicion {
        return posicionActual
    }

    fun cambiarPosicionActual(posicion: Posicion) {
        posicionActual = posicion
    }

    private fun obtenerSubMapaP(posicionCentral: Posicion = posicionActual, vision: Int = Configuracion.rangoVision) : MutableList<MutableList<Terreno>>{

        val subMapa = MutableList(Configuracion.filasCampoVision) {
            MutableList(Configuracion.columnasCampoVision) {
                Terreno.crearTerrenoDesconocido()
            }
        }
        for ((filaActual, filaActualMapaGrande) in ((posicionCentral.fila - vision) .. (posicionCentral.fila + vision)).withIndex()) {
            println("filaActualMapaGrande = $filaActualMapaGrande")
            println("filaActual = $filaActual")
            for ((columnaActual, columnaActualMapaGrande) in ((posicionCentral.columna - vision)..(posicionCentral.columna + vision)).withIndex()) {
                println("columnaActualMapaGrande = $columnaActualMapaGrande")
                println("ColumnaActual = $columnaActual")

                if (!(columnaActualMapaGrande < 0 || filaActualMapaGrande < 0 || columnaActualMapaGrande >= Configuracion.columnasMapa || filaActualMapaGrande >= Configuracion.filasMapa)){
                    subMapa[filaActual][columnaActual] = matriz[filaActualMapaGrande][columnaActualMapaGrande]
                }
            }
        }
        println(matriz)
        println(subMapa)
        return subMapa
    }


}