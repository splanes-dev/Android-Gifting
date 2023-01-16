package com.splanes.gifting.data.common.utils.hash

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MD5Test {

    @Test
    fun `given string, when hashing it, then result is ok`() {
        val given = "HelloWorld"
        val expected = "68e109f0f40ca72a15e05cc22786f8e6"

        assertThat(given.md5()).isEqualTo(expected)
    }
}
