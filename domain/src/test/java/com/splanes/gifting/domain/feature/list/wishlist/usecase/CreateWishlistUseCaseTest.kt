package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.google.common.truth.Truth.assertThat
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistRequest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CreateWishlistUseCaseTest {

    private val repository: WishlistRepository = mockk {
        coEvery { getUid() } returns "user-uid"
    }
    private val createWishlistUseCase = CreateWishlistUseCase(repository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given request, then createWishlist is called`() =
        runTest {
            createWishlistUseCase.invoke(WishlistRequest)

            coVerify {
                repository.createOrUpdateWishlist(
                    wishlist = withArg { wishlist ->
                        assertThat(wishlist.id).isNotEmpty()
                        assertThat(wishlist.name).isEqualTo(WishlistRequest.name)
                        assertThat(wishlist.description).isEqualTo(WishlistRequest.description)
                    }
                )
            }
        }
}

private val WishlistRequest = NewWishlistRequest(
    name = "wishlist-name",
    description = null
)
