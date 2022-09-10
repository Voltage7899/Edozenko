package com.example.edozenko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edozenko.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(),RecyclerAdapterCountry.ClickListener {
    lateinit var binding: ActivityMainBinding
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    var countryListAdapter:RecyclerAdapterCountry?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sign.setOnClickListener {
            startActivity(Intent(this,Sign::class.java))
        }
        binding.find.setOnClickListener {

            countryListAdapter?.loadListToAdapter(findList(binding.findText.text.toString()))

        }

        binding.recCountry.layoutManager= LinearLayoutManager(this)
        countryListAdapter= RecyclerAdapterCountry(this)
        binding.recCountry.adapter=countryListAdapter
        countryListAdapter?.loadListToAdapter(getList())
    }

    fun getList():ArrayList<Country>{
        val list=ArrayList<Country>()
        database.child("Country").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for(i in snapshot.children){
                    val country= i.getValue(Country::class.java)
                    if(country!=null){
                        list.add(country)
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return list
    }
    fun findList(findText:String):ArrayList<Country>{
        val list=ArrayList<Country>()
        val id = findText.substring(0,2)
        database.child("Country").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for(i in snapshot.children){
                    val country= i.getValue(Country::class.java)
                    if(country!=null){
                        if(country.idC==id){
                            list.add(country)
                        }

                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return list
    }

    override fun onClick(country: Country) {
        startActivity(Intent(this, Tours::class.java).apply {
            putExtra("country",country.country)

        })
    }
}