package com.example.todoapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var taskList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var textEmpty: TextView

    private fun updateEmptyState() {
        textEmpty.visibility =
            if (taskList.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val listViewTasks = findViewById<ListView>(R.id.listViewTasks)
        val textEmpty = findViewById<TextView>(R.id.textEmpty)

        sharedPreferences = getSharedPreferences("tasks", MODE_PRIVATE)

        taskList = loadTasks()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        listViewTasks.adapter = adapter

        buttonAdd.setOnClickListener {
            val task = editTextTask.text.toString()
            if (task.isNotEmpty()) {
                taskList.add(task)
                adapter.notifyDataSetChanged()
                saveTasks()
                editTextTask.text.clear()
            } else {
                Toast.makeText(this, "Enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        listViewTasks.setOnItemLongClickListener { _, _, position, _ ->
            taskList.removeAt(position)
            adapter.notifyDataSetChanged()
            saveTasks()
            true
        }
    }


    private fun saveTasks() {
        val editor = sharedPreferences.edit()
        editor.putStringSet("taskSet", taskList.toSet())
        editor.apply()
    }

    private fun loadTasks(): ArrayList<String> {
        val taskSet = sharedPreferences.getStringSet("taskSet", HashSet())
        return ArrayList(taskSet!!)
    }
}



