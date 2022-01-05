package com.denis.myapplication

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.core.app.ActivityCompat
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter


class ContactsFavorites : Fragment(R.layout.fragment_contacts) {

    val REQUEST_CODE_PERMISSION_READ_CONTACTS: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val permissionStatus =
            ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS)

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            println("Permission was granted before")
            readContacts()
        } else {
            println("The permission was noe granted before, opening permission dialog")
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE_PERMISSION_READ_CONTACTS)
        }
    }


    private fun readContacts() {
        val contactsList: ListView? = view?.findViewById(R.id.contacts_list)
        val contactsFromPhone = listOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        ).toTypedArray()
        val cursor: Cursor? = requireActivity().contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null, null, null, null)
        requireActivity().startManagingCursor(cursor)

        val contacstToList = intArrayOf(android.R.id.text1, android.R.id.text2)
        val simple: SimpleCursorAdapter = SimpleCursorAdapter(requireActivity(),android.R.layout.simple_list_item_2, cursor, contactsFromPhone, contacstToList )
        if (contactsList != null) {
            contactsList.adapter  = simple
        }

    }
}

