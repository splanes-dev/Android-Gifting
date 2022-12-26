package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistRequest
import javax.inject.Inject

class CreateWishlistUseCase @Inject constructor(
    private val repository: WishlistRepository
) : UseCase<NewWishlistRequest, Wishlist>() {

    override suspend fun execute(request: NewWishlistRequest): Wishlist {
        TODO("Not yet implemented")
    }
}
