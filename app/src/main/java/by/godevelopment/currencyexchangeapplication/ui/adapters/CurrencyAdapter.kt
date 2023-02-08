package by.godevelopment.currencyexchangeapplication.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.godevelopment.currencyexchangeapplication.databinding.ItemCurrencyBinding
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel

class CurrencyAdapter(
    private val onClickItem: (String) -> Unit,
    private val onTextChange: (String) -> Unit
) : ListAdapter<CurrencyModel, ItemViewHolder>(DiffCallback()) {

    var topItemHasFocus: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickItem = onClickItem,
            onTextChange = onTextChange
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val isTopChosenItem = (topItemHasFocus && position == 0)
        holder.bind(getItem(position), isTopChosenItem)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val isTopChosenItem = (topItemHasFocus && position == 0)
        if (payloads.isEmpty() || payloads[0] !is Bundle) {
            holder.bind(getItem(position), isTopChosenItem)
        }
        else {
            val bundle = payloads[0] as Bundle
            holder.update(bundle, isTopChosenItem)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<CurrencyModel>() {

        override fun getChangePayload(oldItem: CurrencyModel, newItem: CurrencyModel): Any? {
            if (oldItem.id == newItem.id) {
                return if (oldItem.rate == newItem.rate) {
                    super.getChangePayload(oldItem, newItem)
                }
                else {
                    Bundle().apply {
                        putDouble(ARG_CURR, newItem.rate.toDouble())
                    }
                }
            }
            return super.getChangePayload(oldItem, newItem)
        }

        override fun areItemsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        const val ARG_CURR = "arg.curr"
    }
}
