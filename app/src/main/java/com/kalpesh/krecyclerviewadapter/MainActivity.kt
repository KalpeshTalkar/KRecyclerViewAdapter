package com.kalpesh.krecyclerviewadapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var singleItem = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        // Usage for single row/view
        //setSingleItemAdapter();

        // Usage for multiple rows/views
        setMultipleItemsAdapter()
    }

    fun changeAdapter(view: View) {
        if (singleItem) {
            setMultipleItemsAdapter()
        } else {
            setSingleItemAdapter()
        }
    }

    private fun setSingleItemAdapter() {
        singleItem = true

        val data = ArrayList<String>()
        for (i in 0..19) {
            data.add("String$i")
        }

        val adapter = KRecyclerViewAdapter(this, data, object : KRecyclerViewHolderCallBack {
            override fun onCreateViewHolder(parent: ViewGroup): KRecyclerViewHolder {
                val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.simple_item, parent, false)
                return SimpleHolder(layoutView)
            }

            override fun onHolderDisplayed(holder: KRecyclerViewHolder, position: Int) {
                Log.i("onHolderDisplayed", "Holder Displayed At: $position")
            }
        }, KRecyclerViewItemClickListener { holder, itemObject, itemPosition ->
            Toast.makeText(
                this@MainActivity,
                "Clicked position $itemPosition",
                Toast.LENGTH_SHORT
            ).show()
        })

        recyclerView.adapter = adapter
        adapter.allowsMultipleSelection = true
        adapter.deselectItemOnClickIfSelected = true
        adapter.notifyDataSetChanged()
        val selection = ArrayList<Int>()
        selection.add(0)
        selection.add(2)
        selection.add(3)
        selection.add(7)
        selection.add(8)
        adapter.setSelectedIndexes(selection)
    }

    private fun setMultipleItemsAdapter() {
        singleItem = false

        val data = ArrayList<Any>()
        for (i in 0..19) {
            if (i % 2 == 0) {
                data.add("String$i")
            } else {
                val myObject = MyObject()
                myObject.title = "Title$i"
                myObject.description = "Description$i"
                data.add(myObject)
            }
        }

        val adapter2 = KRecyclerViewAdapter(
            this,
            data,
            object : KRecyclerViewHolderViewTypeCallBack {
                override fun recyclerItemViewType(viewType: Int, itemObject: Any): Int {
                    return if (viewType % 2 == 0) {
                        1
                    } else {
                        2
                    }
                }

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KRecyclerViewHolder {
                    return if (viewType == 1) {
                        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.simple_item, null)
                        SimpleHolder(layoutView)
                    } else {
                        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.another_item, null)
                        AnotherHolder(layoutView)
                    }
                }

                override fun onHolderDisplayed(holder: KRecyclerViewHolder, position: Int) {
                    Log.i("onHolderDisplayed", "Holder Displayed At: $position")
                }
            },
            KRecyclerViewItemClickListener { holder, itemObject, itemPosition ->
                Toast.makeText(
                    this@MainActivity,
                    "Clicked position $itemPosition",
                    Toast.LENGTH_SHORT
                ).show()
            })

        adapter2.allowsSingleSelection = true
        adapter2.deselectItemOnClickIfSelected = true

        recyclerView.adapter = adapter2
        adapter2.notifyDataSetChanged()
    }

}
