package ru.perelyginva.shopinglist.data

import ru.perelyginva.shopinglist.domain.ShopItem
import ru.perelyginva.shopinglist.domain.repository.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = ShopItem("name $i", i, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        //находим старый элемент по его id
        val oldElement = getShopItem(shopItem.id)
        //удаляем старый элемент из коллекции
        shopList.remove(oldElement)
        //вставляем новый элемент
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
        //если shopItemId будет null от будет выброшено исключение
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()//возвращаем копию коллекции
    }
}