package com.di.civ

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import java.io.File

class MapController {

    lateinit var labelTerrenoSeleccionado: Label
    lateinit var labelPosicionActual: Label
    lateinit var root : GridPane
    private val mapa = Mapa()
    private var subMapa = mapa.obtenerSubMapa()


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
                root.add(vBox, columna, fila)
                vBox.alignment = Pos.CENTER
            }
        root.hgap = 5.0
        root.vgap = 5.0
        root.padding = Insets(50.0, 50.0, 50.0, 50.0)
    }

    private fun rellenarGirdPaneConMapa(subMapa: MutableList<MutableList<Terreno>>) {
        var pos = 0
        subMapa.forEach { terrenos ->
            terrenos.forEach { terreno ->
                val vBox = root.children[pos]
                vBox as VBox
                vBox.style = "-fx-background-color: ${terreno.colorTerreno};" // $terreno.color

                val imageView = vBox.children[0] as ImageView
                val f = File(terreno.imagen)
                imageView.fitHeight = 60.0
                imageView.fitWidth = 60.0
                imageView.image = Image(f.toURI().toURL().toString())

                val label = vBox.children[1] as Label
                label.text = terreno.nombre
                label.maxWidth = 80.0
                label.minWidth = 80.0
                label.style = "-fx-background-color: ${terreno.colorTexto};"
                label.alignment = Pos.CENTER

                vBox.setOnMouseClicked {
                    actualizarTerrenoSeleccionado(terreno)
                }

                pos++
            }
        }
        labelPosicionActual.text = String.format("Posición Actual: (%s,%s)", mapa.obtenerPosicionActual().fila, mapa.obtenerPosicionActual().columna)
    }

    fun actualizarTerrenoSeleccionado(terreno: Terreno) {
        labelTerrenoSeleccionado.text = String.format("El Terreno seleccionado es: %s", terreno.nombre)
    }

    fun moverArriba() {
        println("moverArriba")
        mapa.moverArriba()
        rellenarGirdPaneConMapa(mapa.obtenerSubMapa())
    }

    fun moverAbajo() {
        println("moverAbajo")
        mapa.moverAbajo()
        rellenarGirdPaneConMapa(mapa.obtenerSubMapa())
    }

    fun moverIzquierda() {
        println("moverIzquierda")
        mapa.moverIzquierda()
        rellenarGirdPaneConMapa(mapa.obtenerSubMapa())
    }

    fun moverDerecha() {
        println("moverDerecha")
        mapa.moverDerecha()
        rellenarGirdPaneConMapa(mapa.obtenerSubMapa())
    }

    fun centerMap(mouseEvent: MouseEvent) {
        mapa.cambiarPosicionActual(Mapa.Posicion(0,0))
        rellenarGirdPaneConMapa(mapa.obtenerSubMapa())
    }
}