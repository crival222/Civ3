package com.di.civ

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import java.io.File

class MapController {

    lateinit var root : GridPane
    private val mapa = Mapa()
    private var filaActual = 1
    private var columnaActual = 1
    private var subMapa = mapa.obtenerSubMapa(filaActual, columnaActual, Configuracion.rangoVision)
    lateinit var labelPosicionActual: Label

    fun initialize() {
        iniciarGridPane()
        rellenarGirdPaneConMapa(subMapa)
    }

    private fun iniciarGridPane() {
        for (fila in 0 until Configuracion.filasCampoVision)
            for (columna in 0 until Configuracion.columnasCampoVision) {
                val vBox = VBox()
                vBox.children.add(0, ImageView())
                vBox.children.add(1, Label("fila $fila columna $columna"))
                root.add(vBox, fila, columna)
                vBox.alignment = Pos.CENTER

            }
        root.hgap = 5.0
        root.vgap = 5.0
        root.padding = Insets(50.0, 50.0, 50.0, 50.0)
    }

    private fun rellenarGirdPaneConMapa(subMapa: MutableList<MutableList<Terreno>>) {
        var pos = 0
        subMapa.forEachIndexed { _, terrenos ->
            terrenos.forEachIndexed { _, terreno ->
                val vBox = root.children[pos]
                vBox as VBox
                vBox.style = "-fx-background-color:"+terreno.color+" ;"

                val imageView = vBox.children[0] as ImageView
                val f = File(terreno.imagen)
                imageView.fitHeight = 60.0
                imageView.fitWidth = 60.0
                imageView.image = Image(f.toURI().toURL().toString())

                val label = vBox.children[1] as Label
                label.text = terreno.nombre
                label.maxWidth = 80.0
                label.style = "-fx-background-color:"+terreno.color1+";"
                label.alignment = Pos.CENTER

                pos++
            }
        }
    }

    fun obtenerPosicionACtual() {
        labelPosicionActual.text = "mi posicion es ("+filaActual+","+columnaActual+")"
    }

    fun botonCentrarPulsado() {
        filaActual = 1
        columnaActual = 1
        subMapa = mapa.obtenerSubMapa(filaActual,columnaActual,Configuracion.rangoVision)
        rellenarGirdPaneConMapa(subMapa)
        obtenerPosicionACtual()
    }

    fun moverArriba() {
        columnaActual++
        subMapa = mapa.obtenerSubMapa(filaActual,columnaActual,Configuracion.rangoVision)
        rellenarGirdPaneConMapa(subMapa)
        obtenerPosicionACtual()
    }
    fun moverAbajo() {
        columnaActual--
        subMapa = mapa.obtenerSubMapa(filaActual,columnaActual,Configuracion.rangoVision)
        rellenarGirdPaneConMapa(subMapa)
        obtenerPosicionACtual()
    }
    fun moverDerecha() {
        filaActual--
        subMapa = mapa.obtenerSubMapa(filaActual,columnaActual,Configuracion.rangoVision)
        rellenarGirdPaneConMapa(subMapa)
        obtenerPosicionACtual()
    }
    fun moverIzquierda() {
        filaActual++
        subMapa = mapa.obtenerSubMapa(filaActual,columnaActual,Configuracion.rangoVision)
        rellenarGirdPaneConMapa(subMapa)
        obtenerPosicionACtual()
    }
}