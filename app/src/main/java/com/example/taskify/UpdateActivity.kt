package com.example.taskify

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class UpdateActivity : AppCompatActivity() {
    var title_input: EditText? = null
    var description_input: EditText? = null
    var update_button: Button? = null
    var delete_button: Button? = null
    var id: String? = null
    var title: String? = null
    var description: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        title_input = findViewById<EditText>(R.id.title_input2)
        description_input = findViewById<EditText>(R.id.description_input2)
        update_button = findViewById<Button>(R.id.update_button)
        delete_button = findViewById<Button>(R.id.delete_button)

        //First we call this
        andSetIntentData

        //Set actionbar title after getAndSetIntentData method
        val ab = supportActionBar
        ab?.title = title
        update_button?.setOnClickListener(View.OnClickListener { //And only then we call this
            val myDB = Database(this@UpdateActivity)
            title = title_input?.getText().toString().trim { it <= ' ' }
            description = description_input?.getText().toString().trim { it <= ' ' }
            id?.let { it1 -> myDB.updateData(it1, title, description) }
        })
        delete_button?.setOnClickListener(View.OnClickListener { confirmDialog() })
    }

    private val andSetIntentData: Unit
        get() {
            if (intent.hasExtra("id") && intent.hasExtra("title") &&
                intent.hasExtra("description")
            ) {
                //Getting Data from Intent
                id = intent.getStringExtra("id")
                title = intent.getStringExtra("title")
                description = intent.getStringExtra("description")

                //Setting Intent Data
                title_input!!.setText(title)
                description_input!!.setText(description)
                Log.d("stev", "$title $description")
            } else {
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
            }
        }

    private fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete $title ?")
        builder.setMessage("Are you sure you want to delete $title ?")
        builder.setPositiveButton(
            "Yes"
        ) { _, _ ->
            val myDB = Database(this@UpdateActivity)
            id?.let { myDB.deleteOneRow(it) }
            finish()
        }
        builder.setNegativeButton(
            "No"
        ) { _, _ -> }
        builder.create().show()
    }
}