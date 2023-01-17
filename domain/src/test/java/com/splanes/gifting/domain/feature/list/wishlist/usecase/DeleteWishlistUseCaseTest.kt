package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.google.common.truth.Truth.assertThat
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DeleteWishlistUseCaseTest {

    private val repository: WishlistRepository = mockk {
        coEvery { deleteWishlist(any()) } returns true
    }
    private val deleteWishlistsUseCase = DeleteWishlistsUseCase(repository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given request, when delete one wishlist, then it is removed from list`() =
        runTest {
            val toDelete = listOf(AnyWishlist)

            deleteWishlistsUseCase.invoke(toDelete)

            coVerify(exactly = toDelete.count()) {
                repository.deleteWishlist(
                    wishlist = withArg { wishlist ->
                        assertThat(toDelete).contains(wishlist)
                    }
                )
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given request, when delete multiple wishlists, then they are removed from list`() =
        runTest {
            val toDelete = Wishlists

            deleteWishlistsUseCase.invoke(toDelete)

            coVerify(exactly = toDelete.count()) {
                repository.deleteWishlist(
                    wishlist = withArg { wishlist ->
                        assertThat(toDelete).contains(wishlist)
                    }
                )
            }
        }
}

private val AnyWishlist = Wishlist(
    id = "w1",
    name = "w-name-1",
    owner = "o1",
    createdOn = 0L,
    updatedOn = 0L
)

private val Wishlists = listOf(
    AnyWishlist,
    AnyWishlist.copy(id = "w2", name = "w-name-2"),
    AnyWishlist.copy(id = "w3", name = "w-name-3"),
    AnyWishlist.copy(id = "w4", name = "w-name-4"),
    AnyWishlist.copy(id = "w5", name = "w-name-5")
)
