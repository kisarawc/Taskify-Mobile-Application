package com.example.taskify

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AllTasks : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var add_button: FloatingActionButton? = null
    var empty_imageview: ImageView? = null
    var no_data: TextView? = null
    var myDB: Database? = null
    var task_id: ArrayList<String>? = null
    var task_title: ArrayList<String>? = null
    var task_description: ArrayList<String>? = null
    var customAdapter: CustomAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tasks)
        recyclerView = findViewById(R.id.recyclerView)
        add_button = findViewById(R.id.add_button)
        empty_imageview = findViewById(R.id.empty_imageview)
        no_data = findViewById(R.id.no_data)
        add_button?.setOnClickListener(View.OnClickListener {
            val intent = Intent(
                this@AllTasks,
                AddTasks::class.java
            )
            startActivity(intent)
        })
        myDB = Database(this@AllTasks)
        task_id = ArrayList()
        task_title = ArrayList()
        task_description = ArrayList()
        storeDataInArrays()
        customAdapter = CustomAdapter(
            this@AllTasks, this, task_id!!, task_title!!,
            task_description!!
        )
        recyclerView?.setAdapter(customAdapter)
        recyclerView?.setLayoutManager(LinearLayoutManager(this@AllTasks))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            recreate()
        }
    }

    fun storeDataInArrays() {
        val cursor: Cursor? = myDB?.readAllData()
        if (cursor != null) {
            if (cursor.count == 0) {
                empty_imageview!!.setVisibility(View.VISIBLE)
                no_data!!.visibility = View.VISIBLE
            } else {
                while (cursor.moveToNext()) {
                    task_id!!.add(cursor.getString(0))
                    task_title!!.add(cursor.getString(1))
                    task_description!!.add(cursor.getString(2))
                }
                empty_imageview!!.setVisibility(View.GONE)
                no_data!!.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all) {
            confirmDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to delete all Data?")
        builder.setPositiveButton(
            "Yes"
        ) { dialogInterface, i ->
            val myDB = Database(this@AllTasks)
            myDB.deleteAllData()
            //Refresh Activity
            val intent = Intent(
                this@AllTasks,
                MainActivity::class.java
            )
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton(
            "No"
        ) { dialogInterface, i -> }
        builder.create().show()
    }
}

