package by.godevelopment.currencyexchangeapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.godevelopment.currencyexchangeapplication.databinding.ItemCurrencyBinding
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel

//class MainAdapter(
//    private val onClickItem: (String) -> Unit,
//    private val onTextChange: (String) -> Unit
//) : RecyclerView.Adapter<ItemViewHolder>() {
//
//    var topItemHasFocus: Boolean = false
//
//    private val diffCallback = object : DiffUtil.ItemCallback<CurrencyModel>() {
//        override fun areItemsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    private val differ = AsyncListDiffer(this, diffCallback)
//    var itemList: List<CurrencyModel>
//        get() = differ.currentList
//        set(value) { differ.submitList(value) }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
//        ItemViewHolder(
//            ItemCurrencyBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            ),
//            onClickItem = onClickItem,
//            onTextChange = onTextChange
//        )
//
//    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        holder.bind(itemList[position], position, topItemHasFocus)
//    }
//
//    override fun getItemCount(): Int = itemList.size
//}
