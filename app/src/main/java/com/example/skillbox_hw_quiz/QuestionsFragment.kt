package com.example.skillbox_hw_quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.databinding.FragmentQuestionsBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage

class QuestionsFragment : Fragment() {

    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    private val quiz = QuizStorage.getQuiz(QuizStorage.Locale.Ru)
    private val bundle = Bundle()
    private lateinit var listOfButtonGroup: List<RadioGroup>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)

        listOfButtonGroup = fillQuestions(binding.myLinerLayout)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
            findNavController().navigate(R.id.action_questionsFragment_to_welcomeFragment)
        }

        binding.buttonSend.setOnClickListener {
            parentFragmentManager.popBackStack()

            val listOfSelectedButton = mutableListOf<Int>()
            listOfButtonGroup.forEach {
                if (it.checkedRadioButtonId != -1) {
                    listOfSelectedButton.add(it.checkedRadioButtonId)
                }
            }
            if (listOfSelectedButton.size == quiz.questions.size) {
                val results = QuizStorage.answer(quiz, listOfSelectedButton)
                bundle.putString("MyArgs", results)
                findNavController().navigate(R.id.action_questionsFragment_to_resultsFragment)
                findNavController().navigate(R.id.resultsFragment, bundle)


            } else Toast.makeText(context, "Вы не выбрали все ответы", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fillQuestions(linearLayout: LinearLayout): List<RadioGroup> {
        val quiz = QuizStorage.getQuiz(QuizStorage.Locale.Ru)
        val listOfGroup = mutableListOf<RadioGroup>()
        quiz.questions.forEachIndexed { index, _ ->
            val textViewQuestion = TextView(context)
            textViewQuestion.text = quiz.questions[index].question
            linearLayout.addView(textViewQuestion)
            val radioGroup = RadioGroup(context)
            var count = 0
            quiz.questions[index].answers.forEach {
                val radioButton = RadioButton(context)
                radioButton.id = count
                radioButton.text = it
                radioGroup.addView(radioButton)
                count++
            }
            listOfGroup.add(radioGroup)
            linearLayout.addView(radioGroup)
        }
        return listOfGroup.toList()
    }
}
