package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.google.common.truth.Truth.assertThat
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistItemRequest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddWishlistItemUseCaseTest {

    private val repository: WishlistRepository = mockk {
        coEvery { getUid() } returns "user-uid"
    }
    private val addWishlistItemUseCase = AddWishlistItemUseCase(repository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given request, when adding item, then updateWishlist is called`() =
        runTest {
            addWishlistItemUseCase.invoke(AddItemRequest)

            coVerify {
                repository.createOrUpdateWishlist(
                    wishlist = withArg { wishlist ->
                        val itemToAdd = wishlist.items.find { item ->
                            item.name == AddItemRequest.name
                        }
                        assertThat(itemToAdd).isNotNull()
                    }
                )
            }
        }
}

private val SomeWishlist = Wishlist(
    id = "wishlist-id",
    name = "wishlist-name",
    description = null,
    items = emptyList(),
    owner = "user-uid",
    createdOn = 0L,
    updatedOn = 0L
)

private val AddItemRequest = NewWishlistItemRequest(
    wishlist = SomeWishlist,
    name = "item-name",
    description = null,
    price = null,
    url = null,
    notes = null,
    categories = emptyList(),
    tags = emptyList()
)
