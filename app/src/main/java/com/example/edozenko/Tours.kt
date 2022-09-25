package com.example.edozenko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edozenko.databinding.ActivityMainBinding
import com.example.edozenko.databinding.ActivityToursBinding
import com.google.firebase.database.*

class Tours : AppCompatActivity() {
    lateinit var binding: ActivityToursBinding
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    var tourListAdapter:RecyclerAdapterTours?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityToursBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recTour.layoutManager= LinearLayoutManager(this)
        tourListAdapter= RecyclerAdapterTours()
        binding.recTour.adapter=tourListAdapter
        tourListAdapter?.loadListToAdapter(getList())
    }

    fun getList():ArrayList<TourModel>{
        val item = intent.getStringExtra("country")
        val list=ArrayList<TourModel>()
        database.child("Tour").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for(i in snapshot.children){
                    val tour= i.getValue(TourModel::class.java)
                    if(tour!=null){
                        if(tour.country==item)
                        {
                            list.add(tour)
                        }

                    }

                }
                tourListAdapter?.loadListToAdapter(list)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return list
    }
}