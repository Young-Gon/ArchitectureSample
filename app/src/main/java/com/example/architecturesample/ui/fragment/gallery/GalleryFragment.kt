package com.example.architecturesample.ui.fragment.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.example.architecturesample.BR
import com.example.architecturesample.R
import com.example.architecturesample.databinding.GalleryFragmentBinding
import com.example.architecturesample.databinding.ViewpagerImageItemBinding
import com.example.architecturesample.model.network.response.ImageData
import com.gondev.imagelist.util.DataBindingPagingDataAdapter
import com.gondev.imagelist.util.dataBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import timber.log.Timber

class GalleryFragment : Fragment() {

    private val binding: GalleryFragmentBinding by dataBinding()
    private val viewModel: GalleryViewModel by stateViewModel(state = {arguments?: Bundle() })

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        // 하단 정보 레이아웃을 네비게이션 바 위로 올림
        (binding.frameLayout2.layoutParams as ConstraintLayout.LayoutParams).bottomMargin =
                getNavigationbarHeight()

        val adapter = DataBindingPagingDataAdapter<ImageData, ViewpagerImageItemBinding>(
                layoutResId = R.layout.item_viewpager_image,
                bindingVariableId = BR.item,
                diffCallback = object : DiffUtil.ItemCallback<ImageData>() {
                    override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData) =
                            oldItem.id == newItem.id

                    override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData) =
                            oldItem == newItem
                },
                lifecycleOwner = viewLifecycleOwner
        ).also { binding.viewPager.adapter = it }

        viewModel.images.observe(viewLifecycleOwner) { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        }
        /*lifecycleScope.launch {
            viewModel.images.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }*/
    }

    /**
     * 하단 네비게이션바 높이
     * @return 하단 네비게이션바 높이
     */
    private fun getNavigationbarHeight(): Int {
        val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId).also {
                Timber.d("navigationbar size=$it")
            }
        } else 0
    }
}