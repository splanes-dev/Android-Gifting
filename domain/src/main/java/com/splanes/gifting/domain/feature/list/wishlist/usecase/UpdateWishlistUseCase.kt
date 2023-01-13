package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.request.UpdateWishlistRequest
import javax.inject.Inject

class UpdateWishlistUseCase @Inject constructor(
    private val repository: WishlistRepository
) : UseCase<UpdateWishlistRequest, Wishlist>() {

    override suspend fun execute(request: UpdateWishlistRequest): Wishlist {
        val wishlistToUpdate = request.wishlist.copy(
            name = request.name,
            description = request.description
        )
        return repository.createOrUpdateWishlist(wishlistToUpdate)
    }
}
