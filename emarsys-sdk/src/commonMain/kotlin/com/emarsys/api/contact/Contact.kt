package com.emarsys.api.contact

import com.emarsys.api.ActivationAware
import com.emarsys.api.GenericApi
import com.emarsys.context.SdkContext

interface ContactInstance : ActivationAware, ContactApi{
}

class Contact(
    loggingInstance: ContactInstance,
    gathererInstance: ContactInstance,
    internalInstance: ContactInstance,
    sdkContext: SdkContext
) : GenericApi(loggingInstance, gathererInstance, internalInstance, sdkContext), ContactApi {
    override suspend fun linkContact(contactFieldId: Int, contactFieldValue: String) {
        active?.activated()
    }

    override suspend fun linkAuthenticatedContact(contactFieldId: Int, openIdToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun unlinkContact() {
        TODO("Not yet implemented")
    }

}