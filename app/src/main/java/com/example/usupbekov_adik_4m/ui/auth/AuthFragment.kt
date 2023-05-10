package com.example.usupbekov_adik_4m.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.usupbekov_adik_4m.R
import com.example.usupbekov_adik_4m.databinding.FragmentAuthBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import org.jetbrains.annotations.Nullable


class AuthFragment : Fragment() {
    private lateinit var signInRequest: BeginSignInRequest
    private lateinit var binding: FragmentAuthBinding
    private val REQ_ONE_TAP = 2
    private val showOneTapUI = true
    private lateinit var auth: FirebaseAuth
    private lateinit var oneTapClient: SignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.btnNumber.setOnClickListener {
            findNavController().navigate(R.id.verifyFragment)
        }
        oneTapClient = Identity.getSignInClient(requireActivity())
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.default_web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
        binding.btnGoogle.setOnClickListener {
            signingGoogle()
        }
    }

    private fun signingGoogle() {
        oneTapClient.beginSignIn(signInRequest).addOnSuccessListener {
            startIntentSenderForResult(
                it.pendingIntent.intentSender, REQ_ONE_TAP,
                null,0,0,0,null
            )
        }.addOnFailureListener {
            Log.d("ololo", "signingGoogle: " + it.message)
        }
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_ONE_TAP -> try {
                val credential: SignInCredential = oneTapClient.getSignInCredentialFromIntent(data)
                val idToken = credential.googleIdToken
                if (idToken != null) {
                    val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                    auth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener(requireActivity(),
                            OnCompleteListener<AuthResult?> { task ->
                                if (task.isSuccessful) {
                                    findNavController().navigateUp()
                                } else {
                                    Log.d("olol", "onActivityResult: " + task.exception)
                                }
                            })
                }
            } catch (e: ApiException) {
            }
        }
    }

}