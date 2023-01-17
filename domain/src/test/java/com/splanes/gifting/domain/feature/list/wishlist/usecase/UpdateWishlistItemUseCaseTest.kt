package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.google.common.truth.Truth.assertThat
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.domain.feature.list.wishlist.request.UpdateWishlistItemRequest
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpdateWishlistItemUseCaseTest {

    private val repository: WishlistRepository = mockk()
    private val updateWishlistItemUseCase = UpdateWishlistItemUseCase(repository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given item update request, when update wishlist, then item has the new params`() =
        runTest {
            updateWishlistItemUseCase.invoke(Request)

            coVerify {
                repository.createOrUpdateWishlist(
                    wishlist = withArg { wishlist ->
                        val itemUpdated = wishlist.items.find { i -> i.id == Request.item.id }

                        assertThat(wishlist.items.count()).isEqualTo(AnyWishlist.items.count())
                        assertThat(itemUpdated).isNotNull()
                        assertThat(itemUpdated?.name).isEqualTo(Request.item.name)
                        assertThat(itemUpdated?.description).isEqualTo(Request.item.description)
                    }
                )
            }
        }
}

private val AnyItem = WishlistItem(
    wishlistId = "w1",
    id = "i1",
    name = "item1",
    owner = "user-uid",
    addedOn = 0L
)

private val AnyWishlist = Wishlist(
    id = "w1",
    name = "wishlist1",
    owner = "user-uid",
    items = listOf(AnyItem, AnyItem.copy(id = "i2", name = "item2")),
    createdOn = 0L,
    updatedOn = 0L
)

private val Request = UpdateWishlistItemRequest(
    wishlist = AnyWishlist,
    item = AnyItem.copy(
        name = "item-updated-1",
        description = "Some description"
    )
)
