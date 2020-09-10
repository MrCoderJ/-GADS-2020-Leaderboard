package com.example.gadproject.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gadproject.R
import com.example.gadproject.api.GoogleFormClient
import com.example.gadproject.ui.widgets.ConfirmDialog
import com.example.gadproject.ui.widgets.ErrorMessageDialog
import com.example.gadproject.ui.widgets.MessageDialog
import kotlinx.android.synthetic.main.activity_project_submission.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectSubmissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_submission)

        setSupportActionBar(toolbar)
        btn_submit.setOnClickListener {
            submitForm()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("goto", MainActivity.HOURS)
        startActivity(intent)
    }


    private fun submitForm(){
        var allGood = true
        val firstName = editext_firstName.text.toString()
        if (firstName.isEmpty()){
            allGood = false
            editext_firstName.error = getString(R.string.firstName)
        }

        val lastName = edittext_lastName.text.toString()
        if (lastName.isEmpty()){
            allGood = false
            edittext_lastName.error = getString(R.string.lastName)
        }

        val emailAddress = editText_emailAddress.text.toString()
        if (emailAddress.isEmpty()){
            allGood = false
            editText_emailAddress.error = getString(R.string.emailAddress)
        }

        val gitHubLink = editText_link.text.toString()
        if (gitHubLink.isEmpty()){
            allGood = false
            editText_link.error = getString(R.string.githubName)
        }

        ConfirmDialog(this, "Are you sure?").show()
        if (allGood){
            val webService = GoogleFormClient.getClient(this)
            webService.postResponse(emailAddress, firstName, lastName, gitHubLink).enqueue(object : Callback<Void>{
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    ErrorMessageDialog(this@ProjectSubmissionActivity, "Submission not Successful" ).show()
                }
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    MessageDialog(this@ProjectSubmissionActivity, "Submission Successful").show()
                }
            })
        }
    }
}