package com.impacta.exercicio1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SobreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)

        val btnVoltar = findViewById<Button>(R.id.btn_voltar_sobre)
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}

