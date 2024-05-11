package com.example.taskify


import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AllTasks : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var add_button: FloatingActionButton? = null
//    var empty_imageview: ImageView? = null
    var no_data: TextView? = null
    var myDB: Database? = null
    var task_id: ArrayList<String>? = null
    var task_title: ArrayList<String>? = null
    var task_description: ArrayList<String>? = null
//    var customAdapter: CustomAdapter? = null
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(com.example.taskify.R.layout.activity_all_tasks)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        add_button = findViewById<FloatingActionButton>(R.id.addBtn)
//        empty_imageview = findViewById<ImageView>(R.id.empty_imageview)
//        no_data = findViewById<TextView>(R.id.no_data)
    add_button?.let { button ->
        button.setOnClickListener {
            val intent = Intent(this@AllTasks, AddTasks::class.java)
            startActivity(intent)
        }
    }
//        myDB = MyDatabaseHelper(this@MainActivity)
//        book_id = ArrayList()
//        book_title = ArrayList()
//        book_author = ArrayList()
//        book_pages = ArrayList()
//        storeDataInArrays()
//        customAdapter = CustomAdapter(
//            this@MainActivity, this, book_id, book_title, book_author,
//            book_pages
//        )
//        recyclerView.setAdapter(customAdapter)
//        recyclerView.setLayoutManager(LinearLayoutManager(this@MainActivity))
    }}

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 1) {
//            recreate()
//        }
//    }
//
//    fun storeDataInArrays() {
//        val cursor: Cursor = myDB.readAllData()
//        if (cursor.count == 0) {
//            empty_imageview!!.setVisibility(View.VISIBLE)
//            no_data!!.visibility = View.VISIBLE
//        } else {
//            while (cursor.moveToNext()) {
//                book_id!!.add(cursor.getString(0))
//                book_title!!.add(cursor.getString(1))
//                book_author!!.add(cursor.getString(2))
//                book_pages!!.add(cursor.getString(3))
//            }
//            empty_imageview!!.setVisibility(View.GONE)
//            no_data!!.visibility = View.GONE
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.my_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.delete_all) {
//            confirmDialog()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    fun confirmDialog() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Delete All?")
//        builder.setMessage("Are you sure you want to delete all Data?")
//        builder.setPositiveButton(
//            "Yes"
//        ) { dialogInterface, i ->
//            val myDB = Database(this@MainActivity)
//            myDB.deleteAllData()
//            //Refresh Activity
//            val intent = Intent(
//                this@MainActivity,
//                MainActivity::class.java
//            )
//            startActivity(intent)
//            finish()
//        }
//        builder.setNegativeButton(
//            "No"
//        ) { dialogInterface, i -> }
//        builder.create().show()
//    }
//}
//
