package com.example.momo.fragment

import android.annotation.SuppressLint
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.momo.activity.TransferDetailActivity
import com.example.momo.adapter.ChatAdapter
import com.example.momo.adapter.ContactAdapter
import com.example.momo.common.Constant
import com.example.momo.databinding.FragmentChatBinding
import com.example.momo.model.ConversationModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class ChatFragment : BaseFragment<FragmentChatBinding>() {
    override fun getViewBinding(): FragmentChatBinding {
        return FragmentChatBinding.inflate(layoutInflater)
    }

    private lateinit var contactAdapter: ContactAdapter
    private lateinit var chatAdapter: ChatAdapter
    private var listConversation: ArrayList<String> = ArrayList()
    private var listConversationModel: ArrayList<ConversationModel> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    override fun setup() {
        contactAdapter = ContactAdapter(requireContext(),
            object : ContactAdapter.OnContactClickListener {
                override fun onContactClickListener(pos: Int) {
                    startActivity(
                        Intent(
                            requireContext(),
                            TransferDetailActivity::class.java
                        ).putExtra(
                            Constant.PHONE_NUMBER,
                            Constant.listConversationDetail[pos].phoneNumber
                        )
                    )
                }
            })
        binding.rcvContact.adapter = contactAdapter
        binding.rcvContact.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        chatAdapter = ChatAdapter(Constant.listConversationDetail, requireContext())
        binding.rcvChat.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcvChat.adapter = chatAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        getListConversation()

    }

    private fun getListConversation() {
        val fireStore = FirebaseFirestore.getInstance().collection("chat_data")
        fireStore.whereEqualTo(Constant.SENDER, Constant.userModel.user_id)
            .addSnapshotListener(eventListener)
        fireStore.whereEqualTo(Constant.RECEIVER, Constant.userModel.user_id)
            .addSnapshotListener(eventListener)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val eventListener: EventListener<QuerySnapshot> =
        label@ EventListener { value: QuerySnapshot?, error: FirebaseFirestoreException? ->
            if (error != null) {
                return@EventListener
            }
            if (value != null) {
                for (documentChange in value.documentChanges) {
                    val chatMessage =
                        Constant.castDataToConversationModel(documentChange.document.data)
                    listConversationModel.add(chatMessage)
                    listConversation.add(chatMessage.sender)
                    listConversation.add(chatMessage.receiver)
                }
            }
            val tmp = listConversationModel.toSet()
            listConversationModel.clear()
            listConversationModel.addAll(tmp)

            val tmp2 = listConversation.toSet()
            listConversation.clear()
            listConversation.addAll(tmp2)

            for (i in listConversation) {
                FirebaseFirestore.getInstance().collection("user_data").document(i).get()
                    .addOnSuccessListener { doc ->
                        val contacInfor = doc.data?.let { Constant.castDataToUserModel(it) }
                        contacInfor?.let { Constant.listConversationDetail.add(it) }
                    }
            }
            Constant.listConversationDetail.toSet()

            chatAdapter.notifyDataSetChanged()
            contactAdapter.setListContact(Constant.listConversationDetail)

        }

}