package com.denis.myapplication

import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.core.app.ActivityCompat
import android.provider.ContactsContract
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.core.view.get
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CharMatcher
import kotlinx.coroutines.NonDisposableHandle


class ContactsFavorites : Fragment(R.layout.fragment_contacts) {

    val REQUEST_CODE_PERMISSION_READ_CONTACTS: Int = 1
    private lateinit var contactsList: ListView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactsList = view.findViewById(R.id.contacts_list)

        val permissionStatus =
            ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS)

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            readContacts()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE_PERMISSION_READ_CONTACTS)
        }
        addListenerOnListView()
    }

    private fun addListenerOnListView() {
//        contactsList.onItemClickListener = OnItemClickListener { parent, view, position, id ->
//           var contact =parent.getItemAtPosition(position)
//            val intent = Intent(Intent.ACTION_DIAL)
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            Uri.parse("tel" + CharMatcher.digit().retainFrom(contact.toString()))
//            requireActivity().startActivity(intent)
//        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when(requestCode){
            REQUEST_CODE_PERMISSION_READ_CONTACTS ->  if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readContacts();
            }else println("Permission Denied")

        }
    }

    private fun readContacts() {


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

        contactsList.adapter = simple

    }
}

