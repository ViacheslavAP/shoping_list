package ru.perelyginva.shopinglist.domain.repository

import androidx.lifecycle.LiveData
import ru.perelyginva.shopinglist.domain.ShopItem

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}