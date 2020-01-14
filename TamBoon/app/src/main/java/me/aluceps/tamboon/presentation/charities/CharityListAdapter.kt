package me.aluceps.tamboon.presentation.charities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.aluceps.tamboon.R
import me.aluceps.tamboon.databinding.ItemCharityBinding
import me.aluceps.tamboon.domain.entities.Charity

class CharityListAdapter : RecyclerView.Adapter<CharityViewHolder>() {

    private val items = mutableListOf<Charity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharityViewHolder =
            CharityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_charity, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharityViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(items: List<Charity>) {
        if (items.isNotEmpty()) {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }
    }
}

class CharityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemCharityBinding.bind(itemView)

    fun bind(item: Charity) {
        binding.itemModel = item
        binding.notifyChange()
    }
}

