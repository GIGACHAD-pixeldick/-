package model

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.control.R
import kotlinx.android.synthetic.main.one_plant.view.*

class Adapter(private var plants: List<ListPlant>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.one_plant,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.title.text=plants[position].title
        holder.itemView.room.text=plants[position].room
        holder.itemView.price.text=plants[position].price.toString()

        if(position % 2 == 0){
            holder.itemView.price.setTextColor(Color.RED)
        }

        else{
            holder.itemView.price.setTextColor(Color.MAGENTA)
        }
    }

    override fun getItemCount(): Int {
        return plants.size
    }

}