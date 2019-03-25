package br.com.netshoes.showcase.Login

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.netshoes.showcase.*
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin: AppCompatActivity() {

    companion object {
        private const val MOCK_USER = "user"
        private const val MOCK_PASSWORD = "123456"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            val user = login_user.text?.toString()?: TEXT_EMPTY
            val password = login_password.text?.toString()?: TEXT_EMPTY

            if (user.isEmpty() and  password.isEmpty()) {
                showDialogError()
            } else if (user != MOCK_USER || password != MOCK_PASSWORD) {
                showDialogError()
            } else {
                getSharedPreferences(NAME_DATABASE, Context.MODE_PRIVATE).edit()
                    .putBoolean(USER_LOGGED_KEY, true)
                    .apply()
                this.goListMovies()
            }
        }
    }

    private fun showDialogError() {
        AlertDialog.Builder(this)
            .setMessage(LOGIN_MESSAGE_ERROR)
            .setTitle(LOGIN_TITLE_ERROR)
            .setPositiveButton(LOGIN_BUTTON_ERROR) { dialog, which -> dialog?.dismiss() }
            .create().show()
    }
}

