package ru.perelyginva.shopinglist.domain

import ru.perelyginva.shopinglist.domain.repository.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopItem(shopItemId: Int): ShopItem{

        return shopListRepository.getShopItem(shopItemId)
    }
}