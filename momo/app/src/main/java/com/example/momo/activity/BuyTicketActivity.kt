package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momo.R
import com.example.momo.common.Common
import com.example.momo.common.Constant
import com.example.momo.database.AppDataBase
import com.example.momo.database.Transaction
import com.example.momo.databinding.ActivityBuyTicketBinding
import com.example.momo.databinding.ItemMovieBinding
import com.example.momo.fragment.OnItemVoucherCLick
import com.example.momo.model.TransactionModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class BuyTicketActivity : AppCompatActivity(), OnItemVoucherCLick {

    private lateinit var binding: ActivityBuyTicketBinding
    private lateinit var appDataBase: AppDataBase
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        val configuration = Configuration()
        configuration.setLocale(Locale(Common.getPreLanguage(this)!!))
        applyOverrideConfiguration(configuration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Common.setLocale(this, Common.getPreLanguage(this))

        appDataBase = AppDataBase.getAppDatabase(this)!!
        setUpView()
    }

    private fun setUpView() {

        binding.ivBacik.setOnClickListener { onBackPressed() }

        val listPoster =
            arrayListOf<Int>(
                R.drawable.image321,
                R.drawable.image322,
                R.drawable.image323,
                R.drawable.image323,
                R.drawable.image322,
                R.drawable.image321
            )

        val adapter = MovieAdapter(listPoster, this@BuyTicketActivity, this)
        binding.rcvMovie.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvMovie.adapter = adapter
    }

    override fun onItemClick() {

    }

    @SuppressLint("StringFormatInvalid")
    override fun onCLickItemBuy() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.are_you_sure))
        dialog.setMessage(getString(R.string.cost))
        dialog.setPositiveButton(getString(R.string.yes)) { dialog, which ->
            val tmp: Long = 79000
            if (tmp > (Constant.userModel.security[Constant.BALANCE] as Long)) {
                Toast.makeText(this@BuyTicketActivity, "So du khong du", Toast.LENGTH_SHORT)
                    .show()
                dialog.dismiss()
            }
            GlobalScope.launch {
                makeTransaction()
            }
            dialog.dismiss()
            Toast.makeText(applicationContext, getString(R.string.buy_success), Toast.LENGTH_SHORT)
                .show()
        }
        dialog.setNegativeButton(getString(R.string.no)) { dialog, which ->
            dialog.dismiss()
        }
        dialog.setCancelable(true)
        dialog.show()

    }

    private suspend fun makeTransaction() {
        withContext(Dispatchers.Main) {

            val fireStore = FirebaseFirestore.getInstance().collection("user_data")

            val balance = Constant.userModel.security[Constant.BALANCE] as Long

            Constant.userModel.security[Constant.BALANCE] = balance - 79000

            val updateData2 = Constant.getUserModelData(Constant.userModel)
            fireStore.document(Constant.userModel.user_id).update(updateData2)


            val c = Calendar.getInstance().time
//            println("Current time => $c")

            val df = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
            val formattedDate: String = df.format(c)

            val transaction = Transaction(
                "${Constant.userModel.user_id}_trans_buyTicket_$formattedDate",
                Constant.userModel.user_id,
                "buyTicket",
                formattedDate,
                "79000"
            )

            val transaction2 = TransactionModel(
                "${Constant.userModel.user_id}_trans_buyTicket_$formattedDate",
                Constant.userModel.user_id,
                "buyTicket",
                formattedDate,
                "79000"
            )

            appDataBase.TransactionDao().insertTransaction(transaction)

            FirebaseFirestore.getInstance().collection("transaction_data")
                .document(transaction.transactionId)
                .set(Constant.getTransactionModelData(transaction2))
        }
    }

}

class MovieAdapter(
    val list: ArrayList<Int>,
    val context: Context,
    val listener: OnItemVoucherCLick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class MovieViewHolder(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val poster = binding.ivPoster
        val buy = binding.tvBuy
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as MovieViewHolder
        Glide.with(context).load(list[position]).into(viewHolder.poster)
        viewHolder.buy.setOnClickListener { listener.onCLickItemBuy() }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
