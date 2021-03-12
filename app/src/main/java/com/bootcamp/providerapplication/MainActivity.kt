package com.bootcamp.providerapplication

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var  notesRecycler:RecyclerView
    lateinit var noteRefreshButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        instanciarComponentes()
        onClickButton()

        getContentProvider()
    }
    private fun instanciarComponentes(){
        noteRefreshButton = findViewById(R.id.cliente_button_refresh)
        notesRecycler = findViewById(R.id.client_list)
    }

    private fun onClickButton(){
        noteRefreshButton.setOnClickListener {
            getContentProvider()
        }
    }

    private fun getContentProvider(){
        try {
            val url = "content://com.courses.applicationcontentprovider.provider/notes"
            val data = Uri.parse(url)
            val cursor: Cursor? = contentResolver.query(data, null, null,null, "title")
            notesRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ClientAdapter(cursor as Cursor)
            }
        }catch (ex: Exception){
            ex.printStackTrace()
        }
    }
}