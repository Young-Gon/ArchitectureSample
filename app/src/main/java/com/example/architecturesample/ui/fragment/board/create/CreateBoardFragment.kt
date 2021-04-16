package com.example.architecturesample.ui.fragment.board.create

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.architecturesample.R
import com.example.architecturesample.databinding.CreateBoardFragementBinding
import com.example.architecturesample.util.EventObserver
import com.example.architecturesample.util.NetworkState
import com.example.architecturesample.util.savedStateViewModel
import com.gondev.imagelist.util.dataBinding
import timber.log.Timber

class CreateBoardFragment : Fragment(R.layout.fragment_create_board) {

    private val binding: CreateBoardFragementBinding by dataBinding()
    private val viewModel: CreateBoardViewModel by savedStateViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.v("postId=${arguments?.get("postId")}")
        binding.vm = viewModel

        viewModel.confirmButtonResult.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireContext(), it.data, Toast.LENGTH_SHORT).show()
            if (it is NetworkState.Success) {
                findNavController().navigateUp()
            }
        })
    }
}
