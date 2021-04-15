package com.example.architecturesample.ui.fragment.board.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.architecturesample.R
import com.example.architecturesample.databinding.CreateBoardFragementBinding
import com.gondev.imagelist.util.dataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateBoardFragment : Fragment(R.layout.fragment_create_board) {

    private val binding: CreateBoardFragementBinding by dataBinding()
    private val viewModel: CreateBoardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}