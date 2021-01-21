package com.shiraj.googlesigndemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.view.SimpleDraweeView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvUserName = findViewById<TextView>(R.id.tvUserName)
        val tvUserEmail = findViewById<TextView>(R.id.tvUserEmail)
        val tvGivenName = findViewById<TextView>(R.id.tvGivenName)
        val tvFamilyName = findViewById<TextView>(R.id.tvFamilyName)
        val tvId = findViewById<TextView>(R.id.tvId)
        val ivProfilePic = findViewById<SimpleDraweeView>(R.id.ivProfilePic)

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val signInAccount = GoogleSignIn.getLastSignedInAccount(this)
        if (signInAccount != null) {
            tvUserName.text = signInAccount.displayName
            tvUserEmail.text = signInAccount.email
            tvGivenName.text = signInAccount.givenName
            tvFamilyName.text = signInAccount.familyName
            tvId.text = signInAccount.id
            ivProfilePic.setImageURI(signInAccount.photoUrl, this)
        }
    }
}