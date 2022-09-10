package com.example.edozenko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.edozenko.databinding.ActivityMainBinding
import com.example.edozenko.databinding.ActivityNewTourBinding
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class NewTour : AppCompatActivity() {
    lateinit var binding: ActivityNewTourBinding
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Tour")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewTourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addd.setOnClickListener {
            val id = binding.addCountry.text.toString().substring(0,2) + binding.addNameTour.text.toString().substring(0,2)
            val tour=TourModel(id,binding.addCountry.text.toString(),binding.addNameTour.text.toString(),binding.addPrice.text.toString(),binding.addData.text.toString(),binding.addNumberNight.text.toString(),binding.addLink.text.toString())

            database.addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.child(id).exists()){
                        Toast.makeText(this@NewTour,"Уже есть", Toast.LENGTH_SHORT).show()
                    }
                    else{

                        database.child(id).setValue(tour)
                        finish()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }

        binding.addImage.setOnClickListener {
            try {
                Picasso.get().load(binding.addLink.text.toString()).fit().into(binding.addImage)
            }catch (ex:Exception){
                Toast.makeText(this,"Нет ссылки на картинку", Toast.LENGTH_SHORT).show()
            }

        }
    }

}