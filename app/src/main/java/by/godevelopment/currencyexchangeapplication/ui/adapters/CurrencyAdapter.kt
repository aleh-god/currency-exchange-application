package by.godevelopment.currencyexchangeapplication.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.godevelopment.currencyexchangeapplication.commons.TAG
import by.godevelopment.currencyexchangeapplication.databinding.ItemCurrencyBinding
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel

class CurrencyAdapter(
    private val onClickItem: (String) -> Unit,
    private val onTextChange: (String) -> Unit
) : ListAdapter<CurrencyModel, ItemViewHolder>(DiffCallback()) {

    init {
        Log.i(TAG, "CurrencyAdapter: init")
    }

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

    private class DiffCallback : DiffUtil.ItemCallback<CurrencyModel>() {

        override fun areItemsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
//            Log.i(TAG, "areContentsTheSame: ${newItem.base} = ${oldItem == newItem}")
            return oldItem == newItem
        }
    }
}
