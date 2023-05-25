package ru.perelyginva.shopinglist.domain

import androidx.lifecycle.LiveData
import ru.perelyginva.shopinglist.domain.repository.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    /*изменилась бизнес логика, теперь список меняется автоматически,
    для этого используем LiveData
     */
    fun getShopList():LiveData<List<ShopItem>>{

        return  shopListRepository.getShopList()
    }
}