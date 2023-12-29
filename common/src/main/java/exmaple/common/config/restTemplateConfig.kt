package exmaple.common.config

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase
import org.apache.http.client.methods.HttpUriRequest
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpMethod
import org.springframework.http.HttpRequest
import org.springframework.http.client.*
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.nio.charset.Charset
import java.time.Duration

@Configuration
@ConditionalOnClass(SimpleClientHttpRequestFactory::class)
class RestTemplateConfig {
    companion object {
        val restTemplateLoggingInterceptor: RestTemplateLoggingInterceptor = RestTemplateLoggingInterceptor()
    }
    private class HttpEntityEnclosingGetRequestBase(uri: URI) : HttpEntityEnclosingRequestBase() {
        init {
            super.setURI(uri)
        }
        override fun getMethod(): String {
            return HttpMethod.GET.name
        }
    }

    class CustomHttpComponentsClientHttpRequestFactory : HttpComponentsClientHttpRequestFactory() {
        override fun createHttpUriRequest(httpMethod: HttpMethod, uri: URI): HttpUriRequest {
            return if (HttpMethod.GET == httpMethod) {
                HttpEntityEnclosingGetRequestBase(uri)
            } else super.createHttpUriRequest(httpMethod, uri)
        }
    }

    @Primary
    @Bean(name = ["restTemplate"])
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate {
        return restTemplateBuilder.requestFactory {
            BufferingClientHttpRequestFactory(CustomHttpComponentsClientHttpRequestFactory())
        }
            .setConnectTimeout(Duration.ofSeconds(1 * 10))
            .setReadTimeout(Duration.ofSeconds(1 * 10))
            .additionalMessageConverters(
                StringHttpMessageConverter(Charset.forName("UTF-8")),
                FormHttpMessageConverter()
            )
            .additionalInterceptors(restTemplateLoggingInterceptor)
            .build()
    }

    @Bean(name = ["restTemplateLongConnection"])
    fun restTemplateLongConnection(restTemplateBuilder: RestTemplateBuilder): RestTemplate {
        return restTemplateBuilder.requestFactory {
            BufferingClientHttpRequestFactory(CustomHttpComponentsClientHttpRequestFactory())
        }
            .setConnectTimeout(Duration.ofSeconds(1 * 180))
            .setReadTimeout(Duration.ofSeconds(1 * 180))
            .additionalMessageConverters(
                StringHttpMessageConverter(Charset.forName("UTF-8")),
                FormHttpMessageConverter()
            )
            .additionalInterceptors(restTemplateLoggingInterceptor)
            .build()
    }
}


class RestTemplateLoggingInterceptor: ClientHttpRequestInterceptor {

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        // 로깅로직처리
        return execution.execute(request, body)
    }

}