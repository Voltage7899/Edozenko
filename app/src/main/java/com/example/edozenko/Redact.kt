package com.example.edozenko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edozenko.databinding.ActivityMainBinding
import com.example.edozenko.databinding.ActivityRedactBinding
import com.google.firebase.database.*

class Redact : AppCompatActivity() {
    lateinit var binding: ActivityRedactBinding
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    var tourListAdapter:RecyclerAdapterTours?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRedactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newTour.setOnClickListener {
            startActivity(Intent(this,NewTour::class.java))
        }

        binding.recRedact.layoutManager= LinearLayoutManager(this)
        tourListAdapter= RecyclerAdapterTours()
        binding.recRedact.adapter=tourListAdapter
        tourListAdapter?.loadListToAdapter(getList())
    }

    fun getList():ArrayList<TourModel>{
        val list=ArrayList<TourModel>()
        database.child("Tour").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for(i in snapshot.children){
                    val tour= i.getValue(TourModel::class.java)
                    if(tour!=null){
                        list.add(tour)
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return list
    }

    override fun onStart() {
        super.onStart()
        tourListAdapter?.loadListToAdapter(getList())
    }
}