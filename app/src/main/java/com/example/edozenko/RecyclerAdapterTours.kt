package com.example.edozenko

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edozenko.databinding.ElementBinding
import com.example.edozenko.databinding.ElementToursBinding
import com.squareup.picasso.Picasso

class RecyclerAdapterTours() : RecyclerView.Adapter<RecyclerAdapterTours.RaceViewHolder>() {

    private var tourListInAdapter= ArrayList<TourModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterTours.RaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_tours,parent,false)

        return RaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterTours.RaceViewHolder, position: Int) {
        holder.bind(tourListInAdapter[position])
    }

    override fun getItemCount(): Int {
        return tourListInAdapter.size
    }

    class RaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ElementToursBinding.bind(itemView)
        fun bind(tours: TourModel){
            binding.elNameTour.text=tours.nameTour
            binding.elPriceTour.text=tours.priceTour
            binding.elDataTour.text=tours.dataTour
            binding.elNightTour.text=tours.nightTour
            Picasso.get().load(tours.imageTour).fit().into(binding.elImageTour)



        }
    }
    fun loadListToAdapter(productList:ArrayList<TourModel>){
        tourListInAdapter= productList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun onClick(tour: TourModel){

        }
    }
    fun deleteItem(i:Int):String?{
        var id=tourListInAdapter.get(i).id

        tourListInAdapter.removeAt(i)

        notifyDataSetChanged()

        return id
    }
}