package com.di.civ

import kotlin.random.Random

class Mapa {

    class PosicionActual(var fila : Int, var coluna : Int)

    private var posicionActual = PosicionActual(0,0)

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

    fun obtenerSubMapa(filaCentro: Int, columnaCentro: Int, vision: Int) : MutableList<MutableList<Terreno>>{



        val subMapa = MutableList(Configuracion.filasCampoVision) {
            MutableList(Configuracion.columnasCampoVision) {
                Terreno.crearTerrenoDesconocido()
            }
        }
        //var filaActual = 0
        for ((filaActual, filaActualMapaGrande) in ((filaCentro - vision) .. (filaCentro + vision)).withIndex()) {
            println("filaActualMapaGrande = $filaActualMapaGrande")
            println("filaActual = $filaActual")
            for ((columnaActual, columnaActualMapaGrande) in ((columnaCentro - vision)..(columnaCentro + vision)).withIndex()) {
                println("columnaActualMapaGrande = $columnaActualMapaGrande")
                println("ColumnaActual = $columnaActual")
                if (!(columnaActualMapaGrande < 0 || filaActualMapaGrande < 0 || columnaActualMapaGrande >= Configuracion.filasMapa || filaActualMapaGrande >= Configuracion.columnasMapa)){
                    subMapa[filaActual][columnaActual] = matriz[filaActualMapaGrande][columnaActualMapaGrande]
                }
            }
        }
        println(matriz)
        println(subMapa)
        return subMapa
    }


}