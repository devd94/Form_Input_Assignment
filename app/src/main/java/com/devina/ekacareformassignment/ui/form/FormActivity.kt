package com.devina.ekacareformassignment.ui.form

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.devina.ekacareformassignment.App
import com.devina.ekacareformassignment.R
import com.devina.ekacareformassignment.data.database.models.User
import com.devina.ekacareformassignment.databinding.ActivityFormBinding
import com.devina.ekacareformassignment.utils.observe
import com.devina.ekacareformassignment.viewmodels.FormViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding
    private lateinit var viewModel: FormViewModel

    var dob = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(FormViewModel::class.java)

        setContentView(binding.root)

        setUpViews()

        observeViewModel()

        observeActivity()
    }

    private fun observeViewModel()
    {
        observe(viewModel.userInsertedLiveData, ::handleUserInsertedData)
        observe(viewModel.allUsersLiveData, ::handleAllUsersData)
    }

    private fun setUpViews() {

        binding.tvDobInput.setOnClickListener {
            val calendar: Calendar = if(binding.tvDobInput.text.toString().isNotBlank())
            {
                val c = Calendar.getInstance()
                c.set(Calendar.YEAR, dob.extractYear())
                c.set(Calendar.MONTH, dob.extractMonth()-1)
                c.set(Calendar.DAY_OF_MONTH, dob.extractDayOfMonth())
                c
            }
            else
            {
                Calendar.getInstance()
            }
            //set up calendar picker
            setUpCalendar(calendar)
        }

        binding.btnSave.setOnClickListener {
            //validate input
            validateInput()
        }
    }

    private fun observeActivity()
    {

    }

    private fun setUpCalendar(calendar: Calendar)
    {
        val datePickerDialog = DatePickerDialog(
            this,
            R.style.DialogStyle,
            {view, selectedYear, monthOfYear, dayOfMonth ->

                dob = dayOfMonth.toString() + "-" + (monthOfYear+1) + "-" + selectedYear
                binding.tvDobInput.text = dob

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun validateInput()
    {
        var name = binding.etNameInput.text.toString()
        var age = binding.etAgeInput.text.toString()
        var address = binding.etAddressInput.text.toString()

        if(name.isNullOrBlank())
        {
            binding.etNameInput.error = ContextCompat.getString(this, R.string.name_reqd_error_msg)
            binding.etNameInput.requestFocus()
            Snackbar.make(binding.root, R.string.name_reqd_error_msg, Snackbar.LENGTH_SHORT).show()
        }
        else
            if(age.isNullOrBlank())
            {
                binding.etAgeInput.error = ContextCompat.getString(this, R.string.age_reqd_error_msg)
                binding.etAgeInput.requestFocus()
                Snackbar.make(binding.root, R.string.age_reqd_error_msg, Snackbar.LENGTH_SHORT).show()
            }
            else
                if(dob.isNullOrBlank())
                {
                    Snackbar.make(binding.root, R.string.dob_reqd_error_msg, Snackbar.LENGTH_SHORT).show()
                }
                else
                {
                    val ageNum =
                        try {
                            age.toInt()
                        }
                        catch (nfe: NumberFormatException)
                        {
                            -1
                        }


                    if(!ageMatchesDob(ageNum))
                    {
                        Snackbar.make(binding.root, R.string.age_dob_mismatch_error_msg, Snackbar.LENGTH_SHORT).show()
                        return
                    }

                    val user = User(name, ageNum, dob, address)
                    binding.progressBar.visibility = View.VISIBLE
                    viewModel.addUser(user)
                }

    }

    private fun ageMatchesDob(age: Int): Boolean
    {
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dobD = dob.extractDayOfMonth()
        val dobM = dob.extractMonth()-1
        val dobY = dob.extractYear()

        return if(dobY+age==year && (month>dobM || (month==dobM && day>=dobD))) {
            true
        } else
        if(dobY+age+1==year && (month<dobM || (month==dobM && day<dobD)))
        {
            true
        }
        else
        {
            false
        }
    }

    private fun handleUserInsertedData(userId: Long)
    {
        //dismiss loader
        binding.progressBar.visibility = View.GONE

        //show success snackbar
        Snackbar.make(binding.root, R.string.user_saved_msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun handleAllUsersData(userList : List<User>)
    {
        //use user data
    }

    private fun String.extractDayOfMonth() = substring(0, this.indexOfFirst { it=='-' }).toInt()

    private fun String.extractMonth() = substring(this.indexOfFirst{ it=='-' }+1, this.indexOfLast{ it=='-' }).toInt()

    private fun String.extractYear() = substring(this.indexOfLast { it=='-' }+1).toInt()
}