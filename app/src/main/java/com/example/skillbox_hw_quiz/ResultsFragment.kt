package com.example.skillbox_hw_quiz

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationSet
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.databinding.FragmentResultsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ResultsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        binding.result0.text = arguments?.getString("MyArgs")

        val animatorSet = AnimatorSet()
        val animationRight = ObjectAnimator.ofFloat(
            binding.startAgain,
            View.TRANSLATION_X,
            0f,
            360f
        ).apply {
            duration = 500
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateInterpolator()
            start()
        }

        val animationLeft = ObjectAnimator.ofFloat(
            binding.startAgain,
            View.TRANSLATION_X,
            0f,
            -360f
        ).apply {
            duration = 500
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateInterpolator()
            start()
        }

        animatorSet.play(animationRight).before(animationLeft)
        animatorSet.start()

        ObjectAnimator.ofFloat(
            binding.result0,
            View.ROTATION,
            0f,
            360f
        ).apply {
            duration = 500
            repeatCount = 2
            start()
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startAgain.setOnClickListener {
            findNavController().navigate(R.id.action_resultsFragment_to_questionsFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}