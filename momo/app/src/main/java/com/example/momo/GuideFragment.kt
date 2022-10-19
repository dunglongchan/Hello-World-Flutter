package com.example.momo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.momo.databinding.FragmentGuideBinding

class GuideFragment : Fragment() {
    private val ARG_OBJECT = "position"
    private lateinit var mBinding: FragmentGuideBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentGuideBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            docApp(it.getInt(ARG_OBJECT))
        }
    }

    private fun docApp(position: Int) {
        when (position) {
            0 -> {
                mBinding.ivIntro.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.img1
                    )
                )
                mBinding.tvTitle.text = getString(R.string.intro1)
            }
            1 -> {
                mBinding.ivIntro.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.img3
                    )
                )
                mBinding.tvTitle.text = resources.getString(R.string.intro2)
            }
            2 -> {
                mBinding.ivIntro.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.img3
                    )
                )
                mBinding.tvTitle.text = resources.getString(R.string.intro3)
            }
            else -> {
                mBinding.ivIntro.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.img1
                    )
                )
                mBinding.tvTitle.text = resources.getString(R.string.intro4)
            }
        }
    }

}