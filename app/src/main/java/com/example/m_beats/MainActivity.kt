package com.example.m_beats

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var option : Spinner

    private var mp: MediaPlayer?=null
    private var currentSong = mutableListOf(R.raw.rg1,R.raw.rg2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        option = findViewById<Spinner>(R.id.spinner1)

        var estilo: String
        val options = arrayOf("Selecione um gênero", "Arrocha", "Forró", "Gospel", "Hip Hop", "Pop", "Reggae", "Sertanejo Universitário")

        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                estilo = options.get(position)
                when(estilo) {
                    "Arrocha" -> {currentSong[0] = R.raw.ar1
                        currentSong[1] = R.raw.ar2}
                    "Forró" -> {currentSong[0] = R.raw.fr1
                        currentSong[1] = R.raw.fr2}
                    "Gospel" -> {currentSong[0] = R.raw.gp1
                        currentSong[1] = R.raw.gp2}
                    "Hip Hop" -> {currentSong[0] = R.raw.hp1
                        currentSong[1] = R.raw.hp2}
                    "Pop" -> {currentSong[0] = R.raw.pp1
                        currentSong[1] = R.raw.pp2}
                    "Reggae" -> {currentSong[0] = R.raw.rg1
                        currentSong[1] = R.raw.rg2}
                    "Sertanejo Universitário" -> {currentSong[0] = R.raw.su1
                        currentSong[1] = R.raw.su2}
                }
                Toast.makeText(this@MainActivity, estilo, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                estilo = "Selecione um gênero"
            }
        }

        play1.setOnClickListener(){
            startSong(currentSong[0])
        }
        play2.setOnClickListener() {
            startSong(currentSong[1])
        }
        stopBt.setOnClickListener(){
            stopSong(currentSong[0])
        }
    }
    private fun startSong(id: Int){
        mp?.reset()
        mp?.release()
        mp=null

        if(mp==null){
            mp= MediaPlayer.create(this,id)
            mp?.isLooping  = true
        }
        mp?.start()

    }
    private fun stopSong(id: Int){
        if(mp!=null){
            mp?.stop()
            mp?.reset()
            mp?.release()
            mp=null
        }
    }
}