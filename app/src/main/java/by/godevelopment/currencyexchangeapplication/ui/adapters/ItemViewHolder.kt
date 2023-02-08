package by.godevelopment.currencyexchangeapplication.ui.adapters

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import by.godevelopment.currencyexchangeapplication.databinding.ItemCurrencyBinding
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import by.godevelopment.currencyexchangeapplication.ui.adapters.CurrencyAdapter.Companion.ARG_CURR

class ItemViewHolder(
    private val binding: ItemCurrencyBinding,
    private val onClickItem: (String) -> Unit,
    private val onTextChange: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var baseForMoveToTopHolder: String? = null
    private var isTopChosenItemHolder: Boolean = false

    init {
        with(binding) {
            root.setOnClickListener {
                baseForMoveToTopHolder?.let { base -> onClickItem(base) }
                rate.isEnabled = true
                isTopChosenItemHolder = true
            }
            rate.doAfterTextChanged {
                if (isTopChosenItemHolder) {
                    onTextChange(it.toString())
                }
            }
        }
    }

    fun bind(item: CurrencyModel, isTopChosenItem: Boolean) {
        isTopChosenItemHolder = isTopChosenItem
        baseForMoveToTopHolder = item.base
        with(binding) {
            base.text = item.base
            name.text = root.resources.getString(item.currencyName)
            image.setImageResource(item.currencyDraw)
            item.rate.toString().also {
                rate.apply {
                    setText(it)
                    isEnabled = isTopChosenItem
                    if (isTopChosenItem) {
                        post {
                            requestFocus()
                            setSelection(it.length)
                        }
                    }
                }
            }
        }
    }

    fun update(bundle: Bundle, isTopChosenItem: Boolean) {
        isTopChosenItemHolder = isTopChosenItem
        if (bundle.containsKey(ARG_CURR)) {
            val rate = bundle.getDouble(ARG_CURR)
            if (!isTopChosenItemHolder) binding.rate.setText(rate.toString())
        }
    }
}
