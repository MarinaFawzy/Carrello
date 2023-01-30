// Generated by view binder compiler. Do not edit!
package com.example.convertapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.convertapp.R;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityChangepassBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button changepass;

  @NonNull
  public final TextView confirm;

  @NonNull
  public final TextInputLayout confirmpassword;

  @NonNull
  public final TextView pass;

  @NonNull
  public final TextInputLayout password;

  private ActivityChangepassBinding(@NonNull ConstraintLayout rootView, @NonNull Button changepass,
      @NonNull TextView confirm, @NonNull TextInputLayout confirmpassword, @NonNull TextView pass,
      @NonNull TextInputLayout password) {
    this.rootView = rootView;
    this.changepass = changepass;
    this.confirm = confirm;
    this.confirmpassword = confirmpassword;
    this.pass = pass;
    this.password = password;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityChangepassBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityChangepassBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_changepass, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityChangepassBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.changepass;
      Button changepass = ViewBindings.findChildViewById(rootView, id);
      if (changepass == null) {
        break missingId;
      }

      id = R.id.confirm;
      TextView confirm = ViewBindings.findChildViewById(rootView, id);
      if (confirm == null) {
        break missingId;
      }

      id = R.id.confirmpassword;
      TextInputLayout confirmpassword = ViewBindings.findChildViewById(rootView, id);
      if (confirmpassword == null) {
        break missingId;
      }

      id = R.id.pass;
      TextView pass = ViewBindings.findChildViewById(rootView, id);
      if (pass == null) {
        break missingId;
      }

      id = R.id.password;
      TextInputLayout password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      return new ActivityChangepassBinding((ConstraintLayout) rootView, changepass, confirm,
          confirmpassword, pass, password);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}