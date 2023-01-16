package com.splanes.gifting.domain.common.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TimestampTest {

    @Test
    fun `timestamp extension return systemCurrentMillis`() {
        val now = System.currentTimeMillis()
        assertThat(timestamp()).isEqualTo(now)
    }
}
