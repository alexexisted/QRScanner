package com.alexadiamant.qrscannerapp.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexadiamant.qrscannerapp.R
import com.alexadiamant.qrscannerapp.data.dataClasses.Customer
import com.alexadiamant.qrscannerapp.data.dataClasses.Order
import com.alexadiamant.qrscannerapp.databinding.OrderInfoAdapterBinding

class OrderInfoAdapter: ListAdapter<Order, OrderInfoAdapter.Holder>(Comparator()) {

    //holder class to hold elements on view
    class Holder(view: View): RecyclerView.ViewHolder(view) {
        //bind the adapter to view
        private val binding = OrderInfoAdapterBinding.bind(view)

        //method to set up data to elements in table
        fun bind(order: Order) = with(binding) {

            // set the data by id of element
            tableTVId.text = order.id

            tableTVCustomerName.text = order.customer.customerFullName
            tableTVPhone.text = order.customer.phone
            tableTVEmail.text = order.customer.email

            tableTVSource.text = order.customer.address.source
            tableTVPostalCode.text = order.customer.address.postalCode
            tableTVRegKladrId.text = order.customer.address.regionKladrId
            tableTVHouse.text = order.customer.address.house
            tableTVBlock.text = order.customer.address.block
            tableTVFlat.text = order.customer.address.flat
            tableTVRegion.text = order.customer.address.regionWithType
            tableTVCityType.text = order.customer.address.cityWithType
            tableTVCityArea.text = order.customer.address.cityArea
            tableTVStreet.text = order.customer.address.streetWithType

            tableTVDetachedHouse.text = order.customer.address.access.detachedHouse
            tableTVEntrance.text = order.customer.address.access.entrance
            tableTVFloor.text = order.customer.address.access.floor.toString()
            tableTVIntercom.text = order.customer.address.access.intercom
            tableTVCargoElevator.text = order.customer.address.access.cargoElevator.toString()
            tableTVComment.text = order.customer.address.access.comment
            tableTVApartment.text = order.customer.address.access.apartment

        }
    }

    //class to compare elements
    class Comparator: DiffUtil.ItemCallback<Order>() {
        //comparing elements by their link
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }
        //comparing elements by their hashcode
        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }
    }
    //method to inflate holder on a layout with data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_info_adapter, parent, false)

        return Holder(view)
    }
    //method to bind holder on view
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}