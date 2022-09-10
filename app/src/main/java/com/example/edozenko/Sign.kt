package com.example.edozenko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edozenko.databinding.ActivityMainBinding
import com.example.edozenko.databinding.ActivitySignBinding
import com.google.firebase.database.*

class Sign : AppCompatActivity() {
    lateinit var binding: ActivitySignBinding
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    var countryListAdapter:RecyclerAdapterCountry?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signSign.setOnClickListener {
            database.child("User").addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.child(binding.phoneSign.text.toString()).exists()) {
                        val userCurrentData: User? = snapshot.child(binding.phoneSign.text.toString()).getValue(
                            User::class.java
                        )

                        if (userCurrentData?.phone.equals(binding.phoneSign.text.toString()) && userCurrentData?.pass.equals(binding.passSign.text.toString())) {

                            startActivity(Intent(this@Sign,Redact::class.java))

                        } else {

                        }
                    }
                    else{


                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }

    }
}