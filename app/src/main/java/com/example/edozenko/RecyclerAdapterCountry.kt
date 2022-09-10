package com.example.edozenko

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edozenko.databinding.ElementBinding
import com.squareup.picasso.Picasso

class RecyclerAdapterCountry(val clickListener: ClickListener) : RecyclerView.Adapter<RecyclerAdapterCountry.RaceViewHolder>() {

    private var raceListInAdapter= ArrayList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterCountry.RaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element,parent,false)

        return RaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterCountry.RaceViewHolder, position: Int) {
        holder.bind(raceListInAdapter[position],clickListener)
    }

    override fun getItemCount(): Int {
        return raceListInAdapter.size
    }

    class RaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ElementBinding.bind(itemView)
        fun bind(country: Country, clickListener: ClickListener){
            binding.nameCountry.text=country.country
            Picasso.get().load(country.imageCountry).fit().into(binding.imageEl)
            itemView.setOnClickListener{

                clickListener.onClick(country)
            }

        }
    }
    fun loadListToAdapter(productList:ArrayList<Country>){
        raceListInAdapter= productList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun onClick(product: Country){

        }
    }
    fun deleteItem(i:Int):String?{
        var id=raceListInAdapter.get(i).idC

        raceListInAdapter.removeAt(i)

        notifyDataSetChanged()

        return id
    }
}
