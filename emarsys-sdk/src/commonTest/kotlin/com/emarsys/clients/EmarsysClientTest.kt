package com.emarsys.clients

import com.emarsys.context.ISdkContext
import com.emarsys.context.SdkContext
import com.emarsys.core.DefaultUrls
import com.emarsys.core.IDefaultUrls
import com.emarsys.core.networking.HttpUrlRequest
import com.emarsys.core.networking.NetworkClient
import com.emarsys.core.session.SessionContext
import io.kotest.assertions.any
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import org.kodein.mock.*
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

class EmarsysClientTest : TestsWithMocks() {
    override fun setUpMocks() = injectMocks(mocker)

    @Mock
    lateinit var mockNetworkClient: NetworkClient

    @Mock
    lateinit var mockSdkContext: ISdkContext

    @Mock
    lateinit var mockDefaultUrls: IDefaultUrls

    @Mock
    lateinit var mockSessionContext: SessionContext

    @Fake
    lateinit var fakeHttpResponse: FakeHttpResponse

    val emarsysClient by withMocks {
        EmarsysClient(
            mockNetworkClient,
            mockDefaultUrls,
            mockSdkContext,
            mockSessionContext
        )
    }

    @BeforeTest
    fun setUp() = runTest {
        every { mockDefaultUrls.clientServiceBaseUrl } returns ""
        every { mockSdkContext.createUrl(isAny(), isAny(), isAny(), isAny()) } returns URLBuilder()
        every { mockSessionContext.additionalHeaders } returns mapOf()
        everySuspending { mockNetworkClient.send(isAny()) } returns FakeHttpResponse()
    }

    @Test
    fun testTest() = runTest {
        val url = mockSdkContext.createUrl(mockDefaultUrls.clientServiceBaseUrl, path = "/client")
        val request = HttpUrlRequest(
            url = url.build(),
            method = HttpMethod.Post,
            body = "",
            headers = mapOf()
        )
        emarsysClient.send(request)
        
        verifyWithSuspend(exhaustive = false) { mockNetworkClient.send(request) }
    }
}
