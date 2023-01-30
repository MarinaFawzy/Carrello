// Generated by view binder compiler. Do not edit!
package com.example.convertapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.convertapp.R;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegistrationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText Eemail;

  @NonNull
  public final EditText Ejob;

  @NonNull
  public final EditText Ename;

  @NonNull
  public final EditText Ephone;

  @NonNull
  public final EditText Euname;

  @NonNull
  public final TextView cpass;

  @NonNull
  public final TextView date;

  @NonNull
  public final TextInputLayout ecpass;

  @NonNull
  public final EditText edate;

  @NonNull
  public final TextView email;

  @NonNull
  public final TextInputLayout epass;

  @NonNull
  public final RadioButton female;

  @NonNull
  public final RadioGroup gender;

  @NonNull
  public final Guideline guideline1;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final TextView job;

  @NonNull
  public final RadioButton male;

  @NonNull
  public final TextView name;

  @NonNull
  public final TextView pass;

  @NonNull
  public final TextView phone;

  @NonNull
  public final Button signin;

  @NonNull
  public final Button signup;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView username;

  private ActivityRegistrationBinding(@NonNull ConstraintLayout rootView, @NonNull EditText Eemail,
      @NonNull EditText Ejob, @NonNull EditText Ename, @NonNull EditText Ephone,
      @NonNull EditText Euname, @NonNull TextView cpass, @NonNull TextView date,
      @NonNull TextInputLayout ecpass, @NonNull EditText edate, @NonNull TextView email,
      @NonNull TextInputLayout epass, @NonNull RadioButton female, @NonNull RadioGroup gender,
      @NonNull Guideline guideline1, @NonNull ImageView imageView2, @NonNull TextView job,
      @NonNull RadioButton male, @NonNull TextView name, @NonNull TextView pass,
      @NonNull TextView phone, @NonNull Button signin, @NonNull Button signup,
      @NonNull TextView textView2, @NonNull TextView username) {
    this.rootView = rootView;
    this.Eemail = Eemail;
    this.Ejob = Ejob;
    this.Ename = Ename;
    this.Ephone = Ephone;
    this.Euname = Euname;
    this.cpass = cpass;
    this.date = date;
    this.ecpass = ecpass;
    this.edate = edate;
    this.email = email;
    this.epass = epass;
    this.female = female;
    this.gender = gender;
    this.guideline1 = guideline1;
    this.imageView2 = imageView2;
    this.job = job;
    this.male = male;
    this.name = name;
    this.pass = pass;
    this.phone = phone;
    this.signin = signin;
    this.signup = signup;
    this.textView2 = textView2;
    this.username = username;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegistrationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegistrationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_registration, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegistrationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Eemail;
      EditText Eemail = ViewBindings.findChildViewById(rootView, id);
      if (Eemail == null) {
        break missingId;
      }

      id = R.id.Ejob;
      EditText Ejob = ViewBindings.findChildViewById(rootView, id);
      if (Ejob == null) {
        break missingId;
      }

      id = R.id.Ename;
      EditText Ename = ViewBindings.findChildViewById(rootView, id);
      if (Ename == null) {
        break missingId;
      }

      id = R.id.Ephone;
      EditText Ephone = ViewBindings.findChildViewById(rootView, id);
      if (Ephone == null) {
        break missingId;
      }

      id = R.id.Euname;
      EditText Euname = ViewBindings.findChildViewById(rootView, id);
      if (Euname == null) {
        break missingId;
      }

      id = R.id.cpass;
      TextView cpass = ViewBindings.findChildViewById(rootView, id);
      if (cpass == null) {
        break missingId;
      }

      id = R.id.date;
      TextView date = ViewBindings.findChildViewById(rootView, id);
      if (date == null) {
        break missingId;
      }

      id = R.id.ecpass;
      TextInputLayout ecpass = ViewBindings.findChildViewById(rootView, id);
      if (ecpass == null) {
        break missingId;
      }

      id = R.id.edate;
      EditText edate = ViewBindings.findChildViewById(rootView, id);
      if (edate == null) {
        break missingId;
      }

      id = R.id.email;
      TextView email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.epass;
      TextInputLayout epass = ViewBindings.findChildViewById(rootView, id);
      if (epass == null) {
        break missingId;
      }

      id = R.id.female;
      RadioButton female = ViewBindings.findChildViewById(rootView, id);
      if (female == null) {
        break missingId;
      }

      id = R.id.gender;
      RadioGroup gender = ViewBindings.findChildViewById(rootView, id);
      if (gender == null) {
        break missingId;
      }

      id = R.id.guideline1;
      Guideline guideline1 = ViewBindings.findChildViewById(rootView, id);
      if (guideline1 == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.job;
      TextView job = ViewBindings.findChildViewById(rootView, id);
      if (job == null) {
        break missingId;
      }

      id = R.id.male;
      RadioButton male = ViewBindings.findChildViewById(rootView, id);
      if (male == null) {
        break missingId;
      }

      id = R.id.name;
      TextView name = ViewBindings.findChildViewById(rootView, id);
      if (name == null) {
        break missingId;
      }

      id = R.id.pass;
      TextView pass = ViewBindings.findChildViewById(rootView, id);
      if (pass == null) {
        break missingId;
      }

      id = R.id.phone;
      TextView phone = ViewBindings.findChildViewById(rootView, id);
      if (phone == null) {
        break missingId;
      }

      id = R.id.signin;
      Button signin = ViewBindings.findChildViewById(rootView, id);
      if (signin == null) {
        break missingId;
      }

      id = R.id.signup;
      Button signup = ViewBindings.findChildViewById(rootView, id);
      if (signup == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      return new ActivityRegistrationBinding((ConstraintLayout) rootView, Eemail, Ejob, Ename,
          Ephone, Euname, cpass, date, ecpass, edate, email, epass, female, gender, guideline1,
          imageView2, job, male, name, pass, phone, signin, signup, textView2, username);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}