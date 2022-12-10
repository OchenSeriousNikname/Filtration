package com.example.filtration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val list = mutableListOf<String>()
    private var filterString = ""
    private val adapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val editTextInput = findViewById<EditText>(R.id.editTextInput)
        val editTextFilter = findViewById<EditText>(R.id.editTextFilter)

        button.setOnClickListener {
            list.add(editTextInput.text.toString())
            editTextInput.text.clear()
            render()
        }

        editTextFilter.addTextChangedListener {
            filterString = it.toString()
            render()
        }


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun render() {
        println(list.joinToString(", "))
        if (filterString.isBlank()) {
            adapter.updateList(list)
        } else {
            val filteredList = list.filter { it.contains(filterString, true) }
            adapter.updateList(filteredList)
        }
    }
}