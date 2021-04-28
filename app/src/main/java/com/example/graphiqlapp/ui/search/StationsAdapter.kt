package com.example.graphiqlapp.ui.search


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Station
import com.example.graphiqlapp.R
import com.example.graphiqlapp.databinding.ViewStationsBinding
import com.example.graphiqlapp.ui.common.collectFlow
import com.example.graphiqlapp.ui.common.loadWithGlide
import com.example.graphiqlapp.ui.common.onClickEvents
import com.example.graphiqlapp.ui.common.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class StationsAdapter(private val scope: CoroutineScope) :
    ListAdapter<Station, StationsAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_stations, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
        scope.collectFlow(itemView.onClickEvents) {

            itemView.context.toast(item.name)
        }
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewStationsBinding.bind(itemView)

        fun bind(item: Station) = with(binding) {
            title.text = item.name
            lat.text = item.latitude.toString()
            lon.text = item.longitude.toString()

            stationImage.loadWithGlide(item.picture)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Station>() {
    override fun areItemsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem == newItem
    }
}