package exmaple.common.ext

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper



val objectMapper = jacksonObjectMapper().apply {
    disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
}
