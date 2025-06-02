package com.impacta.exercicio1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var btnVerPrevisao: Button
    private lateinit var btnSobre: Button  // botão opcional para a tela de integrantes no futuro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnVerPrevisao = findViewById(R.id.btnVerPrevisao)
        btnSobre = findViewById(R.id.btnSobre)

        btnVerPrevisao.setOnClickListener {
            val intent = Intent(this, PrevisaoActivity::class.java)
            startActivity(intent)
        }

        btnSobre.setOnClickListener {
            val intent = Intent(this, SobreActivity::class.java)
            startActivity(intent)
        }
        val btnConfiguracoes = findViewById<Button>(R.id.btn_configuracoes)
        btnConfiguracoes.setOnClickListener {
            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }


    }
}