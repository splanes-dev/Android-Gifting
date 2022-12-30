package com.splanes.gifting.domain.common.utils

import java.util.UUID

fun uuid() = UUID.randomUUID().toString().replace("-", "")
