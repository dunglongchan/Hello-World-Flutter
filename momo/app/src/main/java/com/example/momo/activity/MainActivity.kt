package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.momo.R
import com.example.momo.adapter.ViewPagerAdapter
import com.example.momo.database.AppDataBase
import com.example.momo.database.Contact
import com.example.momo.databinding.ActivityMainBinding
import com.example.momo.fragment.QrCodeFragment


class MainActivity : AppCompatActivity() {

    private var dialog: AlertDialog? = null
    private lateinit var dataBase: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alertDialog = AlertDialog.Builder(this)
            .setMessage("You need to allow permissions to use this app")
            .setPositiveButton("Go to Settings") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", this.packageName, null)
                intent.data = uri
                startActivityForResult(intent, 111)
            }
            .setCancelable(false)
        dialog = alertDialog.create()

        dataBase = AppDataBase.getAppDatabase(this@MainActivity)!!

        val adapter = ViewPagerAdapter(this)
        binding.viewpager.adapter = adapter
        binding.viewpager.offscreenPageLimit = 4
        binding.viewpager.isUserInputEnabled = false

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.nvBtm.menu.findItem(R.id.home).isChecked = true
                    }
                    1 -> {
                        binding.nvBtm.menu.findItem(R.id.voucher).isChecked = true
                    }
                    2 -> {
                        binding.nvBtm.menu.findItem(R.id.tradeHistory).isChecked = true
                    }
                    3 -> {
                        binding.nvBtm.menu.findItem(R.id.chat).isChecked = true
                    }
                    4 -> {
                        binding.nvBtm.menu.findItem(R.id.profile).isChecked = true
                    }
                }
            }
        })

        binding.nvBtm.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    binding.viewpager.currentItem = 0
                    binding.nvBtm.visibility = View.VISIBLE
                    true
                }
                R.id.voucher -> {
                    binding.nvBtm.visibility = View.VISIBLE
                    binding.viewpager.currentItem = 1
                    true
                }
                R.id.tradeHistory -> {
                    binding.viewpager.currentItem = 2
                    binding.nvBtm.visibility = View.GONE
                    true
                }
                R.id.chat -> {
                    binding.viewpager.currentItem = 3
                    binding.nvBtm.visibility = View.VISIBLE
                    true
                }
                R.id.profile -> {
                    binding.viewpager.currentItem = 4
                    binding.nvBtm.visibility = View.VISIBLE
                    true
                }
                else -> false

            }
        }

        QrCodeFragment.close.observe(this, Observer {
            if (it) {
                binding.viewpager.currentItem = 0
                binding.nvBtm.visibility = View.VISIBLE
                QrCodeFragment.close.value = false
            }
        })

    }

    override fun onStart() {
        super.onStart()
        if (!checkPermission(android.Manifest.permission.CAMERA, 111)) {
            dialog!!.show()
        }
        if (!checkPermission(android.Manifest.permission.READ_CONTACTS, 112)) {
            dialog!!.show()
        } else {
            readContact()
        }
    }

    @SuppressLint("Range")
    private fun readContact() {


        val cr = contentResolver
        val cur: Cursor? = cr.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        )

        if ((cur?.getCount() ?: 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                val id: String = cur.getString(
                    cur.getColumnIndex(ContactsContract.Contacts._ID)
                )
                val name: String = cur.getString(
                    cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                    )
                )
                if (cur.getInt(
                        cur.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER
                        )
                    ) > 0
                ) {
                    val pCur: Cursor? = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id),
                        null
                    )
                    while (pCur!!.moveToNext()) {
                        val phoneNo: String = pCur.getString(
                            pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                        )
                        dataBase.ContactDao().insertContact(Contact(name, phoneNo, false))
                        Log.i("====", "Name: $name")
                        Log.i("====", "Phone Number: $phoneNo")
                    }
                    pCur.close()
                }
            }
        }
        cur?.close()
    }

    internal fun checkPermission(permission: String, requestCode: Int): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
//            dialog!!.show()
        } else {
            return true
//            Toast.makeText(this@MainActivity, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111) {
            if (!(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                dialog!!.show()
            }
        }
        if (requestCode == 112) {
            if (!(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                dialog!!.show()
            } else readContact()
        }
    }
}