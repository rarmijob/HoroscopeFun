package dev.rarmijo.horoscopefun.domain.result

sealed interface DataError: Error {
    enum class Network: DataError {
        BadRequest,
        Unauthorized,
        Forbidden,
        NotFound,
        RequestTimeout,
        TooManyRequests,
        NoInternet,
        PayloadTooLarge,
        ServerError,
        Serialization,
        BadGateway,
        ServiceUnavailable,
        GatewayTimeout,
        Unknown
    }
    enum class Local: DataError {
        DiskFull,
        Unknown
    }
}