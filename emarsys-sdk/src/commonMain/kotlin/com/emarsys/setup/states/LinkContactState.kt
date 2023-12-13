package com.emarsys.setup.states

import com.emarsys.clients.contact.ContactClient
import com.emarsys.contants.Constants
import com.emarsys.core.state.State
import com.emarsys.core.storage.SecureStorage
import com.emarsys.setup.SetupState

class LinkContactState(
    private val contactClient: ContactClient,
    private val stringStorage: SecureStorage<String>,
    private val intStorage: SecureStorage<Int>
) : State {

    override var name = SetupState.linkContact.name

    override fun prepare() {
    }

    override suspend fun active() {
        val contactToken: String? = stringStorage[Constants.Contact.contactToken]
        if (contactToken == null) {
            val contactFieldId: Int? = intStorage[Constants.Contact.contactFieldId]
            val contactFieldValue: String? = stringStorage[Constants.Contact.contactFieldValue]
            val openIdToken: String? = stringStorage[Constants.Contact.openIdToken]
            if ((contactFieldId != null && contactFieldValue != null) || (contactFieldId != null && openIdToken != null)) {
                try {
                    contactClient.linkContact(
                        contactFieldId,
                        contactFieldValue,
                        openIdToken
                    )
                } catch (e: Exception) {
                    contactClient.unlinkContact()
                }
            } else {
                contactClient.unlinkContact()
            }
        }
    }

    override fun relax() {
    }

}
