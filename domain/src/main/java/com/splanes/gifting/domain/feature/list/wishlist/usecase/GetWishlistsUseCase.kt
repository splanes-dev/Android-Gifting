package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import javax.inject.Inject

class GetWishlistsUseCase @Inject constructor(
    private val repository: WishlistRepository
) : UseCase<Unit, List<Wishlist>>() {

    override suspend fun execute(request: Unit): List<Wishlist> =
        repository.getWishlists()
}
