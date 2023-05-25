package ru.perelyginva.shopinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.perelyginva.shopinglist.databinding.ItemShopBinding
import ru.perelyginva.shopinglist.domain.ShopItem

class ShopListAdapter : ListAdapter
<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {


    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    //как создавать View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val binding = ItemShopBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

    //как вставить значение внутри View
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        with(holder.binding) {
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()

            cardView.setOnLongClickListener {
                onShopItemLongClickListener?.invoke(shopItem)

                true
            }

            cardView.setOnClickListener {
                onShopItemClickListener?.invoke(shopItem)
            }
        }
    }

    interface OnShopItemLongClickListener {
        fun onShopItemLongClick(shopItem: ShopItem, position: Int)
        fun onShopItemClick(shopItem: ShopItem)
    }

    companion object {
        const val MAX_POOL_SIZE = 15
    }
}