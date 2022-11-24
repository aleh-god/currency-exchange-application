package by.godevelopment.currencyexchangeapplication.ui.adapters

import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import by.godevelopment.currencyexchangeapplication.commons.TAG
import by.godevelopment.currencyexchangeapplication.databinding.ItemCurrencyBinding
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel

class ItemViewHolder(
    private val binding: ItemCurrencyBinding,
    private val onClickItem: (String) -> Unit,
    private val onTextChange: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var baseForMoveToTop: String? = null
    private var isTopChosenItem: Boolean = false

    init {
        with(binding) {
            root.setOnClickListener {
                baseForMoveToTop?.let { base -> onClickItem(base) }
            }
            rate.doAfterTextChanged {
                if (isTopChosenItem) {
                    onTextChange(it.toString())
                    Log.i(TAG, "rate.doAfterTextChanged: $it")
                }
            }
        }
    }

    fun bind(item: CurrencyModel, isTopChosenItemCheck: Boolean) {
        Log.i(TAG, "onBindViewHolder item.base = ${item.base} isTopChosenItemCheck = $isTopChosenItemCheck")
        with(binding) {
            base.text = item.base
            baseForMoveToTop = item.base
            name.text = root.resources.getString(item.currencyName)
            image.setImageResource(item.currencyDraw)
            rate.setText(String.format("%.5f", item.rate))

            isTopChosenItem = isTopChosenItemCheck
        }
    }
}
