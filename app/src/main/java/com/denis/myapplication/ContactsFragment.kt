package com.denis.myapplication

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.CursorWrapper
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CharMatcher
import pub.devrel.easypermissions.EasyPermissions


class ContactsFragment : Fragment(R.layout.fragment_contacts) {

    private val READ_STORAGE_PERMKISSION_REQUEST = 123
    private lateinit var image: ImageView
    private lateinit var contactsList: ListView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsList = view.findViewById(R.id.contacts_list)
//        addListenerOnListView()
        image = view.findViewById(R.id.image)
        val button: Button = view.findViewById(R.id.pickImageBtn)
        button.setOnClickListener {
            getPhotoPermissionResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        val getContactsBtn: Button = view.findViewById(R.id.getContacts)
        getContactsBtn.setOnClickListener{
            getContactsPermissionResultLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    private fun openPhotoPicker() {

            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            resultLauncher.launch(intent)

    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null){
            val chosenPhoto: Uri? = result.data!!.data
            Glide.with(this).load(chosenPhoto).into(image)
        }
    }

    private val getPhotoPermissionResultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
        if(isGranted){
            openPhotoPicker()
        }else{
            println("Permission Denied")
        }
    }

    private val getContactsPermissionResultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
        if(isGranted){
            readContacts()
        }else{
            println("Permission Denied")
        }
    }

//    private fun addListenerOnListView() {
//        contactsList.onItemClickListener =
//            AdapterView.OnItemClickListener { parent, view, position, id ->
//                var contact = parent.getChildAt(position)
//                println(contact.get)
//
//                val intent = Intent(Intent.ACTION_DIAL)
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
////                Uri.parse("tel" + CharMatcher.digit().retainFrom(contact.toString()))
//                requireActivity().startActivity(intent)
//            }
//    }

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

