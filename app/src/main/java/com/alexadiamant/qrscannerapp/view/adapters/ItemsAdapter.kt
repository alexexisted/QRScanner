package com.alexadiamant.qrscannerapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexadiamant.qrscannerapp.R
import com.alexadiamant.qrscannerapp.data.dataClasses.Items
import com.alexadiamant.qrscannerapp.databinding.ItemsAdapterBinding

class ItemsAdapter: ListAdapter<Items, ItemsAdapter.Holder>(Comparator()) {


    class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemsAdapterBinding.bind(view)

        //method to inflate adapter template with data
        fun bind(items: Items) = with(binding){
            TVItemId.text = "Item ID: " + items.itemId
            TVOfferId.text = "Offer ID: " + items.offerId
            TVItemName.text = "Item Name: " + items.itemName
            TVItemPrice.text = "Price: " + items.price
            TVItemFinalPrice.text = "Final price: " + items.finalPrice
            TVQuantity.text = "Quantity: " + items.quantity
            TVExternalHWB.text = "ExternalHWB: " + items.externalHWB
            TVInternalHWB.text = "InternalHWB: " + items.internalHWB
        }
    }

    class Comparator: DiffUtil.ItemCallback<Items>(){
        //compare items by their link
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        //compare objects by their hashcode
        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }
    }

    //method to inflate view with holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_adapter, parent, false)
        return Holder(view)
    }

    //method to bind view holder with objects
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}