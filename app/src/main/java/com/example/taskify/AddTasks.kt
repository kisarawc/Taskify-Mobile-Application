package com.example.taskify

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class AddTasks : AppCompatActivity() {
    var title_input: EditText? = null
    var description_input: EditText? = null
    var add_button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tasks)
        title_input = findViewById(R.id.title_input)
        description_input = findViewById<EditText>(R.id.description_input)
        add_button = findViewById(R.id.add_button)
        add_button?.setOnClickListener(View.OnClickListener {
            val myDB = Database(this@AddTasks)
            myDB.addTask(title_input?.getText().toString().trim { it <= ' ' },
                description_input?.getText().toString().trim { it <= ' ' })

        })
    }
}