package jamoliddin.tj.mytest.presentation.screens.auth.login

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignInClient

//fun getClient(): GoogleSignInClient{
//    val signInRequest = BeginSignInRequest.builder()
//        .setGoogleIdTokenRequestOptions(
//            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                .setSupported(true)
//                // Your server's client ID, not your Android client ID.
//                .setServerClientId(getString(R.string.your_web_client_id))
//                // Only show accounts previously used to sign in.
//                .setFilterByAuthorizedAccounts(true)
//                .build())
//        .build()
//}