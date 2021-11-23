package com.fic.muei.lactachain.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fic.muei.lactachain.databinding.FragmentMilkCollectionBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MilkCollection.newInstance] factory method to
 * create an instance of this fragment.
 */
class MilkCollection : Fragment() {
    private lateinit var binding: FragmentMilkCollectionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMilkCollectionBinding.inflate(layoutInflater)
        val args = MilkCollectionArgs.fromBundle(requireArguments())
        binding.FarmName.text = args.farm.name
        binding.townHall.setText(args.farm.town)
        return binding.root
    }
}