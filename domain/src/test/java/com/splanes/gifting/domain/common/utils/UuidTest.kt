package com.splanes.gifting.domain.common.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UuidTest {

    @Test
    fun `given an uuid, it is not null or blank`() {
        val uuid = uuid()

        assertThat(uuid).apply {
            isNotNull()
            isNotEmpty()
        }
    }

    @Test
    fun `given an uuid, it does not contains hyphens`() {
        val uuid = uuid()

        assertThat(uuid).doesNotContain("-")
    }

    @Test
    fun `given two uuid, they are different`() {
        val uuid1 = uuid()
        val uuid2 = uuid()

        assertThat(uuid1).isNotEqualTo(uuid2)
    }
}
