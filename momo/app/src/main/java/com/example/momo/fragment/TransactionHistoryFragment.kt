package com.example.momo.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.momo.adapter.TransactionHistoryAdapter
import com.example.momo.database.AppDataBase
import com.example.momo.database.Transaction
import com.example.momo.databinding.FragmentTransactionHistoryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionHistoryFragment : BaseFragment<FragmentTransactionHistoryBinding>() {
    override fun getViewBinding(): FragmentTransactionHistoryBinding {
        return FragmentTransactionHistoryBinding.inflate(layoutInflater)
    }

    private lateinit var transactionHistoryAdapter: TransactionHistoryAdapter
    private lateinit var appDataBase: AppDataBase
    private lateinit var listTrans: ArrayList<Transaction>

    override fun setup() {
        appDataBase = AppDataBase.getAppDatabase(requireContext())!!
        transactionHistoryAdapter = TransactionHistoryAdapter(requireContext(),
            object : TransactionHistoryAdapter.OnClickTransaction {
                override fun onClickTransaction(position: Int) {

                }
            })
        binding.rcvTransaction.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvTransaction.adapter = transactionHistoryAdapter
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                listTrans =
                    appDataBase.TransactionDao().getAllTransaction() as ArrayList<Transaction>
            }
            withContext(Dispatchers.Main) {
                transactionHistoryAdapter.setData(listTrans)
            }
        }
    }
}