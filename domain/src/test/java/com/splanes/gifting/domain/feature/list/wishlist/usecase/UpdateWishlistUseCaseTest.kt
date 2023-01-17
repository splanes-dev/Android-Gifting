package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.google.common.truth.Truth.assertThat
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.request.UpdateWishlistRequest
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpdateWishlistUseCaseTest {

    private val repository: WishlistRepository = mockk()
    private val updateWishlistUseCase = UpdateWishlistUseCase(repository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given a request, when updating wishlist, then params are updated`() =
        runTest {
            updateWishlistUseCase.invoke(Request)

            coVerify {
                repository.createOrUpdateWishlist(
                    wishlist = withArg { wishlist ->
                        assertThat(wishlist.id).isEqualTo(Request.wishlist.id)
                        assertThat(wishlist.name).isEqualTo(Request.name)
                        assertThat(wishlist.description).isEqualTo(Request.description)
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

private val Request = UpdateWishlistRequest(
    wishlist = AnyWishlist,
    name = "new-name1",
    description = "Description wishlist 1"
)
