package com.wappiter.app.module.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView.OnEditorActionListener
import com.wappiter.app.BuildConfig
import com.wappiter.app.R
import com.wappiter.app.base.BaseActivity
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.login.presenter.LoginPresenter
import com.wappiter.app.module.login.presenter.LoginView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.analytics.ScreenNames
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar_with_back.*
import javax.inject.Inject


class LoginActivity : BaseActivity(), LoginView {

    private val TAG: String = LoginActivity::class.toString()
    //private val RC_SIGN_IN = 9001

    @Inject
    lateinit var mPresenter: LoginPresenter

//    private lateinit var mGoogleSignInClient: GoogleSignInClient
//    private lateinit var mGoogleSignInOptions: GoogleSignInOptions
//
//    private lateinit var auth: FirebaseAuth
//    private lateinit var callbackManager: CallbackManager

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mPresenter.attachView(this)

//        sign_in_with_google_button.setOnClickListener { mPresenter.onLoginWithGoogleClick() }
//        sign_in_with_facebook_button.setOnClickListener { mPresenter.onLoginWithFacebookClick() }

    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == RC_SIGN_IN) {
//            try {
//                // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//                // The Task returned from this call is always completed, no need to attach a listener.
//                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//                handleSignInResult(task)
//
//            } catch (e: ApiException) {
//                mPresenter.onLoginWithGoogleFailed(e)
//            }
//        } else {
//            // Pass the activity result back to the Facebook SDK
//            callbackManager.onActivityResult(requestCode, resultCode, data)
//        }
//    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun getActivityTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.LOGIN
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(LoginModule(this))?.inject(this)
    }

    override fun setUpView() {
        initToolbar()
        setUpButtons()

        login_til_password.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                mPresenter.didClickLoginButton(login_til_email.text.toString(), login_til_password.text.toString())
                true
            } else false
        })

        if (BuildConfig.DEBUG) {
            login_til_email.text = Editable.Factory.getInstance().newEditable("app2@example.com")
            login_til_password.text = Editable.Factory.getInstance().newEditable("password")
        }
    }

    private fun setUpButtons() {
        login_button_login.setOnClickListener {
            mPresenter.didClickLoginButton(login_til_email.text.toString(), login_til_password.text.toString())
        }
        val registerButton: Button = findViewById(R.id.login_button_register)
        registerButton.text = getText(R.string.login_register_button)
        registerButton.setOnClickListener {
            mPresenter.didClickRegisterButton()
        }
        val resetPasswordButton: Button = findViewById(R.id.login_button_reset_password)
        resetPasswordButton.text = getText(R.string.login_reset_password_button)
        resetPasswordButton.setOnClickListener {
            mPresenter.didClickResetPasswordButton()
        }
    }

    private fun initToolbar() {
        toolbar_back_button.setOnClickListener { mPresenter.didClickToolbarBackButton() }
    }


//    override fun signInWithGoogle() {
//        val signInIntent: Intent = mGoogleSignInClient.signInIntent
//        startActivityForResult(signInIntent, RC_SIGN_IN)
//    }
//
//    override fun signInWithFacebook() {
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"))
//        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
//            override fun onSuccess(loginResult: LoginResult) {
//                Log.d(TAG, "facebook:onSuccess:$loginResult")
//                handleFacebookAccessToken(loginResult.accessToken)
//            }
//
//            override fun onCancel() {
//                Log.d(TAG, "facebook:onCancel")
//                mPresenter.onLoginWithFacebookCancel("facebook:onCancel")
//            }
//
//            override fun onError(error: FacebookException) {
//                Log.d(TAG, "facebook:onError", error)
//                mPresenter.onLoginWithFacebookFailed(error)
//            }
//        })
//    }
//
//    private fun configureGoogleSignIn() {
//        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.google_oauth_key))
//                .requestEmail()
//                .build()
//        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
//    }
//
//    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
//        try {
//            val account = completedTask.getResult(ApiException::class.java)!!
//
//            if (BuildConfig.DEBUG) {
//                Log.d(TAG, "  Google Sign in handleSignInResult")
//                Log.d(TAG, "  Name: " + account.displayName)
//                Log.d(TAG, "  Family: " + account.familyName)
//                Log.d(TAG, "  Given: " + account.givenName)
//                Log.d(TAG, "  Id: " + account.id)
//                Log.d(TAG, "  Token: " + account.idToken)
//                Log.d(TAG, "  Email: " + account.email)
//                Log.d(TAG, "  Photo: " + account.photoUrl)
//            }
//
//            val photoUrl = if (account.photoUrl != null) account.photoUrl.toString() else null
//
//            mPresenter.onLoginWithGoogleSuccess(account.givenName!!,
//                    account.familyName!!,
//                    account.email!!,
//                    photoUrl,
//                    account.id!!,
//                    account.idToken!!)
//        } catch (e: ApiException) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            mPresenter.onLoginWithGoogleFailed(e)
//        }
//    }
//
//    private fun configureFacebookSignIn() {
//        // Initialize Firebase Auth
//        auth = FirebaseAuth.getInstance()
//
//        // Initialize Facebook Login button
//        callbackManager = CallbackManager.Factory.create()
//    }
//
//    // [START auth_with_facebook]
//    private fun handleFacebookAccessToken(token: AccessToken) {
//        Log.d(TAG, "handleFacebookAccessToken:$token")
//        // [START_EXCLUDE silent]
//        // [END_EXCLUDE]
//
//        val credential = FacebookAuthProvider.getCredential(token.token)
//        auth.signInWithCredential(credential)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "signInWithCredential:success")
//
//                        val user = auth.currentUser
//                        if (user != null) {
//
//                            if (BuildConfig.DEBUG) {
//                                Log.d(TAG, "  Google Sign in handleSignInResult")
//                                Log.d(TAG, "  Name: " + user.displayName)
//                                Log.d(TAG, "  Id: " + user.uid)
//                                Log.d(TAG, "  Token: " + user.providerId)
//                                Log.d(TAG, "  Email: " + user.email)
//                                Log.d(TAG, "  Photo: " + user.photoUrl)
//                            }
//                            val photoUrl = if (user.photoUrl != null) user.photoUrl.toString() else null
//
//                            mPresenter.onLoginWithFacebookSuccess(
//                                    user.displayName ?: "",
//                                    user.email ?: "",
//                                    photoUrl,
//                                    user.uid,
//                                    user.providerId)
//
//                            signOut()
//
//                        } else {
//                            mPresenter.onLoginWithFacebookCredentialFailed(Exception("signInFacebookWithCredential:failure"))
//                        }
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInWithCredential:failure", task.exception)
//                        mPresenter.onLoginWithFacebookCredentialFailed(task.exception)
//                    }
//                    // [START_EXCLUDE]
//                    // [END_EXCLUDE]
//                }
//    }
//
//    fun signOut() {
//        auth.signOut()
//        LoginManager.getInstance().logOut()
//    }
//
//    // [END auth_with_facebook]
}