package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.google.common.truth.Truth.assertThat
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.domain.feature.list.wishlist.request.DeleteWishlistItemsRequest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DeleteWishlistItemsUseCaseTest {

    private val repository: WishlistRepository = mockk {
        coEvery { getUid() } returns "user-uid"
    }
    private val deleteWishlistItemsUseCase = DeleteWishlistItemsUseCase(repository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given request, then delete one item, then it is removed from wishlist`() =
        runTest {
            val toDelete = Items.subList(0, 3)
            val request = DeleteWishlistItemsRequest(
                wishlist = AnyWishlist.copy(items = Items),
                itemsToDelete = toDelete
            )

            deleteWishlistItemsUseCase.invoke(request)

            coVerify(exactly = 1) {
                repository.createOrUpdateWishlist(
                    wishlist = withArg { wishlist ->
                        assert(wishlist.items.none { i -> toDelete.contains(i) })
                    }
                )
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given request, then delete multiple items, then they are removed from wishlist`() =
        runTest {
            val toDelete = listOf(Item)
            val request = DeleteWishlistItemsRequest(
                wishlist = AnyWishlist.copy(items = Items),
                itemsToDelete = toDelete
            )

            deleteWishlistItemsUseCase.invoke(request)

            coVerify(exactly = 1) {
                repository.createOrUpdateWishlist(
                    wishlist = withArg { wishlist ->
                        assertThat(wishlist.items).doesNotContain(Item)
                    }
                )
            }
        }
}

private val AnyWishlist = Wishlist(
    id = "w1",
    name = "wishlist1",
    owner = "user-uid",
    createdOn = 0L,
    updatedOn = 0L
)

private val Item = WishlistItem(
    wishlistId = AnyWishlist.id,
    id = "i1",
    owner = "user-uid",
    name = "item1",
    addedOn = 0L
)

private val Items = listOf(
    Item,
    Item.copy(id = "i2", name = "item2"),
    Item.copy(id = "i3", name = "item3"),
    Item.copy(id = "i4", name = "item4"),
    Item.copy(id = "i5", name = "item5")
)
