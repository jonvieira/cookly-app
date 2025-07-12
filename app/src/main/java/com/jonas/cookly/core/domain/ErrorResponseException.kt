package com.jonas.cookly.core.domain

import com.jonas.cookly.core.data.remote.response.ErrorResponse

class ErrorResponseException(val error: ErrorResponse) : RuntimeException()
